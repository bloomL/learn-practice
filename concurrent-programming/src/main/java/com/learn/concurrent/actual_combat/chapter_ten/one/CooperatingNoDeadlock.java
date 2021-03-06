package com.learn.concurrent.actual_combat.chapter_ten.one;

import com.learn.concurrent.actual_combat.chapter_four.Point;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Using open calls to avoiding deadlock between cooperating objects
 *
 * Create by liguo on 2021/11/19
 **/
public class CooperatingNoDeadlock {
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

        public synchronized void setLocation(Point location) {
            boolean reachDestination;
            synchronized (this) {
                this.location = location;
                reachDestination = location.equals(destination);
            }
            if (reachDestination) {
                dispatcher.notifyAvailable(this);
            }
        }

        public synchronized Point getDestination() {
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
            this.taxis = new HashSet<>();
            this.availableTaxis = new HashSet<>();
        }

        public synchronized void notifyAvailable(Taxi taxi) {
            availableTaxis.add(taxi);
        }

        public Image getImage() {
            Set<Taxi> copy;
            synchronized (this) {
                copy = new HashSet<>(taxis);
            }
            Image image = new Image();
            for (Taxi taxi : copy) {
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
