package me.arseniy.demo.controllers;

import me.arseniy.demo.services.FilesService;
import me.arseniy.demo.services.Ingredients;
import me.arseniy.demo.services.Recipes;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Value("${name.of.recipes.file}")
    public String recipesFileName;

    @Value("${name.of.ingredients.file}")
    public String ingredientsFileName;

    private final FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping(value = "/export")
    public ResponseEntity<InputStreamResource> downloadRecipesFile() throws FileNotFoundException {
        File file = filesService.getDataFile(recipesFileName);

            InputStreamResource resource = new InputStreamResource(new FileInputStream((file)));
            return ResponseEntity.ok().
                    contentType(MediaType.APPLICATION_JSON).
                    contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipesList.json\"")
                    .body(resource);
    }


    @PostMapping(value = "/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Void> uploadIngredientsFile(@RequestParam MultipartFile file) {
        filesService.cleanFile(ingredientsFileName);
        File dataFile = filesService.getDataFile(ingredientsFileName);

        try ( FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Void> uploadRecipesFile(@RequestParam MultipartFile file) {
        filesService.cleanFile(recipesFileName);
        File dataFile = filesService.getDataFile(recipesFileName);

        try ( FileOutputStream fos = new FileOutputStream(dataFile)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
