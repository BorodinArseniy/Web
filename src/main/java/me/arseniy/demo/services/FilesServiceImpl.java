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

    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Override
    public boolean saveToIngredientsFile(String json) {
        try {
            cleanIngredientsFile();
            Files.writeString(Path.of(dataFilePath, ingredientsFileName), json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromIngredientsFile(){
        try {
            return Files.readString(Path.of(dataFilePath, ingredientsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanIngredientsFile(){
        try {
            Files.deleteIfExists(Path.of(dataFilePath, ingredientsFileName));
            Files.createFile(Path.of(dataFilePath, ingredientsFileName));
        } catch (IOException e){
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public boolean saveToRecipesFile(String json) {
        try {
            cleanRecipesFile();
            Files.writeString(Path.of(dataFilePath, recipesFileName), json);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readFromRecipesFile(){
        try {
            return Files.readString(Path.of(dataFilePath, recipesFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean cleanRecipesFile(){
        try {
            Files.deleteIfExists(Path.of(dataFilePath, recipesFileName));
            Files.createFile(Path.of(dataFilePath, recipesFileName));
        } catch (IOException e){
            throw new RuntimeException();
        }
        return true;
    }
}
