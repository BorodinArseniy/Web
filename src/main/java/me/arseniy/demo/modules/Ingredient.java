package me.arseniy.demo.modules;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
// есть конструкторы (@NoArgsConstructor @AllArgsConstructor)
public class Ingredient {
    private String name;
    private int count;
    private String unitOfMeasure;
    //модификаторы доступа проставлены

}
