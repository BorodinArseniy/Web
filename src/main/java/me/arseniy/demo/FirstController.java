package me.arseniy.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String endPoint1(){
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String endPoint2( ){
        return "Arseniy, Recipes, 10.01.2023, Java-project/recipes_collection/Spring_web";
    }

}
