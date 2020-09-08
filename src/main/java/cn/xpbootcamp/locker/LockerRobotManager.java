package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LockerRobotManager {
    private List<Locker> lockers = new ArrayList<>();

    private List<LockerRobotBase> lockerRobots = new ArrayList<>();

    public void setLocker(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket deposit(Bag bag) {
        Ticket ticket = lockerRobots.stream()
                .filter(lockerRobotBase -> lockerRobotBase.lockers.stream().anyMatch(locker -> !locker.isFull()))
                .findFirst()
                .map(lockerRobotBase -> lockerRobotBase.deposit(bag))
                .orElse(null);
        if (Objects.isNull(ticket) || lockerRobots.size() == 0) {
            return lockers.stream().filter(locker -> !locker.isFull()).findFirst()
                    .map(locker -> locker.deposit(bag)).orElseThrow(DepositBagFailedException::new);
        }
        return ticket;
    }

    public void setLockerRobot(List<LockerRobotBase> lockerRobots) {
        this.lockerRobots = lockerRobots;
    }
}
