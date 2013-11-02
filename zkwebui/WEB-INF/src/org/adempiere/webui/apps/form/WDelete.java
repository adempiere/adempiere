package org.adempiere.webui.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.DeleteEntitiesModel;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;


public class WDelete implements IFormController,EventListener, ValueChangeListener{
	
	/**
	 * 
	 */
	private static CLogger log = CLogger.getCLogger(WDelete.class);
	private CustomForm form = new CustomForm();
	
	public WDelete()
	{
		Env.setContext(Env.getCtx(), form.getWindowNo(), "IsSOTrx", "Y");   //  defaults to no
		try
		{
			dynInit();
			zkInit();
		}
		catch(Exception e)
		{
			System.out.println(e);
			log.log(Level.SEVERE, "", e);
		}
	}
	
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Panel southPanel = new Panel();
	private Grid centerLayout = GridFactory.newGridLayout();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Grid southLayout = GridFactory.newGridLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Label clientLabel = new Label();
	private Label tableLabel = new Label();
	private WTableDirEditor tablePick = null;
	private Combobox clientPick = null;
	private int ad_table_id = 114 ;
	public Tree tree ;
	public Treecols treeCols;
	public Treecol treeCol;
	public Treecol treeCol2;
	public Checkbox dryRun ;
	public Treerow treeRow ;
	public Treecell treeCell ;
	Set<Treeitem> setOfItemSelected = null;
	List<Treeitem> prevSelectedCol = new ArrayList<Treeitem>();
	private Trx m_trx;
	private int m_totalCount;
	private Integer clientId;
	public HashMap<String, Integer> clientMap = new HashMap<String, Integer>();
	
	private void zkInit() throws Exception
	{
		//Form Init()
		form.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		clientLabel.setText(Msg.translate(Env.getCtx(), "AD_Client_ID"));
		tableLabel.setText(Msg.translate(Env.getCtx(), "AD_Table_ID"));
		dryRun = new Checkbox("Dry Run");
		dryRun.setChecked(true);
		
		parameterPanel.appendChild(parameterLayout);
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		Rows rows = null;
		Row row = null;
		parameterLayout.setWidth("100%");		
		rows = parameterLayout.newRows();
		row = rows.newRow();
		row.appendChild(clientLabel.rightAlign());
		row.appendChild(clientPick);
		row.appendChild(tableLabel.rightAlign());
		row.appendChild(tablePick.getComponent());
		row.appendChild(dryRun);
		
		centerPanel.appendChild(centerLayout);
		centerLayout.setWidth("100%");			
		Center center = new Center();
		mainLayout.appendChild(center);
		center.setStyle("border: none");
		center.appendChild(centerPanel);
		tree = new Tree();
		treeCols = new Treecols();
		treeCol = new Treecol("");
		treeCol2 = new Treecol();
		centerPanel.appendChild(tree);
		treeCols.appendChild(treeCol);
		treeCols.appendChild(treeCol2);
        tree.appendChild(treeCols); 		
		center.setFlex(true);
		center.setAutoscroll(true);
		
		South south = new South();
		south.appendChild(southPanel);
		southPanel.appendChild(southLayout);		
		southPanel.setWidth("100%");
		mainLayout.appendChild(south);
		Rows rows2 = southLayout.newRows();		
		Row south_row = rows2.newRow();		
		south_row.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);
		
