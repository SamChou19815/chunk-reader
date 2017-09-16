package com.developersam.web.chunkreader.processor.summary;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.TextSpan;

/**
 * Annotated Sentence is a sentence with all necessary annotations to build
 * a summary upon it.
 * @author Sam.
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
     * @param salience good default value of salience.
     */
    AnnotatedSentence(String sentence, int position, double salience) {
        this.sentence = sentence;
        this.position = position;
        this.salience = salience;
    }

    /**
     * Construct an annotated sentence with a {@code Sentence}.
     * @param sentence sentence object from Google.
     * @param salience good default value of salience.
     */
    AnnotatedSentence(Sentence sentence, double salience) {
        TextSpan textSpan = sentence.getText();
        this.sentence = textSpan.getContent();
        this.position = textSpan.getBeginOffset();
        this.salience = salience;
    }

    public String getSentence() {
        return sentence;
    }

    public int getPosition() {
        return position;
    }

    public double getSalience() {
        return salience;
    }

    public void setSalience(double salience) {
        this.salience = salience;
    }

    public void increaseSalience(double increment) {
        salience += increment;
    }

    @Override
    public String toString() {
        return "{\n" + "sentence: '" + sentence +
                "', \n position: " + position +
                ", \n salience: " + salience + "}";
    }
}