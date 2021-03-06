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
package ac.soton.eventb.emf.components.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import ac.soton.eventb.emf.components.diagram.part.ComponentsVisualIDRegistry;

/**
 * @generated
 */
public class ComponentsNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7009;

	/**
	 * @generated
	 */
	private static final int SHORTCUTS_CATEGORY = 7008;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof ComponentsNavigatorItem) {
			ComponentsNavigatorItem item = (ComponentsNavigatorItem) element;
			if (item.getView().getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
				return SHORTCUTS_CATEGORY;
			}
			return ComponentsVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
