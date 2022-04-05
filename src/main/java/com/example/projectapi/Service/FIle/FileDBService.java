package com.example.projectapi.Service.FIle;

import com.example.projectapi.Model.FileDB;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileDBService {
    FileDB storeImageFile(MultipartFile file) throws IOException;

    FileDB storeImageByte(FileDB file) throws IOException;


    Stream<FileDB> getAllImagesFiles();

    FileDB getImage(String id);
}