package org.adempiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import org.adempiere.pos.search.QueryBPartner;
import org.adempiere.pos.search.QueryProduct;
import org.adempiere.pos.search.QueryTicket;
import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.search.WQueryProduct;
import org.adempiere.pos.search.WQueryTicket;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
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
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
import org.compiere.pos.PosKeyListener;
import org.compiere.print.ReportCtl;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Space;

public class WPOSActionPanel extends WPosSubPanel implements PosKeyListener, I_POSPanel, POSQueryListener{

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

	private Button 			f_History;
	private	WPosTextField	f_ProductName;
	private boolean			isKeyboard;
	private Button 			f_bNew;
	private Button 			f_Collect;

	private Button			f_bBPartner;
	private Label 			productLabel;
	private Button 			f_logout;
	private Button 			f_Cancel;
	private Button 			f_Next;
	private Button 			f_Back;

	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_LOGOUT      = "Cancel";
	private final String ACTION_CANCEL      = "End";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_NEW         = "New";
	private final String ACTION_PAYMENT     = "Payment";
	
	private WPOSInfoProduct v_InfoProductPanel;
	private Panel parameterPanel;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPOSActionPanel.class);
	
	@Override
	public void init() {

		parameterPanel = new Panel();
		Borderlayout detailPanel = new Borderlayout();
		Grid parameterLayout = GridFactory.newGridLayout();
		Borderlayout fullPanel = new Borderlayout();
		Grid LayoutButton = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;	
		North north = new North();
		isKeyboard = false;

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
		detailPanel.setHeight("45%");
		detailPanel.setWidth("50%");
		detailPanel.appendChild(north);
		
		north.appendChild(LayoutButton);
		LayoutButton.setWidth("100%");
		LayoutButton.setHeight("100%");
		rows = LayoutButton.newRows();
		LayoutButton.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("55px");

		row.appendChild(new Space());
		// NEW
		f_bNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		f_bNew.addActionListener(this);
		row.appendChild(f_bNew);

		// BPartner Search
		f_bBPartner = createButtonAction(ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		f_bBPartner.addActionListener(this);
		f_bBPartner.setTooltiptext(Msg.translate(m_ctx, "IsCustomer"));
		row.appendChild(f_bBPartner);
				
		// HISTORY
		f_History = createButtonAction(ACTION_HISTORY, null);
		f_History.addActionListener(this);
		row.appendChild(f_History); 

		f_Back = createButtonAction("Parent", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		f_Back.setTooltiptext(Msg.translate(m_ctx, "Previous"));
		row.appendChild (f_Back);
		f_Next = createButtonAction("Detail", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		f_Next.setTooltiptext(Msg.translate(m_ctx, "Next"));
		row.appendChild (f_Next);
		
		// PAYMENT
		f_Collect = createButtonAction(ACTION_PAYMENT, null);
		f_Collect.addActionListener(this);
		row.appendChild(f_Collect); 
		f_Collect.setEnabled(false);

		// Cancel
		f_Cancel = createButtonAction (ACTION_CANCEL, null);
		f_Cancel.addActionListener(this);
		f_Cancel.setTooltiptext(Msg.translate(m_ctx, "POS.IsCancel"));
		row.appendChild (f_Cancel);
		f_Cancel.setEnabled(false);
		
		// LOGOUT
		f_logout = createButtonAction (ACTION_LOGOUT, null);
		f_logout.addActionListener(this);
		f_logout.setTooltiptext(Msg.translate(m_ctx, "End"));
		row.appendChild (f_logout);
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.setSpans("1,7");
		row.setHeight("55px");

		row.appendChild (new Space());

		productLabel = new Label(Msg.translate(Env.getCtx(), "M_Product_ID"));
		f_ProductName = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_ProductName.setWidth("100%");
		f_ProductName.setHeight("35px");
		f_ProductName.setStyle("Font-size:medium; font-weight:bold");
		f_ProductName.addEventListener(this);
		f_ProductName.setValue(productLabel.getValue());
		
		row.appendChild(f_ProductName);
		enableButton();
		
		v_InfoProductPanel = new WPOSInfoProduct(v_POSPanel);
		row = rows.newRow();
		row.setSpans("9");
		row.appendChild(v_InfoProductPanel.getPanel());
		//	List Orders
		v_POSPanel.listOrder();
	}
	/**
	 * 	Print Ticket
	 *  @author Raul Muñoz raulmunozn@gmail.com 
	 */
	public void printTicket() {
		if (!v_POSPanel.hasOrder())
			return;
		
		try {
			//print standard document
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						if (p_pos.getAD_Sequence_ID()!= 0) {
						
							String docno = v_POSPanel.getSequenceDoc(trxName);
							String q = "Confirmar el número consecutivo "  + docno;
							if (FDialog.ask(0, null, "", q)) {
								v_POSPanel.setPOReference(docno);
								v_POSPanel.saveNextSeq(trxName);
							}
						}
					}
				});
			
				ReportCtl.startDocumentPrint(0, v_POSPanel.getC_Order_ID(), false);				
			}
			catch (Exception e) 
			{
				log.severe("PrintTicket - Error Printing Ticket");
			}
			  
	}

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		String errorMsg = null;
		String askMsg = "POS.DeleteOrder";	//	TODO Translate it: Do you want to delete Order?
		if (v_POSPanel.isCompleted()) {	
			askMsg = "POS.OrderIsAlreadyCompleted";	//	TODO Translate it: The order is already completed. Do you want to void it?
		}
		//	Show Ask
		if (FDialog.ask(0, this, Msg.getMsg(m_ctx, askMsg))) {
			errorMsg = v_POSPanel.cancelOrder();
		} 
		if(errorMsg != null){
			FDialog.error(0,  Msg.parseTranslation(m_ctx, errorMsg));
			return;
		}
		//	Update
		v_POSPanel.refreshPanel();
	} // deleteOrder
	
	/**
	 * 
	 */
	private void payOrder() {

		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( v_POSPanel.getM_Order() == null ) {
				FDialog.warn(0, Msg.getMsg(m_ctx, "You must create an Order first"));
				return;
		}
		WCollect collect = new WCollect(v_POSPanel);
		if (collect.showCollect()) {
			if(v_POSPanel.isToPrint()) {
				printTicket();
			}
			//	
			v_POSPanel.setOrder(0);
			v_POSPanel.refreshPanel();
			refreshProductInfo(null);
		}
	}
	/**
	 * 	Find/Set Product & Price
	 */
	private void findProduct()
	{
		String query = f_ProductName.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try
		{
			Integer.getInteger(query);
		}
		catch (Exception e)
		{
			allNumber = false;
		}
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		
		MWarehousePrice[] results = null;

		//
		results = MWarehousePrice.find  (m_ctx,
				v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID(), 
				Value, Name, UPC, SKU, null);
		
		//	Set Result
//		if (results.length == 0)
//		{
//			String message = Msg.getMsg(p_ctx,  "POS.SearchProductNF");
//			FDialog.warn(0, null, message +" "+ query,"");
//		}
		if (results.length == 1)
		{
			v_POSPanel.addLine(results[0].getM_Product_ID(), Env.ONE);
			f_ProductName.setValue(results[0].getName());
			
		}
		else	//	more than one
		{
			WQueryProduct qt = new WQueryProduct(v_POSPanel);
			qt.setResults(results);
			qt.setQueryData(v_POSPanel.getM_PriceList_Version_ID(), v_POSPanel.getM_Warehouse_ID());
			AEnv.showWindow(qt);
			Object[] result = qt.getSelectedKeys();
			if(result == null) 
				return;
			
			for(Object item : result) {
				f_ProductName.setText(productLabel.getValue());
				v_POSPanel.addLine((Integer)item, Env.ONE);
			}
		}
	}	//	findProduct

	
	public boolean showKeyboard(WPosTextField field, Label label) {
		isKeyboard = true;
		if(field.getText().equals(label.getValue()))
			field.setValue("");
		WPOSKeyboard keyboard =  v_POSPanel.getKeyboard(field.getKeyLayoutId()); 
		keyboard.setWidth("750px");
		keyboard.setHeight("350px");
		keyboard.setPosTextField(field);	
		AEnv.showWindow(keyboard);
		if(field.getText().equals("")) 
			field.setValue(label.getValue());
		return keyboard.isCancel();
	}
	
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event e) throws Exception {
		
			if(e.getTarget().equals(f_ProductName.getComponent(WPosTextField.SECONDARY)) 
					&& e.getName().equals(Events.ON_FOCUS) && !isKeyboard){
				if(!showKeyboard(f_ProductName,productLabel))
					findProduct(); 
				f_ProductName.setFocus(true);
			}
			if(e.getTarget().equals(f_ProductName.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
		
		if (e.getTarget().equals(f_bNew)){
			v_POSPanel.newOrder();
			refreshProductInfo(null);
		}
		else if(e.getTarget().equals(f_Collect)){
			payOrder();
			return;
		}
		else if (e.getTarget().equals(f_Back)){
			previousRecord();
			refreshProductInfo(null);
		}
		else if (e.getTarget().equals(f_Next)){
			nextRecord();
			refreshProductInfo(null);
		}
		else if(e.getTarget().equals(f_logout)){
			dispose();
			return;
		}
		else if (e.getTarget().equals(f_bBPartner)) {
			WQueryBPartner qt = new WQueryBPartner(v_POSPanel);
			AEnv.showWindow(qt);
			if (qt.getRecord_ID() > 0) {
				if(!v_POSPanel.hasOrder()) {
					v_POSPanel.newOrder(qt.getRecord_ID());
					v_POSPanel.refreshPanel();
				} else {
					v_POSPanel.setC_BPartner_ID(qt.getRecord_ID());
				}
				log.fine("C_BPartner_ID=" + qt.getRecord_ID());
			}
		}
		// Cancel
		else if (e.getTarget().equals(f_Cancel)){
			deleteOrder();
			refreshProductInfo(null);
		}
		//	History
		if (e.getTarget().equals(f_History)) {
			
			WQueryTicket qt = new WQueryTicket(v_POSPanel);
			qt.setVisible(true);
			AEnv.showWindow(qt);
			v_POSPanel.reloadIndex(qt.getRecord_ID());
		}
		v_POSPanel.refreshPanel();

	}

	@Override
	public void refreshPanel() {
	}

	@Override
	public String validatePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		v_POSPanel.previousRecord();
		//	Refresh
		v_POSPanel.refreshPanel();
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		v_POSPanel.nextRecord();
		//	Refresh
		v_POSPanel.refreshPanel();
	}
	
	@Override
	public void changeViewPanel() {
		if(v_POSPanel.hasOrder()) {
			//	For Next
			f_Next.setEnabled(!v_POSPanel.isLastRecord() && v_POSPanel.hasRecord());
			//	For Back
			f_Back.setEnabled(!v_POSPanel.isFirstRecord() && v_POSPanel.hasRecord());
			//	For Collect
			if(v_POSPanel.hasLines()
					&& !v_POSPanel.isPaid()
					&& !v_POSPanel.isVoided()) {
				//	For Credit Order
				f_Collect.setEnabled(true);
			} else {
				f_Collect.setEnabled(false);
			}
			//	For Cancel Action
			f_Cancel.setEnabled(!v_POSPanel.isVoided());
		} else {
			f_bNew.setEnabled(true);
			f_History.setEnabled(true);
			//	For Next
			f_Next.setEnabled(!v_POSPanel.isLastRecord() && v_POSPanel.hasRecord());
			//	For Back
			f_Back.setEnabled(!v_POSPanel.isFirstRecord() && v_POSPanel.hasRecord());
			f_Collect.setEnabled(false);
			//	For Cancel Action
			f_Cancel.setEnabled(false);
		}
	}
	
	public void enableButton(){
		f_ProductName.setText(productLabel.getValue());
		v_POSPanel.setC_BPartner_ID(0);
		f_bNew.setEnabled(true);
		f_Cancel.setEnabled(false);
		f_History.setEnabled(true);
		f_Collect.setEnabled(false);
	}
	
	@Override
	public void keyReturned(MPOSKey key) {

	}
	
	/**
	 * Refresh Product Info from Key
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		v_InfoProductPanel.refreshProduct(key);
		parameterPanel.invalidate();
	}
	
	/**
	 * Refresh Product Info from Product
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		v_InfoProductPanel.refreshProduct(p_M_Product_ID);
		parameterPanel.invalidate();
	}
	
	@Override
	public void okAction(I_POSQuery query) {
		if (query.getRecord_ID() <= 0)
			return;
		//	For Ticket
		if(query instanceof QueryTicket) {
			v_POSPanel.setOrder(query.getRecord_ID());
			v_POSPanel.reloadIndex(query.getRecord_ID()); 
		} else if(query instanceof QueryBPartner) {
			if(!v_POSPanel.hasOrder()) {
				v_POSPanel.newOrder(query.getRecord_ID());
			} else {
				v_POSPanel.setC_BPartner_ID(query.getRecord_ID());
			}
			//	
			log.fine("C_BPartner_ID=" + query.getRecord_ID());
		} else if(query instanceof QueryProduct) {
			if (query.getRecord_ID() > 0) {
				v_POSPanel.addLine(query.getRecord_ID(), Env.ONE);
			}
		}
		//	Refresh
		v_POSPanel.refreshPanel();
	}
	@Override
	public void cancelAction(I_POSQuery query) {
		
	}
	
}
