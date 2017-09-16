package com.developersam.web.chunkreader.processor.summary;

import com.developersam.web.chunkreader.processor.SummaryGenerator;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.Token;

import java.util.List;

abstract class AbstractSummaryGenerator implements SummaryGenerator {

    private List<Entity> entityList;
    private List<Sentence> sentenceList;
    private List<Token> tokenList;

    @Override
    public void read(List<Entity> entityList,
                     List<Sentence> sentenceList,
                     List<Token> tokenList) {
        this.entityList = entityList;
        this.sentenceList = sentenceList;
        this.tokenList = tokenList;
    }

    protected List<Entity> getEntityList() {
        return entityList;
    }

    protected List<Sentence> getSentenceList() {
        return sentenceList;
    }

    protected List<Token> getTokenList() {
        return tokenList;
    }

    protected abstract List<AnnotatedSentence> getEvaluatedSentences();

    @Override
    public void process() {
        List<AnnotatedSentence> sentenceList = getEvaluatedSentences();
        // TODO some code to throw sentence list into the database.
    }
}
