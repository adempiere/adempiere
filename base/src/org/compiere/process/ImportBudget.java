package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPeriod;
import org.compiere.model.X_GL_JournalBatch;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Import Budget
 * 
 * @author Nikunj Panelia
 *
 */
public class ImportBudget extends SvrProcess 
{	
	/** Date of accounting */
	private Timestamp 		acctDate=null ;
	/** No Of periods*/
	private int 			noOfPeriods=0;
	/** Financial year*/
	private int 			finCalendar=0;
	/** Gl Document No*/
	private String		    glDocNo=null;
	/**	Organization to be imported to	*/
	private int 			m_AD_Org_ID = 0;
	/** Batch Description*/
	private String 			batchDesc= null;
	/**	Acct Schema to be imported to	*/
	private int				m_C_AcctSchema_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_DeleteOldImported = false;
	
	private String 			docAction= null;
	private static Logger log = CLogger.getCLogger(ImportBudget.class);
	

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateAcct"))
				acctDate = para[i].getParameterAsTimestamp();
			else if (name.equals("No_Of_Periods"))
				noOfPeriods = para[i].getParameterAsInt();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("BatchDescription"))
				batchDesc = para[i].getParameterAsString();
			else if (name.equals("C_AcctSchema_ID"))
				m_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_DeleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("DocAction"))
				docAction = para[i].getParameterAsString();		
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	
	/**
	 * 
	 * update I_Budget table using import data  
	 * @throws SQLException
	 */
	private boolean updateTable() throws SQLException
	{

		StringBuffer sql = null;
		int no = 0;
		
		
		// Delete Old Imported
		if (m_DeleteOldImported) {
			sql = new StringBuffer("DELETE I_Budget  "
					+ "WHERE I_IsImported='Y'").append(clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
		}
		//Set IsActive, Created/Updated,Client
		sql = new StringBuffer("UPDATE I_Budget  "
				+ "SET IsActive = COALESCE (IsActive, 'Y'),"
				+ " Created = COALESCE (Created, SysDate),"
				+ " CreatedBy = COALESCE (CreatedBy, 0),"
				+ " Updated = COALESCE (Updated, SysDate),"
				+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
				+ " I_ErrorMsg = ' '," + " I_IsImported = 'N' , "
				+ " AD_Client_ID= " + Env.getAD_Client_ID(getCtx()) 
				+ " WHERE (I_IsImported<>'Y' OR I_IsImported IS NULL) ")
				.append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info("Reset=" + no);			
			
	
		//Set organization
		sql = new StringBuffer("UPDATE I_Budget i " + "SET AD_Org_ID= ")
				.append(m_AD_Org_ID).append(" WHERE I_IsImported<>'Y'")
				.append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Client Organizarion=" + no);
					
		// Set Accountschema from name
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_AcctSchema_ID=(SELECT a.C_AcctSchema_ID FROM C_AcctSchema a"
						+ " WHERE i.AcctSchemaName=a.Name AND i.AD_Client_ID=a.AD_Client_ID) "
						+ "WHERE C_AcctSchema_ID IS NULL AND AcctSchemaName IS NOT NULL"
						+ " AND I_IsImported<>'Y'").append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName()); 
		
		// Set defualt Accountschema 
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_AcctSchema_ID= ").append(m_C_AcctSchema_ID)
						.append( " WHERE  I_IsImported<>'Y'").append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set account schema=" + no);	
		
		//Error accounting schema
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E',I_ErrorMsg=I_ErrorMsg||'ERR=Invalid AcctSchema, '"
						+ "WHERE (C_AcctSchema_ID IS NULL OR C_AcctSchema_ID=0"
						+ " OR NOT EXISTS (SELECT * FROM C_AcctSchema a WHERE i.AD_Client_ID=a.AD_Client_ID))"
						+ " AND I_IsImported<>'Y'").append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid AcctSchema=" + no);
			return false;
		}
		
		//Set Gl_Budget_ID from budget code
		sql = new StringBuffer(
				"UPDATE I_Budget i SET GL_Budget_ID= (SELECT g.GL_Budget_ID From GL_Budget g where i.BudgetCode=g.Name and i.AD_Client_ID= g.AD_Client_ID ) "
						+ " WHERE GL_Budget_ID IS NULL and BudgetCode IS NOT NULL AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		
		// Error Budget
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Budget, '"
						+ " WHERE (GL_Budget_ID IS NULL OR GL_Budget_ID=0)"
						+ " AND I_IsImported<>'Y'").append(clientCheck).append(
				docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid Budget=" + no);
			return false;
		}
				
		// Set Asset
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET A_Asset_ID=(SELECT a.A_Asset_ID FROM A_Asset a"
						+ " WHERE a.Value=i.AssetValue AND  i.AD_Client_ID=a.AD_Client_ID) "
						+ "WHERE A_Asset_ID IS NULL AND AssetValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());

		// Error Asset
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Asset, '"
						+ "WHERE A_Asset_ID IS NULL AND AssetValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());

		if (no != 0){
			log.warning("Invalid Asset=" + no);
			return false;
		}

		//	** Account Elements (optional) **
	
		//account_id
		sql = new StringBuffer ("UPDATE I_Budget i "
						+ "SET Account_ID=(SELECT MAX(ev.C_ElementValue_ID) FROM C_ElementValue ev"
						+ " INNER JOIN C_Element e ON (e.C_Element_ID=ev.C_Element_ID)"
						+ " INNER JOIN C_AcctSchema_Element ase ON (e.C_Element_ID=ase.C_Element_ID AND ase.ElementType='AC')"
						+ " WHERE ev.Value=i.AccountValue AND ev.IsSummary='N'"
						+ " AND i.C_AcctSchema_ID=ase.C_AcctSchema_ID AND i.AD_Client_ID=ev.AD_Client_ID) "
						+ "WHERE Account_ID IS NULL AND AccountValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Account from Value=" + no);
		
		//Error account Id
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ " SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Account, '"
						+ " WHERE (Account_ID IS NULL OR Account_ID=0)"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0){
			log.warning("Invalid Account=" + no);
			return false;
		}
		
		
		//Set BPartner
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_BPartner_ID=(SELECT bp.C_BPartner_ID FROM C_BPartner bp"
						+ " WHERE bp.Value=i.BPartnerValue AND bp.IsSummary='N' AND i.AD_Client_ID=bp.AD_Client_ID) "
						+ "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set BPartner from Value=" + no);
		
		//Error Bpartner
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid BPartner, '"
						+ "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());	
		if (no != 0){
			log.warning("Invalid BPartner=" + no);
			return false;
		}

		// Set Product
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET M_Product_ID=(SELECT MAX(p.M_Product_ID) FROM M_Product p"
						+ " WHERE (p.Value=i.ProductValue)"
						+ " AND p.IsSummary='N' AND i.AD_Client_ID=p.AD_Client_ID) "
						+ "WHERE M_Product_ID IS NULL AND (ProductValue IS NOT NULL )"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Product from Value=" + no);
		
		//Error product
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, '"
						+ "WHERE M_Product_ID IS NULL AND (ProductValue IS NOT NULL )"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		if (no != 0){
			log.warning("Invalid Product=" + no);
			return false;
		}

		// Set Project
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_Project_ID=(SELECT p.C_Project_ID FROM C_Project p"
						+ " WHERE p.Value=i.ProjectValue AND p.IsSummary='N' AND i.AD_Client_ID=p.AD_Client_ID) "
						+ "WHERE C_Project_ID IS NULL AND ProjectValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
	    log.fine("Set Project from Value=" + no);
	    
	    //Error project
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Project, '"
						+ "WHERE C_Project_ID IS NULL AND ProjectValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		if (no != 0){
			log.warning("Invalid Project=" + no);
			return false;
		}

		// Set TrxOrg
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET AD_OrgTrx_ID=(SELECT o.AD_Org_ID FROM AD_Org o"
						+ " WHERE o.Value=i.OrgTrxValue AND o.IsSummary='N' AND i.AD_Client_ID=o.AD_Client_ID) "
						+ "WHERE AD_OrgTrx_ID IS NULL AND OrgTrxValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set OrgTrx from Value=" + no);
		
		//Error TrxOrg
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid OrgTrx, '"
						+ "WHERE AD_OrgTrx_ID IS NULL AND OrgTrxValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		if (no != 0){
			log.warning("Invalid OrgTrx=" + no);
			return false;
		}
		
		// Set Campaign
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_Campaign_ID=(SELECT c.C_Campaign_ID FROM C_Campaign c"
						+ " WHERE c.Value=i.CampaignValue AND c.IsSummary='N' AND i.AD_Client_ID=c.AD_Client_ID) "
						+ "WHERE C_Campaign_ID IS NULL AND CampaignValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Campaign from Value=" + no);
		
		//ErroR campaign
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Campaign, '"
						+ "WHERE C_Campaign_ID IS NULL AND CampaignValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		if (no != 0){
			log.warning("Invalid Campaign=" + no);
			return false;
		}
	
		// Set Sales Region
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET C_SalesRegion_ID=(SELECT s.C_SalesRegion_ID FROM C_SalesRegion s"
						+ " WHERE s.Value=i.SalesRegionValue AND s.IsSummary='N' AND i.AD_Client_ID=s.AD_Client_ID) "
						+ "WHERE C_SalesRegion_ID IS NULL AND SalesRegionValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set SalesRegion from Value=" + no);
		
		//Error Sales Region
		sql = new StringBuffer(
				"UPDATE I_Budget i "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Sales Region, '"
						+ "WHERE C_SalesRegion_ID IS NULL AND SalesRegionValue IS NOT NULL"
						+ " AND (C_ValidCombination_ID IS NULL OR C_ValidCombination_ID=0) AND I_IsImported<>'Y'")
				.append(clientCheck).append(docCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		
		if (no != 0){
			log.warning("Invalid Sales Region=" + no);
			return false;
		}
		
		commitEx();
		return true;
	
	}
	
	
	
	/**
	 * Check for balance amount
	 * 
	 * @return message
	 */
	private String  checkBalance() 
	{
		int acctSchema_ID=0;
		StringBuffer sql = new StringBuffer(
				"SELECT SUM(COALESCE(Month_0_Amt,0)),SUM(COALESCE(Month_1_Amt,0)),SUM(COALESCE(Month_2_Amt,0)), " +
				"SUM(COALESCE(Month_3_Amt,0)), SUM(COALESCE(Month_4_Amt,0)), SUM(COALESCE(Month_5_Amt,0))," +
				"SUM(COALESCE(Month_6_Amt,0)), SUM(COALESCE(Month_7_Amt,0)), SUM(COALESCE(Month_8_Amt,0)), " +
				"SUM(COALESCE(Month_9_Amt,0)), SUM(COALESCE(Month_10_Amt,0)), SUM(COALESCE(Month_11_Amt,0)) ,C_AcctSchema_ID "
				+ "FROM I_Budget  " + "WHERE I_IsImported='N'")
				.append(clientCheck).append(docCheck)
				.append("Group by BatchDocumentNo,C_AcctSchema_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				BigDecimal month_0_Amt = rs.getBigDecimal(1);
				BigDecimal month_1_Amt = rs.getBigDecimal(2);
				BigDecimal month_2_Amt = rs.getBigDecimal(3);
				BigDecimal month_3_Amt = rs.getBigDecimal(4);
				BigDecimal month_4_Amt = rs.getBigDecimal(5);
				BigDecimal month_5_Amt = rs.getBigDecimal(6);
				BigDecimal month_6_Amt = rs.getBigDecimal(7);
				BigDecimal month_7_Amt = rs.getBigDecimal(8);
				BigDecimal month_8_Amt = rs.getBigDecimal(9);
				BigDecimal month_9_Amt = rs.getBigDecimal(10);
				BigDecimal month_10_Amt = rs.getBigDecimal(11);
				BigDecimal month_11_Amt = rs.getBigDecimal(12);
				acctSchema_ID=rs.getInt(13);

				if (month_0_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_1_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_2_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_3_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_4_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_5_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_6_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_7_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_8_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_9_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_10_Amt.compareTo(BigDecimal.ZERO) != 0
						|| month_11_Amt.compareTo(BigDecimal.ZERO) != 0)
				{
					log.info("Amount Balance != 0");
					
					return "Amount balance(DR-CR) of all journals are not zero";
				
				}
				else
				{
					MAcctSchema schema=MAcctSchema.get(getCtx(), acctSchema_ID);
					MPeriod  period=MPeriod.get(getCtx(), schema.getC_Period_ID());
					finCalendar=	period.getC_Calendar_ID();
					return "Document Ok";
				}
			}

		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		//	clean up
		try
		{
			if (pstmt != null)
				pstmt.close ();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		return null;
	}
	
	private boolean  checkQty() 
	{
		StringBuffer sql = new StringBuffer(
				"SELECT COALESCE(Month_0_Amt,0),COALESCE(Month_1_Amt,0),COALESCE(Month_2_Amt,0),COALESCE(Month_3_Amt,0),COALESCE(Month_4_Amt,0),COALESCE(Month_5_Amt,0)," +
				"COALESCE(Month_6_Amt,0),COALESCE(Month_7_Amt,0),COALESCE(Month_8_Amt,0),COALESCE(Month_9_Amt,0),COALESCE(Month_10_Amt,0),COALESCE(Month_11_Amt,0)," +
				"COALESCE(Month_0_Qty,0),COALESCE(Month_1_Qty,0),COALESCE(Month_2_Qty,0),COALESCE(Month_3_Qty,0),COALESCE(Month_4_Qty,0),COALESCE(Month_5_Qty,0)," +
				"COALESCE(Month_6_Qty,0),COALESCE(Month_7_Qty,0),COALESCE(Month_8_Qty,0),COALESCE(Month_9_Qty,0),COALESCE(Month_10_Qty,0),COALESCE(Month_11_Qty,0),I_Budget_ID " +
				"FROM I_Budget WHERE I_IsImported='N'")
				.append(clientCheck).append(docCheck);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				BigDecimal month_0_Amt = rs.getBigDecimal(1);
				BigDecimal month_1_Amt = rs.getBigDecimal(2);
				BigDecimal month_2_Amt = rs.getBigDecimal(3);
				BigDecimal month_3_Amt = rs.getBigDecimal(4);
				BigDecimal month_4_Amt = rs.getBigDecimal(5);
				BigDecimal month_5_Amt = rs.getBigDecimal(6);
				BigDecimal month_6_Amt = rs.getBigDecimal(7);
				BigDecimal month_7_Amt = rs.getBigDecimal(8);
				BigDecimal month_8_Amt = rs.getBigDecimal(9);
				BigDecimal month_9_Amt = rs.getBigDecimal(10);
				BigDecimal month_10_Amt = rs.getBigDecimal(11);
				BigDecimal month_11_Amt = rs.getBigDecimal(12);
				BigDecimal month_0_Qty = rs.getBigDecimal(13);
				BigDecimal month_1_Qty = rs.getBigDecimal(14);
				BigDecimal month_2_Qty = rs.getBigDecimal(15);
				BigDecimal month_3_Qty = rs.getBigDecimal(16);
				BigDecimal month_4_Qty = rs.getBigDecimal(17);
				BigDecimal month_5_Qty = rs.getBigDecimal(18);
				BigDecimal month_6_Qty = rs.getBigDecimal(19);
				BigDecimal month_7_Qty = rs.getBigDecimal(20);
				BigDecimal month_8_Qty = rs.getBigDecimal(21);
				BigDecimal month_9_Qty = rs.getBigDecimal(22);
				BigDecimal month_10_Qty = rs.getBigDecimal(23);
				BigDecimal month_11_Qty = rs.getBigDecimal(24);
				int i_Budget_ID         = rs.getInt(25);

				if((month_0_Amt.signum()!=0 && month_0_Qty.signum() !=0 && month_0_Amt.signum()!=month_0_Qty.signum()) ||
						(month_1_Amt.signum()!=0 && month_1_Qty.signum() !=0 && month_1_Amt.signum()!=month_1_Qty.signum()) ||
						(month_2_Amt.signum()!=0 && month_2_Qty.signum() !=0 && month_2_Amt.signum()!=month_2_Qty.signum()) ||
						(month_3_Amt.signum()!=0 && month_3_Qty.signum() !=0 && month_3_Amt.signum()!=month_3_Qty.signum()) ||
						(month_4_Amt.signum()!=0 && month_4_Qty.signum() !=0 && month_4_Amt.signum()!=month_4_Qty.signum()) ||
						(month_5_Amt.signum()!=0 && month_5_Qty.signum() !=0 && month_5_Amt.signum()!=month_5_Qty.signum()) ||
						(month_6_Amt.signum()!=0 && month_6_Qty.signum() !=0 && month_6_Amt.signum()!=month_6_Qty.signum()) ||
						(month_7_Amt.signum()!=0 && month_7_Qty.signum() !=0 && month_7_Amt.signum()!=month_7_Qty.signum()) ||
						(month_8_Amt.signum()!=0 && month_8_Qty.signum() !=0 && month_8_Amt.signum()!=month_8_Qty.signum()) ||
						(month_9_Amt.signum()!=0 && month_9_Qty.signum() !=0 && month_9_Amt.signum()!=month_9_Qty.signum()) ||
						(month_10_Amt.signum()!=0 && month_10_Qty.signum() !=0 && month_10_Amt.signum()!=month_10_Qty.signum()) ||
						(month_11_Amt.signum()!=0 && month_11_Qty.signum() !=0 && month_11_Amt.signum()!=month_11_Qty.signum()) )
				
				{
					sql = new StringBuffer(
							"UPDATE I_Budget i "
									+ "SET I_IsImported='E',I_ErrorMsg=I_ErrorMsg||'ERR=Qty Mismatch.Credit Line must have negative qty and Debit line must have positive qty. '"
									+ " Where I_IsImported<>'Y' ").append(clientCheck).append(docCheck).append(" AND I_Budget_ID=").append(i_Budget_ID);
					DB.executeUpdate(sql.toString(), get_TrxName());
					return false;
				}
			}

		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		
		try
		{
			if (pstmt != null)
				pstmt.close ();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		return true;
	}
	
	//constants
	
	private int gl_Category_ID;
	private int c_Currency_ID;
	private int c_DocType_ID;
	private int c_ConType_ID;
	
	private void getConstantsId()
	{
		gl_Category_ID = DB.getSQLValue(get_TrxName(),"SELECT GL_Category_ID FROM GL_Category WHERE Name = 'Manual'  AND AD_Client_ID =? ", Env.getAD_Client_ID(getCtx()) );
		c_Currency_ID = DB.getSQLValue(get_TrxName(),"SELECT C_Currency_ID FROM c_acctschema WHERE c_acctschema_id = ?", m_C_AcctSchema_ID );
		c_DocType_ID = DB.getSQLValue(get_TrxName(),"SELECT C_DocType_ID FROM C_DocType WHERE Name = 'GL Journal' AND AD_Client_ID =?",  Env.getAD_Client_ID(getCtx()));
		c_ConType_ID= DB.getSQLValue(get_TrxName(),"SELECT C_ConversionType_ID FROM C_ConversionType WHERE Value =? ",  "S");
	}
	
	/**
	 * Creates Journal Batch
	 * 
	 * @return Journal Batch
	 */
	private MJournalBatch createGlBatch()
	{
		
		MJournalBatch journalBatch=new MJournalBatch(getCtx(), 0, get_TrxName());
		journalBatch.setDocumentNo(glDocNo);
		journalBatch.setDescription(batchDesc);
		journalBatch.setPostingType(X_GL_JournalBatch.POSTINGTYPE_Budget);
		journalBatch.setDateAcct(acctDate);
		journalBatch.setDateDoc(acctDate);		
		journalBatch.setGL_Category_ID(gl_Category_ID);
		journalBatch.setC_Currency_ID(c_Currency_ID);
		journalBatch.setC_DocType_ID(c_DocType_ID);
		journalBatch.setAD_Org_ID(m_AD_Org_ID);
		
		int c_Period_ID=MPeriod.getC_Period_ID(getCtx(), acctDate,Env.getAD_Org_ID(getCtx()));
		journalBatch.setC_Period_ID(c_Period_ID);
		journalBatch.saveEx();
		
		if(journalBatch.getGL_JournalBatch_ID() > 0)
		{
			
			StringBuffer sql = new StringBuffer(
					"UPDATE I_Budget  "
							+ "SET GL_JournalBatch_ID= ").append(journalBatch.getGL_JournalBatch_ID() + " WHERE I_IsImported='N' " )
							.append(clientCheck).append(docCheck);
			DB.executeUpdate(sql.toString(), get_TrxName());
			
			return journalBatch;
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * Create GL Journals 
	 * 
	 * @param MJournalBatch
	 */
	private void createJournal(MJournalBatch mjb)
	{	
		int noPeriods=0;
		if(noOfPeriods == 0 || noOfPeriods >12)
			noPeriods = 12;
		else
			noPeriods = noOfPeriods;

		StringBuffer sql=new StringBuffer("SELECT * FROM I_Budget   WHERE I_IsImported='N' " )
							.append(clientCheck).append(docCheck);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			rs = pstmt.executeQuery();
			if (rs.next())	//	first only
			{
				getPeriods(noPeriods);
				if(noPeriods>m_periods.size())
					noPeriods=m_periods.size();

				for (int i = 0; i < noPeriods; i++)

				{
					MJournal journal = new MJournal(getCtx(), 0, get_TrxName());
					journal.setGL_JournalBatch_ID(mjb.getGL_JournalBatch_ID());
				    DateFormat df = new SimpleDateFormat("yyyy-MM");
				    String formattedDate = df.format(m_periods.get(i).getStartDate()); 
				    
					journal.setDocumentNo(glDocNo + "-"+ formattedDate);					
					journal.setDescription(batchDesc);					
					journal.setDateAcct((dates.get(i)));
					journal.setDateDoc((dates.get(i)));
					journal.setC_Period_ID(m_periods.get(i).getC_Period_ID());
					journal.setClientOrg(journal.getGL_JournalBatch().getAD_Client_ID(), journal.getGL_JournalBatch().getAD_Org_ID());
					journal.setPostingType(X_GL_JournalBatch.POSTINGTYPE_Budget);
					journal.setGL_Category_ID(gl_Category_ID);
					journal.setC_Currency_ID(c_Currency_ID);
					journal.setC_DocType_ID(c_DocType_ID);
					journal.setCurrencyRate(BigDecimal.ONE);
					journal.setC_ConversionType_ID(c_ConType_ID);
					journal.setGL_Budget_ID(rs.getInt("GL_Budget_ID"));;
					journal.setC_AcctSchema_ID(rs.getInt("C_AcctSchema_ID"));
					boolean lineCreated = createJournalLine(journal, i);
					if (!lineCreated && noPeriods < m_periods.size())
						noPeriods++;

				}
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE,"" ,e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
	}
	
	/**
	 * Create journal lines
	 * 
	 * @param MJournal journal
	 * @param noOfMonth 
	 * @return true if line created
	 */
	private boolean createJournalLine(MJournal mj,int noOfMonth) 
	{
		boolean lineCreated=false;
		StringBuffer sql=new StringBuffer("SELECT * FROM I_Budget  " + "WHERE I_IsImported='N'").append(
				clientCheck).append(docCheck);
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			int line=10;
			while (rs.next())
			{
				BigDecimal amt=rs.getBigDecimal("Month_" +noOfMonth+"_Amt");
				BigDecimal qty=rs.getBigDecimal("Month_" +noOfMonth+"_Qty");
				if(amt != null && amt.compareTo(BigDecimal.ZERO)!=0)
				{
					if(mj.get_ID()==0)
						mj.saveEx();
					
					MJournalLine journalLine =new MJournalLine(getCtx(), 0, get_TrxName());
					journalLine.setGL_Journal_ID(mj.getGL_Journal_ID());
					if(amt.compareTo(BigDecimal.ZERO)<0)
						journalLine.setAmtSourceCr(amt.abs());
					else
						journalLine.setAmtSourceDr(amt);
						
					if(qty!=null)						
						journalLine.setQty(qty);		
				
					//journalLine.setAD_Client_ID(journalLine.getParent().getAD_Client_ID());
					journalLine.setAD_Org_ID(journalLine.getParent().getAD_Org_ID());
					journalLine.setC_Currency_ID(c_Currency_ID);
					journalLine.setDateAcct(journalLine.getParent().getDateAcct());
					journalLine.setC_ConversionType_ID(journalLine.getParent().getC_ConversionType_ID() );
					journalLine.setCurrencyRate(BigDecimal.ONE);
					journalLine.setLine(line);
					journalLine.setDescription(rs.getString("Jnl_Line_Description"));
					line=line+10;
					if(rs.getInt("A_Asset_ID") >0)
					{
						journalLine.setA_Asset_ID(rs.getInt("A_Asset_ID"));
						journalLine.setA_CreateAsset(true);
					}
								

					MAccount acct = MAccount.get(getCtx(), rs.getInt("AD_Client_ID"), rs.getInt("AD_Org_ID"), 
							rs.getInt("C_AcctSchema_ID"), rs.getInt("Account_ID"), 0,
							rs.getInt("M_Product_ID"), rs.getInt("C_BPartner_ID"),  rs.getInt("AD_OrgTrx_ID"),
							rs.getInt("C_LocFrom_ID"), rs.getInt("C_LocTo_ID"), rs.getInt("C_SalesRegion_ID"),
							rs.getInt("C_Project_ID"), rs.getInt("C_Campaign_ID"), rs.getInt("C_Activity_ID"),
							rs.getInt("User1_ID"), rs.getInt("User2_ID"), 0, 0, get_TrxName());
					if (acct != null && acct.get_ID() == 0)
						acct.saveEx();
					if (acct == null || acct.get_ID() == 0)
					{
						sql = new StringBuffer(
								"UPDATE I_Budget  "
										+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=ERROR creating Account, '"
										+ "WHERE  I_IsImported<>'Y' and I_Budget_ID=").append(rs.getInt("I_Budget_ID"))
								.append(clientCheck).append(docCheck);
						DB.executeUpdate(sql.toString(), get_TrxName());
						return lineCreated;
						
					}
					else
					{
						journalLine.setC_ValidCombination_ID(acct.get_ID());
						
						sql = new StringBuffer(
								"UPDATE I_Budget  "
										+ "SET C_ValidCombination_ID =" ).append(acct.get_ID())
										.append( "WHERE  I_IsImported<>'Y' and I_Budget_ID=").append(rs.getInt("I_Budget_ID"))
								.append(clientCheck).append(docCheck);
						DB.executeUpdate(sql.toString(), get_TrxName());
					}
												
					journalLine.saveEx();	
				    lineCreated=true;
				}
				
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		//	clean up
		try
		{
			if (pstmt != null)
				pstmt.close ();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		return lineCreated;
	}
	ArrayList<MPeriod> m_periods=new ArrayList<MPeriod>();
	ArrayList<Timestamp> dates=new ArrayList<Timestamp>();
	
	/**
	 * @return list of 12 periods before  As At Date
	 */
	private ArrayList<MPeriod> getPeriods(int noPeriods)
	{   
		int offset=0;
		
		MPeriod m_period=MPeriod.findByCalendar(getCtx(),acctDate ,finCalendar,get_TrxName());
	
		Calendar cale = Calendar.getInstance();
		cale.setTimeInMillis(m_period.getEndDate().getTime());
		
		cale.add(Calendar.DATE, 1);
		Timestamp startTime = new Timestamp(cale.getTimeInMillis());
		Date startDateNextPeriod = new Date(startTime.getTime());
		
		offset  =  startDateNextPeriod.getMonth()- m_period.getStartDate().getMonth() ;
	
		m_periods.add(m_period);
		dates.add(acctDate);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(acctDate.getTime());
		
		for(int i=1;i<12;i++)
		{
			cal.add(Calendar.MONTH, offset);
			Timestamp nextDate = new Timestamp(cal.getTimeInMillis());
			dates.add(nextDate);
			MPeriod acctPeriod = MPeriod.findByCalendar(getCtx(), nextDate,finCalendar,get_TrxName());
			cal.setTimeInMillis(nextDate.getTime());
			if(acctPeriod!=null)
				m_periods.add(acctPeriod);
			
		}
		
		return m_periods;
	}
	

	String docCheck;
	String clientCheck;	
	
	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		
		StringBuffer sql=new StringBuffer("SELECT BatchDocumentNo,I_IsImported FROM I_Budget where I_Budget_ID=" + getRecord_ID());
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		String message = null;
		String isImported=null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				glDocNo=rs.getString(1);
				isImported=rs.getString(2);
			}

		} 
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, sql.toString(), ex);
		} 
		finally 
		{
			rs.close();
			pstmt.close();
			
		}
		
		if(isImported.equals("Y"))
			return "Budget already imported.";
	
						
		docCheck = " AND BatchDocumentNo= '" + glDocNo + "'";
		clientCheck = " AND AD_Client_ID= " + Env.getAD_Client_ID(getCtx());
		boolean isUpdated=updateTable();
		
		if(!isUpdated)
			return "Problem when updating table.Refer Import Error message on window";
		
		boolean isQtyOk=checkQty();
		if(!isQtyOk)
			return "Qty Mismatch.Credit Line must have negative qty and Debit line must have positive qty.";
		message =checkBalance();
		int i=0;
		if (message != null && message.equals("Document Ok") && finCalendar>0)
		{
			
			getConstantsId();
			MJournalBatch journalBatch=createGlBatch();
			if(journalBatch!=null)
			{
				createJournal(journalBatch);
				if(docAction.equals("CO"))
					journalBatch.processIt(DocAction.ACTION_Complete);
				
				
				// Set imported and processed true if success
				 sql = new StringBuffer(
						"UPDATE I_Budget "
								+ "SET I_IsImported='Y', Processed='Y', Updated=SysDate "
								+ "WHERE I_IsImported='N' AND GL_JournalBatch_ID IS NOT NULL ")
						.append(clientCheck).append(docCheck);
				i=DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("Imported =" + i);	
				if(i>0)
					 return "Budget Imported."  ;
					
			}
		}
		
		return message;
		
		

	}

}