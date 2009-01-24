package com._3e.ADInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MLookup;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.ValueNamePair;
import org.compiere.util.WebSessionCtx;

import pl.x3E.adInterface.DataField;
import pl.x3E.adInterface.DataRow;
import pl.x3E.adInterface.DataSet;
import pl.x3E.adInterface.LookupValue;
import pl.x3E.adInterface.LookupValues;

/*
 * ADEMPIERE/COMPIERE
 * 
 * zamiana:
 *   GridField na GridField
 *   GridField
 */

// TODO: Implement a better replacement for ROWNUM / LIMIT syntax

public class ADLookup {


	static final int TYPE_PRODUCT = 001;
	static final int TYPE_ORDER = 002;
	static final int TYPE_BUISNESS_PARTNER = 002;
	
	private String m_columnName;
	private int m_type;
	private String m_tableName;
	private String m_keyColumnName;
	private Boolean IsQueryJoin;
	private boolean m_isSOTRX;
	
	private final int MAX_PRODUCT_ROWS=500;
	
	private WebSessionCtx wsc;
	private Lookup m_lookup;
	
	protected static CLogger	log = CLogger.getCLogger(ADLookup.class);	
	
	
	public ADLookup( String columnName )
	{
		m_columnName = columnName;
		
	}
	
	
	public ArrayList GetLookupData()
	{
		
		return null;
	}

	
	
	private String getWhereClause( String keyColumn, DataRow params ) {
		String whereClause = "IsSummary='N'";
		String lookupColumn = keyColumn;
		
		return whereClause;
	}
	
