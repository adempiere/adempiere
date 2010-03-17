package org.compiere.www;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.AlignType;
import org.apache.ecs.xhtml.a;
import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h2;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.label;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.span;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.textarea;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.GridField;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;

public class WFindAdv extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -763641668438890217L;
	
	public static final String	EQUAL = "=";
    public static final String	NOT_EQUAL = "!=";
    public static final String	LIKE = "LIKE";
    public static final String	NOT_LIKE = "NOT LIKE";
    public static final String	GREATER = ">";
    public static final String	GREATER_EQUAL = ">=";
    public static final String	LESS = "<";
    public static final String	LESS_EQUAL = "<=";
    public static final String	BETWEEN = "BETWEEN";
    private static final String P_Tab       = "PTab";

public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WFindAdv.init");
	}

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    HttpSession sess = request.getSession(false);
    WWindowStatus ws = null;
	WebSessionCtx wsc = WebSessionCtx.get(request);

	if (sess != null)
		ws = WWindowStatus.get(request);


	if (wsc == null )
	{
		WebUtil.createTimeoutPage(request, response, this,  null);
		return;
	}
		WebDoc doc = preparePage();
		body body = doc.getBody();
		
        String pTab = request.getParameter(P_Tab);
        
		if (pTab!=null && pTab.equals("FindAdv")) {
     	   body.addElement(createPageFindAdv(request, response, ws));
        } else {
     	   body.addElement(createPageFind(request, response, ws));
        }
		
		WebUtil.createResponse (request, response, this, null, doc, true);
	}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
    HttpSession sess = request.getSession(false);
    WWindowStatus ws = null;
	WebSessionCtx wsc = WebSessionCtx.get(request);

	if (sess != null)
		ws = WWindowStatus.get(request);
	if (wsc == null )
	{
		WebUtil.createTimeoutPage(request, response, this,  null);
		return;
	}
	String pTab = request.getParameter(P_Tab);
	WebDoc doc = preparePage();
	body body = doc.getBody();
   
	if (pTab!=null && pTab.equals("FindAdv")) {
	   body.addElement(createPageFindAdv(request, response, ws));
	} else {
	   body.addElement(createPageFind(request, response, ws));
	}
	WebUtil.createResponse (request, response, this, null, doc, true);
}

/**
 *  Prepare a layout for Find functionality
 *  create appropriate fields for inputing searching keys
 *
 *  @param request
 *  @param response
    @paam es
 *  @throws ServletException
 *  @throws IOException
 *
 *  @return WebDoc
 */

private table createTabs(String selTab){
//  Tabs
	td tabbar = new td("windowCenter", AlignType.LEFT, AlignType.MIDDLE, false);		
	a tab = new a("#",new span("Find"));
	if (selTab.equals("Find")){
		tab.setID("tabSelected");
	} else {
		tab.setID("tab");
		tab.setHref("/adempiere/WFindAdv?PTab=Find");
	}
	
	tab.setOnMouseOver("status='Find';return true;");
	tabbar.addElement(tab);
	
	tab = new a("#",new span("FindAdv"));
	if (selTab.equals("FindAdv")){
		tab.setID("tabSelected");
	} else {
		tab.setID("tab");
		tab.setHref("/adempiere/WFindAdv?PTab=FindAdv");
	}
	
	tab.setOnMouseOver("status='FindAdv';return true;");
	tabbar.addElement(tab);
	
	table topTable = new table ("0", "0", "0", "100%", null);
	topTable.setID("WFindAdv.topTable");
	topTable.addElement(new tr(tabbar));

	return topTable;
}

private form createPageFind(HttpServletRequest request, HttpServletResponse response, WWindowStatus ws) throws ServletException, IOException
{

	boolean hasValue = false;
	boolean hasName = false;
	boolean hasDocNo = false;
	boolean hasDescription = false;

	//Get Info from target Tab
	int size = ws.curTab.getFieldCount();
	for (int i = 0; i < size; i++)
	{
		GridField mField = ws.curTab.getField(i);
		String columnName = mField.getColumnName();

		if (mField.isDisplayed()) {
			if (columnName.equals("Value"))
				hasValue = true;
			else if (columnName.equals("Name"))
				hasName = true;
			else if (columnName.equals("DocumentNo"))
				hasDocNo = true;
			else if (columnName.equals("Description"))
				hasDescription = true;
		}
	}

	form myForm = new form("/adempiere/WWindow", form.METHOD_POST, form.ENC_DEFAULT);
	myForm.setName("WForm");
	
	myForm.setOnSubmit("this.target=window.opener.name");

    myForm.addElement(createTabs("Find"));
    
    table table = new table();
	//table.setClass("centerTable");
	
	tr line=null;

	if(hasValue) {
		line = new tr();
		line.addElement(new td().addElement(new label().addElement(Msg.translate(ws.ctx,"Value"))).setAlign("right"));
		line.addElement(new td().addElement(new input("text","txtValue","")));
		table.addElement(line);
	}
	
	if(hasDocNo) {
		line = new tr();
		line.addElement(new td().addElement(new label().addElement(Msg.translate(ws.ctx,"DocumentNo"))).setAlign("right"));
		line.addElement(new td().addElement(new input("text","txtDocumentNo","")));
		table.addElement(line);
	}
	
	if(hasName) {
		line = new tr();
		line.addElement(new td().addElement(new label().addElement(Msg.translate(ws.ctx,"Name"))).setAlign("right"));
		line.addElement(new td().addElement(new input("text","txtName","")));
		table.addElement(line);
	}
	
	if(hasDescription) {
		line = new tr();
		line.addElement(new td().addElement(new label().addElement(Msg.translate(ws.ctx,"Description"))).setAlign("right"));
		line.addElement(new td().addElement(new input("text","txtDescription","")));
		table.addElement(line);
	}
	
	if (!hasDescription && !hasDocNo && !hasName && !hasValue){
		line= new tr();
		line.addElement(new td().addElement(new h2("N/A!")));
		table.addElement(line);
	}
	
	myForm.addElement(table);
	
	myForm.addElement(new input("hidden","txtSQL","FIND"));
	
	myForm.addElement(new br());
	myForm.addElement(new input("hidden","PCommand","FindAdv"));
	myForm.addElement("&nbsp;&nbsp;");
	myForm.addElement(new input("Reset","","  Reset").setClass("resetbtn"));
	myForm.addElement("&nbsp;");
	
	input cmd=new input("Submit","","  Submit");
	cmd.setClass("submitbtn");
	myForm.addElement(cmd);

	myForm.addElement("&nbsp;");
	cmd=new input("button","","  Close");
	cmd.setClass("closebtn");
	cmd.setOnClick("window.close()");
	myForm.addElement(cmd);
	
	return myForm;
}   //  createFields

