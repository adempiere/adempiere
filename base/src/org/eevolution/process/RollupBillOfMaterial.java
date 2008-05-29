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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.process;

import java.util.logging.*;
import java.math.*;
import java.sql.*;
import java.util.*;


import org.compiere.model.*;
import org.compiere.util.*;
import org.compiere.process.*;
import org.eevolution.model.MPPMRP;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.QueryDB;

/**
 *	Rollup Bill of Material 
 *	
 *  @author Victor Perez, e-Evolution, S.C.
 *  @version $Id: RollupBillOfMaterial.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 */
public class RollupBillOfMaterial extends SvrProcess
{
	/**					*/
       private int		 		   p_AD_Org_ID = 0;
       //private int               p_M_Warehouse_ID = 0;
       private int               p_M_Product_Category_ID=0;
       private int               p_M_Product_ID = 0;
       private int               p_M_CostType_ID = 0;
       //private int               p_S_Resource_ID = 0;
       private int               p_C_AcctSchema_ID = 0;
             
      
       private int Elementtypeint=0;
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		       
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();

			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
                        {    
				p_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        /*else if (name.equals("M_Warehouse_ID"))
                        {    
				p_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }*/
                         else if (name.equals("M_Product_Category_ID"))
                        {    
				p_M_Product_Category_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        else if (name.equals("M_Product_ID"))
                        {    
				p_M_Product_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }
                        /*else if (name.equals("S_Resource_ID"))
                        {    
				p_S_Resource_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }*/
                        else if (name.equals("M_CostType_ID"))
                        {    
				
                                p_M_CostType_ID = ((BigDecimal)para[i].getParameter()).intValue();
                        }
                        else if (name.equals("C_AcctSchema_ID"))
                        {    
				p_C_AcctSchema_ID = ((BigDecimal)para[i].getParameter()).intValue();
                                
                        }                        
                        else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

        
     protected String doIt() throws Exception                
     {
            
               int lowlevel = MPPMRP.getMaxLowLevel();                                                        
               int Level = lowlevel;
               //System.out.println("Low Level >>>>>>>>>>>>>>>>"+lowlevel);               
               
               // Calculate Rollup for all levels
               for (int index = lowlevel ; index >= 0 ; index--)
               {                                
               
                StringBuffer sql = new StringBuffer ("SELECT p.M_Product_ID FROM M_Product p WHERE p.ProductType = '" + MProduct.PRODUCTTYPE_Item + "' AND AD_Client_ID = ? AND p.LowLevel = " + index);
                
                if (p_M_Product_ID != 0)
                {    
                sql.append(" AND p.M_Product_ID = " + p_M_Product_ID);
                }                
                if (p_M_Product_Category_ID != 0)
                {    
                sql.append(" AND p.M_Product_Category_ID = " + p_M_Product_Category_ID);
                }  
                //System.out.print("sql :" + sql.toString());
                
		PreparedStatement pstmt = null;
		try
		{
						pstmt = DB.prepareStatement (sql.toString(),get_TrxName());  
						pstmt.setInt(1, getAD_Client_ID()); 
                        ResultSet rs = pstmt.executeQuery ();
                        while (rs.next())
                        {
                            int M_Product_ID = rs.getInt("M_Product_ID");  
                            
                            
                            StringBuffer sqlw = new StringBuffer ("SELECT p.M_Warehouse_ID FROM M_Warehouse p WHERE IsActive = 'Y' AND AD_Client_ID = " + getAD_Client_ID());
                
                            /*if (p_M_Warehouse_ID != 0)
                            {    
                            sqlw.append(" AND p.M_Warehouse_ID = " + p_M_Warehouse_ID);
                            } */               

                            //System.out.print("sql :" + sqlw.toString());

                            PreparedStatement pstmtw = null;
                            pstmtw = DB.prepareStatement (sqlw.toString());
                            ResultSet rsw = pstmtw.executeQuery ();
                            int M_Warehouse_ID=0;
                            while (rsw.next())
                            {
                                M_Warehouse_ID = rsw.getInt(1);  
                                //System.out.println("WAREHOUSE ************" +M_Warehouse_ID);                                                    
                                org.eevolution.model.MCost[]  pc = org.eevolution.model.MCost.getElements( M_Product_ID , p_C_AcctSchema_ID , p_M_CostType_ID);            
                                //System.out.println("M_Product_ID" + M_Product_ID + "p_C_AcctSchema_ID" + p_C_AcctSchema_ID + "p_PP_Cost_Group_ID" + p_PP_Cost_Group_ID + "p_M_Warehouse_ID" +  M_Warehouse_ID + "p_S_Resource_ID" + p_S_Resource_ID);
                                MProduct product = new MProduct(getCtx(), M_Product_ID ,null);
                                System.out.println("--------------------------Product" + product.getValue() + "-" + product.getName());
                           
                           if (pc != null)
                           {
                                for (int e = 0 ; e < pc.length ; e ++ )
                                {
                                                                        
                                    MCostElement element = new MCostElement(getCtx(), pc[e].getM_CostElement_ID(),null);
                                    // check if element cost is of type Labor
                                    System.out.println("Exist Elemet " + e);
                                    //System.out.println("M_Product_ID" + M_Product_ID + "p_C_AcctSchema_ID" + p_C_AcctSchema_ID + "p_PP_Cost_Group_ID" + p_PP_Cost_Group_ID + "p_M_Warehouse_ID" +  M_Warehouse_ID + "p_S_Resource_ID" + p_S_Resource_ID);
                                    //System.out.println("Material" +element.PP_ELEMENTTYPE_Material);
                                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_Material))
                                    {
                                 
                                    BigDecimal Material = getCostLL(element.COSTELEMENTTYPE_Material , p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);     
                                    System.out.println("Material" + Material);
                                    if (pc[e].getCurrentCostPrice().compareTo(Env.ZERO)==0)
                                    {
                                    //pc[e].setCostLLAmt(Material);
                                    pc[e].save(get_TrxName());
                                    }
                                    continue;
                                    }
                                    
                                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_Resource))
                                    {
                                    BigDecimal Labor = getCostLL(element.COSTELEMENTTYPE_Resource, p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);     
                                    System.out.println("Labor" + Labor);
                                    //pc[e].setCostLLAmt(Labor);
                                    pc[e].save(get_TrxName());
                                    continue;
                                    }
                                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_BurdenMOverhead))
                                    {
                                    BigDecimal Burder = getCostLL(element.COSTELEMENTTYPE_BurdenMOverhead , p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);     
                                    System.out.println("Burder" + Burder);
                                    //pc[e].setCostLLAmt(Burder);
                                    pc[e].save(get_TrxName());
                                    continue;
                                    }
                                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_Overhead))
                                    {
                                    BigDecimal Overhead = getCostLL(element.COSTELEMENTTYPE_Overhead , p_AD_Org_ID , M_Product_ID  , p_M_CostType_ID , p_C_AcctSchema_ID);     
                                    System.out.println("Overhead" + Overhead);
                                    //pc[e].setCostLLAmt(Overhead);
                                    pc[e].save(get_TrxName());
                                    continue;
                                    }
                                    if (element.getCostElementType().equals(element.COSTELEMENTTYPE_OutsideProcessing))
                                    {
                                    BigDecimal Subcontract = getCostLL(element.COSTELEMENTTYPE_OutsideProcessing , p_AD_Org_ID , M_Product_ID , p_M_CostType_ID , p_C_AcctSchema_ID);     
                                    System.out.println("Subcontract" + Subcontract);
                                    //pc[e].setCostLLAmt(Subcontract);
                                    pc[e].save(get_TrxName());
                                    continue;
                                    }
                                    /*if (element.getCostElementType().equals(element.PP_ELEMENTTYPE_Distribution))
                                    {
                                        
                                    BigDecimal Distribution = getCostLL(element.PP_ELEMENTTYPE_Distribution , p_AD_Org_ID , M_Product_ID , M_Warehouse_ID , p_S_Resource_ID , p_PP_Cost_Group_ID , p_C_AcctSchema_ID);     
                                    //pc[e].setCostLLAmt(Distribution);
                                    System.out.println("Distribution" + Distribution);
                                    pc[e].save(get_TrxName());
                                    continue;                                    
                                    }*/
                                }
                           } // end if   
                        } // end while warehouse
                        rsw.close();
                        pstmtw.close();
                        } // end while product
                        rs.close();
                        pstmt.close();

		}
		catch (Exception e)
		{			
                        //log.log(Level.SEVERE,"doIt - " + sql, e);
                        return null;
		}             
               
               }         
                
            return "ok";
     }
     

     
     private BigDecimal getCostLL(String CostElementType , int AD_Org_ID , int M_Product_ID , int M_CostType_ID , int  C_AcctSchema_ID)
     {
         System.out.println("getcostLL ***** " +CostElementType );
         BigDecimal cost = Env.ZERO;
         BigDecimal total = Env.ZERO;
         BigDecimal totalcost = Env.ZERO;
         
         MPPProductBOM bom = new MPPProductBOM(getCtx(), getPP_Product_BOM_ID(AD_Org_ID , M_Product_ID , CostElementType),null);                          
         MPPProductBOMLine[] bomlines = bom.getLines();

         System.out.println("no. de materias primas **********+ " +bomlines.length);
         for (int i = 0 ; i < bomlines.length ; i ++ )
         {
             MPPProductBOMLine bomline = bomlines[i];
             
             //int m_S_Resource_ID = ;
             // get the rate for this resource     
             org.eevolution.model.MCost[]  pc = org.eevolution.model.MCost.getElements( M_Product_ID , p_C_AcctSchema_ID , p_M_CostType_ID);         
             System.out.println("Producto de la linea *************   " +bomline.getM_Product_ID());
             
             for (int e = 0 ; e < pc.length ; e ++ )
             {                 
                 MCostElement element = new MCostElement(getCtx(), pc[e].getM_CostElement_ID(),null);
                 // check if element cost is of type Labor
                 if (element.getCostElementType().equals(CostElementType))
                 {
                     //cost = cost.add(pc[e].getCostTLAmt()).add(pc[e].getCostLLAmt());
                     
                     BigDecimal QtyPercentage = bomline.getQtyBatch().divide(new BigDecimal(100),8,BigDecimal.ROUND_UP);
                     BigDecimal QtyBOM = bomline.getQtyBOM(); 
                     BigDecimal Scrap = bomline.getScrap();
                      	Scrap = Scrap.divide(new BigDecimal(100),4,BigDecimal.ROUND_UP); //convert to decimal
                     BigDecimal QtyTotal = Env.ZERO;
                     
                     //System.out.println("elementos pc[e]  " +pc[e].getM_Product_ID() +" cost ll "+pc[e].getCostLLAmt() +" cost tl " +pc[e].getCostTLAmt());
                     System.out.println("cost:" +  cost + "QtyPercentage:" + QtyPercentage + "QtyBOM" + QtyBOM) ;
                     
                     if (bomline.isQtyPercentage()) 
                     {   
                         QtyTotal =  QtyPercentage.divide( Env.ONE.subtract(Scrap) , 4 ,BigDecimal.ROUND_HALF_UP );
                         
                     }
                    else 
                     {
                    	 QtyTotal =  QtyBOM.divide( Env.ONE.subtract(Scrap) , 4 ,BigDecimal.ROUND_HALF_UP );
                         
                     }
                     
                     totalcost = totalcost.add(cost.multiply(QtyTotal));

                     System.out.println("Cost Total" + totalcost);
                 }
                 cost =Env.ZERO;
             }             
         }    
         
         
         // Calculate Yield Cost
         MPPProductPlanning pps = MPPProductPlanning.getDemandSupplyResource(getCtx(), AD_Org_ID , M_Product_ID, get_TrxName());
         if (pps != null)
         {            
         int Yield = pps.getYield();
         	if(Yield != 0)
         	{
				BigDecimal  DecimalYield = new BigDecimal(Yield/100);
				if (!DecimalYield.equals(Env.ZERO))
					totalcost = totalcost.divide(DecimalYield, 4 ,BigDecimal.ROUND_HALF_UP);
         	}
         }         
         
         return totalcost;
         
     }
     
     
     
     private int getM_Product_ID(int S_Resource_ID)
     {
                    QueryDB query = new QueryDB("org.compiere.model.X_M_Product");
                    String filter = "S_Resource_ID = " + S_Resource_ID;
                    java.util.List results = query.execute(filter);
                    Iterator select = results.iterator();
                    while (select.hasNext())
                    {
                     X_M_Product M_Product =  (X_M_Product) select.next();                                          
                     return M_Product.getM_Product_ID();
                    }
         
                    return 0;
     }
     
     private int getPP_Product_BOM_ID(int AD_Org_ID , int M_Product_ID , String CostElementType)
     {
        
         boolean pp = false;
         
          String sqlec = "SELECT M_CostElement_ID FROM M_CostElement WHERE CostElementType=? AND AD_CLient_ID = " + getAD_Client_ID();
		PreparedStatement pstmtec = null;
		try
		{
			pstmtec = DB.prepareStatement(sqlec, get_TrxName());
			pstmtec.setString(1, CostElementType);

			ResultSet rsec = pstmtec.executeQuery();
			if (rsec.next())
                        {
                            Elementtypeint=rsec.getInt(1);
                        }
				
			rsec.close();
			pstmtec.close();
			pstmtec = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,"getLines", ex);
		}
         
         
                String sql = "SELECT * FROM M_Cost WHERE M_Product_ID=? AND  C_Acctschema_ID = ? AND M_CostType_ID = ? AND M_CostElement_ID = ?  AND AD_Client_ID = ?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql,get_TrxName());
			pstmt.setInt(1, M_Product_ID);
                        pstmt.setInt(2, p_C_AcctSchema_ID);
                        pstmt.setInt(3, p_M_CostType_ID);
                        pstmt.setInt(4, Elementtypeint);
                        //pstmt.setInt(5, M_Warehouse_ID);
                        //pstmt.setInt(6, S_Resource_ID);
                        pstmt.setInt(5, getAD_Client_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
                        {
                            pp= true;
                        }
				
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE,"getLines", ex);
		}
         
         
         
         //System.out.println("pp ********** " +pp);
          
         MProduct M_Product = new MProduct(getCtx(), M_Product_ID,null);
         if (pp)
         {
             
             try
             {
                // System.out.println("producto value ********** * " +M_Product.getValue());
                StringBuffer sqlprod = new StringBuffer("SELECT PP_Product_BOM_ID FROM PP_Product_BOM WHERE Value Like '" + M_Product.getValue() + "%' AND AD_Client_ID = "+getAD_Client_ID() +" ORDER BY Value");
                PreparedStatement pstmtprod = DB.prepareStatement(sqlprod.toString(), get_TrxName());
                //System.out.println("query ********** * " +sqlprod.toString());
                ResultSet rsprod = pstmtprod.executeQuery();
                if (rsprod.next())
                {
                  //System.out.println("producto del bom ********** * " +rsprod.getInt(1));
                  return rsprod.getInt(1);  
                }
                rsprod.close();
                pstmtprod.close();
             }
             catch (SQLException s)
             {
             	//log.log(Level.SEVERE, s);
             }
         }
         
         return 0;        
         }                  
                                                                
}	//	OrderOpen
