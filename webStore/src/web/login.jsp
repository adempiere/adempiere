<%@ include file="/WEB-INF/jspf/page.jspf" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title><c:out value='${ctx.name}'/> - Login</title>
    <script type="text/javascript">
        function doNewUserClicked()
        {
            document.getElementById("LoginOrNewBtns").style.display = "none";
            document.getElementById("NewUserFields").style.display = "block";
            //document.Login.Mode.value='LoginNew';
            document.Login.Mode.value='Submit';
            document.getElementById("LoginOrNewLegend").innerHTML = "Create New User";
        }
        function doCancelClicked()
        {
            document.getElementById("LoginOrNewBtns").style.display = "block";
            document.getElementById("NewUserFields").style.display = "none";
            document.Login.Mode.value='Login';
            document.getElementById("LoginOrNewLegend").innerHTML = "Login Existing User";
        }
        function validateForm()
        {
            if(document.Login.Mode.value == 'Login')
            {
                return checkForm(document.Login, new Array ('EMail','Password'));
            }

            if(document.Login.Mode.value == 'SendEMail')
            {
                return true;
            }

            if(document.Login.Password.value != document.Login.PasswordNew.value)
            {
                alert("Password and Confirm Password do not match.");
                return false;
            }

            return checkForm(document.Login, new Array ('EMail','Password','PasswordNew','Name','Address', 'City', 'Postal', 'C_Country_ID'));
        }
    </script>
</head>

<body><div id="page">
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
    <div id="main">
        <%@ include file="/WEB-INF/jspf/menu.jspf" %>
        <%@ include file="/WEB-INF/jspf/vendor.jspf" %>
        <div id="content">
            <h1>User Information</h1>

            <form action="loginServlet" method="post" enctype="application/x-www-form-urlencoded"
                  name="Login" target="_top" onSubmit="return validateForm()">

                <fieldset>
                    <legend id="LoginOrNewLegend">Login Existing User</legend>

            <input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
            <input name="Source" type="hidden" value=""/>
            <input name="Info" type="hidden" value=""/>
            <input name="Mode" type="hidden" value=""/>
            <script language="Javascript">
                document.Login.Source.value = document.referrer;
                document.Login.Info.value = document.lastModified;
                document.Login.Mode.value='Login';
            </script>

            <label id="LBL_EMail" for="EMail" title="Must be a valid EMail Address!!"><cws:message txt="EMail"/></label>
            <input class="mandatory" size="40" id="ID_EMail" value='<c:out value="${webUser.email}"/>' name="EMail" maxlength="60" type="text"/>
            <input name="validated" type="checkbox" id="validated" value="validated" disabled
                <c:if test='${webUser.EMailVerified}'> checked</c:if>> Address validated
            <br/>

            <label id="LBL_Password" for="Password"><cws:message txt="Password"/></label>
            <input class="mandatory" size="20" type="password" id="ID_Password" value="" name="Password" maxlength="40"/>
            <c:if test="${not empty webUser.passwordMessage}">
                <font color="#FF0000">&nbsp;<c:out value="${webUser.passwordMessage}"/></font>
            </c:if>
            <br/>

            <div class="buttons" id="LoginOrNewBtns">
                <input type="submit" name="Login" id="Login" value="Login existing">
                <input type="button" name="LoginNew" id="LoginNew" value="New user" onClick="doNewUserClicked();">
                <c:if test="${not empty webUser.passwordMessage}">
                    <input type="submit" name="SendEMail" id="SendEMail" value="Send Password to EMail"
                           onClick="document.Login.Mode.value='SendEMail';document.Login.Password.value='?';">
                </c:if>
            </div>
            <br/>

            <div style="display:none" id="NewUserFields">
            <%--<c:if test="${empty webUser || webUser.contactID == 0 || webUser.loggedIn}">--%>

                <label id="LBL_PasswordNew" for="PasswordNew">Confirm Password</label>
                <input class="mandatory" size="20" id="ID_PasswordNew" value="" name="PasswordNew" maxlength="40" type="password"/>
                <br/>

                <label id="LBL_Name" for="Name">Name</label>
                <input class="mandatory" size="40" id="ID_Name" value="" name="Name" maxlength="60" type="text"/>
                <br/>

                <label id="LBL_Company" for="Company">Company</label>
                <input size="40" id="ID_Company" value="" name="Company" maxlength="60" type="text"/>
                <br/>

                <label id="LBL_Title" for="Title">Title</label>
                <input size="40" id="ID_Title" value='' name="Title" maxlength="60" type="text"/>
                <br/>

                <label id="LBL_Address" for="Address">Address</label>
                <input class="mandatory" size="40" id="ID_Address" value="" name="Address" maxlength="60" type="text"/>
                <br/>

                <label id="LBL_Address2" for="Address2">Address2</label>
                <input size="40" id="ID_Address2" value='' name="Address2" maxlength="60" type="text"/>
                <br/>

                <cws:location countryID='${webUser.countryID}' regionID='${webUser.regionID}' regionName='${webUser.regionName}'
                              city='${webUser.city}' postal='${webUser.postal}'/>

                <label id="LBL_Phone" for="Phone">Phone</label>
                <input size="20" id="ID_Phone" value='' name="Phone" maxlength="20" type="text"/>
                <br/>

                <label id="LBL_Fax" for="Fax">Fax</label>
                <input size="20" id="ID_Fax" value='' name="Fax" maxlength="20" type="text"/>
                <br/>


                <div class="buttons">
                    <input type="submit" name="Submit" id="Submit" value="Create New User">
                    <input type="reset" name="Reset" value="Reset">
                    <input name="AddressConfirm" type="hidden" id="AddressConfirm" value="N">
                    <input type="button" name="Cancel" value="Cancel User Creation" onClick="doCancelClicked()"/>
                </div>

            <%--</c:if>--%>
            </div>

            <c:if test="${not empty webUser.saveErrorMessage}">
                <div class="error"><c:out value="${webUser.saveErrorMessage}"/></div>
            </c:if>
            <div id="processingDiv" style="display:none"><strong>Processing ...</strong></div>
            <br/>

            <div align="center">Enter all <b class="mandatory">mandatory</b> data. </div>

                </fieldset>
            </form>
            <p>&nbsp;</p>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
