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
import org.spinsuite.model.MSPSField;
import org.spinsuite.model.MSPSTab;
import org.spinsuite.model.MSPSWindow;
import org.spinsuite.model.X_SPS_Window;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class WindowCopy extends SvrProcess
{

	/**	SFA Window To					*/
	private int			p_SPS_Window_To_ID 	= 0;
	
	/**	SFA Window From					*/
	private int			p_SPS_Window_From_ID 	= 0;
	
	/** SFA Window From					*/
	private MSPSWindow m_SFAWindowFrom 			= null;
	
	/** SFA Window To					*/
	private MSPSWindow m_SFAWindowTo 			= null;
	
	
	private int tabCount = 0;
	int fieldCount = 0;
	
	MSPSTab [] oldTab = null;
	
	
	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter()){
			String name = para.getParameterName();
			
			if(para.getParameter() == null)	{
				;
			}else if(para.getParameterName().equals(X_SPS_Window.COLUMNNAME_SPS_Window_ID)){
				p_SPS_Window_From_ID = para.getParameterAsInt();
			}else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		p_SPS_Window_To_ID = getRecord_ID();
		
	}//	Prepare

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception
	{
		//	Valid Window From
		if(p_SPS_Window_From_ID == 0)
			throw new AdempiereException("@SPS_Window_ID@ @NotFound@");
		//	Valid Window To
		if(p_SPS_Window_To_ID == 0)
			throw new AdempiereException("@SPS_Window_ID@ @NotFound@");
		
		log.info("doIt - To SPS_Window_ID= " + p_SPS_Window_To_ID +" , From= " + p_SPS_Window_From_ID);
		
		//	Instance Window From
		m_SFAWindowFrom = new MSPSWindow(getCtx(), p_SPS_Window_From_ID, get_TrxName());
		
		if(m_SFAWindowFrom == null
				|| m_SFAWindowFrom.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@ (from->) @SPS_Window_ID@");
		
		//	Instance Window To
		m_SFAWindowTo = new MSPSWindow(getCtx(), p_SPS_Window_To_ID, get_TrxName());
		if(m_SFAWindowTo == null
				|| m_SFAWindowTo.get_ID() == 0)
			throw new AdempiereUserError("@NotFound@ (to<-) @SPS_Window_ID@");
		
		
		oldTab = m_SFAWindowFrom.get(false, get_TrxName());
	
		for (MSPSTab lists : oldTab)
		{
			MSPSTab oldSFATab = lists;
			MSPSTab newTab = new MSPSTab(m_SFAWindowTo, lists);
			
			if(newTab.save()){
				tabCount++;
				//	Copy Fields
				MSPSField [] olFields = oldSFATab.getSFAFields(false, get_TrxName());
				
				for (MSPSField listsField : olFields)
				{
					MSPSField oldField = listsField;
					MSPSField newField = new MSPSField(newTab, oldField);
					
					if(newField.save())
						fieldCount++;
					else
						throw new AdempiereUserError("@Error@ @SPS_Field_ID@");
						
				}
			}else
				throw new AdempiereUserError("@Error@ @SPS_Tab_ID@");
		}
		return "@Copied@ #" + tabCount + "/" + fieldCount;

	}

}
