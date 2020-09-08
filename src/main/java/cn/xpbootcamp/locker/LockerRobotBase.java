package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.InvalidTicketException;

import java.util.List;

public abstract class LockerRobotBase {

    public List<Locker> lockers;

    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public abstract Ticket deposit(Bag bag);

    public Bag take(Ticket ticket) {
        return lockers.stream().filter(locker -> !locker.isFull())
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(InvalidTicketException::new);
    }
}
