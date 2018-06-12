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


package org.compiere.model;

import org.adempiere.exceptions.AdempiereException;

import java.sql.ResultSet;
import java.util.Properties;

public class MStandardRequest extends X_R_StandardRequest{
    public MStandardRequest(Properties ctx, int R_StandardRequest_ID, String trxName) {
        super(ctx, R_StandardRequest_ID, trxName);
    }

    public MStandardRequest(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    protected boolean beforeSave (boolean newRecord)
    {
        if (getR_RequestRelated_ID() > 0 && getR_RequestRelated_ID() == getR_StandardRequest_ID())
            throw new AdempiereException("@R_RequestRelated_ID@ @NotValid@");

        return true;
    }
}
