package me.arseniy.demo.services;

import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

public interface Recipes {
    void addRecipe(Recipe recipe);

    Recipe getRecipe(int num);

}
