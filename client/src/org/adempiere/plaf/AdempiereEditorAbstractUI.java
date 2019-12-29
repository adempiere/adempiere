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
import java.awt.Insets;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.TextUI;
import javax.swing.plaf.metal.MetalTextFieldUI;
import javax.swing.text.JTextComponent;

import org.compiere.swing.CButton;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 *  ADempiere UI for an abstract editor.
 *  Provides support for a text editor and a helper button.
 *
 *  @author Michael McKay, mckayERP@gmail.com
 *  
 *  @version 3.9.4
 */
public class AdempiereEditorAbstractUI extends PanelUI
{
	
	static CLogger log = CLogger.getCLogger(AdempiereEditorAbstractUI.class);
	
	/** A flag indicating that the minimum size should be recalculated */
	protected boolean isMinimumSizeDirty = true;
	
	/** A flag indicating that the display size should be recalculated */
	protected boolean isDisplaySizeDirty = true;
	
	/** The cached display size */
	protected Dimension cachedDisplaySize = new Dimension( 0, 0 );
	
	/** The cached minimum size */
	protected Dimension cachedMinimumSize = new Dimension( 0, 0 );
	
	/** A holder for the class used by the implementing editor */
	protected static Class<?> VEditorAbstract = null;
	
	/** 
	 *  A method in VEditorAbstract to identify if the editor
	 *  is being used in a table.
	 */
	protected static Method isTableCellEditorMethod = null;
	
	/** The containter for the editor */
	protected JComponent container;
	
	/** The editor component, typically a text field */
	protected JTextComponent editorComponent;
	
	/** The button for the helper function */
	protected CButton buttonComponent;
	
    /**
     * Used to determine the minimum height of a text field,
     * which in turn is used to answer the editor's minimum height.
     */
    private static final JTextField PHANTOM = new JTextField("phantom");
	
    /**
     * Different Plastic L&amp;fs may need different phantom UIs.
     * Therefore we store the LookAndFeel class and update the
     * phantom UI whenever the Look&amp;Feel changes.
     */
    private static Class<?> phantomLafClass;

    /**
     * The image icon to use in the button if the button is defined.
     */
	protected ImageIcon icon = Env.getImageIcon("Calendar10.gif");
	
	/** 
	 * A flag indicating that the editor is used in a table. This will affect 
	 * the style of borders applied
	 */
	protected boolean isTableCellEditor = false;
	
	/** A flag indicating that the cell is in focus */
	protected boolean isCellInFocus = false;
	
	/** A panel used to hold a border for the editor and button */
	protected JPanel borderPanel = new JPanel();
	
	/** A panel used to hold the editor and button */
	protected JPanel editorPanel;
	
	
	static {
		
		//  TODO - Might be a good idea to move the swing stuff to the base
		//  directory. It is all based in javax.swing anyway so there
		//  there is not much saving having in the Client jar.  Moving 
		//  it would reduce the need for the following redirection which is 
		//  necessary to avoid circular dependencies in the project.
    	
    	//  Find the VEditorAbstract class
		try {
			VEditorAbstract = Class.forName("org.compiere.grid.ed.VEditorAbstract");
			isTableCellEditorMethod = VEditorAbstract.getMethod("isTableCellEditor");
		} catch (ClassNotFoundException
				| NoSuchMethodException 
				| SecurityException 
				| IllegalArgumentException e) {
			log.severe(e.getMessage());
		}
    	
	}
    /**
     * Ensures that the phantom text field has same text field UI.
     */
    private static void ensurePhantomHasSameUI() {
        TextUI ui = PHANTOM.getUI();
        Class<?> lafClass = UIManager.getLookAndFeel().getClass();
        if (   (phantomLafClass != lafClass)
            || !(ui instanceof MetalTextFieldUI)) {
            phantomLafClass = lafClass;
            PHANTOM.updateUI();
        }
    }
    
	/**
	 *  Create UI
	 *  @param c
	 *  @return new instance of AdempiereDateUI
	 */
	public static ComponentUI createUI(JComponent c)
	{
		return new AdempiereEditorAbstractUI();
	}   //  CreateUI

    public AdempiereEditorAbstractUI() {
    	ensurePhantomHasSameUI();    	
    }
	
