SET DEFINE OFF;
UPDATE AD_ELEMENT SET DESCRIPTION='Trees are used for (financial) reporting and security access (via role)', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=134;
UPDATE AD_ELEMENT SET DESCRIPTION='Trees are used for (financial) reporting', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID IN (131,135,136,137,2514,2515);
UPDATE AD_ELEMENT SET HELP='A Callout allow you to create Java extensions to perform certain tasks always after a value changed. Callouts should not be used for validation but consequences of a user selecting a certain value.
The callout is a Java class implementing org.compiere.model.Callout and a method name to call.  Example: "org.compiere.model.CalloutRequest.copyText" instantiates the class "CalloutRequest" and calls the method "copyText". You can have multiple callouts by separating them via a semicolon', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=224;
UPDATE AD_ELEMENT SET HELP='The Costing Method indicates how costs will be calculated (Standard, Average, Lifo, FiFo).  The default costing method is defined on accounting schema level and can be optionally overwritten in the product category.  The costing method cannot conflict with the Material Movement Policy (defined on Product Category).', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=241;
UPDATE AD_ELEMENT SET NAME='Records deletable',PRINTNAME='Records deletable', HELP='The Records Deletable checkbox indicates if a record can be deleted from the database.  If records cannot be deleted, you can only deselect the Active flag', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=366;
UPDATE AD_ELEMENT SET HELP='The Sales Rep checkbox indicates if this business partner is a sales representative. A sales representative may also be an employee, but does not need to be.'
, PO_HELP='The Sales Rep checkbox indicates if this business partner is a company agent. A company agent may also be an employee, but does not need to be.'
, UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=409;
UPDATE AD_ELEMENT SET NAME='Updatable',PRINTNAME='Updatable', HELP='The Updatable checkbox indicates if a field can be updated by the user.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=422;
UPDATE AD_ELEMENT SET NAME='User updatable',PRINTNAME='User updatable', HELP='The User Updatable checkbox indicate if the user can update  this field.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=423;
UPDATE AD_ELEMENT SET HELP='The Credit Limit indicates the total amount allowed "on account" in primary accounting currency.  If the Credit Limit is 0, no check is performed.  Credit Management is based on the Total Open Amount, which includes Vendor activities.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=553;
UPDATE AD_ELEMENT SET HELP='The Order is a control document.  The  Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you close an order, unshipped (backordered) quantities are cancelled.'
, PO_HELP='The Purchase Order is a control document.  The Purchase Order is complete when the quantity ordered is the same as the quantity shipped and invoiced.  When you close an order, unshipped (backordered) quantities are cancelled.'
, UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=558;
UPDATE AD_ELEMENT SET HELP='The Enforce Price Limit check box indicates that prices cannot be below the limit price in Orders and Invoices.  This can be overwritten, if the role allows this.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=882;
UPDATE AD_ELEMENT SET HELP='The SO Sub Type indicates the type of sales order this document refers to.  This field only appears when the Document Base Type is Sales Order.  The selection made here will determine which documents will be generated when an order is processed and which documents must be generated manually or in batches.  <br>
The following outlines this process.<br>
SO Sub Type of <b>Standard Order</b> will generate just the <b>Order</b> document when the order is processed.  <br>
The <b>Delivery Note</b>, <b>Invoice</b> and <b>Receipt</b> must be generated via other processes.  <br>
SO Sub Type of <b>Warehouse Order</b> will generate the <b>Order</b> and <b>Delivery Note</b>. <br> The <b>Invoice</b> and <b>Receipt</b> must be generated via other processes.<br>
SO Sub Type of <b>Credit Order</b> will generate the <b>Order</b>, <b>Delivery Note</b> and <b>Invoice</b>. <br> The <b>Receipt</b> must be generated via other processes.<br>
SO Sub Type of <b>POS</b> (Point of Sale) will generate all document', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=1018;
UPDATE AD_ELEMENT SET DESCRIPTION='Account for Vendor Service Liability', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=1057;
UPDATE AD_ELEMENT SET HELP='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes not the not due invoices.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=1092;
UPDATE AD_ELEMENT SET HELP='The Relative Priority indicates the location to pick from first if an product is stored in more than one location.  (100 = highest priority, 0 = lowest).  For outgoing shipments, the location is picked with the highest priority where the entire quantity can be shipped from.  If there is no location, the location with the highest priority is used.
The Priority is ignored for products with Guarantee Date (always the oldest first) or if a specific instance is selected.
Incoming receipts are stored at the location with the highest priority, if not explicitly selected.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=1145;
UPDATE AD_ELEMENT SET HELP='When processing a web order, a confirmation is sent to the EMail address of the customer from the request EMail address copying this email address when entered.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=1993;
UPDATE AD_ELEMENT SET HELP='Web Click Details', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2007;
UPDATE AD_ELEMENT SET HELP='Attributes are specific to a Product Attribute Set (e.g. Size for T-Shirts: S,M,L). If you have multiple attributes and want to search under a common attribute, you define a search attribute. Example: have one Size search attribute combining the values of all different sizes (Size for Dress Shirt  XL,L,M,S,XS). The Attribute Search allows you to have all values available for selection.  This eases the maintenance of the individual product attribute.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2025;
UPDATE AD_ELEMENT SET DESCRIPTION='Included Tab in this Tab (Master Detail)', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2026;
UPDATE AD_ELEMENT SET HELP='Internal (Operating System) Name of the Printer; Please mote that the printer name may be different on different clients. Enter a printer name, which applies to ALL clients (e.g. printer on a server). <p>
If none is entered, the default printer is used. You specify your default printer when you log in. You can also change the default printer in Preferences.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2051;
UPDATE AD_ELEMENT SET DESCRIPTION='Assignment to (transaction) Organization', HELP='Assignment to the transaction organization (cost center).', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2072;
UPDATE AD_ELEMENT SET HELP='"You can purchase professional support from Adempiere, Inc. or their partners.  See http://www.adempiere.com for details.
"', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2124;
UPDATE AD_ELEMENT SET HELP='The Type of data Replication determines the direction of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2137;
UPDATE AD_ELEMENT SET HELP='If selected, public users can read/view the entry. Public are users without a Role in the system. Use security rules for more specific access control.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2139;
UPDATE AD_ELEMENT SET HELP='If selected, public users can write/create entries. Public are users without a Role in the system. Use security rules for more specific access control.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2140;
UPDATE AD_ELEMENT SET HELP='Set up knowledge categories and values as a search aid. Examples are Release Version, Product Area, etc. Knowledge Category values act like keywords.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2141;
UPDATE AD_ELEMENT SET DESCRIPTION='Related Entry for this Entry'
, HELP='Related Knowledge Entry for this Knowledge Entry'
, UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2145;
UPDATE AD_ELEMENT SET HELP='The Source of a Knowledge Entry is a pointer to the originating system. The Knowledge Entry has an additional entry (Description URL)  for more detailed info.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2146;
UPDATE AD_ELEMENT SET DESCRIPTION='Knowledge Keyword Synonym', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2147;
UPDATE AD_ELEMENT SET HELP='Topic or Discussion Thead', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2148;
UPDATE AD_ELEMENT SET NAME='Knowledge Type', HELP='Area of knowledge - A Type has multiple Topics', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2149;
UPDATE AD_ELEMENT SET DESCRIPTION='Name of the Project Cycle Step', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2165;
UPDATE AD_ELEMENT SET DESCRIPTION='Minimum Amount in Document Currency', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2177;
UPDATE AD_ELEMENT SET DESCRIPTION='Minimum number of guarantee days', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2197;
UPDATE AD_ELEMENT SET HELP='If a line is Description Only, e.g. Product Inventory is not corrected. No accounting transactions are created and the amount or totals are not included in the document.  This for including descriptive detail lines, e.g. for an Work Order.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2183;
UPDATE AD_ELEMENT SET HELP='This allows to have the three general situations of "not open" - "open" - "closed"', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2201;
UPDATE AD_ELEMENT SET NAME='Calculate Maximum (?)', DESCRIPTION='Calculate the maximum amount', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2207;
UPDATE AD_ELEMENT SET HELP='A running total creates a sum at the end of a page and on the top of the next page for all columns, which have a Sum function.  You should define running total only once per format.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2212;
UPDATE AD_ELEMENT SET DESCRIPTION='Electronic Funds Transfer Payee Account Information', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2233;
UPDATE AD_ELEMENT SET DESCRIPTION='Shelf Life Days remaining to Guarantee Date (minus minimum guarantee days)', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2237;
UPDATE AD_ELEMENT SET HELP='Minimum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life ((Guarantee Date-Today) / Guarantee Days) less than the minimum shelf life, unless you select "Show All"', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2240;
UPDATE AD_ELEMENT SET HELP='Minimum Shelf Life of products with Guarantee Date instance. If > 0 you cannot select products with a shelf life less than the minimum shelf life, unless you select "Show All"', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2264;
UPDATE AD_ELEMENT SET DESCRIPTION='If selected, the product is displayed in the initial or any empty search'
, HELP='In the display of products in the Web Store, the product is displayed in the initial view or if no search criteria are entered. To be displayed, the product must be in the price list used.'
, UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2277;
UPDATE AD_ELEMENT SET HELP='The loader definition provides the parameters to load bank statements from EFT formats like SWIFT (MT940) or OFX', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2283;
UPDATE AD_ELEMENT SET DESCRIPTION='Date format used in the input format', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS')
WHERE AD_ELEMENT_ID=2286;
UPDATE AD_ELEMENT SET HELP='Activity Result of the execution of the Workflow Process Instance', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2308;
UPDATE AD_ELEMENT SET HELP='History of changes of the Workflow Process Activity', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2310;
UPDATE AD_ELEMENT SET HELP='Semantics for multiple outgoing Transitions for a Node/Activity.  AND represents multiple concurrent threads - XOR represents the first transition with a true Transition condition.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2337;
UPDATE AD_ELEMENT SET HELP='You can create a bid for a topic.  Depending on the type, the highest bidder wins the Topic - or you participate in funding for a Topic.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2360;
UPDATE AD_ELEMENT SET HELP='Available Funds (from Payments) and Committed or Uncommitted funds for Bids', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2362;
UPDATE AD_ELEMENT SET HELP='Available Funds (for Payments) and Committed or Uncommitted funds from Offers', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2364;
UPDATE AD_ELEMENT SET DESCRIPTION='An Error occurred in the execution', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2395;
UPDATE AD_ELEMENT SET DESCRIPTION='The response can have just the total amount for the RfQ', HELP='If not selected, the response must be provided per line', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2403;
UPDATE AD_ELEMENT SET DESCRIPTION='Are Responses to the Request for Quotation accepted', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2404;
UPDATE AD_ELEMENT SET DESCRIPTION='The response is the selected winner', HELP='The response is the selected winner. If selected on Response level, the line selections are ignored.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2405;
UPDATE AD_ELEMENT SET DESCRIPTION='Product used to determine the price of the membership for the topic type', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2415;
UPDATE AD_ELEMENT SET HELP='Consecutive range to', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2455;
UPDATE AD_ELEMENT SET HELP='Define the method how the next occurrence is calculated', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2457;
UPDATE AD_ELEMENT SET HELP='If selected and if the window / tab is not read only, you can always update the column.  This might be useful for comments, etc.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2468;
UPDATE AD_ELEMENT SET DESCRIPTION='Elapsed Time in milli seconds', HELP='Elapsed Time in milli seconds', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2474;
UPDATE AD_ELEMENT SET DESCRIPTION='Distribution Run Lines define Distribution List, the Product and Quantities', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2486;
UPDATE AD_ELEMENT SET HELP='Maintain and allow to transfer general statistics (number of clients, orgs, business partners, users, products, invoices) to get a better feeling for the application use.  This information is not published.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2489;
UPDATE AD_ELEMENT SET HELP='Forecast of Product Quantity by Period', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2499;
UPDATE AD_ELEMENT SET HELP='The Total Open Balance Amount is the calculated open item amount for Customer and Vendor activity.  If the Balance is below zero, we owe the Business Partner.  The amount is used for Credit Management.
Invoices and Payment Allocations determine the Open Balance (i.e. not Orders or Payments).', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2562;
UPDATE AD_ELEMENT SET HELP='The Attribute Value type determines the data/validation type', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2574;
UPDATE AD_ELEMENT SET DESCRIPTION='Value set by Migration for post-Migration tasks.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2625;
UPDATE AD_ELEMENT SET DESCRIPTION='Type of Workflow', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2626;
UPDATE AD_ELEMENT SET HELP='When a document is due for too long without activity, a reminder is sent. 0 means no reminders.
The Remind Days are the days when the next email reminder is sent.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2631;
UPDATE AD_ELEMENT SET HELP='Internal name of the transaction', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2655;
UPDATE AD_ELEMENT SET HELP='List of classes implementing the interface org.compiere.model.ModelValidator, separated by semicolon.
The class is called for the client and allows to validate documents in the prepare stage and monitor model changes.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2670;
UPDATE AD_ELEMENT SET HELP='This allows to have multiple closed status', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2723;
UPDATE AD_ELEMENT SET HELP='The EMail address is used to send mails to users of the web store', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2731;
UPDATE AD_ELEMENT SET DESCRIPTION='Assignment of Employee (User) to Job Position', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2763;
UPDATE AD_ELEMENT SET HELP='Subscriber to invite to respond to RfQs', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2377;
UPDATE AD_ELEMENT SET HELP='Once per operation', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2779;
UPDATE AD_ELEMENT SET HELP='Change requests for a Bill of Materials. They can be automatically created from Requests, if enabled in the Request Type and the Request Group refers to a Bill of Materials', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2785;
UPDATE AD_ELEMENT SET HELP='The Bill of Material Component determines what products, services and outside processing is included in producing the Product. It references the operation and determines it''s sequence.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2786;
UPDATE AD_ELEMENT SET HELP='Phantom Component are not stored and produced with the product. This is an option to avoid maintaining an Engineering and Manufacturing Bill of Materials.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2788;
UPDATE AD_ELEMENT SET DESCRIPTION='Optional Lead Time offset before starting production', HELP='Optional Lead Time offset before starting production', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2789;
UPDATE AD_ELEMENT SET HELP='If selected, the user has full access to the Business Partner (BP) information (Business Documents like Orders, Invoices - Requests) or resources (Assets, Downloads). If you deselect it, the user has no access rights unless, you explicitly grant it in tab "BP Access"', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2835;
UPDATE AD_ELEMENT SET DESCRIPTION='User/contact access to Business Partner information and resources', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2836;
UPDATE AD_ELEMENT SET HELP='If selected, the accounting consequences are immediately generated when completing a document.  Otherwise the document is posted by a batch process.  You should select this only if you are testing.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2843;
UPDATE AD_ELEMENT SET HELP='Note that the cost queue may not be the same as the physical movement cost queue due to differences in costing level and warehouse priority.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2845;
UPDATE AD_ELEMENT SET HELP='Account used for posting matched product (item) expenses (e.g. AP Invoice, Invoice Match).  You would use a different account then Product Expense, if you want to differentiate service related costs from item related costs. The balance on the clearing account should be zero and accounts for the timing difference between invoice receipt and matching.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2847;
UPDATE AD_ELEMENT SET HELP='If selected, you will post service related revenue to a different receivables account and service related cost to a different payables account.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2849;
UPDATE AD_ELEMENT SET HELP='Enter the number of records the query will return without confirmation to avoid unnecessary system load.  If 0, the system default of 500 is used.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2853;
UPDATE AD_ELEMENT SET HELP='Enter the number of records a user will be able to query to avoid unnecessary system load.  If 0, no restrictions are imposed.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2854;
UPDATE AD_ELEMENT SET HELP='The Posting Type Commitments is created when posting Purchase Orders; The Posting Type Reservation is created when posting Requisitions.  This is used for budgetary control.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2860;
UPDATE AD_ELEMENT SET HELP='Accounting related information for reconciliation with documents. It includes all revenue/expense and tax entries as a base for detail reporting', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2864;
UPDATE AD_ELEMENT SET HELP='If selected AP tax is handled as expense, otherwise it is handled as a VAT credit.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2870;
UPDATE AD_ELEMENT SET HELP='Budget Control allows you to restrict the use of expenditures, commitments (Purchase Orders) and reservations (Requisitions). If defined, you may not be able to approve Requisitions, Purchase Orders, or AP Invoices.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2871;
UPDATE AD_ELEMENT SET HELP='A user defined accounting element refers to a Adempiere table. This allows to use any table content as an accounting dimension (e.g. Project Task).  Note that User Elements are optional and are populated from the context of the document (i.e. not requested)', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID IN (2877,2878);
UPDATE AD_ELEMENT SET HELP='Visual representation of performance by color.  The Schema has often three levels (e.g. red-yellow-green).  Adempiere support two levels (e.g. red-green) or four levels (e.g. gray-bronze-silver-gold).  Note that Measures without a goal are represented white.  The percentages could be between 0 and unlimited (i.e. above 100%).', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2903;
UPDATE AD_ELEMENT SET DESCRIPTION='Performance Ratio', HELP='Calculation instruction set  for a performance ratio', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2919;
UPDATE AD_ELEMENT SET DESCRIPTION='Performance Ratio Used', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2929;
UPDATE AD_ELEMENT SET NAME='Next Maintenance',PRINTNAME='Next Maintenance', DESCRIPTION='Next Maintenance Date', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2932;
UPDATE AD_ELEMENT SET DESCRIPTION='Next Maintenance Unit', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2934;
UPDATE AD_ELEMENT SET HELP='The Meta Robots Tag define on how a search engines robot should handle this page and the following ones. It defines two keywords: (NO)INDEX which defines whether or not to index this content and (NO)FOLLOW which defines whether or not to follow links. The most common combination is INDEX,FOLLOW which will force a search robot to index the content and follow links and images.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2975;
UPDATE AD_ELEMENT SET DESCRIPTION='Contains list of elements separated by CR', HELP='Contains a list of elements this template uses separated by a Carriage Return. Last line should be empty', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2983;
UPDATE AD_ELEMENT SET HELP='A Container defines the abstract level around the content, it defines how the content gets displayed, indexed and stored.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2985;
UPDATE AD_ELEMENT SET DESCRIPTION='External Link (URL) for the Container', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2988;
UPDATE AD_ELEMENT SET HELP='This table contains all the media content like images, flash movies etc.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=2997;
UPDATE AD_ELEMENT SET HELP='If we have a block in content where announce content and also sponsored links we should mention the sponsored ones', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3007;
UPDATE AD_ELEMENT SET HELP='Media Server list to which content should get transferred', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3009;
UPDATE AD_ELEMENT SET HELP='A container element defines the smallest definition of content, i.e. the headline, the content etc.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID IN (3013,3023);
UPDATE AD_ELEMENT SET HELP='The date the revenue recognition starts.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3032;
UPDATE AD_ELEMENT SET DESCRIPTION='Container Stage Template Table', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3050;
UPDATE AD_ELEMENT SET HELP='The entity type determines the ownership of Application Dictionary entries.  The types "Dictionary" and "Adempiere" should not be used and are maintained by Adempiere (i.e. all changes are reversed during migration to the current definition).', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3052;
UPDATE AD_ELEMENT SET HELP='If your application requires additional jar files, enter them here. The jar files must be located in the $ADEMPIERE_HOME/lib directory.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3054;
UPDATE AD_ELEMENT SET HELP='Keyword not to be indexed, optional restricted to specific Document Type, Container or Request Type', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3078;
UPDATE AD_ELEMENT SET DESCRIPTION='Open Debit in document currency & rate', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3083;
UPDATE AD_ELEMENT SET DESCRIPTION='Open Credit in document currency & rate', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3084;
UPDATE AD_ELEMENT SET DESCRIPTION='Open Balance in document currency & rate', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3085;
UPDATE AD_ELEMENT SET HELP='The dunning letter with this level includes all due invoices.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3088;
UPDATE AD_ELEMENT SET HELP='The dunning letter with this level includes all not due invoices.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3089;
UPDATE AD_ELEMENT SET HELP='The LDAP Server allows third party software (e.g. Apache) to use the users defined in the system to authenticate and authorize them.  There is only one server per Adempiere system.  The "o" is the Client key and the optional "ou" is the Interest Area key.', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=3093;
UPDATE AD_ELEMENT SET PRINTNAME=NAME, DESCRIPTION='Table to check whether the migration script has been applied', UPDATED=TO_DATE('2010-01-13 10:38:15','YYYY-MM-DD HH24:MI:SS') 
WHERE AD_ELEMENT_ID=53350;
COMMIT;
