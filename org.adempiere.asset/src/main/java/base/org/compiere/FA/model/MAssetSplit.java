/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.FA.model;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.FA.exceptions.AssetAlreadyDepreciatedException;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;


/**
 * Asset Split Document
 */
public class MAssetSplit extends X_A_Asset_Split implements DocAction {

    /**
     *
     */
    private static final long serialVersionUID = 20180418L;

    /**
     * Standard Constructor
     */
    public MAssetSplit(Properties ctx, int A_Asset_Split_ID, String trxName) {
        super(ctx, A_Asset_Split_ID, trxName);
    }

    /**
     * Load Constructor
     */
    public MAssetSplit(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }


    protected boolean beforeSave(boolean newRecord) {

        if (newRecord) {
            getAssetBalance().ifPresent(assetBalance -> {
                setA_Depreciation_Workfile_ID(assetBalance.getA_Depreciation_Workfile_ID());
                setA_Asset_Cost(assetBalance.getA_Asset_Cost());
                setA_Percent_Original(BigDecimal.valueOf(100));
                setA_QTY_Current(assetBalance.getA_QTY_Current());
                if (getA_QTY_Split().signum() <= 0)
                    setA_QTY_Split(assetBalance.getA_QTY_Current());
            });
        }
        return true;
    }

    protected boolean afterSave(boolean newRecord, boolean success) {
        return true;
    }

    protected boolean beforeDelete() {
        return true;
    }

    /**
     * Get Document Info
     *
     * @return document info (untranslated)
     */
    public String getDocumentInfo() {
        MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
        return docType.getName() + " " + getDocumentNo();
    }    //	getDocumentInfo

    /**
     * Create PDF
     *
     * @return File or null
     */
    public File createPDF() {
        try {
            File temp = File.createTempFile(get_TableName() + get_ID() + "_", ".pdf");
            return createPDF(temp);
        } catch (Exception e) {
            log.severe("Could not create PDF - " + e.getMessage());
        }
        return null;
    }    //	getPDF

    /**
     * Create PDF file
     *
     * @param file output file
     * @return file if success
     */
    public File createPDF(File file) {
        //	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
        //	if (re == null)
        return null;
        //	return re.getPDF(file);
    }    //	createPDF

    /**************************************************************************
     * 	Process document
     *    @param processAction document action
     *	@return true if performed
     */
    public boolean processIt(String processAction) {
        processMsg = null;
        DocumentEngine engine = new DocumentEngine(this, getDocStatus());
        return engine.processIt(processAction, getDocAction());
    }    //	processIt

    /**
     * Process Message
     */
    private String processMsg = null;
    /**
     * Just Prepared Flag
     */
    private boolean justPrepared = false;

    Optional<MDepreciationWorkfile> optionalDepreciationWorkfile = Optional.empty();

    Optional<MAsset> optionalFixedAssetToBeSplit = Optional.empty();

    /**
     * Unlock Document.
     *
     * @return true if success
     */
    public boolean unlockIt() {
        log.info("unlockIt - " + toString());
        //	setProcessing(false);
        return true;
    }    //	unlockIt

    /**
     * Invalidate Document
     *
     * @return true if success
     */
    public boolean invalidateIt() {
        log.info("invalidateIt - " + toString());
        setDocAction(DOCACTION_Prepare);
        return true;
    }    //	invalidateIt

    /**
     * Prepare Document
     *
     * @return new status (In Progress or Invalid)
     */
    public String prepareIt() {
        log.info(toString());
        processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
        if (processMsg != null)
            return DocAction.STATUS_Invalid;

        MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());

        //	Std Period open?
        if (!MPeriod.isOpen(getCtx(), getDateDoc(), docType.getDocBaseType(), getAD_Org_ID())) {
            processMsg = "@PeriodClosed@";
            return DocAction.STATUS_Invalid;
        }

        // Check that the FA is not just depreciated
        MDepreciationWorkfile assetBalance = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
        if (assetBalance.isDepreciated(getDateAcct()))
        {
            throw new AssetAlreadyDepreciatedException();
        }

        MDepreciationExp.checkExistsNotProcessedEntries(getCtx(), getA_Asset_ID(), getDateAcct(), getPostingType(), get_TrxName());

