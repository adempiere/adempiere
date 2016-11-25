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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.adempiere.controller.SmallViewEditable;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.print.ReportCtl;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CButton;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * @author Low Heng Sin
 * @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process
 *         Parameter: add display and readonly logic
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2548216 ] Process Param Panel is not showing any parameter if error 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 			<li>FR [ 3426137 ] Smart Browser
 * 			 https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 			<li> https://adempiere.atlassian.net/browse/ADEMPIERE-97
 * 			<li> The parameters is not show when use display logic, the parameters dialog window is not automatically resize.
 * @version 	2006-12-01
 * @author Michael Mckay michael.mckay@mckayerp.com
 * 			<li>BF3423098 - Labels for process parameters with display logic false are still displayed
 *			<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *			@see https://github.com/adempiere/adempiere/issues/265
 *			<li>FR [ 349 ] GridFieldVO attribute is ambiguous
 * 			@see https://github.com/adempiere/adempiere/issues/349
 * 			<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *			@see https://github.com/adempiere/adempiere/issues/298
 *			<a href="https://github.com/adempiere/adempiere/issues/571">
 * 			@see FR [ 571 ] Process Dialog is not MVC</a>
 * 			<a href="https://github.com/adempiere/adempiere/issues/602">
 * 			@see BR [ 602 ] Error in SmallViewController</a>
 */
