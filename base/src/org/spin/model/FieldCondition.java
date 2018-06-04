/**
 * 
 */
package org.spin.model;

import java.io.Serializable;
import java.util.HashMap;

import org.compiere.model.GridFieldVO;
import org.compiere.model.MFieldCondition;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Evaluator;

/**
 * @author tt-02
 *
 */
public class FieldCondition 
implements Serializable, Evaluatee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3514057815991979828L;

	private String styleSheet;

	private String condition;
	
	private HashMap<String,String> value;
	
	private GridFieldVO gridFieldVO;
	


	public FieldCondition(MFieldCondition fieldCondition, GridFieldVO gridFieldVO) {
		setStyleSheet(fieldCondition.getStylesheet());
		setCondition(fieldCondition.getCondition());
		this.gridFieldVO = gridFieldVO;
	}

	/**
	 * @return the styleSheet
	 */
	public String getStyleSheet() {
		return styleSheet;
	}

	/**
	 * @param styleSheet the styleSheet to set
	 */
	public void setStyleSheet(String styleSheet) {
		this.styleSheet = styleSheet;
	}

	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public void setValue(HashMap<String,String> value) {
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
			return value.get(variableName);
		}
	   if( gridFieldVO.TabNo == 0)
	    	return Env.getContext (gridFieldVO.ctx, gridFieldVO.WindowNo, variableName, true);
	    else
		return Env.getContext (gridFieldVO.ctx, gridFieldVO.WindowNo, gridFieldVO.TabNo, variableName, false, true);
	}	//	get_ValueAsString


	/**
     * Process Condition for Style Definition
     * @return
     */
    public boolean isValid() {
    	if(gridFieldVO == null)
    		return false;
    	if(Evaluator.evaluateLogic(this, condition)) 
    		return  true;
    	else    	
    	return false;
    }
	/**
     * Process Condition for Style Definition
     * @return
     */
    public boolean isValid(HashMap<String, String> value) {
    	setValue(value);
    	if(gridFieldVO == null)
    		return false;
    	if(Evaluator.evaluateLogic(this, condition)) 
    		return  true;
    	else    	
    	return false;
    }

}
