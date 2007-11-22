/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.window;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListCell;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.ToolBar;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.MainPanel;
import org.compiere.model.GridField;
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.X_AD_Column;
import org.compiere.util.AdempiereSystemError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Vbox;

/**
 *  This class is based on org.compiere.apps.search.Find written by Jorg Janke.
 *  Find/Search Records.
 *
 *  @author     Sendy Yagambrum
 *  @date       June 27, 2007
 */
public class FindWindow extends Window implements EventListener,ValueChangeListener
{
    private static final long serialVersionUID = 1L;
    /** Main Window for the Lookup Panel   */
    private MainPanel winMain;
    /**  Simple Window Tab  */
    private Window winLookupRecord;
    /** Advanced Window Tab */
    private Window winAdvanced;
    //
    private Label lblDocumentNo;
    private Label lblDescription;
    private Label lblName;
    private Label lblValue;
    //
    private Textbox fieldDocumentNo;
    private Textbox fieldDescription;
    private Textbox fieldName;
    private Textbox fieldValue;
    //
    private Listbox advancedPanel; 
    /** container of Simple Window contents   */  
    private Vbox contentSimple; 
    /** Target Window No            */
    private int             m_targetWindowNo;
    /** Table ID                    */
    private int             m_AD_Table_ID;
    /** Table Name                  */
    private String          m_tableName;
    /** Where                       */
    private String          m_whereExtended;
    /** Search Fields               */
    private GridField[]     m_findFields;
    /** Resulting query             */
    private MQuery          m_query = null;
    /** Is cancel ?                 */
    private boolean         m_isCancel = false; // teo_sarca [ 1708717 ]
    /** Logger          */
    private static CLogger log = CLogger.getCLogger(FindWindow.class);    
    /** Number of records           */
    private int             m_total;
    private PreparedStatement   m_pstmt;
    //
    private boolean         hasValue = false;
    private boolean         hasDocNo = false;
    private boolean         hasName = false;
    private boolean         hasDescription = false;
    /** Line in Simple Content      */
    private int             sLine = 6;    
    /** Value 2(to)             */
    private boolean         m_valueToColumn;
    /** Between selected        */
    private boolean         m_between = false;
    /** Editor                  */
    private WEditor         m_editor = null;    
    /** List of WEditors            */
    private ArrayList<WEditor>          m_sEditors = new ArrayList<WEditor>();
    /** Target Fields with AD_Column_ID as key  */
    private Hashtable<Integer,GridField>    m_targetFields = new Hashtable<Integer,GridField>();
    /** For Grid Controller         */
    public static final int     TABNO = 99;
    /** Length of Fields on first tab   */
    public static final int     FIELDLENGTH = 20;
        
