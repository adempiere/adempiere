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

import org.compiere.process.ProcessInfo;


/**
 *	Print Info
 *	
 *  @author Jorg Janke
 *  @version $Id: PrintInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class PrintInfo
{
	/**
	 * 	Process Archive Info
	 *	@param pi process info
	 */
	public PrintInfo (ProcessInfo pi)
	{
		setName(pi.getTitle());
		setAD_Process_ID(pi.getAD_Process_ID());
		setAD_Table_ID(pi.getTable_ID());
		setRecord_ID(pi.getRecord_ID());
	}	//	PrintInfo
	
	
	/**
	 * 	Document Archive Info
	 *	@param Name name
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param C_BPartner_ID bpartner
	 */
	public PrintInfo (String Name, int AD_Table_ID, int Record_ID, int C_BPartner_ID)
	{
		setName(Name);
		setAD_Table_ID(AD_Table_ID);
		setRecord_ID(Record_ID);
		setC_BPartner_ID(C_BPartner_ID);
	}	//	ArchiveInfo

	/**
	 * 	Report Archive Info
	 *	@param Name name
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 */
	public PrintInfo (String Name, int AD_Table_ID, int Record_ID)
	{
		setName(Name);
		setAD_Table_ID(AD_Table_ID);
		setRecord_ID(Record_ID);
	}	//	ArchiveInfo
	
	boolean m_withDialog = false;
	private int m_copies = 1;
	private boolean m_isDocumentCopy = false;
	private String m_printerName = null;
	//
	private String m_Name = null;
	private String m_Description = null;
	private String m_Help = null;
	private int m_AD_Process_ID = 0;
	private int m_AD_Table_ID = 0;
	private int m_Record_ID = 0;
	private int m_C_BPartner_ID = 0;
	
	
	/**
	 * 	Is this a Report
	 *	@return true if report
	 */
	public boolean isReport()
	{
		return m_AD_Process_ID != 0	//	Menu Report
			|| m_C_BPartner_ID == 0;
	}	//	isReport
	
	/**
	 * 	Is this a Document
	 *	@return true if BPartner defined
	 */
	public boolean isDocument()
	{
		return m_C_BPartner_ID != 0;
	}	//	isDocument
	
	
	/**
	 * @return Returns the copies.
	 */
	public int getCopies ()
	{
		return m_copies;
	}
	/**
	 * @param copies The copies to set.
	 */
	public void setCopies (int copies)
	{
		m_copies = copies;
	}
	/**
	 * @return Returns the printerName.
	 */
	public String getPrinterName ()
	{
		return m_printerName;
	}
	/**
	 * @param printerName The printerName to set.
	 */
	public void setPrinterName (String printerName)
	{
		m_printerName = printerName;
	}
	/**
	 * @return Returns the withDialog.
	 */
	public boolean isWithDialog ()
	{
		return m_withDialog;
	}
	/**
	 * @param withDialog The withDialog to set.
	 */
	public void setWithDialog (boolean withDialog)
	{
		m_withDialog = withDialog;
	}
	/**
	 * @param isDocumentCopy The isDocument to set.
	 */
	public void setDocumentCopy (boolean isDocumentCopy)
	{
		m_isDocumentCopy = isDocumentCopy;
	}
	/**
	 * 	Document Copy
	 *	@return true if copy
	 */
	public boolean isDocumentCopy()
	{
		return m_isDocumentCopy;
	}	//	isDocument
	/**
	 * @return Returns the aD_Process_ID.
	 */
	public int getAD_Process_ID ()
	{
		return m_AD_Process_ID;
	}
	/**
	 * @param process_ID The aD_Process_ID to set.
	 */
	public void setAD_Process_ID (int process_ID)
	{
		m_AD_Process_ID = process_ID;
	}
	/**
	 * @return Returns the aD_Table_ID.
	 */
	public int getAD_Table_ID ()
	{
		return m_AD_Table_ID;
	}
	/**
	 * @param table_ID The aD_Table_ID to set.
	 */
	public void setAD_Table_ID (int table_ID)
	{
		m_AD_Table_ID = table_ID;
	}
	/**
	 * @return Returns the c_BPartner_ID.
	 */
	public int getC_BPartner_ID ()
	{
		return m_C_BPartner_ID;
	}
	/**
	 * @param partner_ID The c_BPartner_ID to set.
	 */
	public void setC_BPartner_ID (int partner_ID)
	{
		m_C_BPartner_ID = partner_ID;
	}
	/**
	 * @return Returns the description.
	 */
	public String getDescription ()
	{
		return m_Description;
	}
	/**
	 * @param description The description to set.
	 */
	public void setDescription (String description)
	{
		m_Description = description;
	}
	/**
	 * @return Returns the help.
	 */
	public String getHelp ()
	{
		return m_Help;
	}
	/**
	 * @param help The help to set.
	 */
	public void setHelp (String help)
	{
		m_Help = help;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName ()
	{
		if (m_Name == null || m_Name.length() == 0)
			return"Unknown";
		return m_Name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName (String name)
	{
		m_Name = name;
	}
	/**
	 * @return Returns the record_ID.
	 */
	public int getRecord_ID ()
	{
		return m_Record_ID;
	}
	/**
	 * @param record_ID The record_ID to set.
	 */
	public void setRecord_ID (int record_ID)
	{
		m_Record_ID = record_ID;
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer("PrintInfo[");
		sb.append(getName());
		if (getAD_Process_ID() != 0)
			sb.append(",AD_Process_ID=").append(getAD_Process_ID());
		if (getAD_Table_ID() != 0)
			sb.append(",AD_Table_ID=").append(getAD_Table_ID());
		if (getRecord_ID()!= 0)
			sb.append(",Record_ID=").append(getRecord_ID());
		if (getC_BPartner_ID() != 0)
			sb.append(",C_BPartner_ID=").append(getC_BPartner_ID());
		
		sb.append("]");
		return sb.toString();
	}	//	toString
	
}	//	ArchiveInfo
