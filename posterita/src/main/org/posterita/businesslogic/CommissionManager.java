/**
 *  Product: Posterita Web-Based POS (an Adempiere Plugin)
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
 */

/**
	@author Alok Pathak
 */

package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MCharge;
import org.compiere.model.MCommission;
import org.compiere.model.MCommissionLine;
import org.compiere.model.MPInstance;
import org.compiere.process.CommissionCalc;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.CommissionBean;
import org.posterita.core.TimestampConvertor;
import org.posterita.exceptions.InvalidDateTimeException;
import org.posterita.exceptions.OperationException;
import org.posterita.factory.SystemObjectsFactory;
import org.posterita.model.UDIMCharge;
import org.posterita.model.UDIMCommission;
import org.posterita.model.UDIMCommissionLine;


public class CommissionManager
{
    private static final int GENERATE_COMMISSION_PROCESS_ID=123;
    private static final String CHARGE_NAME="Commission Charge";
    
    public static void getCreateCommission(Properties ctx,String name,int partnerId,String docBasisType,BigDecimal amtMultiplier,String frequency,BigDecimal subtractAmt,String trxName) throws OperationException
    {
        int [] commissionIds = MCommission.getAllIDs(MCommission.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_BPARTNER_ID="+partnerId+" and isActive='Y'",null);
        if(commissionIds!=null && commissionIds.length>1)
            throw new OperationException("Sales rep has more than one commission"+ commissionIds.length);
        
        MCommission com;
        MCommissionLine comLine;
        if(commissionIds==null || commissionIds.length<1)
        {
            com = new MCommission(ctx,0,trxName);
            comLine = new MCommissionLine(ctx,0,trxName);
        }
        else
        {
            com = new MCommission(ctx,commissionIds[0],trxName); 
            MCommissionLine [] line =com.getLines();
            if(line.length!=1)
                throw new OperationException("either No commissionLine or more than One commission Line has bean defined for this bPartner");
            comLine = new MCommissionLine(ctx,line[0].get_ID(),trxName);
        }
        com.setC_Charge_ID(getCreateCommissionCharge(ctx));
        com.setName(name);
        com.setC_BPartner_ID(partnerId);
        com.setFrequencyType(frequency);
        com.setC_Currency_ID(POSTerminalManager.getCurrencyOfDefaultCashBook(ctx).get_ID());
        com.setDocBasisType(docBasisType);
        com.setListDetails(true);
        UDIMCommission udiCom = new UDIMCommission(com);
        udiCom.save();
        
               
        
        comLine.setC_Commission_ID(udiCom.getMCommission().get_ID());
        comLine.setAmtMultiplier(amtMultiplier.divide(new BigDecimal(100)));
        comLine.setDescription(name);
        comLine.setCommissionOrders(true);
        comLine.setIsPositiveOnly(true);
        if(subtractAmt!=null)
            comLine.setAmtSubtract(subtractAmt);
        UDIMCommissionLine udiComLine = new UDIMCommissionLine(comLine);
        udiComLine.save();
        
    }
    
    private static int getCreateCommissionCharge(Properties ctx) throws OperationException
    {
        int chargeIds [] = MCharge.getAllIDs(MCharge.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and name='"+CHARGE_NAME+"'",null);
        
        if(chargeIds.length<1)
        {
            MCharge charge = new MCharge(ctx,0,null);
            charge.setName(CHARGE_NAME);
            charge.setChargeAmt(new BigDecimal(0));
            charge.setC_TaxCategory_ID(SystemObjectsFactory.getFactoryInstance().get(ctx,SystemObjectsFactory.TAX_CATEGORY_DEFAULT_ID).getID());
            
            UDIMCharge udiCharge = new UDIMCharge(charge);
            udiCharge.save();
            
            return udiCharge.getMCharge().get_ID();
        }
        else
        {
            return chargeIds[0];
        }
    }
    
    public static void generateCommission(Properties ctx,String date) throws OperationException, InvalidDateTimeException
    {
//        deleteComAmtTableData(ctx);
        int commissionIds [] = MCommission.getAllIDs(MCommission.Table_Name," AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and isActive='Y'",null);
        if(commissionIds!=null && commissionIds.length>0)
            for(int i=0;i<commissionIds.length;i++)
            {
                calculateCommission(ctx,date,commissionIds[i]);
            }
    }
    private static void calculateCommission(Properties ctx,String date, int c_commission_id) throws OperationException, InvalidDateTimeException
    {
        Timestamp timestamp = TimestampConvertor.getTimestamp(date, TimestampConvertor.REPORTS_DATE_PATTERN);
        ProcessInfoParameter param[]=
        {
                new ProcessInfoParameter("StartDate",timestamp,null,null,null),
        };
        
        MPInstance instance = new MPInstance(ctx,GENERATE_COMMISSION_PROCESS_ID,c_commission_id);
        instance.save();
        ProcessInfo poInfo = new ProcessInfo("Generate Commission",GENERATE_COMMISSION_PROCESS_ID);
        poInfo.setRecord_ID(c_commission_id);
        poInfo.setParameter(param);
        poInfo.setAD_Process_ID(GENERATE_COMMISSION_PROCESS_ID);
        poInfo.setAD_PInstance_ID(instance.get_ID());
        CommissionCalc calcComm = new CommissionCalc();
        calcComm.startProcess(ctx,poInfo,null);
    }
    
    public static ArrayList<CommissionBean> getCommissionAmt(Properties ctx) throws OperationException
    {
        
        String sql="select camt.C_COMMISSIONAMT_ID," +//1
        " camt.C_COMMISSIONRUN_ID," +//2
        "camt.C_COMMISSIONLINE_ID," +//3
        "camt.CONVERTEDAMT," +//4
        "camt.ACTUALQTY," +//5
        "camt.COMMISSIONAMT," +//6
        "cline.DESCRIPTION," +//7
        "DECODE(com.FREQUENCYTYPE,'"+MCommission.FREQUENCYTYPE_Monthly+"','Monthly','"+
        MCommission.FREQUENCYTYPE_Quarterly+"','Quarterly','"+
        MCommission.FREQUENCYTYPE_Weekly+"','Weekly','"+
        MCommission.FREQUENCYTYPE_Yearly+"','Yearly',com.FREQUENCYTYPE) frequency," +//8
        "DECODE(com.DOCBASISTYPE,'"+MCommission.DOCBASISTYPE_Invoice+"','Invoice','"+
        MCommission.DOCBASISTYPE_Order+"','Order','"+
        MCommission.DOCBASISTYPE_Receipt+"','Payment Receipt',com.DOCBASISTYPE) docbasis," +//9
        "cline.AMTMULTIPLIER*100, "+//10
        "crun.DESCRIPTION,"+//11
        "nvl(cline.AMTSUBTRACT,0)"+//12
        " from C_COMMISSIONAMT camt, C_COMMISSIONLINE cline,C_COMMISSION com,C_COMMISSIONRUN crun " +
        " where camt.C_COMMISSIONLINE_ID=cline.C_COMMISSIONLINE_ID"+
        " and cline.C_COMMISSION_ID=com.C_COMMISSION_ID"+
        " and camt.C_COMMISSIONRUN_ID=crun.C_COMMISSIONRUN_ID"+
        " and camt.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        CommissionBean bean=null;
        ArrayList<CommissionBean> list =  new ArrayList<CommissionBean>();
        
        
        
        
        try 
        {
            
            ResultSet rs= pstmt.executeQuery();
            
            if(rs.next())
            {
                bean=new CommissionBean();
                bean.setCommissionAmtId(rs.getInt(1));
                bean.setCommissionRunId(rs.getInt(2));
                bean.setCommissionLineId(rs.getInt(3));
                bean.setConvertedAmt(rs.getBigDecimal(4));
                bean.setActualQty(rs.getInt(5));
                bean.setCommissionAmt(rs.getBigDecimal(6));
                bean.setCommissionLineName(rs.getString(7));
                bean.setFrequencyType(rs.getString(8));
                bean.setDocBasisType(rs.getString(9));
                bean.setAmtMultiplier(rs.getBigDecimal(10));
                bean.setPeriodAndCurrencyDesc(rs.getString(11));
                bean.setSubtractAmt(rs.getBigDecimal(12));
                list.add(bean);
                
            }
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try 
            {
                pstmt.close();
            } 
            catch (SQLException e) 
            {
                
                
            }
        }
        return list;
    }
    
    
    
    public static ArrayList getCommissionDetails(Properties ctx, int commissionAmtId) throws OperationException
    {
        String sql=" select det.C_COMMISSIONDETAIL_ID," +//1
        "det.C_COMMISSIONAMT_ID," +//2
        " det.REFERENCE ," +//3
        " det.C_ORDERLINE_ID," +//4
        "det.C_INVOICELINE_ID," +//5
        " det.INFO," +//6
        "det.C_CURRENCY_ID," +//7
        "det.ACTUALAMT," +//8
        "det.CONVERTEDAMT," +//9
        " det.ACTUALQTY, " +//10
        "inv.C_INVOICE_ID,"+//11
        " ord.C_ORDER_ID"+//12
        " from  c_invoiceLine inv left outer join (C_COMMISSIONDETAIL det left outer join c_orderLine ord on det.C_ORDERLINE_ID=ord.C_ORDERLINE_ID ) on det.C_INVOICELINE_ID=inv.C_INVOICELINE_ID "+
        " where det.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        //" and det.C_INVOICELINE_ID=inv.C_INVOICELINE_ID(+)"+
        //" and det.C_ORDERLINE_ID=ord.C_ORDERLINE_ID(+)"+
        " and det.C_COMMISSIONAMT_ID="+commissionAmtId;
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        CommissionBean bean=null;
        ArrayList<CommissionBean> list =  new ArrayList<CommissionBean>();
        try 
        {
            ResultSet rs= pstmt.executeQuery();
            
            while(rs.next())
            {
                bean=new CommissionBean();
                bean.setCommissionDetailId(rs.getInt(1));
                bean.setCommissionAmtId(rs.getInt(2));
                bean.setReference(rs.getString(3));
                bean.setOrderLineId(rs.getInt(4));
                bean.setInvoiceLineId(rs.getInt(5));
                bean.setInfo(rs.getString(6));
                bean.setCurrencyId(rs.getInt(7));
                bean.setActualAmt(rs.getBigDecimal(8));
                bean.setConvertedAmt(rs.getBigDecimal(9));
                bean.setActualQty(rs.getInt(10));
                bean.setInvoiceId(rs.getInt(11));
                bean.setOrderId(rs.getInt(12));
                
                list.add(bean);
            }
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try 
            {
                pstmt.close();
            } 
            catch (SQLException e) 
            {
                
                
            }
        }
        return list;   
    }
    
    public static String getCommissionAmtAsCSV(Properties ctx, ArrayList<CommissionBean> commissionList) throws OperationException
    {
    	ArrayList<Object[]> reportData = new ArrayList<Object[]>();
    	
    	// adding column header
    	Object[] headers = new Object[]{
    			"Name",
    			"Calculation Period And Curr.",
    			"Total Amount",
    			"Substract Amount",
    			"Commission Amount",
    			"Qty",
    			"Frequency Type",
    			"Doc Basis Type",
    			"Rate %"};
    	
    	reportData.add(headers);
    	
    	//adding data records
    	Object[] data = null;
    	
    	for(CommissionBean bean : commissionList)
    	{
    		data = new Object[]{
    		                  bean.getCommissionLineName(),
    		                  bean.getPeriodAndCurrencyDesc(),
    		                  bean.getConvertedAmt(),
    		                  bean.getSubtractAmt(),
    		                  bean.getCommissionAmt(),
    		                  bean.getActualQty(),
    		                  bean.getFrequencyType(),
    		                  bean.getDocBasisType(),
    		                  bean.getAmtMultiplier()
    		};
    		
    		reportData.add(data);    		
    	}
    	
    	//generate CSV
    	String reportName = CSVReportManager.generateCSVReport(ctx,reportData);
    	
    	return reportName;
    }
    
}
