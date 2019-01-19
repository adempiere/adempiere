function loginDynUpdate(selector)
{
    var req = new XMLHttpRequest();
    req.onerror = function()
    {
    };
    
    req.onreadystatechange = function()
    {
        if (req.readyState == 4)
        {
            var clients = document.forms.Login2.AD_Client_IDF.options;
            var orgs = document.forms.Login2.AD_Org_IDF.options;
            clients.length = 0;
            orgs.length = 0;
            var response = JSON.parse(req.responseText);
            if ( response.clients ) {
            	for (i=0; i<response.clients.length; i++)
            	{
            		clients.add(new Option(response.clients[i].text, response.clients[i].value));
            	}
            }
            if ( response.orgs ) {
            	for (i=0; i<response.orgs.length; i++)
            	{
            		orgs.add(new Option(response.orgs[i].text, response.orgs[i].value));
            	}
            }
        }
    };
    
    var query;
    if ( selector.id == "AD_Role_IDF")
      query = "AD_Role_ID="+selector.value;
    else if ( selector.id == "AD_Client_IDF")
      query = "AD_Client_ID="+selector.value;
    req.open("GET", "LoginDynUpdate?"+query, true);
    req.send(null);
}