package org.adempiere.process;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.model.MOrderLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MProduct;
import org.compiere.model.MClientInfo;
import org.compiere.model.MWarehouse;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * This process finds all complete sales orders that has physical items yet to 
 * deliver and tries to allocate items from the items on hand.
 * 
 * @author daniel.tamm
 *
 */
public class AllocateSalesOrders extends SvrProcess {
	
	private int	m_warehouseId;
	
	public static class StockInfo {
		
		public int			productId;
		public BigDecimal	qtyOnHand;
		public BigDecimal	qtyAvailable;
		public BigDecimal	qtyReserved;
		public BigDecimal	qtyAllocated;
		
		public StockInfo() {}
		
	}
	
	private static final String 
			query = "select C_OrderLine.* from C_OrderLine " + 
				   "JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID " + 
				   "JOIN M_Product ON C_OrderLine.M_Product_ID=M_Product.M_Product_ID " + 
				   "where C_Order.IsSOTrx='Y' AND C_Order.DocStatus='CO' AND QtyAllocated<(QtyOrdered-QtyDelivered) " + 
				   "AND M_Product.M_Product_ID=? " + 
				   "order by PriorityRule, C_OrderLine.Created ";
	

	@Override
	protected void prepare() {
		
		m_warehouseId = 1000000; // TODO: Should be mandatory in the process definition
		
        ProcessInfoParameter[] para = getParameter();
        for (int i = 0; i < para.length; i++) {
            String name = para[i].getParameterName();
            if (para[i].getParameter() == null);
            else if (name.equals("M_Warehouse_ID")) {
            	m_warehouseId = para[i].getParameterAsInt();
            } else {
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
            }
        }
		
	}
	
