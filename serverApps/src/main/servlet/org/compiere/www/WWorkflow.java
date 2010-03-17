/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.www;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ecs.xhtml.body;
import org.apache.ecs.xhtml.br;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.h6;
import org.apache.ecs.xhtml.head;
import org.apache.ecs.xhtml.hr;
import org.apache.ecs.xhtml.img;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.link;
import org.apache.ecs.xhtml.script;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;




/**
 *
 *
 *  @author Jack Lam
 *  @version  $Id: WWorkflow.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WWorkflow extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220553249782175533L;
	
	/**
	 * Initialize global variables
	 */

public static final  int SIZE = 3;
public static  final int SPACE = 0;
public static  final int ARROW_FORWARD = -1;
public static  final int ARROW_BACKWARD = -3;
public static final int ARROW_DOWN = -2;

public static final String  WORKFLOW = "Workflow";
public static final String  NODES = "Nodes";
public static final String  NODES_ID = "Nodes_ID";
public static final String  IMAGE_MAP= "Image_Map";
public static final String  ACTIVE_NODE= "Active_Node";

public static final String  M_Command = "MCommand"; // move command
public static final String  J_Command = "JCommand"; // jump command

public static final String  FIRST = "Start";
public static final String  NEXT = "Next";
public static final String  BACK= "Back";
public static final String  LAST= "End";

private static final String FORM_NAME   = "MForm";
private static final String TARGET_WINDOW   = "WWindow";
private static final String FORM_ACTION   = "/adempiere/WWorkflow";


private  static  String  AD_Language = null;
Properties ctx = null;

/** Window Number Counter                   */
private static int          s_WindowNo  = 1;


	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WWorkflow.init");
	}   //  init

	/**
	 *  Process the HTTP Get request - Initial Call.
	 *  <br>
	 *  http://localhost/adempiere/WWorkflow?AD_Menu_ID=123
	 *  <br>
	 *
	 *  Find the AD_Workflow_ID
	 *  Load workflow and initial session atribute
	 *  Create output
	 *
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//Log.trace(Log.l1_User, "WWorkflow.doGet");
//WUtil.debug(new String("In do get"),"");


		//  Get Session attributes
		HttpSession sess = request.getSession();
                WebSessionCtx wsc = WebSessionCtx.get(request);
		 ctx = wsc.ctx;
		//String loginInfo = (String)sess.getAttribute(WebEnv.SA_LOGININFO);
		if (ctx == null)
		{
			 WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}



	    //  Get Parameter: Menu_ID
		int AD_Menu_ID = WebUtil.getParameterAsInt(request, "AD_Menu_ID");

        //  Get Parameter: Menu_ID
		int AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");


		 //set language
		 AD_Language = Env.getAD_Language(ctx);

	    //load AD_Workflow_ID
		int AD_Workflow_ID = getAD_Workflow_ID(AD_Menu_ID);

        //load workflow
		loadWorkflow(ctx, AD_Workflow_ID,sess);

		//get session attributes
		MWorkflow wf      = (MWorkflow)sess.getAttribute(WORKFLOW);
        MWFNode [] nodes  = (MWFNode []) sess.getAttribute( NODES);
        ArrayList nodes_ID = (ArrayList) sess.getAttribute( NODES_ID);
        int [][] imageMap = (int [][] )sess.getAttribute( IMAGE_MAP);
        int    activeNode = ((Integer) sess.getAttribute( ACTIVE_NODE)).intValue();

        //create output
		WebDoc doc = preparePage("loginInfo");
	    doc = createLayout( doc,wf,activeNode,nodes,nodes_ID,imageMap);
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doGet


	/**
	 *  Process the HTTP Post request - Initial Call.
	 *
	 *  Execute the received command
	 *  Update session attributes
	 *  Create output
	 *
	 *  @param request
	 *  @param response
	 *  @throws ServletException
	 *  @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//WUtil.debug(new String("In do Post"),"");
		HttpSession sess = request.getSession();

                WebSessionCtx wsc = WebSessionCtx.get(request);
		Properties ctx = wsc.ctx;
			if (ctx == null){
				 WebUtil.createTimeoutPage(request, response, this, null);

			return;
			}
		//String loginInfo = (String)sess.getAttribute(WEnv.SA_LOGININFO);




		//get session attributes
		MWorkflow wf      = (MWorkflow)sess.getAttribute(WORKFLOW);
        MWFNode [] nodes  = (MWFNode []) sess.getAttribute( NODES);
        ArrayList nodes_ID = (ArrayList) sess.getAttribute( NODES_ID);
        int [][] imageMap = (int [][] )sess.getAttribute( IMAGE_MAP);
        int    activeNode = ((Integer) sess.getAttribute( ACTIVE_NODE)).intValue();



		//execute commnad
		String m_command = request.getParameter(M_Command);
      int j_command = WebUtil.getParameterAsInt(request, J_Command);
	   //WUtil.debug(m_command,"m_command");
	   //WUtil.debug(""+j_command,"j_command");


		executeCommand(m_command,j_command ,wf,activeNode,nodes,nodes_ID,sess);


//get updated session attributes
		 wf      = (MWorkflow)sess.getAttribute(WORKFLOW);
        nodes  = (MWFNode []) sess.getAttribute( NODES);
        nodes_ID = (ArrayList) sess.getAttribute( NODES_ID);
         imageMap = (int [][] )sess.getAttribute( IMAGE_MAP);
           activeNode = ((Integer) sess.getAttribute( ACTIVE_NODE)).intValue();


		//create layout
		WebDoc doc = preparePage ("loginInfo");
        doc = createLayout(doc,wf,activeNode,nodes,nodes_ID,imageMap);
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost


/**********************************************************************************************/

