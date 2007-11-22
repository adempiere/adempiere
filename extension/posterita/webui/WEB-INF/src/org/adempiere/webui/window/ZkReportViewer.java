/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2007 Low Heng Sin hengsin@avantz.com
 * _____________________________________________
 *****************************************************************************/
package org.adempiere.webui.window;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.compiere.print.ReportEngine;
import org.zkoss.util.media.AMedia;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Toolbarbutton;


/**
 * 
 * @author Low Heng Sin
 *
 */public class ZkReportViewer extends Window {

	private ReportEngine report;

	public ZkReportViewer(ReportEngine re, String title) {
		this.report = re;
		this.setTitle(title);
		init();
	}
	
	private void init() {
		Grid grid = new Grid();
		grid.setWidth("100%");
		Rows rows = new Rows();
		Row row = new Row();
		Toolbar toolbar = new Toolbar();
		toolbar.setHeight("26px");
		Toolbarbutton button = new Toolbarbutton();
		button.setImage("/images/Print24.gif");
		button.setTooltip("Print");
		toolbar.appendChild(button);
		row.appendChild(toolbar);
		rows.appendChild(row);
		
		row = new Row();
		Iframe iframe = new Iframe();
		iframe.setId("reportFrame");
		int height = Double.valueOf(SessionManager.getAppDesktop().getClientInfo().desktopHeight * 0.85).intValue();
		height = height - 30;
		iframe.setHeight(height + "px");
		iframe.setWidth("100%");
		AMedia media = new AMedia(getTitle(), "pdf", "application/pdf", report.createPDFData());
		iframe.setContent(media);
		row.appendChild(iframe);
		rows.appendChild(row);
		
		grid.appendChild(rows);
		this.appendChild(grid);
		
		this.setBorder("normal");
	}
}
