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

import java.awt.Cursor;
import java.awt.Frame;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingUtilities;
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
import org.compiere.util.DB;
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
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
	private POSTextField fieldValue;
	private POSTextField fieldTaxID;
	private POSTextField fieldName;
	private POSTextField fieldName2;
	private POSTextField fieldContact;
	private POSTextField fieldEmail;
	private POSTextField fieldPhone;
	private POSTextField fieldCity;
	/**	Internal Variables	*/
	private int 		 partnerId;
	private String 		 partnerName;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(QueryBPartner.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_BPartner_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "TaxID"), "TaxID", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name2"), "Name2", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Email"), "Email", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "Phone", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Postal"), "Postal", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "City"), "City", String.class) 
	};
	/**	From Clause							*/
	private static String sqlFrom = "RV_BPartner";
	/** Where Clause						*/
	private static String sqlWhere = "IsActive='Y'";

	/**
	 * 	Set up Panel
	 */
	protected void init() {
		setTitle(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		//	North
		parameterPanel.setLayout(new MigLayout("fill","", "[50][50][]"));
		parameterPanel.setBorder(new TitledBorder(Msg.getMsg(ctx, "Query")));
		
		CLabel labelValue = new CLabel(Msg.translate(ctx, "Value"));
		parameterPanel.add (labelValue, " growy");
		fieldValue = new POSTextField("", posPanel.getKeyboard());
		labelValue.setLabelFor(fieldValue);
		parameterPanel.add(fieldValue, "h 30, w 200");
		fieldValue.addActionListener(this);

		CLabel labelTaxID = new CLabel(Msg.translate(ctx, "TaxID"));
		parameterPanel.add (labelTaxID, " growy");
		fieldTaxID = new POSTextField("", posPanel.getKeyboard());
		labelTaxID.setLabelFor(fieldTaxID);
		parameterPanel.add(fieldTaxID, "h 30, w 200");
		fieldTaxID.addActionListener(this);
		
		//
		CLabel labelContact = new CLabel(Msg.translate(ctx, "Contact"));
		parameterPanel.add (labelContact, " growy");
		fieldContact = new POSTextField("", posPanel.getKeyboard());
		labelContact.setLabelFor(fieldContact);
		parameterPanel.add(fieldContact, "h 30, w 200");
		fieldContact.addActionListener(this);
		
		//
		CLabel labelPhone = new CLabel(Msg.translate(ctx, "Phone"));
		parameterPanel.add (labelPhone, " growy");
		fieldPhone = new POSTextField("", posPanel.getKeyboard());
		labelPhone.setLabelFor(fieldPhone);
		parameterPanel.add(fieldPhone, "h 30, w 200, wrap");
		fieldPhone.addActionListener(this);
		
		//
		CLabel labelName = new CLabel(Msg.translate(ctx, "Name"));
		parameterPanel.add (labelName, " growy");
		fieldName = new POSTextField("", posPanel.getKeyboard());
		labelName.setLabelFor(fieldName);
		parameterPanel.add(fieldName, "h 30, w 200");
		fieldName.addActionListener(this);

		CLabel labelName2 = new CLabel(Msg.translate(ctx, "Name2"));
		parameterPanel.add (labelName2, " growy");
		fieldName2 = new POSTextField("", posPanel.getKeyboard());
		labelName2.setLabelFor(fieldName2);
		parameterPanel.add(fieldName2, "h 30, w 200");
		fieldName2.addActionListener(this);

		//
		CLabel labelEmail = new CLabel(Msg.translate(ctx, "Email"));
		parameterPanel.add (labelEmail, " growy");
		fieldEmail = new POSTextField("", posPanel.getKeyboard());
		labelEmail.setLabelFor(fieldEmail);
		parameterPanel.add(fieldEmail, "h 30, w 200");
		fieldEmail.addActionListener(this);
		//
		CLabel labelCity = new CLabel(Msg.translate(ctx, "City"));
		parameterPanel.add (labelCity, " growy");
		fieldCity = new POSTextField("", posPanel.getKeyboard());
		labelCity.setLabelFor(fieldCity);
		parameterPanel.add(fieldCity, "h 30, w 200");
		fieldCity.addActionListener(this);
		
		//	Center
		posTable.prepareTable (s_layout, sqlFrom,
				sqlWhere, false, "RV_BPartner");
		//	
		posTable.growScrollbars();
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
				fieldValue.requestFocus();
			}
		});

		addNewAction();
	}	//	init
	
	
	@Override
	protected void newAction() {
		super.newAction();
		VPOSBPartner posPartner = new VPOSBPartner(new Frame(), 0, posPanel);
		posPartner.setVisible(true);
		partnerId = posPartner.getC_BPartner_ID();
		if(partnerId > 0) {
			setOkAction();
		}
	}
	
	@Override
	public void editAction() {
		super.editAction();
		VPOSBPartner posPartner = new VPOSBPartner(new Frame(), 1, posPanel);
		select();
		posPartner.loadBPartner(partnerId);
		posPartner.setVisible(true);
		//	Close
		close();
	}
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MBPartnerInfo[] results) {
		posTable.loadTable(results);
		int rowCount = posTable.getRowCount();
		if (rowCount > 0) {
			posTable.setRowSelectionInterval(0, 0);
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
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setResultsFromArray((MBPartnerInfo[]) results);
		this.setCursor(Cursor.getDefaultCursor());
	}
	
	/**
	 * Load Data BPartner
	 * 
	 * @return void
	 */
	public void loadData() {
		StringBuffer sql = new StringBuffer();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try  {

			this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			sql.append(" SELECT b.C_BPartner_ID, b.Value, b.TaxID , b.Name , b.Name2 , u.Email, u.Phone, l.Postal, lb.name AS City")
				.append(" FROM C_BPartner AS b")
				.append(" INNER JOIN AD_User u ON (u.C_BPartner_ID = b.C_BPartner_ID)")
				.append(" INNER JOIN C_BPartner_Location lb ON (lb.C_BPartner_ID = b.C_BPartner_ID)")
				.append(" INNER JOIN C_Location l ON (l.C_Location_ID = lb.C_Location_ID)")
				.append(" WHERE b.C_BPartner_ID = ?");
			int i = 1;			
			preparedStatement = DB.prepareStatement(sql.toString(), null);
			//	POS
			preparedStatement.setInt(i++, posPanel.getC_BPartner_ID());
			resultSet = preparedStatement.executeQuery();
			posTable.loadTable(resultSet);
			int rowNo = posTable.getRowCount();
			if (rowNo > 0) {
				posTable.setRowSelectionInterval(0, 0);
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			log.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(resultSet);
			DB.close(preparedStatement);
			this.setCursor(Cursor.getDefaultCursor());
		}

	}
	
	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		cleanValues();
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled) {
			Integer id = posTable.getSelectedRowKey();
			if (id != null) {
				partnerId = id.intValue();
				partnerName = (String) posTable.getValueAt(row, 2);
			}
		}
		log.fine("C_BPartner_ID=" + partnerId);
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		dispose();
	}	//	close
	
	@Override
	public void reset() {
		fieldValue.setText(null);
		fieldTaxID.setText(null);
		fieldName.setText(null);
		fieldName2.setText(null);
		fieldContact.setText(null);
		fieldEmail.setText(null);
		fieldPhone.setText(null);
		fieldCity.setText(null);
		setResults(new MBPartnerInfo[0]);
		cleanValues();
	}

	@Override
	public void refresh() {
		cleanValues();
		setResults(MBPartnerInfo.find (ctx,
				fieldValue.getText(), fieldTaxID.getText(),
				fieldName.getText(), fieldName2.getText(),
				fieldContact.getText(), fieldEmail.getText(), fieldPhone.getText(), fieldCity.getText()));
	}
	
	/**
	 * Clean Values
	 * @return void
	 */
	private void cleanValues() {
		partnerId = -1;
		partnerName = null;
	}

	@Override
	protected void cancel() {
		cleanValues();
		dispose();
	}

	@Override
	public int getRecord_ID() {
		return partnerId;
	}

	@Override
	public String getValue() {
		return partnerName;
	}

}	//	PosQueryBPartner