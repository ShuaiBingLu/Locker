package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;

import java.util.List;

public class PrimaryLockerRobot extends LockerRobotBase {
    private List<Locker> lockers;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }

    @Override
    public Ticket deposit(Bag bag) {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.deposit(bag);
            }
        }
        throw new DepositBagFailedException();
    }
}
