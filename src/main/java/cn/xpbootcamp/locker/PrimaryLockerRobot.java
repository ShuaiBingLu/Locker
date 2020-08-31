package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot {
    public Ticket deposit(Bag bag) {
        Ticket ticket = null;
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                ticket = locker.deposit(bag);
                break;
            }
        }
        return ticket;
    }

    private List<Locker> lockers;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }
}
