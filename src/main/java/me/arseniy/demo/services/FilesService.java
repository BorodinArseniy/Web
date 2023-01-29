package me.arseniy.demo.services;

public interface FilesService {
    boolean saveToIngredientsFile(String json);
    boolean saveToRecipesFile(String json);

    String readFromIngredientsFile();
    String readFromRecipesFile();
    boolean cleanIngredientsFile();
    boolean cleanRecipesFile();

}
