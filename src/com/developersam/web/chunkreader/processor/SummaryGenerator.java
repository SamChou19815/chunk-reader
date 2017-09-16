package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.Token;

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
     * @param tokenList list of tokens.
     */
    void read(List<Entity> entityList,
              List<Sentence> sentenceList,
              List<Token> tokenList);

}