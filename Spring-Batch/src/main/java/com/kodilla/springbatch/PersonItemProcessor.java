package com.kodilla.springbatch;

import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;
import java.time.Period;

public class PersonItemProcessor implements ItemProcessor<PersonInput, PersonOutput> {

    @Override
    public PersonOutput process(PersonInput personInput) throws Exception {
        int age = Period.between(personInput.getBirthDate(), LocalDate.now()).getYears();
        return new PersonOutput(personInput.getFirstName(), personInput.getLastName(), age);
    }
}
