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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_C_BPartner;
import org.adempiere.core.domains.models.I_C_CommissionLine;
import org.adempiere.core.domains.models.X_C_Commission;
import org.compiere.util.DB;

/**
 *	Model for Commission.
 *	(has Lines)
 *	
 *  @author Jorg Janke
 *  @version $Id: MCommission.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 *  @author victor.perez@e-evolution.com www.e-evolution.com [ 1867477 ] http://sourceforge.net/tracker/index.php?func=detail&aid=1867477&group_id=176962&atid=879332
 *	FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/766">
 * 		@see FR [ 766 ] Improve Commission Calculation</a>
 */
public class MCommission extends X_C_Commission
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1786202619739310928L;

	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Commission_ID id
	 *	@param trxName transaction
	 */
	public MCommission(Properties ctx, int C_Commission_ID, String trxName)
	{
		super(ctx, C_Commission_ID, trxName);
		if (C_Commission_ID == 0)
		{
		//	setName (null);
		//	setC_BPartner_ID (0);
		//	setC_Charge_ID (0);
		//	setC_Commission_ID (0);
		//	setC_Currency_ID (0);
			//
			setDocBasisType (DOCBASISTYPE_Invoice);	// I
			setFrequencyType (FREQUENCYTYPE_Monthly);	// M
			setListDetails (false);
		}
	}	//	MCommission

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MCommission(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MCommission

	/**
	 * 	Get Lines
	 *	@return array of lines
	 */
	public MCommissionLine[] getLines()
	{
		//[ 1867477 ]
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "IsActive='Y' AND C_Commission_ID=?";
		List<MCommissionLine> list  = new Query(getCtx(), I_C_CommissionLine.Table_Name, whereClause, get_TrxName())
		.setParameters(getC_Commission_ID())
		.setOrderBy("Line")
		.list();	
		//	Convert
		MCommissionLine[] retValue = new MCommissionLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getLines

	/**
	 * 	Set Date Last Run
	 *	@param DateLastRun date
	 */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		if (DateLastRun != null)
			super.setDateLastRun(DateLastRun);
	}	//	setDateLastRun

	/**
	 * 	Copy Lines From other Commission
	 *	@param otherCom commission
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MCommission otherCom)
	{
		if (otherCom == null)
			return 0;
		MCommissionLine[] fromLines = otherCom.getLines ();
		int count = 0;
		for (int i = 0; i < fromLines.length; i++)
		{
			MCommissionLine line = new MCommissionLine (getCtx(), 0, get_TrxName());
			PO.copyValues(fromLines[i], line, getAD_Client_ID(), getAD_Org_ID());
			line.setC_Commission_ID (getC_Commission_ID());
			if (line.save())
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE, "copyLinesFrom - Line difference - From=" + fromLines.length + " <> Saved=" + count);
		return count;
	}	//	copyLinesFrom
	
	/**
	 * Get Sales Representative for commission run
	 * @return
	 */
	public List<MBPartner> getSalesRepsOfCommission() {
		List<MBPartner> salesRepsList = new ArrayList<MBPartner>();
		if(getC_BPartner_ID() != 0) {
			salesRepsList.add((MBPartner) getC_BPartner());
		} else {
			salesRepsList = new Query(getCtx(), I_C_BPartner.Table_Name, "EXISTS(SELECT 1 FROM C_CommissionSalesRep csr "
					+ "WHERE csr.C_BPartner_ID = C_BPartner.C_BPartner_ID "
					+ "AND csr.C_Commission_ID = ?"
					+ "AND csr.IsActive = 'Y')", get_TrxName())
											.setParameters(getC_Commission_ID())
											.setOnlyActiveRecords(true)
											.list();
		}
		return salesRepsList;
	}
	
	/**********************************************************************************
	 * Helper Method for Get Amount from commission                                   *
	 **********************************************************************************/
	
	/**
	 * Get Commission of Employee sales representative from Commission Run
	 * @param bPartnerId
	 * @param from
	 * @param to
	 * @param docBasisType
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getCommissionAmt(int bPartnerId, Timestamp from, Timestamp to, String docBasisType, String trxName) {
		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause = new String();
		//	Add BPartner
		params.add(bPartnerId);
		//	Add From and To
		params.add(from);
		params.add(to);
		//	For Doc Basis Type
		if(docBasisType != null
				&& docBasisType.trim().length() > 0) {
			params.add(docBasisType);
			whereClause = "AND EXISTS(SELECT 1 FROM C_Commission c "
					+ "					INNER JOIN C_CommissionLine cl ON(cl.C_Commission_ID = c.C_Commission_ID) "
					+ "					WHERE cl.C_CommissionLine_ID = cah.C_CommissionLine_ID "
					+ "					AND c.DocBasisType = ?)";
		}
		String sql = new String("SELECT COALESCE(SUM(cah.CommissionAmt), 0) "
				+ "FROM C_CommissionRun crh "
				+ "INNER JOIN C_CommissionAmt cah ON(cah.C_CommissionRun_ID = crh.C_CommissionRun_ID) "
				+ "WHERE crh.DocStatus IN('CO', 'CL') "
				+ "AND cah.C_BPartner_ID = ? "
				+ "AND crh.DateDoc BETWEEN ? AND ? ")
				+ whereClause;
		BigDecimal value = DB.getSQLValueBDEx(null, sql.toString(), params);
		//	Valid Value
		return value;
	}
}	//	MCommission
