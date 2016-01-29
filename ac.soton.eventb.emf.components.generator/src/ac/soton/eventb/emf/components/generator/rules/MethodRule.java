/**
 * (c) Crown owned copyright (2015) (UK Ministry of Defence)
 * This work is licensed under a Creative Commons Attribution-NonCommercial-ShareAlike 4.0
 * International License
 *  
 * This is to identify the UK Ministry of Defence as owners along with the license rights provided. The
 * URL of the CC BY NC SA 4.0 International License is 
 * http://creativecommons.org/licenses/by-nc-sa/4.0/legalcode (Accessed 02-NOV-15).
 * 
 * Contributors:
 * 	University of Southampton - Initial implementation
 *  
*/
package ac.soton.eventb.emf.components.generator.rules;

import java.util.ArrayList;
import java.util.List;

import org.eventb.emf.core.EventBElement;
import org.eventb.emf.core.machine.Event;
import org.eventb.emf.core.machine.Machine;
import org.eventb.emf.core.machine.MachinePackage;

import ac.soton.eventb.emf.components.Component;
import ac.soton.eventb.emf.components.Method;
import ac.soton.eventb.emf.components.generator.strings.Strings;
import ac.soton.eventb.emf.components.util.ComponentsUtils;
import ac.soton.eventb.emf.diagrams.generator.AbstractRule;
import ac.soton.eventb.emf.diagrams.generator.GenerationDescriptor;
import ac.soton.eventb.emf.diagrams.generator.IRule;
import ac.soton.eventb.emf.diagrams.generator.utils.Find;
import ac.soton.eventb.emf.diagrams.generator.utils.Make;

public class MethodRule extends AbstractRule implements IRule {

	private Event timerEvent = null;

	@Override
	public boolean enabled(EventBElement sourceElement) throws Exception{
		assert(sourceElement instanceof Method);
		return true;
	}

	@Override
	public boolean dependenciesOK(EventBElement sourceElement, List<GenerationDescriptor> generatedElements) throws Exception{
		Machine machine = (Machine)sourceElement.getContaining(MachinePackage.Literals.MACHINE);
		Component root = (Component) ComponentsUtils.getRootComponent(sourceElement);
		timerEvent = (Event) Find.generatedElement(generatedElements,machine,events,Strings.TE_NAME(root));
		return timerEvent!=null;
	}
	
	@Override
	public List<GenerationDescriptor> fire(EventBElement sourceElement, List<GenerationDescriptor> generatedElements) throws Exception {
		assert(enabled(sourceElement));
		if (!dependenciesOK(sourceElement,generatedElements)) throw new Exception("rule fired before dependencies available (sourceElement:"+sourceElement+", rule:"+MethodRule.class+")");
		List<GenerationDescriptor> ret = new ArrayList<GenerationDescriptor>();

		Method md = (Method) sourceElement;
		
		//guard in timer event ensures method events finished
		ret.add(Make.descriptor(timerEvent,guards,Make.guard(Strings.TE_MD_DONE_GUARD_NAME(md), Strings.TE_MD_DONE_GUARD_PRED(md)),4));

		////////		
		return ret;
	}

}
