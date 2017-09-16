package com.developersam.web.devsuit.tags.components.card;

import com.developersam.web.devsuit.tags.basis.BlockTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * Card media, a component of material design card.
 */
public class CardMediaTag extends BlockTag {

    public CardMediaTag() {
        setPreDefinedClasses("mdl-card__media");
    }

    private String src;
    private String alt;

    /**
     * Set src of the img
     * @param src src
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Set alt of the img
     * @param alt alt
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printContent("<img src=\"" + src +"\" alt=\"" + alt + "\" style=\"padding:10px;width:90%\" />"); // image
    }

}