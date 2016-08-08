/*
 * Copyright (c) 2011 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.eventb.emf.components.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import org.eclipse.gmf.tooling.runtime.part.DefaultLinkToolEntry;
import org.eclipse.gmf.tooling.runtime.part.DefaultNodeToolEntry;
import ac.soton.eventb.emf.components.diagram.providers.ComponentsElementTypes;

/**
 * @generated
 */
public class ComponentsPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createDefault1Group());
	}

	/**
	 * Creates "Default" palette tool group
	 * @generated
	 */
	private PaletteContainer createDefault1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Default1Group_title);
		paletteContainer.setId("createDefault1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Default1Group_desc);
		paletteContainer.add(createComponent1CreationTool());
		paletteContainer.add(createAsynchronous2CreationTool());
		paletteContainer.add(createSynchronous3CreationTool());
		paletteContainer.add(createProcess4CreationTool());
		paletteContainer.add(createWakeQueue5CreationTool());
		paletteContainer.add(createInputPort6CreationTool());
		paletteContainer.add(createOutputport7CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createPortWake9CreationTool());
		paletteContainer.add(createSelfWake10CreationTool());
		paletteContainer.add(createMethod11CreationTool());
		paletteContainer.add(createExternal12CreationTool());
		paletteContainer.add(createTransition13CreationTool());
		paletteContainer.add(new PaletteSeparator());
		paletteContainer.add(createConnector15CreationTool());
		paletteContainer.add(createSender16CreationTool());
		paletteContainer.add(createReceiver17CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponent1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ComponentsElementTypes.Component_2005);
		types.add(ComponentsElementTypes.Component_3012);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Component1CreationTool_title,
				Messages.Component1CreationTool_desc, types);
		entry.setId("createComponent1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.Component_2005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createAsynchronous2CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Asynchronous2CreationTool_title,
				Messages.Asynchronous2CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Statemachine_3015));
		entry.setId("createAsynchronous2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.Statemachine_3015));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSynchronous3CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Synchronous3CreationTool_title,
				Messages.Synchronous3CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Statemachine_3016));
		entry.setId("createSynchronous3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.eventb.emf.components.edit/icons/full/obj16/SynchronousStatemachine.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProcess4CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Process4CreationTool_title,
				Messages.Process4CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Statemachine_3017));
		entry.setId("createProcess4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.eventb.emf.components.edit/icons/full/obj16/ProcessStatemachine.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createWakeQueue5CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.WakeQueue5CreationTool_title,
				Messages.WakeQueue5CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.WakeQueue_3018));
		entry.setId("createWakeQueue5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.WakeQueue_3018));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInputPort6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ComponentsElementTypes.InPort_3019);
		types.add(ComponentsElementTypes.InPort_2007);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.InputPort6CreationTool_title,
				Messages.InputPort6CreationTool_desc, types);
		entry.setId("createInputPort6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.InPort_3019));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOutputport7CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ComponentsElementTypes.OutPort_3020);
		types.add(ComponentsElementTypes.OutPort_2008);
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Outputport7CreationTool_title,
				Messages.Outputport7CreationTool_desc, types);
		entry.setId("createOutputport7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.OutPort_3020));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPortWake9CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.PortWake9CreationTool_title,
				Messages.PortWake9CreationTool_desc,
				Collections.singletonList(ComponentsElementTypes.PortWake_3008));
		entry.setId("createPortWake9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.PortWake_3008));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSelfWake10CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.SelfWake10CreationTool_title,
				Messages.SelfWake10CreationTool_desc,
				Collections.singletonList(ComponentsElementTypes.SelfWake_3009));
		entry.setId("createSelfWake10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.SelfWake_3009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createMethod11CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Method11CreationTool_title,
				Messages.Method11CreationTool_desc,
				Collections.singletonList(ComponentsElementTypes.Method_3010));
		entry.setId("createMethod11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.Method_3010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createExternal12CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.External12CreationTool_title,
				Messages.External12CreationTool_desc,
				Collections.singletonList(ComponentsElementTypes.External_3013));
		entry.setId("createExternal12CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.External_3013));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransition13CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Transition13CreationTool_title,
				Messages.Transition13CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Transition_3014));
		entry.setId("createTransition13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.Transition_3014));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnector15CreationTool() {
		DefaultNodeToolEntry entry = new DefaultNodeToolEntry(
				Messages.Connector15CreationTool_title,
				Messages.Connector15CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.Connector_2006));
		entry.setId("createConnector15CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsElementTypes
				.getImageDescriptor(ComponentsElementTypes.Connector_2006));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createSender16CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Sender16CreationTool_title,
				Messages.Sender16CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.AbstractOutReceiverSource_4008));
		entry.setId("createSender16CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.eventb.emf.components.edit/icons/full/obj16/Sender.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createReceiver17CreationTool() {
		DefaultLinkToolEntry entry = new DefaultLinkToolEntry(
				Messages.Receiver17CreationTool_title,
				Messages.Receiver17CreationTool_desc,
				Collections
						.singletonList(ComponentsElementTypes.AbstractInSenderDestinations_4009));
		entry.setId("createReceiver17CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ComponentsDiagramEditorPlugin
				.findImageDescriptor("/ac.soton.eventb.emf.components.edit/icons/full/obj16/Receiver.gif")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}
}
