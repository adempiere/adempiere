/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* Created on Nov 15, 2006 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.core.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

import org.posterita.lib.UdiConstants;
import org.posterita.core.bean.ElementBean;

public class ElementManager
{
    protected transient static final CLogger log = CLogger.getCLogger (ElementManager.class);
	private static ElementBean getUnknownElementBean(Properties ctx, String columnName)
	{
		ElementBean elementBean = new ElementBean();
		elementBean.setColumnName(columnName);
		elementBean.setDescription("Undefined column with name: " + columnName);
		elementBean.setHelp(elementBean.getDescription());
		elementBean.setName(columnName);
		elementBean.setPrintName(columnName);
		return elementBean;
	}
	
	private static String getElement_TrlSql(Properties ctx, String columnName)
	{
		String language = Env.getContext(ctx, UdiConstants.LANGUAGE_CTX_PARAM);
		
		if (language == null || language.trim().length() == 0)
		{
			language = Language.AD_Language_en_US;
		}
		
		String tableName = "AD_Element";
		StringBuffer whereClause = new StringBuffer("ColumnName='");
        whereClause.append(columnName);
        whereClause.append("' ");
		
		if (!language.equals(Language.AD_Language_en_US))
		{
			tableName = "AD_Element_Trl";
			whereClause = new StringBuffer();
			whereClause.append("AD_Element_ID= (select AD_Element_ID from AD_Element where ColumnName='" + columnName + "')");
			whereClause.append(" and AD_Language='");
            whereClause.append(language);
            whereClause.append("'");
		}
        
        StringBuffer sql = new StringBuffer();
        sql.append("Select ColumnName, Name, PrintName, Description, Help from ");
        sql.append(tableName);
        sql.append(" where ");
        sql.append(whereClause);
        
        return sql.toString();
    }
    
    public static String getMsg_trlSql(Properties ctx, String text)
    {
        String language = Env.getContext(ctx, UdiConstants.LANGUAGE_CTX_PARAM);
        
        if (language == null || language.trim().length() == 0)
        {
            language = Language.AD_Language_en_US;
        }
        
        String tableName = "AD_Message";
        StringBuffer whereClause = new StringBuffer("value='");
        whereClause.append(text);
        whereClause.append("' ");
        
        if (!language.equals(Language.AD_Language_en_US))
        {
            tableName = "AD_Element_Trl";
            whereClause = new StringBuffer();
            whereClause.append("AD_Message_ID= (select AD_Message_ID from AD_Message where value='");
            whereClause.append(text);
            whereClause.append("')");
            whereClause.append(" and AD_Language='");
            whereClause.append(language);
            whereClause.append("'");
        }
        
        StringBuffer sql = new StringBuffer();
        sql.append("Select Value, MsgText, MsgText, MsgTip, MsgTip from ");
        sql.append(tableName);
        sql.append(" where ");
        sql.append(whereClause);
        
        return sql.toString();
    }
		
	private static ElementBean getTrl(String sql)
    {
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		
		ElementBean retElementBean = null;
		try
		{
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				retElementBean = new ElementBean();
                retElementBean.setColumnName(rs.getString(1));
				retElementBean.setName(rs.getString(2));
				retElementBean.setPrintName(rs.getString(3));
				retElementBean.setDescription(rs.getString(4));
				retElementBean.setHelp(rs.getString(5));
			}
			else
			{
				return null;
			}
			rs.close();
		}
		catch (Exception ex)
		{
			log.severe("Could retrieve element translation with sql: " + sql);
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch (Exception ex){}
		}
		
		return retElementBean;		
	}
    
    public static ElementBean getMsg1(Properties ctx, String text)
    {
        ElementBean elementBean = null;
        
        elementBean = getTrl(getElement_TrlSql(ctx, text));
        
        if (elementBean == null)
        {
            elementBean = getTrl(getMsg_trlSql(ctx, text));
        }
        
        if (elementBean == null)
        {
            elementBean = getUnknownElementBean(ctx, text);
        }
        
        return elementBean;
    }
    
	public static ElementBean getMsg(Properties ctx, String text)
	{
		String language = Env.getContext(ctx, UdiConstants.LANGUAGE_CTX_PARAM);
		
		if (language == null || language.trim().length() == 0)
		{
			language = Language.AD_Language_en_US;
		}
		
		String msg = Msg.getElement(language, text, true);
		
		if (msg == null || msg == "")
		{
			msg = Msg.translate(language, text);
		}
		
		if (msg == null || msg == "")
		{
			msg = text;
		}
		
        int ind = msg.indexOf(Env.NL);
        if (ind != -1)
            msg = msg.substring(0, ind);

        ElementBean elementBean = new ElementBean();
		elementBean.setColumnName(text);
		elementBean.setDescription(msg);
		elementBean.setHelp(msg);
		elementBean.setName(msg);
		elementBean.setPrintName(msg);
		
		return elementBean;
	}
}
