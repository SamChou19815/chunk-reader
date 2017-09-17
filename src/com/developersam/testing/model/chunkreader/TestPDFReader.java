package com.developersam.testing.model.chunkreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class TestPDFReader {
  public static void main(String[] args) throws IOException {

            try (PDDocument document = PDDocument.load(new File("/Users/Administrator/Downloads/a3.pdf"))) {

                document.getClass();

                if (!document.isEncrypted()) {

                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);

                    PDFTextStripper tStripper = new PDFTextStripper();

                    String pdfFileInText = tStripper.getText(document);
                    //System.out.println("Text:" + st);

                    // split by whitespace
                    String lines[] = pdfFileInText.split("\\r?\\n");
                    for (String line : lines) {
                        System.out.println(line);
                    }

                }

            }

        }

}
