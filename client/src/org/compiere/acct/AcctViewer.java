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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.adempiere.core.domains.models.X_C_AcctSchema_Element;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.PrintScreenPainter;
import org.compiere.apps.search.Info;
import org.compiere.grid.ed.VDate;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.Query;
import org.compiere.report.core.RModel;
import org.compiere.report.core.RModelExcelExporter;
import org.compiere.report.core.ResultTable;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CFrame;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CTabbedPane;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 *  Account Viewer
 *
 *  @author Jorg Janke
 *  @version  $Id: AcctViewer.java,v 1.3 2006/08/10 01:00:27 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			BF [ 1778534 ] Info Account: can't find product
 * @author Colin Rooney (croo) 
 * 			BF [ 2006668 ] Selection of Product in the Accounting Viewer
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class AcctViewer extends CFrame 
	implements ActionListener, ChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6160970582569467185L;

	/**
	 *  Default constructor
	 */
	public AcctViewer()
	{
		this (0, 0, 0);
	}   //  AcctViewer

	/**
	 *  Detail Constructor
	 *
	 *  @param clientId Client
	 *  @param tableId Table
	 *  @param recordId Record
	 */
	public AcctViewer(int clientId, int tableId, int recordId)
	{
		super (Msg.getMsg(Env.getCtx(), "AcctViewer"));
		log.info("AD_Table_ID=" + tableId + ", Record_ID=" + recordId);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		accountViewerData = new AcctViewerData (Env.getCtx(), Env.createWindowNo(this), clientId, tableId);
		AEnv.addToWindowManager(this);
		//
		try
		{
			jbInit();
			dynInit (tableId, recordId);
			AEnv.showCenterScreen(this);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			dispose();
		}
	}   //  AcctViewer

	/** State Info          */
	private AcctViewerData accountViewerData = null;
	/** Image Icon			*/
	private ImageIcon findIcon = new ImageIcon(org.compiere.Adempiere.class.getResource("images/Find16.gif"));
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AcctViewer.class);

	/** @todo Display Record Info & Zoom */
	private CPanel mainPanel = new CPanel();
	private CTabbedPane tabbedPane = new CTabbedPane();
	private CPanel query = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CScrollPane result = new CScrollPane();
	private ResultTable table = new ResultTable();
	private CPanel southPanel = new CPanel();
	private CButton bQuery = new CButton();
	private CButton bPrint = new CButton();
	private CButton bExport = new CButton();
	private CButton bZoom = new CButton();
	private CLabel statusLine = new CLabel();
	private BorderLayout southLayout = new BorderLayout();
	private BorderLayout queryLayout = new BorderLayout();
	private CPanel selectionPanel = new CPanel();
	private CPanel displayPanel = new CPanel();
	private TitledBorder displayBorder;
	private TitledBorder selectionBorder;
	private GridBagLayout displayLayout = new GridBagLayout();
	private CCheckBox displayQty = new CCheckBox();
	private CCheckBox displaySourceAmt = new CCheckBox();
