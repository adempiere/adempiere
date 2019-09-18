package com.klst.adempiere.einvoice;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.klst.einvoice.CoreInvoiceLine;
import com.klst.einvoice.PostalAddress;
import com.klst.einvoice.ubl.GenericInvoice;
import com.klst.einvoice.ubl.GenericLine;
import com.klst.einvoice.unece.uncefact.Amount;
import com.klst.einvoice.unece.uncefact.UnitPriceAmount;
import com.klst.untdid.codelist.DocumentNameCode;
import com.klst.untdid.codelist.TaxCategoryCode;

public class UblInvoice extends UblImpl {
	
	private static final Logger LOG = Logger.getLogger(UblInvoice.class.getName());

	private Object ublObject;

	@Override
	public String getDocumentNo() {
		return ublInvoice.getId();
	}

	void mapLine(MInvoiceLine invoiceLine) {
		int lineId = invoiceLine.getLine(); //Id
		BigDecimal taxRate = invoiceLine.getC_Tax().getRate();
		CoreInvoiceLine line = GenericLine.createInvoiceLine(Integer.toString(lineId)
				, this.mapping.mapToQuantity(invoiceLine.getC_UOM().getX12DE355(), invoiceLine.getQtyInvoiced())
				, new Amount(mInvoice.getCurrencyISO(), invoiceLine.getLineNetAmt())
				, new UnitPriceAmount(mInvoice.getCurrencyISO(), invoiceLine.getPriceActual())
				, invoiceLine.getProduct().getName()
				, TaxCategoryCode.StandardRate, taxRate
				);
		line.setDescription(invoiceLine.getDescription());
		ublInvoice.addLine(line);
	}

	Object mapToEModel(MInvoice adInvoice) {
		mInvoice = adInvoice;
		ublInvoice = GenericInvoice.createInvoice(DEFAULT_PROFILE, null, DocumentNameCode.CommercialInvoice);
		ublInvoice.setId(mInvoice.getDocumentNo());
		ublInvoice.setIssueDate(mInvoice.getDateInvoiced());
		ublInvoice.setDocumentCurrency(mInvoice.getC_Currency().getISO_Code());
		this.ublObject = ublInvoice.get();
		super.mapBuyerReference();

		makeOptionals();

		super.mapSellerGroup(); 
		super.mapBuyerGroup();
		
		super.mapPaymentGroup();
		super.mapDocumentTotals();
		super.mapVatBreakDownGroup();
		super.mapLineGroup();
		return ublObject;
	}

	/* optional elements:
	 * VAT accounting currency code, BT-6
	 * Value added tax point date, BT-7
	 * Value added tax point date code, BT-8
	 * Payment due date, BT-9
	 * Project reference, BT-11
	 * Contract reference, BT-12
	 * Purchase order reference, BT-13
	 * Sales order reference, BT-14
	 * Receiving advice reference, BT-15
	 * Despatch advice reference, BT-16        --------- Eine Kennung für eine referenzierte Versandanzeige. LS
	 * Tender or lot reference, BT-17
	 * Invoiced object identifier, BT-18 / + Invoiced object identifier/Scheme identifier
	 * Buyer accounting reference, BT-19
	 * Payment terms, BT-20         --------> makePaymentGroup()
	 * INVOICE NOTE, BG-1
	 * PRECEDING INVOICE REFERENCE, BG-3
	 * PAYEE, BG-10
	 * SELLER TAX REPRESENTATIVE PARTY BG-11,
	 * DELIVERY INFORMATION, BG-13                            
	 * DOCUMENT LEVEL ALLOWANCES, BG-20
	 * DOCUMENT LEVEL CHARGES, BG-21
	 * ADDITIONAL SUPPORTING DOCUMENTS, BG-24   ---------------------- MZ
	 */
	// overwrite this to set optional elements
	protected void makeOptionals() {
		// Description ==> optional INVOICE NOTE
		ublInvoice.setNote(this.mapping.mapNote(mInvoice));
		
//		String description = mInvoice.getDescription();
//		if(description!=null) {
////			"Es gelten unsere Allgem. Geschäftsbedingungen." // Bsp	
//			((Invoice)ublObject).setNote(description);
//			
//			
//			// TODO raus nur Test AdditionalSupportingDocument
//			LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>");
//			AdditionalSupportingDocument asd = new AdditionalSupportingDocument("1","AdditionalSupportingDocument description");
//			asd.setExternalDocumentLocation("a URL TODO");
//			((Invoice)ublObject).addAdditionalSupportingDocument(asd);
//
//		}
		
		//  LS -> DELIVERY : 
		final String subselect = "SELECT m.M_InOut_ID FROM M_InOut m"
				+" LEFT JOIN M_InOutline ml ON ml.M_InOut_ID = m.M_InOut_ID"
				+" LEFT JOIN c_invoiceline il ON il.M_InOutline_ID = ml.M_InOutline_ID"
				+" WHERE il.C_Invoice_ID=? AND m.MovementType IN ('"+MInOut.MOVEMENTTYPE_CustomerShipment+"')"; // 2
		final String sql = "SELECT *"
				+" FROM "+MInOut.Table_Name
				+" WHERE "+MInOut.COLUMNNAME_MovementType + "='"+MInOut.MOVEMENTTYPE_CustomerShipment+"'"
				+" AND (("+MInOut.COLUMNNAME_C_Invoice_ID + "= ? AND "+MInOut.COLUMNNAME_IsSOTrx+"='Y')"  // 1
				+       " OR "+MInOut.COLUMNNAME_M_InOut_ID + " IN("+subselect+"))"
				+" AND "+MInOut.COLUMNNAME_IsActive+"='Y'"; 
//		LOG.info("\n"+sql);
		PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
		ResultSet rs = null;
		List<MInOut> mInOutList = new ArrayList<MInOut>();
		try {
			int invoice_ID = mInvoice.getC_Invoice_ID();
			DB.setParameter(pstmt, 1, invoice_ID);
			DB.setParameter(pstmt, 2, invoice_ID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MInOut mInOut = new MInOut(Env.getCtx(), rs, get_TrxName());
				LOG.info("mInOut:"+mInOut);
				mInOutList.add(mInOut);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(mInOutList.size()==1) { // TODO mehrere Lieferungengen
			int mBP_ID = mInOutList.get(0).getC_BPartner_ID();
			if(mBP_ID==mInvoice.getC_BPartner_ID()) {
				LOG.info("!!!!!!!!!!!!!!!!!!!!!! kein Delivery! size="+mInOutList.size());
			} else {
				int mC_Location_ID = mInOutList.get(0).getC_BPartner_Location().getC_Location_ID();
				
				MBPartner mBPartner = new MBPartner(Env.getCtx(), mBP_ID, get_TrxName());
				String shipToTradeName = mBPartner.getName();
				PostalAddress address = mapLocationToAddress(mC_Location_ID, ublInvoice);
							
				ublInvoice.setDelivery(shipToTradeName, mInOutList.get(0).getMovementDate(), address, null, null);
			}
		} else {
			LOG.warning("!!!!!!!!!!!!!!!!!!!!!! size="+mInOutList.size());
		}
		LOG.info("overwrite this to set optional elements.");
	}

}
