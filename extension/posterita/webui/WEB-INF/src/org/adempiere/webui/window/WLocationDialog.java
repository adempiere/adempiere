/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.window;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * @author Sendy Yagambrum
 * @date July 16, 2007
 * Location Dialog Box
 * This class is based upon VLocationDialog, written by Jorg Janke
 * 
 **/
public class WLocationDialog extends Window implements EventListener
{
    
    private static final long serialVersionUID = 1L;
    /** Logger          */
    private static CLogger log = CLogger.getCLogger(WLocationDialog.class);
    private Label lblAddress1;
    private Label lblAddress2;
    private Label lblAddress3;
    private Label lblAddress4;
    private Label lblCity;
    private Label lblZip;
    private Label lblRegion;
    private Label lblPostal;
    private Label lblPostalAdd;
    private Label lblCountry;
    
    private Textbox txtAddress1;
    private Textbox txtAddress2;
    private Textbox txtAddress3;
    private Textbox txtAddress4;
    private Textbox txtCity;
    private Textbox txtPostal;
    private Textbox txtPostalAdd;
    private Listbox lstRegion;
    private Listbox lstCountry;
    
    private Button btnOk;
    private Button btnCancel;
    private VerticalBox mainPanel;
    
    private boolean     m_change = false;
    private MLocation   m_location;
    private int         m_origCountry_ID;
    private int         s_oldCountry_ID = 0;
    
    public WLocationDialog(String title, MLocation location)
    {
        m_location = location;
        if (m_location == null)
            m_location = new MLocation (Env.getCtx(), 0, null);
        //  Overwrite title 
        if (m_location.getC_Location_ID() == 0)
            setTitle(Msg.getMsg(Env.getCtx(), "LocationNew"));
        else
            setTitle(Msg.getMsg(Env.getCtx(), "LocationUpdate"));    
        
        initComponents();
        init();
//      Current Country
        MCountry.setDisplayLanguage(Env.getAD_Language(Env.getCtx()));
        for (MCountry country:MCountry.getCountries(Env.getCtx()))
        {
            lstCountry.appendItem(country.getName(), country);
        }
        setCountry();
        lstCountry.addEventListener(Events.ON_SELECT,this);
        m_origCountry_ID = m_location.getC_Country_ID();
        //  Current Region
        for (MRegion region : MRegion.getRegions(Env.getCtx(), m_origCountry_ID))
        {
            lstRegion.appendItem(region.getName(),region);
        }
        if (m_location.getCountry().isHasRegion())
            lblRegion.setValue(m_location.getCountry().getRegionName());   //  name for region
        
        setRegion();
        initLocation();
        //               
        this.setWidth("290px");
        this.setClosable(true);
        this.setBorder("normal");
        this.setAttribute("mode","modal");
    }
    
    private void initComponents()
    {
        lblAddress1     = new Label(Msg.getMsg(Env.getCtx(), "Address")+ " 1");
        lblAddress2     = new Label(Msg.getMsg(Env.getCtx(), "Address")+ " 2");
        lblAddress3     = new Label(Msg.getMsg(Env.getCtx(), "Address")+ " 3");
        lblAddress4     = new Label(Msg.getMsg(Env.getCtx(), "Address")+ " 4");
        lblCity         = new Label(Msg.getMsg(Env.getCtx(), "City"));
        lblZip          = new Label(Msg.getMsg(Env.getCtx(), "Postal"));
        lblRegion       = new Label(Msg.getMsg(Env.getCtx(), "Region"));
        lblPostal       = new Label(Msg.getMsg(Env.getCtx(), "Postal"));
        lblPostalAdd    = new Label(Msg.getMsg(Env.getCtx(), "PostalAdd"));
        lblCountry      = new Label(Msg.getMsg(Env.getCtx(), "Country"));
        
        txtAddress1 = new Textbox();
        txtAddress1.setCols(20);
        txtAddress2 = new Textbox();
        txtAddress2.setCols(20);
        txtAddress3 = new Textbox();
        txtAddress3.setCols(20);
        txtAddress4 = new Textbox();
        txtAddress4.setCols(20);
        txtCity     = new Textbox();
        txtCity.setCols(20);
        txtPostal = new Textbox();
        txtPostal.setCols(20);
        txtPostalAdd = new Textbox();
        txtPostalAdd.setCols(20);
        
        lstRegion    = new Listbox();
        lstRegion.setMold("select");
        lstRegion.setWidth("50px");
        lstRegion.setRows(0);
        
        lstCountry  = new Listbox();
        lstCountry.setMold("select");
        lstCountry.setWidth("154px");
        lstCountry.setRows(0);
        
        btnOk = new Button();
        btnOk.setImage("/images/Ok16.gif");
        btnOk.addEventListener(Events.ON_CLICK,this);
        btnCancel = new Button();
        btnCancel.setImage("/images/Cancel16.gif");
        btnCancel.addEventListener(Events.ON_CLICK,this);
        
        mainPanel = new VerticalBox();
        mainPanel.setStyle("padding:5px");
    }
    
