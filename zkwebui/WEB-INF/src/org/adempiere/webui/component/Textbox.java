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

import org.compiere.model.Obscure;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class Textbox extends org.zkoss.zul.Textbox implements EventListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2201466028538929955L;

	private Obscure	m_obscure = null;

    private Object m_oldValue = null;

    private boolean m_infocus;


    public Textbox()
    {
        super();
        addFocusListener(this);
    }

    public Textbox(String value) throws WrongValueException
    {
        super(value);
        addFocusListener(this);
    }

    public void setEnabled(boolean enabled)
    {
        this.setDisabled(!enabled);
    }
    
    public void setObscureType(String obscureType)
    {
    	if (obscureType != null && obscureType.length() > 0)
		{
			m_obscure = new Obscure ("", obscureType);
		}
    	else
    	{
    		m_obscure = null;
    	}
    	setValue(getValue());
    }

    /**
     * method to ease porting of swing form
     * @param listener
     */
	public void addFocusListener(EventListener listener) {
		addEventListener(Events.ON_FOCUS, listener);
		addEventListener(Events.ON_BLUR, listener);
	}

	
	@Override
	public String getValue() throws WrongValueException {
		String value = super.getValue();
		if (m_obscure != null && value != null && value.length() > 0)
		{
			if (value.equals(m_obscure.getObscuredValue()))
				value = m_obscure.getClearValue();
		}
		return value;
	}

	@Override
	public void setValue(String value) throws WrongValueException {
		if (m_obscure != null && !m_infocus)
		{
			super.setValue(m_obscure.getObscuredValue(value));
		}
		else
		{
			super.setValue(value);
		}
	}

	public void onEvent(Event event) throws Exception {
		if (Events.ON_FOCUS.equals(event.getName()))
		{
			m_infocus = true;
			if (m_obscure != null)
				setValue(getValue());
		}
		else if (Events.ON_BLUR.equals(event.getName()))
		{
			m_infocus = false;
			if (m_obscure != null)
				setValue(getValue());
		}		
	}

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


} //  Textbox
