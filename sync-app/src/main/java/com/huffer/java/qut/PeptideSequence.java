package com.huffer.java.qut;

import com.huffer.java.jaligner.BLOSUM62;
import com.huffer.java.jaligner.Sequence;
import com.huffer.java.jaligner.SmithWatermanGotoh;
import com.huffer.java.jaligner.matrix.Matrix;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PeptideSequence 
{
    private byte[] bytes;

    private static final Matrix BLOSUM_62 = BLOSUM62.Load();
    
    PeptideSequence(String string)
    {
        bytes = string.getBytes();
    }
    
    public static double Similarity(PeptideSequence A, PeptideSequence B)
    {  
        return SmithWatermanGotoh.align(new Sequence(A.toString()), new Sequence(B.toString()), BLOSUM_62, 10f, 0.5f).calculateScore();
    }
    
    @Override
    public String toString()
    {
        return new String(bytes);
    }
}
