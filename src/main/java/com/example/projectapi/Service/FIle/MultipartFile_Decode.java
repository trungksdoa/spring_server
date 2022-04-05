package com.example.projectapi.Service.FIle;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

public class MultipartFile_Decode implements MultipartFile {

    private final byte[] imgContent;

    public MultipartFile_Decode(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public String getName() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getOriginalFilename() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public String getContentType() {
        // TODO - implementation depends on your requirements
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public Resource getResource() {
        return MultipartFile.super.getResource();
    }


    @Override
    public void transferTo(Path dest) throws IOException, IllegalStateException {
        MultipartFile.super.transferTo(dest);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        new FileOutputStream(dest).write(imgContent);
    }
}