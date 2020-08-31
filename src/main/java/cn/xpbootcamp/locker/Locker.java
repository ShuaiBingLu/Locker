package cn.xpbootcamp.locker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locker {
    private final Long capacity;

    private final Map<Ticket, Bag> bagTicketRelation;

    private final List<Ticket> alreadyExpiredTickets;

    public Locker(Long capacity) {
        this.capacity = capacity;
        this.alreadyExpiredTickets = new ArrayList<>();
        this.bagTicketRelation = new HashMap<>();
    }

    public boolean isFull() {
        return capacity <= bagTicketRelation.size();
    }

    public Ticket deposit(Bag bag) {
        if (bagTicketRelation.size() >= capacity) {
            throw new DepositBagFailedException();
        }
        Ticket ticket = new Ticket();
        bagTicketRelation.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        if (alreadyExpiredTickets.contains(ticket)) {
            throw new ExpiredTicketExcepiton();
        }
        Bag bag = getBagByTicket(ticket);
        alreadyExpiredTickets.add(ticket);
        bagTicketRelation.remove(ticket);
        return bag;
    }

    private Bag getBagByTicket(Ticket ticket) {
        if (!bagTicketRelation.containsKey(ticket)) {
            throw new InvalidTicketException();
        }
        return bagTicketRelation.get(ticket);
    }
}

