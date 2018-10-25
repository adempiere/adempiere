/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. 			  *
 * All Rights Reserved. 													  *
 * Contributor(s): Raul Muñoz www.erpya.com				  		          	  *
 ******************************************************************************/
package org.spin.util;

import java.io.Serializable;
import java.util.HashMap;

import org.compiere.model.GridFieldVO;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;
import org.spin.model.MADFieldCondition;

/**
 * @author Raul Muñoz, rMunoz@erpya.com, ERPCyA http://www.erpya.com
 * @author Yamel Senih, ySenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1697">
 * 		@see FR [ 1697 ] Add definition for change style</a>
 *
 */
public class FieldCondition implements Serializable, Evaluatee {

	private static final long serialVersionUID = -3514057815991979828L;
	/**	Style	*/
	private String styleSheet;
	/**	Condition for It	*/
	private String condition;
	/**	Value	*/
	private HashMap<String, Object> value;
	/**	GridField	*/
	private GridFieldVO gridFieldVO;
	

	/**
	 * Create from field condition and grid field VO
	 * @param fieldCondition
	 * @param gridFieldVO
	 */
	public FieldCondition(MADFieldCondition fieldCondition, GridFieldVO gridFieldVO) {
		setStyleSheet(fieldCondition.getStylesheet());
		setCondition(fieldCondition.getCondition());
		this.gridFieldVO = gridFieldVO;
	}

	/**
	 * Get Style Sheet
	 * @return the styleSheet
	 */
	public String getStyleSheet() {
		return styleSheet;
	}

	/**
	 * Set Style Sheet
	 * @param styleSheet the styleSheet to set
	 */
	public void setStyleSheet(String styleSheet) {
		this.styleSheet = styleSheet;
	}

	/**
	 * Get Condition
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * Set Condition
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	/**
	 * Set Value
	 * @param value
	 */
	public void setValue(HashMap<String,Object> value) {
		this.value = value;
	}

	/**
	 * 	Get Variable Value (Evaluatee)
	 *	@param variableName name
	 *	@return value
	 */
	@Override
	public String get_ValueAsString (String variableName) {
		if(value != null) {
			return String.valueOf(value.get(variableName));
		}
	   if( gridFieldVO.TabNo == 0) {
		   return Env.getContext (gridFieldVO.ctx, gridFieldVO.WindowNo, variableName, true);
	   } else {
		   return Env.getContext (gridFieldVO.ctx, gridFieldVO.WindowNo, gridFieldVO.TabNo, variableName, false, true);
	   }
	}	//	get_ValueAsString


	/**
     * Process Condition for Style Definition
     * @return
     */
    public boolean isValid() {
    	if(gridFieldVO == null)
    		return false;
    	if(Evaluator.evaluateLogic(this, condition)) {
    		return true;
    	}
    	return false;
    }
	/**
     * Process Condition for Style Definition
     * @return
     */
    public boolean isValid(HashMap<String, Object> value) {
    	setValue(value);
    	if(gridFieldVO == null) {
    		return false;
    	}
    	if(Evaluator.evaluateLogic(this, condition)) {
    		return  true;
    	}
    	return false;
    }

	@Override
	public String toString() {
		return "FieldCondition [styleSheet=" + styleSheet + ", condition=" + condition + "]";
	}
}
