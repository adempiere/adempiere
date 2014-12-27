package com._3e.ADInterface;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;


public class InfoProduct  implements ADInfo {

	private static final String s_productFrom =
		"M_Product p"
		+ " LEFT OUTER JOIN M_ProductPrice pr ON (p.M_Product_ID=pr.M_Product_ID AND pr.IsActive='Y')"
		+ " LEFT OUTER JOIN M_AttributeSet pa ON (p.M_AttributeSet_ID=pa.M_AttributeSet_ID)";

	private DataRow params;
	private boolean m_isSOTrx = true;
	private String m_tableName = "M_Product";
	
	Properties m_ctx;
	int p_WindowNo;
	
	public InfoProduct(DataRow dr, String whereClause, Properties ctx, int WindowNo) {
		params = dr;
		m_ctx = ctx;
		p_WindowNo = WindowNo;
		
		this.getProductLayout();
		
		this.M_PriceList_ID = Env.getContextAsInt( m_ctx, "#M_PriceList_ID");
		
		M_PriceList_Version_ID = findPLV (M_PriceList_ID);
/*		//	Set Value or Name
		if (value.startsWith("@") && value.endsWith("@"))
			fieldName.setText(value.substring(1,value.length()-1));
		else
			fieldValue.setText(value);
		//	Set Warehouse
		if (M_Warehouse_ID == 0)
			M_Warehouse_ID = Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID");

		//fillPicks(M_PriceList_ID);
		int M_PriceList_Version_ID = 0; //findPLV (M_PriceList_ID);
		//	Set Value or Name
*/
		//	Set Warehouse // TODO
		if (M_Warehouse_ID == 0)
			M_Warehouse_ID = Env.getContextAsInt( m_ctx, "#M_Warehouse_ID");
		
		//if (M_Warehouse_ID != 0)
		//	setWarehouse (M_Warehouse_ID);
		// 	Set PriceList Version
		//if (M_PriceList_Version_ID != 0)
		//	setPriceListVersion (M_PriceList_Version_ID);

		//	Create Grid
		StringBuffer where = new StringBuffer();
		where.append("p.IsActive='Y'");
		if (M_Warehouse_ID != 0)
			where.append(" AND p.IsSummary='N'");
		//  dynamic Where Clause
		//if (p_whereClause != null && p_whereClause.length() > 0)
		//	where.append(" AND ")   //  replace fully qalified name with alias
		//		.append(Util.replace(p_whereClause, "M_Product.", "p."));
		//		
		
		prepareTable(s_productFrom, where.toString(), "QtyAvailable DESC, Margin DESC" );
		
		setupParamsFromDataRow();
	}

	void setupParamsFromDataRow() {
		for (int i=0; i< params.sizeOfFieldArray(); i++) {
			DataField f = params.getFieldArray(i);
			System.out.println(f.getColumn() +" - '"+f.getVal()+"'");
			if ("value".equalsIgnoreCase(f.getColumn())) fieldValue = f.getVal();
			if ("name".equalsIgnoreCase(f.getColumn())) fieldName = f.getVal();
			if ("name".equalsIgnoreCase(f.getColumn())) fieldName = f.getVal();
			if ("upc".equalsIgnoreCase(f.getColumn())) fieldUPC = f.getVal();
			if ("upc".equalsIgnoreCase(f.getColumn())) fieldUPC = f.getVal();
			if ("sku".equalsIgnoreCase(f.getColumn())) fieldSKU = f.getVal();
			if ("M_PriceList_Version_ID".equalsIgnoreCase(f.getColumn())) M_PriceList_Version_ID = Integer.parseInt(f.getVal());
			if ("M_Warehouse_ID".equalsIgnoreCase(f.getColumn())) M_Warehouse_ID = Integer.parseInt(f.getVal());
		}
	}
	
		
	protected CLogger log = CLogger.getCLogger(getClass());

	String m_pAttributeWhere = null;
	String fieldValue ="";
	String fieldName ="";
	String fieldUPC ="";
	String fieldSKU ="";
	//int M_PriceList_Version_ID = 1000037; //104;
	//int M_Warehouse_ID = 1000001; //103;
	//int M_PriceList_ID = 1000022; //101
	int M_PriceList_Version_ID = 104;
	int M_Warehouse_ID = 103;
	int M_PriceList_ID = 101;
	