    /**
     * FindPanel Constructor
     * @param targetWindowNo targetWindowNo 
     * @param title title
     * @param AD_Table_ID AD_Table_ID
     * @param tableName tableName 
     * @param whereExtended whereExtended
     * @param findFields findFields
     * @param minRecords minRecords
    **/    
    public FindWindow (int targetWindowNo, String title, 
            int AD_Table_ID, String tableName, String whereExtended,
            GridField[] findFields, int minRecords)
    {
        m_targetWindowNo = targetWindowNo;
        m_AD_Table_ID = AD_Table_ID;
        m_tableName = tableName;
        m_whereExtended = whereExtended;
        m_findFields = findFields;
        //
        m_query = new MQuery (m_tableName);
        m_query.addRestriction(m_whereExtended);
        //  Required for Column Validation
        Env.setContext(Env.getCtx(), m_targetWindowNo, "Find_Table_ID", m_AD_Table_ID);
        //  Context for Advanced Search Grid is WINDOW_FIND
        Env.setContext(Env.getCtx(), Env.WINDOW_FIND, "Find_Table_ID", m_AD_Table_ID);
        //
        initPanel();
        initFind();
        initFindAdvanced();
        if (m_total < minRecords)
        {
            return;
        }
        this.appendChild(winMain);
        this.setBorder("normal");
        this.setWidth("550px");
        this.setTitle("Lookup Record: "+ title);
        this.setAttribute("mode", "modal");
        this.setClosable(true);
    }
    /**
     * initialise lookup record tab
     *
    **/
    private void initSimple()
    {
        lblDocumentNo = new Label();
        lblDocumentNo.setValue(Msg.translate(Env.getCtx(),"DocumentNo").substring(1));
        
        lblDescription = new Label();
        lblDescription.setValue(Msg.translate(Env.getCtx(),"Description"));
        
        lblName = new Label();
        lblName.setValue(Msg.translate(Env.getCtx(),"Name").substring(1));
        
        lblValue = new Label();
        lblValue.setValue(Msg.translate(Env.getCtx(),"Value").substring(1));
        
        fieldDocumentNo = new Textbox();
        fieldDocumentNo.setId("fieldDocumentNo");
        fieldDocumentNo.setMaxlength(40);
                
        fieldDescription = new Textbox();
        fieldDescription.setId("fieldDescription");
        fieldDescription.setMaxlength(40);
        
        fieldName = new Textbox();
        fieldName.setMaxlength(40);
        
        fieldValue = new Textbox();
        fieldValue.setMaxlength(40);       
                
        Button btnNew = new Button();
        btnNew.setName("btnNew");
        btnNew.setSrc("/images/New24.gif");
        btnNew.addEventListener(Events.ON_CLICK,this);
        
        Button btnOk = new Button();
        btnOk.setName("btnOkSimple");
        btnOk.setSrc("/images/Ok24.gif");
        btnOk.addEventListener(Events.ON_CLICK,this);
        
        Button btnCancel = new Button();
        btnCancel.setName("btnCancel");
        btnCancel.setSrc("/images/Cancel24.gif");
        btnCancel.addEventListener(Events.ON_CLICK,this);
        
        Panel pnlButtonRight = new Panel();
        pnlButtonRight.appendChild(btnOk);
        pnlButtonRight.appendChild(btnCancel);
        pnlButtonRight.setAlign("right");
        pnlButtonRight.setWidth("100%");
        
        Panel pnlButtonLeft = new Panel();
        pnlButtonLeft.appendChild(btnNew);
        
        Hbox hboxButton = new Hbox();
        hboxButton.appendChild(pnlButtonLeft);
        hboxButton.appendChild(pnlButtonRight);
        hboxButton.setWidth("100%");
        
        Panel pnlDocument = new Panel();
        pnlDocument.setId("pnlDocument");
        pnlDocument.appendChild(lblDocumentNo);
        pnlDocument.appendChild(fieldDocumentNo);
        pnlDocument.setWidth("70%");
        pnlDocument.setAlign("right");
        
        Panel pnlDescription = new Panel();
        pnlDescription.appendChild(lblDescription);
        pnlDescription.appendChild(fieldDescription);
        pnlDescription.setWidth("70%");
        pnlDescription.setAlign("right");
        
        Panel pnlValue = new Panel();
        pnlValue.appendChild(lblValue);
        pnlValue.appendChild(fieldValue);
        pnlValue.setWidth("70%");
        pnlValue.setAlign("right");
        
        Panel pnlName = new Panel();
        pnlName.appendChild(lblName);
        pnlName.appendChild(fieldName);
        pnlName.setWidth("70%");
        pnlName.setAlign("right");
        
        contentSimple = new Vbox();
        contentSimple.setId("contentSimple");
        contentSimple.setWidth("100%");
        contentSimple.setStyle("padding:10px; text-align:left");
       
        contentSimple.appendChild(pnlValue);
        contentSimple.appendChild(pnlName);
        contentSimple.appendChild(pnlDocument);
        contentSimple.appendChild(pnlDescription);               
       
        winLookupRecord.appendChild(contentSimple);
        winLookupRecord.appendChild(hboxButton);
        winLookupRecord.setWidth("100%");     
        winLookupRecord.addEventListener(Events.ON_OK, this);
        
    }   //  initSimple
    
    /**
     * initialise Advanced Tab
     *
    **/
    private void initAdvanced()
    {
        ToolBarButton btnNew = new ToolBarButton();
        btnNew.setSrc("/images/New24.gif");
        btnNew.setAttribute("name", "btnNewAdv");
        btnNew.addEventListener(Events.ON_CLICK, this);
        
        ToolBarButton btnDelete = new ToolBarButton();
        btnDelete.setAttribute("name","btnDeleteAdv");
        btnDelete.setSrc("/images/Delete24.gif");
        btnDelete.addEventListener(Events.ON_CLICK, this);

        Button btnOk = new Button();
        btnOk.setName("btnOkAdv");
        btnOk.setSrc("/images/Ok24.gif");
        btnOk.addEventListener(Events.ON_CLICK, this);
        
        Button btnCancel = new Button();
        btnCancel.setName("btnCancel");
        btnCancel.setSrc("/images/Cancel24.gif");
        btnCancel.addEventListener(Events.ON_CLICK, this);
        
        Panel pnlButtonRight = new Panel();
        pnlButtonRight.appendChild(btnOk);
        pnlButtonRight.appendChild(btnCancel);
        pnlButtonRight.setAlign("right");
            
        ToolBar toolBar = new ToolBar();
        toolBar.appendChild(btnNew);
        toolBar.appendChild(btnDelete);
        toolBar.setWidth("100%");
        
        Hbox confirmPanel = new Hbox();
        confirmPanel.appendChild(pnlButtonRight);
        confirmPanel.setWidth("100%");      
        
        advancedPanel = new Listbox();
        ListHead listhead = new ListHead();
        listhead.setSizable(true);
        
        ListHeader lstHColumn = new ListHeader();
        lstHColumn.setLabel("Column");    
        lstHColumn.setWidth("100px");
               
        ListHeader lstHOperator = new ListHeader();
        lstHOperator.setLabel("Operator");
        
        ListHeader lstHQueryValue = new ListHeader();
        lstHQueryValue.setLabel("Query Value");
        lstHQueryValue.setWidth("200px");
        
        ListHeader lstHQueryTo = new ListHeader();
        lstHQueryTo.setLabel("To Query Value");
        lstHQueryTo.setWidth("200px");
        
        listhead.appendChild(lstHColumn);
        listhead.appendChild(lstHOperator);
        listhead.appendChild(lstHQueryValue);
        listhead.appendChild(lstHQueryTo);        
        advancedPanel.appendChild(listhead);                   
        
        Vbox advancedWindow = new Vbox();
        advancedWindow.setWidth("100%");
        advancedWindow.appendChild(toolBar);
        advancedWindow.appendChild(advancedPanel);
        advancedWindow.appendChild(confirmPanel);
        winAdvanced.appendChild(advancedWindow);  
        winAdvanced.addEventListener(Events.ON_OK,this);
        
    } // initAdvanced
    
