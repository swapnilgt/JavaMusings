package com.example.swapnil;

/**
 * Created by swapnil on 4/1/17.
 * Test example to demonstrate deadlocks.
 */
public class Deadlock {
    private static class Friend {
        private final String name;
        Friend(String name) {
            this.name = name;
        }
        String getName() {
            return this.name;
        }
        synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }
        synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        new Thread(() -> {
             alphonse.bow(gaston);
        }).start();
        new Thread(() -> { gaston.bow(alphonse); }).start();
    }
}
