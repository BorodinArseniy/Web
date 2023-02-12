package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;

import java.util.Map;

public interface RecipesService {

    void addRecipe(Recipe Recipe);

    Recipe getRecipe(Integer num);

    Map<Integer, Recipe> getAllRecipes();

    void changeRecipe(Integer num, Recipe Recipe);

    void deleteRecipe(Integer num);


}
