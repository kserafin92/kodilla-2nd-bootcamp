package com.kodilla.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Configuration
@IntegrationComponentScan
public class FileIntegrationConfiguration {

    private static final String INPUT_DIR = "input-directory";
    private static final String OUTPUT_FILE = "output-directory/output.txt";

    @Bean
    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public FileReadingMessageSource fileReadingMessageSource() {
        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(INPUT_DIR));
        source.setFilter(new SimplePatternFileListFilter("*.txt"));
        return source;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileInputChannel")
    public MessageHandler fileWritingMessageHandler() {
        return message -> {
            String fileName = (String) message.getPayload();
            appendFileNameToOutput(fileName);
        };
    }

    @Bean
    public IntegrationFlow fileIntegrationFlow() {
        return IntegrationFlow.from(fileReadingMessageSource(), c -> c.poller(Pollers.fixedDelay(1000)))
                .channel(fileInputChannel())
                .transform(File.class, File::getName)
                .handle(fileWritingMessageHandler())
                .get();
    }

    private void appendFileNameToOutput(String fileName) {
        try (FileWriter writer = new FileWriter(OUTPUT_FILE, true)) {
            writer.write(fileName + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void initializeOutputFile() {
        File inputDir = new File(INPUT_DIR);
        try (FileWriter writer = new FileWriter(OUTPUT_FILE)) {
            if (inputDir.isDirectory()) {
                File[] files = inputDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            writer.write(file.getName() + System.lineSeparator());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
