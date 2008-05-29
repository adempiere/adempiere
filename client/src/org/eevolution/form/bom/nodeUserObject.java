package org.eevolution.form.bom;

import org.eevolution.model.X_PP_Product_BOM;
import org.eevolution.model.X_PP_Product_BOMLine;
import org.compiere.model.X_M_Product;

public class nodeUserObject {
  public X_M_Product M_Product = null;
  public String displayString = ""; 
  public X_PP_Product_BOM bom = null;
  public X_PP_Product_BOMLine bomLine = null;
  public boolean isChosen = false;
  public boolean isCheckbox = false;
  public boolean isMandatory = false;
  public boolean isOptional = false;


  public nodeUserObject(String displayString, X_M_Product M_Product, X_PP_Product_BOM bom, X_PP_Product_BOMLine bomLine) {
    this.displayString = displayString;
    this.M_Product = M_Product;
    this.bom = bom;
    this.bomLine = bomLine;
    System.out.println("bomLine: " + bomLine);
    if(bom != null) {
    if(bomLine != null) {
       System.out.println("bomLine.getComponentType: " + bomLine.getComponentType());
       System.out.println("bomLine.COMPONENTTYPE_Component: " + bomLine.COMPONENTTYPE_Component);
       if(bomLine.getComponentType().equals(bomLine.COMPONENTTYPE_Component)) {
          System.out.println("setting checkbox to true");
          isCheckbox = true;
          isMandatory = true;
       }
       if(bomLine.getComponentType().equals(bomLine.COMPONENTTYPE_Variant)) {
          System.out.println("setting checkbox to true");
          isCheckbox = true;
          isOptional = true;
       }
  
     

    }

    }
  }



  public String toString() {
    return displayString;
  }

}
