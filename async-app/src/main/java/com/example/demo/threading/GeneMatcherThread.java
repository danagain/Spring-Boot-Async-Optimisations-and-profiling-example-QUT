package com.example.demo.threading;

import com.example.demo.qut.GenbankRecord;
import com.example.demo.qut.Gene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GeneMatcherThread extends Thread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneMatcherThread.class);
    private ThreadMethods threadMethods;
    private Gene referenceGene;
    private GenbankRecord record;

    public GeneMatcherThread(Gene referenceGene, GenbankRecord record, ThreadMethods threadMethods) {
        this.referenceGene = referenceGene;
        this.record = record;
        this.threadMethods = threadMethods;
    }

    @Override
    public void run() {
        threadMethods.geneMatchingAsync(referenceGene, record);
    }

}
