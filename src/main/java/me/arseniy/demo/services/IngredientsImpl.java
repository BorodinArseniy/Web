package me.arseniy.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.arseniy.demo.modules.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientsImpl implements Ingredients {

    @Value("${name.of.ingredients.file}")
    public String ingredientsFileName;
    private Map<Integer, Ingredient> ingredients = new HashMap<>();
    private static int counter = 0;

    private final FilesService filesService;

    public IngredientsImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        counter++;
        ingredients.put(counter, ingredient);
        saveToFile();
    }

    @PostConstruct
    private void init(){
        filesService.readFromFile(ingredientsFileName);
    }

    @Override
    public Ingredient getIngredient(Integer num) {
        return ingredients.get(num);
    }

    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }

    @Override
    public void changeIngredient(Integer num, Ingredient ingredient) {
        ingredients.put(num, ingredient);
        saveToFile();
    }

    @Override
    public void deleteIngredient(Integer num){
        ingredients.remove(num);
    }


    private void saveToFile(){
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(ingredients);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        filesService.saveToFile(json, ingredientsFileName);
    }

    private void readFromFile(){
        String json = filesService.readFromFile(ingredientsFileName);
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
