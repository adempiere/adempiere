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
package org.adempiere.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.AbstractBatchImport;
import org.compiere.impexp.ImpFormat;
import org.compiere.impexp.ImpFormatRow;
import org.compiere.util.CLogger;
import org.compiere.util.Env;


/**
 *  Add controller for file import
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1295">
 * 		@see FR [ 1295 ] File Loader can be improvement from connection</a>
 */
public abstract class FileImportController {
	/**
	 * 
	 */
	private static final int MAX_LOADED_LINES = 100;
	public static final int MAX_SHOWN_LINES = 10;

	/**
	 *	Initialize Panel
	 *  @param windowNo window
	 */
	public void init (int windowNo) {
		log.info("");
		this.windowNo = windowNo;
	}	//	init

	/**	Window No			*/
	private int         		windowNo = 0;

	private ArrayList<String>	data = new ArrayList<String>();
	private ImpFormat 			format;
	private int					record = -1;
	private Charset				charset;
	private InputStream 	inputStream;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(FileImportController.class);
	//
	public static final String s_none = "----";	//	no format indicator

	/**
	 * Set to busy dialog
	 * @param busy
	 */
	public abstract void setBusy(boolean busy);
	
	/**
	 * Clear View
	 */
	public abstract void clearView();
	
	/**
	 * Clear Preview
	 */
	public abstract void clearPreview();
	
	/**
	 * fill View
	 */
	public abstract void fillView();
	
	/**
	 * fill Info View
	 */
	public abstract void fillInfoView();
	
	/**
	 * Add line to view
	 * @param line
	 */
	public abstract void addLine(String line);
	
	/**
	 * Set Charset
	 * @param charset
	 */
	public void setCharset(Charset charset) {
		this.charset = charset;
	}
	
	/**
	 * Get Window No
	 * @return
	 */
	public int getWindowNo() {
		return windowNo;
	}
	
	/**
	 * Get Record No
	 * @return
	 */
	public int getRecordNo() {
		return record;
	}
	
	/**
	 * Add record No
	 * @param recordToAdded
	 */
	public void addRecordNo(int recordToAdded) {
		this.record += recordToAdded;
	}
	
	/**
	 * Set Record No
	 * @param record
	 */
	public void setRecordNo(int record) {
		this.record = record;
	}
	
	/**
	 * Get Import format row
	 * @param index
	 * @return
	 */
	public ImpFormatRow getRow(int index) {
		if((getRowCount() - 1) >= index) {
			return format.getRow(index);
		}
		return null;
	}
	
	/**
	 * Read data from external connection
	 */
	public void readFromConnection() {
		clearView();
		if(format == null) {
			return;
		}
		setBusy(true);
		//	Connection class
		AbstractBatchImport connectionDataLoader = null;
		//	Get class from parent
		Class<?> clazz = format.getConnectionClass();
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using GenericDeviceHandler");
			return;
		}
		//	
		Constructor<?> constructor = null;
		try {
			constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class});
			//	new instance
			connectionDataLoader = (AbstractBatchImport)constructor.newInstance(new Object[] {Env.getCtx()});
			//	Get data
			String error = connectionDataLoader.testConnetion();
			if(error != null) {
				log.warning("Connection error " + clazz + " (" + error + ")");
				return;
			}
			//	
			readData(connectionDataLoader.getData());
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg == null)
				msg = e.toString();
			log.warning("No transaction Constructor for " + clazz + " (" + msg + ")");
		}
		//	Update
		fillView();
		setBusy(false);
	}
	
	/**
	 * Add Data to import format
	 * @param data
	 */
	private void readData(List<String> externalData) {
		//	No data
		if(data == null)
			return;
		//	
		data.clear();
		clearView();
		for(String line : externalData) {
			data.add(line);
			if (data.size() <= MAX_LOADED_LINES) {
				addLine(line);
				addLine("\n");
			}
		}
		int index = 1;	//	second line as first may be heading
		if (data.size() == 1)
			index = 0;
		int length = 0;
		if (data.size() > 0)
			length = data.get(index).toString().length();
		fillView();
		setBusy(false);
		log.config("Records=" + data.size() 
			+ ", Length=" + length);
	}
	
	/**
	 * Reload/Load file
	 */
	public void cmd_reloadFile() {
		if (inputStream == null)
			return;
		//	Reload
		cmd_reloadFile(inputStream);
	}
	
	/**
	 * Reload/Load file
	 */
	public void cmd_reloadFile(InputStream inputStream) {
		this.inputStream = inputStream;
		if (inputStream == null)
			return;
		
		setBusy(true);
		data.clear();
		clearView();
		try {
			//  see NaturalAccountMap
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, charset), 10240);
			//	not safe see p108 Network pgm
			String s = null;
			while ((s = in.readLine()) != null) {
				data.add(s);
				if (data.size() <= MAX_LOADED_LINES) {
					addLine(s);
					addLine("\n");
				}
			}
			in.close();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
		int index = 1;	//	second line as first may be heading
		if (data.size() == 1)
			index = 0;
		int length = 0;
		if (data.size() > 0)
			length = data.get(index).toString().length();
		fillView();
		setBusy(false);
		log.config("Records=" + data.size() 
			+ ", Length=" + length);
	}	//	cmd_loadFile
	
	/**
	 * Get Data Size
	 * @return
	 */
	public int getDataSize() {
		if(data != null) {
			return data.size();
		}
		//	
		return 0;
	}
	
	/**
	 * Get import format row count
	 * @return
	 */
	public int getRowCount() {
		if(format != null) {
			return format.getRowCount();
		}
		//	
		return 0;
	}
	
	/**
	 * Get Record Length for a index
	 * @param index
	 * @return
	 */
	public int getRecordLength(int index) {
		if((getDataSize() - 1) >= index) {
			return data.get(index).length();
		}
		//	
		return 0;
	}
	
	/**
	 * Parse Line
	 * @param index
	 * @return
	 */
	public String[] parseLine(int index) {
		return format.parseLine(data.get(index), false, true, false);
	}
	
	/**
	 * Load Import Format
	 * @param impFormat
	 * @return
	 */
	public String loadFormat(String impFormat) {
		format = ImpFormat.load(impFormat);
		if (format == null) {
			return "FileImportNoFormat";
		}
		//	Default return
		return null;
	}
	
	/**
	 * Verify if the format if from connection
	 * @return
	 */
	public boolean isFromConnection() {
		return format.isFromConnection();
	}
	
	/**************************************************************************
	 *	Process File
	 */
	public String cmd_process() throws AdempiereException {
		if (format == null) {
			throw new AdempiereException("FileImportNoFormat");
		}
		log.config(format.getName());

		//	For all rows - update/insert DB table
		int row = 0;
		int imported = 0;
		for(String line : data) {
			row++;
			if (format.updateDB(Env.getCtx(), line, null)) {
				imported++;
			}
		}
		//	Clear
		data.clear();
		//
		return "@FileImportR/I@ (" + row + " / " + imported + "#)";
	}	//	cmd_process

}	//	FileImport
