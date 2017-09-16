package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Entity;
import com.google.cloud.language.v1beta2.Sentence;
import com.google.cloud.language.v1beta2.Token;

import java.util.List;

public interface SummaryGenerator extends Processor {

    void read(List<Entity> entityList,
              List<Sentence> sentenceList,
              List<Token> tokenList);

}