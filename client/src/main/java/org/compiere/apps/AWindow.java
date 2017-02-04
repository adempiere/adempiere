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
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.event.WindowEvent;

import org.compiere.model.MQuery;
import org.compiere.swing.CFrame;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  Main Application Window.
 *  - Constructs, initializes and positions JFrame
 *  - Gets content, menu, title from APanel
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: AWindow.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 				<li>BF [ 1836908 ] Report customize NPE when no window access
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/611">
 * 		@see BR [ 611 ] Error dialog is showed and lost focus from window</a>
 */
public class AWindow extends CFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1925388774073536474L;

	/**
	 *	@deprecated
	 *	Standard Constructor - requires initWindow
	 */
	public AWindow ()
	{
		this(null);
	}	//	AWindow
	
	/**
	 *	Standard Constructor - requires initWindow
	 *  @param gc
	 */
	public AWindow (GraphicsConfiguration gc)
	{
		super(gc);
		//	Set UI Components
		this.setIconImage(Env.getImage("mWindow.gif"));
		this.getContentPane().add(m_APanel, BorderLayout.CENTER);
		this.setGlassPane(m_glassPane);
	}	//	AWindow

	/** The GlassPane           */
	private AGlassPane  	m_glassPane = new AGlassPane();
	/** Application Window  	*/
	private APanel			m_APanel = new APanel(this);
	
	/**	Logger					*/
	private static CLogger 	log = CLogger.getCLogger(AWindow.class);

	/**
	 *  Dynamic Initialization Workbench
	 *  @param AD_Workbench_ID workbench
	 *  @return true if loaded OK
	 */
	protected boolean initWorkbench (int AD_Workbench_ID) {
		return initWorkbench(AD_Workbench_ID, true);
	}   //  initWorkbench
	
	/**
	 *  Dynamic Initialization Workbench
	 *  @param AD_Workbench_ID workbench
	 *  @param showError flag or show dialog
	 *  @return true if loaded OK
	 */
	protected boolean initWorkbench (int AD_Workbench_ID, boolean showError) {
		this.setName("AWindow_WB_" + AD_Workbench_ID);
		boolean loadedOK = m_APanel.initPanel (AD_Workbench_ID, 0, null);
		if(!loadedOK
				&& showError
				&& m_APanel.getLoadError() != null) {
			ADialog.warn(0, this, m_APanel.getLoadError(), null);
		}
		//
		commonInit();
		return loadedOK;
	}

	/**
	 *	Dynamic Initialization Single Window
	 *  @param AD_Window_ID window
	 *  @param query query
	 *  @return true if loaded OK
	 */
	public boolean initWindow (int AD_Window_ID, MQuery query) {
		return initWindow(AD_Window_ID, query, true);
	}	//	initWindow
	
	/**
	 *	Dynamic Initialization Single Window
	 *  @param AD_Window_ID window
	 *  @param query query
	 *  @param showError flag for show dialog
	 *  @return true if loaded OK
	 */
	public boolean initWindow(int AD_Window_ID, MQuery query, boolean showError) {
		this.setName("AWindow_" + AD_Window_ID);
		setAD_Window_ID(AD_Window_ID);
		//
		boolean loadedOK = m_APanel.initPanel (0, AD_Window_ID, query);
		if (loadedOK) {
			commonInit();
		} else if(showError
					&& m_APanel.getLoadError() != null){
			ADialog.warn(0, this, m_APanel.getLoadError(), null);
		}
		return loadedOK;
	}

	/**
	 *  Common Init.
	 * 	After APanel loaded
	 */
	private void commonInit()
	{
		this.setJMenuBar(m_APanel.getMenuBar());
		this.setTitle(m_APanel.getTitle());
		//
		Image image = m_APanel.getImage();
		if (image != null)
			setIconImage(image);
	}   //  commonInit

	
	/*************************************************************************
	 *  Set Window Busy
	 *  @param busy busy
	 */
	public void setBusy (boolean busy)
	{
		if (busy == m_glassPane.isVisible())
			return;
		log.config(getName() + " - " + busy);
		m_glassPane.setMessage(null);
		m_glassPane.setVisible(busy);
		if (busy)
			m_glassPane.requestFocus();
	}   //  setBusy

	/**
	 *  Set Busy Message
	 *  @param AD_Message message
	 */
	public void setBusyMessage (String AD_Message)
	{
		m_glassPane.setMessage(AD_Message);
	}   //  setBusyMessage

	/**
	 *  Set and start Busy Counter
	 *  @param time in seconds
	 */
	public void setBusyTimer (int time)
	{
		m_glassPane.setBusyTimer (time);
	}   //  setBusyTimer

	/**
	 *  Window Events
	 *  @param e event
	 */
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
//		System.out.println(">> Apps WE_" + e.getID()    // + " Frames=" + getFrames().length
//			+ " " + e);
	}   //  processWindowEvent

	/**
	 * 	Get Application Panel
	 *	@return application panel
	 */
	public APanel getAPanel()
	{
		return m_APanel;
	}	//	getAPanel
	
	/**
	 *	Dispose
	 */
	public void dispose()
	{
		if (Env.hideWindow(this))
			return;
		log.info(toString());
		if (m_APanel != null)
			m_APanel.dispose();
		m_APanel = null;
		this.removeAll();
		super.dispose();
	//	System.gc();
	}	//	dispose

	/**
	 * 	Get Window No of Panel
	 *	@return window no
	 */
	public int getWindowNo()
	{
		if (m_APanel != null)
			return m_APanel.getWindowNo();
		return 0;
	}	//	getWindowNo
	
	/**
	 *  String Representation
	 *  @return Name
	 */
	public  String toString()
	{
		return getName() + "_" + getWindowNo();
	}   //  toString

}	//	AWindow