package com.developersam.web.model.statistics;

import com.developersam.web.model.datastore.DataStoreObject;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.*;

/**
 * A statistics class designed to track unique visitor ip and their frequency.
 * It does not require Google Account sign in
 */
public class IPStatistics extends DataStoreObject {

    private final String appName;
    private final String appContent;
    protected final Filter filter;

    /**
     * Construct the class by app name and its content
     * @param appName name of app to log statistics
     * @param appContent direct to a specific content of the app
     */
    public IPStatistics(String appName, String appContent){
        super("IPStatistics");
        this.appName = appName;
        this.appContent = appContent;
        this.filter = getGenericFilter();
    }

    /**
     * Obtain a generic filter without ip
     * @return filter without ip
     */
    private Filter getGenericFilter() {
        Filter filterAppName = new FilterPredicate("appName", FilterOperator.EQUAL, appName);
        Filter filterAppContent = new FilterPredicate("appContent", FilterOperator.EQUAL, appContent);
        return CompositeFilterOperator.and(filterAppName, filterAppContent);
    }

    /**
     * Obtain a filter combined ip with generic filter
     * @param ip ip address
     * @return combined filter
     */
    private Filter getFilterWithIP(String ip) {
       Filter filterIP = new FilterPredicate("ip", FilterOperator.EQUAL, ip);
        return CompositeFilterOperator.and(filterIP, filter);
    }

    /**
     * Given an ip address, increase its visiting frequency by 1.
     * @param ip ip address
     */
    public void usagePlusOne(String ip){
        Query q = getQuery().setFilter(getFilterWithIP(ip));
        PreparedQuery pq = getPreparedQuery(q);
        Entity ipVisitEntity = pq.asSingleEntity();
        if (ipVisitEntity == null) {
            ipVisitEntity = getNewEntity();
            ipVisitEntity.setProperty("appName", appName);
            ipVisitEntity.setProperty("appContent", appContent);
            ipVisitEntity.setProperty("ip", ip);
            ipVisitEntity.setProperty("frequency", 1);
        }else {
            long frequency = (long) ipVisitEntity.getProperty("frequency");
            frequency++;
            ipVisitEntity.setProperty("frequency", frequency);
        }
        putIntoDatabase(ipVisitEntity);
    }

}