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
package org.compiere.apps.form;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Stack;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.DeleteEntitiesModel;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MTable;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 *	Delete Dialog.
 *
 *	@author Paul Bowden
 */
public class VDelete extends CPanel 
	implements FormPanel, ActionListener
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 149783846292562740L;
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;   
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDelete.class);

	private BorderLayout mainLayout = new BorderLayout();
	private CPanel CenterPanel = new CPanel();
	private GridLayout centerLayout = new GridLayout(0,2);
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private JScrollPane treePane;
	private CheckboxTree tree;
	private CLabel clientLabel;
	private CComboBox clientPick;
	private VLookup tablePick;
	private CLabel tableLabel;
	private CCheckBox dryRun;
	private Integer clientId;
	private DefaultMutableTreeNode rootNode;
	private Trx m_trx;
	private int m_totalCount;

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info( "VMerge.init - WinNo=" + m_WindowNo);
		try
		{
			preInit();
			jbInit ();
			frame.getContentPane().add(this, BorderLayout.CENTER);
		//	frame.getContentPane().add(statusBar, BorderLayout.SOUTH);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init
	
	@SuppressWarnings("unchecked")
	private void createNodes(DefaultMutableTreeNode root) {
	    
		DeleteEntitiesModel currentNode = (DeleteEntitiesModel) root.getUserObject();
		
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
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(data.tableName);
					node.setUserObject(data);

					log.log(Level.FINE, "Adding node: " + data.tableName + "." + data.joinColumn);
					root.add(node);
				}
				else
					log.log(Level.FINE, "No records:" + data.tableName);
			}
			//
		} catch (SQLException e) {
			log.log(Level.INFO, sql);
			throw new AdempiereException("Couldn't load child tables", e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}		
		

		Enumeration<DefaultMutableTreeNode> kids = root.children();
		while ( kids.hasMoreElements() )
		{
			DefaultMutableTreeNode node = kids.nextElement();
			if ( root.isNodeAncestor(node) )
			{
				log.log(Level.WARNING, "Loop detected, escaping.");
				break;
			}
			else if ( ((DeleteEntitiesModel) node.getUserObject()).mandatoryLink )
				createNodes(node);
		}

	}


	/**
	 * 	Pre Init
	 */
	private void preInit()
	{

		dryRun = new CCheckBox("Dry Run", true);
		
		clientLabel = new CLabel(Msg.getMsg(Env.getCtx(), "AD_Client_ID"));
		String sql = "SELECT AD_Client_ID, Name FROM AD_Client WHERE AD_Client_ID <> 0";
		KeyNamePair[] clients = DB.getKeyNamePairs(sql, false);
		clientPick = new CComboBox(clients);
		clientPick.insertItemAt(null, 0);
		clientPick.setSelectedItem(null);
		clientPick.setMandatory(true);
		clientPick.setBackground(false);
		
		tableLabel = new CLabel(Msg.getMsg(Env.getCtx(), "AD_Table_ID"));
		tablePick = new VLookup("AD_Table_ID", true, false, true,
				MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, 114, DisplayType.TableDir));
		tablePick.addActionListener(this);
		
		DeleteEntitiesModel root = new DeleteEntitiesModel();
		root.tableName = "Tables";
		rootNode = new DefaultMutableTreeNode(null);
		tree = new CheckboxTree(rootNode);
		treePane = new JScrollPane(tree);
		
		JViewport viewPort = treePane.getViewport();
		viewPort.add(tree);
	}	//	preInit

	/**
	 * 	Static init
	 * 	@throws java.lang.Exception
	 */
	void jbInit () throws Exception
	{
		this.setLayout (mainLayout);
		mainLayout.setHgap (5);
		mainLayout.setVgap (5);
		//
		this.add (confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
		//
		centerLayout.setHgap (5);
		centerLayout.setVgap (5);
		centerLayout.setColumns (5);
		centerLayout.setRows (0);
		//
		CenterPanel.setLayout (centerLayout);
		this.add (CenterPanel, BorderLayout.NORTH);
		CenterPanel.add (clientLabel);
		CenterPanel.add (clientPick);
		CenterPanel.add(tableLabel);
		CenterPanel.add(tablePick);
		CenterPanel.add(dryRun);
		
		this.add(treePane, BorderLayout.CENTER);
	}	//	jbInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *  Action ListeneranObject
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.log(Level.FINE, "from: " + e.getSource() + " action: " + e.getActionCommand());
		boolean commit = ! dryRun.isSelected();

		Object o = tablePick.getValue();
		int tableId = 0;
		if ( o != null )
			tableId = (Integer) o;
		
		o = clientPick.getValue();
		if ( o != null )
			clientId = ((KeyNamePair) o).getKey();
		
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
			return;
		}
		//
		else if ( e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (tableId == 0 || clientId == 0)
			{
				ADialog.error(m_WindowNo, this, "Error", 
						"Select client and base table for cascade delete.");
			}
			else
			{
				m_totalCount = 0;
				m_trx = Trx.get(Trx.createTrxName("delete"), true);
				String errorMsg = "";
				try {

					Enumeration<?> nodes = rootNode.breadthFirstEnumeration();
					Stack<DeleteEntitiesModel> stack = new Stack<DeleteEntitiesModel>();
					
					while ( nodes.hasMoreElements() )
					{
						stack.push((DeleteEntitiesModel) (((DefaultMutableTreeNode) nodes.nextElement()).getUserObject()));
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
				catch (Exception ex) {
					errorMsg = ex.getLocalizedMessage();
					log.log(Level.WARNING, "Cascade delete failed.", ex);
					m_totalCount = 0;
					m_trx.rollback();
					ADialog.error(m_WindowNo, this, "DeleteError", 
							errorMsg);
						return;
				}
				finally {
				m_trx.close();
				}
				
			ADialog.info (m_WindowNo, this, "DeleteSuccess", 
				"Records deleted:" + " #" + m_totalCount);

			dispose();
				
			}
		}
		else if ( e.getActionCommand().equals("comboBoxChanged"))
		{
			if (tableId == 0 || clientId == 0)
			{
				ADialog.error(m_WindowNo, this, "Error", 
						"Select client and base table for cascade delete.");
				return;
			}
			MTable table = MTable.get(Env.getCtx(), tableId);

			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			confirmPanel.getOKButton().setEnabled(false);
			//
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
			rootNode = new DefaultMutableTreeNode(data);

			DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			model.setRoot(rootNode);
			createNodes(rootNode);

			tree.expandRow(0);

			//
			confirmPanel.getOKButton().setEnabled(true);
			setCursor(Cursor.getDefaultCursor());
			//
		}
	}   //  actionPerformed

	

}	//	VDelete