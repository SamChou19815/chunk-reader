package com.developersam.web.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.google.objects.Entity;
import com.developersam.web.model.chunkreader.google.objects.Sentence;
import com.developersam.web.model.chunkreader.processor.SummaryGenerator;
import com.google.appengine.api.datastore.Key;

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

    protected Key parentKey;

    @Override
    public void read(List<Entity> entityList,
                           List<Sentence> sentenceList) {
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
                o1.textSpan.beginOffset,
                o2.textSpan.beginOffset)
        ));
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
