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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.adempiere.controller.FileImportController;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.util.ReaderInputStream;
import org.adempiere.webui.window.FDialog;
import org.compiere.impexp.ImpFormatRow;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
 * 	Fixed length file import
 *
 *  @author 	Niraj Sohun
 *  			Aug 16, 2007
 *  
 */

public class WFileImport extends FileImportController implements IFormController, EventListener {
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WFileImport.class);
	
	/**	Main Container	*/
	private CustomForm form = new CustomForm();
	private Listbox pickFormat = new Listbox();
	private Listbox fCharset = new Listbox();
	
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	private Button bFile = new Button();
	private Button bNext = new Button();
	private Button bPrevious = new Button();
	private Button loadData = new Button();
	
	private Textbox rawData = new Textbox();
	private Textbox[] m_fields;
	
	private Label info = new Label();
	private Label[] m_labels;
	private Label record = new Label();
	private Label labelFormat = new Label();

	private Div previewPanel = new Div();

	private Hbox northPanel = new Hbox();

	private Div centerPanel = new Div();

	public WFileImport() {
		initForm();
	}
	
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 */
	private void initForm() {
		log.info("");
		try {
			jbInit();
			dynInit();
			Borderlayout layout = new Borderlayout();
			form.setWidth("100%");
			form.setClosable(true);
			form.setBorder("normal");
			form.appendChild(layout);
			layout.setHeight("100%");
			layout.setWidth("100%");
			North north = new North();
			layout.appendChild(north);
			north.appendChild(northPanel);
			Center center = new Center();
			center.setFlex(true);
			layout.appendChild(center);
			center.appendChild(centerPanel);
			South south = new South();
			layout.appendChild(south);
			south.appendChild(confirmPanel);
		} catch(Exception e) {
			log.log(Level.SEVERE, "init", e);
		}
	}	//	init

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	
	private void jbInit() throws Exception {
		
		loadData.setImage(ITheme.MENU_PROCESS_IMAGE);
		loadData.addActionListener(this);
		//	
		Charset[] charsets = Ini.getAvailableCharsets();
		for (int i = 0; i < charsets.length; i++) {
			fCharset.appendItem(charsets[i].displayName(), charsets[i]);
		}
		setCharset(charsets[0]);
		//	
		bFile.setLabel(Msg.getMsg(Env.getCtx(), "FileImportFile"));
		bFile.setTooltiptext(Msg.getMsg(Env.getCtx(), "FileImportFileInfo"));
		bFile.addEventListener(Events.ON_CLICK, this);
		
		fCharset.setMold("select");
		fCharset.setRows(0);
		fCharset.setTooltiptext(Msg.getMsg(Env.getCtx(), "Charset", false));
		
		info.setValue("   ");
		
		labelFormat.setValue(Msg.translate(Env.getCtx(), "AD_ImpFormat_ID"));
		
		pickFormat.setMold("select");
		pickFormat.setRows(0);
		
		bNext.setTooltiptext(Msg.getMsg(Env.getCtx(), "Next"));
		bNext.setLabel(">");
		bNext.addEventListener(Events.ON_CLICK, this);
		
		record.setValue("------");
		
		bPrevious.setTooltiptext(Msg.getMsg(Env.getCtx(), "Previous"));
		bPrevious.setLabel("<");
		bPrevious.addEventListener(Events.ON_CLICK, this);
		
		northPanel.appendChild(bFile);
		northPanel.appendChild(fCharset);
		northPanel.appendChild(info);
		northPanel.appendChild(labelFormat);
		northPanel.appendChild(pickFormat);
		northPanel.appendChild(loadData);
		northPanel.appendChild(bPrevious);
		northPanel.appendChild(record);
		northPanel.appendChild(bNext);
		
		rawData.setWidth("100%");
		rawData.setCols(80);
		rawData.setRows(MAX_SHOWN_LINES);
		rawData.setHeight("40%");
		
		previewPanel.setWidth("100%");
		previewPanel.setHeight("58%");
		previewPanel.setStyle("overflow: auto");
		
		centerPanel.setWidth("100%"); // Elaine 2008/11/07 - fix text area is not expanded in IE7
		centerPanel.setHeight("100%");
		centerPanel.appendChild(rawData);
		centerPanel.appendChild(new Separator());
		centerPanel.appendChild(previewPanel);
		loadData.setVisible(false);
		confirmPanel.addActionListener(Events.ON_CLICK, this);
	}
	
	/**
	 *	Dynamic Init
	 */
	
	private void dynInit() {
		//	Load Formats
		pickFormat.appendItem(s_none, s_none);
		
		String sql = MRole.getDefault().addAccessSQL("SELECT Name FROM AD_ImpFormat", "AD_ImpFormat",
				MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
		
			while (rs.next())
				pickFormat.appendItem(rs.getString(1), rs.getString(1));
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		}
		
		pickFormat.setSelectedIndex(0);
		pickFormat.addEventListener(Events.ON_SELECT, this);

		Charset charset = Ini.getCharset();
		
		for (int i = 0; i < fCharset.getItemCount(); i++) {
			ListItem listitem = fCharset.getItemAtIndex(i);
			Charset compare = (Charset)listitem.getValue();
			if (charset == compare) {
				fCharset.setSelectedIndex(i);
				break;
			}
		}
		
		fCharset.addEventListener(Events.ON_SELECT, this);
		
		confirmPanel.setEnabled("Ok", false);
	}	//	dynInit

	
	public void onEvent(Event e) throws Exception  {
		if (e.getTarget() == bFile)
		{
			cmd_loadFile();
			fillView();
		} else if (e.getTarget() == fCharset) {
			setCharset((Charset) fCharset.getValue());
			cmd_reloadFile();
			setRecordNo(getRecordNo() - 1);
			cmd_applyFormat(true);
		} else if (e.getTarget() == pickFormat) {
			cmd_loadFormat();
			fillView();
		} else if (e.getTarget() == bNext ) {
			cmd_applyFormat(true);
		} else if (e.getTarget() == bPrevious) {
			cmd_applyFormat(false);
		} else if (e.getTarget() == confirmPanel.getButton("Ok")) {
			setBusy(true);
			cmd_processData();
			setBusy(false);
		} else if(e.getTarget() == loadData) {
			//	Load from Connection
			readFromConnection();
		} else if (e.getTarget() == confirmPanel.getButton("Cancel")) {
			SessionManager.getAppDesktop().closeActiveWindow();
			return;			
		}
		//	
		confirmPanel.getButton("Ok").setEnabled(getDataSize() > 0 && getRowCount() > 0);
	}
	
	/**************************************************************************
	 *	Load File
	 */
	
	private void cmd_loadFile() {
		Media media = null;
		InputStream file = null;
		try {
			media = Fileupload.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//	
		if (media == null) {
			return;
		}
		//	
		if (media.isBinary()) {
			file = media.getStreamData();
		} else {
			ListItem listitem = fCharset.getSelectedItem();
			if (listitem == null) {
				file = new ReaderInputStream(media.getReaderData());
			} else {
				Charset charset = (Charset)listitem.getValue();
				file = new ReaderInputStream(media.getReaderData(), charset.name());
			}
		}
		//	
		log.config(media.getName());
		bFile.setLabel(media.getName());
		//	
		cmd_reloadFile(file);
	}

	/**
	 *	Load Format
	 */
	
	private void cmd_loadFormat() {
		//	clear panel
		previewPanel.getChildren().clear();
		
		ListItem listitem = pickFormat.getSelectedItem();
		
		String formatName = (String)listitem.getValue();
		
		if (formatName.equals(s_none))
			return;
		String error = loadFormat(formatName);
		//	
		if (error != null) {
			FDialog.error(getWindowNo(), form, formatName);
			return;
		}
		//	pointers
		int size = getRowCount();
		m_labels = new Label[size];
		m_fields = new Textbox[size];
		
		for (int i = 0; i < size; i++) {
			ImpFormatRow row = getRow(i);
			m_labels[i] = new Label(row.getColumnName());
			
			Hbox hbox = new Hbox();
			hbox.setWidth("100%");
			hbox.setWidths("30%, 70%");
			hbox.setStyle("padding-bottom: 3px");
			
			hbox.appendChild(m_labels[i].rightAlign());
			
			int length = row.getEndNo() - row.getStartNo();
			
			if (length <= 5)
				length = 5;
			else if (length > 20)
				length = 20;
			
			m_fields[i] = new Textbox();
			m_fields[i].setStyle("margin-left: 2px");
			
			hbox.appendChild(m_fields[i]);
			
			previewPanel.appendChild(hbox);
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
			FDialog.error(getWindowNo(), form, e.getMessage());
		}
		if (infoMsg != null) {
			FDialog.info(getWindowNo(), form, Msg.parseTranslation(Env.getCtx(), infoMsg));
			return;
		}
	}	//	cmd_processData

	@Override
	public void setBusy(boolean busy) {
		confirmPanel.setEnabled("Ok", !busy);
	}

	@Override
	public void clearView() {
		rawData.setText("");
		clearPreview();
	}

	@Override
	public void clearPreview() {
		record.setValue("------");
		previewPanel.invalidate();
	}

	@Override
	public void fillView() {
		fillInfoView();
		form.invalidate();
	}

	@Override
	public void fillInfoView() {
		info.setText(Msg.getMsg(Env.getCtx(), "Records") + "=" + getDataSize()
			+ ", " + Msg.getMsg(Env.getCtx(), "Length") + "=" + getRecordLength(1) + "   ");
	}

	@Override
	public void addLine(String line) {
		line += rawData.getValue();
		rawData.setValue(line);
	}

	@Override
	public ADForm getForm() {
		return form;
	}
}
