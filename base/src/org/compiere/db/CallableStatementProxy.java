/******************************************************************************
 * Copyright (C) 2008 Low Heng Sin                                            *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CStatementVO;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 * Dynamic proxy for the CCallableStatement
 * @author Low Heng Sin
 */
public class CallableStatementProxy extends PreparedStatementProxy {

	public CallableStatementProxy(CStatementVO vo) {
		super(vo);
	}

	public CallableStatementProxy(int resultSetType, int resultSetConcurrency,
			String sql0, String trxName) {
		super(resultSetType, resultSetConcurrency, sql0, trxName);
	}
	
	/**
     * Initialise the prepared statement wrapper object 
     */
    protected void init()
    {
        try
        {
            Connection conn = null;
            Trx trx = p_vo.getTrxName() == null ? null : Trx.get(p_vo.getTrxName(), true);
            if (trx != null)
            {
                conn = trx.getConnection();
            }
            else
            {
                if (p_vo.getResultSetConcurrency() == ResultSet.CONCUR_UPDATABLE)
                    m_conn = DB.getConnectionRW ();
                else
                    m_conn = DB.getConnectionRO();
                conn = m_conn;
            }
            if (conn == null)
                throw new DBException("No Connection");
            p_stmt = conn.prepareCall(p_vo.getSql(), p_vo.getResultSetType(), p_vo.getResultSetConcurrency());
            return;
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, p_vo.getSql(), e);
            throw new DBException(e);
        }
    }
}