/**
	 *  Generate image map array.
	 *  - Generate the orientation of workflow automatically
	 *
	 *  @param nodes
	 *  @return int [][]
	 */
private int [][] generateImageMap(ArrayList nodes_ID){

// number of nodes
int numOfNode = nodes_ID.size();

int rowNode_count = (int) Math.ceil( (float)numOfNode/SIZE ) ;

int rowTotal_count = 2*rowNode_count -1 ;

int ROW =  rowTotal_count;
int COL = 2*SIZE-1;


int [][] imageMap = new int [ROW][COL];
//
boolean isEmptyRow_Right = true;
boolean isForwardRow = true;
int row_Last = ROW - 1;
//indexs
int index_Row =0;
int index_Node =0;
int i,j;


//operate on every row
for (index_Row=0;index_Row < ROW ;index_Row++)
{
//odd row
if ( (index_Row % 2) != 0)
{
if(isEmptyRow_Right){
	imageMap[index_Row][COL-1] = ARROW_DOWN;
isEmptyRow_Right = false;
}
else {
	imageMap[index_Row][0]= ARROW_DOWN;
isEmptyRow_Right = true;
}
}//odd row


//even row
if ( (index_Row % 2) == 0)
{
if (isForwardRow)
{
isForwardRow = false;

// Last row
if (index_Row == row_Last)
{
j=0;
for (i=index_Node;i<numOfNode;i++)
{
imageMap[index_Row][j++] = ((Integer) nodes_ID.get(i)).intValue();
if(i != numOfNode-1 )
imageMap[index_Row][j++] = ARROW_FORWARD;
}

}// Last row-end

//not last row
else{

for (i=0;i<COL;i++)
imageMap[index_Row][i] = ( (i%2 == 0)? ((Integer) nodes_ID.get(index_Node++)).intValue() : ARROW_FORWARD);



}// not last row
}// Forward row - end

else{



isForwardRow = true;

// Backward-Last row
if (index_Row == row_Last)
{
j=0;
for (i=index_Node;i<numOfNode;i++)
{
imageMap[index_Row][COL-1-j++] = ((Integer) nodes_ID.get(i)).intValue();
if (i != numOfNode-1)
imageMap[index_Row][COL-1-j++] = ARROW_BACKWARD;
}

}// Backward-Last row -end

// Backward-Not last row
else{
j=0;
for (i=0;i<COL;i++)
{
imageMap[index_Row][COL-1-i] =  (i%2 == 0)? ((Integer) nodes_ID.get(index_Node++)).intValue() : ARROW_BACKWARD ;
}

}// Backward-Not last row- end


}// Backward row

}//even row
}//for every row


return imageMap;

}//generateImageMap

/**
	 *  Print the workflow.
	 *  - Use the image map array to create the visual layout of workflow
	 *
	 *  @param activeNode
 	 *  @param wf
	 *  @param nodes
	 *  @param nodes_ID
	 *  @param imageMap

	 *
	 *  @return table
	 */
