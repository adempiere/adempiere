package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.util.DB;
import org.compiere.util.Msg;


/**
 * 
 */
public class FactReconciliation extends SvrProcess
{
	private Timestamp			p_DateAcct_From = null;
	private Timestamp			p_DateAcct_To = null;
	private int					p_Account_ID = 0;
	/**	Start Time						*/
	private long 				m_start = System.currentTimeMillis();
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		StringBuffer sb = new StringBuffer ("AD_PInstance_ID=")
			.append(getAD_PInstance_ID());
		//	Parameter
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("DateAcct"))
			{
				p_DateAcct_From = (Timestamp)para[i].getParameter();
				p_DateAcct_To = (Timestamp)para[i].getParameter_To();
			}
			else if (name.equals("Account_ID"))
				p_Account_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	
	/**************************************************************************
	 *  Perform process.
	 *  @return Message to be translated
	 */
	protected String doIt()
	{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT into T_Reconciliation " +
		"(AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, " +
		"IsActive, Fact_Acct_ID,  " +
		"AD_PInstance_ID, MatchCode) " +
		"SELECT f.AD_Client_ID, f.AD_Org_ID, f.Created, f.CreatedBy, " +
		"f.Updated, f.UpdatedBy, f.IsActive, " +
		"f.Fact_Acct_ID, ?, r.MatchCode " +
		"FROM Fact_Acct f " +
		"LEFT OUTER JOIN Fact_Reconciliation r ON (f.Fact_Acct_ID=r.Fact_Acct_ID) " +
		"WHERE Account_ID = ? " +
		"AND DateAcct BETWEEN  ? AND ? ";
		
		try
		{
			

			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_PInstance_ID());
			pstmt.setInt(2, p_Account_ID);
			pstmt.setTimestamp(3, p_DateAcct_From);
			pstmt.setTimestamp(4, p_DateAcct_To);
			int count = pstmt.executeUpdate();
			String result = Msg.getMsg(getCtx(),"@Created@") + ": " + count + ", ";
			
			log.log(Level.FINE, result);

			sql = "DELETE FROM T_Reconciliation t " +
			"WHERE (SELECT SUM(f.amtacctdr-f.amtacctcr) FROM T_Reconciliation r " +
				"    INNER JOIN Fact_Acct f ON (f.Fact_Acct_ID = r.Fact_Acct_ID) " +
				"       WHERE r.MatchCode=t.MatchCode" +
				"       AND r.AD_PInstance_ID = t.AD_PInstance_ID) = 0 " +
				"AND t.AD_PInstance_ID = ?";
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, getAD_PInstance_ID());
		count = pstmt.executeUpdate();
		result = Msg.getMsg(getCtx(), "@Deleted@") + ": " + count;
		
		log.log(Level.FINE, result);
		
		
	}
	catch (SQLException e)
	{
		log.log(Level.SEVERE, sql, e);
		return e.getLocalizedMessage();
	}
	finally
	{
		DB.close(rs, pstmt);
		rs = null; pstmt = null;
	}
	
		log.fine((System.currentTimeMillis() - m_start) + " ms");
		return "";
	}	//	doIt


}
