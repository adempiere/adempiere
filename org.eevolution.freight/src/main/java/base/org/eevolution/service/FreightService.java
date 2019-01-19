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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.service;

import org.compiere.model.Query;
import org.compiere.model.MFreight;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 20/08/16.
 */
public class FreightService implements FreightServiceInterface {

    public List<MFreight> getFreight(Properties ctx, int shipperId, int freightCategoryId, int currencyId, String trxName) {
        StringBuilder where = new StringBuilder();
        where.append(MFreight.COLUMNNAME_M_Shipper_ID).append("=? AND ")
                .append(MFreight.COLUMNNAME_M_FreightCategory_ID).append("=? AND ")
                .append(MFreight.COLUMNNAME_C_Currency_ID).append("=?");
        return new Query(ctx, MFreight.Table_Name, where.toString(), trxName)
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .setParameters(shipperId, freightCategoryId, currencyId)
                .list();
    }

    public List<MFreight> getFreightValid(Properties ctx, int shipperId, int freightCategoryId, int currencyId, Timestamp date, String trxName) {
        List<MFreight> freightValid = getFreight(ctx, shipperId, freightCategoryId, currencyId, trxName)
                .stream()
                .filter(freight -> freight != null && (freight.getValidFrom() == null || freight.getValidFrom().before(date)))
                .collect(Collectors.toList());
        return freightValid;
    }
}