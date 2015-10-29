/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
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

package org.adempiere.pos.search;

import java.awt.Frame;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.adempiere.pos.grid.VPOSBPartner;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MBPartnerInfo;
import org.compiere.model.PO;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	POS Query BPartner
 *	
 *  @author Comunidad de Desarrollo OpenXpertya 
 *         *Basado en Codigo Original Modificado, Revisado y Optimizado de:
 *         *Copyright (c) Jorg Janke
 *  @author Dixon Martinez, ERPCYA 
 *  @author Susanne Calderón Schöningh, Systemhaus Westfalia
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  
 *  @version $Id: QueryBPartner.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 *  @version $Id: QueryBPartner.java,v 2.0 2015/09/01 00:00:00 scalderon
 */
public class QueryBPartner extends POSQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7109518709654253628L;
	
	/**
	 * 	Constructor
	 */
	public QueryBPartner (VPOS posPanel) {
		super(posPanel);
	}	//	PosQueryBPartner
	
	/**	Search Fields		*/
	private POSTextField	f_Value;
	private POSTextField	f_Name;
	private POSTextField	f_Contact;
	private POSTextField	f_Email;
	private POSTextField	f_Phone;
	private POSTextField	f_City;
	/**	Internal Variables	*/
	private int				m_C_BPartner_ID;
	private String 			m_BPartnerName;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(QueryBPartner.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_BPartner_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Email"), "Email", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "Phone", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Postal"), "Postal", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "City"), "City", String.class) 
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "RV_BPartner";
	/** Where Clause						*/
	private static String s_sqlWhere = "IsActive='Y'"; 

	/**
	 * 	Set up Panel
	 */
	protected void init() {
		//	North
		v_ParameterPanel.setLayout(new MigLayout("fill","", "[50][50][]"));
		v_ParameterPanel.setBorder(new TitledBorder(Msg.getMsg(m_ctx, "Query")));
		
		CLabel lvalue = new CLabel(Msg.translate(m_ctx, "Value"));
		v_ParameterPanel.add (lvalue, " growy");
		f_Value = new POSTextField("", v_POSPanel.getKeyboard());
		lvalue.setLabelFor(f_Value);
		v_ParameterPanel.add(f_Value, "h 30, w 200");
		f_Value.addActionListener(this);
		
		//
		CLabel lcontact = new CLabel(Msg.translate(m_ctx, "Contact"));
		v_ParameterPanel.add (lcontact, " growy");
		f_Contact = new POSTextField("", v_POSPanel.getKeyboard());
		lcontact.setLabelFor(f_Contact);
		v_ParameterPanel.add(f_Contact, "h 30, w 200");
		f_Contact.addActionListener(this);
		
		//
		CLabel lphone = new CLabel(Msg.translate(m_ctx, "Phone"));
		v_ParameterPanel.add (lphone, " growy");
		f_Phone = new POSTextField("", v_POSPanel.getKeyboard());
		lphone.setLabelFor(f_Phone);
		v_ParameterPanel.add(f_Phone, "h 30, w 200, wrap");
		f_Phone.addActionListener(this);
		
		//
		CLabel lname = new CLabel(Msg.translate(m_ctx, "Name"));
		v_ParameterPanel.add (lname, " growy");
		f_Name = new POSTextField("", v_POSPanel.getKeyboard());
		lname.setLabelFor(f_Name);
		v_ParameterPanel.add(f_Name, "h 30, w 200");
		f_Name.addActionListener(this);
		//
		CLabel lemail = new CLabel(Msg.translate(m_ctx, "Email"));
		v_ParameterPanel.add (lemail, " growy");
		f_Email = new POSTextField("", v_POSPanel.getKeyboard());
		lemail.setLabelFor(f_Email);
		v_ParameterPanel.add(f_Email, "h 30, w 200");
		f_Email.addActionListener(this);
		//
		CLabel lcity = new CLabel(Msg.translate(m_ctx, "City"));
		v_ParameterPanel.add (lcity, " growy");
		f_City = new POSTextField("", v_POSPanel.getKeyboard());
		lcity.setLabelFor(f_City);
		v_ParameterPanel.add(f_City, "h 30, w 200");
		f_City.addActionListener(this);
		
		//	Center
		m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_BPartner");
		//	
		m_table.growScrollbars();
		f_Value.requestFocus();
		addNewAction();
	}	//	init
	
	
	@Override
	protected void newAction() {
		super.newAction();
		VPOSBPartner t = new VPOSBPartner(new Frame(), 0, v_POSPanel);
		t.setVisible(true);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
	}
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MBPartnerInfo[] results) {
		m_table.loadTable(results);
		int rowCount = m_table.getRowCount();
		if (rowCount > 0) {
			m_table.setRowSelectionInterval(0, 0);
			if(rowCount == 1) {
				select();
			}
		}
	}	//	setResults
	
	@Override
	public void setResults(PO[] results) {
		//	Valid Result
		if(results == null
				|| !(results instanceof MBPartnerInfo[]))
			return;
		//	
		setResultsFromArray((MBPartnerInfo[]) results);
	}

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		cleanValues();
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled) {
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null) {
				m_C_BPartner_ID = ID.intValue();
				m_BPartnerName = (String)m_table.getValueAt(row, 2);
			}
		}
		log.fine("C_BPartner_ID=" + m_C_BPartner_ID); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		select();
		dispose();
	}	//	close
	
	@Override
	public void reset() {
		f_Value.setText(null);
		f_Name.setText(null);
		f_Contact.setText(null);
		f_Email.setText(null);
		f_Phone.setText(null);
		f_City.setText(null);
		setResults(new MBPartnerInfo[0]);
		cleanValues();
	}

	@Override
	public void refresh() {
		cleanValues();
		setResults(MBPartnerInfo.find (m_ctx,
				f_Value.getText(), f_Name.getText(), 
				null, f_Email.getText(),
				f_Phone.getText(), f_City.getText()));
	}
	
	/**
	 * Clean Values
	 * @return void
	 */
	private void cleanValues() {
		m_C_BPartner_ID = -1;
		m_BPartnerName = null;
	}

	@Override
	protected void cancel() {
		cleanValues();
		dispose();
	}

	@Override
	public int getRecord_ID() {
		return m_C_BPartner_ID;
	}

	@Override
	public String getValue() {
		return m_BPartnerName;
	}
}	//	PosQueryBPartner