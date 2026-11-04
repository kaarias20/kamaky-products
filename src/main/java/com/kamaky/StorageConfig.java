package com.kamaky;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class StorageConfig {

    @Value("${firebase.json.path}")
    private String rutaJson;

    @Value("${firebase.json.file}")
    private String archivoJson;

    //Crea el cliente de Firebase Storage con la llave del proyecto
    @Bean
    public Storage storage() throws IOException {
        ClassPathResource recurso = new ClassPathResource(rutaJson + File.separator + archivoJson);
        try (InputStream entrada = recurso.getInputStream()) {
            GoogleCredentials credenciales = GoogleCredentials.fromStream(entrada);
            return StorageOptions.newBuilder().setCredentials(credenciales).build().getService();
        }
    }
}
