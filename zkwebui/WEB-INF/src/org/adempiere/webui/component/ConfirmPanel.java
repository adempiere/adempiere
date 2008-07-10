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

import java.util.Iterator;
import java.util.List;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Messagebox;
/**
 * Application Confirm Panel
 * Web UI port of the rich client's ConfirmPanel by Jorg Janke
 * @author Sendy Yagambrum
 * @date July 25, 2007
 **/
public final class ConfirmPanel extends Hbox
{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Creates a button of the specified id
     * 
     * @param id button id
     * @return  button
     * 
     * <p>The string can be any of the following and the corresponding button will be created: </p>
     * <dl>
     * <dt>Ok</dt>          <dd>Ok button</dd>
     * <dt>Cancel</dt>      <dd>Cancel button</dd>
     * <dt>Refresh</dt>     <dd>Refresh button</dd>
     * <dt>Reset</dt>       <dd>Reset button</dd>
     * <dt>History</dt>     <dd>History button</dd>
     * <dt>Process</dt>     <dd>Process button</dd>
     * <dt>New</dt>         <dd>New button</dd>
     * <dt>Customize</dt>   <dd>Customize button</dd>
     * <dt>Delete</dt>      <dd>Delete button</dd>
     * <dt>Save</dt>        <dd>Save button</dd>
     * <dt>Zoom</dt>        <dd>Zoom button</dd>
     * <dt>Help</dt>        <dd>Help button</dd>
     * </dl>
     * 
     */
    public Button createButton(String name)
    {
        Button button = new Button();
        button.setName("btn"+name);
        button.setId(name);
        button.setSrc("images/"+name+"24.gif");
        return button;
    }
    
   /**
    * create confirm panel with multiple options
    * @param withCancelButton       with cancel
    * @param withRefreshButton      with refresh
    * @param withResetButton        with reset
    * @param withCustomizeButton    with customize
    * @param withHistoryButton      with history
    * @param withZoomButton         with zoom
    */
    public ConfirmPanel(boolean withCancelButton,
            boolean withRefreshButton, 
            boolean withResetButton, 
            boolean withCustomizeButton,
            boolean withHistoryButton, 
            boolean withZoomButton)
    {
        init();
        
        setVisible("Cancel", withCancelButton);      
        addComponentsRight(createButton("Ok"));
        addComponentsRight(createButton("Cancel"));   
                 
        if (withRefreshButton)
        {
             addComponentsLeft(createButton("Refresh"));
        }
        if (withResetButton)
        {
            addComponentsLeft(createButton("Reset"));
        }
        if (withCustomizeButton)
        {
            addComponentsLeft(createButton("Customize"));
        }
        if (withHistoryButton)
        {
            addComponentsLeft(createButton("History"));
        }
        if (withZoomButton)
        {
            addComponentsLeft(createButton("Zoom"));
        }                  
    }    
    
    /**
     * Create confirm panel with Ok button only
     */
    public ConfirmPanel()
    {
        this(false,false,false,false,false,false);
    }
    
    /**
     * Create confirm panel with Ok and Cancel button 
     * @param withCancel with cancel
     *   
     */
    public ConfirmPanel(boolean withCancel)
    {
        this(true,false,false,false,false,false);
    }
    //    
    private Hbox hboxBtnLeft;
    private Hbox hboxBtnRight;   
    //
    private Panel pnlBtnRight;
    private Panel pnlBtnLeft;
   
    /**
     * initialise components     
     */
    private void init()
    {
        pnlBtnLeft = new Panel();
        pnlBtnLeft.setAlign("left");
        
        pnlBtnRight = new Panel();
        pnlBtnRight.setAlign("right");
        
        hboxBtnRight = new Hbox();
        hboxBtnRight.appendChild(pnlBtnRight);
        hboxBtnRight.setWidth("100%");
        hboxBtnRight.setStyle("text-align:right");
       
        hboxBtnLeft = new Hbox();
        hboxBtnLeft.appendChild(pnlBtnLeft);
        hboxBtnLeft.setWidth("100%");
        hboxBtnLeft.setStyle("text-align:left");     
        
        this.appendChild(hboxBtnLeft);
        this.appendChild(hboxBtnRight);
        this.setWidth("100%");
    }
    
    /**
     * add button to the left side of the confirm panel 
     * @param button button
     */
    public void addComponentsLeft(Button button)
    {  
        pnlBtnLeft.appendChild(button);        
    }
    
    /**
     * add button to the right side of the confirm panel 
     * @param button button
     */
    public void addComponentsRight(Button button)
    {
        pnlBtnRight.appendChild(button);
    } 
    
    /**
     * return button of the specified id
     * @param id button id
     * @return button or null if no button is found
     * <p> The button id can be any of the following
     * <dl>
     * <dt>Ok</dt>          <dd>Ok button</dd>
     * <dt>Cancel</dt>      <dd>Cancel button</dd>
     * <dt>Refresh</dt>     <dd>Refresh button</dd>
     * <dt>Reset</dt>       <dd>Reset button</dd>
     * <dt>History</dt>     <dd>History button</dd>
     * <dt>Process</dt>     <dd>Process button</dd>
     * <dt>New</dt>         <dd>New button</dd>
     * <dt>Customize</dt>   <dd>Customize button</dd>
     * <dt>Delete</dt>      <dd>Delete button</dd>
     * <dt>Save</dt>        <dd>Save button</dd>
     * <dt>Zoom</dt>        <dd>Zoom button</dd>
     * <dt>Help</dt>        <dd>Help button</dd>
     * </dl>
     */
    public Button getButton(String id)
    {
        return (Button)this.getFellowIfAny(id);
    }
    
