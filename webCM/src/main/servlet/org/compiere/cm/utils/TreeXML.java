/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.cm.utils;

import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MCStage;
import org.compiere.model.MContainer;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.MWebProject;
import org.compiere.util.CLogger;

public class TreeXML {
	protected StringBuffer xmlContainer = new StringBuffer(" ");
	protected StringBuffer xmlTree = new StringBuffer(" ");
	protected Properties ctx;
	protected String trxName;
	private HashMap<Integer, MContainer> 	m_map = new HashMap<Integer, MContainer>();
	
	
	public TreeXML(MWebProject m_project) {
		ctx = m_project.getCtx();
		trxName = m_project.get_TrxName();

		// Containers
		MContainer[] containers = MContainer.getContainers(m_project);
		for (int i = 0; i < containers.length; i++)
			m_map.put(new Integer(containers[i].getCM_Container_ID()), containers[i]);

		MTree thisTree = new MTree (m_project.getCtx(), m_project.getAD_TreeCMC_ID(), true, true, m_project.get_TrxName());
		generateTree(m_project, thisTree);
	}
	
	public void generateTree(MWebProject m_project, MTree thisTree) {
		MTreeNode root = thisTree.getRoot();
		xmlTree.append(appendNode(root));
	}
	
	private String appendNode(MTreeNode thisNode) {
		StringBuffer tempTree = new StringBuffer();
		Integer ID = new Integer(thisNode.getNode_ID());
		MContainer container = m_map.get(ID);
		//	
		int size = thisNode.getChildCount();
		for (int i = 0; i < size; i++)
		{
			MTreeNode child = (MTreeNode)thisNode.getChildAt(i);
			ID = new Integer(child.getNode_ID());
			container = m_map.get(ID);
			if (container == null)
			{
				continue;
			}
			if (!container.isActive())
				continue;
			//
			tempTree.append("<treenode>");
			tempTree.append("<CM_Container_ID>" + container.get_ID() + "</CM_Container_ID>");
			tempTree.append("<Name>" + container.getName() + "</Name>");
			tempTree.append("<Title>" + container.getTitle() + "</Title>");
			tempTree.append("<RelativeURL>" + container.getRelativeURL() + "</RelativeURL>");
			tempTree.append("<Description>" + container.getDescription() + "</Description>");
			tempTree.append("<children>" + child.getChildCount() + "</children>");
			xmlContainer = container.get_xmlString(xmlContainer);
			if (child.isSummary())
				tempTree.append(appendNode(child));
			tempTree.append("</treenode>");
		}
		return tempTree.toString();
	}
	
	public String getTreeXML() {
		return "\n<containerTree>\n" + xmlTree.toString() + "\n</containerTree>\n";
	}
	
	public String getContainerXML() {
		return "\n<containerList>\n" + xmlContainer.toString() + "\n</containerList>\n";
	}
}
