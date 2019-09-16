package com.example.demo.threading;

import com.example.demo.qut.GenbankRecord;
import com.example.demo.qut.Gene;
import com.example.demo.qut.NucleotideSequence;
import edu.au.jacobi.pattern.Match;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.example.demo.qut.Sequential.*;

@Component
@Scope("prototype")
public class BankMatchingThread extends Thread implements Runnable  {

    private ThreadMethods threadMethods;
    private Gene referenceGene;
    private Gene bankRecord;
    private GenbankRecord record;

    public BankMatchingThread(Gene referenceGene, Gene bankRecord, GenbankRecord record, ThreadMethods threadMethods) {
        this.referenceGene = referenceGene;
        this.bankRecord = bankRecord;
        this.threadMethods = threadMethods;
        this.record = record;
    }
    @Override
    public void run() {
                if (Homologous(this.bankRecord.sequence, this.referenceGene.sequence)) {
                    NucleotideSequence upStreamRegion = GetUpstreamRegion(record.nucleotides, this.bankRecord);
                    Match prediction = PredictPromoter(upStreamRegion);
                    if (prediction != null) {
                        updateConcensus(referenceGene, prediction);
                    }
        }
    }

}
