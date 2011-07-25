/**
 * 
 */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Env;

/**
 * @author teo_sarca
 *
 */
public class MDashboardContent extends X_PA_DashboardContent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5425307033413466516L;

	public static int getForSessionColumnCount()
	{
        int noOfCols = getForSessionQuery().aggregate("DISTINCT "+COLUMNNAME_ColumnNo, Query.AGGREGATE_COUNT, Integer.class);
        return noOfCols;
	}
	
	public static MDashboardContent[] getForSession()
	{
		List<MDashboardContent> list = getForSessionQuery().list();
		return list.toArray(new MDashboardContent[list.size()]);
	}
	
	public static Query getForSessionQuery()
	{
		Properties ctx = Env.getCtx();
		return new Query(ctx, Table_Name, null, null)
		.setOnlyActiveRecords(true)
		.setApplyAccessFilter(true, false)
		.setOrderBy(COLUMNNAME_ColumnNo+","+COLUMNNAME_AD_Client_ID+","+COLUMNNAME_Line);
	}
	
    public MDashboardContent (Properties ctx, int PA_DashboardContent_ID, String trxName)
    {
      super (ctx, PA_DashboardContent_ID, trxName);
    }
    public MDashboardContent (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }
    
    public int getAD_Menu_ID()
    {
    	if (m_AD_Menu_ID != null)
    		return m_AD_Menu_ID;
    	if (getAD_Window_ID() <= 0)
    	{
    		m_AD_Menu_ID = -1;
    		return m_AD_Menu_ID;
    	}
    	m_AD_Menu_ID = new Query(getCtx(), MMenu.Table_Name, MMenu.COLUMNNAME_AD_Window_ID+"=?", null)
    	.setParameters(getAD_Window_ID())
    	.setOnlyActiveRecords(true)
    	.setOrderBy(MMenu.COLUMNNAME_AD_Menu_ID+" DESC")
    	.firstId();
    	return m_AD_Menu_ID;
    }
    private Integer m_AD_Menu_ID = null;
    
    public I_AD_Menu getAD_Menu()
    {
    	return (I_AD_Menu)MTable.get(getCtx(), I_AD_Menu.Table_Name)
    		.getPO(getAD_Menu_ID(), get_TrxName());
    }
}
