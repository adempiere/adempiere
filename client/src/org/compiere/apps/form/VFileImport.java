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

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.adempiere.controller.FileImportController;
import org.compiere.apps.ADialog;
import org.compiere.apps.AppsAction;
import org.compiere.apps.ConfirmPanel;
import org.compiere.impexp.ImpFormatRow;
import org.compiere.model.MRole;
import org.compiere.plaf.CompiereColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;


/**
 * Fixed length file import
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VFileImport.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>FR [ 1658127 ] Select charset encoding on import
 * 			<li>BF [ 1619158 ] Import is not working with UTF-8
 * 			<li>BF [ 1738641 ] Import Formats are accessible for all tenants
 *			<li>BF [ 1778356 ] VFileImport: IndexOfBound exp if the file is not loaded
 */
public class VFileImport extends FileImportController
	implements FormPanel, ActionListener {

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame) {
		super.init(WindowNo);
		log.info("");
		this.frame = frame;
		try
		{
			jbInit();
			dynInit();
			frame.getContentPane().add(northPanel, BorderLayout.NORTH);
			frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
			frame.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "init", e);
		}
	}	//	init

	/**	FormFrame			*/
	private FormFrame 			frame;

	private JLabel[] 			m_labels;
	private JTextField[] 		m_fields;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VFileImport.class);
	//
	private static final String s_none = "----";	//	no format indicator
	//
	private CPanel mainPanel = new CPanel();
	private CPanel northPanel = new CPanel();
	private JButton bFile = new JButton();
	private JComboBox pickFormat = new JComboBox();
	private CPanel centerPanel = new CPanel();
	private BorderLayout centerLayout = new BorderLayout();
	private JScrollPane rawDataPane = new JScrollPane();
	private JTextArea rawData = new JTextArea();
	private JScrollPane previewPane = new JScrollPane();
	private CPanel previewPanel = new CPanel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private JLabel info = new JLabel();
	private JLabel labelFormat = new JLabel();
	private GridBagLayout previewLayout = new GridBagLayout();
	private JButton bNext = new JButton();
	private JButton bPrevious = new JButton();
	private JLabel record = new JLabel();
	private CButton loadData = null;
	private CComboBox fCharset = new CComboBox(Ini.getAvailableCharsets());

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception {
		CompiereColor.setBackground(mainPanel);
		AppsAction action = new AppsAction(ConfirmPanel.A_PROCESS, null, Msg.getMsg(Env.getCtx(), "ConnectToSource"));
		action.setDelegate(this);
		loadData = (CButton) action.getButton();
		bFile.setText(Msg.getMsg(Env.getCtx(), "FileImportFile"));
		bFile.setToolTipText(Msg.getMsg(Env.getCtx(), "FileImportFileInfo"));
		bFile.addActionListener(this);
		fCharset.setToolTipText(Msg.getMsg(Env.getCtx(), "Charset", false));
		info.setText("   ");
		labelFormat.setText(Msg.translate(Env.getCtx(), "AD_ImpFormat_ID"));
		//
		bNext.setToolTipText(Msg.getMsg(Env.getCtx(), "Next"));
		bNext.setMargin(new Insets(2, 2, 2, 2));
		bNext.setText(">");
		bNext.addActionListener(this);
		record.setText("------");
		bPrevious.setToolTipText(Msg.getMsg(Env.getCtx(), "Previous"));
		bPrevious.setMargin(new Insets(2, 2, 2, 2));
		bPrevious.setText("<");
		bPrevious.addActionListener(this);
		//
		northPanel.setBorder(BorderFactory.createEtchedBorder());
		northPanel.add(bFile, null);
		northPanel.add(fCharset);
		northPanel.add(info, null);
		northPanel.add(labelFormat, null);
		northPanel.add(pickFormat, null);
		northPanel.add(loadData, null);
		northPanel.add(bPrevious, null);
		northPanel.add(record, null);
		northPanel.add(bNext, null);
		//
		centerPanel.setLayout(centerLayout);
		rawData.setFont(new java.awt.Font("Monospaced", 0, 10));
		rawData.setColumns(80);
		rawData.setRows(MAX_SHOWN_LINES);
		rawDataPane.getViewport().add(rawData, null);
		centerPanel.add(rawDataPane, BorderLayout.CENTER);
		centerPanel.add(previewPane, BorderLayout.SOUTH);
		//
		previewPanel.setLayout(previewLayout);
		previewPane.getViewport().add(previewPanel, null);
		previewPane.setPreferredSize(new Dimension(700,80));
		//
		confirmPanel.addActionListener(this);
		loadData.setVisible(false);
	}	//	jbInit

	/**
	 * 	Dispose
	 */
	public void dispose()
	{
		if (frame != null)
			frame.dispose();
		frame = null;
	}	//	dispose

	/**
	 *	Dynamic Init
	 */
	private void dynInit()
	{
		//	Load Formats
		pickFormat.addItem(s_none);
		String sql = MRole.getDefault().addAccessSQL("SELECT Name FROM AD_ImpFormat", "AD_ImpFormat",
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				pickFormat.addItem(rs.getString(1));
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		}
		pickFormat.setSelectedIndex(0);
		pickFormat.addActionListener(this);
		//	
		Charset charset = Ini.getCharset();
		setCharset(charset);
		fCharset.setSelectedItem(charset);
		fCharset.addActionListener(this);
		//
		confirmPanel.getOKButton().setEnabled(false);
	}	//	dynInit

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == bFile) {
			cmd_loadFile();
			fillView();
		} else if (e.getSource() == fCharset) {
			setCharset((Charset) fCharset.getSelectedItem());
			cmd_reloadFile();
			setRecordNo(getRecordNo() - 1);
			cmd_applyFormat(true);
		} else if (e.getSource() == pickFormat) {
			cmd_loadFormat();
			fillView();
		} else if (e.getSource() == bNext) {
			cmd_applyFormat(true);
		} else if (e.getSource() == bPrevious) {
			cmd_applyFormat(false);
		} else if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			setBusy(true);
			//
			org.compiere.apps.SwingWorker worker = new org.compiere.apps.SwingWorker() {
				public Object construct() {
					cmd_processData();
					return Boolean.TRUE;
				}
			};
			worker.start();
			//  when you need the result:
			//	x = worker.get();   //  this blocks the UI !!
		} else if(e.getActionCommand().equals(ConfirmPanel.A_PROCESS)) {
			//	Load from Connection
			readFromConnection();
		} else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		}
		//	
		confirmPanel.getOKButton().setEnabled(getDataSize() > 0 && getRowCount() > 0);
	}	//	actionPerformed


	/**************************************************************************
	 *	Load File
	 * @throws FileNotFoundException 
	 */
	private void cmd_loadFile() {
		String directory = org.compiere.Adempiere.getAdempiereHome() 
			+ File.separator + "data" 
			+ File.separator + "import";
		log.config(directory);
		//
		JFileChooser chooser = new JFileChooser(directory);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle(Msg.getMsg(Env.getCtx(), "FileImportFileInfo"));
		if (chooser.showOpenDialog(frame.getContainer()) != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = chooser.getSelectedFile();
		log.config(file.getName());
		bFile.setText(file.getName());
		try {
			cmd_reloadFile(new FileInputStream(file));
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
	}

	/**
	 *	Load Format
	 */
	private void cmd_loadFormat() {
		//	clear panel
		previewPanel.removeAll();
		//
		String formatName = pickFormat.getSelectedItem().toString();
		if (formatName.equals(s_none))
			return;
		String error = loadFormat(formatName);
		//	
		if (error != null) {
			ADialog.error(getWindowNo(), frame.getContainer(), error, formatName);
			return;
		}

		//	pointers
		int size = getRowCount();
		m_labels = new JLabel[size];
		m_fields = new JTextField[size];
		for (int i = 0; i < size; i++) {
			ImpFormatRow row = getRow(i);
			m_labels[i] = new JLabel (row.getColumnName());
			previewPanel.add(m_labels[i], new GridBagConstraints(i, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2, 2, 2, 2), 0, 0));
			//
			int length = row.getEndNo() - row.getStartNo();
			if (length <= 5)
				length = 5;
			else if (length > 20)
				length = 20;
			m_fields[i] = new JTextField (length);
			previewPanel.add(m_fields[i], new GridBagConstraints(i, 1, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		}
		setRecordNo(-1);
		//	Visible for load data from connection
		loadData.setVisible(isFromConnection());
		clearPreview();
	}	//	cmd_format

	/**
	 *	Apply Current Pattern
	 *  @param next next
	 */
	private void cmd_applyFormat (boolean next) {
		if (getRowCount() == 0 
				|| getDataSize() == 0) {
			return;
		}
		//	set position
		if (next) {
			addRecordNo(1);
		} else {
			addRecordNo(-1);
		}
		if (getRecordNo() < 0) {
			setRecordNo(0);
		} else if (getRecordNo() >= getDataSize()) {
			setRecordNo(getDataSize() - 1);
		}
		record.setText(" " + String.valueOf(getRecordNo() + 1) + " ");
		//	Line Info
		String[] lInfo = parseLine(getRecordNo());	//	no label, trace, no ignore
		int size = getRowCount();
		if (lInfo.length != size)
			log.log(Level.SEVERE, "FormatElements=" + size + " != Fields=" + lInfo.length);
		for (int i = 0; i < size; i++) {
			m_fields[i].setText(lInfo[i]);
			m_fields[i].setCaretPosition(0);
		}
	}	//	cmd_applyFormat

	
	/**************************************************************************
	 *	Process File
	 */
	private void cmd_processData() {
		String infoMsg = null;
		try {
			infoMsg = cmd_process();
			clearView();
		} catch (Exception e) {
			ADialog.error(getWindowNo(), frame.getContainer(), e.getMessage());
		} finally {
			setBusy(false);
		}
		if (infoMsg != null) {
			ADialog.info(getWindowNo(), frame.getContainer(), Msg.parseTranslation(Env.getCtx(), infoMsg));
		}
	}	//	cmd_process

	@Override
	public void setBusy(boolean busy) {
		if(busy) {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		} else {
			frame.setCursor(Cursor.getDefaultCursor());
		}
		frame.setBusy(busy);
		confirmPanel.setEnabled(!busy);
	}

	@Override
	public void clearView() {
		rawData.setText("");
		clearPreview();
	}

	@Override
	public void fillView() {
		fillInfoView();
		frame.getContainer().invalidate();
		frame.pack();
	}

	@Override
	public void addLine(String line) {
		rawData.append(line);
	}

	@Override
	public void clearPreview() {
		record.setText("------");
		previewPanel.invalidate();
		previewPanel.repaint();
	}

	@Override
	public void fillInfoView() {
		info.setText(Msg.getMsg(Env.getCtx(), "Records") + "=" + getDataSize()
			+ ", " + Msg.getMsg(Env.getCtx(), "Length") + "=" + getRecordLength(1) + "   ");
	}

}	//	FileImport
