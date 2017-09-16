package com.developersam.web.chunkreader.processor;

import com.google.cloud.language.v1beta2.Sentiment;

/**
 * @author Vivian
 *
 * Put all helper methods here
 */
public class util {

    public util () {
        //construct util object
    }

    /**
     *
     * Calculate sentiment score
     *
     * @param sentiment: sentiment object
     *        length: length of the article. if it is a word, length = 1
     * @return int sentiment score
     */
    public static int getSentimentScore(Sentiment sentiment, int length) {

        double score = sentiment.getScore();
        double magnitude = sentiment.getMagnitude();
        int sentimentScore = 0;

        /**
         * scoreType:
         * < -0.2 ==> negative
         * -0.2 <= score < 0.2 ==> neutral
         * >= 0.2 ==> positive
         *
         * magType:
         * < 2 ==> slightly
         * >= 2 ==> strongly
         */
        char magType = ' ';

        /**
         * if length != 1, then it is an article
         * log the length to process the magnitude
         */
        if (length != 1) {
            magnitude = Math.log(length);
        }

        /**
         * decide the type
         * 1: neutral weak = mixed
         * 2: neutral strong = neutral
         * 3: neg weak
         * 4: neg strong
         * 5: pos weak
         * 6: pos strong
         */
        if (score < -0.2) {
            if (magType < 2) {
                sentimentScore = 3;
            }
            else {
                sentimentScore = 4;
            }
        }
        else if (score < 0.2) {
            if (magType < 2) {
               sentimentScore = 0;
            }
            else {
                sentimentScore = 1;
            }
        }
        else {
            if (magType < 2) {
                sentimentScore = 5;
            }
            else {
                sentimentScore = 6;
            }
        }



        return sentimentScore;
    }
}
