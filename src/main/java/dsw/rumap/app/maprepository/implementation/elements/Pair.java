package dsw.rumap.app.maprepository.implementation.elements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<K, V> {
    private K fist;
    private V second;

    public Pair(K fist, V second) {
        this.fist = fist;
        this.second = second;
    }
}
