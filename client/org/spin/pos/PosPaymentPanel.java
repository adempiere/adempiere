/**
 * 
 */
package org.spin.pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.compiere.apps.WJPanel;
import org.compiere.model.MPOSKey;
import org.compiere.pos.PosBasePanel;
import org.compiere.pos.PosKeyListener;
import org.compiere.pos.PosOrderModel;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.KeyBoard;
import org.compiere.util.Msg;

/**
 * 
 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a>
 *
 */
public class PosPaymentPanel extends CDialog implements PosKeyListener, VetoableChangeListener, ActionListener {

	private PosBasePanel m_PosBase = null;
	private PosOrderModel m_PosOrder = null;
	private boolean is_Paid	= false;
	private KeyBoard m_KeyBoard = null;
	private BigDecimal m_Balance = Env.ZERO;
	private CPanel jPanelTenderType = null;
	private CPanel jPanelResults = null;
	private GridBagConstraints grid = null;
	
	private JButton jButCash = null;
	// Las etiquetas
	private String lblButCash = "cash.payment";
	private String lblSubTotal = "SubTotal"; 
	private String lblTaxAmt = "TaxAmt";
	
	private String lblTenderPayment = "TenderPayment";
	
	
	// los montos de los diferentes tipos de pago
	private BigDecimal cashValue = BigDecimal.ZERO;
	private JFormattedTextField txtSubTotal;
	private JFormattedTextField txtTaxAmt;
	private JFormattedTextField txtTenderPayment;
	
	private JLabel jLabelSubTotal = null;
	private JLabel jLabelTaxAmt = null;
	private JLabel jLabelTenderPayment = null;
	
	
	
	

	private BigDecimal keyBoardValue = BigDecimal.ZERO;
	
	/**
	 * *** Constructor ***
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 5/8/2015, 18:48:35
	 */
	public PosPaymentPanel(PosBasePanel m_PosBase) {
		super(Env.getFrame(m_PosBase), true);
		this.m_PosBase = m_PosBase;
		
		if(m_PosOrder == null)
			dispose();
		
		init();
		pack();
		setLocationByPlatform(true);
	}
	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReturned(MPOSKey key) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Return Is Paid
	 * @author <a href="mailto:yamelsenih@gmail.com">Yamel Senih</a> 5/8/2015, 19:01:48
	 * @return
	 * @return boolean
	 */
	public boolean getPaid() {
		return is_Paid;
	}
	
