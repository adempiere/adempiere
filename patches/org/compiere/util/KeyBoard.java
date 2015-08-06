package org.compiere.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import org.compiere.apps.WJButton;
import org.compiere.apps.WJPasswordField;
import org.compiere.apps.WJTextField;
import org.compiere.swing.CDialog;

/**
 * Clase que muestra un teclado
 * 
 * @author Wilfredo Suarez
 * @version 1.1.1
 */
public class KeyBoard extends CDialog implements ActionListener, MouseListener, KeyListener {

	// Tipos de Teclado Numeric AlphaNumeric
	public static final int KEYBOARD_NUMERIC = 1;
	public static final int KEYBOARD_ALPHANUMERIC = 2;
	public static final int KEYBOARD_NUMERIC_CASHOUT = 3;
	public static final int TYPE_DIALOG = 100;
	public static final int TYPE_POPUP = 101;
	public static final int TYPE_PANEL = 102;

	private static final long serialVersionUID = 3243026003551783514L;
	private JPopupMenu popup = null;
	private int typeShowKeyboard = TYPE_DIALOG;
	private Component outComponent = null;
	private JPanel panel = null;
	private String title = null;
	private JPanel panelKeys = null;
	private JPanel panelNumKeys = null;
	private WJTextField txtField = null;
	private WJPasswordField txtFieldPass = null;
	private double cashOut = 0;
	private StringBuilder str = new StringBuilder(50);
	private JFrame owner = null;
	private JToggleButton btnShift = null;
	private Font fontTxt = new Font("Arial", Font.BOLD, 20);
	private Font fontBtn = new Font("Arial", Font.BOLD, 16);
	private Font fontBtnLittle = new Font("Arial", Font.BOLD, 14);
	private WJButton btnClean = null;
	private WJButton btnSpace = null;
	private WJButton btnEscape = null;
	private WJButton btnEnter = null;
	private WJButton btnEnterNumeric = null;
	private WJButton btnCleanNumeric = null;
	private WJButton btnCashOut = null;
	private String KEY_INTRO = "intro";
	private String KEY_ESCAPE = "escape";
	private String KEY_SPACE = "space";
	private String KEY_DEL = "del";
	private String KEY_SHIFT = "shift";
	private String KEY_COMMA = "comma";
	private String KEY_POINT = "point";
	private String KEY_DIV = "div";
	private String KEY_MULTIPLICAR = "multiplicar";
	private String KEY_RESTAR = "restar";
	private String KEY_SUMAR = "sumar";
	private String KEY_INTRO_NUMERIC = "intro_numeric";
	private String KEY_POINT_NUMERIC = "point_numeric";
	private String KEY_EQUALS_NUMERIC = "equal_numeric";
	private String KEY_CLEAN = "clear";
	private String KEY_CASHOUT = "cashout";
	private int typeKeyboard = KEYBOARD_ALPHANUMERIC;

	private KeyBoardCallBack callBack = null; // callback

