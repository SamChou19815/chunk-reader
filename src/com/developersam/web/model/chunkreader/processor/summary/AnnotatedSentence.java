package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Sentence;
import com.developersam.web.model.chunkreader.google.objects.TextSpan;
import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

/**
 * Annotated Sentence is a sentence with all necessary annotations to build
 * a summary upon it.
 * @author Sam.
 */
public class AnnotatedSentence extends DataStoreObject {

    /**
     * The key of the whole text.
     */
    private Key parentKey;

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
     * Construct the annotated sentence and bind it to the database.
     */
    private AnnotatedSentence() {
        super("TextSummary");
    }

    /**
     * Construct the annotated sentence and bind it to the database.
     * @param parentKey parent key of the text.
     */
    private AnnotatedSentence(Key parentKey) {
        this();
        setParentKey(parentKey);
    }

    /**
     * Construct an annotated sentence with its originally available
     * information.
     * @param parentKey parent key of the text.
     * @param sentence raw text of the sentence.
     * @param position position of the sentence inside the original document.
     * @param salience good default value of salience.
     */
    AnnotatedSentence(Key parentKey,
                      String sentence, int position, double salience) {
        this(parentKey);
        this.sentence = sentence;
        this.position = position;
        this.salience = salience;
    }

    /**
     * Construct an annotated sentence with a {@code Sentence}.
     * @param parentKey parent key of the text.
     * @param sentence sentence object from Google.
     * @param salience good default value of salience.
     */
    AnnotatedSentence(Key parentKey, Sentence sentence, double salience) {
        this(parentKey);
        TextSpan textSpan = sentence.textSpan;
        this.sentence = textSpan.content;
        this.position = textSpan.beginOffset;
        this.salience = salience;
    }

    /**
     * Construct an annotated sentence from a database object.
     * @param textSummaryEntity text summary entity from the database.
     */
    public AnnotatedSentence(Entity textSummaryEntity) {
        this();
        sentence = textToString(textSummaryEntity.getProperty("sentence"));
        position = (int) textSummaryEntity.getProperty("position");
        salience = (double) textSummaryEntity.getProperty("salience");
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

    public void putIntoDatabase() {
        Entity textSummaryEntity = getNewEntity();
        textSummaryEntity.setProperty("sentence", new Text(sentence));
        textSummaryEntity.setProperty("position", position);
        textSummaryEntity.setProperty("salience", salience);
        putIntoDatabase(textSummaryEntity);
    }

    @Override
    public String toString() {
        return "{sentence:\"" + sentence +
                "\",position:" + position +
                ",salience:" + salience + "}";
    }
}