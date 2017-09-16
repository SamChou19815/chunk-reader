package com.developersam.web.chunkreader.processor.summary;

import com.developersam.web.chunkreader.processor.SummaryGenerator;
import com.google.appengine.api.datastore.Key;
import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractSummaryGenerator is responsible for provide some common
 * functionality for all implementations of summary generator.
 * @author Sam.
 */
abstract class AbstractSummaryGenerator implements SummaryGenerator {

    protected List<Entity> entityList;
    protected List<Sentence> sentenceList;
    protected List<Token> tokenList;

    protected Key parentKey;

    @Override
    public final void read(List<Entity> entityList,
                           List<Sentence> sentenceList,
                           List<Token> tokenList) {
        this.entityList = entityList;
        this.sentenceList = new ArrayList<>();
        this.sentenceList.addAll(sentenceList);
        /*
         * Sort sentences to ensure binary search.
         * I'm actually not sure about whether the sentence list is originally
         * sorted. Google's Doc does not say anything about that. It is written
         * as a line of protective code to ensure binary search of the list
         * can work.
         */
        this.sentenceList.sort((o1, o2) -> (Integer.compare(
                o1.getText().getBeginOffset(),
                o2.getText().getBeginOffset())
        ));
        this.tokenList = tokenList;
    }

    @Override
    public final void setParentKey(Key parentKey) {
        this.parentKey = parentKey;
    }

    /**
     * Subclass should override this method in a ways that returns a list of
     * annotated sentences with parentKey bounded to them.
     * @return list of annotate sentences.
     */
    protected abstract List<AnnotatedSentence> getEvaluatedSentences();

    @Override
    public final void process() {
        for (AnnotatedSentence annotatedSentence: getEvaluatedSentences()) {
            annotatedSentence.putIntoDatabase();
        }
    }

}
