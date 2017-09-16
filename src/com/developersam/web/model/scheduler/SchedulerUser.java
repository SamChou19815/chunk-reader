package com.developersam.web.model.scheduler;

import com.developersam.web.model.datastore.DataStoreObject;
import com.developersam.web.model.mail.ServerEmail;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * A user of the scheduler app.
 */
public class SchedulerUser extends DataStoreObject {

    private Entity userEntity;
    private String userEmail;
    private boolean emailNotificationEnabled;

    /**
     * Construct the object by fetching the logged in user.
     */
    public SchedulerUser() {
        super("SchedulerUser");
        this.userEmail = UserServiceFactory.getUserService().getCurrentUser().getEmail();
        this.userEntity = getSchedulerUserEntityByUserEmail(userEmail);
        this.emailNotificationEnabled = (boolean) userEntity.getProperty("emailNotificationEnabled");
    }

    /**
     * Construct the object by a user entity in scheduler app.
     * @param userEntity user entity of scheduler user
     */
    SchedulerUser(Entity userEntity) {
        super("SchedulerUser");
        this.userEmail = (String) userEntity.getProperty("userEmail");
        this.userEntity = userEntity;
        this.emailNotificationEnabled = (boolean) userEntity.getProperty("emailNotificationEnabled");
    }

    /**
     * A helper method to obtain a new user entity or fetch an existing one given user's email.
     * @param userEmail user's email
     * @return an entity corresponding to the user email
     */
    private Entity getSchedulerUserEntityByUserEmail(String userEmail) {
        Filter filterUsername = new FilterPredicate("userEmail", Query.FilterOperator.EQUAL, userEmail);
        Query q = getQuery().setFilter(filterUsername);
        PreparedQuery pq = getPreparedQuery(q);
        Entity userEntity = pq.asSingleEntity();
        if (userEntity == null) {
            userEntity = getNewEntity();
            userEntity.setProperty("userEmail", userEmail);
            userEntity.setProperty("emailNotificationEnabled", false);
            putIntoDatabase(userEntity);
        }
        return userEntity;
    }

    public boolean isEmailNotificationEnabled() {
        return emailNotificationEnabled;
    }

    /**
     * Also sets the notification enabled in the database.
     * @param emailNotificationEnabled whether email notification is enabled
     */
    public void setEmailNotificationEnabled(boolean emailNotificationEnabled) {
        this.emailNotificationEnabled = emailNotificationEnabled;
        userEntity.setProperty("emailNotificationEnabled", emailNotificationEnabled);
        putIntoDatabase(userEntity);
    }

    /**
     * Send email notification to users, if they choose to.
     */
    void sendEmailNotification() {
        if (!emailNotificationEnabled) {
            return;
        }
        int numberOfUnfinishedSchedulerItems = new Scheduler().getNumberOfUnfinishedSchedulerItems(userEmail);
        ServerEmail email = new ServerEmail(userEmail, "Scheduler Daily Report",
                "You have " + numberOfUnfinishedSchedulerItems + " uncompleted items.");
        email.send();
    }

}