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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
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
	private CButton		bImage;
	/**	Product Code		*/
	private CLabel 		fValue;
	/**	Product Name		*/
	private CLabel 		fName;
	/**	Product Description	*/
	private CLabel		fDescription;
	/**	Left Panel			*/
	private CPanel		v_LeftPanel;
	/**	Right Panel			*/
	private CPanel		v_RightPanel;
	
	
	@Override
	protected void init() {
		//	Set Layout
		setLayout(new GridBagLayout());
		//	Set Border
		TitledBorder border = BorderFactory.createTitledBorder(Msg.getMsg(Env.getCtx(), "InfoProduct"));
		border.setTitleFont(v_POSPanel.getFont());
		border.setTitleColor(AdempierePLAF.getTextColor_Label());
		setBorder(border);
		//	Padding
		int m_RightPadding = 30;
		Font localFont = AdempierePLAF.getFont_Field().deriveFont(Font.PLAIN, 16);
		//	Instance Panels
		v_LeftPanel = new CPanel(new GridBagLayout());
		v_RightPanel = new CPanel(new GridBagLayout());
		//	For Value
		fValue = new CLabel (Msg.getElement(Env.getCtx(), "ProductValue"));
		fValue.setFont(v_POSPanel.getFont());
		fValue.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fValue, new GridBagConstraints(0, 0, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 5), 0, 0));
		//	For Name
		fName = new CLabel (Msg.getElement(Env.getCtx(), "ProductName"));
		fName.setFont(localFont);
		fName.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fName, new GridBagConstraints(0, 1, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, m_RightPadding), 0, 0));
		//	For Description
		fDescription = new CLabel (Msg.getElement(Env.getCtx(), "Description"));
		fDescription.setFont(localFont);
		fDescription.setHorizontalAlignment(CLabel.LEFT);
		//	Add
		v_RightPanel.add(fDescription, new GridBagConstraints(0, 2, 1, 1, 1, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	For Image
		bImage = new CButton();
		bImage.setFont(v_POSPanel.getFont());
		bImage.setFocusable(false);
		//	Add to panel
		v_LeftPanel.add(bImage);
		//	Add to main panel
		//	Add Doc Info
		add(v_LeftPanel, new GridBagConstraints(0, 0, 1, 1, 0.5, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Add to Header
		add(v_RightPanel, new GridBagConstraints(1, 0, 1, 1, 1, 1
				,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 0, 5, 5), 0, 0));
	}
	
	
//	public void refreshProduct(int p_M_Product_ID) {
//		if (p_M_Product_ID != 0) {
//			MImage image = MImage.get(Env.getCtx(), key.getAD_Image_ID());
//			Image img = image.getImage();
//			//	https://github.com/erpcya/AD-POS-WebUI/issues/29
//			//	Change Image Size
//			Image imgResized = img.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH) ;  
//			button.setIcon(new ImageIcon(imgResized));
//			button.setVerticalTextPosition(SwingConstants.BOTTOM);
//			button.setHorizontalTextPosition(SwingConstants.CENTER);
//		}
//	}
}