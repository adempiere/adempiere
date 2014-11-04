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
 * Contributor(s): victor.perez@e-evolution.com http://www.e-evolution.com    *
 *****************************************************************************/

package org.eevolution.process;

import org.adempiere.engine.CostEngineFactory;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.I_M_Cost;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.eevolution.model.MPPCostCollector;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Regenerate Cost Detail The Generate Cost Transaction process allows the
 * detailed cost calculation and cost generation beginning from a date. If you
 * have not yet set COGs Adjustment, you should execute this process before a
 * period's end in order to fix the cost layers.
 *
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class GenerateCostDetail extends SvrProcess {
    /**
     * Parameters *
     */
    private int p_M_Product_ID;
    private int p_M_CostElement_ID;
    private int p_M_CostType_ID;
    private int p_C_AcctSchema_ID;
    private Timestamp p_DateAcct;
    private Timestamp p_DateAcctTo;

    /**
     * Variables *
     */
    private ArrayList<Object> deleteParameters;
    private ArrayList<Object> resetCostParameters;
    private List<MAcctSchema> acctSchemas = new ArrayList<MAcctSchema>();
    private List<MCostType> costTypes = new ArrayList<MCostType>();
    private List<MCostElement> costElements = new ArrayList<MCostElement>();
    private StringBuffer deleteCostDetailWhereClause;
    private StringBuffer resetCostWhereClause;
	private String m_trxName;
	private boolean p_IsServerProcess = false;
	private StringBuffer resetCostSubWhereClause;
	
	/**	Log					*/
	private CLogger			log = CLogger.getCLogger(getClass());

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
    	log.finest("Preparing");
        ProcessInfoParameter[] parameters = getParameter();
        for (ProcessInfoParameter parameter : parameters) {
            String name = parameter.getParameterName();
            if (parameter.getParameter() == null)
                ;

            if (name.equals(MCostDetail.COLUMNNAME_C_AcctSchema_ID)) {
                p_C_AcctSchema_ID = parameter.getParameterAsInt();
            } else if (name.equals(MCostDetail.COLUMNNAME_M_CostType_ID)) {
                p_M_CostType_ID = parameter.getParameterAsInt();
            } else if (name.equals(MCostDetail.COLUMNNAME_M_CostElement_ID)) {
                p_M_CostElement_ID = parameter.getParameterAsInt();
            } else if (name.equals(MCostDetail.COLUMNNAME_M_Product_ID)) {
                p_M_Product_ID = parameter.getParameterAsInt();
            } else if (name.equals(MCostDetail.COLUMNNAME_DateAcct)) {
                p_DateAcct = (Timestamp) parameter.getParameter();
                // TODO remove this restriction
                if (p_DateAcct == null)
                    throw new FillMandatoryException(
                            MCostDetail.COLUMNNAME_DateAcct);
                p_DateAcctTo = (Timestamp) parameter.getParameter_To();
                if (p_DateAcctTo == null)
                    p_DateAcctTo = new Timestamp(System.currentTimeMillis());
            }
        }
        
        log.fine("Parameters: " + MCostDetail.COLUMNNAME_C_AcctSchema_ID + "=" + p_C_AcctSchema_ID + ", " + 
            	MCostDetail.COLUMNNAME_M_CostType_ID + "=" + p_M_CostType_ID + ", " +
            	MCostDetail.COLUMNNAME_M_CostElement_ID + "=" + p_M_CostElement_ID + ", " +
            	MCostDetail.COLUMNNAME_M_Product_ID + "=" + p_M_Product_ID + ", " +
            	MCostDetail.COLUMNNAME_DateAcct + " from " + p_DateAcct + 
                " to " + p_DateAcctTo);
        
        if (p_DateAcct != null) {
            setup();
        }
        
        p_IsServerProcess  = true;

    } // prepare

    /**
     * execute the Generate Cost Detail
     */
    protected String doIt() throws Exception {
        generateCostDetail();
        return "@Ok@";

    }

    /**
     * Delete Cost Detail
     *
     * @throws SQLException
     */
    private void deleteCostDetail() throws SQLException {
        StringBuffer sqlDelete;

        int record = 0;
        sqlDelete = new StringBuffer("DELETE M_CostDetail WHERE ");
        sqlDelete.append(deleteCostDetailWhereClause);
        record = DB.executeUpdateEx(sqlDelete.toString(),
                deleteParameters.toArray(), get_TrxName());
        commitEx();
        log.info("Deleted " + record + " M_CostDetail records.");
    }

    private void resetCostDimension(String costingMethod) throws SQLException {
        StringBuffer sqlReset;
        int record = 0;
        sqlReset = new StringBuffer("UPDATE M_Cost SET ");

        // Delete M_Cost not for others than average
        if(MCostType.COSTINGMETHOD_AverageInvoice.equals(costingMethod)) {
            sqlReset.append(I_M_Cost.COLUMNNAME_CurrentCostPrice).append("=0.0,");
            sqlReset.append(I_M_Cost.COLUMNNAME_CurrentCostPriceLL).append("= 0.0,");
        }

        sqlReset.append(I_M_Cost.COLUMNNAME_CurrentQty).append("= 0.0,");
        sqlReset.append(I_M_Cost.COLUMNNAME_CumulatedAmt).append("= 0.0,");
        sqlReset.append(I_M_Cost.COLUMNNAME_CumulatedAmtLL).append("= 0.0,");
        sqlReset.append(I_M_Cost.COLUMNNAME_CumulatedQty).append("= 0.0 ");
        sqlReset.append(" WHERE ").append(resetCostWhereClause);
        record = DB.executeUpdateEx(sqlReset.toString(),
                resetCostParameters.toArray(), get_TrxName());
        commitEx();
        log.info("Reset " + record + " M_Cost records.");


    }
    
    

    /**
     * Setup the collections
     *
     * @throws SQLException
     */
    private void setup() {

        if (p_C_AcctSchema_ID > 0)
            acctSchemas.add(MAcctSchema.get(getCtx(), p_C_AcctSchema_ID, get_TrxName()));
        else
            acctSchemas = new ArrayList(Arrays.asList(MAcctSchema
                    .getClientAcctSchema(getCtx(), getAD_Client_ID(),
                            get_TrxName())));

        if (p_M_CostType_ID > 0)
            costTypes.add(new MCostType(getCtx(), p_M_CostType_ID,
                    get_TrxName()));
        else
            costTypes = MCostType.get(getCtx(), get_TrxName());

        if (p_M_CostElement_ID > 0)
            costElements.add(MCostElement.get(getCtx(), p_M_CostElement_ID));
        else
            costElements = MCostElement.getCostElement(getCtx(), get_TrxName());
        
        log.fine("acctSchemas: " + acctSchemas.toString() + " " +
        		"costTypes " + costTypes.toString() + " " + 
        		"costElements " + costElements.toString());
    }

    /**
     * Apply Criteria for where clause
     *
     * @param accountSchemaId
     * @param costTypeId
     * @param costElementId
     * @param productId
     * @param dateAccount
     */
    private void applyCriteria(int accountSchemaId, int costTypeId,
                                int costElementId, int productId, Timestamp dateAccount, Timestamp dateAccountTo) {
        deleteParameters = new ArrayList<Object>();
        resetCostParameters = new ArrayList<Object>();
        deleteCostDetailWhereClause = new StringBuffer("1=1");
        resetCostWhereClause = new StringBuffer("1=1");
        resetCostSubWhereClause = new StringBuffer(" AND EXISTS ( SELECT 1 FROM RV_Transaction WHERE 1=1");
        

        if (accountSchemaId > 0) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_C_AcctSchema_ID)
                    .append("=? ");
            deleteParameters.add(accountSchemaId);
            resetCostWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_C_AcctSchema_ID)
                    .append("=? ");
            resetCostParameters.add(accountSchemaId);
        }
        if (costTypeId > 0) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_CostType_ID).append("=? ");
            deleteParameters.add(costTypeId);
            resetCostWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_CostType_ID).append("=? ");
            resetCostParameters.add(costTypeId);//SHW
        }
        if (costElementId > 0) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_CostElement_ID)
                    .append("=? ");
            deleteParameters.add(costElementId);
            resetCostWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_CostElement_ID)
                    .append("=? ");
            resetCostParameters.add(costElementId);//SHW
        }
        if (productId > 0) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? ");
            deleteParameters.add(productId);
            resetCostWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? ");
            resetCostParameters.add(productId);
            resetCostSubWhereClause.append(" AND ")
            		.append(MCostDetail.COLUMNNAME_M_Product_ID).append("=? ");
            resetCostParameters.add(productId); // again
        }
        if (dateAccount != null) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_DateAcct).append(">=? ");
            deleteParameters.add(dateAccount);
        	resetCostSubWhereClause.append(" AND TRUNC(")
            		.append(MCostDetail.COLUMNNAME_DateAcct).append(")>=? ");
        	resetCostParameters.add(dateAccount);
        }
        if (dateAccountTo != null) {
            deleteCostDetailWhereClause.append(" AND ")
                    .append(MCostDetail.COLUMNNAME_DateAcct).append("<=? ");
            deleteParameters.add(dateAccountTo);
        	resetCostSubWhereClause.append(" AND TRUNC(")
            		.append(MCostDetail.COLUMNNAME_DateAcct).append(")<=? ");
        	resetCostParameters.add(dateAccountTo);
        }
        resetCostWhereClause.append(resetCostSubWhereClause).append(")");
        return;
    }

    public void generateCostDetail() throws SQLException {

        // Delete Process
        for (MAcctSchema as : acctSchemas) {
            // for each Cost Type
            for (MCostType ct : costTypes) {
                // for each Cost Element
                for (MCostElement ce : costElements) {
                    log.fine("Deleting and resetting cost info for acctSchema: " + as.toString() + " " +
                    		"costType: " + ct.toString() + " " + 
                    		"costElement: " + ce.toString() + " " + 
                    		"Product ID=" + p_M_Product_ID + " " +
                    		"DateAcct from " + p_DateAcct + " to " + p_DateAcctTo);
                    applyCriteria(as.getC_AcctSchema_ID(),
                            ct.getM_CostType_ID(), ce.getM_CostElement_ID(),
                            p_M_Product_ID, p_DateAcct, p_DateAcctTo);
                    deleteCostDetail();
                    resetCostDimension(ct.getCostingMethod());
                }
            }
        }

        KeyNamePair[] transactions = getTransactionIdsByDateAcct();
        log.fine("Transaction to process : " + transactions.length);

        int process = 0;
        int productId = 0;

        for (KeyNamePair keyNamePair : transactions) {

            int transactionId = keyNamePair.getKey();
            int transactionProductId = DB.getSQLValue(get_TrxName() , "SELECT M_Product_ID FROM M_Transaction WHERE M_Transaction_ID=?" , transactionId);

            if (productId != transactionProductId) {  //  New product
                productId = transactionProductId;
                MProduct product = MProduct.get(Env.getCtx(), productId);
                log.fine("Starting to process Product: " + product.getValue() + " " + product.getName());
                generateCostDetailForCollectorCost(productId);
            }

            // for each Account Schema
            for (MAcctSchema accountSchema : acctSchemas) {
                // for each Cost Type
                for (MCostType costType : costTypes) {
                    // for each Cost Element
                    for (MCostElement costElement : costElements) {
                        GenerateCostDetail.generateCostDetail(accountSchema, costType, costElement, transactionId, get_TrxName());
                    }
                }
            }
            process++;
            System.out.println("Transaction " + transactionId + " record ..." + process);
        }
    }

    public static void generateCostDetail(MAcctSchema accountSchema, MCostType costType , MCostElement costElement,int transactionId, String trxName) {

        Trx.run(trxName, new TrxRunnable() {
            MAcctSchema accountSchema;
            MCostType costType;
            MCostElement costElement;
            int transactionId;

            public TrxRunnable setParameters(MAcctSchema accountSchema, MCostType costType, MCostElement costElement, int transactionId) {
                this.accountSchema = accountSchema;
                this.costType = costType;
                this.costElement = costElement;
                this.transactionId = transactionId;
                return this;
            }

            public void run(String trxName) {


                MTransaction transaction = new MTransaction(accountSchema.getCtx(), transactionId, trxName);

                //Create Cost Detail for this Transaction
                 CostEngineFactory.getCostEngine(accountSchema.getAD_Client_ID())
                        .createCostDetail(accountSchema,  costType, costElement, transaction,
                                transaction.getDocumentLine(), true);
                CostEngineFactory.getCostEngine(accountSchema.getAD_Client_ID())
                        .clearAccounting(accountSchema, transaction);


                // Calculate adjustment cost by variances in
                // invoices
                if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(transaction.getMovementType())) {

                    MInOutLine line = (MInOutLine) transaction.getDocumentLine();

                    if (MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {

                        //get purchase matches
                        List<MMatchPO> orderMatches = MMatchPO
                                .getInOutLine(line);
                        for (MMatchPO match : orderMatches) {
                            if (match.getM_Product_ID() == transaction.getM_Product_ID()) {
                                CostEngineFactory.getCostEngine(
                                        accountSchema.getAD_Client_ID())
                                        .createCostDetail(accountSchema,  costType, costElement,transaction,
                                                match, true);
                            }
                        }
                        //get invoice matches
                        List<MMatchInv> invoiceMatches = MMatchInv
                                .getInOutLine(line);
                        for (MMatchInv match : invoiceMatches) {
                            if (match.getM_Product_ID() == transaction.getM_Product_ID()) {
                                CostEngineFactory.getCostEngine(
                                        accountSchema.getAD_Client_ID())
                                        .createCostDetail(accountSchema,  costType, costElement,transaction,
                                                match, true);
                            }
                        }
                    }

                    //get landed allocation cost
                    for (MLandedCostAllocation allocation : MLandedCostAllocation
                            .getOfInOuline(line,
                                    costElement.getM_CostElement_ID())) {
                        //System.out.println("Allocation : " + allocation.getC_LandedCostAllocation_ID() +  " Amount:" +  allocation.getAmt());
                        CostEngineFactory
                                .getCostEngine(accountSchema.getAD_Client_ID())
                                .createCostDetail(accountSchema,  costType, costElement, transaction, allocation, true);
                    }

                    //Trx trx = Trx.get(trxName, false);
                    //trx.commit();

                }
            }
        }.setParameters(accountSchema, costType, costElement, transactionId));
    }

    private void generateCostDetailForCollectorCost(int productId)
            throws SQLException {
        List<MPPCostCollector> ccs = MPPCostCollector
                .getCostCollectorNotTransaction(getCtx(), productId,
                        getAD_Client_ID(), p_DateAcct, get_TrxName());
        // Process Collector Cost Manufacturing
        log.fine("Found " + ccs.size() + " non-transactional cost collectors for product ID " + productId);
        for (MPPCostCollector cc : ccs) {
            for (MCostDetail cd : MCostDetail.getByCollectorCost(cc)) {
                log.fine("Deleting cost detail: " + cd.toString());
                cd.deleteEx(true);
            }

            CostEngineFactory.getCostEngine(getAD_Client_ID())
                    .createCostDetail(null, cc);
            commitEx();
        }
    }

    protected int getAD_Client_ID() {
    	int AD_Client_ID = 0;
    	if (!p_IsServerProcess) {
    		// no server process - use the environment client
    		// TODO split the generate cost details functions out of the process
    		AD_Client_ID = Env.getAD_Client_ID(getCtx());
    	}
    	else {
    		// Server process - use the process client
    		AD_Client_ID = super.getAD_Client_ID();
    	}        
    	return AD_Client_ID;
    }
    
	/**
	 * Return the main transaction of the current process.
	 * @return the transaction name
	 */
	public String get_TrxName()
	{
    	String trxName = "";
    	if (!p_IsServerProcess) {
    		// no server process - use the set transaction name.
    		trxName = m_trxName;
    	}
    	else {
    		// Server process - use the process transaction.
    		trxName = super.get_TrxName();
    	}        
    	return trxName;
	}	//	get_TrxName
    
	/**
	 * Commit the current transaction
	 */
	public void commitEx() throws SQLException
	{
    	if (!p_IsServerProcess) {
    		// no server process - we have to find the transaction to use.
    		Trx trx = Trx.get(get_TrxName(), false); // Don't create a new one.
    		
    		if (trx != null) {
    			trx.commit(true);
    		}
    	}
    	else {
    		// Server process - use the process transaction.
    		super.commitEx();
    	}        
	}	//	commitEx

	/**
	 * @param trxName the trxName to set
	 */
	public void set_trxName(String trxName) {
		this.m_trxName = trxName;
	}

	private KeyNamePair[] getTransactionIdsByDateAcct() {
        StringBuilder sql = new StringBuilder();
        List<Object> parameters = new ArrayList<Object>();
        StringBuilder whereClause = new StringBuilder("WHERE ");
        whereClause.append(MCostDetail.COLUMNNAME_AD_Client_ID).append("=")
                .append(getAD_Client_ID());
        if (p_M_Product_ID > 0) {
            whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_M_Product_ID)
                    .append("=?");
            parameters.add(p_M_Product_ID);
        }
        if (p_DateAcct != null) {
        	whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_DateAcct).append(">=?");
        	parameters.add(p_DateAcct);
        }
        
        if (p_DateAcctTo != null) {
            whereClause.append(" AND ").append(MCostDetail.COLUMNNAME_DateAcct).append("<=?");
            parameters.add(p_DateAcctTo);
        }

        sql.append("SELECT M_Transaction_ID , Value FROM RV_Transaction ")
                .append(whereClause)
                .append(" ORDER BY M_Product_ID ,  TRUNC( DateAcct ) , M_Transaction_ID , SUBSTR(MovementType,2,1) ");
        //.append(" ORDER BY M_Product_ID , DateAcct , M_Transaction_ID");
        log.fine("SQL :" + sql);
        return DB.getKeyNamePairs(get_TrxName(), sql.toString(), false, parameters.toArray());
    }


    public void generateCostDetail(Properties ctx, Timestamp DateAcct, String trxName) {
    	generateCostDetail(ctx, DateAcct, null, 0,0,0,0, trxName);
    }

    public void generateCostDetail(Properties ctx, Timestamp DateAcct, Timestamp DateAcctTo, String trxName) {
    	generateCostDetail(ctx, DateAcct, DateAcctTo, 0,0,0,0, trxName);
    }

    public void generateCostDetail(Properties ctx, int M_Product_ID, String trxName) {
    	generateCostDetail(	ctx, null, null,
	    		M_Product_ID, 0,0,0, trxName);
    }

    public void generateCostDetail(Properties ctx, int M_Product_ID, int M_CostElement_ID, 
			int M_CostType_ID, int C_AcctSchema_ID, String trxName) {

    	generateCostDetail(	ctx, null, null,
	    		M_Product_ID, M_CostElement_ID, 
				M_CostType_ID, C_AcctSchema_ID, trxName);
    }
    /**
     * Generate cost details.  DateAcct is mandatory, the parameters can be null.
     * @param M_Product_ID
     * @param M_CostElement_ID
     * @param M_CostType_ID
     * @param C_AcctSchema_ID
     * @param DateAcct
     * @param DateAcctTo
     */
    public void generateCostDetail(	Properties ctx, Timestamp DateAcct, Timestamp DateAcctTo,
						    		int M_Product_ID, int M_CostElement_ID, 
									int M_CostType_ID, int C_AcctSchema_ID, String trxName) {

    	p_M_Product_ID = M_Product_ID;
        p_M_CostElement_ID = M_CostElement_ID;
        p_M_CostType_ID = M_CostType_ID;
        p_C_AcctSchema_ID = C_AcctSchema_ID;
        p_DateAcct = DateAcct;
        p_DateAcctTo = DateAcctTo;
        
        
        set_trxName(trxName);
        set_ctx(ctx);  // Provide the context to use
        
        //if (p_DateAcct == null)  // Mandatory
        //	return;
        
        setup();
        try {
			generateCostDetail();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
