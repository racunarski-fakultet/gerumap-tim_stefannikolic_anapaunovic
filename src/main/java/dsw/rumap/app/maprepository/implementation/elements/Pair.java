package dsw.rumap.app.maprepository.implementation.elements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}
