//MBPartnerLocationTest.java
package test.functional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.util.DB;
import org.compiere.util.Env;

import test.AdempiereTestCase;

public class MBPartnerLocationTest extends AdempiereTestCase {
	
	// Variables needed for importing/migrating business partners
	private MBPartner m_partner = null;  //business partner
	private MLocation location = null;
 	private MBPartnerLocation bpl = null; //business partner location
 	private MBPGroup m_group = null;
	
	/*
	private MUser user = null; //business contact
	private MContactInterest ci = null; //contact interest area

	// Variables needed for importing/migrating accounts
	private MElementValue ev = null;  //element value
	private MAcctSchema as = null; //account schema
	private MAccount acct = null; //account
	
	// Variables needed for importing/migrating bank statements
	private MBankStatement statement = null;
	private MBankAccount account = null;
	private MBankStatementLine line = null;

	// Variables needed for importing/migrating conversion rates
	private MConversionRate rate = null;

	// Variables needed for importing/migrating General Ledger Journal
	private MJournalBatch batch = null;
	private MJournal journal = null;
	private MJournalLine line = null;

	// Variables needed for importing/migrating Inventory
	private MInventory inventory = null;
	private MProduct product = null;
	private MAttributeSet mas = null;
	private MAttributeSetInstance masi = null;
	private MInventoryLine line = null;

	// Variables needed for importing/migrating Invoices
	private MInvoice invoice = null;
	private MInvoiceLine line = null;

	// Variables needed for importing/migrating orders
	private MOrder order = null;
	private MOrderLine line = null;

	// Variables needed for importing/migrating payments
	private MPayment payment = null;

	// Variables needed for importing/migrating products
	private MProduct product = null;
	private MProductPrice pp = null;

	// Variables needed for mrp
	
	private MCost cost = null;
	private MCostElement costElement = null;
	private MForecastLine forcastLine = null;
	private MOrder order = null;
	private MOrderLine line = null;
	private MPPCostCollector costCollector = null;
	private MPPOrderBOM orderBOM = null;
	private MPPOrderBOMLine = null;
	private MPPOrderBOMLineMA = null;
	private MPPOrderCost = null;
	private MPPOrder = null;
	private MPPProductBOM = null;
	private MPPProductBOMLine = null;
	private MPPProductCosting = null;
	private MPPProductPlanning = null;
	private MPPProfileBOMCost = null;
	private MPPProfileBOM = null;
	private MPPProfileBOMProduct = null;
	private MPPProfileBOMReal = null;
	private MPPProfileBOMSelected = null;
	private MQMSpecification = null;
	private MQMSpecificationLine = null;
	private MRequisition = null;
	private MRequisitionLine = null;
	

	// other variables that may be needed:

	private MAdvertisement = null;
	private MAging = null;
	private MAlert = null;
	private MAlertRecipient = null;
	private MAlertRule = null;

	private MAllocationHdr = null;
	private MAllocationLine = null;

	private MAssetDelivery = null;
	private MAssetGroup = null;
	private MAsset = null;
	private MAssignmentSlot = null;
	private MAttributeInstance = null;

	private MAccessProfile = null;
	private MMailText = null;
	private MAcctProcessor = null;
	private MMatchInv = null;
	private MAcctProcessorLog = null;
	private MMatchPO = null;
	private MAchievement = null;
	private MMeasureCalc = null;
	private MActivity = null;
	private MMeasure = null;
	private MAd = null;
	private MMediaDeploy = null;
	private MAdvertisement = null;
	private MMedia = null;
	private MAging = null;
	private MMediaServer = null;
	private MAlert = null;
	private MMovementConfirm = null;
	private MAlertProcessor = null;
	private MMovement = null;
	private MAlertProcessorLog = null;
	private MMovementLineConfirm = null;
	private MAlertRecipient = null;
	private MMovementLine = null;
	private MAlertRule = null;
	private MMovementLineMA = null;
	private MAllocationHdr = null;
	private MNewsChannel = null;
	private MAllocationLine = null;
	private MNewsItem = null;
	private MAssetDelivery = null;
	private MOrder = null;
	private MAssetGroup = null;
	private MOrderLine = null;
	private MAsset = null;
	private MOrderTax = null;
	private MAssignmentSlot = null;
	private MPackage = null;
	private MAttributeInstance = null;
	private MPackageLine = null;
	private MAttribute = null;
	private MPaymentAllocate = null;
	private MAttributeSetInstance = null;
	private MPaymentBatch = null;
	private MAttributeSet = null;
	private MPayment = null;
	private MAttributeUse = null;
	private MPaymentProcessor = null;
	private MAttributeValue = null;
	private MPaymentTerm = null;
	private MBankAccount = null;
	private MPaymentValidate = null;
	private MBank = null;
	private MPaySchedule = null;
	private MBankStatement = null;
	private MPaySelectionCheck = null;
	private MBankStatementLine = null;
	private MPaySelection = null;
	private MBankStatementLoader = null;
	private MPaySelectionLine = null;
	private MBankStatementMatcher = null;
	private MPeriodControl = null;
	private MBOM = null;
	private MPeriod = null;
	private MBOMProduct = null;
	private MPOS = null;
	private MBPartnerInfo = null;
	private MPOSKey = null;
	private MPOSKeyLayout = null;
	private MPreference = null;
	private MBPBankAccount = null;
	private MPriceList = null;
	private MBPGroup = null;
	private MPriceListVersion = null;
	private MCalendar = null;
	private MProductBOM = null;
	private MCampaign = null;
	private MProductCategoryAcct = null;
	private MCashBook = null;
	private MProductCategory = null;
	private MCash = null;
	private MProductCosting = null;
	private MCashLine = null;
	private MProductDownload = null;
	private MChangeNotice = null;
	private MProduct = null;
	private MChangeRequest = null;
	private MProductPO = null;
	private MCharge = null;
	private MProductPrice = null;
	private MChatEntry = null;
	private MProductPricing = null;
	private MChat = null;
	private MProjectIssue = null;
	private MChatType = null;
	private MProject = null;
	private MClickCount = null;
	private MProjectLine = null;
	private MClick = null;
	private MProjectPhase = null;
	private MColor = null;
	private MProjectTask = null;
	private MColorSchema = null;
	private MProjectType = null;
	private MCommissionAmt = null;
	private MProjectTypePhase = null;
	private MCommissionDetail = null;
	private MProjectTypeTask = null;
	private MCommission = null;
	private MRecurring = null;
	private MCommissionLine = null;
	private MRecurringRun = null;
	private MCommissionRun = null;
	private MRefTable = null;
	private MContactInterest = null;
	private MRegistrationAttribute = null;
	private MContainerElement = null;
	private MRegistration = null;
	private MContainer = null;
	private MRegistrationValue = null;
	private MCostDetail = null;
	private MReplication = null;
	private MCost = null;
	private MReplicationLog = null;
	private MCostQueue = null;
	private MReplicationRun = null;
	private MCounterCount = null;
	private MRequestAction = null;
	private MCStageElement = null;
	private MRequestCategory = null;
	private MCStage = null;
	private MRequest = null;
	private MDesktop = null;
	private MRequestProcessor = null;
	private MDiscountSchemaBreak = null;
	private MRequestProcessorLog = null;
	private MDiscountSchema = null;
	private MRequestProcessorRoute = null;
	private MDiscountSchemaLine = null;
	private MRequestType = null;
	private MDistribution = null;
	private MRequestUpdate = null;
	private MDistributionLine = null;
	private MRequisition = null;
	private MDistributionList = null;
	private MRequisitionLine = null;
	private MDistributionListLine = null;
	private MResolution = null;
	private MDistributionRunDetail = null;
	private MResourceAssignment = null;
	private MDistributionRun = null;
	private MResource = null;
	private MDistributionRunLine = null;
	private MResourceType = null;
	private MDocTypeCounter = null;
	private MResourceUnAvailable = null;
	private MDunning = null;
	private MRevenueRecognition = null;
	private MDunningLevel = null;
	private MRevenueRecognitionPlan = null;
	private MDunningRunEntry = null;
	private MRfQ = null;
	private MDunningRun = null;
	private MRfQLine = null;
	private MDunningRunLine = null;
	private MRfQLineQty = null;
	private Measure = null;
	private MRfQResponse = null;
	private MEntityType = null;
	private MRfQResponseLine = null;
	private MExpenseType = null;
	private MRfQResponseLineQty = null;
	private MFactAcct = null;
	private MRfQTopic = null;
	private MFormAccess = null;
	private MRfQTopicSubscriber = null;
	private MForm = null;
	private MRfQTopicSubscriberOnly = null;
	private MGLCategory = null;
	private MRMA = null;
	private MGoal = null;
	private MRMALine = null;
	private MGoalRestriction = null;
	private MScheduler = null;
	private MGroup = null;
	private MSchedulerLog = null;
	private MHierarchy = null;
	private MSchedulerPara = null;
	private MIndex = null;
	private MSchedulerRecipient = null;
	private MIndexStop = null;
	private MSerNoCtl = null;
	private MInfoColumn = null;
	private MSetup = null;
	private MInfoWindow = null;
	private MShipper = null;
	private MInOutConfirm = null;
	private MSLACriteria = null;
	private MInOut = null;
	private MSLAGoal = null;
	private MInOutLineConfirm = null;
	private MSLAMeasure = null;
	private MInOutLine = null;
	private MStatusCategory = null;
	private MInOutLineMA = null;
	private MStatus = null;
	private MInterestArea = null;
	private MStorage = null;
	private MInventory = null;
	private MStore = null;
	private MInventoryLine = null;
	private MTask = null;
	private MInventoryLineMA = null;
	private MTaxCategory = null;
	private MInvoiceBatch = null;
	private MTaxDeclarationAcct = null;
	private MInvoiceBatchLine = null;
	private MTaxDeclaration = null;
	private MInvoice = null;
	private MTaxDeclarationLine = null;
	private MInvoiceLine = null;
	private MTax = null;
	private MInvoicePaySchedule = null;
	private MTaxPostal = null;
	private MInvoiceSchedule = null;
	private MTemplate = null;
	private MInvoiceTax = null;
	private MTimeExpense = null;
	private MJournalBatch = null;
	private MTimeExpenseLine = null;
	private MJournal = null;
	private MTransaction = null;
	private MJournalLine = null;
	private MTree = null;
	private MKCategory = null;
	private MTreeNode = null;
	private MLandedCostAllocation = null;
	private MultiMap = null;
	private MLandedCost = null;
	private MUOMConversion = null;
	private MLdapAccess = null;
	private MUOM = null;
	private MLdapProcessor = null;
	private MUserMail = null;
	private MLdapProcessorLog = null;
	private MWarehousePrice = null;
	private MLdapUser = null;
	private MWebProjectDomain = null;
	private MLotCtl = null;
	private MWebProject = null;
	private MLot = null;
	private MWithholding = null;
	private MMailMsg = null;
	private MYear = null;

	

	// from dbPort/src/org/compiere/model
	
	private MAccessLog = null;          
	private MIssueProject = null;
	private MRecordAccess = null;
	private MAccount = null;
	private MIssueSystem = null;
	private MRefList = null;
	private MAccountLookup = null;
	private MIssueUser = null;
	private MRegion = null;
	private MAcctSchemaDefault = null;
	private MLanguage = null;
	private M_Registration = null;
	private MAcctSchemaElement = null;
	private MLocation = null;
	private MRole = null;
	private MAcctSchemaGL = null;
	private MLocationLookup = null;
	private MRoleOrgAccess = null;
	private MAcctSchema = null;
	private MLocator = null;
	private MRoleTest = null;
	private MArchive = null;
	private MLocatorLookup = null;
	private MSalesRegion = null;
	private MAttachmentEntry = null;
	private MLookupCache = null;
	private MSequence = null;
	private MAttachment = null;
	private MLookupFactory = null;
	private MSession = null;
	private MAttachmentNote = null;
	private MLookupInfo = null;
	private MSysConfig = null;
	private MChangeLog = null;
	private MLookup = null;
	private MSystem = null;
	private MClientInfo = null;
	private MMenu = null;
	private MTab = null;
	private MClient = null;
	private MMessage = null;
	private MTableAccess = null;
	private MClientShare = null;
	private MNote = null;
	private MTable = null;
	private MColumnAccess = null;
	private ModelValidationEngine = null;
	private MTest = null;
	private MColumn = null;
	private ModelValidator = null;
	private MTree_Base = null;
	private MConversionRate = null;
	private MOrgInfo = null;
	private MTree_NodeBP = null;
	private MConversionType = null;
	private MOrg = null;
	private MTree_NodeCMC = null;
	private MCostElement = null;
	private MPackageExpCommon = null;
	private MTree_NodeCMS = null;
	private MCostType = null;
	private MPackageExpDetail = null;
	private MTree_Node = null;
	private MCountry = null;
	private MPackageExp = null;
	private MTree_NodeMM = null;
	private MCurrencyAcct = null;
	private MPAttributeLookup = null;
	private MTree_NodePR = null;
	private MCurrency = null;
	private MPInstance = null;
	private MUser = null;
	private MDocType = null;
	private MPInstanceLog = null;
	private MUserOrgAccess = null;
	private M_Element = null;
	private MPInstancePara = null;
	private MUserRoles = null;
	private MElement = null;
	private MPrivateAccess = null;
	private MWarehouse = null;
	private MElementValue = null;
	private MProcessAccess = null;
	private MWindowAccess = null;
	private MField = null;
	private MProcess = null;
	private MWindow = null;
	private MImage = null;
	private MProcessPara = null;
	private MIssue = null;
	private MQuery = null;
	*/

