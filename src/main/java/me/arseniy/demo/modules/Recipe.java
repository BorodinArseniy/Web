package me.arseniy.demo.modules;

import lombok.*;
import me.arseniy.demo.modules.Ingredient;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Recipe {

    private String name;
    private int cookingTime;
    private Ingredient[] ingredients;
    private String[] steps;
}