	String getSQLWhere() {
		StringBuffer where = new StringBuffer();
		
		//	Optional PLV
		//int M_PriceList_Version_ID = 0;
		if (M_PriceList_Version_ID != 0)
			where.append(" AND pr.M_PriceList_Version_ID=?");
		
		//	Product Attribute Search
		if (m_pAttributeWhere != null)
		{
			where.append(m_pAttributeWhere);
			return where.toString();
		}

		//  => Value
		String value = fieldValue.toUpperCase();
		if (!(value.equals("") || value.equals("%"))) {
			where.append(" AND UPPER(p.Value) LIKE ?");
		}

		//  => Name
		String name = fieldName.toUpperCase();
		if (!(name.equals("") || name.equals("%")))
			where.append(" AND UPPER(p.Name) LIKE ?");

		//  => UPC
		String upc = fieldUPC.toUpperCase();
		if (!(upc.equals("") || upc.equals("%")))
			where.append(" AND UPPER(p.UPC) LIKE ?");

		//  => SKU
		String sku = fieldSKU.toUpperCase();
		if (!(sku.equals("") || sku.equals("%")))
			where.append(" AND UPPER(p.SKU) LIKE ?");

		return where.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)
	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
	 * @throws SQLException
	 */
	public void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;

		//M_Warehouse_ID = 103;
		//this.M_PriceList_Version_ID = 104;
		//this.M_PriceList_ID = 101;
		
		//fieldValue = "%tree%";
		
		//  => Warehouse
		//int M_Warehouse_ID = 0;
		if (!forCount)	//	parameters in select
		{
			for (int i = 0; i < s_productLayout.length; i++)
			{
				if (s_productLayout[i].getColSQL().indexOf("?") != -1)
					pstmt.setInt(index++, M_Warehouse_ID);
			}
		}
		log.fine("M_Warehouse_ID=" + M_Warehouse_ID + " (" + (index-1) + "*)");

		//  => PriceList
		//int M_PriceList_Version_ID = 0;

		if (M_PriceList_Version_ID != 0)
		{
			pstmt.setInt(index++, M_PriceList_Version_ID);
			log.fine("M_PriceList_Version_ID=" + M_PriceList_Version_ID);
		}
		//	Rest of Parameter in Query for Attribute Search
		if (m_pAttributeWhere != null)
			return;

		//  => Value
		String value = fieldValue.toUpperCase();
		if (!(value.equals("") || value.equals("%")))
		{
			if (!value.endsWith("%"))
				value += "%";
			pstmt.setString(index++, value);
			log.fine("Value: " + value);
		}

		//  => Name
		String name = fieldName.toUpperCase();
		if (!(name.equals("") || name.equals("%")))
		{
			if (!name.endsWith("%"))
				name += "%";
			pstmt.setString(index++, name);
			log.fine("Name: " + name);
		}

		//  => UPC
		String upc = fieldUPC.toUpperCase();
		if (!(upc.equals("") || upc.equals("%")))
		{
			if (!upc.endsWith("%"))
				upc += "%";
			pstmt.setString(index++, upc);
			log.fine("UPC: " + upc);
		}

