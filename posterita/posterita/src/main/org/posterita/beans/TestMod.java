package org.posterita.beans;

import java.math.BigDecimal;

public class TestMod
{
    public static void main(String args[])
    {
        BigDecimal qtyOnHand = new BigDecimal(100);
        BigDecimal qtyEntered = new BigDecimal(50);
        BigDecimal qtyOrdered = new BigDecimal(25);
        
        BigDecimal qty = qtyOnHand.multiply(qtyEntered).divide(qtyOrdered, 12, BigDecimal.ROUND_HALF_UP);
        
        System.out.println(qty);
    }
}
