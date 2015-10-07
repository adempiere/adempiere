package org.adempiere.pos;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkex.zul.North;

public class WPOSInfoProduct extends WPosSubPanel {

	private Panel parameterPanel;
	/**	Image Product		*/
	private Button		bImage;
	/**	Product Code		*/
	private Label 		fValue;
	/**	Product Name		*/
	private Label 		fName;
	/**	Product Price		*/
	private Label 		fPrice;
	/**	Product Description	*/
	private Label		fDescription;
	
	public WPOSInfoProduct(WPOS posPanel) {
		super(posPanel);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -175459707049618428L;

	@Override
	public void onEvent(Event arg0) throws Exception {
		
	}

	@Override
	protected void init() {
		parameterPanel = new Panel();
		Grid infoProductLayout = GridFactory.newGridLayout();
		Grid labelLayout = GridFactory.newGridLayout();
		Borderlayout fullPanel = new Borderlayout();
		
		Rows rows = null;
		Row row = null;	
		North north = new North();

		north.setStyle("border: none; width:100%");
		north.setZindex(0);
		fullPanel.appendChild(north);

		Panel buttonPanel = new Panel();

		buttonPanel.appendChild(labelLayout);
		parameterPanel.appendChild(infoProductLayout);
		infoProductLayout.setWidth("100%");
		infoProductLayout.setHeight("100%");
		rows = infoProductLayout.newRows();
		row = rows.newRow();
		
		//	For Image
		bImage = new Button("-");
		row.appendChild(bImage);
			
		
		row.appendChild(buttonPanel);
		rows = labelLayout.newRows();
		row = rows.newRow();
		//	For Value
		fValue = new Label (Msg.getElement(Env.getCtx(), "ProductValue"));
		fValue.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fValue);
		fPrice = new Label (Msg.getElement(Env.getCtx(), "Price"));
		fPrice.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fPrice);
		
		row = rows.newRow();
		//	For Name
		fName = new Label (Msg.getElement(Env.getCtx(), "ProductName"));
		fName.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fName);
		
		row = rows.newRow();
		//	For Description
		fDescription = new Label (Msg.getElement(Env.getCtx(), "Description"));
		fDescription.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fDescription);

	}
	
	public Panel getPanel(){
		return parameterPanel;
	}
}
