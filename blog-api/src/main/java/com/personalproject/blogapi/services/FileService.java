package com.personalproject.blogapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImage(String path, MultipartFile File) throws IOException;

    InputStream getResource(String path, String Filename) throws FileNotFoundException;

    String deleteImage(String path) throws FileNotFoundException;
}
