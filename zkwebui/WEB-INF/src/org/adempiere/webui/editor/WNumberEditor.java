/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.editor;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.adempiere.webui.ValuePreference;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.webui.window.WRecordInfo;
import org.compiere.model.GridField;
import org.compiere.model.MRole;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 11, 2007
 * @version $Revision: 0.10 $
 *
 * @author Low Heng Sin
 * @author Cristina Ghita, www.arhipac.ro
 *  	   <li> BF [3058780] WNumberEditor allow only BigDecimal
 *  	   @see https://sourceforge.net/tracker/?func=detail&aid=3058780&group_id=176962&atid=955896
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public class WNumberEditor extends WEditor implements ContextMenuListener
{
    public static final String[] LISTENER_EVENTS = {Events.ON_CHANGE, Events.ON_OK};

    public static final int MAX_DISPLAY_LENGTH = 35;
    public static final int MIN_DISPLAY_LENGTH = 11;

    private Object oldValue;

	private int displayType;

	private WEditorPopupMenu popupMenu;

    public WNumberEditor()
    {
    	this("Number", false, false, true, DisplayType.Number, "");
    }

    /**
     *
     * @param gridField
     */
    public WNumberEditor(GridField gridField)
    {
        super(new NumberBox(gridField.getDisplayType() == DisplayType.Integer),
                gridField);
        this.displayType = gridField.getDisplayType();
        init();
    }

    /**
     *
     * @param gridField
     * @param integral
     */
    public WNumberEditor(GridField gridField, boolean integral)
    {
        super(new NumberBox(integral), gridField);
        this.displayType = integral ? DisplayType.Integer : DisplayType.Number;
        init();
    }

    /**
     *
     * @param columnName
     * @param mandatory
     * @param readonly
     * @param updateable
     * @param displayType
     * @param title
     */
    public WNumberEditor(String columnName, boolean mandatory, boolean readonly, boolean updateable,
			int displayType, String title)
    {
		super(new NumberBox(displayType == DisplayType.Integer), columnName, title, null, mandatory,
				readonly, updateable);
		this.displayType = displayType;
		init();
	}

	private void init()
    {
		if (gridField != null)
		{
			getComponent().setTooltiptext(gridField.getDescription());
	        int displayLength = gridField.getDisplayLength();
	        if (displayLength > MAX_DISPLAY_LENGTH)
	            displayLength = MAX_DISPLAY_LENGTH;
	        else if (displayLength <= 0 || displayLength < MIN_DISPLAY_LENGTH)
	        	displayLength = MIN_DISPLAY_LENGTH;
			getComponent().getDecimalbox().setCols(displayLength);
		}

		if (!DisplayType.isNumeric(displayType))
			displayType = DisplayType.Number;
		DecimalFormat format = DisplayType.getNumberFormat(displayType, AEnv.getLanguage(Env.getCtx()));
		getComponent().getDecimalbox().setFormat(format.toPattern());
		
		popupMenu = new WEditorPopupMenu(true, true, false);
    	if (gridField != null && gridField.getGridTab() != null)
		{
			WRecordInfo.addMenu(popupMenu);
		}
    	getComponent().setContext(popupMenu.getId());
    }

	/**
	 * Event handler
	 * @param event
	 */
    public void onEvent(Event event)
    {
    	if (Events.ON_CHANGE.equalsIgnoreCase(event.getName()) || Events.ON_OK.equalsIgnoreCase(event.getName()))
    	{
	        Object newValue = getComponent().getValue();
	        if (oldValue == null && newValue == null) {
	        	return;
	        }
	        
	        if (displayType == DisplayType.Integer) {
		        if (newValue != null && newValue instanceof BigDecimal) {
		        	newValue = new Integer(((BigDecimal)newValue).intValue());
		        }
		        if (oldValue != null && oldValue instanceof BigDecimal) {
		        	oldValue = new Integer(((BigDecimal)oldValue).intValue());
		        }
	        }
	        
	        if (oldValue != null && newValue != null && oldValue.equals(newValue)) 
	        {
	    	    return;
	    	}
	        
	        ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldValue, newValue);
	        super.fireValueChange(changeEvent);
	        oldValue = newValue;
    	}
    }

    @Override
	public NumberBox getComponent() {
		return (NumberBox) component;
	}

	@Override
	public boolean isReadWrite() {
		return getComponent().isEnabled();
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		getComponent().setEnabled(readWrite);
	}

	@Override
    public String getDisplay()
    {
        return getComponent().getText();
    }

    @Override
    public Object getValue()
    {
        return getComponent().getValue();
    }

    @Override
    public void setValue(Object value)
    {
    	if (value == null)
    		oldValue = null;
    	else if (value instanceof BigDecimal)
    		oldValue = (BigDecimal) value;
    	else if (value instanceof Number)
    		oldValue = new BigDecimal(((Number)value).doubleValue());
    	else
    		oldValue = new BigDecimal(value.toString());
    	getComponent().setValue(oldValue);
    }

    @Override
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

    /**
     * Handle context menu events
     * @param evt
     */
    public void onMenu(ContextMenuEvent evt)
	{
	 	if (WEditorPopupMenu.PREFERENCE_EVENT.equals(evt.getContextEvent()) && gridField != null)
		{
			if (MRole.getDefault().isShowPreference())
				ValuePreference.start (this.getGridField(), getValue());
			return;
		}
	 	else if (WEditorPopupMenu.CHANGE_LOG_EVENT.equals(evt.getContextEvent()))
		{
			WRecordInfo.start(gridField);
		}
	}
}
