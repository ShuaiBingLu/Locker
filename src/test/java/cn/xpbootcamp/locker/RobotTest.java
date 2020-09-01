package cn.xpbootcamp.locker;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RobotTest {

    PrimaryLockerRobot primaryLockerRobot;

    @BeforeEach
    void setup() {
        primaryLockerRobot = new PrimaryLockerRobot();
    }

    @Test
    void should_get_ticket_given_1_locker_and_locker_not_full_when_deposit_bag() {

        //Given
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
        List<Locker> lockerList = new ArrayList<>();
        lockerList.add(new Locker(1L));
        primaryLockerRobot.setLocker(lockerList);

        primaryLockerRobot.deposit(new Bag());

        //When Then
        assertThatThrownBy(() -> primaryLockerRobot.deposit(new Bag())).isInstanceOf(DepositBagFailedException.class);
    }

    @Test
    void should_in_first_locker_and_return_ticket_given_2_locker_and_all_locker_is_not_full_when_deposit_bag() {

        //Given
        List<Locker> lockerList = new ArrayList<>();
        lockerList.add(new Locker(3L));
        lockerList.add(new Locker(4L));

        primaryLockerRobot.setLocker(lockerList);

        //When
        Ticket ticket = primaryLockerRobot.deposit(new Bag());

        //Then
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_in_second_locker_and_return_ticket_given_2_locker_and_first_locker_is_full_when_deposit_bag() {

        //Given
        List<Locker> lockerList = new ArrayList<>();
        Locker firstLocker = new Locker(1L);
        Locker secondLocker = new Locker(4L);
        lockerList.add(firstLocker);
        lockerList.add(secondLocker);
        primaryLockerRobot.setLocker(lockerList);

        primaryLockerRobot.deposit(new Bag());

        //When
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.deposit(bag);

        //Then
        assertThat(bag).isEqualTo(secondLocker.take(ticket));
    }

    @Test
    void should_return_locker_is_full_given_two_locker_and_all_is_full_when_deposit_bag() {

        //Given
        List<Locker> lockerList = new ArrayList<>();
        Locker firstLocker = new Locker(1L);
        Locker secondLocker = new Locker(1L);
        lockerList.add(firstLocker);
        lockerList.add(secondLocker);
        primaryLockerRobot.setLocker(lockerList);

        primaryLockerRobot.deposit(new Bag());
        primaryLockerRobot.deposit(new Bag());

        //When
        //Then
        assertThatThrownBy(() -> primaryLockerRobot.deposit(new Bag())).isInstanceOf(DepositBagFailedException.class);
    }

    @Test
    void should_return_ticket_given_two_locker_and_one_ticket_when_take_bag() {

        //Given
        List<Locker> lockerList = new ArrayList<>();
        Locker firstLocker = new Locker(1L);
        Locker secondLocker = new Locker(1L);
        lockerList.add(firstLocker);
        lockerList.add(secondLocker);
        primaryLockerRobot.setLocker(lockerList);
        Bag bag = new Bag();
        Ticket ticket = primaryLockerRobot.deposit(bag);

        //Then
        assertThat(primaryLockerRobot.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_return_ticket_given_two_locker_and_wrong_ticket_when_take_bag() {

        //Given
        List<Locker> lockerList = new ArrayList<>();
        Locker firstLocker = new Locker(1L);
        Locker secondLocker = new Locker(1L);
        lockerList.add(firstLocker);
        lockerList.add(secondLocker);
        primaryLockerRobot.setLocker(lockerList);
        primaryLockerRobot.deposit(new Bag());

        //Then
        assertThatThrownBy(() -> primaryLockerRobot.take(new Ticket())).isInstanceOf(InvalidTicketException.class);
    }
}
