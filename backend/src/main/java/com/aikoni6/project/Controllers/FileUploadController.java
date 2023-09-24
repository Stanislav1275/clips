package com.aikoni6.project.Controllers;

import com.aikoni6.project.Entities.system.FileEntity;
import com.aikoni6.project.Services.general.FileEntityService;
import com.aikoni6.project.Services.system.TokenService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("uploadController")
public class FileUploadController {
    @Autowired
    private FileEntityService fileEntityService;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @CrossOrigin
    @PostMapping("upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam(name = "FILE") MultipartFile file,
            @RequestParam(name = "TOKEN") String token
    ) {
        if(tokenService.tokenUtils.verifyToken(token)){
            FileEntity fileEntity = fileEntityService.storeFile(file);
            return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(fileEntity));
        }
        return ResponseEntity.badRequest().body("Error: current user is not authorized");
    }
}