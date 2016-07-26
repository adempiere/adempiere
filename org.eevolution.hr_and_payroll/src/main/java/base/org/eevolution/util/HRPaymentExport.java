package org.eevolution.util;

import org.eevolution.model.MHRPaySelectionCheck;

import java.io.File;
import java.util.List;


/**
 *
 */
public interface HRPaymentExport
{
	public int exportToFile(List<MHRPaySelectionCheck> checks, File file, StringBuffer err);

}	//	PaymentExport
