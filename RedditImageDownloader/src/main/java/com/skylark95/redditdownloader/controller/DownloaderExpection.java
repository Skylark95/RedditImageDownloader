package com.skylark95.redditdownloader.controller;

public class DownloaderExpection extends Exception {

	private static final long serialVersionUID = 1605220496114508030L;

	public DownloaderExpection() {
		super();
	}

	public DownloaderExpection(String message) {
		super(message);
	}

	public DownloaderExpection(Throwable cause) {
		super(cause);
	}

	public DownloaderExpection(String message, Throwable cause) {
		super(message, cause);
	}

}
