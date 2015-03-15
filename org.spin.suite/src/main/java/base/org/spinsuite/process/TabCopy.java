/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.process;

import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.spinsuite.model.I_SPS_Tab;
import org.spinsuite.model.MSPSField;
import org.spinsuite.model.MSPSTab;


/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class TabCopy extends SvrProcess
{
	/** SFA Tab of copy						*/
	private int p_SPS_Tab_From_ID 			= 0;
	
	/**	SFA Tab to							*/
	private int p_SPS_Tab_To_ID 			= 0;
	
	/** SFA Tab From						*/
	private MSPSTab m_SPS_Tab_From 			= null;
	
	/** SFA Tab To							*/
	private MSPSTab m_SPS_Tab_To 			= null;
	
	@Override
	protected void prepare()
	{
		//	Initialize parameter's 
		for (ProcessInfoParameter para : getParameter()){
			String name = para.getParameterName();
			
			if(para.getParameter() == null)
				;
			else if(name.equals(I_SPS_Tab.COLUMNNAME_SPS_Tab_ID))
				p_SPS_Tab_From_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		
		//	Initialize parameter to copy
		p_SPS_Tab_To_ID = getRecord_ID();

	}//	Prepare

	protected String doIt() throws Exception
	{		
		//	Valid Tab From
		if(p_SPS_Tab_From_ID == 0)
			throw new AdempiereException("@SPS_Tab_ID@ @NotFound@");
		//	Valid Tab To
		if(p_SPS_Tab_To_ID == 0)
			throw new AdempiereException("@SPS_Tab_ID@ @NotFound@");
	
		log.info("To SPS_Tab_ID= " + p_SPS_Tab_To_ID + ", From= " +p_SPS_Tab_From_ID);

		//	Instance Tab From
		m_SPS_Tab_From = new MSPSTab(getCtx(), p_SPS_Tab_From_ID , get_TrxName());
		//	Validate Instance Tab From
		if(m_SPS_Tab_From == null
				|| m_SPS_Tab_From.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@ (from->) @SPS_Tab_ID@");

		//	Instance Tab To
		m_SPS_Tab_To = new MSPSTab(getCtx(), p_SPS_Tab_To_ID, get_TrxName());
		//	Validate Instance Tab From		
		if(m_SPS_Tab_To == null
				|| m_SPS_Tab_To.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@ (to<-) @SPS_Tab_ID@");

		//	Validate Table from not distinct table to		
		if(m_SPS_Tab_From.getSPS_Table_ID() != m_SPS_Tab_To.getSPS_Table_ID())
			throw new AdempiereUserError("@Error@ @SPS_Table_ID@");
		
		int fieldCount = 0;
		
		//	Copy Fields
		MSPSField [] olFields = m_SPS_Tab_From.getSFAFields(false, get_TrxName());
		
		for (MSPSField listsField : olFields)
		{
			MSPSField oldField = listsField;
			MSPSField newField = new MSPSField(m_SPS_Tab_To, oldField);
			
			if(newField.save())
				fieldCount++;
			else
				throw new AdempiereUserError("@Error@ @SPS_Field_ID@");
				
		}

		return "@Copied@ #" + fieldCount;
	}// doIt

}
