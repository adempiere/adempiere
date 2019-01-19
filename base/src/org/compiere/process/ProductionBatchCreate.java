package org.compiere.process;

import java.util.logging.Level;

import org.compiere.model.MProduction;
import org.compiere.model.MProductionBatch;
import org.compiere.util.AdempiereUserError;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;

/**
 * Contributed by Adaxa
 * @author Susanne Calderon, susanne.de.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author JobrianT
 *
 */
public class ProductionBatchCreate extends SvrProcess {

	private int p_M_Production_Batch_ID=0;
	private MProductionBatch pBatch = null;
	
	private boolean m_CreateMovement = true;
	private boolean m_CreateProductionLine = true;
	
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		
		p_M_Production_Batch_ID = getRecord_ID();
		pBatch = new MProductionBatch(getCtx(), p_M_Production_Batch_ID, get_TrxName());

	}	//prepare

	@Override
	protected String doIt() throws Exception
	{
		if (pBatch.get_ID() == 0)
			throw new AdempiereUserError("Could not load production batch");

		if (pBatch.isProcessed())
		{
			if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Close))
				return "Can not process, Production Batch is closed";
			else if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Void))
				return "Can not process, Production Batch is voided";
			else if (pBatch.getDocStatus().equals(MProductionBatch.DOCACTION_Complete))
			{
				if (pBatch.getQtyCompleted().compareTo(pBatch.getTargetQty()) >= 0)
					return "Already processed target qty of production batch";
				return createProduction();
			}
		}
		else
		{
			return "Please first confirm/complete the batch";
		}

		return null;
	}

	protected String createProduction() throws Exception {
		
		String result = pBatch.createAutomaticProduction();		
		return result;
	}
}