    private void init()
    {
        Panel pnlAddress1 = new Panel();
        pnlAddress1.appendChild(lblAddress1);
        pnlAddress1.appendChild(txtAddress1);
        pnlAddress1.setAlign("right");
        
        Panel pnlAddress2 = new Panel();
        pnlAddress2.appendChild(lblAddress2);
        pnlAddress2.appendChild(txtAddress2);
        pnlAddress2.setAlign("right");                
        
        Panel pnlAddress3 = new Panel();
        pnlAddress3.appendChild(lblAddress3);
        pnlAddress3.appendChild(txtAddress3);
        pnlAddress3.setAlign("right");      
        
        Panel pnlAddress4 = new Panel();
        pnlAddress4.appendChild(lblAddress4);
        pnlAddress4.appendChild(txtAddress4);
        pnlAddress4.setAlign("right");      
        
        Panel pnlCity     = new Panel();
        pnlCity.appendChild(lblCity);
        pnlCity.appendChild(txtCity);
        pnlCity.setAlign("right");   
        
        Panel pnlPostal   = new Panel();
        pnlPostal.appendChild(lblPostal);
        pnlPostal.appendChild(txtPostal);
        pnlPostal.setAlign("right");
        
        Panel pnlPostalAdd = new Panel();
        pnlPostalAdd.appendChild(lblPostalAdd);
        pnlPostalAdd.appendChild(txtPostalAdd);
        
        Panel pnlRegion    = new Panel();
        pnlRegion.appendChild(lblRegion);
        pnlRegion.appendChild(lstRegion);
        pnlRegion.setStyle("text-align:right;padding-right:103px");
        
        Panel pnlCountry  = new Panel();
        pnlCountry.appendChild(lblCountry);
        pnlCountry.appendChild(lstCountry);
        pnlCountry.setAlign("right");
        
        Panel pnlButton   = new Panel();
        pnlButton.appendChild(btnOk);
        pnlButton.appendChild(btnCancel);
        pnlButton.setWidth("100%");
        pnlButton.setStyle("text-align:right");
               
        this.appendChild(mainPanel);
        this.appendChild(pnlButton);
    }
    /**
     * Dynamically add fields to the Location dialog box
     * @param panel panel to add
    *
     */
    private void addComponents(Panel panel)
    {
        mainPanel.appendChild(panel);
    }
    
