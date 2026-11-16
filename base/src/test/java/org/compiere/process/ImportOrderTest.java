package org.compiere.process;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.adempiere.core.domains.models.X_I_Order;
import org.compiere.model.MOrder;
import org.junit.jupiter.api.Test;

class ImportOrderTest {

	@Test
	void appliesImportedWarehouseWhenPresent() {
		MOrder order = mock(MOrder.class);
		X_I_Order importOrder = mock(X_I_Order.class);
		when(importOrder.getM_Warehouse_ID()).thenReturn(101);

		ImportOrder.setWarehouseFromImport(order, importOrder);

		verify(order).setM_Warehouse_ID(101);
	}

	@Test
	void leavesWarehouseUnchangedWhenImportHasNoWarehouse() {
		MOrder order = mock(MOrder.class);
		X_I_Order importOrder = mock(X_I_Order.class);

		ImportOrder.setWarehouseFromImport(order, importOrder);

		verify(order, never()).setM_Warehouse_ID(org.mockito.ArgumentMatchers.anyInt());
	}
}
