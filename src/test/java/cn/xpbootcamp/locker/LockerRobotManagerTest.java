package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LockerRobotManagerTest {

    @Test
    void should_get_ticket_given_2_locker_and_0_robot_when_deposit_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers = List.of(new Locker(3L), new Locker(4L));
        lockerRobotManager.setLocker(lockers);
        assertThat(lockerRobotManager.deposit(new Bag())).isNotNull();
    }
}
