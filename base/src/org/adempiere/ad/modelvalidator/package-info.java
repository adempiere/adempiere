 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/

/**
 * API for implementing and controlling the lifecycle of a model validator.
 * 
 * At this moment, the package contains only the API related to annotated model validators.
 * An annotated model validator can be any class which respects following format and is annotated properly.
 * e.g.
 * <code>
 * @Validator(I_C_Order.class)
 * public class C_Order
 * {
 * 	@DocValidate(timings = { ModelValidator.TIMING_BEFORE_PREPARE })
 *  public void doSomethingBeforePrepare(org.compiere.model.I_C_Order order)
 *  {
 *  ....
 *  }
 *  
 *  @ModelChange(timings = ModelValidator.TYPE_BEFORE_DELETE)
 *  public void doSomethingBeforeDelete(org.compiere.model.I_C_Order order)
 *  {
 *  ....
 *  }
 * }
 * </code>
 * 
 * To register this model validator, all you need to do is:
 * <code>
 * {@link org.compiere.model.ModelValidationEngine}.get().addModelValidator(new C_Order());
 * </code>
 * Internally, addModelValidator method will call check if the given object is an annotated model validator and if yes, it will use {@link org.adempiere.ad.modelvalidator.AnnotatedModelValidatorFactory#createModelValidator(Object)} to wrap it and create an {@link org.compiere.model.ModelValidator} implementation.
 * 
 * For more infos, please check annotations from org.adempiere.ad.modelvalidator.annotation package.
 * 
 * Please note that the old way of defining and using model validators is still fully supported.
 * 
 * @task http://dewiki908/mediawiki/index.php/Me00_02505:_Uncouple_Annotated_ModelValidators_from_API_implementations_%282012022410000053%29
 * 
 * @see org.adempiere.ad.modelvalidator.annotations.Validator
 * @see org.adempiere.ad.modelvalidator.annotations.ModelChange
 * @see org.adempiere.ad.modelvalidator.annotations.ModelChanges
 * @see org.adempiere.ad.modelvalidator.annotations.DocValidate
 * @see org.adempiere.ad.modelvalidator.annotations.DocValidates
 */
package org.adempiere.ad.modelvalidator;

