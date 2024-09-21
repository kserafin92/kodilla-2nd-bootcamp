package com.kodilla.integration;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private static final String INPUT_DIR = "input-directory";
    private static final String OUTPUT_FILE = "output-directory/output.txt";

    @PostMapping("/create")
    public ResponseEntity<String> createFile(@RequestParam String fileName, @RequestParam String content) {
        File file = new File(INPUT_DIR + "/" + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
            return ResponseEntity.ok("Plik został utworzony: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Błąd podczas tworzenia pliku: " + e.getMessage());
        }
    }

    @GetMapping("/output")
    public ResponseEntity<List<String>> readOutputFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(OUTPUT_FILE));
            return ResponseEntity.ok(lines);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
