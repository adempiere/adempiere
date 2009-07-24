/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.plaf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.compiere.plaf.CompiereColor;
import org.compiere.plaf.CompiereLookAndFeel;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CTextField;
import org.compiere.swing.CToggleButton;
import org.compiere.swing.ColorBlind;
import org.compiere.util.Ini;
import org.compiere.util.MiniBrowser;
import org.compiere.util.ValueNamePair;

import sun.awt.AppContext;

/**
 *  Adempiere PLAF Editor.
 *  <p>
 *  start with <code>new AdempierePLAFEditor()</code>
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempierePLAFEditor.java,v 1.3 2006/07/30 00:52:24 jjanke Exp $
 */
public class PLAFEditor extends JDialog
	implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6010229234801706748L;


	/**
	 *  Default Constructor
	 *  Don't Show Example
	 */
	public PLAFEditor()
	{
		super();
		init(false);
	}   //  PLAFEditor

	/**
	 *  Constructor
	 *  @param showExample if true, show Example
	 */
	public PLAFEditor (boolean showExample)
	{
		super();
		init(true);
	}   //  PLAFEditor

	/**
	 *  Modal Dialog Constructor
	 *  @param owner
	 *  @param showExample if true, show Example
	 */
	public PLAFEditor(Dialog owner, boolean showExample)
	{
		super(owner, "", true);
		init(true);
	}   //  PLAFEditor

	/**
	 *  Modal Frame Constructor
	 *  @param owner
	 *  @param showExample if true, show Example
	 */
	public PLAFEditor(Frame owner, boolean showExample)
	{
		super(owner, "", true);
		init(true);
	}   //  PLAFEditor

	/**	Logger			*/
	private static Logger log = Logger.getLogger(PLAFEditor.class.getName());

	
	/**************************************************************************
	 *  Init Editor
	 *  @param showExample if true, show Example
	 */
	private void init (boolean showExample)
	{
		try
		{
			jbInit();
			dynInit();

			//  Display
			example.setVisible(showExample);
			blindLabel.setVisible(showExample);
			blindField.setVisible(showExample);
			AdempierePLAF.showCenterScreen(this);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}   //  PLAFEditor

	/** Diable Theme Field          */
	private boolean     m_setting = false;
	/** We did test for true color  */
	private boolean     m_colorTest = false;

	static ResourceBundle   s_res = ResourceBundle.getBundle("org.compiere.plaf.PlafRes");
	static Object[]         s_columns = new Object[] {"-0-", "-1-", "-2-", "-3-", "-O-", "-l-"};
	static Object[][]       s_data = new Object[][] {
		{"-00-", "-01-", "-02-", "-03-", "-0O-", "-0l-"},
		{"-10-", "-11-", "-12-", "-13-", "-1O-", "-1l-"},
		{"-20-", "-21-", "-22-", "-23-", "-2O-", "-2l-"},
		{"-30-", "-31-", "-32-", "-33-", "-3O-", "-3l-"},
		{"-O0-", "-O1-", "-O2-", "-O3-", "-OO-", "-Ol-"},
		{"-l0-", "-l1-", "-l2-", "-l3-", "-lO-", "-ll-"}};
	static Object[]         s_pos = new Object[] {"Top", "Left", "Bottom", "Right"};

	private CPanel mainPanel = new CPanel(new BorderLayout());
	private CPanel northPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private CButton bOK = AdempierePLAF.getOKButton();
	private CButton bCancel = AdempierePLAF.getCancelButton();
	private CButton bHelp = new CButton();	/** @todo Help Button */
	private GridBagLayout northLayout = new GridBagLayout();
	private CLabel lfLabel = new CLabel();
	private CComboBox lfField = new CComboBox(AdempierePLAF.getPLAFs());
	private CLabel themeLabel = new CLabel();
	private CComboBox themeField = new CComboBox(AdempierePLAF.getThemes());
	private FlowLayout southLayout = new FlowLayout();
	private CButton rButton = new CButton();
	private CComboBox blindField = new CComboBox(ColorBlind.COLORBLIND_TYPE);
	private CLabel blindLabel = new CLabel();
	private BorderLayout mainLayout = new BorderLayout();
	//
	private CTabbedPane example = new CTabbedPane();
	private CPanel jPanel1 = new CPanel();
	private TitledBorder exampleBorder;
	private CPanel jPanel2 = new CPanel();
	private JLabel jLabel1 = new JLabel();
	private JTextField jTextField1 = new JTextField();
	private JCheckBox jCheckBox1 = new JCheckBox();
	private JRadioButton jRadioButton1 = new JRadioButton();
	private CButton jButton1 = new CButton();
	private CToggleButton jToggleButton1 = new CToggleButton();
	private CComboBox jComboBox1 = new CComboBox(s_columns);
	private JTextArea jTextArea1 = new JTextArea();
	private JTextPane jTextPane1 = new JTextPane();
	private JEditorPane jEditorPane1 = new JEditorPane();
	private JPasswordField jPasswordField1 = new JPasswordField();
	private JList jList1 = new JList(s_columns);
	private JSplitPane jSplitPane1 = new JSplitPane();
	private BorderLayout borderLayout1 = new BorderLayout();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JTree jTree1 = new JTree();
	private JScrollPane jScrollPane2 = new JScrollPane();
	private JTable jTable1 = new JTable(s_data, s_columns);
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private CPanel jPanelFlat = new CPanel(new CompiereColor(new Color(255,205,255), true));
	private CPanel jPanelGradient = new CPanel(new CompiereColor(new Color(233,210,210), new Color(217,210,233)));
	private CPanel jPanelTexture = new CPanel(new CompiereColor(CompiereColor.class.getResource("vincent.jpg"), Color.lightGray, 0.7f));
	private CPanel jPanelLines = new CPanel(new CompiereColor(new Color(178,181,205), new Color(193,193,205), 1.0f, 5));
	private JButton jButtonFlat = new JButton();
	private CButton jButtonGardient = new CButton();
	private JButton jButtonTexture = new JButton();
	private CButton jButtonLines = new CButton();
	private JComboBox jComboBoxFlat = new JComboBox(s_pos);
	private JTextField jTextFieldFlat = new JTextField();
	private JLabel jLabelFlat = new JLabel();
	private CComboBox jComboBoxGradient = new CComboBox(s_pos);
	private CTextField jTextFieldGradient = new CTextField();
	private CLabel jLabelGradient = new CLabel();
	private JComboBox jComboBoxTexture = new JComboBox(s_pos);
	private JTextField jTextFieldTexture = new JTextField();
	private JLabel jLabelTexture = new JLabel();
	private CComboBox jComboBoxLines = new CComboBox(s_pos);
	private CTextField jTextFieldLines = new CTextField();
	private CLabel jLabelLines = new CLabel();
	private CCheckBox jCheckBoxLines = new CCheckBox();
	private JCheckBox jCheckBoxTexture = new JCheckBox();
	private CCheckBox jCheckBoxGradient = new CCheckBox();
	private JCheckBox jCheckBoxFlat = new JCheckBox();

	/**
	 *  Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle(s_res.getString("LookAndFeelEditor"));
		mainPanel.setLayout(mainLayout);
		mainLayout.setHgap(5);
		mainLayout.setVgap(5);
		jTextFieldFlat.setColumns(10);
		jTextFieldGradient.setColumns(10);
		jTextFieldTexture.setColumns(10);
		jTextFieldLines.setColumns(10);
		jCheckBoxLines.setText("jCheckBox");
		jCheckBoxTexture.setText("jCheckBox");
		jCheckBoxGradient.setText("jCheckBox");
		jCheckBoxFlat.setText("jCheckBox");
		jPanelGradient.setToolTipText("Indented Level 1");
		jPanelTexture.setToolTipText("Indented Level 2");
		jPanelLines.setToolTipText("Indented Level 1");
		this.getContentPane().add(mainPanel,  BorderLayout.CENTER);
		//
		lfLabel.setText(s_res.getString("LookAndFeel"));
		lfField.addActionListener(this);
		themeLabel.setText(s_res.getString("Theme"));
		themeField.addActionListener(this);
		rButton.setText(s_res.getString("Reset"));
		rButton.addActionListener(this);
		blindLabel.setText(s_res.getString("ColorBlind"));
		blindField.addActionListener(this);
		//
		bOK.addActionListener(this);
		bCancel.addActionListener(this);
		bHelp.addActionListener(this);
		//
		northPanel.setLayout(northLayout);
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		//
		exampleBorder = new TitledBorder(s_res.getString("Example"));
		example.setBorder(exampleBorder);

		jLabel1.setText("jLabel");
		jTextField1.setText("jTextField");
		jCheckBox1.setText("jCheckBox");
		jRadioButton1.setText("jRadioButton");
		jButton1.setText("jButton");
		jToggleButton1.setText("jToggleButton");
		jTextArea1.setText("jTextArea");
		jTextPane1.setText("jTextPane");
		jEditorPane1.setText("jEditorPane");
		jPasswordField1.setText("jPasswordField");
		jPanel2.setLayout(borderLayout1);
		jPanel1.setLayout(gridBagLayout1);
		jScrollPane1.setPreferredSize(new Dimension(100, 200));
		jScrollPane2.setPreferredSize(new Dimension(100, 200));
		jButtonFlat.setText("Confirm");
		jButtonGardient.setText("Input");
		jButtonTexture.setText("Message");
		jButtonLines.setText("Error");
		jTextFieldFlat.setText("jTextField");
		jLabelFlat.setText("jLabel");
		jTextFieldGradient.setText("jTextField");
		jLabelGradient.setText("jLabel");
		jTextFieldTexture.setText("jTextField");
		jLabelTexture.setText("jLabel");
		jTextFieldLines.setText("jTextField");
		jLabelLines.setText("jLabel");
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(lfLabel,     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 12, 5, 5), 0, 0));
		northPanel.add(lfField,     new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 5, 12), 0, 0));
		northPanel.add(themeLabel,      new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		northPanel.add(themeField,       new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		northPanel.add(rButton,        new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		northPanel.add(blindLabel,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		northPanel.add(blindField,   new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 12), 0, 0));
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(bCancel, null);
		southPanel.add(bOK, null);
		mainPanel.add(example, BorderLayout.CENTER);
		example.add(jPanel1,  "JPanel");
		jPanel1.add(jTextPane1,                 new GridBagConstraints(2, 3, 1, 1, 0.0, 0.2
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jEditorPane1,                  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.2
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jList1,                        new GridBagConstraints(1, 2, 1, 1, 0.0, 0.2
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jLabel1,            new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jTextField1,             new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jCheckBox1,           new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 0, 0));
		jPanel1.add(jRadioButton1,         new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jButton1,         new GridBagConstraints(2, 0, 1, 1, 0.0, 0.1
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jToggleButton1,         new GridBagConstraints(2, 1, 1, 1, 0.0, 0.1
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jTextArea1,         new GridBagConstraints(0, 3, 1, 1, 0.0, 0.2
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jComboBox1,      new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		jPanel1.add(jPasswordField1,     new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		example.add(jPanel2,  "JPanel");
		jPanel2.add(jSplitPane1,  BorderLayout.CENTER);
		jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
		jSplitPane1.add(jScrollPane2, JSplitPane.RIGHT);
		jPanelFlat.setName("FlatP");
		jPanelGradient.setName("GradientP");
		jPanelTexture.setName("TextureP");
		jPanelLines.setName("LineP");
		example.add(jPanelFlat, "jPanel Flat");
		jPanelFlat.add(jButtonFlat, null);
		jPanelFlat.add(jComboBoxFlat, null);
		example.add(jPanelGradient, "jPanel Gradient 1");
		jPanelGradient.add(jButtonGardient, null);
		jPanelGradient.add(jComboBoxGradient, null);
		jPanelGradient.add(jLabelGradient, null);
		jPanelGradient.add(jTextFieldGradient, null);
		example.add(jPanelTexture, "jPanel Texture 2");
		jPanelTexture.add(jButtonTexture, null);
		jPanelTexture.add(jComboBoxTexture, null);
		jPanelTexture.add(jLabelTexture, null);
		jPanelTexture.add(jTextFieldTexture, null);
		example.add(jPanelLines, "jPanel Lines 1");
		jPanelLines.add(jButtonLines, null);
		jPanelLines.add(jComboBoxLines, null);
		jPanelLines.add(jLabelLines, null);
		jPanelLines.add(jTextFieldLines, null);
		jScrollPane2.getViewport().add(jTable1, null);
		jScrollPane1.getViewport().add(jTree1, null);
		jPanelFlat.add(jLabelFlat, null);
		jPanelFlat.add(jTextFieldFlat, null);
		jPanelLines.add(jCheckBoxLines, null);
		jPanelTexture.add(jCheckBoxTexture, null);
		jPanelGradient.add(jCheckBoxGradient, null);
		jPanelFlat.add(jCheckBoxFlat, null);
	}   //  jbInit

	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		setLFSelection();
		//
		jPanelGradient.setTabLevel(1);
		jPanelTexture.setTabLevel(2);
		jPanelLines.setTabLevel(1);
		//
		jComboBoxFlat.addActionListener(this);
		jComboBoxGradient.addActionListener(this);
		jComboBoxTexture.addActionListener(this);
		jComboBoxLines.addActionListener(this);
		//
		jButton1.addActionListener(this);
		jButtonFlat.addActionListener(this);
		jButtonGardient.addActionListener(this);
		jButtonTexture.addActionListener(this);
		jButtonLines.addActionListener(this);
		//
	}   //  dynInit

	/**
	 *  Set Picks From Environment
	 */
	private void setLFSelection()
	{
		m_setting = true;
		//  Search for PLAF
		ValueNamePair plaf = null;
		LookAndFeel lookFeel = UIManager.getLookAndFeel();
		String look = lookFeel.getClass().getName();
		for (int i = 0; i < AdempierePLAF.getPLAFs().length; i++)
		{
			ValueNamePair vp = AdempierePLAF.getPLAFs()[i];
			if (vp.getValue().equals(look))
			{
				plaf = vp;
				break;
			}
		}
		if (plaf != null)
			lfField.setSelectedItem(plaf);


		//  Search for Theme
		MetalTheme metalTheme = null;
		ValueNamePair theme = null;
		boolean metal = UIManager.getLookAndFeel() instanceof MetalLookAndFeel;
		themeField.setModel(new DefaultComboBoxModel(AdempierePLAF.getThemes()));
		if (metal)
		{
			theme = null;
			AppContext context = AppContext.getAppContext();
			metalTheme = (MetalTheme)context.get("currentMetalTheme");
			if (metalTheme != null)
			{
				String lookTheme = metalTheme.getName();
				for (int i = 0; i < AdempierePLAF.getThemes().length; i++)
				{
					ValueNamePair vp = AdempierePLAF.getThemes()[i];
					if (vp.getName().equals(lookTheme))
					{
						theme = vp;
						break;
					}
				}
			}
			if (theme != null)
				themeField.setSelectedItem(theme);
		}
		m_setting = false;
		log.info(lookFeel + " - " + metalTheme);
	}   //  setLFSelection

	
	/**************************************************************************
	 *  ActionListener
	 *  @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		//  OK - Save & Finish
		if (e.getSource() == bOK)
		{
			Ini.saveProperties(true);
			dispose();
		}
		//  Cancel - Finish
		else if (e.getSource() == bCancel)
		{
			dispose();
		}
		else if (e.getSource() == bHelp)
		{
			new MiniBrowser("http://www.adempiere.com/wiki/index.php/LooksHelp");
		}

		//  Look & Feel changed
		else if (e.getSource() == lfField && !m_setting)
		{
			m_setting = true;   //  disable Theme setting
			//  set new theme
			AdempierePLAF.setPLAF((ValueNamePair)lfField.getSelectedItem(), null, true);
			AdempierePLAF.updateUI(this);
			setLFSelection();
			m_setting = false;  //  enable Theme setting
		}
		//  Theme Field Changed
		else if (e.getSource() == themeField && !m_setting)
		{
			Ini.setProperty(Ini.P_UI_THEME, themeField.getSelectedItem().toString());
			AdempierePLAF.setPLAF((ValueNamePair)lfField.getSelectedItem(), (ValueNamePair)themeField.getSelectedItem(),true);
			AdempierePLAF.updateUI(this);
		}

		//  Reset PLAFs
		else if (e.getSource() == rButton)
		{
			AdempierePLAF.reset();
			AdempierePLAF.updateUI(this);
			setLFSelection();
			ColorBlind.setColorType(ColorBlind.NORMAL);
		}

		//  ColorBlind
		else if (e.getSource() == blindField)
		{
			int sel = blindField.getSelectedIndex();
			if (sel != ColorBlind.getColorType())
			{
				//  Test for True color
				if (!m_colorTest)
				{
					m_colorTest = true;
					int size = Toolkit.getDefaultToolkit().getColorModel().getPixelSize();
					if (size < 24)
						JOptionPane.showMessageDialog(this,
							"Your environment has only a pixel size of " + size
							+ ".\nTo see the effect, you need to have a pixel size of 24 (true color)",
							"Insufficient Color Capabilities",
							JOptionPane.ERROR_MESSAGE);
				}
				ColorBlind.setColorType(sel);
				AdempierePLAF.updateUI(this);
			}
		}


		//  Change Tab Pacement
		else if (e.getSource() == jComboBoxFlat || e.getSource() == jComboBoxGradient
			|| e.getSource() == jComboBoxTexture || e.getSource() == jComboBoxLines)
		{
			if (!m_setting)
			{
				m_setting = true;
				int index = ((JComboBox)e.getSource()).getSelectedIndex();
				example.setTabPlacement(index+1);
				jComboBoxFlat.setSelectedIndex(index);
				jComboBoxGradient.setSelectedIndex(index);
				jComboBoxTexture.setSelectedIndex(index);
				jComboBoxLines.setSelectedIndex(index);
				m_setting = false;
			}
		}
		//  Display Options
		else if (e.getSource() == jButtonFlat)
			JOptionPane.showConfirmDialog(this, "Confirm Dialog");
		else if (e.getSource() == jButtonGardient)
			JOptionPane.showInputDialog(this, "Input Dialog");
		else if (e.getSource() == jButtonTexture)
			JOptionPane.showMessageDialog(this, "Message Dialog");
		else if (e.getSource() == jButtonLines)
			JOptionPane.showMessageDialog(this, "Message Dialog - Error", "Error", JOptionPane.ERROR_MESSAGE);

		//  Test
		else if (e.getSource() == jButton1)
		{
		}

		/********************/

		//  Metal
		boolean metal = UIManager.getLookAndFeel() instanceof MetalLookAndFeel;
		themeField.setEnabled(metal);
		themeLabel.setEnabled(metal);
		boolean adempiere = UIManager.getLookAndFeel() instanceof CompiereLookAndFeel;

		//  ColorBlind - only with Adempiere L&F & Theme
		boolean enableBlind = adempiere
			&& themeField.getSelectedItem() != null
			&& themeField.getSelectedItem().toString().indexOf("Adempiere") != -1;
		blindField.setEnabled(enableBlind);
		blindLabel.setEnabled(enableBlind);
		if (e.getSource() != blindField && !enableBlind)
			blindField.setSelectedIndex(0);

		//  done
		setCursor(Cursor.getDefaultCursor());
	}   //  actionPerformed

	/**
	 *  Dispose
	 *  Exit, if there is no real owning parent (not modal) - shortcut
	 */
	public void dispose()
	{
		super.dispose();
		if (!isModal())
			System.exit(0);
	}   //  dispose

}   //  AdempierePLAFEditor
