package com.example.sistemanf.utils;

import com.example.sistemanf.exceptions.GeracaoArquivoException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileUtil {

    private FileUtil() {}

    public static File converterBase64ToFile(String base64, String filePath) {
        try {
            byte[] fileBytes = Base64.getDecoder().decode(base64);
            Path path = Paths.get(filePath);
            return Files.write(path, fileBytes).toFile();
        }
        catch (Exception e) {
            throw new GeracaoArquivoException(e.getMessage());
        }
    }
}
