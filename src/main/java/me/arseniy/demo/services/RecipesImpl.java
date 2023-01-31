package me.arseniy.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.modules.Recipe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Service
public class RecipesImpl implements Recipes{

    @Value("${name.of.recipes.file}")
    public String recipesFileName;
    private final FilesService filesService;

    public RecipesImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    private Map<Integer, Recipe> recipes = new HashMap<>();
    private static int counter = 0;

    @Override
    public void addRecipe(Recipe Recipe) {
        counter++;
        recipes.put(counter, Recipe);
        saveToFile();
    }

    @Override
    public Recipe getRecipe(Integer num) {
        return recipes.get(num);
    }

    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }

    @Override
    public void changeRecipe(Integer num, Recipe Recipe) {
        recipes.put(num, Recipe);
        saveToFile();
    }

    @Override
    public void deleteRecipe(Integer num){
        recipes.remove(num);
    }

    @PostConstruct
    private void init(){
        filesService.readFromFile(recipesFileName);
    }

    private void saveToFile(){
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(recipes);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        filesService.saveToFile(json, recipesFileName);
    }

    private void readFromFile(){
        String json = filesService.readFromFile(recipesFileName);
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

