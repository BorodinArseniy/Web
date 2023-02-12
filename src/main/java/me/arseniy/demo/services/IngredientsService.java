package me.arseniy.demo.services;

import me.arseniy.demo.modules.Ingredient;

import java.util.Map;



public interface IngredientsService {
    void addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer num);

    Map<Integer, Ingredient> getAllIngredients();

    void changeIngredient(Integer num, Ingredient ingredient);

    void deleteIngredient(Integer num);
}