    /**
     * sets the visibility of the specified button
     * @param btnName   button name
     * @param visible   visibility
     * <p> The button name can be any of the following
     * <dl>
     * <dt>Ok</dt>          <dd>Ok button</dd>
     * <dt>Cancel</dt>      <dd>Cancel button</dd>
     * <dt>Refresh</dt>     <dd>Refresh button</dd>
     * <dt>Reset</dt>       <dd>Reset button</dd>
     * <dt>History</dt>     <dd>History button</dd>
     * <dt>Process</dt>     <dd>Process button</dd>
     * <dt>New</dt>         <dd>New button</dd>
     * <dt>Customize</dt>   <dd>Customize button</dd>
     * <dt>Delete</dt>      <dd>Delete button</dd>
     * <dt>Save</dt>        <dd>Save button</dd>
     * <dt>Zoom</dt>        <dd>Zoom button</dd>
     * <dt>Help</dt>        <dd>Help button</dd>
     * </dl>
     */
    public void setVisible(String id, boolean visible)
    {
        Button btn = getButton(id);
        if (btn != null)
        {
            btn.setVisible(visible);
        }
    }
    /**
     * returns whether the specified button is visible or not
     * @param btnName
     * @return visibility of the button
     * <p> The button name can be any of the following
     * <dl>
     * <dt>Ok</dt>          <dd>Ok button</dd>
     * <dt>Cancel</dt>      <dd>Cancel button</dd>
     * <dt>Refresh</dt>     <dd>Refresh button</dd>
     * <dt>Reset</dt>       <dd>Reset button</dd>
     * <dt>History</dt>     <dd>History button</dd>
     * <dt>Process</dt>     <dd>Process button</dd>
     * <dt>New</dt>         <dd>New button</dd>
     * <dt>Customize</dt>   <dd>Customize button</dd>
     * <dt>Delete</dt>      <dd>Delete button</dd>
     * <dt>Save</dt>        <dd>Save button</dd>
     * <dt>Zoom</dt>        <dd>Zoom button</dd>
     * <dt>Help</dt>        <dd>Help button</dd>
     * </dl>
     */
    public boolean isVisible(String btnName)
    {
        Button btn = getButton(btnName);
        if (btn != null)
        {
            return btn.isVisible();
        }
        else
        {
            try
            {
                Messagebox.show("No "+btnName+" button available");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
    }
    /**
     * enable specific button
     * @param id   button id
     * @param enabled   enabled
     * 
     * <p> The button id can be any of the following
     * <dl>
     * <dt>Ok</dt>          <dd>Ok button</dd>
     * <dt>Cancel</dt>      <dd>Cancel button</dd>
     * <dt>Refresh</dt>     <dd>Refresh button</dd>
     * <dt>Reset</dt>       <dd>Reset button</dd>
     * <dt>History</dt>     <dd>History button</dd>
     * <dt>Process</dt>     <dd>Process button</dd>
     * <dt>New</dt>         <dd>New button</dd>
     * <dt>Customize</dt>   <dd>Customize button</dd>
     * <dt>Delete</dt>      <dd>Delete button</dd>
     * <dt>Save</dt>        <dd>Save button</dd>
     * <dt>Zoom</dt>        <dd>Zoom button</dd>
     * <dt>Help</dt>        <dd>Help button</dd>
     * </dl>
     */
    public void setEnabled(String id, boolean enabled)
    {
        Button button = getButton(id);
        if (button != null)
        {
            button.setEnabled(enabled);
        }
    }
    
    /**
     * enable all components
     * @param enabled enabled
     */
    public void setEnabledAll(boolean enabled)
    {
        List list1 = pnlBtnLeft.getChildren();
        List list2 = pnlBtnRight.getChildren();
        Iterator iter1 = list1.iterator();
        Iterator iter2 = list2.iterator();
       
        while (iter1.hasNext())
        {
            Button button = (Button)iter1.next();
            button.setEnabled(enabled);
        }
        while (iter2.hasNext())
        {
            Button button = (Button)iter2.next();
            button.setEnabled(enabled);
        }
    }
    /**
     * add action listener on the existing buttons
     * @param event event
     * @param listener listener
     */
    public void addActionListener(String event, EventListener listener)
    {
        List list1 = pnlBtnLeft.getChildren();
        List list2 = pnlBtnRight.getChildren();
        Iterator iter1 = list1.iterator();
        Iterator iter2 = list2.iterator();
       
        while (iter1.hasNext())
        {
            Button button = (Button)iter1.next();
            button.addEventListener(event, listener);
        }
        while (iter2.hasNext())
        {
            Button button = (Button)iter2.next();
            button.addEventListener(event, listener);
        }
    }
    
}   //  ConfirmPanel
