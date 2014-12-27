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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Properties;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;


/**
 *  ReportViewDataSource
 *
 * 	@author 	Peter Shen
 * 	@version 	$Id: ReportViewDataSource.java,v 1.3 2005/08/04 09:42:41 pshen Exp $
 *	@description:	ReportViewDataSource
 */
public class ReportViewDataSource extends compiereDataSource
{
 
    /**	Query Data				*/
	private PrintData		m_printData = null;
	private int				m_index	= 0;
	
    public ReportViewDataSource(Properties ctx, ProcessInfo pi, HashMap params)
    {
        ReportEngine re = ReportEngine.get(ctx, pi);
        m_printData = re.getPrintData();        
    }
    

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#next()
     */
    public boolean next() throws JRException
    {
        if(m_index >= m_printData.getRowCount())
            return false;
        
        m_printData.setRowIndex(m_index++);
        return true;
    }

    /* (non-Javadoc)
     * @see net.sf.jasperreports.engine.JRDataSource#getFieldValue(net.sf.jasperreports.engine.JRField)
     */
    public Object getFieldValue(JRField field) throws JRException
    {
        PrintDataElement pde = (PrintDataElement)m_printData.getNode(field.getName());
         if(pde == null || pde.isNull())
            return null;
            
        if(pde.isDate())
            return (java.util.Date)pde.getValue();
        if(pde.isNumeric())
            return (BigDecimal)pde.getValue();
        if(pde.isID() && field.getValueClassName().indexOf("Integer") >= 0)
            return (Integer)pde.getValue();
        else if(pde.isID() && field.getValueClassName().indexOf("String") >= 0)
            return pde.getValue().toString(); 
        if(pde.isYesNo())
            return (Boolean)pde.getValue(); 
            
       return pde.getValue().toString();        
    }

    
    /* (non-Javadoc)
     * @see net.sf.compilo.data.compiereDataSource#close()
     */
    public void close()
    {
    }
}
