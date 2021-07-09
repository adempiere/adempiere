package org.adempiere.webui.apps.graph;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.model.IDocumentStatus;
import org.adempiere.model.MDocumentStatus;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.model.MQuery;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

public class WDocumentStatusPanel extends Panel implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6491684272848160726L;

	private List<WDocumentStatusIndicator> indicatorList = new ArrayList<WDocumentStatusIndicator>();
	
	/**
	 * 	Get Panel if User has Performance Goals
	 *	@return panel pr null
	 */
	public static WDocumentStatusPanel get()
	{
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		IDocumentStatus[] indicators = MDocumentStatus.getDocumentStatusIndicators(Env.getCtx(), AD_User_ID, AD_Role_ID);
		if (indicators.length == 0)
			return null;
		return new WDocumentStatusPanel(indicators);
	}
	
	/**************************************************************************
	 * 	Constructor
	 *	@param goals
	 */
	private WDocumentStatusPanel (IDocumentStatus[] indicators)
	{
		super ();
		m_indicators = indicators;
		init();
	}
	
	/** Goals			*/
	private IDocumentStatus[] 	m_indicators = null;
	
	/**	Logger	*/
	private static CLogger log = CLogger.getCLogger (WPAPanel.class);
	
	/**
	 * 	Static/Dynamic Init
	 */
	private void init()
	{
		Grid grid = new Grid();
		appendChild(grid);
		grid.setWidth("100%");
		grid.setStyle("margin:0; padding:0; position: absolute;");
		grid.makeNoStrip();
		grid.setOddRowSclass("even");

		Rows rows = new Rows();
		grid.appendChild(rows);

		for (int i = 0; i < m_indicators.length; i++)
		{
			Row row = new Row();
			rows.appendChild(row);
			row.setWidth("100%");
			
			WDocumentStatusIndicator pi = new WDocumentStatusIndicator(m_indicators[i]);
			row.appendChild(pi);
			pi.addEventListener(Events.ON_CLICK, this);
			indicatorList.add(pi);
		}	
	}	//	init

	/**
	 * 	Action Listener for Drill Down
	 *	@param e event
	 */
	public void onEvent(Event e) throws Exception 
	{
		if (e.getTarget() instanceof WDocumentStatusIndicator)
		{
			WDocumentStatusIndicator pi = (WDocumentStatusIndicator) e.getTarget();
			log.info(pi.toString());
			IDocumentStatus status = pi.getDocumentStatus();
			int AD_Window_ID = status.getAD_Window_ID();
			if (AD_Window_ID > 0)
			{
				MQuery query = new MQuery(status.getAD_Table_ID());
				query.addRestriction(MDocumentStatus.getWhereClause(status));
				AEnv.zoom(AD_Window_ID, query);
			}
		}
	}

	public void refresh() {
		for (WDocumentStatusIndicator indicator : indicatorList) {
			indicator.refresh();
		}		
	}

	public void updateUI() {
		for (WDocumentStatusIndicator indicator : indicatorList) {
			indicator.updateUI();
		}
	}
}
