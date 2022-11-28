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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Locationbox;
import org.adempiere.webui.event.ContextMenuEvent;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.WRecordInfo;
import org.adempiere.webui.window.WLocationDialog;
import org.compiere.model.GridField;
import org.compiere.model.MLocation;
import org.compiere.model.MLocationLookup;
import org.compiere.model.MOrgInfo;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * @author Sendy Yagambrum
 * @date July 16, 2007
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 		<li>BF [ 3294610] The location should allow open a google map
 * 		<li>https://sourceforge.net/tracker/?func=detail&atid=879335&aid=3294610&group_id=176962
 * 		<a href="https://github.com/adempiere/adempiere/issues/886">
 * 		@see FR [ 886 ] System config Google Map</a>
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1150">
 * 		@see FR [ 1150 ] The url location based on google map not work when the location is empty or with data</a>
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1158">
 * 		@see FR [ 1158 ] Problems with location address: wrong region and not showing region field</a>
 * This class is based on VLocation written by Jorg Janke
 **/
public class WLocationEditor extends WEditor implements EventListener, PropertyChangeListener, ContextMenuListener
{
    private static final String[] LISTENER_EVENTS = {Events.ON_CLICK};
    
    private static CLogger log = CLogger.getCLogger(WLocationEditor.class);
    private MLocationLookup locationLookup;
    private MLocation location;

	private WEditorPopupMenu popupMenu;
    
    /**
     * Constructor without GridField
     * @param columnName    column name
     * @param mandatory     mandatory
     * @param isReadOnly    read only
     * @param isUpdateable  updateable
     * @param locationLookup     location model
    **/
    public WLocationEditor(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
            MLocationLookup locationLookup)
    {
        super(new Locationbox(), "Address","",mandatory,isReadOnly,isUpdateable);
       
        setColumnName(columnName);
        this.locationLookup = locationLookup;
        init();
    }

    /**
     * 
     * @param gridField
     */
    public WLocationEditor(GridField gridField) {
		super(new Locationbox(), gridField);
		locationLookup = (MLocationLookup)gridField.getLookup();
        init();
	}

    private void init()
    {
    	popupMenu = new WEditorPopupMenu(false, false, true);
    	popupMenu.addMenuListener(this);
    	if (gridField != null && gridField.getGridTab() != null)
		{
			WRecordInfo.addMenu(popupMenu);
		}
    	getComponent().setContext(popupMenu.getId());
    }
    
	@Override
    public String getDisplay()
    {
        return getComponent().getText();
    }

    @Override
    public Object getValue()
    {
        if (location == null)
            return null;
        return location.getC_Location_ID();
    }

    @Override
    public void setValue(Object value)
    {
        if (value == null)
        {
            location = null;
            getComponent().setText(null);
        }
        else
        {
            int locationId = (Integer)value;
            location = new MLocation(Env.getCtx(), locationId , null);
            if (location == null)
                getComponent().setText("<" + value + ">");
            else
                getComponent().setText(location.toString());
        }
    }
    
    @Override
	public Locationbox getComponent() {
		return (Locationbox) component;
	}

	@Override
	public boolean isReadWrite() {
		return getComponent().isEnabled();
	}

	@Override
	public void setReadWrite(boolean readWrite) {
		getComponent().setEnabled(readWrite);
	}

	/**
     *  Return Editor value
     *  @return value
     */
    public int getC_Location_ID()
    {
        if (location == null)
            return 0;
        return location.getC_Location_ID();
    }   
    
    /**
     *  Property Change Listener
     *  @param evt PropertyChangeEvent
     */
    public void propertyChange (PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals(org.compiere.model.GridField.PROPERTY))
            setValue(evt.getNewValue());
    }
    
    public void onEvent(Event event) throws Exception
    {    
        //
        if ("onClick".equals(event.getName()))
        {
            location = new MLocation(Env.getCtx(), getC_Location_ID() , null);
        	if( ((Button)event.getTarget()).getName().equals("bUrl") )
        	{
                String urlString = MLocation.getMapUrl(location);
                String message = null;
                try {
                    //Executions.getCurrent().sendRedirect(urlString, "_blank");
                    Env.startBrowser(urlString+"&output=embed");
                }
                catch (Exception e) {
                    message = e.getMessage();
                    FDialog.warn(0, getComponent(), "URLnotValid", message);
                }
    			return;
        	}
            else if( ((Button)event.getTarget()).getName().equals("bRouteUrl") )
            {
                int orgId = Env.getAD_Org_ID(Env.getCtx());
                if (orgId != 0){
                    MOrgInfo orgInfo = 	MOrgInfo.get(Env.getCtx(), orgId,null);
                    MLocation orgLocation = new MLocation(Env.getCtx(),orgInfo.getC_Location_ID(),null);
                    String urlString = MLocation.getRouteUrl(orgLocation , location);
                    String message = null;
                    try {
                        Env.startBrowser(urlString+"&output=embed");
                        //Executions.getCurrent().sendRedirect(urlString, "_blank");
                    }
                    catch (Exception e) {
                        message = e.getMessage();
                        FDialog.warn(0, getComponent(), "URLnotValid", message);
                    }
                }
            }
        	else
        	{	
	            log.config( "actionPerformed - " + location);
	            WLocationDialog ld = new WLocationDialog(Msg.getMsg(Env.getCtx(), "Location"), location);
	            ld.setVisible(true);
	            AEnv.showWindow(ld);
	            location = ld.getValue();
	            //
	           if (!ld.isChanged())
	                return;

	            //  Data Binding
	            int locationId = 0;
	            if (location != null)
	                locationId = location.getC_Location_ID();
	            Integer ii = locationId;
	            //  force Change - user does not realize that embedded object is already saved.
	            ValueChangeEvent valuechange = new ValueChangeEvent(this,getColumnName(),null,null);
	            fireValueChange(valuechange);   //  resets m_mLocation
	            if (locationId != 0)
	            {
	                ValueChangeEvent vc = new ValueChangeEvent(this,getColumnName(),null,ii);
	                fireValueChange(vc);
	            }
	            setValue(ii); 
        	}
	     }
    }
    
    /**
     * return listener events to be associated with editor component
     */
    public String[] getEvents()
    {
        return LISTENER_EVENTS;
    }

    @Override
	public void onMenu(ContextMenuEvent evt) {
		if (WEditorPopupMenu.CHANGE_LOG_EVENT.equals(evt.getContextEvent()))
		{
			WRecordInfo.start(gridField);
		}
	}
}
