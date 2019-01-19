/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.engine.CostEngine;
import org.adempiere.engine.IDocumentLine;
import org.compiere.acct.DocLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MPPCostCollector;

/**
 * 	Cost Detail Model
 *	
 *  @author Jorg Janke
 *  @author Armen Rizal, Goodwill Consulting
 *  	<li>BF: 2431123 Return Trx changes weighted average cost
 *  	<li>BF: 1568752 Average invoice costing: landed costs incorrectly applied
 *  @author Armen Rizal & Bayu Cahya
 *  	<li>BF [ 2129781 ] Cost Detail not created properly for multi acc schema
 *  @author Teo Sarca
 *  	<li>BF [ 2847648 ] Manufacture & shipment cost errors
 *  		https://sourceforge.net/tracker/?func=detail&aid=2847648&group_id=176962&atid=934929
 * 	@author red1 FR: [ 2214883 ] Remove SQL code and Replace for Query
 *  @version $Id: MCostDetail.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 *  
 */
public class MCostDetail extends X_M_CostDetail
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7882724307127281675L;


	/**
	 * Get Cost by Model
	 * @param accountSchemaId
	 * @param costTypeId
	 * @param costElementId
	 * @param model
	 * @return
	 */
	public static BigDecimal getCostByModel(int accountSchemaId, int costTypeId, int costElementId, IDocumentLine model) {

		StringBuilder whereClause = new StringBuilder();
		whereClause.append(I_M_CostDetail.COLUMNNAME_C_AcctSchema_ID).append("=? AND ");
		whereClause.append(I_M_CostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
		whereClause.append(I_M_CostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
		whereClause.append( CostEngine.getIDColumnName(model)).append("=? ");

		return  new Query(model.getCtx(),
				I_M_CostDetail.Table_Name, whereClause.toString(),
				model.get_TrxName())
				.setClient_ID()
				.setParameters(accountSchemaId , costTypeId, costElementId, model.get_ID())
				.sum("(" + MCostDetail.COLUMNNAME_Amt + "+"
						+ MCostDetail.COLUMNNAME_AmtLL + ")");
	}

    /**
     * get Quantity On hand by Attribute Set Instance and Sequence
     * @param context
     * @param productId
     * @param costTypeId
     * @param costElementId
     * @param attributeSetInstanceId
     * @param seqNo
     * @param trxName
     * @return
     */
    public static BigDecimal getQtyOnHandByASIAndSeqNo (Properties context , int productId , int costTypeId , int costElementId , int attributeSetInstanceId, int seqNo , String trxName)
    {
        List<Object> parameters = new ArrayList<Object>();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_M_CostDetail.COLUMNNAME_M_Product_ID).append("=? AND ");
        parameters.add(productId);
        whereClause.append(I_M_CostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
        parameters.add(costTypeId);
        whereClause.append(I_M_CostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
        parameters.add(costElementId);
        whereClause.append(I_M_CostDetail.COLUMNNAME_M_AttributeSetInstance_ID).append("=? AND ");
        parameters.add(attributeSetInstanceId);
        whereClause.append(I_M_CostDetail.COLUMNNAME_SeqNo).append("<=?");
        parameters.add(seqNo);
        return new Query(context, Table_Name, whereClause.toString(), trxName)
                .setParameters(parameters)
                .sum(I_M_CostDetail.COLUMNNAME_Qty);
    }


    /**
     * Get Cost detail by transaction
     * @param transaction
     * @param accountSchemaId
     * @param costTypeId
     * @param costElementId
     * @return
     */
	public static List<MCostDetail> getByTransaction(MTransaction transaction, int accountSchemaId , int costTypeId , int  costElementId)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_Transaction_ID).append("=?");

		return new Query (transaction.getCtx(), I_M_CostDetail.Table_Name, whereClause.toString() , transaction.get_TrxName())
		.setParameters(accountSchemaId ,costTypeId , costElementId , transaction.getM_Transaction_ID())
		.setOrderBy(MCostDetail.COLUMNNAME_SeqNo)
		.list();
	}

	public static MCostDetail getCostDetail(MPPCostCollector cc, int M_CostElement_ID) {
		final String whereClause = MCostDetail.COLUMNNAME_PP_Cost_Collector_ID
				+ "=?" + " AND " + MCostDetail.COLUMNNAME_M_CostElement_ID
				+ "=?";
		MCostDetail cd = new Query(cc.getCtx(), MCostDetail.Table_Name,
				whereClause, cc.get_TrxName())
				.setClient_ID()
				.setParameters(
						new Object[] { cc.getPP_Cost_Collector_ID(),
								M_CostElement_ID }).firstOnly();
		return cd;
	}
	
	/**
	 * get true if cost is different of zero
	 * @param cost
	 * @return
	 */
	public static boolean existsCost(MCostDetail cost)
	{
		if (cost.getCostAmt().add(cost.getCostAdjustment()).add(cost.getCostAmtLL()).add(cost.getCostAdjustmentLL()).signum() == 0)
			return false;
		else
			return true;
	}
	
	/**
	 * get Total Cost as absolute value
	 * @param costDetail
	 * @param acctSchema
	 * @return
	 */
	public static BigDecimal getTotalCost(MCostDetail costDetail , MAcctSchema acctSchema)
	{
		return costDetail
				.getCostAmt()
				.add(costDetail.getCostAdjustment())
				.add(costDetail.getCostAmtLL())
				.add(costDetail.getCostAdjustmentLL())
				.setScale(acctSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
	}
	
	public static List<MCostDetail> getByCollectorCost(MPPCostCollector costCollector)
	{
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(MCostDetail.COLUMNNAME_PP_Cost_Collector_ID).append("=? ");	
		return new Query(costCollector.getCtx(), MCostDetail.Table_Name , whereClause.toString(), costCollector.get_TrxName())
		.setClient_ID()
		.setParameters(costCollector.getPP_Cost_Collector_ID())
		.list();
	}
	
	/**
	 * get the last entry for a Cost Detail based on the Material Transaction and Cost Dimension
	 * @param model
	 * @param transaction Transaction Material
	 * @param C_AcctSchema_ID
	 * @param M_CostType_ID
	 * @param M_CostElement_ID
	 * @param dateAcct
	 * @param costingLevel
	 * @return
	 */
	public static MCostDetail getLastTransaction (
			IDocumentLine model,
			MTransaction transaction,
			int C_AcctSchema_ID ,
			int M_CostType_ID,
			int M_CostElement_ID , 
			Timestamp dateAcct, String costingLevel)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		StringBuffer orderBy = new StringBuffer();
		if(model instanceof MLandedCostAllocation 
				|| model instanceof MMatchInv)
		{
			if (model.getReversalLine_ID()!= 0){
				if (model.getDateAcct().compareTo(transaction.getDocumentLine().getDateAcct())==0){
					whereClause.append(MCostDetail.COLUMNNAME_M_Transaction_ID + "=? AND ");
					params.add(transaction.getM_Transaction_ID());
				}
				else
					whereClause.append("DateAcct <= " +DB.TO_DATE(dateAcct) + " AND ");
			}
			else {
				Timestamp dateacct = transaction.getDocumentLine().getDateAcct();
				MDocType dt = new MDocType(transaction.getCtx(), transaction.getDocumentLine().getC_DocType_ID(), transaction.get_TrxName());
				if (MPeriod.isOpen(transaction.getCtx(), dateacct, dt.getDocBaseType(), transaction.getAD_Org_ID())) {
					whereClause.append(MCostDetail.COLUMNNAME_M_Transaction_ID + "=? AND ");
					params.add(transaction.getM_Transaction_ID());
				} else
					whereClause.append("DateAcct <= " + DB.TO_DATE(dateAcct) + " AND ");
				if (model instanceof MMatchInv) {
					whereClause.append(" (M_MatchInv_ID <>? OR M_MatchInv_ID is null) AND ");
					params.add(model.get_ID());
				}
			}
								
		}	
		else
			whereClause.append("DateAcct <= " +DB.TO_DATE(dateAcct) + " AND ");
		orderBy.append(MCostDetail.COLUMNNAME_SeqNo).append(" DESC");			
		
		whereClause.append(MCostDetail.COLUMNNAME_AD_Client_ID + "=? AND ");
		params.add(transaction.getAD_Client_ID());
		
		if(MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
		{	
			whereClause.append(MCostDetail.COLUMNNAME_AD_Org_ID+ "=? AND ");
			params.add(transaction.getAD_Org_ID());
		}
		
		if(MAcctSchema.COSTINGLEVEL_Warehouse.equals(costingLevel))
		{	
			whereClause.append(MCostDetail.COLUMNNAME_M_Warehouse_ID+ "=? AND ");
			params.add(transaction.getM_Warehouse_ID());
		}
		
		whereClause.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID + "=? AND ");
		params.add(C_AcctSchema_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_Product_ID + "=? AND ");
		params.add(transaction.getM_Product_ID());
		
		if(MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
		{	
			whereClause.append(MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID+ "=? AND ");
			params.add(transaction.getM_AttributeSetInstance_ID());
		}	
		
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID+"=? AND ");
		params.add(M_CostElement_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID + "=? AND ");
		params.add(M_CostType_ID);
		whereClause.append(MCostDetail.COLUMNNAME_Processing + " = ? ");
		params.add(false);

		
		
		/*List<MCostDetail> costs = new Query(transaction.getCtx(), Table_Name, whereClause.toString(), transaction.get_TrxName())
		.setParameters(params)	
		.setOrderBy(orderBy.toString())
		.list();

		System.out.println("---------------------- Transaccion -------------------------------------------------------");
		System.out.println(transaction.toString());
		System.out.println("------------------------------------------------------------------------------------------");
		for (MCostDetail cost : costs)
		{
			System.out.println(cost.toString());
		}
		System.out.println("---------------------- FIN BUSCANDO LA ULTIMA TRANSACCIONES ------------------------------");
		System.out.println("");*/
		
		return  new Query(transaction.getCtx(), Table_Name, whereClause.toString(), transaction.get_TrxName())
		.setParameters(params)	
		.setOrderBy(orderBy.toString())
		.first();
	}
	
	/**
	 * Get the last entry for a Cost Detail based on the Material Transaction and Cost Dimension
	 * @param mtrx Material Transaction ID
	 * @param C_AcctSchema_ID Account Schema ID
	 * @param M_CostType_ID Cos Type ID 
	 * @param M_CostElement_ID Cost Element ID
	 * @param costingLevel TODO
	 * @return MCostDetail
	 */
	public static MCostDetail getLastTransaction (MTransaction mtrx, int C_AcctSchema_ID, int M_CostType_ID,int M_CostElement_ID, String costingLevel)
	{	
		ArrayList<Object> params = new ArrayList();
		final StringBuffer whereClause = new StringBuffer(MCostDetail.COLUMNNAME_AD_Client_ID + "=? AND ");
		params.add(mtrx.getAD_Client_ID());
		if(MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
		{	
			whereClause.append(MCostDetail.COLUMNNAME_AD_Org_ID).append("=? AND ");	
			params.add(mtrx.getAD_Org_ID());
		}
		
		whereClause.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID).append("=? AND ");
		params.add(C_AcctSchema_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? AND ");
		params.add(mtrx.getM_Product_ID());
		if(MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
		{	
			whereClause.append(MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID).append("=? AND ");
			params.add(mtrx.getM_AttributeSetInstance_ID());
		}	
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
		params.add(M_CostElement_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
		params.add(M_CostType_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_Transaction_ID).append("<?  ");
		params.add(mtrx.getM_Transaction_ID());

			
		return  new Query(mtrx.getCtx(), Table_Name, whereClause.toString(), mtrx.get_TrxName())
		.setParameters(params)
		.setOrderBy(MCostDetail.COLUMNNAME_SeqNo+ " DESC")
		.first();
	}
	
	/**
	 * Detect if Cost Detail delayed entry
	 * @param cd
	 * @param C_AcctSchema_ID
	 * @param M_CostType_ID
	 * @param M_CostElement_ID
	 * @param costingLevel TODO
	 * @return
	 */
	public static boolean isEarlierTransaction(MCostDetail cd ,  int C_AcctSchema_ID, int M_CostType_ID,int M_CostElement_ID, String costingLevel)
	{
		MTransaction trx = new MTransaction(cd.getCtx(), cd.getM_Transaction_ID(), cd.get_TrxName());
		MCostDetail last_cd = getLastTransaction(trx,  C_AcctSchema_ID, M_CostType_ID, M_CostElement_ID, costingLevel);
		if(last_cd == null)
			return false;
		
		if(cd.getSeqNo() <= last_cd.getSeqNo()
				&& cd.getM_Transaction_ID() != last_cd.getM_Transaction_ID()) 
		{
			return true;
		}
		return false;
	}

	/**
	 * 	
	 * Get the Cost Detail Based on  Material Transaction 
	 * @param mtrx Material Transaction
	 * @param C_AcctSchema_ID Account Schema ID
	 * @param M_CostType_ID CostType ID
	 * @param M_CostElement_ID Cost Element ID
	 * @return MCostDetail cost detail
	 */
	public static MCostDetail getByTransaction(IDocumentLine model, MTransaction mtrx, int C_AcctSchema_ID, int M_CostType_ID,int M_CostElement_ID)
	{			
		ArrayList<Object> params = new ArrayList<Object>();
		final StringBuffer whereClause = new StringBuffer(MCostDetail.COLUMNNAME_AD_Client_ID + "=? AND ");
		params.add(mtrx.getAD_Client_ID());
		whereClause.append(MCostDetail.COLUMNNAME_AD_Org_ID).append("=? AND ");
		params.add(mtrx.getAD_Org_ID());
		whereClause.append(MCostDetail.COLUMNNAME_M_Warehouse_ID).append("=? AND ");
		params.add(mtrx.getM_Warehouse_ID());
		whereClause.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID).append( "=? AND ");
		params.add(C_AcctSchema_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_Product_ID).append( "=? AND ");
		params.add(mtrx.getM_Product_ID());
		if(mtrx.getM_AttributeSetInstance_ID() > 0)
		{	
			whereClause.append(MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID).append( "=?  AND ");
			params.add(mtrx.getM_AttributeSetInstance_ID());		
		}
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
		params.add(M_CostElement_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID ).append( "=? AND ");
		params.add(M_CostType_ID);
		whereClause.append(MCostDetail.COLUMNNAME_M_Transaction_ID ).append( "=? ");
		params.add(mtrx.getM_Transaction_ID());

		if(model instanceof MMatchPO)
		{
			MMatchPO matchInv = (MMatchPO) model;
			whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_C_OrderLine_ID).append( "=? ");
			params.add(matchInv.getC_OrderLine_ID());
			//whereClause.append(MCostDetail.COLUMNNAME_Qty).append("=0 ");
		}
		else if(model instanceof MMatchInv)
		{	
			MMatchInv matchInv = (MMatchInv) model;
			whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_C_InvoiceLine_ID).append( "=? AND ");
			params.add(matchInv.getC_InvoiceLine_ID());
			whereClause.append(MCostDetail.COLUMNNAME_Qty).append("=0 ");
		}	
		else if (model instanceof MLandedCostAllocation)
		{
			whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_C_LandedCostAllocation_ID).append( "=? ");
			params.add(model.get_ID());
		}
		else
		{
			whereClause.append(" AND ").append(model.get_TableName()).append( "_ID=? AND ");
			params.add(model.get_ID());
			whereClause.append(MCostDetail.COLUMNNAME_Qty).append("<>0 ");
		}
		
		return new Query (mtrx.getCtx(), I_M_CostDetail.Table_Name, whereClause.toString() , mtrx.get_TrxName())
		.setParameters(params)
		.setOrderBy(MCostDetail.COLUMNNAME_SeqNo + " DESC")
		.first();
	}

	/**
	 * Get a list of cost detail based on the document line and cost type
	 * @param docLine Document Line
	 * @param accountSchemaId Account Schema
	 * @param costTypeId Cost type
     * @param isExcludeLandedCost exclude cost detail for landed cost
	 * @return list MCostDetail 
	 */
	public static List<MCostDetail> getByDocLine(DocLine docLine ,int accountSchemaId, int costTypeId, boolean isExcludeLandedCost)
	{
		StringBuilder whereClause = new StringBuilder();
		whereClause.append(MCostDetail.COLUMNNAME_AD_Client_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
		if(isExcludeLandedCost)
			whereClause.append(MCostDetail.COLUMNNAME_C_LandedCostAllocation_ID).append(" IS NULL AND ");
		
		whereClause.append(docLine.getTableName()).append("_ID=?");		
		return new Query (docLine.getCtx(), I_M_CostDetail.Table_Name, whereClause.toString() , docLine.getTrxName())
		.setParameters(
				docLine.getAD_Client_ID(),
                accountSchemaId,
				docLine.getM_Product_ID(),
                costTypeId,
				docLine.get_ID())
		.list();
	}
	
	/**
	 * Get a list of the Cost Detail After the Accounting Date 
	 * @param costDetail Cost Detail
	 * @param costingLevel Costing Level
	 * @return MCostDetail List
	 */
	public static List<MCostDetail> getAfterDate (MCostDetail costDetail, String costingLevel)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		final StringBuffer whereClause = new StringBuffer(MCostDetail.COLUMNNAME_C_AcctSchema_ID + "=? AND ");
		params.add(costDetail.getC_AcctSchema_ID());
		whereClause.append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? AND ");		
		params.add(costDetail.getM_Product_ID());
		
		if(MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
		{	
		whereClause.append(MCostDetail.COLUMNNAME_AD_Org_ID).append("=? AND ");
		params.add(costDetail.getAD_Org_ID());
		}
		if(MAcctSchema.COSTINGLEVEL_Warehouse.equals(costingLevel))
		{	
		whereClause.append(MCostDetail.COLUMNNAME_M_Warehouse_ID).append("=? AND ");
		params.add(costDetail.getM_Warehouse_ID());
		}
		if(MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
		{
			whereClause.append(MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID).append("=? AND ");
			params.add(costDetail.getM_AttributeSetInstance_ID());
		}
		
		whereClause.append( MCostDetail.COLUMNNAME_M_CostType_ID).append("=? AND ");
		params.add(costDetail.getM_CostType_ID());
		
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID).append("=? AND ");
		params.add(costDetail.getM_CostElement_ID());

		whereClause.append( MCostDetail.COLUMNNAME_M_CostDetail_ID).append("<>? AND ");
		params.add(costDetail.getM_CostDetail_ID());
		
		whereClause.append( MCostDetail.COLUMNNAME_M_Transaction_ID).append("<>? AND ");
		params.add(costDetail.getM_Transaction_ID());
		
		whereClause.append(MCostDetail.COLUMNNAME_SeqNo).append(">=? AND ");
		params.add(costDetail.getSeqNo());
		whereClause.append(MCostDetail.COLUMNNAME_Processing).append("=? ");
		params.add(false);


		List<MCostDetail>  afterDateTransactions = new Query(costDetail.getCtx(), Table_Name, whereClause.toString(), costDetail.get_TrxName())
		.setClient_ID()
		.setParameters(params)
		.setOrderBy(MCostDetail.COLUMNNAME_SeqNo + " ASC")
		.list();
		return afterDateTransactions;
	}

	/**************************************************************************
	 * 	Get Cost Detail
	 *	@param ctx context
	 *	@param whereClause where clause
	 *	@param ID 1st parameter
	 *  @param M_AttributeSetInstance_ID ASI
	 *	@param trxName trx
	 *	@return cost detail
	 *  @deprecated
	 */
	public static MCostDetail get (Properties ctx, String whereClause,
		int ID, int M_AttributeSetInstance_ID, String trxName)
	{
		String sql = "SELECT * FROM M_CostDetail WHERE " + whereClause;
		MClientInfo clientInfo = MClientInfo.get(ctx);
		MAcctSchema primary = clientInfo.getMAcctSchema1();
		int C_AcctSchema_ID = primary != null ? primary.getC_AcctSchema_ID() : 0;
		if (C_AcctSchema_ID > 0)
		{
			sql = sql + " AND C_AcctSchema_ID=?";
		}
		MCostDetail retValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, ID);
			pstmt.setInt (2, M_AttributeSetInstance_ID);
			if (C_AcctSchema_ID > 0)
			{
				pstmt.setInt (3, C_AcctSchema_ID);
			}
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MCostDetail (ctx, rs, trxName);
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql + " - " + ID, e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return retValue;
	}
	
	/**************************************************************************
	 * 	Get Cost Detail
	 *	@param ctx context
	 *	@param whereClause where clause
	 *	@param ID 1st parameter
	 *  @param M_AttributeSetInstance_ID ASI
	 *	@param trxName trx
	 *	@return cost detail
	 */
	public static MCostDetail get (Properties ctx, String whereClause, int ID, int M_AttributeSetInstance_ID, int C_AcctSchema_ID, String trxName)
	{
		final String localWhereClause = whereClause
			+ " AND M_AttributeSetInstance_ID=?"
			+ " AND C_AcctSchema_ID=?";
		MCostDetail retValue = new Query(ctx,I_M_CostDetail.Table_Name,localWhereClause,trxName)
		.setParameters(ID,M_AttributeSetInstance_ID,C_AcctSchema_ID)
		.first();
		return retValue;
	}	//	get
	
	/**	Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (MCostDetail.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_CostDetail_ID id
	 *	@param trxName trx
	 */
	public MCostDetail (Properties ctx, int M_CostDetail_ID, String trxName)
	{
		super (ctx, M_CostDetail_ID, trxName);
		if (M_CostDetail_ID == 0)
		{
		//	setC_AcctSchema_ID (0);
		//	setM_Product_ID (0);
			setM_AttributeSetInstance_ID (0);
		//	setC_OrderLine_ID (0);
		//	setM_InOutLine_ID(0);
		//	setC_InvoiceLine_ID (0);
			setProcessed (false);
			setAmt (Env.ZERO);
			setQty (Env.ZERO);
			setIsSOTrx (false);
			setDeltaAmt (Env.ZERO);
			setDeltaQty (Env.ZERO);
		}	
	}	//	MCostDetail

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostDetail (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostDetail

	/**
	 * 	New Constructor
	 *	@param as accounting schema
	 *	@param AD_Org_ID org
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param M_CostElement_ID optional cost element for Freight
	 *	@param Amt amt
	 *	@param Qty qty
	 *	@param Description optional description
	 *	@param trxName transaction
	 * @param M_CostType_ID 
	 */
	public MCostDetail (MAcctSchema as, int AD_Org_ID, 
			int M_Product_ID, int M_AttributeSetInstance_ID,
			int M_CostElement_ID, BigDecimal Amt, BigDecimal Qty,
			String Description, String trxName, int M_CostType_ID)
	{
		this (as.getCtx(), 0, trxName);
		setClientOrg(as.getAD_Client_ID(), AD_Org_ID);
		setC_AcctSchema_ID (as.getC_AcctSchema_ID());
		setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
		//
		setM_CostElement_ID(M_CostElement_ID);
		setM_CostType_ID(M_CostType_ID);
		MCostType ct = new MCostType(as.getCtx(), M_CostType_ID, trxName);
		setCostingMethod(ct.getCostingMethod()); 
		//
		setAmt (Amt);
		setQty (Qty);
		setDescription(Description);
	}	//	MCostDetail

    /**
     *
     * @param transaction
     * @param acctSchemaId
     * @param costTypeId
     * @param costElementId
     * @param amt
     * @param amtLL
     * @param qty
     * @param trxName
     */
	public MCostDetail(MTransaction transaction,int acctSchemaId ,int costTypeId, int costElementId, BigDecimal amt, BigDecimal amtLL, BigDecimal qty, String trxName)
	{
		this (transaction.getCtx(), 0, trxName);
		setAD_Client_ID(transaction.getAD_Client_ID());
		setAD_Org_ID(transaction.getAD_Org_ID());
		setM_Warehouse_ID(transaction.getM_Warehouse_ID());
		setC_AcctSchema_ID(acctSchemaId);
		setM_Product_ID(transaction.getM_Product_ID());
		setM_CostType_ID(costTypeId);
		setM_CostElement_ID(costElementId);
		setM_AttributeSetInstance_ID(transaction.getM_AttributeSetInstance_ID());
		MCostType ct = new MCostType(transaction.getCtx(), costTypeId, transaction.get_TrxName());
		setCostingMethod(ct.getCostingMethod());
		setAmt(amt);
		setAmtLL(amtLL);
		setQty(qty);
		setCostAmt(BigDecimal.ZERO);
		setCostAmtLL(BigDecimal.ZERO);
		setCostAdjustment(Env.ZERO);
		setCostAdjustmentLL(Env.ZERO);
		setCumulatedQty(Env.ZERO);
		setCumulatedAmt(Env.ZERO);
		setCumulatedAmtLL(Env.ZERO);
		setCurrentQty(Env.ZERO);
		setCurrentCostPrice(Env.ZERO);
		setCurrentCostPriceLL(Env.ZERO);
		setCumulatedQty(Env.ZERO);
	}
	
	/**
	 * 	New Constructor
	 *	@param as accounting schema
	 *	@param AD_Org_ID org
	 *	@param M_Product_ID product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param M_CostElement_ID optional cost element for Freight
	 *	@param Amt amt
	 *	@param Qty qty
	 *	@param Description optional description
	 *	@param trxName transaction
	 */
	public MCostDetail (MAcctSchema as, int AD_Org_ID, 
		int M_Product_ID, int M_AttributeSetInstance_ID,
		int M_CostElement_ID, BigDecimal Amt, BigDecimal Qty,
		String Description, String trxName)
	{
		this (as.getCtx(), 0, trxName);
		setClientOrg(as.getAD_Client_ID(), AD_Org_ID);
		setC_AcctSchema_ID (as.getC_AcctSchema_ID());
		setM_Product_ID (M_Product_ID);
		setM_AttributeSetInstance_ID (M_AttributeSetInstance_ID);
		//
		setM_CostElement_ID(M_CostElement_ID);
		//
		setAmt (Amt);
		setQty (Qty);
		setDescription(Description);
	}	//	MCostDetail
	
	/**
	 * 	Set Amt
	 *	@param Amt amt
	 */
	public void setAmt (BigDecimal Amt)
	{
		if (Amt == null)
			super.setAmt (Env.ZERO);
		else
			super.setAmt (Amt);
	}	//	setAmt
	
	/**
	 * 	Set Qty
	 *	@param Qty qty
	 */
	public void setQty (BigDecimal Qty)
	{
		if (Qty == null)
			super.setQty (Env.ZERO);
		else
			super.setQty (Qty);
	}	//	setQty

	/**
	 * 	Is Order
	 *	@return true if order line
	 */
	public boolean isOrder()
	{
		return getC_OrderLine_ID() != 0;
	}	//	isOrder

	/**
	 * 	Is Invoice
	 *	@return true if invoice line
	 */
	public boolean isInvoice()
	{
		return getC_InvoiceLine_ID() != 0;
	}	//	isInvoice

	/**
	 * 	Is Shipment
	 *	@return true if sales order shipment
	 */
	public boolean isShipment()
	{
		return isSOTrx() && getM_InOutLine_ID() != 0;
	}	//	isShipment
	
	/**
	 * 	Is this a Delta Record (previously processed)?
	 *	@return true if delta is not null
	 */
	public boolean isDelta()
	{
		return !(getDeltaAmt().signum() == 0 
			&& getDeltaQty().signum() == 0);
	}	//	isDelta
	
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		return true;
	}	//	afterSave
	
	/**
	 * 	Before Delete
	 *	@return false if processed
	 */
	protected boolean beforeDelete ()
	{
		return !isProcessed();
	}	//	beforeDelete
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MCostDetail[");
		sb.append (get_ID());
		sb.append(",SeqNo=").append(getSeqNo());
		sb.append(",AD_Org_ID=").append(getAD_Org_ID());
		sb.append(",C_AcctSchema_ID=").append(getC_AcctSchema_ID());
		sb.append(",M_CostType_ID=").append(getM_CostType_ID());
		sb.append(",M_CostElement_ID=").append(getM_CostElement_ID());
		sb.append(",M_Transaction_ID=").append(getM_Transaction_ID());
		sb.append(",DateAcct=").append(getDateAcct());
		if (getC_OrderLine_ID() != 0)
			sb.append (",C_OrderLine_ID=").append (getC_OrderLine_ID());
		if (getM_InOutLine_ID() != 0)
			sb.append (",M_InOutLine_ID=").append (getM_InOutLine_ID());
		if (getC_InvoiceLine_ID() != 0)
			sb.append (",C_InvoiceLine_ID=").append (getC_InvoiceLine_ID());
		if (getC_ProjectIssue_ID() != 0)
			sb.append (",C_ProjectIssue_ID=").append (getC_ProjectIssue_ID());
		if (getM_MovementLine_ID() != 0)
			sb.append (",M_MovementLine_ID=").append (getM_MovementLine_ID());
		if (getM_InventoryLine_ID() != 0)
			sb.append (",M_InventoryLine_ID=").append (getM_InventoryLine_ID());
		if (getM_ProductionLine_ID() != 0)
			sb.append (",M_ProductionLine_ID=").append (getM_ProductionLine_ID());
		if (getC_LandedCostAllocation_ID() != 0)
			sb.append (",C_LandedCostAllocation_ID=").append (getC_LandedCostAllocation_ID());
		sb.append(",Cost =").append(getAmt());
		sb.append(",Cost Amt=").append(getCostAmt());
		sb.append(",Qty=").append(getQty());
		sb.append(",CumulatedQty =").append(getCumulatedQty());
		sb.append(",Qty Onhand =").append(getQty().add(getCumulatedQty()));
		sb.append(",Current Qty =").append(getCurrentQty());
		sb.append(",Cumulated Amt =").append(getCumulatedAmt());
		sb.append(",Cumulated Amt LL =").append(getCumulatedAmtLL());
		sb.append(",Current Price =").append(getCurrentCostPrice());
		sb.append(",Current Proce TL =").append(getCurrentCostPriceLL());
		
		
		if (isDelta())
			sb.append(",DeltaAmt=").append(getDeltaAmt())
				.append(",DeltaQty=").append(getDeltaQty());
		sb.append ("]");
		return sb.toString ();
	}	//	toString

    /**
     * process
     * @param as
     * @param product
     * @param costElement
     * @param OrgId
     * @param warehouseId
     * @param attributeSetInstanceId
     * @return
     */
    private boolean process (MAcctSchema as, MProduct product, MCostElement costElement,
                             int OrgId, int warehouseId , int attributeSetInstanceId)
    {
        return true;
    }

	// Elaine 2008/6/20	
	protected boolean afterDelete (boolean success)
	{
		if(success)
		{
			// recalculate MCost			
			boolean ok = false;
			//	get costing level for product
			MAcctSchema as = new MAcctSchema (getCtx(), getC_AcctSchema_ID(), null);
			MProduct product = MProduct.get(getCtx(), getM_Product_ID());
			String CostingLevel = product.getCostingLevel(as);
			//	Org Element
            int organizationId = getAD_Org_ID();
            int warehouseId = getM_Warehouse_ID();
            int attributeSetInstanceId = getM_AttributeSetInstance_ID();
            if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
            {
                organizationId = 0;
                warehouseId = 0;
                attributeSetInstanceId = 0;
            }
            else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel)) {
                warehouseId = 0;
                attributeSetInstanceId = 0;
            }
            else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel)) {
                warehouseId = 0;
                organizationId = 0;
            }

			//	Create Material Cost elements
			if (getM_CostElement_ID() == 0)
			{
                for (MCostElement costElement : MCostElement.getCostElement(getCtx(), get_TrxName()))
                {
                    ok = process (as, product, costElement , organizationId , warehouseId, attributeSetInstanceId);
                    if (!ok)
                        break;
                }
			}	//	Material Cost elements
			else
			{
				MCostElement ce = MCostElement.get(getCtx(), getM_CostElement_ID());
				ok = process (as, product, ce, organizationId, warehouseId , attributeSetInstanceId);
			}
			
			return ok;
		}
		
		return super.afterDelete(success);
	}
	
	/**
	 * Set Date Acct using the source document
	 */
	private void setDateAcct(boolean force)
	{
		Timestamp dateAcct = getDateAcct();
		if (dateAcct != null && !force)
			return;
		//
		String sql = null;
		int param1 = -1;
		if (getC_InvoiceLine_ID() > 0)
		{
			sql = "SELECT i.DateAcct FROM C_InvoiceLine il"
				+" INNER JOIN C_Invoice i ON (i.C_Invoice_ID=il.C_Invoice_ID)"
				+" WHERE il.C_InvoiceLine_ID=?";
			param1 = getC_InvoiceLine_ID();
		}
		else if (getM_InOutLine_ID() > 0)
		{
			sql = "SELECT i.DateAcct FROM M_InOutLine il"
				+" INNER JOIN M_InOut i ON (i.M_InOut_ID = il.M_InOut_ID)"
				+" WHERE il.M_InOutLine_ID=?";
			param1 = getM_InOutLine_ID();
		}
		else if (getC_OrderLine_ID() > 0)
		{
			sql = "SELECT i.DateAcct FROM C_OrderLine il"
				+" INNER JOIN C_Order i ON (i.C_Order_ID = il.C_Order_ID)"
				+" WHERE il.C_OrderLine_ID=?";
			param1 = getC_OrderLine_ID();
		}
		else if (getM_InventoryLine_ID() > 0)
		{
			sql = "SELECT i.MovementDate FROM M_InventoryLine il"
				+" INNER JOIN M_Inventory i ON (i.M_Inventory_ID = il.M_Inventory_ID)"
				+" WHERE il.M_InventoryLine_ID=?";
			param1 = getM_InventoryLine_ID();
		}
		else if (getM_MovementLine_ID() > 0)
		{
			sql = "SELECT i.MovementDate FROM M_MovementLine il"
				+" INNER JOIN M_Movement i ON (i.M_Movement_ID = il.M_Movement_ID)"
				+" WHERE il.M_MovementLine_ID=?";
			param1 = getM_MovementLine_ID();
		}
		else if (getC_LandedCostAllocation_ID() > 0)
		{
			sql = "SELECT i.DateAcct FROM C_Invoice i"
				+" INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID)"
				+" INNER JOIN C_LandedCostAllocation la ON (il.C_InvoiceLine_ID=la.C_InvoiceLine_ID)"
				+" WHERE la.C_LandedCostAllocation_ID=?";
			param1 = getC_LandedCostAllocation_ID();
		}
		//
		dateAcct = DB.getSQLValueTSEx(get_TrxName(), sql, param1);
		setDateAcct(dateAcct);
	}
	
	/**
	 * Restore the Posting to that document can be posting again
	 */
	private void rePosted()
	{		
		if (getC_InvoiceLine_ID() > 0)
		{
			int id = DB.getSQLValue(get_TrxName(), "SELECT M_MatchInv_ID FROM M_MatchInv WHERE C_InvoiceLine_ID=?", getC_InvoiceLine_ID());
			if(id > 0)
			{	
				DB.executeUpdate("UPDATE M_MatchInv SET Posted='N', Processing='N', ProcessedOn=null WHERE M_MatchInv_ID=? AND Processed='Y'", id, get_TrxName());
				MFactAcct.deleteEx (MMatchInv.Table_ID, id, get_TrxName());	
			}
		}
		else if (getM_InOutLine_ID() > 0)
		{
			int id = DB.getSQLValue(get_TrxName(), "SELECT M_InOut_ID FROM M_InOutLine WHERE M_InOutLine_ID=? ", getM_InOutLine_ID());
			if(id > 0)
			{	
				DB.executeUpdate("UPDATE M_InOut SET Posted='N', Processing='N', ProcessedOn=null WHERE M_InOut_ID=? AND Processed='Y'", id , get_TrxName());
				MFactAcct.deleteEx (MInOut.Table_ID, id, get_TrxName());
			}
		}
		else if (getC_OrderLine_ID() > 0)
		{
			int id = DB.getSQLValue(get_TrxName(), "SELECT M_MatchPO_ID FROM M_MatchPO WHERE C_OrderLine_ID=?", getC_OrderLine_ID());
			if(id > 0)
			{	
				DB.executeUpdate("UPDATE M_MatchPO SET Posted='N', Processing='N', ProcessedOn=null WHERE M_MatchPO_ID=? AND Processed='Y'", id, get_TrxName());
				MFactAcct.deleteEx (MMatchPO.Table_ID, id, get_TrxName());
			}
		}
		else if (getM_InventoryLine_ID() > 0)
		{
			int id = DB.getSQLValue(get_TrxName(), "SELECT M_Inventory_ID FROM M_InventoryLine WHERE M_InventoryLine_ID=?", getM_InventoryLine_ID());
			if(id>0)
			{	
				DB.executeUpdate("UPDATE M_Inventory SET Posted='N', Processing='N', ProcessedOn=null WHERE M_Inventory_ID=? AND Processed='Y'", id, get_TrxName());
				MFactAcct.deleteEx (MInventory.Table_ID, id, get_TrxName());
			}
		}
		else if (getM_MovementLine_ID() > 0)
		{
			int id = DB.getSQLValue(get_TrxName(), "SELECT M_Movement_ID FROM M_MovementLine WHERE M_MovementLine_ID=?", getM_MovementLine_ID());
			if(id>0)
			{
				DB.executeUpdate("UPDATE M_Movement SET Posted='N', Processing='N', ProcessedOn=null WHERE M_Movement_ID=? AND Processed='Y'", id, get_TrxName());
				MFactAcct.deleteEx (MMovement.Table_ID, id, get_TrxName());
			}
		}
		else if (getC_LandedCostAllocation_ID() > 0)
		{
			//Is necessary the logic when exist a landen cost
		}
	}

	@Override
	protected boolean beforeSave(boolean newRecord)
	{
		setDateAcct(false);
		return true;
	}


	@Override
	public I_M_CostType getM_CostType() throws RuntimeException
	{
		// TODO OPTIMIZATION: use a cached method
		return super.getM_CostType();
	}
	
	/**
	 * return warehouse id
	 * @return warehouse id
	 */
	public int getM_Warehouse_ID()
	{
		final String whereClause = "SELECT l.M_Warehouse_ID FROM M_CostDetail cd " 
								 + "INNER JOIN  M_Transaction t ON (cd.M_Transaction_ID=t.M_Transaction_ID) "
								 + "INNER JOIN M_Locator l ON (t.M_Locator_ID=l.M_Locator_ID) WHERE cd.M_CostDetail_ID=? ";
		return DB.getSQLValue(this.get_TrxName(), whereClause , getM_CostDetail_ID());		
	}

	/**
	 * Get cost detail by document line landed cost allocation
	 * @param landedCostAllocation
	 * @param acctSchemaId
	 * @param costTypeId
	 * @return
	 */
	public static BigDecimal getByDocLineLandedCost(MLandedCostAllocation landedCostAllocation ,int acctSchemaId, int costTypeId)
	{
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(COLUMNNAME_AD_Client_ID).append("=? AND ")
				   	.append(COLUMNNAME_C_AcctSchema_ID).append("=? AND ")
				   	.append(COLUMNNAME_M_Product_ID).append("=? AND ")
				   	.append(COLUMNNAME_M_AttributeSetInstance_ID).append("=? AND ")
				   	.append(COLUMNNAME_M_CostType_ID).append("=? AND ")
				   	.append(COLUMNNAME_M_CostElement_ID).append("=? AND ")
				   	.append(COLUMNNAME_C_InvoiceLine_ID).append("=? AND ")
					.append(COLUMNNAME_M_InOutLine_ID).append("=? AND ")
				   	.append(COLUMNNAME_C_LandedCostAllocation_ID).append("=? ");
		return new Query (landedCostAllocation.getCtx(), Table_Name, whereClause.toString() , landedCostAllocation.get_TrxName())
		.setParameters(
				landedCostAllocation.getAD_Client_ID(),
				acctSchemaId,
				landedCostAllocation.getM_Product_ID(),
				landedCostAllocation.getM_AttributeSetInstance_ID(),
				costTypeId,
				landedCostAllocation.getM_CostElement_ID(),
				landedCostAllocation.getC_InvoiceLine_ID(),
				landedCostAllocation.getM_InOutLine_ID(),
				landedCostAllocation.getC_LandedCostAllocation_ID())
		.aggregate(COLUMNNAME_CostAmt + " + " +
				COLUMNNAME_CostAdjustment + " + " +
				COLUMNNAME_CostAmtLL + " + " +
				COLUMNNAME_CostAdjustmentLL, Query.AGGREGATE_SUM);
	}
	/**
	 * Returns the summary costs of costdetails (different cost elements)
	 * @param invoiceLine
	 * @param inOutLine
	 * @param acctSchemaId
	 * @param costTypeId
	 * @return
	 */
	public static BigDecimal getByDocLineMatchInv(MInvoiceLine invoiceLine, MInOutLine inOutLine ,int acctSchemaId, int costTypeId)
	{
		final StringBuilder whereClause = new StringBuilder();
		whereClause.append(COLUMNNAME_AD_Client_ID).append("=? AND ")
				   .append(COLUMNNAME_C_AcctSchema_ID).append("=? AND ")
				   .append(COLUMNNAME_M_Product_ID).append( "=? AND ")
				   .append(COLUMNNAME_M_AttributeSetInstance_ID + "=? AND ")
				   .append(COLUMNNAME_M_CostType_ID).append("=? AND ")
				   .append(COLUMNNAME_C_InvoiceLine_ID).append("=? AND ")
				   .append(COLUMNNAME_M_InOutLine_ID).append("=?");

		return new Query (invoiceLine.getCtx(), I_M_CostDetail.Table_Name, whereClause.toString() , invoiceLine.get_TrxName())
		.setParameters(
				invoiceLine.getAD_Client_ID(),
				acctSchemaId,
				invoiceLine.getM_Product_ID(),
				invoiceLine.getM_AttributeSetInstance_ID(),
				costTypeId,
				invoiceLine.getC_InvoiceLine_ID(),
				inOutLine.getM_InOutLine_ID())
		.aggregate(COLUMNNAME_CostAdjustment + " + " +
				COLUMNNAME_CostAdjustmentLL, Query.AGGREGATE_SUM);
	}
}	//	MCostDetail