private form createPageFindAdv (HttpServletRequest request, HttpServletResponse response, WWindowStatus ws) throws ServletException, IOException
{    
	form myForm = new form("/adempiere/WWindow", form.METHOD_POST, form.ENC_DEFAULT);
	myForm.setName("WForm");
	
	myForm.setOnSubmit("this.target=window.opener.name");

    myForm.addElement(createTabs("FindAdv"));
    
    table table = new table();
	//table.setClass("centerTable");
	
	tr line=new tr();
	
	line.addElement(new td().addElement("Field"));
	line.addElement(new td().addElement("Operator"));
	line.addElement(new td().addElement("Value"));
	line.addElement(new td().addElement("To Value"));
	table.addElement(line);
	
	select select=new select("cboField");
	int size = ws.curTab.getFieldCount();
	for (int i = 0; i < size; i++)
	{
		GridField mField = ws.curTab.getField(i);
		if (mField.isDisplayed()) { 
			select.addElement(new option(mField.getColumnSQL(false)).addElement(mField.getColumnSQL(false)));
		}
	}
	
	line=new tr();
	line.addElement(new td().addElement(select));
	
	select=new select("cboOperator",new option[]{
			new option(LIKE).addElement(LIKE),
			new option(NOT_LIKE).addElement(NOT_LIKE),
			new option(EQUAL).addElement(EQUAL),
			new option(NOT_EQUAL).addElement(NOT_EQUAL),
			new option(GREATER).addElement(GREATER),
			new option(GREATER_EQUAL).addElement(GREATER_EQUAL),
			new option(LESS).addElement(LESS),
			new option(LESS_EQUAL).addElement(LESS_EQUAL),
			new option(BETWEEN).addElement(BETWEEN),
	});
	line.addElement(new td().addElement(select));
	line.addElement(new td().addElement(new input("text","txtValue","%")));
	line.addElement(new td().addElement(new input("text","txtToValue","")));
	table.addElement(line);
	
	line=new tr();
	input cmd=new input("button","cmdAdd","Add Clause");
	cmd.setOnClick("txtSQL.value+=cboField.value + ' ' + cboOperator.value + ' \\'' + txtValue.value + '\\'\\n'");
	line.addElement(new td().addElement(cmd));
	
	cmd=new input("button","cmdAnd","AND");
	cmd.setOnClick("txtSQL.value+='AND' + '\\n'");
	line.addElement(new td().addElement(cmd));
	table.addElement(line);
	
	cmd=new input("button","cmdOr","OR");
	cmd.setOnClick("txtSQL.value+='OR' + '\\n'");
	line.addElement(new td().addElement(cmd));
	table.addElement(line);
	
	line=new tr();
	line.addElement(new td().addElement("SQL Clause"));
	table.addElement(line);
	
	line=new tr();
	line.addElement(new td().addElement(new textarea().setName("txtSQL").setStyle("width:100%")).setColSpan(4));
	table.addElement(line);
	
	myForm.addElement(table);
	
	myForm.addElement(new br());
	
	myForm.addElement(new input("hidden","PCommand","FindAdv"));
	myForm.addElement("&nbsp;&nbsp;");
	myForm.addElement(new input("Reset","","  Reset").setClass("resetbtn"));
	myForm.addElement("&nbsp;");
	
	cmd=new input("Submit","","  Submit");
	cmd.setClass("submitbtn");
	cmd.setOnClick("if (txtSQL.value.length==0) {alert('Input SQL clause before submit');return false}");
	myForm.addElement(cmd);


	myForm.addElement("&nbsp;");
	cmd=new input("button","","  Close");
	cmd.setClass("closebtn");
	cmd.setOnClick("window.close()");
	myForm.addElement(cmd);

	return myForm;
}

private WebDoc preparePage()
{
	WebDoc doc = WebDoc.create ("Find Advance");
	head header = doc.getHead();
	header.addElement(new script("", WebEnv.getBaseDirectory("js/window.js")));
	header.addElement(new script("", WebEnv.getBaseDirectory("/js/check.js")));
	header.addElement(new link().setRel("stylesheet").setHref(WebEnv.getBaseDirectory("css/window.css")));
	return doc;
}
}