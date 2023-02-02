package me.arseniy.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.data.file}")
    private String dataFilePath;


    @Override
    public String readFromFile(String fileName){
        try {
            return Files.readString(Path.of(dataFilePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanFile(String fileName){
        try {
            Files.deleteIfExists(Path.of(dataFilePath, fileName));
            Files.createFile(Path.of(dataFilePath, fileName));
        } catch (IOException e){
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public boolean saveToFile(String json, String fileName) {
        try {
            cleanFile(fileName);
            Files.writeString(Path.of(dataFilePath, fileName), json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
