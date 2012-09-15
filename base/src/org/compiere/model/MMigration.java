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
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MMigration extends X_AD_Migration {

	public boolean isFailOnError() {
		return isFailOnError;
	}

	public void setFailOnError(boolean isFailOnError) {
		this.isFailOnError = isFailOnError;
	}

	private boolean isFailOnError = false;

	public MMigration(Properties ctx, int AD_Migration_ID, String trxName) {
		super(ctx, AD_Migration_ID, trxName);
	}

	public MMigration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	public void apply() throws SQLException {
		for ( MMigrationStep step : getSteps(false) )
		{
			try {
				Trx.run(new StepRunner(step, false));
			}
			catch (Exception e) {
				if ( isFailOnError  )	// abort on first error
					throw new AdempiereException(e);
				// else continue processing
			}
		}
		updateStatus(null);
	}
	
	public void rollback() throws SQLException {
		for ( MMigrationStep step : getSteps(true) )
		{
			try {
				Trx.run(new StepRunner(step, true));
			} catch (Exception e) {
				if ( isFailOnError )
					throw new AdempiereException(e);
				// else continue
			}
		}
	}
	
	public void updateStatus(String trxName) {
		
		String base = "SELECT count(1) " +
		" FROM AD_MigrationStep " +
		" WHERE AD_Migration_ID = " + getAD_Migration_ID() +
		" AND IsActive = 'Y'";
		int total = DB.getSQLValue(null, base);

		String sql = base + " AND StatusCode = 'A'";
		int applied = DB.getSQLValue(trxName, sql);
		
		sql = base + " AND StatusCode = 'U'";
		int unapplied = DB.getSQLValue(trxName, sql);

		if ( applied == total && applied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_Applied);
			setApply(MMigration.APPLY_Rollback);
		}
		else if ( unapplied == total && unapplied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_Unapplied);
			setApply(MMigration.APPLY_Apply);
		}
		else if ( total > applied && applied > 0 )
		{
			setStatusCode(MMigration.STATUSCODE_PartiallyApplied);
			setApply(MMigration.APPLY_Rollback);
		}
		// overlaps with unapplied
		//else if ( applied <= 0 )
		//	setStatusCode(MMigration.STATUSCODE_Failed);
		
		saveEx();
	}
	
	private List<MMigrationStep> getSteps(boolean rollback) {
		String where = "AD_Migration_ID = " + getAD_Migration_ID();
		String order = rollback ? "SeqNo DESC" : "SeqNo ASC";
		return MTable.get(getCtx(), MMigrationStep.Table_ID)
		.createQuery(where, get_TrxName())
		.setOnlyActiveRecords(true)
		.setOrderBy(order)
		.list();
	}
	

	public static boolean updated = false;
	
	public static MMigration fromXmlNode(Properties ctx, Element element, String trx)
	{
		
		if ( !updated )
			update();
		
		if ( !"Migration".equals(element.getLocalName() ) )
				return null;
		
		String name = element.getAttribute("Name");
		String seqNo = element.getAttribute("SeqNo");
		String entityType = element.getAttribute("EntityType");
		String releaseNo = element.getAttribute("ReleaseNo");
		
		
		String where = "Name = ?"
			+ " AND SeqNo = ?"
			+ " AND EntityType = ?";
		Object[] params = new Object[] {name, Integer.parseInt(seqNo), entityType};
		MMigration mmigration = new Query(ctx, MMigration.Table_Name, where, trx)
		.setParameters(params).firstOnly();
		if ( mmigration != null )
			return null;  // already exists (TODO: update?)
		
		mmigration = new MMigration(ctx, 0, trx);
		
		mmigration.setName(name);
		mmigration.setSeqNo(Integer.parseInt(seqNo));
		mmigration.setEntityType(entityType);
		mmigration.setReleaseNo(releaseNo);
		mmigration.saveEx();

		Node comment = (Element) element.getElementsByTagName("Comments").item(0);
		if ( comment != null )
			mmigration.setComments(comment.getTextContent());
		
		NodeList children = element.getElementsByTagName("Step");
		for ( int i = 0; i < children.getLength(); i++ )
		{
			Element step = (Element) children.item(i);
			if ( "Step".equals(step.getTagName()))
				MMigrationStep.fromXmlNode(mmigration, step);
		}
		
		mmigration.saveEx();
		
		return mmigration;
	}
	
	private static void update() {
		
		String sql = "UPDATE AD_Column SET FieldLength = 999999999 WHERE AD_Column_ID IN (57874, 57873) " +
				"AND FieldLength = 2000";
		int count = DB.executeUpdateEx(sql, null);
		
		if ( count > 0 )
		{
			MColumn col = new MColumn(Env.getCtx(), 57874, null);
			col.syncDatabase();

			col = new MColumn(Env.getCtx(), 57873, null);
			col.syncDatabase();
			
		}

		updated = true;
		
	}

	public Node toXmlNode(Document document) throws ParserConfigurationException, SAXException {

		Element migration = document.createElement("Migration");

		migration.setAttribute("SeqNo", Integer.toString(getSeqNo()));
		migration.setAttribute("Name", getName());
		migration.setAttribute("EntityType", getEntityType());
		migration.setAttribute("ReleaseNo", getReleaseNo());
		
		if ( getComments() != null ) 
		{
			Element comment = document.createElement("Comments");
			migration.appendChild(comment);
			comment.appendChild(document.createTextNode(getComments()));
		} 
		
		
		for (MMigrationStep step : getSteps(false) )
		{
			log.log(Level.FINE, "Exporting step: " + step);
			migration.appendChild(step.toXmlNode(document));
		}
		
		return migration;
	}
	
	public void mergeMigration(MMigration from) {
		
		int lastSeq = DB.getSQLValue(get_TrxName(),
				"SELECT COALESCE(MAX(SeqNo),0) FROM AD_MigrationStep WHERE AD_Migration_ID = " + getAD_Migration_ID());
		
		String updateSql = "UPDATE AD_MigrationStep SET AD_Migration_ID = ?, SeqNo = SeqNo + ? WHERE AD_Migration_ID = ? ";
		Object[] params = new Object[] { getAD_Migration_ID(), lastSeq, from.getAD_Migration_ID() };
		DB.executeUpdateEx(updateSql, params, get_TrxName());
		
		from.deleteEx(false, get_TrxName());
	}
	
	private class StepRunner implements TrxRunnable {
		MMigrationStep step;
		boolean rollback;
		public StepRunner(MMigrationStep step, boolean rollback) {
			this.step = step;
			this.rollback = rollback;
		}
		public void run(String trxName) {
			step.set_TrxName(trxName);
			if ( rollback )
				step.rollback();
			else
				step.apply();
		}
	}
}