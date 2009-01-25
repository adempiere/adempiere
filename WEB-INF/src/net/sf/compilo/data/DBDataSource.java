/**
 *   reportServer for JasperReport.
 *   Copyright (C) 2004  Peter Shen.
 *   Shanghai, China.
 *   Email: zpshen@gmail.com
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Lesser General Public
 *   License as published by the Free Software Foundation; either
 *   version 2.1 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public
 *   License along with this library; if not, write to the Free Software
 *   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *   Contributor: Marco LOMBARDO, Compilo subAdministrator.
 *                lombardo@mayking.com, mar9000@gmail.com
 *                Italy.
 **/
package net.sf.compilo.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

import net.sf.compilo.report.ReportInfo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRQueryExecuter;

import org.compiere.util.DB;

/**
 *  DBDataSource
 *
 * 	@author 	Peter Shen
 * 	@version 	$Id: DBDataSource.java,v 1.3 2005/08/04 09:42:41 pshen Exp $
 *	@description:	DBDataSource
 */
public class DBDataSource extends compiereDataSource
{
    private PreparedStatement m_pstmt = null;
    private ResultSet	m_resultSet = null;
    
    public DBDataSource(Properties ctx, ReportInfo ri, HashMap params)
    {
        JasperReport jr = ri.getJasperReport();
        //Generate parameters map
        HashMap parametersMap = new HashMap();
        JRParameter[] jpara = jr.getParameters();
        for (int i=0; i<jpara.length; i++)
        {
            parametersMap.put(jpara[i].getName(), jpara[i]);
        }
        
        try
        {
            m_pstmt = JRQueryExecuter.getStatement(
                    jr.getQuery(), 
    				parametersMap, 
    				params, 
    				DB.getConnectionRO()
    				);
            if(m_pstmt != null)
                m_resultSet = m_pstmt.executeQuery();
        }
        catch (JRException jre)
        {
            log.saveError("GetStatement", jre);
            m_resultSet = null;
        }
        catch (SQLException sqle)
        {
            log.saveError("GetResultSet", sqle);
            m_resultSet = null;
        }
    }
    
   
    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField field) throws JRException
    {
        Object objValue = null;
		if (field != null && m_resultSet != null)
		{
			Class clazz = field.getValueClass();

			try
			{
				if (clazz.equals(java.lang.Object.class))
				{
					objValue = m_resultSet.getObject(field.getName());
				}
				else if (clazz.equals(java.lang.Boolean.class))
				{
					objValue = m_resultSet.getBoolean(field.getName()) ? Boolean.TRUE : Boolean.FALSE;
				}
				else if (clazz.equals(java.lang.Byte.class))
				{
					objValue = new Byte(m_resultSet.getByte(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.util.Date.class))
				{
					objValue = m_resultSet.getDate(field.getName());
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.sql.Timestamp.class))
				{
					objValue = m_resultSet.getTimestamp(field.getName());
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.sql.Time.class))
				{
					objValue = m_resultSet.getTime(field.getName());
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Double.class))
				{
					objValue = new Double(m_resultSet.getDouble(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Float.class))
				{
					objValue = new Float(m_resultSet.getFloat(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Integer.class))
				{
					objValue = new Integer(m_resultSet.getInt(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Long.class))
				{
					objValue = new Long(m_resultSet.getLong(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.Short.class))
				{
					objValue = new Short(m_resultSet.getShort(field.getName()));
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.math.BigDecimal.class))
				{
					objValue = m_resultSet.getBigDecimal(field.getName());
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
				else if (clazz.equals(java.lang.String.class))
				{
					objValue = m_resultSet.getString(field.getName());
					if(m_resultSet.wasNull())
					{
						objValue = null;
					}
				}
			}
			catch (Exception e)
			{
				throw new JRException("Unable to get value for field '" + field.getName() + "' of class '" + clazz.getName() + "'", e);
			}
		}
		return objValue;        
    }
    
    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException
    {
        boolean retValue = false;
        if(m_resultSet == null)
            return retValue;
        
        try
        {
            retValue = m_resultSet.next();
        }
        catch(SQLException sqle)
        {
            throw new JRException(sqle);
        }
        return retValue;
    }
    
    /* (non-Javadoc)
     * @see net.sf.compilo.data.compiereDataSource#close()
     */
    public void close()
    {
        try
        {
            if(m_resultSet != null)
                m_resultSet.close();
            
            if(m_pstmt != null)
                m_pstmt.close();
        }
        catch (Exception e){}
    }
}
