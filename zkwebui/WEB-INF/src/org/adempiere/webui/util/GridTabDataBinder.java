/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
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
package org.adempiere.webui.util;

import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.util.CLogger;

/**
 * Transfer data from editor to GridTab
 * @author hengsin
 *
 */
public class GridTabDataBinder implements ValueChangeListener {

	private final static CLogger logger = CLogger.getCLogger(GridTabDataBinder.class);
	
	private GridTab gridTab;

	/**
	 * 
	 * @param gridTab
	 */
	public GridTabDataBinder(GridTab gridTab) {
		this.gridTab = gridTab;
	}
	
	/**
	 * @param e
	 */
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
//        	  this is the original code from GridController, don't know what it does there but it breaks ignore button for web ui        
//            mTable.setChanged (true);  
        	mTable.setValueAt (e.getNewValue(), row, col);
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
