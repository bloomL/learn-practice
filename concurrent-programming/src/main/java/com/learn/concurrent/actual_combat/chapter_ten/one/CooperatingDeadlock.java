package com.learn.concurrent.actual_combat.chapter_ten.one;

import com.learn.concurrent.actual_combat.chapter_four.Point;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Lock-ordering deadlock between cooperating objects
 *
 * Create by liguo on 2021/11/19
 **/
public class CooperatingDeadlock {
    class Taxi {
        private Point location;
        private Point destination;
        private final Dispatcher dispatcher;

        public Taxi(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        public synchronized Point getLocation() {
            return location;
        }

        /**
         *  首先获取Taxi的锁，再获取Dispatcher的锁
         * @param location
         */
        public synchronized void setLocation(Point location) {
            this.location = location;
            if (location.equals(destination)) {
                dispatcher.notifyAvailable(this);
            }
        }

        public synchronized  Point getDestination() {
            return destination;
        }

        public synchronized void setDestination(Point destination) {
            this.destination = destination;
        }
    }

    class Dispatcher {
        private final Set<Taxi> taxis;
        private final Set<Taxi> availableTaxis;

        public Dispatcher() {
            taxis = new HashSet<>();
            availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        /**
         * 先获取Dispatcher的锁，再遍历获取获取每个出租车的锁；因此与setLocation会造成死锁
         */
        public synchronized Image getImage() {
            Image image = new Image();
            for (Taxi taxi : taxis) {
                image.drawMarker(taxi.getLocation());
            }
            return image;
        }
    }

    class Image {
        public void drawMarker(Point p) {
        }
    }
}
