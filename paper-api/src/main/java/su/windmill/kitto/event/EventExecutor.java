package su.windmill.kitto.event;

import org.bukkit.event.Event;

@FunctionalInterface
public interface EventExecutor<E extends Event> {

    void execute(E event);

}
