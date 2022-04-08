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
package org.compiere.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import org.adempiere.plaf.AdempierePLAF;


/**
 *  Mini Browser
 *
 *  @author     Jorg Janke
 *  @version    $Id: MiniBrowser.java,v 1.2 2006/07/30 00:52:23 jjanke Exp $
 */
public class MiniBrowser extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9084820549169098075L;

	/**
	 *  Default Constructor
	 */
	public MiniBrowser()
	{
		this (null);
	}   //  MiniBrowser

	/**
	 *  Create MiniBrowser with URL
	 *  @param url
	 */
	public MiniBrowser(String url)
	{
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		setURL (url);
		AdempierePLAF.showCenterScreen(this);
	}   //  MiniBrowser

	private JScrollPane scrollPane = new JScrollPane();
	private JEditorPane editorPane = new JEditorPane();

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		scrollPane.setPreferredSize(new Dimension(500, 500));
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(editorPane, null);
	}   //  jbInit

	/**
	 *  Set URL
	 *  @param url
	 */
	private void setURL (String url)
	{
		String myURL = url;
		if (url == null)
			myURL = "http://www.adempiere.org";
		this.setTitle(myURL);

		//  Set URL
		URL realURL = null;
		try
		{
			realURL = new URL(myURL);
		}
		catch (Exception e)
		{
			System.err.println("MiniBrowser.setURL (set) - " + e.toString());
		}
		if (realURL == null)
			return;

		//  Open
		try
		{
			editorPane.setPage(realURL);
		}
		catch (Exception e)
		{
			System.err.println("MiniBrowser.setURL (open) - " + e.toString());
		}
	}   //  setURL
}   //  MiniBrowser
