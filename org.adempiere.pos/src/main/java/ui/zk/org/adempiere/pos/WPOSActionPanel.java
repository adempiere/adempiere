package org.adempiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import org.adempiere.pos.search.WPosQuery;
import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.search.WQueryTicket;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPOSKey;
import org.compiere.model.MSequence;
import org.compiere.pos.PosKeyListener;
import org.compiere.print.ReportCtl;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Space;

public class WPOSActionPanel extends WPosSubPanel implements PosKeyListener, I_POSPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;
	
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSActionPanel (WPOS posPanel) {
		super (posPanel);
	}	//	WPOSActionPanel

	private Button 		f_history;
	private	Label		f_name;
	private Button 		f_bNew;
	private Button 		f_cashPayment;

	private Button			f_bBPartner;
	private Button 		f_logout;
	private Button 		f_cancel;
	private Button 			f_Next;
	private Button 			f_Back;

	private final String BG_GRADIENT = "";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_LOGOUT      = "Cancel";
	private final String ACTION_CANCEL      = "End";
	private final String ACTION_CREDITSALE  = "Credit Sale";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_NEW         = "New";
	private final String ACTION_PAYMENT     = "Payment";
	private Panel card;
	private int 				recordPosition;
	private ArrayList<Integer>	orderList;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPOSActionPanel.class);
	
	@Override
	public void init() {

		Panel parameterPanel = new Panel();
		Borderlayout detailPanel = new Borderlayout();
		Grid parameterLayout = GridFactory.newGridLayout();
		Panel productPanel = new Panel();
		Borderlayout fullPanel = new Borderlayout();
		Grid productLayout = GridFactory.newGridLayout();
		Grid parameterLayout3 = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;	
		North north = new North();
		
		listOrder();
		recordPosition = orderList.size()-1;

		north.setStyle("border: none; width:60%");
		north.setZindex(0);
		fullPanel.appendChild(north);
		parameterPanel.appendChild(parameterLayout);
		parameterLayout.setWidth("60%");
		north.appendChild(parameterPanel);
		rows = parameterLayout.newRows();
		row = rows.newRow();
		Center center = new Center();
		center.setStyle("border: none; width:400px");
		appendChild(center);
		center.appendChild(detailPanel);
		north = new North();
		north.setStyle("border: none");
		detailPanel.setHeight("40%");
		detailPanel.setWidth("50%");
		detailPanel.appendChild(north);
		
		north.appendChild(parameterLayout3);
		parameterLayout3.setWidth("100%");
		parameterLayout3.setHeight("100%");
		rows = parameterLayout3.newRows();
		parameterLayout3.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("60px");

		row.appendChild(new Space());
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		f_bNew.addActionListener(this);
		row.appendChild(f_bNew);

		// BPartner Search
		f_bBPartner = createButtonAction(ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		f_bBPartner.addActionListener(this);
		f_bBPartner.setTooltiptext(Msg.translate(p_ctx, "IsCustomer"));
		row.appendChild(f_bBPartner);
				
				
		// HISTORY
		f_history = createButtonAction(ACTION_HISTORY, null);
		f_history.addActionListener(this);
		row.appendChild(f_history); 

		f_Back = createButtonAction("Parent", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		f_Back.setTooltiptext(Msg.translate(p_ctx, "Previous"));
		row.appendChild (f_Back);
		f_Next = createButtonAction("Detail", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		f_Next.setTooltiptext(Msg.translate(p_ctx, "Next"));
		row.appendChild (f_Next);
		
		// PAYMENT
		f_cashPayment = createButtonAction(ACTION_PAYMENT, null);
		f_cashPayment.addActionListener(this);
		row.appendChild(f_cashPayment); 
		f_cashPayment.setEnabled(false);

		// Cancel
		f_cancel = createButtonAction (ACTION_CANCEL, null);
		f_cancel.addActionListener(this);
		f_cancel.setTooltiptext(Msg.translate(p_ctx, "POS.IsCancel"));
		row.appendChild (f_cancel);
		f_cancel.setEnabled(false);
		
		// LOGOUT
		f_logout = createButtonAction (ACTION_LOGOUT, null);
		f_logout.addActionListener(this);
		f_logout.setTooltiptext(Msg.translate(p_ctx, "End"));
		row.appendChild (f_logout);
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.setSpans("3,5");
		row.setHeight("25px");
		// BP
		Label bpartner = new Label(Msg.translate(Env.getCtx(), "IsCustomer")+":");
		row.appendChild (bpartner.rightAlign());
		bpartner.setStyle("Font-size:medium; font-weight:700");
		
		f_name = new Label("-");
		f_name.setStyle("Font-size:medium");
		f_name.setWidth("100%");
		row.appendChild  (f_name);

	}
	/**
	 * 	Print Ticket
	 *  @author Raul Muñoz raulmunozn@gmail.com 
	 */
	public void printTicket()
	{
		if ( v_POSPanel.getM_Order()  == null )
			return;
		
		MOrder order = v_POSPanel.getM_Order();
		//int windowNo = p_posPanel.getWindowNo();
		//Properties m_ctx = p_posPanel.getPropiedades();
		
		if (order != null)
		{
			try 
			{
				//print standard document
				Boolean print = true;
				if (p_pos.getAD_Sequence_ID() != 0)
				{
					MSequence seq = new MSequence(Env.getCtx(), p_pos.getAD_Sequence_ID(), order.get_TrxName());
					String docno = seq.getPrefix() + seq.getCurrentNext();
					String q = "Confirmar el número consecutivo "  + docno;
					if (FDialog.ask(0, null, q))						
					{
						order.setPOReference(docno);
						order.saveEx();
						ReportCtl.startDocumentPrint(0, order.getC_Order_ID(), false);
						int next = seq.getCurrentNext() + seq.getIncrementNo();
						seq.setCurrentNext(next);
						seq.saveEx();
					}
				}
				else
					ReportCtl.startDocumentPrint(0, order.getC_Order_ID(), false);				
			}
			catch (Exception e) 
			{
				log.severe("PrintTicket - Error Printing Ticket");
			}
		}	  
	}
	

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		if (v_POSPanel == null || v_POSPanel.getM_Order() == null) {
			FDialog.warn(0,Msg.getMsg(p_ctx, "POS.MustCreateOrder"));
			return;			
		} else if (v_POSPanel.getM_Order().getDocStatus().equals(MOrder.STATUS_Drafted) ) {
			if (FDialog.ask(0, this, Msg.getMsg(p_ctx, "POS.DeleteOrder"))) {	//	TODO translate it: Do you want to delete the Order? 
				if (!v_POSPanel.deleteOrder()) {
					FDialog.warn(0, Msg.getMsg(p_ctx, "POS.OrderCouldNotDeleted"));	//	TODO translate it: Order could not be deleted
				}
			}
		} else if (v_POSPanel.getM_Order().getDocStatus().equals(MOrder.STATUS_Completed)) {	
			if (FDialog.ask(0, this, Msg.getMsg(p_ctx, Msg.getMsg(p_ctx, "POS.OrderIsAlreadyCompleted")))) {	//	TODO Translate it: The order is already completed. Do you want to void it?
				if (!v_POSPanel.cancelOrder())
					FDialog.warn(0, Msg.getMsg(p_ctx, "POS.OrderCouldNotVoided"));	//	TODO Translate it: Order could not be voided
			}
		} else {
			FDialog.warn(0,  Msg.getMsg(p_ctx, "POS.OrderIsNotProcessed"));	//	TODO Translate it: Order is not Drafted nor Completed. Try to delete it other way
			return;
		}
		//	Update
		changeViewPanel();

	} // deleteOrder
	/**
	 * 
	 */
	private void payOrder() {

		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( v_POSPanel.getM_Order() == null ) {
				FDialog.warn(0, Msg.getMsg(p_ctx, "You must create an Order first"));
				return;
		}
		WCollect collect = new WCollect(v_POSPanel);
		if (collect.showCollect()) {
			printTicket();
			v_POSPanel.setOrder(0);
		}
	}
	/**
	 * 	Find/Set BPartner
	 */
	private void findBPartner()
	{
		
		if ( !v_POSPanel.hasBPartner() )
			return;
		
		MBPartner results = MBPartner.get(p_ctx, v_POSPanel.getC_BPartner_ID());
		f_name.setText(results.getName());

	}	//	findBPartner
	
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event e) throws Exception {
		String action = e.getTarget().getId();
		if (e.getTarget().equals(f_bNew)){
			v_POSPanel.newOrder();
			v_POSPanel.refreshPanel();
			e.stopPropagation();
				return;
		}
		else if(e.getTarget().equals(f_cashPayment)){
			payOrder();
			return;
		}
		else if (e.getTarget().equals(f_Back) ){
			previousRecord();
		}
		else if (e.getTarget().equals(f_Next) ){
			nextRecord();
		}
		else if(e.getTarget().equals(f_logout)){
			dispose();
			return;
		}
		else if (e.getTarget().equals(f_bBPartner))
			{
				WQueryBPartner qt = new WQueryBPartner(v_POSPanel);
				AEnv.showWindow(qt);
				findBPartner();
//				if(m_table.getRowCount() > 0){
//					int row = m_table.getSelectedRow();
//					if (row < 0) row = 0;
//					m_table.setSelectedIndex(row);
//				}
		}
		// Cancel
		else if (e.getTarget().equals(f_cancel)){
			deleteOrder();
		}
		//	History
		if (e.getTarget().equals(f_history)) {
			
			WPosQuery qt = new WQueryTicket(v_POSPanel);
			qt.setVisible(true);
			AEnv.showWindow(qt);
		}
		v_POSPanel.refreshPanel();

	}

	@Override
	public void refreshPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String validatePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeViewPanel() {
		if (v_POSPanel != null )
		{
			MOrder order = v_POSPanel.getM_Order();
			if (order != null)
			{			
  				// Button BPartner: enable when drafted, and order has no lines
//				v_POSPanel.setC_BPartner_ID(order.getC_BPartner_ID());
  				if(order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted) && 
  						order.getLines().length == 0 )
  					f_bBPartner.setEnabled(true);
  				else
  					f_bBPartner.setEnabled(false);
  				
  			    // Button New: enabled when lines existing or order is voided
  				f_bNew.setEnabled(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided));
  				
  				if(!order.getDocStatus().equals(MOrder.DOCSTATUS_Voided))			
  					f_cancel.setEnabled(true);
  				else
  					f_cancel.setEnabled(false);

  			    // History Button: enabled when lines existing or order is voided
  				if(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided))
  	  				f_history.setEnabled(true);  	
  				else
  					f_history.setEnabled(false);
  				
  				// Button Payment: enable when (drafted, with lines) or (completed, on credit, (not invoiced or not paid) ) 
  				if((order.getDocStatus().equals(MOrder.DOCSTATUS_Drafted) && order.getLines().length != 0) ||
  	  				   (order.getDocStatus().equals(MOrder.DOCSTATUS_Completed) && 
  	  				    order.getC_DocType().getDocSubTypeSO().equalsIgnoreCase(MOrder.DocSubTypeSO_OnCredit) &&
  	  				    	(order.getC_Invoice_ID()<=0  ||
  	  				    	 !MInvoice.get(p_ctx, order.getC_Invoice_ID()).isPaid()
  	  				    	 )
  	  				   )
  	  				  )
  	  					f_cashPayment.setEnabled(true);
  	  				else 
  						f_cashPayment.setEnabled(false);	
  				
  			    // Next and Back Buttons:  enabled when lines existing or order is voided
  				if(order.getLines().length != 0 || order.getDocStatus().equals(MOrder.DOCSTATUS_Voided)) {

  					if(recordPosition==orderList.size()-1)
  					    f_Next.setEnabled(false); // End of order list
  					else
  	  					f_Next.setEnabled(true);

  					if(recordPosition==0)
  						f_Back.setEnabled(false); // Begin of order list
  					else
  						f_Back.setEnabled(true);
  				}
  				else{
  					f_Next.setEnabled(false);
  	  				f_Back.setEnabled(false);
  				}
			}
			else
			{
				f_bBPartner.setEnabled(false);
				v_POSPanel.setC_BPartner_ID(0);
				f_bNew.setEnabled(true);
				f_cancel.setEnabled(false);
				f_history.setEnabled(true);
				f_cashPayment.setEnabled(false);
			}
			
		}

		
	}

	@Override
	public void keyReturned(MPOSKey key) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get Data List Order
	 */
	public void listOrder() {
		String sql = "";
		PreparedStatement pstm;
		ResultSet rs;
		orderList = new ArrayList<Integer>();
		try {
			sql=" SELECT o.C_Order_ID"
					+ " FROM C_Order o"
					+ " LEFT JOIN c_invoice i ON i.c_order_ID = o.c_order_ID"
					+ " WHERE"
					+ " (coalesce(invoiceopen(i.c_invoice_ID, 0), 0) > 0 OR o.docstatus IN ('DR', 'IP') ) AND "
					+ " o.issotrx='Y' AND "
					+ " o.ad_client_id=? "
					+ " ORDER BY o.dateordered ASC, o.datepromised ASC";
			
			pstm= DB.prepareStatement(sql, null);
			pstm.setInt (1, Env.getAD_Client_ID(Env.getCtx()));
			rs = pstm.executeQuery();
			//	Add to List
			while(rs.next()){
				orderList.add(rs.getInt(1));
			}
		} catch(Exception e) {
			log.severe("SubOrder.listOrder: " + e + " -> " + sql);
		}
	}
	
	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		if(recordPosition>0)
			v_POSPanel.setOrder(orderList.get(recordPosition--));
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		if(recordPosition < orderList.size()-1)
			v_POSPanel.setOrder(orderList.get(recordPosition++));
		
	}
}
