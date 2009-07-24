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
package org.compiere.wstore;

import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.span;
import org.apache.taglibs.standard.tag.el.core.ExpressionUtil;
import org.compiere.model.MCountry;
import org.compiere.model.MLocation;
import org.compiere.model.MRegion;
import org.compiere.util.CLogger;
import org.compiere.util.HtmlCode;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *  Location City - Postal - Region - Country (Address).
 *  <pre>
 *	<cws:location country="${webUser.country}" regionID="${webUser.regionID}" regionName="${webUser.regionName}" city="${webUser.city}" postal="${webUser.postal}" />
 *	</pre>
 *
 *  @author Jorg Janke
 *  @version $Id: LocationTag.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class LocationTag extends TagSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1083458086024632709L;

	/**	Logging						*/
	private CLogger			log = CLogger.getCLogger(getClass());

	private String			m_countryID_el;
	private String			m_regionID_el;
	private String			m_regionName_el;
	private String			m_city_el;
	private String			m_postal_el;

	private MCountry		m_country;

	//  CSS Classes
	private static final String C_MANDATORY = "mandatory";
	private static final String C_ERROR     = "error";

	/**
	 * 	Set Country
	 *	@param info_el country info
	 */
	public void setCountryID (String info_el)
	{
		m_countryID_el = info_el;
	}	//	setCountry

	/**
	 * 	Set Region
	 *	@param info_el region info
	 */
	public void setRegionID (String info_el)
	{
		m_regionID_el = info_el;
	}	//	setRegion

	/**
	 * 	Set Region
	 *	@param info_el region info
	 */
	public void setRegionName (String info_el)
	{
		m_regionName_el = info_el;
	}	//	setRegion

	/**
	 * 	Set City
	 *	@param info_el city info
	 */
	public void setCity (String info_el)
	{
		m_city_el = info_el;
	}	//	setCity

	/**
	 * 	Set Postal
	 *	@param info_el postal info
	 */
	public void setPostal (String info_el)
	{
		m_postal_el = info_el;
	}	//	setPostal

	private int getCountryID(MLocation loc)
	{
		int C_Country_ID = 0;
		try
		{
			String info = (String)ExpressionUtil.evalNotNull ("location", "countryID",
				m_countryID_el, String.class, this, pageContext);
			if (info != null && info.length () != 0)
				C_Country_ID = Integer.parseInt (info);
		}
		catch (Exception e)
		{
			log.severe ("Country - " + e);
		}

		if (C_Country_ID == 0)
			C_Country_ID = loc.getC_Country_ID(); //	default

		return C_Country_ID;
	}
	private int getRegionID(MLocation loc)
	{
		int C_Region_ID = 0;
		try
		{
			String info = (String)ExpressionUtil.evalNotNull ("location", "regionID",
				m_regionID_el, String.class, this, pageContext);
			if (info != null && info.length () != 0)
				C_Region_ID = Integer.parseInt (info);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "RegionID - " + e);
		}
		if (C_Region_ID == 0)
			C_Region_ID = loc.getC_Region_ID(); //	default
		
		return C_Region_ID;
	}
	/**
	 *  Start Tag
	 *  @return SKIP_BODY
	 * 	@throws JspException
	 */
	public int doStartTag() throws JspException
	{
		Properties ctx = JSPEnv.getCtx((HttpServletRequest)pageContext.getRequest());
		MLocation loc = new MLocation (ctx, 0, null);
		HtmlCode html = new HtmlCode();
		
		int C_Country_ID = getCountryID(loc);
		int C_Region_ID = getRegionID(loc);		
		option[] countries = getCountries(loc, C_Country_ID);
//		if (m_country != null) m_country.DisplaySequence;
		
		String name = null;
		label lbl = null;
		input field = null;
		select sel = null;
		
		//	City	***********************************************************
		name = "City";
		String city = (String)ExpressionUtil.evalNotNull ("location", "city",
			m_city_el, String.class, this, pageContext);

		lbl = new label();
		lbl.setFor(name);
		lbl.setID("LBL_"+name);
		lbl.addElement(Msg.translate(ctx, name));
		html.addElement(lbl);

		field = new input (input.TYPE_TEXT, name, city);
		field.setSize(40).setMaxlength(60).setID("ID_" + name);
		field.setClass(C_MANDATORY);
		html.addElement(field);

		html.addElement(new br());
		
		//	Postal	***********************************************************
		name = "Postal";
		String postal = (String)ExpressionUtil.evalNotNull ("location", "postal",
			m_postal_el, String.class, this, pageContext);
		
		lbl = new label();
		lbl.setFor(name);
		lbl.setID("LBL_"+name);
		lbl.addElement(Msg.translate(ctx, name));
		html.addElement(lbl);

		field = new input (input.TYPE_TEXT, name, postal);
		field.setSize(10).setMaxlength(10).setID("ID_" + name);
		field.setClass(C_MANDATORY);
		html.addElement(field);

		html.addElement(new br());
		
		
		//	Region		*******************************************************
		name = "C_Region_ID";

		lbl = new label();
		lbl.setFor(name);
		lbl.setID("LBL_"+name);

		String regionName = (String)ExpressionUtil.evalNotNull ("location", "regionName",
			m_regionName_el, String.class, this, pageContext);
		field = new input (input.TYPE_TEXT, "RegionName", regionName);
		field.setSize(40).setMaxlength(60).setID("ID_RegionName");
		if (m_country != null && m_country.isHasRegion())
		{
			sel = new select (name, getRegions (loc, C_Country_ID, C_Region_ID));
			sel.setID("ID_" + name);
			lbl.addElement(m_country.getRegionName());
			html.addElement(lbl);
			html.addElement(sel);
			html.addElement(new span(" - "));
			html.addElement(field);
		}
		else{
			lbl.addElement(Msg.translate(ctx, name));
			html.addElement(lbl);
			html.addElement(field);
		}

		html.addElement(new br());

		//	Country		*******************************************************
		name = "C_Country_ID";

		lbl = new label();
		lbl.setFor(name);
		lbl.setID("LBL_"+name);
		lbl.addElement(Msg.translate(ctx, name));
		html.addElement(lbl);

		sel = new select (name, countries);
		sel.setID("ID_" + name);
		sel.setClass(C_MANDATORY);
		sel.setOnChange("changeCountry('ID_"+name+"');");
		html.addElement(sel);

		html.addElement(new br());


		
		log.fine("C_Country_ID=" + C_Country_ID + ", C_Region_ID=" + C_Region_ID
			+ ", RegionName=" + regionName + ", City=" + city + ", Postal=" + postal);

		JspWriter out = pageContext.getOut();
		html.output(out);
		//
		return (SKIP_BODY);
	}   //  doStartTag

	/**
	 * 	End Tag - NOP
	 * 	@return EVAL_PAGE
	 * 	@throws JspException
	 */
	public int doEndTag() throws JspException
	{
		return EVAL_PAGE;
	}	//	doEndTag

	/**
	 * 	Get Country Options.
	 * 	Add Regions for selected country
	 * 	Set Default
	 *	@param loc MLocation
	 * 	@param C_Country_ID default country
	 *	@return array of country options
	 */
	private option[] getCountries (MLocation loc, int C_Country_ID)
	{
		MCountry[] countries = MCountry.getCountries(loc.getCtx());
		option[] options = new option[countries.length];
		m_country = null;
		//
		for (int i = 0; i < countries.length; i++)
		{
			options[i] = new option (String.valueOf(countries[i].getC_Country_ID()));
			options[i].addElement(Util.maskHTML(countries[i].getName()));
			if (countries[i].getC_Country_ID() == C_Country_ID)
			{
				m_country = countries[i];
				options[i].setSelected (true);
			}
		}
		//
		return options;
	}	//	getCountries

	/**
	 *	Get Region Options for Country
	 * 	@param loc location
	 * 	@param C_Country_ID country
	 * 	@param C_Region_ID default region
	 * 	@return region array
	 */
	private option[] getRegions (MLocation loc, int C_Country_ID, int C_Region_ID)
	{
		MRegion[] regions = MRegion.getRegions(loc.getCtx(), C_Country_ID);
		option[] options = new option[regions.length+1];
		//
		options[0] = new option ("0");
		options[0].addElement(" ");
		//
		for (int i = 0; i < regions.length; i++)
		{
			options[i+1] = new option (String.valueOf(regions[i].getC_Region_ID()));
			options[i+1].addElement(regions[i].getName());
			if (regions[i].getC_Region_ID() == C_Region_ID)
				options[i+1].setSelected(true);
		}
		return options;
	}	//	getRegions


}	//	LocationTag
