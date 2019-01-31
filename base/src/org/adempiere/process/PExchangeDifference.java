package org.openup.core.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by nicolas on 02/10/18.
 */
public class PExchangeDifference extends SvrProcess {

    /**	Mandatory Acct Schema			*/
    private int				p_C_AcctSchema_ID = 0;
    /** Mandatory Conversion Type		*/
    private int				p_C_ConversionTypeReval_ID = 0;
    /** Revaluation Date				*/
    /** Optional Invoice Currency		*/
    private int				p_C_Currency_ID = 0;
    /** Revaluation Date				*/
    private Timestamp p_DateReval = null;
    /** GL Document Type				*/
    private int				p_C_DocTypeReval_ID = 0;
    private int				p_AD_Org_ID = 0;


    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++)
        {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null)
                ;
            else if (name.equals("C_AcctSchema_ID"))
                p_C_AcctSchema_ID = para[i].getParameterAsInt();
            else if (name.equals("C_ConversionTypeReval_ID"))
                p_C_ConversionTypeReval_ID = para[i].getParameterAsInt();
            else if (name.equals("DateReval"))
                p_DateReval = (Timestamp)para[i].getParameter();
            else if (name.equals("C_DocTypeReval_ID"))
                p_C_DocTypeReval_ID = para[i].getParameterAsInt();
            else if (name.equals("C_Currency_ID"))
                p_C_Currency_ID = para[i].getParameterAsInt();
            else if (name.equals("AD_Org_ID"))
                p_AD_Org_ID = para[i].getParameterAsInt();

            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
        }

    }

    @Override
    protected String doIt() throws Exception {

        log.info("C_AcctSchema_ID=" + p_C_AcctSchema_ID
                + ",C_ConversionTypeReval_ID=" + p_C_ConversionTypeReval_ID
                + ",DateReval=" + p_DateReval
                + ",C_Currency_ID=" + p_C_Currency_ID
                + ", C_DocType_ID=" + p_C_DocTypeReval_ID);

        //	Parameter
        if (p_DateReval == null)
            p_DateReval = new Timestamp(System.currentTimeMillis());

        //	Delete - just to be sure
        String sql = "DELETE T_InvoiceGL WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
        int no = DB.executeUpdate(sql, get_TrxName());
        if (no > 0)
            log.info("Deleted #" + no);

        //	Insert Trx
        String dateStr = DB.TO_DATE(p_DateReval, true);
        sql = "INSERT INTO T_InvoiceGL (AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated,UpdatedBy, AD_PInstance_ID,"
                + " Fact_Acct_ID, AmtSourceBalance, AmtAcctBalance,  AmtRevalDr, AmtRevalCr, C_DocTypeReval_ID, DateReval, C_ConversionTypeReval_ID,"
                + "AmtRevalDrDiff, AmtRevalCrDiff, account_id) "
                //	--
                + " SELECT fa.AD_Client_ID, fa.AD_Org_ID, fa.IsActive, fa.Created, fa.CreatedBy, fa.Updated, fa.UpdatedBy, " + getAD_PInstance_ID() + ", fa.Fact_Acct_ID,"
                + " fa.AmtSourceDr-fa.AmtSourceCr, fa.AmtAcctDr-fa.AmtAcctCr,"
                + " currencyConvert(fa.AmtSourceDr, fa.C_Currency_ID, a.C_Currency_ID, " + dateStr + ", " + p_C_ConversionTypeReval_ID + ", fa.AD_Client_ID, fa.AD_Org_ID),"
                + " currencyConvert(fa.AmtSourceCr, fa.C_Currency_ID, a.C_Currency_ID, " + dateStr + ", " + p_C_ConversionTypeReval_ID + ", fa.AD_Client_ID, fa.AD_Org_ID),"
                + (p_C_DocTypeReval_ID==0 ? "NULL" : String.valueOf(p_C_DocTypeReval_ID)) + "," + dateStr + ", " + p_C_ConversionTypeReval_ID + ", 0, 0, fa.account_id"
                + " FROM Fact_Acct fa"
                + " INNER JOIN C_AcctSchema a ON (fa.C_AcctSchema_ID=a.C_AcctSchema_ID)"
                + " WHERE EXISTS (SELECT * FROM C_ElementValue ev WHERE ev.C_ElementValue_ID=fa.Account_ID AND ev.IsForeignCurrency = 'Y'"
                + " AND (ev.AccountType='A' OR ev.AccountType='L' OR ev.AccountType='O'))"
                + " AND fa.C_AcctSchema_ID = " + p_C_AcctSchema_ID + " AND fa.C_Currency_ID <> a.C_Currency_ID AND fa.C_Currency_ID = " + p_C_Currency_ID
                + " AND fa.DateAcct <= " + dateStr + " AND fa.ad_org_id = " + p_AD_Org_ID;

        no = DB.executeUpdate(sql, get_TrxName());
        if (no != 0)
            log.info("Inserted #" + no);
        else if (CLogMgt.isLevelFiner())
            log.warning("Inserted #" + no + " - " + sql);
        else
            log.warning("Inserted #" + no);

        //	Calculate Difference
        sql = "UPDATE T_InvoiceGL gl "
                + "SET (AmtRevalDrDiff,AmtRevalCrDiff)="
                + "(SELECT gl.AmtRevalDr-fa.AmtAcctDr, gl.AmtRevalCr-fa.AmtAcctCr "
                + "FROM Fact_Acct fa "
                + "WHERE gl.Fact_Acct_ID=fa.Fact_Acct_ID) "
                + "WHERE AD_PInstance_ID=" + getAD_PInstance_ID();
        int noT = DB.executeUpdate(sql, get_TrxName());
        if (noT > 0)
            log.config("Difference #" + noT);

        //	Create Document
        String info = createGLJournal();

        return "#" + noT + info;
    }

    /**
     * 	Create GL Journal
     * 	@return document info
     */
    private String createGLJournal()
    {
        final String whereClause = "AD_PInstance_ID=?";
        List<X_T_InvoiceGL> list = new Query(getCtx(), X_T_InvoiceGL.Table_Name, whereClause, get_TrxName())
                .setParameters(getAD_PInstance_ID())
                .setOrderBy("AD_Org_ID")
                .list();

        if (list.size() == 0)
            return " - No Records found";

        //
        MAcctSchema as = MAcctSchema.get(getCtx(), p_C_AcctSchema_ID);
        MAcctSchemaDefault asDefaultAccts = MAcctSchemaDefault.get(getCtx(), p_C_AcctSchema_ID);
        MGLCategory cat = MGLCategory.getDefaultSystem(getCtx());
        if (cat == null)
        {
            MDocType docType = MDocType.get(getCtx(), p_C_DocTypeReval_ID);
            cat = MGLCategory.get(getCtx(), docType.getGL_Category_ID());
        }
        //
        MJournalBatch batch = new MJournalBatch(getCtx(), 0, get_TrxName());
        batch.setDescription (getName());
        batch.setC_DocType_ID(p_C_DocTypeReval_ID);
        batch.setDateDoc(new Timestamp(System.currentTimeMillis()));
        batch.setDateAcct(p_DateReval);
        batch.setC_Currency_ID(as.getC_Currency_ID());
        batch.setIsYearEndClosing(true);
        if (!batch.save())
            return " - Could not create Batch";
        //
        MJournal journal = null;
        BigDecimal drTotal = Env.ZERO;
        BigDecimal crTotal = Env.ZERO;
        int AD_Org_ID = 0;
        int i = 0;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try{

            String sql = "select account_id, sum(amtrevaldrdiff) as dr, sum(amtrevalcrdiff) as cr" +
                            " from t_invoicegl" +
                            " where ad_pinstance_id = " + this.getAD_PInstance_ID() +
                            " and (amtrevaldrdiff <> 0 and amtrevalcrdiff = 0 or amtrevaldrdiff = 0 and amtrevalcrdiff <> 0)" +
                            " group by account_id";

            pstmt = DB.prepareStatement (sql, get_TrxName());
            rs = pstmt.executeQuery ();

            while(rs.next()){

                if (journal == null)
                {
                    journal = new MJournal (batch);
                    journal.setC_AcctSchema_ID (as.getC_AcctSchema_ID());
                    journal.setC_Currency_ID(as.getC_Currency_ID());
                    journal.setC_ConversionType_ID(p_C_ConversionTypeReval_ID);
                    MOrg org = MOrg.get(getCtx(), Env.getAD_Org_ID(getCtx()));
                    journal.setDescription (getName() + " - " + org.getName());
                    journal.setGL_Category_ID (cat.getGL_Category_ID());
                    if (!journal.save())
                        return " - Could not create Journal";
                }

                int accountID = rs.getInt("Account_ID");

                MAccount account = new MAccount(getCtx(), accountID, get_TrxName());

                MJournalLine line = new MJournalLine(journal);
                line.setLine((i+1) * 10);
                line.setDescription("Diferencia de cambio");
                line.setAccount_ID(accountID);
                //line.setC_ValidCombination_ID(MAccount.get(fa));
                BigDecimal dr = rs.getBigDecimal("dr");
                BigDecimal cr = rs.getBigDecimal("cr");
                drTotal = drTotal.add(dr);
                crTotal = crTotal.add(cr);

                BigDecimal diff = Env.ZERO;
                boolean ok = true;

                if(dr.compareTo(cr) > 0){

                    diff = dr.subtract(cr);
                    line.setAmtSourceDr (diff);
                    line.setAmtAcctDr (diff);
                    line.setAmtSourceCr (Env.ZERO);
                    line.setAmtAcctCr (Env.ZERO);

                } else if(cr.compareTo(dr) > 0){

                    diff = cr.subtract(dr);
                    line.setAmtSourceDr (Env.ZERO);
                    line.setAmtAcctDr (Env.ZERO);
                    line.setAmtSourceCr (diff);
                    line.setAmtAcctCr (diff);

                } else if(dr.compareTo(cr) ==0)
                    ok = false;

                if(ok) line.saveEx();

                createBalancing (asDefaultAccts, journal, dr, cr,  AD_Org_ID, (i+1) * 10);

            }

            return " - " + batch.getDocumentNo() + " #" + list.size();

        } catch (Exception ex){
            throw new AdempiereException(ex.getMessage());
        }finally {
            DB.close(rs, pstmt);
            rs = null; pstmt = null;
        }

    }	//	createGLJournal

    /**
     * 	Create Balancing Entry
     *	@param acctSchemaDefault acct schema default accounts
     *	@param journal journal
     *	@param debitTotal dr
     *	@param creditTotal cr
     *	@param orgId org
     *	@param lineNo base line no
     */
    private void createBalancing (MAcctSchemaDefault acctSchemaDefault, MJournal journal,
                                  BigDecimal dr, BigDecimal cr, int orgId, int lineNo)
    {
        if (journal == null)
            throw new IllegalArgumentException("Jornal is null");

        BigDecimal diff = Env.ZERO;

        //		CR Entry = Gain
        if (dr.compareTo(cr) > 0)
        {
            MJournalLine line = new MJournalLine(journal);
            line.setLine(lineNo+1);
            MAccount base = MAccount.getValidCombination(getCtx(), acctSchemaDefault.getUnrealizedGain_Acct(), get_TrxName());
            MAccount acct = MAccount.get(getCtx(), acctSchemaDefault.getAD_Client_ID(), orgId,
                    acctSchemaDefault.getC_AcctSchema_ID(), base.getAccount_ID(), base.getC_SubAcct_ID(),
                    base.getM_Product_ID(), base.getC_BPartner_ID(), base.getAD_OrgTrx_ID(),
                    base.getC_LocFrom_ID(), base.getC_LocTo_ID(), base.getC_SalesRegion_ID(),
                    base.getC_Project_ID(), base.getC_Campaign_ID(), base.getC_Activity_ID(),
                    base.getUser1_ID(), base.getUser2_ID() , base.getUser3_ID(), base.getUser4_ID(),
                    base.getUserElement1_ID(), base.getUserElement2_ID(), get_TrxName());
            line.setDescription(Msg.getElement(getCtx(), "UnrealizedGain_Acct"));
            line.setC_ValidCombination_ID(acct.getC_ValidCombination_ID());
            diff = dr.subtract(cr);
            line.setAmtSourceCr (diff);
            line.setAmtAcctCr (diff);
            line.saveEx();
        }
        //	DR Entry = Loss
        if (cr.compareTo(dr) > 0)
        {
            MJournalLine line = new MJournalLine(journal);
            line.setLine(lineNo+2);
            MAccount base = MAccount.getValidCombination(getCtx(), acctSchemaDefault.getUnrealizedLoss_Acct(), get_TrxName());
            MAccount acct = MAccount.get(getCtx(), acctSchemaDefault.getAD_Client_ID(), orgId,
                    acctSchemaDefault.getC_AcctSchema_ID(), base.getAccount_ID(), base.getC_SubAcct_ID(),
                    base.getM_Product_ID(), base.getC_BPartner_ID(), base.getAD_OrgTrx_ID(),
                    base.getC_LocFrom_ID(), base.getC_LocTo_ID(), base.getC_SalesRegion_ID(),
                    base.getC_Project_ID(), base.getC_Campaign_ID(), base.getC_Activity_ID(),
                    base.getUser1_ID(), base.getUser2_ID() , base.getUser3_ID(), base.getUser4_ID(),
                    base.getUserElement1_ID(), base.getUserElement2_ID(), get_TrxName());
            line.setDescription(Msg.getElement(getCtx(), "UnrealizedLoss_Acct"));
            line.setC_ValidCombination_ID(acct.getC_ValidCombination_ID());
            diff = cr.subtract(dr);
            line.setAmtSourceDr (diff);
            line.setAmtAcctDr (diff);
            line.saveEx();
        }
    }	//	createBalancing
}