    /**
     * initialise Main Window
     *
    **/
    private void initPanel()
    {
        winMain = new MainPanel();
        winMain.setWidth("100%");
        winAdvanced = new Window();
        winLookupRecord = new Window();     
        winMain.addWindow(winLookupRecord, "Lookup Record",false, true);
        winMain.addWindow(winAdvanced, "Advanced", false, false);
        initSimple();
        initAdvanced();        
        
    } // initPanel
    
    /**
     *  Dynamic Init.6
     *  Set up GridController
    **/
    private void initFind()
    {
        log.config("");

        //  Get Info from target Tab
        for (int i = 0; i < m_findFields.length; i++)
        {
            GridField mField = m_findFields[i];
            String columnName = mField.getColumnName();

            if (columnName.equals("Value"))
                hasValue = true;
            else if (columnName.equals("Name"))
                hasName = true;
            else if (columnName.equals("DocumentNo"))
                hasDocNo = true;
            else if (columnName.equals("Description"))
                hasDescription = true;
            else if (mField.isSelectionColumn())
                addSelectionColumn (mField);
            else if (columnName.indexOf("Name") != -1)
                addSelectionColumn (mField);

            //  TargetFields
            m_targetFields.put (new Integer(mField.getAD_Column_ID()), mField);
        }   //  for all target tab fields

        //  Disable simple query fields
        lblValue.setVisible(hasValue);
        fieldValue.setVisible(hasValue);
        if (hasValue)
            fieldValue.addEventListener(Events.ON_CHANGE,this);
        lblDocumentNo.setVisible(hasDocNo);
        fieldDocumentNo.setVisible(hasDocNo);
        if (hasDocNo)
            fieldDocumentNo.addEventListener(Events.ON_CHANGE,this);
        lblName.setVisible(hasName);
        fieldName.setVisible(hasName);
        if (hasName)
            fieldName.addEventListener(Events.ON_CHANGE,this);
        lblDescription.setVisible(hasDescription);
        fieldDescription.setVisible(hasDescription);
        if (hasDescription)
            fieldDescription.addEventListener(Events.ON_CHANGE,this);
        
        m_total = getNoOfRecords(null, false);
        
    }   //  initFind
    
    /**
     *  initialise Advanced tab
    **/
    private void initFindAdvanced()
    {
        log.config("");
        createFields();
             
    }   //  initFindAdvanced
    
    /**
     * create respective fields in the advanced window tab
     *
    **/
    private void createFields()
    {
        ListItem listItem = new ListItem();
        
        Listbox listColumn = new Listbox();
        listColumn.setId("listColumn"+listItem.getId());
        listColumn.setName("listColumn");
        listColumn.setMold("select");
        listColumn.setWidth("150px");
        listColumn.setRows(0);
        listColumn.addEventListener(Events.ON_SELECT,this);
        
        Listbox listOperator = new Listbox();
        listOperator.setId("listOperator"+listItem.getId());
        listOperator.setName("listOperator");        
        listOperator.setMold("select");
        listOperator.setWidth("50px");
        listOperator.setRows(0);  
        listOperator.addEventListener(Events.ON_SELECT,this);
        
        setValues(listColumn, listOperator);
                   
        ListCell cellColumn = new ListCell();
        cellColumn.appendChild(listColumn);
        cellColumn.setId("cellColumn"+listItem.getId());
        
        ListCell cellOperator = new ListCell();
        cellOperator.appendChild(listOperator);
        cellOperator.setId("cellOperator"+listItem.getId());
        
        ListCell cellQueryFrom = new ListCell();
        cellQueryFrom.setId("cellQueryFrom"+listItem.getId());
        
        ListCell cellQueryTo = new ListCell();
        cellQueryTo.setId("cellQueryTo"+listItem.getId());
        
        listItem.appendChild(cellColumn);
        listItem.appendChild(cellOperator);
        listItem.appendChild(cellQueryFrom);
        listItem.appendChild(cellQueryTo);
        
        advancedPanel.appendChild(listItem);
        advancedPanel.setSelectedItem(listItem);
        
   }    // createFields
    /**
     * sets the list of values of column and operator listboxes
     * @param listColumn column
     * @param listOperator operator
    **/
    private void setValues(Listbox listColumn, Listbox listOperator)
    {
        //  0 = Columns
        ArrayList<ValueNamePair> items = new ArrayList<ValueNamePair>();
        for (int c = 0; c < m_findFields.length; c++)
        {
            GridField field = m_findFields[c];
            String columnName = field.getColumnName();
            String header = field.getHeader();
            if (header == null || header.length() == 0)
            {
                header = Msg.translate(Env.getCtx(), columnName);
                
                if (header == null || header.length() == 0)
                    continue;
            }
            if (field.isKey())
                header += (" (ID)");
            ValueNamePair pp = new ValueNamePair(columnName, header);
            items.add(pp);
        }
        ValueNamePair[] cols = new ValueNamePair[items.size()];
        items.toArray(cols);
        Arrays.sort(cols);      //  sort alpha
        
        listColumn.appendItem("","" );
        for (ValueNamePair item: cols)
        {
            listColumn.appendItem(item.getName(), item.getValue());
        }
        listColumn.setSelectedIndex(0);
        
        ValueNamePair[] op = MQuery.OPERATORS;
        
        for (ValueNamePair item: op)
        {
            listOperator.appendItem(item.getName(), item.getValue());
        }
        listOperator.setSelectedIndex(0);
    }   // setValues
    
