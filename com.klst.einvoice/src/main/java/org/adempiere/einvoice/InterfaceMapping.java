package org.adempiere.einvoice;

import java.math.BigDecimal;

import org.compiere.model.MInvoice;

import com.klst.einvoice.unece.uncefact.Quantity;

//Proxy Subjekt-Interface: Der Klient/AbstractEinvoice hängt nur von dieser Abstraktion ab.
public interface InterfaceMapping {

	/**
	 * mapping of ADempiere UoM and quantity to UN/ECE QuantityType and quantity (Class einvoice.unece.uncefact.Quantity)
	 * <p>
	 * Example UoM mapping: 
	 * <br> "PCE" --> "EA"    // piece/each
	 * <br> "HR"  --> "HUR"   // hour
	 * <p>
	 * You can convert also: 1000kg to 1metric ton
	 * <p>
	 * Notes on UN/ECE QuantityType:
	 * 
	 * The unit of measure shall be chosen from the lists in UN/ECE Recommendation N°. 20 “Codes for Units of Measure Used in International Trade” 
	 * and UN/ECE Recommendation N° 21 “Codes for Passengers, Types of Cargo, Packages and Packaging Materials (with Complementary Codes for Package Names)” 
	 * applying the method described in UN/ECE Rec N° 20 Intro 2.a). 
	 * Note that in most cases it is not needed for Buyers and Sellers to implement these lists fully in their software. 
	 * Sellers need only to support the units needed for their goods and services; 
	 * Buyers only need to verify that the units used in the Invoice are equal to the units used in other documents 
	 * (such as Contract, Catalogue, Order and Despatch advice).
	 * 
	 * @param unitCode ADempiere UoM
	 * @param quantity
	 * @return object of einvoice.unece.uncefact.Quantity
	 */
	public Quantity mapToQuantity(String unitCode, BigDecimal quantity);
	
	public String mapUoM(String unitCode);
	
	/**
	 * Purchase order reference BT-13 - An identifier of a referenced purchase order, issued by the Buyer. 
	 * Cardinality: 0..1 (optional)
	 * 
	 * @param mInvoice
	 * @return identifier String
	 */
	public String mapPOReference(MInvoice mInvoice);
	
	/**
	 * optional Invoice note BT-22
	 * 
	 * @param mInvoice
	 * @return Text can be null
	 */
	public String mapNote(MInvoice mInvoice);

}
