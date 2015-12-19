/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;

import org.adempiere.pos.service.ProductInfo;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.model.MImage;
import org.compiere.model.MPOSKey;
import org.compiere.model.MProduct;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSInfoProduct extends WPOSSubPanel {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param posPanel
	 */
	public WPOSInfoProduct(WPOS posPanel) {
		super(posPanel);
		
	}
	
	private Panel 		parameterPanel;
	/**	Image Product		*/
	private Panel		bImage;
	/**	Product Code		*/
	private Label 		fValue;
	/**	Product Name		*/
	private Label 		fName;
	/**	Product Price		*/
	private Label 		fPrice;
	/**	Product Description	*/
	private Label		fDescription;
	/**	Product UOM Symbol	*/
	private Label 		fUOMSymbol;
	/**	Product Category	*/
	private Label		fProductCategory;
	/**	Product Tax			*/
	private Label		fProductTax;
	

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
		Groupbox groupPanel = new Groupbox();
		Grid infoProductLayout = GridFactory.newGridLayout();

		Caption v_TitleBorder = new Caption(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		groupPanel.appendChild(v_TitleBorder);
		groupPanel.appendChild(infoProductLayout);
		
		Grid labelLayout = GridFactory.newGridLayout();
		Borderlayout fullPanel = new Borderlayout();
		
		Rows rows = null;
		Row row = null;	
		North north = new North();

		north.setStyle("border: none; width:100%;");
		north.setZindex(0);
		fullPanel.appendChild(north);

		Panel buttonPanel = new Panel();

		buttonPanel.appendChild(labelLayout);
		parameterPanel.appendChild(groupPanel);
		infoProductLayout.setWidth("100%");
		infoProductLayout.setHeight("95%");
		rows = infoProductLayout.newRows();
		row = rows.newRow();
		
		//	For Image
		bImage = new Panel();
		row.appendChild(bImage);
		bImage.setWidth("138px");
		bImage.setHeight("130px");	
		
		row.appendChild(buttonPanel);
		rows = labelLayout.newRows();
		row = rows.newRow();
		//	For Value
		fValue = new Label ();
		fValue.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fValue);
		fPrice = new Label ();
		fPrice.setStyle(WPOS.FONTSIZELARGE+"font-weight:bold");
		//	Add
		row.appendChild(fPrice);
		
		row = rows.newRow();
		//	For Name
		fName = new Label ();
		fName.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fName);
		
		row = rows.newRow();
		//  For UOM
		fUOMSymbol = new Label (Msg.getElement(Env.getCtx(), "C_UOM_ID"));
		fUOMSymbol.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fUOMSymbol);
		
		row = rows.newRow();
		//	For Category
		fProductCategory = new Label(Msg.getElement(Env.getCtx(), "M_Product_Category_ID"));
		fProductCategory.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(fProductCategory);
		
		row = rows.newRow();
		//	For Category
		fProductTax = new Label(Msg.getElement(Env.getCtx(), "C_TaxCategory_ID"));
		fProductTax.setStyle("Font-size:medium; font-weight:bold");
		
		//	Add
		row.appendChild(fProductTax);
		
		row = rows.newRow();
		//	For Description
		fDescription = new Label ();

		fDescription.setClass("label-description");
		//	Add
		row.appendChild(fDescription);
		initialValue();
	}
	
	/**
	 * Initial value
	 * @param key
	 * @return void
	 */
	public void initialValue() {
		fDescription.setText(Msg.getElement(Env.getCtx(), "Description"));
		fName.setText(Msg.getElement(Env.getCtx(), "ProductName"));
		fPrice.setText(Msg.getElement(Env.getCtx(), "Price"));
		fValue.setText(Msg.getElement(Env.getCtx(), "ProductValue"));
		fUOMSymbol.setText(Msg.getElement(Env.getCtx(), "C_UOM_ID"));
		fProductCategory.setText(Msg.getElement(Env.getCtx(), "M_Product_Category_ID"));
		fProductTax.setText(Msg.getElement(Env.getCtx(), "C_TaxCategory_ID"));
		bImage.getChildren().clear();
	}
	
	/**
	 * Get Panel 
	 * @return Panel
	 */
	public Panel getPanel(){
		return parameterPanel;
	}

	/**
	 * setValuesFromProduct
	 * @param productId
	 * @param imageId
     */
	public void setValuesFromProduct(int productId, int imageId) {
		if(productId <= 0){
			initialValue();
			return;
		}
		
		//	Refresh Values
		ProductInfo productInfo = new ProductInfo(productId, imageId , posPanel.getM_PriceList_Version_ID() , posPanel.getM_Warehouse_ID());
		String currencyISO_Code = posPanel.getCurSymbol();
		fValue.setText(productInfo.value);
		fPrice.setText(currencyISO_Code + "" 
					+ posPanel.getNumberFormat()
						.format(productInfo.priceStd));
		fName.setText(productInfo.name);
		fUOMSymbol.setText(productInfo.uomSymbol);
		fProductCategory.setText(productInfo.productCategoryName);
		fProductTax.setText(productInfo.productTaxCategory);
		fDescription.setText(productInfo.description);
		if(productInfo.imageData != null) {
			Label label = new Label();
			North nt = new North();
			Borderlayout mainLayout = new Borderlayout();
			AImage img = null;
			byte[] data = productInfo.imageData;
			if (data != null && data.length > 0) {
				try {
					img = new AImage(null, data);				
				} catch (Exception e) {		
				}
			}
			Image bImg = new Image();
			bImg.setContent(img);
			bImg.setWidth("100%");
			bImg.setHeight("100px");
			nt.appendChild(bImg);
		
		label.setStyle("word-wrap: break-word; white-space: pre-line;margin: 25px 0px 0px 0px; top:20px; font-size:10pt; font-weight: bold;color: #FFF;");
		label.setHeight("100%");
		bImage.setClass("z-button");
		
		mainLayout.appendChild(nt);
		mainLayout.setStyle("background-color: transparent");
		nt.setStyle("background-color: transparent");
		bImage.getChildren().clear();
		bImage.appendChild(mainLayout);
		bImage.invalidate();
		
		} else {
			bImage.getChildren().clear();
			bImage.invalidate();
		}
	}
	
	/**
	 * Refresh Product from Key
	 * @param key
	 * @return void
	 */
	public void refreshProduct(MPOSKey key) {
		if(key == null){
			initialValue();
			return;
		}
			
		setValuesFromProduct(key.getM_Product_ID(), key.getAD_Image_ID());
	}
	
	/**
	 * Refresh from product
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProduct(int p_M_Product_ID) {
		//	Valid Product
		if(p_M_Product_ID == 0){
			initialValue();
			return;
		}
		//	Get POS Key
		int m_C_POSKey_ID = DB.getSQLValue(null, "SELECT pk.C_POSKey_ID "
				+ "FROM C_POSKey pk "
				+ "WHERE pk.C_POSKeyLayout_ID = ? "
				+ "AND pk.M_Product_ID = ? "
				+ "AND pk.IsActive = 'Y'", posPanel.getC_POSKeyLayout_ID(), p_M_Product_ID);
		//	Valid POS Key
		if(m_C_POSKey_ID <= 0) {
			return;
		}
		MPOSKey key =  new MPOSKey(ctx, m_C_POSKey_ID, null);
		//	
		setValuesFromProduct(p_M_Product_ID, key.getAD_Image_ID());
	}
}