package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;

import java.io.File;
import java.util.List;

public interface Serializer {
    void saveProject(Project project);
    Project loadProject(File file);
    void saveMindMapTemplate(MindMap mindMap);
    MindMap loadMindMapTemplate(File file);
}
