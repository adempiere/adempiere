<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=expenseLine.jsp'/>
</c:if>
<c:if test='${empty param.W_Expense_ID}'>
  <c:redirect url='expenses.jsp'/>
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
}

function updateCampaignList()
{
    /**
     * call searchServlet
     * param: get = 'campaignList'
     *
     * get back:
     * <campaigns>
	 *   <campaign id='0'>ANY</campaign>
	 *   <campaign id='1'>Rose Festival</campaign>
	 * </campaigns>
     */

	var params = new Array();
	params['get']='campaignList';
	var loader = new AJAX.AjaxLoader("searchServlet", updateCampaignListCallback, null, "GET", params);
}
function updateCampaignListCallback()
{
	updateSelect(this, "campaigns", "ID_C_Campaign_ID");
}
addOnLoadListener(updateCampaignList);

function updateProjectList()
{
    /**
     * call searchServlet
     * param: get = 'projectList'
     *
     * get back:
     * <projects>
	 *   <project id='0'>ANY</project>
	 *   <project id='1'>Landscaping New Office</project>
	 * </projects>
     */

	var params = new Array();
	params['get']='projectList';
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updateProjectListCallback, null, "GET", params);
}
function updateProjectListCallback()
{
	updateSelect(this, "projects", "ID_C_Project_ID");
	updatePhaseList();
}
addOnLoadListener(updateProjectList);

function updatePhaseList()
{
    var projectSelect = document.getElementById('ID_C_Project_ID');
    var projectOption = projectSelect.options[projectSelect.selectedIndex];
    var projectID = projectOption.attributes.getNamedItem('value').value;

    /**
     * call searchServlet
     * param: get = 'phaseList'
     * param: projectID = '123'
     *
     * get back:
	 * <phases projectID='123'>
	 *   <phase id='0'>ANY</phase>
	 *   <phase id='1'>Planning</phase>
	 * </phases>
     */

	var params = new Array();
	params['get']='phaseList';
	params['projectID']=projectID;
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updatePhaseListCallback, null, "GET", params);
}
function updatePhaseListCallback()
{
	updateSelect(this, "phases", "ID_C_Phase_ID");
	updateTaskList();
}

function updateTaskList()
{
    var projectSelect = document.getElementById('ID_C_Project_ID');
    var projectOption = projectSelect.options[projectSelect.selectedIndex];
    var projectID = projectOption.attributes.getNamedItem('value').value;

    var phaseSelect = document.getElementById('ID_C_Phase_ID');
    var phaseOption = phaseSelect.options[phaseSelect.selectedIndex];
    var phaseID = phaseOption.attributes.getNamedItem('value').value;

    /**
     * call searchServlet
     * param: get = 'phaseList'
     * param: projectID = '123'
     * param: phaseID = '456'
     *
     * get back:
	 * <tasks projectID='123' phaseID='456'>
	 *   <task id='0'>ANY</task>
	 *   <task id='1'>Contact Owner</task>
	 * </tasks>
     */

	var params = new Array();
	params['get']='taskList';
	params['projectID']=projectID;
	params['phaseID']=phaseID;
	params['W_Expense_ID']=queryString['W_Expense_ID'];
	var loader = new AJAX.AjaxLoader("searchServlet", updateTaskListCallback, null, "GET", params);
}
function updateTaskListCallback()
{
	updateSelect(this, "tasks", "ID_C_Task_ID");
}
</script>
</head>
<body><div id="page">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="main">
	<%@ include file="/WEB-INF/jspf/menu.jspf" %>
    <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
	<div id="content">
		<c:choose>
			<c:when test="${not empty param.W_ExpenseLine_ID}">
		      <h1>My Expense: <c:out value='${param.W_Expense_ID}'/>, Line <c:out value='${param.W_ExpenseLine_ID}'/></h1>
			</c:when>
			<c:otherwise>
		      <h1>My Expense: New Expense Line</h1>
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
						<fieldset>
							<legend>Details</legend>
							
							<label>Client</label>
							<select id="ID_C_Client_ID" name="ID_C_Client_ID" onchange="updateOrgList()">
							</select>
							<br/>

							<label>Organization</label>
							<select id="ID_C_Organization_ID" name="ID_C_Organization_ID">
							</select>
							<br/>
							
							<label>Date</label>
							<input type="text" size="8"/>
							<img src="Calendar16.gif" border="0"/>
							<br/>

							<label>Type</label>
							<input type="radio" name="type" value="Time" style="width: 20px;"/> Time
							<input type="radio" name="type" value="Monetary" checked="true" style="width: 20px;"/> Monetary
							<br/>
							
							<label>Product</label>
							<input type="text" size="20"/>
							<br/>

							<label>Quantity</label>
							<input type="text" size="10"/>
							<br/>

							<label>Expense Amount</label>
							<input type="text" size="10"/>
							<img src="Calculator16.gif" border="0"/>
							<br/>

							<label>Invoice Price</label>
							<input type="text" size="10"/>
							<img src="Calculator16.gif" border="0"/>
							<br/>

						</fieldset>
					</td>
					<td class="lineItem">
						<fieldset>
							<legend>Reference</legend>
				
							<label>Business Partner</label>
							<select id="ID_C_Partner_ID" name="ID_C_Partner_ID">
							</select>
							<br/>

							<label>Campaign</label>
							<select id="ID_C_Campaign_ID" name="ID_C_Campaign_ID">
								<option selected="selected">Any</option>
							</select>
							<br/>
	
							<label>Project</label>
							<select id="ID_C_Project_ID" name="ID_C_Project_ID" onchange="updatePhaseList()">
								<option selected="selected">Any</option>
							</select>
							<br/>
	
							<label>Phase</label>
							<select id="ID_C_Phase_ID" name="ID_C_Phase_ID" onchange="updateTaskList()">
								<option>Any</option>
							</select>
							<br/>
	
							<label>Task</label>
							<select id="ID_C_Task_ID" name="ID_C_Task_ID">
								<option>Any</option>
							</select>
							<br/>
						</fieldset>
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
				<input type="submit" name="Submit" value="Submit Draft for Approval" />
				<input type="button" value="Cancel Draft" onclick="cancelExpense();"/>
				<input type="reset"/>
			</div>
		</form>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
