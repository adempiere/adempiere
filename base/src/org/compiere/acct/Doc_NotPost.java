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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.acct.Doc;
import org.compiere.acct.Fact;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MFactAcct;
import org.compiere.model.MTable;
import org.compiere.util.Env;

/**
 *  Class for Documents without Accounting Records
 *  @author Carlos Parada
 */
public class Doc_NotPost extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_NotPost (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, Doc_NotPost.getDocumentClass(rs), rs, null, trxName);
		setDateAcctColumn(rs);
	}	//	Doc_NotAcounting


	/**
	 * Get Document Class for ResultSet
	 * @param rs
	 * @return
	 */
	private static Class<?> getDocumentClass(ResultSet rs){
		AtomicReference<Class<?>> docClass = new AtomicReference<>(null);
		Optional<ResultSet> maybeResultSet= Optional.ofNullable(rs) ;
		maybeResultSet.ifPresent(resultSet ->{
			try {
				MTable table = MTable.get(Env.getCtx(), resultSet.getMetaData().getTableName(1));
				docClass.set(MTable.getClass(table.getTableName()));
				
			} catch (SQLException e) {
				s_log.severe(e.getMessage());
			}
		});
		return docClass.get();
	}
	
	private  void setDateAcctColumn(ResultSet rs) {
		AtomicReference<Timestamp> dateAcctValue = new AtomicReference<Timestamp>(null); 
		Arrays.asList(new String[] {"DateDoc", "DateAcct", "MovementDate"})
			  .stream()
			  .forEach(dateAcct ->{
				  try {
					if (rs.findColumn(dateAcct) > 0
							&& dateAcctValue.get()==null) {
						dateAcctValue.set(rs.getTimestamp(dateAcct));
						setDateAcct(dateAcctValue.get());
						setDateDoc(dateAcctValue.get());
					}
				} catch (SQLException e) {
					s_log.warning(e.getMessage());
				}
			  });
	}
	@Override
	protected String loadDocumentDetails() {
		return null;
	}

	@Override
	public BigDecimal getBalance() {
		return Env.ZERO;
	}

	@Override
	public ArrayList<Fact> createFacts(MAcctSchema as) {
		ArrayList<Fact> facts = new ArrayList<Fact>();
		Fact fact = new Fact (this, as, MFactAcct.POSTINGTYPE_Actual);
		facts.add(fact);
		return facts;
	}

}   //  Doc_NotAcounting
