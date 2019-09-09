package com.huffer.java.jaligner;

/**
 * Markups line characters.
 * 
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

abstract class Markups {
	/**
	 * Markup line identity character
	 */
	static final char IDENTITY	= '|';
	
	/**
	 * Markup line similarity character
	 */
	static final char SIMILARITY	= ':';
	
	/**
	 * Markup line gap character
	 */
	static final char GAP		= ' ';
	
	/**
	 * Markup line mismatch character
	 */
	static final char MISMATCH	= '.';
}
