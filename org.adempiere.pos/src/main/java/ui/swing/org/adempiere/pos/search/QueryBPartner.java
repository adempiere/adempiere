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
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.adempiere.pos.service.I_POSQuery;
import org.compiere.grid.ed.VBPartner;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MBPartnerInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CTextField;
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
public class QueryBPartner extends POSQuery implements I_POSQuery {
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
	private POSTextField	f_value;
	private POSTextField	f_name;
	private POSTextField	f_contact;
	private POSTextField	f_email;
	private POSTextField	f_phone;
	private CTextField		f_city;
	/**	Internal Variables	*/
	private int				m_C_BPartner_ID;
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
		f_value = new POSTextField("", v_POSPanel.getKeyboard());
		lvalue.setLabelFor(f_value);
		v_ParameterPanel.add(f_value, "h 30, w 200");
		f_value.addActionListener(this);
		
		//
		CLabel lcontact = new CLabel(Msg.translate(m_ctx, "Contact"));
		v_ParameterPanel.add (lcontact, " growy");
		f_contact = new POSTextField("", v_POSPanel.getKeyboard());
		lcontact.setLabelFor(f_contact);
		v_ParameterPanel.add(f_contact, "h 30, w 200");
		f_contact.addActionListener(this);
		
		//
		CLabel lphone = new CLabel(Msg.translate(m_ctx, "Phone"));
		v_ParameterPanel.add (lphone, " growy");
		f_phone = new POSTextField("", v_POSPanel.getKeyboard());
		lphone.setLabelFor(f_phone);
		v_ParameterPanel.add(f_phone, "h 30, w 200, wrap");
		f_phone.addActionListener(this);
		
		//
		CLabel lname = new CLabel(Msg.translate(m_ctx, "Name"));
		v_ParameterPanel.add (lname, " growy");
		f_name = new POSTextField("", v_POSPanel.getKeyboard());
		lname.setLabelFor(f_name);
		v_ParameterPanel.add(f_name, "h 30, w 200");
		f_name.addActionListener(this);
		//
		CLabel lemail = new CLabel(Msg.translate(m_ctx, "Email"));
		v_ParameterPanel.add (lemail, " growy");
		f_email = new POSTextField("", v_POSPanel.getKeyboard());
		lemail.setLabelFor(f_email);
		v_ParameterPanel.add(f_email, "h 30, w 200");
		f_email.addActionListener(this);
		//
		CLabel lcity = new CLabel(Msg.translate(m_ctx, "City"));
		v_ParameterPanel.add (lcity, " growy");
		f_city = new CTextField(10);
		lcity.setLabelFor(f_city);
		v_ParameterPanel.add(f_city, "h 30, w 200");
		f_city.addActionListener(this);
		
		//	Center
		m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_BPartner");
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		select();
		m_table.growScrollbars();
		f_value.requestFocus();
		addNewAction();
	}	//	init
	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource() == f_value
			|| e.getSource() == f_name
			) {
			refresh();
			return;
		}
	}	//	actionPerformed
	
	
	@Override
	protected void newAction() {
		super.newAction();
		VBPartner t = new VBPartner(new Frame(), 0);
		t.setVisible(true);
		m_C_BPartner_ID = t.getC_BPartner_ID();
		//	Close
		close();
	}
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (MBPartnerInfo[] results) {
		m_table.loadTable(results);
		if (m_table.getRowCount() >0 )
			m_table.setRowSelectionInterval(0, 0);
		select();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		m_C_BPartner_ID = -1;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled) {
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null) {
				m_C_BPartner_ID = ID.intValue();
			//	m_BPartnerName = (String)m_table.getValueAt(row, 2);
			//	m_Price = (BigDecimal)m_table.getValueAt(row, 7);
			}
		}
		log.fine("C_BPartner_ID=" + m_C_BPartner_ID); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_C_BPartner_ID = ID.intValue();
		dispose();
	}	//	close
	
	@Override
	public void reset() {
		f_value.setText(null);
		f_name.setText(null);
		f_contact.setText(null);
		f_email.setText(null);
		f_phone.setText(null);
		f_city.setText(null);
		setResults(new MBPartnerInfo[0]);
	}

	@Override
	public void refresh() {
		setResults(MBPartnerInfo.find (m_ctx,
				f_value.getText(), f_name.getText(), 
				null, f_email.getText(),
				f_phone.getText(), f_city.getText()));
	}

	@Override
	protected void cancel() {
		m_C_BPartner_ID = 0;
		dispose();
	}

	@Override
	public int getRecord_ID() {
		return m_C_BPartner_ID;
	}

	@Override
	public String getValue() {
		return null;
	}
}	//	PosQueryBPartner