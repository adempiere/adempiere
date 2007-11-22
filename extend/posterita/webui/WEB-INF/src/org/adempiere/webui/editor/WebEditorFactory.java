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

package org.adempiere.webui.editor;

import org.compiere.model.GridField;
import org.compiere.model.MLocationLookup;
import org.compiere.model.MLocatorLookup;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 12, 2007
 * @version $Revision: 0.10 $
 */
public class WebEditorFactory
{

    private final static CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(WebEditorFactory.class);
    }
    
    public static WEditor getEditor(GridField gridField, boolean tableEditor)
    {
        if (gridField == null)
        {
            return null;
        }

        WEditor editor = null;
        int displayType = gridField.getDisplayType();
        int WindowNo = gridField.getWindowNo();

        /** Not a Field */
        if (gridField.isHeading())
        {
            return null;
        }

        /** String (clear/password) */
        if (displayType == DisplayType.String
            || displayType == DisplayType.PrinterName
            || (tableEditor && (displayType == DisplayType.Text || displayType == DisplayType.TextLong)) )
        {
            if (gridField.isEncryptedField())
            {
                editor = new WPasswordEditor(gridField);
            }
            else
            {
                editor = new WStringEditor(gridField);
            }
        }
        /** Number */
        else if (DisplayType.isNumeric(displayType))
        {
            editor = new WNumberEditor(gridField);
        }

        /** YesNo */
        else if (displayType == DisplayType.YesNo)
        {
            editor = new WYesNoEditor(gridField);
        }

        /** Text */
        else if (displayType == DisplayType.Text || displayType == DisplayType.Memo || displayType == DisplayType.TextLong)
        {
            editor = new WStringEditor(gridField);
        }
        
        /** Date */
        else if (DisplayType.isDate(displayType))
        {
            editor = new WDateEditor(gridField);
        }
        
        /**  Button */
        else if (displayType == DisplayType.Button)
        {
            editor = new WButtonEditor(gridField);
        }

        /** Table Direct */
        else if (displayType == DisplayType.TableDir || 
                displayType == DisplayType.Table || displayType == DisplayType.List
                || displayType == DisplayType.ID )
        {
            boolean integralValue = (displayType == DisplayType.TableDir || displayType == DisplayType.ID); 
            editor = new WTableDirEditor(gridField);
            
        }
                   
        else if (displayType == DisplayType.URL)
        {
        	editor = new WUrlEditor(gridField);
        }
        
        else if (displayType == DisplayType.Search)
        {
        	editor = new WSearchEditor(gridField);
        }
        
        else if (displayType == DisplayType.Location)
        {
            editor = new WLocationEditor(gridField.getColumnName(),gridField.isMandatory(false),gridField.isReadOnly(),gridField.isUpdateable(),
                    (MLocationLookup)gridField.getLookup());
        }
        else if (displayType == DisplayType.Locator)
        {
        	editor = new WLocatorEditor(gridField.getColumnName(), gridField.isMandatory(false), 
        			gridField.isReadOnly(), gridField.isUpdateable(), 
        			(MLocatorLookup)gridField.getLookup()); 
        }
        else
        {
            editor = new WUnknownEditor(gridField);
        }
        
        return editor;
    }
}