	private void init()	{
		initComponents();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel princPanel = new JPanel();
		princPanel.setLayout(new GridBagLayout());
		
		m_KeyBoard = new KeyBoard();
		m_KeyBoard.setTypeKeyboard(KeyBoard.KEYBOARD_NUMERIC_CASHOUT);
		m_KeyBoard.setTypeShowKeyboard(KeyBoard.TYPE_PANEL);
		m_KeyBoard.paintKeyBoard();
		m_KeyBoard.getBtnEnterNumeric().setVisible(false);
		m_KeyBoard.getBtnEscape().setVisible(false);
		m_KeyBoard.getBtnCashOut().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m_KeyBoard.getTxtField().setText(m_Balance.toString());
			}
		});
		
		princPanel.add(m_KeyBoard.getPanel(), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		
		princPanel.add(showTenderType(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		
		princPanel.add(showResults(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		
		princPanel.add(showPanelProcessCancel(), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		
		this.add(princPanel);
		pack();
		
		
	}
	
	private CPanel showTenderType()	{
		jPanelTenderType = new CPanel();
		jPanelTenderType.setLayout(new GridBagLayout());
		jPanelTenderType.setOpaque(true);
		Dimension d = new Dimension(80, 80);
		
		jButCash = new JButton(lblButCash);
		jButCash.setFont(new Font("SansSerif", 0, 12));
		jButCash.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		jButCash.setIconTextGap(12);
		jButCash.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
		jButCash.setPreferredSize(d);
		jButCash.setMinimumSize(d);
		jButCash.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButCashActionPerformed(e);
			}

			private void jButCashActionPerformed(ActionEvent e) {
				cashValue = getKeyBoardValue().add(cashValue);
				txtSubTotal.setText(cashValue.toString());
				txtSubTotal.setValue(cashValue);

//				setSaldoPayment();

//				processPay(PAYMENT_CASH, getKeyBoardValue());

				m_KeyBoard.getBtnCleanNumeric().doClick();
			}
		});
		
		jPanelTenderType.add(jButCash, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,5,5,5), 0, 0));
		
		return jPanelTenderType;
	}
	
	
	int pivotLabelsX = 0;
	int pivotLabelsY = 0;
	int pivotTxtX = 4;
	int pivotTxtY = 0;
	
	private CPanel showResults() {
		jPanelResults = new CPanel(new GridBagLayout());
		DefaultFormatterFactory format = null;
		Dimension dimensionTextField = new Dimension(150, 25);
		Color color = new Color(0,0,0);
		Font fontLabel = new Font("SansSerif", 0, 14);
		Font fontField = new Font("SansSerif", 1, 14);
		Font fontLabelBig = new Font("SansSerif", 0, 18);
		Font fontFieldBig = new Font("SansSerif", 1, 18);

		format = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("$###,###,##0.00")));
		
		
		jLabelSubTotal = new JLabel();
		setValuesJLabel(jLabelSubTotal, fontLabel, color, lblSubTotal);
		
		txtSubTotal = new JFormattedTextField();
		setValuesJFormattedTextField(txtSubTotal, fontLabel, color, format, dimensionTextField, fontField);
		

		jLabelTaxAmt = new JLabel();
		setValuesJLabel(jLabelTaxAmt, fontLabel, color, lblTaxAmt);
		
		txtTaxAmt= new JFormattedTextField();
		setValuesJFormattedTextField(txtTaxAmt, fontLabel, color, format, dimensionTextField, fontField);
		
		jLabelTenderPayment = new JLabel();
		setValuesJLabel(jLabelTenderPayment, fontLabel, color, lblTenderPayment);
		
		txtTenderPayment = new JFormattedTextField();
		setValuesJFormattedTextField(txtTenderPayment, fontLabel, color, format, dimensionTextField, fontField);
		
		return jPanelResults;
	}
	
	private void setValuesJLabel( JLabel m_JLabel, Font fontLabel, Color color, String label) {
		m_JLabel.setFont(fontLabel); // NOI18N
		m_JLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		
		
		
		m_JLabel.setText("<html>" + Msg.translate(Env.getCtx(), label + ":") + "</html>");
		m_JLabel.setForeground(color);
		
		jPanelResults.add(m_JLabel, new GridBagConstraints(pivotLabelsX, pivotLabelsY, 1, 1, 0.0, 0.0
				,GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		pivotLabelsY++;
	}
	
	private void setValuesJFormattedTextField (
			JFormattedTextField m_JFormattedTextField, Font fontLabel, 
			Color color, DefaultFormatterFactory format, 
			Dimension dimensionTextField, Font fontField) {
		
		
		m_JFormattedTextField.setEditable(false);
		m_JFormattedTextField.setFormatterFactory(format);
		m_JFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		m_JFormattedTextField.setText("0");
		m_JFormattedTextField.setValue(0);
		m_JFormattedTextField.setPreferredSize(dimensionTextField);
		m_JFormattedTextField.setMinimumSize(dimensionTextField);
		m_JFormattedTextField.setFont(fontField); // NOI18N
		m_JFormattedTextField.setOpaque(false);
		m_JFormattedTextField.setBorder(null);
		m_JFormattedTextField.setForeground(color);
		
		jPanelResults.add(m_JFormattedTextField, new GridBagConstraints(pivotTxtX, pivotTxtY, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0,2,5,5), 0, 0));
		pivotTxtY++;
	}
	

	private CPanel showPanelResultados() {

		JPanel panelResultados = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Dimension dimensionTextField = new Dimension(150, 25);
		DefaultFormatterFactory format = null;

//		Color color = new Color(255, 255, 255);
		Color color = new Color(0,0,0);
		Font fontLabel = new Font("SansSerif", 0, 14);
		Font fontField = new Font("SansSerif", 1, 14);
		Font fontLabelBig = new Font("SansSerif", 0, 18);
		Font fontFieldBig = new Font("SansSerif", 1, 18);

		int gridx = 1;
		int gridy = 1;

//		if (posRefunds == null) {
//
//			format = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("$###,###,##0.00")));
//
//		} else {

			format = new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("-$###,###,##0.00")));
