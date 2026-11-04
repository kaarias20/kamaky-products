package com.kamaky.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//Servicio compartido: sube imágenes a Firebase Storage y devuelve la URL para guardarla en la BD
@Service
public class FirebaseStorageService {

    @Value("${firebase.bucket.name}")
    private String nombreBucket;

    @Value("${firebase.storage.path}")
    private String rutaStorage;

    private final Storage storage;

    public FirebaseStorageService(Storage storage) {
        this.storage = storage;
    }

    //carpeta: por ejemplo "producto" o "promocion"; id: id del registro dueño de la imagen
    public String subirImagen(MultipartFile archivoLocal, String carpeta, Integer id) throws IOException {
        String nombreOriginal = archivoLocal.getOriginalFilename();
        String extension = "";
        if (nombreOriginal != null && nombreOriginal.contains(".")) {
            extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        }
        String nombreArchivo = "img" + String.format("%014d", id) + extension;

        File archivoTemporal = convertirArchivo(archivoLocal);
        try {
            return subirAFirebase(archivoTemporal, carpeta, nombreArchivo);
        } finally {
            if (archivoTemporal.exists()) {
                archivoTemporal.delete();
            }
        }
    }

    private File convertirArchivo(MultipartFile archivo) throws IOException {
        File temporal = File.createTempFile("subida-", ".tmp");
        try (FileOutputStream salida = new FileOutputStream(temporal)) {
            salida.write(archivo.getBytes());
        }
        return temporal;
    }

    private String subirAFirebase(File archivo, String carpeta, String nombreArchivo) throws IOException {
        BlobId blobId = BlobId.of(nombreBucket, rutaStorage + "/" + carpeta + "/" + nombreArchivo);
        String tipo = Files.probeContentType(archivo.toPath());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(tipo != null ? tipo : "media").build();
        storage.create(blobInfo, Files.readAllBytes(archivo.toPath()));
        //URL firmada con vigencia de 5 años
        return storage.signUrl(blobInfo, 1825, TimeUnit.DAYS).toString();
    }
}
