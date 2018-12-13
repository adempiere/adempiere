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
package org.adempiere.webui.apps.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WListItemRenderer;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.component.WTableColumn;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.compiere.apps.form.TrialBalanceDrill;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MPeriod;
import org.compiere.model.MReportCube;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listfoot;
import org.zkoss.zul.Listfooter;
import org.zkoss.zul.Menuitem;

/**
 * @author Nikunj Panelia
 */
public class WTrialBalance extends TrialBalanceDrill implements IFormController, EventListener, ValueChangeListener
{
	private CustomForm		form			= new CustomForm();
	/** Logger */
	private static CLogger	log				= CLogger.getCLogger(WTrialBalance.class);

	private Borderlayout	mainLayout		= new Borderlayout();
	private Panel			parameterPanel	= new Panel();
	private Grid			parameterLayout	= GridFactory.newGridLayout();
	private Panel			commandPanel	= new Panel();
	private Borderlayout	commandLayout	= new Borderlayout();
	private WListbox		miniTable		= ListboxFactory.newDataTable();

	// components

	private Label			lBudget			= new Label(Msg.translate(Env.getCtx(), "GL_Budget_ID"));
	private WTableDirEditor	fieldBudget		= null;
	
	private Label			lPeriodTo		= new Label(Msg.translate(Env.getCtx(), "Period To") + "*");
	private WTableDirEditor	fieldPeriod		= null;
	private Label			lOrg			= new Label(Msg.translate(Env.getCtx(), "AD_Org_ID") + "*");
	private WTableDirEditor	fieldOrg		= null;
	private Label			lAcctFrom		= new Label(Msg.translate(Env.getCtx(), "Account Key"));
	private WTableDirEditor	fieldAcctFrom	= null;
	private Label			lAcctTo			= new Label("-");
	private WTableDirEditor	fieldAcctTo		= null;

	private Label			luser1			= new Label(Msg.translate(Env.getCtx(), "User1_ID"));
	private WTableDirEditor	fUser1	= null;
	

	private Label			lCube			= new Label(Msg.translate(Env.getCtx(), "PA_ReportCube_ID"));
	private WTableDirEditor	fReportCube	= null;

	ConfirmPanel			cp				= new ConfirmPanel();
	private Button			bRefresh		= cp.createButton(ConfirmPanel.A_REFRESH);
	private Button			bCancel			= cp.createButton(ConfirmPanel.A_CANCEL);
	private Button			bExportExcel	= cp.createButton(ConfirmPanel.A_EXPORT);

	public WTrialBalance()
	{
		try
		{
			super.dynInit();
			dynInit();
			init();
		}
		catch (Exception e)

		{
			log.log(Level.SEVERE, "", e.getLocalizedMessage());
		}
	}

	private void init()
	{
		form.appendChild(mainLayout);
		parameterPanel.appendChild(parameterLayout);

		bRefresh.addActionListener(this);
		fieldAcctFrom.addValueChangeListener(this);
		fieldAcctTo.addValueChangeListener(this);
		fieldOrg.addValueChangeListener(this);
		fieldPeriod.addValueChangeListener(this);

		// Parameter Panel
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);

		Rows rows = null;
		Row row = null;
		parameterLayout.setWidth("100%");
		rows = parameterLayout.newRows();
		row = rows.newRow();
		
		row.appendChild(lOrg.rightAlign());
		row.appendChild(fieldOrg.getComponent());
		row.appendChild(lBudget.rightAlign());
		row.appendChild(fieldBudget.getComponent());
		
		row = rows.newRow();
		row.appendChild(lPeriodTo.rightAlign());
		row.appendChild(fieldPeriod.getComponent());
		if (Env.getContext(Env.getCtx(), "$Element_U1").equalsIgnoreCase("Y"))
		{
			row.appendChild(luser1.rightAlign());
			row.appendChild(fUser1.getComponent());
		}
		
		row = rows.newRow();		
		row.appendChild(lAcctFrom.rightAlign());
		row.appendChild(fieldAcctFrom.getComponent());
		row.appendChild(lAcctTo.rightAlign());
		row.appendChild(fieldAcctTo.getComponent());
		
		row = rows.newRow();
		row.appendChild(lCube.rightAlign());
		row.appendChild(fReportCube.getComponent());
		row.appendChild(bRefresh);

		// Data Panel
		Center center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(miniTable);
		miniTable.setWidth("99%");
		miniTable.setHeight("99%");
		center.setFlex(true);
		center.setStyle("border: none");

