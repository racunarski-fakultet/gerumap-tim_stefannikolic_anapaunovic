package dsw.rumap.app.maprepository.composite;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
public abstract class MapNodeC extends MapNode{
    private List<MapNode> children;

    protected abstract void add(MapNode child);

    protected abstract void delete(MapNode child);
}