		//  => SKU
		String sku = fieldSKU.toUpperCase();
		if (!(sku.equals("") || sku.equals("%")))
		{
			if (!sku.endsWith("%"))
				sku += "%";
			pstmt.setString(index++, sku);
			log.fine("SKU: " + sku);
		}

	}   //  setParameters

		
	String m_sqlMain = "";
	String m_sqlCount = "";
	String m_sqlOrder = "";
	
	int m_keyColumnIndex = -1;
	
	protected void prepareTable ( String from, String staticWhere, String orderBy)
	{
		
		StringBuffer sql = new StringBuffer ("SELECT ");
		
		for (int i = 0; i < s_productLayout.length; i++)
		{
			if (i > 0)
				sql.append(", ");
			sql.append(s_productLayout[i].getColSQL());
			//  adding ID column
			if (s_productLayout[i].isIDcol())
				sql.append(",").append(s_productLayout[i].getIDcolSQL());
			//  add to model
			//p_table.addColumn(s_productLayout[i].getColHeader());
			//if (s_productLayout[i].isColorColumn())
				//p_table.setColorColumn(i);
			if (s_productLayout[i].getColClass() == IDColumn.class)
				m_keyColumnIndex = i;
		}

		sql.append( " FROM ").append(from);
		//
		sql.append(" WHERE ").append(staticWhere);
		m_sqlMain = sql.toString();
		m_sqlCount = "SELECT COUNT(*) FROM " + from + " WHERE " + staticWhere;
		//
		m_sqlOrder = "";
		if (orderBy != null && orderBy.length() > 0)
			m_sqlOrder = " ORDER BY " + orderBy;

		//if (m_keyColumnIndex == -1)
		//	log.log(Level.SEVERE, "No KeyColumn - " + sql);
		
	}   //  prepareTable
	
	
	Info_Column[] s_productLayout = null;
	
	private Info_Column[] getProductLayout() {
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "p.M_Product_ID", IDColumn.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "bomQtyAvailable(p.M_Product_ID,?,0) AS QtyAvailable", Double.class, true, true, null));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceList"), "bomPriceList(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceList",  BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceStd"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceStd", BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOnHand"), "bomQtyOnHand(p.M_Product_ID,?,0) AS QtyOnHand", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class));
		/*if (isUnconfirmed())
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmed"), "(SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) WHERE c.Processed='N' AND i.M_Warehouse_ID=? AND il.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmed", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmedMove"), "(SELECT SUM(c.TargetQty) FROM M_MovementLineConfirm c INNER JOIN M_MovementLine ml ON (c.M_MovementLine_ID=ml.M_MovementLine_ID) INNER JOIN M_Locator l ON (ml.M_LocatorTo_ID=l.M_Locator_ID) WHERE c.Processed='N' AND l.M_Warehouse_ID=? AND ml.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmedMove", Double.class));
		}*/
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Margin"), "bomPriceStd(p.M_Product_ID, pr.M_PriceList_Version_ID)-bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS Margin", BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "PriceLimit"), "bomPriceLimit(p.M_Product_ID, pr.M_PriceList_Version_ID) AS PriceLimit", BigDecimal.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class));
		s_productLayout = new Info_Column[list.size()];
		list.toArray(s_productLayout);
		//INDEX_NAME = 3;
		//INDEX_PATTRIBUTE = s_productLayout.length - 1;	//	last item	
		return s_productLayout;

	}
	
	public DataRow getColumns() {
		DataRow col = DataRow.Factory.newInstance();
		DataField f = null;
		f = col.addNewField(); //0
		f.setType("int");
		f.setLval("20, 0");
		f.setColumn("key" );
		
		f = col.addNewField(); //1
		f.setType("string");
		f.setLval("1, 15");
		f.setColumn("Discontinued" );
		
		f = col.addNewField(); //2
		f.setType("string");
		f.setLval("40, 110");
		f.setColumn("Value" );
		
		f = col.addNewField(); //3
		f.setType("string");
		f.setLval("60, 140");
		f.setColumn("Name" );

		f = col.addNewField(); //4
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("QtyAvailable" );
		
		f = col.addNewField(); //5
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("PriceList" );
		
		f = col.addNewField(); //6
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("PriceStd" );
		
		f = col.addNewField(); //7
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("QtyOnHand" );
		
		f = col.addNewField(); //8
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("QtyReserved" );
		
		f = col.addNewField(); //9
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("QtyOrdered" );
		
		f = col.addNewField(); //10
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("Margin" );
		
		f = col.addNewField(); //10
		f.setType("string");
		f.setLval("10, 50");
		f.setColumn("PriceLimit" );
		
		f = col.addNewField(); //10
		f.setType("string");
		f.setLval("1, 20");
		f.setColumn("IsInstanceAttribute" );
		
		
		return col;
	}


	public String getSQL() {
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		
		sql.append(m_sqlOrder);
				
		System.out.println( "GetSQL = "+sql.toString());
		String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		dataSql = MRole.getDefault().addAccessSQL(dataSql, "p", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		
		return dataSql;
	}
	
	public String getSQLCount() {
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		
		String countSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		countSql = MRole.getDefault().addAccessSQL(countSql, "p", 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		//log.finer(countSql);
		 
		
		return countSql;

	}
	
	/**
	 *	Find Price List Version and update context
	 *
	 * @param M_PriceList_ID price list
	 * @return M_PriceList_Version_ID price list version
	 */
	private int findPLV (int M_PriceList_ID)
	{
		Timestamp priceDate = null;
		//	Sales Order Date
		String dateStr = Env.getContext(m_ctx, p_WindowNo, "DateOrdered"); 
		if (dateStr != null && dateStr.length() > 0)
			priceDate = Env.getContextAsDate( m_ctx, p_WindowNo, "DateOrdered");
		else	//	Invoice Date
		{
			dateStr = Env.getContext(m_ctx, p_WindowNo, "DateInvoiced");
			if (dateStr != null && dateStr.length() > 0)
				priceDate = Env.getContextAsDate(m_ctx, p_WindowNo, "DateInvoiced");
		}
		//	Today
		if (priceDate == null) 
			priceDate = new Timestamp(System.currentTimeMillis());
		//
		log.config("M_PriceList_ID=" + M_PriceList_ID + " - " + priceDate);
		int retValue = 0;
		String sql = "SELECT plv.M_PriceList_Version_ID, plv.ValidFrom "
			+ "FROM M_PriceList pl, M_PriceList_Version plv "
			+ "WHERE pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND plv.IsActive='Y'"
			+ " AND pl.M_PriceList_ID=? "					//	1
			+ "ORDER BY plv.ValidFrom DESC";
		//	find newest one
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next() && retValue == 0)
			{
				Timestamp plDate = rs.getTimestamp(2);
				if (!priceDate.before(plDate))
					retValue = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		Env.setContext(m_ctx, p_WindowNo, "M_PriceList_Version_ID", retValue);
		return retValue;
	}	//	findPLV

	
}
