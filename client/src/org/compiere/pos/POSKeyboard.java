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

package org.compiere.pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.TitledBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.text.Position;

import net.miginfocom.swing.MigLayout;

import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.print.MPrintColor;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;


/**
 *	On Screen Keyboard
 *	@author Paul Bowden
 *	Adaxa Pty Ltd
 */
public class POSKeyboard extends CDialog implements ActionListener, PosKeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296839634889851637L;

		private PosTextField field;

		private MPOSKeyLayout keylayout;


	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSKeyboard (PosBasePanel posPanel, int C_POSKeyLayout_ID, PosTextField field, String title)
	{
		this(posPanel, C_POSKeyLayout_ID);
		setTitle(title);
		setPosTextField(field);
	}

	public POSKeyboard(PosBasePanel posPanel, int keyLayoutId) {
		super(Env.getFrame(posPanel), true);
		keylayout = MPOSKeyLayout.get(posPanel.getCtx(), keyLayoutId);
		init( keyLayoutId );
	}

	private JFormattedTextField text = new JFormattedTextField();

	private HashMap<Integer, MPOSKey> keys;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(POSKeyboard.class);
	
	
	/**
	 * 	Initialize
	 * @param startText 
	 * @param POSKeyLayout_ID 
	 */
	public void init(int POSKeyLayout_ID )
	{
		CPanel panel = new CPanel();
		getContentPane().add(panel);
		
		//	Content
		panel.setLayout(new MigLayout("fill"));
		
		if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
			text.setHorizontalAlignment(JTextField.TRAILING);
		panel.add(text, "north, growx, h 30!, wrap, gap 10 10 10 10");

		PosKeyPanel keys = new PosKeyPanel(POSKeyLayout_ID, this);
		panel.add(keys, "center, growx, growy");
		
		ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
		confirm.addActionListener(this);
		Dimension buttonDim = new Dimension(50,50);
		confirm.getResetButton().setPreferredSize(buttonDim);
		confirm.getOKButton().setPreferredSize(buttonDim);
		confirm.getCancelButton().setPreferredSize(buttonDim);
		panel.add(confirm, "south");
		pack();
		setLocationByPlatform(true);
		text.requestFocusInWindow();
		
	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose()
	{
		if (keys != null)
		{
			keys.clear();
			keys = null;
		}
		super.dispose();
	}	//	dispose

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		else if ( action.equals(ConfirmPanel.A_RESET))
		{
			if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
				text.setText("0");
			else
				text.setText("");
			try {
				text.commitEdit();
			} catch (ParseException e1) {
				log.log(Level.FINE, "JFormattedTextField commit failed");
			}
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))
		{
			dispose();
		}
		else if (action.equals(ConfirmPanel.A_OK))
		{
			field.setText(text.getText());
			try {
				field.commitEdit();
			} catch (ParseException e1) {
				log.log(Level.FINE, "JFormattedTextField commit failed");
			}
			dispose();
		}
		log.info( "PosSubBasicKeys - actionPerformed: " + action);

	}	//	actionPerformed

	public void keyReturned(MPOSKey key) {
		
		String entry = key.getText();
		String old = text.getText();
		int caretPos = text.getCaretPosition();
		if ( text.getSelectedText() != null )
			caretPos = text.getSelectionStart();
		String head = old.substring(0, caretPos);
		if ( text.getSelectedText() != null )
			caretPos = text.getSelectionEnd();
		String tail = old.substring(caretPos, old.length());
		
		if ( entry != null && !entry.isEmpty() )
		{
			if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Keyboard))
			{
				if ( key.getText() != null )
					text.setText( head + entry + tail);
			}
			else if ( keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
			{
				if ( entry.equals(".") )
				{
					text.setText(head + entry + tail);
				}
				if ( entry.equals(",") )
				{
					text.setText(head + entry + tail);
				}
				else if ( entry.equals("C") )
				{
					text.setText("0");
				}
				else {
				try
				{
					int number = Integer.parseInt(entry);		// test if number
					if ( number >= 0 && number <= 9 )
					{
						text.setText(head + number + tail);
					}
					// greater than 9, add to existing
					else 
					{
						Object current = text.getValue();
						if ( current == null )
						{
							text.setText(Integer.toString(number));
						}
						else if ( current instanceof BigDecimal )
						{
							text.setText(((BigDecimal) current).add( 
									new BigDecimal(Integer.toString(number))).toPlainString());
						}
						else if ( current instanceof Integer )
						{
							text.setText(Integer.toString(((Integer) current) + number));
						}
						else if ( current instanceof Long )
						{
							text.setText(Long.toString(((Long) current) + number));
						}
						else if ( current instanceof Double )
						{
							text.setText(Double.toString(((Double) current) + number));
						}
					}


				}
				catch (NumberFormatException e)
				{
					// ignore non-numbers
				}
				}
				
				try {
					text.commitEdit();
				} catch (ParseException e) {
					log.log(Level.FINE, "JFormattedTextField commit failed");
				}
			}
		}
	}

	public void setPosTextField(PosTextField posTextField) {
		
		field = posTextField;
		text.setFormatterFactory(field.getFormatterFactory());
		text.setText(field.getText());
		text.setValue(field.getValue());
		getContentPane().invalidate();
		
	}

}	//	PosSubBasicKeys
