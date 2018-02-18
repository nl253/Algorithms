package data_structures;

/**
 * @author norbert
 */

public interface PriorityQueue<E extends Comparable<E>> {

    E getMin();

    boolean isEmpty();

}
