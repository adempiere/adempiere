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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * 	Web Media Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MMedia.java,v 1.7 2006/07/30 00:51:02 jjanke Exp $
 */
public class MMedia extends X_CM_Media
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2292852420984727096L;

	/**
	 * 	Get Media
	 *	@param project
	 *	@return server list
	 */
	public static MMedia[] getMedia (MWebProject project)
	{
		ArrayList<MMedia> list = new ArrayList<MMedia>();
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM CM_Media WHERE CM_WebProject_ID=? ORDER BY CM_Media_ID";
		try
		{
			pstmt = DB.prepareStatement (sql, project.get_TrxName());
			pstmt.setInt (1, project.getCM_WebProject_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				list.add (new MMedia (project.getCtx(), rs, project.get_TrxName()));
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MMedia[] retValue = new MMedia[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getMedia
	
	/**	Logger	*/
	private static CLogger s_log = CLogger.getCLogger (MMedia.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param CM_Media_ID id
	 *	@param trxName transaction
	 */
	public MMedia (Properties ctx, int CM_Media_ID, String trxName)
	{
		super (ctx, CM_Media_ID, trxName);
	}	//	MMedia

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MMedia (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MMedia
	
	/** Web Project			*/
	private MWebProject 	m_project = null;
	
	/**
	 * 	Get Web Project
	 *	@return web project
	 */
	public MWebProject getWebProject()
	{
		if (m_project == null)
			m_project = MWebProject.get(getCtx(), getCM_WebProject_ID());
		return m_project;
	}	//	getWebProject
	
	/**
	 * 	Get AD_Tree_ID
	 *	@return tree
	 */
	public int getAD_Tree_ID()
	{
		return getWebProject().getAD_TreeCMM_ID();
	}	//	getAD_Tree_ID;
	
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (isSummary())
		{
			setMediaType(null);
			setAD_Image_ID(0);
		}
			
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save.
	 * 	Insert
	 * 	- create tree
	 *	@param newRecord insert
	 *	@param success save success
	 *	@return true if saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		if (newRecord)
		{
			StringBuffer sb = new StringBuffer ("INSERT INTO AD_TreeNodeCMM "
				+ "(AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, "
				+ "AD_Tree_ID, Node_ID, Parent_ID, SeqNo) "
				+ "VALUES (")
				.append(getAD_Client_ID()).append(",0, 'Y', SysDate, 0, SysDate, 0,")
				.append(getAD_Tree_ID()).append(",").append(get_ID())
				.append(", 0, 999)");
			int no = DB.executeUpdate(sb.toString(), get_TrxName());
			if (no > 0)
				log.fine("#" + no + " - TreeType=CMM");
			else
				log.warning("#" + no + " - TreeType=CMM");
			return no > 0;
		}
		return success;
	}	//	afterSave
	
	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (!success)
			return success;
		//
		StringBuffer sb = new StringBuffer ("DELETE FROM AD_TreeNodeCMM ")
			.append(" WHERE Node_ID=").append(get_IDOld())
			.append(" AND AD_Tree_ID=").append(getAD_Tree_ID());
		int no = DB.executeUpdate(sb.toString(), get_TrxName());
		if (no > 0)
			log.fine("#" + no + " - TreeType=CMM");
		else
			log.warning("#" + no + " - TreeType=CMM");
		return no > 0;
	}	//	afterDelete

	/**
	 * 	Get File Name
	 *	@return file name return ID
	 */
	public String getFileName()
	{
		return get_ID() + getExtension();
	}	//	getFileName
	
	/**
	 * 	Get Extension with .
	 *	@return extension
	 */
	public String getExtension()
	{
		String mt = getMediaType();
		if (MEDIATYPE_ApplicationPdf.equals(mt))
			return ".pdf";
		if (MEDIATYPE_ImageGif.equals(mt))
			return ".gif";
		if (MEDIATYPE_ImageJpeg.equals(mt))
			return ".jpg";
		if (MEDIATYPE_ImagePng.equals(mt))
			return ".png";
		if (MEDIATYPE_TextCss.equals(mt))
			return ".css";
		//	Unknown
		return ".dat";
	}	//	getExtension

	/**
	 * 	Get Image
	 *	@return image or null
	 */
	public MImage getImage()
	{
		if (getAD_Image_ID() != 0)
			return MImage.get(getCtx(), getAD_Image_ID());
		return null;
	}	//	getImage
	
	/**
	 * 	Get Data as byte array
	 *	@return data or null
	 */
	public byte[] getData()
	{
		MImage image = getImage();
		if (image != null)
		{
			byte[] data = image.getData();
			if (data == null || data.length == 0)
				log.config("No Image Data");
		}
		
		//	Attachment
		MAttachment att = getAttachment();
		if (att == null || att.getEntryCount() == 0)
		{
			log.config("No Attachment");
			return null;
		}
		if (att.getEntryCount() > 1)
			log.warning(getName() + " - more then one attachment - " + att.getEntryCount());
		//
		MAttachmentEntry entry = att.getEntry(0);
		if (entry == null)
		{
			log.config("No Attachment Entry");
			return null;
		}
		byte[] buffer = entry.getData();
		if (buffer == null || buffer.length == 0)
		{
			log.config("No Attachment Entry Data");
			return null;
		}
		return buffer;
	}	//	getData

	/**
	 * 	Get Input Stream
	 *	@return imput stream or null
	 */
	public InputStream getInputStream()
	{
		byte[] buffer = getData();
		ByteArrayInputStream is = new ByteArrayInputStream(buffer);
		return is;
	}	//	getInputStream

	/**
	 * 	Get Updated timestamp of Attachment
	 *	@return updated or null if no attchment
	 */
	public Timestamp getAttachmentUpdated()
	{
		MAttachment att = getAttachment();
		if (att == null)
			return null;
		return att.getUpdated();
	}	//	getAttachmentUpdated
	
}	//	MMedia