    /**
     *  Add Selection Column to first Tab
     *  @param mField field
    **/
    public void addSelectionColumn(GridField mField)
    {
        log.config(mField.getHeader());
        int displayLength = mField.getDisplayLength();
        if (displayLength <= 0 || displayLength > FIELDLENGTH)
            mField.setDisplayLength(FIELDLENGTH);
        else
            displayLength = 0;
        
        //  Editor
        WEditor editor = null;
        if (mField.isLookup())
        {
            WTableDirEditor wd = new WTableDirEditor(mField);
            editor = wd;
        }
        else
        {
            editor = WebEditorFactory.getEditor(mField, false);
            editor.setMandatory(false);
            editor.setReadWrite(true);
        }
        Label label = editor.getLabel();
        Component fieldLabel = editor.getComponent();
              
        //
        if (displayLength > 0)      //  set it back
            mField.setDisplayLength(displayLength);
        //
        Panel panel = new Panel();
        panel.setWidth("70%");
        panel.setAlign("right");
        panel.appendChild(label);
        panel.appendChild(fieldLabel);
        
        contentSimple.appendChild(panel);
        m_sEditors.add(editor);
        
    }   // addSelectionColumn
    
    public void onEvent(Event event) throws Exception
    {
        if ("onSelect".equals(event.getName()))
        {
            if (event.getTarget() instanceof Listbox)
            {
                ListItem row = (ListItem)(event.getTarget().getParent().getParent());
                Listbox listbox = (Listbox)event.getTarget();
                advancedPanel.setSelectedItem(row);
                Listbox listColumn = (Listbox)row.getFellow("listColumn"+row.getId());
                Listbox listOperator = (Listbox)row.getFellow("listOperator"+row.getId());
                
                if (listbox.getId().equals(listColumn.getId()))
                {
                    ListItem column = listColumn.getSelectedItem();
                    if (column != null)
                    {
                        addOperators(column, listOperator);
                    }
                }
                Component componentFrom = getEditorCompQueryFrom(row);
                Component componentTo = getEditorCompQueryTo(row);
               
                addRowEditor(componentFrom, (ListCell)row.getFellow("cellQueryFrom"+row.getId()));
                addRowEditor(componentTo,(ListCell)row.getFellow("cellQueryTo"+row.getId()));
            }
        }   //
        else if ("onClick".equals(event.getName()))
        {
            //  Toolbar Buttons actions
            if(event.getTarget() instanceof ToolBarButton)
            {
                ToolBarButton button = (ToolBarButton)event.getTarget();
                
                if ("btnNewAdv".equals(button.getAttribute("name").toString()))
                {
                    initFindAdvanced();
                }
                
                else if ("btnDeleteAdv".equals(button.getAttribute("name").toString()))
                {
                   
                    int index = advancedPanel.getSelectedIndex();   
                    advancedPanel.getSelectedItem().detach();
                    advancedPanel.setSelectedIndex(--index);
                }
            }
            //  Confirm panel actions
            else if(event.getTarget() instanceof Button)
            {
                Button btn = (Button)event.getTarget();
                
                if ("btnOkSimple".equals(btn.getName()))
                {
                    cmd_ok_Simple();
                    dispose();                 
                }
                else if ("btnOkAdv".equals(btn.getName()))
                {
                    cmd_ok_Advanced();
                    dispose();
                }
                else if("btnCancel".equals(btn.getName()))
                {
                    dispose();
                }
                else if ("btnNew".equals(btn.getName()))
                {
                    m_query = MQuery.getNoRecordQuery(m_tableName, true);
                    m_total = 0;
                    dispose();
                }
            }
            else if (event.getTarget() instanceof Label)
            {
                Label label = (Label)event.getTarget();
                ListCell listcell = (ListCell)label.getParent();
                ListItem row = (ListItem)listcell.getParent();
                advancedPanel.setSelectedItem(row);                
                
                if (listcell.getId().equals("cellQueryFrom"+row.getId()))
                {
                    Component component = getEditorCompQueryFrom(row);
                    addRowEditor(component, listcell);
                }
                else if (listcell.getId().equals("cellQueryTo"+row.getId()))
                {
                    Component component = getEditorCompQueryTo(row);
                    addRowEditor(component,listcell);
                }
            }
        }   
        else if ("onOK".equals(event.getName()))
        {
            if (winLookupRecord.equals(event.getTarget()))
            {
                cmd_ok_Simple();
                dispose();
            }
            else if (winAdvanced.equals(event.getTarget()))
            {
                cmd_ok_Advanced();
                dispose();
            }
        }
        
    }   //  onEvent
    
