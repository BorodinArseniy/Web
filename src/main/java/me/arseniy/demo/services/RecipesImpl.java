package me.arseniy.demo.services;

import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class RecipesImpl implements Recipes{

    Map<Integer, Recipe> recipes = new HashMap<>();
    int counter = 0;

    @Override
    public void addRecipe(Recipe recipe) {
        counter++;
        recipes.put(counter, recipe);
    }

    @Override
    public me.arseniy.demo.modules.Recipe getRecipe(int num) {
        return recipes.get(num);
    }

}