public class ProcessPanel extends ProcessController 
	implements SmallViewEditable, ActionListener, ASyncProcess {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessPanel(int WindowNo, ProcessInfo pi) {
		super(WindowNo, pi);
	}	//	ProcessParameterPanel

	/**
	 * With Columns
	 * @param WindowNo
	 * @param pi
	 * @param column
	 */
	public ProcessPanel(int WindowNo, ProcessInfo pi, int column) {
		super(WindowNo, pi, column);
	}	//	ProcessParameterPanel
	
	/**
	 * With Columns
	 * @param parent
	 * @param WindowNo
	 * @param pi
	 * @param column
	 */
	public ProcessPanel(IProcessDialog parent, int WindowNo, ProcessInfo pi, int column) {
		super(WindowNo, pi, column);
		this.parent = parent;
	}	//	ProcessParameterPanel
	
	//Layout Mode
	private int cols = 0;
	private int row = 0;
	private int[] ids = null;
	private IProcessDialog parent = null;
	//
	// Presentation for ranges
	private ArrayList<CLabel> 	m_separators;
	//	
	private BorderLayout 	mainLayout;
	private CPanel 			centerPanel;
	private CScrollPane 	parameterScrollPane;
	//	
	private CPanel southPanel;
	private CButton bOK;
	private CButton bCancel;
	private FlowLayout southLayout;
	private CButton bPrint;
	
	private CPanel mainPanel = new CPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 428410337428677817L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMinimumSize();
			if ( d.height < m.height || d.width < m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.max(d.height, m.height);
				d1.width = Math.max(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private JEditorPane message = new JEditorPane() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2271852928089812014L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	
	private JScrollPane messagePane = new JScrollPane(message) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 3605316311642118445L;

		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			Dimension m = getMaximumSize();
			if ( d.height > m.height || d.width > m.width ) {
				Dimension d1 = new Dimension();
				d1.height = Math.min(d.height, m.height);
				d1.width = Math.min(d.width, m.width);
				return d1;
			} else
				return d;
		}
	};
	private boolean 		isLocked = false;
	private int 			labelMinWidth = 0;
	private int 			fieldMinWidth = 0;
	private final int		SEPARATOR = 25;
	
	private static CLogger log	= CLogger.getCLogger(ProcessPanel.class);

	@Override
	public void initComponents() {
		mainLayout 			= new BorderLayout();
		centerPanel 		= new CPanel();
		parameterScrollPane = new CScrollPane(centerPanel);
		//	
		southPanel 			= new CPanel();
		southLayout 		= new FlowLayout();
		//	Buttons
		bOK 				= ConfirmPanel.createOKButton(true);
		bCancel				= ConfirmPanel.createCancelButton(true);
		bPrint 				= ConfirmPanel.createPrintButton(true);
		bOK.addActionListener(this);
		bCancel.addActionListener(this);
		bPrint.addActionListener(this);
		//	
		mainPanel.setLayout(mainLayout);
		mainPanel.setMinimumSize(new Dimension(500, 100));
		//	South Panel
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		southPanel.add(bPrint, null);
		southPanel.add(bCancel, null);
		southPanel.add(bOK, null);
		//	Editors
		m_separators 	= new ArrayList<CLabel>();
		//	
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setFocusable(true);
		//	
		centerPanel.setLayout(new ALayout());
		centerPanel.setBorder(null);
		if(isShowDescription()) {
			mainPanel.add(messagePane, BorderLayout.NORTH);
			messagePane.setMaximumSize(new Dimension(600, 300));
		}
		//	Add Center Panel with scroll bar
		parameterScrollPane.setAutoscrolls(true);
		parameterScrollPane.createVerticalScrollBar();
		parameterScrollPane.createHorizontalScrollBar();
		//	
		mainPanel.add(parameterScrollPane, BorderLayout.CENTER);
		//	Add buttons
		if(isShowButtons()) {
			mainPanel.add(southPanel, BorderLayout.SOUTH);
		}
		//	
		mainLayout.setVgap(2);
		//	Set Text
		message.setText(getTextMsg());
	}
	
	/**
	 * after Init Process
	 */
	public void afterInit() {
		//	BR [ 265 ]
		if (!hasParameters()) {
			if (getShowHelp() != null 
					&& getShowHelp().equals("N")) {
				setAutoStart(true);    // don't ask first click
				// anyway show resulting window
			}
		}
		// Check if the process is a silent one
		if(getShowHelp() != null 
				&& getShowHelp().equals("S")) {
			setAutoStart(true);
		}
		//	
		mainPanel.validate();
		//	If is Auto Start
		if(isAutoStart()) {
			bOK.doClick();
		}
	}
	
	/**
	 * Set Text Message from result
	 * @param textMsg
	 */
	public void setResult(String textMsg) {
		StringBuffer currentText = new StringBuffer(getTextMsg());
		currentText.append(textMsg);
		message.setText(currentText.toString());
		message.setCaretPosition(message.getDocument().getLength());	//	scroll down
	}

	/**
	 * Get Main Panel
	 * @return
	 */
	public CPanel getPanel() {
		return mainPanel;
	}
	
	public CEditor createEditor(GridField field) {
		return(VEditorFactory.getEditor(field, false));
	}

	
	public void formatEditor(CEditor editor1, CEditor editor2) {
		log.fine("");
		
		VEditor editor = (VEditor) editor1;
		VEditor editorTo = (VEditor) editor2;

		if(editor == null) {
			return;
		}

		configColumns(editor, editorTo);
		//  Create label
		CLabel label = VEditorFactory.getLabel(editor.getField());
		if (label == null) {
			centerPanel.add(Box.createHorizontalStrut(12), new ALayoutConstraint(row, cols++));   	//	left gap
		} else {
			int currentWidth = label.getPreferredSize().width;
			labelMinWidth = currentWidth > labelMinWidth ? currentWidth : labelMinWidth;
//			label.setSize(label.getPreferredSize());
//			label.setMinimumSize(label.getPreferredSize());
//			label.putClientProperty("LabelUI.truncationString", ">");
			centerPanel.add(label, new ALayoutConstraint(row, cols++));
		}
		//	
		Component component = (Component) editor;
		fieldMinWidth = component.getPreferredSize().width > fieldMinWidth 
				? component.getPreferredSize().width : fieldMinWidth;
		centerPanel.add ((Component)editor, new ALayoutConstraint(row, cols++));
		//	To
		if(editorTo == null) {
			m_separators.add(null);
			return;
		}
		//	Add
		CLabel dash = new CLabel(" - ");
		centerPanel.add (dash, new ALayoutConstraint(row, cols++));
		m_separators.add(dash);
		//
		centerPanel.add ((Component)editorTo, new ALayoutConstraint(row, cols++));
	}
	
	/**
	 * Congure columns
	 * @param editor
	 * @param editor_To
	 */
	private void configColumns(VEditor editor, VEditor editorTo) {
		int maxToAdd = getColumns() * 2;
		int columnsToAdd = getColumns();
		//	for To field
		if(editorTo != null) {
			columnsToAdd += 2;
		}
		if((cols + columnsToAdd) > maxToAdd) {
			cols = 0;
			row ++;
		}
	}


	@Override
	public void dynamicDisplay() {
		//	
		centerPanel.setPreferredSize(new Dimension(labelMinWidth + fieldMinWidth + SEPARATOR, (row + 1) * SEPARATOR)); // Row height
		
		super.dynamicDisplay();

	} // Dynamic Display.
	
	@Override
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange) {
		
		VEditor editor = (VEditor) getEditor(index);
		VEditor editorTo = (VEditor) getEditorTo(index);
		if (editor == null)
			return;
		
		Component[] components = centerPanel.getComponents();
		for (Component comp : components) {
			if (editor.getField().getColumnName().equals(comp.getName())) {	
				if (visible) {
					if (!comp.isVisible()) {
						comp.setVisible(true); // visibility
						//	FR [ 349 ]
						if (isRange && editorTo != null) {
							m_separators.get(index).setVisible(true);
							editorTo.setVisible(true);
						}
					}
				}
				else if (comp.isVisible()) {
					comp.setVisible(false);
					if(isRange && editorTo != null) {
						m_separators.get(index).setText("");
						editorTo.setVisible(false);
					}
				}
			}
		}
	}	
		
	@Override
	public String saveParameters() {
		String validError = super.saveParameters();
		if(validError != null) {
			ADialog.error(getWindowNo(), getPanel(), "FillMandatory", validError);
		}
		//	
		return validError;
	}
	
	/**
	 * Get default button for window
	 * @return
	 */
	public CButton getDefaultButton() {
		return bOK;
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		isLocked = true;
		bOK.setText("");
		bOK.setEnabled(false);
		mainPanel.setEnabled(false);
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		//	
		setResult(getLogInfo(false));
		ids = pi.getIDs();
		//
		bOK.setEnabled(true);
		mainPanel.setEnabled(true);
		isLocked = false;
		
		//no longer needed, hide to give more space to display log
		mainPanel.remove(messagePane);
		mainPanel.remove(parameterScrollPane);
		mainPanel.remove(southPanel);
		mainPanel.add(messagePane, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		//	Validate Screen
		if(parent != null) {
			parent.validateScreen();
			parent.showCenterScreen();
		}
		//
		afterProcessTask();
		//	Close automatically
		if (isReport() 
				&& !pi.isError()) {
			bOK.doClick();
		}
		
		// If the process is a silent one and no errors occured, close the dialog
		if(getShowHelp() != null 
				&& getShowHelp().equals("S"))
			bOK.doClick();
	}

	@Override
	public void dispose() {
		super.dispose();
		if(parent != null) {
			parent.dispose();
		}
	}
	
	@Override
	public boolean isUILocked() {
		return isLocked;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		log.config("-");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bOK) {
			setIsOkPressed(true);
			if(isOnlyPanel()) {
				//	check if saving parameters is complete
				if (saveParameters() == null) {
					//	Save Parameters
					dispose();
				}
			} else if (isProcessed()) {
				dispose();
			} else {
				//	BR [ 265 ]
				if(validateParameters() == null) {
					ProcessCtl.process(parent.getParentProcess(), getWindowNo(), this, getProcessInfo(), null);
				}
				if(parent.isEmbedded()) {
					dispose();
				}
			}
		} else if(e.getSource() == bCancel) {
			dispose();
		} else if (e.getSource() == bPrint) {
			if(parent != null) {
				parent.printScreen();
			}
		}
	}
	
	/**
	 * After Task (It is hardcoded!!!!!!)
	 */
	private void afterProcessTask() {
		//	TODO remove hardcode
		//  something to do?
		if(ids != null && ids.length > 0) {
			log.config("");
			//	Print invoices
			if(getProcessInfo().getAD_Process_ID() == 119)
				printInvoices();
			else if (getProcessInfo().getAD_Process_ID() == 118)
				printShipments();
		}
	}	//	afterProcessTask
	
	/**************************************************************************
	 *	Print Shipments
	 */
	private void printShipments() {
		if (ids == null)
			return;
		if (!ADialog.ask(getWindowNo(), getPanel(), "PrintShipments"))
			return;
		StringBuffer messageText = new StringBuffer();
		messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintShipments")).append("</p>");
		setResult(messageText.toString());
		int retValue = ADialogDialog.A_CANCEL;
		do {
			//	Loop through all items
			for (int i = 0; i < ids.length; i++) {
				int M_InOut_ID = ids[i];
				ReportCtl.startDocumentPrint(ReportEngine.SHIPMENT, M_InOut_ID, this, getWindowNo(), true);
			}
			ADialogDialog d = new ADialogDialog (Env.getWindow(getWindowNo()),
				Env.getHeader(Env.getCtx(), getWindowNo()),
				Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
				JOptionPane.QUESTION_MESSAGE);
			retValue = d.getReturnCode();
		}
		while (retValue == ADialogDialog.A_CANCEL);
	}	//	printInvoices

	/**
	 *	Print Invoices
	 */
	private void printInvoices() {
		if (ids == null)
			return;
		if (!ADialog.ask(getWindowNo(), getPanel(), "PrintInvoices"))
			return;
		StringBuffer messageText = new StringBuffer();
		messageText.append("<p>").append(Msg.getMsg(Env.getCtx(), "PrintInvoices")).append("</p>");
		setResult(messageText.toString());
		int retValue = ADialogDialog.A_CANCEL;
		do {
			//	Loop through all items
			for (int i = 0; i < ids.length; i++) {
				int AD_Invoice_ID = ids[i];
				ReportCtl.startDocumentPrint(ReportEngine.INVOICE, AD_Invoice_ID, this, getWindowNo(), true);
			}
			ADialogDialog d = new ADialogDialog (Env.getWindow(getWindowNo()),
				Env.getHeader(Env.getCtx(), getWindowNo()),
				Msg.getMsg(Env.getCtx(), "PrintoutOK?"),
				JOptionPane.QUESTION_MESSAGE);
			retValue = d.getReturnCode();
		}
		while (retValue == ADialogDialog.A_CANCEL);
	}	//	printInvoices

}	//	ProcessParameterPanel
