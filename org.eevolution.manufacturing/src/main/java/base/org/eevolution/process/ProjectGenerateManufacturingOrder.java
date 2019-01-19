/**
 * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
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

package org.eevolution.process;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.Msg;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPProductBOM;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.compiere.model.X_C_ProjectPhase.PROJINVOICERULE_ProductQuantity;

/**
 * Project Generate Manufacturing Order
 * Process create a Manufacturing Order from Phase , Trask or Project Line
 */
public class ProjectGenerateManufacturingOrder extends ProjectGenerateManufacturingOrderAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        MProject project = new MProject(getCtx(), getProjectId(), get_TrxName());
        if (project == null || project.getC_Project_ID() <= 0)
            throw new AdempiereException("@C_Project@ @NotFound@");

        AtomicReference<Optional<Timestamp>> atomicDateOrdered = new AtomicReference<>();
        AtomicReference<Optional<Timestamp>> atomicDatePromised = new AtomicReference<>();
        AtomicInteger atomicBOMId = new AtomicInteger(0);
        AtomicInteger atomicWorkflowId = new AtomicInteger(0);
        AtomicReference<MProjectPhase> atomicProjectPhase = new AtomicReference<>();
        AtomicReference<MProjectTask> atomicProjectTask = new AtomicReference<>();
        AtomicReference<BigDecimal> atomicQuantity = new AtomicReference<>(BigDecimal.ZERO);
        if (getProjectLineId() > 0) {
            MProjectLine projectLine = new MProjectLine(getCtx(), getProjectLineId(), get_TrxName());
            if (projectLine.getPP_Order_ID() > 0) {
                MPPOrder order = (MPPOrder) projectLine.getPP_Order();
                throw new AdempiereException(order.getDocumentInfo() + " @AlreadyExists@ @To@ @C_ProjectLine_ID@");
            }

            MProduct product = MProduct.get(getCtx(), projectLine.getM_Product_ID());
            validProduct(product);
            atomicBOMId.set(getProductBOMId() > 0
                    ? getProductBOMId() : MPPProductBOM.getDefault(product, get_TrxName()).getPP_Product_BOM_ID());
            atomicWorkflowId.set(getWorkflowId() > 0
                    ? getWorkflowId() : MWorkflow.getWorkflowSearchKey(product));
            atomicQuantity.set(projectLine.getPlannedQty());
            projectLine.getProjectPhase().ifPresent(projectPhaseLine -> {
                Optional<Timestamp> dateOrderedOptional = Optional.ofNullable(
                        Optional.ofNullable(projectPhaseLine.getDateStartSchedule()).orElse(project.getDateStartSchedule()));
                Optional<Timestamp> datePromisedOptional = Optional.ofNullable(
                        Optional.ofNullable(projectPhaseLine.getDateFinishSchedule()).orElse(project.getDateFinishSchedule()));
                atomicProjectPhase.set((MProjectPhase) projectPhaseLine);
                atomicDateOrdered.set(Optional.ofNullable(dateOrderedOptional.orElse(
                        Optional.ofNullable(projectPhaseLine.getStartDate()).orElse(project.getDateStartSchedule()))));
                atomicDatePromised.set(Optional.ofNullable(datePromisedOptional.orElse(
                        Optional.ofNullable(projectPhaseLine.getDateDeadline()).orElse(project.getDateFinishSchedule()))));
            });
            projectLine.getProjectTask().ifPresent(projectTaskLine -> {
                MProjectPhase projectPhase = (MProjectPhase) projectTaskLine.getC_ProjectPhase();
                atomicProjectTask.set((MProjectTask) projectTaskLine);
                atomicProjectPhase.set(projectPhase);
                Optional<Timestamp> dateOrderedOptional = Optional.ofNullable(Optional.ofNullable(projectTaskLine.getDateStartSchedule())
                        .orElse(Optional.ofNullable(projectPhase.getDateStartSchedule())
                                .orElse(project.getDateStartSchedule())));
                Optional<Timestamp> datePromisedOptional = Optional.ofNullable(Optional.ofNullable(projectTaskLine.getDateFinishSchedule())
                        .orElse(Optional.ofNullable(projectPhase.getDateFinishSchedule())
                                .orElse(project.getDateFinishSchedule())));
                dateOrderedOptional.ifPresent(dateOrdered -> atomicDateOrdered.set(Optional.ofNullable(dateOrdered)));
                datePromisedOptional.ifPresent(datePromised -> atomicDatePromised.set(Optional.ofNullable(datePromised)));
            });
        } else if (getProjectTaskId() > 0) {
            MProjectTask projectTask = new MProjectTask(getCtx(), getProjectTaskId(), get_TrxName());
            if (!PROJINVOICERULE_ProductQuantity.equals(projectTask.getProjInvoiceRule())) {
                String errorMessage = "@ProjInvoiceRule@ "
                        + MRefList.getListName(getCtx(), MProjectTask.PROJINVOICERULE_AD_Reference_ID, MProjectTask.PROJINVOICERULE_ProductQuantity);
                throw new AdempiereException(errorMessage);
            }
            MProduct product = MProduct.get(getCtx(), projectTask.getM_Product_ID());
            validProduct(product);
            atomicBOMId.set(getProductBOMId() > 0 ? getProductBOMId() : MPPProductBOM.getDefault(product, get_TrxName()).getPP_Product_BOM_ID());
            atomicWorkflowId.set(getWorkflowId() > 0 ? getWorkflowId() : MWorkflow.getWorkflowSearchKey(product));
            atomicQuantity.set(projectTask.getQty());
            MProjectPhase projectPhase = (MProjectPhase) projectTask.getC_ProjectPhase();
            Optional<Timestamp> dateOrderedPhase = Optional.ofNullable(projectPhase.getDateStartSchedule());
            Optional<Timestamp> datePromisedPhase = Optional.ofNullable(projectPhase.getDateFinishSchedule());
            atomicProjectPhase.set(projectPhase);
            atomicProjectTask.set(projectTask);
            Optional<Timestamp> dateOrderedTask = Optional.ofNullable(projectTask.getDateStartSchedule());
            Optional<Timestamp> datePromisedTask = Optional.ofNullable(projectTask.getDateFinishSchedule() != null
                    ? projectTask.getDateFinishSchedule() : projectTask.getDateDeadline());
            atomicDateOrdered.set(Optional.ofNullable(dateOrderedTask.orElse(dateOrderedPhase
                    .orElse(project.getDateStartSchedule()))));
            atomicDatePromised.set(Optional.ofNullable(datePromisedTask.orElse(datePromisedPhase
                    .orElse(project.getDateFinishSchedule()))));
        } else if (getProjectPhaseId() > 0) {
            MProjectPhase projectPhase = new MProjectPhase(getCtx(), getProjectPhaseId(), get_TrxName());
            if (!PROJINVOICERULE_ProductQuantity.equals(projectPhase.getProjInvoiceRule())) {
                String errorMessage = "@ProjInvoiceRule@ "
                        + MRefList.getListName(getCtx(), MProjectPhase.PROJINVOICERULE_AD_Reference_ID, MProjectPhase.PROJINVOICERULE_ProductQuantity);
                throw new AdempiereException(errorMessage);
            }
            MProduct product = MProduct.get(getCtx(), projectPhase.getM_Product_ID());
            validProduct(product);
            atomicBOMId.set(getProductBOMId() > 0
                    ? getProductBOMId() : MPPProductBOM.getDefault(product, get_TrxName()).getPP_Product_BOM_ID());
            atomicWorkflowId.set(getWorkflowId() > 0
                    ? getWorkflowId() : MWorkflow.getWorkflowSearchKey(product));
            atomicQuantity.set(projectPhase.getQty());
            Optional<Timestamp> dateOrderedPhase = Optional.ofNullable(projectPhase.getDateStartSchedule());
            Optional<Timestamp> datePromisedPhase = Optional.ofNullable(projectPhase.getDateFinishSchedule());
            atomicProjectPhase.set(projectPhase);
            atomicDateOrdered.set(Optional.ofNullable(dateOrderedPhase.orElse(
                    Optional.ofNullable(projectPhase.getStartDate()).orElse(project.getDateStartSchedule()))));
            atomicDatePromised.set(Optional.ofNullable(datePromisedPhase.orElse(
                    Optional.ofNullable(projectPhase.getDateDeadline()).orElse(project.getDateFinishSchedule()))));
        }

        Timestamp dateOrdered = atomicDateOrdered.get()
                .orElseThrow(() -> new AdempiereException("@DateStartSchedule@ @NotFound@"));
        Timestamp datePromised = atomicDatePromised.get()
                .orElseThrow(() -> new AdempiereException("@aDateFinishSchedule@ @NotFound@"));
        MPPOrder order = createOrder(
                project,
                Optional.ofNullable(atomicProjectPhase.get()),
                Optional.ofNullable(atomicProjectTask.get()),
                atomicBOMId.get(),
                atomicWorkflowId.get(),
                dateOrdered,
                datePromised,
                atomicQuantity.get());

        addLog(Msg.parseTranslation(getCtx(), "@PP_Order_ID@ ") + order.getDocumentInfo());

        if (getProjectLineId() <= 0) {
            MProjectLine projectLine = new MProjectLine(project);
            if (order.getC_ProjectPhase_ID() > 0)
                projectLine.setC_ProjectPhase_ID(order.getC_ProjectPhase_ID());
            if (order.getC_ProjectTask_ID() > 0)
                projectLine.setC_ProjectTask_ID(order.getC_ProjectTask_ID());
            projectLine.setM_Product_ID(order.getM_Product_ID());
            projectLine.setPP_Product_BOM_ID(order.getPP_Product_BOM_ID());
            projectLine.setAD_Workflow_ID(order.getAD_Workflow_ID());
            projectLine.setPP_Order_ID(order.getPP_Order_ID());
            projectLine.setPlannedQty(order.getQtyOrdered());
            projectLine.saveEx();
            addLog(Msg.parseTranslation(getCtx(), "@C_ProjectLine_ID@ ") + projectLine.getM_Product().getName());
        }

        return "@Ok@";
    }

    private MPPOrder createOrder(
            MProject project,
            Optional<MProjectPhase> projectPhaseOptional,
            Optional<MProjectTask> projectTaskOptional,
            Integer bomId,
            Integer workflowId,
            Timestamp dateOrdered,
            Timestamp datePromised,
            BigDecimal orderdQuantity) {
        MPPOrder order = new MPPOrder(project, bomId, workflowId);
        order.setDateOrdered(dateOrdered);
        order.setDatePromised(datePromised);
        order.setDateStartSchedule(dateOrdered);
        order.setDateFinishSchedule(datePromised);
        order.setQtyOrdered(orderdQuantity);
        projectPhaseOptional.ifPresent(projectPhase -> {
            order.setC_ProjectPhase_ID(projectPhase.getC_ProjectPhase_ID());
            Optional.ofNullable(projectPhase.getPriorityRule())
                    .ifPresent(priorityRule -> order.setPriorityRule(priorityRule));
        });
        projectTaskOptional.ifPresent(projectTask -> {
            order.setC_ProjectTask_ID(projectTask.getC_ProjectTask_ID());
            Optional.ofNullable(projectTask.getPriorityRule())
                    .ifPresent(priorityRule -> order.setPriorityRule(priorityRule));
        });
        order.saveEx();
        return order;
    }

    private void validProduct(MProduct product) {
        if (product == null)
            throw new AdempiereException("@M_Product_ID@ @NotFound@");
        if (product != null && !product.isBOM())
            throw new AdempiereException("@M_Product_ID@ @IsBOM@ @NotFound@");

    }

}