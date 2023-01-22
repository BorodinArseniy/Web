package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Service
public class RecipesImpl implements Recipes{

    Map<Integer, Recipe> recipes = new HashMap<>();
    private static int counter = 0;

    @Override
    public void addRecipe(Recipe Recipe) {
        counter++;
        recipes.put(counter, Recipe);
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
    }

    @Override
    public void deleteRecipe(Integer num){
        recipes.remove(num);
    }
}
