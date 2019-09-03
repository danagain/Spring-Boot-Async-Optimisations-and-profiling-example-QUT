package com.example.demo.qut;

import edu.au.jacobi.pattern.Match;
import edu.au.jacobi.pattern.Series;
import edu.au.jacobi.sequence.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BioPatterns
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BioPatterns.class);

    public static Match getBestMatch(Series pattern, String inputString)
    {
        LOGGER.debug("BioPatterns :: getBestMatch() :: Starting");
        return new Sequence("DNA", inputString).searchBest(1, 1, pattern);
    }	        
}
