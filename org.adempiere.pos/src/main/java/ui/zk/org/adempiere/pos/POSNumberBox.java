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

package org.adempiere.pos;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Textbox;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Vbox;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Mar 11, 2007
 * @version $Revision: 0.10 $
 * 
 * @author Low Heng Sin
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raúl Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class POSNumberBox extends Div
{
    	
    /**
     * Constructor
     * @param integral
     */
    public POSNumberBox(boolean integral)
    {
        super();
        this.integral = integral;
        init();
    }
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7089099079981906933L;
	
	/**	Field Calc					*/
	private Textbox 		txtCalc = new Textbox();
    /** Integral					*/
    private boolean 		integral = false;
    /** Number Format 				*/
    private NumberFormat 	format = null;
    /** Field Decimal 				*/
    private Decimalbox 		decimalBox = null;
    /** Action	 					*/
    private Button 			btn;
    /** Old Value 					*/
    private Object 			m_oldValue = null;
    /** Button Enabled 				*/
    private boolean 		btnEnabled = true;
    /** Pop Up						*/
	private Popup 			popup;

	/** Default Font Size 					*/
	private final String 	FONT_SIZE 	= "Font-size:medium;";
	/** Default Font Height 				*/
	private final String 	HEIGHT 		= "height:30px;";
	/** Default Font Width					*/
	private final String 	WIDTH 		= "width:auto;";
    
    private void init()
    {
    	Table grid = new Table();
		appendChild(grid);
		grid.setStyle("border: none; padding: 0px; margin: 0px;"+HEIGHT+WIDTH+FONT_SIZE);
		grid.setDynamicProperty("border", "0");
		grid.setDynamicProperty("cellpadding", "0");
		grid.setDynamicProperty("cellspacing", "0");
		
		Tr tr = new Tr();
		grid.appendChild(tr);
		tr.setStyle("border: none; padding: 0px; margin: 0px; white-space:nowrap; ");

		Td td = new Td();
		tr.appendChild(td);
		td.setStyle("border: none; padding: 0px; margin: 0px;");
		decimalBox = new Decimalbox();
    	if (integral) {
    		decimalBox.setScale(0);
        	decimalBox.setStyle("display: inline;width:85x;"+HEIGHT+FONT_SIZE);
    	}
    	else
        	decimalBox.setStyle("display: inline;text-align:right;width:80px;"+HEIGHT+FONT_SIZE);
    	
		td.appendChild(decimalBox);
		
		Td btnColumn = new Td();
		tr.appendChild(btnColumn);
		btnColumn.setStyle("border: none; padding: 0px; margin: 0px;"+HEIGHT);
		btnColumn.setSclass("editor-button");
		btn = new Button();
        btn.setImage("/images/Calculator10.png");
		btn.setTabindex(-1);
		LayoutUtils.addSclass("editor-button", btn);
		btnColumn.appendChild(btn);
        
        popup = getCalculatorPopup();
        LayoutUtils.addSclass("editor-button", btn);
        btn.setPopup(popup);
        btn.setStyle("text-align: center; height:35px;");
        appendChild(popup);
     
        String style = AEnv.isFirefox2() ? "display: inline" : "display: inline-block"; 
        style = style + ";white-space:nowrap";
        this.setStyle(style);	     
    }
    
    /**
     * 
     * @param format
     */
    public void setFormat(NumberFormat format)
    {
    	this.format = format;
    }
    
    /**
     * 
     * @param value
     */
    public void setValue(Object value)
    {
    	if (value == null)
    		decimalBox.setValue(null);
    	else if (value instanceof BigDecimal)
    		decimalBox.setValue((BigDecimal) value);
    	else if (value instanceof Number)
    		decimalBox.setValue(new BigDecimal(((Number)value).doubleValue()));
    	else
    		decimalBox.setValue(new BigDecimal(value.toString()));
    }
    
    /**
     * 
     * @return BigDecimal
     */
    public BigDecimal getValue()
    {
    	return decimalBox.getValue();
    }
    
    /**
     * 
     * @return text
     */
    public String getText()
    {
    	BigDecimal value = decimalBox.getValue();
    	if (value == null) return null;
    	
    	if (format != null)
    		return format.format(value);
    	else
    		return value.toPlainString();
    }
    
    /**
     * 
     * @param value
     */
    public void setValue(String value)
    {
    	Number numberValue = null;
    	
    	if (format != null)
    	{
    		try
			{
    			numberValue = format.parse(value);
    			setValue(numberValue);
			}
			catch (ParseException e)
			{
			}
    	}
    	else
    	{
    		decimalBox.setValue(new BigDecimal(value));
    	}    	
    }
    
    private Popup getCalculatorPopup()
    {
        Popup popup = new Popup(); 

        Vbox vbox = new Vbox();

        char separatorChar = DisplayType.getNumberFormat(DisplayType.Number, Env.getLanguage(Env.getCtx())).getDecimalFormatSymbols().getDecimalSeparator();
        String separator = Character.toString(separatorChar);

        txtCalc = new Textbox();
        txtCalc.setAction("onKeyPress : return calc.validate('" + 
        		decimalBox.getId() + "','" + txtCalc.getId() 
                + "'," + integral + "," + (int)separatorChar + ", event);");
        txtCalc.setMaxlength(250);
        txtCalc.setCols(30);
        txtCalc.setWidth("98%");
        txtCalc.setHeight("30px");
        txtCalc.setStyle(FONT_SIZE);
        
        String txtCalcId = txtCalc.getId();

        vbox.appendChild(txtCalc);
        Hbox row1 = new Hbox();

        Button btnAC = new Button();
        btnAC.setWidth("60px");
        btnAC.setHeight("60px");
        btnAC.setStyle(FONT_SIZE);
        btnAC.setLabel("AC");
        btnAC.setAction("onClick : calc.clearAll('" + txtCalcId + "')");

        Button btn7 = new Button();
        btn7.setWidth("60px");
        btn7.setHeight("60px");
        btn7.setStyle(FONT_SIZE);
        btn7.setLabel("7");
        btn7.setAction("onClick : calc.append('" + txtCalcId + "', '7')");

        Button btn8 = new Button();
        btn8.setWidth("60px");
        btn8.setHeight("60px");
        btn8.setStyle(FONT_SIZE);
        btn8.setLabel("8");
        btn8.setAction("onClick : calc.append('" + txtCalcId + "', '8')");

        Button btn9 = new Button();
        btn9.setWidth("60px");
        btn9.setHeight("60px");
        btn9.setStyle(FONT_SIZE);
        btn9.setLabel("9");
        btn9.setAction("onClick : calc.append('" + txtCalcId + "', '9')");

        Button btnMultiply = new Button();
        btnMultiply.setWidth("60px");
        btnMultiply.setHeight("60px");
        btnMultiply.setStyle(FONT_SIZE);
        btnMultiply.setLabel("*");
        btnMultiply.setAction("onClick : calc.append('" + txtCalcId + "', ' * ')");

        row1.appendChild(btnAC);
        row1.appendChild(btn7);
        row1.appendChild(btn8);
        row1.appendChild(btn9);
        row1.appendChild(btnMultiply);

        Hbox row2 = new Hbox();

        Button btnC = new Button();
        btnC.setWidth("60px");
        btnC.setHeight("60px");
        btnC.setStyle(FONT_SIZE);
        btnC.setLabel("C");
        btnC.setAction("onClick : calc.clear('" + txtCalcId + "')");
        
        Button btn4 = new Button();
        btn4.setWidth("60px");
        btn4.setHeight("60px");
        btn4.setStyle(FONT_SIZE);
        btn4.setLabel("4");
        btn4.setAction("onClick : calc.append('" + txtCalcId + "', '4')");

        Button btn5 = new Button();
        btn5.setWidth("60px");
        btn5.setHeight("60px");
        btn5.setStyle(FONT_SIZE);
        btn5.setLabel("5");
        btn5.setAction("onClick : calc.append('" + txtCalcId + "', '5')");

        Button btn6 = new Button();
        btn6.setWidth("60px");
        btn6.setHeight("60px");
        btn6.setStyle(FONT_SIZE);
        btn6.setLabel("6");
        btn6.setAction("onClick : calc.append('" + txtCalcId + "', '6')");
        
        Button btnDivide = new Button();
        btnDivide.setWidth("60px");
        btnDivide.setHeight("60px");
        btnDivide.setStyle(FONT_SIZE);
        btnDivide.setLabel("/");
        btnDivide.setAction("onClick : calc.append('" + txtCalcId + "', ' / ')");

        row2.appendChild(btnC);
        row2.appendChild(btn4);
        row2.appendChild(btn5);
        row2.appendChild(btn6);
        row2.appendChild(btnDivide);

        Hbox row3 = new Hbox();

        Button btnModulo = new Button();
        btnModulo.setWidth("60px");
        btnModulo.setHeight("60px");
        btnModulo.setStyle(FONT_SIZE);
        btnModulo.setLabel("%");
        btnModulo.setAction("onClick : calc.percentage('" + decimalBox.getId() + "','" 
                + txtCalcId + "','" + separator + "')");
        
        
        Button btn1 = new Button();
        btn1.setWidth("60px");
        btn1.setHeight("60px");
        btn1.setStyle(FONT_SIZE);
        btn1.setLabel("1");
        btn1.setAction("onClick : calc.append('" + txtCalcId + "', '1')");

        Button btn2 = new Button();
        btn2.setWidth("60px");
        btn2.setHeight("60px");
        btn2.setLabel("2");
        btn2.setStyle(FONT_SIZE);
        btn2.setAction("onClick : calc.append('" + txtCalcId + "', '2')");

        Button btn3 = new Button();
        btn3.setWidth("60px");
        btn3.setHeight("60px");
        btn3.setStyle(FONT_SIZE);
        btn3.setLabel("3");
        btn3.setAction("onClick : calc.append('" + txtCalcId + "', '3')");

        Button btnSubstract = new Button();
        btnSubstract.setWidth("60px");
        btnSubstract.setHeight("60px");
        btnSubstract.setStyle(FONT_SIZE);
        btnSubstract.setLabel("-");
        btnSubstract.setAction("onClick : calc.append('" + txtCalcId + "', ' - ')");

        row3.appendChild(btnModulo);
        row3.appendChild(btn1);
        row3.appendChild(btn2);
        row3.appendChild(btn3);
        row3.appendChild(btnSubstract);

        Hbox row4 = new Hbox();

        Button btnCurrency = new Button();
        btnCurrency.setWidth("60px");
        btnCurrency.setHeight("60px");
        btnCurrency.setStyle(FONT_SIZE);
        btnCurrency.setLabel("$");
        btnCurrency.setDisabled(true);

        Button btn0 = new Button();
        btn0.setWidth("60px");
        btn0.setHeight("60px");
        btn0.setStyle(FONT_SIZE);
        btn0.setLabel("0");
        btn0.setAction("onClick : calc.append('" + txtCalcId + "', '0')");

        
        Button btnDot = new Button();
        btnDot.setWidth("60px");
        btnDot.setHeight("60px");
        btnDot.setStyle(FONT_SIZE);
        btnDot.setLabel(separator);
        btnDot.setDisabled(integral);
        btnDot.setAction("onClick : calc.append('" + txtCalcId + "', '" + separator + "')");

        Button btnEqual = new Button();
        btnEqual.setWidth("60px");
        btnEqual.setHeight("60px");
        btnEqual.setStyle(FONT_SIZE);
        btnEqual.setLabel("=");
        btnEqual.setAction("onClick : calc.evaluate('" + decimalBox.getId() + "','" 
                + txtCalcId + "','" + separator + "')");
        
        Button btnAdd = new Button();
        btnAdd.setWidth("60px");
        btnAdd.setHeight("60px");
        btnAdd.setStyle(FONT_SIZE);
        btnAdd.setLabel("+");
        btnAdd.setAction("onClick : calc.append('" + txtCalcId + "', ' + ')");

        row4.appendChild(btnCurrency);
        row4.appendChild(btnDot);
        row4.appendChild(btn0);
        row4.appendChild(btnEqual);
        row4.appendChild(btnAdd);

        vbox.appendChild(row1);
        vbox.appendChild(row2);
        vbox.appendChild(row3);
        vbox.appendChild(row4);

        row1.setHeight("60px");
        row2.setHeight("60px");
        row3.setHeight("60px");
        row4.setHeight("60px");
        popup.appendChild(vbox);
        return popup;
    }

    /**
     * 
     * @return boolean
     */
	public boolean isIntegral() {
		return integral;
	}

	/**
	 * 
	 * @param integral
	 */
	public void setIntegral(boolean integral) {
		this.integral = integral;
		if (integral)
			decimalBox.setScale(0);
		else
			decimalBox.setScale(Decimalbox.AUTO);
	}
	
	/**
	 * 
	 * @param enabled
	 */
	public void setEnabled(boolean enabled)
	{
	     decimalBox.setReadonly(!enabled);
	     
	     boolean isCalculatorEnabled = btnEnabled && enabled;
	     btn.setEnabled(isCalculatorEnabled);
	     if (isCalculatorEnabled)
	    	 btn.setPopup(popup);
	     else 
	     {
	    	 Popup p = null;
	    	 btn.setPopup(p);
	     }
	}
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isEnabled()
	{
		 return decimalBox.isReadonly();
	}
	
	public boolean isReadonly()
	{
		return decimalBox.isReadonly();
	}
	
	@Override
	public boolean addEventListener(String evtnm, EventListener listener)
	{
	     if(Events.ON_CLICK.equals(evtnm))
	     {
	    	 btn.setFocus(true);
	       	 return btn.addEventListener(evtnm, listener);
	     }
	     else
	     {
	         return decimalBox.addEventListener(evtnm, listener);
	     }
	}
	
	@Override
	public void focus()
	{
		decimalBox.focus();
	}
	
	/**
	 * 
	 * @return decimalBox
	 */
	public Decimalbox getDecimalbox()
	{
		return decimalBox;
	}
	
	public void setCalculatorEnabled(boolean enabled)
	{
		btnEnabled = enabled;
		btn.setEnabled(btnEnabled);
		btn.setVisible(btnEnabled);
	}
	public boolean isCalculatorEnabled()
	{
		return this.btnEnabled;
	}

    /**
     * Set the old value of the field.  For use in future comparisons.
     * The old value must be explicitly set though this call.
     */
    public void set_oldValue() {
        this.m_oldValue = getValue();
    }

    /**
     * Get the old value of the field explicitly set in the past
     * @return
     */
    public Object get_oldValue() {
        return m_oldValue;
    }
    /**
     * Has the field changed over time?
     * @return true if the old value is different than the current.
     */
    public boolean hasChanged() {
        // Both or either could be null
        if(getValue() != null)
            if(m_oldValue != null)
                return !m_oldValue.equals(getValue());
            else
                return true;
        else  // getValue() is null
            if(m_oldValue != null)
                return true;
            else
                return false;
    }

}
