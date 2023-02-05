package me.arseniy.demo.services;

import java.io.File;

public interface FilesService {
    boolean saveToFile(String json, String fileName);
    String readFromFile(String fileName);
    boolean cleanFile(String fileName);

    File getDataFile(String fileName);
}
