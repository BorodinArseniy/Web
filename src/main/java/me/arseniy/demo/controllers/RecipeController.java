package me.arseniy.demo.controllers;

import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.services.Recipes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/recipe")
public class RecipeController {
    Recipes recipes;

    public RecipeController(Recipes recipes) {
        this.recipes = recipes;
    }

    @GetMapping("/{num}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer num) {
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes.getRecipe(num));
    }

    @GetMapping("/allRecipes")
    public ResponseEntity<Map<Integer, Recipe>> getIAllRecipes() {
        if (recipes.getAllRecipes() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes.getAllRecipes());
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe){
        recipes.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{num}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer num, @RequestBody Recipe recipe){
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipes.changeRecipe(num, recipe);
        return ResponseEntity.ok(recipes.getRecipe(num));
    }

    @DeleteMapping("/{num}")
    public ResponseEntity deleteRecipe(@PathVariable Integer num) {
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipes.deleteRecipe(num);
        return ResponseEntity.ok().build();
    }

}
