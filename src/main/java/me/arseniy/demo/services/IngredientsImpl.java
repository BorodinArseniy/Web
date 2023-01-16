package me.arseniy.demo.services;

import me.arseniy.demo.modules.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsImpl implements Ingredients {
    Map<Integer, Ingredient> ingredients = new HashMap<>();
    int counter = 0;


    @Override
    public void addIngredient(Ingredient ingredient) {
        counter++;
        ingredients.put(counter, ingredient);
    }

    @Override
    public Ingredient getIngredient(int num) {
        return ingredients.get(num);
    }
}
