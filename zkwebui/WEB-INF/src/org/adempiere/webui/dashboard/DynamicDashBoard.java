package org.adempiere.webui.dashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_AD_Browse;
import org.adempiere.core.domains.models.I_AD_Browse_Field;
import org.adempiere.core.domains.models.I_AD_Tab;
import org.adempiere.core.domains.models.I_AD_Table;
import org.adempiere.core.domains.models.I_AD_View_Column;
import org.adempiere.core.domains.models.I_AD_View_Definition;
import org.adempiere.core.domains.models.X_AD_Browse;
import org.adempiere.core.domains.models.X_AD_Browse_Field;
import org.adempiere.core.domains.models.X_AD_View_Definition;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.adempiere.model.MViewColumn;
import org.adempiere.model.MViewDefinition;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MQuery;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTab;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Vbox;

/**
 * 
 * @author Walking Tree <BR>
 ***************************         </BR>
 * 
 *         This class creates the Dynamic DashBoard based on
 *         configuration in <B>Dashboard Content Edit</B> window.
 * 
 *         <li>
 *         Provide required configuration values in <B>Dashboard Content Edit</B> window,
 *         like Smart Browse(which is used to create dash board based Smart Browse fields ),
 *              zoom window(which is selected to show zoom window), 
 *              zoom tab(which is selected to show as zoom window tab),
 *              zoom column ( record id in zoom tab ) ,
 *              onEvent(event value), 
 *              page size( number of rows for dash board page) 
 *         
 *         <li> This class handles events based on event configured in Edit Dash board Content window. 
 *         If event name not configured it will take double click as default event name.
 * 
 *         </li>
 */
public class DynamicDashBoard extends DashboardPanel implements EventListener 
{
	private static final long serialVersionUID = 1L;
	private static final CLogger logger = CLogger.getCLogger(DynamicDashBoard.class);
	private Properties ctx = Env.getCtx();
	private int pageSize = 0;
	private int zoomWindowId = 0;
	private int zoomTabId = 0;
	private int zoomFieldId = 0;
	private String event = null;
	private int browseId = 0;
	private MTable table =null;
	private MTab zoomTab = null;
	private MTable zoomTable = null;
	private String zoomTableName = "";
	private String zoomTableColumnName = "";

	Label lable[] = null;
	Column column[] = null;
	StringBuffer sqlQuery = new StringBuffer();
	Grid grid = new Grid();
	Vbox vbox = new Vbox();
	Boolean displayZoomCol = Boolean.FALSE ;

	public DynamicDashBoard() {
		super();
		// initialize the context values
		browseId = Env.getContextAsInt(ctx, "#AD_Browse_ID");
		pageSize = Env.getContextAsInt(ctx, "#PageSize");
		zoomWindowId = Env.getContextAsInt(ctx, "#Zoom_Window_ID");
		zoomTabId = Env.getContextAsInt(ctx, "#Zoom_Tab_ID");
		zoomFieldId = Env.getContextAsInt(ctx, "#Zoom_Field_ID");
		event = Env.getContext(ctx, "#OnEvent");
		createView();
	}

	/**
	 * createView creates the dynamic dash board view based on configuration
	 * values if configured, otherwise it creates dash board with default values
	 */
	private void createView() {
		Columns columns = new Columns();
		prepareSelectQuery();//prepares a select query. 

		int columnsSize = column.length;
		for (int i1 = 0; i1 < columnsSize; i1++) {
			if (column[i1] != null) {
				column[i1].setSort("auto");
				columns.appendChild(column[i1]);
			}

		}
		grid.appendChild(columns);
		columns.setSizable(true);
		vbox.appendChild(grid);

		//vbox.setStyle("overflow:auto");

		grid.appendChild(createRows());
		grid.setMold("paging");
		vbox.setVisible(Boolean.TRUE);
		this.appendChild(vbox);

		if (pageSize <= 0) {
			grid.setPageSize(MSysConfig.getIntValue( "DASHBOARD_PAGE_SIZE", 5));// default size
		} else {

			grid.setPageSize(pageSize);
		}
		//grid.renderAll();
	}

