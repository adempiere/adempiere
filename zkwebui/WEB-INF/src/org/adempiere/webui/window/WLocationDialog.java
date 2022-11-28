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

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MOrgInfo;
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
 * @author Cristina Ghita, www.arhipac.ro
 * 			<li>FR [ 2794312 ] Location AutoComplete
 * @author Teo Sarca, teo.sarca@gmail.com
 * 			<li>BF [ 2995212 ] NPE on Location dialog
 * 				https://sourceforge.net/tracker/?func=detail&aid=2995212&group_id=176962&atid=955896
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 			<li>BF [ 3294610] The location should allow open a google map
 * 				https://sourceforge.net/tracker/?func=detail&atid=879335&aid=3294610&group_id=176962
 * 		<a href="https://github.com/adempiere/adempiere/issues/886">
 * 		@see FR [ 886 ] System config Google Map</a>
 *
 * @TODO: Implement fOnline button present in swing client
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/685">
 * 		@see FR [ 685 ] Location dialog for ZK don't have a Standard ADempiere Buttons Position</a>
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1150">
 * 		@see FR [ 1150 ] The url location based on google map not work when the location is empty or with data</a>
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1158">
 * 		@see FR [ 1158 ] Problems with location address: wrong region and not showing region field</a>
 *
 * @contributors - Carlos Ruiz / globalqss
 * 				 - Show GoogleMap on Location Dialog (integrate approach from LBR)	
 * 				 - http://jira.idempiere.com/browse/IDEMPIERE-147
 **/

