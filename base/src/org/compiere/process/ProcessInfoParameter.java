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
package org.compiere.process;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 	Process Parameter
 *
 *  @author Jorg Janke
 *  @version $Id: ProcessInfoParameter.java,v 1.2 2006/07/30 00:54:44 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2430845 ] Add ProcessInfoParameter.getParameterAsBoolean method
 */
public class ProcessInfoParameter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4536416337960754407L;

	/**
	 *  Construct Parameter
	 *  @param parameterName parameter name
	 *  @param parameter parameter
	 *  @param parameter_To to parameter
	 *  @param info info
	 *  @param info_To to info
	 */
	public ProcessInfoParameter (String parameterName, Object parameter, Object parameter_To, String info, String info_To)
	{
		setParameterName (parameterName);
		setParameter (parameter);
		setParameter_To (parameter_To);
		setInfo (info);
		setInfo_To (info_To);
	}   //  ProcessInfoParameter

	private String 	m_ParameterName;
	private Object 	m_Parameter;
	private	Object	m_Parameter_To;
	private String	m_Info = "";
	private String 	m_Info_To = "";

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString()
	{
		//	From .. To
		if (m_Parameter_To != null || m_Info_To.length() > 0)
			return "ProcessInfoParameter[" + m_ParameterName + "=" + m_Parameter
				+ (m_Parameter==null ? "" : "{" + m_Parameter.getClass().getName() + "}")
				+ " (" + m_Info + ") - "
				+ m_Parameter_To 
				+ (m_Parameter_To==null ? "" : "{" + m_Parameter_To.getClass().getName() + "}")
				+ " (" + m_Info_To + ")";
		//	Value
		return "ProcessInfoParameter[" + m_ParameterName + "=" + m_Parameter
			+ (m_Parameter==null ? "" : "{" + m_Parameter.getClass().getName() + "}")
			+ " (" + m_Info + ")";
	}	//	toString


	/**
	 * Method getInfo
	 * @return String
	 */
	public String getInfo ()
	{
		return m_Info;
	}

	/**
	 * Method getInfo_To
	 * @return String
	 */
	public String getInfo_To ()
	{
		return m_Info_To;
	}

	/**
	 * Method getParameter
	 * @return Object
	 */
	public Object getParameter ()
	{
		return m_Parameter;
	}
	
	/**
	 * Method getParameter as Int
	 * @return Object
	 */
	public int getParameterAsInt ()
	{
		if (m_Parameter == null)
			return 0;
		if (m_Parameter instanceof Number)
			return ((Number)m_Parameter).intValue();
		BigDecimal bd = new BigDecimal(m_Parameter.toString());
		return bd.intValue();
	}	//	getParameterAsInt
	
	/**
	 * Method getParameter as Boolean
	 * @return boolean value
	 */
	public boolean getParameterAsBoolean ()
	{
		if (m_Parameter == null)
			return false;
		if (m_Parameter instanceof Boolean)
			return ((Boolean)m_Parameter).booleanValue();
		return "Y".equals(m_Parameter);
	}

	/**
	 * Method getParameter_To
	 * @return Object
	 */
	public Object getParameter_To ()
	{
		return m_Parameter_To;
	}

	/**
	 * Method getParameter as Int
	 * @return Object
	 */
	public int getParameter_ToAsInt ()
	{
		if (m_Parameter_To == null)
			return 0;
		if (m_Parameter_To instanceof Number)
			return ((Number)m_Parameter_To).intValue();
		BigDecimal bd = new BigDecimal(m_Parameter_To.toString());
		return bd.intValue();
	}	//	getParameter_ToAsInt

	/**
	 * Method getParameter as Boolean
	 * @return boolean
	 */
	public boolean getParameter_ToAsBoolean ()
	{
		if (m_Parameter_To == null)
			return false;
		if (m_Parameter_To instanceof Boolean)
			return ((Boolean)m_Parameter_To).booleanValue();
		return "Y".equals(m_Parameter_To);
	}
	
	
	/**
	 * Method getParameterName
	 * @return String
	 */
	public String getParameterName ()
	{
		return m_ParameterName;
	}

	/**
	 * Method setInfo
	 * @param Info String
	 */
	public void setInfo (String Info)
	{
		if (Info == null)
			m_Info = "";
		else
			m_Info = Info;
	}

	/**
	 * Method setInfo_To
	 * @param Info_To String
	 */
	public void setInfo_To (String Info_To)
	{
		if (Info_To == null)
			m_Info_To = "";
		else
			m_Info_To = Info_To;
	}

	/**
	 * Method setParameter
	 * @param Parameter Object
	 */
	public void setParameter (Object Parameter)
	{
		m_Parameter = Parameter;
	}

	/**
	 * Method setParameter_To
	 * @param Parameter_To Object
	 */
	public void setParameter_To (Object Parameter_To)
	{
		m_Parameter_To = Parameter_To;
	}

	/**
	 * Method setParameterName
	 * @param ParameterName String
	 */
	public void setParameterName (String ParameterName)
	{
		m_ParameterName = ParameterName;
	}

}   //  ProcessInfoParameter
