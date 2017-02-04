/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.plaf;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CTextField;
import org.compiere.util.ValueNamePair;

import sun.awt.AppContext;

/**
 * Look and feel selection panel.
 * @author Low Heng Sin
 * @version 2006-11-27
 */
public class PLAFEditorPanel extends CPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3991197818429424319L;

	/**	Logger			*/
	private static Logger log = Logger.getLogger(PLAFEditor.class.getName());
	
	static ResourceBundle   s_res = ResourceBundle.getBundle("org.compiere.plaf.PlafRes");
	static Object[]         s_columns = new Object[] {"-0-", "-1-"};
	static Object[][]       s_data = new Object[][] {
		{"-00-", "-01-"},
		{"-10-", "-11-"},
		{"-20-", "-21-"},
		{"-30-", "-31-"},
		{"-O0-", "-O1-"},
		{"-l0-", "-l1-"}};
	
	private PreviewPanel previewPanel = new PreviewPanel();
	private JList lookList = new JList(AdempierePLAF.getPLAFs());
	private JList themeList = new JList();
	
	/** 
	 * Instance variable to disable list event handling when updating list with value 
	 * from UIManager 
	 */
	private boolean m_setting;
	
	//preview components
	private CTextField error = new CTextField(s_res.getString("Error"));
	private CTextField mandatory = new CTextField(s_res.getString("Mandatory"));
	CButton button = new CButton("Button");
	CPanel tabPage1 = new CPanel();
	
	public PLAFEditorPanel() {
		init();
	}
	
	private void init() {
		setupUI();
		setupPreview();
		setLFSelection();
	}
	
	/**
	 * Create and layout UI components
	 */
	private void setupUI() {
		this.setLayout(new BorderLayout());
		
		CPanel selectionPanel = new CPanel();
		CPanel previewPart = new CPanel();
		add(selectionPanel, BorderLayout.CENTER);
		add(previewPart, BorderLayout.SOUTH);
		
		//setup look and theme selection component 
		selectionPanel.setLayout(new GridBagLayout());
		CLabel label = new CLabel(s_res.getString("LookAndFeel"));
		label.setForeground(AdempierePLAF.getPrimary1());
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		selectionPanel.add(label, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		label = new CLabel(s_res.getString("Theme"));
		label.setForeground(AdempierePLAF.getPrimary1());
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		selectionPanel.add(label, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 0, 0), 0, 0));
		
		lookList.setVisibleRowCount(12);
		JScrollPane scrollPane = new JScrollPane(lookList);
		scrollPane.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getSecondary1(), 1));
		selectionPanel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, 
				GridBagConstraints.NORTHWEST, GridBagConstraints.VERTICAL, new Insets(0, 5, 2, 2), 100, 0));
		
		themeList.setVisibleRowCount(12);
		scrollPane = new JScrollPane(themeList);
		scrollPane.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getSecondary1(), 1));
		selectionPanel.add(scrollPane, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, 
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 2, 2, 5), 0, 0));
		
		previewPart.setBorder(BorderFactory.createEmptyBorder());
		previewPart.setLayout(new GridBagLayout());
		label = new CLabel(s_res.getString("Preview"));
		label.setForeground(AdempierePLAF.getPrimary1());
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		previewPart.add(label, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, 
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		previewPart.add(previewPanel, new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0, 
				GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 5, 5, 5), 0, 0));
		
		lookList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				lookAndFeelSelectionChanged(e);
			}
			
		});
		themeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				themeSelectionChanged(e);
			}
			
		});
	}
	
	/**
	 * Handle theme selection changed
	 * @param e
	 */
	protected void themeSelectionChanged(ListSelectionEvent e) {
		if (m_setting) return;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ValueNamePair laf = (ValueNamePair)lookList.getSelectedValue();
		ValueNamePair theme = (ValueNamePair)themeList.getSelectedValue();
		LookAndFeel currentLaf = UIManager.getLookAndFeel();
		MetalTheme currentTheme = MetalLookAndFeel.getCurrentTheme();
		AdempierePLAF.setPLAF(laf, theme, false);
		previewPanel.refresh(currentLaf,currentTheme);
		SwingUtilities.updateComponentTreeUI(previewPanel);
		updatePreviewComponents();
		setLFSelection();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.repaint();
	}

	/**
	 * Handle look and feel selection changed
	 * @param e
	 */
	protected void lookAndFeelSelectionChanged(ListSelectionEvent e) {
		if (m_setting) return;
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		ValueNamePair laf = (ValueNamePair)lookList.getSelectedValue();
		LookAndFeel currentLaf = UIManager.getLookAndFeel();
		MetalTheme currentTheme = MetalLookAndFeel.getCurrentTheme();
		AdempierePLAF.setPLAF(laf, null, false);
		previewPanel.refresh(currentLaf,currentTheme);
		SwingUtilities.updateComponentTreeUI(previewPanel);
		updatePreviewComponents();
		setLFSelection();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.repaint();
	}

	private void updatePreviewComponents() {
		error.setBackground(true);
		mandatory.setMandatory(true);
		button.setOpaque(true);
		tabPage1.setOpaque(false);
	}

	/**
	 * Create the preview UI components 
	 */
	private void setupPreview() {
		JRootPane rootPane = new JRootPane();
		previewPanel.setLayout(new BorderLayout());
		previewPanel.add(rootPane, BorderLayout.CENTER);
		previewPanel.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getSecondary1(), 1));
		GridLayout gridLayout = new GridLayout(1, 3);
		gridLayout.setHgap(4);
		rootPane.getContentPane().setLayout(gridLayout);
		rootPane.setGlassPane(new GlassPane());
		rootPane.getGlassPane().setVisible(true);
		CPanel column1 = new CPanel();
		rootPane.getContentPane().add(column1);
		CPanel column2 = new CPanel();
		rootPane.getContentPane().add(column2);
		CPanel column3 = new CPanel();
		rootPane.getContentPane().add(column3);
		
		column1.setLayout(new GridBagLayout());
		JTree jtree = new JTree();
		jtree.setFocusable(false);
		jtree.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getSecondary1(), 1));
		column1.add(jtree, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5,5,2,2),0,0));
		CTextField normal = new CTextField("Text Field");
		normal.setFocusable(false);
		column1.add(normal, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,5,5,2),0,0));
		error.setBackground(true);
		error.setFocusable(false);
		column1.add(error, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,2,5,2),0,0));
		
		column2.setLayout(new GridBagLayout());
		JTable jtable = new JTable(s_data, s_columns);
		JScrollPane scrollPane = new JScrollPane(jtable);
		jtable.setFocusable(false);
		scrollPane.setPreferredSize(jtable.getPreferredSize());
		column2.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5,2,2,2),0,0));
		mandatory.setMandatory(true);
		mandatory.setFocusable(false);
		column2.add(mandatory, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,2,5,2),0,0));
		CTextField inactive = new CTextField(s_res.getString("Inactive"));
		inactive.setEnabled(false);
		column2.add(inactive, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,2,5,2),0,0));
		
		column3.setLayout(new GridBagLayout());
		CTabbedPane tab = new CTabbedPane();
		column3.add(tab, new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0,
				GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5,2,2,5),0,0));
		CComboBox editable = new CComboBox(new Object[]{"Editable"});
		editable.setEditable(true);
		editable.setFocusable(false);
		column3.add(editable, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,2,5,2),0,0));
		CComboBox choice = new CComboBox(new Object[]{"Choice"});
		choice.setEditable(false);
		choice.setFocusable(false);
		column3.add(choice, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2,2,5,5),0,0));
		
		tabPage1.setLayout(new BoxLayout(tabPage1, BoxLayout.Y_AXIS));
		JRadioButton radio = new JRadioButton("Radio");
		radio.setSelected(true);
		radio.setMargin(new Insets(5,5,5,5));
		radio.setFocusable(false);
		radio.setOpaque(false);
		tabPage1.add(radio);
		CCheckBox checkBox = new CCheckBox("Checkbox");
		checkBox.setSelected(true);
		checkBox.setMargin(new Insets(5,5,5,5));
		checkBox.setFocusable(false);
		tabPage1.add(checkBox);
		CLabel label = new CLabel("Label");
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		tabPage1.add(label);
		button.setMargin(new Insets(5,5,5,5));
		button.setFocusable(false);
		tabPage1.add(button);
		tab.add(tabPage1, "tab1");
		tab.add(new CPanel(), "tab2");
	}
	
	/**
	 *  Update the look list and theme list to show the current selection
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
			lookList.setSelectedValue(plaf, true);


		//  Search for Theme
		MetalTheme metalTheme = null;
		ValueNamePair theme = null;
		boolean metal = UIManager.getLookAndFeel() instanceof MetalLookAndFeel;
		themeList.setModel(new DefaultComboBoxModel(AdempierePLAF.getThemes()));
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
				themeList.setSelectedValue(theme, true);
		}
		m_setting = false;
		log.info(lookFeel + " - " + metalTheme);
	}   //  setLFSelection
	
	/**
	 * Get look and feel selected by user
	 * @return selected look and feel
	 */
	public ValueNamePair getSelectedLook() {
		return (ValueNamePair)lookList.getSelectedValue();
	}
	
	/**
	 * Get theme selected by user
	 * @return selected theme
	 */
	public ValueNamePair getSelectedTheme() {
		return (ValueNamePair)themeList.getSelectedValue();
	}
}