	/**
	 * 
	 * @return String<br>
	 * 
	 *         This method builds the sql query to generate Result set. It creates Columns and labels as well.
	 * 
	 * 
	 */
	public String prepareSelectQuery() {

		// Getting isDisplayed fields based on the Browse ID
		StringBuffer whereClause = new StringBuffer(X_AD_Browse.COLUMNNAME_AD_Browse_ID + " = " + browseId
				+ " AND isDisplayed = 'Y'");

		List<MBrowseField> fieldList = new Query(Env.getCtx(), I_AD_Browse_Field.Table_Name,
				whereClause.toString(), null)
		.setOnlyActiveRecords(true)
		.setOrderBy(X_AD_Browse_Field.COLUMNNAME_SeqNo)
		.list();



		if (fieldList != null && !fieldList.isEmpty())
		{
			lable    = new Label[fieldList.size()];
			column   = new Column[fieldList.size()];
			sqlQuery = sqlQuery.append(" SELECT ");


			// evaluating whether event is enabled

			if ( zoomWindowId > 0 && zoomTabId > 0 ) 
			{
				
				MBrowseField zoomField = new MBrowseField(Env.getCtx(), zoomFieldId, null) ; 
				
				if ( !fieldList.contains( zoomField ) ) 
				{ 
					StringBuffer columnWhereClause = new StringBuffer( "AD_View_Column_ID = " + 
							zoomField.getAD_View_Column_ID());
					MViewColumn viewColumn = new Query(Env.getCtx(),
							I_AD_View_Column.Table_Name, 
							columnWhereClause.toString(),
							null)
					.setOnlyActiveRecords(true)
					.first();
					sqlQuery = sqlQuery.append(viewColumn.getColumnSQL() + ", " );

				} 
				else 
				{
					displayZoomCol = Boolean.TRUE;
				}

				whereClause = new StringBuffer("AD_Tab_ID= " + zoomTabId);
				zoomTab = new Query(Env.getCtx(), I_AD_Tab.Table_Name, whereClause.toString(), null)
				.setOnlyActiveRecords(true)
				.first();

				if (zoomTab != null) {
					// Getting Zoom table based on the zoom tab
					whereClause = new StringBuffer("AD_Table_ID= " + zoomTab.getAD_Table_ID());
					zoomTable = new Query(Env.getCtx(), I_AD_Table.Table_Name, whereClause.toString(), null)
					.setOnlyActiveRecords(true)
					.first();

					if (zoomTable != null) {

						// table or view? based on suffix "_v". It assumed that
						// created view is having this suffix
						//@The View Name must be Actual Table Name and It must ends with one of above suffixes @
						//If this view combination of multiple tables, the view name must be prefix with any one of Joining Tables
						//And That table should have primary key::: Eg: AD_USer_v, here AD_User_ID is Primary key of AD_User

						zoomTableName = zoomTable.getTableName();
						if (zoomTable.isView()) {
							if (zoomTableName.endsWith("_vt")) {
								zoomTableColumnName = zoomTableName.replace("_vt", "_ID");
								sqlQuery = sqlQuery.append(zoomTableColumnName+", ");
							}
							// if view ends with _v only
							else if (zoomTableName.endsWith("_v")) {
								zoomTableColumnName = zoomTableName.replace("_v", "_ID");
								sqlQuery = sqlQuery.append(zoomTableColumnName+", ");
							}

						} else {
							zoomTableColumnName = zoomTable.getTableName()+ "_ID";
						}
					}
				}
			}
			int i = 0;
			for (MBrowseField field : fieldList) 
			{
				if (i != 0) {
					sqlQuery = sqlQuery.append(", ");
				}
				// creating column and preparing sql query with db column names
				column[i] = new Column(field.getName(), null, null);
				StringBuffer columnWhereClause = new StringBuffer(
						"AD_View_Column_ID = " + field.getAD_View_Column_ID());
				MViewColumn viewColumn = new Query(Env.getCtx(),
						I_AD_View_Column.Table_Name, 
						columnWhereClause.toString(),
						null)
				.setOnlyActiveRecords(true)
				.first();

				sqlQuery = sqlQuery.append(viewColumn.getColumnName().replaceFirst("_", ".") );
				i = i + 1;
			}
		}

		whereClause = new StringBuffer("AD_Browse_ID= " + browseId);
		MBrowse browse = new Query ( Env.getCtx() , I_AD_Browse.Table_Name, whereClause.toString(),null)
		.setOnlyActiveRecords(true)
		.first();

		if( browse != null ) {

			whereClause = new StringBuffer("AD_View_ID= "
					+ browse.getAD_View_ID());

			List<MViewDefinition> list = new Query ( Env.getCtx() , 
					I_AD_View_Definition.Table_Name , 
					whereClause.toString() , null )
			.setOnlyActiveRecords(true)
			.list();

			whereClause = new StringBuffer( X_AD_View_Definition.COLUMNNAME_AD_Table_ID + " = " + list.get(0).getAD_Table_ID());
			table = new Query ( Env.getCtx(), I_AD_Table.Table_Name ,whereClause.toString(), null)
			.setOnlyActiveRecords(true)
			.first();

			StringBuffer joinClause = new StringBuffer();

			for(MViewDefinition viewDefinition : list) {
				if ( viewDefinition.getJoinClause() == null )
					joinClause.append( " " + viewDefinition.getTableAlias() );
				else

					joinClause.append(" " + viewDefinition.getJoinClause());
			}

			// Associating sql query with table name 
			sqlQuery.append(" FROM " + table.getTableName() ).append( joinClause.toString());

			String where = browse.getWhereClause();//get where clause of this Smart Browse

			if (where != null) 
			{
				boolean success = true;
				
				do 
				{
					int index = where.indexOf("@");
					int index2 = where.indexOf("@", index + 1);
					boolean integer = false;
					int replacedValue = 0;
					String replacedString = null;

					if (index > 0 || index2 > 0) 
					{
						String subString1 = where.substring(index + 1, index2);
						if (subString1.contains("#")) 
						{
							if (subString1.endsWith("_ID")) 
							{
								replacedValue = Env.getContextAsInt( ctx, subString1 );
								integer = true;
							} 
							else 
							{
								replacedString = Env.getContext(ctx, subString1);
							}
							if (integer) 
							{
								where = where.replaceAll(
										where.substring(index, index2 + 1),
										String.valueOf(replacedValue));
							} 
							else 
							{
								where = where.replaceAll(
										where.substring(index, index2 + 1), "'"
										+ replacedString + "'");
							}
						} 
						else 
						{
							where = where.replaceAll(
									where.substring(index, index2 + 1),
									where.substring(index + 1, index2));
						}
						success = true;
						
					} else {
						success = false;
						
					}

				} while ( success );

				sqlQuery.append(" Where " + where);
			}
		}
		return sqlQuery.toString();
	}

