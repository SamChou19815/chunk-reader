package com.developersam.web.devsuit.tags.components.input;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A check box input.
 * It is a material design input component.
 */
public class CheckboxInputTag extends InputTag {

    public CheckboxInputTag() {
        setTagName("label");
        setPreDefinedClasses("mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect");
    }

    @Override
    protected void printWrapperStart() throws JspException, IOException {
        super.printWrapperStart();
        printInput();
        printContent("<span class=\"mdl-checkbox__label\">");
    }

    @Override
    protected void printInput() throws JspException, IOException {
        printContent("<input type=\"checkbox\" class=\"mdl-checkbox__input\"" + idStringForInput + ">");
    }

    @Override
    protected void printWrapperEnd() throws JspException, IOException {
        printContent("</span>");
        super.printWrapperEnd();
    }

    @Override
    protected void initialize() {
        addAdditionalAttributeString(forString);
    }
}