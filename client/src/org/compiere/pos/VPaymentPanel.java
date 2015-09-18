package org.compiere.pos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTextField;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

public class VPaymentPanel extends Collect implements VetoableChangeListener, ActionListener, FocusListener {
	
	public CPanel 			panelTypePay 	= new CPanel();
	private CPanel 			bank_Panel;
	private GridBagLayout 	layout 			= new GridBagLayout();
	
	// Fields Bank
	private VComboBox 		bankList 		= new VComboBox();
	private PosTextField 	fCheckRouteNo;
	private CTextField 		fCheckdate;
	
	// Fields DebitCard
	private CPanel 			Cdebit;
	private GridBagLayout 	layoutDebit 	= new GridBagLayout();
	private CTextField 		CNrouteDebit;
	private CTextField 		CNcvcDebit;
	private CTextField 		CPaisDebit;
	
	// Fields CreditCard
	private CPanel 			Ccredit;
	private GridBagLayout 	layoutCredit 	= new GridBagLayout();
	private PosTextField 	fCCardNo;
	private PosTextField 	fCCardName;
	private PosTextField 	fCCardMonth;
	private PosTextField 	fCCardVC;
	private VComboBox 		fCCardType 		= new VComboBox();
	private ArrayList<Object> types;
	
	private Properties 		p_ctx;
	private String 			p_TenderType;
	private VPOS 			p_posBasePanel;
	private CComboBox 		tenderTypePick 	= new CComboBox();
	public 	VNumber 		f_PayAmt 		= new VNumber();
	private MPOS p_MPOS;
	
	public VPaymentPanel(Properties ctx, MOrder m_Order, int m_M_POS_ID, String m_TendeType, VPOS m_posBasePanel) {
		super(ctx, m_Order, m_M_POS_ID);
		p_ctx = ctx;
		p_TenderType = m_TendeType;
		p_posBasePanel = m_posBasePanel;
		//	Instance POS
		p_MPOS = MPOS.get(ctx, m_M_POS_ID);
	}
	
	public CPanel cashPayPanel(){
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		types = lookup.getData(true, false, true, true);
		// default to cash payment
		for (int x=0; x < types.size(); x++) {
			Object obj = types.get(x); 
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				if (!"X".contains(key.getID() ) ){ 
					types.remove(x);
					x--;
				}
			}
		}
		DefaultComboBoxModel typeModel = new DefaultComboBoxModel(types.toArray());
		
