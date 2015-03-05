/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.eevolution.form;

import org.adempiere.webui.apps.wf.WFNode;
import org.adempiere.webui.apps.wf.WFNodeContainer;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.panel.ADForm;
import org.compiere.apps.wf.WFLine;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWFNodeNext;
import org.compiere.wf.MWorkflow;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Imagemap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

/**
 * 
 * TODO: implement support for edit
 * @author Low Heng Sin
 *
 */
public class WWFPanelManufacturing extends ADForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874950519612113345L;
	private Listbox workflowList;
	private Imagemap imageMap;

    private static final String WF_WhereClause = MWorkflow.COLUMNNAME_WorkflowType+" IN ("
            +    DB.TO_STRING(MWorkflow.WORKFLOWTYPE_Manufacturing)
            +","+DB.TO_STRING(MWorkflow.WORKFLOWTYPE_Quality)
            +")";

	@Override
	protected void initForm() {
		this.setHeight("100%");
		Borderlayout layout = new Borderlayout();
		layout.setStyle("width: 100%; height: 100%; position: absolute;");
		appendChild(layout);
		
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT AD_Workflow_ID, Name FROM AD_Workflow WHERE "+ WF_WhereClause +" ORDER BY 2",
				"AD_Workflow", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
			KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		
		workflowList = ListboxFactory.newDropdownListbox();
		for (KeyNamePair knp : pp) {
			workflowList.addItem(knp);
		}
		workflowList.addEventListener(Events.ON_SELECT, this);
		
		North north = new North();
		layout.appendChild(north);
		north.appendChild(workflowList);
		workflowList.setStyle("margin-left: 10px; margin-top: 5px;");
		north.setHeight("30px");
		
		imageMap = new Imagemap();
		Center center = new Center();
		layout.appendChild(center);
		center.setAutoscroll(true);
//		center.setFlex(true);
		center.appendChild(imageMap);
		
		ConfirmPanel confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		South south = new South();
		layout.appendChild(south);
		south.appendChild(confirmPanel);
		south.setHeight("36px");
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
			this.detach();
		else if (event.getTarget().getId().equals(ConfirmPanel.A_OK))
			this.detach();
		else if (event.getTarget() == workflowList) {
			ListItem item = workflowList.getSelectedItem();
			KeyNamePair knp = item != null ? item.toKeyNamePair() : null;
			if (knp != null && knp.getKey() > 0) {
				load(knp.getKey());
			} else {
				Image dummy = null;
				imageMap.setContent(dummy);
			}
		}
	}

	private void load(int workflowId) {
		//	Get Workflow
		MWorkflow wf = new MWorkflow (Env.getCtx(), workflowId, null);
		WFNodeContainer nodeContainer = new WFNodeContainer();
		nodeContainer.setWorkflow(wf);
		
		//	Add Nodes for Paint
		MWFNode[] nodes = wf.getNodes(true, Env.getAD_Client_ID(Env.getCtx()));
		for (int i = 0; i < nodes.length; i++)
		{
			WFNode wfn = new WFNode (nodes[i]);
			nodeContainer.add (wfn);
			//	Add Lines
			MWFNodeNext[] nexts = nodes[i].getTransitions(Env.getAD_Client_ID(Env.getCtx()));
			for (int j = 0; j < nexts.length; j++)
				nodeContainer.add (new WFLine (nexts[j]));
		}
		Dimension dimension = nodeContainer.getDimension();
		BufferedImage bi = new BufferedImage (dimension.width + 2, dimension.height + 2, BufferedImage.TYPE_INT_ARGB);
		nodeContainer.paint(bi.createGraphics());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "png", os);
			AImage imageContent = new AImage("workflow.png", os.toByteArray());
			imageMap.setWidth(dimension.width + "px");
			imageMap.setHeight(dimension.height + "px");
			imageMap.setContent(imageContent);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		
	}
	
	

}
