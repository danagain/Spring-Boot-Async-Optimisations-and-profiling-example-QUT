package com.example.demo.jaligner;

/**
 * Traceback directions.
 * 
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public abstract class Directions {
	/**
	 * Traceback direction stop
	 */
	public static final byte STOP = 0;
	/**
	 * Traceback direction left
	 */
	public static final byte LEFT = 1;
    /**
	 * Traceback direction diagonal
	 */
	public static final byte DIAGONAL = 2;
	/**
	 * Traceback direction up
	 */
	public static final byte UP = 3;
}