package dsw.rumap.app.serializer;

import com.google.gson.Gson;
import dsw.rumap.app.core.Serializer;
import dsw.rumap.app.maprepository.implementation.Project;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

    private final Gson gson;

    public GsonSerializer() {
        this.gson = new Gson();
    }

    @Override
    public void saveProject(Project project) {
        try {
            FileWriter fileWriter = new FileWriter(project.getFilePath());
            gson.toJson(project, fileWriter);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Project loadProject(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            return gson.fromJson(fileReader, Project.class);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
