package me.arseniy.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import me.arseniy.demo.services.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import me.arseniy.demo.services.FilesService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
public class FilesController {

    private final RecipesImpl recipes;


    @Value("${name.of.recipes.file}")
    public String recipesFileName;
    @Value("${name.of.ingredients.file}")
    public String ingredientsFileName;

    private final FilesService filesService;

    public FilesController(RecipesImpl recipes, FilesService filesService) {
        this.recipes = recipes;
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

    @GetMapping("/recipe/export")
    @Operation(description = "Экспорт файла рецептов")
    public ResponseEntity<InputStreamResource> downloadTxtRecipeFile() throws IOException {
        InputStreamResource inputStreamResource = filesService.exportTxtFile(recipes.getRecipeMap());
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(Files.size(Path.of(filesService.getDataFilePath())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"Recipes.json\"")
                .body(inputStreamResource);
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