    private void initLocation()
    {
        MCountry country = m_location.getCountry();
        log.fine(country.getName() + ", Region=" + country.isHasRegion() + " " + country.getDisplaySequence()
            + ", C_Location_ID=" + m_location.getC_Location_ID());
        //  new Region
        if (m_location.getC_Country_ID() != s_oldCountry_ID && country.isHasRegion())
        {
            for (MRegion region : MRegion.getRegions(Env.getCtx(), country.getC_Country_ID()))
            {
                lstRegion.appendItem(region.getName(),region);
            }
            if (m_location.getRegion() != null)
            {
               setRegion();
            }
            s_oldCountry_ID = m_location.getC_Country_ID();
        }
        addComponents((Panel)lblAddress1.getParent());
        addComponents((Panel)lblAddress2.getParent());
        addComponents((Panel)lblAddress3.getParent());
        addComponents((Panel)lblAddress4.getParent());
//      sequence of City Postal Region - @P@ @C@ - @C@, @R@ @P@
        String ds = country.getDisplaySequence();
        if (ds == null || ds.length() == 0)
        {
            log.log(Level.SEVERE, "DisplaySequence empty - " + country);
            ds = "";    //  @C@,  @P@
        }
        StringTokenizer st = new StringTokenizer(ds, "@", false);
        while (st.hasMoreTokens())
        {
            String s = st.nextToken();
            if (s.startsWith("C"))
                addComponents((Panel)lblCity.getParent());
            else if (s.startsWith("P"))
                addComponents((Panel)lblPostal.getParent());
            else if (s.startsWith("A"))
                addComponents((Panel)lblPostalAdd.getParent());
            else if (s.startsWith("R") && m_location.getCountry().isHasRegion())
                addComponents((Panel)lblRegion.getParent());
        }
        //  Country Last
        addComponents((Panel)lblCountry.getParent());

//      Fill it
        if (m_location.getC_Location_ID() != 0)
        {
            txtAddress1.setText(m_location.getAddress1());
            txtAddress2.setText(m_location.getAddress2());
            txtAddress3.setText(m_location.getAddress3());
            txtAddress4.setText(m_location.getAddress4());
            txtCity.setText(m_location.getCity());
            txtPostal.setText(m_location.getPostal());
            txtPostalAdd.setText(m_location.getPostal_Add());
            if (m_location.getCountry().isHasRegion())
            {
                lblRegion.setValue(m_location.getCountry().getRegionName());
                setRegion();                
            }
            setCountry();
        }
    }
    private void setCountry()
    {
        List listCountry = lstCountry.getChildren();
        Iterator iter = listCountry.iterator();
        while (iter.hasNext())
        {
            ListItem listitem = (ListItem)iter.next();
            if (m_location.getCountry().equals(listitem.getValue()))
            {
                lstCountry.setSelectedItem(listitem);
            }
        }
    }
    private void setRegion()
    {
        List listState = lstRegion.getChildren();
        Iterator iter = listState.iterator();
        while (iter.hasNext())
        {
            ListItem listitem = (ListItem)iter.next();
            if (m_location.getRegion().equals(listitem.getValue()))
            {
                lstRegion.setSelectedItem(listitem);
            }
        }
        
    }
    /**
     *  Get result
     *  @return true, if changed
     */
    public boolean isChanged()
    {
        return m_change;
    }   //  getChange
    /**
     *  Get edited Value (MLocation)
     *  @return location
     */
    public MLocation getValue()
    {
        return m_location;
    }   

    public void onEvent(Event event) throws Exception
    {
        if (btnOk.equals(event.getTarget()))
        {
            action_OK();
            m_change = true;
            this.detach();
        }
        else if (btnCancel.equals(event.getTarget()))
        {
            m_change = false;
            this.detach();
        }

        //  Country Changed - display in new Format
        else if (lstCountry.equals(event.getTarget()))
        {
            //  Modifier for Mouse selection is 16  - for any key selection 0
            MCountry c = (MCountry)lstCountry.getSelectedItem().getValue();
            m_location.setCountry(c);
            //  refrseh
            initLocation();
        }        
    }
    /**
     *  OK - check for changes (save them) & Exit
     */
    private void action_OK()
    {
        m_location.setAddress1(txtAddress1.getValue());
        m_location.setAddress2(txtAddress2.getValue());
        m_location.setAddress3(txtAddress3.getValue());
        m_location.setAddress4(txtAddress4.getValue());
        m_location.setCity(txtCity.getValue());
        m_location.setPostal(txtPostal.getValue());
        //  Country/Region
        MCountry c = (MCountry)lstCountry.getSelectedItem().getValue();
        m_location.setCountry(c);
        if (m_location.getCountry().isHasRegion())
        {
            MRegion r = (MRegion)lstRegion.getSelectedItem().getValue();
            m_location.setRegion(r);
        }
        else
            m_location.setC_Region_ID(0);
        //  Save chnages
        m_location.save();
        
    }   //  actionOK
}
