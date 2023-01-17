package me.arseniy.demo.controllers;

import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.services.Recipes;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    Recipes recipes;

    public RecipeController(Recipes recipes) {
        this.recipes = recipes;
    }

    @GetMapping
    public Recipe getRecipe(@RequestParam Integer num){
        return recipes.getRecipe(num);
    }

    @PostMapping
    public void createRecipe(@RequestParam Recipe newRecipe) {
        recipes.addRecipe(newRecipe);
    }

}
