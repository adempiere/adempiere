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

import org.compiere.model.MFreight;
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

        BigDecimal freightRateAmount = BigDecimal.ZERO;
        FreightServiceInterface freightService = new FreightService();
        BigDecimal freightRate = getFreightRate(
                ctx,
                shipperId,
                freightCategoryId,
                currencyId,
                locationFromId,
                locationToId,
                date,
                trxName);
        if (freightRate.signum() != 0)
            freightRateAmount = product.getWeight().multiply(freightRate);
        else
            freightRateAmount = BigDecimal.ZERO;
        return  freightRateAmount;
    }

    /**
     * get Freight Rate
     * @param ctx
     * @param shipperId
     * @param freightCategoryId
     * @param currencyId
     * @param date
     * @param trxName
     * @return
     */
    public BigDecimal getFreightRate(
            Properties ctx,
            int shipperId,
            int freightCategoryId,
            int currencyId,
            int locationFromId,
            int locationToId,
            Timestamp date, String trxName) {

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

        FreightServiceInterface freightService = new FreightService();
        Optional<MFreight> freightOptioonal = freightService.getFreightValid(ctx, shipperId, freightCategoryId, currencyId, date, trxName)
                .stream()
                .filter(freight -> {
                    if (freight.getC_Country_ID() == 0 && freight.getTo_Country_ID() == 0)
                        return true;
                    else if (freight.getC_Country_ID() == 0 &&  (countryToOptionalId.isPresent() && countryToOptionalId.get() == freight.getTo_Country_ID()))
                        return true;
                    else if ((countryFromOptionalId.isPresent() && countryFromOptionalId.get() == freight.getC_Country_ID())
                            &&  (countryToOptionalId.isPresent() && countryToOptionalId.get() == freight.getTo_Country_ID()))
                        return true;
                    else
                        return false;
                }).filter(freight -> {
                    if (freight.getC_Region_ID() == 0 && freight.getTo_Region_ID() == 0)
                        return true;
                    else if (freight.getC_Region_ID() == 0 &&   (regionToOptionalId.isPresent() && regionToOptionalId.get() == freight.getTo_Region_ID()))
                        return true;
                    else if ((regionFromOptionalId.isPresent() && regionFromOptionalId.get() == freight.getC_Region_ID()) &&
                            (regionToOptionalId.isPresent() && regionToOptionalId.get() == freight.getTo_Region_ID()))
                        return true;
                    else
                        return false;
                }).findFirst();
        if (freightOptioonal.isPresent())
        {
            return freightOptioonal.get().getFreightAmt();
        }
        else
        {
            return BigDecimal.ZERO;
        }
    }
}
