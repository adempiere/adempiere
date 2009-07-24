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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Application Confirm Panel.
 *  <code>
 *  if (e.getActionCommand().equals(ConfirmPanel.A_OK))
 *  </code>
 *  @author 	Jorg Janke
 *  @version 	$Id: ConfirmPanel.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ConfirmPanel extends CPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6041019802043360966L;


	/**
	 *	Create OK Button with label text and F4 Shortcut
	 *  @param text text
	 *  @return OK Button
	 */
	public static final CButton createOKButton (String text)
	{
		AppsAction aa = new AppsAction (A_OK, KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		button.setDefaultCapable(true);
		return button;
	}	//	createOKButton

	/**
	 *	Create OK Button with Standard text
	 *  @param withText with text
	 *  @return OK Button
	 */
	public static final CButton createOKButton (boolean withText)
	{
		if (withText)
			return createOKButton(Msg.getMsg(Env.getCtx(), A_OK));
		return createOKButton("");
	}	//	createOKButton

	/**
	 *	Create Cancel Button wlth label text and register ESC as KeyStroke
	 *  @param text text
	 *  @return Cancel Button
	 */
	public static final CButton createCancelButton (String text)
	{
		AppsAction aa = new AppsAction (A_CANCEL, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createCancelButton

	/**
	 *	Create Cancel Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createCancelButton(boolean withText)
	{
		if (withText)
			return createCancelButton(Msg.getMsg(Env.getCtx(), A_CANCEL));
		return createCancelButton("");
	}	//	createCancelButton

	
	/************************
	 *	Create Refresh Button wlth label text and F5
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createRefreshButton (String text)
	{
		AppsAction aa = new AppsAction (A_REFRESH, KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createRefreshButton

	/**
	 *	Create Refresh Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createRefreshButton (boolean withText)
	{
		if (withText)
			return createRefreshButton(Msg.getMsg(Env.getCtx(), A_REFRESH));
		return createRefreshButton("");
	}	//	createRefreshButton

	
	/************************
	 *	Create Reset Button wlth label text
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createResetButton (String text)
	{
		AppsAction aa = new AppsAction (A_RESET, null, text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createResetButton

	/**
	 *	Create Reset Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createResetButton (boolean withText)
	{
		if (withText)
			return createResetButton(Msg.getMsg(Env.getCtx(), A_RESET));
		return createResetButton(null);
	}	//	createRefreshButton

	/************************
	 *	Create Customize Button wlth label text
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createCustomizeButton (String text)
	{
		AppsAction aa = new AppsAction (A_CUSTOMIZE, null, text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	//	Env.getImageIcon("Preference24.gif"));
	}	//	createCustomizeButton

	/**
	 *	Create Customize Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createCustomizeButton (boolean withText)
	{
		if (withText)
			return createCustomizeButton(Msg.getMsg(Env.getCtx(), A_CUSTOMIZE));
		return createCustomizeButton(null);
	}	//	createCustomizeButton

	
	/************************
	 *	Create History Button wlth label text
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createHistoryButton (String text)
	{
		AppsAction aa = new AppsAction (A_HISTORY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	//	Env.getImageIcon("HistoryX24.gif"));
	}	//	createHistoryButton

	/**
	 *	Create History Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createHistoryButton (boolean withText)
	{
		if (withText)
			return createHistoryButton(Msg.getMsg(Env.getCtx(), A_HISTORY));
		return createHistoryButton(null);
	}	//	createHistoryButton

	
	/************************
	 *	Create Zoom Button wlth label text
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createZoomButton (String text)
	{
		AppsAction aa = new AppsAction (A_ZOOM, null, text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createZoomButton

	/**
	 *	Create Zoom Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createZoomButton (boolean withText)
	{
		if (withText)
			return createZoomButton(Msg.getMsg(Env.getCtx(), A_ZOOM));
		return createZoomButton(null);
	}	//	createZoomButton

	
	/************************
	 *	Create Process Button wlth label text Shift-F4
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createProcessButton (String text)
	{
		AppsAction aa = new AppsAction (A_PROCESS, 
			KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.SHIFT_MASK), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createProcessButton

	/**
	 *	Create Process Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createProcessButton (boolean withText)
	{
		if (withText)
			return createProcessButton(Msg.getMsg(Env.getCtx(), A_PROCESS));
		return createProcessButton(null);
	}	//	createProcessButton

	
	/************************
	 *	Create Print Button wlth label text
	 *  @param text text
	 *  @return button
	 */
	public static final CButton createPrintButton (String text)
	{
		AppsAction aa = new AppsAction (A_PRINT, KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createPrintButton

	/**
	 *	Create Print Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createPrintButton (boolean withText)
	{
		if (withText)
			return createPrintButton(Msg.getMsg(Env.getCtx(), A_PRINT));
		return createPrintButton(null);
	}	//	createPrintButton

	
	/************************
	 *	Create Help Button wlth label text
	 *  @param text text
	 *  @return Button
	 */
	public static final CButton createHelpButton (String text)
	{
		AppsAction aa = new AppsAction (A_HELP, KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createHelpButton

	/**
	 *	Create Help Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createHelpButton (boolean withText)
	{
		if (withText)
			return createHelpButton(Msg.getMsg(Env.getCtx(), A_HELP));
		return createHelpButton(null);
	}	//	createHelpButton

	
	/************************
	 *	Create Export Button wlth label text
	 *  @param text text
	 *  @return Button
	 */
	public static final CButton createExportButton (String text)
	{
		AppsAction aa = new AppsAction (A_EXPORT, null, text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createExportButton

	/**
	 *	Create Export Button wlth Standard text
	 *  @param withText with text
	 *  @return Button
	 */
	public static final CButton createExportButton (boolean withText)
	{
		if (withText)
			return createExportButton(Msg.getMsg(Env.getCtx(), A_EXPORT));
		return createExportButton(null);
	}	//	createExportButton

	
	/************************
	 *	Create Delete Button with label text - F3
	 *  @param text text
	 *  @return Delete Button
	 */
	public static final CButton createDeleteButton (String text)
	{
		AppsAction aa = new AppsAction (A_DELETE, KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createDeleteButton

	/**
	 *	Create Delete Button with Standard text
	 *  @param withText with text
	 *  @return Delete Button
	 */
	public static final CButton createDeleteButton (boolean withText)
	{
		if (withText)
			return createDeleteButton(Msg.getMsg(Env.getCtx(), A_DELETE));
		return createDeleteButton(null);
	}	//	createDeleteButton

	
	/************************
	 *	Create Product Attribute Button with Standard text
	 *  @param withText with text
	 *  @return Product Attribute Button
	 */
	public static final CButton createPAttributeButton (boolean withText)
	{
		if (withText)
			return createPAttributeButton(Msg.getMsg(Env.getCtx(), A_PATTRIBUTE));
		return createPAttributeButton(null);
	}	//	createPAttributeButton

	/**
	 *	Create Product Attribute Button with label text
	 *  @param text text
	 *  @return Product Attribute Button
	 */
	public static final CButton createPAttributeButton (String text)
	{
		AppsAction aa = new AppsAction (A_PATTRIBUTE, null, text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createPAttributeButton


	/************************
	 *	Create New Button with Standard text
	 *  @param withText with text
	 *  @return New Button
	 */
	public static final CButton createNewButton (boolean withText)
	{
		if (withText)
			return createNewButton(Msg.getMsg(Env.getCtx(), A_NEW));
		return createNewButton(null);
	}	//	createNewButton

	/**
	 *	Create New Button with label text - F2
	 *  @param text text
	 *  @return Product Attribute Button
	 */
	public static final CButton createNewButton (String text)
	{
		AppsAction aa = new AppsAction (A_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), text);
		CButton button = (CButton)aa.getButton();
		button.setMargin(s_insets);
		return button;
	}	//	createNewButton

	
	/*************************************************************************/

	/**	Action String OK        */
	public static final String A_OK = "Ok";
	/**	Action String Cancel    */
	public static final String A_CANCEL = "Cancel";
	/**	Action String Refresh   */
	public static final String A_REFRESH = "Refresh";
	/**	Action String Reset   	*/
	public static final String A_RESET = "Reset";
	/**	Action String Customize */
	public static final String A_CUSTOMIZE = "Customize";
	/**	Action String History   */
	public static final String A_HISTORY = "History";
	/**	Action String Zoom      */
	public static final String A_ZOOM = "Zoom";

	/** Action String Process   */
	public static final String A_PROCESS = "Process";
	/** Action String Print     */
	public static final String A_PRINT = "Print";
	/** Action String Export    */
	public static final String A_EXPORT = "Export";
	/** Action String Help      */
	public static final String A_HELP = "Help";
	/**	Action String Delete    */
	public static final String A_DELETE = "Delete";
	/**	Action String PAttribute	*/
	public static final String A_PATTRIBUTE = "PAttribute";
	/**	Action String New	*/
	public static final String A_NEW = "New";

	/**	Standard Insets used    */
	public static Insets 	s_insets = new Insets (0, 10, 0, 10);
	
	
	/**************************************************************************
	 *	Create Confirmation Panel with OK Button
	 */
	public ConfirmPanel()
	{
		this (false, false, false, false, false, false, true);
	}	//	ConfirmPanel

	/**
	 *	Create Confirmation Panel with OK and Cancel Button
	 *  @param withCancelButton with cancel
	 */
	public ConfirmPanel(boolean withCancelButton)
	{
		this (withCancelButton, false, false, false, false, false, true);
	}	//	ConfirmPanel

	/**
	 *	Create Confirmation Panel with different buttons
	 *  @param withCancelButton with cancel
	 *  @param withRefreshButton with refresh
	 *  @param withResetButton with reset
	 *  @param withCustomizeButton with customize
	 *  @param withHistoryButton with history
	 *  @param withZoomButton with zoom
	 *  @param withText with text
	 */
	public ConfirmPanel(boolean withCancelButton,
		boolean withRefreshButton, 
		boolean withResetButton, 
		boolean withCustomizeButton,
		boolean withHistoryButton, 
		boolean withZoomButton,
		boolean withText)
	{
		super();
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		this.setName("confirmPanel");
		//
		CPanel okCancel = new CPanel(new FlowLayout(FlowLayout.RIGHT));
		okCancel.setOpaque(false);
		bCancel = createCancelButton(withText);
		okCancel.add(bCancel);
		bOK = createOKButton(withText);
		okCancel.add(bOK);
		setCancelVisible(withCancelButton);
		this.add(okCancel, BorderLayout.EAST);
		//
		if (withRefreshButton)
		{
			bRefresh = createRefreshButton(withText);
			addComponent(bRefresh);
		}
		if (withResetButton)
		{
			bReset = createResetButton(withText);
			addComponent(bReset);
		}
		if (withCustomizeButton)
		{
			bCustomize = createCustomizeButton(withText);
			addComponent(bCustomize);
		}
		if (withHistoryButton)
		{
			bHistory = createHistoryButton(withText);
			addComponent(bHistory);
		}
		if (withZoomButton)
		{
			bZoom = createZoomButton(withText);
			addComponent(bZoom);
		}
	}	//	ConfirmPanel

	/**	Additional Buttons Panel			*/
	private CPanel  m_addlButtons = null;

	private CButton bOK;
	private CButton bCancel;
	//
	private CButton bRefresh;
	private CButton bReset;
	private CButton bCustomize;
	private CButton bHistory;
	private CButton bZoom;

	/**
	 *  Add Button to left side of confirmPanel
	 *  @param button button
	 */
	public void addComponent (Component button)
	{
		if (m_addlButtons == null)
		{
			m_addlButtons = new CPanel(new FlowLayout(FlowLayout.LEFT));
			this.add(m_addlButtons, BorderLayout.WEST);
		}
		m_addlButtons.add(button);
	}   //  addButton

	/**
	 *  Add Button to left side of confirmPanel
	 *  @param action action command
	 *  @param toolTipText tool tip text
	 *  @param icon icon
	 *  @return JButton
	 */
	public CButton addButton (String action, String toolTipText, Icon icon)
	{
		AppsAction aa = new AppsAction(action, null, toolTipText);
		CButton b = (CButton)aa.getButton(); 
//			new DialogButton (action, toolTipText, icon);
		addComponent (b);
		return b;
	}   //  addButton

	/**
	 *  Add Button to left side of confirmPanel
	 *  @param button button
	 *  @return JButton
	 */
	public JButton addButton (JButton button)
	{
		addComponent (button);
		return button;
	}   //  addButton

	/**
	 *	Get OK Button
	 *  @return OK Button
	 */
	public CButton getOKButton()
	{
		return bOK;
	}	//	getOKButton

	/**
	 *	Get Cancel Button
	 *  @return Cancel Button
	 */
	public CButton getCancelButton()
	{
		return bCancel;
	}	//	getCancelButton

	/**
	 *	Show OK button
	 *  @param value true for visible
	 */
	public void setOKVisible (boolean value)
	{
		bOK.setVisible(value);
		bOK.setEnabled(value);
	}	//	setOKVisible

	/**
	 *	Is OK Visible
	 *  @return true of OK visisble
	 */
	public boolean isOKVisible()
	{
		return bOK.isVisible();
	}	//	isOKVisible

	/**
	 *	Show Cancel button
	 *  @param value trie for visible
	 */
	public void setCancelVisible (boolean value)
	{
		bCancel.setVisible(value);
		bCancel.setEnabled(value);
	}	//	setCancelVisible

	/**
	 *	Is Cancel Visible
	 *  @return true if Canvel is visible
	 */
	public boolean isCancelVisible()
	{
		return bCancel.isVisible();
	}	//	isCancelVisible

	/**
	 *	Get Reset Button
	 *  @return Button
	 */
	public CButton getResetButton()
	{
		return bReset;
	}	//	getResetButton
	
	/**
	 *	Get Customize Button
	 *  @return Button
	 */
	public CButton getCustomizeButton()
	{
		return bCustomize;
	}	//	getCustomizeButton

	/**
	 *	Get History Button
	 *  @return Button
	 */
	public CButton getHistoryButton()
	{
		return bHistory;
	}	//	getHistoryButton
	
	/**
	 *	Get Zoom Button
	 *  @return Button
	 */
	public CButton getZoomButton()
	{
		return bZoom;
	}	//	getZoomyButton

	/**
	 *	Get Refresh Button
	 *  @return Button
	 */
	public CButton getRefreshButton()
	{
		return bRefresh;
	}	//	getRefreshButton
	
	
	/**************************************************************************
	 *	Add Action Listener
	 *  <code>
	 *  if (e.getActionCommand().equals(ConfirmPanel.A_OK))
	 *  </code>
	 *  @param al listener
	 */
	public void addActionListener(ActionListener al)
	{
		((AppsAction)bOK.getAction()).setDelegate(al);
		((AppsAction)bCancel.getAction()).setDelegate(al);
		//
		if (bRefresh != null)
			((AppsAction)bRefresh.getAction()).setDelegate(al);
		if (bReset != null)
			((AppsAction)bReset.getAction()).setDelegate(al);
		if (bCustomize != null)
			((AppsAction)bCustomize.getAction()).setDelegate(al);
		if (bHistory != null)
			((AppsAction)bHistory.getAction()).setDelegate(al);
		if (bZoom != null)
			((AppsAction)bZoom.getAction()).setDelegate(al);
			
		//	Set OK as default Button
		JRootPane rootpane = null;
		if (al instanceof JDialog)
			rootpane = ((JDialog)al).getRootPane();
		else if (al instanceof JFrame)
			rootpane = ((JFrame)al).getRootPane();
		if (rootpane != null)
			rootpane.setDefaultButton(bOK);
	}	//	addActionListener

	/**
	 *  Enable all components
	 *  @param enabled trie if enabled
	 */
	public void setEnabled (boolean enabled)
	{
		super.setEnabled(enabled);
		bOK.setEnabled(enabled);
		bCancel.setEnabled(enabled);
		//
		if (bRefresh != null)
			bRefresh.setEnabled(enabled);
		if (bCustomize != null)
			bCustomize.setEnabled(enabled);
		if (bHistory != null)
			bHistory.setEnabled(enabled);
		if (bZoom != null)
			bZoom.setEnabled(enabled);
	}   //  setEnabled

}	//	ConfirmPanel
