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


import java.sql.ResultSet;
import java.util.Properties;

/**
 * Project Class
 */
public class MProjectClass extends X_C_ProjectClass {
    public MProjectClass(Properties ctx, int C_ProjectClass_ID, String trxName) {
        super(ctx, C_ProjectClass_ID, trxName);
    }

    public MProjectClass(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
