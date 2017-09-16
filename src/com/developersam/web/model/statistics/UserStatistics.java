package com.developersam.web.model.statistics;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;
import com.google.appengine.api.users.User;
import com.developersam.web.model.datastore.DataStoreObject;

/**
 * A statistics class designed to track google user's usage of an app.
 * It requires user to sign in to google account.
 */
public class UserStatistics extends DataStoreObject {

    private final String appName;

    /**
     * Construct a UserStatistics object by app name
     * An UserStatistics object is used only for one app only.
     * @param appName app name
     */
    public UserStatistics(String appName){
        super("UserStatistics");
        this.appName = appName;
    }

    /**
     * Given a user, increase his/her app usage frequency by 1.
     * @param user a google user
     */
    public void usagePlusOne(User user){
        Filter filterAppName = new FilterPredicate("appName", FilterOperator.EQUAL, appName);
        Filter filterUser = new FilterPredicate("user", FilterOperator.EQUAL, user.getNickname());
        Filter filter = CompositeFilterOperator.and(filterAppName, filterUser);
        Query q = getQuery().setFilter(filter);
        PreparedQuery pq = getPreparedQuery(q);
        Entity userUsageEntity = pq.asSingleEntity();
        if (userUsageEntity == null) {
            userUsageEntity = getNewEntity();
            userUsageEntity.setProperty("appName", appName);
            userUsageEntity.setProperty("user", user.getNickname());
            userUsageEntity.setProperty("frequency", 1);
        }else {
            long frequency = (long) userUsageEntity.getProperty("frequency");
            frequency++;
            userUsageEntity.setProperty("frequency", frequency);
        }
        putIntoDatabase(userUsageEntity);
    }

}