package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.exception.DepositBagFailedException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LockerRobotManagerTest {

    @Test
    void should_get_ticket_given_2_locker_and_0_robot_when_deposit_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers = List.of(new Locker(3L), new Locker(4L));
        lockerRobotManager.setLocker(lockers);
        assertThat(lockerRobotManager.deposit(new Bag())).isNotNull();
    }

    @Test
    void should_return_deposit_failed_given_two_full_locker_and_0_robot_when_deposit_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers = List.of(new Locker(0L), new Locker(0L));
        lockerRobotManager.setLocker(lockers);
        assertThatThrownBy(() -> lockerRobotManager.deposit(new Bag())).isInstanceOf(DepositBagFailedException.class);
    }
}
