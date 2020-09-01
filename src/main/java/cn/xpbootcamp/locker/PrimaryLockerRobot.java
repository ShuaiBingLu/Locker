package cn.xpbootcamp.locker;

import java.util.List;
import java.util.Objects;

public class PrimaryLockerRobot {
    public Ticket deposit(Bag bag) {
        Ticket ticket = null;
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                ticket = locker.deposit(bag);
                break;
            }
        }
        if (Objects.isNull(ticket)) {
            throw new DepositBagFailedException();
        }
        return ticket;
    }

    private List<Locker> lockers;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Bag take(Ticket ticket) {
        Bag bag = null;
        for (Locker locker : lockers) {
            if (locker.existedTicket(ticket)) {
                bag = locker.take(ticket);
                break;
            }
        }
        if (Objects.isNull(bag)) {
            throw new InvalidTicketException();
        }
        return bag;
    }
}
