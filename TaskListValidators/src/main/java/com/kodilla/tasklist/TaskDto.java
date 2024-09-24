package com.kodilla.tasklist;
import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class TaskDto {
    @NotNull
    @DateMin("2021-01-01")
    LocalDate when;
    @NotNull
    String title;
    @Min(1)
    @Max(5)
    int priority;
}