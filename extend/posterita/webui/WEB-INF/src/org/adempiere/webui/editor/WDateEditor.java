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

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.event.ValueChangeEvent;
import org.compiere.model.GridField;
import org.compiere.util.CLogger;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Mar 12, 2007
 * @version $Revision: 0.10 $
 */
public class WDateEditor extends WEditor
{
	private static final String[] LISTENER_EVENTS = {Events.ON_CHANGE};
    private static final CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(WDateEditor.class);
    }
    
    private Timestamp oldValue = new Timestamp(0);
    
    private Datebox datebox;
    
    public WDateEditor(GridField gridField)
    {
        super(new Datebox(), gridField);
        datebox = (Datebox)super.component;
    }
    
	
	/**
	 * Constructor for use if a grid field is unavailable
	 * 
	 * @param label
	 *            column name (not displayed)
	 * @param description
	 *            description of component
	 * @param mandatory
	 *            whether a selection must be made
	 * @param readonly
	 *            whether or not the editor is read only
	 * @param updateable
	 *            whether the editor contents can be changed
	 */
	public WDateEditor (String label, String description, boolean mandatory, boolean readonly, boolean updateable)
	{
		super(new Datebox(), label, description, mandatory, readonly, updateable);
		
		this.datebox = (Datebox)super.component;
		setColumnName("Date");
	}
	
	public WDateEditor()
	{
		this("Date", "Date", false, false, true);
	}   // VDate
    
    public void onEvent(Event event)
    {
        Date date = datebox.getValue();
        Timestamp newValue = null;
        
        if (date != null)
        {
            newValue = new Timestamp(date.getTime());
        }
        
        ValueChangeEvent changeEvent = new ValueChangeEvent(this, this.getColumnName(), oldValue, newValue);
        super.fireValueChange(changeEvent);
        oldValue = newValue;
    }

    @Override
    public String getDisplay()
    {
        return null;
    }

    @Override
    public Object getValue()
    {
        return null;
    }

    @Override
    public boolean isMandatory()
    {
        return false;
    }

    @Override
    public void setMandatory(boolean mandatory)
    {
    }

    @Override
    public void setValue(Object value)
    {
    	if (value == null)
    	{
    		oldValue = null;
    	}
    	else if (value instanceof Timestamp)
        {
            datebox.setValue((Timestamp)value);
            oldValue = (Timestamp)value;
        }
        else
        {
            logger.log(Level.SEVERE, "New field value is not of type timestamp");
        }
    }
    	
	public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

}
