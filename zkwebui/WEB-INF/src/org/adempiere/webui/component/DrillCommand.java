/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
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
package org.adempiere.webui.component;

import java.util.Map;

import org.adempiere.webui.event.DrillEvent;
import org.compiere.model.MQuery;
import org.zkoss.json.JSONArray;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuService;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;

/**
 * 
 * @author hengsin
 *
 */
public class DrillCommand implements AuService {

	public DrillCommand() {
	}

	public boolean service(AuRequest request, boolean everError) {
		if (!DrillEvent.ON_DRILL_ACROSS.equals(request.getCommand())
				&& !DrillEvent.ON_DRILL_DOWN.equals(request.getCommand()))
			return false;

		final Map<?, ?> map = request.getData();
		JSONArray data = (JSONArray) map.get("data");

		final Component comp = request.getComponent();
		if (comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, this);
		
		if (data == null || data.size() < 2)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {
					Objects.toString(data), this });
		
		String columnName = (String) data.get(0);
		String tableName = MQuery.getZoomTableName(columnName);
		String code = (String) data.get(1);
		//
		MQuery query = new MQuery(tableName);
		query.addRestriction(columnName, MQuery.EQUAL, code);

		Events.postEvent(new DrillEvent(request.getCommand(), comp, query));

		return true;
	}
}