	String keys[] = { "!", "@", "#", "$", "_", "\\", ":", "(", ")", "|", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ", "Z", "X", "C", "V", "B", "N", "M" };

	String keysNumericKeyBoard[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "10", "20", "50", "100" };

	/**
	 * Constructor por defecto
	 */
	public KeyBoard() {
		// TODO Auto-generated constructor stub
		super();
		// init();
	}

	/**
	 * 
	 * @param outComponent
	 *            El componente que mostrara la salida
	 * @param owner
	 *            El componente propietario
	 */
	public KeyBoard(Component outComponent, String title, int typeKeyboard, int typeLayoutKeyboard, KeyBoardCallBack callBack) {
		// TODO Auto-generated constructor stub

		this.outComponent = outComponent;
		this.title = title;
		this.typeKeyboard = typeKeyboard;
		this.typeShowKeyboard = typeLayoutKeyboard;
		this.callBack = callBack;

		this.setTitle(title);

		paintKeyBoard();

	}

	/**
	 * 
	 * @param outComponent
	 *            El componente que mostrara la salida
	 * @param owner
	 *            El componente propietario
	 */
	public KeyBoard(JFrame owner, Component outComponent, String title, int typeKeyboard, int typeLayoutKeyboard, KeyBoardCallBack callBack) {
		// TODO Auto-generated constructor stub
		super(owner, title);

		this.outComponent = outComponent;
		this.title = title;
		this.typeKeyboard = typeKeyboard;
		this.typeShowKeyboard = typeLayoutKeyboard;
		this.callBack = callBack;

		paintKeyBoard();

	}

	/**
	 * 
	 * @param outComponent
	 *            El componente que mostrara la salida
	 * @param owner
	 *            El componente propietario
	 */
	public KeyBoard(JDialog owner, Component outComponent, String title, int typeKeyboard, int typeLayoutKeyboard, KeyBoardCallBack callBack) {
		// TODO Auto-generated constructor stub
		super(owner, title);

		this.outComponent = outComponent;
		this.title = title;
		this.typeKeyboard = typeKeyboard;
		this.typeShowKeyboard = typeLayoutKeyboard;
		this.callBack = callBack;

		paintKeyBoard();

	}

	public void paintKeyBoard() {

		panel = new JPanel(new BorderLayout());

		panel.setLayout(new GridBagLayout());
		panel.setOpaque(true);
		// panel.setBackground(Color.black);

		drawKeyBoard();
		setModal(true);

		switch (typeShowKeyboard) {
		case TYPE_DIALOG:

			setDefaultCloseOperation(CDialog.DISPOSE_ON_CLOSE);
			setResizable(false);
			setUndecorated(false);

			this.add(panel);

			pack();

			if (this.owner != null) {

				setLocationRelativeTo(this.owner);

			} else {

				setLocationRelativeTo(null);

			}

			setMayus(false);

			if (outComponent != null) {

				if (!this.outComponent.isEnabled()) {

					this.dispose();
					return;

				}

			}

			setVisible(true);

			break;
		case TYPE_POPUP:

			popup = new JPopupMenu();
			popup.add(panel);
			
			setMayus(false);
			
			// popup.show(outComponent, 10, (int)
			// outComponent.getPreferredSize().getHeight());

			if (outComponent != null) {

				popup.show(outComponent, 0, 0);

			}

			break;
		case TYPE_PANEL:
			break;
		default:
			break;
		}

	}

	private void drawKeyBoard() {

		GridBagConstraints c = new GridBagConstraints();
		int gridx = 0;
		int gridy = 0;

		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipady = 30;

		if (outComponent instanceof JPasswordField) {

			txtFieldPass = new WJPasswordField();
			txtFieldPass.setPreferredSize(new Dimension(10, 30));
			txtFieldPass.setFont(fontTxt);
			txtFieldPass.setForeground(Color.DARK_GRAY);
			txtFieldPass.setFocusable(false);
			txtFieldPass.addKeyListener(this);

			panel.add(txtFieldPass, c);

		} else if (outComponent instanceof JTextField) {

			txtField = new WJTextField();
			txtField.setPreferredSize(new Dimension(10, 30));
			txtField.setFont(fontTxt);
			txtField.setForeground(Color.DARK_GRAY);
			txtField.setFocusable(false);
			txtField.addKeyListener(this);

			panel.add(txtField, c);

		} else {

			txtField = new WJTextField();
			txtField.setPreferredSize(new Dimension(10, 30));
			txtField.setFont(fontTxt);
			txtField.setForeground(Color.DARK_GRAY);
			txtField.setFocusable(false);
			txtField.addKeyListener(this);

			panel.add(txtField, c);

		}

		gridx = 0;
		gridy++;

		panelKeys = new JPanel();
		panelKeys.setLayout(new GridBagLayout());

		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.weighty = 0.0;
		panel.add(panelKeys, c);
		gridx++;

		panelNumKeys = new JPanel();
		panelNumKeys.setLayout(new GridBagLayout());
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = 0;
		c.ipady = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(0, 0, 0, 0);
		c.weighty = 0.0;
		panel.add(panelNumKeys, c);

		switch (typeKeyboard) {

		case KEYBOARD_ALPHANUMERIC:

			panelKeys.setBorder(new BevelBorder(BevelBorder.LOWERED));
			panelNumKeys.setBorder(new BevelBorder(BevelBorder.LOWERED));

			drawKeys(panelKeys);
			drawKeysNumeric(panelNumKeys, typeKeyboard);

			break;
		case KEYBOARD_NUMERIC:

			drawKeysNumeric(panelNumKeys, typeKeyboard);
			
			txtField.setHorizontalAlignment(JTextField.RIGHT);
			
			break;
		case KEYBOARD_NUMERIC_CASHOUT:

			drawKeysNumeric(panelNumKeys, typeKeyboard);
			txtField.setHorizontalAlignment(JTextField.RIGHT);

			break;

		default:
			break;

		}

	}

	private void drawKeys(JPanel panelKeys) {

		GridBagConstraints c = new GridBagConstraints();
		int gridx = 0;
		int gridy = 0;
		int countKeys = 0;
		int levels[] = { 10, 10, 10, 7 };

		c.gridwidth = 1;
		c.ipadx = 40;
		c.ipady = 40;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		gridy++;

		for (int j = 0; j < levels.length; j++) {

			if (levels[j] == 7) {

				btnShift = new JToggleButton("Shift");
				btnShift.setFont(fontBtn);
				btnShift.setToolTipText("Shift");
				btnShift.addActionListener(this);
				btnShift.setActionCommand(KEY_SHIFT);
				btnShift.setPreferredSize(new Dimension(40, 40));
				btnShift.setFocusable(false);
				c.gridx = gridx;
				c.gridy = gridy;
				panelKeys.add(btnShift, c);
				gridx++;

				btnClean = new WJButton("Clear");
				btnClean.setFocusable(false);
				btnClean.setFont(fontBtn);
				btnClean.addActionListener(this);
				btnClean.setActionCommand(KEY_CLEAN);
				btnClean.setPreferredSize(new Dimension(40, 40));
				c.gridx = gridx;
				c.gridy = gridy;
				panelKeys.add(btnClean, c);

			}

			for (int i = 0; i < levels[j]; i++) {

				if (countKeys == keys.length) {

					break;

				}

				WJButton btn = new WJButton(keys[countKeys]);
				btn.setFocusable(false);
				btn.setFont(fontBtn);
				btn.addActionListener(this);
				btn.setActionCommand(keys[countKeys]);
				btn.setPreferredSize(new Dimension(40, 40));

				c.gridx = gridx;
				c.gridy = gridy;
				panelKeys.add(btn, c);

				gridx++;
				countKeys++;

			}

			gridx = 0;
			gridy++;

		}

		gridx = 8;
		gridy--;

		WJButton btnDel = new WJButton("DEL");
		btnDel.setFont(fontBtn);
		btnDel.addActionListener(this);
		btnDel.addMouseListener(this);
		btnDel.setActionCommand(KEY_DEL);
		btnDel.setPreferredSize(new Dimension(40, 40));
		btnDel.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		panelKeys.add(btnDel, c);
		gridx = c.gridwidth;

		gridx = 0;
		gridy++;

		btnEscape = new WJButton("ESC", WJButton.TYPE_EXIT);
		btnEscape.setFont(fontBtn);
		btnEscape.addActionListener(this);
		btnEscape.setActionCommand(KEY_ESCAPE);
		btnEscape.setPreferredSize(new Dimension(40, 40));
		btnEscape.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		panelKeys.add(btnEscape, c);
		gridx = c.gridwidth;

		WJButton btnComma = new WJButton(",");
		btnComma.setFont(fontBtn);
		btnComma.addActionListener(this);
		btnComma.setActionCommand(KEY_COMMA);
		btnComma.setPreferredSize(new Dimension(40, 40));
		btnComma.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
		panelKeys.add(btnComma, c);
		gridx++;

		btnSpace = new WJButton();
		btnSpace.setFont(fontBtn);
		btnSpace.addActionListener(this);
		btnSpace.setActionCommand(KEY_SPACE);
		btnSpace.setPreferredSize(new Dimension(40, 40));
		btnSpace.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 4;
		panelKeys.add(btnSpace, c);
		gridx = gridx + c.gridwidth;

		WJButton btnPoint = new WJButton(".");
		btnPoint.setFont(fontBtn);
		btnPoint.addActionListener(this);
		btnPoint.setActionCommand(KEY_POINT);
		btnPoint.setPreferredSize(new Dimension(40, 40));
		btnPoint.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 1;
		panelKeys.add(btnPoint, c);
		gridx++;

		btnEnter = new WJButton("ENTER", WJButton.TYPE_EXIT);
		btnEnter.setFont(fontBtn);
		btnEnter.addActionListener(this);
		btnEnter.setActionCommand(KEY_INTRO);
		btnEnter.setPreferredSize(new Dimension(40, 40));
		btnEnter.setFocusable(false);
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = 2;
		panelKeys.add(btnEnter, c);
		gridx += 2;
		gridy = 0;

	}

	private void drawKeysNumeric(JPanel panelNumKeys, int typeKeyboard) {

		WJButton btnEquals = null;
		WJButton btnDiv = null;
		WJButton btnMultiplicar = null;
		WJButton btnRestar = null;
		WJButton btn7 = null;
		WJButton btn8 = null;
		WJButton btn9 = null;
		WJButton btn4 = null;
		WJButton btn5 = null;
		WJButton btn6 = null;
		WJButton btnSumar = null;
		WJButton btn1 = null;
		WJButton btn2 = null;
		WJButton btn3 = null;
		WJButton btn0 = null;
		WJButton btnPointNumeric = null;
		// btnIntroNumeric esta al inicio
		WJButton btn10 = null;
		WJButton btn20 = null;
		WJButton btn50 = null;
		WJButton btn100 = null;
		// btnCleanNumeric esta al inicio

		GridBagConstraints c = new GridBagConstraints();

		int gridx = 0;
		int gridy = 0;

		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = 40;
		c.ipady = 40;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(0, 0, 0, 0);
		c.weighty = 0.0;

		switch (typeKeyboard) {

		case KEYBOARD_ALPHANUMERIC:

			btnEquals = new WJButton("=");
			btnEquals.setFont(fontBtn);
			btnEquals.addActionListener(this);
			btnEquals.setActionCommand(KEY_EQUALS_NUMERIC);
			btnEquals.setPreferredSize(new Dimension(40, 40));
			btnEquals.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btnEquals, c);
			gridx++;

			btnDiv = new WJButton("/");
			btnDiv.setFont(fontBtn);
			btnDiv.addActionListener(this);
			btnDiv.setActionCommand(KEY_DIV);
			btnDiv.setPreferredSize(new Dimension(40, 40));
			btnDiv.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btnDiv, c);
			gridx++;

			btnMultiplicar = new WJButton("*");
			btnMultiplicar.setFont(fontBtn);
			btnMultiplicar.addActionListener(this);
			btnMultiplicar.setActionCommand(KEY_MULTIPLICAR);
			btnMultiplicar.setPreferredSize(new Dimension(40, 40));
			btnMultiplicar.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btnMultiplicar, c);
			gridx++;

