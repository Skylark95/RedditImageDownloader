package com.skylark95.redditdownloader.core;

public class SettingsExecption extends Exception {

	private static final long serialVersionUID = -3303185944732039797L;
	
	public SettingsExecption() {
		super();
	}

	public SettingsExecption(String message) {
		super(message);
	}

	public SettingsExecption(Throwable cause) {
		super(cause);
	}

	public SettingsExecption(String message, Throwable cause) {
		super(message, cause);
	}

}
