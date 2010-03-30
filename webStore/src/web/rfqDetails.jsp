<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=rfqs.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My RfQ Details</title>
</head>
<body>
<div id="page">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <!-- Set RfQ ID and get RfQ		-->
    <c:set target='${info}' property='id' value='${param.C_RfQ_ID}' />
    <c:set var='rfqResponse' value='${info.rfQResponse}' />
    <c:if test='${empty rfqResponse}'>
      <c:set target='${info}' property='message' value='No RfQ Response' />
      <c:redirect url='rfqs.jsp'/>
    </c:if>
    <c:set var='rfq' value='${rfqResponse.rfQ}' />
    <c:if test='${empty rfq}'>
      <c:set target='${info}' property='message' value='RfQ not found' />
      <c:redirect url='rfqs.jsp'/>
    </c:if>

    <div id="main">
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>
        <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
        <div id="content">
            <h1>My RfQ Details: <c:out value='${rfq.name}'/></h1>
            <c:if test='${not empty info.info}'>
                <p><b><c:out value='${info.message}'/></b></p>
            </c:if>
            <form action="rfqServlet" method="post" enctype="application/x-www-form-urlencoded" name="RfQResponse">
                <fieldset>
                    <legend>RfQ Summary</legend>
                    <input name="C_RfQ_ID" type="hidden" value="<c:out value='${rfqResponse.c_RfQ_ID}'/>"/>
                    <input name="C_RfQResponse_ID" type="hidden" value="<c:out value='${rfqResponse.c_RfQResponse_ID}'/>"/>
                    <table class="contentTable">
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Details</th>
                            <th>Response</th>
                            <th>Work Start</th>
                            <th colspan="4">Delivery</th>
                        </tr>
                        <tr>
                            <td class="oddRow lineItem">
                                <c:out value='${rfq.name}'/>
                                <br/>
                                <input name="Name" type="text" id="Name" value="<c:out value='${rfqResponse.name}'/>">
                            </td>
                            <td class="oddRow lineItem">
                                <c:out value='${rfq.description}'/>
                                <br/>
                                <textarea name="Description" rows="3" id="Description"><c:out value='${rfqResponse.description}'/></textarea>
                            </td>
                            <td class="oddRow lineItem">
                                <c:out value='${rfq.help}'/>
                                <c:if test='${rfq.pdfAttachment}'>
                                    <br>
                                    <a href="rfqServlet/RfQ_<c:out value='${rfq.c_RfQ_ID}'/>.pdf?C_RfQ_ID=<c:out value='${rfq.c_RfQ_ID}'/>" target="_blank"><img src="pdf.gif" alt="Get Report" width="30" height="30" border="0" /></a>
                                </c:if>
                                <br/>
                                <textarea name="Help" rows="3" id="Help"><c:out value='${rfqResponse.help}'/></textarea>                            </td>
                            <td class="oddRow lineItem">
                                By <fmt:formatDate value='${rfq.dateResponse}' dateStyle='short'/>
                                <br/>
                                <fmt:formatDate value='${rfqResponse.dateResponse}' dateStyle='short'/>
                                <br>Total: <input name="Price" type="text" id="Price" value="<fmt:formatNumber value='${rfqResponse.price}' type='currency' currencySymbol=''/>" size="15" />
                            </td>
                            <td class="oddRow lineItem">
                                <fmt:formatDate value='${rfq.dateWorkStart}' dateStyle='short'/>
                                <br/>
                                <input name="DateWorkStart" type="text" id="DateWorkStart" value="<fmt:formatDate value='${rfqResponse.dateWorkStart}' dateStyle='short'/>" size="12">
                            </td>
                            <td class="oddRow lineItem">
                                <fmt:formatDate value='${rfq.dateWorkComplete}' dateStyle='short'/>
                                <br/>
                                <input name="DateWorkComplete" type="text" id="DateWorkComplete" value="<fmt:formatDate value='${rfqResponse.dateWorkComplete}' dateStyle='short'/>" size="12">
                            </td>
                            <td class="oddRow lineItem">
                                -
                                <br/>
                                -
                            </td>
                            <td class="oddRow lineItem">
                                 <c:out value='${rfq.deliveryDays}'/>
                                <br/>
                                <input name="DeliveryDays" type="text" id="DeliveryDays" value="<c:out value='${rfqResponse.deliveryDays}'/>" size="4" maxlength="4" />
                            </td>
                            <td class="oddRow lineItem">
                                days
                                <br/>
                                days
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset>
                    <legend>Line Items</legend>

                    <table class="contentTable">
                        <tr>
                            <th>#</th>
                            <th>Product</th>
                            <th colspan="2">Line Details</th>
                        </tr>
                        <c:forEach items='${rfqResponse.lines}' var='line' varStatus='status'>
                            <jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
                            <c:choose>
                                <c:when test="<%= status.getCount() %2 == 0 %>">
                                    <c:set var="rowClass" value="evenRow"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="rowClass" value="oddRow"/>
                                </c:otherwise>
                            </c:choose>
                            <c:set var='rfqLine' value='${line.rfQLine}' />
                            <tr>
                                <td class="<c:out value='${rowClass}' /> lineItem">
                                    <c:out value="${rfqLine.line}" />
                                </td>
                                <td class="<c:out value='${rowClass}' /> lineItem">
                                    <c:out value='${rfqLine.productDetailHTML}' escapeXml='false'/>
                                </td>
                                <td class="<c:out value='${rowClass}' /> lineItem">
                                    <fieldset>
                                        <legend>Timeline</legend>
                                        <table>
                                            <tr>
                                                <th>Work Start</th>
                                                <th colspan="4">Delivery</th>
                                            </tr>
                                            <tr>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <fmt:formatDate value='${rfqLine.dateWorkStart}' dateStyle='short'/>
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <fmt:formatDate value='${rfqLine.dateWorkComplete}' dateStyle='short'/>
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    -
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <c:out value='${rfqLine.deliveryDays}'/>
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    days
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <input name="DateWorkStart_<c:out value='${line.c_RfQResponseLine_ID}'/>" type="text" id="DateWorkStart" value="<fmt:formatDate value='${line.dateWorkStart}' dateStyle='short'/>" size="12">
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <input name="DateWorkComplete_<c:out value='${line.c_RfQResponseLine_ID}'/>" type="text" id="DateWorkComplete" value="<fmt:formatDate value='${line.dateWorkComplete}' dateStyle='short'/>" size="12">
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    -
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    <input name="DeliveryDays_<c:out value='${line.c_RfQResponseLine_ID}'/>" type="text" id="DeliveryDays" value="<c:out value='${line.deliveryDays}'/>" size="4" maxlength="4" />
                                                </td>
                                                <td class="<c:out value='${rowClass}' />">
                                                    days
                                                </td>
                                            </tr>
                                        </table>
                                    </fieldset>
                                    <fieldset>
                                        <legend>Line Quantities</legend>
                                        <table>
                                            <tr>
                                                <th>UOM</th>
                                                <th>Quantity</th>
                                                <c:if test='${not rfq.quoteTotalAmtOnly}'>
                                                    <th>Price</th>
                                                    <th>Discount</th>
                                                </c:if>
                                            </tr>
                                            <c:forEach items='${line.qtys}' var='qty'>
                                                <c:set var='rfqQty' value='${qty.rfQLineQty}'/>
                                                <tr>
                                                    <td class="<c:out value='${rowClass}' />">
                                                        <c:out value='${rfqQty.uomName}'/>
                                                    </td>
                                                    <td class="<c:out value='${rowClass}' /> quantity">
                                                        <c:out value='${rfqQty.qty}'/>
                                                    </td>
                                                    <c:if test='${not rfq.quoteTotalAmtOnly}'>
                                                        <td class="<c:out value='${rowClass}' /> amount">
                                                            <input name="Price_<c:out value='${qty.c_RfQResponseLineQty_ID}'/>" type="text" id="Price" value="<fmt:formatNumber value='${qty.price}' type='currency' currencySymbol=''/>" size="15" />
                                                        </td>
                                                        <td class="<c:out value='${rowClass}' /> amount">
                                                            <input name="Discount_<c:out value='${qty.c_RfQResponseLineQty_ID}'/>" type="text" id="Discount" value="<c:out value='${qty.discount}'/>" size="15" />
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </fieldset>
                                </td>
                                <td class="<c:out value='${rowClass}' /> lineItem wideText">
                                    <fieldset>
                                        <legend>Product Description</legend>
                                        <c:out value='${rfqLine.description}'/>
                                        <br/>
                                        <textarea class="wideText" name="Description_<c:out value='${line.c_RfQResponseLine_ID}'/>" rows="3" id="Description"><c:out value='${line.description}'/></textarea>
                                    </fieldset>
                                    <fieldset>
                                        <legend>Product Details</legend>
                                        <c:out value='${rfqLine.help}'/>
                                        <br>
                                        <textarea class="wideText" name="Help_<c:out value='${line.c_RfQResponseLine_ID}'/>" rows="3" id="Help"><c:out value='${line.help}'/></textarea>
                                    </fieldset>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </fieldset>
                <div class="buttons">
                    <input type="checkbox" name="IsComplete" value="IsComplete" id="IsComplete"> Complete
                    <br/>
                    <input name="Submit" type="submit" id="Submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div>
</body>
</html>
