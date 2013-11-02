/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2010 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.compiere.model.MResource;
import org.compiere.model.MResourceType;
import org.compiere.model.MUOM;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.form.crp.CRPDatasetFactory;
import org.eevolution.form.crp.CRPModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * CRP this class show the capacity for Resource Manufacturing
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author alberto.juarez@e-evolution.com ,  www.e-evolution.com
 */
public class CRP {
	
	public int         	m_WindowNo = 0;
	public static CLogger log = CLogger.getCLogger(CRP.class);
    public int AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
    protected CRPModel model;

    /**
     * create Chart using the data set and UOM
     * @param dataset
     * @param title
     * @param uom
     * @return JFreeChart
     */
    protected JFreeChart  createChart(CategoryDataset dataset  , String title, MUOM uom) 
	{
		JFreeChart chart = ChartFactory.createBarChart3D(title," "," ",dataset,PlotOrientation.VERTICAL,true,true,false);
		if(uom == null || uom.isHour())
		{
			chart = ChartFactory.createBarChart3D
	        ( title ,
	          Msg.translate(Env.getCtx(), "Days"),    // X-Axis label
	          Msg.translate(Env.getCtx(), "Hours"),   // Y-Axis label
	          dataset,         						  // Dataset
			  PlotOrientation.VERTICAL, 			  // orientation
	          true,                     			  // include legend
	          true,                     			  // tooltips?
	          false                     			  // URLs?
	        );
		}
		else
		{
			chart = ChartFactory.createBarChart3D
	        ( title ,
	          Msg.translate(Env.getCtx(), "Days"),            // X-Axis label
	          Msg.translate(Env.getCtx(), "Kilo"),             // Y-Axis label
	          dataset,         // Dataset
			  PlotOrientation.VERTICAL, // orientation
	          true,                     // include legend
	          true,                     // tooltips?
	          false                     // URLs?
	        );				        	        	        
		}
        return chart;        
    }
    
