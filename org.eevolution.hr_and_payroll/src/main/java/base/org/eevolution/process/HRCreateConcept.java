/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
//package org.eevolution.process;
package org.eevolution.process;

import org.compiere.model.Query;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRPayrollConcept;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *	Create Concept of current Payroll
 *	
 *  @author Oscar Gómez Islas
 *  @version $Id: HRCreateConcept.java,v 1.0 2005/10/24 04:58:38 ogomezi Exp $
 *  
 *  @author Cristina Ghita, www.arhipac.ro
 */
public class HRCreateConcept extends HRCreateConceptAbstract
{
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		super.prepare();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		int count = 0;
		List<MHRConcept> concepts = (List<MHRConcept>) getInstancesForSelection(get_TrxName());
		if (getRecord_ID() > 0 && getSelectionKeys().size() > 0 && getTableSelectionId() == MHRConcept.Table_ID)
			count = createPayrollConcept(concepts, getRecord_ID());
		else if (getRecord_ID() == 0 && getSelectionKeys().size() > 0)
			count = updatingConceptProperties(concepts, getRecord_ID());

		return "@Created@/@Updated@ #" + count;
	}	//	doIt

	/**
	 * Create Payroll Concept
	 * @param concepts
	 * @param payrollId
	 * @return
	 */
	private int createPayrollConcept(List<MHRConcept> concepts, int payrollId)
	{
		AtomicInteger count = new AtomicInteger(0);
		concepts.stream()
				.filter(concept -> !existsPayrollConcept(concept.getHR_Concept_ID(), payrollId))
				.forEach(concept -> {
			MHRPayrollConcept payrollConcept =  new MHRPayrollConcept(concept, payrollId , get_TrxName());
			payrollConcept.setIsPrinted(concept.isPrinted());
			payrollConcept.setSeqNo(concept.getSeqNo());
			payrollConcept.setName(concept.getName());
			payrollConcept.saveEx();
			addLog("@SeqNo@" + payrollConcept.getSeqNo() + " @HR_PayrollConcept_ID@ : " + payrollConcept.getName());
			count.updateAndGet(no -> no + 1);
		});
		return count.get();
	}

	/**
	 * Update Concept Properties
	 * @param concepts
	 * @return
	 */
	private int updatingConceptProperties(List<MHRConcept> concepts, int payrollId) {
		AtomicInteger count = new AtomicInteger(0);
		concepts.stream().filter(concept -> concept != null)
				.forEach(concept -> {
					//Update Concept properties
					Optional.ofNullable(getSelectionAsString(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_Name)).ifPresent(name -> concept.setName(name));
					Optional.ofNullable(getSelectionAsString(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_Description)).ifPresent(description -> concept.setDescription(description));
					Optional.ofNullable(getSelectionAsInt(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_HR_Concept_Category_ID)).ifPresent(categoryId -> concept.setHR_Concept_Category_ID(categoryId));
					Optional.ofNullable(getSelectionAsInt(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_HR_Concept_Type_ID)).ifPresent(typeId -> concept.setHR_Concept_Type_ID(typeId));
					Optional.ofNullable(getSelectionAsInt(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_SeqNo)).ifPresent(seqNo -> concept.setSeqNo(seqNo));
					Optional.ofNullable(getSelectionAsString(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_Type)).ifPresent(type -> concept.setType(type));
					Optional.ofNullable(getSelectionAsString(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_ColumnType)).ifPresent(columnType -> concept.setColumnType(columnType));
					Optional.ofNullable(getSelectionAsString(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_AccountSign)).ifPresent(accountSign -> concept.setAccountSign(accountSign));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsActive)).ifPresent(isActive -> concept.setIsActive(isActive));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsEmployee)).ifPresent(isEmployee -> concept.setIsEmployee(isEmployee));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsManual)).ifPresent(isManual -> concept.setIsManual(isManual));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsPaid)).ifPresent(isPaid -> concept.setIsPaid(isPaid));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsPrepayment)).ifPresent(isPrepayment -> concept.setIsPrepayment(isPrepayment));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsInvoiced)).ifPresent(isInvoiced -> concept.setIsInvoiced(isInvoiced));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsSaveInHistoric)).ifPresent(isSaveInHistoric -> concept.setIsSaveInHistoric(isSaveInHistoric));
					Optional.ofNullable(getSelectionAsBoolean(concept.get_ID(), getPrefixAliasForTableSelection() + MHRConcept.COLUMNNAME_IsPrinted)).ifPresent(isPrint -> concept.setIsPrinted(isPrint));
					if (concept.is_Changed()) {
						count.updateAndGet(no -> no + 1);
						concept.saveEx();
						addLog("@Updated@ @HR_Concept_ID@ : " + concept.getName());
					}
				});
		return count.get();
	}

	/**
	 * Exists Payroll Concept
	 * @param conceptId
	 * @param payrollId
	 * @return
	 */
	private boolean existsPayrollConcept(int conceptId, int payrollId)
	{
		final String whereClause = "HR_Payroll_ID=? AND HR_Concept_ID=?";
		return new Query(getCtx(), MHRPayrollConcept.Table_Name, whereClause, get_TrxName())
			.setParameters(new Object[]{payrollId, conceptId})
			.match();
	}
}	//	Create Concept of the current Payroll
