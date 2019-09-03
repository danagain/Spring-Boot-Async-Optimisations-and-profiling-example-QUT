package com.example.demo.jaligner.matrix;

import java.io.Serializable;

/**
 * Scoring matrix.
 * 
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public class Matrix implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3833742170619524400L;

    /**
     * Matrix id (or name)
     */
    private String id;
    
    /**
     * Scores
     */
    private float[][] scores;
    
    public Matrix(String id, float[][] scores) {
        this.id = id;
        this.scores = scores;
    }
    
    /**
     * @return Returns the id.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * @return Returns the scores.
     */
    public float[][] getScores() {
        return this.scores;
    }
    
    /**
     * 
     * @param a
     * @param b
     * @return score
     */
    public float getScore(char a, char b) {
        return this.scores[a][b];
    }
}