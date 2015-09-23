/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 *				                                                              *
 *****************************************************************************/

package org.adempiere.webui.component;

/**
 * This code extends the ZK Group functionality which is not included in the
 * community edition.  This implementation provides some of the same functionality
 * and draws on the css styles for groups.
 * 
 * The code is taken from the idempiere project, implementation of ZK7, and modified for the ADempiere implementation.
 * See https://bitbucket.org/idempiere/idempiere/src/b688916292295d1279cd842148157b85cdf09f0f/org.adempiere.ui.zk/WEB-INF/src/org/adempiere/webui/component/Group.java?at=zk7
 *	
 * @author  Various (Elain, Carlos, Hengsin, McKay)
 * @date    2 June 2014
 * @version $Revision: 0.10 $
 */

import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.theme.ThemeUtils;
import org.zkoss.zhtml.Link;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Span;

public class Group extends org.zkoss.zul.Group {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6735090880559291438L;

	public static final String GROUP_ROW_VISIBLE_KEY = "groupRowVisible";

	private List<Row> m_rows = new ArrayList<Row>();

	public Group() {
		super();
		ThemeUtils.addSclass("ad-group", this);
	}
	
	public Group(String label) {
		super(label);
		ThemeUtils.addSclass("ad-group", this);
	}
	
	public <T> Group(String label, T value) {
		super(label, value);
		ThemeUtils.addSclass("ad-group", this);
	}

	public String getLabel() {
		final Component cell = getFirstChild();
		return cell != null && cell instanceof GroupHeader ? ((GroupHeader)cell).getTitle() : null;
	}

	@Override
	public void setLabel(String label) {
		autoFirstCell().setTitle(label);
	}	
	
	public void setSpan(int colspan) {
		autoFirstCell().setColspan(colspan);;
	}	

	
	private GroupHeader autoFirstCell() {
		Component cell = getFirstChild();
		if (cell == null || cell instanceof GroupHeader) {
			if (cell == null) cell = new GroupHeader();
			cell.applyProperties();
			cell.setParent(this);
			return (GroupHeader)cell;
		}
		throw new UiException("Unsupported child for setLabel: "+cell);
	}
	
	public void setOpen(boolean open) {
		super.setOpen(open);
		autoFirstCell().setOpen(open);
		
		if (getParent() != null)
		{
			for (Row row : m_rows)
			{
				boolean visible = true;
				String value = (String) row.getAttribute(GROUP_ROW_VISIBLE_KEY);
				if (value != null)
					visible = value.equals("true");				
				row.setVisible(isOpen() && visible);	// hide the row of the children when group is not open
			}
		}
	}
	
	public void add(Row row) {
		m_rows.add(row);
	}

	public static class GroupHeader extends Cell implements EventListener<Event>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = -4070011056533999557L;
		private Span span;
		private Label lbl;
		
		private String title;
		private boolean open;
		
		public GroupHeader()
		{
			super();
			init();
			setTitle(null);
			setOpen(true);
		}
		
		private void init()
		{
			ThemeUtils.addSclass("ad-group-header", this);
			
			Div div = new Div();
			ThemeUtils.addSclass("ad-group-header-body", div);
			appendChild(div);
			
			span = new Span();
			div.appendChild(span);
			span.addEventListener(Events.ON_CLICK, this);
			ThemeUtils.addSclass("ad-group-header-button", span);

			lbl = new Label();
			lbl.setStyle("cursor: pointer");
			lbl.addEventListener(Events.ON_CLICK, this);
			div.appendChild(lbl);
			ThemeUtils.addSclass("ad-group-header-label", lbl);
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
			lbl.setValue(this.title);
		}

		public boolean isOpen() {
			return open;
		}

		public void setOpen(boolean open) {
			this.open = open;
			span.setZclass(this.open ? "z-icon-minus-square" : "z-icon-plus-square");
		}

		@Override
		public void onEvent(Event e) throws Exception 
		{
			if (e.getName().equals(Events.ON_CLICK))
			{
				((Group) getParent()).setOpen(!isOpen());
			}
		}
	}

}