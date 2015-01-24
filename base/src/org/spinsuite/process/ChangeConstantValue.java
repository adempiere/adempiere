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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados.                    *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.process;

import java.util.logging.Level;

import org.compiere.model.X_WS_WebService_Para;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

/**
 * @author <a href="mailto:dixon.22martinez@gmail.com">Dixon Martinez</a>
 *
 */
public class ChangeConstantValue extends SvrProcess {

	private X_WS_WebService_Para 	m_WebService_Para 		= 	null;
	
	private int 					p_WebService_Para_ID	= 	0;
	
	private String 					p_ConstantValue			= 	null;
	
	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#prepare()
	 */
	@Override
	protected void prepare() {
		for (ProcessInfoParameter para : getParameter()){
			String name = para.getParameterName();
			
			if(para.getParameter() == null)	{
				;
			}else if(para.getParameterName().equals("ConstantValue")){
				p_ConstantValue = (String) para.getParameter();
			}else if(para.getParameterName().equals("ConstantValue1")){
				p_ConstantValue = (String) para.getParameter();
			}else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		p_WebService_Para_ID = getRecord_ID();
	}

	/* (non-Javadoc)
	 * @see org.compiere.process.SvrProcess#doIt()
	 */
	@Override
	protected String doIt() throws Exception {
		m_WebService_Para = new X_WS_WebService_Para(getCtx(), p_WebService_Para_ID, get_TrxName());
		if(m_WebService_Para != null) {
			m_WebService_Para.setConstantValue(p_ConstantValue);
			m_WebService_Para.saveEx();
		}
		return "";
	}

}
