/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for HR_Education
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_HR_Education extends PO implements I_HR_Education, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_HR_Education (Properties ctx, int HR_Education_ID, String trxName)
    {
      super (ctx, HR_Education_ID, trxName);
      /** if (HR_Education_ID == 0)
        {
			setHR_Degree_ID (0);
			setHR_Education_ID (0);
			setYearOfPassing (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Education (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_Education[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Duration.
		@param Duration 
		Normal Duration in Duration Unit
	  */
	public void setDuration (String Duration)
	{
		set_Value (COLUMNNAME_Duration, Duration);
	}

	/** Get Duration.
		@return Normal Duration in Duration Unit
	  */
	public String getDuration () 
	{
		return (String)get_Value(COLUMNNAME_Duration);
	}

	public org.eevolution.model.I_HR_Degree getHR_Degree() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Degree)MTable.get(getCtx(), org.eevolution.model.I_HR_Degree.Table_Name)
			.getPO(getHR_Degree_ID(), get_TrxName());	}

	/** Set Degree.
		@param HR_Degree_ID 
		Degree for an Employee
	  */
	public void setHR_Degree_ID (int HR_Degree_ID)
	{
		if (HR_Degree_ID < 1) 
			set_Value (COLUMNNAME_HR_Degree_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Degree_ID, Integer.valueOf(HR_Degree_ID));
	}

	/** Get Degree.
		@return Degree for an Employee
	  */
	public int getHR_Degree_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Degree_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Education.
		@param HR_Education_ID 
		Education of an Employee
	  */
	public void setHR_Education_ID (int HR_Education_ID)
	{
		if (HR_Education_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Education_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Education_ID, Integer.valueOf(HR_Education_ID));
	}

	/** Get Education.
		@return Education of an Employee
	  */
	public int getHR_Education_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Education_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_Employee getHR_Employee() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Employee)MTable.get(getCtx(), org.eevolution.model.I_HR_Employee.Table_Name)
			.getPO(getHR_Employee_ID(), get_TrxName());	}

	/** Set Payroll Employee.
		@param HR_Employee_ID Payroll Employee	  */
	public void setHR_Employee_ID (int HR_Employee_ID)
	{
		if (HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Employee_ID, Integer.valueOf(HR_Employee_ID));
	}

	/** Get Payroll Employee.
		@return Payroll Employee	  */
	public int getHR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set School/College.
		@param SchoolCollege 
		School/College
	  */
	public void setSchoolCollege (String SchoolCollege)
	{
		set_Value (COLUMNNAME_SchoolCollege, SchoolCollege);
	}

	/** Get School/College.
		@return School/College
	  */
	public String getSchoolCollege () 
	{
		return (String)get_Value(COLUMNNAME_SchoolCollege);
	}

	/** Set School/College Address.
		@param SchoolCollegeAddress 
		School/College Address
	  */
	public void setSchoolCollegeAddress (String SchoolCollegeAddress)
	{
		set_Value (COLUMNNAME_SchoolCollegeAddress, SchoolCollegeAddress);
	}

	/** Get School/College Address.
		@return School/College Address
	  */
	public String getSchoolCollegeAddress () 
	{
		return (String)get_Value(COLUMNNAME_SchoolCollegeAddress);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	/** YearOfPassing AD_Reference_ID=53618 */
	public static final int YEAROFPASSING_AD_Reference_ID=53618;
	/** 1970 = 1970 */
	public static final String YEAROFPASSING_1970 = "1970";
	/** 1971 = 1971 */
	public static final String YEAROFPASSING_1971 = "1971";
	/** 1972 = 1972 */
	public static final String YEAROFPASSING_1972 = "1972";
	/** 1973 = 1973 */
	public static final String YEAROFPASSING_1973 = "1973";
	/** 1974 = 1974 */
	public static final String YEAROFPASSING_1974 = "1974";
	/** 1975 = 1975 */
	public static final String YEAROFPASSING_1975 = "1975";
	/** 1976 = 1976 */
	public static final String YEAROFPASSING_1976 = "1976";
	/** 1977 = 1977 */
	public static final String YEAROFPASSING_1977 = "1977";
	/** 1978 = 1978 */
	public static final String YEAROFPASSING_1978 = "1978";
	/** 1979 = 1979 */
	public static final String YEAROFPASSING_1979 = "1979";
	/** 1980 = 1980 */
	public static final String YEAROFPASSING_1980 = "1980";
	/** 1981 = 1981 */
	public static final String YEAROFPASSING_1981 = "1981";
	/** 1982 = 1982 */
	public static final String YEAROFPASSING_1982 = "1982";
	/** 1983 = 1983 */
	public static final String YEAROFPASSING_1983 = "1983";
	/** 1984 = 1984 */
	public static final String YEAROFPASSING_1984 = "1984";
	/** 1985 = 1985 */
	public static final String YEAROFPASSING_1985 = "1985";
	/** 1986 = 1986 */
	public static final String YEAROFPASSING_1986 = "1986";
	/** 1987 = 1987 */
	public static final String YEAROFPASSING_1987 = "1987";
	/** 1988 = 1988 */
	public static final String YEAROFPASSING_1988 = "1988";
	/** 1989 = 1989 */
	public static final String YEAROFPASSING_1989 = "1989";
	/** 1990 = 1990 */
	public static final String YEAROFPASSING_1990 = "1990";
	/** 1991 = 1991 */
	public static final String YEAROFPASSING_1991 = "1991";
	/** 1992 = 1992 */
	public static final String YEAROFPASSING_1992 = "1992";
	/** 1993 = 1993 */
	public static final String YEAROFPASSING_1993 = "1993";
	/** 1994 = 1994 */
	public static final String YEAROFPASSING_1994 = "1994";
	/** 1995 = 1995 */
	public static final String YEAROFPASSING_1995 = "1995";
	/** 1996 = 1996 */
	public static final String YEAROFPASSING_1996 = "1996";
	/** 1997 = 1997 */
	public static final String YEAROFPASSING_1997 = "1997";
	/** 1998 = 1998 */
	public static final String YEAROFPASSING_1998 = "1998";
	/** 1999 = 1999 */
	public static final String YEAROFPASSING_1999 = "1999";
	/** 2000 = 2000 */
	public static final String YEAROFPASSING_2000 = "2000";
	/** 2001 = 2001 */
	public static final String YEAROFPASSING_2001 = "2001";
	/** 2002 = 2002 */
	public static final String YEAROFPASSING_2002 = "2002";
	/** 2003 = 2003 */
	public static final String YEAROFPASSING_2003 = "2003";
	/** 2004 = 2004 */
	public static final String YEAROFPASSING_2004 = "2004";
	/** 2005 = 2005 */
	public static final String YEAROFPASSING_2005 = "2005";
	/** 2006 = 2006 */
	public static final String YEAROFPASSING_2006 = "2006";
	/** 2007 = 2007 */
	public static final String YEAROFPASSING_2007 = "2007";
	/** 2008 = 2008 */
	public static final String YEAROFPASSING_2008 = "2008";
	/** 2009 = 2009 */
	public static final String YEAROFPASSING_2009 = "2009";
	/** 2010 = 2010 */
	public static final String YEAROFPASSING_2010 = "2010";
	/** 2011 = 2011 */
	public static final String YEAROFPASSING_2011 = "2011";
	/** 2012 = 2012 */
	public static final String YEAROFPASSING_2012 = "2012";
	/** 2013 = 2013 */
	public static final String YEAROFPASSING_2013 = "2013";
	/** 2014 = 2014 */
	public static final String YEAROFPASSING_2014 = "2014";
	/** 2015 = 2015 */
	public static final String YEAROFPASSING_2015 = "2015";
	/** 2016 = 2016 */
	public static final String YEAROFPASSING_2016 = "2016";
	/** 2017 = 2017 */
	public static final String YEAROFPASSING_2017 = "2017";
	/** 2018 = 2018 */
	public static final String YEAROFPASSING_2018 = "2018";
	/** 2019 = 2019 */
	public static final String YEAROFPASSING_2019 = "2019";
	/** 2020 = 2020 */
	public static final String YEAROFPASSING_2020 = "2020";
	/** 2021 = 2021 */
	public static final String YEAROFPASSING_2021 = "2021";
	/** 2022 = 2022 */
	public static final String YEAROFPASSING_2022 = "2022";
	/** 2023 = 2023 */
	public static final String YEAROFPASSING_2023 = "2023";
	/** 2024 = 2024 */
	public static final String YEAROFPASSING_2024 = "2024";
	/** 2025 = 2025 */
	public static final String YEAROFPASSING_2025 = "2025";
	/** 2026 = 2026 */
	public static final String YEAROFPASSING_2026 = "2026";
	/** 2027 = 2027 */
	public static final String YEAROFPASSING_2027 = "2027";
	/** 2028 = 2028 */
	public static final String YEAROFPASSING_2028 = "2028";
	/** 2029 = 2029 */
	public static final String YEAROFPASSING_2029 = "2029";
	/** 2030 = 2030 */
	public static final String YEAROFPASSING_2030 = "2030";
	/** 2031 = 2031 */
	public static final String YEAROFPASSING_2031 = "2031";
	/** 2032 = 2032 */
	public static final String YEAROFPASSING_2032 = "2032";
	/** 2033 = 2033 */
	public static final String YEAROFPASSING_2033 = "2033";
	/** 2034 = 2034 */
	public static final String YEAROFPASSING_2034 = "2034";
	/** 2035 = 2035 */
	public static final String YEAROFPASSING_2035 = "2035";
	/** 2036 = 2036 */
	public static final String YEAROFPASSING_2036 = "2036";
	/** 2037 = 2037 */
	public static final String YEAROFPASSING_2037 = "2037";
	/** 2038 = 2038 */
	public static final String YEAROFPASSING_2038 = "2038";
	/** 2039 = 2039 */
	public static final String YEAROFPASSING_2039 = "2039";
	/** 2040 = 2040 */
	public static final String YEAROFPASSING_2040 = "2040";
	/** 2041 = 2041 */
	public static final String YEAROFPASSING_2041 = "2041";
	/** 2042 = 2042 */
	public static final String YEAROFPASSING_2042 = "2042";
	/** 2043 = 2043 */
	public static final String YEAROFPASSING_2043 = "2043";
	/** 2044 = 2044 */
	public static final String YEAROFPASSING_2044 = "2044";
	/** 2045 = 2045 */
	public static final String YEAROFPASSING_2045 = "2045";
	/** 2046 = 2046 */
	public static final String YEAROFPASSING_2046 = "2046";
	/** 2047 = 2047 */
	public static final String YEAROFPASSING_2047 = "2047";
	/** 2048 = 2048 */
	public static final String YEAROFPASSING_2048 = "2048";
	/** 2049 = 2049 */
	public static final String YEAROFPASSING_2049 = "2049";
	/** 2050 = 2050 */
	public static final String YEAROFPASSING_2050 = "2050";
	/** Set Year of Passing.
		@param YearOfPassing 
		Year of Passing
	  */
	public void setYearOfPassing (String YearOfPassing)
	{

		set_Value (COLUMNNAME_YearOfPassing, YearOfPassing);
	}

	/** Get Year of Passing.
		@return Year of Passing
	  */
	public String getYearOfPassing () 
	{
		return (String)get_Value(COLUMNNAME_YearOfPassing);
	}
}