package su.windmill.kitto.event;

import net.kyori.adventure.key.Key;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.IllegalPluginAccessException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class EventManagerImpl implements EventManager {

    @Override
    public void adaptBukkitListenerAsEventBus(final Key key, final Listener listener) {
        var clazz = listener.getClass();
        try {
            Reference<Listener> reference = new WeakReference<>(listener);
            for (final Method method : clazz.getDeclaredMethods()) {
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if(eventHandler == null || method.getParameterCount() != 1) continue;

                EventBus<?> eventBus;
                try {
                    eventBus = getEventBus((Class<? extends Event>) method.getParameterTypes()[0]);
                }
                catch (Throwable e) {
                    e.printStackTrace();
                    eventBus = null;
                }

                if(eventBus == null) continue;

                method.setAccessible(true);

                eventBus.addLast(
                    Key.key(key.namespace(), key.value() + method.getName()),
                    eventHandler.priority(),
                    new ReflectionEventExecutor<>(reference, method)
                );
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearAll(final String namespace) {
        EventBus.clearAll(namespace);
    }

    @Override
    public <E extends Event> EventBus<E> getEventBus(final Class<E> eventClazz) {
        try {
            Method method = eventClazz.getDeclaredMethod("getStaticEventBus");
            return (EventBus<E>) method.invoke(null);
        }
        catch (NoSuchMethodException e) {
            throw new IllegalPluginAccessException("Unable to find event bus for event " + eventClazz.getName() + ". Static getStaticEventBus method required!");
        }
        catch (Throwable e) {
            throw new IllegalPluginAccessException(e.toString());
        }
    }

}
