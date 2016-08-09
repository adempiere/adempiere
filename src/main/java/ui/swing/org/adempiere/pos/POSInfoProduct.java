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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.ProductInfo;
import org.compiere.model.MPOSKey;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;


/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * <li> Show Product Info
 */
public class POSInfoProduct extends POSSubPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7670376798966290954L;
	
	/**
	 * Instance from main panel
	 * *** Constructor ***
	 * @param posPanel
	 */
	public POSInfoProduct(VPOS posPanel) {
		super(posPanel);
	}
	
	/**	Image Product		*/
	private CButton 	buttonImage;
	/**	Product Code		*/
	private CLabel 		labelValue;
	/**	Product Name		*/
	private CLabel 		labelName;
	/**	Product UOM Symbol	*/
	private CLabel 		labelUOMSymbol;
	/**	Product Name		*/
	private CLabel 		labelPriceName;
	/**	Product Price		*/
	private CLabel 		labelPriceLimit;
	/**	Product Price		*/
	private CLabel 		labelPrice;
	/**	Product Price		*/
	private CLabel 		labelPriceList;
	/**	Product Category	*/
	private CLabel 		labelProductCategory;
	/**	Product Tax			*/
	private CLabel 		labelProductTax;
	/**	Product Description	*/
	private CLabel 		labelDescription;
	/**	Right Panel			*/
	private CPanel 		rightPanel;
	/**	Button Size			*/
	private final int	BUTTON_SIZE = 100;
	
	@Override
	protected void init() {
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Set Border
		TitledBorder border = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		border.setTitleFont(posPanel.getFont());
		border.setTitleColor(AdempierePLAF.getTextColor_Label());
		setBorder(border);
		//	
		FontMetrics metrics = getFontMetrics(posPanel.getPlainFont());
		//	Instance Panels
		rightPanel = new CPanel(new GridBagLayout());
		//	For Name
		labelName = new CLabel (Msg.getElement(Env.getCtx(), "ProductName"));
		labelName.setFont(posPanel.getBigFont());
		labelName.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		rightPanel.add(labelName, new GridBagConstraints(0, 0, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));

		labelPriceName = new CLabel (Msg.parseTranslation(ctx , "@PriceStd@ , @PriceList@ ") + posPanel.getCurSymbol());
		labelPriceName.setFont(posPanel.getBigFont());
		labelPriceName.setHorizontalAlignment(CLabel.LEFT);
		labelPriceName.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		rightPanel.add(labelPriceName, new GridBagConstraints(0, 1, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));



		/*labelPriceLimit = new CLabel (posPanel.getNumberFormat().format(Env.ZERO));
		labelPriceLimit.setFont(posPanel.getBigFont());
		labelPriceLimit.setHorizontalAlignment(CLabel.RIGHT);
		labelPriceLimit.setHorizontalTextPosition(CLabel.RIGHT);
		labelPriceLimit.setPreferredSize(new Dimension(100, metrics.getHeight()));
		labelPriceLimit.setMinimumSize(new Dimension(150, metrics.getHeight()));
		//	Add
		rightPanel.add(labelPriceLimit, new GridBagConstraints(1, 1, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));*/

		//	For Price
		labelPrice = new CLabel (posPanel.getNumberFormat().format(Env.ZERO));
		labelPrice.setFont(posPanel.getBigFont());
		labelPrice.setHorizontalAlignment(CLabel.RIGHT);
		labelPrice.setHorizontalTextPosition(CLabel.RIGHT);
		labelPrice.setPreferredSize(new Dimension(100, metrics.getHeight()));
		labelPrice.setMinimumSize(new Dimension(150, metrics.getHeight()));
		//	Add
		rightPanel.add(labelPrice, new GridBagConstraints(2, 1, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));


		labelPriceList = new CLabel (posPanel.getNumberFormat().format(Env.ZERO));
		labelPriceList.setFont(posPanel.getBigFont());
		labelPriceList.setHorizontalAlignment(CLabel.RIGHT);
		labelPriceList.setHorizontalTextPosition(CLabel.RIGHT);
		labelPriceList.setPreferredSize(new Dimension(100, metrics.getHeight()));
		labelPriceList.setMinimumSize(new Dimension(150, metrics.getHeight()));
		//	Add
		rightPanel.add(labelPriceList, new GridBagConstraints(3, 1, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));


		//	For Value
		labelValue = new CLabel (Msg.getElement(Env.getCtx(), "ProductValue"));
		labelValue.setFont(posPanel.getPlainFont());
		labelValue.setHorizontalAlignment(CLabel.LEFT);
		labelValue.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		rightPanel.add(labelValue, new GridBagConstraints(0, 2, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For UOM
		labelUOMSymbol = new CLabel (Msg.getElement(Env.getCtx(), "C_UOM_ID"));
		labelUOMSymbol.setFont(posPanel.getPlainFont());
		labelUOMSymbol.setHorizontalAlignment(CLabel.LEFT);
		labelUOMSymbol.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		rightPanel.add(labelUOMSymbol, new GridBagConstraints(0, 3, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		labelProductCategory = new CLabel(Msg.getElement(Env.getCtx(), "M_Product_Category_ID"));
		labelProductCategory.setFont(posPanel.getPlainFont());
		labelProductCategory.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		rightPanel.add(labelProductCategory, new GridBagConstraints(0, 4, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		labelProductTax = new CLabel(Msg.getElement(Env.getCtx(), "C_TaxCategory_ID"));
		labelProductTax.setFont(posPanel.getPlainFont());
		labelProductTax.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		rightPanel.add(labelProductTax, new GridBagConstraints(0, 5, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Description
		labelDescription = new CLabel(Msg.getElement(Env.getCtx(), "Description"));
		labelDescription.setFont(posPanel.getPlainFont());
		labelDescription.setHorizontalAlignment(CLabel.LEFT);
		labelDescription.setPreferredSize(new Dimension(20, metrics.getHeight()));
		labelDescription.setMaximumSize(new Dimension(20, metrics.getHeight()));
		//	Add
		rightPanel.add(labelDescription, new GridBagConstraints(0, 6, 2, 1, 0, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	For Image
		buttonImage = new CButton();
		buttonImage.setFont(posPanel.getFont());
		buttonImage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		buttonImage.setMinimumSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		buttonImage.setFocusable(false);
		buttonImage.setVerticalTextPosition(SwingConstants.BOTTOM);
		buttonImage.setHorizontalTextPosition(SwingConstants.CENTER);
		//	Add to panel
		//	Add Doc Info
		add(buttonImage, new GridBagConstraints(0, 0, 1, 1, 0.1, 1
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	Add to Header
		add(rightPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	}
	
	/**
	 * Set Values from product
	 * @param productId
	 * @param imageId
	 * @return void
	 */
	private void setValuesFromProduct(int productId, BigDecimal quantity ,  int imageId, int priceListId , int partnerId) {
		if(productId <= 0)
			return;
		//	Refresh Values
		labelPriceName.setText(Msg.parseTranslation(ctx , "@PriceStd@ , @PriceList@ ") + posPanel.getCurSymbol());
		ProductInfo productInfo = new ProductInfo(productId ,quantity , imageId , priceListId , partnerId );
		labelValue.setText(productInfo.value);
		//labelPriceLimit.setText(posPanel.getNumberFormat().format(warehousePrice.getPriceLimit()));
		labelPrice.setText(posPanel.getNumberFormat().format(productInfo.priceStd));
		labelPriceList.setText(posPanel.getNumberFormat().format(productInfo.priceList));
		labelName.setText(productInfo.name);
		labelUOMSymbol.setText(productInfo.uomSymbol);
		labelProductCategory.setText(productInfo.productCategoryName);
		labelProductTax.setText(productInfo.productTaxCategory);
		posPanel.updateProductPlaceholder(productInfo.name);
		String description = productInfo.description;
		if(description == null)
			description = "-";
		labelDescription.setText(description.trim());
		//	Set Image
		if(productInfo.imageData != null) {
			try
			{
				BufferedImage image = ImageIO.read(new ByteArrayInputStream(productInfo.imageData));
				Image imgResized = image.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH);
				buttonImage.setIcon(new ImageIcon(imgResized));
			}
			catch (IOException e) {
			}
		} else {
			buttonImage.setIcon(null);
			buttonImage.invalidate();
		}
	}

	/**
	 * Refresh Product from Key
	 * @param key
	 * @return void
	 */
	public void refreshProduct(MPOSKey key ,BigDecimal quantity ,  int priceListId , int partnerId) {
		if(key == null) {
			//initialValue();
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
		labelValue.setText(NO_TEXT);
		labelPriceName.setText(NO_TEXT);
		labelPrice.setText(NO_TEXT);
		labelPriceList.setText(NO_TEXT);
		labelName.setText(NO_TEXT);
		labelUOMSymbol.setText(NO_TEXT);
		labelProductCategory.setText(NO_TEXT);
		labelProductTax.setText(NO_TEXT);
		labelDescription.setText(NO_TEXT);
		buttonImage.setIcon(null);
		buttonImage.invalidate();
	}

	public String getUOMSymbol()
	{
		return  labelUOMSymbol.getText();
	}
}