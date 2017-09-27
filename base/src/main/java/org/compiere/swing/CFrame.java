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
package org.compiere.swing;

import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 	Adempiere Frame
 *	
 *  @author Jorg Janke
 *  @version $Id: CFrame.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 */
public class CFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6282268921005682543L;

	/**
	 * 	CFrame
	 *	@throws HeadlessException
	 */
	public CFrame () throws HeadlessException
	{
		super ();
	}	//	CFrame

	/**
	 * 	CFrame
	 *	@param gc
	 */
	public CFrame (GraphicsConfiguration gc)
	{
		super (gc);
	}	//	CFrame

	/**
	 * 	CFrame
	 *	@param title
	 *	@throws HeadlessException
	 */
	public CFrame (String title) throws HeadlessException
	{
		super (cleanup(title));
	}	//	CFrame

	/**
	 * 	CFrame
	 *	@param title
	 *	@param gc
	 */
	public CFrame (String title, GraphicsConfiguration gc)
	{
		super (cleanup(title), gc);
	}	//	CFrame

	/** Window ID			*/
	private int		p_AD_Window_ID = 0;
	//	Yamel Senih FR [ 114 ]
	/**	Form ID				*/
	private int		p_AD_Form_ID = 0;
	
	/**
	 * 	Frame Init.
	 * 	Install ALT-Pause
	 */
	protected void frameInit ()
	{
		super.frameInit ();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//
		Container c = getContentPane();
		if (c instanceof JPanel)
		{
			JPanel panel = (JPanel)c;
			panel.getActionMap().put(CDialog.ACTION_DISPOSE, CDialog.s_dialogAction);
			panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(CDialog.s_disposeKeyStroke, CDialog.ACTION_DISPOSE);
		}
	}	//	frameInit
	
	/**
	 * 	Cleanedup Title
	 *	@param title title
	 *	@return title w/o mn
	 */
	private static String cleanup (String title)
	{
		if (title != null)
		{
			int pos = title.indexOf('&');
			if (pos != -1 && title.length() > pos)	//	We have a mnemonic
			{
				int mnemonic = title.toUpperCase().charAt(pos+1);
				if (mnemonic != ' ')
					title = title.substring(0, pos) + title.substring(pos+1);
			}
		}
		return title;
	}	//	getTitle

	/**
	 * 	Set Title
	 *	@param title title
	 */
	public void setTitle(String title)
	{
		super.setTitle(cleanup(title));
	}	//	setTitle

	/**
	 * @return Returns the AD_Window_ID.
	 */
	public int getAD_Window_ID ()
	{
		return p_AD_Window_ID;
	}	//	getAD_Window_ID

	/**
	 * @param window_ID The AD_Window_ID to set.
	 */
	public void setAD_Window_ID (int window_ID)
	{
		p_AD_Window_ID = window_ID;
	}	//	getAD_Window_ID
	
	/**
	 * Get Form ID
	 * @return
	 */
	public int getAD_Form_ID() {
		return p_AD_Form_ID;
	}
	
	/**
	 * Set Form ID
	 * @param p_AD_Form_ID
	 */
	public void setAD_Form_ID(int p_AD_Form_ID) {
		this.p_AD_Form_ID = p_AD_Form_ID;
	}

}	//	CFrame
