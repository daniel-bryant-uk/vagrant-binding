package com.guigarage.vagrant.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class VagrantUtils {

	private static VagrantUtils instance;

	public VagrantUtils() {
	}

	public synchronized static VagrantUtils getInstance() {
		if (instance == null) {
			instance = new VagrantUtils();
		}
		return instance;
	}

	public URL load(String path) throws IOException {
		URL url = ClassLoader.getSystemClassLoader().getResource(path);
		if (url == null) {
			// For use in JAR
			url = this.getClass().getResource(path);
		}
		if (url == null) {
			url = ClassLoader.getSystemResource(path);
		}
		if (url == null) {
			throw new IOException("Can't create URL for path " + path);
		}
		return url;
	}

	public String getLucid32MasterContent() {
		try {
			URL fileUrl = load("com/guigarage/vagrant/master/lucid32");
			return IOUtils.toString(fileUrl);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public String getLucid64MasterContent() throws IOException {
		try {
			URL fileUrl = load("com/guigarage/vagrant/master/lucid64");
			return IOUtils.toString(fileUrl);
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public URL getLucid32Url() {
		try {
			return new URL("http://files.vagrantup.com/lucid32.box");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public URL getLucid64Url() {
		try {
			return new URL("http://files.vagrantup.com/lucid64.box");
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
