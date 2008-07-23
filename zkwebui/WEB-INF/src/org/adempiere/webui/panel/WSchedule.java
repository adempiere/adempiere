/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.adempiere.webui.panel;

import java.text.DateFormat;
import java.util.*;
import java.util.logging.*;

import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.window.InfoSchedule;
import org.compiere.util.*;
import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.Timeline;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *	Visual and Control Part of Schedule.
 *  Contains Time and Schedule Panels
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VSchedule.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 */
public class WSchedule extends Panel implements EventListener
{
	private InfoSchedule infoSchedule;

	/**
	 *	Constructor
	 *  @param is InfoSchedule for call back
	 *  @param type Type of schedule TYPE_...
	 */
	public WSchedule (InfoSchedule is)
	{		
		infoSchedule = is;
		try
		{
			init();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "VSchedule", e);
		}
	}	//	WSchedule

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WSchedule.class);

	Timeline schedulePanel;
	private Bandinfo hourBand;
	private Bandinfo dayBand;

	private ToolBarButton button;

	/**
	 * 	Static init
	 *  <pre>
	 * 	timePanel (West)
	 *  schedlePanel (in schedulePane - Center)
	 *  </pre>
	 * 	@throws Exception
	 */
	private void init() throws Exception
	{
		schedulePanel = new Timeline();
		schedulePanel.setHeight("400px");
		schedulePanel.setWidth("100%");
		schedulePanel.setId("resoureSchedule");
		
		this.appendChild(schedulePanel);
		
		hourBand = new Bandinfo();
		schedulePanel.appendChild(hourBand);
		hourBand.setIntervalUnit("hour");
		hourBand.setWidth("60%");
		hourBand.setIntervalPixels(40);
		hourBand.setId("hour");
		hourBand.setTimeZone(TimeZone.getDefault());
		
		dayBand = new Bandinfo();
		schedulePanel.appendChild(dayBand);
		dayBand.setIntervalUnit("day");
		dayBand.setWidth("40%");
		dayBand.setIntervalPixels(100);
		dayBand.setId("day");
		dayBand.setSyncWith("hour");		
		dayBand.setTimeZone(TimeZone.getDefault());
		dayBand.setShowEventText(false);
		
		button = new ToolBarButton();
		button.setLabel("Edit");
		button.setStyle("visibility: hidden; height: 0px; width: 0px");
		button.addEventListener(Events.ON_CLICK, this);
		this.appendChild(button);
	}	//	jbInit

	/**
	 * 	Recreate View
	 * 	@param S_Resource_ID Resource
	 * 	@param date Date
	 */
	public void recreate (int S_Resource_ID, Date date)
	{
		hourBand.setDate(date);
		hourBand.scrollToCenter(date);
		
		String feedUrl = "timeline?S_Resource_ID=" + S_Resource_ID + "&date=" + DateFormat.getInstance().format(date)
			+ "&uuid=" + button.getUuid();
		hourBand.setEventSourceUrl(feedUrl);
		dayBand.setEventSourceUrl(feedUrl);
		schedulePanel.invalidate();
	}	//	recreate

	/**
	 * 	Enable/disable to Create New Assignments
	 * 	@param createNew if true, allows to create new Assignments
	 */
	public void setCreateNew (boolean createNew)
	{
//		schedulePanel.setCreateNew(createNew);
	}	//	setCreateNew

	public void onEvent(Event event) throws Exception {
		//TODO: Edit, S_ResourceAssignment_ID is in MouseEvent.x
	}

}	//	WSchedule
