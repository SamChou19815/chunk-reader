package com.developersam.web.devsuit.tags.basis;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * It is the basic form of custom tag.
 * It only provides basic support to print content easily.
 * It has two direct subclasses: BlockTag and TemplateTag.
 */
public abstract class CustomTag extends SimpleTagSupport {

    /**
     * Print content to jsp.
     * @param content content in html
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected void printContent(String content) throws JspException, IOException {
        getJspContext().getOut().print(content);
    }

    /**
     * Subclass must override this method.
     * It must be ensured that doTag only outputs content through printContent.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    @Override
    public abstract void doTag() throws JspException, IOException;
}