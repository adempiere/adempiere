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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.impexp.ImpFormat;
import org.compiere.impexp.ImpFormatRow;
import org.compiere.model.MRole;
import org.compiere.plaf.CompiereColor;
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
public class VFileImport extends CPanel
	implements FormPanel, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3996535986364873964L;
	private static final int MAX_LOADED_LINES = 100;
	private static final int MAX_SHOWN_LINES = 10;

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame)
	{
		log.info("");
		m_WindowNo = WindowNo;
		m_frame = frame;
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

	/**	Window No			*/
	private int         		m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 			m_frame;

	private ArrayList<String>	m_data = new ArrayList<String>();
	private ImpFormat 			m_format;
	private JLabel[] 			m_labels;
	private JTextField[] 		m_fields;
	private int					m_record = -1;
	/**	Current selected file */
	private File				m_file = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VFileImport.class);
	//
	private static final String s_none = "----";	//	no format indicator
	//
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
	private CComboBox fCharset = new CComboBox(Ini.getAvailableCharsets());

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		CompiereColor.setBackground(this);
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
	 *	Dynamic Init
	 */
	private void dynInit()
	{
		//	Load Formats
		pickFormat.addItem(s_none);
		String sql = MRole.getDefault().addAccessSQL("SELECT Name FROM AD_ImpFormat", "AD_ImpFormat",
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				pickFormat.addItem(rs.getString(1));
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		pickFormat.setSelectedIndex(0);
		pickFormat.addActionListener(this);
		//
		fCharset.setSelectedItem(Ini.getCharset());
		fCharset.addActionListener(this);
		//
		confirmPanel.getOKButton().setEnabled(false);
	}	//	dynInit

	
	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == bFile)
		{
			cmd_loadFile();
			invalidate();
			m_frame.pack();
		}
		else if (e.getSource() == fCharset) {
			int record = m_record;
			cmd_reloadFile();
			m_record = record - 1;
			cmd_applyFormat(true);
		}
		else if (e.getSource() == pickFormat)
		{
			cmd_loadFormat();
			invalidate();
			m_frame.pack();
		}
		else if (e.getSource() == bNext)
			cmd_applyFormat(true);
		else if (e.getSource() == bPrevious)
			cmd_applyFormat(false);
		
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			m_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			confirmPanel.setEnabled(false);
			m_frame.setBusy(true);
			//
			org.compiere.apps.SwingWorker worker = new org.compiere.apps.SwingWorker()
			{
				public Object construct()
			    {
			    	cmd_process();
					return Boolean.TRUE;
				}
			};
			worker.start();
			//  when you need the result:
			//	x = worker.get();   //  this blocks the UI !!
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
		
		if (m_data != null && m_data.size()	> 0					//	file loaded
			&& m_format != null && m_format.getRowCount() > 0)	//	format loaded
			confirmPanel.getOKButton().setEnabled(true);
		else
			confirmPanel.getOKButton().setEnabled(false);
	}	//	actionPerformed


	/**************************************************************************
	 *	Load File
	 */
	private void cmd_loadFile()
	{
		String directory = org.compiere.Adempiere.getAdempiereHome() 
			+ File.separator + "data" 
			+ File.separator + "import";
		log.config(directory);
		//
		JFileChooser chooser = new JFileChooser(directory);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle(Msg.getMsg(Env.getCtx(), "FileImportFileInfo"));
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
			return;
		m_file = chooser.getSelectedFile();
		log.config(m_file.getName());
		bFile.setText(m_file.getName());
		cmd_reloadFile();
	}
	
	/**
	 * Reload/Load file
	 */
	private void cmd_reloadFile()
	{
		if (m_file == null)
			return;
		
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		m_data.clear();
		rawData.setText("");
		try
		{
			//  see NaturalAccountMap
			Charset charset = (Charset)fCharset.getSelectedItem(); 
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(m_file), charset), 10240);
			//	not safe see p108 Network pgm
			String s = null;
			while ((s = in.readLine()) != null)
			{
				m_data.add(s);
				if (m_data.size() <= MAX_LOADED_LINES)
				{
					rawData.append(s);
					rawData.append("\n");
				}
			}
			in.close();
			rawData.setCaretPosition(0);
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
			bFile.setText(Msg.getMsg(Env.getCtx(), "FileImportFile"));
		}
		int index = 1;	//	second line as first may be heading
		if (m_data.size() == 1)
			index = 0;
		int length = 0;
		if (m_data.size() > 0)
			length = m_data.get(index).toString().length();
		info.setText(Msg.getMsg(Env.getCtx(), "Records") + "=" + m_data.size()
			+ ", " + Msg.getMsg(Env.getCtx(), "Length") + "=" + length + "   ");
		setCursor (Cursor.getDefaultCursor());
		log.config("Records=" + m_data.size() 
			+ ", Length=" + length);
	}	//	cmd_loadFile

	/**
	 *	Load Format
	 */
	private void cmd_loadFormat()
	{
		//	clear panel
		previewPanel.removeAll();
		//
		String formatName = pickFormat.getSelectedItem().toString();
		if (formatName.equals(s_none))
			return;
		m_format = ImpFormat.load (formatName);
		if (m_format == null)
		{
			ADialog.error(m_WindowNo, this, "FileImportNoFormat", formatName);
			return;
		}

		//	pointers
		int size = m_format.getRowCount();
		m_labels = new JLabel[size];
		m_fields = new JTextField[size];
		for (int i = 0; i < size; i++)
		{
			ImpFormatRow row = m_format.getRow(i);
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
		m_record = -1;
		record.setText("------");
		previewPanel.invalidate();
		previewPanel.repaint();
	}	//	cmd_format

	/**
	 *	Apply Current Pattern
	 *  @param next next
	 */
	private void cmd_applyFormat (boolean next)
	{
		if (m_format == null || m_data.size() == 0)
			return;

		//	set position
		if (next)
			m_record++;
		else
			m_record--;
		if (m_record < 0)
			m_record = 0;
		else if (m_record >= m_data.size())
			m_record = m_data.size() - 1;
		record.setText(" " + String.valueOf(m_record+1) + " ");
		//	Line Info
		String[] lInfo = m_format.parseLine(m_data.get(m_record).toString(), false, true, false);	//	no label, trace, no ignore
		int size = m_format.getRowCount();
		if (lInfo.length != size)
			log.log(Level.SEVERE, "FormatElements=" + size + " != Fields=" + lInfo.length);
		for (int i = 0; i < size; i++)
		{
			m_fields[i].setText(lInfo[i]);
			m_fields[i].setCaretPosition(0);
		}
	}	//	cmd_applyFormat

	
	/**************************************************************************
	 *	Process File
	 */
	private void cmd_process()
	{
		if (m_format == null)
		{
			ADialog.error(m_WindowNo, this, "FileImportNoFormat");
			return;
		}
		log.config(m_format.getName());

		//	For all rows - update/insert DB table
		int row = 0;
		int imported = 0;
		for (row = 0; row < m_data.size(); row++)
			if (m_format.updateDB(Env.getCtx(), m_data.get(row).toString(), null))
				imported++;
		//
		ADialog.info(m_WindowNo, this, "FileImportR/I", row + " / " + imported + "#");
		dispose();
	}	//	cmd_process

}	//	FileImport
