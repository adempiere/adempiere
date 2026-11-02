package org.eevolution.test

import org.compiere.model.{MOrg, MRequest, MStandardRequest, MStandardRequestType, Query}
import org.compiere.util.Env
import org.scalatest.featurespec.AnyFeatureSpec

/** Issue #4444: generated requests retain the definition that created them. */
class ValidateStandardRequestSourceReference extends AnyFeatureSpec with AdempiereTestCase {
  Feature("Standard request source reference") {
    Scenario("two definitions of one type persist distinct sources; an independent request has none") {
      // AdempiereTestCase rolls this transaction back in afterAll.
      val requestType = new Query(Context, "R_RequestType", "IsActive='Y'", trxName)
        .setClient_ID().first().asInstanceOf[org.compiere.model.MRequestType]
      val category = new Query(Context, "R_Category", "IsActive='Y'", trxName)
        .setClient_ID().first()
      assert(requestType != null && category != null, "test client needs a request type and category")

      val sourceType = new MStandardRequestType(Context, 0, trxName)
      sourceType.setName("Regression source " + java.util.UUID.randomUUID().toString)
      sourceType.setAD_Table_ID(MOrg.Table_ID)
      sourceType.setEventModelValidator(MStandardRequestType.EVENTMODELVALIDATOR_TableAfterChange)
      sourceType.saveEx()

      def definition(label: String): MStandardRequest = {
        val source = new MStandardRequest(Context, 0, trxName)
        source.setR_StandardRequestType_ID(sourceType.getR_StandardRequestType_ID)
        source.setR_RequestType_ID(requestType.getR_RequestType_ID)
        source.setR_Category_ID(category.get_ID)
        source.setSummary(label)
        source.setConfidentialTypeEntry(MStandardRequest.CONFIDENTIALTYPEENTRY_PublicInformation)
        source.setDueType(MStandardRequest.DUETYPE_Due)
        source.setPriority(MStandardRequest.PRIORITY_Medium)
        source.setDurationUnit(MStandardRequest.DURATIONUNIT_Day)
        source.setDuration(1)
        source.saveEx()
        source
      }

      val first = definition("First standard source")
      val second = definition("Second standard source")
      assert(first.getR_StandardRequest_ID != second.getR_StandardRequest_ID)

      val entity = new MOrg(Context, Env.getAD_Org_ID(Context), trxName)
      assert(entity.get_ID > 0)
      val generated = sourceType.createStandardRequest(entity)
      assert(generated.size() == 2)
      val persisted = generated.toArray.map { item =>
        new MRequest(Context, item.asInstanceOf[MRequest].getR_Request_ID, trxName)
      }
      assert(persisted.forall(_.get_ID > 0))
      assert(persisted.map(_.get_ValueAsInt("R_StandardRequest_ID")).toSet ==
        Set(first.getR_StandardRequest_ID, second.getR_StandardRequest_ID))
      persisted.foreach { request =>
        val source = if (request.getSummary == first.getSummary) first else second
        assert(request.getSummary == source.getSummary)
        assert(request.get_ValueAsInt("R_StandardRequest_ID") == source.getR_StandardRequest_ID)
      }

      val independent = new MRequest(Context, 0, trxName)
      independent.setR_RequestType_ID(requestType.getR_RequestType_ID)
      independent.setR_Category_ID(category.get_ID)
      independent.setSummary("Independent regression request")
      independent.setSalesRep_ID(Env.getAD_User_ID(Context))
      independent.saveEx()
      val reloaded = new MRequest(Context, independent.getR_Request_ID, trxName)
      assert(reloaded.get_ID > 0)
      assert(reloaded.get_ValueAsInt("R_StandardRequest_ID") == 0)
    }
  }
}
