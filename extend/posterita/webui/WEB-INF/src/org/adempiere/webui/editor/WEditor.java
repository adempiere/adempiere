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

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.EditorBox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Locationbox;
import org.adempiere.webui.component.Searchbox;
import org.adempiere.webui.component.Urlbox;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.impl.InputElement;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 11, 2007
 * @version $Revision: 0.10 $
 */
public abstract class WEditor implements EventListener, PropertyChangeListener
{
    private static final String[] lISTENER_EVENTS = {};

    public static final int MAX_DISPLAY_LENGTH = 35;

    protected GridField gridField;

    protected GridTab gridTab;

    protected Label label;

    protected Component component;

    protected boolean mandatory;

    protected ArrayList<ValueChangeListener> listeners = new ArrayList<ValueChangeListener>();

    private String strLabel;

    private String description;

    private boolean readOnly;

    private boolean updateable;

    private String columnName;

    public WEditor(Component comp, GridField gridField)
    {
        if (comp == null)
        {
            throw new IllegalArgumentException("Component cannot be null");
        }

        if (gridField == null)
        {
            throw new IllegalArgumentException("Grid field cannot be null");
        }

        this.setComponent(comp);
        this.gridField = gridField;
        this.setMandatory(gridField.isMandatory(false));
        this.readOnly = gridField.isReadOnly();
        this.description = gridField.getDescription();
        this.updateable = gridField.isUpdateable();
        this.columnName = gridField.getColumnName();
        this.strLabel = gridField.getHeader();
        init();
    }

    /**
     * Method is used to distinguish between 2 similar WSearchEditors
     *
     */
    public String getDescription()
    {
    	return description;

    }

	/**
	 * Constructor for use if a grid field is unavailable
	 *
	 * @param comp			The editor's component
	 * @param label			column name (not displayed)
	 * @param description	description of component
	 * @param mandatory		whether a selection must be made
	 * @param readonly		whether or not the editor is read only
	 * @param updateable	whether the editor contents can be changed
	 */
    public WEditor(Component comp, String label, String description, boolean mandatory, boolean readonly, boolean updateable)
    {
    	if (comp == null)
        {
            throw new IllegalArgumentException("Component cannot be null");
        }

    	this.setComponent(comp);
    	this.setMandatory(mandatory);
        this.readOnly = readonly;
        this.description = description;
        this.updateable = updateable;
        this.strLabel = label;
        init();
    }

    /**
     * Set the editor component.
     * @param comp the editor component
     */
    protected void setComponent(Component comp)
    {
        this.component = comp;
    }

    private void init()
    {
        label = new Label("");
        label.setValue(strLabel);
        label.setTooltiptext(description);


        this.setMandatory (mandatory);

        if (readOnly || !updateable)
        {
            this.setReadWrite(false);
        }
        else
        {
            this.setReadWrite(true);
        }

        ((HtmlBasedComponent)component).setTooltiptext(description);
        label.setTooltiptext(description);
    }

    public GridField getGridField()
    {
        return gridField;
    }

    public String getColumnName()
    {
        return columnName;
    }

    /**
     * Remove the table qualifier from the supplied column name.
     *
     * The column name may be prefixed with the table name
     * i.e. <code>[table name].[column name]</code>.
     * The function returns
     *
     * @param originalColumnName	The column name to clean
     * @return 	the column name with any table qualifier removed
     * 			i.e. <code>[column name]</code>
     */
    protected String cleanColumnName(String originalColumnName)
	{
		String cleanColumnName;
		/*
		 *  The regular expression to use to find the table qualifier.
		 *  Matches "<table name>."
		 */
		final String regexTablePrefix = ".*\\.";

		cleanColumnName = originalColumnName.replaceAll(regexTablePrefix, "");

		return cleanColumnName;
	}

    protected void setColumnName(String columnName)
    {
    	String cleanColumnName = cleanColumnName(columnName);
    	this.columnName = cleanColumnName;
    }

    public Component getComponent()
    {
        return component;
    }

    public void setGridTab(GridTab gridTab)
    {
    	this.gridTab = gridTab;
    }

    public WEditorPopupMenu getPopupMenu()
    {
        return null;
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
        {
            setValue((evt.getNewValue()));
        }
    }

    public void addValueChangeListner(ValueChangeListener listener)
    {
        if (listener == null)
        {
            return;
        }

        if (listeners.size() == 0)
        {
            for (String event : this.getEvents())
            {
                component.addEventListener(event, this);
            }
        }
        listeners.add(listener);
    }

    protected void fireValueChange(ValueChangeEvent event)
    {
        for (ValueChangeListener listener : listeners)
        {
            listener.valueChange(event);
        }
    }

    public Label getLabel()
    {
        return label;
    }

    public void setReadWrite(boolean readWrite)
    {
        if (component instanceof Checkbox)
        {
            ((Checkbox)component).setEnabled(readWrite);
        }
        else if (component instanceof Button)
        {
            ((Button)component).setEnabled(readWrite);
        }
        else if (component instanceof Listbox)
        {
            ((Listbox)component).setEnabled(readWrite);
        }
        else if (component instanceof Datebox)
        {
            ((Datebox)component).setEnabled(readWrite);
        }
        else if (component instanceof Urlbox)
        {
            ((Urlbox)component).setEnabled(readWrite);
        }
        else if (component instanceof Searchbox)
        {
        	((Searchbox)component).setEnabled(readWrite);
        }
        else if (component instanceof Locationbox)
        {
            ((Locationbox)component).setEnabled(readWrite);
        }
        else if (component instanceof EditorBox)
        {
        	((EditorBox)component).setEnabled(readWrite);
        }
        else
        {
            ((InputElement)component).setReadonly(!readWrite);
        }
    }

    public boolean isReadWrite()
    {
        if (component instanceof Checkbox)
        {
            return ((Checkbox)component).isDisabled();
        }
        else if (component instanceof Button)
        {
            return ((Button)component).isEnabled();
        }
        else if (component instanceof Listbox)
        {
            return ((Listbox)component).isEnabled();
        }
        else if (component instanceof Searchbox)
        {
        	return ((Searchbox)component).isEnabled();
        }
        else if (component instanceof Locationbox)
        {
            return ((Locationbox)component).isEnabled();
        }
        else if (component instanceof EditorBox)
        {
        	return ((EditorBox)component).isEnabled();
        }
        else
        {
            return ((InputElement)component).isReadonly();
        }
    }

    public void setVisible(boolean visible)
    {
        label.setVisible(visible);
        component.setVisible(visible);
    }

    public boolean isVisible()
    {
        return component.isVisible();
    }

    public void setBackground(boolean error)
    {

    }

    public void setBackground(Color color)
    {

    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer(30);
        sb.append(this.getClass().getName());
        sb.append("[").append(this.getColumnName());
        sb.append("=");
        sb.append(this.getValue()).append("]");
        return sb.toString();
    }

    abstract public void setValue(Object value);

    abstract public Object getValue();

    abstract public String getDisplay();

    public String[] getEvents()
    {
        return WEditor.lISTENER_EVENTS;
    }

    /**
     * Set whether the editor represents a mandatory field.
     * @param mandatory whether the field is mandatory
     */
    public void setMandatory (boolean mandatory)
    {
        this.mandatory = mandatory;
    }

    public boolean isMandatory()
    {
        return this.mandatory;
    }

    public boolean isAsap()
    {
        return true;
    }
}
