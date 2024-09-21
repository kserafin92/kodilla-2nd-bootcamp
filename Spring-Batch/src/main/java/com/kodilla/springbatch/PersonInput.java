package com.kodilla.springbatch;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PersonInput {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
