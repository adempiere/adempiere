package org.compiere.model;

import java.util.EventObject;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class StateChangeEvent extends EventObject {

	private int eventType;

	public StateChangeEvent(Object source, int eventType) {
		super(source);
		this.eventType = eventType;
	}
	
	public final static int DATA_REFRESH_ALL = 0;
	public final static int DATA_REFRESH = 1;
	public final static int DATA_NEW = 2;
	public final static int DATA_DELETE = 3;
	public final static int DATA_SAVE = 4;
	public final static int DATA_IGNORE = 5;

}
