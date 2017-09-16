package com.developersam.web.model.scheduler;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.users.UserServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Scheduler extends DataStoreObject {

    public Scheduler() {
        super("SchedulerItem");
    }

    /**
     * Obtain a list of all scheduler items for a user signed in.
     * @return a list of scheduler items
     */
    public List<SchedulerItem> getAllSchedulerItems() {
        List<SchedulerItem> schedulerItems = new ArrayList<>();
        String userEmail = UserServiceFactory.getUserService().getCurrentUser().getEmail();
        Filter filterUser = new FilterPredicate("userEmail", FilterOperator.EQUAL, userEmail);
        Filter filterDeadline = new FilterPredicate("deadline", FilterOperator.GREATER_THAN, new Date());
        List<Boolean> trueAndFalse = new ArrayList<>(2);
        trueAndFalse.add(true);
        trueAndFalse.add(false);
        Filter filterCompleted = new FilterPredicate("completed", FilterOperator.IN, trueAndFalse);
        Filter filter = CompositeFilterOperator.and(filterCompleted, filterUser, filterDeadline);
        Query q = getQuery().
                setFilter(filter).
                addSort("completed", SortDirection.ASCENDING).
                addSort("deadline", SortDirection.ASCENDING);
        PreparedQuery pq = getPreparedQuery(q);
        for (Entity itemEntity: pq.asIterable()) {
            SchedulerItem schedulerItem = new SchedulerItem(itemEntity);
            schedulerItems.add(schedulerItem);
        }
        return schedulerItems;
    }

    /**
     * Obtain number of unfinished scheduler items for a user.
     * Used for sending email notification.
     * @param userEmail email address of the user
     * @return number of unfinished items
     */
    int getNumberOfUnfinishedSchedulerItems(String userEmail) {
        Filter filterUser = new FilterPredicate("userEmail", FilterOperator.EQUAL, userEmail);
        Filter filterDeadline = new FilterPredicate("deadline", FilterOperator.GREATER_THAN, new Date());
        Filter filter = CompositeFilterOperator.and(filterUser, filterDeadline);
        Query q = getQuery().setFilter(filter).setKeysOnly();;
        PreparedQuery pq = getPreparedQuery(q);
        return pq.asList(FetchOptions.Builder.withLimit(50)).size();
    }

    @Override
    protected SimpleDateFormat getDateFormatter() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        return formatter;
    }

    /**
     * Add a new scheduler item to the database.
     * @param description description of the scheduler item
     * @param deadline deadline of the scheduler item
     * @return whether the adding is successful
     */
    public boolean addItem(String description, String deadline) {
        if (description.equals("")) {
            return false;
        }
        try {
            Date deadlineDate = dateFormatter(deadline);
            if (deadlineDate.compareTo(new Date()) > 0) {
                new SchedulerItem(UserServiceFactory.getUserService().getCurrentUser().getEmail(),
                        description, deadlineDate);
                return true;
            }else {
                return false;
            }
        }catch (ParseException e) {
            return false;
        }
    }

    /**
     * Delete a scheduler item with a given key.
     * @param key key of the item to be deleted.
     */
    public void delete(String key) {
        new SchedulerItem(getEntityByKey(key)).delete();
    }

    /**
     * Change the completion status for a given scheduler item.
     * @param key key of the item
     * @param complete completion status
     */
    public void changeCompletionStatus(String key, boolean complete) {
        SchedulerItem schedulerItem = new SchedulerItem(getEntityByKey(key));
        if (complete) {
            schedulerItem.markAsCompleted();
        }else {
            schedulerItem.markAsUncompleted();
        }
    }

}