private table printWorkflow(int activeNode, MWorkflow wf, MWFNode [] nodes, ArrayList nodes_ID,int [][] imageMap){

//create a new table
table imageTable = new table();
//find the dimension for the table
final int ROW = imageMap.length;
final int COL = imageMap[0].length;

//process every row
for (int i=0;i<ROW;i++ )
{
//create a new row
tr aRow =new tr();

for(int j=0;j<COL;j++){
//create a new grid
td grid = new td();
grid.setAlign("center");
//not node images
img spaceImage = createWorkflowImage("Spacer", 0, null, true);
img arrowForwardImage =  createWorkflowImage( "ArrowFW_",0, null, true);
img arrowBackwardImage = createWorkflowImage( "ArrowBW_", 0,null, true);
img arrowDownImage =createWorkflowImage( "ArrowDown_", 0 ,null, true);
//node images

img normalNodeImage = null;
img activeNodeImage = null;


//not node images
if (imageMap[i][j] <= 0){

if (imageMap[i][j] == ARROW_FORWARD) grid.addElement(arrowForwardImage);

if (imageMap[i][j] == ARROW_BACKWARD) grid.addElement(arrowBackwardImage);

if (imageMap[i][j] == ARROW_DOWN) grid.addElement(arrowDownImage);

if (imageMap[i][j] == SPACE) grid.addElement(spaceImage);


}//not node images

else{

//Initialize variable
int AD_Window_ID=0;
String url = "";
String Target_Window = "";
String parameters = "";
String js_command = "";

if(imageMap[i][j] == activeNode){
 //Create image
 
 AD_Window_ID = nodes[getIndex(imageMap[i][j],nodes_ID)].getAD_Window_ID();
  //  url = "'WWindow?AD_Window_ID="+AD_Window_ID+"&Target_Window=win_"+AD_Window_ID+"'";
url = "'WWindow?AD_Window_ID="+AD_Window_ID;
            Target_Window = "'WWindow"+AD_Window_ID+"'";
        ///url=WebEnv.getBaseDirectory("WWindow")+	"?AD_Window_ID="+AD_Window_ID+"&Target_Window=win_"+AD_Window_ID+"'";
	//	Target_Window = "'win_"+AD_Window_ID+"'";
System.out.println("WorkFlow if(true)======"+url);

            parameters = url+ "',"+Target_Window;
        js_command = "popUp("+parameters+");submit()";
		 activeNodeImage = createWorkflowImage("mWorkFlow_",imageMap[i][j] ,js_command, true);

grid.addElement(activeNodeImage);
grid.addElement(new br());
grid.addElement(""+imageMap[i][j]);
grid.addElement(new br());
grid.addElement("<font color=blue>"+nodes[getIndex(imageMap[i][j],nodes_ID)].getName()+"</font>");
}//active node

else {
//Create image
AD_Window_ID = nodes[getIndex(imageMap[i][j],nodes_ID)].getAD_Window_ID();
  // url = "'WWindow?AD_Window_ID="+AD_Window_ID+"&Target_Window=win_"+AD_Window_ID+"'";
  url = "'WWindow?AD_Window_ID="+AD_Window_ID;

//url=WebEnv.getBaseDirectory("WWindow")+"?AD_Window_ID="+AD_Window_ID+"&Target_Window=win_"+AD_Window_ID+"'";
            Target_Window = "'WWindow"+AD_Window_ID+"'";

			System.out.println("WorkFlow else======"+url);
		parameters = url+ "',"+Target_Window;
        js_command = "popUp("+parameters+");submit()";
		normalNodeImage = createWorkflowImage("mWorkFlow_", imageMap[i][j],js_command, false);

grid.addElement(normalNodeImage);
grid.addElement(new br());
grid.addElement(""+imageMap[i][j]);
grid.addElement(new br());
grid.addElement(nodes[getIndex(imageMap[i][j],nodes_ID)].getName());
}//inactive node
}
aRow.addElement(grid);
}//process every gird

imageTable.addElement(aRow);
}//every row



return imageTable;
}//printWorkflow



/**
	 *  Print the description for a workflow or a node.
	 *
	 *
	 *  @param activeNode
 	 *  @param wf
	 *  @param nodes
	 *  @param nodes_ID
	 *
	 *  @return table
	 */
private table printDescription(int activeNode, MWorkflow wf, MWFNode [] nodes, ArrayList nodes_ID){
//create new table
table desTable = new table();

String WFName="";
String WFDescription ="";

//not started yet
if(activeNode < 0 ){
WFName =wf.getName();
WFDescription=wf.getHelp();
}

//started
else{
WFName =nodes[getIndex(activeNode,nodes_ID)].getName();
WFDescription=nodes[getIndex(activeNode,nodes_ID)].getHelp();
}

//add info to the desciption table
tr aRow = new tr();
td aGrid = new td();
aGrid.addElement (new h6(WFName));
aGrid.addElement (new br());
aGrid.addElement (WFDescription);
desTable.addElement(aRow.addElement(aGrid));

return desTable;
}// printDescription



