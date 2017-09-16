package com.developersam.testing.chunkreader.processor;

import com.developersam.web.chunkreader.google.GoogleAnalyzer;
import com.developersam.web.chunkreader.processor.Processor;
import com.developersam.web.chunkreader.processor.ProcessorFactory;
import com.developersam.web.chunkreader.processor.summary.NaiveSummaryGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

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