	@Override
    public void installUI(JComponent c) {
			
		//  The component should be the container for the editor
		//  typically a JPane but it could be the editor if the
		//  editor has no button
		this.container = c;
		
		//  This Look and Feel UI only applies to the VEditorAbstract class or its subclasses
		if (!(VEditorAbstract.isAssignableFrom(c.getClass())))
			return;
		
		//  Determine if the editor is being used in a table as either a renderer or editor
		try {
			isTableCellEditor = (boolean) isTableCellEditorMethod.invoke(c);
		} catch (SecurityException 
				| IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException e) {
			log.severe(e.getMessage());
		}
		
		//  Create the text field editor. This could be a JTextField or 
		//  a JTextPane. Subclasses of this UI can override the createEditorComponent()
		//  method to create specialized editors.
		editorComponent = createEditorComponent();
		if (editorComponent != null)
		{
			// Use the PLAF colors
			editorComponent.setForeground(AdempierePLAF.getTextColor_Normal());
			editorComponent.setMargin(new Insets(0,3,0,3));
		}
		
		//  Create the button for the helper function. It is null be default bu
		//  subclasses can override to provide a specific button.
		buttonComponent = createButton();
		if (buttonComponent != null)
		{
							
			//  Setup the button.
			icon = getButtonIcon();
			buttonComponent.setIcon(icon);
			buttonComponent.setBorderPainted(true);
			
		}

		//  If the container and the editor component are the same
		//  don't add buttons.  The container needs to be something
		//  with a layout to hold multiple components.
		if (container == editorComponent)
			return;
		
		//  To avoid having the editors shift when used in a table
		//  the layout of the editor is changed to have an overlay that
		//  draws the border around the entire editor on top of the editor
		//  components.  To make this happen, change the layout manager to 
		//  an overlayLayout
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

		//  Set the borders. The borders used depend on whether the editor
		//  is being used in a table cell or not.
		setBorders();

		//  Layout the editor panel with the editor in the center
		//  and the button, if any on the right
		editorPanel = new JPanel(new BorderLayout());
		if (editorComponent != null)
			editorPanel.add(editorComponent, BorderLayout.CENTER);
		if (buttonComponent != null)
			editorPanel.add(buttonComponent, BorderLayout.EAST);

		c.removeAll();  // In case the install runs more than once.
		c.add(borderPanel);  // The order here is important.  The first added ends up on top.
		c.add(editorPanel);
				
    }