public class WLocationDialog extends Window implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8511642461845783366L;
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

	private Label lblLatitude;

	private Label lblLongitude;

	private Label lblAltitude;

	private Textbox txtAddress1;
	private Textbox txtAddress2;
	private Textbox txtAddress3;
	private Textbox txtAddress4;
	private WAutoCompleterCity txtCity;
	private Textbox txtPostal;
	private Textbox txtPostalAdd;

	private Listbox lstRegion;
	private Listbox lstCountry;

	private NumberBox fieldLatitude;

	private NumberBox fieldLongitude;

	private NumberBox fieldAltitude;

	private Button btnOk;
	private Button btnCancel;
	private Grid mainPanel;

	private boolean change = false;
	private MLocation location;
	private int countryId = 0;
	private int oldCountryId = 0;

	private int windowNo = 0;

	private boolean isCityMandatory = false;
	private boolean isRegionMandatory = false;
	private boolean isAddress1Mandatory = false;
	private boolean isAddress2Mandatory = false;
	private boolean isAddress3Mandatory = false;
	private boolean isAddress4Mandatory = false;
	private boolean isPostalMandatory = false;
	private boolean isPostalAddMandatory = false;

	private boolean inCountryAction;
	private boolean inOKAction;

	/** The "route" key  */
	private static final String TO_ROUTE = Msg.getMsg(Env.getCtx(), "Route");
	/** The "to link" key  */
	private static final String TO_LINK = Msg.getMsg(Env.getCtx(), "Map");

	private Button toLink;
	private Button toRoute;
	//END

	public WLocationDialog(String title, MLocation location)
	{
		this.location = location;
		if (this.location == null)
			this.location = new MLocation (Env.getCtx(), 0, null);
		//  Overwrite title 
		if (this.location.getC_Location_ID() == 0)
			setTitle(Msg.getMsg(Env.getCtx(), "LocationNew"));
		else
			setTitle(Msg.getMsg(Env.getCtx(), "LocationUpdate"));    
		//
		// Reset TAB_INFO context
		Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "C_Region_ID", null);
		Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "C_Country_ID", null);
		//
		initComponents();
		init();
		//      Current Country
		for (MCountry country:MCountry.getCountries(Env.getCtx()))
		{
			lstCountry.appendItem(country.toString(), country);
		}
		setCountry();
		lstCountry.addEventListener(Events.ON_SELECT,this);
		lstRegion.addEventListener(Events.ON_SELECT,this);
		countryId = this.location.getC_Country_ID();
		//  Current Region
		lstRegion.appendItem("", null);
		for (MRegion region : MRegion.getRegions(Env.getCtx(), countryId))
		{
			lstRegion.appendItem(region.getName(),region);
		}
		if (this.location.getCountry().isHasRegion()) {
			if (this.location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName) != null
					&& this.location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName).trim().length() > 0)
				lblRegion.setValue(this.location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName));
			else
				lblRegion.setValue(Msg.getMsg(Env.getCtx(), "Region"));
		}

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

		lblLatitude = new Label(Msg.getMsg(Env.getCtx(), "Latitude"));
		lblLatitude.setStyle(LABEL_STYLE);

		lblLongitude = new Label(Msg.getMsg(Env.getCtx(), "Longitude"));
		lblLongitude.setStyle(LABEL_STYLE);

		lblAltitude = new Label(Msg.getMsg(Env.getCtx(), "Altitude"));
		lblAltitude.setStyle(LABEL_STYLE);

		txtAddress1 = new Textbox();
		txtAddress1.setCols(20);
		txtAddress2 = new Textbox();
		txtAddress2.setCols(20);
		txtAddress3 = new Textbox();
		txtAddress3.setCols(20);
		txtAddress4 = new Textbox();
		txtAddress4.setCols(20);

		//autocomplete City
		txtCity = new WAutoCompleterCity(windowNo);
		txtCity.setCols(20);
		txtCity.setAutodrop(true);
		txtCity.setAutocomplete(true);
		txtCity.addEventListener(Events.ON_CHANGING, this);
		//txtCity

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

		fieldLatitude = new NumberBox(false);
		fieldLatitude.setValue(BigDecimal.ZERO);

		fieldLongitude = new NumberBox(false);
		fieldLongitude.setValue(BigDecimal.ZERO);

		fieldAltitude = new NumberBox(false);
		fieldAltitude.setValue(BigDecimal.ZERO);


		btnOk = new Button();
		btnOk.setImage("/images/Ok16.png");
		btnOk.addEventListener(Events.ON_CLICK,this);
		btnCancel = new Button();
		btnCancel.setImage("/images/Cancel16.png");
		btnCancel.addEventListener(Events.ON_CLICK,this);

		toLink = new Button(TO_LINK);
		toLink.setImage("/images/Online10.png");
		toLink.addEventListener(Events.ON_CLICK,this);

		toRoute = new Button(TO_ROUTE);
		toRoute.setImage("/images/Route10.png");
		toRoute.addEventListener(Events.ON_CLICK,this);

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

		Row pnlLatitude  = new Row();
		pnlLatitude.appendChild(lblLatitude.rightAlign());
		pnlLatitude.appendChild(fieldLatitude);

		Row pnlLongitude  = new Row();
		pnlLongitude.appendChild(lblLongitude.rightAlign());
		pnlLongitude.appendChild(fieldLongitude);

		Row pnlAltitude  = new Row();
		pnlAltitude.appendChild(lblAltitude.rightAlign());
		pnlAltitude.appendChild(fieldAltitude);

		Panel pnlLinks    = new Panel();
		pnlLinks.appendChild(toLink);
		if (MLocation.LOCATION_MAPS_URL_PREFIX == null)
			toLink.setVisible(false);
		pnlLinks.appendChild(toRoute);
		if (MLocation.LOCATION_MAPS_ROUTE_PREFIX == null)
			toRoute.setVisible(false);
		pnlLinks.setWidth("100%");
		pnlLinks.setStyle("text-align:left");

		Panel pnlButton   = new Panel();
		pnlButton.appendChild(btnOk);
		pnlButton.appendChild(btnCancel);
		pnlButton.setWidth("100%");
		pnlButton.setStyle("text-align:right");

		this.appendChild(mainPanel);
		if (MLocation.LOCATION_MAPS_URL_PREFIX != null || MLocation.LOCATION_MAPS_ROUTE_PREFIX != null)
			this.appendChild(pnlLinks);
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

		MCountry country = location.getCountry();
		log.fine(country.getName() + ", Region=" + country.isHasRegion() + " " + country.getCaptureSequence()
				+ ", C_Location_ID=" + location.getC_Location_ID());
		//  new Country
		if (location.getC_Country_ID() != oldCountryId)
		{
			lstRegion.getChildren().clear();
			if (country.isHasRegion()) {
				lstRegion.appendItem("", null);
				for (MRegion region : MRegion.getRegions(Env.getCtx(), country.getC_Country_ID()))
				{
					lstRegion.appendItem(region.getName(),region);
				}
				if (location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName) != null
						&& location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName).trim().length() > 0)
					lblRegion.setValue(location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName));
				else
					lblRegion.setValue(Msg.getMsg(Env.getCtx(), "Region"));
			}
			oldCountryId = location.getC_Country_ID();
		}
		
		if (location.getC_Region_ID() > 0 && location.getC_Region().getC_Country_ID() == country.getC_Country_ID()) {
			setRegion();
		} else {
			lstRegion.setSelectedItem(null);
			location.setC_Region_ID(0);
		}

		if (country.isHasRegion() && location.getC_Region_ID() > 0)
		{
			Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "C_Region_ID", String.valueOf(location.getC_Region_ID()));
		} else {
			Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "C_Region_ID", "0");
		}
		Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "C_Country_ID", String.valueOf(country.get_ID()));
		
		txtCity.fillList();
		
		//      sequence of City Postal Region - @P@ @C@ - @C@, @R@ @P@
		String ds = country.getCaptureSequence();
		if (ds == null || ds.length() == 0)
		{
			log.log(Level.SEVERE, "CaptureSequence empty - " + country);
			ds = "";    //  @C@,  @P@
		}
		isCityMandatory = false;
		isRegionMandatory = false;
		isAddress1Mandatory = false;
		isAddress2Mandatory = false;
		isAddress3Mandatory = false;
		isAddress4Mandatory = false;
		isPostalMandatory = false;
		isPostalAddMandatory = false;
		StringTokenizer st = new StringTokenizer(ds, "@", false);
		while (st.hasMoreTokens())
		{
			String s = st.nextToken();
			if (s.startsWith("CO")) {
				//  Country Last
				addComponents((Row)lstCountry.getParent());
				// TODO: Add Online
				// if (m_location.getCountry().isPostcodeLookup()) {
					// addLine(line++, lOnline, fOnline);
				// }
			} else if (s.startsWith("A1")) {
				addComponents((Row)txtAddress1.getParent());
				isAddress1Mandatory = s.endsWith("!");
			} else if (s.startsWith("A2")) {
				addComponents((Row)txtAddress2.getParent());
				isAddress2Mandatory = s.endsWith("!");
			} else if (s.startsWith("A3")) {
				addComponents((Row)txtAddress3.getParent());
				isAddress3Mandatory = s.endsWith("!");
			} else if (s.startsWith("A4")) {
				addComponents((Row)txtAddress4.getParent());
				isAddress4Mandatory = s.endsWith("!");
			} else if (s.startsWith("C")) {
				addComponents((Row)txtCity.getParent());
				isCityMandatory = s.endsWith("!");
			} else if (s.startsWith("P")) {
				addComponents((Row)txtPostal.getParent());
				isPostalMandatory = s.endsWith("!");
			} else if (s.startsWith("A")) {
				addComponents((Row)txtPostalAdd.getParent());
				isPostalAddMandatory = s.endsWith("!");
			} else if (s.startsWith("R") && location.getCountry().isHasRegion()) {
				addComponents((Row)lstRegion.getParent());
				isRegionMandatory = s.endsWith("!");
			}
		}

		addComponents((Row)fieldLatitude.getParent());
		addComponents((Row)fieldLongitude.getParent());
		addComponents((Row)fieldAltitude.getParent());

		//      Fill it
		if (location.getC_Location_ID() != 0)
		{
			txtAddress1.setText(location.getAddress1());
			txtAddress2.setText(location.getAddress2());
			txtAddress3.setText(location.getAddress3());
			txtAddress4.setText(location.getAddress4());
			txtCity.setText(location.getCity());
			txtPostal.setText(location.getPostal());
			txtPostalAdd.setText(location.getPostal_Add());

			fieldLatitude.setValue(location.getLatitude());
			fieldLongitude.setValue(location.getLongitude());
			fieldAltitude.setValue(location.getAltitude());

			if (location.getCountry().isHasRegion())
			{
				if (location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName) != null
						&& location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName).trim().length() > 0)
					lblRegion.setValue(location.getCountry().get_Translation(MCountry.COLUMNNAME_RegionName));
				else
					lblRegion.setValue(Msg.getMsg(Env.getCtx(), "Region"));

				setRegion();                
			}
			setCountry();
		}
	}
	private void setCountry()
	{
		List<?> listCountry = lstCountry.getChildren();
		Iterator<?> iter = listCountry.iterator();
		while (iter.hasNext())
		{
			ListItem listitem = (ListItem)iter.next();
			if (location.getCountry().equals(listitem.getValue()))
			{
				lstCountry.setSelectedItem(listitem);
			}
		}
	}

	private void setRegion()
	{
		if (location.getRegion() != null)
		{
			List<?> listState = lstRegion.getChildren();
			Iterator<?> iter = listState.iterator();
			while (iter.hasNext())
			{
				ListItem listitem = (ListItem)iter.next();
				if (location.getRegion().equals(listitem.getValue()))
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
		return change;
	}   //  getChange
	/**
	 *  Get edited Value (MLocation)
	 *  @return location
	 */
	public MLocation getValue()
	{
		return location;
	}   

	public void onEvent(Event event) throws Exception
	{
		if (btnOk.equals(event.getTarget()))
		{
			inOKAction = true;
			
			if (location.getCountry().isHasRegion() && lstRegion.getSelectedItem() == null) {
				if (txtCity.getC_Region_ID() > 0 && txtCity.getC_Region_ID() != location.getC_Region_ID()) {
					location.setRegion(MRegion.get(Env.getCtx(), txtCity.getC_Region_ID()));
					setRegion();
				}
			}
			
			String msg = validate_OK();
			if (msg != null) {
				FDialog.error(0, this, "FillMandatory", Msg.parseTranslation(Env.getCtx(), msg));
				inOKAction = false;
				return;
			}
			
			if(action_OK())
			{
				change = true;
				inOKAction = false;
				this.dispose();
			}
			else
			{
				FDialog.error(0, this, "CityNotFound");
			}
			inOKAction = false;
		}
		else if (btnCancel.equals(event.getTarget()))
		{
			change = false;
			this.dispose();
		}
		else if (toLink.equals(event.getTarget()))
		{
			String urlString = MLocation.getMapUrl(location);
			String message = null;
			try {
				Env.startBrowser(urlString+"&output=embed");
			}
			catch (Exception e) {
				message = e.getMessage();
				FDialog.warn(0, this, "URLnotValid", message);
			}
		}
		else if (toRoute.equals(event.getTarget()))
		{
			int orgId = Env.getAD_Org_ID(Env.getCtx());
			if (orgId != 0){
				MOrgInfo orgInfo = 	MOrgInfo.get(Env.getCtx(), orgId,null);
				MLocation orgLocation = new MLocation(Env.getCtx(),orgInfo.getC_Location_ID(),null);
				String urlString =MLocation.getRouteUrl(orgLocation, location);
				String message = null;
				try {
					Env.startBrowser(urlString+"&output=embed");
				}
				catch (Exception e) {
					message = e.getMessage();
					FDialog.warn(0, this, "URLnotValid", message);
				}
			}
		}
		//  Country Changed - display in new Format
		else if (lstCountry.equals(event.getTarget()))
		{
			inCountryAction = true;
			MCountry c = (MCountry)lstCountry.getSelectedItem().getValue();
			location.setCountry(c);
			location.setC_City_ID(0);
			location.setCity(null);
			//  refresh
			initLocation();
			inCountryAction = false;
		}
		//  Region Changed 
		else if (lstRegion.equals(event.getTarget()))
		{
			if (inCountryAction || inOKAction)
				return;
			MRegion r = (MRegion)lstRegion.getSelectedItem().getValue();
			location.setRegion(r);
			location.setC_City_ID(0);
			location.setCity(null);
			//  refresh
			initLocation();
		}
	}

	
	// LCO - address 1, region and city required
	private String validate_OK() {
		String fields = "";
		if (isAddress1Mandatory && txtAddress1.getText().trim().length() == 0) {
			fields = fields + " " + "@Address1@, ";
		}
		if (isAddress2Mandatory && txtAddress2.getText().trim().length() == 0) {
			fields = fields + " " + "@Address2@, ";
		}
		if (isAddress3Mandatory && txtAddress3.getText().trim().length() == 0) {
			fields = fields + " " + "@Address3@, ";
		}
		if (isAddress4Mandatory && txtAddress4.getText().trim().length() == 0) {
			fields = fields + " " + "@Address4@, ";
		}
		if (isCityMandatory && txtCity.getValue().trim().length() == 0) {
			fields = fields + " " + "@C_City_ID@, ";
		}
		if (isRegionMandatory && lstRegion.getSelectedItem() == null) {
			fields = fields + " " + "@C_Region_ID@, ";
		}
		if (isPostalMandatory && txtPostal.getText().trim().length() == 0) {
			fields = fields + " " + "@Postal@, ";
		}
		if (isPostalAddMandatory && txtPostalAdd.getText().trim().length() == 0) {
			fields = fields + " " + "@PostalAdd@, ";
		}
		
		if (fields.trim().length() > 0)
			return fields.substring(0, fields.length() -2);

		return null;
	}

	/**
	 *  OK - check for changes (save them) & Exit
	 */
	private boolean action_OK()
	{
		location.setAddress1(txtAddress1.getValue());
		location.setAddress2(txtAddress2.getValue());
		location.setAddress3(txtAddress3.getValue());
		location.setAddress4(txtAddress4.getValue());
		location.setC_City_ID(txtCity.getC_City_ID());
		location.setCity(txtCity.getValue());
		location.setPostal(txtPostal.getValue());

		Optional.ofNullable(fieldLatitude.getValue())
				.ifPresent(latitude -> location.setLatitude( latitude));

		Optional.ofNullable(fieldLongitude.getValue())
				.ifPresent(longitude -> location.setLongitude( longitude));

		Optional.ofNullable(fieldAltitude.getValue())
				.ifPresent(altitude -> location.setAltitude( altitude));

		//  Country/Region
		MCountry country = (MCountry)lstCountry.getSelectedItem().getValue();
		location.setCountry(country);
		if (country.isHasRegion() && lstRegion.getSelectedItem() != null)
		{
			MRegion r = (MRegion)lstRegion.getSelectedItem().getValue();
			location.setRegion(r);
		}
		else
		{
			location.setC_Region_ID(0);
		}
		//  Save chnages
		if(location.save())
		{
			return true;
		}
		else
		{
			return false;
		}
	}   //  actionOK

	@Override
	public void dispose()
	{
		if (!change && location != null && !location.is_new())
		{
			location = new MLocation(location.getCtx(), location.get_ID(), null);
		}	
		super.dispose();
	}

}
