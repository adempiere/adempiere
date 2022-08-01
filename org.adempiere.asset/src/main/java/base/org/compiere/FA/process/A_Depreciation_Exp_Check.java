/**
 * 
 */
package org.compiere.FA.process;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.FA.model.MAsset;
import org.compiere.FA.model.MDepreciationEntry;
import org.compiere.FA.model.MDepreciationExp;
import org.compiere.FA.model.MDepreciationWorkfile;
import org.compiere.model.MPeriod;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;


/**
 * @author Anca Bradau www.arhipac.ro
 *
 */
public class A_Depreciation_Exp_Check extends SvrProcess
{
	private boolean p_IsTest = true;
	private int p_A_Asset_ID = -1;
	private String p_WhereClause = null;

	
	protected void prepare()
	{
		;
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("IsTest"))
			{
				p_IsTest = para.getParameterAsBoolean();
			}
			else if (name.equals("A_Asset_ID"))
			{
				p_A_Asset_ID = para.getParameterAsInt();
			}
			else if (name.equals("WhereClause"))
			{
				p_WhereClause = (String)para.getParameter();
			}
			else
			{
			}
		}
	}
	
	
	protected String doIt() throws Exception
	{
//		ARHIPAC.assertDebugging();
		
		for (int A_Asset_ID : getAsset_IDs())
		{
			fixDepreciation(A_Asset_ID);
			if (p_IsTest)
			{
				rollback();
			}
		}

		return "Ok";
	}
	
	private int[] getAsset_IDs()
	{
		ArrayList<Object> params = new ArrayList<Object>();
		String whereClause = null;
		if (p_A_Asset_ID > 0)
		{
			whereClause = "A_Asset_ID=?";
			params.add(p_A_Asset_ID);
		}
		else
		{
			whereClause = p_WhereClause;
		}
		
		return new Query(getCtx(), MAsset.Table_Name, whereClause, get_TrxName())
			.setParameters(params)
			.setOrderBy("A_Asset_ID")
			.getIDs();
	}

	private void fixDepreciation(int A_Asset_ID)
	{
		MAsset asset = MAsset.get(getCtx(), A_Asset_ID, get_TrxName());
		List<MDepreciationExp> depreciations = getDepreciation(asset);
		// if exist depreciations with period 0 
		if (depreciations.get(0).getA_Period()==0)
		{
			fixDepreciationExp(depreciations.get(0), TimeUtil.getMonthLastDay(asset.getAssetServiceDate()));
			Timestamp tms = depreciations.get(0).getDateAcct();
			for (int i=1; i<depreciations.size(); i++)
			{
				fixDepreciationExp(depreciations.get(i), TimeUtil.getMonthLastDay(TimeUtil.addMonths(tms,1 )));
				tms = depreciations.get(i).getDateAcct();
			}	
		}
		else 
		{
			fixDepreciationExp(depreciations.get(0), TimeUtil.getMonthLastDay(TimeUtil.addMonths(asset.getAssetServiceDate(),1 )));

			Timestamp tms = depreciations.get(0).getDateAcct();
			for (int i=1; i<depreciations.size(); i++)
			{
				fixDepreciationExp(depreciations.get(i), TimeUtil.getMonthLastDay(TimeUtil.addMonths(tms,1 )));
				tms = depreciations.get(i).getDateAcct();
			}	
		}
		//
		for (MDepreciationWorkfile wk : MDepreciationWorkfile.forA_Asset_ID(getCtx(), A_Asset_ID, get_TrxName()))
		{
			wk.setA_Current_Period();
			wk.saveEx();
			addLog(""+wk+": Period="+wk.getA_Current_Period()+", DateAcct="+wk.getDateAcct());
			
		}
	}

	private void fixDepreciationExp(MDepreciationExp exp, Timestamp dateAcctNew)
	{
		if (!exp.getDateAcct().equals(dateAcctNew))
		{
			addLog("OLD1: "+exp);
			MDepreciationEntry.deleteFacts(exp);
			exp.setDateAcct(dateAcctNew);
			exp.setA_Depreciation_Entry_ID(0);
			exp.saveEx();
			addLog("NEW1: "+exp);
		}
		//
		// Check DateAcct and A_Depreciation_Entry.C_Period_ID relation:
		if (exp.getA_Depreciation_Entry_ID() > 0)
		{
			int C_Period_ID = DB.getSQLValueEx(exp.get_TrxName(),
							"SELECT C_Period_ID FROM A_Depreciation_Entry WHERE A_Depreciation_Entry_ID=?",
							exp.getA_Depreciation_Entry_ID());
			MPeriod period = MPeriod.get(exp.getCtx(), C_Period_ID);
			if (!period.isInPeriod(exp.getDateAcct()))
			{
				addLog("OLD2: "+exp);
				MDepreciationEntry.deleteFacts(exp);
				exp.setA_Depreciation_Entry_ID(0);
				exp.saveEx();
				addLog("NEW2: "+exp);
			}
		}
	}
	
	private List<MDepreciationExp> getDepreciation(MAsset asset)
	{
		String whereClause = "A_Asset_ID=?";
		return new Query(getCtx(), MDepreciationExp.Table_Name, whereClause, get_TrxName())
			.setParameters(new Object[]{asset.get_ID()})
			.setOrderBy(MDepreciationExp.COLUMNNAME_A_Period)
			.list();	
	}
	
}
