package com.example.projectapi.Service.FIle;

import com.example.projectapi.Model.FileDB;
import com.example.projectapi.Repo.FileDBRepository;
import com.example.projectapi.handelError.CustomIllegalStateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileDBServiceImpl implements FileDBService {
    private final FileDBRepository fileDBRepository;
    @Override
    public FileDB storeImageFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB image = new FileDB(fileName, file.getContentType(), file.getBytes());
        return fileDBRepository.save(image);
    }

    @Override
    public FileDB storeImageByte(FileDB file) throws IOException {
        return fileDBRepository.save(file);
    }

    @Override
    public Stream<FileDB> getAllImagesFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Override
    public FileDB getImage(String id) {
        Optional<FileDB> optionalFileDB = Optional.ofNullable(fileDBRepository.findById(id).orElseThrow(() -> new CustomIllegalStateException("Không tìm thấy ảnh")));
        FileDB file = optionalFileDB.get();
        return file;
    }
}