//		}

		lblYousavedtotal = new javax.swing.JLabel();
		lblYousavedtotal.setFont(fontLabel); // NOI18N
		lblYousavedtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblYousavedtotal.setText("You Saved Total:");
		lblYousavedtotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 5, 5);
		panelResultados.add(lblYousavedtotal, c);
		gridx++;

		txtYousavedtotal = new javax.swing.JFormattedTextField();
		txtYousavedtotal.setEditable(false);
		txtYousavedtotal.setFormatterFactory(format);
		txtYousavedtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtYousavedtotal.setText("0");
		txtYousavedtotal.setValue(0);
		txtYousavedtotal.setPreferredSize(dimensionTextField);
		txtYousavedtotal.setMinimumSize(dimensionTextField);
		txtYousavedtotal.setFont(fontField); // NOI18N
		txtYousavedtotal.setOpaque(false);
		txtYousavedtotal.setBorder(null);
		txtYousavedtotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtYousavedtotal, c);
		gridx--;
		gridy++;

		lblYourwictotal = new javax.swing.JLabel();
		lblYourwictotal.setFont(fontLabel); // NOI18N
		lblYourwictotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblYourwictotal.setText("Your WIC Total:");
		lblYourwictotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblYourwictotal, c);
		gridx++;

		txtYouwictotal = new javax.swing.JFormattedTextField();
		txtYouwictotal.setEditable(false);
		txtYouwictotal.setFormatterFactory(format);
		txtYouwictotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtYouwictotal.setText("0");
		txtYouwictotal.setValue(0);
		txtYouwictotal.setFont(fontField); // NOI18N
		txtYouwictotal.setOpaque(false);
		txtYouwictotal.setBorder(null);
		txtYouwictotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtYouwictotal, c);
		gridx--;
		gridy++;

		lblFoodstamptotal = new javax.swing.JLabel();
		lblFoodstamptotal.setFont(fontLabel); // NOI18N
		lblFoodstamptotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblFoodstamptotal.setText("Food Stamp Total:");
		lblFoodstamptotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblFoodstamptotal, c);
		gridx++;

		txtFoodstamptotal = new javax.swing.JFormattedTextField();
		txtFoodstamptotal.setEditable(false);
		txtFoodstamptotal.setFormatterFactory(format);
		txtFoodstamptotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtFoodstamptotal.setText("0");
		txtFoodstamptotal.setValue(0);
		txtFoodstamptotal.setFont(fontField); // NOI18N
		txtFoodstamptotal.setOpaque(false);
		txtFoodstamptotal.setBorder(null);
		txtFoodstamptotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtFoodstamptotal, c);
		gridx--;
		gridy++;

		lblDeposit = new javax.swing.JLabel();
		lblDeposit.setFont(fontLabel); // NOI18N
		lblDeposit.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblDeposit.setText("Deposit:");
		lblDeposit.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblDeposit, c);
		gridx++;

		txtDeposit = new javax.swing.JFormattedTextField();
		txtDeposit.setEditable(false);
		txtDeposit.setFormatterFactory(format);
		txtDeposit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtDeposit.setText("0");
		txtDeposit.setValue(0);
		txtDeposit.setFont(fontField); // NOI18N
		txtDeposit.setOpaque(false);
		txtDeposit.setBorder(null);
		txtDeposit.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtDeposit, c);
		gridx--;
		gridy++;

		lblSubtotal = new javax.swing.JLabel();
		lblSubtotal.setFont(fontLabel); // NOI18N
		lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblSubtotal.setText("Sub Total:");
		lblSubtotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblSubtotal, c);
		gridx++;

		txtSubtotal = new javax.swing.JFormattedTextField();
		txtSubtotal.setEditable(false);
		txtSubtotal.setFormatterFactory(format);
		txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtSubtotal.setText("0");
		txtSubtotal.setValue(0);
		txtSubtotal.setFont(fontField); // NOI18N
		txtSubtotal.setOpaque(false);
		txtSubtotal.setBorder(null);
		txtSubtotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtSubtotal, c);
		gridx--;
		gridy++;

		lblTaxamount = new javax.swing.JLabel();
		lblTaxamount.setFont(fontLabel); // NOI18N
		lblTaxamount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblTaxamount.setText("Tax Amount:");
		lblTaxamount.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblTaxamount, c);
		gridx++;

		txtTaxamount = new javax.swing.JFormattedTextField();
		txtTaxamount.setEditable(false);
		txtTaxamount.setFormatterFactory(format);
		txtTaxamount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtTaxamount.setText("0");
		txtTaxamount.setValue(0);
		txtTaxamount.setFont(fontField); // NOI18N
		txtTaxamount.setOpaque(false);
		txtTaxamount.setBorder(null);
		txtTaxamount.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtTaxamount, c);
		gridx--;
		gridy++;

		lblTender = new javax.swing.JLabel();
		lblTender.setFont(fontLabel); // NOI18N
		lblTender.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblTender.setText("Tender Payment:");
		lblTender.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblTender, c);
		gridx++;

		txtTender = new javax.swing.JFormattedTextField();
		txtTender.setEditable(false);
		txtTender.setFormatterFactory(format);
		txtTender.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtTender.setText("0");
		txtTender.setValue(0);
		txtTender.setFont(fontField); // NOI18N
		txtTender.setOpaque(false);
		txtTender.setBorder(null);
		txtTender.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtTender, c);
		gridx--;
		gridy++;

		lblGrandtotal = new javax.swing.JLabel();
		lblGrandtotal.setFont(fontLabelBig); // NOI18N
		lblGrandtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblGrandtotal.setText("Grand Total:");
		lblGrandtotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblGrandtotal, c);
		gridx++;

		txtGrandTotal = new javax.swing.JFormattedTextField();
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setFormatterFactory(format);
		txtGrandTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtGrandTotal.setText("0");
		txtGrandTotal.setValue(0);
		txtGrandTotal.setFont(fontFieldBig); // NOI18N
		txtGrandTotal.setOpaque(false);
		txtGrandTotal.setBorder(null);
		txtGrandTotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtGrandTotal, c);
		gridx--;
		gridy++;

		jSeparator4 = new JSeparator();
		jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		panelResultados.add(jSeparator4, c);
		gridy++;

