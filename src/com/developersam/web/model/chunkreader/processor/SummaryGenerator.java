package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.google.objects.Sentence;

import java.util.List;

/**
 * Summary Generator is used to evaluate the importance of each sentence, and
 * store the sentences' importance indicator into database, so that they can
 * be used to generate summary.
 */
public interface SummaryGenerator extends Processor {

    /**
     * Read entities, sentences, and tokens into summary generator.
     * @param entityList list of entities.
     * @param sentenceList list of sentences.
     */
    void read(List<Entity> entityList,
              List<Sentence> sentenceList);

}