package com.developersam.web.view.tags.scheduler;

import com.developersam.web.devsuit.tags.components.button.LinkButtonTag;
import com.developersam.web.devsuit.tags.components.card.CardActionsTag;
import com.developersam.web.devsuit.tags.components.card.CardTag;
import com.developersam.web.devsuit.tags.components.card.CardTextTag;
import com.developersam.web.model.scheduler.SchedulerUser;

import javax.servlet.jsp.JspException;
import java.io.IOException;

/**
 * A card to change settings regarding Scheduler app.
 */
public class SchedulerSettingsCardTag extends CardTag {

    private SchedulerUser schedulerUser;

    public SchedulerSettingsCardTag() {
        setTitle("Settings");
    }

    @Override
    protected void printBodyContent() throws JspException, IOException {
        printTitle();
        printCommands();
    }

    public void setSchedulerUser(SchedulerUser schedulerUser) {
        this.schedulerUser = schedulerUser;
    }

    /**
     * Print commands to change settings.
     * @throws JspException jsp exception
     * @throws IOException io exception
     */
    private void printCommands()  throws JspException, IOException {
        String terms;
        if (schedulerUser.isEmailNotificationEnabled()) {
            terms = "By choosing 'DISABLE EMAIL NOTIFICATION', you will stop receiving daily email reports.";
        }else {
            terms = "By choosing 'ENABLE EMAIL NOTIFICATION', you will receive daily email reports.";
        }
        CardTextTag cardTextTag = new CardTextTag();
        cardTextTag.setParent(this);
        cardTextTag.setBodyContent(terms);
        cardTextTag.doTag();
        // terms and conditions ends
        CardActionsTag cardActionsTag = new CardActionsTag();
        cardActionsTag.setParent(this);
        LinkButtonTag linkButtonTag = new LinkButtonTag();
        linkButtonTag.setHref("#");
        linkButtonTag.setOpenInNewTab(false);
        if (schedulerUser.isEmailNotificationEnabled()) {
            linkButtonTag.setOnClick("Controller.setEmailNotificationSwitch(false);");
            linkButtonTag.setBodyContent("Disable email notification");
        }else {
            linkButtonTag.setOnClick("Controller.setEmailNotificationSwitch(true);");
            linkButtonTag.setBodyContent("Enable email notification");
        }
        cardActionsTag.addChildrenTag(linkButtonTag);
        cardActionsTag.doTag();
    }

}