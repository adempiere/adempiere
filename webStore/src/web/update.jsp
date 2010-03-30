<%@ include file="/WEB-INF/jspf/page.jspf" %>
<c:if test='${empty webUser || !webUser.loggedIn}'>
  <c:redirect url='loginServlet?ForwardTo=update.jsp'/>
</c:if>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title><c:out value='${ctx.name}'/> - Update</title>
    <script type="text/javascript">
        function validateEMail()
        {
            if(document.UpdateEMail.EMail.value == document.UpdateEMail.EMailNew.value)
            {
                alert("Change of EMail requires Old and New EMail addresses.")
                return false;
            }
            if(document.UpdateEMail.EMailNew.value != document.UpdateEMail.EMailConfirm.value)
            {
                alert("New EMail Address and Confirm New EMail Address do not match.");
                return false;
            }

            return true;
        }
        function validatePassword()
        {
            if(document.UpdatePassword.Password.value == document.UpdatePassword.PasswordNew.value)
            {
                alert("Change of Password required Old and New Passwords");
                return false;
            }
            if(document.UpdatePassword.PasswordNew.value != document.UpdatePassword.PasswordConfirm.value)
            {
                alert("New Password and Confirm New Password do not match.");
                return false;
            }

            return true;
        }
        function validateAddress()
        {
            return true;
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
            <form action="updateServlet" method="post" enctype="application/x-www-form-urlencoded" name="UpdateEMail" target="_top" onSubmit="return validateEMail()">
                <fieldset>
                    <legend><cws:message txt="Change EMail"/></legend>
                    <input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
                    <input name="Source" type="hidden" value=""/>
                    <input name="Info" type="hidden" value=""/>
                    <input name="Mode" type="hidden" value="email"/>
                    <script language="Javascript">
                        document.UpdateEMail.Source.value = document.referrer;
                        document.UpdateEMail.Info.value = document.lastModified;
                    </script>

                    <label><cws:message txt="Old EMail Address"/></label>
                    <input class="mandatory" size="40" value='<c:out value="${webUser.email}"/>' name="EMail" maxlength="60" type="text"/>
                    <input name="validated" type="checkbox" value="validated" disabled
                        <c:if test='${webUser.EMailVerified}'> checked</c:if>> Address validated
                    <br/>

                    <label title="Must be a valid EMail Address!!"><cws:message txt="New EMail Address"/></label>
                    <input class="mandatory" size="40" value='' name="EMailNew" maxlength="60" type="text"/>
                    <br/>

                    <label><cws:message txt="Confirm New EMail Address"/></label>
                    <input class="mandatory" size="40" value='' name="EMailConfirm" maxlength="60" type="text"/>
                    <br/>

                    <c:if test="${not empty webUser.saveErrorMessage}">
                        <div class="error"><c:out value="${webUser.saveErrorMessage}"/></div>
                    </c:if>

                    <div class="buttons">
                        <input type="reset" name="Reset" value="Reset">
                        <input name="AddressConfirm" type="hidden" value="N">
                        <input type="submit" name="Submit" value="Submit New EMail Address"/>
                    </div>
                </fieldset>
            </form>

            <form action="updateServlet" method="post" enctype="application/x-www-form-urlencoded" name="UpdatePassword" target="_top" onSubmit="return validatePassword()">
                <fieldset>
                    <legend><cws:message txt="Change Password"/></legend>
                    <input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
                    <input name="Source" type="hidden" value=""/>
                    <input name="Info" type="hidden" value=""/>
                    <input name="Mode" type="hidden" value="password"/>
                    <script language="Javascript">
                        document.UpdatePassword.Source.value = document.referrer;
                        document.UpdatePassword.Info.value = document.lastModified;
                    </script>

                    <label id="LBL_Password" for="Password"><cws:message txt="Existing Password"/></label>
                    <input class="mandatory" size="20" type="password" id="ID_Password" value="" name="Password" maxlength="40"/>
                    <font color="#FF0000">&nbsp;<c:out value="${webUser.passwordMessage}"/></font>
                    <br/>

                    <label id="LBL_PasswordNew" for="PasswordNew"><cws:message txt="New Password"/></label>
                    <input class="mandatory" size="20" id="ID_PasswordNew" value="" name="PasswordNew" maxlength="40" type="password"/>
                    <br/>

                    <label id="LBL_PasswordConfirm" for="PasswordConfirm"><cws:message txt="Confirm New Password"/></label>
                    <input class="mandatory" size="20" id="ID_PasswordConfirm" value="" name="PasswordConfirm" maxlength="40" type="password"/>
                    <br/>

                    <c:if test="${not empty webUser.saveErrorMessage}">
                        <div class="error"><c:out value="${webUser.saveErrorMessage}"/></div>
                    </c:if>

                    <div class="buttons">
                        <input type="reset" name="Reset" value="Reset">
                        <input type="submit" name="Submit" value="Submit New Password"/>
                    </div>
                </fieldset>
            </form>

            <form action="updateServlet" method="post" enctype="application/x-www-form-urlencoded" name="UpdateAddress" target="_top" onSubmit="return validateAddress()">
                <fieldset>
                    <legend><cws:message txt="Change Contact Information"/></legend>

                    <input name="AD_Client_ID" type="hidden" value='<c:out value="${initParam.#AD_Client_ID}" default="0"/>'/>
                    <input name="Source" type="hidden" value=""/>
                    <input name="Info" type="hidden" value=""/>
                    <input name="Mode" type="hidden" value="address"/>
                    <script language="Javascript">
                        document.UpdateAddress.Source.value = document.referrer;
                        document.UpdateAddress.Info.value = document.lastModified;
                    </script>

                    <label id="LBL_Name" for="Name">Name</label>
                    <input class="mandatory" size="40" id="ID_Name" value="<c:out value='${webUser.name}'/>" name="Name" maxlength="60" type="text"/>
                    <br/>

                    <label id="LBL_Company" for="Company">Company</label>
                    <input size="40" id="ID_Company" value="<c:out value='${webUser.company}'/>" name="Company" maxlength="60" type="text"/>
                    <br/>

                    <label id="LBL_Title" for="Title">Title</label>
                    <input size="40" id="ID_Title" value='<c:out value="${webUser.title}"/>' name="Title" maxlength="60" type="text"/>
                    <br/>

                    <label id="LBL_Address" for="Address">Address</label>
                    <input class="mandatory" size="40" id="ID_Address" value="<c:out value='${webUser.address}'/>" name="Address" maxlength="60" type="text"/>
                    <br/>

                    <label id="LBL_Address2" for="Address2">Address2</label>
                    <input size="40" id="ID_Address2" value='<c:out value="${webUser.address2}"/>' name="Address2" maxlength="60" type="text"/>
                    <br/>

                    <cws:location countryID='${webUser.countryID}' regionID='${webUser.regionID}' regionName='${webUser.regionName}'
                                  city='${webUser.city}' postal='${webUser.postal}'/>

                    <label id="LBL_Phone" for="Phone">Phone</label>
                    <input size="20" id="ID_Phone" value='<c:out value="${webUser.phone}"/>' name="Phone" maxlength="20" type="text"/>
                    <br/>

                    <label id="LBL_Fax" for="Fax">Fax</label>
                    <input size="20" id="ID_Fax" value='<c:out value="${webUser.fax}"/>' name="Fax" maxlength="20" type="text"/>
                    <br/>

                    <c:if test="${not empty webUser.saveErrorMessage}">
                        <div class="error"><c:out value="${webUser.saveErrorMessage}"/></div>
                    </c:if>

                    <div class="buttons">
                        <input type="reset" name="Reset" value="Reset">
                        <input name="AddressConfirm" type="hidden" id="AddressConfirm" value="N">
                        <input type="submit" name="Submit" id="Submit" value="Submit New Contact Information"/>
                    </div>

                </fieldset>

            </form>
            <div id="processingDiv" style="display:none"><strong>Processing ...</strong></div>
            <br/>

            <div align="center">Enter all <b class="mandatory">mandatory</b> data, for any specific section. </div>

            <p>&nbsp;</p>
        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
</div></body>
</html>
