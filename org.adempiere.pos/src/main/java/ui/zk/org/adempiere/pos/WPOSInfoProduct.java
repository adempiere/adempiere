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
import org.compiere.model.MPOSKey;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;

import java.math.BigDecimal;

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
	/**	Product Price List	*/
	private Label 		lPriceList;
	private Label 		fPriceList;
	/**	Product Description	*/
	private Label		fDescription;
	/**	Product UOM Symbol	*/
	private Label 		fUOMSymbol;
	/**	Product Category	*/
	private Label		fProductCategory;
	/**	Product Tax			*/
	private Label		fProductTax;
	/** Grid Panel 			*/
	private Grid 		infoProductLayout;
	private Grid 		labelLayout;
	private Panel 		buttonPanel;
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
		infoProductLayout = GridFactory.newGridLayout();

		Caption v_TitleBorder = new Caption(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		groupPanel.appendChild(v_TitleBorder);
		groupPanel.appendChild(infoProductLayout);
		
		labelLayout = GridFactory.newGridLayout();
		
		buttonPanel = new Panel();

		buttonPanel.appendChild(labelLayout);
		parameterPanel.appendChild(groupPanel);
		
		buttonPanel.setStyle("border: none; width:99%;moz-box-shadow: 0 0 0px #888;-webkit-box-shadow: 0 0 0px #888;box-shadow: 0 0 0px #888;");
		labelLayout.setStyle("border: none; width:100%;moz-box-shadow: 0 0 0px #888;-webkit-box-shadow: 0 0 0px #888;box-shadow: 0 0 0px #888;");
		infoProductLayout.setStyle("border: none; width:100%; moz-box-shadow: 0 0 0px #888;-webkit-box-shadow: 0 0 0px #888;box-shadow: 0 0 0px #888;");
		
		Rows rows = null;
		Row  row = null;
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
		
		row = rows.newRow();
		//  For Price List
		lPriceList = new Label ();
		lPriceList.setStyle("Font-size:medium; font-weight:bold");
		//	Add
		row.appendChild(lPriceList);
		

		fPrice = new Label ();
		fPrice.setStyle(WPOS.FONTSIZELARGE+"font-weight:bold");
		//	Add
		row.appendChild(fPrice);
		
		fPriceList = new Label ();
		fPriceList.setStyle(WPOS.FONTSIZELARGE+"font-weight:bold");
		//	Add
		row.appendChild(fPriceList);
		
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

		fDescription.setHeight("19px");	

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
		lPriceList.setText(Msg.parseTranslation(ctx , "@PriceStd@ , @PriceList@ ") + posPanel.getCurSymbol());
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
	public void setValuesFromProduct(int productId, BigDecimal quantity , int imageId, int priceListId , int partnerId) {
		if(productId <= 0){
			initialValue();
			return;
		}
		
		//	Refresh Values
		ProductInfo productInfo = new ProductInfo(productId, quantity ,  imageId , priceListId , partnerId);
		lPriceList.setText(Msg.parseTranslation(ctx , "@PriceStd@ , @PriceList@ ") + posPanel.getCurSymbol());
		fValue.setText(productInfo.value);
		fPrice.setText(posPanel.getNumberFormat().format(productInfo.priceStd));
		fPriceList.setText(posPanel.getNumberFormat().format(productInfo.priceList));
		fName.setText(productInfo.name);
		fUOMSymbol.setText(productInfo.uomSymbol);
		fProductCategory.setText(productInfo.productCategoryName);
		fProductTax.setText(productInfo.productTaxCategory);
		fDescription.setText(productInfo.description);
		if(productInfo.imageData != null) {
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
		
		bImage.setClass("z-button");
		
		mainLayout.appendChild(nt);
		mainLayout.setStyle("background-color: transparent");
		nt.setStyle("background-color: transparent");
		bImage.getChildren().clear();
		bImage.appendChild(mainLayout);
		bImage.invalidate();
		infoProductLayout.invalidate();
		labelLayout.invalidate();
		buttonPanel.invalidate();
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
	public void refreshProduct(MPOSKey key ,BigDecimal quantity ,  int priceListId , int partnerId) {
		if(key == null) {
			initialValue();
			return;
		}
		setValuesFromProduct(key.getM_Product_ID() , quantity , key.getAD_Image_ID() , priceListId , partnerId);
	}

	/**
	 * Refresh from product
	 * @param productId
	 * @return void
	 */
	public void refreshProduct(int productId , BigDecimal quantity , int priceListId , int partnerId) {
		int imageId = posPanel.getProductImageId(productId, posPanel.getC_POSKeyLayout_ID());
		setValuesFromProduct(productId, quantity , imageId , priceListId , partnerId);
	}
	
	/**
	 * Reset Values of Info Product
	 * @return void
	 */
	public void resetValues() {
		final String NO_TEXT = "--";
		fValue.setText(NO_TEXT);
		fPrice.setText(NO_TEXT);
		lPriceList.setText(NO_TEXT);
		fPriceList.setText(NO_TEXT);
		fName.setText(NO_TEXT);
		fUOMSymbol.setText(NO_TEXT);
		fProductCategory.setText(NO_TEXT);
		fProductTax.setText(NO_TEXT);
		fDescription.setText(NO_TEXT);
		bImage.getChildren().clear();
		bImage.invalidate();
	}

}