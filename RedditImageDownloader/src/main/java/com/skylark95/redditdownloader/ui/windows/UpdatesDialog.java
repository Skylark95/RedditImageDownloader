package com.skylark95.redditdownloader.ui.windows;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.skylark95.redditdownloader.controller.RedditImageDownloader;
import com.skylark95.redditdownloader.ui.bindings.Buttons;
import com.skylark95.redditdownloader.ui.bindings.Fonts;
import com.skylark95.redditdownloader.ui.bindings.Labels;
import com.skylark95.redditdownloader.ui.events.CloseDialogListener;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UpdatesDialog extends Dialog implements RedditDialog {

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdatesDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	@Override
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(435, 150);
		shell.setText(getText());
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.addSelectionListener(new CloseDialogListener(shell));
		btnOk.setBounds(179, 87, 75, 25);
		btnOk.setText(Buttons.OK_TXT);
		
		Label lblCheckForUpdates = new Label(shell, SWT.NONE);
		lblCheckForUpdates.setFont(SWTResourceManager.getFont(Fonts.FONT_SEGOE_UI, 11, SWT.NORMAL));
		lblCheckForUpdates.setBounds(10, 25, 410, 25);
		lblCheckForUpdates.setText(Labels.UPDATES_DIALOG_TXT);

	}
}
