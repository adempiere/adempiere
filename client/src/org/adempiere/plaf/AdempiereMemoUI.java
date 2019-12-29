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
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;

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
	
	/** The number of columns to display */
	private int displayColumns = MAXDISPLAY_LENGTH;  // Defaults to max in columns
	
	/** The number of rows to display by default */
	private int displayRows = 3; // Default

	/** The editor - a text area */
	private CTextArea textArea;

	/** The field length in total number of characters */
	private int fieldLength;

	/** The displayLength in columns */
	private int displayLength;

	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereMemoUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereMemoUI();
	}   //  CreateUI
    
	
	@Override
    public void installUI(JComponent c) {
			
		//  The component should be the container for the editor
		//  typically a JPane but it could be the editor if the
		//  editor has no button
		this.container = c;
		
		if (!(VEditorAbstract.isAssignableFrom(c.getClass())))
			return;
		
		try {
			this.isTableCellEditor = (boolean) isTableCellEditorMethod.invoke(c);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			log.severe(e.getMessage());
		}
		
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
		
		this.fieldLength= fieldLength;
		int rows = this.fieldLength/displayLength;
		setDisplaySize(rows, displayLength);
		
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
		
		this.displayLength = displayLength > MAXDISPLAY_LENGTH ? MAXDISPLAY_LENGTH : displayLength;
		int rows = fieldLength/this.displayLength;
		setDisplaySize(rows, this.displayLength);
		
	}

    
	/**
	 * 	Set Display size in terms of the number of rows and columns
	 */
	public void setDisplaySize(int rows, int columns)
	{
		displayRows = rows;
		displayColumns = columns;
		if (textArea != null)
		{
			textArea.setRows(displayRows);
			textArea.setColumns(displayColumns);
		}
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
}   