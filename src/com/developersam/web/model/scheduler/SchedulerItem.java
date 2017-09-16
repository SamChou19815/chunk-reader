package com.developersam.web.model.scheduler;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * An individual item in the scheduler.
 * It consists of description, deadline, and a completion status.
 */
public class SchedulerItem extends DataStoreObject {

    private Key key;

    private String description;
    private Date deadline;
    private boolean completed;

    /**
     * Used when fetching a new entity.
     * @param projectEntity a project entity
     */
    SchedulerItem(Entity projectEntity) {
        super("SchedulerItem");
        this.key = projectEntity.getKey();
        this.description = (String) projectEntity.getProperty("description");
        this.deadline = (Date) projectEntity.getProperty("deadline");
        this.completed = (boolean) projectEntity.getProperty("completed");
    }

    /**
     * Used when adding a new item record into database.
     * @param userEmail the email of the user who owns the item
     * @param description description of the item
     * @param deadline deadline of the item
     */
    SchedulerItem(String userEmail, String description, Date deadline) {
        super("SchedulerItem");
        Entity itemEntity = getNewEntity();
        itemEntity.setProperty("userEmail", userEmail);
        this.description = description;
        itemEntity.setProperty("description", description);
        this.deadline = deadline;
        itemEntity.setProperty("deadline", deadline);
        this.completed = false;
        itemEntity.setProperty("completed", false);
        putIntoDatabase(itemEntity);
    }

    /**
     * Obtain a unique id of the scheduler item object from data store.
     * @return key string
     */
    public String getKeyString() {
        return KeyFactory.keyToString(key);
    }

    public String getDescription() {
        return description;
    }

    /**
     * Obtain the string form of the deadline.
     * @return deadline for the scheduler item
     */
    public String getDeadline() {
        return dateFormatter(deadline);
    }

    /**
     * Calculate and obtain how many days left for the deadline.
     * @return days left
     */
    public int getDaysLeft() {
        Date timeNow = new Date();
        long diff = deadline.getTime() - timeNow.getTime();
        return (int) TimeUnit.MILLISECONDS.toDays(diff) + 1;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    protected SimpleDateFormat getDateFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return formatter;
    }

    /**
     * Delete the item from scheduler database.
     */
    public void delete() {
        removeFromDatabase(key);
    }

    /**
     * A helper method to change the complete status for an item.
     * It will directly read the complete status from this object.
     */
    private void changeCompleteStatus() {
        Entity existingItemEntity = getEntityByKey(key);
        existingItemEntity.setProperty("completed", completed);
        putIntoDatabase(existingItemEntity);
    }

    /**
     * Mark the item as completed.
     */
    void markAsCompleted() {
        completed = true;
        changeCompleteStatus();
    }

    /**
     * Mark the item as uncompleted.
     */
    void markAsUncompleted() {
        this.completed = false;
        changeCompleteStatus();
    }


}