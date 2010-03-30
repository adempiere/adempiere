<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=expenses.jsp'/>
</c:if>
<html>
<head>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<title><c:out value='${ctx.name}'/> - My Expense</title>
<script type="text/javascript">
function cancelExpense()
{
	window.location="expenses.jsp";
}

function updateClientList()
{
    /**
     * call searchServlet
     * param: get = 'clientList'
     *
     * get back:
     * <clients>
	 *   <client id='0'>ANY</client>
	 *   <client id='1'>GardenWorld</client>
	 *   <client id='2'>ComPiere, Inc.</client>
	 * </clients>
     */

	var params = new Array();
	params['get']='clientList';
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updateClientListCallback, null, "GET", params);
}
function updateClientListCallback()
{
	updateSelect(this, "clients", "ID_C_Client_ID");
	updateOrgList();
}
addOnLoadListener(updateClientList);

function updatePartnerList()
{
    /**
     * call searchServlet
     * param: get = 'partnerList'
     *
     * get back:
     * <partners>
	 *   <partner id='0'>ANY</partner>
	 *   <partner id='1'>Joe Block</partner>
	 * </partners>
     */

	var params = new Array();
	params['get']='partnerList';
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updatePartnerListCallback, null, "GET", params);
}
function updatePartnerListCallback()
{
	updateSelect(this, "partners", "ID_C_Partner_ID");
}
addOnLoadListener(updatePartnerList);

function updateOrgList()
{
    var clientSelect = document.getElementById('ID_C_Client_ID');
    var clientOption = clientSelect.options[clientSelect.selectedIndex];
    var clientId = clientOption.attributes.getNamedItem('value').value;

    /**
     * call searchServlet
     * param: get = 'orgList'
     * param: clientID = '123'
     *
     * get back:
	 * <orgs clientID='123'>
	 *   <org id='0'>ANY</client>
	 *   <org id='1'>HQ</org>
	 *   <org id='2'>Sales</org>
	 *   <org id='3'>Support</org>
	 * </orgs>
     */

	var params = new Array();
	params['get']='orgList';
	params['clientID']=clientId;
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updateOrgListCallback, null, "GET", params);
}
function updateOrgListCallback()
{
	updateSelect(this, "orgs", "ID_C_Organization_ID");
	updateWarehouseList();
}

function updateWarehouseList()
{
    var orgSelect = document.getElementById('ID_C_Organization_ID');
    var orgOption = orgSelect.options[orgSelect.selectedIndex];
    var orgId = orgOption.attributes.getNamedItem('value').value;

    /**
     * call searchServlet
     * param: get = 'warehouseList'
     *
     * get back:
     * <warehouses>
	 *   <warehouse id='1'>HQ Warehouse</warehouse>
	 *   <warehouse id='2'>Oregon Warehouse</warehouse>
	 * </warehouses>
     */

	var params = new Array();
	params['get']='warehouseList';
	params['orgID']=orgId;
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updateWarehouseListCallback, null, "GET", params);
}
function updateWarehouseListCallback()
{
	updateSelect(this, "warehouses", "ID_C_Warehouse_ID");
}

