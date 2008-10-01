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
package org.adempiere.webui.apps.wf;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.VerticalBox;
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
import org.zkoss.zul.Imagemap;
import org.zkoss.zul.Separator;

/**
 * 
 * TODO: implement support for edit
 * @author Low Heng Sin
 *
 */
public class WFEditor extends ADForm {

	private Listbox workflowList;
	private Imagemap imageMap;

	@Override
	protected void initForm() {
		VerticalBox vbox = new VerticalBox();
		appendChild(vbox);
		
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT AD_Workflow_ID, Name FROM AD_Workflow ORDER BY 2",
				"AD_Workflow", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);	//	all
			KeyNamePair[] pp = DB.getKeyNamePairs(sql, true);
		
		workflowList = ListboxFactory.newDropdownListbox();
		for (KeyNamePair knp : pp) {
			workflowList.addItem(knp);
		}
		workflowList.addEventListener(Events.ON_SELECT, this);
		
		vbox.appendChild(workflowList);
		vbox.appendChild(new Separator());
		
		imageMap = new Imagemap();
		vbox.appendChild(imageMap);
		
		ConfirmPanel confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		vbox.appendChild(new Separator());
		vbox.appendChild(confirmPanel);
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
