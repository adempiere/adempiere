/* ExecutionsCtrl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mon Jun  6 12:20:51     2005, Created by tomyeh
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.ui.sys;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

/**
 * Additional utilities for {@link Execution}.
 *
 * @author tomyeh
 */
public class ExecutionsCtrl extends Executions {
	protected ExecutionsCtrl() {} //prevent from instantiation

	/** Sets the execution for the current thread.
	 * Called only internally.
	 *
	 * <p>Note: you have to clean up the current execution
	 * with try/finally:
	 * <pre><code>
	 * setCurrent(exec);
	 * try {
	 * ...
	 * finally {
	 *   setCurrent(null);
	 * }
	 */
	public static final void setCurrent(Execution exec) {
		_exec.set(exec);
	}
	/** Returns the current {@link ExecutionCtrl}.
	 */
	public static final ExecutionCtrl getCurrentCtrl() {
		return (ExecutionCtrl)getCurrent();
	}
}