package me.arseniy.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.services.FilesService;
import me.arseniy.demo.services.Ingredients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "CRUD/ingredients")
public class IngredientController {

    private Ingredients ingredients;


    @GetMapping("/{num}")
    @Operation(description = "searching for an ingredient")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Integer num) {
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients.getIngredient(num));
    }

    @GetMapping("/allIngredients")
    @Operation(description = "getting all ingredients")
    public ResponseEntity<Map<Integer, Ingredient>> getIAllIngredients() {
        if (ingredients.getAllIngredients() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients.getAllIngredients());
    }

    @PostMapping
    @Operation(description = "posting new ingredient")
    public ResponseEntity<Ingredient> createIngredient(@Valid @RequestBody Ingredient ingredient){
        ingredients.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{num}")
    @Operation(description = "changing an ingredient")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer num, @Valid @RequestBody Ingredient ingredient){
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        ingredients.changeIngredient(num, ingredient);
        return ResponseEntity.ok(ingredients.getIngredient(num));
    }

    @DeleteMapping("/{num}")
    @Operation(description = "deleting an ingredient")
    public ResponseEntity deleteIngredient(@PathVariable Integer num) {
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        ingredients.deleteIngredient(num);
        return ResponseEntity.ok().build();
    }


}
