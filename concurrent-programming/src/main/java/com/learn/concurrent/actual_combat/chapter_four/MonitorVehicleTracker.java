package com.learn.concurrent.actual_combat.chapter_four;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆追踪器
 * Create by liguo on 2021/11/1
 **/
public class MonitorVehicleTracker {
    private final Map<String, MutablePoint> locations;

    public MonitorVehicleTracker(Map<String,MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String, MutablePoint> getLocations() {
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id) {
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id, int x, int y) {
        MutablePoint point = locations.get(id);
        if (point == null) {
            throw new IllegalArgumentException("no such id : " + id);
        }
        point.x = x;
        point.y = y;
    }

    private static Map<String, MutablePoint> deepCopy(Map<String, MutablePoint> pointMap) {
        Map<String, MutablePoint> result = new HashMap<>(pointMap.size());
        for (String id : pointMap.keySet()) {
            result.put(id, new MutablePoint(pointMap.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
