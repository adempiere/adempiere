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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpconsultoresyasociados.com               *
 *****************************************************************************/
package org.spinsuite.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *
 */
public class MSPSSyncMenu extends X_SPS_SyncMenu implements I_SPS_SyncMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6909895480750744213L;
	
	/**
	 * *** Constructor ***
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 22/05/2013, 01:25:27
	 * @param ctx
	 * @param SPS_SyncMenu_ID
	 * @param trxName
	 */
	public MSPSSyncMenu(Properties ctx, int SPS_SyncMenu_ID, String trxName) {
		super(ctx, SPS_SyncMenu_ID, trxName);
	}

	/**
	 * *** Constructor ***
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 22/05/2013, 01:25:27
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MSPSSyncMenu(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 * @author <a href="mailto:carlosaparadam@gmail.com">Carlos Parada</a> 11/02/2014, 23:42:07
	 * @param p_ParentNode
	 * @param p_CurrentItems
	 * @return
	 * @return List<MSFASyncMenu>
	 */
	public static List<MSPSSyncMenu> getNodes(int p_ParentNode,String p_WebServiceDefinitionValue,String p_WebServiceMethodValue,String p_WebServiceTypeValue) {
		
		StringBuffer sql = new StringBuffer();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MSPSSyncMenu> items = new ArrayList<MSPSSyncMenu>();
				
		sql.append( "SELECT treend.Parent_ID, treend.Node_ID, treend.SeqNo, CASE WHEN Qty IS NULL THEN 'N' ELSE 'Y' END As HasNodes, wst.Value As ValueType, wsm.Value As ValueMethod " + 
					"FROM " +
					"AD_Tree tree " + 
					"INNER JOIN AD_Table tab ON tree.AD_Table_ID = tab.AD_Table_ID " +
					"INNER JOIN AD_TreeNode treend On treend.AD_Tree_ID = tree.AD_Tree_ID "+
					"LEFT JOIN (SELECT Count(1) Qty,Parent_ID,AD_Tree_ID FROM AD_TreeNode GROUP BY Parent_ID,AD_Tree_ID) hasnodes ON hasnodes.Parent_ID=treend.Node_ID AND hasnodes.AD_Tree_ID=treend.AD_Tree_ID " +
					"INNER JOIN SPS_SyncMenu sm ON treend.Node_ID = sm.SPS_SyncMenu_ID " +
					"INNER JOIN WS_WebService ws ON sm.WS_WebService_ID = ws.WS_WebService_ID " +
					"LEFT JOIN WS_WebServiceType wst ON sm.WS_WebServiceType_ID = wst.WS_WebServiceType_ID " +
					"LEFT JOIN WS_WebServiceMethod wsm ON sm.WS_WebServiceMethod_ID = wsm.WS_WebServiceMethod_ID OR wst.WS_WebServiceMethod_ID = wsm.WS_WebServiceMethod_ID " +
					"WHERE tab.TableName = ? AND treend.Parent_ID = ? AND ws.Value = ? AND sm.IsActive ='Y'" +
					"ORDER By treend.SeqNo ");
		
		try{
			ps = DB.prepareStatement(sql.toString(),null);
			ps.setString(1, MSPSSyncMenu.Table_Name);
			ps.setInt(2, p_ParentNode);
			ps.setString(3, p_WebServiceDefinitionValue);
			
			rs = ps.executeQuery();
			
			while (rs.next()){
				MSPSSyncMenu item = new MSPSSyncMenu(Env.getCtx(), rs.getInt("Node_ID"), null);
				
				if (rs.getString("HasNodes").equals("Y")){
						items.addAll(getNodes(rs.getInt("Node_ID"),p_WebServiceDefinitionValue,p_WebServiceMethodValue,p_WebServiceTypeValue));					
				}
				else{
					if ((item.getWS_WebServiceType_ID()!=0 
							&& p_WebServiceTypeValue!=null
								&& p_WebServiceTypeValue.equals(rs.getString("ValueType")))
						||
						(p_WebServiceTypeValue==null 
							&& p_WebServiceMethodValue!=null
								&&  p_WebServiceMethodValue.equals(rs.getString("ValueMethod"))))						
							items.add(item);

					
				}
			}
		}
		catch(SQLException ex){
			new AdempiereException(ex.getMessage());
		}
		finally{
			DB.close(rs, ps);
		    rs = null; ps = null;	
		}
		
		return items;
	}//getNodes

	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//	Changed from Not to Active
		/*if (!newRecord && is_ValueChanged("IsActive") && isActive())
		{
			log.severe ("Cannot Re-Activate deactivated Allocations");
			return false;
		}
		
		//		
		if(getArea() == null
				|| getArea().equals(Env.ZERO)) {
			throw new AdempiereException("@Area@ = @0@");
		} else if(getArea().compareTo(getFTA_FarmDivision().getArea()) > 0){
			throw new AdempiereException("@Area@ > @Area@ @of@ @FTA_FarmDivision_ID@");
		}*/
		return true;
	}	//	beforeSave
	
	
}