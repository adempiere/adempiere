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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

import org.adempiere.controller.RecordInfoController;
import org.compiere.grid.VTable;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridField;
import org.compiere.swing.CDialog;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTextArea;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * Record Info (Who) With Change History
 * <p>
 * Change log:
 * <ul>
 * <li>2007-02-26 - teo_sarca - [ 1666598 ] RecordInfo shows ColumnName instead of name
 * </ul>
 * 
 * @author Jorg Janke
 * @version $Id: RecordInfo.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public class RecordInfo extends RecordInfoController
{
	public static final String CHANGE_LOG_COMMAND = "ChangeLog";
	/** The Menu Icon               */
	private static Icon s_icon = new ImageIcon(org.compiere.Adempiere.class.getResource("images/ChangeLog16.png"));
	/**	Dialog			*/
	private CDialog m_Dialog = null;

	/**
	 *	Record Info (Convert to private contructor)
	 *	@param owner owner
	 *	@param title title
	 *	@param dse data status event
	 *	@param mField used when the change log is from a specific column
	 */
	private RecordInfo (Frame owner, String title, DataStatusEvent dse, GridField mField)
	{
		//	Load super
		super(title, dse, mField);
		//	Validate ok load
		m_Dialog = new CDialog(owner, title, true) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -1667930305647519065L;

			@Override
			public void actionPerformed(ActionEvent e) {
				super.actionPerformed(e);
				m_Dialog.dispose();
			}
		};
		//	Set field
		try
		{
			dynInit();
			jbInit();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		AEnv.positionCenterWindow (owner, m_Dialog);
	}	//	RecordInfo
	
	/**
	 * Constructor for generic window
	 * @param owner
	 * @param title
	 * @param dse
	 */
	public RecordInfo (Frame owner, String title, DataStatusEvent dse) {
		this(owner, title, dse, null);
	}

	/**
	 * Constructor for specific column
	 * @param owner
	 * @param mField
	 */
	public RecordInfo (Frame owner, GridField mField) {
		this(owner, Msg.getElement(Env.getCtx(), "AD_ChangeLog_ID"), null, mField);
	}

	private CPanel	mainPanel	= new CPanel (new BorderLayout(0,0));
	private CScrollPane	scrollPane = new CScrollPane ();
	private VTable table = new VTable ();
	private ConfirmPanel confirmPanel = new ConfirmPanel (false);

	/**	Logger			*/
	protected CLogger		log = CLogger.getCLogger(getClass());

	/**
	 * 	Static Layout
	 *	@throws Exception
	 */
	private void jbInit () throws Exception
	{
		m_Dialog.getContentPane().add(mainPanel);
		CTextArea info = new CTextArea(getInfo());
		info.setReadWrite(false);
		info.setOpaque(false);	//	transparent
		info.setForeground(Color.blue);
		info.setBorder(null);
		//
		if (isOk())
		{
			mainPanel.add (info, BorderLayout.NORTH);
			mainPanel.add (scrollPane, BorderLayout.CENTER);
			scrollPane.getViewport().add(table);
			scrollPane.setPreferredSize(new Dimension(500,100));
		}
		else
		{
			info.setPreferredSize(new Dimension(400,75));
			mainPanel.add (info, BorderLayout.CENTER);
		}
		//
		mainPanel.add (confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(m_Dialog);
	}	//	jbInit
	
	
	/**
	 * 	Dynamic Init
	 */
	protected void dynInit() {
		DefaultTableModel model = new DefaultTableModel(getData(), getColumnNames());
		table.setModel(model);
		table.autoSize(false);
	}	//	dynInit
	
	/**
	 * Get Dialog
	 * @return
	 */
	public CDialog getDialog() {
		return m_Dialog;
	}
	
	/**
	 * Add change log menu item
	 * @param l
	 * @param popupMenu
	 * @return CMenuItem
	 */
	public static CMenuItem addMenu (ActionListener l, JPopupMenu popupMenu)
	{
		CMenuItem mi = new CMenuItem (Msg.getElement(Env.getCtx(), "AD_ChangeLog_ID"), s_icon);
		mi.setActionCommand(CHANGE_LOG_COMMAND);
		mi.addActionListener(l);
		popupMenu.add(mi);
		return mi;
	}   //  addMenu

	/**
	 * Open field record info dialog
	 * @param mField
	 */
	public static void start(GridField mField) {
		int WindowNo = mField.getWindowNo();
		Frame frame = Env.getWindow(WindowNo);
		RecordInfo info = new RecordInfo(frame, mField);
		AEnv.showCenterScreen(info.getDialog());
	}
}	// RecordInfo
