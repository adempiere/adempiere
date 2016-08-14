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

package org.adempiere.pos;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.logging.Level;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPOSKeyLayout;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;


/**
 *	On Screen Keyboard
 *	@author Paul Bowden
 *  @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li>Centre Dialog
 *  <li>Add support to SCAPE and ENTER keys
 *  <li>Default focus in Text
 *	Adaxa Pty Ltd
 */
public class POSKeyboard extends CDialog 
	implements ActionListener, POSKeyListener, KeyListener, FocusListener, WindowFocusListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3296839634889851637L;
	
	/**	Source Text Field	*/
	private POSTextField 				f_SourceField;
	/**	Key Layout			*/
	private MPOSKeyLayout 				m_Keylayout;
	/**	Format				*/
	private JFormattedTextField 		m_Text = new JFormattedTextField();
	/**	Keys				*/
	private HashMap<Integer, MPOSKey> 	m_Keys;
	/**	Is Ok Action		*/
	private boolean 					m_IsOk;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(POSKeyboard.class);


	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public POSKeyboard (CPanel posPanel, int C_POSKeyLayout_ID, POSTextField field, String title) {
		this(posPanel, C_POSKeyLayout_ID);
		setTitle(title);
		setPosTextField(field);
	}

	/**
	 * Constructor
	 * @param posPanel
	 * @param keyLayoutId
	 */
	public POSKeyboard(CPanel posPanel, int keyLayoutId) {
		super(Env.getFrame(posPanel), true);
		m_Keylayout = MPOSKeyLayout.get(Env.getCtx(), keyLayoutId);
		init(keyLayoutId);
	}
	
	/**
	 * 	Initialize
	 * @param startText 
	 * @param POSKeyLayout_ID 
	 */
	public void init(int POSKeyLayout_ID) {
		CPanel panel = new CPanel();
		getContentPane().add(panel);
		
		//	Content
		panel.setLayout(new MigLayout("fill"));
		
		if (m_Keylayout.getPOSKeyLayoutType() != null &&
			m_Keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
			m_Text.setHorizontalAlignment(JTextField.TRAILING);
		//	Add Listener
		m_Text.addKeyListener(this);
		m_Text.addFocusListener(this);
		m_Text.setFocusable(true);
		//	
		panel.add(m_Text, "north, growx, h 30!, wrap, gap 10 10 10 10");
		//	
		POSKeyPanel keys = new POSKeyPanel(POSKeyLayout_ID, this);
		keys.setFocusable(false);
		//	
		panel.add(keys, "center, growx, growy");
		
		ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
		confirm.addActionListener(this);
		Dimension buttonDim = new Dimension(50,50);
		confirm.getResetButton().setPreferredSize(buttonDim);
		confirm.getOKButton().setPreferredSize(buttonDim);
		confirm.getCancelButton().setPreferredSize(buttonDim);
		panel.add(confirm, "south");
		pack();
	}	//	init
	
	/**
	 * 	Dispose - Free Resources
	 */
	public void dispose() {
		if (m_Keys != null) {
			m_Keys.clear();
			m_Keys = null;
		}
		//	
		super.dispose();
	}	//	dispose

	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e) {
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		else if (action.equals(ConfirmPanel.A_RESET)) {
			if ( m_Keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
				m_Text.setText("0");
			else
				m_Text.setText("");
			try {
				m_Text.commitEdit();
			} catch (ParseException e1) {
				log.log(Level.FINE, "JFormattedTextField commit failed");
			}
		} else if (action.equals(ConfirmPanel.A_CANCEL)) {
			processCancelAction();
		} else if (action.equals(ConfirmPanel.A_OK)) {
			processOkAction();
		}
		log.info( "PosSubBasicKeys - actionPerformed: " + action);

	}	//	actionPerformed
	
	/**
	 * Process Ok Action
	 * @return void
	 */
	private void processOkAction() {
		f_SourceField.setText(m_Text.getText());
		try {
			f_SourceField.commitEdit();
			//	Set Is Ok
			m_IsOk = true;
		} catch (ParseException e1) {
			log.log(Level.FINE, "JFormattedTextField commit failed");
		}
		dispose();
	}

	/**
	 * For Cancel Action
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	private void processCancelAction() {
		m_IsOk = false;
		dispose();
	}
	
	@Override
	public void keyReturned(MPOSKey key) {
		
		String entry = key.getText();
		String old = m_Text.getText();
		int caretPos = m_Text.getCaretPosition();
		if ( m_Text.getSelectedText() != null )
			caretPos = m_Text.getSelectionStart();
		String head = old.substring(0, caretPos);
		if ( m_Text.getSelectedText() != null )
			caretPos = m_Text.getSelectionEnd();
		String tail = old.substring(caretPos, old.length());
		
		if ( entry != null && !entry.isEmpty() )
		{
			if ( m_Keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Keyboard))
			{
				if ( key.getText() != null )
					m_Text.setText( head + entry + tail);
			}
			else if ( m_Keylayout.getPOSKeyLayoutType().equals(MPOSKeyLayout.POSKEYLAYOUTTYPE_Numberpad))
			{
				if ( entry.equals(".") )
				{
					m_Text.setText(head + entry + tail);
				}
				if ( entry.equals(",") )
				{
					m_Text.setText(head + entry + tail);
				}
				else if ( entry.equals("C") )
				{
					m_Text.setText("0");
				}
				else {
				try
				{
					int number = Integer.parseInt(entry);		// test if number
					if ( number >= 0 && number <= 9 )
					{
						m_Text.setText(head + number + tail);
					}
					// greater than 9, add to existing
					else 
					{
						Object current = m_Text.getValue();
						if ( current == null )
						{
							m_Text.setText(Integer.toString(number));
						}
						else if ( current instanceof BigDecimal )
						{
							m_Text.setText(((BigDecimal) current).add( 
									new BigDecimal(Integer.toString(number))).toPlainString());
						}
						else if ( current instanceof Integer )
						{
							m_Text.setText(Integer.toString(((Integer) current) + number));
						}
						else if ( current instanceof Long )
						{
							m_Text.setText(Long.toString(((Long) current) + number));
						}
						else if ( current instanceof Double )
						{
							m_Text.setText(Double.toString(((Double) current) + number));
						}
					}


				}
				catch (NumberFormatException e)
				{
					// ignore non-numbers
				}
				}
				
				try {
					m_Text.commitEdit();
				} catch (ParseException e) {
					log.log(Level.FINE, "JFormattedTextField commit failed");
				}
			}
		}
	}

	/**
	 * Set Pos Text Field
	 * @param posTextField
	 * @return void
	 */
	public void setPosTextField(POSTextField posTextField) {
		
		f_SourceField = posTextField;
		m_Text.setFormatterFactory(f_SourceField.getFormatterFactory());
		m_Text.setText(f_SourceField.getText());
		m_Text.setValue(f_SourceField.getValue());
		getContentPane().invalidate();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		log.finest("Key=" + e.getKeyCode() + " - " + e.getKeyChar()
			+ " -> " + m_Text.getText());

		//  ESC
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			processCancelAction();
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {	//	10
			processOkAction();
		}
	}	//	keyReleased
	
	/**
	 * Is Ok Pressed
	 * @return
	 * @return boolean
	 */
	public boolean isOk() {
		return m_IsOk;
	}

	@Override
	public void focusGained(FocusEvent e) {
		m_Text.selectAll();
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		processCancelAction();
	}
}	//	PosSubBasicKeys