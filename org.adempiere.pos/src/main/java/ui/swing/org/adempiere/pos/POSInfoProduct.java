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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.model.MImage;
import org.compiere.model.MPOSKey;
import org.compiere.model.MProduct;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.DB;
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
	/**	Product Price		*/
	private CLabel 		labelPrice;
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
		//	For Price
		labelPrice = new CLabel (posPanel.getNumberFormat().format(Env.ZERO));
		labelPrice.setFont(posPanel.getBigFont());
		labelPrice.setHorizontalAlignment(CLabel.RIGHT);
		labelPrice.setHorizontalTextPosition(CLabel.RIGHT);
		labelPrice.setPreferredSize(new Dimension(100, metrics.getHeight()));
		labelPrice.setMinimumSize(new Dimension(150, metrics.getHeight()));
		//	Add
		rightPanel.add(labelPrice, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		//	For Value
		labelValue = new CLabel (Msg.getElement(Env.getCtx(), "ProductValue"));
		labelValue.setFont(posPanel.getPlainFont());
		labelValue.setHorizontalAlignment(CLabel.LEFT);
		labelValue.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		rightPanel.add(labelValue, new GridBagConstraints(0, 1, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For UOM
		labelUOMSymbol = new CLabel (Msg.getElement(Env.getCtx(), "C_UOM_ID"));
		labelUOMSymbol.setFont(posPanel.getPlainFont());
		labelUOMSymbol.setHorizontalAlignment(CLabel.LEFT);
		labelUOMSymbol.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		rightPanel.add(labelUOMSymbol, new GridBagConstraints(0, 2, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		labelProductCategory = new CLabel(Msg.getElement(Env.getCtx(), "M_Product_Category_ID"));
		labelProductCategory.setFont(posPanel.getPlainFont());
		labelProductCategory.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		rightPanel.add(labelProductCategory, new GridBagConstraints(0, 3, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		labelProductTax = new CLabel(Msg.getElement(Env.getCtx(), "C_TaxCategory_ID"));
		labelProductTax.setFont(posPanel.getPlainFont());
		labelProductTax.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		rightPanel.add(labelProductTax, new GridBagConstraints(0, 4, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Description
		labelDescription = new CLabel(Msg.getElement(Env.getCtx(), "Description"));
		labelDescription.setFont(posPanel.getPlainFont());
		labelDescription.setHorizontalAlignment(CLabel.LEFT);
		labelDescription.setPreferredSize(new Dimension(20, metrics.getHeight()));
		labelDescription.setMaximumSize(new Dimension(20, metrics.getHeight()));
		//	Add
		rightPanel.add(labelDescription, new GridBagConstraints(0, 5, 2, 1, 0, 1
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
	private void setValuesFromProduct(int productId, int imageId) {
		if(productId <= 0)
			return;
		//	Refresh Values
		MProduct product = MProduct.get(ctx, productId);
		String currencyISOCode = posPanel.getCurSymbol();
		labelValue.setText(product.getValue());
		labelPrice.setText(currencyISOCode + " "
					+ posPanel.getNumberFormat()
						.format(posPanel.getPrice(product)));
		labelName.setText(product.getName());
		labelUOMSymbol.setText(product.getC_UOM().getUOMSymbol());
		labelProductCategory.setText(product.getM_Product_Category().getName());
		labelProductTax.setText(product.getC_TaxCategory().getName());
		String m_Description = product.getDescription();
		if(m_Description == null)
			m_Description = "-";
		labelDescription.setText(m_Description.trim());
		//	Set Image
		if(imageId != 0) {
			MImage image = MImage.get(Env.getCtx(), imageId);
			Image img = image.getImage();
			//	Change Image Size
			Image imgResized = img.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH) ;  
			buttonImage.setIcon(new ImageIcon(imgResized));
		} else {
			buttonImage.setIcon(null);
		}
	}
	
	/**
	 * Refresh Product from Key
	 * @param key
	 * @return void
	 */
	public void refreshProduct(MPOSKey key) {
		if(key == null)
			return;
		setValuesFromProduct(key.getM_Product_ID(), key.getAD_Image_ID());
	}
	
	/**
	 * Refresh from product
	 * @param productId
	 * @return void
	 */
	public void refreshProduct(int productId) {
		int imageId = 0;

		//	Valid Product
		if(productId == 0)
			return;

		//	Get POS Key
		int m_C_POSKey_ID = DB.getSQLValue(null, "SELECT pk.C_POSKey_ID "
				+ "FROM C_POSKey pk "
				+ "WHERE pk.C_POSKeyLayout_ID = ? "
				+ "AND pk.M_Product_ID = ? "
				+ "AND pk.IsActive = 'Y'", posPanel.getC_POSKeyLayout_ID(), productId);
		//	Valid POS Key
		if(m_C_POSKey_ID <= 0) {
			MPOSKey key =  new MPOSKey(ctx, m_C_POSKey_ID, null);
			imageId = key.getAD_Image_ID();
		}

		//	
		setValuesFromProduct(productId,imageId);
	}
}