/**
	 *  Find the index at which the WFNode ID is positioned.
	 *
	 *
	 *  @param node_ID
 	 *  @param nodes_ID
	 *
	 *  @return int
	 */
private int getIndex(int node_ID, ArrayList nodes_ID){

int index = 0;

for(int i =0;i<nodes_ID.size();i++){
int element_ID = ((Integer) nodes_ID.get(i)).intValue();
if ( element_ID == node_ID)
index = i;

}//for

return index;
}//getIndex

/**
	 *  Print the control panel.
	 *
	 *
	 *  @param activeNode
 	 *  @param wf
	 *  @param nodes
	 *  @param nodes_ID
	 *
	 *  @return form
	 */
private form printControlPanel(int activeNode, MWorkflow wf, MWFNode [] nodes, ArrayList nodes_ID){
//create a new form
form myForm = new form(FORM_ACTION, form.METHOD_POST, form.ENC_DEFAULT);
myForm.addElement(new input("hidden", M_Command, ""));
myForm.addElement(new input("hidden", J_Command, ""));
myForm.setTarget(TARGET_WINDOW);
myForm.setName(FORM_NAME);

//test fist or last node

boolean isFirst = wf.isFirst(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));
boolean isLast = wf.isLast(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));

//get AD_Window_ID for the first and last node
int First_Window_ID = wf.getFirstNode().getAD_Window_ID();
int Last_Window_ID = nodes[nodes.length-1].getAD_Window_ID();

int Back_Window_ID=0;
int Next_Window_ID=0;
int index=0;

//WUtil.debug(""+activeNode,"activeNode");
if(!isFirst){
index = activeNode >=0 ? getIndex(activeNode,nodes_ID)-1 : 0 ;
//WUtil.debug(""+index,"index");
Back_Window_ID= nodes[index].getAD_Window_ID();
}
if(!isLast){
index = activeNode >=0 ? getIndex(activeNode,nodes_ID)+1 : 0 ;
Next_Window_ID= nodes[index].getAD_Window_ID();
}
boolean enableFirst = true;
boolean enableBack = true;
boolean enableNext = true;
boolean enableLast = true;

if(isFirst) {
	enableBack = false;
    enableFirst = false;
}
if(isLast) {
enableNext = false;
enableLast = false;
}

td firstImage = new td();
td backImage = new td();
td nextImage = new td();
td lastImage = new td();

//first
        String url = "'WWindow?AD_Window_ID="+First_Window_ID +"'";
	//String url=WebEnv.getBaseDirectory("WWindow")+"?AD_Window_ID="+First_Window_ID+"&Target_Window=win_"+First_Window_ID+"'";
          String Target_Window = "'WWindow"+First_Window_ID+"'";
		String parameters = url+ ","+Target_Window;
        String js_command = "popUp("+parameters+");submit()";
		firstImage.addElement(createControlButtonImage("Start", js_command, enableFirst));
//back
 url = "'WWindow?AD_Window_ID="+Back_Window_ID + "'";
   //  url=WebEnv.getBaseDirectory("WWindow")+"?AD_Window_ID="+Back_Window_ID+"&Target_Window=win_"+Back_Window_ID+"'";
 		 Target_Window = "'WWindow"+Back_Window_ID+"'";
		 parameters = url+ ","+Target_Window;
         js_command = "popUp("+parameters+");submit()";
		backImage.addElement(createControlButtonImage("Back", js_command, enableBack));

//next
 url = "'WWindow?AD_Window_ID="+Next_Window_ID+"'";
  //url=WebEnv.getBaseDirectory("WWindow")+"?AD_Window_ID="+Next_Window_ID+"&Target_Window=win_"+Next_Window_ID+"'";
		 Target_Window = "'WWindow"+Next_Window_ID+"'";
		 parameters = url+ ","+Target_Window;
         js_command = "popUp("+parameters+");submit()";
		nextImage.addElement(createControlButtonImage("Next", js_command, enableNext));

//last
 url = "'WWindow?AD_Window_ID="+Last_Window_ID+"'";
