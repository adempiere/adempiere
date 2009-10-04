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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

/**
 *  Calendar Period Model
 *
 *	@author Jorg Janke
 *	@version $Id: MPeriod.java,v 1.4 2006/07/30 00:51:05 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1779438 ] Minor auto period control bug
 * 				<li>BF [ 1893486 ] Auto Period Control return that period is always open
 *
 *  @author victor.perez@e-evolution.com, e-Evolution http://www.e-evolution.com
 * 			<li> FR [ 2520591 ] Support multiples calendar for Org 
 *			@see http://sourceforge.net/tracker2/?func=detail&atid=879335&aid=2520591&group_id=176962 
 */
public class MPeriod extends X_C_Period
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4342181292848531751L;


	/**
	 * Get Period from Cache
	 * @param ctx context
	 * @param C_Period_ID id
	 * @return MPeriod
	 */
	public static MPeriod get (Properties ctx, int C_Period_ID)
	{
		if (C_Period_ID <= 0)
			return null;
		//
		Integer key = new Integer(C_Period_ID);
		MPeriod retValue = (MPeriod) s_cache.get (key);
		if (retValue != null)
			return retValue;
		//
		retValue = new MPeriod (ctx, C_Period_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	} 	//	get

	/**
	 * 	Find standard Period of DateAcct based on Client Calendar
	 *	@param ctx context
	 *	@param DateAcct date
	 *	@return active Period or null
	 *  @deprecated
	 */
	public static MPeriod get (Properties ctx, Timestamp DateAcct)
	{	
		return get(ctx, DateAcct, 0);
	}	//	get
	
	/**
	 * Find standard Period of DateAcct based on Client Calendar
	 * @param ctx context
	 * @param DateAcct date
	 * @param AD_Org_ID Organization
	 * @return active Period or null
	 */
	public static MPeriod get (Properties ctx, Timestamp DateAcct, int AD_Org_ID)
	{
		
		if (DateAcct == null)
			return null;
		
        int C_Calendar_ID = getC_Calendar_ID(ctx,AD_Org_ID);
        
        return findByCalendar(ctx, DateAcct, C_Calendar_ID);
	}	//	get

	/**
	 * 
	 * @param ctx
	 * @param DateAcct
	 * @param C_Calendar_ID
	 * @return MPeriod
	 * @deprecated
	 */
	public static MPeriod findByCalendar(Properties ctx, Timestamp DateAcct, int C_Calendar_ID) {
		return findByCalendar(ctx, DateAcct, C_Calendar_ID, null);
	}
		
	/**
	 * 
	 * @param ctx
	 * @param DateAcct
	 * @param C_Calendar_ID
	 * @param trxName
	 * @return MPeriod
	 */
	public static MPeriod findByCalendar(Properties ctx, Timestamp DateAcct, int C_Calendar_ID, String trxName) {
		
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		//	Search in Cache first
		Iterator<MPeriod> it = s_cache.values().iterator();
		while (it.hasNext())
		{
			MPeriod period = (MPeriod)it.next();
			if (period.getC_Calendar_ID() == C_Calendar_ID && period.isStandardPeriod() && period.isInPeriod(DateAcct) 
					&& period.getAD_Client_ID() == AD_Client_ID)  // globalqss - CarlosRuiz - Fix [ 1820810 ] Wrong Period Assigned to Fact_Acct
				return period;
		}
		
		//	Get it from DB
	    MPeriod retValue = null;
		String sql = "SELECT * "
			+ "FROM C_Period "
			+ "WHERE C_Year_ID IN "
				+ "(SELECT C_Year_ID FROM C_Year WHERE C_Calendar_ID= ?)"
			+ " AND ? BETWEEN TRUNC(StartDate) AND TRUNC(EndDate)"
			+ " AND IsActive=? AND PeriodType=?";
        
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt (1, C_Calendar_ID);
            pstmt.setTimestamp (2, TimeUtil.getDay(DateAcct));
            pstmt.setString(3, "Y");
            pstmt.setString(4, "S");
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MPeriod period = new MPeriod(ctx, rs, trxName);
				Integer key = new Integer(period.getC_Period_ID());
				s_cache.put (key, period);
				if (period.isStandardPeriod())
					retValue = period;
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "DateAcct=" + DateAcct, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (retValue == null)
			s_log.info("No Standard Period for " + DateAcct 
				+ " (AD_Client_ID=" + AD_Client_ID + ")");
		return retValue;
	}

	/**
	 * 	Find valid standard Period of DateAcct based on Client Calendar
	 *	@param ctx context
	 *	@param DateAcct date
	 *	@return C_Period_ID or 0
	 *  @deprecated
	 */
	public static int getC_Period_ID (Properties ctx, Timestamp DateAcct)
	{
		MPeriod period = get (ctx, DateAcct);
		if (period == null)
			return 0;
		return period.getC_Period_ID();
	}	//	getC_Period_ID
	
	/**
	 * 	Find valid standard Period of DateAcct based on Client Calendar
	 *	@param ctx context
	 * @param DateAcct date
	 * @param AD_Org_ID Organization
	 *	@return C_Period_ID or 0
	 */
	public static int getC_Period_ID (Properties ctx, Timestamp DateAcct, int AD_Org_ID)
	{
		MPeriod period = get (ctx, DateAcct, AD_Org_ID);
		if (period == null)
			return 0;
		return period.getC_Period_ID();
	}	//	getC_Period_ID

	/**
	 * 	Is standard Period Open for Document Base Type
	 *	@param ctx context
	 *	@param DateAcct date
	 *	@param DocBaseType base type
	 *	@return true if open
	 *  @deprecated
	 */
	public static boolean isOpen (Properties ctx, Timestamp DateAcct, String DocBaseType)
	{
		return isOpen(ctx, DateAcct,DocBaseType, 0 );
	}	//	isOpen
	
	/**
	 * 	Is standard Period Open for Document Base Type
	 *	@param ctx context
	 * @param DateAcct date
	 * @param DocBaseType base type
	 * @param AD_Org_ID Organization
	 *	@return true if open
	 */
	public static boolean isOpen (Properties ctx, Timestamp DateAcct, String DocBaseType, int AD_Org_ID)
	{
		if (DateAcct == null)
		{
			s_log.warning("No DateAcct");
			return false;
		}
		if (DocBaseType == null)
		{
			s_log.warning("No DocBaseType");
			return false;
		}
		MPeriod period = MPeriod.get (ctx, DateAcct, AD_Org_ID);
		if (period == null)
		{
			s_log.warning("No Period for " + DateAcct + " (" + DocBaseType + ")");
			return false;
		}
		boolean open = period.isOpen(DocBaseType, DateAcct);
		if (!open)
			s_log.warning(period.getName()
				+ ": Not open for " + DocBaseType + " (" + DateAcct + ")");
		return open;
	}	//	isOpen

	/**
	 * 	Find first Year Period of DateAcct based on Client Calendar
	 *	@param ctx context
	 *	@param DateAcct date
	 *	@return active first Period
	 *  @deprecated
	 */
	public static MPeriod getFirstInYear (Properties ctx, Timestamp DateAcct)
	{
		return getFirstInYear(ctx , DateAcct, 0);
	}	//	getFirstInYear
		
	/**
	 * 	Find first Year Period of DateAcct based on Client Calendar
	 *	@param ctx context
	 * @param DateAcct date
	 * @param AD_Org_ID TODO
	 *	@return active first Period
	 */
	public static MPeriod getFirstInYear (Properties ctx, Timestamp DateAcct, int AD_Org_ID)
	{
		MPeriod retValue = null;
		int C_Calendar_ID = MPeriod.get(ctx, DateAcct, AD_Org_ID).getC_Calendar_ID();

        String sql = "SELECT * "
                    + "FROM C_Period "
                    + "WHERE C_Year_ID IN "
                    + "(SELECT p.C_Year_ID "
                    + "FROM C_Year y"
                    + " INNER JOIN C_Period p ON (y.C_Year_ID=p.C_Year_ID) "
                    + "WHERE y.C_Calendar_ID=?"
                    + "     AND ? BETWEEN StartDate AND EndDate)"
                    + " AND IsActive=? AND PeriodType=? "
                    + "ORDER BY StartDate";
        
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt (1, C_Calendar_ID);
			pstmt.setTimestamp (2, DateAcct);
			pstmt.setString (3, "Y");
			pstmt.setString (4, "S");
			rs = pstmt.executeQuery();
			if (rs.next())	//	first only
				retValue = new MPeriod(ctx, rs, null);
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return retValue;
	}	//	getFirstInYear

	/**	Cache							*/
	private static CCache<Integer,MPeriod> s_cache = new CCache<Integer,MPeriod>("C_Period", 10);
	
	/**	Logger							*/
	private static CLogger			s_log = CLogger.getCLogger (MPeriod.class); 
	
	/** Calendar 					   */
	private int 					m_C_Calendar_ID = 0;
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Period_ID id
	 *	@param trxName transaction
	 */
	public MPeriod (Properties ctx, int C_Period_ID, String trxName)
	{
		super (ctx, C_Period_ID, trxName);
		if (C_Period_ID == 0)
		{
		//	setC_Period_ID (0);		//	PK
		//  setC_Year_ID (0);		//	Parent
		//  setName (null);
		//  setPeriodNo (0);
		//  setStartDate (new Timestamp(System.currentTimeMillis()));
			setPeriodType (PERIODTYPE_StandardCalendarPeriod);
		}
	}	//	MPeriod

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MPeriod (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MPeriod

	/**
	 * 	Parent constructor
	 *	@param year year
	 *	@param PeriodNo no
	 *	@param name name
	 *	@param startDate start
	 *	@param endDate end
	 */
	public MPeriod (MYear year, int PeriodNo, String name, 
		Timestamp startDate,Timestamp endDate)
	{
		this (year.getCtx(), 0, year.get_TrxName());
		setClientOrg(year);
		setC_Year_ID(year.getC_Year_ID());
		setPeriodNo(PeriodNo);
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
	}	//	MPeriod
	
	
	/**	Period Controls			*/
	private MPeriodControl[] m_controls = null;
		
	/**
	 * 	Get Period Control
	 *	@param requery requery
	 *	@return period controls
	 */
	public MPeriodControl[] getPeriodControls (boolean requery)
	{
		if (m_controls != null && !requery)
			return m_controls;
		//
		ArrayList<MPeriodControl> list = new ArrayList<MPeriodControl>();
		String sql = "SELECT * FROM C_PeriodControl "
			+ "WHERE C_Period_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, getC_Period_ID());
			rs = pstmt.executeQuery();
			while (rs.next())
				list.add (new MPeriodControl (getCtx(), rs, null));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//
		m_controls = new MPeriodControl[list.size ()];
		list.toArray (m_controls);
		return m_controls;
	}	//	getPeriodControls
	
	/**
	 * 	Get Period Control
	 *	@param DocBaseType Document Base Type
	 *	@return period control or null
	 */
	public MPeriodControl getPeriodControl (String DocBaseType)
	{
		if (DocBaseType == null)
			return null;
		getPeriodControls(false);
		for (int i = 0; i < m_controls.length; i++)
		{
		//	log.fine("getPeriodControl - " + 1 + " - " + m_controls[i]);
			if (DocBaseType.equals(m_controls[i].getDocBaseType()))
				return m_controls[i];
		}
		return null;
	}	//	getPeriodControl

	/**
	 * 	Date In Period
	 *	@param date date
	 *	@return true if in period
	 */
	public boolean isInPeriod (Timestamp date)
	{
		if (date == null)
			return false;
		Timestamp dateOnly = TimeUtil.getDay(date);
		Timestamp from = TimeUtil.getDay(getStartDate());
		if (dateOnly.before(from))
			return false;
		Timestamp to = TimeUtil.getDay(getEndDate());
		if (dateOnly.after(to))
			return false;
		return true;
	}	//	isInPeriod
	
	/**
	 * Is Period Open for Doc Base Type
	 * @param DocBaseType document base type
	 * @return true if open
	 * @deprecated since 3.3.1b; use {@link #isOpen(String, Timestamp)} instead
	 */
	public boolean isOpen (String DocBaseType)
	{
		return isOpen(DocBaseType, null);
	}
	
	/**
	 * Is Period Open for Doc Base Type
	 * @param DocBaseType document base type
	 * @param dateAcct date;
	 * 			Applies only for "Auto Period Control":
	 * 			<li>if not null, date should be in auto period range (today - OpenHistory, today+OpenHistory)
	 * 			<li>if null, this period should be in auto period range
	 * @return true if open
	 * @since 3.3.1b
	 */
	public boolean isOpen (String DocBaseType, Timestamp dateAcct)
	{
		if (!isActive())
		{
			s_log.warning("Period not active: " + getName());
			return false;
		}

		MAcctSchema as = MClient.get(getCtx(), getAD_Client_ID()).getAcctSchema();
		if (as != null && as.isAutoPeriodControl())
		{
			Timestamp today = TimeUtil.trunc(new Timestamp (System.currentTimeMillis()), TimeUtil.TRUNC_DAY);
			Timestamp first = TimeUtil.addDays(today, - as.getPeriod_OpenHistory()); 
			Timestamp last = TimeUtil.addDays(today, as.getPeriod_OpenFuture());
			Timestamp date1, date2;
			if (dateAcct != null) {
				date1 = TimeUtil.trunc(dateAcct, TimeUtil.TRUNC_DAY);
				date2 = date1;
			}
			else {
				date1 = getStartDate();
				date2 = getEndDate();
			}
			//
			if (date1.before(first))
			{
				log.warning ("Automatic Period Control:" + date1 + " before first day - " + first);
				return false;
			}
			if (date2.after(last))
			{
				log.warning ("Automatic Period Control:" + date2 + " after last day - " + first);
				return false;
			}
			//	We are OK
			if (isInPeriod(today))
			{
				as.setC_Period_ID(getC_Period_ID());
				as.save();
			}
			return true;
		}
		
		//	Standard Period Control
		if (DocBaseType == null)
		{
			log.warning(getName() + " - No DocBaseType");
			return false;
		}
		MPeriodControl pc = getPeriodControl (DocBaseType);
		if (pc == null)
		{
			log.warning(getName() + " - Period Control not found for " + DocBaseType);
			return false;
		}
		log.fine(getName() + ": " + DocBaseType);
		return pc.isOpen();
	}	//	isOpen

	/**
	 * 	Standard Period
	 *	@return true if standard calendar perios
	 */
	public boolean isStandardPeriod()
	{
		return PERIODTYPE_StandardCalendarPeriod.equals(getPeriodType());
	}	//	isStandardPeriod
	
	
	/**
	 * 	Before Save.
	 * 	Truncate Dates
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Truncate Dates
		Timestamp date = getStartDate(); 
		if (date != null)
			setStartDate(TimeUtil.getDay(date));
		else
			return false;
		//
		date = getEndDate();
		if (date != null)
			setEndDate(TimeUtil.getDay(date));
		else
			setEndDate(TimeUtil.getMonthLastDay(getStartDate()));
		
		if (getEndDate().before(getStartDate()))
		{
			SimpleDateFormat df = DisplayType.getDateFormat(DisplayType.Date);
			log.saveError("Error", df.format(getEndDate()) + " < " + df.format(getStartDate()));
			return false;
		}
		
		MYear year = new MYear(getCtx(), getC_Year_ID(), get_TrxName());
		
		Query query = MTable.get(getCtx(), "C_Period")
		.createQuery("C_Year_ID IN (SELECT y.C_Year_ID from C_Year y WHERE" +
				"                   y.C_Calendar_ID =?)" +
				" AND (? BETWEEN StartDate AND EndDate" +
				" OR ? BETWEEN StartDate AND EndDate)" +
				" AND PeriodType=?",get_TrxName());
		query.setParameters(new Object[] {year.getC_Calendar_ID(),
				getStartDate(), getEndDate(),
				getPeriodType()});
		
		List<MPeriod> periods = query.list();
		
		for ( int i=0; i < periods.size(); i++)
		{
			if ( periods.get(i).getC_Period_ID() != getC_Period_ID() )
			{
				log.saveError("Error", "Period overlaps with: "	+ periods.get(i).getName());
				return false;
			}
		}
		
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord)
		{
		//	SELECT Value FROM AD_Ref_List WHERE AD_Reference_ID=183
			MDocType[] types = MDocType.getOfClient(getCtx());
			int count = 0;
			ArrayList<String> baseTypes = new ArrayList<String>();
			for (int i = 0; i < types.length; i++)
			{
				MDocType type = types[i];
				String DocBaseType = type.getDocBaseType();
				if (baseTypes.contains(DocBaseType))
					continue;
				MPeriodControl pc = new MPeriodControl(this, DocBaseType);
				if (pc.save())
					count++;
				baseTypes.add (DocBaseType);
			}
			log.fine("PeriodControl #" + count);
		}
		return success;
	}	//	afterSave
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPeriod[");
		sb.append (get_ID())
			.append("-").append (getName())
			.append(", ").append(getStartDate()).append("-").append(getEndDate())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * Conventient method for testing if a period is open
	 * @param ctx
	 * @param dateAcct
	 * @param docBaseType
	 * @throws PeriodClosedException if period is closed
	 * @see #isOpen(Properties, Timestamp, String)
	 * @deprecated
	 */
	public static void testPeriodOpen(Properties ctx, Timestamp dateAcct, String docBaseType)
	throws PeriodClosedException 
	{
		if (!MPeriod.isOpen(ctx, dateAcct, docBaseType)) {
			throw new PeriodClosedException(dateAcct, docBaseType);
		}
	}
	
	/**
	 * Conventient method for testing if a period is open
	 * @param ctx
	 * @param dateAcct
	 * @param docBaseType
	 * @param AD_Org_ID Organization
	 * @throws PeriodClosedException if period is closed
	 * @see #isOpen(Properties, Timestamp, String, int)
	 */
	public static void testPeriodOpen(Properties ctx, Timestamp dateAcct, String docBaseType, int AD_Org_ID)
	throws PeriodClosedException 
	{
		if (!MPeriod.isOpen(ctx, dateAcct, docBaseType, AD_Org_ID)) {
			throw new PeriodClosedException(dateAcct, docBaseType);
		}
	}
	
		/**
	 * Conventient method for testing if a period is open
	 * @param ctx
	 * @param dateAcct
	 * @param C_DocType_ID
	 * @throws PeriodClosedException
	 * @see {@link #isOpen(Properties, Timestamp, String)}
     * @deprecated
	 */
	public static void testPeriodOpen(Properties ctx, Timestamp dateAcct, int C_DocType_ID)
	throws PeriodClosedException
	{
		MDocType dt = MDocType.get(ctx, C_DocType_ID);
		testPeriodOpen(ctx, dateAcct, dt.getDocBaseType());
	}
	
	/**
	 * Conventient method for testing if a period is open
	 * @param ctx
	 * @param dateAcct
	 * @param C_DocType_ID
	 * @param AD_Org_ID Organization
	 * @throws PeriodClosedException
	 * @see {@link #isOpen(Properties, Timestamp, String, int)}
	 */
	public static void testPeriodOpen(Properties ctx, Timestamp dateAcct, int C_DocType_ID, int AD_Org_ID)
	throws PeriodClosedException
	{
		MDocType dt = MDocType.get(ctx, C_DocType_ID);
		testPeriodOpen(ctx, dateAcct, dt.getDocBaseType(),  AD_Org_ID);
	}
	
	/**
	 *  Get Calendar of Period
	 *  @return calendar
	 */
	public int getC_Calendar_ID()
	{
		if (m_C_Calendar_ID == 0)
		{
			MYear year = (MYear) getC_Year();
			if (year != null)
				m_C_Calendar_ID = year.getC_Calendar_ID();
			else
				log.severe("@NotFound@ C_Year_ID=" + getC_Year_ID());
		}
		return m_C_Calendar_ID;
	}   //  getC_Calendar_ID
    
	/**
	 * Get Calendar for Organization
	 * @param ctx Context
	 * @param AD_Org_ID Organization
	 * @return
	 */
    public static int getC_Calendar_ID(Properties ctx,int AD_Org_ID)
    {	
        int C_Calendar_ID = 0;
        if (AD_Org_ID != 0)
        {
            MOrgInfo info = MOrgInfo.get(ctx, AD_Org_ID);
            C_Calendar_ID = info.getC_Calendar_ID();
        }
        
        if (C_Calendar_ID == 0)
        {
            MClientInfo cInfo = MClientInfo.get(ctx);
            C_Calendar_ID = cInfo.getC_Calendar_ID();
        }
        
      return C_Calendar_ID;
    }   //  getC_Calendar_ID
    
}	//	MPeriod
