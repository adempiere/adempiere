/**
  * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * You should have received a copy of the GNU General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

import org.compiere.model._
import org.eevolution.model.{MProjectCategory, MProjectClass, MProjectGroup, X_C_ProjectCategory}
import org.compiere.util.{Env}
import org.eevolution.services.ProductService
import org.eevolution.test.AdempiereTestCase
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.collection.JavaConversions._


/**
  * Test to validate process of  Sales in POS
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
class ProjectTestSuite extends FeatureSpec  with AdempiereTestCase with GivenWhenThen with ProductService{
  feature("Create a Project type ") {
    info("Create Project Type A and Copy to Project Type B")

    scenario("Create Project Type A") {

      val projectTypeNameA = "Project Type A"
      val projectTypeNameB = "Project Type B"
      val projectTypeA =  new MProjectType(Context , 0 , Transaction.getTrxName)
      projectTypeA.setName(projectTypeNameA)
      projectTypeA.setDescription(s"Description [$projectTypeNameA]")
      projectTypeA.setHelp(s"Help [$projectTypeNameA]")
      projectTypeA.saveEx()

      Given(s"a Project Type was created")
      When("is saved")
      Then(s"The Project Type [$projectTypeNameA]")
      assert(projectTypeA.getName == projectTypeNameA)

      val projectTypePhaseNameA = s"Phase A $projectTypeNameA"
      val Azalea = { getProductByValue("Azalea Bush") }
      val projectTypePhaseA = new MProjectTypePhase(Context , 0 , Transaction.getTrxName)
      projectTypePhaseA.setC_ProjectType_ID(projectTypeA.get_ID())
      projectTypePhaseA.setSeqNo(10)
      projectTypePhaseA.setName(projectTypePhaseNameA)
      projectTypePhaseA.setM_Product_ID(Azalea.get_ID())
      projectTypePhaseA.setDescription(s"Descripttion [$projectTypeNameA]")
      projectTypePhaseA.setHelp(s"Help [$projectTypeNameA]")
      projectTypePhaseA.saveEx()

      And(s"The Standard Phase Type [$projectTypePhaseNameA]")
      assert(projectTypePhaseA.getName == projectTypePhaseNameA)

      val projectTypeTaskNameA = "Task A"
      val Elm = { getProductByValue("Elm") }
      val projectTypeTaskA = new MProjectTypeTask(Context , 0 , Transaction.getTrxName)
      projectTypeTaskA.setC_Phase_ID(projectTypePhaseA.get_ID())
      projectTypeTaskA.setSeqNo(10)
      projectTypeTaskA.setM_Product_ID(Elm.get_ID())
      projectTypeTaskA.setName(projectTypeTaskNameA)
      projectTypeTaskA.setDescription(s"Description [$projectTypeTaskNameA  $projectTypeNameA]")
      projectTypeTaskA.setHelp(s"Help  $projectTypeTaskNameA [$projectTypeNameA]")
      projectTypeTaskA.saveEx()

      And(s"The Standard Task Type [$projectTypeTaskNameA]")
      assert(projectTypeTaskA.getName == projectTypeTaskNameA)

      val projectTypeTaskNameB = "Task B"
      val Hoe = { getProductByValue("Hoe") }
      val projectTypeTaskB = new MProjectTypeTask(Context , 0 , Transaction.getTrxName)
      projectTypeTaskB.setC_Phase_ID(projectTypePhaseA.get_ID())
      projectTypeTaskB.setSeqNo(10)
      projectTypeTaskB.setM_Product_ID(Hoe.get_ID())
      projectTypeTaskB.setName(projectTypeTaskNameB)
      projectTypeTaskB.setDescription(s"Description [$projectTypeTaskNameB $projectTypeNameA]")
      projectTypeTaskB.setHelp(s"Help [$projectTypeTaskNameB $projectTypeNameA]")
      projectTypeTaskB.saveEx()

      And(s"The Standard Task Type [$projectTypeTaskNameB]")
      assert(projectTypeTaskB.getName == projectTypeTaskNameB)


      val projectTypePhaseNameB = s"Phase B $projectTypeNameA"
      val Oak = { getProductByValue("Oak") }
      val projectTypePhaseB = new MProjectTypePhase(Context , 0 , Transaction.getTrxName)
      projectTypePhaseB.setC_ProjectType_ID(projectTypeA.get_ID())
      projectTypePhaseB.setSeqNo(10)
      projectTypePhaseB.setM_Product_ID(Oak.get_ID())
      projectTypePhaseB.setName(projectTypePhaseNameB)
      projectTypePhaseB.setDescription(s"Descripttion [$projectTypePhaseNameB $projectTypeNameA]")
      projectTypePhaseB.setHelp(s"Help [$projectTypePhaseNameB $projectTypeNameA]")
      projectTypePhaseB.saveEx()

      And(s"The Standard Phase Type [$projectTypePhaseNameB]")
      assert(projectTypePhaseB.getName == projectTypePhaseNameB)

      val projectTypeTaskNameAA = "Task AA"
      val projectTypeTaskAA = new MProjectTypeTask(Context , 0 , Transaction.getTrxName)
      projectTypeTaskAA.setC_Phase_ID(projectTypePhaseB.get_ID())
      projectTypeTaskAA.setSeqNo(10)
      projectTypeTaskAA.setName(projectTypeTaskNameAA)
      projectTypeTaskAA.setM_Product_ID(Elm.get_ID())
      projectTypeTaskAA.setDescription(s"Description [$projectTypeTaskNameAA $projectTypeNameA]")
      projectTypeTaskAA.setHelp(s"Help $projectTypeTaskNameAA $projectTypeNameA")
      projectTypeTaskAA.saveEx()

      And(s"The Standard Task Type [$projectTypeTaskNameAA]")
      assert(projectTypeTaskAA.getName == projectTypeTaskNameAA)

      val projectTypeTaskNameAB = "Task AB"
      val projectTypeTaskAB = new MProjectTypeTask(Context , 0 , Transaction.getTrxName)
      projectTypeTaskAB.setC_Phase_ID(projectTypePhaseB.get_ID())
      projectTypeTaskAB.setSeqNo(10)
      projectTypeTaskAB.setName(projectTypeTaskNameAB)
      projectTypeTaskAB.setM_Product_ID(Hoe.get_ID())
      projectTypeTaskAB.setDescription(s"Description [$projectTypeTaskNameAB $projectTypeNameA]")
      projectTypeTaskAB.setHelp(s"Help [$projectTypeTaskNameAB $projectTypeNameA]")
      projectTypeTaskAB.saveEx()

      And(s"The Standard Task Type $projectTypeTaskNameAB]")
      assert(projectTypeTaskAB.getName == projectTypeTaskNameAB)

      val projectTypeB =  new MProjectType(Context , 0 , Transaction.getTrxName)
      projectTypeB.setName(projectTypeNameB)
      projectTypeB.setDescription(s"Description [$projectTypeNameB]")
      projectTypeB.setHelp(s"Help $projectTypeNameB")
      projectTypeB.saveEx()

      And(s"The Project Type [$projectTypeNameB]")
      assert(projectTypeB.getName == projectTypeNameB)
      val projectSetType = ProcessBuilder.create(Context)
        .process(org.eevolution.process.CopyFromProjectTypeAbstract.getProcessValue)
        .withTitle(org.eevolution.process.CopyFromProjectTypeAbstract.getProcessName)
        .withRecordId(I_C_ProjectType.Table_ID, projectTypeB.get_ID)
        .withParameter(I_C_ProjectType.COLUMNNAME_C_ProjectType_ID, projectTypeA.get_ID())
        .withoutTransactionClose()
        .execute(trxName)

      var taskListA = Set[MProjectTypeTask]()
      var taskListB = Set[MProjectTypeTask]()
      val phasesA = projectTypeA.getPhases
      val phasesB =  projectTypeB.getPhases
      When(s"copying the Project Type from [${projectTypeNameA}] with ${phasesA.size} phases")
      Then(s"Project Type [${projectTypeNameB}] have ${phasesB.size} phases too")
      assert(phasesA.size == phasesB.size)
      phasesA.foreach{
        phase =>
          val tasksA = phase.getTasks
          tasksA.foreach{task =>
            taskListA = taskListA.+(task)
          }
      }

      phasesB.foreach{phase =>
        val tasksB = phase.getTasks
        tasksB.foreach{task =>
          taskListB= taskListB.+(task)
        }
      }

      When(s"copying the Standard Task from Project Type[${projectTypeNameA}] with ${taskListA.size} tasks ")
      Then(s"Project Type [${projectTypeNameB}] have ${taskListB.size} tasks too")
      assert(taskListA.size == taskListB.size)

      val projectCategorynName = "Category 1"
      val projectCategory = new MProjectCategory(Context , 0 , Transaction.getTrxName)
      projectCategory.setValue(projectCategorynName)
      projectCategory.setName(projectCategorynName)
      projectCategory.setDescription(s"Description $projectCategorynName")
      projectCategory.setProjectCategory(X_C_ProjectCategory.PROJECTCATEGORY_General)
      projectCategory.saveEx()
      When(s"the Project Category is created")
      Then(s"the Project Category have name of [$projectCategorynName]")
      assert(projectCategory.getName == projectCategorynName)

      val projectClassName = "Class 1"
      val projectClass = new MProjectClass(Context , 0 , Transaction.getTrxName)
      projectClass.setValue(projectClassName)
      projectClass.setName(projectClassName)
      projectClass.setDescription(s"Description $projectClassName")
      projectClass.saveEx()
      When(s"the Project Class is created")
      Then(s"the Project Class have name of [$projectClassName]")
      assert(projectClass.getName == projectClassName)

      val projectGroupName = "Group 1"
      val projectGroup = new MProjectGroup(Context , 0 , Transaction.getTrxName)
      projectGroup.setValue(projectGroupName)
      projectGroup.setName(projectGroupName)
      projectGroup.setDescription(s"Description [$projectGroupName]")
      projectGroup.saveEx()
      When(s"a Project Group is created")
      Then(s"a Project Group have name of [$projectGroupName]")
      assert(projectGroup.getName == projectGroupName)

      val currencyId = Env.getContextAsInt(Context, "$C_Currency_ID")
      val projectName = "Project for Test"
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val SalePriceList = { MPriceList.getDefault(Context, true) }
      val project = new MProject(Context , 0 , Transaction.getTrxName)
      project.setValue(projectName)
      project.setName(projectName)
      project.setC_BPartner_ID(JoeBlock.getC_BPartner_ID)
      project.setC_ProjectCategory_ID(projectCategory.getC_ProjectCategory_ID)
      project.setC_ProjectGroup_ID(projectGroup.get_ID())
      project.setC_ProjectClass_ID(projectClass.getC_ProjectClass_ID)
      project.setM_PriceList_Version_ID(SalePriceList.getPriceListVersion(Today).getM_Pricelist_Version_Base_ID)
      project.setM_Warehouse_ID(Warehouse.get_ID())
      project.setC_BPartner_Location_ID(JoeBlock.getPrimaryC_BPartner_Location.get_ID())
      project.setSalesRep_ID(Env.getAD_User_ID(Env.getCtx))
      project.setC_PaymentTerm_ID(MPaymentTerm.getPaymentTermByDefault(Context, Transaction.getTrxName).get_ID())
      project.setC_Currency_ID(currencyId)
      project.saveEx()
      When("Project is created")
      Then(s"Project have name of [$projectName]")
      assert(project.getName == projectName)
      When(s"define Project Type [${projectTypeA.getName}]")
      val pryectSetType = dsl.ProcessBuilder.create(Context)
        .process(org.compiere.process.ProjectSetTypeAbstract.getProcessValue)
        .withTitle(org.compiere.process.ProjectSetTypeAbstract.getProcessName)
        .withRecordId(I_C_Project.Table_ID, project.get_ID())
        .withParameter(I_C_Project.COLUMNNAME_C_ProjectType_ID, projectTypeA.get_ID())
        .withoutTransactionClose()
        .execute(trxName)

      val projectPhases = project.getPhases
      var projectTaskList = Set[MProjectTask]()
      var orderList = Set[MOrder]()
      Then(s"${phasesA.size} phases should be creted so that the Project Type Standard Phases have ${projectPhases.size}")
      assert(phasesA.size == projectPhases.size)
      projectPhases.foreach { projectPhase =>
        projectPhase.getTasks.forEach { projectTask =>
          projectTaskList = projectTaskList.+(projectTask)
        }
      }

      And(s"${taskListA.size} tasks should be created so that the Project Standard Tasks have ${projectTaskList.size}")
      assert(taskListA.size == projectTaskList.size)

      projectPhases.forEach{ phase =>
      val generateOrder = dsl.ProcessBuilder.create(Context)
        .process(org.compiere.process.ProjectPhaseGenOrderAbstract.getProcessValue)
        .withTitle(org.compiere.process.ProjectPhaseGenOrderAbstract.getProcessName)
        .withRecordId(I_C_ProjectPhase.Table_ID, phase.get_ID())
        .withoutTransactionClose()
        .execute(trxName)
      }

      projectPhases.forEach { phase =>
        orderList = orderList.++(phase.getOrders)
      }
      When(s"process generate order for Phase is executed  for Project [${projectTypeA.getName}]")
      Then(s"${orderList.size} orders should be generate because this project have ${projectPhases.size()} phases with product defined")
      assert(orderList.size == projectPhases.size)
    }
  }
}
