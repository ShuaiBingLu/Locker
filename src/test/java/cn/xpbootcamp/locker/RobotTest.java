package cn.xpbootcamp.locker;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RobotTest {

    @Test
    void should_get_ticket_given_1_locker_and_locker_not_full_when_deposit_bag() {

        //Given
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        List<Locker> lockerList = new ArrayList<>();
        lockerList.add(new Locker(5L));
        primaryLockerRobot.setLocker(lockerList);

        //When
        Ticket ticket = primaryLockerRobot.deposit(new Bag());

        //Then
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_locker_is_full_given_1_locker_and_locker_is_full_when_deposit_bag() {

        //Given
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        List<Locker> lockerList = new ArrayList<>();
        lockerList.add(new Locker(1L));
        primaryLockerRobot.setLocker(lockerList);

        primaryLockerRobot.deposit(new Bag());

        //When Then
        assertThatThrownBy(() -> primaryLockerRobot.deposit(new Bag())).isInstanceOf(DepositBagFailedException.class);
    }
}
