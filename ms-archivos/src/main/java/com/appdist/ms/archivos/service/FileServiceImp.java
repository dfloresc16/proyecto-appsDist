package com.appdist.ms.archivos.service;

import com.appdist.ms.archivos.controller.FileController;
import com.appdist.ms.archivos.model.entity.FileDist;
import com.appdist.ms.archivos.repository.FileRepository;
import com.appdist.ms.archivos.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;

@Service
public class FileServiceImp implements FileService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private FileRepository fileRepository;


    @Autowired
    private ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImp.class);

    private static final String path = "G:\\proyectofinal\\proyecto-final\\ms-archivos\\src\\main\\resources\\static";


    @Override
    public void saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String[] name = fileName.split("\\."); // 1.png
        logger.info(name[0]+" "+name[1]);
        Random random = new Random();
        int numeroEntero = random.nextInt(Integer.MAX_VALUE)+1;
        try {
            String filePath = path + File.separator + String.valueOf(numeroEntero)+"."+name[1];
            file.transferTo(new File(filePath));
            FileDist fileDist = new FileDist();
            fileDist.setFilePath(filePath);
            fileDist.setFileName(String.valueOf(numeroEntero)+"."+name[1]);
            fileDist.setType(file.getContentType());
            fileRepository.save(fileDist);
        }catch (Exception e){
            logger.warn("Service: " + e.getMessage());
        }
    }


    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            String filePath = path + File.separator + fileName;
            file.transferTo(new File(filePath));
            FileDist fileDetails = new FileDist();
            fileDetails.setFileName(fileName);
            fileDetails.setFilePath(filePath);
            fileDetails.setType(file.getContentType());
            fileRepository.save(fileDetails);

            return "Archivo almacenado con éxito.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al almacenar el archivo.";
        }
    }


    public String getFileNameById(Long id) {
        Optional<FileDist> fileDetailsOptional = fileRepository.findById(id);

        if (fileDetailsOptional.isPresent()) {
            return fileDetailsOptional.get().getFileName();
        } else {
            throw new FileNotFoundException("No se encontró el archivo con ID: " + id);
        }
    }



    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = Paths.get(path).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Archivo no encontrado: " + fileName);
            }
        } catch (IOException ex) {
            throw new FileNotFoundException("Archivo no encontrado: " + fileName, ex);
        }
    }


    public String detectContentType(File file) throws IOException {
        String contentType = Files.probeContentType(file.toPath());

        if (contentType == null) {
            throw new IOException("No se pudo detectar el tipo MIME del archivo: " + file.getName());
        }

        return contentType;
    }

    /*@Override
    public Optional<FileDist> byId(Long id) {
        return Optional.ofNullable(fileRepository.findById(id).orElseThrow(() -> new ArchivoNotFoundException(id)));
    }*/

    @Override
    public void delete(Long id) {

    }
    class FileNotFoundException extends RuntimeException {

        public FileNotFoundException(String message) {
            super(message);
        }

        public FileNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
