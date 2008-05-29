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
package org.eevolution.model;

import java.util.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;


/**
 *  Order Line Model.
 * 	<code>
 * 			MPPProductBOMLine l = new MPPProductBOMLine(bom);
			l.setM_Product_ID(wbl.getM_Product_ID());
			l.setQty(wbl.getQuantity());;
			l.save();
 *	</code>
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrderLine.java,v 1.22 2004/03/22 07:15:03 vpj-cd Exp $
 */
public class MPPProductBOMLine extends X_PP_Product_BOMLine
{
    
    

        static private int AD_Client_ID = 0;
        static Hashtable tableproduct = new Hashtable();
        private static CLogger	s_log	= CLogger.getCLogger (MPPProductBOMLine.class);
        
	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param PP_Product_BOMLine  BOM line to load
	 *  @param Transaction Line
	 */
	public MPPProductBOMLine(Properties ctx, int PP_Product_BOMLine,String trxName)
	{
		super (ctx, PP_Product_BOMLine,trxName);
	}	//	MOrderLine
        

	/**
	 *  Parent Constructor.
	 		ol.setM_Product_ID(wbl.getM_Product_ID());
			ol.setQtyOrdered(wbl.getQuantity());
			ol.setPrice();
			ol.setPriceActual(wbl.getPrice());
			ol.setTax();
			ol.save();
	 *  @param  order parent order
	 */
	public MPPProductBOMLine(MPPProductBOM bom)
	{
		super (bom.getCtx(), 0,bom.get_TableName());
		if (bom.get_ID() == 0)
			throw new IllegalArgumentException("Header not saved");
		setPP_Product_BOM_ID (bom.getPP_Product_BOM_ID());	//	parent
	}	

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPProductBOMLine(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,trxName);
	}	//	 MPPProductBOMLine

	

	/**
	 * 	Set Defaults from BOM.
	 * 	Does not set Parent !!
	 * 	@param BOM BOM
	 */
	public void setMPPProductBOM (MPPProductBOM bom)
	{
		setClientOrg(bom);
	}	
	

	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPProductBOMLine[")
			.append(get_ID())
			.append ("]");
		return sb.toString ();
	}
       
        
	/**
	 * 	String Description
	 * 	@return info
	 */
	public String getDescriptionText()
	{
		return super.getDescription();
	}	//	getDescriptionText
        
	 /**
	  * get low leve of a Product
	  * @param ID Product
	  * @return int low level
	  */  
    public int getLowLevel(int M_Product_ID)
	{
                AD_Client_ID = Integer.parseInt(Env.getContext(Env.getCtx(), "#AD_Client_ID"));
                tableproduct.clear();
                DefaultMutableTreeNode ibom = iparent(M_Product_ID,0);
                return ibom.getDepth();
	}	
        
	 /**
	  * get a implotion the a prduct 
	  * @param ID Product
	  * @param ID BOM
	  * @return DefaultMutableTreeNode Tree with all parent product
	  */  
    private DefaultMutableTreeNode  parent(int M_Product_ID, int PP_Product_BOM_ID)
    { 
         
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(Integer.toString(M_Product_ID) +"|"+ Integer.toString(PP_Product_BOM_ID));
                    
        String sql =  new String("SELECT pbom.PP_Product_BOM_ID FROM  PP_Product_BOM pbom WHERE pbom.IsActive = 'Y'  AND  pbom.AD_Client_ID= ? AND pbom.M_Product_ID = ? ");
                    
                    
        PreparedStatement pstmt = null;
        try
        {
             pstmt = DB.prepareStatement (sql, get_TrxName());
             pstmt.setInt(1,  Env.getAD_Client_ID(Env.getCtx()));
             pstmt.setInt(2,  M_Product_ID);
             ResultSet rs = pstmt.executeQuery ();                        
             while (rs.next())
             {   
                   DefaultMutableTreeNode bom = component(rs.getInt(1), M_Product_ID , parent);
                   if (bom != null)
                   {
                      parent.add(bom);
                   }   

            }                                                
                        
            rs.close();
            pstmt.close();
                        
            return parent;

         }
         catch (Exception e)
         {
                        s_log.log(Level.SEVERE ,"doIt - " + sql  + e);
                      
         }
     return parent;
    }
           
	 /**
	  * get a explotion the a product 
	  * @param ID BOM
	  * @param ID Product
	  * @param DefaultMutableTreeNode Tree BOM
	  * @return DefaultMutableTreeNode Tree with all components product
	  */  
    private DefaultMutableTreeNode  component(int M_Product_BOM_ID, int M_Product_ID , DefaultMutableTreeNode bom)
    {                               
                    
                    String sql =  new String("SELECT pboml.M_Product_ID , pbom.Value , pboml.PP_Product_BOMLine_ID , pbom.PP_Product_BOM_ID FROM  PP_Product_BOM pbom INNER JOIN PP_Product_BOMLine pboml ON (pbom.PP_Product_BOM_ID = pboml.PP_Product_BOM_ID) WHERE pbom.IsActive= 'Y' AND pboml.IsActive= 'Y' AND pbom.AD_Client_ID= ? AND pbom.PP_Product_BOM_ID = ? ");
                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql,get_TrxName());
                    	pstmt.setInt(1,  Env.getAD_Client_ID(Env.getCtx()));
                        pstmt.setInt(2,  M_Product_BOM_ID);                        
                        ResultSet rs = pstmt.executeQuery ();                        
                        while (rs.next())
                        {
                            
                            if (M_Product_ID != rs.getInt(1))    
                            {                                                       
                                bom.add(parent(rs.getInt(1), rs.getInt(4)));
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Componet will be deactivated for BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) + ")", "Error Cycle BOM" , JOptionPane.ERROR_MESSAGE);
                                MPPProductBOMLine PP_Product_BOMLine = new MPPProductBOMLine(Env.getCtx(), rs.getInt(3),get_TrxName());
                                PP_Product_BOMLine.setIsActive(false);
                                PP_Product_BOMLine.save();
                            }    

                        }
                        if (rs.getRow() == 0)
                        {
                            DefaultMutableTreeNode parent = new DefaultMutableTreeNode(Integer.toString(M_Product_ID) + "|0");
                            bom.add(parent);
                            return bom;
                        }
                        
                        rs.close();
                        pstmt.close();           

                    }
                    catch (Exception e)
                    {
                    	 s_log.log(Level.SEVERE ,"doIt - " + sql  + e);
                    }
                    
          return null;
     }
           
           
           
	 /**
	  * get a implotion the a product 
	  * @param ID Product
	  * @param ID BOM
	  * @return DefaultMutableTreeNode Tree with all parent product
	  */  
     private DefaultMutableTreeNode  iparent(int M_Product_ID , int PP_Product_BOM_ID)
     { 
         
                    DefaultMutableTreeNode parent = new DefaultMutableTreeNode(Integer.toString(M_Product_ID) +"|"+ Integer.toString(PP_Product_BOM_ID));
                    
                    String sql =  new String("SELECT pboml.PP_Product_BOMLine_ID FROM  PP_Product_BOMLine pboml WHERE pboml.IsActive= 'Y' AND pboml.AD_Client_ID = ? AND pboml.M_Product_ID = ? ");
                    
                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql, get_TrxName());
                        pstmt.setInt(1, AD_Client_ID);
                        pstmt.setInt(2, M_Product_ID);
                        ResultSet rs = pstmt.executeQuery ();                        
                        
                        while (rs.next())
                        {
                            
                                DefaultMutableTreeNode bom = icomponent(rs.getInt(1), M_Product_ID , parent);
                                if (bom != null)
                                {
                                    parent.add(bom);
                                }                               
                                                            

                        }
                        
                        
                        rs.close();
                        pstmt.close();
                        
                        return parent;

                    }
                    catch (Exception e)
                    {
                    	 s_log.log(Level.SEVERE ,"doIt - " + sql  + e);
                    }
                     return parent;
   }    
           
	 /**
	  * get a implotion the a product 
	  * @param ID Product
	  * @param ID BOM
	  * @return DefaultMutableTreeNode Tree with all parent product
	  */  
     private  DefaultMutableTreeNode  icomponent(int PP_Product_BOMLine_ID, int M_Product_ID , DefaultMutableTreeNode bom)
     {                              
                    
                    String sql =  new String("SELECT pbom.M_Product_ID , pbom.Value , pbom.PP_Product_BOM_ID FROM  PP_Product_BOMLine pboml INNER JOIN PP_Product_BOM pbom ON (pbom.PP_Product_BOM_ID = pboml.PP_Product_BOM_ID) WHERE pbom.IsActive= 'Y' AND pboml.IsActive= 'Y' AND pboml.AD_Client_ID =? AND pboml.PP_Product_BOMLine_ID = ? ");      
                    PreparedStatement pstmt = null;
                    try
                    {
                    	pstmt = DB.prepareStatement (sql,get_TrxName());
                        pstmt.setInt(1, AD_Client_ID);
                        pstmt.setInt(2,  PP_Product_BOMLine_ID);
                        ResultSet rs = pstmt.executeQuery ();                        
                        while (rs.next())
                        {
                            if (M_Product_ID != rs.getInt(1))    
                            { 
                                if(!tableproduct(rs.getInt(1),rs.getInt(3)))
                                bom.add(iparent(rs.getInt(1),rs.getInt(3)));
                                else
                                	 s_log.log(Level.SEVERE ,"Cycle BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) + ")");
                                //JOptionPane.showMessageDialog(null,"Cycle BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) + ")", "Error Cycle BOM" , JOptionPane.ERROR_MESSAGE);
                                    
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Componet will be deactivated for BOM & Formula:" + rs.getString(2) + "(" + rs.getString(3) + ")", "Error Cycle BOM" , JOptionPane.ERROR_MESSAGE);
                                MPPProductBOMLine PP_Product_BOMLine = new MPPProductBOMLine(Env.getCtx(), rs.getInt(3),get_TrxName());
                                PP_Product_BOMLine.setIsActive(false);
                                PP_Product_BOMLine.save();
                            }    

                        }
                        rs.close();
                        pstmt.close();

                    }
                    catch (Exception e)
                    {
                    	 s_log.log(Level.SEVERE ,"doIt - " + sql  + e);
                    }
                    
         return null;
   }
           
     /**
	  * find a product in cache
	  * @param ID Product
	  * @param ID BOM
	  * @return true if product is find
	  */          
   private static boolean tableproduct(int M_Product_ID, int PP_Product_BOM_ID)
   {
               Integer p = new Integer(M_Product_ID);
               Integer bom = new Integer(PP_Product_BOM_ID);
               
               if (!tableproduct.containsKey(p))
               {    
               tableproduct.put(p , bom);
               return false;
               }
               else return true;
                 
  }
   
   /**************************************************************************
    * 	Before Save
    *	@param newRecord new
    *	@return save
    */
   protected boolean afterSave(boolean newRecord, boolean success) 
   {
	    MProduct product = new MProduct(getCtx(), getM_Product_ID() , get_TrxName());
	    product.set_CustomColumn("LowLevel", getLowLevel(getM_Product_ID()));
	    product.save();
		return success;
   }  
}
