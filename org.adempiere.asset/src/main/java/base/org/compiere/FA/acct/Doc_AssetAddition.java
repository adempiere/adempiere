package org.compiere.FA.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.compiere.model.I_C_Project;
import org.compiere.model.I_C_Project_Acct;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.FA.model.MAssetAcct;
import org.compiere.FA.model.MAssetAddition;
import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.acct.FactLine;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.ProductCost;
import org.compiere.model.X_C_Project_Acct;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * @author Teo_Sarca, SC ARHIPAC SERVICE SRL
 * 
 * 
 */
public class Doc_AssetAddition extends Doc
{
	public Doc_AssetAddition (MAcctSchema[] as, ResultSet rs, String trxName)
	{
		super(as, MAssetAddition.class, rs, MDocType.DOCBASETYPE_GLDocument, trxName);
	}

	
	protected String loadDocumentDetails()
	{
		return null;
	}

	
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}

	/**
	 * Produce inregistrarea:
	 * <pre>
	 *	20.., 21..[A_Asset_Acct]			=	23..[P_Asset_Acct/Project Acct]
	 * </pre>
	 */
	
	public ArrayList<Fact> createFacts(MAcctSchema acctSchema)
	{
		MAssetAddition assetAddition = getAssetAddition();
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact(this, acctSchema, assetAddition.getPostingType());
		facts.add(fact);
		//
		if (MAssetAddition.A_SOURCETYPE_Imported.equals(assetAddition.getA_SourceType())
				|| MAssetAddition.A_CAPVSEXP_Expense.equals(assetAddition.getA_CapvsExp())) //@win prevent create journal if expense addition
		{
			// no accounting if is imported record
			return facts;
		}
		//
		BigDecimal assetValueAmt = assetAddition.getAssetValueAmt();
		FactLine[] fls = FactUtil.createSimpleOperation(fact, null,
				getA_Asset_Acct(), getP_Asset_Acct(acctSchema),
				acctSchema.getC_Currency_ID(),
				assetValueAmt,
				false);
		// Set BPartner and C_Project dimension for "Imobilizari in curs / Property Being"
		final int invoiceBusinessPartnerId = getInvoicePartner_ID();
		final int invoiceProjectId = getInvoiceProject_ID();
		if (invoiceBusinessPartnerId > 0)
		{
			fls[1].setC_BPartner_ID(invoiceBusinessPartnerId);
		}
		if (invoiceProjectId >0)
		{
			 fls[1].setC_Project_ID(invoiceProjectId);
		}
		//
		return facts;
	}
	
	private MAssetAddition getAssetAddition()
	{
		return (MAssetAddition)getPO();
	}
	
	private MAccount getP_Asset_Acct(MAcctSchema acctSchema)
	{
		MAssetAddition assetAddition = getAssetAddition();
		// Source Account
		MAccount account = null;
		if (MAssetAddition.A_SOURCETYPE_Project.equals(assetAddition.getA_SourceType()))
		{
			I_C_Project project = assetAddition.getC_Project();
			return getProjectAcct(project, acctSchema);
		}
		else if (MAssetAddition.A_SOURCETYPE_Manual.equals(assetAddition.getA_SourceType())
				&& getC_Charge_ID() > 0) // backward compatibility: only if charge defined; if not fallback to product account 
		{	
			account = MCharge.getAccount(getC_Charge_ID(), acctSchema, null);
			return account;
		}	
		else if (MAssetAddition.A_SOURCETYPE_Invoice.equals(assetAddition.getA_SourceType())
				&& assetAddition.getC_InvoiceLine().getC_Project_ID() > 0)
		{
			I_C_Project prj = assetAddition.getC_InvoiceLine().getC_Project();
			return getProjectAcct(prj, acctSchema);
		}
		else
		{
			account = getP_Expense_Acct(assetAddition.getM_Product_ID(), acctSchema);
		}
		//
		return account;
	}
	
	public MAccount getP_Expense_Acct(int productId, MAcctSchema acctSchema)
	{
		ProductCost productCost = new ProductCost(getCtx(), productId, 0, getTrxName());
		return productCost.getAccount(ProductCost.ACCTTYPE_P_Expense, acctSchema);
	}
	
	
	private MAccount getProjectAcct(I_C_Project project, MAcctSchema acctSchema)
	{
		String acctName = X_C_Project_Acct.COLUMNNAME_PJ_WIP_Acct;
		String sql = "SELECT "+acctName
					+ " FROM "+I_C_Project_Acct.Table_Name
					+ " WHERE "+I_C_Project_Acct.COLUMNNAME_C_Project_ID+"=?"
						+" AND "+I_C_Project_Acct.COLUMNNAME_C_AcctSchema_ID+"=?"
						;
		int accountId = DB.getSQLValueEx(getTrxName(), sql, project.getC_Project_ID(), acctSchema.get_ID());
		return MAccount.getValidCombination(getCtx(), accountId , getTrxName());
	}

	private MAccount getA_Asset_Acct()
	{
		MAssetAddition assetAddition = getAssetAddition();
		int accountId = MAssetAcct
				.forA_Asset_ID(getCtx(), assetAddition.getA_Asset_ID(), assetAddition.getPostingType(), assetAddition.getDateAcct(), getTrxName())
				.getA_Asset_Acct();
		return MAccount.getValidCombination(getCtx(), accountId , getTrxName());
	}

	public int getInvoicePartner_ID()
	{
		MAssetAddition assetAddition = getAssetAddition();
		if (MAssetAddition.A_SOURCETYPE_Invoice.equals(assetAddition.getA_SourceType())
				&& assetAddition.getC_Invoice_ID() > 0)
		{
			return assetAddition.getC_Invoice().getC_BPartner_ID();
		}
		else
		{
			return 0;
		}
	}
	public int getInvoiceProject_ID()
	{
		MAssetAddition assetAddition = getAssetAddition();
		if (MAssetAddition.A_SOURCETYPE_Invoice.equals(assetAddition.getA_SourceType())
				&& assetAddition.getC_Invoice_ID() > 0)
		{
			return assetAddition.getC_InvoiceLine().getC_Project_ID();
		}
		else
		{
			return 0;
		}
	}		
}
