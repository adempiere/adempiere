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
	private static final long serialVersionUID = 1L;

	/**
	 * 	Constructor.
	 * 	Called from PAPanel, ViewPI (Performance Indicator)
	 *	@param goal goal
	 */
	public WPerformanceDetail (MGoal goal)
	{
		super();
		setTitle(goal.getName());
		
		WBarGraph barPanel = new WBarGraph(goal);
		appendChild(barPanel);
				
		SessionManager.getAppDesktop().showWindowInTabPanel(this);
	}	//	PerformanceDetail
}
