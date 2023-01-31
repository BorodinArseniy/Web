package me.arseniy.demo.services;

public interface FilesService {
    boolean saveToFile(String json, String fileName);
    String readFromFile(String fileName);
    boolean cleanFile(String fileName);

}