		clientPick.addEventListener(Events.ON_SELECT, this);
		
	} 
	
	public void dynInit() throws Exception
	{
		
		// Client Pick
		String sql = "SELECT AD_Client_ID, Name FROM AD_Client WHERE AD_Client_ID <> 0";
		clientPick = new Combobox();

		PreparedStatement pstmt1 = DB.prepareStatement(sql, null);
		ResultSet rs1 = null;

		String clientName = null;
		Integer clientID = null;

		try 
		{
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) 
			{
				clientID = new Integer(rs1.getInt(1));
				clientName = new String(rs1.getString(2));
				clientPick.appendItem(clientName, clientID);
				clientMap.put(clientName, clientID);
			}

		} 
		catch (SQLException e) 
		{
			System.out.println(e);
		} 
		finally 
		{
			DB.close(rs1);
			DB.close(pstmt1);
		}

		// Table Pick
		MLookup lookupTable = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), 0, ad_table_id, DisplayType.TableDir);
		tablePick = new WTableDirEditor("AD_Table_ID", true, false, true, lookupTable);
		tablePick.setValue(new Integer((Integer) Env.getContextAsInt(Env.getCtx(), "$AD_Table_ID")));
		tablePick.addValueChangeListener(this);
	}   //  dynInit
	
	
	
	private void createNodes(DeleteEntitiesModel tableData, Treechildren ItemChildren) 
	{
		DeleteEntitiesModel currentNode = tableData;
		HashSet<String> tablesIgnored = new HashSet<String>(Arrays.asList(new String[] {
				"T_Report", "T_ReportStatement", "AD_Attribute_Value", "AD_PInstance_Log", "A_Valid_Asset_Combinations"
		}));

		if ( tablesIgnored.contains(currentNode.tableName) )
			return;

		String sql = "SELECT t.TableName, c.ColumnName, c.IsMandatory "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE t.IsView='N'"
			+ " AND c.ColumnName NOT IN ('CreatedBy', 'UpdatedBy') "
				+ " AND t.TableName NOT IN ('C_TaxDeclarationAcct',?)"     // not the same table
				+ " AND ("
				+ "(c.ColumnName=? AND c.IsKey='N' AND c.ColumnSQL IS NULL)"		//	#1 - direct
			+ " OR "
				+ "c.AD_Reference_Value_ID IN "				//	Table Reference
					+ "(SELECT rt.AD_Reference_ID FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Table tt ON (rt.AD_Table_ID=tt.AD_Table_ID)" +
							" WHERE tt.TableName = ? ) "	//	#2
			+ ") "
			+ "ORDER BY t.LoadSeq DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String keyCol = currentNode.tableName + "_ID";
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, currentNode.tableName);
			pstmt.setString(2, keyCol);
			pstmt.setString(3, currentNode.tableName);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				DeleteEntitiesModel data = new DeleteEntitiesModel();
				data.mandatoryLink = "Y".equals(rs.getString(3));
				data.tableName = rs.getString(1);
				data.joinColumn = rs.getString(2);
				data.whereClause = " EXISTS (SELECT 1 FROM " + currentNode.tableName 
						+ " WHERE " + currentNode.tableName + "." + currentNode.tableName + "_ID" // + currentNode.joinColumn 
						+ " = " + data.tableName + "." + data.joinColumn + " AND " + currentNode.whereClause + ") ";
				
				int count = data.getCount();

				if ( count > 0 )
				{						
						Treeitem treeitem = new Treeitem();
						ItemChildren.appendChild(treeitem);
			            treeitem.setLabel(data.tableName+"."+data.joinColumn);	
			            treeitem.setValue(data);			            
				}
				else
					log.log(Level.FINE, "No records:" + data.tableName);
			}
			
		} 
		catch (SQLException e) 
		{
			log.log(Level.INFO, sql);
			throw new AdempiereException("Couldn't load child tables", e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}		

		@SuppressWarnings("unchecked")
		Collection<Treeitem> collItemChild = (Collection<Treeitem>) ItemChildren.getItems();
		Iterator<Treeitem> it = collItemChild.iterator();
		
		while ( it.hasNext() )
		{
			Treeitem node = (Treeitem) it.next();
			Treeitem rootOfNode = node.getParentItem();
			if ( rootOfNode != null && rootOfNode.getParentItem() != null &&  rootOfNode.getParentItem().equals(node))
			{
				log.log(Level.WARNING, "Loop detected, escaping.");
				break;
			}
			else if ( ((DeleteEntitiesModel) node.getValue()).mandatoryLink )
			{	
				DeleteEntitiesModel itemTableData = (DeleteEntitiesModel) node.getValue();
				Treechildren nodeChild = new Treechildren();
				createNodes(itemTableData, nodeChild);
				
				if(nodeChild.getItemCount() != 0 )
				{
					node.appendChild(nodeChild);
				}
			}	
		}
	}	// createNodes(arg1, arg2)	

	@Override
	public void onEvent(Event e) throws Exception 
	{
		boolean commit = ! dryRun.isChecked();
		
		if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
		}
		
		else if (e.getTarget().equals(clientPick)) {
			 
			String clientIDStr = clientPick.getSelectedItem().getLabel();
			clientId = clientMap.get(clientIDStr);
			
			Object value=tablePick.getValue();
			generateTree(value,clientId);
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_OK))
		{
			//String clientIDStr = clientPick.getText();
			//Integer clientId = clientMap.get(clientIDStr);
			
			Object objTableID = tablePick.getValue();
			int tableId = 0;
			if ( objTableID != null )
				tableId = (Integer) objTableID;
			
			if (tableId == 0 || clientId == null)
			{
				FDialog.error(form.getWindowNo(), "Error", 
						"Select client and base table for cascade delete.");
			}
			else
			{
				m_totalCount = 0;
				m_trx = Trx.get(Trx.createTrxName("delete"), true);
				String errorMsg = "";
				try 
				{
					@SuppressWarnings("unchecked")
					Collection<Treeitem> items = tree.getItems();	
					Iterator<Treeitem> nodes = items.iterator();
					
					Stack<DeleteEntitiesModel> stack = new Stack<DeleteEntitiesModel>();
					
					while ( nodes.hasNext() )
					{
						stack.push((DeleteEntitiesModel) (((Treeitem) nodes.next()).getValue()));
					}
					
					while ( !stack.empty() )
					{
						DeleteEntitiesModel tableData = (DeleteEntitiesModel) stack.pop();
						m_totalCount += tableData.delete(m_trx);
					}
					if  ( commit )
						m_trx.commit(true);
					else
						m_trx.rollback(true);
				}
				catch (Exception ex) 
				{
					errorMsg = ex.getLocalizedMessage();
					log.log(Level.WARNING, "Cascade delete failed.", ex);
					m_totalCount = 0;
					m_trx.rollback();
					FDialog.error(form.getWindowNo(), "DeleteError", 
							errorMsg);
						return;
				}
				finally 
				{
					m_trx.close();
				}
				
			FDialog.info(form.getWindowNo(), form, "DeleteSuccess", "Records deleted:" + " #" + m_totalCount);
			dispose();
			
			}
		}		
	}	// onEvent
	
	/**
	 * Dispose
	 */
	
	public void dispose() {
		
		SessionManager.getAppDesktop().closeActiveWindow();
	} // dispose

	@Override
	public void valueChange(ValueChangeEvent e) 
	{
		log.info(e.getPropertyName() + "=" + e.getNewValue());
		
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		
		//String clientIDStr = clientPick.getText();
		//Integer clientId = clientMap.get(clientIDStr);
		
		log.config(name + "=" + value);
		
	/*	Integer selectedTableID ;		
		log.config(name + "=" + value);
		if (value == null)
			return;
		
		else if (name.equals("AD_Table_ID"))
		{
			selectedTableID = ((Integer) value).intValue();

			if (selectedTableID == 0 || clientId == null)
			{
				FDialog.error(form.getWindowNo(), "ParameterError", "Table or Client cannot be Null.");
				return;
			}
			
			MTable table = MTable.get(Env.getCtx(), selectedTableID);
			
			DeleteEntitiesModel data = new DeleteEntitiesModel();
			data.mandatoryLink = true;
			data.tableName = table.getTableName();
			data.joinColumn = table.getKeyColumns()[0];
			data.whereClause = " " + data.tableName + ".AD_Client_ID = " + clientId;
			if ( table.getTableName().equals("AD_User"))
			{
				data.whereClause = data.whereClause + 
				" AND NOT EXISTS (SELECT * FROM C_BPartner bp " +
				"WHERE AD_User.Link_BPartner_ID=bp.C_BPartner_ID " +
				"AND (bp.IsEmployee='Y' OR bp.IsSalesRep='Y'))";
			}
			
			tree.clear();
			if((tree.getChildren()).size() > 1)
			{
				@SuppressWarnings("rawtypes")
				List treePreviousChild = tree.getChildren();
				tree.removeChild((Treechildren) treePreviousChild.get(1));
			}
			
			Treechildren rootTreeChild = new Treechildren();				
			Treeitem rootTreeItem = new Treeitem();
			rootTreeItem.setValue(data);
			rootTreeItem.setLabel(data.tableName+"."+data.joinColumn);
			
			Treechildren rootTreeItemChild = new Treechildren();
			createNodes(data, rootTreeItemChild);
			
			rootTreeItem.appendChild(rootTreeItemChild);		
			rootTreeChild.appendChild(rootTreeItem);
			tree.appendChild(rootTreeChild);*/
		
		if (name.equals("AD_Table_ID")) {
			
			generateTree(value,clientId);
		}
	}	// ValueChange
	
	private void generateTree(Object value,Integer clientID) {
		
		if (value == null)
			return;
		
		Integer selectedTableID = ((Integer) value).intValue();
		
		if (selectedTableID == 0 || clientId == null) {
			FDialog.error(form.getWindowNo(), "ParameterError", "Table or Client cannot be Null.");
			return;
		}
		
		MTable table = MTable.get(Env.getCtx(), selectedTableID);
		
		DeleteEntitiesModel data = new DeleteEntitiesModel();
		data.mandatoryLink = true;
		data.tableName = table.getTableName();
		data.joinColumn = table.getKeyColumns()[0];
		data.whereClause = " " + data.tableName + ".AD_Client_ID = " + clientId;
		if ( table.getTableName().equals("AD_User")) {
			
			data.whereClause = data.whereClause + " AND NOT EXISTS (SELECT * FROM C_BPartner bp " 
								+ "WHERE AD_User.Link_BPartner_ID=bp.C_BPartner_ID " 
								+ "AND (bp.IsEmployee='Y' OR bp.IsSalesRep='Y'))";
		}
		
		tree.clear();
		if((tree.getChildren()).size() > 1) {
			
			@SuppressWarnings("rawtypes")
			List treePreviousChild = tree.getChildren();
			tree.removeChild((Treechildren) treePreviousChild.get(1));
		}
		
		Treechildren rootTreeChild = new Treechildren();				
		Treeitem rootTreeItem = new Treeitem();
		rootTreeItem.setValue(data);
		rootTreeItem.setLabel(data.tableName+"."+data.joinColumn);
	
		Treechildren rootTreeItemChild = new Treechildren();
		createNodes(data, rootTreeItemChild);
				
		rootTreeItem.appendChild(rootTreeItemChild);		
		rootTreeChild.appendChild(rootTreeItem);
		tree.appendChild(rootTreeChild);
	}
	
	public ADForm getForm()
	{
		return form;
	}

} 	// WDelete