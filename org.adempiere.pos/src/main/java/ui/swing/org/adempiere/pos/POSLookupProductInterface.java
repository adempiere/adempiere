package org.adempiere.pos;

import javax.swing.Timer;
/**
 * Created by e-Evolution on 17/02/16.
 */
public interface POSLookupProductInterface {

    public Object getProductTimer();
    public void findProduct(boolean isNewLine) throws Exception;
    public void quantityRequestFocus();

}
