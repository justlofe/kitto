package su.windmill.kitto.util;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Ordered map with function to add objects between each other.
 * @param <T>
 */
public class Pipeline<T> {

    private final Map<Key, T> objects;
    private final List<Key> order;

    public Pipeline() {
        objects = new HashMap<>();
        order = new ArrayList<>();
    }

    public boolean addLast(Key key, T newObject) {
        return addAfter(null, key, newObject);
    }

    public boolean addFirst(Key key, T newObject) {
        return addBefore(null, key, newObject);
    }

    public boolean addAfter(Key baseObject, @NotNull Key newObject, @NotNull T object) {
        if(order.contains(newObject)) return false;

        int baseIndex = order.indexOf(baseObject);
        int newIndex;
        if(baseIndex == -1 || baseIndex >= objects.size()) newIndex = objects.size();
        else newIndex = baseIndex + 1;

        objects.put(newObject, object);
        order.add(newIndex, newObject);
        return true;
    }

    public boolean addBefore(Key baseObject, @NotNull Key newObject, @NotNull T object) {
        if(order.contains(newObject)) return false;

        int baseIndex = order.indexOf(baseObject);
        int newIndex;
        if(baseIndex == -1 || baseIndex >= objects.size()) newIndex = 0;
        else newIndex = baseIndex;

        objects.put(newObject, object);
        order.add(newIndex, newObject);
        return true;
    }

    public T getObject(Key name) {
        return objects.get(name);
    }

    public void remove(Key name) {
        objects.remove(name);
        order.remove(name);
    }

    /**
     * @return a copy of order list
     */
    public List<Key> order() {
        return new ArrayList<>(order);
    }

    /**
     * forEach based on order
     */
    public void forEach(Consumer<T> consumer) {
        for (Key id : order) {
            T object = objects.get(id);
            if(object != null) consumer.accept(object);
        }
    }

    public T get(int index) {
        return objects.get(order.get(index));
    }

    public int size() {
        return order.size();
    }

    public void clear() {
        order.clear();
        objects.clear();
    }

}