//	private CPanel graphPanel = new CPanel();
	private CCheckBox displayDocumentInfo = new CCheckBox();
	private CLabel lSort = new CLabel();
	private CComboBox sortBy1 = new CComboBox();
	private CComboBox sortBy2 = new CComboBox();
	private CComboBox sortBy3 = new CComboBox();
	private CCheckBox group1 = new CCheckBox();
	private CCheckBox group2 = new CCheckBox();
	private CCheckBox group3 = new CCheckBox();
	private CLabel lGroup = new CLabel();
	private GridBagLayout selectionLayout = new GridBagLayout();
	private CComboBox selAcctSchema = new CComboBox();
	private CComboBox selPostingType = new CComboBox();
	private CCheckBox selDocument = new CCheckBox();
	private CComboBox selTable = new CComboBox();
	private CButton selRecord = new CButton();
	private CLabel lOrg = new CLabel();
	private CComboBox selOrg = new CComboBox();
	private CLabel lAcct = new CLabel();
	private CButton selAcct = new CButton();
	private CLabel lDate = new CLabel();
	private CLabel lacctSchema = new CLabel();
	private CLabel lpostingType = new CLabel();
	private VDate selDateFrom = new VDate("DateFrom", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateFrom"));
	private VDate selDateTo = new VDate("DateTo", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "DateTo"));
	private CLabel lsel1 = new CLabel();
	private CLabel lsel2 = new CLabel();
	private CLabel lsel3 = new CLabel();
	private CLabel lsel4 = new CLabel();
	private CLabel lsel5 = new CLabel();
	private CLabel lsel6 = new CLabel();
	private CLabel lsel7 = new CLabel();
	private CLabel lsel8 = new CLabel();
	private CLabel lsel9 = new CLabel();
	private CLabel lsel10 = new CLabel();
	private CLabel lsel11 = new CLabel();
	private CLabel lsel12 = new CLabel();
	private CLabel lsel13 = new CLabel();

	private CButton sel1 = new CButton();
	private CButton sel2 = new CButton();
	private CButton sel3 = new CButton();
	private CButton sel4 = new CButton();
	private CButton sel5 = new CButton();
	private CButton sel6 = new CButton();
	private CButton sel7 = new CButton();
	private CButton sel8 = new CButton();
	private CButton sel9 = new CButton();
	private CButton sel10 = new CButton();
	private CButton sel11 = new CButton();
	private CButton sel12 = new CButton();
	private CButton sel13 = new CButton();

	//
	private CButton bRePost = new CButton();
	private CCheckBox forcePost = new CCheckBox();
	private CComboBox sortBy4 = new CComboBox();
	private CCheckBox group4 = new CCheckBox();

	/**
	 *  Static Init.
	 *  <pre>
	 *  - mainPanel
	 *      - tabbedPane
	 *          - query
	 *          - result
	 *          - graphPanel
	 *  </pre>
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		ImageIcon imageIcon = new ImageIcon(org.compiere.Adempiere.class.getResource("images/InfoAccount16.gif"));
		setIconImage(imageIcon.getImage());
		//
		mainLayout.setHgap(5);
		mainLayout.setVgap(5);
		mainPanel.setLayout(mainLayout);
		selectionPanel.setLayout(selectionLayout);
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(tabbedPane, BorderLayout.CENTER);
		//  Selection
		selectionBorder = new TitledBorder(BorderFactory.createEtchedBorder(
			Color.white,new Color(148, 145, 140)), Msg.getMsg(Env.getCtx(),"Selection"));
		selectionPanel.setBorder(selectionBorder);
		lacctSchema.setLabelFor(selAcctSchema);
		lacctSchema.setText(Msg.translate(Env.getCtx(), "C_AcctSchema_ID"));
		lpostingType.setLabelFor(selPostingType);
		lpostingType.setText(Msg.translate(Env.getCtx(), "PostingType"));
		selDocument.setText(Msg.getMsg(Env.getCtx(), "SelectDocument"));
		selDocument.addActionListener(this);

		lOrg.setLabelFor(selOrg);
		lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		lAcct.setLabelFor(selAcct);
		lAcct.setText(Msg.translate(Env.getCtx(), "Account_ID"));
		lDate.setLabelFor(selDateFrom);
		lDate.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		lsel1.setLabelFor(sel1);
		lsel2.setLabelFor(sel2);
		lsel3.setLabelFor(sel3);
		lsel4.setLabelFor(sel4);
		lsel5.setLabelFor(sel5);
		lsel6.setLabelFor(sel6);
		lsel7.setLabelFor(sel7);
		lsel8.setLabelFor(sel8);
		lsel9.setLabelFor(sel9);
		lsel10.setLabelFor(sel10);
		lsel11.setLabelFor(sel11);
		lsel12.setLabelFor(sel12);
		lsel13.setLabelFor(sel13);



		//  Display
		displayBorder = new TitledBorder(BorderFactory.createEtchedBorder(
			Color.white,new Color(148, 145, 140)), Msg.getMsg(Env.getCtx(),"Display"));
		displayPanel.setBorder(displayBorder);
		displayPanel.setLayout(displayLayout);
		displayQty.setText(Msg.getMsg(Env.getCtx(), "DisplayQty"));
		displaySourceAmt.setText(Msg.getMsg(Env.getCtx(), "DisplaySourceInfo"));
		displayDocumentInfo.setText(Msg.getMsg(Env.getCtx(), "DisplayDocumentInfo"));
		displayDocumentInfo.setSelected(true);
		displayDocumentInfo.addActionListener(this);
		lSort.setText(Msg.getMsg(Env.getCtx(), "SortBy"));
		lGroup.setText(Msg.getMsg(Env.getCtx(), "GroupBy"));
		//
		displayPanel.add(displaySourceAmt,          new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(displayDocumentInfo,        new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(lSort,       new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(sortBy1,       new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(sortBy2,         new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(group1,     new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(group2,      new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(lGroup,       new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(displayQty,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		displayPanel.add(sortBy3,   new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(group3,  new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(sortBy4,  new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		displayPanel.add(group4,  new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		//
		selectionPanel.add(lacctSchema,         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		selectionPanel.add(selAcctSchema,        new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			
		selectionPanel.add(selDocument,           new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 5, 10, 5), 0, 0));
		selectionPanel.add(selTable,          new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 5, 10, 5), 0, 0));
		selectionPanel.add(selRecord,         new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 5, 10, 5), 0, 0));

		selectionPanel.add(lpostingType,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(selPostingType,        new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lDate,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(selDateFrom,     new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(selDateTo,   new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lOrg,         new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 0, 5), 0, 0));
		selectionPanel.add(selOrg,         new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lAcct,         new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(selAcct,         new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel1,  new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel2,     new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel3,   new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel1,   new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel2,   new GridBagConstraints(1, 7, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel3,   new GridBagConstraints(1, 8, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel4,  new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel4,  new GridBagConstraints(1, 9, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

		selectionPanel.add(lsel5,  new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel5,  new GridBagConstraints(1, 10, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel6,  new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel6,  new GridBagConstraints(1, 11, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel7,  new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel7,  new GridBagConstraints(1, 12, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel8,  new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel8,  new GridBagConstraints(1, 13, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel9,  new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel9,  new GridBagConstraints(1, 14, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel10,  new GridBagConstraints(0, 15, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel10,  new GridBagConstraints(1, 15, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel11,  new GridBagConstraints(0, 16, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel11,  new GridBagConstraints(1, 16, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel12,  new GridBagConstraints(0, 17, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel12,  new GridBagConstraints(1, 17, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(lsel13,  new GridBagConstraints(0, 18, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		selectionPanel.add(sel13,  new GridBagConstraints(1, 18, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		//
		queryLayout.setHgap(5);
		queryLayout.setVgap(5);
		query.setLayout(queryLayout);
		query.add(selectionPanel,  BorderLayout.CENTER);
		query.add(displayPanel,  BorderLayout.EAST);
		//
		tabbedPane.add(query,        Msg.getMsg(Env.getCtx(), "ViewerQuery"));
		tabbedPane.add(result,       Msg.getMsg(Env.getCtx(), "ViewerResult"));
//		tabbedPane.add(graphPanel,   Msg.getMsg(Env.getCtx(), "ViewerGraph"));
		tabbedPane.addChangeListener(this);
		result.getViewport().add(table, null);
		//  South
		southLayout.setHgap(5);
		southLayout.setVgap(5);
		southPanel.setLayout(southLayout);
		statusLine.setForeground(Color.blue);
		statusLine.setBorder(BorderFactory.createLoweredBevelBorder());
		southPanel.add(statusLine, BorderLayout.CENTER);
		bRePost.setText(Msg.getMsg(Env.getCtx(), "RePost"));
		bRePost.setToolTipText(Msg.getMsg(Env.getCtx(), "RePostInfo"));
		bRePost.addActionListener(this);
		bRePost.setVisible(false);
		forcePost.setText(Msg.getMsg(Env.getCtx(), "Force"));
		forcePost.setToolTipText(Msg.getMsg(Env.getCtx(), "ForceInfo"));
		forcePost.setVisible(false);
		CPanel leftSide = new CPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
		leftSide.add(bRePost);
		leftSide.add(forcePost);
		southPanel.add(leftSide, BorderLayout.WEST);
		//
		bQuery.setIcon(Env.getImageIcon("Refresh16.gif"));
		bQuery.setToolTipText(Msg.getMsg(Env.getCtx(), "Refresh"));
		bQuery.addActionListener(this);
		bPrint.setIcon(Env.getImageIcon("Print16.gif"));
		bPrint.setToolTipText(Msg.getMsg(Env.getCtx(), "Print"));
		bPrint.addActionListener(this);
		bExport.setIcon(Env.getImageIcon("Export16.gif"));
		bExport.setToolTipText(Msg.getMsg(Env.getCtx(), "Export"));
		bExport.setVisible(displayDocumentInfo.isSelected());
		bExport.addActionListener(this);
		bZoom.setIcon(Env.getImageIcon("Zoom16.gif"));
		bZoom.setToolTipText(Msg.getMsg(Env.getCtx(), "Zoom"));
		bZoom.setVisible(displayDocumentInfo.isSelected());
		bZoom.addActionListener(this);
		CPanel rightSide = new CPanel(new FlowLayout(FlowLayout.TRAILING, 0, 0));
		rightSide.add(bZoom);
		rightSide.add(bExport);
		rightSide.add(bPrint);
		rightSide.add(bQuery);
		southPanel.add(rightSide,  BorderLayout.EAST);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		//
	}   //  jbInit

	/**
	 *  Dynamic Init
	 *
	 *  @param tableId table
	 *  @param recordId record
	 */
	private void dynInit (int tableId, int recordId)
	{
		accountViewerData.fillAcctSchema(selAcctSchema);
		selAcctSchema.addActionListener(this);
		actionAcctSchema();
		//
		accountViewerData.fillTable(selTable);
		selTable.addActionListener(this);
		selRecord.setIcon(findIcon);
		selRecord.addActionListener(this);
		selRecord.setText("");
		//
		accountViewerData.fillPostingType (selPostingType);

		//  Mandatory Elements
		accountViewerData.fillOrg(selOrg);
		selAcct.setActionCommand("Account_ID");
		selAcct.addActionListener(this);
		selAcct.setText("");
		selAcct.setIcon(findIcon);

		//  Document Select
		boolean haveDocument = tableId != 0 && recordId != 0;
		selDocument.setSelected (haveDocument);
		actionDocument();
		actionTable();
		statusLine.setText(" " + Msg.getMsg(Env.getCtx(), "ViewerOptions"));

		//  Initial Query
		if (haveDocument)
		{
			accountViewerData.AD_Table_ID = tableId;
			accountViewerData.Record_ID = recordId;
			actionQuery();
			String keyColumn = selRecord.getActionCommand();
			String tableName = keyColumn.substring(0, keyColumn.length()-3);
			String selectSQL = keyColumn + "=" + recordId;
			accountViewerData.buttonRecordID.put(keyColumn,recordId);
			selRecord.setText(accountViewerData.getButtonText(tableName, keyColumn, selectSQL));
		}
		
		// Display quantity as default
		displayQty.setValue('Y');
	}   //  dynInit

	/**
	 *  Dispose
	 */
	public void dispose()
	{
		if (accountViewerData != null)
			accountViewerData.dispose();
		accountViewerData = null;
		super.dispose();
	}   //  dispose;

	/**************************************************************************
	 *  Tab Changed
	 *  @param changeEvent ChangeEvent
	 */
	public void stateChanged(ChangeEvent changeEvent)
	{
	//	log.info( "AcctViewer.stateChanged");
		boolean visible = accountViewerData.documentQuery && tabbedPane.getSelectedIndex() == 1;
		bRePost.setVisible(visible);
		bExport.setVisible(tabbedPane.getSelectedIndex() == 1);
		if (Ini.isPropertyBool(Ini.P_SHOW_ADVANCED))
			forcePost.setVisible(visible);
	}   //  stateChanged


	/**
	 *  Action Performed (Action Listener)
	 *  @param actionEvent ActionEvent
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
	//	log.info(e.getActionCommand());
		Object source = actionEvent.getSource();
		if (source == displayDocumentInfo)
		{
			if (displayDocumentInfo.isSelected())
				bZoom.setVisible(true);
			else
				bZoom.setVisible(false);
		}
		if (source == selAcctSchema)
			actionAcctSchema();
		else if (source == bQuery)
			actionQuery();
		else if (source == selDocument)
			actionDocument();
		else if (source == selTable)
			actionTable();
		else if (source == bRePost)
			actionRePost();
		else if  (source == bPrint)
			PrintScreenPainter.printScreen(this);
		else if (source == bZoom)
			actionZoom();
		else if  (source == bExport)
			exportExcel();
		//  InfoButtons
		else if (source instanceof CButton)
			actionButton((CButton)source);
	}   //  actionPerformed

	/**
	 * 	New Acct Schema
	 */
	private void actionAcctSchema()
	{
		KeyNamePair keyNamePair = (KeyNamePair)selAcctSchema.getSelectedItem();
		if (keyNamePair == null)
			return;
		accountViewerData.C_AcctSchema_ID = keyNamePair.getKey();
		accountViewerData.ASchema = MAcctSchema.get(Env.getCtx(), accountViewerData.C_AcctSchema_ID);
		log.info(accountViewerData.ASchema.toString());
		//
		//  Sort Options
		sortBy1.removeAllItems();
		sortBy2.removeAllItems();
		sortBy3.removeAllItems();
		sortBy4.removeAllItems();
		sortAddItem(new ValueNamePair("",""));
		sortAddItem(new ValueNamePair("DateAcct", Msg.translate(Env.getCtx(), "DateAcct")));
		sortAddItem(new ValueNamePair("DateTrx", Msg.translate(Env.getCtx(), "DateTrx")));
		sortAddItem(new ValueNamePair("C_Period_ID", Msg.translate(Env.getCtx(), "C_Period_ID")));
		//
		CLabel[] labels = new CLabel[] {lsel1, lsel2, lsel3, lsel4, lsel5, lsel6, lsel7, lsel8 , lsel9 , lsel10 , lsel11 , lsel12 , lsel13};
		CButton[] buttons = new CButton[] {sel1, sel2, sel3, sel4, sel5, sel6, sel7, sel8 , sel9 , sel10 , sel11 , sel12 , sel13};
		int selectionIndex = 0;
		MAcctSchemaElement[] elements = accountViewerData.ASchema.getAcctSchemaElements();
		for (int i = 0; i < elements.length && selectionIndex < labels.length; i++)
		{
			MAcctSchemaElement acctSchemaElement = elements[i];
			String columnName = acctSchemaElement.getColumnName();
			String displayColumnName = acctSchemaElement.getDisplayColumnName();
			if (columnName.equals("User1_ID") || columnName.equals("User2_ID") || columnName.equals("User3_ID") || columnName.equals("User4_ID"))
				displayColumnName = acctSchemaElement.getName();
			else
				displayColumnName = acctSchemaElement.getDisplayColumnName();
			//  Add Sort Option
			sortAddItem(new ValueNamePair(columnName, Msg.translate(Env.getCtx(), displayColumnName)));
			//  Additional Elements
			if (!acctSchemaElement.isElementType(X_C_AcctSchema_Element.ELEMENTTYPE_Organization)
			&&  !acctSchemaElement.isElementType(X_C_AcctSchema_Element.ELEMENTTYPE_Account))
			{

				labels[selectionIndex].setText(Msg.translate(Env.getCtx(), displayColumnName));
				labels[selectionIndex].setVisible(true);
				buttons[selectionIndex].setActionCommand(columnName);
				buttons[selectionIndex].addActionListener(this);
				buttons[selectionIndex].setIcon(findIcon);
				buttons[selectionIndex].setText("");
				buttons[selectionIndex].setVisible(true);
				selectionIndex ++;
			}
		}
		//	don't show remaining
		while (selectionIndex < labels.length)
		{
			labels[selectionIndex].setVisible(false);
			buttons[selectionIndex ++].setVisible(false);
		}
	}	//	actionAcctSchema
	
	/**
	 * 	Add to Sort
	 *	@param valueNamePair name pair
	 */
	private void sortAddItem(ValueNamePair valueNamePair)
	{
		sortBy1.addItem(valueNamePair);
		sortBy2.addItem(valueNamePair);
		sortBy3.addItem(valueNamePair);
		sortBy4.addItem(valueNamePair);
	}	//	sortAddItem

	/**
	 *  Query
	 */
	private void actionQuery()
	{
		//  Parameter Info
		StringBuffer para = new StringBuffer();
		//  Reset Selection Data
		accountViewerData.C_AcctSchema_ID = 0;
		accountViewerData.AD_Org_ID = 0;

		//  Save Selection Choices
		KeyNamePair keyNamePair = (KeyNamePair)selAcctSchema.getSelectedItem();
		if (keyNamePair != null)
			accountViewerData.C_AcctSchema_ID = keyNamePair.getKey();
		para.append("C_AcctSchema_ID=").append(accountViewerData.C_AcctSchema_ID);
		//
		ValueNamePair vp = (ValueNamePair)selPostingType.getSelectedItem();
		accountViewerData.PostingType = vp.getValue();
		para.append(", PostingType=").append(accountViewerData.PostingType);

		//  Document
		accountViewerData.documentQuery = selDocument.isSelected();
		para.append(", DocumentQuery=").append(accountViewerData.documentQuery);
		if (selDocument.isSelected())
		{
			if (accountViewerData.AD_Table_ID == 0 || accountViewerData.Record_ID == 0)
				return;
			para.append(", AD_Table_ID=").append(accountViewerData.AD_Table_ID)
				.append(", Record_ID=").append(accountViewerData.Record_ID);
		}
		else
		{
			accountViewerData.DateFrom = (Timestamp)selDateFrom.getValue();
			para.append(", DateFrom=").append(accountViewerData.DateFrom);
			accountViewerData.DateTo = (Timestamp)selDateTo.getValue();
			para.append(", DateTo=").append(accountViewerData.DateTo);
			keyNamePair = (KeyNamePair)selOrg.getSelectedItem();
			if (keyNamePair != null)
				accountViewerData.AD_Org_ID = keyNamePair.getKey();
			para.append(", AD_Org_ID=").append(accountViewerData.AD_Org_ID);
			//
			Iterator it = accountViewerData.whereInfo.values().iterator();
			while (it.hasNext())
				para.append(", ").append(it.next());
		}

		//  Save Display Choices
		accountViewerData.displayQty = displayQty.isSelected();
		para.append(" - Display Qty=").append(accountViewerData.displayQty);
		accountViewerData.displaySourceAmt = displaySourceAmt.isSelected();
		para.append(", Source=").append(accountViewerData.displaySourceAmt);
		accountViewerData.displayDocumentInfo = displayDocumentInfo.isSelected();
		para.append(", Doc=").append(accountViewerData.displayDocumentInfo);
		//
		accountViewerData.sortBy1 = ((ValueNamePair)sortBy1.getSelectedItem()).getValue();
		accountViewerData.group1 = group1.isSelected();
		para.append(" - Sorting: ").append(accountViewerData.sortBy1).append("/").append(accountViewerData.group1);
		accountViewerData.sortBy2 = ((ValueNamePair)sortBy2.getSelectedItem()).getValue();
		accountViewerData.group2 = group2.isSelected();
		para.append(", ").append(accountViewerData.sortBy2).append("/").append(accountViewerData.group2);
		accountViewerData.sortBy3 = ((ValueNamePair)sortBy3.getSelectedItem()).getValue();
		accountViewerData.group3 = group3.isSelected();
		para.append(", ").append(accountViewerData.sortBy3).append("/").append(accountViewerData.group3);
		accountViewerData.sortBy4 = ((ValueNamePair)sortBy4.getSelectedItem()).getValue();
		accountViewerData.group4 = group4.isSelected();
		para.append(", ").append(accountViewerData.sortBy4).append("/").append(accountViewerData.group4);

		bQuery.setEnabled(false);
		statusLine.setText(" " + Msg.getMsg(Env.getCtx(), "Processing"));

		log.config(para.toString());
		Thread.yield();

		//  Switch to Result pane
		tabbedPane.setSelectedIndex(1);

		//  Set TableModel with Query
		table.setModel(accountViewerData.query());

		bQuery.setEnabled(true);
		statusLine.setText(" " + Msg.getMsg(Env.getCtx(), "ViewerOptions"));
	}   //  actionQuery

	/**
	 *  Document selection
	 */
	private void actionDocument()
	{
		boolean isDocumentSelected = selDocument.isSelected();
		selTable.setEnabled(isDocumentSelected);
		selRecord.setEnabled(isDocumentSelected);
		//
		selDateFrom.setReadWrite(!isDocumentSelected);
		selDateTo.setReadWrite(!isDocumentSelected);
		selOrg.setEnabled(!isDocumentSelected);
		selAcct.setEnabled(!isDocumentSelected);
		sel1.setEnabled(!isDocumentSelected);
		sel2.setEnabled(!isDocumentSelected);
		sel3.setEnabled(!isDocumentSelected);
		sel4.setEnabled(!isDocumentSelected);
		sel5.setEnabled(!isDocumentSelected);
		sel6.setEnabled(!isDocumentSelected);
		sel7.setEnabled(!isDocumentSelected);
		sel8.setEnabled(!isDocumentSelected);
	}   //  actionDocument

	/**
	 *  Save Table selection (reset Record selection)
	 */
	private void actionTable()
	{
		ValueNamePair valueNamePair = (ValueNamePair)selTable.getSelectedItem();
		accountViewerData.AD_Table_ID = ((Integer) accountViewerData.tableInfo.get(valueNamePair.getValue())).intValue();
		log.config(valueNamePair.getValue() + " = " + accountViewerData.AD_Table_ID);
		//  Reset Record
		accountViewerData.Record_ID = 0;
		selRecord.setText("");
		selRecord.setActionCommand(valueNamePair.getValue() + "_ID");
	}   //  actionTable

	/**
	 *  Action Button
	 *
	 *  @param button pressed button
	 *  @return ID
	 */
	private int actionButton(CButton button)
	{
		String keyColumn = button.getActionCommand();
		log.info(keyColumn);
		String whereClause = "(IsSummary='N' OR IsSummary IS NULL)";
		String lookupColumn = keyColumn;
		int record_id = accountViewerData.getButtonRecordID(keyColumn);

		if (keyColumn.equals("Account_ID"))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_Account);
			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if (keyColumn.equals("User1_ID"))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList1);
			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if (keyColumn.equals("User2_ID"))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
				.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList2);
			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if (keyColumn.equals("User3_ID"))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
					.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList3);
			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if (keyColumn.equals("User4_ID"))
		{
			lookupColumn = "C_ElementValue_ID";
			MAcctSchemaElement ase = accountViewerData.ASchema
					.getAcctSchemaElement(X_C_AcctSchema_Element.ELEMENTTYPE_UserList4);
			if (ase != null)
				whereClause += " AND C_Element_ID=" + ase.getC_Element_ID();
		}
		else if (keyColumn.equals("M_Product_ID"))
		{
			whereClause = "";
		}
		if (lookupColumn.equals("UserElement1_ID"))			
		{
			MAcctSchemaElement et = new Query(Env.getCtx(), MAcctSchemaElement.Table_Name, 
					"elementtype = 'X1' ", 
					null)
			.setClient_ID()
			.firstOnly();
			String tableName = et.getAD_Column().getAD_Table().getTableName();
			String lookupcolumnname = tableName + "_ID";
			lookupColumn = lookupcolumnname;
			whereClause = "";
		}
		if (lookupColumn.equals("UserElement2_ID"))			
		{
			MAcctSchemaElement et = new Query(Env.getCtx(), MAcctSchemaElement.Table_Name, 
					"elementtype = 'X2' ", 
					null)
			.setClient_ID()
			.firstOnly();
			String tableName = et.getAD_Column().getAD_Table().getTableName();
			String lookupcolumnname = tableName + "_ID";
			lookupColumn = lookupcolumnname;
			whereClause = "";
		}
		else if (selDocument.isSelected())
			whereClause = "";
		
		if (button == selRecord)                            //  Record_ID
			record_id = accountViewerData.Record_ID;
		else
			record_id = accountViewerData.getButtonRecordID(keyColumn);

		String tableName = lookupColumn.substring(0, lookupColumn.length()-3);
		Info info = Info.create(this, true, accountViewerData.WindowNo, tableName, lookupColumn, record_id, "", false, true, whereClause);
		if (!info.loadedOK())
		{
			info.dispose();
			info = null;
			button.setText("");
			accountViewerData.whereInfo.put(keyColumn, "");
			accountViewerData.buttonRecordID.put(keyColumn, null);
			return 0;
		}
		info.setVisible(true);

		boolean isCancelled = info.isCancelled();
		boolean isOK = info.isOk();
		Integer key = 0;
		
		if (isCancelled && !isOK) // Delete the saved info
		{
			key = 0;
			if (button == selRecord)                            //  Record_ID
				accountViewerData.Record_ID = key.intValue();
			else
			{
				accountViewerData.whereInfo.put(keyColumn, "");    //  no query
				accountViewerData.buttonRecordID.put(keyColumn, key.intValue());
			}
			button.setText("");
		}
		else if(!isCancelled && isOK)
		{
			//  Save for query
			String selectSQL = info.getSelectedSQL();       //  C_Project_ID=100 or ""
			key = (Integer)info.getSelectedKey();
			log.config(keyColumn + " - " + key);
			if (button == selRecord)                            //  Record_ID
				accountViewerData.Record_ID = key.intValue();
			else
			{
				accountViewerData.whereInfo.put(keyColumn, keyColumn + "=" + key.intValue());  //  Add to query
				accountViewerData.buttonRecordID.put(keyColumn, key.intValue());
			}
			//  Display Selection and resize
			button.setText(accountViewerData.getButtonText(tableName, lookupColumn, selectSQL));
			pack();
		}
		else if(!(isCancelled ^ isOK)) // xor: window closed or error - no change
		{
			// accountViewerData not changed
			if (button == selRecord)                            //  Record_ID
				key = accountViewerData.Record_ID = key.intValue();
			else
				key = accountViewerData.getButtonRecordID(keyColumn);
		}
		info = null;
		return key.intValue();
	}   //  actionButton

	/**
	 * Zoom Document
	 */
	private  void actionZoom()
	{
		if(displayDocumentInfo.isSelected() && table.getSelectedRow() >= 0)
		{
			RModel model = accountViewerData.getRModel();
			int columnTableId = model.getColumnIndex("AD_Table_ID");
			int columnRecordId = model.getColumnIndex("Record_ID");
			if (columnRecordId >= 0 && columnRecordId >= 0 ) {
				KeyNamePair tableKeyPair = (KeyNamePair) table.getValueAt(table.getSelectedRow(), columnTableId);
				Integer recordId = (Integer) table.getValueAt(table.getSelectedRow(), columnRecordId);
				if (tableKeyPair != null && recordId != null)
					AEnv.zoom(tableKeyPair.getKey(), recordId);
			}
		}
	}

	/**
	 *  RePost Record
	 */
	private void actionRePost()
	{
		if (accountViewerData.documentQuery
			&& accountViewerData.AD_Table_ID != 0 && accountViewerData.Record_ID != 0
			&& ADialog.ask(accountViewerData.WindowNo, this, "PostImmediate?"))
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			boolean force = forcePost.isSelected();
			String error = AEnv.postImmediate (accountViewerData.WindowNo, accountViewerData.AD_Client_ID,
				accountViewerData.AD_Table_ID, accountViewerData.Record_ID, force);
			setCursor(Cursor.getDefaultCursor());
			if (error != null)
				ADialog.error(0, this, "PostingError-N", error);
			actionQuery();
		}
	}   //  actionRePost

	/**
	 * Export to Excel
	 */
	private void exportExcel() {
		RModel model = table.getRModel();
		if (model == null) {
			return;
		}
		try {
			RModelExcelExporter exporter = new RModelExcelExporter((RModel)model);
			exporter.export(null, null);
		}
		catch (Exception e) {
			ADialog.error(0, this, "Error", e.getLocalizedMessage());
			if (CLogMgt.isLevelFinest()) e.printStackTrace();
		}
	}
}   //  AcctViewer
