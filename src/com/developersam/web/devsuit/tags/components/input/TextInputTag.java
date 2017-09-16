package com.developersam.web.devsuit.tags.components.input;

import javax.servlet.jsp.JspException;
import java.io.IOException;

public abstract class TextInputTag extends InputTag {

    public TextInputTag() {
        setPreDefinedClasses("mdl-textfield mdl-js-textfield");
    }

    @Override
    protected void printWrapperStart() throws JspException, IOException {
        super.printWrapperStart();
        printInput();
        printContent("<label class=\"mdl-textfield__label\"" + forString + ">");
    }

    @Override
    protected void printWrapperEnd() throws JspException, IOException {
        printContent("</label>");
        super.printWrapperEnd();
    }
}