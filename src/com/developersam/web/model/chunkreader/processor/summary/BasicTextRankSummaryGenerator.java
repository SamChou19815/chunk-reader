package com.developersam.web.model.chunkreader.processor.summary;

import java.util.HashSet;

public class BasicTextRankSummaryGenerator extends TextRankSummaryGenerator {

    @Override
    protected double calculateSimilarity(int s1ID, int s2ID) {
        HashSet<String> common = new HashSet<>();
        common.addAll(records.get(s1ID));
        common.addAll(records.get(s2ID));
        int s1Len = annotatedSentenceList.get(s1ID).getSentence().length();
        int s2Len = annotatedSentenceList.get(s2ID).getSentence().length();
        return 1.0 * common.size() / (Math.log(s1Len) + Math.log(s2Len));
    }
}
