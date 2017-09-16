package com.developersam.web.chunkreader.processor.summary;

/**
 * Annotated Sentence is a sentence with all necessary annotations to build
 * a summary upon it.
 */
public class AnnotatedSentence {

    /**
     * The original text of the sentence.
     */
    private String sentence;
    /**
     * The position where the sentence occurs in the original document.
     */
    private int position;
    /**
     * A value indicating the importance of the sentence. It is useful because
     * it is used to decide which sentence to pick when building summary.
     */
    private double salience;

    /**
     * Construct an annotated sentence with its originally available
     * information.
     * @param sentence raw text of the sentence.
     * @param position position of the sentence inside the original document.
     */
    public AnnotatedSentence(String sentence, int position) {
        this.sentence = sentence;
        this.position = position;
        this.salience = Math.random(); // random initialization.
    }

    public double getSalience() {
        return salience;
    }

    public void setSalience(double salience) {
        this.salience = salience;
    }

    /**
     * Store the database into the database.
     */
    public void storeIntoDatabase() {
        // TODO
    }

}