    /**
     * retrieve the columnName of the Column item selected
     * @param label label
    **/
    private String getColumnName(ListItem row)
    {
        /*List list = row.getChildren();
        Panel pnlColumn = (Panel)list.get(0);
        List lstColumn = pnlColumn.getChildren();*/
        Listbox listColumn = (Listbox)row.getFellow("listColumn"+row.getId());
        String columnName = listColumn.getSelectedItem().getValue().toString();
        
        return columnName;
    
    }   // getColumnName
    
    /**
     *  get editor component for 'query' field 
     * @param row   row
     * @return  editor component
    **/
    private Component getEditorCompQueryFrom(ListItem row)
    {
        m_valueToColumn = false;
        return getEditorComponent(row);
    }
    
    /**
     *  get editor component for 'query to' field 
     * @param row   row
     * @return  editor component
    **/
    private Component getEditorCompQueryTo(ListItem row)
    {
        m_valueToColumn = true;
        return getEditorComponent(row);
    }
    
    /**
     * add the editor component in the 'QueryValue' field
     * @param component editor component
     * @param label label to replace by editor component
    **/
    private void addRowEditor(Component component, ListCell listcell)
    {
        listcell.setLabel("");
        listcell.getChildren().clear();
        listcell.appendChild(component); 
        
     }   //  addComponent
    
    /**
     *    Retrieve operators depending on the item selected in the 'Column' field
     *    and add them to the selection
     *    @param column Column field selected
    **/               
    private void addOperators(ListItem column, Listbox listOperator)
    {
        String columnName = column.getValue().toString();
        log.config("Column: " + columnName);
        
        if (columnName.endsWith("_ID") || columnName.endsWith("_Acct"))
        {
             addOperators(MQuery.OPERATORS_ID, listOperator);              
        }
        else if (columnName.startsWith("Is"))
        {
            addOperators(MQuery.OPERATORS_YN, listOperator);            
        }
        else
        {
            addOperators(MQuery.OPERATORS, listOperator);                             
        }
    } //    addOperators
    
    /**
     * add Operators
     * @param op array of operators
    **/
    private void addOperators(ValueNamePair[] op, Listbox listOperator)
    {
        List itemList = listOperator.getChildren();
        itemList.clear();
        for (ValueNamePair item: op)
        {
            listOperator.appendItem(item.getName(), item.getValue());
        }
        listOperator.setSelectedIndex(0);
    }   //  addOperators
    
    /**
     *  Get Editor
     *  @param row row
     *  @return Editor component
    **/
    public Component getEditorComponent(ListItem row)
    {
        String columnName = getColumnName(row);
        m_between = false;
        Listbox listOp = (Listbox) row.getFellow("listOperator"+row.getId());
        String betweenValue = listOp.getSelectedItem().getValue().toString();
        String opValue = MQuery.OPERATORS[MQuery.BETWEEN_INDEX].getValue();
        if (m_valueToColumn &&  betweenValue != null 
            && betweenValue.equals(opValue))
            m_between = true;

        boolean enabled = !m_valueToColumn || (m_valueToColumn && m_between); 
        
        //  Create Editor
        GridField field = getTargetMField(columnName);
    //  log.fine( "Field=" + field.toStringX());
        if (field.isKey())
            m_editor = new WNumberEditor(field);
        else
            m_editor = WebEditorFactory.getEditor(field, true);
        if (m_editor == null)
            m_editor = new WStringEditor(field);
        field.addPropertyChangeListener(m_editor);
        m_editor.addValueChangeListner(this);
        m_editor.setValue(null);
        m_editor.setReadWrite(enabled);
        m_editor.setVisible(enabled);
        //
        return m_editor.getComponent();
        
    }   //  getTableCellEditorComponent
    
    /**
     *  Get Target MField
     *  @param columnName column name
     *  @return MField
    **/
    public GridField getTargetMField (String columnName)
    {
        if (columnName == null)
            return null;
        for (int c = 0; c < m_findFields.length; c++)
        {
            GridField field = m_findFields[c];
            if (columnName.equals(field.getColumnName()))
                return field;
        }
        return null;
        
    }   //  getTargetMField
    
