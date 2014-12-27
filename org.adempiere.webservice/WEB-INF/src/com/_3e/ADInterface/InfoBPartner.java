package com._3e.ADInterface;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;


public class InfoBPartner implements ADInfo {

	private static String s_partnerFROM = "C_BPartner"
		+ " LEFT OUTER JOIN C_BPartner_Location l ON (C_BPartner.C_BPartner_ID=l.C_BPartner_ID AND l.IsActive='Y')"
		+ " LEFT OUTER JOIN AD_User c ON (C_BPartner.C_BPartner_ID=c.C_BPartner_ID AND (c.C_BPartner_Location_ID IS NULL OR c.C_BPartner_Location_ID=l.C_BPartner_Location_ID) AND c.IsActive='Y')" 
		+ " LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)";

	private DataRow params;
	private boolean m_isSOTrx = true;
	private String m_tableName = "C_BPartner";
	
	public InfoBPartner(DataRow dr, String whereClause) {
		params = dr;
		m_isSOTrx = true; // TODO
		
		StringBuffer where = new StringBuffer();
		where.append("C_BPartner.IsSummary='N' AND C_BPartner.IsActive='Y'");
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		
		prepareTable(s_partnerFROM, where.toString(), "C_BPartner.Value");
		
		setupParamsFromDataRow();
	}

	void setupParamsFromDataRow() {
		for (int i=0; i< params.sizeOfFieldArray(); i++) {
			DataField f = params.getFieldArray(i);
			System.out.println(f.getColumn() +" - '"+f.getVal()+"'");
			if ("value".equalsIgnoreCase(f.getColumn())) fieldValue = f.getVal();
			if ("name".equalsIgnoreCase(f.getColumn())) fieldName = f.getVal();
			if ("contact".equalsIgnoreCase(f.getColumn())) fieldContact = f.getVal();
			if ("checkCustomer".equalsIgnoreCase(f.getColumn())) checkCustomer = "Y".equalsIgnoreCase(f.getVal());
			if ("checkAND".equalsIgnoreCase(f.getColumn())) checkAND = "Y".equalsIgnoreCase(f.getVal());
		}
	}
	
	String fieldValue = "";
	String fieldName = "";
	String fieldContact = "";
	String fieldEMail = "";
	String fieldPhone = "";
	
	String fieldPostal = "";
	boolean checkAND = false;
	boolean checkCustomer = false;
	
	
	
	protected CLogger log = CLogger.getCLogger(getClass());
	
	
	
