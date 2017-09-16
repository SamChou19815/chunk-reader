package com.developersam.web.devsuit.tags.components.card;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Card title with a link to other page.
 */
public class CardTitleLinkedTag extends CardTitleTag {

    private String url;

    /**
     * Set where the title link sends to.
     * @param url another page to be sent to.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * It overrides the superclass printTitle to give the title a link
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    @Override
    protected void printTitle() throws JspException, IOException {
        printContent("<a href=\"" + url + "\" class=\"title-link\">" + title + "</a>");
    }

}