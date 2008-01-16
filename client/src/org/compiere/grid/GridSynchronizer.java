package org.compiere.grid;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.compiere.model.StateChangeEvent;
import org.compiere.model.StateChangeListener;
import org.compiere.model.GridTab;
import org.compiere.model.MRole;

/**
 * 
 * @author Low Heng Sin
 *
 */
public class GridSynchronizer implements PropertyChangeListener, StateChangeListener {
	
	private GridController parent;
	private GridController child;

	public GridSynchronizer(GridController parent, GridController child) {
		this.parent = parent;
		this.child = child;
	
		parent.getMTab().addPropertyChangeListener(this);
		parent.getMTab().addStateChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(GridTab.PROPERTY)) {
			if (child.isCurrent()) {
				child.getMTab().dataRefresh();
			} else {
				MRole role = MRole.getDefault(); 
				child.query (false, 0, role.getMaxQueryRecords());
			}
		}
	}

	public void stateChange(StateChangeEvent event) {
		if (child.isCurrent()) {
			child.getMTab().dataRefresh();
		} else {
			MRole role = MRole.getDefault(); 
			child.query (false, 0, role.getMaxQueryRecords());
		}
	}

}
