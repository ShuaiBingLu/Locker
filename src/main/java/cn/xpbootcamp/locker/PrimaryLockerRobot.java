package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot {
    public Ticket deposit(Bag bag) {
        return lockers.get(0).deposit(bag);
    }

    private List<Locker> lockers;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }
}
