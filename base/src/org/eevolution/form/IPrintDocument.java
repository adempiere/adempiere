
package org.eevolution.form;

import org.compiere.model.PO;

/**
 * Created by eEvolution author Victor Perez <victor.perez@e-evolution.com> on 25/01/15.
 */
public interface IPrintDocument {

    public void print (PO document, String printFormantName ,int windowNo);

}
