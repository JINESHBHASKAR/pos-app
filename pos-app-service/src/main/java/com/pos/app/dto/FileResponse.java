package com.pos.app.dto;

import java.io.Serializable;

public class FileResponse implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String fileDownloadUri;
	
	
	
	public FileResponse(String fileName, String fileDownloadUri) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileDownloadUri() {
		return fileDownloadUri;
	}


	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}




}
