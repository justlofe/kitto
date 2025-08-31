package su.windmill.kitto.event;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import java.lang.ref.Reference;
import java.lang.reflect.Method;

class ReflectionEventExecutor<E extends Event> implements EventExecutor<E> {

    private final Reference<Listener> listener;
    private final Method method;

    ReflectionEventExecutor(Reference<Listener> listener, Method method) {
        this.listener = listener;
        this.method = method;
    }

    @Override
    public void execute(final E event) {
        try {
            method.invoke(listener, event);
        }
        catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

}
