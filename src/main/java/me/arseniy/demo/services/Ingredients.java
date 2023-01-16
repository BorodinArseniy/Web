package me.arseniy.demo.services;

import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

public interface Ingredients {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int num);
}
