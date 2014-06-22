/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.adempiere.webui.theme.ThemeUtils;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Timebox;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class DatetimeBox extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1075410511739601354L;
	private Datebox dateBox;
	private Timebox timeBox;
	
	public DatetimeBox() {
		dateBox = new Datebox();
		timeBox = new Timebox();
		appendChild(dateBox);
		appendChild(timeBox);	
		
		initComponents();
	}

	private void initComponents() {
		ThemeUtils.addSclass("ad-datetimebox", this);

		//dateBox.setStyle("display: inline;");
		//timeBox.setStyle("display: inline;");
		timeBox.setButtonVisible(false);
		timeBox.setZclass(dateBox.getZclass());		
	}

	/**
	 * @param date
	 */
	public void setValue(Date date) {
		dateBox.setValue(date);
		timeBox.setValue(date);
	}

	/**
	 * @param text
	 */
	public void setText(String text) {
		if (text != null && text.trim().length() > 0) 
		{
			String[] s = text.split("\\s");
			
			dateBox.setText(s[0]);
			timeBox.setText(s[1]);
		}
		else
		{
			dateBox.setValue(null);
			timeBox.setValue(null);
		}		
	}
	
	/**
	 * @return String
	 */
	public String getText() {
		return dateBox.getText() + " " + timeBox.getText();
	}
	
	public boolean addEventListener(String evtnm, EventListener<? extends Event> listener) {
		return dateBox.addEventListener(evtnm, listener) && timeBox.addEventListener(evtnm, listener);
	}

	/**
	 * @return boolean
	 */
	public boolean isEnabled() {
		return !dateBox.isReadonly();
	}

	/**
	 * @param readWrite
	 */
	public void setEnabled(boolean readWrite) {
		dateBox.setReadonly(!readWrite);
		timeBox.setReadonly(!readWrite);
		dateBox.setButtonVisible(readWrite);
	}

	/**
	 * @return date
	 */
	public Date getValue() {
		Date d = dateBox.getValue();
		Date t = timeBox.getValue();
		
		if (d != null && t != null) {
			Calendar cd = Calendar.getInstance();
            cd.setTime(d);
            Calendar ct = Calendar.getInstance();
            ct.setTime(t);
            cd.set(cd.get(Calendar.YEAR), cd.get(Calendar.MONTH), cd.get(Calendar.DAY_OF_MONTH),
                       ct.get(Calendar.HOUR_OF_DAY), ct.get(Calendar.MINUTE), ct.get(Calendar.SECOND));
            d = cd.getTime();
		}
		
		return d;
	}

	/**
	 * 
	 * @param dateFormat
	 */
	public void setDateFormat(SimpleDateFormat dateFormat) {
		dateBox.setFormat(dateFormat.toPattern());
	}
	
	/**
	 * @return dateBox
	 */
	public Datebox getDatebox()
	{
		return dateBox;
	}
	
	/**
	 * @return timeBox
	 */
	public Timebox getTimebox()
	{
		return timeBox;
	}
}
