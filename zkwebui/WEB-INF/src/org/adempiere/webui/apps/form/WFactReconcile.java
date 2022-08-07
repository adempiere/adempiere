/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2013 Adaxa Inc., All Rights Reserved.			              *
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
 * Contributors: Adaxa                                                        *
 *****************************************************************************/
package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_Fact_Reconciliation;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.session.SessionManager;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MFactAcct;
import org.compiere.model.MFactReconciliation;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Space;


/**
* Based on VFactReconcile.java contributed by Adaxa
* Create manual match of accounting facts
* 
* Zk Port
* 
* @author Michael McKay, ADEMPIERE-41 GL Reconciliation integration
* @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
*		<a href="https://github.com/adempiere/adempiere/issues/889">
* 		@see FR [ 889 ] Ambidexter General Ledger Reconciliation</a>
*/
public class WFactReconcile extends CustomForm
	implements IFormController, EventListener, WTableModelListener, ASyncProcess
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5188910630217870819L;

	protected void initForm()
	{
		log.info("");
		try
		{
			initComponents();
			initLayout();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	initForm

	/**	Window No			*/
	private int         	m_WindowNo = 0;

	/** Format                  */
	private DecimalFormat   m_format = DisplayType.getNumberFormat(DisplayType.Amount);
	/** SQL for Query           */
	private String          m_sql;
	/** Number of selected rows */
	private int             m_noSelected = 0;
	/** Client ID               */
	private int             m_AD_Client_ID = 0;
	/**/
	private boolean         m_isLocked = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WFactReconcile.class);

	//
	private Borderlayout mainPanel = new Borderlayout(); 
	private Grid parameterGrid = GridFactory.newGridLayout();
	private Borderlayout parameterPanel = new Borderlayout();  
	private Borderlayout commandPanel = new Borderlayout(); // To hold the buttons and status bar
	private Hbox commandPane; // To hold the buttons
	private ConfirmPanel confirmPanel = new ConfirmPanel(); // Needed only to generate buttons easily.
    //
    private Hbox hboxBtnLeft;
    private Hbox hboxBtnRight;
    private Hbox hboxTextCenter;
    //
    private Panel pnlBtnRight;
    private Panel pnlBtnLeft;
    private Panel pnlTextCenter;
    //
	private WListbox miniTable = new WListbox();
	//
	private Label labelAcctSchema = new Label();
	private Label labelOrg = new Label();
	private Label labelBlank = new Label();
	private Label labelAccount = new Label();
	private Label labelBPartner = new Label();
	protected StatusBarPanel statusBar = new StatusBarPanel();
	//
	private Label labelDateAcct = new Label();
	private Label labelDateAcct2 = new Label();
	private Label labelProduct = new Label();
	private Label differenceLabel = new Label();
	//
	private WTableDirEditor fieldAcctSchema = null;
	private WTableDirEditor fieldOrg = null;
	private WSearchEditor fieldProduct = WSearchEditor.createProduct(m_WindowNo);	
	private WSearchEditor fieldBPartner = WSearchEditor.createBPartner(m_WindowNo);
	//
	private Checkbox isReconciled = new Checkbox();
	private WTableDirEditor fieldAccount = null;
	private Textbox differenceField = new Textbox();
	private Datebox fieldDateAcct = new Datebox();
	private Datebox fieldDateAcct2 = new Datebox();
	//
	private Button bCancel = confirmPanel.createButton(ConfirmPanel.A_CANCEL);
	private Button bGenerate = confirmPanel.createButton(ConfirmPanel.A_PROCESS);
	private Button bReset = confirmPanel.createButton(ConfirmPanel.A_RESET);
	private Button bZoom = confirmPanel.createButton(ConfirmPanel.A_ZOOM);
	private Button bRefresh = confirmPanel.createButton(ConfirmPanel.A_REFRESH);
	//
	private boolean loading = false;
	private int amtColIndex = 1;

	/**
	 *  Static Init
	 *  @throws Exception
	 */
	private void initComponents() throws Exception
	{

		//  Buttons
		bCancel.addActionListener(this);
		//
		bGenerate.setLabel(Msg.getMsg(Env.getCtx(),"Process"));
		bGenerate.addActionListener(this);
		bGenerate.setEnabled(false);
		//	
		bReset.setLabel(Msg.getMsg(Env.getCtx(),"Reset"));
		bReset.addActionListener(this);
		bReset.setEnabled(false);
		//
		bZoom.setLabel(Msg.translate(Env.getCtx(), "Fact_Acct_ID"));
		bZoom.setEnabled(false);
		bZoom.addActionListener(this);
		//
		bRefresh.addActionListener(this);
	
		//  Labels
		labelBlank.setValue(" ");
		labelAcctSchema.setText(Msg.translate(Env.getCtx(), "C_AcctSchema_ID"));
		labelAccount.setText(Msg.translate(Env.getCtx(), "Account_ID"));
		labelBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		labelDateAcct.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		labelDateAcct2.setText("-");
		labelProduct.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
		//
		labelOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		isReconciled.setText(Msg.translate(Env.getCtx(), "IsReconciled"));
		//
		statusBar.setEastVisibility(false);
		statusBar.setAttribute("zk_component_ID", "info_statusBar");
        setStatusLine(Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB("0");
		//
		differenceLabel.setText(Msg.getMsg(Env.getCtx(), "Difference"));
		differenceField.setReadonly(true);
		differenceField.setValue("0");
		differenceField.setAttribute("zk_component_ID", "ConfirmPanel_differenceField");
		
		//  Find context and client
		Properties ctx = Env.getCtx();
		m_AD_Client_ID = Env.getAD_Client_ID(Env.getCtx());	

		// Organization filter selection
		//  Fact_Acct.C_AcctSchema_ID AD_Column_ID = 2513
		fieldAcctSchema = new WTableDirEditor("C_AcctSchema_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
						MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_C_AcctSchema_ID),
						DisplayType.TableDir));
		fieldAcctSchema.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_AcctSchema_ID");
		fieldAcctSchema.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldAcctSchema.getComponent().setAttribute("IsDynamic", "False");
		fieldAcctSchema.getComponent().setAttribute("fieldName", "fieldAcctSchema");
		fieldAcctSchema.setValue(MClient.get(Env.getCtx()).getAcctSchema().getC_AcctSchema_ID());
		//
		//Fact_Acct.AD_Org_ID (needed to allow org 0) AD_Column_ID = 839; 
		fieldOrg = new WTableDirEditor("AD_Org_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
						MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_AD_Org_ID),
						DisplayType.TableDir));
		fieldOrg.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_AD_Org_ID");
		fieldOrg.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldOrg.getComponent().setAttribute("IsDynamic", "False");
		fieldOrg.getComponent().setAttribute("fieldName", "fieldOrg");
		fieldOrg.setValue(0);  // Attempt to select "*" and fall back to context           
        if (fieldOrg.getValue() == null || ((Integer) fieldOrg.getValue()).intValue() != 0)
			fieldOrg.setValue(Env.getAD_Org_ID(Env.getCtx()));
		//
        //  BPartner C_Invoice.C_BPartner_ID AD_Column_ID = 3499; 
		fieldBPartner.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fieldBPartner.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldBPartner.getComponent().setAttribute("IsDynamic", "False");
		fieldBPartner.getComponent().setAttribute("fieldName", "fieldBPartner");
		fieldBPartner.getComponent().setWidth("200px");
		//
		// Product Fact_Acct.M_Product_ID AD_Column_ID = 2527;        
		fieldProduct.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_Product_ID");
		fieldProduct.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldProduct.getComponent().setAttribute("IsDynamic", "False");
		fieldProduct.getComponent().setAttribute("fieldName", "fieldProduct");
		fieldProduct.getComponent().setWidth("200px");
		//
		// The Account combo.  A bit more involved if we try to filter out the summary accounts.
		MLookup lookup;
		try{
			lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 
					MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_Account_ID),
					DisplayType.TableDir, Env.getLanguage(Env.getCtx()), MFactAcct.COLUMNNAME_Account_ID, 
					0, false, "C_ElementValue.IsSummary = 'N'");
		}
		catch (Exception e)
		{
			// Jut alors!  Drop the validation and try again.
			lookup = MLookupFactory.get (Env.getCtx(), m_WindowNo, 0, 
					MColumn.getColumn_ID(MFactAcct.Table_Name, MFactAcct.COLUMNNAME_Account_ID), DisplayType.TableDir);
		}
		fieldAccount = new WTableDirEditor("AD_Org_ID", true, false, true, lookup);
		fieldAccount.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_Account_ID");
		fieldAccount.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldAccount.getComponent().setAttribute("IsDynamic", "False");
		fieldAccount.getComponent().setAttribute("fieldName", "fieldAccount");
		if (fieldAccount.getComponent().getItemCount() > 2)
			fieldAccount.getComponent().setSelectedIndex(1);

		//  Define the table
		m_sql = miniTable.prepareTable(new ColumnInfo[] {
				new ColumnInfo(" ", "fa.Fact_Acct_ID", IDColumn.class, false, false, null),
				new ColumnInfo(Msg.translate(ctx, "AmtAcct"), "(fa.amtacctdr-fa.amtacctcr)", BigDecimal.class,true,true,null),
				new ColumnInfo("DR/CR", "(CASE WHEN (fa.amtacctdr-fa.amtacctcr) < 0 THEN 'CR' ELSE 'DR' END)", String.class),
				new ColumnInfo(Msg.translate(ctx, "C_BPartner_ID"), "bp.Name", String.class),
				new ColumnInfo(Msg.translate(ctx, "DateAcct"), "fa.DateAcct", Timestamp.class),
				new ColumnInfo(Msg.translate(ctx, "GL_Category_ID"), "glc.Name", String.class),
				new ColumnInfo(Msg.translate(ctx, "M_Product_ID"), "p.Value", String.class),
				new ColumnInfo(Msg.translate(ctx, "Qty"), "Qty", BigDecimal.class),
				new ColumnInfo(Msg.translate(ctx, "Description"), "fa.Description", String.class),
				new ColumnInfo(Msg.translate(ctx, "MatchCode"), "r.MatchCode", String.class),
				new ColumnInfo(Msg.translate(ctx, "DateTrx"), "fa.DateTrx", Timestamp.class),
				new ColumnInfo(Msg.translate(ctx, "AD_Org_ID"), "o.Value", String.class),
				new ColumnInfo(Msg.translate(ctx, "Amt"), "abs(fa.amtacctdr-fa.amtacctcr)", BigDecimal.class, 0, true, false, null, false)},
				//	FROM
				"Fact_Acct fa"
				+ " LEFT OUTER JOIN Fact_Reconciliation r ON (fa.Fact_Acct_ID=r.Fact_Acct_ID)"
				+ " LEFT OUTER JOIN C_BPartner bp ON (fa.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " LEFT OUTER JOIN AD_Org o ON (o.AD_Org_ID=fa.AD_Org_ID)"
				+ " LEFT OUTER JOIN M_Product p ON (p.M_Product_ID=fa.M_Product_ID)"
				+ " LEFT OUTER JOIN GL_Category glc ON (fa.GL_Category_ID=glc.GL_Category_ID)",
				//	WHERE
				" fa.AD_Client_ID=?",	//	additional where & order in loadTableInfo()
				true, "fa");
			//
			miniTable.addActionListener(new EventListener() {
				public void onEvent(Event event) throws Exception {

					if (event.getName().equals("onSelect"))
					{
						calculateSelection();
						boolean enable = (miniTable.getSelectedCount() == 1);
						bZoom.setEnabled(enable);
					}					
				}
			});
	}   //  initComponents

	/**
	 *  Initialize the form structure
	 *  - Load Bank Info
	 *  - Load BPartner
	 *  - Load Document Type
	 *  - Init Table
	 */
	private void initLayout()
	{
		setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
		setBorder("none");
		setWidth("100%");
		setHeight("100%");
		setStyle("position: absolute");

        miniTable.setAttribute("zk_component_ID", "Lookup_Data_SearchResults");        
        miniTable.setVflex(true);

        //  Define the criteria rows and grid  
		Rows rows = new Rows();
		//
		Row row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1");
		row.appendChild(labelAcctSchema.rightAlign());
		row.appendChild(fieldAcctSchema.getComponent());
		row.appendChild(labelBPartner.rightAlign());
		row.appendChild(fieldBPartner.getComponent());
		//
		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1");
		row.appendChild(labelOrg.rightAlign());
		row.appendChild(fieldOrg.getComponent());
		row.appendChild(labelProduct.rightAlign());
		row.appendChild(fieldProduct.getComponent());
		//
		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1");
		row.appendChild(labelAccount.rightAlign());
		row.appendChild(fieldAccount.getComponent());
		row.appendChild(new Space());
		row.appendChild(isReconciled);
		//
		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1");
		row.appendChild(labelDateAcct.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(fieldDateAcct);
		hbox.appendChild(labelDateAcct2);
		hbox.appendChild(fieldDateAcct2);
		row.appendChild(hbox);
		//
		parameterGrid.appendChild(rows);
		//
		Center center = new Center();
		center.setBorder("0");
		center.appendChild(parameterGrid);
		Hbox btnBox = new Hbox();
		btnBox.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
		btnBox.appendChild(bRefresh);
		btnBox.setHeight("100%");
		West west = new West();
		west.setBorder("0");
		west.appendChild(btnBox);
		parameterPanel.appendChild(center);
		parameterPanel.appendChild(west);
		parameterPanel.setHeight("100px");
		//
		North north = new North();
		north.appendChild(parameterPanel);
		mainPanel.appendChild(north);
		//
		center = new Center();
		center.appendChild(miniTable);
		mainPanel.appendChild(center);

		//  Setup the command buttons
		pnlBtnLeft = new Panel();
        pnlBtnLeft.setAlign("left");
        pnlBtnLeft.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
        pnlBtnLeft.appendChild(bZoom);
        //
        pnlBtnRight = new Panel();
        pnlBtnRight.setAlign("right");
        pnlBtnRight.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
        pnlBtnRight.appendChild(bGenerate);
        pnlBtnRight.appendChild(bReset);
        pnlBtnRight.appendChild(bCancel);
        //
        pnlTextCenter = new Panel();
        pnlTextCenter.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
        pnlTextCenter.setAlign("center");
        pnlTextCenter.appendChild(differenceLabel);
        pnlTextCenter.appendChild(differenceField);
        //
        hboxBtnRight = new Hbox();
        hboxBtnRight.appendChild(pnlBtnRight);
        hboxBtnRight.setWidth("100%");
        hboxBtnRight.setStyle("text-align:right");
        //
        hboxBtnLeft = new Hbox();
        hboxBtnLeft.appendChild(pnlBtnLeft);
        hboxBtnLeft.setWidth("100%");
        hboxBtnLeft.setStyle("text-align:left");
        //
        hboxTextCenter = new Hbox();
        hboxTextCenter.appendChild(pnlTextCenter);
        hboxTextCenter.setWidth("100%");
        hboxTextCenter.setHeight("100%");
        hboxTextCenter.setPack("center");
        hboxTextCenter.setStyle("text-align:Center");
        //
        commandPane = new Hbox();
        commandPane.appendChild(hboxBtnLeft);
        commandPane.appendChild(hboxTextCenter);
        commandPane.appendChild(hboxBtnRight);
        commandPane.setWidth("100%");
        commandPane.setPack("center");        
        //
		commandPanel.setHeight("70px");
		commandPanel.setStyle("border-top: 2px; border-bottom: 2px; padding: 4px");
		commandPanel.setWidth("100%");
		center = new Center();
		center.appendChild(commandPane);
		center.setBorder("0");
		commandPanel.appendChild(center);
		South south = new South();
		south.appendChild(statusBar);
		south.setBorder("0");
		commandPanel.appendChild(south);
		south = new South();
		south.appendChild(commandPanel);
		south.setBorder("0");
		mainPanel.appendChild(south);
		
		//  Add everything to the form 
		this.appendChild(mainPanel);
		
	}   //  initLayout

	/**
	 *  Query and create TableInfo
	 */
	private void loadTableInfo()
	{
		log.config("");
		//  not yet initialized
		if (m_sql == null)
			return;
		loading  = true;

		String sql = m_sql;
		int Account_ID = ((Integer) fieldAccount.getValue()).intValue() ;
		if (Account_ID != 0)
			sql += " AND fa.Account_ID=?";
		
		if ( ((Integer) fieldAcctSchema.getValue()) > 0 )
			sql += " AND fa.C_AcctSchema_ID = ?";
		
		sql += " AND ((SELECT SUM(f.amtacctdr-f.amtacctcr) FROM Fact_Reconciliation rec " +
				" INNER JOIN Fact_Acct f ON (f.Fact_Acct_ID = rec.Fact_Acct_ID) " +
				" WHERE r.MatchCode=rec.MatchCode) ";
		if ( isReconciled.isSelected() )
			sql += "= 0) ";
		else
			sql += "<> 0 OR r.MatchCode IS NULL) ";
				
		if ( fieldBPartner.getValue() != null )
			sql += " AND fa.C_BPartner_ID = ?";
		
		if ( fieldProduct.getValue() != null )
			sql += " AND fa.M_Product_ID = ?";
		
		if ( fieldDateAcct.getValue() != null )
			sql += " AND fa.DateAcct >= ?";
		
		if ( fieldDateAcct2.getValue() != null )
			sql += " AND fa.DateAcct <= ?";
		
		
		sql += " ORDER BY 13,4,3,5";

		log.finest(sql + "Account_ID =" + Account_ID );
		//  Get facts
		try
		{
			int index = 1;
			PreparedStatement pstmt = DB.prepareStatement(sql, null);

			pstmt.setInt(index++, m_AD_Client_ID);		//	Client

			pstmt.setInt(index++, (Integer) fieldAccount.getValue());		//	account
			
			if ( ((Integer) fieldAcctSchema.getValue()) > 0 )
				pstmt.setInt(index++, (Integer) fieldAcctSchema.getValue());
			
			if (  fieldBPartner.getValue() != null )
				pstmt.setInt(index++, (Integer) fieldBPartner.getValue());
			
			if ( fieldProduct.getValue() != null )
				pstmt.setInt(index++, (Integer) fieldProduct.getValue());
			
			if ( fieldDateAcct.getValue() != null )
				pstmt.setTimestamp(index++, (Timestamp) fieldDateAcct.getValue());
			

			if ( fieldDateAcct2.getValue() != null )
				pstmt.setTimestamp(index++, (Timestamp) fieldDateAcct2.getValue());
			
			
			ResultSet rs = pstmt.executeQuery();
			miniTable.loadTable(rs);
			rs.close();
			pstmt.close();
			log.log(Level.FINE, sql);
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		loading = false;
		
		calculateSelection();
	}   //  loadTableInfo

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		miniTable.clear();
    	SessionManager.getAppDesktop().closeActiveWindow();
	}	//	dispose

	@Override
	public void onEvent(Event event) throws Exception {
		if  (event!=null)
        {
    		if (event.getName().equals("onOK"))
    		{
    			//  The enter key was pressed in a criteria field.  Ignore it.  The key click will trigger
    			//  other events that will be trapped.
    			event.stopPropagation();
    			return;
    		}

        	Component component = event.getTarget();
    		
    		if(component != null)
    		{
					//  Generate Reconciliation
					if (component.equals(bGenerate))
					{
						generateReconciliation();
					}
					else if (component.equals(bReset))
					{
						resetReconciliation();
					}
					else if (component.equals(bZoom))
					{
						zoom();
					}
					else if (component.equals(bCancel))
					{
						dispose();
					}
					else if (component.equals(bRefresh))
					{
						loadTableInfo();
					}
    		}
        }
	}
	
	/**
	 *	Zoom to target
	 *  @param AD_Window_ID window id
	 *  @param zoomQuery zoom query
	 */
	protected void zoom ()
	{
		log.info("");
		
		Integer factId = miniTable.getSelectedRowKey();
		if (factId != null)
			AEnv.zoom(270, factId.intValue());
		
	}	//	zoom

	/**
     *  Get the keys of selected row/s based on layout defined in prepareTable
     *  @return IDs if selection present
     *  @author ashley
     */
    protected ArrayList<Integer> getSelectedRowKeys()
    {
        ArrayList<Integer> selectedDataList = new ArrayList<Integer>();
        
        if (miniTable.getKeyColumnIndex() == -1)
        {
            return selectedDataList;
        }
        
    	int[] rows = miniTable.getSelectedIndices();
        for (int row = 0; row < rows.length; row++)
        {
            Object data = miniTable.getModel().getValueAt(rows[row], miniTable.getKeyColumnIndex());
            if (data instanceof IDColumn)
            {
                IDColumn dataColumn = (IDColumn)data;
                selectedDataList.add(dataColumn.getRecord_ID());
            }
            else
            {
                log.severe("For multiple selection, IDColumn should be key column for selection");
            }
        }
         
        if (selectedDataList.size() == 0)
        {
        	int row = miniTable.getSelectedRow();
    		if (row != -1 && miniTable.getKeyColumnIndex() != -1)
    		{
    			Object data = miniTable.getModel().getValueAt(row, miniTable.getKeyColumnIndex());
    			if (data instanceof IDColumn)
    				selectedDataList.add(((IDColumn)data).getRecord_ID());
    			if (data instanceof Integer)
    				selectedDataList.add((Integer)data);
    		}
        }
      
        return selectedDataList;
    }   //  getSelectedRowKeys

	private void resetReconciliation() {
		log.info("");
		//
		if (miniTable.getRowCount() == 0)
			return;

		if (m_noSelected == 0)
			return;

    	int[] rows = miniTable.getSelectedIndices();
    	if (rows.length ==0)
    		return;
    	
        for (int row = rows.length-1; row >= 0; row--)  //work backwards and shrink the table as you go
        {
			int factId = miniTable.getRowKey(rows[row]);

			MFactReconciliation rec = new Query(Env.getCtx(), I_Fact_Reconciliation.Table_Name, 
					I_Fact_Reconciliation.COLUMNNAME_Fact_Acct_ID + " = ?", null)
					.setParameters(new Object[] {factId}).first();
			//	
			if (rec == null) {
				continue;
			}

			rec.setMatchCode(null);
			rec.saveEx();

			((ListModelTable) miniTable.getModel()).remove(rows[row]);
		}		
 
        calculateSelection();
	}

	/**
	 *  Calculate selected rows.
	 *  - add up selected rows
	 */
	public void calculateSelection()
	{
		m_noSelected = 0;
		BigDecimal selectedAmt = new BigDecimal(0.0);

        if (miniTable.getKeyColumnIndex() == -1)
        {
            return;
        }
        
    	int[] rows = miniTable.getSelectedIndices();
        for (int row = 0; row < rows.length; row++)
        {
			BigDecimal amt = (BigDecimal)miniTable.getModel().getValueAt(rows[row], amtColIndex);
			if (amt != null)
				selectedAmt = selectedAmt.add(amt);
			m_noSelected++;
        }

		//  Information
		StringBuffer info = new StringBuffer();
		info.append(m_noSelected).append(" ").append(Msg.getMsg(Env.getCtx(), "Selected")).append(" / ").append(miniTable.getRowCount());
		
		differenceField.setValue(m_format.format(selectedAmt));
        setStatusLine(info.toString() + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
        setStatusDB(Integer.toString(miniTable.getRowCount()));

		//
		bGenerate.setEnabled(m_noSelected != 0 && Env.ZERO.compareTo(selectedAmt) == 0 && !isReconciled.isSelected());
		bReset.setEnabled(m_noSelected > 0 && isReconciled.isSelected());
	}   //  calculateSelection

	/**
	 *  Generate Reconciliation record
	 */
	private void generateReconciliation()
	{
		log.info("");
		//
		//miniTable.stopEditor(true);
		if (miniTable.getRowCount() == 0)
			return;

		if (m_noSelected == 0)
			return;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = DisplayType.getDateFormat();
		String time = sdf.format(cal.getTime());

		String matchcode = Msg.parseTranslation(Env.getCtx(), "@IsManual@: " + Env.getContext(Env.getCtx(), "#AD_User_Name") + " " + time);
        
    	int[] rows = miniTable.getSelectedIndices();
    	Arrays.sort(rows);
    	int[] sortedRows = rows;
    	if (rows.length ==0)
    		return;
    	
        for (int row = sortedRows.length-1; row >= 0; row--)  //work backwards and shrink the table as you go
        {
			int factId = miniTable.getRowKey(sortedRows[row]);
			//	
			MFactReconciliation rec = new Query(Env.getCtx(), I_Fact_Reconciliation.Table_Name, 
					I_Fact_Reconciliation.COLUMNNAME_Fact_Acct_ID + " = ?", null)
					.setParameters(new Object[] {factId}).first();
			//	
			if (rec == null) {
				rec = new MFactReconciliation(Env.getCtx(), 0, null);
				rec.setFact_Acct_ID(factId);
			}

			rec.setMatchCode(matchcode);
			rec.saveEx();

			miniTable.getModel().remove(rows[row]);
		}
        calculateSelection();
	}   

	/**
	 *  Lock User Interface
	 *  Called from the Worker before processing
	 *  @param pi process info
	 */
	public void lockUI (ProcessInfo pi)
	{
		//this..setEnabled(false);  // Can't do this in ZK - create a modal dialog?
		m_isLocked = true;
	}   //  lockUI

	/**
	 *  Unlock User Interface.
	 *  Called from the Worker when processing is done
	 *  @param pi process info
	 * @return 
	 */
	public void unlockUI (ProcessInfo pi)
	{
		m_isLocked = false;
	}   //  unlockUI

	/**
	 *  Is the UI locked (Internal method)
	 *  @return true, if UI is locked
	 */
	public boolean isUILocked()
	{
		return m_isLocked;
	}   //  isLoacked

	/**
	 *  Method to be executed async.
	 *  Called from the ASyncProcess worker
	 *  @param pi process info
	 */
	public void executeASync (ProcessInfo pi)
	{
		log.config("-");
	}   //  executeASync

	@Override
	public ADForm getForm() {
		return this;
	}

	@Override
	public void tableChanged(WTableModelEvent event) {
		log.info(" ");
		if (! loading )
			calculateSelection();
	}
	/**
	 *	Set Status Line
	 *  @param text text
	 *  @param error error
	 */
	public void setStatusLine (String text, boolean error)
	{
		statusBar.setStatusLine(text, error);
	}	//	setStatusLine

	/**
	 *	Set Status DB
	 *  @param text text
	 */
	public void setStatusDB (String text)
	{
		statusBar.setStatusDB(text);
	}	//	setStatusDB

} 