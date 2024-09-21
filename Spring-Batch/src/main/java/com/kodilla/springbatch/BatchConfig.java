package com.kodilla.springbatch;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;

@Configuration
public class BatchConfig {

    @Value("${input.file.path}")
    private String inputFilePath;

    @Value("${output.file.path}")
    private String outputFilePath;

    @Bean
    public FlatFileItemReader<PersonInput> reader() {
        return new FlatFileItemReaderBuilder<PersonInput>()
                .name("personItemReader")
                .resource(new ClassPathResource("input.csv"))
                .delimited()
                .delimiter(";")
                .names(new String[]{"firstName", "lastName", "birthDate"})
                .fieldSetMapper(this::mapFieldSet)
                .build();
    }

    private PersonInput mapFieldSet(FieldSet fieldSet) {
        PersonInput person = new PersonInput();
        person.setFirstName(fieldSet.readString("firstName"));
        person.setLastName(fieldSet.readString("lastName"));
        person.setBirthDate(LocalDate.parse(fieldSet.readString("birthDate")));
        return person;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<PersonOutput> writer() {
        return new FlatFileItemWriterBuilder<PersonOutput>()
                .name("personItemWriter")
                .resource(new FileSystemResource(outputFilePath))
                .delimited()
                .delimiter(",")
                .names(new String[]{"firstName", "lastName", "age"})
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<PersonInput, PersonOutput>chunk(100, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