//		lblSubTotal = new javax.swing.JLabel();
//		lblSubTotal.setFont(fontLabel); // NOI18N
//		lblSubTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//		lblSubTotal.setText("CASH:");
//		lblSubTotal.setForeground(color);
//		c.gridx = gridx;
//		c.gridy = gridy;
//		c.gridwidth = 1;
//		panelResultados.add(lblSubTotal, c);
//		gridx++;

		txtSubTotal = new javax.swing.JFormattedTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setFormatterFactory(format);
		txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtSubTotal.setText("0");
		txtSubTotal.setValue(0);
		txtSubTotal.setFont(fontField); // NOI18N
		txtSubTotal.setOpaque(false);
		txtSubTotal.setBorder(null);
		txtSubTotal.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtSubTotal, c);
		gridx--;
		gridy++;

		lblCheck = new javax.swing.JLabel();
		lblCheck.setFont(fontLabel); // NOI18N
		lblCheck.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCheck.setText("CHECK:");
		lblCheck.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblCheck, c);
		gridx++;

		txtCheck = new javax.swing.JFormattedTextField();
		txtCheck.setEditable(false);
		txtCheck.setFormatterFactory(format);
		txtCheck.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtCheck.setText("0");
		txtCheck.setValue(0);
		txtCheck.setFont(fontField); // NOI18N
		txtCheck.setOpaque(false);
		txtCheck.setBorder(null);
		txtCheck.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtCheck, c);
		gridx--;
		gridy++;

		lblCcard = new javax.swing.JLabel();
		lblCcard.setFont(fontLabel); // NOI18N
		lblCcard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblCcard.setText("C. CARD:");
		lblCcard.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblCcard, c);
		gridx++;

		txtCcard = new javax.swing.JFormattedTextField();
		txtCcard.setEditable(false);
		txtCcard.setFormatterFactory(format);
		txtCcard.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtCcard.setText("0");
		txtCcard.setValue(0);
		txtCcard.setFont(fontField); // NOI18N
		txtCcard.setOpaque(false);
		txtCcard.setBorder(null);
		txtCcard.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtCcard, c);
		gridx--;
		gridy++;

		lblHousecharge = new javax.swing.JLabel();
		lblHousecharge.setFont(fontLabel); // NOI18N
		lblHousecharge.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblHousecharge.setText("HOUSE CHARGE:");
		lblHousecharge.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblHousecharge, c);
		gridx++;

		txtHousecharge = new javax.swing.JFormattedTextField();
		txtHousecharge.setEditable(false);
		txtHousecharge.setFormatterFactory(format);
		txtHousecharge.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtHousecharge.setText("0");
		txtHousecharge.setValue(0);
		txtHousecharge.setFont(fontField); // NOI18N
		txtHousecharge.setOpaque(false);
		txtHousecharge.setBorder(null);
		txtHousecharge.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtHousecharge, c);
		gridx--;
		gridy++;

		lblGcard = new javax.swing.JLabel();
		lblGcard.setFont(fontLabel); // NOI18N
		lblGcard.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblGcard.setText("G. CARD:");
		lblGcard.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblGcard, c);
		gridx++;

		txtGiftcard = new javax.swing.JFormattedTextField();
		txtGiftcard.setEditable(false);
		txtGiftcard.setFormatterFactory(format);
		txtGiftcard.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtGiftcard.setText("0");
		txtGiftcard.setValue(0);
		txtGiftcard.setFont(fontField); // NOI18N
		txtGiftcard.setOpaque(false);
		txtGiftcard.setBorder(null);
		txtGiftcard.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtGiftcard, c);
		gridx--;
		gridy++;

		lblStorecoupon = new javax.swing.JLabel();
		lblStorecoupon.setFont(fontLabel); // NOI18N
		lblStorecoupon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblStorecoupon.setText("STORE COUPON:");
		lblStorecoupon.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblStorecoupon, c);
		gridx++;

		txtStoreCoupon = new javax.swing.JFormattedTextField();
		txtStoreCoupon.setEditable(false);
		txtStoreCoupon.setFormatterFactory(format);
		txtStoreCoupon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtStoreCoupon.setText("0");
		txtStoreCoupon.setValue(0);
		txtStoreCoupon.setFont(fontField); // NOI18N
		txtStoreCoupon.setOpaque(false);
		txtStoreCoupon.setBorder(null);
		txtStoreCoupon.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtStoreCoupon, c);
		gridx--;
		gridy++;

		lblVendorcoupon = new javax.swing.JLabel();
		lblVendorcoupon.setFont(fontLabel); // NOI18N
		lblVendorcoupon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblVendorcoupon.setText("VENDOR COUPON:");
		lblVendorcoupon.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblVendorcoupon, c);
		gridx++;

		txtVendorCoupon = new javax.swing.JFormattedTextField();
		txtVendorCoupon.setEditable(false);
		txtVendorCoupon.setFormatterFactory(format);
		txtVendorCoupon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtVendorCoupon.setText("0");
		txtVendorCoupon.setValue(0);
		txtVendorCoupon.setFont(fontField); // NOI18N
		txtVendorCoupon.setOpaque(false);
		txtVendorCoupon.setBorder(null);
		txtVendorCoupon.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtVendorCoupon, c);
		gridx--;
		gridy++;

		jSeparator5 = new JSeparator();
		jSeparator5.setForeground(new java.awt.Color(0, 0, 0));
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		panelResultados.add(jSeparator5, c);
		gridy++;

