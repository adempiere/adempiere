<%@ include file="/WEB-INF/jspf/page.jspf" %>
<cws:priceList priceList_ID="0"/>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - Welcome</title>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
	  <c:out value='${ctx.webParam2}' escapeXml='false'/>
      <form action="productServlet" method="post" enctype="application/x-www-form-urlencoded" name="search" id="search">
          <fieldset>
              <legend>Search</legend>

              <label id="productSearch" for="SearchString">Product </label>
              <input name="SearchString" type="text" id="SearchString" />
              <cws:productCategoryList/>
              <input type="submit" name="Submit" value="Search" />
              <c:if test='${priceList.notAllPrices}'>
                <p><i>Not all Products displayed - enter Search criteria to limit selection</i></p>
              </c:if>
              <c:if test='${priceList.noLines}'>
                <p><i>No Products found - enter Search criteria</i></p>
              </c:if>
          </fieldset>
      </form>
      <br/>
      <form action="basketServlet" method="post" enctype="application/x-www-form-urlencoded" name="products" id="products">
        <input name="M_PriceList_ID" type="hidden" value="<c:out value='${priceList.priceList_ID}'/>" />
        <input name="M_PriceList_Version_ID" type="hidden" value="<c:out value='${priceList.priceList_Version_ID}'/>" />
        <table class="contentTable">
          <tr> 
            <th colspan="2" align="left">Product</th>
            <th>Description</th>
            <th><c:out value='${priceList.currency}'/>&nbsp;Price</th>
            <th>Quantity</th>
            <th>UOM</th>
            <th>&nbsp;</th>
            <th class="availProduct"><cws:message txt="Availability"/></th>
          </tr>
          <c:forEach items='${priceList.prices}' var='product' varStatus='status'> 
        	<jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
        	<c:choose>
        		<c:when test="<%= status.getCount() %2 == 0 %>">
	        		<c:set var="rowClass" value="evenRow"/>
        		</c:when>
        		<c:otherwise>
	        		<c:set var="rowClass" value="oddRow"/>
        		</c:otherwise>
        	</c:choose>
        	<tr> 
            <td class="<c:out value='${rowClass}' />"><c:if test='${not empty product.imageURL}'><img src="<c:out value='${product.imageURL}'/>" /></c:if></td>
            <td class="<c:out value='${rowClass}' />"> <input name="Name_<c:out value='${product.id}'/>" type="hidden" value="<c:out value='${product.name}'/>" />
                <c:choose>
                    <c:when test="${not empty product.descriptionURL}">
                        <a href="<c:out value='${product.descriptionURL}'/>" target="pd"><c:out value="${product.name}"/></a>
                    </c:when>
                    <c:otherwise>
                        <c:out value='${product.name}'/>
                    </c:otherwise>
                </c:choose>
			</td>
            <td class="<c:out value='${rowClass}' />"><c:out value='${product.description}'/> <c:if test="${empty product.description}">&nbsp;</c:if></td>
            <td class="<c:out value='${rowClass}' /> amount"> <input name="Price_<c:out value='${product.id}'/>" type="hidden" value="<c:out value='${product.price}'/>" /> 
              <fmt:formatNumber value='${product.price}' type="currency" currencySymbol="" /> </td>
            <td class="<c:out value='${rowClass}' /> quantity"> <input name="Qty_<c:out value='${product.id}'/>" type="text" id="qty_<c:out value='${product.id}'/>" value="1" size="5" maxlength="5" /></td>
            <td class="<c:out value='${rowClass}' />"><c:out value='${product.uomName}'/>&nbsp;</td>
            <td class="<c:out value='${rowClass}' />"> <input name="Add_<c:out value='${product.id}'/>" type="submit" id="Add_<c:out value='${product.id}'/>" value="Add" /></td>
            <td class="<c:out value='${rowClass}'/> availProduct"><!-- c:out value='$ {product.available}'/ -->&nbsp;</td>
          </tr>
          </c:forEach> 
        </table>
      </form>
	  <p><font size="-1">Price List: <c:out value='${priceList.name}'/>  (<c:out value='${priceList.priceCount}'/>) - <c:out value='${priceList.searchInfo}'/></font></p>
      <p>&nbsp;</p></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
