
package com.huffer.assignment.jaligner;

/**
 * A cell in a similarity matrix, to hold row, column and score.
 * 
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public class Cell {
	/**
	 * Row of the cell
	 */
	private int row;
	/**
	 * Column of the cell
	 */
	private int col;
	/**
	 * Alignment score at this cell
	 */
	private float score;
	
	/**
	 * Constructor
	 */
	Cell() {
		super();
		this.row = 0;
		this.col = 0;
		this.score = Float.NEGATIVE_INFINITY;
	}
	/**
	 * @return Returns the col.
	 */
	int getCol() {
		return this.col;
	}
	/**
	 * @param col The col to set.
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * @return Returns the row.
	 */
	int getRow() {
		return this.row;
	}
	/**
	 * @param row The row to set.
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return Returns the score.
	 */
	float getScore() {
		return this.score;
	}
	/**
	 * @param score The score to set.
	 */
	public void setScore(float score) {
		this.score = score;
	}
	
	/**
	 * Sets the row, column and score of the cell.
	 * @param row The row to set.
	 * @param col The col to set.
	 * @param score The score to set.
	 */
	void set(int row, int col, float score) {
		this.row = row;
		this.col = col;
		this.score = score;
	}
}