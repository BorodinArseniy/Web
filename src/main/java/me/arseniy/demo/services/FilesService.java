package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FilesService {
    boolean saveToFile(String json, String fileName);

    String getDataFilePath();

    String readFromFile(String fileName);
    boolean cleanFile(String fileName);

    InputStreamResource exportTxtFile(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException;

    File getDataFile(String fileName);
}
