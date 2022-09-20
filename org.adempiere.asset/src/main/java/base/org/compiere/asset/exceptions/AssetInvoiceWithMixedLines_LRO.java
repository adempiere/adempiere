/**
 * 
 */
package org.compiere.asset.exceptions;


/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetInvoiceWithMixedLines_LRO extends AssetException {
	private static final long serialVersionUID = 1L;

	public AssetInvoiceWithMixedLines_LRO() {
		super("No new bills that contain both fixed and normal products");
	}
}
