package org.eevolution.hr.util;

import java.io.File;
import java.util.List;

import org.eevolution.hr.model.MHRPaySelectionCheck;


/**
 *
 */
public interface HRPaymentExport
{
	public int exportToFile(List<MHRPaySelectionCheck> checks, File file, StringBuffer err);

}	//	PaymentExport
