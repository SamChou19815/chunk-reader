package com.developersam.testing.model.chunkreader.processor.summary;

import com.developersam.web.model.chunkreader.processor.ProcessorFactory;
import com.developersam.web.model.chunkreader.processor.summary.NaiveSummaryGenerator;
import org.junit.Test;

import java.io.IOException;

public class NaiveSummaryGeneratorTest {


    private ProcessorFactory factory;

    public NaiveSummaryGeneratorTest() {
        try {
            factory = new ProcessorFactory("Google, headquartered " +
                    "in Mountain View, unveiled the new Android phone at the " +
                    "Consumer Electronic Show.  Sundar Pichai said in his " +
                    "keynote that users love their new Android phones.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEvaluatedSentences() throws Exception {
        NaiveSummaryGenerator naiveSummaryGenerator =
                (NaiveSummaryGenerator) factory.createSummaryGenerator();
        naiveSummaryGenerator.process();
    }

}
