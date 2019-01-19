package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MProductionLineMA extends X_M_ProductionLineMA {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MInventoryLineMA.class);

	public MProductionLineMA(Properties ctx, int M_ProductionLineMA_ID,
			String trxName) {
		super(ctx, M_ProductionLineMA_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProductionLineMA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Parent constructor
	 * @param parent
	 * @param asi
	 * @param qty
	 * @param ctx
	 * @param trxName
	 */
	public MProductionLineMA( MProductionLine parent, int asi, BigDecimal qty)	{
		
		super(parent.getCtx(),0,parent.get_TrxName());
		setM_AttributeSetInstance_ID(asi);
		setM_ProductionLine_ID(parent.get_ID());
		setMovementQty(qty);
		setAD_Org_ID(parent.getAD_Org_ID());
	}
	
	public static MProductionLineMA get( MProductionLine parent, int asi )  {
		
		String where = " M_ProductionLine_ID = ? AND M_AttributeSetInstance_ID = ? ";
		
		MProductionLineMA lineMA = MTable.get(parent.getCtx(), MProductionLineMA.Table_Name).createQuery(where, parent.get_TrxName())
		.setParameters(parent.getM_ProductionLine_ID(), asi).firstOnly();
		
		if (lineMA != null)
			return lineMA;
		else
			return new MProductionLineMA( parent,
				asi,
				Env.ZERO);
	}
	public static MProductionLineMA[] get (Properties ctx, int M_ProductionLine_ID, String trxName)
	{
		ArrayList<MProductionLineMA> list = new ArrayList<MProductionLineMA>();
		String sql = "SELECT * FROM M_ProductionLineMA WHERE M_ProductionLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_ProductionLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MProductionLineMA (ctx, rs, trxName));
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		MProductionLineMA[] retValue = new MProductionLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
		}

}
