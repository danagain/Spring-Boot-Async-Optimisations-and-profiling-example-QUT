package com.huffer.assignment.qut;

import edu.au.jacobi.pattern.Match;
import edu.au.jacobi.pattern.Series;
import edu.au.jacobi.sequence.Sequence;

class BioPatterns
{
    static Match getBestMatch(Series pattern, String inputString)
    {
        return new Sequence("DNA",inputString).searchBest(1, 1, pattern);
    }	        
}
