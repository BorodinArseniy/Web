package me.arseniy.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.services.Recipes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD/recipes")
public class RecipeController {
    Recipes recipes;

    public RecipeController(Recipes recipes) {
        this.recipes = recipes;
    }

    @GetMapping("/{num}")
    @Operation(description = "searching for an recipe")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer num) {
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes.getRecipe(num));
    }

    @GetMapping("/allRecipes")
    @Operation(description = "getting all recipes")
    public ResponseEntity<Map<Integer, Recipe>> getIAllRecipes() {
        if (recipes.getAllRecipes() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes.getAllRecipes());
    }

    @PostMapping
    @Operation(description = "posting new recipe")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe){
        recipes.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{num}")
    @Operation(description = "changing an recipe")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer num,@Valid @RequestBody Recipe recipe){
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipes.changeRecipe(num, recipe);
        return ResponseEntity.ok(recipes.getRecipe(num));
    }

    @DeleteMapping("/{num}")
    @Operation(description = "deleting an recipe")
    public ResponseEntity deleteRecipe(@PathVariable Integer num) {
        if (recipes.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipes.deleteRecipe(num);
        return ResponseEntity.ok().build();
    }

}
