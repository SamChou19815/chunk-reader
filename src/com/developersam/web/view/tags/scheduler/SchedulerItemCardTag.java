package com.developersam.web.view.tags.scheduler;

import com.developersam.web.devsuit.tags.components.button.LinkButtonTag;
import com.developersam.web.devsuit.tags.components.card.CardActionsTag;
import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextBorderedTag;
import com.developersam.web.model.scheduler.SchedulerItem;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A card consists of all the information of a scheduler item.
 */
public class SchedulerItemCardTag extends CardTag {

    private SchedulerItem schedulerItem;

    /**
     * Initialize the tag by binding a scheduler item object to it.
     * The object will contain sufficient info to be displayed properly.
     * @param schedulerItem a scheduler item
     */
    public void setSchedulerItem(SchedulerItem schedulerItem) {
        this.schedulerItem = schedulerItem;
        setId("scheduler-item-"+schedulerItem.getKeyString());
        setTitle(schedulerItem.getDescription());
        int daysLeft = schedulerItem.getDaysLeft();
        if (daysLeft == 1) {
            setTitleColor("mdl-color--red-400");
        }else if (daysLeft <= 3) {
            setTitleColor("mdl-color--deep-orange-400");
        }else if (daysLeft <= 6) {
            setTitleColor("mdl-color--orange-400");
        }else {
            setTitleColor("mdl-color--green-400");
        }
        if (schedulerItem.isCompleted()) {
            addAdditionalAttributeString(" style='opacity: 0.5'"); // completed vs uncompleted
        }
    }

    /**
     * Print the deadline of the scheduler item in a nice manner.
     * @throws JspException error
     * @throws IOException error
     */
    private void printSchedulerItemContent() throws JspException, IOException {
        CardTextBorderedTag cardTextTagForDeadline = new CardTextBorderedTag();
        cardTextTagForDeadline.setParent(this);
        cardTextTagForDeadline.setBodyContent("Deadline: " + schedulerItem.getDeadline());
        cardTextTagForDeadline.doTag();
        CardTextBorderedTag cardTextTagForDaysLeft = new CardTextBorderedTag();
        cardTextTagForDaysLeft.setParent(this);
        cardTextTagForDaysLeft.setBodyContent("Days Left: " + schedulerItem.getDaysLeft());
        cardTextTagForDaysLeft.doTag();
    }

    /**
     * Print the buttons of the scheduler items.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    private void printActions() throws JspException, IOException {
        CardActionsTag cardActionsTag = new CardActionsTag();
        cardActionsTag.setParent(this);
        LinkButtonTag linkButtonDeleteTag = new LinkButtonTag();
        linkButtonDeleteTag.setHref("#");
        linkButtonDeleteTag.setOpenInNewTab(false);
        linkButtonDeleteTag.setOnClick("Controller.deleteItem('" + schedulerItem.getKeyString() + "');");
        linkButtonDeleteTag.setBodyContent("Delete");
        cardActionsTag.addChildrenTag(linkButtonDeleteTag);
        LinkButtonTag linkButtonMarkTag = new LinkButtonTag();
        linkButtonMarkTag.setHref("#");
        linkButtonMarkTag.setOpenInNewTab(false);
        boolean completed = schedulerItem.isCompleted();
        String key = schedulerItem.getKeyString();
        linkButtonMarkTag.setOnClick("Controller.changeCompletionStatusOfAnItem('" + key + "', " + !completed + ");");
        if (completed) {
            linkButtonMarkTag.setBodyContent("Mark as uncompleted");
        }else {
            linkButtonMarkTag.setBodyContent("Mark as completed");
        }
        cardActionsTag.addChildrenTag(linkButtonMarkTag);
        cardActionsTag.doTag();
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printSchedulerItemContent();
        printActions();
    }
}