package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;
import cn.xpbootcamp.exception.ExpiredTicketExcepiton;
import cn.xpbootcamp.exception.InvalidTicketException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LockerTest {
    @Test
    void should_get_ticket_when_deposit_given_locker_is_not_full() {
        //Given

        Locker locker = new Locker(5L);
        Bag bag = new Bag();
        Ticket ticket = locker.deposit(bag);
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_get_ticket_when_deposit_given_locker_is_full() {
        //Given

        Locker locker = new Locker(1L);
        //when

        locker.deposit(new Bag());
        Bag bag = new Bag();
        //then

        assertThatThrownBy(() -> locker.deposit(bag)).isInstanceOf(DepositBagFailedException.class);
    }

    @Test
    void should_get_bag_when_take_given_current_ticket() {
        Locker locker = new Locker(1L);
        Bag bag = new Bag();
        Ticket ticket = locker.deposit(bag);
        assertThat(locker.take(ticket)).isEqualTo(bag);
    }

    @Test
    void should_get_bag_when_take_given_error_ticket() {
        Locker locker = new Locker(1L);
        Bag bag = new Bag();
        Ticket ticket = locker.deposit(bag);
        assertThatThrownBy(() -> locker.take(new Ticket())).isInstanceOf(InvalidTicketException.class);
    }

    @Test
    void should_get_bag_when_take_given_expired_ticket() {
        Locker locker = new Locker(1L);
        Bag bag = new Bag();
        Ticket ticket = locker.deposit(bag);
        locker.take(ticket);
        assertThatThrownBy(() -> locker.take(ticket)).isInstanceOf(ExpiredTicketExcepiton.class);

    }
}
