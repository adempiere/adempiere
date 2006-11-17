<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=paymentInfo.jsp'/>
</c:if>
<c:if test='${empty payment}'>
  <c:redirect url='index.jsp'/>
</c:if>
<html>
<!--
  - Author:  Jorg Janke
  - Version: $Id: paymentInfo.jsp,v 1.3 2006/05/06 02:13:56 mdeaelfweald Exp $
  - Adempiere ERP & CRM Smart Business Solution - Copyright (c) 1999-2003 Jorg Janke
  - - -
  - Web Payment Info
  - Variables: webOrder, webUser, payment
  -->
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Payment Info</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content"> 
	  <c:if test='${not empty webOrder}'>
	  <h1>Thank you for your Order</h1>
      <table class="contentTable">
        <tr> 
          <th>Order</th>
          <th>Lines</th>
          <th>Shipping</th>
          <th>Tax</th>
          <th>Total</th>
        </tr>
        <tr> 
          <td class="oddRow">&nbsp;<c:out value='${webOrder.documentNo}'/></td>
          <td class="oddRow amount">&nbsp;<fmt:formatNumber value='${webOrder.totalLines}' type="currency" currencySymbol=""/></td>
          <td class="oddRow amount">&nbsp;<fmt:formatNumber value='${webOrder.freightAmt}' type="currency" currencySymbol=""/></td>
          <td class="oddRow amount">&nbsp;<fmt:formatNumber value='${webOrder.taxAmt}' type="currency" currencySymbol=""/></td>
          <td class="oddRow amount"><b><c:out value='${webOrder.currencyISO}'/>&nbsp;<fmt:formatNumber value='${webOrder.grandTotal}' type="currency" currencySymbol=""/></b></td>
        </tr>
      </table>
	  </c:if>
	  <c:if test='${empty webOrder}'>
	  <h1>Payment of  <c:out value='${payment.currencyISO}'/> <fmt:formatNumber value='${payment.payAmt}' type="currency" currencySymbol=""/></h1>
	  </c:if>
      <h2>Please enter your payment information</h2>
      <c:if test="${not empty payment.r_PnRef}">
        <p><b>Payment Info: <c:out value='${payment.r_PnRef}'/></b></p>
        <c:if test="${not empty payment.errorMessage}">
            <div class="error"><c:out value="${payment.errorMessage}"/></div>
        </c:if>
      </c:if>

	  <form action="paymentServlet" method="post" enctype="application/x-www-form-urlencoded" 
	  	name="payment" target="_top" id="payment"
	    onSubmit="checkForm(this, new Array ('CreditCardNumber','CreditCardExpMM','CreditCardExpYY','CreditCardVV','A_Name','A_Street','A_City','A_Zip'));">
        <fieldset>
            <legend>Payment Information</legend>

            <label id="LBL_CreditCard" for="Name">CreditCard</label>
            <select id="ID_CreditCard" name="CreditCard" size="1">
                <c:forEach items='${payment.creditCards}' var='cc'>
                    <option value="<c:out value='${cc.value}'/>" <c:if test='${payment.creditCardType == cc.value}'>selected</c:if>><c:out value='${cc.name}'/></option>
                </c:forEach>
            </select>
            <br/>

            <label id="LBL_CreditCardNumber" for="CreditCardNumber">Credit Card Number</label>
            <input class="mandatory" size="30" id="ID_CreditCardNumber" value='<c:out value="${payment.creditCardNumber}"/>' name="CreditCardNumber" maxlength="16" type="text"/>
            <br/>


            <label id="LBL_Exp">Expiry Date</label>
            <select id="ID_CreditCardExpMM" name="CreditCardExpMM" class="mandatory" size="1">
                <c:forEach var='mm' begin="1" end="12">
                    <option value="<c:out value='${mm}'/>" <c:if test='${payment.creditCardExpMM == mm}'>selected</c:if>><c:out value='${mm}'/></option>
                </c:forEach>
            </select>&nbsp;-&nbsp;
            <select id="ID_CreditCardExpYY" name="CreditCardExpYY" class="mandatory" size="1">
                <c:forEach var='yy' begin="5" end="20">
                    <option value="<c:out value='${yy}'/>" <c:if test='${payment.creditCardExpYY == yy}'>selected</c:if>><c:out value='${yy+2000}'/></option>
                </c:forEach>
            </select>
            <br/>

            <label id="LBL_CreditCardVV" for="CreditCardVV">Validation Code<sup>*</sup></label>
            <input class="mandatory" size="5" id="ID_CreditCardVV" value='<c:out value="${payment.creditCardVV}"/>' name="CreditCardVV" maxlength="4" type="text"/>
            <br/>

            <label id="LBL_A_Name" for="A_Name">Name on Credit Card</label>
            <input class="mandatory" size="40" id="ID_A_Name" value='<c:out value="${payment.a_Name}"/>' name="A_Name" maxlength="60" type="text"/>
            <br/>

            <label id="LBL_A_Street" for="A_Street">Street</label>
            <input class="mandatory" size="40" id="ID_A_Street" value='<c:out value="${payment.a_Street}"/>' name="A_Street" maxlength="60" type="text"/>
            <br/>

            <label id="LBL_A_City" for="A_City">City</label>
            <input class="mandatory" size="40" id="ID_A_City" value='<c:out value="${payment.a_City}"/>' name="A_City" maxlength="40" type="text"/>
            <br/>

            <label id="LBL_A_Zip" for="A_Zip">Zip</label>
            <input class="mandatory" size="10" id="ID_A_Zip" value='<c:out value="${payment.a_Zip}"/>' name="A_Zip" maxlength="10" type="text"/>
            <br/>

            <label id="LBL_A_State" for="A_State">State</label>
            <input size="10" id="ID_A_State" value='<c:out value="${payment.a_State}"/>' name="A_State" maxlength="20" type="text"/>
            <br/>

            <label id="LBL_A_Country" for="A_Country">Country</label>
            <input class="mandatory" size="40" id="ID_A_Country" value='<c:out value="${payment.a_Country}"/>' name="A_Country" maxlength="40" type="text"/>
            <br/>


            <div align="center">
                <input name="SavePayment" type="checkbox" id="SavePayment" value="SavePayment" checked>
                    Save Payment Information
            </div>
            <div class="buttons">
                <input type="reset" name="Reset" value="Reset">
                <input type="submit" name="Submit" id="Submit" value="Submit Payment">
            </div>
            <c:if test="${not empty payment.errorMessage}">
                <div class="error"><c:out value="${payment.errorMessage}"/></div>
            </c:if>
            <div id="processingDiv" style="display:none"><strong>Processing ...</strong></div>
            <div align="center">Enter all <b class="mandatory">mandatory</b> data. </div>

        </fieldset>
      </form>
      <div id="validationInfo">
          <div id="visaBox"><img src="visaCID.jpg" height="80" width="135"/></div>
          <div id="amexBox"><img src="amexCID.jpg" height="80" width="135"/></div>
          <strong>Credit Card Validation Code <br/>
              <font size="-1">(Card ID)<br/></font></strong>
          <font size="-1">Visa and Mastercard: 3 digits - back<br/>
            American Express: 4 digits - front</font>
	      <p>&nbsp;</p>
      </div>
	</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
