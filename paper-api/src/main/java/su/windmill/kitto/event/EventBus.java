package su.windmill.kitto.event;

import net.kyori.adventure.key.Key;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import su.windmill.kitto.util.Pipeline;
import java.util.HashMap;
import java.util.Map;

public final class EventBus<E extends Event> {

    private static final Map<Class<? extends Event>, EventBus<? extends Event>> eventBuses = new HashMap<>();

    private final Map<EventPriority, Pipeline<EventExecutor<E>>> pipelineByPriority = new HashMap<>();

    private EventBus() {
    }

    public Pipeline<EventExecutor<E>> getPipeline(EventPriority priority) {
        return pipelineByPriority.computeIfAbsent(priority, (ignored) -> new Pipeline<>());
    }

    public boolean addFirst(Key newObject, EventExecutor<E> executor) {
        return addFirst(newObject, EventPriority.NORMAL, executor);
    }

    public boolean addLast(Key newObject, EventExecutor<E> executor) {
        return addLast(newObject, EventPriority.NORMAL, executor);
    }

    public boolean addBefore(Key baseObject, Key newObject, EventExecutor<E> executor) {
        return addBefore(baseObject, newObject, EventPriority.NORMAL, executor);
    }

    public boolean addAfter(Key baseObject, Key newObject, EventExecutor<E> executor) {
        return addAfter(baseObject, newObject, EventPriority.NORMAL, executor);
    }

    public boolean addFirst(Key newObject, EventPriority priority, EventExecutor<E> executor) {
        return getPipeline(priority).addFirst(newObject, executor);
    }

    public boolean addLast(Key newObject, EventPriority priority, EventExecutor<E> executor) {
        return getPipeline(priority).addLast(newObject, executor);
    }

    public boolean addBefore(Key baseObject, Key newObject, EventPriority priority, EventExecutor<E> executor) {
        return getPipeline(priority).addBefore(baseObject, newObject, executor);
    }

    public boolean addAfter(Key baseObject, Key newObject, EventPriority priority, EventExecutor<E> executor) {
        return getPipeline(priority).addAfter(baseObject, newObject, executor);
    }

    public void clear() {
        for (final EventPriority priority : EventPriority.values()) {
            Pipeline<EventExecutor<E>> pipeline = pipelineByPriority.get(priority);
            if(pipeline != null) pipeline.clear();
        }
    }

    public void clear(String namespace) {
        for (final EventPriority priority : EventPriority.values()) {
            Pipeline<EventExecutor<E>> pipeline = pipelineByPriority.get(priority);
            if(pipeline != null) {
                for (final Key key : pipeline.order()) {
                    if(key.namespace().equals(namespace)) pipeline.remove(key);
                }
            }
        }
    }

    public static void clearAll() {
        eventBuses.forEach((clazz, eventBus) -> eventBus.clear());
    }

    public static void clearAll(String namespace) {
        eventBuses.forEach((clazz, eventBus) -> eventBus.clear(namespace));
    }

    public static <E extends Event> EventBus<E> getEventBus(Class<E> clazz) {
        return (EventBus<E>) eventBuses.computeIfAbsent(clazz, (ignored) -> new EventBus<>());
    }

}
