package com.developersam.web.devsuit.tags.components.card;

import com.developersam.web.devsuit.tags.basis.BlockTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Card title, a component of material design card.
 */
public class CardTitleTag extends BlockTag {

    public CardTitleTag() {
        setPreDefinedClasses("mdl-card__title mdl-card--border");
    }

    protected String title;

    /**
     * Set title text of the card title.
     * The title will be in big font size and appears first.
     * @param title title text of the title card
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Print the title.
     * It only prints the plain text.
     * Subclasses can override this to print more complex titles.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected void printTitle() throws JspException, IOException {
        printContent(title);
    }

    @Override
    protected void printWrapperStart() throws JspException, IOException {
        super.printWrapperStart();
        printContent("<h2 class=\"mdl-card__title-text\">"); // title css
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
    }

    @Override
    protected void printWrapperEnd() throws JspException, IOException {
        printContent("</h2>");
        super.printWrapperEnd();
    }

}