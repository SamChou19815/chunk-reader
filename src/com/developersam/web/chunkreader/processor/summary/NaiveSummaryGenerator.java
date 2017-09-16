package com.developersam.web.chunkreader.processor.summary;

import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.EntityMention;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.TextSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * A naive summary generator decides the importance of sentence simply by
 * summing up the salience of each keyword in it.
 * @author Sam.
 */
public class NaiveSummaryGenerator extends AbstractSummaryGenerator {

    private int findSentenceID(List<AnnotatedSentence> annotatedSentenceList,
                               int pos) {
        /*
        // Wrong binary search method, may fix in the future.
        int start = 0, end = annotatedSentenceList.size() - 1;
        while (true) {
            if (end - start <= 1) {
                return start;
            }
            int mid = (start + end) / 2;
            int midPos = annotatedSentenceList.get(mid).getPosition();
            if (pos < midPos) {
                end = mid;
            } else if (pos == midPos) {
                return mid;
            } else {
                start = mid;
            }
        }
        */
        for (int i = 0; i < annotatedSentenceList.size(); i++) {
            int sentencePos = annotatedSentenceList.get(i).getPosition();
            if (sentencePos == pos) {
                return i;
            } else if (sentencePos > pos) {
                return i-1;
            }
        }
        return annotatedSentenceList.size()-1;
    }

    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        List<AnnotatedSentence> annotatedSentenceList = new ArrayList<>();
        // Add all sentences into the list.
        for (Sentence sentence: sentenceList) {
            annotatedSentenceList.add(
                    new AnnotatedSentence(parentKey, sentence, 0));
        }
        // Increase values of salience of each sentence.
        for (Entity entity: entityList) {
            double salience = entity.getSalience();
            for (EntityMention mention: entity.getMentionsList()) {
                int pos = mention.getText().getBeginOffset();
                annotatedSentenceList
                        .get(findSentenceID(annotatedSentenceList, pos))
                        .increaseSalience(salience);
            }
        }
        return annotatedSentenceList;
    }

}
