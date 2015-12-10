/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Invoice Tax Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MInvoiceTax.java,v 1.5 2006/10/06 00:42:24 jjanke Exp $
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  		<li>FR [ 2214883 ] Remove SQL code and Replace for Query
 */
public class MInvoiceTax extends X_C_InvoiceTax
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5560880305482497098L;


	/**
	 * 	Get Tax Line for Invoice Line
	 *	@param line invoice line
	 *	@param precision currency precision
	 *	@param oldTax if true old tax is returned
	 *	@param trxName transaction name
	 *	@return existing or new tax
	 */
	public static MInvoiceTax get (MInvoiceLine line, int precision, 
		boolean oldTax, String trxName)
	{
		MInvoiceTax retValue = null;
		if (line == null || line.getC_Invoice_ID() == 0 || line.isDescription())
			return null;
		int C_Tax_ID = line.getC_Tax_ID();
		boolean isOldTax = oldTax && line.is_ValueChanged(MInvoiceLine.COLUMNNAME_C_Tax_ID); 
		if (isOldTax)
		{
			Object old = line.get_ValueOld(MInvoiceLine.COLUMNNAME_C_Tax_ID);
			if (old == null)
				return null;
			C_Tax_ID = ((Integer)old).intValue();
		}
		if (C_Tax_ID == 0)
		{
			s_log.warning("C_Tax_ID=0");
			return null;
		}
		
		retValue = new Query(line.getCtx(), Table_Name, "C_Invoice_ID=? AND C_Tax_ID=?", trxName)
						.setParameters(line.getC_Invoice_ID(), C_Tax_ID)
						.firstOnly();
		if (retValue != null)
		{
			retValue.set_TrxName(trxName);
			retValue.setPrecision(precision);
			s_log.fine("(old=" + oldTax + ") " + retValue);
			return retValue;
		}
		// If the old tax was required and there is no MInvoiceTax for that
		// return null, and not create another MInvoiceTax - teo_sarca [ 1583825 ]
		else {
			if (isOldTax)
				return null;
		}
		
		//	Create New
		retValue = new MInvoiceTax(line.getCtx(), 0, trxName);
		retValue.set_TrxName(trxName);
		retValue.setClientOrg(line);
		retValue.setC_Invoice_ID(line.getC_Invoice_ID());
		retValue.setC_Tax_ID(line.getC_Tax_ID());
		retValue.setPrecision(precision);
		retValue.setIsTaxIncluded(line.isTaxIncluded());
		s_log.fine("(new) " + retValue);
		return retValue;
	}	//	get
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MInvoiceTax.class);
	
	
	/**************************************************************************
	 * 	Persistency Constructor
	 *	@param ctx context
	 *	@param ignored ignored
	 *	@param trxName transaction
	 */
	public MInvoiceTax (Properties ctx, int ignored, String trxName)
	{
		super(ctx, 0, trxName);
		if (ignored != 0)
			throw new IllegalArgumentException("Multi-Key");
		setTaxAmt (Env.ZERO);
		setTaxBaseAmt (Env.ZERO);
		setIsTaxIncluded(false);
	}	//	MInvoiceTax

	/**
	 * 	Load Constructor.
	 * 	Set Precision and TaxIncluded for tax calculations!
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MInvoiceTax (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MInvoiceTax
	
	/** Tax							*/
	private MTax 		m_tax = null;
	/** Cached Precision			*/
	private Integer		m_precision = null;
	
	
	/**
	 * 	Get Precision
	 * 	@return Returns the precision or 2
	 */
	private int getPrecision ()
	{
		if (m_precision == null)
			return 2;
		return m_precision.intValue();
	}	//	getPrecision

	/**
	 * 	Set Precision
	 *	@param precision The precision to set.
	 */
	protected void setPrecision (int precision)
	{
		m_precision = new Integer(precision);
	}	//	setPrecision

	/**
	 * 	Get Tax
	 *	@return tax
	 */
	protected MTax getTax()
	{
		if (m_tax == null)
			m_tax = MTax.get(getCtx(), getC_Tax_ID());
		return m_tax;
	}	//	getTax
	
	
	/**************************************************************************
	 * Ossagho Development Team 09-03-2015
	 * 	Calculate/Set Tax Base Amt from Invoice Lines
	 * 	@return true if tax calculated
	 */
	public boolean calculateTaxFromLines ()
	{
		BigDecimal taxBaseAmt = Env.ZERO;
		BigDecimal taxAmt = Env.ZERO;
		//
		boolean documentLevel = getTax().isDocumentLevel();
		MTax tax = getTax();
		//
//		String sql = "SELECT il.LineNetAmt, COALESCE(il.TaxAmt,0), i.IsSOTrx "
//			+ "FROM C_InvoiceLine il"
//			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
//			+ "WHERE il.C_Invoice_ID=? AND il.C_Tax_ID=?";
		String sql = "SELECT il.LineNetAmt, COALESCE(il.TaxAmt,0), i.IsSOTrx,il.C_Tax_ID,il.C_InvoiceLine_ID,il.M_Product_ID,il.C_Charge_ID FROM C_InvoiceLine il INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID) "
				+ "WHERE il.C_Invoice_ID=? AND il.C_Tax_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getC_Invoice_ID());
			pstmt.setInt (2, getC_Tax_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				//	BaseAmt
				BigDecimal baseAmt = rs.getBigDecimal(1);
				taxBaseAmt = taxBaseAmt.add(baseAmt);
				//	TaxAmt
				BigDecimal amt = rs.getBigDecimal(2);
				if (amt == null)
					amt = Env.ZERO;
				boolean isSOTrx = "Y".equals(rs.getString(3));
				//
				// phib [ 1702807 ]: manual tax should never be amended
				// on line level taxes
				if (!documentLevel && amt.signum() != 0 && !isSOTrx)	//	manually entered
					;
				else if (documentLevel || baseAmt.signum() == 0)
					amt = Env.ZERO;
				else	// calculate line tax
					amt = tax.calculateTax(baseAmt, isTaxIncluded(), getPrecision());
				//
				taxAmt = taxAmt.add(amt);
				taxAmt = taxAmt.add(CreateChildTaxes(rs.getBigDecimal(4),rs.getBigDecimal(5),rs.getBigDecimal(6),rs.getBigDecimal(7),baseAmt));
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		//	Calculate Tax
		if (documentLevel || taxAmt.signum() == 0)
			taxAmt = tax.calculateTax(taxBaseAmt, isTaxIncluded(), getPrecision());
		setTaxAmt(taxAmt);

		//	Set Base
		if (isTaxIncluded())
			setTaxBaseAmt (taxBaseAmt.subtract(taxAmt));
		else
			setTaxBaseAmt (taxBaseAmt);
		return true;
	}	//	calculateTaxFromLines

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MInvoiceTax[");
		sb.append("C_Invoice_ID=").append(getC_Invoice_ID())
			.append(",C_Tax_ID=").append(getC_Tax_ID())
			.append(", Base=").append(getTaxBaseAmt()).append(",Tax=").append(getTaxAmt())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**@author AB
	 * Ossagho Development Team 09-03-2015
	 * @param C_Tax_ID
	 * @param C_InvoiceLine_ID
	 * @param M_Product_ID
	 * @param C_LineAmount
	 * @return
	 */
	public BigDecimal CreateChildTaxes(BigDecimal C_Tax_ID,BigDecimal C_InvoiceLine_ID,BigDecimal M_Product_ID,BigDecimal C_Charge_ID,BigDecimal C_LineAmount)
	{
		
		BigDecimal v_tax_firsttax= Env.ZERO;
		BigDecimal v_tax_LastTax= Env.ZERO;
		BigDecimal v_tax_BaseAmt= C_LineAmount;
		BigDecimal v_tax_TaxSum= Env.ZERO;
		BigDecimal v_tax_TaxSumBaseAmt= C_LineAmount;
		BigDecimal v_tax_Child= Env.ZERO;
        BigDecimal v_TaxablePC=Env.ZERO;
		
		int Child_TaxID;
		
		
		int Count=0;
		String sql=null;
		 sql="delete from c_invoicetax_detail where c_invoiceline_id="+C_InvoiceLine_ID;
		 Count=DB.executeUpdateEx(sql, get_TrxName());
		 System.out.println("Total rows deleted from Order tax Detail : "+Count);
	
		{
	
			
			//String sql2="select c_tax_id,Calculated_on from c_tax where parent_tax_id=? order by c_tax_id";
			//String sql2="select t.c_tax_id,t.Calculated_on from c_tax t inner join c_tax_child c on t.c_tax_id=
			                //c.child_tax_id where c.c_tax_id=? and t.isparent='N' order by t.c_tax_id";          10-02-2015
			String sql2="Select  distinct c.Child_tax_id,cd.calculated_on,c.sequence,c.c_tax_id,cd.taxablepcntg from c_tax_childdetails cd inner join c_tax_child c on c.c_tax_child_id=cd.c_tax_child_id where c.c_tax_id=? and cd.isactive='Y' order by c.sequence";
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			try
			{
				pstmt2 = DB.prepareStatement (sql2, get_TrxName());
				pstmt2.setBigDecimal(1, C_Tax_ID);
				rs2 = pstmt2.executeQuery   ();
				while (rs2.next ())
				{	
					
					X_C_INVOICETAX_DETAIL Obj_InvoiceTax_Detail=new X_C_INVOICETAX_DETAIL(getCtx(), 0, get_TrxName());
					
													
					Child_TaxID=rs2.getInt(1);

                    //AB 28-10 Taxable Percentage Change
                    v_TaxablePC=rs2.getBigDecimal(5);
					
					if (rs2.getString(2).equals("BA"))
					{
						v_tax_Child=MTax.calculateChildTax(Child_TaxID,v_tax_BaseAmt, isTaxIncluded(), getPrecision(),rs2.getInt(4),v_TaxablePC);
						//v_tax_Child= taxAmt.add(tax.calculateChildTax(Child_TaxID,v_tax_BaseAmt, isTaxIncluded(), getPrecision()));	
						Obj_InvoiceTax_Detail.setTaxBaseAmt(v_tax_BaseAmt);
					}
													
					if(v_tax_firsttax.compareTo(new BigDecimal(0))==0)
					{
						v_tax_firsttax = v_tax_Child;
															
					}
					
					if (rs2.getString(2).equals("LT"))
					{
						v_tax_Child=(MTax.calculateChildTax(Child_TaxID,v_tax_LastTax, isTaxIncluded(), getPrecision(),rs2.getInt(4),v_TaxablePC));
						Obj_InvoiceTax_Detail.setTaxBaseAmt(v_tax_LastTax);
					}
					
					if (rs2.getString(2).equals("FT"))
					{
						v_tax_Child= (MTax.calculateChildTax(Child_TaxID,v_tax_firsttax, isTaxIncluded(), getPrecision(),rs2.getInt(4),v_TaxablePC));
						Obj_InvoiceTax_Detail.setTaxBaseAmt(v_tax_firsttax);
					}
					
					if (rs2.getString(2).equals("SM"))
					{
						v_tax_Child= (MTax.calculateChildTax(Child_TaxID,v_tax_TaxSum, isTaxIncluded(), getPrecision(),rs2.getInt(4),v_TaxablePC));
						Obj_InvoiceTax_Detail.setTaxBaseAmt(v_tax_TaxSum);
					}
					
					if (rs2.getString(2).equals("SB"))
					{
						v_tax_Child= (MTax.calculateChildTax(Child_TaxID,v_tax_TaxSumBaseAmt, isTaxIncluded(), getPrecision(),rs2.getInt(4),v_TaxablePC));
						Obj_InvoiceTax_Detail.setTaxBaseAmt(v_tax_TaxSumBaseAmt);
					}
					
					String GetRateSql ="select rate from (select rate from c_tax_childdetails cd inner join c_tax_child c on c.c_tax_child_id=cd.c_tax_child_id where  c.child_tax_id="+Child_TaxID+" and c.c_tax_id="+C_Tax_ID+" and cd.validfrom<=(select sysdate from dual) order by cd.validfrom desc ) where rownum=1";
					BigDecimal TaxRate=DB.getSQLValueBD(null,GetRateSql);
					
					v_tax_LastTax=v_tax_Child;
					v_tax_TaxSumBaseAmt=v_tax_TaxSumBaseAmt.add(v_tax_Child);
					v_tax_TaxSum=v_tax_TaxSum.add(v_tax_Child);
						
					Obj_InvoiceTax_Detail.setTaxAmt(v_tax_LastTax);
					Obj_InvoiceTax_Detail.setC_Invoice_ID(getC_Invoice_ID());
					Obj_InvoiceTax_Detail.setC_Tax_ID(Child_TaxID);
					Obj_InvoiceTax_Detail.setTAXRATE(TaxRate);
					Obj_InvoiceTax_Detail.setProcessed(false);
					Obj_InvoiceTax_Detail.setIsTaxIncluded(false);
					Obj_InvoiceTax_Detail.setPARENT_TAXID(getC_Tax_ID());
					Obj_InvoiceTax_Detail.setC_InvoiceLine_ID(C_InvoiceLine_ID.intValue());
					if(M_Product_ID!=null)
					{
						Obj_InvoiceTax_Detail.setM_Product_ID(M_Product_ID.intValue());
					}
					else
					{
						Obj_InvoiceTax_Detail.setC_Charge_ID(C_Charge_ID.intValue());
					}
					Obj_InvoiceTax_Detail.setProcessed(false);
					Obj_InvoiceTax_Detail.save();
					System.out.print("Row created in Invoice Tax Detail Table Successfully");
					
				}
	
				/**************************************************************************
				 * 	Calculate/ CHILD Set Tax Amt from Order Lines
				 *  By: OSSAGHO Development Team 08-09-2014
				 */				
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, get_TrxName(), e);
				return Env.ZERO;
			}
			
		}
		
		return v_tax_TaxSum;
	}
	
}	//	MInvoiceTax
