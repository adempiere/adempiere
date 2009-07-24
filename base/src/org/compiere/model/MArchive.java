/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Archive Model
 * 
 * @author Jorg Janke
 * @version $Id: MArchive.java,v 1.3 2006/07/30 00:58:36 jjanke Exp $
 */
public class MArchive extends X_AD_Archive {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3476918478008050158L;

	/**
	 * Get Archives
	 * 
	 * @param ctx
	 *            context
	 * @param whereClause
	 *            optional where clause (starting with AND)
	 * @return archives
	 */
	public static MArchive[] get(Properties ctx, String whereClause) {
		ArrayList<MArchive> list = new ArrayList<MArchive>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM AD_Archive WHERE AD_Client_ID=?";
		if (whereClause != null && whereClause.length() > 0)
			sql += whereClause;
		sql += " ORDER BY Created";

		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, Env.getAD_Client_ID(ctx));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				list.add(new MArchive(ctx, rs, null));
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			s_log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		if (list.size() == 0)
			s_log.fine(sql);
		else
			s_log.finer(sql);
		//
		MArchive[] retValue = new MArchive[list.size()];
		list.toArray(retValue);
		return retValue;
	} // get

	/** Logger */
	private static CLogger s_log = CLogger.getCLogger(MArchive.class);

	private Integer m_inflated = null;

	private Integer m_deflated = null;

	/***************************************************************************
	 * Standard Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param AD_Archive_ID
	 *            id
	 * @param trxName
	 *            transaction
	 */
	public MArchive(Properties ctx, int AD_Archive_ID, String trxName) {
		super(ctx, AD_Archive_ID, trxName);
		initArchiveStoreDetails(ctx, trxName);
	} // MArchive

	/**
	 * Load Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param rs
	 *            result set
	 * @param trxName
	 *            transaction
	 */
	public MArchive(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		initArchiveStoreDetails(ctx, trxName);
	} // MArchive

	/**
	 * Constructor
	 * 
	 * @param ctx
	 *            context
	 * @param info
	 *            print info
	 * @param trxName
	 *            transaction
	 */
	public MArchive(Properties ctx, PrintInfo info, String trxName) {
		this(ctx, 0, trxName);
		setName(info.getName());
		setIsReport(info.isReport());
		//
		setAD_Process_ID(info.getAD_Process_ID());
		setAD_Table_ID(info.getAD_Table_ID());
		setRecord_ID(info.getRecord_ID());
		setC_BPartner_ID(info.getC_BPartner_ID());
		initArchiveStoreDetails(ctx, trxName);
	} // MArchive

	/** is this client using the file system for archive */
	private boolean isStoreArchiveOnFileSystem = false;

	/** archive (root) path - if file system is used */
	private String m_archivePathRoot = "";

	/**
	 * string replaces the archive root in stored xml file to allow the
	 * changing of the attachment root.
	 */
	private final String ARCHIVE_FOLDER_PLACEHOLDER = "%ARCHIVE_FOLDER%";

	/**
	 * Get the isStoreArchiveOnFileSystem and archivePath for the client.
	 * 
	 * @param ctx
	 * @param trxName
	 */
	private void initArchiveStoreDetails(Properties ctx, String trxName) {
		final MClient client = new MClient(ctx, this.getAD_Client_ID(), trxName);
		isStoreArchiveOnFileSystem = client.isStoreArchiveOnFileSystem();
		if (isStoreArchiveOnFileSystem) {
			if (File.separatorChar == '\\') {
				m_archivePathRoot = client.getWindowsArchivePath();
			} else {
				m_archivePathRoot = client.getUnixArchivePath();
			}
			if ("".equals(m_archivePathRoot)) {
				log.severe("no archivePath defined");
			} else if (!m_archivePathRoot.endsWith(File.separator)) {
				log.warning("archive path doesn't end with " + File.separator);
				m_archivePathRoot = m_archivePathRoot + File.separator;
				log.fine(m_archivePathRoot);
			}
		}
	}

	/**
	 * String Representation
	 * 
	 * @return info
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer("MArchive[");
		sb.append(get_ID()).append(",Name=").append(getName());
		if (m_inflated != null)
			sb.append(",Inflated=" + m_inflated);
		if (m_deflated != null)
			sb.append(",Deflated=" + m_deflated);
		sb.append("]");
		return sb.toString();
	} // toString

	public byte[] getBinaryData() {
		if (isStoreArchiveOnFileSystem) {
			return getBinaryDataFromFileSystem();
		}
		return getBinaryDataFromDB();
	}

	/**
	 * @return attachment data
	 */
	private byte[] getBinaryDataFromFileSystem() {
		if ("".equals(m_archivePathRoot)) {
			throw new IllegalArgumentException("no attachmentPath defined");
		}
		byte[] data = super.getBinaryData();
		m_deflated = null;
		m_inflated = null;
		if (data == null) {
			return null;
		}

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new ByteArrayInputStream(data));
			final NodeList entries = document.getElementsByTagName("entry");
			if(entries.getLength()!=1){
				log.severe("no archive entry found");
			}
				final Node entryNode = entries.item(0);
				final NamedNodeMap attributes = entryNode.getAttributes();
				final Node fileNode = attributes.getNamedItem("file");
				if(fileNode==null ){
					log.severe("no filename for entry");
					return null;
				}
				String filePath = fileNode.getNodeValue();
				log.fine("filePath: " + filePath);
				if(filePath!=null){
					filePath = filePath.replaceFirst(ARCHIVE_FOLDER_PLACEHOLDER, m_archivePathRoot.replaceAll("\\\\","\\\\\\\\"));
					//just to be shure...
					String replaceSeparator = File.separator;
					if(!replaceSeparator.equals("/")){
						replaceSeparator = "\\\\";
					}
					filePath = filePath.replaceAll("/", replaceSeparator);
					filePath = filePath.replaceAll("\\\\", replaceSeparator);
				}
				log.fine("filePath: " + filePath);
				final File file = new File(filePath);
				if (file.exists()) {
					// read files into byte[]
					final byte[] dataEntry = new byte[(int) file.length()];
					try {
						final FileInputStream fileInputStream = new FileInputStream(file);
						fileInputStream.read(dataEntry);
						fileInputStream.close();
					} catch (FileNotFoundException e) {
						log.severe("File Not Found.");
						e.printStackTrace();
					} catch (IOException e1) {
						log.severe("Error Reading The File.");
						e1.printStackTrace();
					}
					return dataEntry;
				} else {
					log.severe("file not found: " + file.getAbsolutePath());
					return null;
				}

		} catch (SAXException sxe) {
			// Error generated during parsing)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
			log.severe(x.getMessage());

		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();
			log.severe(pce.getMessage());

		} catch (IOException ioe) {
			// I/O error
			ioe.printStackTrace();
			log.severe(ioe.getMessage());
		}
		return null;
	}

	/**
	 * Get Binary Data. (inflate)
	 * 
	 * @return inflated data
	 */
	private byte[] getBinaryDataFromDB() {
		byte[] deflatedData = super.getBinaryData();
		m_deflated = null;
		m_inflated = null;
		if (deflatedData == null)
			return null;
		//
		log.fine("ZipSize=" + deflatedData.length);
		m_deflated = new Integer(deflatedData.length);
		if (deflatedData.length == 0)
			return null;

		byte[] inflatedData = null;
		try {
			ByteArrayInputStream in = new ByteArrayInputStream(deflatedData);
			ZipInputStream zip = new ZipInputStream(in);
			ZipEntry entry = zip.getNextEntry();
			if (entry != null) // just one entry
			{
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[2048];
				int length = zip.read(buffer);
				while (length != -1) {
					out.write(buffer, 0, length);
					length = zip.read(buffer);
				}
				//
				inflatedData = out.toByteArray();
				log.fine("Size=" + inflatedData.length + " - zip=" + entry.getCompressedSize()
						+ "(" + entry.getSize() + ") "
						+ (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
				m_inflated = new Integer(inflatedData.length);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
			inflatedData = null;
		}
		return inflatedData;
	} // getBinaryData

	/**
	 * Get Data as Input Stream
	 * 
	 * @return input stream or null
	 */
	public InputStream getInputStream() {
		byte[] inflatedData = getBinaryData();
		if (inflatedData == null)
			return null;
		return new ByteArrayInputStream(inflatedData);
	} // getInputStream

	/**
	 * Save Binary Data to file system or db.
	 * 
	 * @param inflatedData
	 *            inflated data
	 */
	public void setBinaryData(byte[] inflatedData) {
		if (isStoreArchiveOnFileSystem) {
			saveBinaryDataIntoFileSystem(inflatedData);
		} else {
			saveBinaryDataIntoDB(inflatedData);
		}
	}

	/**
	 * Save to file system. If the MArchive is not saved yet (id==0) it will
	 * first save the MArchive object because it uses the id as filename.
	 * @param inflatedData
	 */
	private void saveBinaryDataIntoFileSystem(byte[] inflatedData) {
		if ("".equals(m_archivePathRoot)) {
			throw new IllegalArgumentException("no attachmentPath defined");
		}
		if (inflatedData == null || inflatedData.length == 0) {
			throw new IllegalArgumentException("InflatedData is NULL");
		}
		if(this.get_ID()==0){
			//set binary data otherwise save will fail
			super.setBinaryData(new byte[]{'0'});
			if(!this.save()) {
				throw new IllegalArgumentException("unable to save MArchive");
			}
		}
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		BufferedOutputStream out = null;
		try {
			// create destination folder
			final File destFolder = new File(m_archivePathRoot + File.separator
					+ getArchivePathSnippet());
			if (!destFolder.exists()) {
				if (!destFolder.mkdirs()) {
					log.warning("unable to create folder: " + destFolder.getPath());
				}
			}
			// write to pdf
			final File destFile = new File(m_archivePathRoot + File.separator
					+ getArchivePathSnippet() + this.get_ID() + ".pdf");

			out = new BufferedOutputStream(new FileOutputStream(destFile));
			out.write(inflatedData);
			out.flush();

			//create xml entry
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.newDocument();
			final Element root = document.createElement("archive");
			document.appendChild(root);
			document.setXmlStandalone(true);
			final Element entry = document.createElement("entry");
			entry.setAttribute("file", ARCHIVE_FOLDER_PLACEHOLDER + getArchivePathSnippet() + this.get_ID() + ".pdf");
			root.appendChild(entry);
			final Source source = new DOMSource(document);
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final Result result = new StreamResult(bos);
			final Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
			final byte[] xmlData = bos.toByteArray();
			log.fine(bos.toString());
			//store xml in db
			super.setBinaryData(xmlData);

		} catch (Exception e) {
			log.log(Level.SEVERE, "saveLOBData", e);
			m_deflated = null;
			super.setBinaryData(null);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (Exception e) {	}
			}
		}

	}

	/**
	 * Save Binary Data to database.
	 * 
	 * @param inflatedData
	 *            inflated data
	 */
	private void saveBinaryDataIntoDB(byte[] inflatedData) {
		if (inflatedData == null || inflatedData.length == 0)
			throw new IllegalArgumentException("InflatedData is NULL");
		m_inflated = new Integer(inflatedData.length);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(out);
		zip.setMethod(ZipOutputStream.DEFLATED);
		zip.setLevel(Deflater.BEST_COMPRESSION);
		zip.setComment("adempiere");
		//
		byte[] deflatedData = null;
		try {
			ZipEntry entry = new ZipEntry("AdempiereArchive");
			entry.setTime(System.currentTimeMillis());
			entry.setMethod(ZipEntry.DEFLATED);
			zip.putNextEntry(entry);
			zip.write(inflatedData, 0, inflatedData.length);
			zip.closeEntry();
			log.fine(entry.getCompressedSize() + " (" + entry.getSize() + ") "
					+ (entry.getCompressedSize() * 100 / entry.getSize()) + "%");
			//
			// zip.finish();
			zip.close();
			deflatedData = out.toByteArray();
			log.fine("Length=" + inflatedData.length);
			m_deflated = new Integer(deflatedData.length);
		} catch (Exception e) {
			log.log(Level.SEVERE, "saveLOBData", e);
			deflatedData = null;
			m_deflated = null;
		}
		super.setBinaryData(deflatedData);
	} // setBinaryData

	/**
	 * Get Created By (User) Name
	 * 
	 * @return name
	 */
	public String getCreatedByName() {
		String name = "?";
		String sql = "SELECT Name FROM AD_User WHERE AD_User_ID=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getCreatedBy());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				name = rs.getString(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		}
		try {
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			pstmt = null;
		}
		return name;
	} // getCreatedByName

	/**
	 * Returns the archive path (snippet), containing client, org and archive
	 * id. The process, table and record id are only included when they are not
	 * null.
	 * 
	 * @return String
	 */
	private String getArchivePathSnippet() {
		String path = this.getAD_Client_ID() + File.separator + this.getAD_Org_ID()
				+ File.separator;
		if (this.getAD_Process_ID() > 0) {
			path = path + this.getAD_Process_ID() + File.separator;
		}
		if (this.getAD_Table_ID() > 0) {
			path = path + this.getAD_Table_ID() + File.separator;
		}
		if (this.getRecord_ID() > 0) {
			path = path + this.getRecord_ID() + File.separator;
		}
		// path = path + this.get_ID() + ".pdf";
		return path;
	}

	/**
	 * Before Save
	 * 
	 * @param newRecord
	 *            new
	 * @return true if can be saved
	 */
	protected boolean beforeSave(boolean newRecord) {
		// Binary Data is Mandatory
		byte[] data = super.getBinaryData();
		if (data == null || data.length == 0)
			return false;
		//
		log.fine(toString());
		return true;
	} // beforeSave

} // MArchive
