/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the                                  *
 * GNU General Public License as published                                    *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.util.support.mq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.adempiere.exceptions.AdempiereException;

public class PrinterMessage implements IMessageQueue {

	/**
	 * Constructor used for communicate file to print: the file should be PDF or XML format
	 * @param fileToPrint
	 */
	public PrinterMessage(File fileToPrint) {
		//	Validate null file
		if(fileToPrint == null) {
			throw new AdempiereException("@File@ @NotFound@");
		}
		//	Convert File
		try {
			stream = new FileInputStream(fileToPrint);
			fileName = fileToPrint.getName();
		} catch (FileNotFoundException e) {
			throw new AdempiereException(e);
		}
	}
	
	private InputStream stream = null;
	private String fileName = null;
	
	@Override
	public int getType() {
		return IMessageQueue.FILE;
	}

	@Override
	public Object getMessage() {
		return null;
	}

	@Override
	public String getMessageAsText() {
		return null;
	}

	@Override
	public InputStream getMessageAsInputStream() {
		return stream;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

}
