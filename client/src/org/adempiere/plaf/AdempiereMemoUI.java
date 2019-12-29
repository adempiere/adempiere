/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.plaf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.basic.BasicTextAreaUI;
import javax.swing.text.JTextComponent;

import org.compiere.grid.ed.VEditorAbstract;
import org.compiere.swing.CTextArea;
import org.compiere.util.CLogger;

/**
 *  ADempiere Memo UI.
 *  Provides support for a Memo editor.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereMemoUI extends AdempiereEditorAbstractUI
{
	
	CLogger log = CLogger.getCLogger(AdempiereMemoUI.class);
	
	/** Max Display Length - 60 */
	public static final int MAXDISPLAY_LENGTH = org.compiere.model.GridField.MAXDISPLAY_LENGTH;
	/** Max Display rows - 45 */
	public static final int MAXDISPLAY_ROWS = 20;
	
	/** The number of columns to display */
	private int displayColumns = MAXDISPLAY_LENGTH;  // Defaults to max in columns
	
	/** The number of rows to display by default */
	private int displayRows = 3; // Default

	/** The editor - a text area */
	private CTextArea textArea;

	/** The field length in total number of characters */
	private int fieldLength;

    /**
     * Different Plastic L&amp;fs may need different phantom UIs.
     * Therefore we store the LookAndFeel class and update the
     * phantom UI whenever the Look&amp;Feel changes.
     */
    private static Class<?> phantomLafClass;

    /**
     * Used to determine the minimum height of a text field,
     * which in turn is used to answer the editor's minimum height.
     */
    private static final JTextArea PHANTOM = new JTextArea("phantom");

    /**
     * Ensures that the phantom text field has same text field UI.
     */
    private static void ensurePhantomHasSameUI() {
        TextUI ui = PHANTOM.getUI();
        Class<?> lafClass = UIManager.getLookAndFeel().getClass();
        if (   (phantomLafClass != lafClass)
            || !(ui instanceof BasicTextAreaUI)) {
            phantomLafClass = lafClass;
            PHANTOM.updateUI();
        }
    }
	
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereMemoUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereMemoUI();
	}   //  CreateUI
    
    public AdempiereMemoUI() {
    	ensurePhantomHasSameUI();    	
    }

	@Override
    public void installUI(JComponent c) {
			
		//  The component should be the container for the editor
		//  typically a JPane but it could be the editor if the
		//  editor has no button
		this.container = c;
		
		//  This Look and Feel UI only applies to the VEditorAbstract class or its subclasses
		if (!(c instanceof VEditorAbstract))
			return;
		
		isTableCellEditor = ((VEditorAbstract) c).isTableCellEditor();

		textArea = new CTextArea(displayRows, displayColumns);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		textArea.setForeground(AdempierePLAF.getTextColor_Normal());
		textArea.setBackground(AdempierePLAF.getFieldBackground_Normal());

		// Change the layout manager to an overlayLayout
		if ( ! (container.getLayout() instanceof OverlayLayout))
		{
			container.setLayout(new OverlayLayout(container));
		}
		
		//  Create a panel that will display the cell focus border. It
		//  will be transparent and sit as an overlay on the editor. This
		//  is done to prevent the application of a border from changing
		//  the internal position of the editor components.  Icons on the
		//  right or left tend to shift when the border is applied directly
		//  to their parent panel.
		borderPanel.setOpaque(false);
		borderPanel.setBackground(new Color(0,0,0,0));  // Transparent

		//  Set the borders. This depends on whether the editor
		//  is being used in a table cell or not.
		setBorders();

		editorPanel = new JPanel(new BorderLayout());
		editorPanel.add(textArea, BorderLayout.CENTER);

		c.add(borderPanel);
		c.add(editorPanel);
				
    }

    /**
     * The Memo doesn't use a textComponent - it uses a scroll pane
     * as a container for a text component.
     * @return null as the editor component.
     */
    protected JTextComponent createEditorComponent() {
    	
    	return null;
    	
    }
    
    /**
     * Set the field length of the text area
     * @param fieldLength
     */
	public void setFieldLength(int fieldLength)
	{
		int rows = 1;
		if (displayColumns > 0)
			rows = fieldLength/displayColumns;
		setDisplaySize(rows, displayColumns);
		
	}
	
	/**
	 * Set the display length (width) of the 
	 * text area.  The display size will default to show the
	 * entire text by adding rows to the display.
	 * @param displayLength. Must be greater than 0 or 
	 * an IllegalArgumentException will be thrown.
	 */
	public void setDisplayLength(int displayLength)
	{
		if (displayLength <= 0)
			throw new IllegalArgumentException("displayLength > 0");
		
		displayLength = displayLength > MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength;
		int rows = this.displayRows;
		if (fieldLength > 0)
			rows = fieldLength/displayLength;
		setDisplaySize(rows, displayLength);
		
	}

    
	/**
	 * 	Set Display size in terms of the number of rows and columns
	 */
	public void setDisplaySize(int rows, int columns)
	{
		displayRows = rows > MAXDISPLAY_ROWS ? MAXDISPLAY_ROWS : rows;
		displayColumns = columns > MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : columns;
		if (textArea != null)
		{
			textArea.setRows(displayRows);
			textArea.setColumns(displayColumns);
		}
		
		this.isDisplaySizeDirty = true;
		this.isMinimumSizeDirty = true;

	}

	/**
	 * Set the borders of the text area
	 */
	@Override
	public void setBorders() {
		
		if (!isTableCellEditor)
		{
			textArea.setBorder(UIManager.getBorder("TextField.border"));
		}
		else
		{
			textArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		}
	}

	/**
	 * Add focus listeners to the text area
	 * @param listener
	 */
	public void addFocusListener(FocusListener listener) {
		
		textArea.addFocusListener(listener);
	}

	/**
	 * Add mouse listeners to the text area
	 * @param mouseAdapter
	 */
	public void addMouseListener(MouseListener mouseAdapter) {
		
		textArea.addMouseListener(mouseAdapter);
	}


	/**
	 * Get the text value.
	 * @return
	 */
	public String getText() {
		return textArea.getText();
	}


	/**
	 * Return the component that will get the focus
	 * in a table
	 * @return the text area
	 */
	public JComponent getComponent() {
		return textArea;
	}

	/**
	 * Sets the text value.
	 * @param value the text to set/display
	 */
	public void setText(String value) {
		textArea.setText(value);
		//	Always position Top 
		textArea.setCaretPosition(0);
	}
	
    /**
     * Returns the calculated size of the display area. The display area is the
     * text pane displaying the value.
     *
     * @return the size of the display area calculated
     */
    protected Dimension getDisplaySize() {

    	if (!isDisplaySizeDirty)  {
            return new Dimension(cachedDisplaySize);
        }
        
        Dimension result = new Dimension();

        PHANTOM.setText(getPhantomString());
    	PHANTOM.setColumns(((CTextArea) getComponent()).getColumns());
    	PHANTOM.setRows(((CTextArea) getComponent()).getRows());
        
        result = PHANTOM.getPreferredSize();
        // Add two pixels to ensure there are no scroll bars on an empty field
        result.height = result.height + ((CTextArea) getComponent()).getRows() + 2;
        
        // Set the cached value
        cachedDisplaySize.setSize(result.width, result.height);
        isDisplaySizeDirty = false;

        return result;
    }

}   