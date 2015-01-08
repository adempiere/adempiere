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
package org.compiere.mobile;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.b;
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h1;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.html;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.meta;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.title;
import org.apache.ecs.xhtml.tr;


/**
 *  XHTML Document.
 *
 *  @author Jorg Janke
 *  @version  $Id: WebDoc.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 */
public class MobileDoc
{
	/**
	 *  Create styled Document with Title
	 *  @param plain if true adds standard.css and standard.js
	 *  @param title optional header title and h1 
	 *  @param javaClient true if Java Client - browser otherwise
	 *  @return Document
	 */
	public static MobileDoc create (boolean plain, String title, boolean javaClient)
	{
		MobileDoc doc = new MobileDoc();
		doc.setUp (plain, javaClient, title);
		return doc;
	}   //  create
	
	/**
	 *  Create Document
	 *  @param plain if true adds stylesheet and standard js
	 *  @return Document
	 */
	public static MobileDoc create (boolean plain)
	{
		return create (plain, null, false);
	}   //  create
	
	/**
	 *  Create styled popup Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	public static MobileDoc createPopup (String title)
	{
		MobileDoc doc = create (title);
		doc.getHead().addElement(new link("iui/iui.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new script((Element)null, MobileEnv.getBaseDirectory("iui/iui.js")));
		doc.getHead().addElement(new script((Element)null, "js/window.js"));
		doc.getHead().addElement(new link("images/AdempiereButton.png", "apple-touch-icon-precomposed", "image/png"));
		doc.getHead().addElement(new link("images/AdempiereButton.png", "shortcut icon", "image/png"));
		doc.getHead().addElement(new meta().setName("viewport")
				.setContent("width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"));	
		
		return doc;
	}   //  createPopup

	/**
	 *  Create styled window Document with Title
	 *  @param title header title and h1 
	 *  @return Document
	 */
	public static MobileDoc createWindow (String title)
	{
		MobileDoc doc = create (true, title, false);
		
		doc.getHead().addElement(new link("iui/iui.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new script((Element)null, MobileEnv.getBaseDirectory("iui/iui.js")));
		doc.getHead().addElement(new script((Element)null, "js/window.js"));
		// doc.getHead().addElement(new script((Element)null, "js/calendar-setup.js"));
		doc.getHead().addElement(new script((Element)null, "js/calendar.js"));
		doc.getHead().addElement(new script((Element)null, "lang/calendar-en.js"));
		doc.getHead().addElement(new link("css/calendar-blue.css", link.REL_STYLESHEET, link.TYPE_CSS));
		doc.getHead().addElement(new link("images/AdempiereButton.png", "apple-touch-icon-precomposed", "image/png"));
		doc.getHead().addElement(new link("images/AdempiereButton.png", "shortcut icon", "image/png"));
		doc.getHead().addElement(new meta().setName("viewport")
				.setContent("width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0; user-scalable=0;"));
		return doc;
	}   //  createWindow

	/**
	 *  Create styled web Document with Title
	 *  @param title optional header title and h1 
	 *  @return Document
	 */
	public static MobileDoc create (String title)
	{
		return create (false, title, false);
	}   //  create

	/** Non brealing Space					*/
	public static final String	NBSP	= "&nbsp;";
	
	
	/**************************************************************************
	 *  Create new XHTML Document structure
	 */
	private MobileDoc ()
	{
	}   //  WDoc

	private html    m_html = new html();
	private head    m_head = new head();
	private body    m_body = new body();

	/**
	 *  Set up Document
	 *  @param plain if true adds stylesheet and standard js
	 *  @param javaClient true if Java Client - browser otherwise
	 *  @param title header title and h1
	 */
	private void setUp (boolean plain, boolean javaClient, String title)
	{
		m_html.addElement(m_head);
		m_html.addElement(m_body);
		if (title != null)
			m_head.addElement(new title(title));
		if (plain)
			return;

	}   //  setUp

	/**
	 * 	Set css Classes
	 *	@param tableClass optional class for table
	 *	@param tdClass optional class for left/right td
	 */
	public void setClasses (String tableClass, String tdClass)
	{
	}	//	setClasses

	
	/**
	 *  Get Body
	 *  @return Body
	 */
	public body getBody()
	{
		return m_body;
	}   //  getBody

	/**
	 *  Get Head
	 *  @return Header
	 */
	public head getHead()
	{
		return m_head;
	}   //  getHead

	
	/**
	 *  String representation
	 *  @return String
	 */
	public String toString()
	{
		return m_html.toString();
	}   //  toString

	/**
	 *  Output Document
	 *  @param out out
	 */
	public void output (OutputStream out)
	{
		m_html.output(out);
	}   //  output

	/**
	 *  Output Document
	 *  @param out out
	 */
	public void output (PrintWriter out)
	{
		m_html.output(out);
	}   //  output
	
	/**
	 *  Output Document
	 *  @param out out
	 */
	public void outputBody (PrintWriter out)
	{
		m_body.output(out);
	}   //  output
	
	/**************************************************************************
	 *  Test Class
	 *  @param args args
	 */
	public static void main (String[] args)
	{
		MobileDoc doc = MobileDoc.create("Test");
		doc.getBody().addElement(new b("111 <<< >>> &&& \\\\ \u0100 �"));
		form f = new form("myaction");
		f.addElement(new input());
		doc.getBody().addElement(f);
		System.out.println(doc.toString());
		System.out.println("---------");
		doc.output(System.out);
		System.out.println("---------");
	}   //  main
}   //  WDoc
