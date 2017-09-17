package com.developersam.testing.model.chunkreader.processor.knowledge;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class KnowledgeGraphBuilderImpTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(com.developersam.web.model.chunkreader.processor.knowledge.KnowledgeGraphBuilderImp.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }



}
