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

package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MOpportunity;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * 
 * Convert lead into business partner and opportunity
 * @author Paul Bowden, Adaxa Pty Ltd
 *
 */
public class ConvertLead extends SvrProcess {

	private boolean p_createOpportunity = true;
	private BigDecimal p_opportunityAmt = null;
	private int p_AD_User_ID = 0;
	private Timestamp p_expectedCloseDate = null;
	private int p_C_SalesStage_ID = 0;
	private String p_Description = null;
	private int p_C_Currency_ID = 0;
	private int p_SalesRep_ID = 0;

	@Override
	protected String doIt() throws Exception {
		if (p_AD_User_ID <= 0)
			throw new FillMandatoryException("AD_User_ID");
		
		MUser lead = new MUser(getCtx(), p_AD_User_ID, get_TrxName());
		if (!lead.isSalesLead() || lead.getC_BPartner_ID() != 0)
			throw new AdempiereUserError("Lead already converted");
		
		MBPartner bp = MBPartner.getTemplate(getCtx(), getAD_Client_ID());
		bp.set_TrxName(get_TrxName());
		if ( !Util.isEmpty(lead.getBPName()) )
			bp.setName(lead.getBPName());
		else
			bp.setName(lead.getName());
		
		bp.saveEx();
		addLog("Business Partner created.");
		
		lead.setC_BPartner_ID(bp.getC_BPartner_ID());
		
		if (lead.getC_Location_ID() != 0)
		{
			MLocation leadAddress = (MLocation) lead.getC_Location();
			MBPartnerLocation loc = new MBPartnerLocation(bp);
			MLocation address = new MLocation(getCtx(), 0, get_TrxName());
			PO.copyValues(leadAddress, address);
			address.saveEx();
			
			loc.setC_Location_ID(address.getC_Location_ID());
			loc.setPhone(lead.getPhone());
			loc.setPhone2(lead.getPhone2());
			loc.setFax(lead.getFax());
			loc.saveEx();
			
			lead.setC_BPartner_Location_ID(loc.getC_BPartner_Location_ID());
			
			addLog("Contact Location added.");
		}
		
		// company address
		if (lead.getBP_Location_ID() != 0)
		{
			MLocation leadAddress = (MLocation) lead.getBP_Location();
			MBPartnerLocation loc = new MBPartnerLocation(bp);
			MLocation address = new MLocation(getCtx(), 0, get_TrxName());
			PO.copyValues(leadAddress, address);
			address.saveEx();
			
			loc.setC_Location_ID(address.getC_Location_ID());
			loc.saveEx();
			
			addLog("BP Address added.");
		}
		
		if (p_createOpportunity )
		{
			MOpportunity op = new MOpportunity(getCtx(), 0, get_TrxName());
			op.setAD_User_ID(lead.getAD_User_ID());
			op.setC_BPartner_ID(bp.getC_BPartner_ID());
			op.setExpectedCloseDate(p_expectedCloseDate != null ? p_expectedCloseDate : new Timestamp(System.currentTimeMillis()));
			op.setOpportunityAmt(p_opportunityAmt != null ? p_opportunityAmt : Env.ZERO);
			
			if ( p_C_SalesStage_ID > 0 )
				op.setC_SalesStage_ID(p_C_SalesStage_ID);
			
			String sql = "SELECT Probability FROM C_SalesStage WHERE C_SalesStage_ID = ?";
			BigDecimal probability = DB.getSQLValueBD(get_TrxName(), sql, p_C_SalesStage_ID);
			op.setProbability(probability != null ? probability : Env.ZERO);
				
			op.setDescription(p_Description);
			
			if ( p_C_Currency_ID > 0 )
				op.setC_Currency_ID(p_C_Currency_ID);
			else
				op.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			
			if (p_SalesRep_ID > 0 )
				op.setSalesRep_ID(p_SalesRep_ID);
			else if ( lead.getSalesRep_ID() > 0 ) 
				op.setSalesRep_ID(lead.getSalesRep_ID());
			else
				op.setSalesRep_ID(Env.getContextAsInt(getCtx(), "#SalesRep_ID"));
			
			op.setC_Campaign_ID(lead.getC_Campaign_ID());
			
			op.saveEx();
			
			addLog("Opportunity created.");
		}
		
		lead.setIsSalesLead(false);
		lead.setLeadStatus(MUser.LEADSTATUS_Converted);
		lead.saveEx();
		
		addLog("Lead converted.");
		
		return "@OK@";
	}

	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter para : paras)
		{
			String name = para.getParameterName();
			if ( Util.isEmpty(name) )
				;
			else if ("AD_User_ID".equals(name))
				p_AD_User_ID = para.getParameterAsInt();
			else if ( "CreateOpportunity".equals(name))
				p_createOpportunity  = para.getParameterAsBoolean();
			else if ( "OpportunityAmt".equals(name))
				p_opportunityAmt  = para.getParameterAsBigDecimal();
			else if ("ExpectedCloseDate".equals(name))
				p_expectedCloseDate  = para.getParameterAsTimestamp();
			else if ("C_SalesStage_ID".equals(name))
				p_C_SalesStage_ID  = para.getParameterAsInt();
			else if ("SalesRep_ID".equals(name))
				p_SalesRep_ID   = para.getParameterAsInt();
			else if ("Description".equals(name))
				p_Description = para.getParameterAsString();
			else if ("C_Currency_ID".equals(name))
				p_C_Currency_ID  = para.getParameterAsInt();
			else 
			{
				log.log(Level.WARNING, "Unknown parameter: " + name);
			}
			
			if ( MUser.Table_ID == getTable_ID() )
				p_AD_User_ID  = getRecord_ID();
			
			if (p_C_SalesStage_ID == 0)
			{
				String sql = "SELECT MIN(s.C_SalesStage_ID) FROM C_SalesStage s WHERE s.AD_Client_ID = ? AND s.IsActive = 'Y' " +
						"AND NOT EXISTS (SELECT * FROM C_SalesStage ss WHERE ss.AD_Client_ID=s.AD_Client_ID AND ss.IsActive='Y' AND ss.Value < s.Value)";
				p_C_SalesStage_ID = DB.getSQLValue(get_TrxName(), sql, getAD_Client_ID());
			}
			
		}

	}

}
