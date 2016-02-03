/*
 * Copyright (c) 2011 University of Southampton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package ac.soton.eventb.emf.components.diagram.part;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.PropertiesBrowserPage;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eventb.emf.core.CorePackage;
import org.eventb.emf.core.EventBElement;
import org.eventb.emf.core.EventBNamedCommentedComponentElement;
import org.eventb.emf.core.EventBObject;

import ac.soton.eventb.emf.components.diagram.navigator.ComponentsNavigatorItem;
import ac.soton.eventb.emf.diagrams.refactor.Recorder;

/**
 * @generated
 */
public class ComponentsDiagramEditor extends DiagramDocumentEditor implements
		IGotoMarker {

	/**
	 * @generated
	 */
	public static final String ID = "ac.soton.eventb.emf.components.diagram.part.ComponentsDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "ac.soton.eventb.emf.components.diagram.ui.diagramContext"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public ComponentsDiagramEditor() {
		super(true);
	}

	/**
	 * @generated
	 */
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	 * @generated
	 */
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new ComponentsPaletteFactory().fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	protected PreferencesHint getPreferencesHint() {
		return ComponentsDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	public String getContributorId() {
		return ComponentsDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class type) {
		if (type == IShowInTargetList.class) {
			return new IShowInTargetList() {
				public String[] getShowInTargetIds() {
					return new String[] { ProjectExplorer.VIEW_ID };
				}
			};
		}
		return super.getAdapter(type);
	}

	/**
	 * @generated
	 */
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			return ComponentsDiagramEditorPlugin.getInstance()
					.getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated NOT
	 * was getting npe from getDocumentProvider() returning null
	 */
	public TransactionalEditingDomain getEditingDomain() {
		IDocument document = (getEditorInput() != null && getDocumentProvider() != null) ? getDocumentProvider()
				.getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	/**
	 * @generated
	 */
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			setDocumentProvider(ComponentsDiagramEditorPlugin.getInstance()
					.getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	 * @generated
	 */
	public void gotoMarker(IMarker marker) {
		MarkerNavigationService.getInstance().gotoMarker(this, marker);
	}

	/**
	 * @generated
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * @generated
	 */
	public void doSaveAs() {
		performSaveAs(new NullProgressMonitor());
	}

	/**
	 * @generated
	 */
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
				.getFile() : null;
		if (original != null) {
			dialog.setOriginalFile(original);
		}
		dialog.create();
		IDocumentProvider provider = getDocumentProvider();
		if (provider == null) {
			// editor has been programmatically closed while the dialog was open
			return;
		}
		if (provider.isDeleted(input) && original != null) {
			String message = NLS.bind(
					Messages.ComponentsDiagramEditor_SavingDeletedFile,
					original.getName());
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
		}
		if (dialog.open() == Window.CANCEL) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IPath filePath = dialog.getResult();
		if (filePath == null) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(filePath);
		final IEditorInput newInput = new FileEditorInput(file);
		// Check if the editor is already open
		IEditorMatchingStrategy matchingStrategy = getEditorDescriptor()
				.getEditorMatchingStrategy();
		IEditorReference[] editorRefs = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < editorRefs.length; i++) {
			if (matchingStrategy.matches(editorRefs[i], newInput)) {
				MessageDialog.openWarning(shell,
						Messages.ComponentsDiagramEditor_SaveAsErrorTitle,
						Messages.ComponentsDiagramEditor_SaveAsErrorMessage);
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);
			getDocumentProvider(newInput).saveDocument(progressMonitor,
					newInput,
					getDocumentProvider().getDocument(getEditorInput()), true);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog.openError(shell,
						Messages.ComponentsDiagramEditor_SaveErrorTitle,
						Messages.ComponentsDiagramEditor_SaveErrorMessage,
						x.getStatus());
			}
		} finally {
			provider.changed(newInput);
			if (success) {
				setInput(newInput);
			}
		}
		if (progressMonitor != null) {
			progressMonitor.setCanceled(!success);
		}
	}

	/**
	 * @generated
	 */
	public ShowInContext getShowInContext() {
		return new ShowInContext(getEditorInput(), getNavigatorSelection());
	}

	/**
	 * @generated
	 */
	private ISelection getNavigatorSelection() {
		IDiagramDocument document = getDiagramDocument();
		if (document == null) {
			return StructuredSelection.EMPTY;
		}
		Diagram diagram = document.getDiagram();
		if (diagram == null || diagram.eResource() == null) {
			return StructuredSelection.EMPTY;
		}
		IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
		if (file != null) {
			ComponentsNavigatorItem item = new ComponentsNavigatorItem(diagram,
					file, false);
			return new StructuredSelection(item);
		}
		return StructuredSelection.EMPTY;
	}

	/**
	 * @generated
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(
				this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
				provider, getDiagramGraphicalViewer());
	}

	/**
	 * @generated
	 */
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(),
						LocalSelectionTransfer.getTransfer()) {

					protected Object getJavaObject(TransferData data) {
						return LocalSelectionTransfer.getTransfer()
								.nativeToJava(data);
					}

				});
		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(),
						LocalTransfer.getInstance()) {

					protected Object getJavaObject(TransferData data) {
						return LocalTransfer.getInstance().nativeToJava(data);
					}

				});
	}

	/**
	 * @generated
	 */
	private abstract class DropTargetListener extends DiagramDropTargetListener {

		/**
		 * @generated
		 */
		public DropTargetListener(EditPartViewer viewer, Transfer xfer) {
			super(viewer, xfer);
		}

		/**
		 * @generated
		 */
		protected List getObjectsBeingDropped() {
			TransferData data = getCurrentEvent().currentDataType;
			HashSet<URI> uris = new HashSet<URI>();

			Object transferedObject = getJavaObject(data);
			if (transferedObject instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) transferedObject;
				for (Iterator<?> it = selection.iterator(); it.hasNext();) {
					Object nextSelectedObject = it.next();
					if (nextSelectedObject instanceof ComponentsNavigatorItem) {
						View view = ((ComponentsNavigatorItem) nextSelectedObject)
								.getView();
						nextSelectedObject = view.getElement();
					} else if (nextSelectedObject instanceof IAdaptable) {
						IAdaptable adaptable = (IAdaptable) nextSelectedObject;
						nextSelectedObject = adaptable
								.getAdapter(EObject.class);
					}

					if (nextSelectedObject instanceof EObject) {
						EObject modelElement = (EObject) nextSelectedObject;
						uris.add(EcoreUtil.getURI(modelElement));
					}
				}
			}

			ArrayList<EObject> result = new ArrayList<EObject>(uris.size());
			for (URI nextURI : uris) {
				EObject modelObject = getEditingDomain().getResourceSet()
						.getEObject(nextURI, true);
				result.add(modelObject);
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected abstract Object getJavaObject(TransferData data);

	}

	////////////////////////////////////////////////////////////////////////////////
	/// The following methods have been overridden to 
	// a) save on de-activation to avoid synch problems between several editors
	// b) provide change recording
	////////////////////////////////////////////////////////////////////////////////


	/**
	 * This listens for when the outline becomes active 
	 * <!-- begin-user-doc -->
	 * It also listens for possible deactivation of the editor (i.e. deactivation of the
	 * content outline view, property sheets or the editor itself) and if the subsequent 
	 * activation confirms that none of these associated views are being activated,
	 *  and the editor is dirty, all changes are automatically saved.
	 * This prevents editor conflicts if the same resource is edited with another editor.
	 *  <!-- end-user-doc -->
	 * 
	 * @custom
	 */
	protected IPartListener deactivationPartListener = new IPartListener() {

		private boolean deactivated = false;

		public void partActivated(IWorkbenchPart p) {
			if (p instanceof PropertySheet) {
				IPage cp = ((PropertySheet) p).getCurrentPage();
				if (cp instanceof PropertiesBrowserPage
						&& ((PropertiesBrowserPage) cp).getContributor() == ComponentsDiagramEditor.this) {
					deactivated = false;
				}
			} else if (p == ComponentsDiagramEditor.this) {
				deactivated = false;
			}
			if (deactivated && isDirty())
				doSave(new NullProgressMonitor());
		}

		@Override
		public void partBroughtToTop(IWorkbenchPart p) {
			// Ignore.
		}

		@Override
		public void partDeactivated(IWorkbenchPart p) {
			if (p instanceof PropertySheet) {
				IPage cp = ((PropertySheet) p).getCurrentPage();
				if (cp instanceof PropertiesBrowserPage
						&& ((PropertiesBrowserPage) cp).getContributor() == ComponentsDiagramEditor.this) {
					deactivated = true;
				}
			} else if (p == ComponentsDiagramEditor.this) {
				deactivated = true;
			}
		}
		
		///////////////////changeRecording///////////////////
		//FIXME: probably, this should be made into a different listener that only listens to the Statemachines Diagram Editor Part
		//instead of all parts on the page.
		@Override
		public void partClosed(IWorkbenchPart part) {
			if (part==ComponentsDiagramEditor.this){
				System.out.println("closing "+part.getTitle());
				if (ecr!=null) {
					ecr.disposeChangeRecorder();
					System.out.println("... disposed change recorder");
				}
			}
		}

		@Override
		public void partOpened(IWorkbenchPart part) {
			if (part==ComponentsDiagramEditor.this){
				System.out.println("opening "+part.getTitle());
				EObject diagramElement = ComponentsDiagramEditor.this.getDiagram().getElement();
				if (diagramElement instanceof EventBElement){
					EventBObject component = ((EventBElement)diagramElement).getContaining(CorePackage.Literals.EVENT_BNAMED_COMMENTED_COMPONENT_ELEMENT);
					ecr= Recorder.getNewRecorder((EventBNamedCommentedComponentElement)component);
					if (ecr!=null){
						int result = ecr.resumeRecording();
						if (result==1){
							//TODO: message dialogue here, save old changes in a different file
							System.out.println("... previous changes were out of sync and are lost.. restarting change record");				
						}
					}
				}
				System.out.println(ecr==null? "...opened but not recording" : "... opened and recording");
			}
		}
	};

	/**
	 * Add the deactivation part Listener to the Page
	 * @custom
	 */
	@Override
	public void setInput(IEditorInput input) {
		super.setInput(input);
		getSite().getPage().addPartListener(deactivationPartListener);
	}

	/**
	 * Remove the deactivation part Listener from the Page
	 * @custom
	 */
	@Override
	public void dispose() {
		if (ecr!=null) ecr.disposeChangeRecorder();
		super.dispose();
		getSite().getPage().removePartListener(deactivationPartListener);
	}
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		
		if (isAnimating()) return;	//must never save while animating as model contains animation artifacts which will not save
		
		System.out.println("saving "+this.getPartName());
		if (ecr!=null) {
			ecr.endRecording();
			System.out.println("... end recording");
		}
		super.doSave(progressMonitor);
		System.out.println("... saved");
		if (ecr!=null) {
			ecr.resumeRecording();
			System.out.println("... resume recording");
		}

	}

	private Recorder ecr=null;

	/////////////animating////////////////
	private boolean animating = false;

	public void startAnimating(){
		animating = true;
		System.out.println("started animating "+this.getPartName());
		if (ecr!=null) {
			ecr.endRecording();
			System.out.println("... stopped recording");
		}
	}

	public void stopAnimating(){
		animating = false;
		System.out.println("stopped animating "+this.getPartName());
		if (ecr!=null) {
			ecr.resumeRecording();
			System.out.println("... resume recording");
		}
	}

	/*
	 * checks whether this diagram is being animated
	 */
	public boolean isAnimating(){
		return animating; 
	}
}