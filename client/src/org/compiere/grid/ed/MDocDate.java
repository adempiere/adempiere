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

package org.compiere.grid.ed;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import org.compiere.apps.ADialog;
import org.compiere.util.CLogger;

/**
 *	Date Model.
 *		Validates input based on date pattern
 *  @see VDate
 *
 *  @author Jorg Janke
 *  @version  $Id: MDocDate.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public final class MDocDate extends DefaultStyledDocument implements CaretListener, FocusListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8453168098223574265L;
	private String hintText;
	boolean showingHint = false;
	private MutableAttributeSet backgroundStyle;

	/**
	 *	Constructor
	 *  @param displayType display type
	 *  @param format format
	 *  @param tc text component
	 *  @param title title
	 */
	public MDocDate (int displayType, SimpleDateFormat format,
		JTextPane tc, String title)
	{
		super();
		m_tc = tc;
		m_tc.addCaretListener(this);
		m_tc.addFocusListener(this);
		//
		setFormat(format);

		//	Mark delimiters as '^' in Pattern
		char[] pattern = m_format.toPattern().toCharArray();
		for (int i = 0; i < pattern.length; i++)
		{
			//	do we have a delimiter?
			if ("Mdy".indexOf(pattern[i]) == -1)
				pattern[i] = DELIMITER;
		}
		m_mask = new String(pattern);
		//
		m_title = title;
		if (m_title == null)
			m_title = "";

		normalStyle = m_tc.getStyle("default");
		rightAlignedStyle = this.addStyle("rightAligned", (Style) normalStyle);
		backgroundStyle = this.addStyle("background", (Style) normalStyle);
		StyleConstants.setAlignment(rightAlignedStyle, StyleConstants.ParagraphConstants.ALIGN_RIGHT);
		hintStyle = this.addStyle("hintStyle", (Style) normalStyle);
		Color fg = getForeground(normalStyle);
		if (fg == null)
			fg = m_tc.getForeground();
		Color hintColor = new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), fg.getAlpha()/4);
		StyleConstants.setForeground(hintStyle, hintColor);
		StyleConstants.setAlignment(hintStyle, StyleConstants.ParagraphConstants.ALIGN_RIGHT);

		Color bg = getBackground(normalStyle);
		StyleConstants.setBackground(backgroundStyle, bg);
		
		this.setParagraphAttributes(0, getLength(), rightAlignedStyle, false);
		this.setParagraphAttributes(0, getLength(), backgroundStyle, false);
		
        // Add key listener to change the TAB behavior in
        // JTextPane to transfer focus forward or backward.
        m_tc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getModifiers() > 0) {
                    	m_tc.transferFocusBackward();
                    } else {
                    	m_tc.transferFocus();
                    }
                    e.consume();
                }
            }
        });
				
	}	//	MDocDate

	public void setFormat(SimpleDateFormat format) {
		
		m_format = format;
		if (m_format == null)
			m_format = new SimpleDateFormat();
		m_format.setLenient(false);
		hintText = m_format.toLocalizedPattern();
		
	}

	private JTextPane 		m_tc;
	private SimpleDateFormat	m_format;
	private String				m_mask;
	private static final char	DELIMITER = '^';
	//	for Calendar
	private String				m_title;
	private int					m_lastDot = 0;		//	last dot position
	private MutableAttributeSet hintStyle;
	private MutableAttributeSet rightAlignedStyle;
	private MutableAttributeSet normalStyle;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MDocDate.class);
	
	/**
	 *	Insert String
	 *  @param offset offset
	 *  @param string string
	 *  @param attr attributes
	 *  @throws BadLocationException
	 */
	@Override
	public void insertString (int offset, String string, AttributeSet attr)
		throws BadLocationException
	{
		log.finest("Offset=" + offset + ",String=" + string + ",Attr=" + attr
			+ ",OldText=" + getText() + ",OldLength=" + getText().length());

		if (getText().equals(hintText))
		{
			remove(0, hintText.length());
		}
		showingHint = false;
		
		log.info("showingHint: " + showingHint);
		//	manual entry
		//	DBTextDataBinder.updateText sends stuff at once - length=8
		if (string != null && string.length() == 1)
		{
			//	ignore if too long
			if (offset >= m_mask.length())
				return;

			
			//	is it an empty field?
			int length = getText().length();
			if (length == 0)
			{
				Date today = new Date(System.currentTimeMillis());
				String dateStr = m_format.format(today);
				super.insertString(0, dateStr, hintStyle);
				m_tc.setCaretPosition(0);
			}

			//	is it a digit ?
			try
			{
				Integer.parseInt(string);
			}
			catch (Exception pe)
			{
				//hengsin, [ 1660175 ] Date field - annoying popup
				//startDateDialog();
				ADialog.beep();
				return;
			}

			//	positioned before the delimiter - jump over delimiter
			if (offset != m_mask.length()-1 && m_mask.charAt(offset+1) == DELIMITER)
			{
				m_tc.setCaretPosition(offset+2);
				// Change the style of the delimiter in case it uses the hint style
				this.setCharacterAttributes(offset+1, 1, normalStyle, true);
			}
			
			//	positioned at the delimiter
			if (m_mask.charAt(offset) == DELIMITER)
			{
				// Change the style of the delimiter in case it uses the hint style
				this.setCharacterAttributes(offset, 1, normalStyle, true);
				offset++;
				m_tc.setCaretPosition(offset+1);
			}
			super.remove(offset, 1);	//	replace current position
		}

		//	Set new character
		super.insertString(offset, string, normalStyle);
		//	New value set Cursor
		if (offset == 0 && string != null && string.length() > 1)
			m_tc.setCaretPosition(0);
	}	//	insertString

	/**
	 *	Delete String
	 *  @param offset offset
	 *  @param length length
	 *  @throws BadLocationException
	 */
	@Override
	public void remove (int offset, int length)
		throws BadLocationException
	{
		log.finest("Offset=" + offset + ",Length=" + length);

		//	begin of string
		if (offset == 0 || length == 0)
		{
			//	empty the field
			//  if the length is 0 or greater or equal with the mask length - teo_sarca, [ 1660595 ] Date field: incorrect functionality on paste
			if (length >= m_mask.length() || length == 0)
				super.remove(offset, length);
			return;
		}

		//	one position behind delimiter
		if (offset-1 >= 0 && offset-1 < m_mask.length()
			&& m_mask.charAt(offset-1) == DELIMITER)
		{
			if (offset-2 >= 0)
				m_tc.setCaretPosition(offset-2);
			else
				return;
		}
		else
			m_tc.setCaretPosition(offset-1);
	}	//	deleteString

	/**
	 *	Caret Listener
	 *  @param e event
	 */
	public void caretUpdate(CaretEvent e)
	{
		log.finest("Dot=" + e.getDot() + ",Last=" + m_lastDot
			+ ", Mark=" + e.getMark());
		//	Selection
		if (e.getDot() != e.getMark())
		{
			m_lastDot = e.getDot();
			return;
		}
		//

		//	Is the current position a fixed character?
		if (e.getDot()+1 > m_mask.length() 
			|| m_mask.charAt(e.getDot()) != DELIMITER)
		{
			m_lastDot = e.getDot();
			return;
		}

		//	Direction?
		int newDot = -1;
		if (m_lastDot > e.getDot())			//	<-
			newDot = e.getDot() - 1;
		else								//	-> (or same)
			newDot = e.getDot() + 1;
		if (e.getDot() == 0)						//	first
			newDot = 1;
		else if (e.getDot() == m_mask.length()-1)	//	last
			newDot = e.getDot() - 1;
		//
		log.fine("OnFixedChar=" + m_mask.charAt(e.getDot())
			+ ", newDot=" + newDot + ", last=" + m_lastDot);
		//
		m_lastDot = e.getDot();
		if (newDot >= 0 && newDot < getText().length())
			m_tc.setCaretPosition(newDot);
	}	//	caretUpdate

	/**
	 *	Get Full Text
	 *  @return text
	 */
	private String getText()
	{
		String str = "";
		try
		{
			str = getContent().getString(0, getContent().length()-1);		//	cr at end
		}
		catch (Exception e)
		{
			str = "";
		}
		return str;
	}	//	getString


	@Override
	public void focusGained(FocusEvent e) {
		
		// If empty, display a format hint
		if (getText().isEmpty())
		{
			try {
				super.insertString(0, hintText, hintStyle);
				showingHint = true;
				log.info("showingHint: " + showingHint);

			} catch (BadLocationException e1) {}
		}
		m_tc.setCaretPosition(0);
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		if (e.isTemporary())
			return;
		
		log.info("showingHint true -> false: " + showingHint);
		if (showingHint)
		{
			try {
				remove(0,hintText.length());
				showingHint = false;

			} catch (BadLocationException e1) {}
		}
		
	}

	public void setBackground(Color bg) {
		
		StyleConstants.setBackground(backgroundStyle, bg);
		this.setParagraphAttributes(0, getLength(), backgroundStyle, false);

	}

	/**
	 * Return the string used as a hint when the document "value" is 
	 * empty.
	 * @return The hint text, typically the date format applied.
	 */
	public String getHint() {
		return hintText;
	}

	
}	//	MDocDate
