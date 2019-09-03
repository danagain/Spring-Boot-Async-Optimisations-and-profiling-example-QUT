package com.example.demo.threading;

import com.example.demo.qut.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Scope("prototype")
public class FileReaderThread extends Thread implements Runnable {

    @Autowired
    private ThreadMethods threadMethods;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderThread.class);

    private String filename;

    private List<Gene> referenceGenes;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Gene> getReferenceGenes() {
        return referenceGenes;
    }

    public void setReferenceGenes(List<Gene> referenceGenes) {
        this.referenceGenes = referenceGenes;
    }

    @Override
    public void run() {
        try {
            threadMethods.parseFileAsync(filename, referenceGenes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}