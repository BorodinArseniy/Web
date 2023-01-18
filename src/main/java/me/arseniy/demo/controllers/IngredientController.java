package me.arseniy.demo.controllers;

import me.arseniy.demo.modules.Ingredient;
import me.arseniy.demo.services.Ingredients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private Ingredients ingredients;

    public IngredientController(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    @GetMapping("/{num}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Integer num) {
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients.getIngredient(num));
    }

    @GetMapping("/allIngredients")
    public ResponseEntity<Map<Integer, Ingredient>> getIAllIngredients() {
        if (ingredients.getAllIngredients() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients.getAllIngredients());
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient){
        ingredients.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }

    @PutMapping("/{num}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable Integer num, @RequestBody Ingredient ingredient){
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        ingredients.changeIngredient(num, ingredient);
        return ResponseEntity.ok(ingredients.getIngredient(num));
    }

    @DeleteMapping("/{num}")
    public ResponseEntity deleteIngredient(@PathVariable Integer num) {
        if (ingredients.getIngredient(num) == null) {
            return ResponseEntity.notFound().build();
        }
        ingredients.deleteIngredient(num);
        return ResponseEntity.ok().build();
    }


}