    /**
     * Create Category Dataset based on date start and resource
     * @param start
     * @param resource
     * @return CategoryDataset
     */
    protected CategoryDataset createDataset(Timestamp start ,MResource resource)
 	{
		 GregorianCalendar gc1 = new GregorianCalendar();
		 gc1.setTimeInMillis(start.getTime());
		 gc1.clear(Calendar.MILLISECOND);
 		 gc1.clear(Calendar.SECOND);
 		 gc1.clear(Calendar.MINUTE);
 		 gc1.clear(Calendar.HOUR_OF_DAY);
 			  	
 		 Timestamp date = start;
 		 String namecapacity = Msg.translate(Env.getCtx(), "Capacity");
 		 String nameload = Msg.translate(Env.getCtx(), "Load");
 		 String namesummary = Msg.translate(Env.getCtx(), "Summary");
		 MResourceType t = MResourceType.get(Env.getCtx(),resource.getS_ResourceType_ID());
		 int days = 1;
	     long hours = t.getTimeSlotHours();
		 
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();	 
		 //		Long Hours = new Long(hours); 			 		 			 
		 int C_UOM_ID = DB.getSQLValue(null,"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , resource.getS_Resource_ID());
		 MUOM uom = MUOM.get(Env.getCtx(),C_UOM_ID);
	     if (!uom.isHour())
 	     {	
 			return dataset;		 		 			 
 		 }			 		
	     long summary = 0;
	     
 		 while(days < 32)
 		 {	
 		 		String day = new String(new Integer (date.getDate()).toString()); 
 		 		long HoursLoad = getLoad(resource,date).longValue();
 		 		Long Hours = new Long(hours); 
 		 		
 		 		switch(gc1.get(Calendar.DAY_OF_WEEK))
				{
					case Calendar.SUNDAY:
						days ++; 
						if (t.isOnSunday())
						{	
							dataset.addValue(hours, namecapacity, day );
							dataset.addValue(HoursLoad ,nameload, day );
					        dataset.addValue(summary, namesummary, day );
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{	
							dataset.addValue(0, namecapacity, day );
							dataset.addValue(HoursLoad , nameload, day);
							dataset.addValue(summary, namesummary, day );
							summary = summary - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}					
					case Calendar.MONDAY:
						days ++; 
						if (t.isOnMonday())
						{
								dataset.addValue(hours, namecapacity, day );
								dataset.addValue(HoursLoad , nameload, day );
						        dataset.addValue(summary, namesummary, day);
						        summary = summary + Hours.intValue() - (HoursLoad ); 
						        gc1.add(Calendar.DATE, 1);
				 		 		date = new Timestamp(gc1.getTimeInMillis());
				 		 		break;
						}
						else
						{
								dataset.addValue(0, namecapacity, day );
								dataset.addValue(HoursLoad, nameload, day );
								dataset.addValue(summary, namesummary, day );
								summary = summary  - (HoursLoad); 
								gc1.add(Calendar.DATE, 1);
				 		 		date = new Timestamp(gc1.getTimeInMillis());
				 		 		break;
						}
					case Calendar.TUESDAY:
						days ++; 
						if (t.isOnTuesday())
						{							
							dataset.addValue(hours, namecapacity, day );				
							dataset.addValue(HoursLoad, nameload, day );
					        dataset.addValue(summary, namesummary, day );
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{
							dataset.addValue(0, namecapacity, day );
							dataset.addValue(HoursLoad, nameload, day);
							dataset.addValue(summary, namesummary, day);
							summary = summary - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
					case Calendar.WEDNESDAY:
						days ++; 
						if (t.isOnWednesday())
						{				 		 										 		 			 		
							dataset.addValue(hours, namecapacity, day);			
							dataset.addValue(HoursLoad, nameload, day);
					        dataset.addValue(summary, namesummary, day);
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{									 		 			 
							dataset.addValue(0, namecapacity, day);
							dataset.addValue(HoursLoad, nameload, day);
							dataset.addValue(summary, namesummary, day);
							summary = summary - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
					case Calendar.THURSDAY:
						days ++; 
						if (t.isOnThursday())
						{				 		 										 		 			 							
							dataset.addValue(hours, namecapacity, day);				
							dataset.addValue(HoursLoad, nameload, day);
					        dataset.addValue(summary, namesummary, day);
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{									 		 			 		
							dataset.addValue(0, namecapacity, day);
							dataset.addValue(HoursLoad, nameload, day);
							dataset.addValue(summary, namesummary, day);
							summary = summary  - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}						
					case Calendar.FRIDAY:
						days ++; 
						if (t.isOnFriday())
						{				 		 										 		 			 		
							dataset.addValue(hours, namecapacity, day);
							dataset.addValue(HoursLoad, nameload, day);												       
					        dataset.addValue(summary, namesummary, day);
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{
									 		 			 		
							dataset.addValue(0, namecapacity, day);
							dataset.addValue(HoursLoad, nameload, day);
							dataset.addValue(summary, namesummary, day);
							summary = summary - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
					case Calendar.SATURDAY:
						days ++; 
						if (t.isOnSaturday())	
						{				 		 										 		 			 		
							dataset.addValue(hours, namecapacity, day);
							dataset.addValue(HoursLoad,nameload, day);
					        dataset.addValue(summary,namesummary, day);
					        summary = summary + Hours.intValue() - (HoursLoad); 
					        gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}
						else
						{
							dataset.addValue(0, namecapacity, day);
							dataset.addValue(HoursLoad, nameload, day);
							dataset.addValue(summary, namesummary, day);
							summary = summary - (HoursLoad); 
							gc1.add(Calendar.DATE, 1);
			 		 		date = new Timestamp(gc1.getTimeInMillis());
			 		 		break;
						}				
				}
 		 		 		 	
 		 } 	 		 
 		return dataset;
 	}
    
    /**
     * Create Category Dataset based on Weight , date start and resource
     * @param start
     * @param resource
     * @return CategoryDataset
     */
    protected CategoryDataset createWeightDataset(Timestamp start, MResource rosource) {

		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTimeInMillis(start.getTime());
		gc1.clear(Calendar.MILLISECOND);
 		gc1.clear(Calendar.SECOND);
 		gc1.clear(Calendar.MINUTE);
 		gc1.clear(Calendar.HOUR_OF_DAY);
 			  	
 		 String namecapacity = Msg.translate(Env.getCtx(), "Capacity");
 		 String nameload = Msg.translate(Env.getCtx(), "Load");
 		 String namesummary = Msg.translate(Env.getCtx(), "Summary");
 		 String namepossiblecapacity = "Possible Capacity";
 		 
 		 MResourceType t = MResourceType.get(Env.getCtx(),rosource.getS_ResourceType_ID());

		 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		 
		 double currentweight = DB.getSQLValue(null, "SELECT SUM( (mo.qtyordered-mo.qtydelivered)*(SELECT mp.weight FROM M_Product mp WHERE  mo.m_product_id=mp.m_product_id )) FROM PP_Order mo WHERE AD_Client_ID=?", rosource.getAD_Client_ID());
		 double dailyCapacity = rosource.getDailyCapacity().doubleValue(); 
		 double utilization = rosource.getPercentUtilization().doubleValue();
	     double summary = 0;

	     int day = 0;
	     while(day < 32) {
	    	 
 		 		day++;
 		 		switch(gc1.get(Calendar.DAY_OF_WEEK)) {
 		 		
					case Calendar.SUNDAY:
						
						if (t.isOnSunday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					
						
					case Calendar.MONDAY:
											
						if (t.isOnMonday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					
					
					case Calendar.TUESDAY:
								
						if (t.isOnTuesday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					

					case Calendar.WEDNESDAY:
						
						if (t.isOnWednesday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					

					case Calendar.THURSDAY:
						
						if (t.isOnThursday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					

					case Calendar.FRIDAY:
					
						if (t.isOnFriday()) {
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					

					case Calendar.SATURDAY:

						if (t.isOnSaturday()) {	
							
							currentweight -= (dailyCapacity*utilization)/100;
					        summary += ((dailyCapacity*utilization)/100); 

							dataset.addValue(dailyCapacity ,namepossiblecapacity, new Integer(day));
							dataset.addValue((dailyCapacity*utilization)/100, namecapacity, new Integer(day) );
						}
						else {

							dataset.addValue(0,namepossiblecapacity, new Integer(day) );
							dataset.addValue(0, namecapacity, new Integer(day) );
						}			
						
		 		 		break;					
				}
		
 		 		dataset.addValue(currentweight, nameload, new Integer(day));
				dataset.addValue(summary, namesummary, new Integer(day) );

				gc1.add(Calendar.DATE, 1);
 		 } 	 		 
	     return dataset;
 	}
	
    /**
     * get the Load of a Resource
     * @param resource
     * @param start
     * @return BigDecimal load of the resource 
     */
	private BigDecimal getLoad(MResource resource, Timestamp start)
 	{
		model = CRPDatasetFactory.get(start, start, resource);
		return model.calculateLoad(start, resource, null);
		
 	}
}
