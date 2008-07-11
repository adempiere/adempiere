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


import org.adempiere.webui.component.Button;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.window.WImageDialog;
import org.compiere.model.GridField;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

/**
 * This class is based on org.compiere.grid.ed.VButton written by Jorg Janke.
 * @author Jorg Janke
 * 
 * Modifications - UI Compatibility
 * @author ashley
 */
public class WImageEditor extends WEditor
{
    private static final String[] LISTENER_EVENTS = {};
    
    private static final CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(WImageEditor.class);
    }
    
    /** The Image Model         */
	private MImage  m_mImage = null;
	/** Column Name             */
	private String	m_columnName = "AD_Image_ID";
	
    private boolean         m_mandatory;
    
    /**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WImageEditor.class);
    
    public WImageEditor(GridField gridField)
    {
        super(new Button(), gridField);
        init();
    }

    @Override
    public Button getComponent() {
    	return (Button) component;
    }
    
    private void init()
    {
        getComponent().setLabel("-");
        getComponent().addEventListener(Events.ON_CLICK, this);
    }

     @Override
    public String getDisplay()
    {
    	 return m_mImage.getName();
    }

    @Override
    public Object getValue()
    {
    	if (m_mImage == null || m_mImage.get_ID() == 0)
			return null;
		return new Integer(m_mImage.get_ID());
    }

    @Override
    public boolean isMandatory()
    {
        return m_mandatory;
    }
   
    
    @Override
    public void setMandatory(boolean mandatory)
    {
        m_mandatory = mandatory;
    }

    @Override
    public void setValue(Object value)
    {
    	int newValue = 0;
		if (value instanceof Integer)
			newValue = ((Integer)value).intValue();
		if (newValue == 0)
		{
			m_mImage = null;
			getComponent().setLabel("-");
			return;
		}
		//  Get/Create Image
		if (m_mImage == null || newValue != m_mImage.get_ID())
			m_mImage = MImage.get (Env.getCtx(), newValue);
		//
		log.fine(m_mImage.toString());
		getComponent().setLabel(m_mImage.getName());
		if (m_mImage.getDescription() != null)
			getComponent().setTooltip(m_mImage.getDescription());
    }
    
    @Override
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

	public void onEvent(Event event) throws Exception 
	{
		if (Events.ON_CLICK.equals(event.getName()))
		{
			WImageDialog vid = new WImageDialog(m_mImage);
			int AD_Image_ID = vid.getAD_Image_ID();
			Object oldValue = getValue();
			Integer newValue = null;
			if (AD_Image_ID != 0)
				newValue = new Integer (AD_Image_ID);
			//
			m_mImage = null;	//	force reload
			setValue(newValue);	//	set explicitly
			//
			ValueChangeEvent vce = new ValueChangeEvent(this, gridField.getColumnName(), oldValue, newValue);
			fireValueChange(vce);
		}
	}
}
