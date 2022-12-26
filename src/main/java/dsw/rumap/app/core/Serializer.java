package dsw.rumap.app.core;

import dsw.rumap.app.maprepository.implementation.Project;

import java.io.File;

public interface Serializer {
    void saveProject(Project project);
    Project loadProject(File file);
}
