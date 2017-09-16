package com.developersam.web.devsuit.tags.components.input;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A line input that is only used to input dates.
 * It is NOT a material design input component.
 */
public class LineInputDateTag extends LineInputTag {

    @Override
    protected void printInput() throws JspException, IOException {
        printContent("<input class=\"mdl-textfield__input\" type=\"date\"" + idStringForInput + valueString + ">");
    }
}
