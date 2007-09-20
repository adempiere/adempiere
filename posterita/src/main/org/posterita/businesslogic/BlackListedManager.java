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
	@author Martine
 */

 
package org.posterita.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.posterita.beans.BlackListedBean;
import org.posterita.core.CheckDuplicateEntities;
import org.posterita.exceptions.BPartnerNotFoundException;
import org.posterita.exceptions.BankAlreadyExistException;
import org.posterita.exceptions.OperationException;
import org.posterita.exceptions.ProductNotFoundException;
import org.posterita.model.MBlackListCheque;
import org.posterita.model.TMKMBlackListCheque;


public class BlackListedManager
{
	public static MBlackListCheque createBlackListed(Properties ctx, BlackListedBean bean, String trxName) throws OperationException, BankAlreadyExistException
	{
		if(CheckDuplicateEntities.checkDuplicateChequeNo(ctx,bean.getBlackListedChequeNo(),MBlackListCheque.Table_Name))
			throw new BankAlreadyExistException("Cheque already exists");	
		
		MBlackListCheque blacklisted= new MBlackListCheque(ctx, -1, trxName);
		blacklisted.setBankName(bean.getBlackListedBankName());
		blacklisted.setChequeNo(bean.getBlackListedChequeNo());
				
		TMKMBlackListCheque tmkBlackListCheque = new TMKMBlackListCheque(blacklisted);
		tmkBlackListCheque.save();
		return blacklisted;
	}
	
	public static BlackListedBean viewBlackListed(Properties ctx, int blackListedId) throws ProductNotFoundException
	{
		MBlackListCheque blackListed = loadBlackListedCheque(ctx, blackListedId, null);
		BlackListedBean bean = new BlackListedBean();
		
		bean.setBlackListedId(blackListed.get_ID()); 
		bean.setBlackListedBankName(blackListed.getBankName());
		bean.setBlackListedChequeNo(blackListed.getChequeNo());
		
		return bean;
	}
	
	public static MBlackListCheque loadBlackListedCheque(Properties ctx, int blackListedId, String trxName) throws ProductNotFoundException
	{
		MBlackListCheque blacklisted = new MBlackListCheque(ctx, blackListedId, trxName);
		if (blacklisted.get_ID() ==0)
			throw new ProductNotFoundException("Could not load BlackListed Cheque with ID: "+blackListedId);
		return blacklisted;
	}
	
	public static BlackListedBean editBlackListed(Properties ctx,BlackListedBean bean,String trxName) throws OperationException
	{
		MBlackListCheque blackListed = new MBlackListCheque(ctx,bean.getBlackListedId(), null);
		blackListed.setBankName(bean.getBlackListedBankName());
		blackListed.setChequeNo(bean.getBlackListedChequeNo());
		blackListed.setU_BlackListCheque_ID(bean.getBlackListedId());
		
		TMKMBlackListCheque tmkBlackListCheque = new TMKMBlackListCheque(blackListed);
		tmkBlackListCheque.save();
		
		return bean;
	}
	
	public static ArrayList<BlackListedBean> getAllBlackListed(Properties ctx) throws OperationException
	{
		ArrayList<BlackListedBean> list= new ArrayList<BlackListedBean>();
		String sql=getBlackListedsql(ctx);
		
		PreparedStatement pstmt = DB.prepareStatement(sql, null);
		ResultSet rs = null;
		BlackListedBean bean= null;
		try
		{
			rs=pstmt.executeQuery();
		
			while(rs.next())
			{
				bean=new BlackListedBean();
				bean.setBlackListedBankName(rs.getString(1));
				bean.setBlackListedChequeNo(rs.getString(2));
				bean.setBlackListedId(rs.getInt(3));
							
				list.add(bean);
			}
		rs.close();
		}
		catch(SQLException e)
		{
			throw new OperationException(e);
		}
		finally
		{
			try
			{
				pstmt.close();
			}
			catch(Exception e)
			{
			}
			pstmt=null;
		}
		return list;
	}
	 private static String getBlackListedsql(Properties ctx)
	 {
		 int ad_client_id = Env.getAD_Client_ID(ctx);
		
		 StringBuffer strBufSQL = new StringBuffer();
		 
		 strBufSQL.append("select bl.bankName, bl.chequeNo, bl.U_BLACKLISTCHEQUE_ID");
		 strBufSQL.append(" from U_BLACKLISTCHEQUE bl");
		 strBufSQL.append(" where ad_client_id =  " + ad_client_id );
		 strBufSQL.append(" and ad_ORG_ID in (" + Env.getContext(ctx,"#User_Org")+")");
		 
		 
		 return strBufSQL.toString();
	 }
	 
