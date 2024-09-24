package com.kodilla.kodillabytereflecion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {

    @GetMapping("/students")
    public Map<Integer, String> generateStudents(
            @RequestParam(defaultValue = "20") @Range(min = 1, max = 100) int n,
            @RequestParam(defaultValue = "10") @Range(min = 5, max = 50) int z) {

        Map<Integer, String> resultMap = new HashMap<>();

        try {
            for (int i = 0; i < n; i++) {
                Student student = new Student(z);

                Field indexNumberField = Student.class.getDeclaredField("indexNumber");
                indexNumberField.setAccessible(true);
                String indexNumber = (String) indexNumberField.get(student);

                resultMap.put(System.identityHashCode(student), indexNumber);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
