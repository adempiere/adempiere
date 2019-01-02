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

import org.adempiere.webui.component.Button;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.image.AImage;

import java.util.logging.Level;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 12, 2007
 * @version $Revision: 0.10 $
 * 
 * @author Low Heng Sin
 * @date 	July 14 2008
 */
public class WebEditorFactory
{

    @SuppressWarnings("unused")
	private final static CLogger logger;
    
    static
    {
        logger = CLogger.getCLogger(WebEditorFactory.class);
    }
    
    public static WEditor getEditor(GridField gridField, boolean tableEditor)
    {
    	return getEditor(null, gridField, tableEditor);
    }
    
    public static WEditor getEditor(GridTab gridTab, GridField gridField, boolean tableEditor)
    {
        if (gridField == null)
        {
            return null;
        }

        WEditor editor = null;
        int displayType = gridField.getDisplayType();
        
        /** Not a Field */
        if (gridField.isHeading())
        {
            return null;
        }

        /** String (clear/password) */
        if (displayType == DisplayType.String
            || displayType == DisplayType.PrinterName 
            || (tableEditor && (displayType == DisplayType.Text || displayType == DisplayType.TextLong)))
        {
            if (gridField.isEncryptedField())
            {
                editor = new WPasswordEditor(gridField);
            }
            else
            {
                editor = new WStringEditor(gridField, tableEditor);
            }
        }
        /** File */
        else if (displayType == DisplayType.FileName)
        {
        	editor = new WFilenameEditor(gridField);
        }
        /** File Path */
        else if (displayType == DisplayType.FilePath)
        {
        	editor = new WFileDirectoryEditor(gridField);
        }
        /** File Path or Name */
        else if (displayType == DisplayType.FilePathOrName)
        {
        	editor = new WFilenameEditor(gridField);
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
            if (tableEditor)
            	((WYesNoEditor)editor).getComponent().setLabel("");
        }

        /** Text */
        else if (displayType == DisplayType.Text || displayType == DisplayType.Memo || displayType == DisplayType.TextLong)
        {
            editor = new WStringEditor(gridField);
        }
        
        /** Date */
        else if (DisplayType.isDate(displayType))
        {
        	if (displayType == DisplayType.Time)
        		editor = new WTimeEditor(gridField);
        	else if (displayType == DisplayType.DateTime)
        		editor = new WDatetimeEditor(gridField);
        	else
        		editor = new WDateEditor(gridField);
        }
        
        /**  Button */
        else if (displayType == DisplayType.Button)
        {
            editor = new WButtonEditor(gridField);
            if (gridField.getAD_Image_ID() > 0)
            {
            MImage icon = MImage.get(gridField.getVO().ctx , gridField.getAD_Image_ID());
            AImage imageIcon = null;
            byte[] image = icon.getData();
            if (image != null && image.length > 0) {
                try {
                    imageIcon = new AImage(null, image);
                } catch (Exception e) {
                    logger.log(Level.WARNING, e.getLocalizedMessage(), e);
                }
            }
            if (imageIcon != null)
                ((Button) editor.getComponent()).setImageContent(imageIcon);
            }
        }

        /** Table Direct */
        else if (displayType == DisplayType.TableDir || 
                displayType == DisplayType.Table || displayType == DisplayType.List
                || displayType == DisplayType.ID )
        {
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
            editor = new WLocationEditor(gridField);
        }
        else if (displayType == DisplayType.Locator)
        {
        	editor = new WLocatorEditor(gridField); 
        }
        else if (displayType == DisplayType.Account)
        {
        	editor = new WAccountEditor(gridField);
        }
        else if (displayType == DisplayType.Image)
        {
        	editor = new WImageEditor(gridField);
        }
        else if (displayType == DisplayType.Binary)
        {
        	editor = new WBinaryEditor(gridField);        	
        }
        else if (displayType == DisplayType.PAttribute)
        {
        	editor = new WPAttributeEditor(gridTab, gridField);
        }
        else if (displayType == DisplayType.Assignment)
        {
        	editor = new WAssignmentEditor(gridField);
        }
        else if (displayType == DisplayType.Chart)
        {
        	editor = new WChartEditor(gridField, gridTab.getWindowNo());
        }
        else
        {
            editor = new WUnknownEditor(gridField);
        }
        
        // Change the label from the column to a user defined value for specific fields.
        if (gridField.getColumnName() != null && gridField.getColumnName().equals("User1_ID")
         || gridField.getColumnName() != null && gridField.getColumnName().equals("User2_ID")
         || gridField.getColumnName() != null && gridField.getColumnName().equals("User3_ID")
         || gridField.getColumnName() != null && gridField.getColumnName().equals("User4_ID")) {
        	int accountSchemaId = Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID");
        	if (accountSchemaId > 0) {
            	MAcctSchema accountSchema = MAcctSchema.get(Env.getCtx(),	accountSchemaId);
            	if (accountSchema != null) {
            		MAcctSchemaElement accountSchemaElement = null;
            		if (gridField.getColumnName().equals("User1_ID"))
                    	accountSchemaElement = accountSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList1);
            		else if (gridField.getColumnName().equals("User2_ID"))
            			accountSchemaElement = accountSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList2);
                    else if (gridField.getColumnName().equals("User3_ID"))
                        accountSchemaElement = accountSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList3);
                    else if (gridField.getColumnName().equals("User4_ID"))
                        accountSchemaElement = accountSchema.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_UserList4);
            		
            		if ( accountSchemaElement != null )
            			editor.setLabel(accountSchemaElement.getName());
            	}
        	}
        }
        return editor;
    }
}
