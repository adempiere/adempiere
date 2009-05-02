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
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Textbox;
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
    /**
	 * 
	 */
	private static final long serialVersionUID = 7510181548669317310L;
	private static final String LABEL_STYLE = "white-space: nowrap;";
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
    private Grid mainPanel;
    
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
        if (m_location.getCountry().isHasRegion() &&
        	m_location.getCountry().getRegionName() != null &&
        	m_location.getCountry().getRegionName().trim().length() > 0)
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
        lblAddress1     = new Label(Msg.getElement(Env.getCtx(), "Address1"));
        lblAddress1.setStyle(LABEL_STYLE);
        lblAddress2     = new Label(Msg.getElement(Env.getCtx(), "Address2"));
        lblAddress2.setStyle(LABEL_STYLE);
        lblAddress3     = new Label(Msg.getElement(Env.getCtx(), "Address3"));
        lblAddress3.setStyle(LABEL_STYLE);
        lblAddress4     = new Label(Msg.getElement(Env.getCtx(), "Address4"));
        lblAddress4.setStyle(LABEL_STYLE);
        lblCity         = new Label(Msg.getMsg(Env.getCtx(), "City"));
        lblCity.setStyle(LABEL_STYLE);
        lblZip          = new Label(Msg.getMsg(Env.getCtx(), "Postal"));
        lblZip.setStyle(LABEL_STYLE);
        lblRegion       = new Label(Msg.getMsg(Env.getCtx(), "Region"));
        lblRegion.setStyle(LABEL_STYLE);
        lblPostal       = new Label(Msg.getMsg(Env.getCtx(), "Postal"));
        lblPostal.setStyle(LABEL_STYLE);
        lblPostalAdd    = new Label(Msg.getMsg(Env.getCtx(), "PostalAdd"));
        lblPostalAdd.setStyle(LABEL_STYLE);
        lblCountry      = new Label(Msg.getMsg(Env.getCtx(), "Country"));
        lblCountry.setStyle(LABEL_STYLE);
        
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
        lstRegion.setWidth("154px");
        lstRegion.setRows(0);
        
        lstCountry  = new Listbox();
        lstCountry.setMold("select");
        lstCountry.setWidth("154px");
        lstCountry.setRows(0);
        
        btnOk = new Button();
        btnOk.setImage("/images/Ok16.png");
        btnOk.addEventListener(Events.ON_CLICK,this);
        btnCancel = new Button();
        btnCancel.setImage("/images/Cancel16.png");
        btnCancel.addEventListener(Events.ON_CLICK,this);
        
        mainPanel = GridFactory.newGridLayout();
        mainPanel.setStyle("padding:5px");
    }
    
    private void init()
    {
        Row pnlAddress1 = new Row();
        pnlAddress1.appendChild(lblAddress1.rightAlign());
        pnlAddress1.appendChild(txtAddress1);        
        
        Row pnlAddress2 = new Row();
        pnlAddress2.appendChild(lblAddress2.rightAlign());
        pnlAddress2.appendChild(txtAddress2);
        
        Row pnlAddress3 = new Row();
        pnlAddress3.appendChild(lblAddress3.rightAlign());
        pnlAddress3.appendChild(txtAddress3);
        
        Row pnlAddress4 = new Row();
        pnlAddress4.appendChild(lblAddress4.rightAlign());
        pnlAddress4.appendChild(txtAddress4);
        
        Row pnlCity     = new Row();
        pnlCity.appendChild(lblCity.rightAlign());
        pnlCity.appendChild(txtCity);
        
        Row pnlPostal   = new Row();
        pnlPostal.appendChild(lblPostal.rightAlign());
        pnlPostal.appendChild(txtPostal);
        
        Row pnlPostalAdd = new Row();
        pnlPostalAdd.appendChild(lblPostalAdd.rightAlign());
        pnlPostalAdd.appendChild(txtPostalAdd);
        
        Row pnlRegion    = new Row();
        pnlRegion.appendChild(lblRegion.rightAlign());
        pnlRegion.appendChild(lstRegion);
        
        Row pnlCountry  = new Row();
        pnlCountry.appendChild(lblCountry.rightAlign());
        pnlCountry.appendChild(lstCountry);
        
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
    private void addComponents(Row row)
    {
    	if (mainPanel.getRows() != null)
    		mainPanel.getRows().appendChild(row);
    	else
    		mainPanel.newRows().appendChild(row);
    }
    
    private void initLocation()
    {
    	if (mainPanel.getRows() != null)
    		mainPanel.getRows().getChildren().clear();
    	
        MCountry country = m_location.getCountry();
        log.fine(country.getName() + ", Region=" + country.isHasRegion() + " " + country.getDisplaySequence()
            + ", C_Location_ID=" + m_location.getC_Location_ID());
        //  new Region
        if (m_location.getC_Country_ID() != s_oldCountry_ID && country.isHasRegion())
        {
        	lstRegion.getChildren().clear();
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
        addComponents((Row)txtAddress1.getParent());
        addComponents((Row)txtAddress2.getParent());
        addComponents((Row)txtAddress3.getParent());
        addComponents((Row)txtAddress4.getParent());
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
                addComponents((Row)txtCity.getParent());
            else if (s.startsWith("P"))
                addComponents((Row)txtPostal.getParent());
            else if (s.startsWith("A"))
                addComponents((Row)txtPostalAdd.getParent());
            else if (s.startsWith("R") && m_location.getCountry().isHasRegion())
                addComponents((Row)lstRegion.getParent());
        }
        //  Country Last
        addComponents((Row)lstCountry.getParent());

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
            	if (m_location.getCountry().getRegionName() != null
            		&& m_location.getCountry().getRegionName().trim().length() > 0)
            		lblRegion.setValue(m_location.getCountry().getRegionName());
            	else
            		lblRegion.setValue(Msg.getMsg(Env.getCtx(), "Region"));
            	
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
    	if (m_location.getRegion() != null) 
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
    	else
    	{
    		lstRegion.setSelectedItem(null);
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
