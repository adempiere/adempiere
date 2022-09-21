/**
 * 
 */
package org.compiere.asset.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.compiere.asset.model.MAsset;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @category STUB for upgrading to 361
 *
 */
public class CalloutAsset extends CalloutEngine {
	
	public String location (Properties ctx, int windowno, GridTab gridTab, GridField gridField, Object value, Object oldValue)
	{
		Integer locator = (Integer)value;
		if (locator == null || locator <= 0)
			return "";
		if (isCalloutActive())
			return "";
		//
		//TODO - found missing but MAsset table is asking for this in 3 location fields.
		//
		return "";
	}
	public String locator (Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value, Object oldValue)
	
	{
		Integer locator = (Integer)value;
		if (locator == null || locator <= 0)
			return "";
		if (isCalloutActive())
			return "";
		//
		//TODO - found missing but MAsset table is asking for this in 3 location fields.
		//
		return "";
	}
	

	public String asset(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (value != null && MAsset.COLUMNNAME_A_Asset_ID.equals(gridField.getColumnName()))
		{
			MAsset asset = new MAsset(ctx, (Integer) value, null);
			if (asset != null)
				gridTab.setValue(MAsset.COLUMNNAME_M_Product_ID, asset.getM_Product_ID());
		}
		
		return "";
		
	}
	
	
	/**
	 *	Table_Period.  Used to set the Manual Period Field.  This allows
	 *	the Spread Field to be displayed when there is a code that
	 *  has been setup as Yearly. 
	 *  The string in the Callout field is: 
	 *  <code>com.compiere.custom.CalloutEngine.Table_Period</code> 
	 *
	 *  @param ctx      Context
	 *  @param windowNo current Window No
	 *  @param gridTab     Model Tab
	 *  @param gridField   Model Field
	 *  @param value    The new value
	 *  @param oldValue The old value
	 *	@return error message or "" if OK
	 */
	public String tablePeriod(Properties ctx, int windowNo,
							  GridTab gridTab, GridField gridField, Object value, Object oldValue)
		{
		Integer depreciationTableHeaderId = (Integer)value;
			
			try
			{
				if (depreciationTableHeaderId != null){
				String SQL = "SELECT A_Term "
					+ "FROM A_Depreciation_Table_Header "
					+ "WHERE A_Depreciation_Table_Header_ID='"
					+depreciationTableHeaderId
					+"'";
				
				PreparedStatement pstmt = DB.prepareStatement(SQL, null);				// arhipac: compatibility
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
//					Charges - Set Context
						Env.setContext(ctx, windowNo, "A_DEPRECIATION_MANUAL_PERIOD", rs.getString("A_Term"));
						gridTab.setValue ("A_DEPRECIATION_MANUAL_PERIOD", rs.getString("A_Term"));
		
				}
				rs.close();
				pstmt.close();
				}
			}
			catch (SQLException e)
			{
				log.info("PeriodType "+ e);
				return e.getLocalizedMessage();
			}
			return "";
		}	//	Period Type
	
	/**
	 *	Field_Clear.  Used to set the Manual Period Field.  This allows
	 *	the Spread Field to be displayed when there is a code that
	 *  has been setup as Yearly. 
	 *  The string in the Callout field is: 
	 *  <code>com.compiere.custom.CalloutEngine.Table_Period</code> 
	 *
	 *  @param ctx      Context
	 *  @param windowNo current Window No
	 *  @param gridTab     Model Tab
	 *  @param gridField   Model Field
	 *  @param value    The new value
	 *  @param oldValue The old value
	 *	@return error message or "" if OK
	 */
	public String fieldClear(Properties ctx, int windowNo,
							 GridTab gridTab, GridField gridField, Object value, Object oldValue)
		{
		Object depreciationId = value;
			
			try
			{
				String SQL = "SELECT DepreciationType "
					+ "FROM A_Depreciation "
					+ "WHERE A_Depreciation_ID="
					+ depreciationId;
				
				PreparedStatement pstmt = DB.prepareStatement(SQL, null);				// arhipac: compatibility
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
				{
//					Charges - Set Context
					String depType = rs.getString("DepreciationType");
					if ("TAB".equals(depType) || "MAN".equals(depType))
					{
						Env.setContext(ctx, windowNo, "A_DEPRECIATION_MANUAL_PERIOD", "");
						gridTab.setValue ("A_Depreciation_Manual_Period", null);
						gridTab.setValue ("A_Depreciation_Manual_Amount", null);
						gridTab.setValue ("A_Depreciation_Table_Header_ID", null);
					}
					if (rs.getString("DepreciationType")== "TAB")
					{						
						gridTab.setValue ("A_Depreciation_Manual_Amount", null);
					}
					if (rs.getString("DepreciationType")== "MAN")
					{
						gridTab.setValue ("A_Depreciation_Table_Header_ID", null);
					}	
				}
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{
				log.info("PeriodType "+ e);
				return e.getLocalizedMessage();
			}
			return "";
		}	//	Period Type	

	/** ARHIPAC: TEO: BEGIN ------------------------------------------------------------------------------------------------------------------------------ */
	/* commented by @win - no necessary code
	public String invoiceLineProduct(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (isCalloutActive()) {
			return "";
		}
		ro.arhipac.adempiere.fa.ModelValidator.modelChange_InvoiceLine(
				SetGetUtil.wrap(mTab),
				-1);
		return "";
	}
	
	public String inventoryLineProduct(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		if (isCalloutActive()) {
			return "";
		}
		ro.arhipac.adempiere.fa.ModelValidator.modelChange_InventoryLine(
				SetGetUtil.wrap(mTab),
				-1);
		return "";
	}
	*/
}
