package me.arseniy.demo.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.arseniy.demo.modules.Ingredient;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Recipe {

    private String name;
    private int cookingTime;
    private Ingredient[] ingredients;
    private String[] steps;
}
