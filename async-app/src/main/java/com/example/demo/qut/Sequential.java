package com.example.demo.qut;

import com.example.demo.jaligner.BLOSUM62;
import com.example.demo.jaligner.Sequence;
import com.example.demo.jaligner.SmithWatermanGotoh;
import com.example.demo.jaligner.matrix.Matrix;
import com.example.demo.threading.FileReaderThread;
import edu.au.jacobi.pattern.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

import static com.example.demo.AppEntryPoint.RESOURCES_PATH;


@Component
public class Sequential
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Sequential.class);

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;

    public static HashMap<String, Sigma70Consensus> consensus = new HashMap<>();

    private static Series sigma70_pattern = Sigma70Definition.getSeriesAll_Unanchored();

    private static final Matrix BLOSUM_62 = BLOSUM62.Load();

    private static byte[] complement = new byte['z'];

    static
    {
        complement['C'] = 'G'; complement['c'] = 'g';
        complement['G'] = 'C'; complement['g'] = 'c';
        complement['T'] = 'A'; complement['t'] = 'a';
        complement['A'] = 'T'; complement['a'] = 't';
    }

                    
    private synchronized static List<Gene> ParseReferenceGenes(String referenceFile) throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(referenceFile)));
        List<Gene> referenceGenes = new ArrayList<>();
        while (true)
        {
            String name = reader.readLine();
            if (name == null)
                break;
            String sequence = reader.readLine();
            referenceGenes.add(new Gene(name, 0, 0, sequence));
            consensus.put(name, new Sigma70Consensus());
        }
        consensus.put("all", new Sigma70Consensus());
        reader.close();
        return referenceGenes;
    }

    public static boolean Homologous(PeptideSequence A, PeptideSequence B)
    {
        return SmithWatermanGotoh.align(new Sequence(A.toString()), new Sequence(B.toString()), BLOSUM_62, 10f, 0.5f).calculateScore() >= 60;
    }

    public static NucleotideSequence GetUpstreamRegion(NucleotideSequence dna, Gene gene)
    {
        int upStreamDistance = 250;
        if (gene.location < upStreamDistance)
           upStreamDistance = gene.location-1;

        if (gene.strand == 1)
            return new NucleotideSequence(Arrays.copyOfRange(dna.bytes, gene.location-upStreamDistance-1, gene.location-1));
        else
        {
            byte[] result = new byte[upStreamDistance];
            int reverseStart = dna.bytes.length - gene.location + upStreamDistance;
            for (int i=0; i<upStreamDistance; i++)
                result[i] = complement[dna.bytes[reverseStart-i]];
            return new NucleotideSequence(result);
        }
    }

    public synchronized static Match PredictPromoter(NucleotideSequence upStreamRegion)
    {
        LOGGER.debug("Sequential :: PredictPromoter() :: Starting");
        return BioPatterns.getBestMatch(sigma70_pattern, upStreamRegion.toString());
    }

    private static void ProcessDir(List<String> list, File dir)
    {
        if (dir.exists())
            for (File file : Objects.requireNonNull(dir.listFiles()))
                if (file.isDirectory())
                    ProcessDir(list, file);
                else
                    list.add(file.getPath());
    }

    private static List<String> ListGenbankFiles(String dir)
    {
        List<String> list = new ArrayList<>();
        ProcessDir(list, new File(dir));
        return list;
    }

    public static GenbankRecord Parse(String file) throws IOException
    {
        GenbankRecord record = new GenbankRecord();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        record.Parse(reader);
        reader.close();
        return record;
    }

    public synchronized static void updateConcensus(Gene referenceGene, Match prediction) {
        consensus.get(referenceGene.name).addMatch(prediction);
        consensus.get("all").addMatch(prediction);
    }

    public void run() throws IOException {
        List<Gene> referenceGenes = ParseReferenceGenes(new ClassPathResource("referenceGenes.list")
                .getFile().getAbsolutePath());

        for (String filename : ListGenbankFiles(RESOURCES_PATH + "/Ecoli"))
        {
            FileReaderThread myThread = applicationContext.getBean(FileReaderThread.class);
            myThread.setFilename(filename);
            myThread.setReferenceGenes(referenceGenes);
            taskExecutor.execute(myThread);
        }

    }
}