    public int getC_Region_ID(String Region) {

		String sql = "select c_region_id from c_region where name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int C_Region_ID = -1;
		
	    try {
			pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getTrxName());
			pstmt.setString(1, Region);
			rs = pstmt.executeQuery();
			
	
			while (rs.next()) {
				C_Region_ID = rs.getInt(1);
			}
		} catch (SQLException e) {
			fail(e.getLocalizedMessage());
		} finally {
			DB.close( rs, pstmt );
		}
	    return C_Region_ID;
    }

    public int getC_Country_ID(String Country) {
	
		String sql = "select c_country_id from c_country where name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int C_Country_ID = -1;
		
	    try {
			pstmt = DB.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getTrxName());
			pstmt.setString(1, Country);
			rs = pstmt.executeQuery();
			
	
			while (rs.next()) {
				C_Country_ID = rs.getInt(1);
			}
		} catch (SQLException e) {
			fail(e.getLocalizedMessage());
		} finally {
			DB.close( rs, pstmt );
		}
	
	    return C_Country_ID;

    }

    public void testCreatePartnerLocation()
    {
		try {
			location = new MLocation(getCtx(), 0, getTrxName());
			location.setC_Country_ID(getC_Country_ID("United States"));
			location.setC_Region_ID(getC_Region_ID("CA"));
			location.setCity("Windsor");
			location.setAddress1("Happy Lane");
			location.setAddress2("Happy Lane 2");
	        String zipcode =  ("95492");
			location.setPostal(zipcode);
			location.setPostal_Add(zipcode);
			location.setAD_Org_ID(0);
			location.saveEx();
	
			m_group = new MBPGroup (getCtx(), 0, getTrxName());
			m_group.setName ("Test Group Name");  // N
			m_group.setIsConfidentialInfo (false);  // N
			m_group.setIsDefault (false);
	
			m_group.setPriorityBase(MBPGroup.PRIORITYBASE_Same);
			m_group.saveEx();
	
			m_partner = new MBPartner (getCtx(), 0, getTrxName());
			m_partner.setValue ("");
			m_partner.setName ("Test Business Partner Location");
			m_partner.setName2 (null);
			m_partner.setDUNS("");
			m_partner.setFirstSale(null);
			//
			m_partner.setSO_CreditLimit (Env.ZERO);
			m_partner.setSO_CreditUsed (Env.ZERO);
			m_partner.setTotalOpenBalance (Env.ZERO);
			//      s_m_partner.setRating(null);
			//
			m_partner.setActualLifeTimeValue(Env.ZERO);
			m_partner.setPotentialLifeTimeValue(Env.ZERO);
			m_partner.setAcqusitionCost(Env.ZERO);
			m_partner.setShareOfCustomer(0);
			m_partner.setSalesVolume(0);
			m_partner.setBPGroup(m_group);
			// Reset Created, Updated to current system time ( teo_sarca )
			if(m_partner.save()) {
				bpl = new MBPartnerLocation (getCtx(), 0, getTrxName());
				bpl.setIsActive(true);
				bpl.setName("Test Business Partner Location");
				bpl.setC_BPartner_ID(m_partner.get_ID());
				bpl.setC_Location_ID(location.get_ID());
				bpl.saveEx();
			}
	 
			commit();
		} catch(Exception e) {
			fail(e.getLocalizedMessage());
		} 

    }
}
