package org.compiere.pos;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrder;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class PosPayment extends CDialog implements VetoableChangeListener,
		ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6625606525941639975L;

	public PosPayment(VPOS posPanel) {
		super();
		p_posPanel = posPanel;
		p_ctx = p_posPanel.m_ctx;
		p_order = posPanel.getM_Order();
		keyLayoutId = p_posPanel.f_order.p_pos.getOSNP_KeyLayout_ID();

		setTitle(Msg.translate(p_ctx, "Payment"));
		if (p_order == null)
			dispose();

		init();
	}

	public void init() {
		log.info("");

		try {
			jbInit();
			getContentPane().add(commandPanel, BorderLayout.SOUTH);
			getContentPane().add(mainPanel, BorderLayout.CENTER);
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	} // init

	int m_WindowNo;
	private boolean isPaid;
	private int keyLayoutId;
	private MOrder p_order;
	private VPOS p_posPanel;
	private Properties p_ctx;
	private CLogger log = CLogger.getCLogger(PosPayment.class);
	private CPanel panel = new CPanel();
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel parameterPanel = new CPanel();
	private CPanel centerPanel = new CPanel();

	private CPanel C_Blast_credit = new CPanel();
	private GridBagLayout G_Blast = new GridBagLayout();

	private BigDecimal balance = Env.ZERO;
	private CLabel fBalance = new CLabel();

	private CLabel lGrandTotal;
	private CLabel lReturnAmt;
	private CLabel fReturnAmt = new CLabel("0");
	private CLabel fGrandTotal = new CLabel("0");
	private CLabel lPayAmt;
	private CLabel fPayAmt = new CLabel("0");

	private CButton bMinus;
	private int add_file = 0;

	private ArrayList<VPaymentPanel> pp = new ArrayList<VPaymentPanel>();
	private ArrayList<CButton> fMinus = new ArrayList<CButton>();
	private Random rand = new Random();

	// button
	private JButton bCancel = ConfirmPanel.createCancelButton(true);
	private JButton bProcess = ConfirmPanel.createProcessButton(true);
	private FlowLayout commandLayout = new FlowLayout();

	private GridBagLayout parameterLayout = new GridBagLayout();
	private GridBagLayout parameterLayout2 = new GridBagLayout();
	private CPanel commandPanel = new CPanel();
	private JScrollPane scrollPanel = new JScrollPane();
	private VPaymentPanel paymentPanel;
	private CButton f_Plus;
	private int precision;

	// JBinit
	private void jbInit() throws Exception {
		CompiereColor.setBackground(panel);
		Font fontBold = new Font("Helvetica", Font.BOLD, 18);
		//
		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		centerPanel.setLayout(parameterLayout2);
		C_Blast_credit.setLayout(G_Blast);
		mainPanel.add(scrollPanel);
		scrollPanel.getViewport().add(centerPanel);

		// sizeFrame
		setPreferredSize(new Dimension(270, 400));
		precision = MCurrency.getStdPrecision(p_ctx, p_posPanel.m_CurrentOrder.getC_Currency_ID());
		// ADD
		lGrandTotal = new CLabel(Msg.translate(p_ctx, "GrandTotal") + ":");
		lGrandTotal.setFont(fontBold);
		fGrandTotal.setFont(fontBold);
		parameterPanel.add(lGrandTotal, new GridBagConstraints(0, 0, 1, 1, 0.0,0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		fGrandTotal.setPreferredSize(new Dimension(100, 30));
		fGrandTotal.setText(p_order.getGrandTotal().toString());
		parameterPanel.add(fGrandTotal, new GridBagConstraints(1, 0, 1, 1, 0.0,0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));

		lPayAmt = new CLabel(Msg.translate(p_ctx, "PayAmt") + ":");
		lPayAmt.setFont(fontBold);
		fPayAmt.setFont(fontBold);
		parameterPanel.add(lPayAmt, new GridBagConstraints(0, 1, 1, 1, 0.0,	0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));

		fPayAmt.setPreferredSize(new Dimension(60, 30));
		parameterPanel.add(fPayAmt, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		CLabel f_Line = new CLabel ("________________");
		parameterPanel.add(f_Line, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		lReturnAmt = new CLabel(Msg.translate(p_ctx, "AmountReturned") + ":");
		lReturnAmt.setFont(fontBold);
		fReturnAmt.setFont(fontBold);
		parameterPanel.add(lReturnAmt, new GridBagConstraints(0, 3, 1, 1, 0.0,0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		parameterPanel.add(fReturnAmt, new GridBagConstraints(1, 3, 1, 1, 0.0,0.0, 
								GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));
		
		mainPanel.add(parameterPanel, BorderLayout.NORTH);
		
		VPaymentPanel pPayment = new VPaymentPanel(p_ctx, p_order,
				p_order.getC_POS_ID(), "X", p_posPanel);
		pp.add(pPayment);

		parameterPanel.add(pp.get(0).cashPayPanel(), new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0,
							GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		pp.get(0).f_PayAmt.setValue(new Double(0.0));
		pp.get(0).f_PayAmt.addFocusListener(this);
		pp.get(0).f_PayAmt.addVetoableChangeListener(this);
		f_Plus = p_posPanel.f_order.createButtonAction("Plus",
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));

		f_Plus.addActionListener(this);

		fMinus.add(bMinus = new CButton("-"));
		parameterPanel.add(f_Plus, new GridBagConstraints(3, 4, 1, 1, 0.0, 0.0,
							GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,0, 5, 5), 0, 0));
		// comandPanel
		commandPanel.setLayout(commandLayout);
		commandLayout.setAlignment(FlowLayout.RIGHT);
		commandLayout.setHgap(10);
		commandPanel.add(bCancel, null);
		commandPanel.add(bProcess, null);
		bProcess.addActionListener(this);
		bCancel.addActionListener(this);
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		calculate();
	}

	public void addTypePay() {
		add_file++;
		paymentPanel = new VPaymentPanel(p_ctx, p_order, p_order.getC_POS_ID(),	"K", p_posPanel);
		pp.add(paymentPanel);
		fMinus.add(bMinus = p_posPanel.f_order.createButtonAction("Minus",
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2)));
		// Generate Random Name
		String mirand = "t_" + rand.nextInt(10000);
		bMinus.setName(mirand);
		bMinus.addActionListener(this);
		// add parameter panel
		centerPanel.add(pp.get(pp.size() - 1).paymentPanel(),new GridBagConstraints(0, add_file, 2, 1, 0.0, 0.0,
						GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		centerPanel.add(fMinus.get(fMinus.size()-1),	new GridBagConstraints(3, add_file, 1, 1, 0.0, 0.0,
						GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		pp.get(pp.size()-1).panelTypePay.setName(mirand);
		pp.get(pp.size()-1).f_PayAmt.setValue(new Double(0.0));
		pp.get(pp.size()-1).f_PayAmt.addFocusListener(this);
		pp.get(pp.size()-1).f_PayAmt.addVetoableChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String temp_name = "";
		if (e.getSource().equals(f_Plus)) {
			addTypePay();
			calculate();
		} else if (e.getSource().equals(bProcess)) {
			calculate();
			// Process Payment: first Process Order (if needed)
			if (!p_order.isProcessed() && !p_posPanel.processOrder()) {
				ADialog.warn(0, this, Msg.getMsg(p_ctx, "PosOrderProcessFailed")+" "+Msg.translate(p_ctx, p_order.getProcessMsg()));
				return;
			}
			for (int x = 0; x < pp.size(); x++)
				pp.get(x).savePay();
			dispose();
			return;
		} else if (e.getSource().equals(bCancel)) {
			isPaid = false;
			dispose();
			return;
		}
		else if (((CButton) e.getSource()).getName().indexOf("t_") >= 0) {
			for (int i = 0; i < pp.size(); i++) {
				temp_name = ((CButton) e.getSource()).getName();
				if (fMinus.get(i).getName().equals(temp_name)) {
					pp.get(i).paymentPanel().removeAll();
					pp.remove(i);
					fMinus.get(i).removeAll();
					fMinus.remove(i);
				}
			}

			Component[] d = centerPanel.getComponents();
			for (int k = 0; k < d.length; k++) {
				if (d[k].getName() == temp_name) {
					centerPanel.remove(d[k]);
				}
			}

			calculate();
			scrollPanel.validate();
			scrollPanel.repaint();
		}

		mainPanel.validate();
	}

	public static boolean pay(VPOS posPanel) {
		PosPayment pay = new PosPayment(posPanel);
		pay.setMinimumSize(new Dimension(445, 580));
		pay.pack();

		AEnv.positionCenterScreen(pay);
		pay.setModal(true);
		pay.setVisible(true);
		return pay.isPaid();
	}

	private boolean isPaid() {
		return isPaid;
	}

	private void calculate() {
		BigDecimal mount = new BigDecimal(Env.ZERO.toString());
		for (int x = 0; x < pp.size(); x++) {
			if (pp.get(x).f_PayAmt.getValue() == null) {
				pp.get(x).f_PayAmt.setValue(Env.ZERO);
			}
			BigDecimal pay = new BigDecimal(String.valueOf(pp.get(x).f_PayAmt.getValue()));
			mount = mount.add(pay);
		}

		balance = p_order.getGrandTotal().subtract(mount);
		if (balance.compareTo(Env.ZERO) <= 0) {
			isPaid = true;
		}
		balance.setScale(2,BigDecimal.ROUND_HALF_UP);
		mount = mount.setScale(2,BigDecimal.ROUND_HALF_UP);
		
		p_posPanel.m_CurrentOrder.getC_Currency_ID();

		fBalance.setText(String.valueOf(balance.setScale(precision,BigDecimal.ROUND_HALF_UP)));
		fReturnAmt.setText(String.valueOf(balance.setScale(precision,BigDecimal.ROUND_HALF_UP)));
		fPayAmt.setText(String.valueOf(mount.setScale(precision,BigDecimal.ROUND_HALF_UP)));
	}

	@Override
	public void focusGained(FocusEvent e) {
		for (int x = 0; x < pp.size(); x++)
			if (e.getComponent().equals(pp.get(x).f_PayAmt)) {
				calculate();
			}
	}

	@Override
	public void focusLost(FocusEvent e) {
		for (int x = 0; x < pp.size(); x++)
			if (e.getComponent().equals(pp.get(x).f_PayAmt)) {
				calculate();
			}
	}
}