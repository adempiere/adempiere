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

package org.adempiere.webui.panel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
* Based on InfoInOut written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 03, 2007
*/

public class InfoInOutPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	private static final long serialVersionUID = 1L;
	
	/**  String Array of Column Info    */
	private ColumnInfo[] m_generalLayout;
	
	/** list of query columns           */
	private ArrayList m_queryColumns = new ArrayList();
	
	/** Table Name              */
	private String m_tableName;
	
	/** Key Column Name         */
	private String m_keyColumn;

	private Textbox fDocumentNo = new Textbox();

	private WEditor fBPartner_ID;

	private Textbox fDescription = new Textbox();
	private Textbox fPOReference = new Textbox();

	private Datebox fDateFrom = new Datebox();
	private Datebox fDateTo = new Datebox();

	private Checkbox fIsSOTrx = new Checkbox();

	private Label lDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	private Label lDescription = new Label(Msg.translate(Env.getCtx(), "Description"));
	private Label lPOReference = new Label(Msg.translate(Env.getCtx(), "POReference"));

	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "MovementDate"));
	private Label lDateTo = new Label("-");

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_invoiceLayout = {
		new ColumnInfo(" ", "i.M_InOut_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=i.C_BPartner_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "MovementDate"), "i.MovementDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"), "i.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"), "i.Description", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "POReference"), "i.POReference", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsSOTrx"), "i.IsSOTrx", Boolean.class)
	};

	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	
	protected InfoInOutPanel(	int WindowNo, String value,
								boolean multiSelection, String whereClause)
	{
		super (WindowNo, "i", "M_InOut_ID", multiSelection, whereClause);
		log.info( "InfoInOut");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoInOut"));

		try
		{
			statInit();
			p_loadedOK = initInfo ();
		}
		catch (Exception e)
		{
			return;
		}

		int no = contentPanel.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		if (value != null && value.length() > 0)
		{
			fDocumentNo.setValue(value);
			executeQuery();
		}

		//pack();
		
		//	Focus
		//fDocumentNo.requestFocus();
	} // InfoInOutPanel

	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be initialized
	 */
	
	private void statInit() throws Exception
	{
		Hbox boxDocumentNo = new Hbox();
		
		fDocumentNo.addEventListener(Events.ON_CHANGE, this);
		
		boxDocumentNo.setWidth("100%");
		boxDocumentNo.setWidths("40%, 60%");
		boxDocumentNo.appendChild(lDocumentNo);
		boxDocumentNo.appendChild(fDocumentNo);

		Hbox boxDescription = new Hbox();
		
		fDescription.addEventListener(Events.ON_CHANGE, this);
		
		boxDescription.setWidth("100%");
		boxDescription.setWidths("40%, 60%");
		boxDescription.appendChild(lDescription);
		boxDescription.appendChild(fDescription);
				
		Hbox boxPORef = new Hbox();
		
		fPOReference.addEventListener(Events.ON_CHANGE, this);
		
		boxPORef.setWidth("100%");
		boxPORef.setWidths("40%, 60%");
		boxPORef.appendChild(lPOReference);
		boxPORef.appendChild(fPOReference);

		fIsSOTrx.setLabel(Msg.translate(Env.getCtx(), "IsSOTrx"));
		fIsSOTrx.setChecked(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		fIsSOTrx.addEventListener(Events.ON_CHECK, this);

		//	fOrg_ID = new VLookup("AD_Org_ID", false, false, true,
		//	MLookupFactory.create(Env.getCtx(), 3486, m_WindowNo, DisplayType.TableDir, false),
		//	DisplayType.TableDir, m_WindowNo);
		//	lOrg_ID.setLabelFor(fOrg_ID);
		//	fOrg_ID.setBackground(AdempierePLAF.getInfoBackground());
		
		Hbox boxBPartner = new Hbox();
		
		fBPartner_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search), 
				Msg.translate(Env.getCtx(), "BPartner"), "", false, false, true);
		fBPartner_ID.addValueChangeListner(this);

		boxBPartner.appendChild(fBPartner_ID.getLabel());
		boxBPartner.appendChild(fBPartner_ID.getComponent());
		
		Hbox boxDateFrom = new Hbox();
		boxDateFrom.setWidth("100%");
		boxDateFrom.setWidths("40%, 60%");
		boxDateFrom.appendChild(lDateFrom);
		boxDateFrom.appendChild(fDateFrom);
		
		Hbox boxDateTo = new Hbox();
		boxDateTo.setWidth("100%");
		boxDateTo.setWidths("10%, 90%");
		boxDateTo.appendChild(lDateTo);
		boxDateTo.appendChild(fDateTo);

		VerticalBox boxCol1 = new VerticalBox();
		boxCol1.appendChild(boxDocumentNo);
		boxCol1.appendChild(new Separator());
		boxCol1.appendChild(boxDescription);
		boxCol1.appendChild(new Separator());
		boxCol1.appendChild(boxPORef);
		
		VerticalBox boxCol2 = new VerticalBox();
		boxCol2.appendChild(boxBPartner);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxDateFrom);
		
		VerticalBox boxCol3 = new VerticalBox();
		boxCol3.appendChild(fIsSOTrx);
		boxCol3.appendChild(new Separator());
		boxCol3.appendChild(boxDateTo);
		
		Hbox mainBox = new Hbox();
		mainBox.setWidth("100%");
		mainBox.setWidths("40%, 50%, 10%");
		mainBox.appendChild(boxCol1);
		mainBox.appendChild(boxCol2);
		mainBox.appendChild(boxCol3);
		
		this.setWidth("850px");
		this.setTitle("InOut Info");
		this.setClosable(true);
		this.setBorder("normal");
		this.appendChild(mainBox);
		this.appendChild(new Separator());
		this.appendChild(contentPanel);
		this.appendChild(new Separator());
		this.appendChild(confirmPanel);
		this.appendChild(new Separator());
		this.appendChild(statusBar);
	}
	
	/**
	 *	General Init
	 *	@return true, if success
	 */
	
	private boolean initInfo ()
	{
		//  Set Defaults
		String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
	
		if (bp != null && bp.length() != 0)
			fBPartner_ID.setValue(new Integer(bp));

		// Prepare table
		
		StringBuffer where = new StringBuffer("i.IsActive='Y'");
		
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "M_InOut.", "i."));
		
		prepareTable(s_invoiceLayout, " M_InOut i", where.toString(), "2,3,4");

		return true;
	} // initInfo

	/*************************************************************************/

	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 * 	@return where clause
	 */
	
	String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		
		if (fDocumentNo.getText().length() > 0)
			sql.append(" AND UPPER(i.DocumentNo) LIKE ?");
		
		if (fDescription.getText().length() > 0)
			sql.append(" AND UPPER(i.Description) LIKE ?");
		
		if (fPOReference.getText().length() > 0)
			sql.append(" AND UPPER(i.POReference) LIKE ?");

		if (fBPartner_ID.getDisplay() != "")
			sql.append(" AND i.C_BPartner_ID=?");

		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Date f = fDateFrom.getValue();
			Timestamp from = new Timestamp(f.getTime());
			
			Date t = fDateTo.getValue();
			Timestamp to = new Timestamp(t.getTime());

			if (from == null && to != null)
				sql.append(" AND TRUNC(i.MovementDate) <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(i.MovementDate) >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(i.MovementDate) BETWEEN ? AND ?");
		}
		sql.append(" AND i.IsSOTrx=?");

		//	log.fine( "InfoInOut.setWhereClause", sql.toString());
		return sql.toString();
	} // getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	
	void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
	
		if (fDocumentNo.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDocumentNo));
		
		if (fDescription.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDescription));
		
		if (fPOReference.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fPOReference));

		if (fBPartner_ID.getDisplay() != "")
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
		}

		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Date f = fDateFrom.getValue();
			Timestamp from = new Timestamp(f.getTime());
			
			Date t = fDateTo.getValue();
			Timestamp to = new Timestamp(t.getTime());

			log.fine("Date From=" + from + ", To=" + to);
			
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
		pstmt.setString(index++, fIsSOTrx .isChecked() ? "Y" : "N");
	} // setParameters

	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return sql part
	 */
	
	private String getSQLText (Textbox f)
	{
		String s = f.getText().toUpperCase();
	
		if (!s.endsWith("%"))
			s += "%";
		
		log.fine( "String=" + s);
		return s;
	} // getSQLText

	/**
	 *	Zoom
	 */
	
	public void zoom()
	{
/*		log.info( "InfoInOut.zoom");
		Integer M_InOut_ID = getSelectedRowKey();
	
		if (M_InOut_ID == null)
			return;
		
		MQuery query = new MQuery("M_InOut");
		query.addRestriction("M_InOut_ID", MQuery.EQUAL, M_InOut_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_InOut", fIsSOTrx.isSelected());
		zoom (AD_WindowNo, query);*/
	} // zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	
	boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	public void valueChange(ValueChangeEvent evt) 
	{
		if (fBPartner_ID.equals(evt.getSource()))
		{
	    	fBPartner_ID.setValue(evt.getNewValue());
		}
	}

	public void tableChanged(WTableModelEvent event) 
	{
		
	}
}