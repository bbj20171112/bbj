package com.bbj.attachment.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    void store(MultipartFile file);
    
    void delete(String fileName) throws Exception;

    void deleteAll();
    
    int update(String fileName, String newFileName);
    
    Path load(String filename);
    
    Resource loadAsResource(String filename);
    
    Stream<Path> loadAll();

	Stream<Path> loadAll(String fileNameKey);


}