		tenderTypePick.setModel(typeModel);
		tenderTypePick.setPreferredSize(new Dimension(130, 30));
		panelTypePay.add(tenderTypePick,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
						,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		f_PayAmt.setPreferredSize(new Dimension(130, 30));
		panelTypePay.add(f_PayAmt,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		
		return panelTypePay;

	}
	//We create the dynamic rows
		public CPanel paymentPanel(){
			
			int AD_Column_ID = 5046;        //  C_PaySelectionCheck.C_PaySelection_ID
			MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
			types = lookup.getData(true, false, true, true);
			
			AD_Column_ID = 8374; //C_Payment_v.TenderType
			MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
			ArrayList<Object> cards = cardlookup.getData(true, false, true, true);
			filterTypes();
			panelTypePay.setLayout(layout);
			f_PayAmt = new VNumber();
			DefaultComboBoxModel typeModel = new DefaultComboBoxModel(types.toArray());
			
			tenderTypePick.setModel(typeModel);
			for (Object obj : types)
			{
				if ( obj instanceof ValueNamePair )	{
					ValueNamePair key = (ValueNamePair) obj;
					if ( key.getID().equals(p_TenderType))   // Cash
						tenderTypePick.setSelectedItem(key);
				}
			}
			tenderTypePick.setRenderer(new ListCellRenderer() {
				protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

				public Component getListCellRendererComponent(JList list, Object value,
						int index, boolean isSelected, boolean cellHasFocus) {
					
					JLabel renderer = (JLabel) defaultRenderer
			        .getListCellRendererComponent(list, value, index, isSelected,
			            cellHasFocus);
					
					renderer.setPreferredSize(new Dimension(50, 50));
					renderer.setHorizontalAlignment(JLabel.CENTER);
					
					return renderer;

				}
			});

			tenderTypePick.addVetoableChangeListener(this);
			bank_Panel = new CPanel();
			bank_Panel.setLayout(layout);
			
			// Add Bank List
			fCheckRouteNo = new PosTextField(Msg.translate(p_ctx, "RoutingNo"), p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
			fCheckRouteNo.addFocusListener(this);
			fCheckRouteNo.setForeground(Color.GRAY);
			fCheckRouteNo.setText(Msg.translate(p_ctx, "RoutingNo"));
			
			fCheckdate = new CTextField("MM/DD/YYYY");
			fCheckdate.addFocusListener(this);
			fCheckdate.setName("MM/DD/YYYY");
			fCheckdate.setForeground(Color.GRAY);
			//	lCheckNo = new CLabel(Msg.translate(p_ctx, "CheckNo"));

			ValueNamePair[] banks = getBank();
			for(int i=0; i < banks.length; i++)
				bankList.addItem(banks[i]);
			
			tenderTypePick.setPreferredSize(new Dimension(130, 30));
			fCheckRouteNo.setPreferredSize(new Dimension(130, 30));
			bankList.setPreferredSize(new Dimension(130, 30));
			fCheckdate.setPreferredSize(new Dimension(130, 30));
			bank_Panel.add(fCheckRouteNo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			bank_Panel.add(bankList,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			bank_Panel.add(fCheckdate,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			bank_Panel.setVisible(true);
			
			Cdebit = 		new CPanel();
			Cdebit.setLayout(layoutDebit);
			CNrouteDebit = 	new CTextField("00123");
			CNcvcDebit = 	new CTextField("393");
			CPaisDebit = 	new CTextField("Venezuela");
				
			CNrouteDebit.setPreferredSize(new Dimension(130, 30));
			CPaisDebit.setPreferredSize(new Dimension(130, 30));
			CNcvcDebit.setPreferredSize(new Dimension(130, 30));
			Cdebit.add(CNrouteDebit,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			Cdebit.add(CPaisDebit,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));		
			Cdebit.add(CNcvcDebit,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			Cdebit.setVisible(false);
			
			Ccredit = 			new CPanel();
			layoutCredit = 		new GridBagLayout();
			Ccredit.setLayout(layoutCredit);
			
			/**
			 *	Load Credit Cards
			 */
			for (Object obj : cards) {
				if ( obj instanceof ValueNamePair )	{
					ValueNamePair key = (ValueNamePair) obj;
					fCCardType.addItem(key);
				}
			}
			
			fCCardNo = new PosTextField(Msg.translate(p_ctx, "CreditCardNumber"),p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
			fCCardName = new PosTextField(Msg.translate(p_ctx, "Name"), p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
			fCCardMonth = new PosTextField(Msg.translate(p_ctx, "Expires"),p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
			fCCardVC = new PosTextField(Msg.translate(p_ctx, "CVC"), p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
			
			//add layout  of credit Target fields 
			fCCardType.setPreferredSize(new Dimension(130, 30));
			fCCardNo.setPreferredSize(new Dimension(130, 30));
			fCCardMonth.setPreferredSize(new Dimension(130, 30));
			fCCardName.setPreferredSize(new Dimension(130, 30));
			fCCardVC.setPreferredSize(new Dimension(130, 30));

			fCCardNo.addFocusListener(this);
			fCCardNo.setText(Msg.translate(p_ctx, "CreditCardNumber"));
			fCCardNo.setForeground(Color.GRAY);
			
			fCCardMonth.addFocusListener(this);
			fCCardMonth.setText(Msg.translate(p_ctx, "Expires"));
			fCCardMonth.setForeground(Color.GRAY);
			
			fCCardName.addFocusListener(this);
			fCCardName.setText(Msg.translate(p_ctx, "Name"));
			fCCardName.setForeground(Color.GRAY);
			
			fCCardVC.addFocusListener(this);
			fCCardVC.setText(Msg.translate(p_ctx, "CVC"));
			fCCardVC.setForeground(Color.GRAY);
			
			Ccredit.add(fCCardType,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			Ccredit.add(fCCardNo,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));		
			
			Ccredit.add(fCCardName,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
					,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			Ccredit.add(fCCardMonth,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			Ccredit.add(fCCardVC,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		
			Ccredit.setVisible(false);
		
			panelTypePay.add(tenderTypePick,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			f_PayAmt.setPreferredSize(new Dimension(130, 30));
			panelTypePay.add(f_PayAmt,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			panelTypePay.add(bank_Panel,  new GridBagConstraints(0, 1, 3, 3, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			panelTypePay.add(Cdebit,  new GridBagConstraints(0, 1, 3, 3, 0.0, 0.0
					,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
			
			panelTypePay.add(Ccredit,  new GridBagConstraints(0, 1, 3, 3, 0.0, 0.0
					,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));

			tenderTypePick.addActionListener(this);
			
			panelTypePay.validate();
			return panelTypePay;
		}
		
		/**
		 * 	Get Bank Data
		 * 
		 */
		public ValueNamePair[] getBank(){
			return DB.getValueNamePairs("SELECT C_Bank_ID, Name FROM C_Bank", true, null);
		}
		
		@Override
		public void vetoableChange(PropertyChangeEvent evt)
				throws PropertyVetoException {
		}

		public void filterTypes(){
			for (int x=0; x < types.size(); x++) {
				Object obj = types.get(x); 
				if ( obj instanceof ValueNamePair )	{
					ValueNamePair key = (ValueNamePair) obj;
					if (!"CKXFN".contains(key.getID() ) ){ 
						types.remove(x);
						x--;
					}
					
				}
			}
		}

		public boolean savePay(){
			BigDecimal payAmt = new BigDecimal(f_PayAmt.getValue().toString());
			
			if(p_TenderType.equals(MPayment.TENDERTYPE_Cash))
				addCash(payAmt);
			else if(p_TenderType.equals(MPayment.TENDERTYPE_Check)) {
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = fCheckdate.getText();
			    Timestamp dateTrx = null;
			    
				try{
				    Date parsedDate = dateFormat.parse(strDate);
				    dateTrx = new Timestamp(parsedDate.getTime());
				}catch(Exception e){
					e.printStackTrace(); 
				}

				String bank_ID = String.valueOf(bankList.getValue());
				String routeNo = fCheckRouteNo.getText();
				addCheck(payAmt, routeNo, Integer.parseInt(bank_ID), dateTrx);
			}
			else if(p_TenderType.equals(MPayment.TENDERTYPE_CreditCard)){

				int month = MPaymentValidate.getCreditCardExpMM(fCCardMonth.getText());
				int year = MPaymentValidate.getCreditCardExpYY(fCCardMonth.getText());
				String cardNo = fCCardNo.getText();
				String cardCVC = fCCardVC.getText();
				String cardType = String.valueOf(fCCardType.getValue());
				
				payCreditCard(payAmt, fCCardName.getText(), month, year, cardNo, cardCVC, cardType);
			}
				
			processPayment();
			return true;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource() instanceof CComboBox) {
				CComboBox c_TypePay = (CComboBox) evt.getSource();
				ValueNamePair temp_value = (ValueNamePair)c_TypePay.getSelectedItem();
				temp_value.getID();
				p_TenderType = temp_value.getID();
				if(p_TenderType.equals(MPayment.TENDERTYPE_Check)){
					c_TypePay.getParent().getComponent(2).setVisible(true);
					c_TypePay.getParent().getComponent(3).setVisible(false);
					c_TypePay.getParent().getComponent(4).setVisible(false);
				}else if(p_TenderType.equals(MPayment.TENDERTYPE_DirectDebit)){
					c_TypePay.getParent().getComponent(2).setVisible(false);
					c_TypePay.getParent().getComponent(3).setVisible(true);
					c_TypePay.getParent().getComponent(4).setVisible(false);
				}else if(p_TenderType.equals(MPayment.TENDERTYPE_CreditCard)){
					c_TypePay.getParent().getComponent(2).setVisible(false);
					c_TypePay.getParent().getComponent(3).setVisible(false);
					c_TypePay.getParent().getComponent(4).setVisible(true);
				}else{	
					c_TypePay.getParent().getComponent(2).setVisible(false);
					c_TypePay.getParent().getComponent(3).setVisible(false);
					c_TypePay.getParent().getComponent(4).setVisible(false);
				}	
			}
		}

		public void placeHolder(Component p_Field){
			JTextField field;
			if (p_Field instanceof CTextField) {
				 field = (CTextField) p_Field;
			}
			else {
				field = (PosTextField) p_Field;
			}
			if(p_Field.getForeground().equals(Color.DARK_GRAY)){
				field.setText(null);
				field.setForeground(Color.BLACK);
			}
			else if(field.getForeground().equals(Color.BLACK) && field.getText().length()==0){
				field.setText(field.getName());
				field.setForeground(Color.DARK_GRAY);
			}
		}
		
		@Override
		public void focusGained(FocusEvent e) {
				placeHolder(e.getComponent());
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getComponent().getName().equals(fCheckdate.getName())){
				placeHolder(e.getComponent());
			}
		}
}