/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.apps;



import java.util.ArrayList;
import java.util.List;

import org.adempiere.controller.SmallViewEditable;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WAppsAction;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ProcessController;
import org.compiere.apps.ProcessCtl;
import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MPInstance;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSysConfig;
import org.compiere.print.MPrintFormat;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CEditor;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Html;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * 	@author 	Low Heng Sin
 * 	@author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 		<li>FR [ 3426137 ] Smart Browser
 *  	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *		@see https://github.com/adempiere/adempiere/issues/298
 *  @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 299 ] Instance saved, is not supported for swing UI
 *		<li>FR [ 1051 ] Process Dialog have not scroll bar in zk
 *		<li>FR [ 1061 ] Process Modal Dialog in zk height is not autosize
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">#495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/1926">#1926</a> ] ZK Exports migration XML files to 
 *       different location than what is selected in the dialogs.
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/1975">#1975</a> ] 
 *			Key Listeners in ZK remain active when panel is not displayed. References to the Key Listener
 *			have been removed. Instead use the bOK.setFocus() method to capture the enter key.
 * 	@version 	2006-12-01
 */
public class ProcessPanel extends ProcessController implements SmallViewEditable, EventListener, ASyncProcess {
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessPanel(int WindowNo, ProcessInfo pi) {
		this(null, WindowNo, pi, "100%");
	}	//	ProcessParameterPanel
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessPanel(IZKProcessDialog parent, int WindowNo, ProcessInfo pi, String width) {
		this(WindowNo, pi, width, COLUMNS_1);
		this.parent = parent;
	}	//	ProcessParameterPanel
	
	/**
	 * With Columns
	 * @param WindowNo
	 * @param pi
	 * @param width
	 * @param columns
	 */
	public ProcessPanel(int WindowNo, ProcessInfo pi, String width, int columns) {
		super(WindowNo, pi, columns);
		this.width = width;
	}

	private static final String MESSAGE_DIV_STYLE = "max-height: 120px; overflow: auto";
	/**	Width	*/
	private String width;
	//Layout Mode
	private int cols = 0;
	//
	private ArrayList<Label> 	m_separators;
	/**	Rows for Parameters	*/
	private Rows 	rows;
	private Row		currentRow;
	private int		qtyRow = 0;
	//
	private Center 	centerPanel;
	private Panel	mainPanel;
	
	private boolean isLocked = false;
	private Borderlayout mainLayout;
	private Div 	messageDiv;
	private North 	messagePanel;
	private Grid 	parameterPanel;
	
	private Html message = null;
	private Button bOK = null;
	private Button bCancel = null;
	private IZKProcessDialog parent = null;
	private BusyDialog progressWindow;
	//saved paramaters

	private Combobox fSavedName=new Combobox();
	private Button bDelete = new Button(Msg.getMsg(Env.getCtx(),"delete"));
	private Label lSaved = new Label(Msg.getMsg(Env.getCtx(), "SavedParameter"));

