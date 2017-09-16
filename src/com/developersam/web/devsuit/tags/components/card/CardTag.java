package com.developersam.web.devsuit.tags.components.card;

import com.developersam.web.devsuit.tags.basis.BlockTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A card with or without title.
 * The card is decorated with material design.
 */
public class CardTag extends BlockTag {

    public CardTag() {
        setPreDefinedClasses("mdl-card mdl-shadow--2dp");
    }

    private String title;
    private String titleID;
    private String titleColorCSS;

    /**
     * Set title of the card.
     * The title will be in big font size and appears first.
     * @param title title of the card
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set the id of the title.
     * It enables Javascript to change the content of the title.
     * @param titleID id of title div
     */
    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    /**
     * Set the color for CSS, useful for customization.
     * Only used for customization.
     * @param colorClass color css for title
     */
    protected void setTitleColor(String colorClass) {
        titleColorCSS = colorClass;
    }

    /**
     * Print the title card
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    protected void printTitle() throws JspException, IOException {
        CardTitleTag cardTitleTag = new CardTitleTag();
        cardTitleTag.setTitle(title);
        if (titleID != null) {
            cardTitleTag.setId(titleID);
        }
        if (titleColorCSS != null) {
            cardTitleTag.setCustomClasses(titleColorCSS);
            cardTitleTag.addAdditionalAttributeString(" style='color:white'");
        }
        cardTitleTag.setParent(this);
        cardTitleTag.doTag();
    }

    /**
     * Print the body content.
     * First prints the title if it exist.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    @Override
    protected void printBodyContent() throws JspException, IOException {
        if (title != null) {
            /*
                In default, a standard card has a standard title.
                Engineers can also choose to make title null and add 2 or more title components in jsp.
             */
            printTitle();
        }
        super.printBodyContent();
    }

}