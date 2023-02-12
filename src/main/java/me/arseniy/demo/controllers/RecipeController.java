package me.arseniy.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.arseniy.demo.modules.Recipe;
import me.arseniy.demo.services.RecipesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD/recipes")
public class RecipeController {
    RecipesService recipesService;

    public RecipeController(RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/{num}")
    @Operation(description = "searching for an recipe")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer num) {
        if (recipesService.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipesService.getRecipe(num));
    }

    @GetMapping("/allRecipes")
    @Operation(description = "getting all recipes")
    public ResponseEntity<Map<Integer, Recipe>> getIAllRecipes() {
        if (recipesService.getAllRecipes() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipesService.getAllRecipes());
    }

    @PostMapping
    @Operation(description = "posting new recipe")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe){
        recipesService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{num}")
    @Operation(description = "changing an recipe")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer num,@Valid @RequestBody Recipe recipe){
        if (recipesService.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipesService.changeRecipe(num, recipe);
        return ResponseEntity.ok(recipesService.getRecipe(num));
    }

    @DeleteMapping("/{num}")
    @Operation(description = "deleting an recipe")
    public ResponseEntity deleteRecipe(@PathVariable Integer num) {
        if (recipesService.getRecipe(num) == null) {
            return ResponseEntity.notFound().build();
        }
        recipesService.deleteRecipe(num);
        return ResponseEntity.ok().build();
    }

}