	private DataSet getResult( com._3e.ADInterface.ADInfo info, DataSet ds, int count, String mode ) 	{ 
	 if ("count".equals(mode) && count > 1) {
		 DataRow dr = ds.addNewDataRow();
		 DataField df = dr.addNewField();
		 df.setVal( ""+count );						
		 df.setColumn( "__count" );
		 df.setType( "string" );
		 return ds;
	 }
	 
	 try  {
		 
		PreparedStatement pstmt = DB.prepareStatement( info.getSQL() );
		info.setParameters( pstmt, false );
		ResultSet rs = pstmt.executeQuery();
		while (rs.next())
		{
			DataRow dr = ds.addNewDataRow();
			DataField df = null;
			//DataField df = dr.addNewField();
			
			DataRow cols = info.getColumns();
			
			for (int i=0; i< cols.sizeOfFieldArray(); i++ ) {
				df = dr.addNewField();
				DataField f = cols.getFieldArray(i);
				//if ("string".equalsIgnoreCase(f.getType())) {
				{
					df.setVal(rs.getString( i + 1 ));						
					df.setColumn( f.getColumn() );
					df.setType( f.getType() );
					df.setLval( f.getLval() );
				}
			}
						
		}		
		rs.close();
		pstmt.close();		
	 }
	 catch (Exception e)
	 {
		System.out.println( e.getMessage() ); 
	 }
	 return ds;
	}

	

		
	public void getLookupSearchValues( DataRow adr, DataSet ds, Properties ctx, int windowNo )
	{	
		//String whereClause = getWhere(adr);		
		String whereClause = getWhereClause(m_columnName, adr);
		String finalSQL="";		
		
		IsQueryJoin = false;
		
		String mode = "normal"; 
		for (int i=0; i< adr.sizeOfFieldArray(); i++) {
			DataField f = adr.getFieldArray(i);
			if ("mode".equalsIgnoreCase(f.getColumn())) mode = f.getVal();
		}

		
		ADInfo info = null;
		
		// TODO: Implement info for generic lookups !!
		
		if (m_columnName.equals("C_BPartner_ID"))
		{				
		 //finalSQL = getBuisnessPartnerSQL(whereClause, adr);
			info = new InfoBPartner( adr, whereClause );
		} else if (m_columnName.equals("M_Product_ID"))
		{			
			info = new InfoProduct( adr, whereClause, ctx, windowNo );
		 //finalSQL = getProductSQL(whereClause);	
		}else if (m_columnName.equals("C_Order_ID")) {
		  //finalSQL = getOrderAccessSQL(whereClause);
		 } 
		 if (finalSQL.equals("")) {
		 //finalSQL = getDirectAccessSQL(whereClause);
		 }
		
		
		//Sprawdzam czy przy wykonaniu FinalSql zwr�ci mi 0, 1 lub wiele wierszy
		//Je�eli zwr�ci mi 0 wierszy to ko�cz�
		//Je�eli zwr�ci mi 1 wiersz to ko�cowy wynik b�dzie kwerend� normaln� (w przypadku join�w, np.: bp zostanie wykonana alternatywna kwerenda bez join�w)
		//Je�eli zwr�ci mi n wierszy to zwracam je
		int id = 0;
		log.info("Starting execution to base");
		try
		{			
			/*
			PreparedStatement pstmt = DB.prepareStatement( finalSQL );
			info.setParameters( pstmt, true);
			ResultSet rs = pstmt.executeQuery();
			//Wykonuje zapytanie do bazy z "normalnym" sql i sprawdzam ile, mam wierszy wyniku
			int ile = 0;
			while (rs.next())
			{
			 ile = ile+1;				
			}*/
			int ile = 0;
			finalSQL = info.getSQLCount();
			PreparedStatement pstmt = DB.prepareStatement(finalSQL, null);
			info.setParameters (pstmt, true);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				ile = rs.getInt(1);
			rs.close();
			pstmt.close();

			System.out.println("Znalaz�em " + ile + " wierszy / rekord�w");
			//Je�eli jest 0 wierszy to ko�czymy
			//w kliencie spowoduje to wyswietlenie pustego okna lookUp
			if (ile==0)
			{
			 System.out.println("Nie znalaz�em rekord�w. W kliencie zostanie wy�wietlone okno LookUp'a bez rekord�w.");	
			}
			//Je�eli jest tylko jeden wiersz to odpowiednie tabele b�d� wywo�ywane bez
			//join�w
			if ((ile==1))
			{
			 System.out.println("Znalaz�em 1 rekord wi�c szukam dla bez join. W kliencie zostanie automatycznie uzupe�niona warto�c");
			 IsQueryJoin = false;				 			 
			}
			//Jesli wiecej niz jeden to uzywamy join�w
			//Spowoduje to wyswietlenie rekord�w spe�niajacych kryterium
			//w oknie LookUp'a
			if (ile>1)
			{
			 System.out.println("Znalaz�em wi�cej ni� 1 rekord wi�c szukam dla whereClause i z joinami. W kliencie zostanie wy�wietlone LookUpWindow z przefiltrowanymi rekordami.");
			 IsQueryJoin = true;		 			 

			}	
			ds = getResult(info, ds, ile, mode);			
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, finalSQL, e);
			id = -2;
		}
		//	No (unique) result
		if (id <= 0)
		{
			if (id == 0)
				log.fine("Not Found - " + finalSQL);
			else
				log.fine("Not Unique - " + finalSQL);
			//m_value = null;	// force re-display
			//actionButton(m_text.getText());
			//return r;
		}
		log.fine("Unique ID=" + id);

	}	//	actionText	
	
	/**
	 * 	Generate Access SQL for Search.
	 * 	The SQL returns the ID of the value entered
	 * 	Also sets m_tableName and m_keyColumnName
	 *	@param text uppercase text for LIKE comparison
	 *	@return sql or ""
	 *  Example
	 *	SELECT C_Payment_ID FROM C_Payment WHERE UPPER(DocumentNo) LIKE x OR ...
	 */
	private String getDirectAccessSQL (String text)
	{
		//Tutaj trzeba doda� dodatkowe pole dla odpowiednich typ�w wyszukiwania w selectach
		StringBuffer sql = new StringBuffer();
		m_tableName = m_columnName.substring(0, m_columnName.length()-3);  // without _ID
		m_keyColumnName = m_columnName;
		if (m_columnName.equals("C_Invoice_ID"))
		{
			sql.append("SELECT C_Invoice_ID, DocumentNo FROM C_Invoice WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("M_InOut_ID"))
		{
			sql.append("SELECT M_InOut_ID, '' FROM M_InOut WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("C_Payment_ID"))
		{
			sql.append("SELECT C_Payment_ID, DocumentNo FROM C_Payment WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("GL_JournalBatch_ID"))
		{
			sql.append("SELECT GL_JournalBatch_ID, '' FROM GL_JournalBatch WHERE UPPER(DocumentNo) LIKE ")
				.append(DB.TO_STRING(text));
		}
		else if (m_columnName.equals("SalesRep_ID"))
		{
			sql.append("SELECT AD_User_ID, NAME FROM AD_User WHERE UPPER(Name) LIKE ")
				.append(DB.TO_STRING(text));
			m_tableName = "AD_User";
			m_keyColumnName = "AD_User_ID";
		}

		//	Predefined
		if (sql.length() > 0)
		{
			String wc = getWhereClause();
			if (wc != null && wc.length() > 0)
				sql.append(" AND ").append(wc);
			sql.append(" AND IsActive='Y'");
			
			if (DB.isOracle())
				sql.append(" AND ROWNUM < "+MAX_PRODUCT_ROWS+" "); 
			//	***
			//log.finest("(predefined) " + sql.toString());
			
			String sqlret = MRole.getDefault().addAccessSQL(sql.toString(),
					m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
			
			if (DB.isPostgreSQL())
				sqlret = sqlret + " LIMIT "+MAX_PRODUCT_ROWS; 

			return sqlret;
		}
		
		//	Check if it is a Table Reference
		// TODO  dorobic obsluge where z ad_ref_table !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (m_lookup != null && m_lookup instanceof MLookup)
		{
			int AD_Reference_ID = ((MLookup)m_lookup).getAD_Reference_Value_ID();
			if (AD_Reference_ID != 0)
			{
				String query = "SELECT kc.ColumnName, dc.ColumnName, t.TableName "
					+ "FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Column kc ON (rt.AD_Key=kc.AD_Column_ID)"
					+ " INNER JOIN AD_Column dc ON (rt.AD_Display=dc.AD_Column_ID)"
					+ " INNER JOIN AD_Table t ON (rt.AD_Table_ID=t.AD_Table_ID) "
					+ "WHERE rt.AD_Reference_ID=?";
				String displayColumnName = null;
				PreparedStatement pstmt = null;
				try
				{
					pstmt = DB.prepareStatement(query);
					pstmt.setInt(1, AD_Reference_ID);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next())
					{
						m_keyColumnName = rs.getString(1);
						displayColumnName = rs.getString(2);
						m_tableName = rs.getString(3);
					}
					rs.close();
					pstmt.close();
					pstmt = null;
				}
				catch (Exception e)
				{
					//log.log(Level.SEVERE, "getDirectAccessSQL", e);
				}
				try
				{
					if (pstmt != null)
						pstmt.close();
					pstmt = null;
				}
				catch (Exception e)
				{
					pstmt = null;
				}
				if (displayColumnName != null)
				{
					sql = new StringBuffer();
					sql.append("SELECT ").append(m_keyColumnName).append(", "+displayColumnName) // kolec  - bylo m_displayColumnName
						.append(" FROM ").append(m_tableName)
						.append(" WHERE UPPER(").append(displayColumnName)
						.append(") LIKE ").append(DB.TO_STRING(text))
						.append(" AND IsActive='Y'");
					
					if (DB.isOracle())
						sql.append(" AND ROWNUM < "+MAX_PRODUCT_ROWS+" ");
					String wc = getWhereClause();
					if (wc != null && wc.length() > 0)
						sql.append(" AND ").append(wc);
					//	***
					//log.finest("(Table) " + sql.toString());
					
					String sqlret = MRole.getDefault().addAccessSQL(sql.toString(),
							m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
					
					if (DB.isPostgreSQL())
						sqlret = sqlret + " LIMIT "+MAX_PRODUCT_ROWS; 

					return sqlret;
				}
			}	//	Table Reference
		}	//	MLookup
		
		/** Check Well Known Columns of Table - assumes TableDir	**/
		String query = "SELECT t.TableName, c.ColumnName "
			+ "FROM AD_Column c "
			+ " INNER JOIN AD_Table t ON (c.AD_Table_ID=t.AD_Table_ID AND t.IsView='N') "
			+ "WHERE (c.ColumnName IN ('DocumentNo', 'Value', 'Name') OR c.IsIdentifier='Y')"
			+ " AND c.AD_Reference_ID IN (10,14)"
			+ " AND EXISTS (SELECT * FROM AD_Column cc WHERE cc.AD_Table_ID=t.AD_Table_ID"
				+ " AND cc.IsKey='Y' AND cc.ColumnName=?)";
		m_keyColumnName = m_columnName;
		sql = new StringBuffer();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(query);
			pstmt.setString(1, m_keyColumnName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				if (sql.length() != 0)
					sql.append(" OR ");
				m_tableName = rs.getString(1);
				sql.append("UPPER(").append(rs.getString(2)).append(") LIKE ").append(DB.TO_STRING(text));
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (SQLException ex)
		{
			log.log(Level.SEVERE, "getDirectAccessSQL", ex);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (SQLException ex1)
		{
		}
		pstmt = null;
		//
		if (sql.length() == 0)
		{
			log.log(Level.SEVERE, "(TableDir) - no standard/identifier columns");
			return "";
		}
		//
		StringBuffer retValue = new StringBuffer ("SELECT ")
			.append(m_columnName).append(" , NAME").append(" FROM ").append(m_tableName)
			.append(" WHERE ").append(sql)
			.append(" AND IsActive='Y' ");
		
		if (DB.isOracle())
			retValue.append(" AND ROWNUM < "+MAX_PRODUCT_ROWS+" ");
		String wc = getWhereClause();
		if (wc != null && wc.length() > 0)
			retValue.append(" AND ").append(wc);
		//	***
		log.finest("(TableDir) " + sql.toString());
		
		String sqlret = MRole.getDefault().addAccessSQL(retValue.toString(),
				m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		
		if (DB.isPostgreSQL())
			sqlret = sqlret + " LIMIT "+MAX_PRODUCT_ROWS; 

		return sqlret;
	}	//	getDirectAccessSQL

	/**
	 * 	Get Where Clause
	 *	@return where clause or ""
	 */
	private String getWhereClause()
	{
		String whereClause = "";
		if (m_lookup == null)
			return "";
		if (m_lookup.getZoomQuery() != null)
			whereClause = m_lookup.getZoomQuery().getWhereClause();
		String validation = m_lookup.getValidation();
		if (whereClause.length() == 0)
			whereClause = validation;
		else
			if (validation.length()>0)				// kolec 
			  whereClause += " AND " + validation;
	//	log.finest("ZoomQuery=" + (m_lookup.getZoomQuery()==null ? "" : m_lookup.getZoomQuery().getWhereClause())
	//		+ ", Validation=" + m_lookup.getValidation());
		if (whereClause.indexOf('@') != -1)
		{
			String validated = Env.parseContext(wsc.ctx, m_lookup.getWindowNo(), whereClause, false);
			if (validated.length() == 0)
				log.severe("Cannot Parse=" + whereClause);
			else
			{
				log.fine("Parsed: " + validated);
				return validated;
			}
		}
		return whereClause;
	}	//	getWhereClause
		
		
	
	
	
	public static void fillLookupValues( LookupValues lvs, KeyNamePair[] kp )
	{
		if (kp!=null && lvs != null)
		for (int i=0; i<kp.length; i++)
		{
			LookupValue lv = lvs.addNewLv();
			lv.setKey( kp[i].getID() );
			lv.setVal( kp[i].getName() );
		}
	}
		
    public static void fillLookupValues( LookupValues lvs, Lookup lookup, GridField field  )
    {		
    	fillLookupValues( lvs, lookup, field.isMandatory(true), field.isReadOnly());
    }
	
	public static void fillLookupValues( LookupValues lvs, Lookup lookup, boolean isMandatory, boolean isReadOnly  )
    {		
    	    	
		if(lookup.getSize() == 0)
			lookup.refresh();
		Object[] list = lookup.getData(isMandatory, true, !isReadOnly, false).toArray();										
		
		for (int i=0; i<list.length; i++)
		{
			boolean isNumber = list[0] instanceof KeyNamePair;
			
			LookupValue lv = lvs.addNewLv();
			
			if (isNumber)
			{
				KeyNamePair p = (KeyNamePair)list[i];												
				lv.setKey( Integer.toString(p.getKey()) );
				lv.setVal( p.getName() );
				
			} else
			{
				ValueNamePair p = (ValueNamePair)list[i];
				lv.setKey( p.getValue() );
				lv.setVal( p.getName() );
			}									
		}    	
    }
    
	
	
		
}
