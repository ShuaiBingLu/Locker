package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;

public class PrimaryLockerRobot extends LockerRobotBase {
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
