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

package org.eevolution.freight.engine;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Properties;

/**
 * Created by e-Evolution author Victor Perez <victor.perez@e-evolution.com> on 23/08/16.
 */
public class FreightRule extends org.eevolution.engine.freight.FreightRule {

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
            Timestamp date, 
            String trxName,
            Map<String, Object> parameters) {
    	return super.getFreightRate(ctx, shipperId, freightCategoryId, currencyId, locationFromId, locationToId, date, trxName, parameters);
    }

	@Override
	public org.eevolution.freight.engine.FreightInfo calculate(Properties ctx, int shipperId, int locationId, int locationToId, int freightCategoryId,
			int currencyId, Timestamp date, BigDecimal weight, BigDecimal volume, String trxName, Map<String, Object> parameters) {
		org.eevolution.engine.freight.FreightInfo info = super.calculate(ctx, shipperId, locationId, locationToId, freightCategoryId, currencyId, date, weight, volume, trxName, parameters);
		return new FreightInfo(info);
	}
}
