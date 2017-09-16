package com.developersam.web.devsuit.tags.components.input;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A textarea input that accepts line breaks.
 * It is a material design input component.
 */
public class TextAreaInputTag extends TextInputTag {

    private int rows;

    /**
     * Set the initial rows of the textarea input
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    protected void printInput() throws JspException, IOException {
        printContent("<textarea class=\"mdl-textfield__input\" type=\"text\" rows=\"" + rows + "\"");
        printContent(idStringForInput + ">");
        printContent("</textarea>");
    }
}