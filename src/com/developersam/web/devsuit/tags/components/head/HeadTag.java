package com.developersam.web.devsuit.tags.components.head;

import com.developersam.web.devsuit.tags.basis.TemplateTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * The tag prints head of a web page, with a customizable title.
 */
public class HeadTag extends TemplateTag {

    private String title;

    /**
     * Set the title that appears on the top of browser.
     * @param title title that can be dynamically calculated.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void doTag() throws JspException, IOException {
        printContent("<meta charset=\"UTF-8\">");
        printContent("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        printContent("<meta name=\"description\" content=\"Official Website of Sam\">");
        printContent("<title>" + title +"</title>");
        printContent("<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />");
        printContent("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.3.0/material.deep_orange-red.min.css\" />");
        printContent("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" />");
        printContent("<link rel=\"stylesheet\" href=\"/framework/css/base.css\" />");
        printContent("<script defer src=\"https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.3.0/material.min.js\"></script>");
        printContent("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>");
        printContent("<link rel=\"shortcut icon\" href=\"/favicon.ico?v=1\" />");
    }
}