//		if (posPanel.m_order.isWIC()) {
//
//			lblWICAmount = new javax.swing.JLabel();
//			lblWICAmount.setFont(fontLabelBig); // NOI18N
//			lblWICAmount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
//			lblWICAmount.setText("WIC Limit:");
//			lblWICAmount.setForeground(color);
//			c.gridx = gridx;
//			c.gridy = gridy;
//			c.gridwidth = 1;
//			c.insets = new Insets(5, 5, 5, 5);
//			c.ipady = 5;
//			panelResultados.add(lblWICAmount, c);
//			gridx++;
//
//			txtWICAmount = new javax.swing.JFormattedTextField();
//			txtWICAmount.setEditable(false);
//			txtWICAmount.setFormatterFactory(format);
//			txtWICAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
//			txtWICAmount.setText("0");
//			txtWICAmount.setValue(0);
//			txtWICAmount.setFont(fontFieldBig); // NOI18N
//			txtWICAmount.setOpaque(false);
//			txtWICAmount.setBorder(null);
//			txtWICAmount.setForeground(color);
//			c.gridx = gridx;
//			c.gridy = gridy;
//			panelResultados.add(txtWICAmount, c);
//			gridx--;
//			gridy++;
//
//		}

		lblBalance = new javax.swing.JLabel();
		lblBalance.setFont(fontLabelBig); // NOI18N
		lblBalance.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblBalance.setText("Balance:");
		lblBalance.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.ipady = 5;
		panelResultados.add(lblBalance, c);
		gridx++;

		txtBalance = new javax.swing.JFormattedTextField();
		txtBalance.setEditable(false);
		txtBalance.setFormatterFactory(format);
		txtBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtBalance.setText("0");
		txtBalance.setValue(0);
		txtBalance.setFont(fontFieldBig); // NOI18N
		txtBalance.setOpaque(false);
		txtBalance.setBorder(null);
		txtBalance.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtBalance, c);
		gridx--;
		gridy++;

		lblChange = new javax.swing.JLabel();
		lblChange.setFont(fontLabelBig); // NOI18N
		lblChange.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		lblChange.setText("Change:");
		lblChange.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(lblChange, c);
		gridx++;

		txtChange = new javax.swing.JFormattedTextField();
		txtChange.setEditable(false);
		txtChange.setFormatterFactory(format);
		txtChange.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
		txtChange.setText("0");
		txtChange.setValue(0);
		txtChange.setFont(fontFieldBig); // NOI18N
		txtChange.setOpaque(false);
		txtChange.setBorder(null);
		txtChange.setForeground(color);
		c.gridx = gridx;
		c.gridy = gridy;
		panelResultados.add(txtChange, c);
		gridx--;
		gridy++;

