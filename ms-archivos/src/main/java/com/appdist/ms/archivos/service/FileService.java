package com.appdist.ms.archivos.service;

import com.appdist.ms.archivos.model.entity.FileDist;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileService {

    void saveFile(MultipartFile file) throws IOException;

    //Optional<FileDist> byId(Long id);
    Resource loadFileAsResource(String fileName);

    void delete(Long id);

    String storeFile(MultipartFile file);
}
