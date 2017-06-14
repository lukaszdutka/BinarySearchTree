/**
 * Created by Michał on 03.05.2017.
 */

import java.util.Comparator;

public class MyComparator<T> implements Comparator<T> {

    public int compare(T left, T right) {
        return ((Comparable<T>)left).compareTo(right);
    }
}
