/** ****************************************************************************
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos.service;
import java.math.BigDecimal;

/**
 * Interface for Scales Panel , allows the implement Scales Panel out of POS
 * eEvolution author Victor Perez <victor.perez@e-evolution.com> , Created by e-Evolution on 17/02/16.
 */
public interface POSScalesPanelInterface {
    public String getElectronicScales();

    public String getMeasureRequestCode();

    public Object getScalesTimer();

    public int getWindowNo();

    public String getProductUOMSymbol();

    public void setScalesMeasure(String measure);

    public void setQty(BigDecimal qty);

    public void changeViewPanel();

    public void updateLineTable();

    public void hideScales();

    public void showKeyboard();

    public void refreshPanel();

    public void getMainFocus();
}
