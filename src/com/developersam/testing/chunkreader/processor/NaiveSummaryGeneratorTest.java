package com.developersam.testing.chunkreader.processor;

import com.developersam.web.chunkreader.processor.summary.NaiveSummaryGenerator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class NaiveSummaryGeneratorTest {
    @Test
    public void getEvaluatedSentences() throws Exception {

    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(NaiveSummaryGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
