/**
 *
 */
package org.compiere.FA.feature;

import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.FA.model.MAssetGroup;
import org.compiere.FA.model.SetGetModel;
import org.compiere.FA.model.SetGetUtil;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.PO;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

 
 
 /**
  * Asset properties - classification of assets, service period, life use.
  *	@author Teo Sarca, SC ARHIPAC SERVICE SRL
  *	@version $Id$
  */
public class UseLifeImpl
	implements UseLife
{
	private final static String FIELD_UseLifeYears = "UseLifeYears";
	private final static String FIELD_UseLifeMonths = "UseLifeMonths";
	private final static String FIELD_FiscalPostfix = "_F";
	
	private SetGetModel m_obj = null;
	private CLogger log = CLogger.getCLogger(getClass());
	private boolean fiscal = false;
	
	/**
	 */
	public static UseLifeImpl get(SetGetModel obj) {
		return new UseLifeImpl(obj, false);
	}
	
	/**
	 */
	public static UseLifeImpl get(SetGetModel obj, boolean fiscal) {
		return new UseLifeImpl(obj, fiscal);
	}
	
	/**
	 */
	public UseLifeImpl(SetGetModel obj, boolean fiscal) {
		m_obj = obj;
		this.fiscal = fiscal;
	}
	
	/**
	 */
	public Properties getCtx() {
		return m_obj.getCtx();
	}
	
	public int get_Table_ID() {
		return m_obj.get_Table_ID();
	}

	public String get_TableName() {
		return m_obj.get_TableName();
	}

	/**
	 */
	private final static String getFieldName(String fieldName, boolean fiscal) {
		String field = fieldName;
		if (fiscal) {
			field += FIELD_FiscalPostfix;
		}
		return field;
	}
	
	/**
	 */
	public boolean isFiscal() {
		return fiscal;
	}
	
	/**
	 */
	public boolean set_AttrValue(String name, Object value) {
		return m_obj.set_AttrValue(name, value);
	}
	
	/**
	 */
	public Object get_AttrValue(String name) {
		return m_obj.get_AttrValue(name);
	}
	
	/**
	 */
	public boolean is_AttrValueChanged(String name) {
		return m_obj.is_AttrValueChanged(name);
	}
	
	/**
	 *	@return transaction name for decorated object
	 */
	public String get_TrxName() {
		return m_obj.get_TrxName();
	}

	/**	Set UseLifeMonths and UseLifeYears
	 *	@param	value	use life months
	 */
	public void setUseLifeMonths(int value) {
		if(CLogMgt.isLevelFine()) log.fine("Entering: value=" + value + ", " + this);
		m_obj.set_AttrValue(getFieldName(FIELD_UseLifeMonths, fiscal), Integer.valueOf(value));
		m_obj.set_AttrValue(getFieldName(FIELD_UseLifeYears, fiscal), Integer.valueOf(value/12));
		if(CLogMgt.isLevelFine()) log.fine("Leaving: value=" + value + ", " + this);
	}
	
	/**
	 *	@return use life months
	 */
	public int getUseLifeMonths() {
		Object obj = m_obj.get_AttrValue(getFieldName(FIELD_UseLifeMonths, fiscal));
		if (obj != null && obj instanceof Number) {
			return ((Number)obj).intValue();
		}
		return 0;
	}
	
	/**	Set UseLifeYears and UseLifeMonths
	 *	@param value		use life years
	 */
	public void setUseLifeYears(int value) {
		if(CLogMgt.isLevelFine()) log.fine("Entering: value=" + value + ", " + this);
		m_obj.set_AttrValue(getFieldName(FIELD_UseLifeYears, fiscal), Integer.valueOf(value));
		m_obj.set_AttrValue(getFieldName(FIELD_UseLifeMonths, fiscal), Integer.valueOf(value*12));
		if(CLogMgt.isLevelFine()) log.fine("Leaving: value=" + value + ", " + this);
	}
	
	/**
	 *	@return use life years
	 */
	public int getUseLifeYears() {
		Object obj = m_obj.get_AttrValue(getFieldName(FIELD_UseLifeYears, fiscal));
		if (obj != null && obj instanceof Number) {
			return ((Number)obj).intValue();
		}
		return 0;
	}
	
	/**
	 * Adjust use life years
	 * @param deltaUseLifeYears
	 * @param reset
	 */
	public void adjustUseLifeYears(int deltaUseLifeYears, boolean reset)
	{
		int uselife = (reset ? 0 : getUseLifeYears());
		int new_uselife = uselife + deltaUseLifeYears;
		setUseLifeYears(new_uselife);
		if(CLogMgt.isLevelFine()) 
			log.fine("UseLifeYears=" + uselife + ", delta=" + deltaUseLifeYears + " => new UseLifeYears=" + new_uselife + " (isFiscal=" + isFiscal() + ")");
	}
	
	/**
	 *	@return Asset Service Date (PIF)
	 */
	public Timestamp getAssetServiceDate() {
		if (m_obj instanceof UseLife) {
			return ((UseLife)m_obj).getAssetServiceDate();
		} else {
			Object obj = m_obj.get_AttrValue("AssetServiceDate");
			if (obj != null && obj instanceof Timestamp) {
				return (Timestamp)obj;
			}
		}
		return null;
	}
	
	/**
	 *	@return asset class ID
	 */
	/* commented out by @win
	public int getA_Asset_Class_ID()
	{
		if (m_obj instanceof UseLife)
		{
			return ((UseLife)m_obj).getA_Asset_Class_ID();
		}
		else
		{
			Object obj = m_obj.get_AttrValue("A_Asset_Class_ID");
			if (obj != null && obj instanceof Number)
			{
				return ((Number)obj).intValue();
			}
		}
		return 0;
	}
	*/ // end comment by @win
	
	/**	Validates and corrects errors in model  */
	public boolean validate() {
		return validate(true);
	}
	
	/**	Validates and corrects errors in model */
	public boolean validate(boolean saveError) {
		if(CLogMgt.isLevelFine()) log.fine("Entering: " + this);
		
		int useLifeYears = 0;
		int useLifeMonths = 0;
		useLifeYears = getUseLifeYears();
		useLifeMonths = getUseLifeMonths();
		
		if (useLifeMonths == 0) {
			useLifeMonths = useLifeYears * 12;
		}
		if (useLifeMonths % 12 != 0) {
			if(saveError) log.saveError("Error", "@Invalid@ @UseLifeMonths@=" + useLifeMonths + "(@Diff@=" + (useLifeMonths % 12) + ")" );
			return false;
		}
		if (useLifeYears == 0) {
			useLifeYears = (int)(useLifeMonths / 12);
		}
		/* commented out by @win
		int A_Asset_Class_ID = getA_Asset_Class_ID();
		if (A_Asset_Class_ID > 0 && (useLifeMonths == 0 || useLifeYears == 0)) {
			if(saveError) log.saveError("Error", "@Invalid@ @UseLifeMonths@=" + useLifeMonths + ", @UseLifeYears@=" + useLifeYears);
			return false;
		}
		*/ //commented out by @win
		
		setUseLifeMonths(useLifeMonths);
		setUseLifeYears(useLifeYears);
		
		/* commented by @win
		MAssetClass assetClass = MAssetClass.get(getCtx(), A_Asset_Class_ID);
		if (assetClass != null && !assetClass.validate(this)) {
			log.fine("Leaving [RETURN FALSE]");
			return false;
		}
		*/ //end comment by @win
		
		log.fine("Leaving [RETURN TRUE]");
		return true;
	}
	
	/**	String representation (intern)
	 */
	public String toString()
	{
		return 
			"UseLifeImpl[UseLife=" + getUseLifeYears() + "|" + getUseLifeMonths()
				+ ", isFiscal=" + isFiscal()
				+ ", AssetServiceDate=" + getAssetServiceDate()
				//+ ", A_Asset_Class=" + getA_Asset_Class_ID() //commented by @win
				+ ", m_obj=" + m_obj
				+ "]"
		;
	}
	
	/**	Calculate date accounting for = assetServiceDate + A_Current_Period
	 *	@param assetServiceDate	data PIF
	 *	@param A_Current_Period	 (displacement)
	 *	@return assetServiceDate + A_Current_Period
	 */
	public static Timestamp getDateAcct(Timestamp assetServiceDate, int A_Current_Period) {
		if (assetServiceDate == null)
			return null;
		return TimeUtil.addMonths(assetServiceDate, A_Current_Period);
	}
	
	
	
	/**
	 *	Callout Class 
	 */
	public static class Callout extends org.compiere.model.CalloutEngine {
		/**	*/
		private String validate(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
			/* commented out by @win
			Integer A_Asset_Class_ID = (Integer)mTab.getValue("A_Asset_Class_ID");
			if (A_Asset_Class_ID == null || A_Asset_Class_ID == 0) {
				return NO_ERROR;
			}
			*/ //end commented by @win
			Timestamp AssetServiceDate = (Timestamp)mTab.getValue("AssetServiceDate");
			if (AssetServiceDate == null) {
				return NO_ERROR;
			}
			/* commented out by @win
			MAssetClass assetClass = MAssetClass.get(ctx, A_Asset_Class_ID);
			if (assetClass == null) {
				return NO_ERROR;
			}
			*/ // end comment by @win
			
			Integer UseLifeMonths = (Integer)mTab.getValue("UseLifeMonths");
			if (UseLifeMonths == null) {
				UseLifeMonths = 0;
			}
			/* commented out by @win
			String errmsg = assetClass.validate(false, UseLifeMonths, AssetServiceDate);
			if(CLogMgt.isLevelFine()) log.fine("assetClass=" + assetClass + ", UseLifeMonths=" + UseLifeMonths + ", AssetServiceDate=" + AssetServiceDate + ", errmsg=" + errmsg);
			return errmsg;
			*/ // end comment by @win
			return NO_ERROR; //added by @win
		}
		
		/**	*/
		public String assetServiceDate(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
			if (isCalloutActive() || value == null) {
				return NO_ERROR;
			}
			return validate(ctx, WindowNo, mTab, mField, value, oldValue);
		}
		
		/**	*/
		public String useLife(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
			if (isCalloutActive()) {
				return NO_ERROR;
			}
			
			String sufix = "";
			int ivalue = 0;
			int UseLifeYears = 0;
			int UseLifeMonths = 0;
			String errmsg = "";
			if (value != null) {
				ivalue = ((Integer)value).intValue();
			}
			
			String columnName = mField.getColumnName().toUpperCase();
			if (columnName.endsWith(FIELD_FiscalPostfix)) {
				sufix = FIELD_FiscalPostfix;
				columnName = columnName.substring(0, columnName.length() - FIELD_FiscalPostfix.length());
			}
			
			if (columnName.equalsIgnoreCase("UseLifeMonths")) {
				//~ UseLifeMonths = ivalue;
				if (ivalue % 12 != 0) {
					errmsg = "@Invalid@ @UseLifeMonths " + sufix + "@=" + ivalue;
				} else {
					UseLifeYears = (int)(ivalue / 12);
					mTab.setValue("UseLifeYears" + sufix, Integer.valueOf(UseLifeYears));
				}
			}
			else if (columnName.equalsIgnoreCase("UseLifeYears")) {
				UseLifeMonths = ivalue * 12;
				//~ UseLifeYears = ivalue;
				mTab.setValue("UseLifeMonths" + sufix, Integer.valueOf(UseLifeMonths));
			}
			
			if (errmsg.length() > 0) {
				errmsg = Msg.parseTranslation(ctx, errmsg);
			}
			return errmsg;
		}
		
		/**
		 */
		public String assetGroup(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
			if (isCalloutActive()) {
				return NO_ERROR;
			}
			
			int A_Asset_Group_ID = -1;
			if (value != null && value instanceof Number) {
				A_Asset_Group_ID = ((Number)value).intValue();
			}
			MAssetGroup.updateAsset(SetGetUtil.wrap(mTab), A_Asset_Group_ID);
			return NO_ERROR;
		}
		
		/**	*/
		/* commented by @win
		public String assetClass(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
			if (isCalloutActive()) {
				return NO_ERROR;
			}
			
			String errmsg = NO_ERROR;
			int A_Asset_Class_ID = -1;
			String columnName = mField.getColumnName();
			if(CLogMgt.isLevelFine()) log.fine("Entering: columnName: " + columnName + ", value=" + value);
			
			if (value != null && value instanceof Number) {
				A_Asset_Class_ID = ((Number)value).intValue();
			}
			if(CLogMgt.isLevelFine()) log.fine("A_Asset_Class_ID=" + A_Asset_Class_ID);
			
			if (A_Asset_Class_ID > 0) {
				MAssetClass assetClass = MAssetClass.get(ctx, A_Asset_Class_ID);
				Integer UseLifeMonths = (Integer)mTab.getValue("UseLifeMonths_F");
				Timestamp AssetServiceDate = (Timestamp)mTab.getValue("AssetServiceDate");
				if (UseLifeMonths == null || UseLifeMonths == 0) {
					UseLifeMonths = assetClass.getA_Life_Period_Min(AssetServiceDate);
					mTab.setValue("UseLifeMonths", UseLifeMonths);
				}
				else {
					errmsg = assetClass.validate(false, UseLifeMonths, AssetServiceDate);
				}
				if(CLogMgt.isLevelFine()) log.fine("assetClass=" + assetClass + ", UseLifeMonths=" + UseLifeMonths + ", AssetServiceDate=" + AssetServiceDate + ", errmsg=" + errmsg);
			}
			
			if(CLogMgt.isLevelFine()) log.fine("Leaving: errmsg=" + errmsg);
			return errmsg;
		}
		*/ // end commented by @win
	} //	class Callout
 }