	/**
	 * 
	 * @return Rows <BR>
	 * 
	 * 
	 *         This method creates the Rows
	 */
	public Rows createRows() {

		Vbox msgbox = new Vbox();
		Label rowItem = null;
		Rows rows = new Rows();
		ResultSet rs = null;

		if (rows.getChildren() != null) {
			List<Component> childs = rows.getChildren();
			for (Component cmp : childs) {
				rows.removeChild(cmp);
			}
		}

		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement(sqlQuery.toString(), null);
		try {
			rs = pstmt.executeQuery();

			try {
				if (rs.next()) {
					grid.setVisible(true);
					msgbox.setVisible(false);
					prepareRow(rs, lable, rows);
					while (rs.next()) {
						prepareRow(rs, lable, rows);
					}

				} else {
					vbox.removeChild(msgbox);
					grid.setVisible(false);
					msgbox.setVisible(true);

					if (msgbox != null && msgbox.getChildren().size() > 0) {
						msgbox.removeChild(rowItem);
					}
					grid.setVisible(false);
					msgbox.setVisible(true);
					String msg = Msg.getMsg(Env.getCtx(), "NO_DATA");
					rowItem = new Label(msg);

					rowItem.setParent(msgbox);
					msgbox.setParent(vbox);
				}

			} catch (SQLException e) {
				logger.log(Level.SEVERE, "SQL  faile");
			}

		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					e.toString()
					+ "  Check for configured Zoom Tab having Primary key of Table:"
					+ table.getTableName());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE, "Unable to close Resultset:"+e);
				}
			}
		}
		return rows;
	}

	/**
	 * 
	 * @param rs
	 * @param labelList
	 * @param rows
	 * <BR>
	 * 
	 * 
	 *            This method holding the logic to prepare Row based on
	 *            ResultSet.
	 * 
	 * 
	 */
	public void prepareRow(ResultSet rs, Label labelList[], Rows rows) {
		Row row = null;

		try {
			ResultSetMetaData rsm = rs.getMetaData();

			int count = rsm.getColumnCount();

			int i = 0;
			int a = 0;
			if (zoomWindowId > 0 && zoomTabId > 0 ) {
				if (displayZoomCol){
					i = 1;
					a = 1;
				}
				else
				{
					i = 2;	//Event enabled
					a = 2;	//To start creating labels from second column as first column is record ID. 
				}

			}
			else
			{
				i = 1;
				a = 1;
			}
			
			row = new Row();
			//create rows with data
			while (i <= count) {
				String name = rsm.getColumnTypeName(i);
				if (name.equalsIgnoreCase("varchar")) {
					if ( rs.getString(i) != null )
						labelList[i - a] = new Label(rs.getString(i));
					else
						labelList[i - a] = new Label(" ");	
				} else if (name.equalsIgnoreCase("numeric")) {
						if ( rs.getString(i) != null )
							labelList[i - a] = new Label(Integer.valueOf(rs.getInt(i)).toString());
						else
							labelList[i - a] = new Label(" ");
				} else if (name.equalsIgnoreCase("bpchar")) {
					if ( rs.getString(i) != null )
						labelList[i - a] = new Label(rs.getString(i));
					else
						labelList[i - a] = new Label(" ");
				}else if (name.equalsIgnoreCase("timestamptz")) {
					if (rs.getTimestamp(i) != null) {
						labelList[i - a] = new Label(rs.getTimestamp(i)
								.toString());

					} else {
						labelList[i - a] = new Label(" ");
					}
				}

				i = i + 1;

			}
			for (int j = 0; j < labelList.length; j++) {
				row.appendChild(labelList[j]);

			}

			if (zoomWindowId > 0 && zoomTabId > 0) {
				row.setId(String.valueOf(rs.getInt(zoomTableColumnName)));
				if (event == null || event.length() == 0) {
					event = Events.ON_DOUBLE_CLICK;//default event

				}
				row.addEventListener(event, this);

			}
			row.setParent(rows);

		} catch (Exception e) {
			logger.log(Level.WARNING, "Result set execution failed" + e);
		}

	}


	public void onEvent(Event event) throws Exception {
		Component comp = event.getTarget();
		Row row = (Row) comp;
		int recordId = Integer.valueOf(row.getId());
		MQuery query = new MQuery();
		query.setZoomValue(recordId);
		query.setZoomTableName(zoomTableName);
		query.setZoomColumnName(zoomTableColumnName);
		query.setRecordCount(1);
		query.addRestriction(zoomTableColumnName,MQuery.EQUAL, Integer.valueOf(recordId));
		AEnv.zoom(zoomWindowId, query);
	}


	public void refresh(ServerPushTemplate template) {
		if(true){
			template.execute(this);
		}
	}
	@SuppressWarnings("unchecked")
	public void updateUI() {
		Component c = (Component) this.getParent();
		updateView(); 
		if(this.getPreviousSibling() != null)
			this.getPreviousSibling().setParent(null);
	}
	private void updateView(){
		grid.getRows().setParent(null);
		grid.appendChild(createRows());
	}
}


