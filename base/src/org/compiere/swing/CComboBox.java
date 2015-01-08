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
package org.compiere.swing;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.FocusManager;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.text.JTextComponent;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.plaf.CompiereComboBoxUI;
import org.compiere.util.NamePair;
import org.compiere.util.Trace;

/**
 *  Adempiere Colored Combo Box.
 *
 *  @author     Jorg Janke
 *  @version    $Id: CComboBox.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CComboBox extends JComboBox
	implements CEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5918151626085721856L;

	/**
	 * Creates a <code>JComboBox</code> that takes it's items from an
	 * existing <code>ComboBoxModel</code>.  Since the
	 * <code>ComboBoxModel</code> is provided, a combo box created using
	 * this constructor does not create a default combo box model and
	 * may impact how the insert, remove and add methods behave.
	 *
	 * @param aModel the <code>ComboBoxModel</code> that provides the
	 * 		displayed list of items
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(ComboBoxModel aModel)
	{
		super(aModel);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified array.  By default the first item in the array
	 * (and therefore the data model) becomes selected.
	 *
	 * @param items  an array of objects to insert into the combo box
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(final Object items[])
	{
		super(items);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified array.  By default the first item in the array
	 * (and therefore the data model) becomes selected.
	 *
	 * @param items  an array of objects to insert into the combo box
	 * @param key set selected if exists
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(final Object items[], String key)
	{
		this(items);
		if (key == null)
			return;
		for (int i = 0; i < 0; i++)
		{
			Object item = items[i];
			if (item == null)
				continue;
			boolean found = false;
			if (item instanceof NamePair)
				found = ((NamePair)item).getID().equals(key);
			else
				found = item.toString().equals(key);
			if (found)
			{
				setSelectedIndex(i);
				break;
			}
		}
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> that contains the elements
	 * in the specified Vector.  By default the first item in the vector
	 * and therefore the data model) becomes selected.
	 *
	 * @param items  an array of vectors to insert into the combo box
	 * @see DefaultComboBoxModel
	 */
	public CComboBox(Vector items)
	{
		super(items);
		init();
	}   //  CComboBox

	/**
	 * Creates a <code>JComboBox</code> with a default data model.
	 * The default data model is an empty list of objects.
	 * Use <code>addItem</code> to add items.  By default the first item
	 * in the data model becomes selected.
	 *
	 * @see DefaultComboBoxModel
	 */
	public CComboBox()
	{
		super();
		init();
	}   //  CComboBox
	
	/** Field Height 				 */
	public static int     		FIELD_HIGHT = 0;
	   
	/** Property key for auto-reduction.             */
	public static final String AUTO_REDUCIBLE_PROPERTY = "autoReducible";

	/** Property key for case sensitive auto-reduction.             */
	public static final String CASE_SENSITIVE_PROPERTY = "caseSensitive";

	/** View model for hiding showing only filtered data             */
	ReducibleModel m_reducibleModel;

	/** Key listener for triggering an update the filtering model .             */
	private ReducibleKeyListener reducibleKeyListener = new ReducibleKeyListener();

	/** Reference Field         */
	private static  JTextField  s_text = new JTextField(15);

	/**
	 *  Common Init
	 */
	private void init()
	{
		FIELD_HIGHT = getPreferredSize().height;

		setEditable(true);
		setAutoReducible(true);

		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me) && isAutoReducible())
					updateReducibleModel(false);
			}
		});

		// when auto-reducing, the focus listener will ensure all data choices
		// are shown on initial focus, and that a valid selection is in place
		// when focus is lost
		final JTextComponent textComponent =
			(JTextComponent)getEditor().getEditorComponent();
		textComponent.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent fe)
			{
				if (isEditable())
					textComponent.selectAll();
				textComponent.repaint();
			}

			public void focusLost(FocusEvent fe)
			{
				if (isAutoReducible())
				{
					Object item = m_reducibleModel.getSelectedItem();
					item = (item == null && m_reducibleModel.getSize() != 0) ?
							m_reducibleModel.getElementAt(0) : item;
							if (item == null)
							{
								updateReducibleModel(false);
								if (m_reducibleModel.getSize() != 0)
									item = m_reducibleModel.getElementAt(0);
								else
									return;
							}
							m_reducibleModel.setSelectedItem(item);
				}
				textComponent.setCaretPosition(0);
				hidePopup();
				textComponent.repaint();
			}
		});

		textComponent.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me) && 
						isAutoReducible() &&
						!isPopupVisible())
				{
					updateReducibleModel(false);
					showPopup();
				}
			}
		});
	}   //  init


	/*************************************************************************/

	/** Icon        */
	private Icon m_icon = null;

	/**
	 *  Set Icon of arrow button to icon
	 *  @param defaultIcon Icon to be displayed
	 */
	public void setIcon (Icon defaultIcon)
	{
		if (getUI() instanceof CompiereComboBoxUI)
			((CompiereComboBoxUI)getUI()).setIcon(defaultIcon);
		m_icon = defaultIcon;
	}   //  setIcon

	   
	public ComboBoxModel getCompleteComboBoxModel()
	{
		return m_reducibleModel.getModel();
	}   //   getCompleteComboBoxModel
	
	/**
	 * @see javax.swing.JComboBox#setModel(javax.swing.ComboBoxModel)
	 */
	public void setModel(ComboBoxModel aModel) 
	{
		m_reducibleModel = (m_reducibleModel == null) ? new ReducibleModel() : m_reducibleModel;
		m_reducibleModel.setModel(aModel);

		super.setModel(m_reducibleModel);
	}   //   setModel

	/**
	 *  Set UI and re-set Icon for arrow button
	 *  @param ui
	 */
	public void setUI (ComboBoxUI ui)
	{
		super.setUI(ui);
		if (m_icon != null && ui instanceof CompiereComboBoxUI)
			((CompiereComboBoxUI)getUI()).setIcon(m_icon);
	}   //  setUI

	/**
	 *  Display Popup.
	 *  Called from AdempiereComboPopup and allows to implement
	 *  alternative actions than showing the popup
	 *  @return if true, the popup should be displayed
	 */
	public boolean displayPopup()
	{
		return true;
	}   //  displayPopup

	/*************************************************************************/

	/** Mandatory (default false)   */
	private boolean m_mandatory = false;

	/**
	 *	Set Editor Mandatory
	 *  @param mandatory true, if you have to enter data
	 */
	public void setMandatory (boolean mandatory)
	{
		m_mandatory = mandatory;
		setBackground(false);
	}   //  setMandatory

	/**
	 *	Is Field mandatory
	 *  @return true, if mandatory
	 */
	public boolean isMandatory()
	{
		return m_mandatory;
	}   //  isMandatory

	/**
	 *	Enable Editor
	 *  @param rw true, if you can enter/select data
	 */
	public void setReadWrite (boolean rw)
	{
		if (super.isEnabled() != rw)
			super.setEnabled (rw);
		setBackground(false);
	}   //  setReadWrite

	/**
	 *	Is it possible to edit
	 *  @return true, if editable
	 */
	public boolean isReadWrite()
	{
		return super.isEnabled();
	}   //  isReadWrite

	/**
	 *  Set Background based on editable / mandatory / error
	 *  @param error if true, set background to error color, otherwise mandatory/editable
	 */
	public void setBackground (boolean error)
	{
		if (error)
			setBackground(AdempierePLAF.getFieldBackground_Error());
		else if (!isReadWrite())
			setBackground(AdempierePLAF.getFieldBackground_Inactive());
		else if (m_mandatory)
			setBackground(AdempierePLAF.getFieldBackground_Mandatory());
		else
			setBackground(AdempierePLAF.getFieldBackground_Normal());
	}   //  setBackground

	/**
	 *  Set Background
	 *  @param bg
	 */
	public void setBackground (Color bg)
	{
		if (bg.equals(getBackground()))
			return;
		// Set same color for editor component - teo_sarca [ 1735122 ]
		if (getEditor() != null && getEditor().getEditorComponent() != null)
			getEditor().getEditorComponent().setBackground(bg);
		super.setBackground(bg);
	}   //  setBackground

	/**
	 *	Set Editor to value
	 *  @param value value of the editor
	 */
	public void setValue (Object value)
	{
		super.setSelectedItem(value);
	}   //  setValue

	/**
	 *	Return Editor value
	 *  @return current value
	 */
	public Object getValue()
	{
		return super.getSelectedItem();
	}   //  getValue

	/**
	 *  Return Display Value
	 *  @return displayed String value
	 */
	public String getDisplay()
	{
		Object o = super.getSelectedItem();
		if (o == null)
			return "";
		return o.toString();
	}   //  getDisplay

	/**
	 *  Add Mouse Listener - 1-4-0 Bug.
	 *  Bug in 1.4.0 Metal: arrowButton gets Mouse Events, so add the JComboBox
	 *  MouseListeners to the arrowButton - No context menu if right-click
	 *  @see CompiereComboBoxUI#installUI(JComponent)
	 *  @param ml
	 */
	public void addMouseListener (MouseListener ml)
	{
		super.addMouseListener(ml);
		//  ignore calls from javax.swing.plaf.basic.BasicComboBoxUI.installListeners(BasicComboBoxUI.java:271)
		if (getUI() instanceof CompiereComboBoxUI && !Trace.getCallerClass(1).startsWith("javax"))
		{
			JButton b = ((CompiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.addMouseListener(ml);
		}
                //begin  vpj-cd e-evolution               
                if (getUI() instanceof org.adempiere.plaf.AdempiereComboBoxUI && !Trace.getCallerClass(1).startsWith("javax"))
		{
			JButton b = ((org.adempiere.plaf.AdempiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.addMouseListener(ml);
		}
                //end vpj-cd e-evolution
	}   //  addMouseListener

	/**
	 *  Remove Mouse Listener.
	 *  @param ml
	 */
	public void removeMouseListener (MouseListener ml)
	{
		super.removeMouseListener(ml);
		if (getUI() instanceof CompiereComboBoxUI)
		{
			JButton b = ((CompiereComboBoxUI)getUI()).getArrowButton();
			if (b != null)
				b.removeMouseListener(ml);
		}
	}   //  removeMouseListener

	/**
	 * 	Set Action Command
	 *	@param actionCommand command 
	 */
	public void setActionCommand (String actionCommand)
	{
		super.setActionCommand (actionCommand);
		if (getName() == null && actionCommand != null && actionCommand.length() > 0)
			setName(actionCommand);
	}	//	setActionCommand

	/**
	 * Called only when auto-reducing.  By default, does a case insensitive
	 * string search for a match in the string representation of the given
	 * element.
	 * 
	 * @param element an element in the combo box model
	 * 
	 * @return true if the choice is to be displayed in the popup menu
	 */
	protected boolean isMatchingFilter(Object element) 
	{
		String str = (element != null) ? element.toString().trim() : "";
		str = isCaseSensitive() ? str : str.toLowerCase();

		return str.indexOf(m_reducibleModel.getMatchingFilter()) > -1;
	}

	/**
	 * Is the combo box auto-reducible?
	 * 
	 * @return true if isAutoReducible()
	 */
	public boolean isAutoReducible()
	{
		Boolean b = (Boolean)getClientProperty(AUTO_REDUCIBLE_PROPERTY);
		return (b != null) && b.booleanValue();
	}

	/**
	 * Set whether the combo box is auto-reducible.  The combo box must also be editable
	 * for auto-reduction to fully functional.  Auto-reduction of data will preclude
	 * the ability for users to enter in their own choices.
	 * 
	 * @param autoreducible true will activate auto-reduction of choices when user enters text
	 */
	public void setAutoReducible(boolean autoreducible)
	{
		if (isAutoReducible() != autoreducible)
		{
			putClientProperty(AUTO_REDUCIBLE_PROPERTY, Boolean.valueOf(autoreducible));
			updateReducibleModel(false);

			JTextComponent textComponent =
				(JTextComponent)getEditor().getEditorComponent();
			if (autoreducible)
				textComponent.addKeyListener(reducibleKeyListener);
			else
				textComponent.removeKeyListener(reducibleKeyListener);
		}
	}

	/**
	 * Is the auto-reduction case sensitive?
	 * 
	 * @return true if case sensitive
	 */
	public boolean isCaseSensitive()
	{
		Boolean b = (Boolean)getClientProperty(CASE_SENSITIVE_PROPERTY);
		return (b != null) && b.booleanValue();
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComboBox#removeAllItems()
	 */
	public void removeAllItems()
	{
		m_reducibleModel.removeAllElements();
	}

	/**
	 * Set whether auto-reduction is case sensitive.
	 * 
	 * @param caseSensitive true will make auto-reduction is case sensitive
	 */
	public void setCaseSensitive(boolean caseSensitive)
	{
		putClientProperty(CASE_SENSITIVE_PROPERTY, Boolean.valueOf(caseSensitive));
	}

	/**
	 * Updates the auto-reduction model.
	 * 
	 * @param filtering true if the underlying data model should be filtered
	 */
	void updateReducibleModel(boolean filtering)
	{
		if (filtering ||
				m_reducibleModel.getSize() != m_reducibleModel.getModel().getSize())
		{
			if (getParent() != null)
				hidePopup();

			// remember to caret position
			JTextComponent textComponent =
				(JTextComponent)getEditor().getEditorComponent();
			int pos = textComponent.getCaretPosition();
			m_reducibleModel.setFilter(textComponent.getText());

			// update the model
			m_reducibleModel.updateModel(filtering);

			// reset the caret
			textComponent.setText(m_reducibleModel.getFilter());
			textComponent.setCaretPosition(pos);

			// ensure the combo box is resized to match the popup, if necessary
			if (getParent() != null)
			{
				getParent().validate();
				getParent().repaint();

				if (isShowing() && m_reducibleModel.getSize() > 0) {
					// only show the popup if there is something to show
					showPopup();
				}
			}
		}
	}

	/**
	 * A view adapter model to hide filtered choices in the underlying combo box model.
	 */
	private class ReducibleModel implements MutableComboBoxModel, ListDataListener 
	{
		/**
		 * Default constructor.  Creates a ReducibleModel.
		 */
		public ReducibleModel()
		{
		}

		/** The wrapped data model. */
		private ComboBoxModel m_model;

		/** The wrapped data model. */
		private EventListenerList m_listenerList = new EventListenerList();

		/** The filtered data. */
		private ArrayList<Object> m_visibleData = new ArrayList<Object>();

		/** The filtered data. */
		private ArrayList<Object> m_modelData = new ArrayList<Object>();

		/** The current filter. */
		private String m_filter = "";

		/** The cached filter for case insensitive filtering. */
		private String m_lcFilter = "";

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#addElement(java.lang.Object)
		 */
		public void addElement(Object anObject)
		{
			checkMutableComboBoxModel();
			m_modelData.add(anObject);
			((MutableComboBoxModel)m_model).addElement(anObject);
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#addListDataListener(javax.swing.event.ListDataListener)
		 */
		public void addListDataListener(ListDataListener ldl)
		{
			m_listenerList.remove(ListDataListener.class, ldl);
			m_listenerList.add(ListDataListener.class, ldl);
		}

		/** 
		 * Checks that the <code>dataModel</code> is an instance of 
		 * <code>MutableComboBoxModel</code>.  If not, it throws an exception.
		 * 
		 * @exception RuntimeException if <code>dataModel</code> is not an
		 *      instance of <code>MutableComboBoxModel</code>.
		 */
		void checkMutableComboBoxModel()
		{
			if ( !(m_model instanceof MutableComboBoxModel) )
				throw new RuntimeException("Cannot use this method with a non-Mutable data model.");
		}

		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
		 */
		public void contentsChanged(ListDataEvent lde)
		{
			updateDataModel();
			updateModel(false);

			if (isPopupVisible())
			{
				hidePopup();
				showPopup();
			}
		}

		/**
		 * 
		 */
		private void fireContentsChanged()
		{
			ListDataEvent lde = null;
			for (ListDataListener ldl : getListDataListeners())
			{
				lde = (lde == null) ?
						new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, getSize()) : lde;
						ldl.contentsChanged(lde);
			}
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#getElementAt(int)
		 */
		public Object getElementAt(int index)
		{
			return m_visibleData.get(index);
		}

		/**
		 * Return the current filter.
		 * 
		 * @return the filter
		 */
		public String getFilter()
		{
			return m_filter;
		}

		/**
		 * 
		 */
		public ListDataListener[] getListDataListeners()
		{
			return (ListDataListener[])m_listenerList.getListeners(ListDataListener.class);
		}

		/**
		 * @return the filter to use for matching; hecks case sensistivity
		 */
		protected String getMatchingFilter()
		{
			return isCaseSensitive() ? m_filter : m_lcFilter;
		}

		/**
		 * @return the wrapped model
		 */
		public ComboBoxModel getModel()
		{
			return m_model;
		}

		/**
		 * @return the selected item in the wrapped model
		 * 
		 * @see javax.swing.DefaultComboBoxModel#getSelectedItem()
		 */
		public Object getSelectedItem()
		{
			return m_model.getSelectedItem();
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#getSize()
		 */
		public int getSize()
		{
			return m_visibleData.size(); 
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#insertElementAt(java.lang.Object, int)
		 */
		public void insertElementAt(Object anObject, int index)
		{
			checkMutableComboBoxModel();
			m_modelData.add(index, anObject);
			((MutableComboBoxModel)m_model).insertElementAt(anObject, index);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
		 */
		public void intervalAdded(ListDataEvent lde)
		{
			updateDataModel();
			updateModel(false);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
		 */
		public void intervalRemoved(ListDataEvent lde)
		{
			updateDataModel();
			updateModel(false);
		}

		/**
		 * 
		 */
		public void removeAllElements()
		{
			checkMutableComboBoxModel();

			ListDataListener[] listeners = getListDataListeners();
			for (int i = 0; i < listeners.length; i++)
				removeListDataListener(listeners[i]);
			m_model.removeListDataListener(this);

			m_modelData.clear();
			m_visibleData.clear();
			while (m_model.getSize() > 0)
				((MutableComboBoxModel)m_model).removeElementAt(0);

			for (ListDataListener ldl : listeners)
				addListDataListener(ldl);
			m_model.addListDataListener(this);

			updateModel(false);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#removeElement(java.lang.Object)
		 */
		public void removeElement(Object anObject)
		{
			checkMutableComboBoxModel();
			m_modelData.remove(anObject);
			m_visibleData.clear();
			((MutableComboBoxModel)m_model).removeElement(anObject);
		}

		/**
		 * Pass through to the wrapped model if underlying model is MutableComboBoxModel.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#removeElementAt(int)
		 */
		public void removeElementAt(int index)
		{
			checkMutableComboBoxModel();
			m_modelData.remove(index);
			m_visibleData.clear();
			((MutableComboBoxModel)m_model).removeElementAt(index);
		}

		/* (non-Javadoc)
		 * @see javax.swing.ListModel#removeListDataListener(javax.swing.event.ListDataListener)
		 */
		public void removeListDataListener(ListDataListener ldl)
		{
			m_listenerList.remove(ListDataListener.class, ldl);
		}

		/**
		 * @param filter the filter to set
		 */
		public void setFilter(String filter)
		{
			this.m_filter = (filter != null) ? filter : "";
			m_lcFilter = filter.trim().toLowerCase();
		}

		/**
		 * Set the wrapped combo box model.
		 * 
		 * @param model the model to set
		 */
		public void setModel(ComboBoxModel model)
		{
			if (this.m_model != null)
				this.m_model.removeListDataListener(this);

			this.m_model = model;
			updateDataModel();
			m_filter = "";

			model.addListDataListener(this);
			updateModel(false);
		}

		/**
		 * Set the selected item in the wrapped model.
		 * 
		 * @see javax.swing.DefaultComboBoxModel#setSelectedItem(java.lang.Object)
		 */
		public void setSelectedItem(Object anObject)
		{
			if (anObject == null || m_modelData.contains(anObject))
				m_model.setSelectedItem(anObject);
		}

		/**
		 * Updates the view model based on whether filtering or not.
		 * 
		 * @param filtering true if the underlying model is to be filtered
		 */
		public void updateDataModel()
		{
			m_modelData.clear();
			int size = m_model.getSize();
			for (int i = 0; i < size; i++)
				m_modelData.add(m_model.getElementAt(i));
		}

		/**
		 * Updates the view model based on whether filtering or not.
		 * 
		 * @param filtering true if the underlying model is to be filtered
		 */
		public void updateModel(boolean filtering)
		{
			boolean includeAll = !filtering || !isAutoReducible() || "".equals(m_lcFilter);
			if (includeAll)
			{
				m_visibleData.clear();
				m_visibleData.addAll(m_modelData);
			}
			else
			{
				m_visibleData.clear();
				Object selected = getSelectedItem();
				ListDataListener[] listeners = getListDataListeners();
				for (int i = 0; i < listeners.length; i++)
					removeListDataListener(listeners[i]);
				m_model.removeListDataListener(this);


				int size = m_model.getSize();

				for (int i = 0; i < size; i++)
				{
					Object element = m_model.getElementAt(i);
					if (element == null || isMatchingFilter(element))
					{
						m_visibleData.add(element);
					}
				}

				if (m_visibleData.contains(selected) || selected == null)
					setSelectedItem(selected);

				for (ListDataListener ldl : listeners)
					addListDataListener(ldl);
				m_model.addListDataListener(this);
			}

			fireContentsChanged();
		}
	}   //   ReducibleModel

	/**
	 * Key listener for editor's text compontent to trigger auto-reduction.  Only
	 * used when auto-reduction is enabled.
	 */
	class ReducibleKeyListener extends KeyAdapter
	{
		/** Invokes autoreduction. */
		private Runnable m_invoker = new Runnable()
		{
			public void run()
			{
				updateReducibleModel(true);
			}
		};

		/** Visibly updates the popup menu. */
		private Runnable m_updateMenu = new Runnable()
		{
			public void run()
			{
				hidePopup();
				getParent().validate();
				getParent().repaint();
				showPopup();
			}
		};

		/* (non-Javadoc)
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		public void keyPressed(KeyEvent ke) 
		{   
			if (ke.getKeyCode() != KeyEvent.VK_CONTROL &&
					ke.getKeyCode() != KeyEvent.VK_ALT &&
					ke.getKeyCode() != KeyEvent.VK_SHIFT && 
					( ke.getModifiersEx() & InputEvent.ALT_DOWN_MASK ) == 0 )
			{
				if (ke.getKeyCode() == KeyEvent.VK_ENTER ||
						ke.getKeyCode() == KeyEvent.VK_TAB)
				{
					// enter key pressed, so complete editing and select item
					Object selObject = getSelectedItem();
					selObject = (selObject == null && getItemCount() > 0) ? getItemAt(0) : selObject;
					setSelectedItem(selObject);
					getEditor().setItem(getSelectedItem());
				}
				else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					// escape key ends editing and rejects focus of text editor
					FocusManager.getCurrentManager().upFocusCycle();
				}
				else if (ke.getKeyCode() == KeyEvent.VK_UP ||
						ke.getKeyCode() == KeyEvent.VK_KP_UP ||
						ke.getKeyCode() == KeyEvent.VK_DOWN ||
						ke.getKeyCode() == KeyEvent.VK_KP_DOWN)
				{
					// up or down selects new value
					SwingUtilities.invokeLater(m_updateMenu);
				}
				else
				{
					// key typed, so filter
					SwingUtilities.invokeLater(m_invoker);
					setSelectedItem(null);
				}
			}
		}
	}   //   ReducibleKeyListener

}   //  CComboBox
