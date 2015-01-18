/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
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
 * @author Cristina Ghita, c.ghita@metas.ro,  METAS GROUP  					  *
 * FR ADEMPIERE-191 Autocomplete functionality       						  *
 * https://adempiere.atlassian.net/browse/ADEMPIERE-190                       *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.webui.component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Timer;

public abstract class AutoCompleter extends AutoComplete implements EventListener
{

	private static final long serialVersionUID = -377518697359312755L;

	private static final int PopupDelayMillis = 500;
	/** Minimum chars required to popup */
	public static final int DEFAULT_PopupMinimumChars = 2;
	private int m_popupMinimumChars = DEFAULT_PopupMinimumChars;

	public void setPopupMinimumChars(int m_popupMinimumChars)
	{
		this.m_popupMinimumChars = m_popupMinimumChars;
	}

	public int getPopupMinimumChars()
	{
		return m_popupMinimumChars;
	}

	public static final String ITEM_More = "...";

	public static final int DEFAULT_MaxItems = 7;
	protected int m_maxItems = DEFAULT_MaxItems;

	protected final CLogger log = CLogger.getCLogger(getClass());

	private final Timer timer = new Timer(PopupDelayMillis);
	protected String defaultStyle;

	private Object m_userObject = null;
	private String m_text = null;
	
	public AutoCompleter()
	{
		super();
		defaultStyle = getStyle();
		addEventListener(Events.ON_CHANGING, this);
		addEventListener(Events.ON_CHANGE, this);
	}
	
	public void setUserObject(Object userObject)
	{
		m_userObject = userObject;
		if (m_userObject == null && !Util.isEmpty(getText(), true))
		{
			setStyle("background:red");
		}
		else
		{
			setStyle(defaultStyle);
		}
		//
		setTooltiptext(userObject == null ? "" : userObject.toString());
	}

	public Object getUserOject()
	{
		return m_userObject;
	}

	private void showPopupDelayed()
	{
		log.finest("showPopupDelayed..");
		// Popup only if we a minimum number of characters - 2009_0017_AP1_G42_CR048
		final String search = getText();
		if (search != null && search.trim().length() < m_popupMinimumChars)
		{
			setVisible(false);
			return;
		}
		
		timer.setRepeats(false);
		timer.start();
	}

	@Override
	public void onChanging(InputEvent evt)
	{
		showPopupDelayed();
		setSearchText(evt.getValue());
		updateListData();
		super.onChanging(evt);
	}
	
	public String getSearchText()
	{
		return m_text;
	}
	
	public void setSearchText(String txt)
	{
		m_text = txt;
	}
	
	public Textbox getComponent()
	{
		return getComponent();
	}
	
	private String convertUserObjectForTextField(Object userObject)
	{
		return userObject == null ? "" : userObject.toString();
	}

	protected boolean isMatching(Object userObject, String search)
	{
		if (userObject == null)
			return false;

		String s1 = Util.stripDiacritics(convertUserObjectForTextField(userObject));
		String s2 = Util.stripDiacritics(search);

		return s1.equalsIgnoreCase(s2);
	}
	
	abstract protected String getSelectSQL(String search, List<Object> params);
	
	abstract protected Object fetchUserObject(ResultSet rs) throws SQLException;
	
	abstract protected boolean updateListData();

}