//		if (posRefunds != null) {
//
//			lblBalance.setText(lblChange.getText());
//			lblChange.setVisible(false);
//			txtChange.setVisible(false);
//
//		}

//		return panelResultados;
		return null;

	}
	

	private CPanel showPanelProcessCancel() {

		panelProcessCancel = new CPanel(new GridBagLayout());
		Dimension d = new Dimension(10, 80);
		GridBagConstraints c = new GridBagConstraints();

		int gridx = 1;
		int gridy = 1;

		btnCancel = new javax.swing.JButton();
		btnCancel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		btnCancel.setIcon(Env.getImageIcon("Cancel24.gif"));
		btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnCancel.setIconTextGap(12);
		// btnCancel.setPreferredSize(d);
		// btnCancel.setMinimumSize(d);
		btnCancel.setFocusable(false);
		btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				btnCancelActionPerformed(evt);
			}
		});
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipady = 30;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		panelProcessCancel.add(btnCancel, c);
		gridx++;

		btnProcess = new javax.swing.JButton();
		btnProcess.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
		btnProcess.setIcon(Env.getImageIcon("Ok24.gif"));
		btnProcess.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		btnProcess.setIconTextGap(12);
		btnProcess.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
		// btnProcess.setPreferredSize(d);
		// btnProcess.setMinimumSize(d);
		btnProcess.setFocusable(false);
		btnProcess.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
//				btnProcessActionPerformed(evt);
			}
		});
		c.gridx = gridx;
		c.gridy = gridy;
		panelProcessCancel.add(btnProcess, c);
		gridx++;

		return panelProcessCancel;
	}

	
	private BigDecimal getKeyBoardValue() {
		keyBoardValue = new BigDecimal(m_KeyBoard.getTxtFieldValueAsDouble());
		return keyBoardValue;
	}
	
	private JFormattedTextField txtGiftcard;
	private JFormattedTextField txtCcard;
	private JFormattedTextField txtCheck;
	private JFormattedTextField txtTender;
	private JFormattedTextField txtFoodstamptotal;
	private JFormattedTextField txtDeposit;
	private JFormattedTextField txtGrandTotal;
	private JFormattedTextField txtHousecharge;
	private JFormattedTextField txtStoreCoupon;
	private JFormattedTextField txtSubtotal;
	private JFormattedTextField txtTaxamount;
	private JFormattedTextField txtVendorCoupon;
	private JFormattedTextField txtYousavedtotal;
	private JFormattedTextField txtYouwictotal;
	private JFormattedTextField txtBalance;
	private JFormattedTextField txtWICAmount;
	private JFormattedTextField txtChange;
	private CPanel panelProcessCancel;
	private WJPanel panelTipoPago;
	private JSeparator jSeparator4;
	private JSeparator jSeparator5;
	private JLabel lblCcard;
	private JLabel lblCheck;
	private JLabel lblTender;
	private JLabel lblFoodstamptotal;
	private JLabel lblDeposit;
	private JLabel lblGcard;
	private JLabel lblGrandtotal;
	private JLabel lblHousecharge;
	private JLabel lblStorecoupon;
	private JLabel lblSubtotal;
	private JLabel lblTaxamount;
	private JLabel lblVendorcoupon;
	private JLabel lblYourwictotal;
	private JLabel lblYousavedtotal;
	private JLabel lblBalance;
	private JLabel lblWICAmount;
	private JLabel lblChange;
	private WJPanel panelResultados;
	private JButton btnCancel;
	private JButton btnProcess;

}