	/**
	 * Finds all order lines that contains not yet delivered physical items of a specific product.
	 * 
	 * @param conn			An open connection.
	 * @param productId		The product id being allocated
	 * @return  Order lines to allocate products to.
	 * @throws SQLException
	 */
	public static List<MOrderLine> getOrderLinesToAllocate(Connection conn, int productId, String trxName) throws SQLException {
		List<MOrderLine> result = new Vector<MOrderLine>();
		Properties ctx = Env.getCtx();
		MOrderLine line;
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, productId);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			line = new MOrderLine(ctx, rs, trxName);
			result.add(line);
		}
		rs.close();
		ps.close();
		return(result);
	}
	
	/**
	 * Finds all products that can be allocated. A product can be allocated if there are more items 
	 * on hand than what is already allocated. To be allocated the item must also be in demand
	 * (reserved < allocated)
	 * 
	 * @param 	conn
	 * @return
	 * @throws 	SQLException
	 */
	public static List<StockInfo> getProductsToAllocate(Connection conn, int WarehouseID) throws SQLException {
		
		List<StockInfo> result = new Vector<StockInfo>();
		StockInfo si;
		String query1 = "select M_Product_ID, sum(qtyonhand), sum(qtyreserved), sum(m_Product_Stock_v.qtyallocated) " +
						"from M_Product_Stock_v " + 
						"WHERE M_Warehouse_ID=? AND M_Product_ID in " +
						"(select DISTINCT C_OrderLine.M_Product_ID FROM C_OrderLine " +
					   "JOIN C_Order ON C_OrderLine.C_Order_ID=C_Order.C_Order_ID " + 
					   "JOIN M_Product ON C_OrderLine.M_Product_ID=M_Product.M_Product_ID " +
					   "JOIN M_Product_Stock_v ON C_OrderLine.M_Product_ID=M_Product_Stock_v.M_Product_ID " +
					   "WHERE " +
					   "C_Order.IsSOTrx='Y' AND C_Order.DocStatus='CO' AND C_OrderLine.M_Warehouse_ID=? AND " + 
					   "(QtyOrdered-QtyDelivered)>0 AND (QtyOrdered-QtyDelivered)>C_OrderLine.QtyAllocated)" + 
					   "group by M_Product_ID " + 
					   "order by M_Product_ID";

		PreparedStatement ps = conn.prepareStatement(query1);
		ps.setInt(1, WarehouseID);
		ps.setInt(2, WarehouseID);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			si = new StockInfo();
			si.productId = rs.getInt(1);
			si.qtyOnHand = rs.getBigDecimal(2);
			si.qtyReserved = rs.getBigDecimal(3);
			si.qtyAvailable = si.qtyOnHand.subtract(si.qtyReserved);
			si.qtyAllocated = rs.getBigDecimal(4);
			result.add(si);
		}
		rs.close();
		ps.close();
		return(result);
	}
	
	@Override
	protected String doIt() throws Exception {

		Connection conn = DB.getConnectionRO();		
		List<StockInfo> products = AllocateSalesOrders.getProductsToAllocate(conn, m_warehouseId);
		conn.close();
		List<MOrderLine> lines;
		MOrderLine line;
		BigDecimal lineAllocate;
		BigDecimal toAllocate;
		BigDecimal onHand;
		BigDecimal allocated;
		BigDecimal qtyAvailable;
		BigDecimal willAllocate;
		StockInfo si;
		
		// Make sure we have settings that needs allocation
		MWarehouse warehouse = new MWarehouse(getCtx(), m_warehouseId, get_TrxName());
		MOrgInfo orgInfo = MOrgInfo.get(getCtx(), warehouse.getAD_Org_ID(), get_TrxName());
		if (!orgInfo.getDeliveryPolicy().equals(MClientInfo.DELIVERYPOLICY_StrictOrder)) {
			return "The current delivery policy of the warehouse doesn't use allocation.";
		}
		
		if (products.size()==0) {
			log.info("There are no products to allocate.");
			return "";
		}
		
		/** Iterate through all products to allocate */
		for (Iterator<StockInfo> it = products.iterator(); it.hasNext();) {
			
			MProduct product = null;
			si = it.next();
			conn = DB.getConnectionRO();
			// Get all lines to allocate
			lines = AllocateSalesOrders.getOrderLinesToAllocate(conn, si.productId, get_TrxName());
			conn.close();
			
			// Check if there are any lines to allocate
			// and create a log.
			if (lines.size()>0) {
				product = lines.get(0).getProduct();
				line = lines.get(0);
				qtyAvailable = si.qtyAvailable;
				onHand = si.qtyOnHand;
				// TO allocate = Min (qtyOnHand - qtyAllocated, qtyReserved - qtyAllocated)
				toAllocate = si.qtyOnHand.subtract(si.qtyAllocated).min(si.qtyReserved.subtract(si.qtyAllocated));
				if (toAllocate.signum()>0) {
					log.info("Allocating " + product.getValue() + " : " + product.getName() + " Avail: " + qtyAvailable + " On hand: " + onHand + " To alloc: " + toAllocate);
					log.info(lines.size() + " lines to allocate.");
				} else {
					continue;
				}
			} else {
				continue;
			}
			
			allocated = BigDecimal.ZERO;
			
			// When we are here we know what product, qty available and we have the lines
			// that need to be allocated.
			for (Iterator<MOrderLine> it2 = lines.iterator(); it2.hasNext(); ) {
				line = it2.next();

				// Calculate what to allocate (what we want)
				lineAllocate = line.getQtyOrdered().subtract(line.getQtyDelivered()).subtract(line.getQtyAllocated());
				willAllocate = lineAllocate.min(toAllocate);
				if (willAllocate.signum()==1) {
					willAllocate = line.allocateOnHand(willAllocate, get_TrxName());
					allocated = allocated.add(willAllocate);
					toAllocate = toAllocate.subtract(willAllocate);
					log.info("Allocated " + willAllocate + " to order " + line.getC_Order().getDocumentNo() + " " + toAllocate + " left to allocate.");
					if (toAllocate.equals(BigDecimal.ZERO))
						break;
				} else {
					log.info("Skipping allocation of order " + line.getC_Order().getDocumentNo());
					continue;
				}
			}
			
		}
		
		return("");
	}

}
