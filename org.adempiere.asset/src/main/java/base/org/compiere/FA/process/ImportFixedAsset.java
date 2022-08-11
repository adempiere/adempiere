package org.compiere.FA.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MAssetAddition;
import org.compiere.FA.model.MIFixedAsset;
import org.compiere.FA.model.X_I_FixedAsset;
import org.compiere.model.MSysConfig;
import org.compiere.model.POResultSet;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *	Import Fixed Asset
 *
 *	Goodwill Consulting Change Log
 *	- Add Org Value
 *	- Allow Overwrite DateAcct
 *	- Add some data validation
 *	- BF: Assigning Locator ID
 *	- Current period = 0, means the asset is fully depreciated
 *	- Asset Cost must be a positive number
 *
 * 	@author 	Zuhri Utama, Ambidexter [based on ImportAssetClass Teo Sarca]
 * 
 * 	@version 	$Id$
 */
public class ImportFixedAsset extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				p_AD_Client_ID = 0;
	/**	Organization to be imported to	*/
	private int				p_AD_Org_ID = 0;

	/** Account Date					*/
	private Timestamp		p_DateAcct = null;

	/** Validate Only - only validate import data */
	private boolean			p_IsValidateOnly = false;
	
	/**	Delete old Imported				*/
	private boolean			p_DeleteOldImported = true;

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
			else if (name.equals("AD_Client_ID"))
				p_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();

			else if (name.equals("DateAcct"))
				p_DateAcct = ((Timestamp)para[i].getParameter());
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("IsValidateOnly"))
				p_IsValidateOnly = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare


	/**
	 *  Perrform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		if(p_AD_Client_ID==0)
			p_AD_Client_ID = Env.getAD_Client_ID(getCtx());
		String sqlCheck = " AND AD_Client_ID=" + p_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (p_DeleteOldImported)
		{
			sql = new StringBuffer ("DELETE "+X_I_FixedAsset.Table_Name
				  + " WHERE I_IsImported='Y'").append (sqlCheck);
			no = DB.executeUpdateEx(sql.toString(), get_TrxName());
			log.fine("Delete Old Imported =" + no);
		}
		
		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (p_AD_Client_ID).append ("),"
			  + " AD_Org_ID = COALESCE (AD_Org_ID,").append (p_AD_Org_ID).append ("),"
			  + " IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, SysDate),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, SysDate),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " I_ErrorMsg = ' ',"
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.info ("Reset=" + no);
		
		//Goodwill
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET AD_Org_ID=(SELECT MAX(AD_Org_ID) FROM AD_Org org"
			  + " WHERE ifa.OrgValue=org.Value AND org.IsSummary='N' AND org.AD_Client_ID=ifa.AD_Client_ID) "
			  + "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0) AND OrgValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Organization from Value=" + no);
		//end Goodwill
		
		// Check if Org is Null or 0
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE ifa.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);
		
		// Check if Name is Null
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Name Is Mandatory, '"
			+ "WHERE Name IS NULL AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Name=" + no);
		
		// Asset Group From Value
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET A_Asset_Group_ID=(SELECT MAX(A_Asset_Group_ID) FROM A_Asset_Group t"
			  + " WHERE ifa.A_Asset_Group_Value=t.Name AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE A_Asset_Group_ID IS NULL AND A_Asset_Group_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Asset Group from Value=" + no);
		
		// Check if Asset Group Have Asset Group Acct Record
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Asset Group Doesnt Have Asset Group Acct Record, ' "
			+ "WHERE A_Asset_Group_ID IS NOT NULL AND A_Asset_Group_ID>0 " //@win change to AND from OR
			+ "AND NOT EXISTS (SELECT 1 FROM A_Asset_Group_Acct aga WHERE ifa.A_Asset_Group_ID=aga.A_Asset_Group_ID) " //@win change to AND from OR
			+ "AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Asset Group=" + no);
		
		// Asset Type From Value
		/* commented by @win
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET A_Asset_Type_ID=(SELECT MAX(A_Asset_Type_ID) FROM A_Asset_Type t"
			  + " WHERE ifa.A_Asset_Type_Value=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE A_Asset_Type_ID IS NULL AND A_Asset_Type_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Asset Type from Value=" + no);
		*/
		
		// BP From Value
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET C_BPartnerSR_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner t"
			  + " WHERE ifa.BPartner_Value=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE C_BPartnerSR_ID IS NULL AND BPartner_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set BP from Value=" + no);
		
		// City From Value
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET C_City_ID=(SELECT MAX(C_City_ID) FROM C_City t"
			  + " WHERE ifa.C_City_Value=t.Name AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE C_City_ID IS NULL AND C_City_Value IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set City from Value=" + no);
		
		// Product 
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product t"
			  + " WHERE ifa.ProductValue=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Product from Value=" + no);
		
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "
			  + "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Product=" + no);
		
		// Check if Product using Product Category has A Asset Category Set
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Product Using Product Category Without Asset Group Defined, ' "
			+ "WHERE EXISTS (SELECT 1 FROM M_Product p "
			+ "JOIN M_Product_Category pc ON p.M_Product_Category_ID=pc.M_Product_Category_ID "
			+ "WHERE ifa.M_Product_ID=p.M_Product_ID "
			+ "AND (pc.A_Asset_Group_ID=0 OR pc.A_Asset_Group_ID IS NULL)) "
			+ "AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Product Category=" + no);
				
		// Locator From Value
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+" ifa "
			  + "SET M_Locator_ID=(SELECT MAX(M_Locator_ID) FROM M_Locator t"
			  + " WHERE ifa.LocatorValue=t.Value AND ifa.AD_Client_ID=t.AD_Client_ID) "
			  + "WHERE M_Locator_ID IS NULL AND LocatorValue IS NOT NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		log.fine("Set Locator from Value=" + no);
		
		//Goodwill
		//Check current period, 0 means the asset is fully depreciated
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Current Period, ' "
			  + "WHERE a_current_period < 0 OR a_current_period > uselifemonths"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Current Period=" + no);
		
		//Asset cost must not be null, 0, or negative numbers
		sql = new StringBuffer ("UPDATE " +MIFixedAsset.Table_Name+ " "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg ||'ERR=Invalid Asset Cost, ' "
			  + "WHERE a_asset_cost < 1 AND I_IsImported<>'Y'").append(sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning("Invalid Asset Cost=" + no);
		
		double toleranceAmt = MSysConfig.getDoubleValue("TOLERANCE_AMT", 1, 0, 0);
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Accumulated Depreciation, ' "
			  + "WHERE uselifemonths<>a_current_period AND ABS(((a_asset_cost / uselifemonths) * (a_current_period - 1)) - a_accumulated_depr) > " + BigDecimal.valueOf(toleranceAmt)
			  //Goodwill - no toleranceAmt check on fully depreciated asset (current period = 0)
			  + " AND I_IsImported<>'Y' AND a_current_period <> 0").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Accumulated Depreciation=" + no);
		// end Goodwill
				
		//-- New BPartner ---------------------------------------------------

		//	Go through Fixed Assets Records w/o C_BPartner_ID
		/* no need this @win
		sql = new StringBuffer ("SELECT * FROM "+MIFixedAsset.Table_Name+ " 
			  + "WHERE I_IsImported='N' AND C_BPartnerSR_ID IS NULL").append (sqlCheck);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MIFixedAsset ifa = new MIFixedAsset (getCtx(), rs, get_TrxName());
				if (ifa.getBPartner_Value () == null)
					continue;
				
				//	BPartner
				MBPartner bp = MBPartner.get (getCtx(), ifa.getBPartner_Value());
				if (bp == null)
				{
					bp = new MBPartner (getCtx (), -1, get_TrxName());
					bp.setClientOrg (ifa.getAD_Client_ID (), ifa.getAD_Org_ID ());
					bp.setValue (ifa.getBPartner_Value ());
					bp.setName (ifa.getBPartner_Value ());
					if (!bp.save ())
						continue;
				}
				ifa.setC_BPartnerSR_ID (bp.getC_BPartner_ID ());
				
				MBPartnerLocation bpl = null;
				
				if (bpl == null)
				{
					//	New Location
					MLocation loc = new MLocation (getCtx (), 0, get_TrxName());
					loc.setCity (ifa.getC_City_Value ());
					if (!loc.save ())
						continue;
					//
					bpl = new MBPartnerLocation (bp);
					bpl.setC_Location_ID (loc.getC_Location_ID());
					if (!bpl.save ())
						continue;
				}
				ifa.save ();
			}	//	for all new BPartners
			rs.close ();
			pstmt.close ();
			//
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "CreateBP", e);
		}
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BPartner, ' "
			  + "WHERE C_BPartnerSR_ID IS NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("No BPartner=" + no);
		
		commitEx();
		
		//-- New Product ---------------------------------------------------
		// TODO : zuhri Utama - need to fixed create new product

		//	Go through Fixed Assets Records w/o M_Product_ID
		sql = new StringBuffer ("SELECT * FROM "+MIFixedAsset.Table_Name+ " "
			  + "WHERE I_IsImported='N' AND M_Product_ID IS NULL").append (sqlCheck);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MIFixedAsset ifa = new MIFixedAsset (getCtx(), rs, get_TrxName());
				if (ifa.getProductValue () == null)
					continue;
				
				//	Product
				String Value = ifa.getProductValue ();
				if (Value == null || Value.length() == 0)
					return null;
				final String whereClause = "Value=? AND AD_Client_ID=?";
				MProduct product = new Query(getCtx(), MProduct.Table_Name, whereClause, null)
				.setParameters(Value,Env.getAD_Client_ID(getCtx()))
				.firstOnly();
				if (product == null)
				{
					product = new MProduct (getCtx (), -1, get_TrxName());
					product.setAD_Org_ID(ifa.getAD_Org_ID ());
					product.setValue (ifa.getProductValue ());
					product.setName (ifa.getProductValue ());
					product.setC_UOM_ID(ifa.getC_UOM_ID());
					if(p_M_Product_Category_ID>0)
						product.setM_Product_Category_ID(p_M_Product_Category_ID);
					if (!product.save ())
						continue;
				}
				ifa.setM_Product_ID (product.getM_Product_ID());
				
				ifa.save ();
			}	//	for all new Products
			rs.close ();
			pstmt.close ();
			//
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "CreateProduct", e);
		}
		sql = new StringBuffer ("UPDATE "+MIFixedAsset.Table_Name+ " "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Product, ' "
			  + "WHERE M_Product_ID IS NULL"
			  + " AND I_IsImported<>'Y'").append (sqlCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("No Product=" + no);
		
		commitEx();
		*/ //commented by @win
		
		if(p_IsValidateOnly)
			return "Data Was Validated";

		int cnt_ok = 0;
		int cnt_err = 0;
		
		String whereClause = "COALESCE(I_IsImported,'N')='N'"+sqlCheck;
		POResultSet<X_I_FixedAsset>
		rs = new Query(getCtx(), X_I_FixedAsset.Table_Name, whereClause, get_TrxName()).scroll();
		try
		{
			while (rs.hasNext()) {
				X_I_FixedAsset xfa = rs.next();
				MIFixedAsset ifa = new MIFixedAsset(getCtx(), xfa.getI_FixedAsset_ID(), get_TrxName());
				try
				{
					MAssetAddition assetAdd = MAssetAddition.createAsset(ifa);
					if(assetAdd==null){
						ifa.setI_ErrorMsg("Failed Create Assets");
						cnt_err++;
						assetAdd=null;
						continue;
					}
					//if(p_A_Asset_Group_ID>0)
					//	assetAdd.getA_Asset().setA_Asset_Group_ID(p_A_Asset_Group_ID);
					if(p_DateAcct!=null)
						assetAdd.setDateAcct(p_DateAcct);
					assetAdd.saveEx();
					
					//Process Asset Addition Based on Document Action
					if(!assetAdd.processIt(ifa.getDocAction())){
						ifa.setI_ErrorMsg("Failed Process Asset Addition");
						cnt_err++;
						assetAdd=null;
						continue;												
					}
					assetAdd.saveEx();
					
					//Goodwill - Set asset properties is imported on fully depreciated status
					if(ifa.getA_Current_Period() == 0)
					{
						MAsset asset = new MAsset(getCtx(), assetAdd.getA_Asset_ID(), get_TrxName());
						asset.setUseLifeYears(0);
						asset.setUseLifeMonths(0);
						asset.setLifeUseUnits(0);
						asset.setUseUnits(0);
						asset.saveEx();
					}//Goodwill - End set
					
					ifa.setI_IsImported(true);
					ifa.setI_ErrorMsg(null);
					ifa.setA_Asset_ID(assetAdd.getA_Asset_ID());
					ifa.setProcessed(true);
					ifa.saveEx();
					
					cnt_ok++;
				}
				catch (Exception e) {
					ifa.setI_ErrorMsg(e.getLocalizedMessage());
					cnt_err++;
					ifa.saveEx();
				}
			}
		}
		finally
		{
			DB.close(rs); rs = null;
			// Goodwill
			//	Set Error to indicator to not imported
			sql = new StringBuffer ("UPDATE I_FixedAsset "
				+ "SET I_IsImported='N', Updated=SysDate "
				+ "WHERE I_IsImported<>'Y'").append(sqlCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			addLog (0, null, new BigDecimal (no), "@Errors@");
			addLog (0, null, new BigDecimal (cnt_ok), "@A_Asset_ID@: @Inserted@");
			addLog (0, null, new BigDecimal (cnt_err), "@A_Asset_ID@: @Failed@");
		}
		
		return "";
	}	//	doIt
	
}	//	ImportAssetClass