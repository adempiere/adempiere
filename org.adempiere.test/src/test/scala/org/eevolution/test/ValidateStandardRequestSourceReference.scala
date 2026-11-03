package org.eevolution.test

import org.adempiere.core.domains.models.{I_AD_Org, X_R_StandardRequest, X_R_StandardRequestType}
import org.compiere.model.{MOrg, MRequest, MRequestType, MStandardRequest, MStandardRequestType, PO, Query}
import org.compiere.util.Env
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import scala.jdk.CollectionConverters.*

/** Issue #4444: generated requests retain the definition that created them. */
class ValidateStandardRequestSourceReference extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {
  Feature("Standard request source reference") {
    Scenario("definitions sharing a summary persist distinct sources; an independent request has none (#4444)") {
      Given("a request type and category in the test client")
      // AdempiereTestCase rolls this transaction back in afterAll.
      val requestType: MRequestType = new Query(Context, "R_RequestType", "IsActive='Y'", trxName)
        .setClient_ID().first()
      val category: PO = new Query(Context, "R_Category", "IsActive='Y'", trxName)
        .setClient_ID().first()
      assert(Option(requestType).isDefined && Option(category).isDefined,
        "test client needs a request type and category")

      val sourceType = new MStandardRequestType(Context, 0, trxName)
      sourceType.setName("Regression source " + java.util.UUID.randomUUID().toString)
      sourceType.setAD_Table_ID(I_AD_Org.Table_ID)
      sourceType.setEventModelValidator(X_R_StandardRequestType.EVENTMODELVALIDATOR_TableAfterChange)
      sourceType.saveEx()

      def definition(summary: String, subject: String): MStandardRequest = {
        val source = new MStandardRequest(Context, 0, trxName)
        source.setR_StandardRequestType_ID(sourceType.getR_StandardRequestType_ID)
        source.setR_RequestType_ID(requestType.getR_RequestType_ID)
        source.setR_Category_ID(category.get_ID)
        source.setSummary(summary)
        source.setSubject(subject)
        source.setConfidentialTypeEntry(X_R_StandardRequest.CONFIDENTIALTYPEENTRY_PublicInformation)
        source.setDueType(X_R_StandardRequest.DUETYPE_Due)
        source.setPriority(X_R_StandardRequest.PRIORITY_Medium)
        source.setDurationUnit(X_R_StandardRequest.DURATIONUNIT_Day)
        source.setDuration(1)
        source.saveEx()
        source
      }

      When("three definitions, including two with the same summary, generate requests")
      val first = definition("Shared standard source", "First source subject")
      val second = definition("Second standard source", "Second source subject")
      val third = definition("Shared standard source", "Third source subject")
      val definitions = List(first, second, third)
      assert(definitions.map(_.getR_StandardRequest_ID).distinct.size == 3)

      val entity = new MOrg(Context, Env.getAD_Org_ID(Context), trxName)
      assert(entity.get_ID > 0)
      val generated = sourceType.createStandardRequest(entity)
      assert(generated.size() == 3)
      val persisted = generated.asScala.map { generatedRequest =>
        new MRequest(Context, generatedRequest.getR_Request_ID, trxName)
      }.toList
      Then("each persisted request retains its own source definition")
      assert(persisted.forall(_.get_ID > 0))
      assert(persisted.map(_.get_ValueAsInt("R_StandardRequest_ID")).toSet ==
        definitions.map(_.getR_StandardRequest_ID).toSet)
      val definitionsById = definitions.map(source => source.getR_StandardRequest_ID -> source).toMap
      persisted.foreach { request =>
        val sourceId = request.get_ValueAsInt("R_StandardRequest_ID")
        assert(definitionsById.get(sourceId).exists { source =>
          request.getSummary == source.getSummary && request.getSubject == source.getSubject
        })
      }

      And("a separately created request has no source definition")
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
