package com.developersam.web.devsuit.tags.components.input;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * An input that must be done in one line. No line breaks.
 * It is a material design input component.
 */
public class LineInputTag extends TextInputTag {

    String valueString = "";

    /**
     * Set the default value of the input.
     * It is highly recommended that the default value is a legit value.
     * @param defaultValue default value of input.
     */
    public void setDefaultValue(String defaultValue) {
        this.valueString = " value=\"" + defaultValue + "\"";
    }

    @Override
    protected void printInput() throws JspException, IOException {
        printContent("<input class=\"mdl-textfield__input\" type=\"text\"" + idStringForInput + valueString + ">");
    }
}