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

package org.adempiere.webui.component;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class Checkbox extends org.zkoss.zul.Checkbox
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3306775098016676628L;
	
	private Object m_oldValue;

	public void setEnabled(boolean enabled)
    {
        this.setDisabled(!enabled);
    }
    
    public boolean isEnabled()
    {
    	return !this.isDisabled();
    }

    /**
     * alias for setLabel, added to ease porting of swing form
     * @param label
     */
	public void setText(String label) {
		if (label != null)
			label = label.replaceAll("[&]", "");
		setLabel(label);
	}

	/**
	 * alias for isChecked, to ease porting of swing form
	 * @return
	 */
	public boolean isSelected() {
		return isChecked();
	}

	/**
	 * alias for setChecked, to ease porting of swing form
	 * @param checked
	 */
	public void setSelected(boolean checked) {
		setChecked(checked);
	}

	/**
	 * alias for addEventListener(Events.ON_CHECK, listener), to ease porting of swing form
	 * @param listener
	 */
	public void addActionListener(EventListener listener) {
		addEventListener(Events.ON_CHECK, listener);
	}

	/**
	 *	Return Editor value
	 *  @return value
	 */
	public Object getValue()
	{
		return Boolean.valueOf(isSelected());
	}	//	getValue

	/**
	 * Set the old value of the field.  For use in future comparisons.
	 * The old value must be explicitly set though this call.
	 * @param m_oldValue
	 */
	public void set_oldValue() {
		this.m_oldValue = getValue();
	}
	/**
	 * Get the old value of the field explicitly set in the past
	 * @return
	 */
	public Object get_oldValue() {
		return m_oldValue;
	}
	/**
	 * Has the field changed over time?
	 * @return true if the old value is different than the current.
	 */
	public boolean hasChanged() {
		// Both or either could be null
		if(getValue() != null)
			if(m_oldValue != null)
				return !m_oldValue.equals(getValue());
			else
				return true;
		else  // getValue() is null
			if(m_oldValue != null)
				return true;
			else
				return false;
	}

}
