/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos.search;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.pos.WPOS;
import org.adempiere.pos.WPOSTextField;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MOrder;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

/**
 *	POS Query DocType
 *	
 *  @version $Id: QueryBPartner.java,v 1.1 2004/07/12 04:10:04 jjanke Exp $
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */

public class WQueryDocType extends WPOSQuery implements POSQueryInterface
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7713957495649128816L;
	/**
	 * 	Constructor
	 */
	public WQueryDocType (WPOS posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct


	private WPOSTextField 	fieldName;
	private WPOSTextField 	fieldDescription;

	private boolean			isKeyboard;
	/**	Internal Variables	*/
	private int 			documentTypeId;
	
	static final private String NAME      		= "Name";
	static final private String DOCUMENTNOSEQUENCE = "DocNoSequence_ID";
	static final private String DESCRIPTION  	= "Description";
	static final private String QUERY           = "Query";
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] columnInfos = new ColumnInfo[] {
		new ColumnInfo(" ", "C_DocType_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), NAME), NAME, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DOCUMENTNOSEQUENCE), DOCUMENTNOSEQUENCE, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DESCRIPTION), DESCRIPTION, String.class),
	};

	/**
	 * 	Set up Panel
	 */
	protected void init()
	{
		setTitle(Msg.translate(Env.getCtx(), "C_DocType_ID"));
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid productLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(ctx, QUERY));
		
		//	Set title window
		this.setClosable(true);
		// add listener on 'ENTER' key 
        addEventListener(Events.ON_OK,this);
        
		appendChild(panel);
		northPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		groupPanel.appendChild(v_TitleBorder);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(groupPanel);
		groupPanel.appendChild(productLayout);
		appendChild(mainPanel);
		productLayout.setWidth("100%");
		Rows rows = null;
		Row row = null;
		rows = productLayout.newRows();
		row = rows.newRow();

		Label labelName = new Label(Msg.translate(ctx, NAME));
		labelName.setStyle(WPOS.FONTSIZESMALL);
		row.setHeight("60px");
		row.appendChild(labelName.rightAlign());
		fieldName = new WPOSTextField("", posPanel.getKeyboard());
		row.appendChild(fieldName);
		fieldName.addEventListener(this);
		fieldName.setWidth("120px");
		fieldName.setStyle(WPOS.FONTSIZESMALL);

		Label labelDescription = new Label(Msg.translate(ctx, DESCRIPTION));
		labelDescription.setStyle(WPOS.FONTSIZESMALL);
		row.setHeight("60px");
		row.appendChild(labelDescription.rightAlign());
		fieldDescription = new WPOSTextField(null, posPanel.getKeyboard());
		row.appendChild(fieldDescription);
		fieldDescription.addEventListener(this);
		fieldDescription.setWidth("120px");
		fieldDescription.setStyle(WPOS.FONTSIZESMALL);
		
		//	Center
		posTable = ListboxFactory.newDataTable();
		posTable.prepareTable (columnInfos, "C_DocType",null, false, "C_DocType");

		enableButtons();
		center = new Center();
		center.setStyle("border: none");
		posTable.setWidth("100%");
		posTable.setHeight("99%");
		posTable.addActionListener(this);
		center.appendChild(posTable);
		mainLayout.appendChild(center);
		posTable.setClass("Table-OrderLine");
		posTable.autoSize();
		posTable.addEventListener(Events.ON_DOUBLE_CLICK, this);
		refresh();
	}	//	init
	
	/**
	 * 
	 */
	@Override
	public void reset() {
		fieldName.setText("");
		refresh();
	}

	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (Properties ctx, String name, String description)
	{
		StringBuffer sql = new StringBuffer();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try  {
			sql.append(" SELECT dt.C_DocType_ID, dt.Name, sq.Name, (COALESCE(sq.Prefix, '') || sq.CurrentNext || COALESCE(sq.Suffix, '')) SeqNo")
			.append(" FROM C_DocType dt")
			.append(" LEFT JOIN AD_Sequence sq ON (sq.AD_Sequence_ID = dt.DocNoSequence_ID)")
			.append(" WHERE dt.AD_Client_ID = ? AND dt.AD_Org_ID IN (0, ?)")
			.append(" AND dt.isActive='Y'")
			.append(" AND dt.DocBaseType='SOO'")
			.append(" AND dt.DocSubTypeSO IN(?, ?, ?, ?, ?)")
		    .append(" ORDER BY dt.Name");
			int i = 1;			
			preparedStatement = DB.prepareStatement(sql.toString(), null);
			//	POS
			preparedStatement.setInt(i++, Env.getAD_Client_ID(ctx));
			preparedStatement.setInt(i++, posPanel.getAD_Org_ID());
			preparedStatement.setString(i++, MOrder.DocSubTypeSO_POS);
			preparedStatement.setString(i++, MOrder.DocSubTypeSO_OnCredit);
			preparedStatement.setString(i++, MOrder.DocSubTypeSO_Standard);
			preparedStatement.setString(i++, MOrder.DocSubTypeSO_Prepay);
			preparedStatement.setString(i++, MOrder.DocSubTypeSO_Warehouse);
			//	
			resultSet = preparedStatement.executeQuery();
			posTable.loadTable(resultSet);
			int rowNo = posTable.getRowCount();
			if (rowNo > 0) {
				if(rowNo == 1) {
					select();
				}
			}
		} catch(Exception e) {
			logger.severe("QueryTicket.setResults: " + e + " -> " + sql);
		} finally {
			DB.close(resultSet);
			DB.close(preparedStatement);
		}
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		documentTypeId = -1;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				documentTypeId = ID.intValue();
			}
		}
		logger.info("ID=" + documentTypeId);
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	@Override
	protected void close()
	{
		logger.info("C_DocType_ID=" + documentTypeId);
		if (documentTypeId > 0)
		{
		posPanel.setC_DocType_ID(documentTypeId);
		}
			dispose();
	}	//	close


	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getTarget().equals(fieldName.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			isKeyboard = true;
			//	Get Keyboard Panel
			fieldName.showKeyboard();
			refresh();
			fieldName.setFocus(true);

		}
		else if(e.getTarget().equals(fieldName.getComponent(WPOSTextField.PRIMARY))) {
			 isKeyboard = false;
		}
		if(e.getTarget().equals(fieldDescription.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
			isKeyboard = true;
			//	Get Keyboard Panel
			fieldDescription.showKeyboard();
			refresh();
			fieldDescription.setFocus(true);
		}
		else if(e.getTarget().equals(fieldDescription.getComponent(WPOSTextField.PRIMARY))) {
			 isKeyboard = false;
		}
		else if(e.getTarget().getId().equals("Refresh")) {
			refresh();
		}
		else if (Events.ON_OK.equals(e.getName()) 
				|| e.getTarget().equals(posTable) 
				&& e.getName().equals(Events.ON_DOUBLE_CLICK)) {
			close();	
		}
		else if(e.getTarget().getId().equals("Ok")){
			close();
		}
		else if(e.getTarget().getId().equals("Cancel")){
			close();
		}		else if(e.getTarget().getId().equals("Reset")){
			reset();
		}
		enableButtons();
	}

	@Override
	public void refresh() {
		lockUI();
		setResults(ctx, fieldName.getText(), fieldDescription.getText());
		unlockUI();
	}

	@Override
	protected void select() {
		documentTypeId = -1;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				documentTypeId = ID.intValue();
			}
		}
		logger.info("ID=" + documentTypeId);
	}
	@Override
	protected void cancel() {
		documentTypeId = -1;
		dispose();
	}
	
	@Override
	public int getRecord_ID() {
		return documentTypeId;
	}
	
	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}
	
}	//	PosQueryProduct
