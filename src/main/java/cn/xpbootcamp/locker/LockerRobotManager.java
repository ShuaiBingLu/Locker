package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;

import java.util.List;

public class LockerRobotManager {
    private List<Locker> lockers;

    private List<LockerRobotBase> lockerRobots;

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket deposit(Bag bag) {
        if (lockerRobots.size() > 0) {
            return lockerRobots.get(0).deposit(bag);
        }
        return lockers.stream().filter(locker -> !locker.isFull()).findFirst()
                .map(locker -> locker.deposit(bag)).orElseThrow(DepositBagFailedException::new);
    }

    public void setLockerRobot(List<LockerRobotBase> lockerRobots) {
        this.lockerRobots = lockerRobots;
    }
}
