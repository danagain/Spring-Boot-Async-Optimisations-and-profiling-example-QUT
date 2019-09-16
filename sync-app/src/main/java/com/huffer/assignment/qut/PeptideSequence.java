package com.huffer.assignment.qut;

import com.huffer.assignment.jaligner.BLOSUM62;
import com.huffer.assignment.jaligner.Sequence;
import com.huffer.assignment.jaligner.SmithWatermanGotoh;
import com.huffer.assignment.jaligner.matrix.Matrix;
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
