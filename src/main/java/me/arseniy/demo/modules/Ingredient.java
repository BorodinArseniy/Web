package me.arseniy.demo.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Ingredient {
    private String name;
    private int count;
    private String unitOfMeasure;

}