    /**
     *  Simple OK Button pressed
    **/
    private void cmd_ok_Simple()
    {
        //  Create Query String
        m_query = new MQuery(m_tableName);
        if (hasValue && !fieldValue.getText().equals("%") && fieldValue.getText().length() != 0)
        {
            String value = fieldValue.getText().toUpperCase();
            
            if (!value.endsWith("%"))
                value += "%";
            m_query.addRestriction("UPPER(Value)", MQuery.LIKE, value, lblValue.getValue(), value);
        }
        //
        if (hasDocNo && !fieldDocumentNo.getText().equals("%") && fieldDocumentNo.getText().length() != 0)
        {
            String value = fieldDocumentNo.getText().toUpperCase();
            
            if (!value.endsWith("%"))
                value += "%";
            m_query.addRestriction("UPPER(DocumentNo)", MQuery.LIKE, value, lblDocumentNo.getValue(),value);
        }
        //
        if ((hasName) && !fieldName.getText().equals("%") && fieldName.getText().length() != 0)
        {
            String value = fieldName.getText().toUpperCase();
            
            if (!value.endsWith("%"))
                value += "%";
            m_query.addRestriction("UPPER(Name)", MQuery.LIKE, value, lblName.getValue(), value);
        }
        //
        if (hasDescription && !fieldDescription.getText().equals("%") && fieldDescription.getText().length() != 0)
        {
            String value = fieldDescription.getText().toUpperCase();
            if (!value.endsWith("%"))
                value += "%";
            m_query.addRestriction("UPPER(Description)", MQuery.LIKE, value, lblDescription.getValue(), value);
        }
        //  Special Editors
        for (int i = 0; i < m_sEditors.size(); i++)
        {
            WEditor wed = (WEditor)m_sEditors.get(i);
            Object value = wed.getValue();
            if (value != null && value.toString().length() > 0)
            {
                String ColumnName = wed.getColumnName();
                log.fine(ColumnName + "=" + value);
                
                // globalqss - Carlos Ruiz - 20060711
                // fix a bug with virtualColumn + isSelectionColumn not yielding results
                GridField field = getTargetMField(ColumnName);
                boolean isProductCategoryField = isProductCategoryField(field.getAD_Column_ID());
                String ColumnSQL = field.getColumnSQL(false);
                if (value.toString().indexOf('%') != -1)
                    m_query.addRestriction(ColumnSQL, MQuery.LIKE, value, ColumnName, wed.getDisplay());
                else if (isProductCategoryField && value instanceof Integer) 
                    m_query.addRestriction(getSubCategoryWhereClause(((Integer) value).intValue()));
                else
                    m_query.addRestriction(ColumnSQL, MQuery.EQUAL, value, ColumnName, wed.getDisplay());
                /*
                if (value.toString().indexOf('%') != -1)
                    m_query.addRestriction(ColumnName, MQuery.LIKE, value, ColumnName, ved.getDisplay());
                else
                    m_query.addRestriction(ColumnName, MQuery.EQUAL, value, ColumnName, ved.getDisplay());
                */
                // end globalqss patch
            }
        }   //  editors

        m_isCancel = false; // teo_sarca [ 1708717 ]
        //  Test for no records
        if (getNoOfRecords(m_query, true) != 0)
          dispose();
        
    }   //  cmd_ok_Simple
    
    private void dispose()
    {
        log.config("");

        //  Find SQL
        if (m_pstmt != null)
        {
            try {
                m_pstmt.close();
            } catch (SQLException e)    {}
        }
        m_pstmt = null;

        
        //  TargetFields
        if (m_targetFields != null)
            m_targetFields.clear();
        m_targetFields = null;
        //
        this.detach();
    }   //  dispose

    /**
     *  Advanced OK Button pressed
     */
    private void cmd_ok_Advanced()
    {
        m_isCancel = false; // teo_sarca [ 1708717 ]
        //  save pending
        cmd_save();
        if (getNoOfRecords(m_query, true) != 0)
          dispose();
    }   //  cmd_ok_Advanced
    
