package com.kodilla.springintegration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

@Configuration
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
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_FILE).getParentFile());
        handler.setAppendNewLine(true);
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileWritingMessageHandler.FileExistsMode.APPEND);
        handler.setFileNameGenerator(message -> "output.txt");
        return handler;
    }

    @Bean
    public IntegrationFlow fileIntegrationFlow() {
        return IntegrationFlow.from(fileReadingMessageSource(), c -> c.poller(Pollers.fixedDelay(1000)))
                .channel(fileInputChannel())
                .transform(File.class, File::getName)
                .handle(fileWritingMessageHandler())
                .get();
    }
}
