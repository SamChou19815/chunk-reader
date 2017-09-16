package com.developersam.web.devsuit.tags.components.input;

import com.developersam.web.devsuit.tags.basis.BlockTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * An abstract input tag with material design, which lets users to input things.
 */
public abstract class InputTag extends BlockTag {

    String idStringForInput;
    String forString;

    /**
     * Overridden to also set up for string in label.
     * @param id id of the input tag
     */
    @Override
    public void setId(String id) {
        idStringForInput = " id=\"" + id + "\"";
        forString = " for=\"" + id + "\"";
    }

    /**
     * Print input element.
     * Since there are multiple inputs, the method is abstract.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected abstract void printInput() throws JspException, IOException;

}