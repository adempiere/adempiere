package org.adempiere.pos;
import java.math.BigDecimal;

/**
 * Created by e-Evolution on 17/02/16.
 */
public interface POSScalesPanelInterface {
    public String getElectronicScales();

    public String getMeasureRequestCode();

    public Object getScalesTimer();

    public int getWindowNo();

    public String getProductUOMSymbol();

    public void setScalesMeasure(String measure);

    public void setQuantity(BigDecimal qty);

    public void changeViewPanel();

    public void updateLineTable();

    public void hideScales();

    public void showKeyboard();
}
