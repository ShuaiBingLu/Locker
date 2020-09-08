package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;

import java.util.Comparator;

public class SmartLockerRobot extends LockerRobotBase {
    @Override
    public Ticket deposit(Bag bag) {
        return this.lockers.stream().max(Comparator.comparing(Locker::getAvailableCapability))
                .map(locker -> locker.deposit(bag)).orElseThrow(DepositBagFailedException::new);
    }
}
