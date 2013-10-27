package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;


public class BOMFlagValidate extends SvrProcess {

	/** Product Category	*/
	private int		p_M_Product_Category_ID = 0;
	
	
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_Category_ID"))
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		flagNonBOMs();
		flagBOMs();
		return "BOM Flags set correctly";
	}
	
	private void flagNonBOMs() throws Exception
	{
		
		//Select Products where there's a BOM, and there are no lines
		String sql = "SELECT NAME FROM M_PRODUCT WHERE ISBOM = 'Y' AND " + 
		"M_PRODUCT_ID NOT IN (SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM ) AND "; 
		if (p_M_Product_Category_ID == 0)
			sql += "AD_Client_ID= ?";
	        
		else
			sql += "M_Product_Category_ID= ?";
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, null);
		if (p_M_Product_Category_ID == 0)
			pstmt.setInt (1, Env.getAD_Client_ID(getCtx()));
		else
			pstmt.setInt(1, p_M_Product_Category_ID);
		ResultSet rs = pstmt.executeQuery ();
		
		while (rs.next())
		{
			addLog(0, null, null, rs.getString(1) + "Has Been Flagged as NonBOM as it has no lines");
		}
		
		rs.close();
		pstmt.close();
		
		String update = "UPDATE M_Product SET ISBOM = 'N' WHERE ISBOM = 'Y' AND M_PRODUCT_ID NOT IN " +
		"(SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM ) AND "; 
		if (p_M_Product_Category_ID == 0)
			update += "AD_Client_ID= ?";
		else
			update += "M_Product_Category_ID= ?";
		pstmt = null;
		pstmt = DB.prepareStatement (update, null);
		if (p_M_Product_Category_ID == 0)
			pstmt.setInt (1, Env.getAD_Client_ID(getCtx()));
		else
			pstmt.setInt(1, p_M_Product_Category_ID);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	private void flagBOMs() throws Exception
	{
		
		//Select Products where there's a BOM, and there are no lines
		String sql = "SELECT NAME FROM M_PRODUCT WHERE ISBOM = 'N' AND " + 
		"M_PRODUCT_ID IN (SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM ) AND "; 
		if (p_M_Product_Category_ID == 0)
			sql += "AD_Client_ID= ?";
	        
		else
			sql += "M_Product_Category_ID= ?";
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, null);
		if (p_M_Product_Category_ID == 0)
			pstmt.setInt (1, Env.getAD_Client_ID(getCtx()));
		else
			pstmt.setInt(1, p_M_Product_Category_ID);
		ResultSet rs = pstmt.executeQuery ();
		
		while (rs.next())
		{
			addLog(0, null, null, rs.getString(1) + "Has Been Flagged as BOM as it has BOM lines");
		}
		rs.close();
		pstmt.close();
		
		String update = "UPDATE M_Product SET ISBOM = 'Y' WHERE ISBOM = 'N' AND M_PRODUCT_ID IN " +
		"(SELECT M_PRODUCT_ID FROM M_PRODUCT_BOM ) AND "; 
		if (p_M_Product_Category_ID == 0)
			update += "AD_Client_ID= ?";
		else
			update += "M_Product_Category_ID= ?";
		pstmt = null;
		pstmt = DB.prepareStatement (update, null);
		if (p_M_Product_Category_ID == 0)
			pstmt.setInt (1, Env.getAD_Client_ID(getCtx()));
		else
			pstmt.setInt(1, p_M_Product_Category_ID);
		pstmt.executeUpdate();
		pstmt.close();
		
	}

}
