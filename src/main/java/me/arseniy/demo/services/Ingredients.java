package me.arseniy.demo.services;

import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.modules.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface Ingredients {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer num);

    Map<Integer, Ingredient> getAllIngredients();

    void changeIngredient(Integer num, Ingredient ingredient);

    void deleteIngredient(Integer num);
}
