/**
 * reportServer for JasperReport. Copyright (C) 2004 Peter Shen. Shanghai,
 * China. Email: zpshen@gmail.com
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Contributor: Marco LOMBARDO, Compilo subAdministrator. lombardo@mayking.com,
 * mar9000@gmail.com Italy.
 */
package net.sf.compilo.report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.compiere.util.CLogger;

;

/**
 * ReportPool
 * 
 * @author Peter Shen
 * @version $Id: ReportPool.java,v 1.2 2005/08/03 10:33:00 pshen Exp $
 * @description: JasperReport Object Pool
 */
public class ReportPool
{
    private static HashMap   pool   = new HashMap();

    private static final int MAXNUM = 15;
    private static int 	size = 0;

    private CLogger log = CLogger.getCLogger(ReportPool.class);
    
    public ReportInfo getReport (int AD_Process_ID )
    {
/*        ReportInfo reportinfo = (ReportInfo) pool.get(new Integer(AD_Process_ID));
        if (reportinfo == null)
        {
            reportinfo = loadReport(AD_Process_ID);
            addReport(AD_Process_ID, reportinfo, false);
        }
        else if(reportinfo.isDirty())
        {
            log.info("ReportInfo  " + reportinfo + "dirty, Refresh");
            reportinfo = loadReport(AD_Process_ID);
            if(!reportinfo.hasError())
                addReport(AD_Process_ID, reportinfo, true);
        }
*/
        ReportInfo reportinfo;
	reportinfo = loadReport(AD_Process_ID);
        return reportinfo;
    }

    private ReportInfo loadReport (int AD_Process_ID )
    {
        ReportInfo reportinfo = new ReportInfo(AD_Process_ID);
        return reportinfo;
    }
    
    private synchronized void addReport(int AD_Process_ID, ReportInfo reportinfo, boolean refresh)
    {
        if(refresh)
        {
            pool.put(new Integer(AD_Process_ID), reportinfo);            
        }
        else
        {
            while(size > MAXNUM)
            {
                Set s = pool.keySet();
                Iterator it = s.iterator();
            	if (it.hasNext())
            	{
            		Integer key = (Integer)it.next();
            		log.info("Remove " + key + " from the report pool");
            		pool.remove(key);
            	}
            	size--;
            }
            
            pool.put(new Integer(AD_Process_ID), reportinfo);
            size++;            
        }
        log.info("Load " + reportinfo + " into Report Pool" + " Size=" + size);
    }

}
