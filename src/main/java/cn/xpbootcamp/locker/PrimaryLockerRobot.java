package cn.xpbootcamp.locker;

import java.util.List;

public class PrimaryLockerRobot {
    private List<Locker> lockers;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket deposit(Bag bag) {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.deposit(bag);
            }
        }
        throw new DepositBagFailedException();
    }

    public Bag take(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.existedTicket(ticket)) {
                return locker.take(ticket);
            }
        }
        throw new InvalidTicketException();
    }
}