    /**
     *  Save (Advanced)
     */
    private void cmd_save()
    {
        log.info("");
        //
        m_query = new MQuery(m_tableName);
        List rowList = advancedPanel.getChildren();
        
        for (int rowIndex = 1; rowIndex < rowList.size() ; rowIndex++)
        {
            //  Column
            ListItem row = (ListItem)rowList.get(rowIndex);
            Listbox column = (Listbox)row.getFellow("listColumn"+row.getId()); 
            if (column == null)
                continue;
            String ColumnName = column.getSelectedItem().getValue().toString();
            String infoName = column.toString();
            //
            GridField field = getTargetMField(ColumnName);
            boolean isProductCategoryField = isProductCategoryField(field.getAD_Column_ID());
            String ColumnSQL = field.getColumnSQL(false);
            //  Op
            Listbox op = (Listbox)row.getFellow("listOperator"+row.getId());
            if (op == null)
                continue;
            String Operator = op.getSelectedItem().getValue().toString();
            
            //  Value   ******
            ListCell cellQueryFrom = (ListCell)row.getFellow("cellQueryFrom"+row.getId());
            Label labelFrom = (Label)(cellQueryFrom.getChildren().get(0));
            Object value = labelFrom.getAttribute("value");
            if (value == null)
                continue;
            Object parsedValue = parseValue(field, value);
            if (parsedValue == null)
                continue;
            String infoDisplay = value.toString();
            if (field.isLookup())
                infoDisplay = field.getLookup().getDisplay(value);
            else if (field.getDisplayType() == DisplayType.YesNo)
                infoDisplay = Msg.getMsg(Env.getCtx(), infoDisplay);
            //  Value2  ******
            if (MQuery.OPERATORS[MQuery.BETWEEN_INDEX].equals(op))
            {
                ListCell cellQueryTo = (ListCell)row.getFellow("cellQueryTo"+row.getId());
                Label labelTo = (Label)(cellQueryTo.getChildren().get(0));
                Object value2 = labelTo.getAttribute("value");
                if (value2 == null)
                    continue;
                Object parsedValue2 = parseValue(field, value2);
                String infoDisplay_to = value2.toString();
                if (parsedValue2 == null)
                    continue;
                m_query.addRangeRestriction(ColumnSQL, parsedValue, parsedValue2,
                    infoName, infoDisplay, infoDisplay_to);
            }
            else if (isProductCategoryField && MQuery.OPERATORS[MQuery.EQUAL_INDEX].equals(op)) {
                if (!(parsedValue instanceof Integer)) {
                    continue;
                }
                m_query

                .addRestriction(getSubCategoryWhereClause(((Integer) parsedValue).intValue()));
            }
            else
                m_query.addRestriction(ColumnSQL, Operator, parsedValue,
                    infoName, infoDisplay);
        }
    }   //  cmd_save

