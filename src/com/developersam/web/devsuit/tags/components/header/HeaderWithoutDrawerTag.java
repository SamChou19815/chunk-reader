package com.developersam.web.devsuit.tags.components.header;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * The tag prints a header without drawer.
 * The tag is used usually in admin apps.
 */
public class HeaderWithoutDrawerTag extends HeaderTag {

    @Override
    public void doTag() throws JspException, IOException {
        printHeader();
    }
}
