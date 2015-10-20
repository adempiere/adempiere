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
	private CButton		bImage;
	/**	Product Code		*/
	private CLabel 		fValue;
	/**	Product Name		*/
	private CLabel 		fName;
	/**	Product UOM Symbol	*/
	private CLabel 		fUOMSymbol;
	/**	Product Price		*/
	private CLabel 		fPrice;
	/**	Product Category	*/
	private CLabel		fProductCategory;
	/**	Product Tax			*/
	private CLabel		fProductTax;
	/**	Product Description	*/
	private CLabel		fDescription;
	/**	Right Panel			*/
	private CPanel		v_RightPanel;
	/**	Button Size			*/
	private final int	BUTTON_SIZE = 100;
	
	@Override
	protected void init() {
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Set Border
		TitledBorder border = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		border.setTitleFont(v_POSPanel.getFont());
		border.setTitleColor(AdempierePLAF.getTextColor_Label());
		setBorder(border);
		//	
		FontMetrics metrics = getFontMetrics(v_POSPanel.getPlainFont());
		//	Instance Panels
		v_RightPanel = new CPanel(new GridBagLayout());
		//	For Name
		fName = new CLabel (Msg.getElement(Env.getCtx(), "ProductName"));
		fName.setFont(v_POSPanel.getBigFont());
		fName.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fName, new GridBagConstraints(0, 0, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 0), 0, 0));
		//	For Price
		fPrice = new CLabel (v_POSPanel.getNumberFormat().format(Env.ZERO));
		fPrice.setFont(v_POSPanel.getBigFont());
		fPrice.setHorizontalAlignment(CLabel.RIGHT);
		fPrice.setHorizontalTextPosition(CLabel.RIGHT);
		fPrice.setPreferredSize(new Dimension(100, metrics.getHeight()));
		fPrice.setMinimumSize(new Dimension(150, metrics.getHeight()));
		//	Add
		v_RightPanel.add(fPrice, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 0), 0, 0));
		//	For Value
		fValue = new CLabel (Msg.getElement(Env.getCtx(), "ProductValue"));
		fValue.setFont(v_POSPanel.getPlainFont());
		fValue.setHorizontalAlignment(CLabel.LEFT);
		fValue.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fValue, new GridBagConstraints(0, 1, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For UOM
		fUOMSymbol = new CLabel (Msg.getElement(Env.getCtx(), "C_UOM_ID"));
		fUOMSymbol.setFont(v_POSPanel.getPlainFont());
		fUOMSymbol.setHorizontalAlignment(CLabel.LEFT);
		fUOMSymbol.setHorizontalTextPosition(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fUOMSymbol, new GridBagConstraints(0, 2, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		fProductCategory = new CLabel(Msg.getElement(Env.getCtx(), "M_Product_Category_ID"));
		fProductCategory.setFont(v_POSPanel.getPlainFont());
		fProductCategory.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fProductCategory, new GridBagConstraints(0, 3, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Category
		fProductTax = new CLabel(Msg.getElement(Env.getCtx(), "C_TaxCategory_ID"));
		fProductTax.setFont(v_POSPanel.getPlainFont());
		fProductTax.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fProductTax, new GridBagConstraints(0, 4, 2, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	For Description
		fDescription = new CLabel(Msg.getElement(Env.getCtx(), "Description"));
		fDescription.setFont(v_POSPanel.getPlainFont());
		fDescription.setHorizontalAlignment(CLabel.LEFT);
		fDescription.setPreferredSize(new Dimension(20, metrics.getHeight()));
		fDescription.setMaximumSize(new Dimension(20, metrics.getHeight()));
		//	Add
		v_RightPanel.add(fDescription, new GridBagConstraints(0, 5, 2, 1, 0, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	For Image
		bImage = new CButton();
		bImage.setFont(v_POSPanel.getFont());
		bImage.setPreferredSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		bImage.setMinimumSize(new Dimension(BUTTON_SIZE, BUTTON_SIZE));
		bImage.setFocusable(false);
		bImage.setVerticalTextPosition(SwingConstants.BOTTOM);
		bImage.setHorizontalTextPosition(SwingConstants.CENTER);
		//	Add to panel
		//	Add Doc Info
		add(bImage, new GridBagConstraints(0, 0, 1, 1, 0.1, 1
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 0, 5, 5), 0, 0));
		//	Add to Header
		add(v_RightPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	}
	
	/**
	 * Set Values from product
	 * @param p_M_Product_ID
	 * @param p_AD_Image_ID
	 * @return void
	 */
	private void setValuesFromProduct(int p_M_Product_ID, int p_AD_Image_ID) {
		if(p_M_Product_ID <= 0)
			return;
		//	Refresh Values
		MProduct m_Product = MProduct.get(m_ctx, p_M_Product_ID);
		String currencyISO_Code = v_POSPanel.getCurSymbol();
		fValue.setText(m_Product.getValue());
		fPrice.setText(currencyISO_Code + " " 
					+ v_POSPanel.getNumberFormat()
						.format(v_POSPanel.getPrice(m_Product)));
		fName.setText(m_Product.getName());
		fUOMSymbol.setText(m_Product.getC_UOM().getUOMSymbol());
		fProductCategory.setText(m_Product.getM_Product_Category().getName());
		fProductTax.setText(m_Product.getC_TaxCategory().getName());
		String m_Description = m_Product.getDescription();
		if(m_Description == null)
			m_Description = "-";
		fDescription.setText(m_Description.trim());
		//	Set Image
		if(p_AD_Image_ID != 0) {
			MImage image = MImage.get(Env.getCtx(), p_AD_Image_ID);
			Image img = image.getImage();
			//	Change Image Size
			Image imgResized = img.getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_SMOOTH) ;  
			bImage.setIcon(new ImageIcon(imgResized));
		} else {
			bImage.setIcon(null);
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
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProduct(int p_M_Product_ID) {
		//	Valid Product
		if(p_M_Product_ID == 0)
			return;
		//	Get POS Key
		int m_C_POSKey_ID = DB.getSQLValue(null, "SELECT pk.C_POSKey_ID "
				+ "FROM C_POSKey pk "
				+ "WHERE pk.C_POSKeyLayout_ID = ? "
				+ "AND pk.M_Product_ID = ? "
				+ "AND pk.IsActive = 'Y'", v_POSPanel.getC_POSKeyLayout_ID(), p_M_Product_ID);
		//	Valid POS Key
		if(m_C_POSKey_ID <= 0) {
			return;
		}
		MPOSKey key =  new MPOSKey(m_ctx, m_C_POSKey_ID, null);
		//	
		setValuesFromProduct(p_M_Product_ID, key.getAD_Image_ID());
	}
}