	public static ArrayList searchChequeNo(Properties ctx, String chequeNo) throws OperationException 
	{
		ArrayList<BlackListedBean> list = new ArrayList<BlackListedBean>();
    	
    	int ad_client_id = Env.getAD_Client_ID(ctx);
    	
    	String sql = " select" +
    			" bl.bankName," +	//1.BankName
    			" bl.chequeNo," +//2.ChequeNo
    			" bl.U_BLACKLISTCHEQUE_ID" +//3.ID
    			" from U_BLACKLISTCHEQUE bl" +
    			" where ad_client_id =  " + ad_client_id +
    			"and bl.isActive = 'Y'"+
    			" and ad_ORG_ID in (" + Env.getContext(ctx,"#User_Org")+") ";
    	
    	if(chequeNo != null && chequeNo.length() != 0)
    			sql += " and  ( lower(bl.chequeNo) like lower('%" + chequeNo + "%')) ";
    	
    	sql += " order by  bl.chequeNo" ;
    			
    	
    	PreparedStatement pstmt = DB.prepareStatement(sql,null);    	
    	ResultSet rs = null;
    	
    	BlackListedBean bean = null;
    	
    	try
    	{
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bean = new BlackListedBean();
				
				//set bean				
				bean.setBlackListedBankName(rs.getString(1));
				bean.setBlackListedChequeNo(rs.getString(2));
				bean.setBlackListedId(rs.getInt(3));
															
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
	   		 catch(Exception ex){}
	   		 
	   		 pstmt = null;
   	 	}
    	   	
    	return list;
        
	}
	
	public static BlackListedBean getBlackListedDetails(Properties ctx, int blackListedId) throws OperationException,BPartnerNotFoundException
    {
//    	if (blackListedId == 0)
//    		throw new OperationException("BlackListed details not found. Please contact your administrator.");
    	
    	
    	int id[] = MBlackListCheque.getAllIDs(MBlackListCheque.Table_Name,"U_BLACKLISTCHEQUE_ID="+blackListedId,null);
    	
    	if((id==null)||(id.length==0))
    	{
    		throw new BPartnerNotFoundException("Could not find Cheque with id :"+blackListedId);
    	}
    	
	    MBlackListCheque blacklisted = new MBlackListCheque(ctx, blackListedId, null);
    	BlackListedBean bean = new BlackListedBean();
	    bean.setBlackListedBankName(blacklisted.getBankName());
	    bean.setBlackListedChequeNo(blacklisted.getChequeNo());
	    bean.setBlackListedId(blackListedId);
    	
	    
//        MUser user=null;
//      int userIds [] =MUser.getAllIDs(MUser.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_BPARTNER_ID="+partner.getID(),null);
//      if(userIds.length==0)
//      {
//          user = new MUser(ctx,0,null);
//          user.setName(partner.getName());
//          
//      }
//      else
//      {
//          user = new MUser(ctx,userIds[0],null);
//      }
    	return bean;
    }
	
	public static void deactivateBListedCheque(Properties ctx, int blackListedId, String trxName) throws OperationException 
    {
		
    	MBlackListCheque blackListed = new MBlackListCheque(ctx, blackListedId, trxName);
    	
        if(blackListed == null)throw new OperationException("Business Partner does not exist!");
        
        blackListed.setIsActive(false);
         
        blackListed.save();
            
    }
	
	
	

  		
}
