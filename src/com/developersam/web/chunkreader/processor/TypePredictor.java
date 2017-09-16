package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Sentence;

public interface TypePredictor extends Processor {

    void read(Sentence sentence);

}