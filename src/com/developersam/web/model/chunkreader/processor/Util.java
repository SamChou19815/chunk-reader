package com.developersam.web.model.chunkreader.processor;

import com.developersam.web.model.chunkreader.google.objects.Sentiment;

/**
 * @author Vivian
 *
 * Put all helper methods here
 */
public class Util {

    public Util () {
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
    public static int getSentimentScore(Sentiment sentiment) {
        double score = sentiment.score;
        double magnitude = sentiment.magnitude;
        int sentimentScore = 0;

        /*
         * score:
         * < -0.2 ==> negative
         * -0.2 <= score < 0.2 ==> neutral
         * >= 0.2 ==> positive
         *
         * magnitude:
         * < 2 ==> slightly
         * >= 2 ==> strongly
         */

//        /*
//         * if length != 1, then it is an article
//         * log the length to process the magnitude
//         */
//        if (length != 1) {
//            magnitude = Math.log(length);
//        }

        /*
         * decide the type
         * 1: neutral weak = mixed
         * 2: neutral strong = neutral
         * 3: neg weak
         * 4: neg strong
         * 5: pos weak
         * 6: pos strong
         */
        if (score < -0.2) {
            if (magnitude < 2) {
                sentimentScore = 3;
            }
            else {
                sentimentScore = 4;
            }
        }
        else if (score < 0.2) {
            if (magnitude < 2) {
               sentimentScore = 1;
            }
            else {
                sentimentScore = 2;
            }
        }
        else {
            if (magnitude < 2) {
                sentimentScore = 5;
            }
            else {
                sentimentScore = 6;
            }
        }



        return sentimentScore;
    }
}
