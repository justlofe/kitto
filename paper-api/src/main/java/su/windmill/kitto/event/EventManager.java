package su.windmill.kitto.event;

import net.kyori.adventure.key.Key;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public interface EventManager {

    @Deprecated
    void adaptBukkitListenerAsEventBus(Key key, Listener listener);

    void clearAll(String namespace);

    <E extends Event> EventBus<E> getEventBus(Class<E> eventClazz);

    default <E extends Event> EventManager fastAdd(Class<E> eventClazz, Key newObject, EventExecutor<E> executor) {
        return fastAdd(eventClazz, newObject, EventPriority.NORMAL, executor);
    }

    default <E extends Event> EventManager fastAdd(Class<E> eventClazz, Key newObject, EventPriority priority, EventExecutor<E> executor) {
        getEventBus(eventClazz).addLast(newObject, priority, executor);
        return this;
    }

}
