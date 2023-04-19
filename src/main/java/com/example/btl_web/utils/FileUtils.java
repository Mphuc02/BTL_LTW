package com.example.btl_web.utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static void saveImageToServer(Part filePart, Long blogId,String projectLocation)
    {
        File file = new File(projectLocation + "/" + blogId + "/" + blogId + "_title.jpg");
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}