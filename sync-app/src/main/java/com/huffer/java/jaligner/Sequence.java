package com.huffer.java.jaligner;

import java.io.Serializable;

/**
 * A basic (nucleic or protein) sequence. It's a wrapper to {@link String}.
 *
 * @author Ahmed Moustafa (ahmed@users.sf.net)
 */

public class Sequence implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 3256721801357898297L;

    /**
	 * Sequence type nucleic.
	 */
    public static final int NUCLEIC = 0;
    
    /**
     * Sequence type protein.
     */
	public static final int PROTEIN = 1;
	
	/**
	 * Sequence
	 */
	private String sequence;
	
	/**
	 * Sequence id.
	 */
	private String id = null;

	/**
	 * Sequence description.
	 */
	private String description = null;
	
	/**
	 * Sequence type.
	 */
	private int type = PROTEIN;
	
	/**
	 * Constructor
	 */
	public Sequence( ) {
	   super(); 
	}

	/**
	 * Constructor
	 * @param sequence
	 */
	public Sequence(String sequence) {
	   super(); 
	   this.sequence = sequence;
	}

	
	/**
	 * Constructor
	 * @param sequence
	 * @param id
	 * @param description
	 * @param type
	 */
	public Sequence(String sequence, String id, String description, int type) {
	   super(); 
	   this.sequence = sequence;
	   this.id = id;
	   this.description = description;
	   this.type = type;
	}

    /**
     * Returns the sequence string
     * @return Returns the sequence
     */
    public String getSequence() {
        return sequence;
    }
    
    /**
     * Sets the sequence string
     * @param sequence The sequence to set
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
	
	/**
	 * Returns the sequence id
     * @return Returns the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the sequence id
     * @param id The id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Returns the sequence description
	 * @return Returns the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the sequence description
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
    /**
     * Returns the sequence type (nucleic or protein) 
     * @return Returns the type 
     */
    public int getType() {
        return type;
    }
    
    /**
     * Sets the sequence type (nucleic or protein)
     * @param type The type to set
     */
    public void setType(int type) {
        this.type = type;
    }
    
    /**
     * Returns the length of the sequence
     * @return sequence length
     */
    public int length( ) {
    	return this.sequence.length();
    }

    /**
     * Returns a subsequence
     * @param index start index
     * @param length length of subsequence
     * @return subsequence
     */
    public String subsequence(int index, int length) {
    	return this.sequence.substring(index, index + length);
    }
    
    /**
     * Returns the acid at specific location in the sequence
     * @param index
     * @return acid at index
     */
    public char acidAt(int index) {
    	return this.sequence.charAt(index);
    }
    
    /**
     * Returns the sequence as an array of characters.
     * @return array of chars.
     */
    public char[] toArray( ) {
    	return this.sequence.toCharArray();
    }
}