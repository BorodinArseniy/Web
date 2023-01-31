package me.arseniy.demo.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
// есть конструкторы (@NoArgsConstructor @AllArgsConstructor)
public class Ingredient {
    private String name;
    private int count;
    private String unitOfMeasure;
    //модификаторы доступа проставлены

}
