package com.appdist.ms.archivos.controller;

import com.appdist.ms.archivos.model.dto.FileRequest;
import com.appdist.ms.archivos.model.dto.GenericResponseDTO;
import com.appdist.ms.archivos.model.entity.FileDist;
import com.appdist.ms.archivos.service.FileService;
import com.appdist.ms.archivos.service.FileServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;


@RestController
@Slf4j
@RequestMapping("/v1/api/files")
public class FileController extends CommonController{

    private static final Logger logInfo = LoggerFactory.getLogger(FileController.class);

    private static final String UPLOAD_DIR = "G:\\proyectofinal\\proyecto-final\\imagesRequest";  // Ruta donde se buscarán los archivos

    @Autowired
    private FileServiceImp fileServiceImp;

    /*@RequestMapping(path = "/uploadFile",method = RequestMethod.POST)
    public ResponseEntity<GenericResponseDTO<?>> cargarArchivo(@RequestParam("archivo") MultipartFile archivo) {
        try {
            fileServiceImp.saveFile(archivo);
            return ResponseEntity.ok(new GenericResponseDTO<>(SUCCESS,HTTP_SUCCESS,null,
                    null,"servicio ejecutado exitosamente",null));
        }catch (Exception e){
            log.warn(e.getMessage());
            return new ResponseEntity<>(new GenericResponseDTO<>(ERROR,HTTP_APP_FAILURE,null,
                    e.getMessage(),null,null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @RequestMapping(path = "/upload",method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestBody FileRequest fileRequest) {
        try {
            // Construir la ruta completa del archivo
            String filePath = UPLOAD_DIR + File.separator + fileRequest.getFileName();
            log.info("UploadFile Controller -> filePath: " + filePath);

            // Verificar si el archivo existe
            File file = new File(filePath);
            if (!file.exists()) {
                return new ResponseEntity<>("Archivo no encontrado", HttpStatus.NOT_FOUND);
            }

            // Convertir el archivo a MultipartFile
            Path path = Paths.get(filePath);
            byte[] fileContent = Files.readAllBytes(path);
            MultipartFile multipartFile = new InMemoryMultipartFile(fileRequest.getFileName(), fileContent);
            fileServiceImp.saveFile(multipartFile);
            return new ResponseEntity<>("Archivo cargado exitosamente", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error al cargar el archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        try {
            // Recuperar el nombre del archivo basándose en el ID (deberías implementar este método en tu servicio)
            String fileName = fileServiceImp.getFileNameById(id);

            // Cargar el archivo como recurso
            Resource resource = fileServiceImp.loadFileAsResource(fileName);

            // Determinar el tipo MIME del archivo
            String contentType = fileServiceImp.detectContentType(resource.getFile());

            // Devolver la respuesta con el archivo adjunto
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            // Manejar el caso en el que el archivo no se encuentra
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /*@GetMapping("/listar")
    public ResponseEntity<List<Archivo>> listarArchivos() {
        List<Archivo> archivos = archivoService.listarArchivos();
        return ResponseEntity.ok(archivos);
    }*/

    // Clase auxiliar para convertir un byte array a un MultipartFile
    private static class InMemoryMultipartFile implements MultipartFile {
        private final String name;
        private final byte[] content;

        public InMemoryMultipartFile(String name, byte[] content) {
            this.name = name;
            this.content = content;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getOriginalFilename() {
            return name;
        }

        @Override
        public String getContentType() {
            // Puedes ajustar el tipo de contenido según el tipo de archivo
            return "application/octet-stream";
        }

        @Override
        public boolean isEmpty() {
            return content.length == 0;
        }

        @Override
        public long getSize() {
            return content.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return content;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            Files.write(dest.toPath(), content);
        }
    }



}
