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

package org.eevolution.engine.freight;

import org.compiere.model.MLocation;
import org.compiere.model.MProduct;
import org.eevolution.service.FreightService;
import org.eevolution.service.FreightServiceInterface;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by e-Evolution author Victor Perez <victor.perez@e-evolution.com> on 23/08/16.
 */
public class FreightRule implements FreightRuleInterface {
    @Override
    public BigDecimal calculate(
            Properties ctx,
            int productId,
            int shipperId,
            int locationFromId,
            int locationToId,
            int freightCategoryId,
            int currencyId,
            Timestamp date,
            String trxName) {
        MProduct product = MProduct.get(ctx, productId);
        MLocation locationFrom = MLocation.get(ctx, locationFromId, trxName);
        MLocation locationTo = MLocation.get(ctx, locationToId, trxName);
        Optional<Integer> countryFromOptionalId;
        Optional<Integer> regionFromOptionalId;
        if (locationFrom != null && locationFrom.get_ID() > 0) {
            countryFromOptionalId = Optional.ofNullable(locationFrom.getC_Country_ID());
            regionFromOptionalId = Optional.ofNullable(locationFrom.getC_Region_ID());
        } else {
            countryFromOptionalId = Optional.empty();
            regionFromOptionalId = Optional.empty();
        }

        Optional<Integer> countryToOptionalId;
        Optional<Integer> regionToOptionalId;
        if (locationTo != null && locationTo.get_ID() > 0) {
            countryToOptionalId = Optional.ofNullable(locationTo.getC_Country_ID());
            regionToOptionalId = Optional.ofNullable(locationTo.getC_Region_ID());
        } else {
            countryToOptionalId = Optional.empty();
            regionToOptionalId = Optional.empty();
        }



        BigDecimal freightRateAmount = BigDecimal.ZERO;
        FreightServiceInterface freightService = new FreightService();
        BigDecimal freightRate = freightService.getFreightRate(
                ctx,
                shipperId,
                freightCategoryId,
                currencyId,
                countryFromOptionalId,
                regionFromOptionalId,
                countryToOptionalId,
                regionToOptionalId,
                date,
                trxName);
        if (freightRate.signum() != 0)
            freightRateAmount = product.getWeight().multiply(freightRate);
        else
            freightRateAmount = BigDecimal.ZERO;
        return  freightRateAmount;
    }
}
