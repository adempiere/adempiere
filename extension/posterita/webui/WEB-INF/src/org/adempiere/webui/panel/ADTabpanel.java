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

package org.adempiere.webui.panel;

import java.util.ArrayList;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.MLookup;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Image;

/**
 * 
 * This class is based on org.compiere.grid.GridController written by Jorg Janke.
 * Changes have been brought for UI compatibility.
 * 
 * @author Jorg Janke
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 * 
 */
public class ADTabpanel extends Tabpanel implements Evaluatee, EventListener, 
DataStatusListener, ValueChangeListener
{
    
    private static final CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(ADTabpanel.class);
    }

    private Image             panelImage;
    
    private GridTab           gridTab;

    private GridWindow        gridWindow;

    private ADWindowPanel      windowPanel;

    private int               windowNo;

    private Grid              grid;
    
    private ArrayList<WEditor> editors = new ArrayList<WEditor>();
    
    private boolean           editing;

    public ADTabpanel()
    {
        init();
    }

    private void init()
    {
        initComponents();
//        this.appendChild(panelImage);
//        this.appendChild(grid);
    }

    private void initComponents()
    {
        grid = new Grid();
        grid.setWidth("750px");
        
        panelImage = new Image();
//        panelImage.setSrc("/images/loading.png");
        
    }

    public void init(ADWindowPanel winPanel, int windowNo, GridTab gridTab,
            GridWindow gridWindow)
    {
        this.windowNo = windowNo;
        this.gridWindow = gridWindow;
        this.gridTab = gridTab;
        this.windowPanel = winPanel;
        gridTab.addDataStatusListener(this);
        initGrid();
    }

    private void initGrid()
    {
    	Rows rows = new Rows();
        GridField fields[] = gridTab.getFields();
        Row row = new Row();
        for (int i = 0; i < fields.length; i++)
        {
            GridField field = fields[i];
            if (field.isDisplayed())
            {
                if (!field.isSameLine())
                {
                    if (row.getChildren().size() == 2)
                    {
                        row.appendChild(new Label(" "));
                        row.appendChild(new Label(" "));
                    }
                    rows.appendChild(row);
                    row = new Row();
                } 

                WEditor comp = WebEditorFactory.getEditor(field, false);
                
                if (comp != null) // Not heading
                {
                    comp.setGridTab(this.getGridTab());
                	field.addPropertyChangeListener(comp);
                    editors.add(comp);
                    row.appendChild(comp.getLabel());
                    row.appendChild(comp.getComponent());
                    
                    if (comp instanceof WButtonEditor)
                    {
                    	((WButtonEditor)comp).addActionListener(windowPanel);
                    }
                    else
                    {
                    	comp.addValueChangeListner(this);
                    }
                    
                    WEditorPopupMenu popupMenu = comp.getPopupMenu();
                    
                    if (popupMenu != null)
                    {
                    	popupMenu.addMenuListener((ContextMenuListener)comp);
                        this.appendChild(popupMenu);
                    }
                }
            }
        }
        if (row.getChildren().size() > 0)
        {
            if (row.getChildren().size() == 2)
            {
                row.appendChild(new Label(" "));
                row.appendChild(new Label(" "));
            }
            rows.appendChild(row);
        }
        grid.appendChild(rows);
        
    }
    
    public void dynamicDisplay (int col)
    {
        if (!gridTab.isOpen())
        {
            return;
        }
        
        //  Selective
        if (col > 0)
        {
            GridField changedField = gridTab.getField(col);
            String columnName = changedField.getColumnName();
            ArrayList dependants = gridTab.getDependantFields(columnName);
            logger.config("(" + gridTab.toString() + ") "
                + columnName + " - Dependents=" + dependants.size());
            if (dependants.size() == 0 && changedField.getCallout().length() > 0)
            {
                for (WEditor comp : editors)
                {
                    if (columnName.equals(comp.getColumnName()))
                    {
                        boolean manMissing = false;
                        boolean noValue = changedField.getValue() == null || changedField.getValue().toString().length() == 0;
                        if (noValue && changedField.isEditable(true) && changedField.isMandatory(true))
                        {
                            manMissing = true;
                        }
                        comp.setBackground(manMissing || changedField.isError());
                        break;
                    }
                }
                return;
            }
        }

        boolean noData = gridTab.getRowCount() == 0;
        logger.config(gridTab.toString() + " - Rows=" + gridTab.getRowCount());
        for (WEditor comp : editors)
        {
            GridField mField = comp.getGridField();
            if (mField != null)
            {
                if (mField.isDisplayed(true))       //  check context
                {
                    if (!comp.isVisible())
                    {
                        comp.setVisible(true);      //  visibility
                    }
                    if (noData)
                    {
                        comp.setReadWrite(false);
                    }
                    else
                    {
                        boolean rw = mField.isEditable(true);   //  r/w - check Context
                        comp.setReadWrite(rw);
                        boolean manMissing = false;
                        if (rw && mField.getValue() == null && mField.isMandatory(true))    //  check context
                        {
                            manMissing = true;
                        }
                        comp.setBackground(manMissing || mField.isError());
                    }
                }
                else if (comp.isVisible())
                {
                    comp.setVisible(false);
                }
            }
        }   //  all components
        
        logger.config(gridTab.toString() + " - fini - " + (col<=0 ? "complete" : "seletive"));
    }   //  dynamicDisplay

    public String getDisplayLogic()
    {
        return gridTab.getDisplayLogic();
    }

    public String getTitle()
    {
        return gridTab.getName();
    } // getTitle

    /**
     * @param variableName
     */
    public String get_ValueAsString(String variableName)
    {
        return Env.getContext(Env.getCtx(), windowNo, variableName);
    } // get_ValueAsString

    /**
     * @return The tab level of this Tabpanel
     */
    public int getTabLevel()
    {
        return gridTab.getTabLevel();
    }

    public boolean isCurrent()
    {
        return gridTab != null ? gridTab.isCurrent() : false;
    }

    public int getWindowNo()
    {
        return windowNo;
    }

    public void query()
    {
        gridTab.query(false);
    }
    
    public void query (boolean onlyCurrentRows, int onlyCurrentDays, int maxRows)
    {
//        gridTab.query(onlyCurrentRows, onlyCurrentDays, maxRows);
        gridTab.query(false);
    }
    
    public GridTab getGridTab()
    {
        return gridTab;
    }
    
    public void refresh()
    {
        gridTab.dataRefresh();
    }
    
    public void activate(boolean activate)
    {
        if (!this.getChildren().contains(grid))
        {
        	this.appendChild(grid);
        }
        
        grid.setVisible(activate);
    }
    
    public boolean isEditing()
    {
        return this.editing;
    }
    
    public void editRecord(boolean edit)
    {
       /* this.editing = edit;
        for (Editor editor : editors)
        {
            editor.setEditing(edit);
        }*/
    }

    public boolean isAsap()
    {
        return false;
    }

    public void onEvent(Event event)
    {
/*        if (event == null)
        	return;
        
        if (event.getTarget() == listView)
        {
        	keyRecordId = listView.getSelectedIndex();
        	
        	//Object value = gridTab.getValue(keyRecordId, keyColumnName);
        	
        	//MQuery mquery = MQuery.getEqualQuery(keyColumnName, value);
        }
        else if (confirmPanel.getButton("Ok").equals(event.getTarget()))
		{
        	gridTab.navigate(keyRecordId);
        	gridView.setVisible(false);
		}
        else if (confirmPanel.getButton("Cancel").equals(event.getTarget()))
		{
        	gridView.setVisible(false);
        	windowPanel.showTabbox(true);
		}*/
    }

    public void dataStatusChanged(DataStatusEvent e)
    {
        int col = e.getChangedColumn();
        logger.config("(" + gridTab + ") Col=" + col + ": " + e.toString());

        //  Process Callout
        GridField mField = gridTab.getField(col);
        if (mField != null 
            && (mField.getCallout().length() > 0 || gridTab.hasDependants(mField.getColumnName())))
        {
            String msg = gridTab.processFieldChange(mField);     //  Dependencies & Callout
            if (msg.length() > 0)
            {
                FDialog.error(windowNo, this, msg);
            }
            
            // Refresh the list on dependant fields
    		ArrayList list = gridTab.getDependantFields(mField.getColumnName());
    		for (int i = 0; i < list.size(); i++)
    		{
    			GridField dependentField = (GridField)list.get(i);
    		//	log.trace(log.l5_DData, "Dependent Field", dependentField==null ? "null" : dependentField.getColumnName());
    			//  if the field has a lookup
    			if (dependentField != null && dependentField.getLookup() instanceof MLookup)
    			{
    				MLookup mLookup = (MLookup)dependentField.getLookup();
    				//  if the lookup is dynamic (i.e. contains this columnName as variable)
    				if (mLookup.getValidation().indexOf("@"+mField.getColumnName()+"@") != -1)
    				{
    					mLookup.refresh();
    				}
    			}
    		}   //  for all dependent fields
            
        }
        //if (col >= 0)
        dynamicDisplay(col);
    }
    
    public void valueChange(ValueChangeEvent e)
    {
        if (gridTab.isProcessed())       //  only active records
        {
            Object source = e.getSource();
            if (source instanceof WEditor)
            {
                if (!((WEditor)source).isReadWrite())
                {
                    logger.config("(" + gridTab.toString() + ") " + e.getPropertyName());
                    return;
                }
            }
            else
            {
                logger.config("(" + gridTab.toString() + ") " + e.getPropertyName());
                return;
            }
        }   //  processed
        logger.config("(" + gridTab.toString() + ") "
            + e.getPropertyName() + "=" + e.getNewValue() + " (" + e.getOldValue() + ") "
            + (e.getOldValue() == null ? "" : e.getOldValue().getClass().getName()));
        

        //  Get Row/Col Info
        GridTable mTable = gridTab.getTableModel();
        int row = gridTab.getCurrentRow();
        int col = mTable.findColumn(e.getPropertyName());
        //
        if (e.getNewValue() == null && e.getOldValue() != null 
            && e.getOldValue().toString().length() > 0)     //  some editors return "" instead of null
            mTable.setChanged (true);
        else
        {
        //  mTable.setValueAt (e.getNewValue(), row, col, true);
           	mTable.setValueAt (e.getNewValue(), row, col);  //  -> dataStatusChanged -> dynamicDisplay
            //  Force Callout
            if ( e.getPropertyName().equals("S_ResourceAssignment_ID") )
            {
                GridField mField = gridTab.getField(col);
                if (mField != null && mField.getCallout().length() > 0)
                {
                    gridTab.processFieldChange(mField);     //  Dependencies & Callout
                }
            }
        }

    } // ValueChange 
}
