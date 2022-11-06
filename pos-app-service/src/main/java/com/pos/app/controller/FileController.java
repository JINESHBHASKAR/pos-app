package com.pos.app.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pos.app.dto.FileResponse;
import com.pos.app.service.FileStorageService;
import com.pos.app.vo.StatusResponse;

@Controller
public class FileController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    
    @PostMapping("/upload-file")
    public ResponseEntity<StatusResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    	logger.info("upload file() in FileController");
        String fileName = fileStorageService.storeFile(file);
        
        logger.info("upload file() in FileController after storeFile "+ fileName);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(fileName)
                .toUriString();
        logger.info("upload file() in FileController  from CurrentContextPath "+ fileName+", uri: "+fileDownloadUri);
        FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri);
        
        StatusResponse status = new StatusResponse();
        status.setStatus(true);
        status.setMessage("File Uploaded Successfully");
        status.setData(fileResponse);
        
        return ResponseEntity.ok().body(status);
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/download-file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
               
                .body(resource);
    }

}
