package com.developersam.web.devsuit.tags.basis;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * It is an abstract subclass of CustomTag.
 * This type of custom tag does not prints html blocks.
 * It prints lines of code that is often reused with few modifications, for example, head part of html.
 */
public abstract class TemplateTag extends CustomTag {

    @Override
    public abstract void doTag() throws JspException, IOException;
}
