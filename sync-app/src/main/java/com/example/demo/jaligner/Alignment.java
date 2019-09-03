package com.example.demo.jaligner;



import com.example.demo.jaligner.matrix.Matrix;
import com.example.demo.jaligner.util.Commons;

import java.text.DecimalFormat;

/**
 * Holds the output of a pairwise sequences alignment.
 * 
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public class Alignment {

	/**
	 * Gap character
	 */
	static final char GAP = '-';

	/**
	 * Default name for sequence #1
	 */
	private static final String DEFAULT_SEQUENCE1_NAME = "jaligner_1";

	/**
	 * Default name for sequence #2
	 */
	private static final String DEFAULT_SEQUENCE2_NAME = "jaligner_2";

	/**
	 * Scoring matrix
	 */
	private Matrix matrix;

	/**
	 * Gap open cost
	 */
	private float open;

	/**
	 * Gap extend cost
	 */
	private float extend;

	/**
	 * Alignment score
	 */
	private float score;

	/**
	 * Aligned sequence #1
	 */
	private char[] sequence1;

	/**
	 * Name of sequence #1
	 */
	private String name1;

	/**
	 * Alignment start location in sequence #1
	 */
	private int start1;

	/**
	 * Aligned sequence #2
	 */
	private char[] sequence2;

	/**
	 * Name of sequence #2
	 */
	private String name2;

	/**
	 * Alignment start location in sequence #2
	 */
	private int start2;

	/**
	 * Markup line
	 */
	private char[] markupLine;

	/**
	 * Count of identical locations
	 */
	private int identity;

	/**
	 * Count of similar locations
	 */
	private int similarity;

	/**
	 * Count of gap locations
	 */
	private int gaps;

	/**
	 * Constructor for Alignment
	 */

	Alignment() {
		super();
	}

	/**
	 * @return Returns the extend.
	 */
	public float getExtend() {
		return extend;
	}

	/**
	 * @param extend
	 *            The extend to set.
	 */
	void setExtend(float extend) {
		this.extend = extend;
	}

	/**
	 * @return Returns the matrix.
	 */
	public Matrix getMatrix() {
		return matrix;
	}

	/**
	 * @param matrix
	 *            The matrix to set.
	 */
	void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	/**
	 * @return Returns the name1.
	 */
	private String getName1() {
		return name1 == null || name1.length() == 0 ? DEFAULT_SEQUENCE1_NAME
				: name1;
	}

	/**
	 * @param name1
	 *            The name1 to set.
	 */
	void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * @return Returns the name2.
	 */
	private String getName2() {
		return name2 == null || name2.length() == 0 ? DEFAULT_SEQUENCE2_NAME
				: name2;
	}

	/**
	 * @param name2
	 *            The name2 to set.
	 */
	void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * @return Returns the open.
	 */
	public float getOpen() {
		return open;
	}

	/**
	 * @param open
	 *            The open to set.
	 */
	void setOpen(float open) {
		this.open = open;
	}

	/**
	 * @return Returns the score.
	 */
	public float getScore() {
		return score;
	}

	/**
	 * @param score
	 *            The score to set.
	 */
	void setScore(float score) {
		this.score = score;
	}

	/**
	 * @return Returns the sequence1.
	 */
	private char[] getSequence1() {
		return sequence1;
	}

	/**
	 * @param sequence1
	 *            The sequence1 to set.
	 */
	void setSequence1(char[] sequence1) {
		this.sequence1 = sequence1;
	}

	/**
	 * @return Returns the sequence2.
	 */
	public char[] getSequence2() {
		return sequence2;
	}

	/**
	 * @param sequence2
	 *            The sequence2 to set.
	 */
	void setSequence2(char[] sequence2) {
		this.sequence2 = sequence2;
	}

	/**
	 * @return Returns the start1.
	 */
	public int getStart1() {
		return start1;
	}

	/**
	 * @param start1
	 *            The start1 to set.
	 */
	void setStart1(int start1) {
		this.start1 = start1;
	}

	/**
	 * @return Returns the start2.
	 */
	public int getStart2() {
		return start2;
	}

	/**
	 * @param start2
	 *            The start2 to set.
	 */
	void setStart2(int start2) {
		this.start2 = start2;
	}

	/**
	 * @return Returns the gaps.
	 */
	public int getGaps() {
		return gaps;
	}

	/**
	 * @param gaps
	 *            The gaps to set.
	 */
	void setGaps(int gaps) {
		this.gaps = gaps;
	}

	/**
	 * @return Returns the identity.
	 */
	public int getIdentity() {
		return identity;
	}

	/**
	 * @param identity
	 *            The identity to set.
	 */
	void setIdentity(int identity) {
		this.identity = identity;
	}

	/**
	 * @return Returns the markupLine.
	 */
	public char[] getMarkupLine() {
		return markupLine;
	}

	/**
	 * @param markupLine
	 *            The markupLine to set.
	 */
	void setMarkupLine(char[] markupLine) {
		this.markupLine = markupLine;
	}

	/**
	 * @return Returns the similarity.
	 */
	public int getSimilarity() {
		return similarity;
	}

	/**
	 * @param similarity
	 *            The similarity to set.
	 */
	void setSimilarity(int similarity) {
		this.similarity = similarity;
	}

	/**
	 * Returns a summary for alignment
	 * 
	 * @return alignment summary
	 */
	public String getSummary() {
		StringBuilder buffer = new StringBuilder();
		DecimalFormat f1 = new DecimalFormat("0.00");
		DecimalFormat f2 = new DecimalFormat("0.00%");

		int length = getSequence1().length;

		buffer.append("Sequence #1: ").append(getName1());
		buffer.append(Commons.getLineSeparator());
		buffer.append("Sequence #2: ").append(getName2());
		buffer.append(Commons.getLineSeparator());
		buffer.append("Length #1: ").append(sequence1.length);
		buffer.append(Commons.getLineSeparator());
		buffer.append("Length #2: ").append(sequence2.length);
		buffer.append(Commons.getLineSeparator());
		buffer.append("Matrix: ").append(matrix.getId() == null ? "" : matrix.getId());
		buffer.append(Commons.getLineSeparator());
		buffer.append("Gap open: ").append(open);
		buffer.append(Commons.getLineSeparator());
		buffer.append("Gap extend: ").append(extend);
		buffer.append(Commons.getLineSeparator());
		buffer.append("Length: ").append(length);
		buffer.append(Commons.getLineSeparator());
		buffer.append("Identity: ").append(identity).append("/").append(length)
				.append(" (").append(f2.format(identity / (float) length)).append(")");
		buffer.append(Commons.getLineSeparator());
		buffer.append("Similarity: ").append(similarity).append("/")
				.append(length).append(" (").append(f2.format(similarity / (float) length)).append(")");
		buffer.append(Commons.getLineSeparator());
		buffer.append("Gaps: ").append(gaps).append("/").append(length)
				.append(" (").append(f2.format(gaps / (float) length)).append(")");
		buffer.append(Commons.getLineSeparator());
		buffer.append("Score: ").append(f1.format(score));
		buffer.append(Commons.getLineSeparator());

		return buffer.toString();
	}

	/**
	 * Calculate the score of the alignment, not using the score field (the
	 * function only uses sequence1, sequence2, matrix and gap penalties). (By:
	 * Bram Minnaert)
	 * 
	 * @return the calculated score
	 */
	public float calculateScore() {
		float calcScore = 0; // the calculated score
		boolean previous1wasGap = false; // in the previous step there was a gap
										 // in the first sequence
		boolean previous2wasGap = false; // in the previous step there was a gap
										 // in the second sequence
		char c1, c2; // the next character
		for (int i = 0, n = sequence1.length; i < n; i++) {
			c1 = sequence1[i];
			c2 = sequence2[i];
			// the next character in the first sequence is a gap
			if (c1 == GAP) {
				if (previous1wasGap) {
					calcScore -= extend;
				} else {
					calcScore -= open;
				}
				previous1wasGap = true;
				previous2wasGap = false;
			}
			// the next character in the second sequence is a gap
			else if (c2 == GAP) {
				if (previous2wasGap) {
					calcScore -= extend;
				} else {
					calcScore -= open;
				}
				previous1wasGap = false;
				previous2wasGap = true;
			}
			// the next characters in boths sequences are not gaps
			else {
				calcScore += matrix.getScore(c1, c2);
				previous1wasGap = false;
				previous2wasGap = false;
			}
		}
		return calcScore;
	}

	/**
	 * Check if the calculated score matches the field score.
	 * 
	 * @return true if equal, else false. (By: Bram Minnaert)
	 */
	public boolean checkScore() {
		return calculateScore() == score;
	}
}
