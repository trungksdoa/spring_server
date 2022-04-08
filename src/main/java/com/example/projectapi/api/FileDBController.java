package com.example.projectapi.api;

import com.example.projectapi.Model.FileDB;
import com.example.projectapi.Repo.FileDBRepository;
import com.example.projectapi.Service.FIle.FileDBService;
import com.example.projectapi.dtos.ErrorMessage;
import com.example.projectapi.dtos.FileDBResponse;
import com.example.projectapi.dtos.ResponeMessage;
import com.example.projectapi.dtos.SuccessMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/image/")
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class FileDBController {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private FileDBService fileDBService;

    @GetMapping("hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("upload")
    public ResponseEntity<ResponeMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Upload success");
        String message = "";
        try {
            fileDBService.storeImageFile(file);

            message = "Uploaded the file successfulls: " + file.getOriginalFilename();

            SuccessMessage<String, String> successMessage = new SuccessMessage();
            successMessage.setSuccessMessage(message);

            ResponeMessage messages = new ResponeMessage();
            messages.setSuccess(successMessage);
            return ResponseEntity.status(HttpStatus.OK).body(messages);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            ResponeMessage messages = new ResponeMessage();

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorMessage(message);
            messages.setError(errorMessage);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(messages);
        }
    }

    @GetMapping("files")
    public ResponseEntity<List<FileDBResponse>> getListFiles() {
        List<FileDBResponse> files = fileDBService.getAllImagesFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/v1/image/files/get/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new FileDBResponse(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }


    @GetMapping(value = "files/get/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable String id) throws IOException {
        FileDB image_file = null;
        image_file = fileDBService.getImage(id);
//        log.info(mimeType);
        //.... /id an
//        List<Integer> dsadsa = List.of(56,56,5);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image_file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image_file.getName() + "\"")
                .body(image_file.getData());
    }
}
