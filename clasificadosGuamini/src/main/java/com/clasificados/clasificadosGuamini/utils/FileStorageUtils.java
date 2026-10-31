package com.clasificados.clasificadosGuamini.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileStorageUtils {

    private final String UPLOAD_DIR = "uploads/";

    public String guardarArchivoEnDisco(MultipartFile archivo) throws IOException {
        String nombreImagen = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
        Path rutaCompleta = Paths.get(UPLOAD_DIR + nombreImagen);
        
        Path directorioPadre = rutaCompleta.getParent();
        if (directorioPadre != null) {
            Files.createDirectories(directorioPadre);
        }
        
        Files.write(rutaCompleta, archivo.getBytes());
        return "/uploads/" + nombreImagen;
    }
}