	/**
	 * Set the border for the editor and button.   The challenge is to make them look 
	 * integrated and to not allow the editor or button to shift as different borders
	 * are applied.  Also, its important that the active border appear be the same size
	 * as the inactive border since the editor can be inactive while the button is active
	 * or vice versa.  Painting of the border looks after some of this.
	 * <br><br>
	 * For tables, there is no external border except the one applied by the renderer or editor
	 * of the table cell.  Internally, though, there may be borders between the button 
	 * and the editor.
	 * 
	 * @see {@link AdempiereBorders}
	 */
	public void setBorders() {
		
		if (!isTableCellEditor)
		{
			// Not a table cell, so use the standard borders
			if (buttonComponent == null || !buttonComponent.isVisible())
			{
				if (editorComponent != null)
					editorComponent.setBorder(UIManager.getBorder("TextField.border"));
			}
			else
			{
				if (editorComponent != null)
				{
					//  The editor component border needs to extend passed its size to be
					//  overwritten by the button border. In this way, the button and
					//  text editor will appear to share a border. Do this by creating
					//  a compound border with an outer empty border extending past the 
					//  right side and a standard text field border.
					editorComponent.setBorder(BorderFactory.createCompoundBorder(
													BorderFactory.createEmptyBorder(0, 0, 0, -2),
													UIManager.getBorder("TextField.border")));
					
					//  The button gets a special borderwhich mimics the combobox arrow button border
					buttonComponent.setBorder(UIManager.getBorder("AbstractEditor.ButtonBorder"));
				}
			}
		}
		else
		{
			
			//  In a table cell, all we want is a border between the button and the text.
			//  The cell renderer/editor will apply a border around the JPanel
			
			if (editorComponent != null)
			{
				//  Remove the TextField.border around the editor
				editorComponent.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			
			// If the button is not null
			if (buttonComponent != null)
			{
				// The outer border removes the outer edges of the arrowButtonBorder
				buttonComponent.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(-2, 0, -2, -2),
						BorderFactory.createCompoundBorder(
								UIManager.getBorder("AbstractEditor.ButtonBorder"),
								BorderFactory.createEmptyBorder(2, 0, 2, 0))));
				buttonComponent.setMargin(new Insets(0, 2, 0, 2));
			}			
		}
	}

	@Override
    public Dimension getPreferredSize( JComponent c ) {
        return getMinimumSize(c);
    }

    @Override
    public Dimension getMinimumSize( JComponent c ) {
    	
        if ( !isMinimumSizeDirty  ) {
            return new Dimension(cachedMinimumSize);
        }
        
        Dimension size = getDisplaySize();
        
        int buttonWidth = 0;
        
        if (buttonComponent != null && buttonComponent.isVisible())
        {
        	buttonWidth = getEditableButtonWidth();
        }
        
        //adjust the size based on the button width
        size.width +=  buttonWidth;

        cachedMinimumSize.setSize( size.width, size.height );
        isMinimumSizeDirty = false;

        return new Dimension(size);
    }

    @Override
    public Dimension getMaximumSize( JComponent c ) {
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
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
        
        // The number of columns overrides the width based on the phantom string length
        if (editorComponent instanceof JTextField && ((JTextField) editorComponent).getColumns() > 0)
        	PHANTOM.setColumns(((JTextField) editorComponent).getColumns());
        else
        	PHANTOM.setColumns(0);
        
        PHANTOM.setPreferredSize(null);        
        result = PHANTOM.getPreferredSize();

        // Set the cached value
        cachedDisplaySize.setSize(result.width, result.height);
        isDisplaySizeDirty = false;

        return result;
    }

    /**
     * Computes and returns the width of the arrow button in editable state.
     * The perceived width shall be equal to the width of a scroll bar.
     * Therefore we subtract a pixel that is perceived as part of the
     * arrow button but that is painted by the editor's border.
     *
     * @return the width of the arrow button in editable state
     */
    static int getEditableButtonWidth() {
    	
    	//  TODO - the editor buttons are a little wider than this in
    	//  reality.  Need to look at insets and the icon image sizes
        return UIManager.getInt("ScrollBar.width") - 1;
    }
    
    /**
     * Create the editor using the appropriate text component. 
     * Subclasses should override to provide the appropriate editor
     * or null.
     * @return the Text Component. The default is a CTextField.
     */
    protected JTextComponent createEditorComponent() {
    	
    	return new CTextField();
    	
    }
    
    /**
	 * @return the editorComponent
	 */
	public JTextComponent getEditorComponent() {
		return editorComponent;
	}

	/**
     * Create the button that provides the helper function.
     * Subclasses should override this to provide the 
     * button component.
     * @return the button component. Default is null.
     */
    protected CButton createButton() {
    	return (CButton) null;
    }
    
    /**
	 * @return the buttonComponent
	 */
	public CButton getButtonComponent() {
		return buttonComponent;
	}

	/**
     * Create the ImageIcon to use with the button.  Defaults 
     * to a null icon.  Subclasses should override to 
     * provide a specific icon.
     * @return
     */
    protected ImageIcon getButtonIcon() {
    	return (ImageIcon) null;
    }
    
    /**
     * Get the string to use to establish the minimum size. This will
     * typically be a format pattern of some sort.
     * @return
     */
    protected String getPhantomString() {
    	return "phantom";
    }

	public void setTableCellEditor(boolean isTableCellEditor) {
		
		if (this.isTableCellEditor != isTableCellEditor)
		{
			this.isTableCellEditor = isTableCellEditor;
			setBorders();
			isMinimumSizeDirty = true;
			// Display size should be OK
		}
		
	}
	
	/**
	 * Set the flag that indicates the cell is currently in the selected
	 * row and has the focus - meaning it gets a special border. This affects
	 * the width of the button or editor so the value does not appear to move
	 * as the focus shifts across a table.
	 * @param hasFocus
	 */
	public void setHasFocus(boolean hasFocus)
	{
		if (isCellInFocus != hasFocus)
		{
			isCellInFocus = hasFocus;
			
			if (isTableCellEditor)
			{
				setBorders();
				isMinimumSizeDirty = true;
			}
			
		}			
	}

	public JPanel getBorderPanel() {
		
		return borderPanel;
	}

	public JPanel getEditorPanel() {
		
		return editorPanel;
	}
	
	/**
	 *  Apply borders to the editor. The border
	 *  will be applied as an overlay so insets
	 *  will be ignored.  
	 *  @param border the border to apply.
	 */
	public void setBorder(Border border) {
		
		//  Borders applied to the editor are applied
		//  to the border panel only.  This overlays
		//  the border on top of the editor container
		//  to prevent the contents of the editor from
		//  shifting.  Note that the UI should ensure the 
		//  editor has sufficient insets so important info
		//  is not overwritten by the border.
		borderPanel.setBorder(border);
		
	}
	
	public void resetSize() {
		isMinimumSizeDirty = true;
		isDisplaySizeDirty = true;
	}
    
}   //  AdempiereEditorAbstractUI
