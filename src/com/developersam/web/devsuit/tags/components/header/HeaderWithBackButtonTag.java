package com.developersam.web.devsuit.tags.components.header;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * The tag prints the header with a back button.
 */
public class HeaderWithBackButtonTag extends HeaderWithoutDrawerTag {

    private String navButtonURL;

    /**
     * Set the page that the back button links to
     * @param navButtonURL page to be sent back
     */
    public void setNavButtonURL(String navButtonURL) {
        this.navButtonURL = navButtonURL;
    }

    /**
     * The custom nav button has a link
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    @Override
    protected void printCustomNavButton() throws JspException, IOException {
        String classes = "mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon mdl-layout__drawer-button";
        printContent("<a class=\"" + classes + "\" href=\"" + navButtonURL + "\">");
        printContent("<i class=\"material-icons\">arrow_back</i>");
        printContent("</a>");
    }
}
