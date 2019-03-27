/******************************************************************************
// * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.adempiere.webui.window;

import java.util.logging.Level;

import org.adempiere.controller.RecordInfoController;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.SimpleListModel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.GridField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zhtml.Pre;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Menuitem;

/**
 * Record Info (Who) With Change History
 * <p>
 * Change log:
 * <ul>
 * <li>2007-02-26 - teo_sarca - [ 1666598 ] RecordInfo shows ColumnName instead of name
 * </ul>
 * 
 * @author Jorg Janke
 * 
 * Zk Port
 * @author Low Heng Sin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public class WRecordInfo extends RecordInfoController implements EventListener
{
	/**	Container			*/
	private Window v_Container = null;
	
	/**
	 *	Record Info
	 *	@param title title
	 *	@param dse data status event
	 *	@param mField field for especific column
	 */
	public WRecordInfo (String title, DataStatusEvent dse, GridField mField)
	{
		super(title, dse, mField);
		v_Container = new Window();
		v_Container.setTitle(title);
		v_Container.setAttribute("modal", Boolean.TRUE);
		v_Container.setWidth("800px");
		v_Container.setHeight("400px");
		v_Container.setBorder("normal");
		v_Container.setSizable(true);
		v_Container.setClosable(true);
		v_Container.setMaximizable(true);
		//	Init
		try
		{
			dynInit();
			init ();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		AEnv.showCenterScreen(v_Container);
	}	//	RecordInfo
	
	/**
	 * Constructor for generic window
	 * @param title
	 * @param dse
	 */
	public WRecordInfo (String title, DataStatusEvent dse) {
		this(title, dse, null);
	}

	/**
	 * Constructor for specific column
	 * @param owner
	 * @param mField
	 */
	public WRecordInfo (GridField mField) {
		this(Msg.getElement(Env.getCtx(), "AD_ChangeLog_ID"), null, mField);
	}


	private Listbox table = new Listbox();
	private ConfirmPanel confirmPanel = new ConfirmPanel (false);

	/**	Logger			*/
	protected CLogger		log = CLogger.getCLogger(getClass());
	
	/**
	 * 	Static Layout
	 *	@throws Exception
	 */
	private void init () throws Exception
	{

		Div div = new Div();
		div.setStyle("width: 100%; height: 100%");
		Pre pre = new Pre();
		Text text = new Text(getInfo());
		text.setParent(pre);
		pre.setParent(div);
		//
		
		Borderlayout layout = new Borderlayout();
		layout.setParent(v_Container);
		layout.setWidth("100%");
		layout.setHeight("100%");
		
		Center center = new Center();
		center.setParent(layout);
		center.setFlex(true);
		if (isOk())
		{
			North north = new North();
			north.setParent(layout);
			north.appendChild(div);
						
			center.appendChild(table);
			table.setWidth("100%");
			table.setVflex(true);
		}
		else
		{
			center.appendChild(div);
		}
		//
		South south = new South();
		south.setParent(layout);
		south.appendChild(confirmPanel);
		
		confirmPanel.addActionListener(Events.ON_CLICK, this);
	}	//	jbInit
	
	
	/**
	 * 	Dynamic Init
	 */
	private void dynInit()
	{	
		Listhead listhead = new Listhead();
		listhead.setSizable(true);
		//	Add Columns
		for (String columnName : getColumnNames()) {
			Listheader listheader = new Listheader(columnName.replaceAll("[&]", ""));
			listhead.appendChild(listheader);
		}
		//	Instance Table
		table.appendChild(listhead);
		SimpleListModel model = new SimpleListModel(getData());
		table.setItemRenderer(model);
		table.setModel(model);
	}	//	dynInit
	
	/**
	 * Event
	 */
	public void onEvent(Event event) throws Exception {
		v_Container.detach();
	}
	
	/**
	 * Open field record info dialog
	 * @param gridField
	 */
	public static void start(GridField gridField) {
		new WRecordInfo(gridField);
	}

	/**
	 * Add change log menu item
	 * @param popupMenu
	 */
	public static void addMenu(WEditorPopupMenu popupMenu) {
		Menuitem changeLogItem = new Menuitem();
        changeLogItem.setLabel(Msg.getElement(Env.getCtx(), "AD_ChangeLog_ID"));
        changeLogItem.setImage("/images/dark/ChangeLog16.png");
        changeLogItem.setAttribute(WEditorPopupMenu.EVENT_ATTRIBUTE, WEditorPopupMenu.CHANGE_LOG_EVENT);
        changeLogItem.addEventListener(Events.ON_CLICK, popupMenu);
        //	Add
        popupMenu.appendChild(changeLogItem);
	}

}	// WRecordInfo
