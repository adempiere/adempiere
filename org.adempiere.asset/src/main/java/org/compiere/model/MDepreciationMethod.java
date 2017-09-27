package org.compiere.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.exceptions.DBException;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;

/**
 * Method of adjusting the difference between depreciation (Calculated) and registered as (booked).
 * ex. MDI, LDI, YDI ...
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MDepreciationMethod extends X_A_Depreciation_Method
{
	/** Standard Constructor */
	public MDepreciationMethod (Properties ctx, int A_Depreciation_Method_ID, String trxName)
	{
		super (ctx, A_Depreciation_Method_ID, trxName);
	}	//	MDepreciationMethod

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MDepreciationMethod (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MDepreciationMethod

	/**		Cache									*/
	private static CCache<Integer,MDepreciationMethod>
	s_cache = new CCache<Integer,MDepreciationMethod>(Table_Name, 5);
	/**		Cache for type							*/
	private static CCache<String,MDepreciationMethod>
	s_cache_forType = new CCache<String,MDepreciationMethod>(Table_Name+"_DepreciationType", 5);
	
	/**
	 *
	 */
	private static void addToCache(MDepreciationMethod depr)
	{
		if (depr == null)
		{
			return;
		}
		s_cache.put(depr.get_ID(), depr);
		s_cache_forType.put(depr.getDepreciationType(), depr);
	}

	/**
	 *
	 */
	private static int getPrecision()
	{
		return 2;
	}
 
	/**
	 *
	 */
	public static MDepreciationMethod get(Properties ctx, int A_Depreciation_Method_ID)
	{
		MDepreciationMethod depr = s_cache.get(A_Depreciation_Method_ID);
		if (depr != null)
		{
			return depr;
		}
		depr = new MDepreciationMethod(ctx, A_Depreciation_Method_ID, null);
		if (depr.get_ID() > 0)
		{
			addToCache(depr);
		}
		else
		{
			depr = null;
		}
		return depr;
	} // get

	/**
	 *
	 */
	public static MDepreciationMethod get(Properties ctx, String depreciationType)
	{
		String key = depreciationType;
		MDepreciationMethod depr = s_cache_forType.get(key);
		if (depr != null)
		{
			return depr;
		}
		depr = new Query(ctx, Table_Name, COLUMNNAME_DepreciationType+"=?", null)
					.setParameters(new Object[]{depreciationType})
					.firstOnly();
		addToCache(depr);
		return depr;
	}

	
	/**
	 *
	 */
	public static BigDecimal invoke (Properties ctx, String depreciationType,
										int A_Asset_ID, BigDecimal A_Asset_Adjustment,
										int A_PeriodNo, String PostingType, int A_Asset_Acct_ID)
	{
		MDepreciationMethod depr = get(ctx, depreciationType);
		if (depr == null)
		{
			throw new IllegalArgumentException("@NotFound@ @DepreciationType@ " + depreciationType);
		}
		return depr.invoke(A_Asset_ID, A_Asset_Adjustment, A_PeriodNo, PostingType, A_Asset_Acct_ID);
	}

	/**
	 * Calculate adjustment
	 * @return adjustment to be applied in the specified period
	 */
	public BigDecimal invoke (MDepreciationWorkfile assetwk,
								MAssetAcct assetAcct, BigDecimal A_Asset_Adjustment,
								int A_PeriodNo)
	{
		return invoke(assetwk.getA_Asset_ID(), A_Asset_Adjustment, A_PeriodNo, assetAcct.getPostingType(), assetAcct.get_ID());
	}
	
	/**
	 * Calculate adjustment
	 * @return adjustment to be applied in the specified period
	 */
	public BigDecimal invoke (int A_Asset_ID, BigDecimal A_Asset_Adjustment,
								int A_PeriodNo, String PostingType, int A_Asset_Acct_ID)
	{
		String depreciationType = getDepreciationType();
		BigDecimal retValue = null;
		
		if(CLogMgt.isLevelFine())
			log.fine("Entering: DepreciationMethodType=" + depreciationType 
						+ ", A_Asset_ID=" + A_Asset_ID + ", A_Asset_Adjustment=" + A_Asset_Adjustment
						+ ", A_PeriodNo=" + A_PeriodNo + ", PostingType=" + PostingType + ", A_Asset_Acct_ID=" + A_Asset_Acct_ID
			);
		
		if (depreciationType.equalsIgnoreCase("MDI"))
		{
			retValue = apply_MDI(A_Asset_ID, A_Asset_Adjustment, A_PeriodNo, PostingType, A_Asset_Acct_ID);
		}
		else if (depreciationType.equalsIgnoreCase("YDI"))
		{
			retValue = apply_YDI(A_Asset_ID, A_Asset_Adjustment, A_PeriodNo, PostingType, A_Asset_Acct_ID);
		}
		else if (depreciationType.equalsIgnoreCase("LDI"))
		{
			retValue = apply_LDI(A_Asset_ID, A_Asset_Adjustment, A_PeriodNo, PostingType, A_Asset_Acct_ID);
		}
		else
		{
			String sql = "{ ? = call "+ depreciationType + "(?, ?, ?, ?, ?) }";
			CallableStatement cs = null;
			try
			{
				cs = DB.prepareCall(sql);
				cs.registerOutParameter(1, java.sql.Types.DECIMAL);
				cs.setInt(2, A_Asset_ID);
				cs.setBigDecimal(3, A_Asset_Adjustment);
				cs.setInt(4, A_PeriodNo);
				cs.setString(5, PostingType);
				cs.setInt(6, A_Asset_Acct_ID);
				cs.execute();						
				retValue = cs.getBigDecimal(1);
				cs.close();
			}
			catch (SQLException e)
			{
				throw new DBException(e);
			}
			finally
			{
				DB.close(cs);
				cs = null;
			}
		}
		//
		if (retValue == null)
		{
			retValue = BigDecimal.ZERO;
		}
		//
		if(CLogMgt.isLevelFine()) log.fine("Leaving: retValue=" + retValue);
		return retValue;
	}

	/**	MDI	- adjustment is made ​​in the current month
	 *
	 */
	public BigDecimal apply_MDI (int A_Asset_ID, BigDecimal A_Asset_Adjustment, int A_PeriodNo, String PostingType, int A_Asset_Acct_ID)
	{
		return A_Asset_Adjustment;
	}
	
	/**	YDI	-adjustment is made ​​for periods of the year remains
	 *
	 */
	public BigDecimal apply_YDI (int A_Asset_ID, BigDecimal A_Asset_Adjustment, int A_PeriodNo, String PostingType, int A_Asset_Acct_ID)
	{
		BigDecimal remainingPeriods = new BigDecimal(12 - A_PeriodNo);
		if (remainingPeriods.signum() == 0) {
			log.warning("A_PeriodNo=" + A_PeriodNo + " => remainingPeriods=" + remainingPeriods);
		}
		BigDecimal periodAdjustment = A_Asset_Adjustment.divide(remainingPeriods, getPrecision(), RoundingMode.HALF_UP);
		
		if(CLogMgt.isLevelFine()) {
			log.fine("A_Asset_Adjustment=" + A_Asset_Adjustment + ", remainingPeriods=" + remainingPeriods + " => periodAdjustment=" + periodAdjustment);
		}
		return periodAdjustment;
	}
	
	/**	LDI	- adjustment is made ​​on the remaining life
	 *
	 */
	public BigDecimal apply_LDI (int A_Asset_ID, BigDecimal A_Asset_Adjustment, int A_PeriodNo, String PostingType, int A_Asset_Acct_ID)
	{
		MDepreciationWorkfile wk = MDepreciationWorkfile.get(getCtx(), A_Asset_ID, PostingType);
		
		int A_Life_Period = wk.getA_Life_Period();
		int A_Period_Posted = wk.getA_Period_Posted();
		BigDecimal remainingPeriods = new BigDecimal(A_Life_Period - A_Period_Posted + 1);
		if (remainingPeriods.signum() == 0) {
			log.warning("A_Life_Period=" + A_Life_Period + ", A_Period_Posted=" + A_Period_Posted + " => remainingPeriods=" + remainingPeriods);
			return BigDecimal.ZERO;
		}
		
		BigDecimal periodAdjustment = A_Asset_Adjustment.divide(remainingPeriods, getPrecision(), RoundingMode.HALF_UP);
		
		if(CLogMgt.isLevelFine()) {
			log.fine("A_Asset_Adjustment=" + A_Asset_Adjustment + ", remainingPeriods=" + remainingPeriods + " => periodAdjustment=" + periodAdjustment);
		}
		return periodAdjustment;
	}
}