function updatePriceList()
{
    /**
     * call searchServlet
     * param: get = 'priceList'
     *
     * get back:
     * <pricelists>
	 *   <pricelist id='1'>Standard</pricelist>
	 *   <pricelist id='2'>Summer Specials</pricelist>
	 * </pricelists>
     */

	var params = new Array();
	params['get']='priceList';
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updatePriceListCallback, null, "GET", params);
}
function updatePriceListCallback()
{
	updateSelect(this, "pricelists", "ID_C_Pricelist_ID");
}
addOnLoadListener(updatePriceList);
</script>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
		<c:choose>
			<c:when test="${not empty param.W_Expense_ID}">
		      <h1>My Expense: <c:out value='${param.W_Expense_ID}'/></h1>
			</c:when>
			<c:otherwise>
		      <h1>My Expense: New Expense</h1>
			</c:otherwise>
		</c:choose>
      <c:if test='${not empty info.info}'>
	    <p><b><c:out value='${info.message}'/></b></p>
	  </c:if>
		<form method="post" name="Expense" action="expenseServlet" enctype="application/x-www-form-urlencoded">
            <input name="Source" type="hidden" value=""/>
            <input name="Info" type="hidden" value=""/>
            <script language="Javascript">
              document.Request.Source.value=document.referrer;
              document.Request.Info.value=document.lastModified;
            </script>
            <input name="W_Expense_ID" type="hidden" value="<c:out value='${param.W_Expense_ID}'/>" id="W_Expense_ID"/>
            <input name="ForwardTo" type="hidden" value="<c:out value='${param.ForwardTo}'/>"/>
            <c:if test='${not empty param.SalesRep_ID}'>
              <input name="SalesRep_ID" type="hidden" value="<c:out value='${param.SalesRep_ID}'/>"/>
            </c:if>
            <c:if test='${empty param.SalesRep_ID}'>
              <input name="SalesRep_ID" type="hidden" value="<c:out value='${webUser.salesRep_ID}'/>"/>
            </c:if>


			<table class="internalTable">
				<tr>
					<td class="lineItem">
						<label>Client</label>
						<select id="ID_C_Client_ID" name="ID_C_Client_ID" onchange="updateOrgList()">
						</select>
						<br/>

						<label>Organization</label>
						<select id="ID_C_Organization_ID" name="ID_C_Organization_ID" onchange="updateWarehouseList()">
						</select>
						<br/>
						
						<label>Business Partner</label>
						<select id="ID_C_Partner_ID" name="ID_C_Partner_ID">
						</select>
						<br/>

						<label>Warehouse</label>
						<select id="ID_C_Warehouse_ID" name="ID_C_Warehouse_ID">
							<option>HQ Warehouse</option>
						</select>
					</td>
					<td class="lineItem">
						
						<label>Price List</label>
						<select id="ID_C_Pricelist_ID" name="ID_C_Pricelist_ID">
							<option>Standard</option>
						</select>
						<br/>
							
						<label>Report Date</label>
						<input type="text" size="8"/>
						<img src="Calendar16.gif" border="0"/>
						<br/>

						<label>Approval Amount</label>
						<input type="text" size="10"/>
						<img src="Calculator16.gif" border="0"/>
						<br/>

						<label>Document Status</label>
						<select>
							<option>Drafted</option>
							<option>Submitted</option>
							<option>Approved</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lineItem">
						<fieldset>
							<legend>Description</legend>
							
							<textarea cols="40" class="wideText"></textarea>
							<div class="entryNote">Max 1500 Characters</div>
						</fieldset>
					</td>
					<td class="lineItem">
						<fieldset>
							<legend>Note</legend>
							
							<textarea cols="40" class="wideText"></textarea>
							<div class="entryNote">Max 1500 Characters</div>
						</fieldset>
					</td>
				</tr>
			</table>
			
			<br/>
			<div class="buttons">
				<input type="submit" name="Submit" value="Save Draft" />
				<input type="submit" name="Submit" value="Complete" />
				<input type="button" value="Cancel Draft" onclick="cancelExpense();"/>
				<input type="reset"/>
			</div>
		</form>

		<br/>
		
      <table class="contentTable">
	        <tr> 
	          <th>Line #</th>
	          <th>Product</th>
	          <th>Quantity</th>
	          <th>Description</th>
	          <th>Amount</th>
	        </tr>
	        <!--  TEST DATA START ******************************** -->
	        <tr> 
	          <td class="oddRow"><a href="expenseLine.jsp?W_Expense_ID=<c:out value='${param.W_Expense_ID}'/>&W_ExpenseLine_ID=10">10</a></td>
	          <td class="oddRow">Mary Consultant</td>
	          <td class="oddRow amount">8</td>
	          <td class="oddRow">Feb 5,2003 10:00:00am PST 8h</td>
	          <td class="oddRow">850.00</td>
	        </tr>
	        <tr> 
	          <td class="oddRow"><a href="expenseLine.jsp?W_Expense_ID=<c:out value='${param.W_Expense_ID}'/>&W_ExpenseLine_ID=10">20</a></td>
	          <td class="oddRow">Mary Consultant</td>
	          <td class="oddRow amount">8</td>
	          <td class="oddRow">Feb 6,2003 8:00:00am PST 8h</td>
	          <td class="oddRow">950.00</td>
	        </tr>
	        <!--  TEST DATA END ******************************** -->
	        <c:forEach items='${info.expense.lines}' var='line' varStatus='status'> 
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
		          <td class="<c:out value='${rowClass}' />"><a href="expenseLine.jsp?W_Expense_ID=<c:out value='${param.W_Expense_ID}'/>&W_ExpenseLine_ID=<c:out value='${line.line}'/>"><c:out value='${rowClass}' /></a>&nbsp;</td>
		          <td class="<c:out value='${rowClass}' />"><c:out value='${line.M_Product_ID}'/>&nbsp;</td>
		          <td class="<c:out value='${rowClass}' /> amount"><c:out value='${line.qtyInvoiced}'/>&nbsp;</td>
		          <td class="<c:out value='${rowClass}' />"><c:out value='${line.dateExpense}'/>&nbsp;</td>
		          <td class="<c:out value='${rowClass}' />"><c:out value='${line.invoicePrice}'/>&nbsp;</td>
		        </tr>
	        </c:forEach> 
		</table>

    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
