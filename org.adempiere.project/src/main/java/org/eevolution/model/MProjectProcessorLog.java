/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.eevolution.model;

import org.compiere.model.AdempiereProcessorLog;

import java.sql.ResultSet;
import java.util.Properties;

public class MProjectProcessorLog extends X_C_ProjectProcessorLog implements AdempiereProcessorLog {
    public MProjectProcessorLog(Properties ctx, int C_ProjectProcessorLog_ID, String trxName) {
        super(ctx, C_ProjectProcessorLog_ID, trxName);
    }

    public MProjectProcessorLog(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * 	Parent Constructor
     *	@param parent parent
     *	@param summary summary
     */
    public MProjectProcessorLog (MProjectProcessor parent, String summary)
    {
        this (parent.getCtx(), 0, parent.get_TrxName());
        setClientOrg(parent);
        setC_ProjectProcessor_ID(parent.getC_ProjectProcessor_ID());
        setSummary(summary);
    }
}
