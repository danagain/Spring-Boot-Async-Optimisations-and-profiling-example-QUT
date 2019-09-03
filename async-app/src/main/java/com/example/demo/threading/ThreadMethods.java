package com.example.demo.threading;

import com.example.demo.qut.*;
import edu.au.jacobi.pattern.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.example.demo.qut.Sequential.*;

@Component
public class ThreadMethods {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadMethods.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Sequential sequential;

    void geneMatchingAsync(Gene referenceGene, GenbankRecord record) {
        System.out.println(referenceGene.name);
        for (Gene gene : record.genes)
            if (Homologous(gene.sequence, referenceGene.sequence))
            {
                NucleotideSequence upStreamRegion = GetUpstreamRegion(record.nucleotides, gene);
                LOGGER.debug(upStreamRegion.toString());
                Match prediction = PredictPromoter(upStreamRegion);
                    if (prediction != null)
                    {
                        updateConcensus(referenceGene, prediction);

                    }
            }
        for (Map.Entry<String, Sigma70Consensus> entry : consensus.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }


    void parseFileAsync(String filename, List<Gene> referenceGenes) throws IOException {
        LOGGER.info("Sequential :: parseFileAsync() :: Running File Job");
        System.out.println(filename);
        GenbankRecord record = Parse(filename);
        for (Gene referenceGene : referenceGenes)
        {
            Thread t = new Thread(new GeneMatcherThread(referenceGene, record, this));
            taskExecutor.execute(t);
        }
        LOGGER.info("Sequential :: parseFileAsync() :: File Job Complete");
    }
}