	// Print Format
	private WTableDirEditor		fPrintFormat		= null;
	private Combobox 			fReportType = new Combobox();
	private Label				lPrintFormat		= new Label(Msg.getMsg(Env.getCtx(),"PrintFormat"));
	private Label 				lReportType = new Label(Msg.getMsg(Env.getCtx(),"ReportType"));

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessPanel.class);
	
	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		m_separators = new ArrayList<Label>();
		rows = new Rows();
		//
		mainPanel = new Panel();
		parameterPanel = GridFactory.newGridLayout();
		parameterPanel.setInnerWidth(width);
		
		//setup columns
    	Columns columns = new Columns();
    	parameterPanel.appendChild(columns);
    	int colN = getColumns() * 2;
    	if(colN != 0) {
    		int percent = 99 / colN;
    		for(int i = 0; i < colN; i++) {
    			Column col = new Column();
	        	col.setWidth((i == 0
	        			? ((int) percent / 2)
	        			: percent) + "%");
	        	columns.appendChild(col);
	    	}
    	}
    	//	Add Rows
    	parameterPanel.appendChild(rows);
    	//	
    	mainLayout = new Borderlayout();
		mainLayout.setStyle("border: none;");
		//	Message Panel
		if(isShowDescription()) {
			messageDiv = new Div();
			message = new Html();
			messageDiv.appendChild(message);
			messageDiv.setStyle(MESSAGE_DIV_STYLE);
			messagePanel = new North();
			messagePanel.appendChild(messageDiv);
			messagePanel.setAutoscroll(true);
			messagePanel.setStyle("border: none;");
			mainLayout.appendChild(messagePanel);
		}
		//	Parameter Panel
		centerPanel = new Center();
		mainLayout.appendChild(centerPanel);
		centerPanel.appendChild(parameterPanel);
		centerPanel.setFlex(false);
		//	FR [ 1051 ]
		centerPanel.setStyle("border: none; overflow-y:auto;width:98%");
		
		//	Buttons Panel
		if(isShowButtons()) {
			Grid southRowPanel = GridFactory.newGridLayout();
			Rows rows = southRowPanel.newRows();
			Row row = rows.newRow();
			Hbox hBox = new Hbox();
			hBox.appendChild(lSaved);
			hBox.appendChild(fSavedName);

			Panel confParaPanel = new Panel();
			confParaPanel.setAlign("right");
			//	BR [ 300 ]
			try{
				//	Set Ok
				WAppsAction action = new WAppsAction(ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
				bOK = action.getButton();
				//	Set to Cancel
				action = new WAppsAction(ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
				bCancel = action.getButton();
				//	Set Delete
				action = new WAppsAction(ConfirmPanel.A_DELETE, null, ConfirmPanel.A_DELETE);
				bDelete = action.getButton();
				//	Add Listener
				bOK.addEventListener(Events.ON_CLICK, this);
				bCancel.addEventListener(Events.ON_CLICK, this);
				//	Add to Panel
				confParaPanel.appendChild(bCancel);
				confParaPanel.appendChild(bOK);
			} catch(Exception e) {
				log.severe("Error loading Buttons " + e.getLocalizedMessage());
			}
			
			bDelete.setEnabled(false);
			bDelete.setImage("/images/Delete16.png");
			bDelete.setSclass("action-button");
			bDelete.addActionListener(this);
			hBox.appendChild(bDelete);

			if (isReport() ) {
				Lookup lookup = listPrintFormat();
				fPrintFormat = new WTableDirEditor("AD_PrintFormat_ID", false, false, true, lookup);
				MRole roleCurrent = MRole.get(Env.getCtx(), Env.getAD_Role_ID(Env.getCtx()));
				boolean m_isAllowHTMLView = roleCurrent.isAllow_HTML_View();
				boolean m_isAllowXLSView = roleCurrent.isAllow_XLS_View();
				String typeSelection = MSysConfig.getValue("ZK_REPORT_TABLE_OUTPUT_TYPE", Env.getAD_Client_ID(Env.getCtx()));
				int selectionIndex = 0;
				if (typeSelection != null && "HTML".equals(typeSelection))
					selectionIndex = 3;

				fReportType.removeAllItems();
				fReportType.appendItem("PDF", "P");
				if ("PDF".equals(typeSelection))
					selectionIndex = 0;
				fReportType.appendItem("CSV", "C");
				if ("CSV".equals(typeSelection))
					selectionIndex = 1;

				if (m_isAllowXLSView) {
					fReportType.appendItem("Excel", "X");
					if ("XLS".equals(typeSelection))
						selectionIndex = 2;
					fReportType.appendItem("XLSX", "XX");
					if ("XLSX".equals(typeSelection))
						selectionIndex = 3;

				}
				if (m_isAllowHTMLView)
					fReportType.appendItem("HTML", "H");

				if ("HTML".equals(typeSelection))
					selectionIndex = 4;

				fReportType.setSelectedIndex(selectionIndex);

				hBox.appendChild(lPrintFormat);
				hBox.appendChild(fPrintFormat.getComponent());

				hBox.appendChild(lReportType);
				hBox.appendChild(fReportType);
			}

			row.appendChild(hBox);
			//
			row.appendChild(confParaPanel);
			
			South south = new South();
			south.appendChild(southRowPanel);
			//	Add to Main panel
			mainLayout.appendChild(south);
		}
		//	Set Text
		if(isShowDescription()) {
			message.setContent(getTextMsg());
		}
		//	
		mainPanel.appendChild(mainLayout);
		
		//mainPanel.setHeight("100%");
		mainPanel.setWidth("100%");
		parameterPanel.setWidth("97%");
		mainPanel.invalidate();
		mainPanel.setStyle("height:100%");
		mainPanel.invalidate();
		//
		loadQuerySaved();
		fSavedName.addEventListener(Events.ON_CHANGE, this);
		
		mainPanel.addEventListener(Events.ON_CANCEL, this);
		
	}
	
	/**
	 * Get Main Panel
	 * @return
	 */
	public Panel getPanel() {
		return mainPanel;
	}

	/**
	 *  Dispose
	 */
	public void dispose() {
		super.dispose();
		m_separators.clear();
		mainPanel.detach();
		if(parent != null) {
			parent.dispose();
		}
	}   //  dispose
	
	public CEditor createEditor(GridField field) {
		return(WebEditorFactory.getEditor(field, false));
	}

	public void formatEditor(CEditor editor1, CEditor editor2) {
		//
		WEditor editor = (WEditor) editor1;
		WEditor editorTo = (WEditor) editor2;
		
		configColumns(editor, editorTo);
		//	
		if(editor == null) {
			return;
		}
		//	FR [ 1061 ]
		qtyRow++;
		//streach component to fill grid cell
		editor.fillHorizontal();
        //setup editor context menu
		WEditorPopupMenu popupMenu;
        popupMenu = editor.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor);
            mainPanel.appendChild(popupMenu);
        }
		//	
    	Div div = new Div();
        div.setAlign("right");
        
        Label label = editor.getLabel();
        div.appendChild(label);
        if (label.getDecorator() != null)
        	div.appendChild(label.getDecorator());
        //	
        currentRow.appendChild(div);
        //	Add Child
        cols += 2;
		//	
        Hbox box;
        if(editorTo != null) {
        	box = new Hbox();
        	box.appendChild(editor.getComponent());
        } else {
            currentRow.appendChild(editor.getComponent());
        	m_separators.add(null);
        	return;
        }
		//	The 2nd range editor
		editorTo.fillHorizontal();
		//setup editor context menu
        popupMenu = editorTo.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editorTo);
            mainPanel.appendChild(popupMenu);
        }
        //
		Label separator = new Label(" - ");
		m_separators.add(separator);
		box.appendChild(separator);
		box.appendChild(editorTo.getComponent());
        //	Add
        currentRow.appendChild(box);
	}
	
	/**
	 * Congure columns
	 * @param editor
	 * @param editorTo
	 */
	private void configColumns(WEditor editor, WEditor editorTo) {
		int maxToAdd = getColumns() * 2;
		int columnsToAdd = getColumns();
		//	for To field
		if(editorTo != null) {
			columnsToAdd += 2;
		}
		//	Verify if is new row or not
		if((cols + columnsToAdd) > maxToAdd
				|| currentRow == null) {
			cols = 0;
			currentRow = new Row();
			rows.appendChild(currentRow);
		}
	}

	@Override
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange) {
		WEditor editor = (WEditor) getEditor(index);
		WEditor editorTo = (WEditor) getEditorTo(index);
		if (visible) {
			if (!editor.isVisible()) {
				editor.setVisible(true);  // Also makes the label visible
				if (isRange && editorTo != null) {
					m_separators.get(index).setVisible(true);
					editorTo.setVisible(true);
				}
			}
		}
		else if (editor.isVisible()) {
			editor.setVisible(false);  // Also hides the label
			if (isRange && editorTo != null) {
				m_separators.get(index).setText("");
				editorTo.setVisible(false);
			}
		}
	}
	
	/**
	 * Get a item from name in the combo box
	 * @param saveName
	 * @return
	 */
	public Comboitem getComboItem(String saveName) {
		if(saveName == null)
			return null;
		Comboitem item = null;
		for(int i = 0; i < fSavedName.getItems().size(); i++){
			if(fSavedName.getItems().get(i) != null){
				item = (Comboitem)fSavedName.getItems().get(i);
				if(saveName.equals(item.getLabel().toString())){
					break;
				}
			}
		}
		//	Return Item
		return item;
	}
	
	/**
	 * Load Combo
	 */
	private void loadQuerySaved() {
		//user query
		List<MPInstance> savedParams = getSavedInstances(true);	
		fSavedName.removeAllItems();
		for (MPInstance instance : savedParams) {
			String queries = instance.getName();
			fSavedName.appendItem(queries);
		}
		//	
		fSavedName.setValue("");
	}

	@Override
	public void lockUI(ProcessInfo pi) {
		if (isLocked) {
			return;
		}
		//	Validate Parent
		if(parent.getParentProcess() != null) {
			parent.getParentProcess().lockUI(pi);
		}
	}

	@Override
	public void unlockUI(ProcessInfo pi) {
		isLocked = false;
		if(parent.getParentProcess() != null) {
			hideBusyDialog();
			if(parent.isEmbedded()) {
				dispose();
			}
			parent.getParentProcess().unlockUI(pi);
			return;
		}
		// avoid close dialog when an report is executed
		if((isReport() && pi.isError()) || !isReport()) {
			if(progressWindow != null) {
				//move message div to center to give more space to display potentially very long log info
				parameterPanel.detach();
				centerPanel.removeChild(parameterPanel);
				centerPanel.setStyle("overflow-y:auto");	
				Html message = new Html(getLogInfo(false));
				centerPanel.appendChild(message);
				centerPanel.setAutoscroll(true);
				parent.validateScreen();
			}
		}
		//	
		hideBusyDialog();
		//	Show Result
		openResult();
		//	Hide
		if(isReport() && !pi.isError()) {
			dispose();
		}
	}

	@Override
	public boolean isUILocked() {
		return isLocked;
	}

	@Override
	public void executeASync(ProcessInfo pi) {
		
	}

	@Override
	public void onEvent(Event event) throws Exception {
		String saveName = null;
		boolean lastRun = false;
		if(fSavedName.getRawText() != null) {
			saveName = fSavedName.getRawText();
			lastRun = ("** " + Msg.getMsg(Env.getCtx(), "LastRun") + " **").equals(saveName);
		}
		//	Ok
		if (event.getTarget().equals(bOK)) {
			setIsOkPressed(true);
			if(isOnlyPanel()) {
				//	check if saving parameters is complete
				if (saveOrUpdateParameters(saveName) == null) {
					//	Save Parameters
					dispose();
				}
			} else if (isProcessed()) {
				dispose();
			} else {

				// Print format on report para
				if (fReportType != null && fReportType.getSelectedItem() != null
						&& fReportType.getSelectedItem().getValue() != null) {
					getProcessInfo().setReportType(fReportType.getSelectedItem().getValue().toString());
				}
				if (fPrintFormat != null && fPrintFormat.getValue() != null) {
					MPrintFormat format = MPrintFormat.get(Env.getCtx(), (Integer) fPrintFormat.getValue(), false);
					if (format != null) {
						if (Ini.isClient())
							getProcessInfo().setTransientObject(format);
						else
							getProcessInfo().setSerializableObject(format);
					}
				}
				//	BR [ 265 ]
				process(saveName);
			}
		} else if (event.getTarget().equals(bCancel) || event.getName().equals(Events.ON_CANCEL)) {
			dispose();
		}  else if(event.getTarget().equals(bDelete) 
				&& fSavedName != null && !lastRun) {
			Object o = fSavedName.getSelectedItem();
			if (o != null) {
				String selected = fSavedName.getSelectedItem().getLabel();
				deleteInstance(selected);
			}
			//	
			loadQuerySaved();  // #1975 - focus stays with the save parameters field
		} else if(event.getTarget().equals(fSavedName)) {
			//	Load saved parameters
			loadParameters(saveName);
			boolean enabled = !Util.isEmpty(saveName);
			bDelete.setEnabled(enabled && fSavedName.getSelectedIndex() > -1 && !lastRun);
			// #1975 - have the process run when the enter key is pressed - this creates 
			// an onOK event which will be trapped by the bOK button if it has the focus.
			bOK.setFocus(true);
		}
	}

	/**
	 * Save or Update Parameters 
	 * @param saveName
	 * @return 
	 */
	private String saveOrUpdateParameters(String saveName) {
		String saveError = null;
		boolean lastRun = ("** " + Msg.getMsg(Env.getCtx(), "LastRun") + " **").equals(saveName);
		if(fSavedName != null && !lastRun) {
			if (fSavedName.getSelectedIndex() > -1) {
				saveError = updateInstance(saveName);
				if(saveError != null) {
					FDialog.error(getWindowNo(), getPanel(), "Error", saveError);
				}
			} else {
				saveError = saveParameters(saveName);
				if(saveError != null) {
					FDialog.error(getWindowNo(), getPanel(), "Error", saveError);
				}
			}
		} else {
			saveError = saveParameters();
		}
			
		return saveError;
	}
	
	@Override
	public void afterInit() {
		//	BR [ 265 ]
		validateAutoStart();
		//	
		if(!isAutoStart()) {
			loadQuerySaved();
			// #1975 - have the process run when the enter key is pressed - this creates 
			// an onOK event which will be trapped by the bOK button if it has the focus.
			// If the buttons aren't enabled - as in the smart browser - bOK could be null.
			if (bOK != null) {
				bOK.setFocus(true);
			}
		} else if(parent.getParentProcess() == null) {
			Events.postEvent("onClick", bOK, null);
		}
	}
	
	
	public void process() {
		process(null);
	}
	
	/**
	 * Save Parameters and process it
	 */
	public void process(String saveName) {
		if(saveOrUpdateParameters(saveName) == null) {
			showBusyDialog();
			Clients.response(new AuEcho((Component) parent.getParentContainer(), "runProcess", null));
		}
	}
	
	/**
	 * Run it
	 */
	protected void runProcess() {
		getProcessInfo().setPrintPreview(true);
		
		// #1926 ZK Exports migration XML files to different location 
		// than what is selected in the dialogs. Fix is to let the process
		// know what interface is being used so it can manage the export 
		// process correctly.
		getProcessInfo().setInterfaceType(ProcessInfo.INTERFACE_TYPE_ZK);
		
		ProcessCtl worker = new ProcessCtl(this, getWindowNo(), getProcessInfo(),null);
		worker.run();
		//	Run
	}
	
	/**
	 * Show busy Dialog
	 */
	private void showBusyDialog() {
		progressWindow = new BusyDialog();
		progressWindow.setPage(((Window)parent.getParentContainer()).getPage());
		progressWindow.doHighlighted();
	}
	
	/**
	 * Hide busy Dialog
	 */
	private void hideBusyDialog() {
		if (progressWindow != null) {
			progressWindow.detach();
			progressWindow.dispose();
			progressWindow = null;
		}
	}
	
	@Override
	public String saveParameters() {
		String validError = super.saveParameters();
		if(validError != null) {
			FDialog.error(getWindowNo(), getPanel(), "FillMandatory", validError);
		}
		//	
		return validError;
	}	//	saveParameters
	
	@Override
	public String validateParameters() {
		String validError = super.validateParameters();
		if(validError != null) {
			FDialog.error(getWindowNo(), getPanel(), "FillMandatory", validError);
		}
		//	
		return validError;
	}	//	printInvoices
	
	/**
	 * Get Quantity Row
	 * @return
	 */
	public int getQtyRow() {
		return qtyRow;
	}

	@Override
	public void openResult(MQuery query) {
		AEnv.zoom(query);
	}
}	//	ProcessParameterPanel
