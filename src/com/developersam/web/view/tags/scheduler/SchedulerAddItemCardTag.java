package com.developersam.web.view.tags.scheduler;

import com.developersam.web.devsuit.tags.components.button.LinkButtonTag;
import com.developersam.web.devsuit.tags.components.card.CardActionsTag;
import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.devsuit.tags.components.input.LineInputDateTag;
import com.developersam.web.devsuit.tags.components.input.LineInputTag;
import com.developersam.web.devsuit.tags.components.loading.ProgressBarIntermediateTag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A card to add new scheduler items into database.
 */
public class SchedulerAddItemCardTag extends CardTag {

    public SchedulerAddItemCardTag() {
        setTitle("Add a New Item");
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printAddComment();
    }

    /**
     * Print add item card
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    private void printAddComment()  throws JspException, IOException {
        // print add item box
        CardTextTag cardTextTag = new CardTextTag();
        cardTextTag.setParent(this);
        LineInputTag lineInputTagForDescription = new LineInputTag();
        lineInputTagForDescription.setId("item-description");
        lineInputTagForDescription.setBodyContent("Any project.");
        LineInputDateTag lineInputTagForDeadline = new LineInputDateTag();
        lineInputTagForDeadline.setId("item-deadline");
        ProgressBarIntermediateTag progressBarIntermediateTag = new ProgressBarIntermediateTag();
        progressBarIntermediateTag.setId("progress-add-item");
        cardTextTag.addChildrenTag(lineInputTagForDescription);
        cardTextTag.addChildrenTag(lineInputTagForDeadline);
        cardTextTag.addChildrenTag(progressBarIntermediateTag);
        cardTextTag.doTag();
        // print submit button
        CardActionsTag cardActionsTag = new CardActionsTag();
        cardActionsTag.setParent(this);
        LinkButtonTag linkButtonTag = new LinkButtonTag();
        linkButtonTag.setHref("#");
        linkButtonTag.setOpenInNewTab(false);
        linkButtonTag.setOnClick("Controller.addItem();");
        linkButtonTag.setBodyContent("Add a new item");
        cardActionsTag.addChildrenTag(linkButtonTag);
        cardActionsTag.doTag();
    }

}