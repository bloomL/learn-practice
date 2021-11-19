package com.learn.concurrent.actual_combat.chapter_four;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于委托的车辆追踪器
 * Create by liguo on 2021/11/1
 **/
public class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locaions;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locaions =new ConcurrentHashMap<>(points);
        unmodifiableMap = Collections.unmodifiableMap(locaions);
    }

    public Map<String, Point> getSnapshotLocations() {
        return unmodifiableMap;
    }

    public Map<String, Point> getShallowLocations() {
        return Collections.unmodifiableMap(new HashMap<>(locaions));
    }

    public Point getLocation(String id) {
        return locaions.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locaions.replace(id, new Point(x, y)) == null) {
            throw new IllegalArgumentException("no vehicle name : " + id);
        }
    }
}