		// Command Panel
		
		bExportExcel.setLabel("Export to Excel");
		bExportExcel.addActionListener(this);
		
		bCancel.addActionListener(this);
		
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(commandPanel);
		commandPanel.appendChild(commandLayout);
		
		commandPanel.appendChild(bExportExcel);
		commandPanel.appendChild(bCancel);
	}

	protected void dynInit() throws Exception
	{


		// Budget
		MLookup lookupBud = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), col_GL_Budget_ID,
				DisplayType.TableDir, Env.getLanguage(Env.getCtx()), "GL_Budget_ID", 0, false, null);
		fieldBudget = new WTableDirEditor("GL_Budget_ID", false, false, true, lookupBud);
		String sql = "SELECT GL_Budget_ID FROM GL_Budget WHERE IsPrimary='Y' AND BudgetStatus='A' AND AD_Client_ID = ?";
		int budget = DB.getSQLValueEx(null, sql, Env.getAD_Client_ID(Env.getCtx()));
		if ( budget > 0 )
			fieldBudget.setValue(budget);

	
		// Organization
		MLookupInfo info = MLookupFactory.getLookupInfo(Env.getCtx(), form.getWindowNo(), col_AD_Org_ID,
				DisplayType.Table, Env.getLanguage(Env.getCtx()), "AD_Org_ID", 322, false, null);
		MLookup lookupOrg = new MLookup(info, 0);
		fieldOrg = new WTableDirEditor("AD_Org_ID", false, false, true, lookupOrg);
		if (lookupOrg.containsKey(0))
			fieldOrg.setValue(0);
		else
			fieldOrg.setValue(Env.getAD_Org_ID(Env.getCtx()));

	

		// Period
		MLookup lookupPeriod = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), col_C_Period_ID,
				DisplayType.Table, Env.getLanguage(Env.getCtx()), "C_Period_ID", 275, false, null); 	
		fieldPeriod = new WTableDirEditor("C_Period_ID", true, false, true, lookupPeriod);
		
		java.util.Date date = new java.util.Date();
		MPeriod per = MPeriod.get(Env.getCtx(), new Timestamp(date.getTime()), Env.getAD_Org_ID(Env.getCtx()));
		if (per != null)
			fieldPeriod.setValue(per.get_ID());

		//Account From
		MLookup lookupAcct = MLookupFactory.get(Env.getCtx(),
						form.getWindowNo(),
						col_Account_ID,
						DisplayType.Table,
						Env.getLanguage(Env.getCtx()),
						"C_ElementValue_ID",
						362,
						false,
						"C_ElementValue.IsActive='Y' AND C_ElementValue.AD_Client_ID="+m_AD_Client_ID );
		
		fieldAcctFrom = new WTableDirEditor("Account_ID", false	, false, true, lookupAcct); 
		
		fieldAcctTo = new WTableDirEditor("Account_ID", false, false, true, lookupAcct); 
		
		
		
		MLookup lookupUser1 = MLookupFactory.get(Env.getCtx(),
						form.getWindowNo(),
						col_User1_ID,
						DisplayType.Table,
						Env.getLanguage(Env.getCtx()),
						"C_ElementValue_ID",
						ReferenceID_of_User1_ID,
						false,
						"C_ElementValue.IsActive='Y' AND C_ElementValue.AD_Client_ID="+m_AD_Client_ID );
		
		fUser1 = new WTableDirEditor("User1_ID", false	, false, true, lookupUser1); 
		
		// ReportCube
				
		MLookup lookupCube = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), col_PA_ReportCube_ID,
				DisplayType.TableDir, Env.getLanguage(Env.getCtx()), "PA_ReportCube_ID", 0, false, null);
		fReportCube = new WTableDirEditor("PA_ReportCube_ID", false, false, true, lookupCube);
		
		
		setOrgStyle();
		setPeriodToStyle();
	}

	private void loadTable()
	{

		miniTable.clear();
		m_AD_Org_ID = 0;
		m_AccountFrom_ID = 0;
		m_AccountTo_ID = 0;
		gl_Budget_ID = 0;
		c_PeriodTo_ID = 0;
		user1_ID=0;
		
		if (fieldOrg.getValue() != null)
			m_AD_Org_ID = (Integer) fieldOrg.getValue();

		if (fieldAcctFrom.getValue() != null)
			m_AccountFrom_ID = (Integer)fieldAcctFrom.getValue();

		if (fieldAcctTo.getValue() != null)
			m_AccountTo_ID = (Integer)fieldAcctTo.getValue();

		if (fieldBudget.getValue() != null)
			gl_Budget_ID = (Integer) fieldBudget.getValue();
		
		
		if (fUser1.getValue() != null)
			user1_ID = (Integer) fUser1.getValue();

		if (fieldPeriod.getValue() != null)
			c_PeriodTo_ID = (Integer) fieldPeriod.getValue();
		
		if (fReportCube.getValue() !=null)
			pa_ReportCube_ID = (Integer)fReportCube.getValue();

		//pa_ReportCube_ID = 1000000;

		MAcctSchema acctSchema = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()))[0];
		if (acctSchema == null)
			FDialog.error(form.getWindowNo(), "@No Acctschema@");
		MAcctSchemaElement acctElementOrg = acctSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_Organization);
		Boolean orgMandatory = acctElementOrg.isBalanced()?true:false;

		StringBuffer errorMessage = new StringBuffer();
		if (pa_ReportCube_ID == 0) {
			errorMessage.append("@PA_ReportCube_ID@");
		}
		if (c_PeriodTo_ID == 0) {
			if(errorMessage.length() > 0) {
				errorMessage.append(Env.NL);
			}
			errorMessage.append("@C_PeriodTo_ID@");
		}
		if (orgMandatory && m_AD_Org_ID == 0) {
			if(errorMessage.length() > 0) {
				errorMessage.append(Env.NL);
			}
			errorMessage.append("@AD_Org_ID@");
		}
		if(errorMessage.length() > 0) {
			FDialog.error(form.getWindowNo(), "FillMandatory", errorMessage.toString());
			return;
		}

		MReportCube cube = new MReportCube(Env.getCtx(), pa_ReportCube_ID, null);
		String result = cube.update(false, false);
		log.log(Level.FINE, result);

		// Set Model
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		getData(data, false);
		Vector<String> columnNames = getColumnNames();

		ListModelTable modelI = new ListModelTable(data);
		miniTable.setData(modelI, columnNames);
		miniTable.repaint();
		miniTable.setMultiSelection(true);
		setColumnClass(miniTable);
		miniTable.addActionListener(this);
		
		ListHead listHead = miniTable.getListHead();
		WListItemRenderer itemRenderer = (WListItemRenderer) ((WListbox) listHead.getParent()).getItemRenderer();
		ArrayList<ListHeader> m_headers = itemRenderer.getHeaders();
		
		for (int i = 0; i < miniTable.getColumnCount(); i++)
		{
			WTableColumn m_tableColumns = itemRenderer.getColumn(i);
			if (m_tableColumns.getColumnClass() == BigDecimal.class)
				m_headers.get(i).setAlign("right");
			m_headers.get(i).setSort("none");
		}
	}

	@Override
	public ADForm getForm()
	{
		return form;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (event.getTarget() == bRefresh)
		{
			loadTable();
			renderListBox();
			updateFooter();
		}
		else if( event.getName() == Events.ON_OPEN)
		{
			OpenEvent openEvt = (OpenEvent)event;
			WEditorPopupMenu popup = (WEditorPopupMenu)openEvt.getTarget();
			Component referencedComponent = openEvt.getReference();
			popup.setAttribute("ref", referencedComponent);
		}
		else if (event.getName() == Events.ON_CLICK)
		{
			if (event.getTarget() instanceof Menuitem)
			{
				Component menuItem = event.getTarget();
				Component popup = menuItem.getParent();
				Component referencedComponent = (Component) popup.getAttribute("ref");
				openReport(referencedComponent);
			}
			else if (event.getTarget().equals(bCancel))
				SessionManager.getAppDesktop().closeActiveWindow();
			else if (event.getTarget().equals(bExportExcel))
			{
				exportXLS();
			}
		}
		else if (event.getName() == Events.ON_SELECT)
		{
			updateFooter();
		}
	}

	private void updateFooter() {
		List<ListItem> items = miniTable.getItems();
		Iterator<ListItem> it = items.iterator();
		
		BigDecimal periodActual	= new BigDecimal(0.0);
		BigDecimal periodBudget = new BigDecimal(0.0);
		BigDecimal periodVariance = new BigDecimal(0.0);
		BigDecimal totalActual	= new BigDecimal(0.0);
		BigDecimal totalBudget	= new BigDecimal(0.0);
		BigDecimal totalVariance= new BigDecimal(0.0);
		
		boolean noneSelected = true;
		while(it.hasNext())
		{
			ListItem item = it.next();
			if (item.isSelected())
				noneSelected = false;
		}
		
		it = items.iterator();
		
		while(it.hasNext())
		{
			ListItem item = it.next();
			
			if ((noneSelected || item.isSelected()) && item.getChildren().size() > 1)
			{
				Listcell cell = (Listcell) item.getChildren().get(4);
				periodActual = new BigDecimal(cell.getValue().toString()).add(periodActual);

				cell = (Listcell) item.getChildren().get(5);
				periodBudget = new BigDecimal(cell.getValue().toString()).add(periodBudget);
				
				cell = (Listcell) item.getChildren().get(6);
				periodVariance = new BigDecimal(cell.getValue().toString()).add(periodVariance);
				
				cell = (Listcell) item.getChildren().get(7);
				totalActual = new BigDecimal(cell.getValue().toString()).add(totalActual);
				
				cell = (Listcell) item.getChildren().get(8);
				totalBudget = new BigDecimal(cell.getValue().toString()).add(totalBudget);
				
				cell = (Listcell) item.getChildren().get(9);
				totalVariance = new BigDecimal(cell.getValue().toString()).add(totalVariance);
			}
		}
		
		Listfoot listfoot = miniTable.getListfoot();
		
		Listfooter listfooter = (Listfooter) listfoot.getChildren().get(4);
		listfooter.setLabel(periodActual.toString().trim());
		
		listfooter = (Listfooter) listfoot.getChildren().get(5);
		listfooter.setLabel(periodBudget.toString().trim());
		
		listfooter = (Listfooter) listfoot.getChildren().get(6);
		listfooter.setLabel(periodVariance.toString());
		
		listfooter = (Listfooter) listfoot.getChildren().get(7);
		listfooter.setLabel(totalActual.toString());
		
		listfooter = (Listfooter) listfoot.getChildren().get(8);
		listfooter.setLabel(totalBudget.toString());
		
		listfooter = (Listfooter) listfoot.getChildren().get(9);
		listfooter.setLabel(totalVariance.toString());
	}

	private void openReport(Component comp)
	{

		int column = getColumnPosition(comp);
		int[] rows = miniTable.getSelectedIndices();
		int currentRow = getRowPosition(comp);
		StringBuffer selectedIdList = new StringBuffer();
		selectedIdList.append(((IDColumn) miniTable.getValueAt(currentRow, 0)).getRecord_ID()).append(",");
		for (int value : rows)
		{
			int id = ((IDColumn) miniTable.getValueAt(value, 0)).getRecord_ID();
			selectedIdList.append(id).append(",");
		}
		
//		if (rows.length > 0)
			executeTrialBalanceProcess(column, selectedIdList.toString().substring(0, selectedIdList.lastIndexOf(",")));
//		else
//			FDialog.info(0, null, Msg.getMsg(Env.getCtx(), "SelectExisting"));
	}

	WEditorPopupMenu	popup	= null;

	private void renderListBox()
	{
		miniTable.renderAll();
		List<ListItem> items = miniTable.getItems();
		Iterator<ListItem> it = items.iterator();
		popup = new WEditorPopupMenu(true, false, false);
		popup.addEventListener(Events.ON_OPEN, this);
		Menuitem menu = (Menuitem) popup.getChildren().get(0);
		menu.addEventListener(Events.ON_CLICK, this);
		boolean flag = true;
		boolean isAttached = false;
		while (it.hasNext())
		{
			ListItem item = it.next();
			if (item.getChildren().size() > 1)
			{

				Listcell cell1 = (Listcell) item.getChildren().get(4);
				Listcell cell2 = (Listcell) item.getChildren().get(5);
				Listcell cell3 = (Listcell) item.getChildren().get(7);
				Listcell cell4 = (Listcell) item.getChildren().get(8);
				cell1.setContext(popup.getId());
				cell2.setContext(popup.getId());
				cell3.setContext(popup.getId());
				cell4.setContext(popup.getId());
				
				if (flag)
				{
					cell1.setStyle("background-color:#F7F7F7;");
					cell2.setStyle("background-color:#F7F7F7;");
					cell3.setStyle("background-color:#F7F7F7;");
					cell4.setStyle("background-color:#F7F7F7;");
					flag = false;
				}
				else
				{
					cell1.setStyle("background-color:#E9E8E5;");
					cell2.setStyle("background-color:#E9E8E5;");
					cell3.setStyle("background-color:#E9E8E5;");
					cell4.setStyle("background-color:#E9E8E5;");
					flag = true;
				}
				
				if (!isAttached)
				{
					cell1.appendChild(popup);
					isAttached = true;
				}
			}
		}
		
		Listfoot listfoot = new Listfoot();
		listfoot.setHeight("25px");
		Listfooter listfooter = new Listfooter(""); // Select
		listfoot.appendChild(listfooter);
		
		listfooter = new Listfooter("Total:");	// Account No
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("");	// Account Name
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("");	// User List 1
		listfoot.appendChild(listfooter);
		
		listfooter = new Listfooter("0.0");	// Period Actual
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("0.0");	// Period Budget
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("0.0");	// Period Variance
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("0.0");	// Total Actual
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("0.0");	// Total Budget
		listfoot.appendChild(listfooter);
		listfooter = new Listfooter("0.0");	// Total Variance
		listfoot.appendChild(listfooter);
		
		miniTable.appendChild(listfoot);
	}

	protected int getColumnPosition(Component source)
	{
		Listcell cell;
		int col = -1;

		cell = findListcell(source);
		col = cell.getColumnIndex();

		return col;
	}

	private Listcell findListcell(Component source)
	{
		if (source instanceof Listcell)
			return (Listcell) source;
		Component c = source.getParent();
		while (c != null)
		{
			if (c instanceof Listcell)
				return (Listcell) c;
			c = c.getParent();
		}
		return null;
	}

	protected int getRowPosition(Component source)
	{
		Listcell cell;
		ListItem item;
		int row = -1;

		cell = findListcell(source);
		item = (ListItem) cell.getParent();
		row = item.getIndex();

		return row;
	}
	
	private void exportXLS()
	{
		if (miniTable.getListHead() == null)
			return;
		
		ListHead listHead = miniTable.getListHead();
		WListItemRenderer itemRenderer = (WListItemRenderer) ((WListbox) listHead.getParent()).getItemRenderer();
		
		try
		{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Trial Balance Drillable Report");
			HSSFRow row = sheet.createRow(0);
			int column = 0;
			for (String columnNames : getColumnNames())
			{
				row.createCell(column++).setCellValue(columnNames);
			}
			
			for (int i = 0; i < miniTable.getRowCount(); i++)
			{
				row = sheet.createRow(i + 1);
				ListItem item = miniTable.getItemAtIndex(i);
				
				for (int j = 0; j < miniTable.getColumnCount(); j++)
				{
					Listcell cell = (Listcell) item.getChildren().get(j);
					WTableColumn m_tableColumns = ((WTableColumn)itemRenderer.getColumn(j));
					
					if (m_tableColumns.getColumnClass() == BigDecimal.class)
						row.createCell(j).setCellValue(Double.parseDouble(cell.getValue().toString()));
					else
						row.createCell(j).setCellValue(cell.getValue().toString());
				}
			}
			
			File file = File.createTempFile("Export", ".xls");
			FileOutputStream out = new FileOutputStream(file);
			workbook.write(out);
			out.close();
			
			AMedia media = new AMedia(file.getName(), "xls", "application/vnd.ms-excel", file, true);
			Filedownload.save(media, file.getName());
		}
		catch (FileNotFoundException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, e.getLocalizedMessage());
		}
	}

	@Override
	public void valueChange(ValueChangeEvent evt)
	{
		if (evt.getSource() == fieldOrg)
		{
			setOrgStyle();
		}
		else if (evt.getSource() == fieldPeriod)
		{
			setPeriodToStyle();
		}
	}

	/**
	 * Set style on PeriodTo Label
	 */
	private void setPeriodToStyle()
	{
		if (fieldPeriod.getValue() == null)
			lPeriodTo.setStyle("color : red");
		else
			lPeriodTo.setStyle("color : black");
	}

	/**
	 * Set style on organization Label
	 */
	private void setOrgStyle()
	{MAcctSchema acctSchema = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()))[0];
		if (acctSchema == null)
		{
			StringBuffer errorMessage = new StringBuffer();
				errorMessage.append("@No Acctschema@");

				FDialog.error(form.getWindowNo(), "FillMandatory", Msg.parseTranslation(Env.getCtx(), errorMessage.toString()));

		}
		MAcctSchemaElement acctElementOrg = acctSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_Organization);
		Boolean orgMandatory = acctElementOrg.isBalanced()?true:false;


		if (orgMandatory)
			lOrg.setStyle("color : red");
		else
			lOrg.setStyle("color : black");
		lPeriodTo.setStyle("color:red");
		lCube.setStyle("color:red");
	}
}