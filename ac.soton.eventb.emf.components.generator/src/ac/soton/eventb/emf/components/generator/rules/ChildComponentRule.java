/*******************************************************************************
 * (c) Crown owned copyright (2017) (UK Ministry of Defence)
 *
 * All rights reserved. This program and the accompanying materials are 
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      University of Southampton - Initial API and implementation
 *******************************************************************************/
package ac.soton.eventb.emf.components.generator.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;

import ac.soton.eventb.decomposition.DecompositionPackage;
import ac.soton.eventb.emf.components.Component;
import ac.soton.eventb.emf.components.util.ComponentsUtils;
import ac.soton.emf.translator.eventb.rules.AbstractEventBGeneratorRule;
import ac.soton.emf.translator.TranslationDescriptor;
import ac.soton.emf.translator.configuration.IRule;
import ac.soton.emf.translator.eventb.utils.Make;
import ac.soton.eventb.statemachines.State;
import ac.soton.eventb.statemachines.Statemachine;
import ac.soton.eventb.statemachines.StatemachinesPackage;
import ac.soton.eventb.statemachines.TranslationKind;

public class ChildComponentRule extends AbstractEventBGeneratorRule implements IRule {

	protected static final EReference allocatedVariables = DecompositionPackage.Literals.ABSTRACT_REGION__ALLOCATED_VARIABLES;
	protected static final EReference allocatedExtensions = DecompositionPackage.Literals.ABSTRACT_REGION__ALLOCATED_EXTENSIONS;
	protected static final EAttribute machineName = DecompositionPackage.Literals.ABSTRACT_REGION__MACHINE_NAME;

	/**
	 * This rule updates the region details of a component
	 * Currently it just sets the machine name and adds the component to the Allocated Extensions
	 * In future it could set the project name and/or context name
	 * Allocated variables are done as the variables are created by other rules
	 *	
	 */
	
	@Override
	public boolean enabled(EObject sourceElement) throws Exception{
		assert(sourceElement instanceof Component);
		return sourceElement != ComponentsUtils.getRootComponent(sourceElement);
	}
	
	@Override
	public List<TranslationDescriptor> fire(EObject sourceElement, List<TranslationDescriptor> generatedElements) throws Exception {
		assert(enabled(sourceElement));
		Component cp = (Component) sourceElement;
		Machine machine = (Machine)cp.getContaining(MachinePackage.Literals.MACHINE);
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		
		//allocate variables for any contained statemachines (as these are not allocated by the state-machine generator rules
		for (EObject eo : cp.eContents()){
			if (eo instanceof Statemachine){
				Statemachine rootsm = (Statemachine)eo;
				TranslationKind tk = rootsm.getTranslation();
				ret.addAll(getStatemachineVariables(cp, machine, rootsm, tk));
			}
		}
		return ret;
	}

	/**
	 * recursively searches the containment tree from the given eObject looking for statemachines
	 * for each statemachine creates a make descriptor to add the statemachines relevant generated variables (as a proxy reference)
	 * to the given components allocatedVariables collection. The translation kind is passed as a parameter to enable recursion.
	 * 
	 * @param cp
	 * @param machine
	 * @param eObject
	 * @param tk
	 * @return
	 */
	private Collection<? extends TranslationDescriptor> getStatemachineVariables(Component cp, Machine machine, EObject eObject, TranslationKind tk) {
		List<TranslationDescriptor> ret = new ArrayList<TranslationDescriptor>();
		if (eObject instanceof Statemachine){
			if (tk == TranslationKind.SINGLEVAR){
				ret.add(Make.descriptor(cp, allocatedVariables, Make.variableProxyReference(machine, ((Statemachine)eObject).getName()) , -10));
			}else{
				for (EObject state : ((Statemachine)eObject).getAllContained(StatemachinesPackage.Literals.STATE, true)){
					if (state instanceof State){
						ret.add(Make.descriptor(cp, allocatedVariables, Make.variableProxyReference(machine, ((State)state).getName()) , -10));							
					}
				}
			}
		}
		for (EObject eo : eObject.eContents()){
			ret.addAll(getStatemachineVariables(cp, machine, eo, tk));
		}
		return ret;
	}
	
}
