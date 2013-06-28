package org.adempiere.webui.event;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

/**
 * ZK Component that supports invokeLater events 
 * @author tsa
 *
 */
public interface ZkInvokeLaterComponent extends Component
{
	/**
	 * Method called when an invokeLater event arrives.
	 * In a normal case, please just call {@link ZkInvokeLaterSupport#onInvokeLater(Event)} method
	 * @param event
	 * @see ZkInvokeLaterSupport#onInvokeLater(Event)
	 */
	public void onInvokeLater(Event event);
	
	/**
	 * 
	 * @return {@link ZkInvokeLaterSupport} for this component
	 */
	public ZkInvokeLaterSupport getInvokeLaterSupport();
}
