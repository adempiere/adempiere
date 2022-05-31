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

package org.compiere.crm.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.crm.model.MOpportunity;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
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
public class ConvertLead extends ConvertLeadAbstract {
	/**	User	*/
	private int userId = 0;
	
	@Override
	protected void prepare() {
		super.prepare();
		if (MUser.Table_ID == getTable_ID()) {
			userId = getRecord_ID();
		} else {
			userId = getAD_User_ID();
		}
		
		if (getSalesStageId() == 0) {
			String sql = "SELECT MIN(s.C_SalesStage_ID) FROM C_SalesStage s WHERE s.AD_Client_ID = ? AND s.IsActive = 'Y' " +
					"AND NOT EXISTS (SELECT * FROM C_SalesStage ss WHERE ss.AD_Client_ID=s.AD_Client_ID AND ss.IsActive='Y' AND ss.Value < s.Value)";
			setSalesStageId(DB.getSQLValue(get_TrxName(), sql, getAD_Client_ID()));
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		if (userId <= 0)
			throw new FillMandatoryException("AD_User_ID");
		
		MUser lead = new MUser(getCtx(), userId, get_TrxName());
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
		
		if (isCreateOpportunity() )
		{
			MOpportunity op = new MOpportunity(getCtx(), 0, get_TrxName());
			op.setAD_User_ID(lead.getAD_User_ID());
			op.setC_BPartner_ID(bp.getC_BPartner_ID());
			op.setExpectedCloseDate(getExpectedCloseDate() != null ? getExpectedCloseDate() : new Timestamp(System.currentTimeMillis()));
			op.setOpportunityAmt(getOpportunityAmt() != null ? getOpportunityAmt() : Env.ZERO);
			
			if ( getSalesStageId() > 0 )
				op.setC_SalesStage_ID(getSalesStageId());
			
			String sql = "SELECT Probability FROM C_SalesStage WHERE C_SalesStage_ID = ?";
			BigDecimal probability = DB.getSQLValueBD(get_TrxName(), sql, getSalesStageId());
			op.setProbability(probability != null ? probability : Env.ZERO);
				
			op.setDescription(getDescription());
			
			if ( getCurrencyId() > 0 )
				op.setC_Currency_ID(getCurrencyId());
			else
				op.setC_Currency_ID(Env.getContextAsInt(getCtx(), "$C_Currency_ID"));
			
			if (getSalesRepId() > 0 )
				op.setSalesRep_ID(getSalesRepId());
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
}
