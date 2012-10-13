package com.skylark95.redditdownloader.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.custom.CLabel;

public class RedditMainWindow {

	protected Shell shlRedditImageDownloader;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RedditMainWindow window = new RedditMainWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlRedditImageDownloader.open();
		shlRedditImageDownloader.layout();
		while (!shlRedditImageDownloader.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		createWindow();
		createMenus();
		createBody();
		createStatusFooter();
	}

	private void createBody() {
		createRedditUsername();
		createSaveTo();
		createButtons();
	}

	private void createMenus() {
		Menu menu = new Menu(shlRedditImageDownloader, SWT.BAR);
		shlRedditImageDownloader.setMenuBar(menu);

		createFileMenu(menu);
		createHelpMenu(menu);
	}

	private void createHelpMenu(Menu menu) {
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_2 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_2);

		MenuItem mntmAbout = new MenuItem(menu_2, SWT.NONE);
		mntmAbout.setText("About");

		MenuItem mntmDonate = new MenuItem(menu_2, SWT.NONE);
		mntmDonate.setText("Donate");

		MenuItem mntmWebsite = new MenuItem(menu_2, SWT.NONE);
		mntmWebsite.setText("Website");

		new MenuItem(menu_2, SWT.SEPARATOR);

		MenuItem mntmSubmitAnIssue = new MenuItem(menu_2, SWT.NONE);
		mntmSubmitAnIssue.setText("Submit an Issue");

		MenuItem mntmCheckForUpdates = new MenuItem(menu_2, SWT.NONE);
		mntmCheckForUpdates.setText("Check for Updates");
	}

	private void createFileMenu(Menu menu) {
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		MenuItem mntmSettings = new MenuItem(menu_1, SWT.NONE);
		mntmSettings.setText("Settings");

		MenuItem mntmQuit = new MenuItem(menu_1, SWT.NONE);
		mntmQuit.setText("Exit");
	}

	private void createStatusFooter() {
	}

	private void createButtons() {

		Group grpDownlad = new Group(shlRedditImageDownloader, SWT.NONE);
		grpDownlad.setText("Settings");
		grpDownlad.setBounds(10, 10, 564, 115);
		Label lblRedditUsername = new Label(grpDownlad, SWT.NONE);
		lblRedditUsername.setToolTipText("The username of the Reddit User to downlad Images for.");
		lblRedditUsername.setBounds(10, 20, 123, 21);
		lblRedditUsername.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblRedditUsername.setText("Reddit Username:");

		text = new Text(grpDownlad, SWT.BORDER);
		text.setToolTipText("The username of the Reddit User to downlad Images for.");
		text.setBounds(139, 22, 326, 21);
		Combo combo = new Combo(grpDownlad, SWT.NONE);
		combo.setToolTipText("The location to save the downloaded images.\r\n\r\nNOTE: This field will keep history of previous save locations by default.  This can be turned off in settings.");
		combo.setBounds(73, 57, 392, 23);

		Label lblSaveTo = new Label(grpDownlad, SWT.NONE);
		lblSaveTo
				.setToolTipText("The location to save the downloaded images.\r\n\r\nNOTE: This field will keep history of previous save locations by default.  This can be turned off in settings.");
		lblSaveTo.setBounds(10, 55, 57, 21);
		lblSaveTo.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblSaveTo.setText("Save To:");

		Button btnBrowse = new Button(grpDownlad, SWT.NONE);
		btnBrowse.setBounds(471, 55, 76, 25);
		btnBrowse.setText("Browse");
		Button btnDownload = new Button(grpDownlad, SWT.NONE);
		btnDownload.setBounds(472, 20, 75, 25);
		btnDownload.setText("Download");
		
		CLabel lblNewLabel = new CLabel(grpDownlad, SWT.BORDER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(10, 86, 537, 21);
		lblNewLabel.setText("To change more settings, go to File > Settings");

		Group grpLog = new Group(shlRedditImageDownloader, SWT.NONE);
		grpLog.setText("Log");
		grpLog.setBounds(10, 142, 564, 390);

		text_1 = new Text(grpLog, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		text_1.setBounds(10, 21, 544, 359);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
	}

	private void createSaveTo() {
	}

	private void createRedditUsername() {
	}

	private void createWindow() {
		shlRedditImageDownloader = new Shell();
		shlRedditImageDownloader.setSize(600, 600);
		shlRedditImageDownloader.setText("Reddit Image Downloader");
	}
}
