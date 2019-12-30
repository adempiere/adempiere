/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.apps.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.adempiere.controller.form.BOMDropController;
import org.adempiere.controller.form.BOMDropForm;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.grid.ed.VRadioButton;
import org.compiere.model.MLookup;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CollapsiblePanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Drop BOM
 *  <p>This custom form can be used standalone or as a button action on a Order, invoice, 
 *  or project document. It allows the user to select a BOM and drop it into a draft 
 *  document. The form has two parts, a selection panel where the BOM is selected and a 
 *  product selection panel where individual lines of the BOM can be selected and 
 *  quantities adjusted.
 *  <p>Clicking OK will save the selected lines to the document. 
 *	
 *  @author Jorg Janke
 *  @version $Id: VBOMDrop.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li>Extensive rewrites
 */
public class VBOMDrop extends CPanel 
	implements FormPanel, BOMDropForm, ActionListener
{	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6952031736723743235L;



	/**
	 * Standard constructor
	 */
	VBOMDrop () {

		super ();
		controller = new BOMDropController(this);

	}

	/** The controller for this form */
	private BOMDropController controller;
	/**	Window No					*/
	private int         windowNo = 0;
	/**	FormFrame					*/
	private FormFrame 	formFrame;
	/**	Line Counter				*/
	private int			lineCount = 0;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VBOMDrop.class);

	/** Alternative Group Lists		*/
	private HashMap<CollapsiblePanel,ButtonGroup>	buttonGroups = new HashMap<CollapsiblePanel,ButtonGroup>();

	/** Preferred window width */
	private static final int	WINDOW_WIDTH = 600;	//	width of the window
	/** Preferred width of search editor */
	private static final int	SEARCH_WIDTH = 120;
	/** preferred width of qty editor */
	private static final int	QTY_WIDTH = 60;
	/** preferred width of uom editor */
	private static final int	UOM_WIDTH = 60;
	/** preferred width of name editor */
	private static final int	NAME_WIDTH = 120;
	/** preferred width of check editor */
	private static final int	CHECK_WIDTH = 15;
	
	/** The confirmation panel with OK and Cancel buttons */
	private ConfirmPanel confirmPanel = new ConfirmPanel (true);
	/** The panel where the selection of the BOM is performed */
	private CPanel selectionPanel = new CPanel(new GridBagLayout());

	/** Option Group Lists		*/
	private HashMap<String, Object> groupboxes = new HashMap<String, Object>();

	/** The pane that holds the BOM list */
	private CScrollPane scroll;
	private CPanel header = new CPanel();



	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame parent frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		windowNo = WindowNo;
		formFrame = frame;

		if (!controller.init(frame.getProcessInfo(), windowNo))
			dispose();

		selectionPanel.setBorder(new TitledBorder(Msg.translate(Env.getCtx(), MSG_SELECTIONPANEL)));
		confirmPanel.addActionListener(this);

		String title = Msg.getMsg(Env.getCtx(), MSG_SELECTBOMLINES);
		scroll = new CScrollPane (this);
		scroll.setColumnHeaderView(header);
		scroll.setBorder(new TitledBorder(title));

		formFrame.getContentPane().setLayout(new BorderLayout());
		formFrame.getContentPane().add(selectionPanel, BorderLayout.NORTH);
		formFrame.getContentPane().add(scroll, BorderLayout.CENTER);
		formFrame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		
		
		sizeIt();
		
		this.setLayout (new GridBagLayout());
		
		scroll.setVisible(false); // Hide it until ready

	}	//	init

	/**
	 * 	Size Window
	 */
	@Override
	public void sizeIt()
	{

		//  Reset the preferred size to the default behavior
		formFrame.getWindow().setPreferredSize(null);
		
		// Calculates the size based on the content
		Dimension size = new Dimension(formFrame.getPreferredSize()); 
		
		// Limit the width
		size.width = WINDOW_WIDTH;
		formFrame.getWindow().setPreferredSize(size); 

		formFrame.pack();
		
		Rectangle myBounds = formFrame.getWindow().getBounds();

		for(GraphicsDevice gd:GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()){
			Rectangle screenBounds = gd.getDefaultConfiguration().getBounds();
			if (screenBounds.intersects(myBounds) && !screenBounds.contains(myBounds))
			{
				// The window is partially off the screen.
				AEnv.positionCenterScreen(formFrame.getWindow());
				break;
			}
		}

	}	//	size

	/**
	 * 	Dispose
	 */
	@Override
	public void dispose()
	{
		if (formFrame != null)
			formFrame.dispose();
		formFrame = null;

		removeAll();

		if (buttonGroups != null)
			buttonGroups.clear();
		buttonGroups = null;

	}	//	dispose


	/**
	 * 	Get Preferred Size
	 *	@return size
	 */
	public Dimension getPreferredSize ()
	{
		Dimension size = super.getPreferredSize ();
		if (size.width > WINDOW_WIDTH)
			size.width = WINDOW_WIDTH - 30;
		return size;
	}	//	getPreferredSize


	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.config(e.getActionCommand());

		//	OK
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			controller.confirmOK();
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			controller.confirmCancel();
		}

	}	//	actionPerformed

	
	@Override
	public CEditor createSelectionEditor(int displayType, MLookup lookup, String columnName, String name, String description, int row, int col) {

		if (DisplayType.isLookup(displayType) || displayType == DisplayType.ID)
		{
			VLookup editor = new VLookup (lookup.getColumnName(), false, false, true, lookup);

			Dimension size = new Dimension(editor.getMinimumSize());
			size.width = SEARCH_WIDTH;
			editor.setPreferredSize(size);
			CLabel label = new CLabel(name);
			label.setLabelFor(editor);
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = col;
			c.gridy = row;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.ipadx = 5;
			c.insets = new Insets(2, 5, 2, 5);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.FIRST_LINE_START;

			
			selectionPanel.add(label, c);
			
			c.gridx = col+1;
			selectionPanel.add(editor, c);

			return editor;

		}
		else if (DisplayType.isNumeric(displayType))
		{
			VNumber editor = new VNumber(columnName, true, false, true, displayType, columnName);

			Dimension size = new Dimension(editor.getMinimumSize());
			size.width = QTY_WIDTH;
			editor.setPreferredSize(new Dimension(size));

			GridBagConstraints c = new GridBagConstraints();
			c.gridx = col;
			c.gridy = row;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.ipadx = 5;
			c.insets = new Insets(2, 5, 2, 5);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.FIRST_LINE_END;

			CLabel label = new CLabel(columnName);
			label.setLabelFor(editor);
			selectionPanel.add(label,c);
			
			c.gridx = col+1;
			selectionPanel.add(editor, c);

			return editor;

		}
		else if (DisplayType.YesNo == displayType)
		{
			VCheckBox editor = new VCheckBox( columnName, false, false, true,
					name, description, false);
	
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = col;
			c.gridy = row;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weightx = 0.5;
			c.weighty = 0.0;
			c.ipadx = 5;
			c.insets = new Insets(2, 5, 2, 5);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
	
			selectionPanel.add(editor, c);
			return editor;
		}
		
		throw new IllegalArgumentException("Unhandled display type " + displayType);
	}

	@Override
	public void enableConfirmOK(boolean enable) {

		confirmPanel.getOKButton().setEnabled(enable);

	}


	@Override
	public void showDialog(String message, String result) {

		ADialog.info(windowNo, this, message, result);

	}

	@Override
	public void clearBOMList() {

		this.removeAll();
		this.revalidate();
		this.repaint();
		lineCount = 0;
		//
		buttonGroups.clear();
		groupboxes.clear();
		scroll.setVisible(false);

	}

	@Override
	public Object createFeature(String featureKey, String caption) {

		log.fine("Groupbox=" + featureKey);

		// Create a group box to show the items
		CollapsiblePanel optionGroup = new CollapsiblePanel(caption);
		groupboxes.put(featureKey, optionGroup);
		optionGroup.setCollapsed(true);
		optionGroup.getCollapsiblePane().getContentPane().setLayout(new GridBagLayout());
		optionGroup.setToolTipText(MSG_ClickToOpen);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = lineCount++;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weighty = 0.0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.add(optionGroup, c);

		return optionGroup;

	}

	@Override
	public void updateFeatureCaption(Object feature, String caption) {

		if (feature == null || !(feature instanceof CollapsiblePanel))
			throw new IllegalArgumentException("Feature type not an instance of CollapsiblePanel " + feature);

		CollapsiblePanel optionGroup = (CollapsiblePanel) feature;
		optionGroup.setTitle(caption);

	}

	@Override
	public CEditor addCheck(Object feature, String itemType, String name) {

		GridBagConstraints checkConstraint = new GridBagConstraints();
		checkConstraint.gridx = 0;
		checkConstraint.gridwidth = 1;
		checkConstraint.gridheight = 1;
		checkConstraint.weightx = 0;
		checkConstraint.weighty = 0.0;
		checkConstraint.ipadx = 5;
		checkConstraint.ipady = 5;
		checkConstraint.fill = GridBagConstraints.NONE;
		checkConstraint.anchor = GridBagConstraints.CENTER;
		
		GridBagConstraints labelConstraint = new GridBagConstraints();
		labelConstraint.gridx = 1;
		labelConstraint.gridwidth = 1;
		labelConstraint.gridheight = 1;
		labelConstraint.weightx = 0.3;
		labelConstraint.weighty = 0.0;
		labelConstraint.fill = GridBagConstraints.HORIZONTAL;
		labelConstraint.anchor = GridBagConstraints.CENTER;

		CLabel label = new CLabel (name);
		label.setHorizontalAlignment(CLabel.LEFT);
		Dimension size = new Dimension(label.getMinimumSize());
		size.width = NAME_WIDTH;
		label.setPreferredSize(size);
		
		if (ITEMTYPE_CHECK.equals(itemType))
		{
			VCheckBox cb;
			
			if (feature != null && (feature instanceof CollapsiblePanel))
			{
				cb = new VCheckBox(name, false, false, true, name, "", true);
				cb.addVetoableChangeListener(controller);
				cb.setEnabled(true);
				cb.setSelected(false);
				size = new Dimension(cb.getMinimumSize());
				size.width = CHECK_WIDTH;
				cb.setPreferredSize(size);

				checkConstraint.gridy = GridBagConstraints.RELATIVE;
				labelConstraint.gridy = GridBagConstraints.RELATIVE;

				CollapsiblePanel optionGroup = (CollapsiblePanel) feature;
				optionGroup.getCollapsiblePane().getContentPane().add(cb, checkConstraint);
				optionGroup.getCollapsiblePane().getContentPane().add(label, labelConstraint);
			}
			else
			{
				cb = new VCheckBox(name, false, true, false, name, "", true);
				cb.addVetoableChangeListener(controller);
				cb.setSelected(true);
				size = new Dimension(cb.getMinimumSize());
				size.width = CHECK_WIDTH;
				cb.setPreferredSize(size);

				checkConstraint.gridy = lineCount;
				labelConstraint.gridy = lineCount;
				
				this.add(cb, checkConstraint);
				this.add(label, labelConstraint);
			}

			return cb;

		}
		else if (ITEMTYPE_RADIO.equals(itemType))
		{
			if (feature == null || !(feature instanceof CollapsiblePanel))
				throw new IllegalArgumentException("Can't have radiobutton type without a group!");

			VRadioButton rb = new VRadioButton(name, false, false, true, name, "", true);
			rb.addVetoableChangeListener(controller);
			size = new Dimension(rb.getMinimumSize());
			size.width = CHECK_WIDTH;
			rb.setPreferredSize(size);

			CollapsiblePanel optionGroup = (CollapsiblePanel) feature;
			ButtonGroup buttonGroup = (ButtonGroup)buttonGroups.get(optionGroup);
			if (buttonGroup == null)
			{
				buttonGroup = new ButtonGroup();
				buttonGroups.put(optionGroup, buttonGroup);
				buttonGroup.add(rb);
				rb.setValue(true);
			}
			buttonGroup.add(rb);
			
			checkConstraint.gridy = GridBagConstraints.RELATIVE;
			labelConstraint.gridy = GridBagConstraints.RELATIVE;
			
			optionGroup.getCollapsiblePane().getContentPane().add(rb,checkConstraint);
			optionGroup.getCollapsiblePane().getContentPane().add(label,labelConstraint);

			return rb;
			
		}
		else
		{
			log.severe("Unhandled Item type: " + itemType);
		}
		return null;

	}

	@Override
	public CEditor addQty(Object feature, BigDecimal qty) {

		VNumber qtyEditor = new VNumber ("Qty", true, false, true, DisplayType.Quantity, Msg.translate(Env.getCtx(), "Qty"));
		Dimension size = new Dimension(qtyEditor.getMinimumSize());
		size.width = QTY_WIDTH;
		qtyEditor.setPreferredSize(size);
		qtyEditor.setValue(qty);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.25;
		c.weighty = 0.0;
		c.ipadx = 5;
		c.insets = new Insets(2, 5, 2, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		//	Add to List & display
		if (feature != null && (feature instanceof CollapsiblePanel))
		{
			c.gridy = GridBagConstraints.RELATIVE;
			CollapsiblePanel optionGroup = (CollapsiblePanel) feature;
			optionGroup.getCollapsiblePane().getContentPane().add(qtyEditor,c);
		}
		else
		{
			c.gridy = lineCount;
			this.add(qtyEditor,c);
		}

		return qtyEditor;

	}

	@Override
	public CEditor addUOM(Object feature, MLookup uomLookup, int c_uom_id) {

		VLookup uomEditor = new VLookup (uomLookup.getColumnName(), true, false, true, uomLookup);
		Dimension size = new Dimension(uomEditor.getMinimumSize());
		size.width = UOM_WIDTH;
		uomEditor.setPreferredSize(size);
		uomEditor.setValue(c_uom_id);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.25;
		c.weighty = 0.0;
		c.insets = new Insets(2, 5, 2, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.FIRST_LINE_START;

		//	Add to List & display
		if (feature != null && (feature instanceof CollapsiblePanel))
		{
			c.gridy = GridBagConstraints.RELATIVE;
			CollapsiblePanel optionGroup = (CollapsiblePanel) feature;
			optionGroup.getCollapsiblePane().getContentPane().add(uomEditor,c);
		}
		else
		{
			c.gridy = lineCount++;
			this.add(uomEditor, c);
		}

		return uomEditor;

	}

	@Override
	public void enableBOMList() {

		JSeparator dummy = new JSeparator(SwingConstants.HORIZONTAL);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = lineCount;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 1.0; // !Important
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;

		this.add(dummy, c);
		scroll.setVisible(true);

	}

	@Override
	public void setBOMListHeaders(String checkName, String productName, String qtyName, String uomName) {
		
		header.removeAll();
		header.setLayout(new GridBagLayout());
		CLabel selectLabel = new CLabel(checkName, SwingConstants.LEFT);
		CLabel nameLabel = new CLabel(productName, SwingConstants.LEFT);
		CLabel qtyLabel = new CLabel(qtyName, SwingConstants.CENTER);
		CLabel uomLabel = new CLabel(uomName, SwingConstants.CENTER);
		Dimension size = new Dimension(CHECK_WIDTH, 22);
		selectLabel.setPreferredSize(size);
		size = new Dimension(NAME_WIDTH, 22);
		nameLabel.setPreferredSize(size);
		size = new Dimension(QTY_WIDTH, 22);
		qtyLabel.setPreferredSize(size);
		size = new Dimension(UOM_WIDTH, 22);
		uomLabel.setPreferredSize(size);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.0;
		c.weighty = 0.0;
		c.ipadx = 5;
		c.insets = new Insets(2, 5, 2, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_END;

		header.add(selectLabel, c);
		
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.3;
		
		header.add(nameLabel, c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.25;
		c.weighty = 0.0;
		c.ipadx = 5;
		c.insets = new Insets(2, 5, 2, 5);

		header.add(qtyLabel, c);
		
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0.25;
		c.weighty = 0.0;
		c.ipadx = 5;
		c.insets = new Insets(2, 5, 2, 5);
		
		header.add(uomLabel, c);
		
		JSeparator dummy = new JSeparator(SwingConstants.HORIZONTAL);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.weightx = 0.5;
		c.weighty = 1.0; // !Important
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;

		header.add(dummy, c);
		
		scroll.setVisible(true);


	}
}	//	VBOMDrop
