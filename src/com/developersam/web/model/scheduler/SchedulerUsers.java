package com.developersam.web.model.scheduler;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

/**
 * Manages the collection of all the scheduler users in the database.
 */
public class SchedulerUsers extends DataStoreObject {

    public SchedulerUsers() {
        super("SchedulerUser");
    }

    /**
     * Send the notifications to all the users if they choose to have email notification.
     */
    public void sendEmailNotifications() {
        PreparedQuery pq = getPreparedQuery(getQuery());
        for (Entity userEntity: pq.asIterable()) {
            new SchedulerUser(userEntity).sendEmailNotification();
        }
    }

}