			btnRestar = new WJButton("-");
			btnRestar.setFont(fontBtn);
			btnRestar.addActionListener(this);
			btnRestar.setActionCommand(KEY_RESTAR);
			btnRestar.setPreferredSize(new Dimension(40, 40));
			btnRestar.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btnRestar, c);
			gridx = 0;
			gridy++;

			btn7 = new WJButton(keysNumericKeyBoard[6]);
			btn7.setFocusable(false);
			btn7.setFont(fontBtn);
			btn7.addActionListener(this);
			btn7.setActionCommand(keysNumericKeyBoard[6]);
			btn7.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn7, c);
			gridx++;

			btn8 = new WJButton(keysNumericKeyBoard[7]);
			btn8.setFocusable(false);
			btn8.setFont(fontBtn);
			btn8.addActionListener(this);
			btn8.setActionCommand(keysNumericKeyBoard[7]);
			btn8.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn8, c);
			gridx++;

			btn9 = new WJButton(keysNumericKeyBoard[8]);
			btn9.setFocusable(false);
			btn9.setFont(fontBtn);
			btn9.addActionListener(this);
			btn9.setActionCommand(keysNumericKeyBoard[8]);
			btn9.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn9, c);
			gridx = 0;
			gridy++;

			btn4 = new WJButton(keysNumericKeyBoard[3]);
			btn4.setFocusable(false);
			btn4.setFont(fontBtn);
			btn4.addActionListener(this);
			btn4.setActionCommand(keysNumericKeyBoard[3]);
			btn4.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			c.fill = GridBagConstraints.NONE;
			panelNumKeys.add(btn4, c);
			gridx++;

			btn5 = new WJButton(keysNumericKeyBoard[4]);
			btn5.setFocusable(false);
			btn5.setFont(fontBtn);
			btn5.addActionListener(this);
			btn5.setActionCommand(keysNumericKeyBoard[4]);
			btn5.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			c.fill = GridBagConstraints.NONE;
			panelNumKeys.add(btn5, c);
			gridx++;

			btn6 = new WJButton(keysNumericKeyBoard[5]);
			btn6.setFocusable(false);
			btn6.setFont(fontBtn);
			btn6.addActionListener(this);
			btn6.setActionCommand(keysNumericKeyBoard[5]);
			btn6.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			c.fill = GridBagConstraints.NONE;
			panelNumKeys.add(btn6, c);
			gridx++;
			gridy--;

			btnSumar = new WJButton("+");
			btnSumar.setFont(fontBtn);
			btnSumar.addActionListener(this);
			btnSumar.setActionCommand(KEY_SUMAR);
			btnSumar.setPreferredSize(new Dimension(40, 40));
			btnSumar.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.fill = GridBagConstraints.BOTH;
			c.gridheight = 2;
			panelNumKeys.add(btnSumar, c);
			gridx = 0;
			gridy += 2;

			btn1 = new WJButton(keysNumericKeyBoard[0]);
			btn1.setFocusable(false);
			btn1.setFont(fontBtn);
			btn1.addActionListener(this);
			btn1.setActionCommand(keysNumericKeyBoard[0]);
			btn1.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridheight = 1;
			c.fill = GridBagConstraints.NONE;
			panelNumKeys.add(btn1, c);
			gridx++;

			btn2 = new WJButton(keysNumericKeyBoard[1]);
			btn2.setFocusable(false);
			btn2.setFont(fontBtn);
			btn2.addActionListener(this);
			btn2.setActionCommand(keysNumericKeyBoard[1]);
			btn2.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn2, c);
			gridx++;

			btn3 = new WJButton(keysNumericKeyBoard[2]);
			btn3.setFocusable(false);
			btn3.setFont(fontBtn);
			btn3.addActionListener(this);
			btn3.setActionCommand(keysNumericKeyBoard[2]);
			btn3.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn3, c);
			gridx = 0;
			gridy++;

			btn0 = new WJButton(keysNumericKeyBoard[9]);
			btn0.setFocusable(false);
			btn0.setFont(fontBtn);
			btn0.addActionListener(this);
			btn0.setActionCommand(keysNumericKeyBoard[9]);
			btn0.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			c.fill = GridBagConstraints.BOTH;
			c.gridwidth = 2;
			panelNumKeys.add(btn0, c);
			gridx += 2;

			btnPointNumeric = new WJButton(".");
			btnPointNumeric.setFont(fontBtn);
			btnPointNumeric.addActionListener(this);
			btnPointNumeric.setActionCommand(KEY_POINT_NUMERIC);
			btnPointNumeric.setPreferredSize(new Dimension(40, 40));
			btnPointNumeric.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = 1;
			c.fill = GridBagConstraints.NONE;
			panelNumKeys.add(btnPointNumeric, c);
			gridx++;
			gridy--;

			btnEnterNumeric = new WJButton("ENTER", WJButton.TYPE_EXIT);
			btnEnterNumeric.setFont(fontBtn);
			btnEnterNumeric.addActionListener(this);
			btnEnterNumeric.setActionCommand(KEY_INTRO_NUMERIC);
			btnEnterNumeric.setPreferredSize(new Dimension(40, 40));
			btnEnterNumeric.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridheight = 2;
			c.fill = GridBagConstraints.BOTH;
			panelNumKeys.add(btnEnterNumeric, c);
			gridx = 0;
			gridy++;

			break;

		case KEYBOARD_NUMERIC:
		case KEYBOARD_NUMERIC_CASHOUT:

			panelNumKeys.setBorder(null);

			c.fill = GridBagConstraints.BOTH;

			btn7 = new WJButton(keysNumericKeyBoard[6]);
			btn7.setFocusable(false);
			btn7.setFont(fontBtn);
			btn7.addActionListener(this);
			btn7.setActionCommand(keysNumericKeyBoard[6]);
			btn7.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn7, c);
			gridx++;

			btn8 = new WJButton(keysNumericKeyBoard[7]);
			btn8.setFocusable(false);
			btn8.setFont(fontBtn);
			btn8.addActionListener(this);
			btn8.setActionCommand(keysNumericKeyBoard[7]);
			btn8.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn8, c);
			gridx++;

			btn9 = new WJButton(keysNumericKeyBoard[8]);
			btn9.setFocusable(false);
			btn9.setFont(fontBtn);
			btn9.addActionListener(this);
			btn9.setActionCommand(keysNumericKeyBoard[8]);
			btn9.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn9, c);
			gridx++;

			btn10 = new WJButton(keysNumericKeyBoard[10]);
			btn10.setFocusable(false);
			btn10.setFont(fontBtn);
			btn10.addActionListener(this);
			btn10.setActionCommand(keysNumericKeyBoard[10]);
			btn10.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn10, c);
			gridx = 0;
			gridy++;

			btn4 = new WJButton(keysNumericKeyBoard[3]);
			btn4.setFocusable(false);
			btn4.setFont(fontBtn);
			btn4.addActionListener(this);
			btn4.setActionCommand(keysNumericKeyBoard[3]);
			btn4.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn4, c);
			gridx++;

			btn5 = new WJButton(keysNumericKeyBoard[4]);
			btn5.setFocusable(false);
			btn5.setFont(fontBtn);
			btn5.addActionListener(this);
			btn5.setActionCommand(keysNumericKeyBoard[4]);
			btn5.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn5, c);
			gridx++;

			btn6 = new WJButton(keysNumericKeyBoard[5]);
			btn6.setFocusable(false);
			btn6.setFont(fontBtn);
			btn6.addActionListener(this);
			btn6.setActionCommand(keysNumericKeyBoard[5]);
			btn6.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn6, c);
			gridx++;

			btn20 = new WJButton(keysNumericKeyBoard[11]);
			btn20.setFocusable(false);
			btn20.setFont(fontBtn);
			btn20.addActionListener(this);
			btn20.setActionCommand(keysNumericKeyBoard[11]);
			btn20.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn20, c);
			gridx = 0;
			gridy++;

			btn1 = new WJButton(keysNumericKeyBoard[0]);
			btn1.setFocusable(false);
			btn1.setFont(fontBtn);
			btn1.addActionListener(this);
			btn1.setActionCommand(keysNumericKeyBoard[0]);
			btn1.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn1, c);
			gridx++;

			btn2 = new WJButton(keysNumericKeyBoard[1]);
			btn2.setFocusable(false);
			btn2.setFont(fontBtn);
			btn2.addActionListener(this);
			btn2.setActionCommand(keysNumericKeyBoard[1]);
			btn2.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn2, c);
			gridx++;

			btn3 = new WJButton(keysNumericKeyBoard[2]);
			btn3.setFocusable(false);
			btn3.setFont(fontBtn);
			btn3.addActionListener(this);
			btn3.setActionCommand(keysNumericKeyBoard[2]);
			btn3.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn3, c);
			gridx++;

			btn50 = new WJButton(keysNumericKeyBoard[12]);
			btn50.setFocusable(false);
			btn50.setFont(fontBtn);
			btn50.addActionListener(this);
			btn50.setActionCommand(keysNumericKeyBoard[12]);
			btn20.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn50, c);
			gridx = 0;
			gridy++;

			btnCleanNumeric = new WJButton("C");
			btnCleanNumeric.setFocusable(false);
			btnCleanNumeric.setFont(fontBtn);
			btnCleanNumeric.addActionListener(this);
			btnCleanNumeric.setActionCommand(KEY_CLEAN);
			btnCleanNumeric.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btnCleanNumeric, c);
			gridx++;

			btn0 = new WJButton(keysNumericKeyBoard[9]);
			btn0.setFocusable(false);
			btn0.setFont(fontBtn);
			btn0.addActionListener(this);
			btn0.setActionCommand(keysNumericKeyBoard[9]);
			btn0.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn0, c);
			gridx++;

			btnPointNumeric = new WJButton(".");
			btnPointNumeric.setFont(fontBtn);
			btnPointNumeric.addActionListener(this);
			btnPointNumeric.setActionCommand(KEY_POINT_NUMERIC);
			btnPointNumeric.setPreferredSize(new Dimension(40, 40));
			btnPointNumeric.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = 1;
			panelNumKeys.add(btnPointNumeric, c);
			gridx++;

			btn100 = new WJButton(keysNumericKeyBoard[13]);
			btn100.setFocusable(false);
			btn100.setFont(fontBtn);
			btn100.addActionListener(this);
			btn100.setActionCommand(keysNumericKeyBoard[13]);
			btn100.setPreferredSize(new Dimension(40, 40));
			c.gridx = gridx;
			c.gridy = gridy;
			panelNumKeys.add(btn100, c);
			gridx = 0;
			gridy++;

			if (typeKeyboard == KEYBOARD_NUMERIC_CASHOUT) {

				btnCashOut = new WJButton("Cash Out");
				btnCashOut.setFocusable(false);
				btnCashOut.setFont(fontBtnLittle);
				btnCashOut.addActionListener(this);
				btnCashOut.setActionCommand(KEY_CASHOUT);
				btnCashOut.setPreferredSize(new Dimension(40, 40));
				c.gridx = gridx;
				c.gridy = gridy;
				c.gridwidth = 4;
				c.ipady = 15;
				panelNumKeys.add(btnCashOut, c);
				gridx = 0;
				gridy++;

			}

			btnEscape = new WJButton("ESC", WJButton.TYPE_EXIT);
			btnEscape.setFont(fontBtn);
			btnEscape.addActionListener(this);
			btnEscape.setActionCommand(KEY_ESCAPE);
			btnEscape.setPreferredSize(new Dimension(40, 40));
			btnEscape.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = 2;
			c.ipady = 40;
			c.fill = GridBagConstraints.BOTH;
			panelNumKeys.add(btnEscape, c);
			gridx += 2;

			btnEnterNumeric = new WJButton("ENTER", WJButton.TYPE_EXIT);
			btnEnterNumeric.setFont(fontBtn);
			btnEnterNumeric.addActionListener(this);
			btnEnterNumeric.setActionCommand(KEY_INTRO_NUMERIC);
			btnEnterNumeric.setPreferredSize(new Dimension(40, 40));
			btnEnterNumeric.setFocusable(false);
			c.gridx = gridx;
			c.gridy = gridy;
			c.gridwidth = 2;
			c.fill = GridBagConstraints.BOTH;
			panelNumKeys.add(btnEnterNumeric, c);
			gridx = 0;
			gridy++;

			break;
		}

	}

	private void setMayus(boolean mayus) {

		Component obj[] = panelKeys.getComponents();
		int size = obj.length;
		int length = keys.length;
		String action = null;

		WJButton btn = null;

		for (int i = 0; i < size; i++) {

			if (obj[i] instanceof JButton) {

				btn = (WJButton) obj[i];
				action = btn.getActionCommand();

				for (int j = 0; j < length; j++) {

					if (action.equalsIgnoreCase(keys[j])) {

						if (mayus) {

							btn.setText(action.toUpperCase());
							btn.setActionCommand(action.toUpperCase());

						} else {

							btn.setText(action.toLowerCase());
							btn.setActionCommand(action.toLowerCase());

						}

					}

				}

			}

		}

	}

	private void setText(StringBuilder strBuilder) {

		if (txtField != null) {

			txtField.setText(strBuilder.toString());

		} else if (txtFieldPass != null) {

			txtFieldPass.setText(new String(strBuilder.toString()));

		}

	}

	public KeyBoardCallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(KeyBoardCallBack callBack) {
		this.callBack = callBack;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String action = e.getActionCommand();
		String text = null;

		if (txtField != null) {

			text = txtField.getText();

		} else if (txtFieldPass != null) {

			text = new String(txtFieldPass.getPassword());

		}

		if (action.equals(KEY_INTRO) || action.equals(KEY_INTRO_NUMERIC)) {

			if (text.length() > 0) {

				if (outComponent instanceof JPasswordField) {

					((JPasswordField) outComponent).setText(new String(text.toString()));

				} else if (outComponent instanceof JTextField) {

					((JTextField) outComponent).setText(text.toString());

				}

				Thread t = new Thread() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						super.run();

						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (getCallBack() != null) {

							getCallBack().callBack();

						}
					}

				};

				t.start();

				switch (typeShowKeyboard) {
				case TYPE_DIALOG:
					this.dispose();
					break;
				case TYPE_POPUP:
					popup.setVisible(false);
					break;
				default:
					break;
				}

			}

		} else if (action.equals(KEY_CASHOUT)) {

			if (outComponent instanceof JTextField) {

				((JTextField) outComponent).setText("" + getCashOut());

			}

			Thread t = new Thread() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if (getCallBack() != null) {

						getCallBack().callBack();

					}
				}

			};

			t.start();

			switch (typeShowKeyboard) {
			case TYPE_DIALOG:
				this.dispose();
				break;
			case TYPE_POPUP:
				popup.setVisible(false);
				break;
			default:
				break;
			}

		} else if (action.equals(KEY_ESCAPE)) {

			switch (typeShowKeyboard) {
			case TYPE_DIALOG:
				this.dispose();
				break;
			case TYPE_POPUP:
				popup.setVisible(false);
				break;
			default:
				break;
			}

		} else if (action.equals(KEY_SHIFT)) {

			if (btnShift.getModel().isSelected()) {

				setMayus(true);

			} else {

				setMayus(false);

			}

		} else if (action.equals(KEY_SPACE)) {

			if (str.length() > 0) {

				str = str.append(" ");
				setText(str);

			}

		} else if (action.equals(KEY_DEL)) {

			if (str.length() > 0) {

				str = str.delete(str.length() - 1, str.length());
				setText(str);

			}

		} else if (action.equals(KEY_COMMA)) {

			str = str.append(",");
			setText(str);

		} else if (action.equals(KEY_POINT)) {

			str = str.append(".");
			setText(str);

		} else if (action.equals(KEY_EQUALS_NUMERIC)) {

			str = str.append("=");
			setText(str);

		} else if (action.equals(KEY_DIV)) {

			str = str.append("/");
			setText(str);

		} else if (action.equals(KEY_MULTIPLICAR)) {

			str = str.append("*");
			setText(str);

		} else if (action.equals(KEY_RESTAR)) {

			str = str.append("-");
			setText(str);

		} else if (action.equals(KEY_SUMAR)) {

			str = str.append("+");
			setText(str);

		} else if (action.equals(KEY_CLEAN)) {

			str = str.delete(0, str.length());
			setText(str);

		} else if (action.equals(KEY_POINT_NUMERIC)) {

			DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
			DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
			char sep = symbols.getDecimalSeparator();

			str = str.append(sep);
			setText(str);

		} else {

			str.append(action);
			setText(str);

		}

	}

	public double getCashOut() {
		return cashOut;
	}

	public void setCashOut(double cashOut) {
		this.cashOut = cashOut;
	}

	public int getTypeKeyboard() {
		return typeKeyboard;
	}

	public void setTypeKeyboard(int typeKeyboard) {
		this.typeKeyboard = typeKeyboard;
	}

	public int getTypeShowKeyboard() {
		return typeShowKeyboard;
	}

	public void setTypeShowKeyboard(int typeShowKeyboard) {
		this.typeShowKeyboard = typeShowKeyboard;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Component getOutComponent() {
		return outComponent;
	}

	public void setOutComponent(Component outComponent) {
		this.outComponent = outComponent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public WJButton getBtnEscape() {
		return btnEscape;
	}

	public WJButton getBtnEnter() {
		return btnEnter;
	}

	public WJButton getBtnEnterNumeric() {
		return btnEnterNumeric;
	}

	public WJButton getBtnCleanNumeric() {
		return btnCleanNumeric;
	}

	public WJButton getBtnCashOut() {
		return btnCashOut;
	}

	public WJTextField getTxtField() {
		return txtField;
	}

	public WJPasswordField getTxtFieldPass() {
		return txtFieldPass;
	}

	public double getTxtFieldValueAsDouble() {
		return Double.parseDouble((txtField.getText().trim().equals("")) ? "0" : txtField.getText());
	}

	public JFrame getOwner() {
		return owner;
	}

	public void setOwner(JFrame owner) {
		this.owner = owner;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {

			btnEnter.doClick();

		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			btnEscape.doClick();

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