	public String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		//	=> Value
		String value = fieldValue.toUpperCase();
		if (!(value.equals("") || value.equals("%")))
			list.add ("UPPER(C_BPartner.Value) LIKE ?");
		//	=> Name
		String name = fieldName.toUpperCase();
		if (!(name.equals("") || name.equals("%")))
			list.add ("UPPER(C_BPartner.Name) LIKE ?");
		//	=> Contact
		String contact = fieldContact.toUpperCase();
		if (!(contact.equals("") || contact.equals("%")))
			list.add ("UPPER(c.Name) LIKE ?");
		//	=> EMail
		String email = fieldEMail.toUpperCase();
		if (!(email.equals("") || email.equals("%")))
			list.add ("UPPER(c.EMail) LIKE ?");
		//	=> Phone
		String phone = fieldPhone.toUpperCase();
		if (!(phone.equals("") || phone.equals("%")))
			list.add ("UPPER(c.Phone) LIKE ?");
		//	=> Postal
		String postal = fieldPostal.toUpperCase();
		if (!(postal.equals("") || postal.equals("%")))
			list.add ("UPPER(a.Postal) LIKE ?");

		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = checkAND;
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}

		//	Static SQL
		if (checkCustomer)
		{
			sql.append(" AND ");
			if (m_isSOTrx)
				sql.append ("C_BPartner.IsCustomer='Y'");
			else
				sql.append ("C_BPartner.IsVendor='Y'");
		}
		System.out.println( sql.toString() );
		return sql.toString();
	}	//	getSQLWhere
	
	
	
	public void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		//	=> Value
		String value = fieldValue.toUpperCase();
		if (!(value.equals("") || value.equals("%")))
		{
			if (!value.endsWith("%"))
				value += "%";
			pstmt.setString(index++, value);
			log.fine("Value: " + value);
		}
		//	=> Name
		String name = fieldName.toUpperCase();
		if (!(name.equals("") || name.equals("%")))
		{
			if (!name.endsWith("%"))
				name += "%";
			pstmt.setString(index++, name);
			log.fine("Name: " + name);
		}
		//	=> Contact
		String contact = fieldContact.toUpperCase();
		if (!(contact.equals("") || contact.equals("%")))
		{
			if (!contact.endsWith("%"))
				contact += "%";
			pstmt.setString(index++, contact);
			log.fine("Contact: " + contact);
		}
		//	=> EMail
		String email = fieldEMail.toUpperCase();
		if (!(email.equals("") || email.equals("%")))
		{
			if (!email.endsWith("%"))
				email += "%";
			pstmt.setString(index++, email);
			log.fine("EMail: " + email);
		}
		//	=> Phone
		String phone = fieldPhone.toUpperCase();
		if (!(phone.equals("") || phone.equals("%")))
		{
			if (!phone.endsWith("%"))
				phone += "%";
			pstmt.setString(index++, phone);
			log.fine("Phone: " + phone);
		}
		//	=> Postal
		String postal = fieldPostal.toUpperCase();
		if (!(postal.equals("") || postal.equals("%")))
		{
			if (!postal.endsWith("%"))
				postal += "%";
			pstmt.setString(index++, postal);
			log.fine("Postal: " + postal);
		}
	}   //  setParameters
		
	String m_sqlMain = "";
	String m_sqlCount = "";
	String m_sqlOrder = "";
	
	
	protected void prepareTable ( String from, String staticWhere, String orderBy)
	{
		
		StringBuffer sql = new StringBuffer ("SELECT ");
		//  add columns & sql
		sql.append( " C_BPartner.C_BPartner_ID, C_BPartner.Value, C_BPartner.Name, c.Name AS Contact,  " );
		sql.append( " C_BPartner.SO_CreditLimit-C_BPartner.SO_CreditUsed AS SO_CreditAvailable, ");
		sql.append( " C_BPartner.SO_CreditUsed, c.Phone, a.Postal, 	a.City, C_BPartner.TotalOpenBalance, C_BPartner.ActualLifetimeValue	");
		
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
	
	
	public DataRow getColumns() {
		DataRow col = DataRow.Factory.newInstance();
		DataField f = null;
		
		f = col.addNewField(); //0
		f.setType("int");
		f.setColumn("key" );
		f.setLval("20,0");
		
		f = col.addNewField(); //1
		f.setType("string");
		f.setColumn("value" );
		f.setLval("20,100");
		 
		f = col.addNewField(); //2
		f.setType("string");
		f.setColumn("name" );
		f.setLval("20, 140");		
		
		f = col.addNewField(); //3
		f.setType("string");
		f.setColumn("contact" );
		f.setLval("20, 70");

		f = col.addNewField(); //4
		f.setType("string");
		f.setColumn("SO_CreditAvailable" );
		f.setLval("20, 50");
		
		f = col.addNewField(); //5
		f.setType("string");
		f.setColumn("SO_CreditUsed" );
		f.setLval("20, 40");
		
		f = col.addNewField(); //6
		f.setType("string");
		f.setColumn("Phone" );
		f.setLval("20,50");
		
		f = col.addNewField(); //7
		f.setType("string");
		f.setColumn("Postal" );
		f.setLval("20, 40");
		
		f = col.addNewField(); //8
		f.setType("string");
		f.setColumn("City" );
		f.setLval("20, 50");
		
		f = col.addNewField(); //9
		f.setType("string");
		f.setColumn("TotalOpenBalance" );
		f.setLval("10,50");
		
		f = col.addNewField(); //10
		f.setType("string");
		f.setColumn("ActualLifetimeValue" );
		f.setLval("10,50");
		
		return col;
	}


	public String getSQL() {
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlMain);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		sql.append(m_sqlOrder);
		String dataSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		dataSql = MRole.getDefault().addAccessSQL(dataSql, m_tableName, 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		
		return dataSql;
	}

	public String getSQLCount() {
		String dynWhere = getSQLWhere();
		StringBuffer sql = new StringBuffer (m_sqlCount);
		if (dynWhere.length() > 0)
			sql.append(dynWhere);   //  includes first AND
		String countSql = Msg.parseTranslation(Env.getCtx(), sql.toString());	//	Variables
		countSql = MRole.getDefault().addAccessSQL(countSql, m_tableName, 
			MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		//log.finer(countSql);
		
		return countSql;

	}
	
}
