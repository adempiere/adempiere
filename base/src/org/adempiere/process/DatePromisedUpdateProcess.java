package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Process that updates the promised date of a specific product
 * 
 * @author Daniel Tamm
 *
 */
public class DatePromisedUpdateProcess extends SvrProcess {

	private DatePromisedMsg 	m_msg;
	private MBPartner			m_bPartner;
	private MProduct			m_product;
	
	@Override
	protected void prepare() {
		if (m_msg==null) {
			m_msg = new DatePromisedMsg();
		}

		m_msg.setMsgTime(new java.util.Date(Calendar.getInstance().getTimeInMillis()));
		
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++) {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null);
            else if (name.equals("PromisedDateMsg")) {
            	m_msg = (DatePromisedMsg)para[i].getParameter();
            } else if (name.equals("C_BPartner_ID")) {
            	// Lookup business partner
            	m_bPartner = new MBPartner(getCtx(), para[i].getParameterAsInt(), get_TrxName());
            } else if (name.equals("M_Product_ID")) {
            	m_product = new MProduct(getCtx(), para[i].getParameterAsInt(), get_TrxName());
            } else if (name.equals("DatePromised")) {
            	m_msg.setPromisedDate((java.util.Date)para[i].getParameter());
            } else if (name.equals("DatePrecision")) {
            	m_msg.setPromisedDatePrecision((String)para[i].getParameter());
            } else if (name.equals("Qty")) {
            	BigDecimal qty = (BigDecimal)para[i].getParameter();
            	m_msg.setDeliveryCount(qty.doubleValue());
            } else {
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
            }
        }
	}
	
	@Override
	protected String doIt() throws Exception {
		
		return(updatePromisedDate(m_msg));
		
	}
	
	/**
	 * Updates promised date by calling a database stored procedure.
	 * 
	 * This method is public because it can be called either via normal process running or directly
	 * from another thread.
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String updatePromisedDate(DatePromisedMsg msg) throws Exception {
		StringBuffer result = new StringBuffer();

		// Find Business Partner and product. If BPartner is already set in "prepare" the bpartner in msg
		// is disregarded.
		if (m_bPartner==null) {
			m_bPartner = MBPartner.get(getCtx(), msg.getbPartnerNo());
			if (m_bPartner==null) {
				throw new AdempiereException("No Business Partner with value " + msg.getbPartnerNo());
			}
		}
		// Find product. If product is already set in "prepare" the product value in msg is disregarded.
		if (m_product==null) {
			// First look in M_Product_PO
			MProductPO productPo = new Query(getCtx(), MProductPO.Table_Name, "VendorProductNo=? AND C_BPartner_ID=?", get_TrxName())
										.setParameters(new Object[]{msg.getbPartnerArticleNo(), m_bPartner.get_ID()})
										.first();
			if (productPo==null) {
				// Check if we have a product with that ID
				m_product = new Query(getCtx(), MProductPO.Table_Name, "Value=?", get_TrxName())
				.setParameters(new Object[]{msg.getbPartnerArticleNo()})
				.setClient_ID()
				.first();
			} else {
				m_product = new MProduct(getCtx(), productPo.getM_Product_ID(), get_TrxName());
			}
			if (m_product==null) {
				throw new AdempiereException("No product with value " + msg.getbPartnerArticleNo());
			}
		}

		log.fine("Prod id: " + m_product.get_ID());
		log.fine("Partner id: " + m_bPartner.get_ID());
		log.fine("Promised date: " + (msg.getPromisedDate()!=null ? new java.sql.Date(msg.getPromisedDate().getTime()) : "null"));
		log.fine("Msg time: " + (msg.getMsgTime()!=null ? new java.sql.Date(msg.getMsgTime().getTime()) : "null"));
		log.fine("Precision: " + msg.getPromisedDatePrecision());
		log.fine("Count: " + msg.getDeliveryCount());
		
		// Call stored procedure
		if (DB.isPostgreSQL()) {
			
			String query = "select update_promiseddate(?::numeric, ?::numeric, ?::date, ?::date, ?::char(1), ?::numeric)";
			PreparedStatement ps = DB.prepareStatement(query, get_TrxName());
			ps.setInt(1, m_product.get_ID());
			ps.setInt(2, m_bPartner.get_ID());
			ps.setDate(3, msg.getPromisedDate()!=null ? new java.sql.Date(msg.getPromisedDate().getTime()) : null);
			ps.setDate(4, msg.getMsgTime()!=null ? new java.sql.Date(msg.getMsgTime().getTime()) : null);
			ps.setString(5, msg.getPromisedDatePrecision());
			ps.setDouble(6, msg.getDeliveryCount());
			ps.execute();
			ResultSet rs = ps.getResultSet();
			
		}
		if (DB.isOracle()) {

			String query = "{call update_promiseddate(?,?,?,?,?,?)}";
			CallableStatement ps = DB.prepareCall(query, ResultSet.CONCUR_UPDATABLE, get_TrxName());
			ps.setInt(1, m_product.get_ID());
			ps.setInt(2, m_bPartner.get_ID());
			ps.setDate(3, msg.getPromisedDate()!=null ? new java.sql.Date(msg.getPromisedDate().getTime()) : null);
			ps.setDate(4, msg.getMsgTime()!=null ? new java.sql.Date(msg.getMsgTime().getTime()) : null);
			ps.setString(5, msg.getPromisedDatePrecision());
			ps.setDouble(6, msg.getDeliveryCount());
			ps.execute();
			
		}
		
		return(result.toString());
	}

}
