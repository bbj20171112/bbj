package com.bbj.attachment.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bbj.attachment.config.StorageProperties;
import com.bbj.attachment.exception.StorageException;
import com.bbj.attachment.exception.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
    
    /**
     * 增
     */
    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    /**
     * 删
     */
    @Override
    public void delete(String fileName) throws Exception {
    	Path path = load(fileName);
    	Files.delete(path);
    }
    

    /**
     * 删除所有
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    
    /**
     * 修改名称
     * @param fileName
     * @param newFileName
     * @return
     */
    @Override
    public int update(String fileName,String newFileName) {
    	Path source = load(fileName);
    	Path target = load(newFileName);
    	try {
			Files.move(source, target);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
    	return 1;
    }
    

    /**
     * 查询[单个]
     */
    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    /**
     * 根据关键字查询所有
     */
    @Override
    public Stream<Path> loadAll(String fileNameKey) {
        try {
        	if(!Files.exists(this.rootLocation)){
        		init();
        	}
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> (!path.equals(this.rootLocation) && !path.getFileName().toString().toLowerCase().contains(fileNameKey.toLowerCase())))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    
    /**
     * 查询所有
     */
    @Override
    public Stream<Path> loadAll() {
        try {
        	if(!Files.exists(this.rootLocation)){
        		init();
        	}
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + fileName);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

}