        //	Add up Amounts
        processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
        if (processMsg != null)
            return DocAction.STATUS_Invalid;
        justPrepared = true;
        if (!DOCACTION_Complete.equals(getDocAction()))
            setDocAction(DOCACTION_Complete);
        return DocAction.STATUS_InProgress;
    }    //	prepareIt

    /**
     * Approve Document
     *
     * @return true if success
     */
    public boolean approveIt() {
        log.info("approveIt - " + toString());
        setIsApproved(true);
        return true;
    }    //	approveIt

    /**
     * Reject Approval
     *
     * @return true if success
     */
    public boolean rejectIt() {
        log.info("rejectIt - " + toString());
        setIsApproved(false);
        return true;
    }    //	rejectIt

    /**
     * Complete Document
     *
     * @return new status (Complete, In Progress, Invalid, Waiting ..)
     */
    public String completeIt() {
        //	Re-Check
        if (!justPrepared) {
            String status = prepareIt();
            if (!DocAction.STATUS_InProgress.equals(status))
                return status;
        }

        processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
        if (processMsg != null)
            return DocAction.STATUS_Invalid;

        //	Implicit Approval
        if (!isApproved())
            approveIt();
        log.info(toString());

        createSplit();

        //	User Validation
        String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
        if (valid != null) {
            processMsg = valid;
            return DocAction.STATUS_Invalid;
        }
        //	Set Definitive Document No
        setDefiniteDocumentNo();

        setProcessed(true);
        setDocAction(DOCACTION_Close);
        return DocAction.STATUS_Completed;
    }    //	completeIt

    /**
     * Set the definite document number after completed
     */
    private void setDefiniteDocumentNo() {
        MDocType docType = MDocType.get(getCtx(), getC_DocType_ID());
        if (docType.isOverwriteDateOnComplete()) {
            setDateDoc(new Timestamp(System.currentTimeMillis()));
        }
        if (docType.isOverwriteSeqOnComplete()) {
            String value = null;
            int index = p_info.getColumnIndex("C_DocType_ID");
            if (index == -1)
                index = p_info.getColumnIndex("C_DocTypeTarget_ID");
            if (index != -1)        //	get based on Doc Type (might return null)
                value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
            if (value != null) {
                setDocumentNo(value);
            }
        }
    }

    /**
     * Void Document.
     * Same as Close.
     *
     * @return true if success
     */
    public boolean voidIt() {
        log.info("voidIt - " + toString());
        return closeIt();
    }    //	voidIt

    /**
     * Close Document.
     * Cancel not delivered Qunatities
     *
     * @return true if success
     */
    public boolean closeIt() {
        log.info("closeIt - " + toString());

        //	Close Not delivered Qty
        setDocAction(DOCACTION_None);
        return true;
    }    //	closeIt

    /**
     * Reverse Correction
     *
     * @return true if success
     */
    public boolean reverseCorrectIt() {
        log.info("reverseCorrectIt - " + toString());
        return false;
    }    //	reverseCorrectionIt

    /**
     * Reverse Accrual - none
     *
     * @return true if success
     */
    public boolean reverseAccrualIt() {
        log.info("reverseAccrualIt - " + toString());
        return false;
    }    //	reverseAccrualIt

    /**
     * Re-activate
     *
     * @return true if success
     */
    public boolean reActivateIt() {
        log.info("reActivateIt - " + toString());
        setProcessed(false);
        if (reverseCorrectIt())
            return true;
        return false;
    }    //	reActivateIt


    /*************************************************************************
     * 	Get Summary
     *    @return Summary of Document
     */
    public String getSummary() {
        StringBuffer sb = new StringBuffer();
        sb.append(getDocumentNo());
        //	sb.append(": ")
        //		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
        //		.append(" (#").append(getLines(false).length).append(")");
        //	 - Description
        if (getDescription() != null && getDescription().length() > 0)
            sb.append(" - ").append(getDescription());
        return sb.toString();
    }    //	getSummary

    /**
     * Get Process Message
     *
     * @return clear text error message
     */
    public String getProcessMsg() {
        return processMsg;
    }    //	getProcessMsg

    /**
     * Get Document Owner (Responsible)
     *
     * @return AD_User_ID
     */
    public int getDoc_User_ID() {
        //	return getSalesRep_ID();
        return 0;
    }    //	getDoc_User_ID

    /**
     * Get Document Approval Amount
     *
     * @return amount
     */
    public BigDecimal getApprovalAmt() {
        return null;    //getTotalLines();
    }    //	getApprovalAmt

    /**
     * Get Document Currency
     *
     * @return C_Currency_ID
     */
    public int getC_Currency_ID() {
        //	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
        //	return pl.getC_Currency_ID();
        return 0;
    }    //	getC_Currency_ID

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("MAssetSplit[")
                .append(getSummary()).append("]");
        return sb.toString();
    }

    /**
     * CreateSplit
     * @return
     */
    private List<MAsset> createSplit() {
        List<MAsset> assetsSplit = new ArrayList<>();
        if (!isProcessed()) {
            getAssetBalance().ifPresent(assetBalance -> {
                MAsset fixedAssetToBeSplit = getAssetToBeSplit();
                BigDecimal acquisitionCost = BigDecimal.ZERO;
                BigDecimal accumulatedCost = BigDecimal.ZERO;
                BigDecimal remainingCost = BigDecimal.ZERO;
                if (MAssetSplit.A_SPLIT_TYPE_Quantity.equals(getA_Split_Type())) {
                    if (getA_QTY_Split().signum() <= 0)
                        throw new AdempiereException("@A_QTY_Split@ @NotFound@");

                    acquisitionCost = assetBalance.getA_Asset_Cost().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                    accumulatedCost = assetBalance.getA_Accumulated_Depr().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                    remainingCost = assetBalance.getA_Asset_Remaining().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                    for (int sequence = 1; sequence < getA_QTY_Split().intValue() + 1; sequence++) {
                        MAsset fixedAssetSplit = createAssetSplit(
                                fixedAssetToBeSplit,
                                assetBalance,
                                acquisitionCost,
                                accumulatedCost,
                                remainingCost, sequence);
                        assetsSplit.add(fixedAssetSplit);
                    }
                }

                if (MAssetSplit.A_SPLIT_TYPE_Amount.equals(getA_Split_Type())) {
                    BigDecimal percentage = getA_Amount_Split().divide(getA_Asset_Cost(), 7, RoundingMode.HALF_UP);
                    acquisitionCost = assetBalance.getA_Asset_Cost().subtract(getA_Amount_Split());
                    accumulatedCost = assetBalance.getA_Accumulated_Depr().multiply(percentage);
                    remainingCost = assetBalance.getA_Asset_Remaining().multiply(percentage);
                    MAsset fixedAssetSplitFirst = createAssetSplit(
                            fixedAssetToBeSplit,
                            assetBalance,
                            assetBalance.getActualCost().subtract(acquisitionCost),
                            assetBalance.getA_Accumulated_Depr().subtract(accumulatedCost),
                            assetBalance.getA_Asset_Remaining().subtract(remainingCost), 1);
                    assetsSplit.add(fixedAssetSplitFirst);
                    MAsset assetSplitSecond = createAssetSplit(
                            fixedAssetToBeSplit,
                            assetBalance,
                            acquisitionCost,
                            accumulatedCost,
                            remainingCost, 2);
                    assetsSplit.add(assetSplitSecond);
                }

                if (MAssetSplit.A_SPLIT_TYPE_Percentage.equals(getA_Split_Type())) {
                    BigDecimal percentage = getA_Percent_Split().divide(BigDecimal.valueOf(100));
                    acquisitionCost = assetBalance.getA_Asset_Cost().multiply(percentage);
                    accumulatedCost = assetBalance.getA_Accumulated_Depr().multiply(percentage);
                    remainingCost = assetBalance.getA_Asset_Remaining().multiply(percentage);
                    MAsset assetSplitFirst = createAssetSplit(
                            fixedAssetToBeSplit,
                            assetBalance,
                            assetBalance.getActualCost().subtract(acquisitionCost),
                            assetBalance.getA_Accumulated_Depr().subtract(accumulatedCost),
                            assetBalance.getA_Asset_Remaining().subtract(remainingCost), 1);
                    assetsSplit.add(assetSplitFirst);
                    MAsset fixedAssetSplitSecond = createAssetSplit(
                            fixedAssetToBeSplit,
                            assetBalance,
                            acquisitionCost,
                            accumulatedCost,
                            remainingCost, 2);
                    assetsSplit.add(fixedAssetSplitSecond);
                }
               split(fixedAssetToBeSplit);
            });
        }
        if (assetsSplit.size() == 0)
            throw new AdempiereException("@A_Asset_Split_ID@ @ProcessRunError@");
        return assetsSplit;
    }

    /**
     * Split Asset
     * @param asset
     */
    private void split (MAsset asset)
    {
        asset.changeStatus(MAsset.A_ASSET_STATUS_Preservation, getDateDoc());
        //Update Product Balance
        MAssetProduct assetProduct = MAssetProduct.getCreate(getCtx(),
                getA_Asset_ID(), asset.getM_Product_ID(),
                asset.getM_AttributeSetInstance_ID(), get_TrxName());
        assetProduct.setA_QTY_Current(BigDecimal.ZERO);
        assetProduct.setAD_Org_ID(asset.getAD_Org_ID());
        assetProduct.saveEx();
        assetProduct.updateAsset(asset);

        asset.setA_QTY_Current(BigDecimal.ZERO);
        asset.setQty(BigDecimal.ZERO);
        asset.saveEx();

        MDepreciationWorkfile assetBalance = MDepreciationWorkfile.get(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
        assetBalance.setA_Asset_Cost(BigDecimal.ZERO);
        assetBalance.setA_Salvage_Value(BigDecimal.ZERO);
        assetBalance.setA_Accumulated_Depr(BigDecimal.ZERO);
        assetBalance.setA_Asset_Remaining(BigDecimal.ZERO);
        assetBalance.setA_Accumulated_Depr_F(BigDecimal.ZERO);
        assetBalance.setA_Asset_Remaining_F(BigDecimal.ZERO);
        assetBalance.setIsDepreciated(false);
        assetBalance.saveEx();

        // Delete not processed expense entries
        MDepreciationExp.getNotProcessedEntries(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName())
                .forEach(depreciation -> depreciation.deleteEx(false));

        //Update Asset History
        MAssetChange.createSplit(this, assetBalance);
    }

    /**
     * Create Asset Split
     * @param assetSource
     * @param assetBalanceSource
     * @param acquisitionCost
     * @param accumulatedCost
     * @param remainingCost
     * @param sequence
     * @return
     */
    private MAsset createAssetSplit(
            MAsset assetSource,
            MDepreciationWorkfile assetBalanceSource,
            BigDecimal acquisitionCost,
            BigDecimal accumulatedCost,
            BigDecimal remainingCost,
            Integer sequence) {
        String sufixValue = null;
        if (sequence < 10)
            sufixValue = "00" + sequence.toString();
        else if (sequence < 100)
            sufixValue = "0" + sequence.toString();
        else if (sequence < 1000)
            sufixValue = sequence.toString();

        MAsset assetTarget = new MAsset(getCtx(), 0, get_TrxName());
        PO.copyValues(assetSource, assetTarget);
        assetTarget.setValue(assetSource.getValue() + sufixValue);
        assetTarget.setName(assetSource.getName() + "-" + assetSource.getInventoryNo());
        assetTarget.setInventoryNo(assetSource.getInventoryNo() + sufixValue);
        assetTarget.setA_Asset_Split_ID(getA_Asset_Split_ID());
        assetTarget.setA_Parent_Asset_ID(assetSource.getA_Asset_ID());
        assetTarget.setA_Asset_Status(assetSource.getA_Asset_Status());
        assetTarget.saveEx();

        MAssetProduct.getAssetProduct(assetSource).ifPresent(assetProductSource -> {
            MAssetProduct assetProductTarget = new MAssetProduct(getCtx(), 0, get_TrxName());
            PO.copyValues(assetProductSource, assetProductTarget);
            assetProductSource.setA_Asset_ID(assetTarget.getA_Asset_ID());
            assetProductSource.setA_QTY_Current(BigDecimal.ONE);
            assetProductSource.saveEx();
        });

        MDepreciationWorkfile assetBalanceTarget = Optional
                .ofNullable(MDepreciationWorkfile.get(getCtx(), assetTarget.getA_Asset_ID(), getPostingType(), assetTarget.get_TrxName()))
                .orElseGet(() -> new MDepreciationWorkfile(getCtx(), 0, get_TrxName()));

        PO.copyValues(assetBalanceSource, assetBalanceTarget);
        assetBalanceTarget.setA_Asset_ID(assetTarget.getA_Asset_ID());
        assetBalanceTarget.setA_Asset_Cost(acquisitionCost);
        assetBalanceTarget.setA_Accumulated_Depr(accumulatedCost);
        assetBalanceTarget.setA_Accumulated_Depr_F(accumulatedCost);
        assetBalanceTarget.setA_Asset_Remaining(remainingCost);
        assetBalanceTarget.setA_Asset_Remaining_F(remainingCost);
        assetBalanceTarget.saveEx();

        createDepreciation(assetTarget, acquisitionCost);
        MAssetChange.createAddition(this, assetSource , assetTarget , assetBalanceTarget);
        return assetTarget;
    }


    /**
     * Create Depreciation
     * @param asset
     * @param acquisitionCost
     */
    private void createDepreciation(MAsset asset, BigDecimal acquisitionCost) {
        List<MDepreciationExp> depreciationList = MDepreciationExp.getEntries(getCtx(), getA_Asset_ID(), getPostingType(), get_TrxName());
        depreciationList.stream()
                .filter(depreciation -> !depreciation.isProcessed())
                .forEach(depreciation -> {
                    MDepreciationExp depreciationTarge = new MDepreciationExp(getCtx(), 0, get_TrxName());
                    BigDecimal accumulated = BigDecimal.ZERO;
                    BigDecimal accumulatedDelta = BigDecimal.ZERO;
                    BigDecimal remaining =  BigDecimal.ZERO;
                    BigDecimal expense =  BigDecimal.ZERO;
                    BigDecimal accumulatedFiscal = BigDecimal.ZERO;
                    BigDecimal accumulatedDeltaFiscal = BigDecimal.ZERO;
                    BigDecimal remainingFiscal =  BigDecimal.ZERO;
                    BigDecimal expenseFiscal =  BigDecimal.ZERO;

                    if (MAssetSplit.A_SPLIT_TYPE_Quantity.equals(getA_Split_Type())) {

                        accumulated = depreciation.getA_Accumulated_Depr().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                        accumulatedDelta = depreciation.getA_Accumulated_Depr_Delta().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                        remaining = depreciation.getA_Asset_Remaining().divide(getA_QTY_Split(),  7, RoundingMode.HALF_UP);
                        expense = depreciation.getExpense().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);

                        accumulatedFiscal = depreciation.getA_Accumulated_Depr_F().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                        accumulatedDeltaFiscal = depreciation.getA_Accumulated_Depr_F_Delta().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                        remainingFiscal = depreciation.getA_Asset_Remaining_F().divide(getA_QTY_Split(), 7, RoundingMode.HALF_UP);
                        expenseFiscal = depreciation.getExpense_F().divide(getA_QTY_Split());
                    }

                    if (MAssetSplit.A_SPLIT_TYPE_Amount.equals(getA_Split_Type())) {
                        BigDecimal proportion = acquisitionCost.divide(depreciation.getA_Asset_Cost(), 7, RoundingMode.HALF_UP);
                        BigDecimal percentage = getA_Amount_Split().divide(getA_Asset_Cost());

                        accumulated = depreciation.getA_Accumulated_Depr().multiply(proportion).multiply(percentage);
                        accumulatedDelta = depreciation.getA_Accumulated_Depr_Delta().multiply(proportion).multiply(percentage);
                        remaining = depreciation.getA_Asset_Remaining().multiply(proportion).multiply(percentage);
                        expense = depreciation.getExpense().multiply(proportion).multiply(percentage);

                        accumulatedFiscal = depreciation.getA_Accumulated_Depr_F().multiply(proportion).multiply(percentage);
                        accumulatedDeltaFiscal = depreciation.getA_Accumulated_Depr_F_Delta().multiply(proportion).multiply(percentage);
                        remainingFiscal = depreciation.getA_Asset_Remaining_F().multiply(proportion).multiply(percentage);
                        expenseFiscal = depreciation.getExpense_F().multiply(proportion).multiply(percentage);
                    }

                    if (MAssetSplit.A_SPLIT_TYPE_Percentage.equals(getA_Split_Type())) {
                        BigDecimal proportion = acquisitionCost.divide(depreciation.getA_Asset_Cost(), 7, RoundingMode.HALF_UP);
                        BigDecimal percentage = getA_Percent_Split().divide(BigDecimal.valueOf(100));

                        accumulated = depreciation.getA_Accumulated_Depr().multiply(proportion).multiply(percentage);
                        accumulatedDelta = depreciation.getA_Accumulated_Depr_Delta().multiply(proportion).multiply(percentage);
                        remaining = depreciation.getA_Asset_Remaining().multiply(proportion).multiply(percentage);
                        expense = depreciation.getExpense().multiply(proportion).multiply(percentage);

                        accumulatedFiscal = depreciation.getA_Accumulated_Depr_F().multiply(proportion).multiply(percentage);
                        accumulatedDeltaFiscal = depreciation.getA_Accumulated_Depr_F_Delta().multiply(proportion).multiply(percentage);
                        remainingFiscal = depreciation.getA_Asset_Remaining_F().multiply(proportion).multiply(percentage);
                        expenseFiscal = depreciation.getExpense_F().multiply(proportion).multiply(percentage);
                    }
                    PO.copyValues(depreciation, depreciationTarge);
                    String help = "" + accumulated.subtract(expense) + "|" + accumulatedFiscal.subtract(expenseFiscal)
                            + " + " + expense + "|" + expenseFiscal + " = "
                            + "" + accumulated + "|" + accumulatedFiscal;
                    depreciationTarge.setHelp(help);
                    depreciationTarge.setA_Asset_ID(asset.getA_Asset_ID());
                    depreciationTarge.setA_Asset_Cost(acquisitionCost);

                    depreciationTarge.setA_Accumulated_Depr(accumulated);
                    depreciationTarge.setA_Accumulated_Depr_Delta(accumulatedDelta);
                    depreciationTarge.setA_Asset_Remaining(remaining);
                    depreciationTarge.setExpense(expense);

                    depreciationTarge.setA_Accumulated_Depr_F(accumulatedFiscal);
                    depreciationTarge.setA_Accumulated_Depr_F_Delta(accumulatedDeltaFiscal);
                    depreciationTarge.setA_Asset_Remaining_F(remainingFiscal);
                    depreciationTarge.setExpense_F(expenseFiscal);

                    depreciationTarge.saveEx();
                });
    }

    /**
     * get  Asset to be Split
     * @return
     */
    private MAsset getAssetToBeSplit() {
        if (!optionalFixedAssetToBeSplit.isPresent())
            optionalFixedAssetToBeSplit = Optional.ofNullable(new MAsset(getCtx(), getA_Asset_ID(), get_TrxName()));

        return optionalFixedAssetToBeSplit.orElseThrow(() -> new AdempiereException("@A_Asset_ID@ @NotFound@"));
    }

    /**
     * Ger  Asset Balance
     * @return
     */
    public Optional<MDepreciationWorkfile> getAssetBalance() {
        if (!optionalDepreciationWorkfile.isPresent())
            optionalDepreciationWorkfile = Optional.ofNullable(MDepreciationWorkfile.get(getCtx(), getAssetToBeSplit().getA_Asset_ID(), getPostingType(), get_TrxName()));
        return optionalDepreciationWorkfile;
    }

    /**
     * get  Assets Split
     * @return
     */
    public List<MAsset> getAssetsSplit() {
        return new Query(getCtx(), MAsset.Table_Name, MAsset.COLUMNNAME_A_Asset_Split_ID + "=?", get_TrxName())
                .setClient_ID()
                .setParameters(getA_Asset_Split_ID())
                .list(MAsset.class);
    }

}