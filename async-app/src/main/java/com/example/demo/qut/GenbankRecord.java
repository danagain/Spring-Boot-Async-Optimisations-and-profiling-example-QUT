package com.example.demo.qut;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class GenbankRecord
{
    public NucleotideSequence nucleotides;
    public List<Gene> genes = new ArrayList<>();

    public void Parse(BufferedReader reader) throws IOException
    {
        genes = Gene.ParseGenes(reader);
        if (genes == null || genes.size() == 0) // EOF
            return;

        StringBuilder builder = new StringBuilder();
        while (true)
        {
            String line = reader.readLine();
            if (line.startsWith("//"))
                break;

            int length = line.length();
            for (int i = 10; i<length; i+=11)
                builder.append(line, i, Math.min(i+10, length));
        }
        nucleotides = new NucleotideSequence(builder.toString());

    }
}
