package me.arseniy.demo.controllers;

import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.services.Ingredients;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private Ingredients ingredients;

    public IngredientController(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @PostMapping
    public void createIngredient(@RequestParam Ingredient ingredient) {
        ingredients.addIngredient(ingredient);
    }

    @GetMapping
    public Ingredient getIngredient(@RequestParam Integer num){
        return ingredients.getIngredient(num);
    }

}
