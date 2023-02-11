package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.data.file}")
    public String dataFilePath;

    @Override
    public String getDataFilePath() {
        return dataFilePath;
    }

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

    @Override
    public InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException {
        Path path = this.createAllRecipesFile("allRecipes");
        for (Recipe recipe : recipeMap.values()) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(" Название рецепта: ");
                writer.append(recipe.getName());
                writer.append("\n Время приготовления: ");
                writer.append(String.valueOf(recipe.getCookingTime()));
                writer.append(" ");
                writer.append("\n Ингредиенты: ");
                writer.append(String.valueOf(recipe.getIngredients()));
                writer.append("\n Шаги приготовления: ");
                writer.append(String.valueOf(recipe.getSteps()));
            }
        }

        File file = path.toFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    private Path createAllRecipesFile(String suffix) throws IOException {
        if (Files.exists(Path.of(dataFilePath, suffix))) {
            Files.delete(Path.of(dataFilePath, suffix));
            Files.createFile(Path.of(dataFilePath, suffix));
            return Path.of(dataFilePath, suffix);
        }
        return Files.createFile(Path.of(dataFilePath, suffix));
    }



    @Override
    public File getDataFile(String fileName){
        return new File(dataFilePath+"/"+fileName);
    }
}