// url=WebEnv.getBaseDirectory("WWindow")+"?AD_Window_ID="+Last_Window_ID+"&Target_Window=win_"+Last_Window_ID+"'";
        Target_Window = "'WWindow"+Last_Window_ID+"'";
		 parameters = url+ ","+Target_Window;
         js_command = "popUp("+parameters+");submit()";
		lastImage.addElement(createControlButtonImage("End", js_command, enableLast));


tr aRow = new tr();
aRow.addElement(firstImage);
aRow.addElement(backImage);
aRow.addElement(nextImage);
aRow.addElement(lastImage);

//add the table to the form
myForm.addElement(new table().addElement(aRow));

return myForm;
}//printControlPanel


/**
	 *  Create Image with name, id of button_name and set M_Command onClick
	 *
	 *
	 *  @param  name        Name of the Image used also for Name24.gif
	 *  @param  js_command  Java script command, null results in 'submit();', an empty string disables OnClick
	 *  @param  enabled     Enable the immage button, if not uses the "D" image

	 *  @return Image
	 */
	private static img createControlButtonImage (String name, String js_command, boolean enabled)
	{
		StringBuffer imgName = new StringBuffer("wf" + name);

		if (!enabled)
			imgName.append("D");

         		imgName.append("24.gif");
		//
		img img = new img (WebEnv.getImageDirectory(imgName.toString()), name);
		if (enabled)
			img.setAlt(Msg.getMsg(AD_Language, name));  //  Translate ToolTip

					img.setID("imgButton");                     //  css
					
		//append js command only to enabled image
		if ( js_command != null && js_command.length() > 0 && enabled){
			img.setOnClick("document." + FORM_NAME + "." + M_Command + ".value='" + name + "';" + js_command);
		}
		//
		return img;
	}   //  createImage

/**
	 *  Create Image with name, id of button_name and set M_Command onClick
	 *
	 *
	 *  @param  name        Name of the Image used also for Name24.gif
	 *  @param  js_command  Java script command, null results in 'submit();', an empty string disables OnClick
	 *  @param  enabled     Enable the immage button, if not uses the "D" image

	 *  @return Image
	 */
	private static img createWorkflowImage (String name, int activeNode , String js_command, boolean pressed)
	{
		StringBuffer imgName = new StringBuffer(name);
		  imgName.append("WF");

		  		imgName.append(".gif");
		//
		img img = new img (WebEnv.getImageDirectory(imgName.toString()), name);



if ( !(imgName.toString()).startsWith("Spacer") &&  !(imgName.toString()).startsWith("Arrow")){
		if(!pressed)
			img.setID("imgButton");                     //  css
		else
			img.setID("imgButtonPressed");
	}

		//append js command only to enabled image
		if ( js_command != null && js_command.length() > 0 && activeNode != 0){
String js_command_front = "document." + FORM_NAME + "." + J_Command + ".value='" + activeNode + "'; " ;
js_command_front= js_command_front + "document." + FORM_NAME + ".submit();";
			img.setOnClick(js_command_front + js_command );
		}
		//
		return img;
	}   //  createImage


/**
	 *  Print the entire layout .
	 *
	 *
	 *  @param activeNode
 	 *  @param wf
	 *  @param nodes
	 *  @param nodes_ID
	 *  @param imageMap
	 *
	 *  @return WReportEngine
	 */
private WebDoc createLayout(WebDoc doc , MWorkflow wf,int activeNode,MWFNode [] nodes, ArrayList nodes_ID, int [][] imageMap){
body b= doc.getBody();

b.addElement(printWorkflow(activeNode, wf,  nodes, nodes_ID, imageMap));
b.addElement(new hr());
b.addElement(printDescription(activeNode, wf, nodes, nodes_ID));
b.addElement(new br());
b.addElement(printControlPanel(activeNode, wf, nodes, nodes_ID));

return doc;
}//createLayout

/**
	 *  Load workflw and initialize the session attributes.
	 *
	 *
	 *  @param ctx
 	 *  @param AD_Workflow_ID
	 *  @param sess
	 *
	 */
private void loadWorkflow(Properties ctx, int AD_Workflow_ID, HttpSession sess) {


		MWorkflow wf = new MWorkflow (ctx, AD_Workflow_ID,null);
		//get the MWFNode in order
		MWFNode[] nodes = wf.getNodes(true,Env.getContextAsInt(ctx, "#AD_Client_ID"));
		MWFNode wfn=null;
		ArrayList nodes_ID = new ArrayList();
		for (int i = 0; i < nodes.length; i++)
		{
		wfn= nodes[i];
		nodes_ID.add (new Integer(wfn.getAD_WF_Node_ID()));
						}//for
        int imageMap [][] = generateImageMap(nodes_ID);
 //printMap(imageMap);
//set session attribtes
sess.setAttribute( WORKFLOW,wf);
sess.setAttribute( NODES,nodes);
sess.setAttribute( NODES_ID,nodes_ID);
sess.setAttribute( IMAGE_MAP,imageMap);
sess.setAttribute( ACTIVE_NODE,new Integer(-999));

}//loadWorkflow

