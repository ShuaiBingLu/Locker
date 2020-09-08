package cn.xpbootcamp.locker;

import cn.xpbootcamp.domain.Bag;
import cn.xpbootcamp.domain.Ticket;
import cn.xpbootcamp.exception.DepositBagFailedException;
import cn.xpbootcamp.exception.InvalidTicketException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final Long capacity;

    private final Map<Ticket, Bag> bagStore;

    public Locker(Long capacity) {
        this.capacity = capacity;
        this.bagStore = new HashMap<>();
    }

    public Long getAvailableCapability() {
        return this.capacity - bagStore.size();
    }

    public boolean isFull() {
        return bagStore.size() >= capacity;
    }

    public boolean existedTicket(Ticket ticket) {
        return bagStore.containsKey(ticket);
    }

    public Ticket deposit(Bag bag) {
        if (bagStore.size() >= capacity) {
            throw new DepositBagFailedException();
        }
        Ticket ticket = new Ticket();
        bagStore.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        Bag bag = getBagByTicket(ticket);
        bagStore.remove(ticket);
        return bag;
    }

    private Bag getBagByTicket(Ticket ticket) {
        if (!bagStore.containsKey(ticket)) {
            throw new InvalidTicketException();
        }
        return bagStore.get(ticket);
    }
}

