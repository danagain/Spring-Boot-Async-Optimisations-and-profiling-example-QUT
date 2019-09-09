package com.huffer.java.qut;

import java.io.*;
import java.util.*;

class GenbankRecord
{
    NucleotideSequence nucleotides;
    List<Gene> genes = new ArrayList<>();

    void Parse(BufferedReader reader) throws IOException
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
