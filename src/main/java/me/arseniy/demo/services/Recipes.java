package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface Recipes {
    void addRecipe(Recipe Recipe);

    Recipe getRecipe(Integer num);

    Map<Integer, Recipe> getAllRecipes();

    void changeRecipe(Integer num, Recipe Recipe);

    void deleteRecipe(Integer num);

}
