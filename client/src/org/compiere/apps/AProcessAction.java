/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Teo Sarca, t.sarca@metas.ro METAS GROUP 							  *
 *				                                                              *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JPopupMenu;

import org.adempiere.apps.toolbar.AProcessActionModel;
import org.adempiere.core.domains.models.I_AD_Process;
import org.compiere.grid.ed.VButton;
import org.compiere.model.MProcess;
import org.compiere.swing.CMenuItem;
import org.compiere.util.Env;

/**
 * 
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/999">
 * 		@see FR [ 999 ] Add ZK Support for Process Action</a>
 *
 */
public class AProcessAction
{
	private AProcessActionModel model = new AProcessActionModel();
	private APanel parent;
	private AppsAction action;

	public static AppsAction createAppsAction(APanel parent)
	{
		AProcessAction app = new AProcessAction(parent);
		return app.action;
	}

	public AProcessAction(APanel parent)
	{
		this.parent = parent;
		initAction();
	}

	private void initAction()
	{
		this.action = new AppsAction(model.getActionName(), null, false);
		action.setDelegate(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showPopup();
			}
		});
	}

	private JPopupMenu getPopupMenu()
	{
		JPopupMenu popup = new JPopupMenu("ProcessMenu");

		List<MProcess> processes = model.fetchProcesses(Env.getCtx(), parent.getCurrentTab());
		if (processes.size() == 0)
			return null;

		for (MProcess process : processes)
		{
			CMenuItem mi = createProcessMenuItem(process);
			popup.add(mi);
		}

		return popup;
	}

	public void showPopup()
	{
		JPopupMenu popup = getPopupMenu();
		if (popup == null)
			return;

		final AbstractButton button = action.getButton();
		if (button.isShowing())
			popup.show(button, 0, button.getHeight()); // below button
	}

	private CMenuItem createProcessMenuItem(final MProcess process)
	{
		final CMenuItem mi = new CMenuItem(model.getDisplayName(process));
		mi.setToolTipText(model.getDescription(process));
		mi.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				startProcess(process);
			}
		});
		return mi;
	}

	private void startProcess(MProcess process) {
		final VButton button = new VButton(
				"StartProcess", // columnName,
				false, // mandatory,
				false, // isReadOnly,
				true, // isUpdateable,
				process.get_Translation(I_AD_Process.COLUMNNAME_Name),
				process.get_Translation(I_AD_Process.COLUMNNAME_Description),
				process.get_Translation(I_AD_Process.COLUMNNAME_Help),
				process.getAD_Process_ID());
		parent.actionButton(button);
	}
}