    /**
     *  Get the number of records of target tab
     *  @param query where clause for target tab
     *  @param alertZeroRecords show dialog if there are no records
     *  @return number of selected records;
     *          if the results are more then allowed this method will return 0
    **/
    private int getNoOfRecords (MQuery query, boolean alertZeroRecords)
    {
        log.config("" + query);
        StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ");
        sql.append(m_tableName);
        boolean hasWhere = false;
        if (m_whereExtended != null && m_whereExtended.length() > 0)
        {
            sql.append(" WHERE ").append(m_whereExtended);
            hasWhere = true;
        }
        if (query != null && query.isActive())
        {
            if (hasWhere)
                sql.append(" AND ");
            else
                sql.append(" WHERE ");
            sql.append(query.getWhereClause());
        }
        //  Add Access
        String finalSQL = MRole.getDefault().addAccessSQL(sql.toString(), 
            m_tableName, MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
        finalSQL = Env.parseContext(Env.getCtx(), m_targetWindowNo, finalSQL, false);
        Env.setContext(Env.getCtx(), m_targetWindowNo, TABNO, "FindSQL", finalSQL);

        //  Execute Qusery
        m_total = 999999;
        try
        {
            Statement stmt = DB.createStatement();
            ResultSet rs = stmt.executeQuery(finalSQL);
            if (rs.next())
                m_total = rs.getInt(1);
            rs.close();
            stmt.close();
        }
        catch (SQLException e)
        {
            log.log(Level.SEVERE, finalSQL, e);
        }
        MRole role = MRole.getDefault(); 
        //  No Records
      /*  if (m_total == 0 && alertZeroRecords)
            FDialog.warn(m_targetWindowNo, this, "FindZeroRecords");*/
        //  More then allowed
        if (query != null && role.isQueryMax(m_total))
        {
            FDialog.error(m_targetWindowNo, this, "FindOverMax", 
                m_total + " > " + role.getMaxQueryRecords());
            m_total = 0; // return 0 if more then allowed - teo_sarca [ 1708717 ]
        }
        else
            log.config("#" + m_total);
        //
        /*if (query != null)
            statusBar.setStatusToolTip (query.getWhereClause());*/
        return m_total;
        
    }   //  getNoOfRecords

    /**
     * Checks the given column.
     * @param columnId
     * @return true if the column is a product category column
    **/
    private boolean isProductCategoryField(int columnId) {
        X_AD_Column col = new X_AD_Column(Env.getCtx(), columnId, null);
        if (col.get_ID() == 0) {
            return false; // column not found...
        }
        return MProduct.COLUMNNAME_M_Product_Category_ID.equals(col.getColumnName());
        
    }   //  isProductCategoryField

    /**
     * Returns a sql where string with the given category id and all of its subcategory ids.
     * It is used as restriction in MQuery.
     * @param productCategoryId
     * @return
    **/
    private String getSubCategoryWhereClause(int productCategoryId) {
        //if a node with this id is found later in the search we have a loop in the tree
        int subTreeRootParentId = 0;
        String retString = " M_Product_Category_ID IN (";
        String sql = " SELECT M_Product_Category_ID, M_Product_Category_Parent_ID FROM M_Product_Category";
        final Vector<SimpleTreeNode> categories = new Vector<SimpleTreeNode>(100);
        try {
            Statement stmt = DB.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getInt(1)==productCategoryId) {
                    subTreeRootParentId = rs.getInt(2);
                }
                categories.add(new SimpleTreeNode(rs.getInt(1), rs.getInt(2)));
            }
            retString += getSubCategoriesString(productCategoryId, categories, subTreeRootParentId);
            retString += ") ";
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            log.log(Level.SEVERE, sql, e);
            retString = "";
        } catch (AdempiereSystemError e) {
            log.log(Level.SEVERE, sql, e);
            retString = "";
        }
        return retString;
        
    }   //  getSubCategoryWhereClause

    /**
     * Recursive search for subcategories with loop detection.
     * @param productCategoryId
     * @param categories
     * @param loopIndicatorId
     * @return comma seperated list of category ids
     * @throws AdempiereSystemError if a loop is detected
    **/
    private String getSubCategoriesString(int productCategoryId, Vector<SimpleTreeNode> categories, int loopIndicatorId) throws AdempiereSystemError {
        String ret = "";
        final Iterator iter = categories.iterator();
        while (iter.hasNext()) {
            SimpleTreeNode node = (SimpleTreeNode) iter.next();
            if (node.getParentId() == productCategoryId) {
                if (node.getNodeId() == loopIndicatorId) {
                    throw new AdempiereSystemError("The product category tree contains a loop on categoryId: " + loopIndicatorId);
                }
                ret = ret + getSubCategoriesString(node.getNodeId(), categories, loopIndicatorId) + ",";
            }
        }
        log.fine(ret);
        return ret + productCategoryId;
        
    }   //  getSubCategoriesString

    /**
     * Simple tree node class for product category tree search.
     * @author Karsten Thiemann, kthiemann@adempiere.org
     *
    **/
    private class SimpleTreeNode {

        private int nodeId;

        private int parentId;

        public SimpleTreeNode(int nodeId, int parentId) {
            this.nodeId = nodeId;
            this.parentId = parentId;
        }

        public int getNodeId() {
            return nodeId;
        }

        public int getParentId() {
            return parentId;
        }
    }   //  SimpleTreeNode
    
    /**
     *  Parse Value
     *  @param field column
     *  @param in value
     *  @return data type corected value
    **/
    private Object parseValue (GridField field, Object in)
    {
        if (in == null)
            return null;
        int dt = field.getDisplayType();
        try
        {
            //  Return Integer
            if (dt == DisplayType.Integer
                || (DisplayType.isID(dt) && field.getColumnName().endsWith("_ID")))
            {
                if (in instanceof Integer)
                    return in;
                int i = Integer.parseInt(in.toString());
                return new Integer(i);
            }
            //  Return BigDecimal
            else if (DisplayType.isNumeric(dt))
            {
                if (in instanceof BigDecimal)
                    return in;
                return DisplayType.getNumberFormat(dt).parse(in.toString());
            }
            //  Return Timestamp
            else if (DisplayType.isDate(dt))
            {
                if (in instanceof Timestamp)
                    return in;
                long time = 0;
                try
                {
                    time = DisplayType.getDateFormat_JDBC().parse(in.toString()).getTime();
                    return new Timestamp(time);
                }
                catch (Exception e)
                {
                    log.log(Level.SEVERE, in + "(" + in.getClass() + ")" + e);
                    time = DisplayType.getDateFormat(dt).parse(in.toString()).getTime();
                }
                return new Timestamp(time);
            }
            //  Return Y/N for Boolean
            else if (in instanceof Boolean)
                return ((Boolean)in).booleanValue() ? "Y" : "N";
        }
        catch (Exception ex)
        {
            log.log(Level.SEVERE, "Object=" + in, ex);
            String error = ex.getLocalizedMessage();
            if (error == null || error.length() == 0)
                error = ex.toString();
            StringBuffer errMsg = new StringBuffer();
            errMsg.append(field.getColumnName()).append(" = ").append(in).append(" - ").append(error);
            //
            FDialog.error(0, this, "ValidationError", errMsg.toString());
            return null;
        }

        return in;
    }   //  parseValue
    
    /**************************************************************************
     *  Get Query - Retrieve result
     *  @return String representation of query
     */
    public MQuery getQuery()
    {
        MRole role = MRole.getDefault();
        if (role.isQueryMax(getTotalRecords()) && !m_isCancel)
        {
            m_query = MQuery.getNoRecordQuery (m_tableName, false);
            m_total = 0;
            log.warning("Query - over max");
        }
        else
            log.info("Query=" + m_query);
        return m_query;
    }   //  getQuery
    
    /**
     *  Get Total Records
     *  @return no of records
    **/
    public int getTotalRecords()
    {
        return m_total;
        
    }   //  getTotalRecords
    
    public void valueChange(ValueChangeEvent evt)
    {
        if (evt != null && evt.getSource() instanceof WEditor)
        {
            WEditor editor = (WEditor)evt.getSource();
            // Editor component
            Component component = editor.getComponent();
            
            ListCell listcell = (ListCell)component.getParent();
            Label label = new Label(evt.getNewValue().toString());
            label.setAttribute("value", evt.getNewValue());
            
            listcell.appendChild(label);
            listcell.removeChild(component);
            label.addEventListener(Events.ON_CLICK,this);            
        }
    }
    
    
}   //  FindPanel