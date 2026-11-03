package org.eevolution.distribution.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.compiere.model.MProduct;
import org.junit.jupiter.api.Test;

class MDDOrderLineTest {

	@Test
	void calculatesLineWeightAndVolumeUsingOrderedQuantity() {
		MDDOrderLine line = mock(MDDOrderLine.class, CALLS_REAL_METHODS);
		MProduct product = mock(MProduct.class);
		doReturn(product).when(line).getProduct();
		doReturn(new BigDecimal("4")).when(line).getQtyOrdered();
		doReturn(new BigDecimal("2")).when(product).getWeight();
		doReturn(new BigDecimal("3")).when(product).getVolume();

		assertEquals(new BigDecimal("8"), line.getWeight());
		assertEquals(new BigDecimal("12"), line.getVolume());
	}

	@Test
	void treatsMissingProductWeightAndVolumeAsZero() {
		MDDOrderLine line = mock(MDDOrderLine.class, CALLS_REAL_METHODS);
		MProduct product = mock(MProduct.class);
		doReturn(product).when(line).getProduct();
		doReturn(new BigDecimal("4")).when(line).getQtyOrdered();

		assertEquals(BigDecimal.ZERO, line.getWeight());
		assertEquals(BigDecimal.ZERO, line.getVolume());
	}
}