/**
 * Glass pane to block input and mouse event from the preview components 
 * @author Low Heng Sin
 */
class GlassPane extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4416920279272513L;

	GlassPane() {
		addMouseListener(new MouseAdapter() {});
		addKeyListener(new KeyAdapter() {});
		addFocusListener(new FocusAdapter() {});
	}
}

/**
 * A custom panel, only repaint when look and feel or theme changed. 
 * @author Low Heng Sin
 */
class PreviewPanel extends CPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6028614986952449622L;
	private boolean capture = true;
	private LookAndFeel laf = null;
	private MetalTheme theme = null;
	private BufferedImage image;
	
	@Override
	public void paint(Graphics g) {
		if (capture) {
			//capture preview image
			image = (BufferedImage)createImage(this.getWidth(),this.getHeight());
			super.paint(image.createGraphics());
			g.drawImage(image, 0, 0, null);
			capture = false;
			if (laf != null) {
				//reset to origianl setting
				if (laf instanceof MetalLookAndFeel)
					AdempierePLAF.setCurrentMetalTheme((MetalLookAndFeel)laf, theme);
				try {
					UIManager.setLookAndFeel(laf);
				} catch (UnsupportedLookAndFeelException e) {
				}
				laf = null;
				theme = null;
			}
		} else {
			//draw captured preview image
			if (image != null)
				g.drawImage(image, 0, 0, null);
		}
	}
	
	/**
	 * Refresh look and feel preview, reset to original setting after
	 * refresh.
	 * @param currentLaf Current Look and feel
	 * @param currentTheme Current Theme
	 */
	public void refresh(LookAndFeel currentLaf, MetalTheme currentTheme) {
		this.laf = currentLaf;
		this.theme = currentTheme;
		capture = true;
	}
}