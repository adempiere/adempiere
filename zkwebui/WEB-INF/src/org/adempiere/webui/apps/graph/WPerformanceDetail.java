package org.adempiere.webui.apps.graph;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MGoal;

/**
 * 	Performance Detail Frame.
 * 	BarPanel for Drill-Down
 *	
 *  @author Jorg Janke
 *  @version $Id: PerformanceDetail.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class WPerformanceDetail extends Window
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3460594735973451874L;

	/**
	 * 	Constructor.
	 * 	Called from PAPanel, ViewPI (Performance Indicator)
	 *	@param goal goal
	 */
	public WPerformanceDetail (MGoal goal)
	{
		super();
		setTitle(goal.getName());

		WGraph barPanel = new WGraph(goal, 0, true, false, true, true);
		appendChild(barPanel);
				
		this.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
		this.setStyle("height: 100%; width: 100%; position: absolute; overflow: auto");
		barPanel.setStyle("height: 100%; width: 100%; position: absolute; overflow: visible");
		SessionManager.getAppDesktop().showWindow(this);
	}	//	PerformanceDetail
}
