package org.compiere.model;

import java.util.EventListener;

/**
 * 
 * @author Low Heng Sin
 *
 */
public interface StateChangeListener extends EventListener {

	public void stateChange(StateChangeEvent event);
	
}
