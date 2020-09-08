package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;
import cn.xpbootcamp.exception.InvalidTicketException;
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

    @Test
    void should_get_ticket_and_in_primary_robot_given_1_primary_robot_can_deposit_bag_and_1_smart_robot_when_deposit_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers = List.of(new Locker(2L), new Locker(2L));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setLockers(lockers);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot();
        smartLockerRobot.setLockers(lockers);
        List<LockerRobotBase> lockerRobots = List.of(primaryLockerRobot, smartLockerRobot);
        lockerRobotManager.setLockerRobot(lockerRobots);
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.deposit(bag);

        assertThat(ticket).isNotNull();
        assertThat(primaryLockerRobot.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_get_ticket_and_in_smart_robot_given_1_primary_robot_can_deposit_bag_and_1_smart_robot_when_deposit_bag() {

        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers1 = List.of(new Locker(0L), new Locker(0L));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setLockers(lockers1);
        List<Locker> lockers2 = List.of(new Locker(2L), new Locker(2L));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot();
        smartLockerRobot.setLockers(lockers2);
        List<LockerRobotBase> lockerRobots = List.of(primaryLockerRobot, smartLockerRobot);
        lockerRobotManager.setLockerRobot(lockerRobots);
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.deposit(bag);

        assertThat(ticket).isNotNull();
        assertThat(smartLockerRobot.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_get_ticket_and_in_locker_given_1_primary_robot_can_not_deposit_bag_and_1_smart_robot_can_not_deposit_bag_when_deposit_bag() {
        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers1 = List.of(new Locker(0L), new Locker(0L));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setLockers(lockers1);
        List<Locker> lockers2 = List.of(new Locker(0L), new Locker(0L));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot();
        smartLockerRobot.setLockers(lockers2);
        List<LockerRobotBase> lockerRobots = List.of(primaryLockerRobot, smartLockerRobot);
        lockerRobotManager.setLockerRobot(lockerRobots);
        Locker locker = new Locker(1L);
        lockerRobotManager.setLocker(List.of(locker, new Locker(1L)));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.deposit(bag);

        assertThat(ticket).isNotNull();
        assertThat(locker.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_return_ticket_given_one_ticket_when_take_bag() {

        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers1 = List.of(new Locker(0L), new Locker(0L));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setLockers(lockers1);
        List<Locker> lockers2 = List.of(new Locker(0L), new Locker(0L));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot();
        smartLockerRobot.setLockers(lockers2);
        List<LockerRobotBase> lockerRobots = List.of(primaryLockerRobot, smartLockerRobot);
        lockerRobotManager.setLockerRobot(lockerRobots);
        Locker locker = new Locker(1L);
        lockerRobotManager.setLocker(List.of(locker, new Locker(1L)));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.deposit(bag);
        assertThat(lockerRobotManager.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_return_ticket_given_and_wrong_ticket_when_take_bag() {


        LockerRobotManager lockerRobotManager = new LockerRobotManager();
        List<Locker> lockers1 = List.of(new Locker(0L), new Locker(0L));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot();
        primaryLockerRobot.setLockers(lockers1);
        List<Locker> lockers2 = List.of(new Locker(0L), new Locker(0L));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot();
        smartLockerRobot.setLockers(lockers2);
        List<LockerRobotBase> lockerRobots = List.of(primaryLockerRobot, smartLockerRobot);
        lockerRobotManager.setLockerRobot(lockerRobots);
        Locker locker = new Locker(1L);
        lockerRobotManager.setLocker(List.of(locker, new Locker(1L)));
        Bag bag = new Bag();

        Ticket ticket = lockerRobotManager.deposit(bag);

        assertThatThrownBy(()->lockerRobotManager.take(new Ticket())).isInstanceOf(InvalidTicketException.class);
    }
}
