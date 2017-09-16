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
 */
public class NaiveSummaryGenerator extends AbstractSummaryGenerator {

    private int findSentenceID(List<AnnotatedSentence> annotatedSentenceList,
                               int pos) {
        int start = 0, end = annotatedSentenceList.size() - 1;
        while (true) {
            if (start == end) {
                return start;
            }
            int mid = (start + end) / 2;
            if (annotatedSentenceList.get(mid).getPosition() >
                    annotatedSentenceList.get(pos).getPosition()) {
                start = mid;
            } else {
                end = mid;
            }
        }
    }
    
    @Override
    protected List<AnnotatedSentence> getEvaluatedSentences() {
        List<AnnotatedSentence> annotatedSentenceList = new ArrayList<>();
        // Add all sentences into the list.
        for (Sentence sentence: sentenceList) {
            TextSpan textSpan = sentence.getText();
            AnnotatedSentence annotatedSentence = new AnnotatedSentence(
                    textSpan.getContent(), textSpan.getBeginOffset(),
                    0);
            annotatedSentenceList.add(annotatedSentence);
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