/**
	 *  Execute the M_Command and update the .
	 *
	 *
	 *  @param m_command
 	 *  @param wf
	 *  @param activeNode
	 *  @param nodes
	 *  @param nodes_ID
	 *  @param sess
	 *
	 */
private void executeCommand(String m_command, int j_command , MWorkflow wf,int activeNode, MWFNode [] nodes,ArrayList nodes_ID, HttpSession sess){
if (j_command != 0 )
{
sess.setAttribute(ACTIVE_NODE, new Integer(j_command));
return;
}

debug(m_command,"m_command in executeCommand");
//check first or last node
boolean isFirst = wf.isFirst(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));
boolean isLast = wf.isLast(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));

boolean notReady = false;
int updatedActiveNode =  activeNode;

if (activeNode < 0 ) notReady = true;

if (notReady){
updatedActiveNode = ((Integer) nodes_ID.get(0)).intValue();

}

else{
if(m_command.equals(NEXT)){
if(notReady)
	updatedActiveNode = ((Integer) nodes_ID.get(0)).intValue();
 	else
	updatedActiveNode= wf.getNext(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));

}//next
if(m_command.equals(BACK)) {
	if(notReady)
	updatedActiveNode = ((Integer) nodes_ID.get(0)).intValue();
 	else
	updatedActiveNode= wf.getPrevious(activeNode,Env.getContextAsInt(ctx, "#AD_Client_ID"));
}//back
if(m_command.equals(FIRST))
	updatedActiveNode= ((Integer) nodes_ID.get(0)).intValue();

if(m_command.equals(LAST)) updatedActiveNode= wf.getLast(0,Env.getContextAsInt(ctx, "#AD_Client_ID"));
}//ready

//update
sess.setAttribute(ACTIVE_NODE,new Integer(updatedActiveNode));
}//executeCommand


/**
	 *  Retrieve the AD_Workflow_ID from database using AD_Menu_ID.
	 *
	 *
	 *  @param AD_Menu_ID
 	 *
	 * @return int
	 */
private int getAD_Workflow_ID(int AD_Menu_ID){

 int AD_Workflow_ID=0;
 String sql = "SELECT AD_Workflow_ID FROM AD_Menu "
				+ "WHERE AD_Menu_ID=? AND Action='F'";
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql);
				pstmt.setInt(1, AD_Menu_ID);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next())
					AD_Workflow_ID= rs.getInt(1);
				rs.close();
				pstmt.close();
			}
			catch (SQLException e)
			{

				e.printStackTrace();
			}
return AD_Workflow_ID;
}//getAD_Workflow_ID


/**
	 *  Prepare Page.
	 *  - Set Header
	 *
	 *  @param loginInfo
	 *
	 *  @return WReportEngine page
	 */
	private WebDoc preparePage(String loginInfo)
	{
		WebDoc doc = WebDoc.create ("Workflow");
		head header = doc.getHead();
		body b=doc.getBody();
		//  add window.js & .css
		header.addElement(new script("", WebEnv.getBaseDirectory("/js/window.js")));
		header.addElement(new link().setRel("stylesheet").setHref(WebEnv.getBaseDirectory("/css/window.css")));
		//
		//
		String title ="Workflow"+" - " + loginInfo;
		b.addElement(new script("top.document.title='" + title + "';"));
		return doc;
	}   //  preparePage

/**
	 *  Debugger.
	 *  - Display the name and value set
	 *
	 *  @param variable
	 *  @param name
	 *
	 *  @return WReportEngine page
	 */
private void debug(Object  variable , String name){
if (variable!=null)
{
System.out.println("*************"+name+"="+variable);
}
else
System.out.println("*************"+name+" is null");
}//debug
private void printMap(int [][] map){
final int ROW = map.length;
final int COL = map[0].length;

   for(int row=0;row<ROW;row++){
     for(int col=0;col<COL;col++){
System.out.print(" "+map[row][col]+" ");
     }
System.out.println(" ");
   }


        }// printMap

}   //  WWorkflow
