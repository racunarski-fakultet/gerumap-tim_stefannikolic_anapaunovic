package dsw.rumap.app.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dsw.rumap.app.core.Serializer;
import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.Project;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer {

    private Gson gson;

    public GsonSerializer() {
        setUpAdapters();
    }

    private void setUpAdapters(){
        RuntimeTypeAdapterFactory<MapNode> mapNodeTypeFactory = RuntimeTypeAdapterFactory
                .of(MapNode.class, "type", true)
                .registerSubtype(Project.class)
                .registerSubtype(MindMap.class)
                .registerSubtype(Element.class)
                .registerSubtype(TermElement.class)
                .registerSubtype(RelationElement.class);

        RuntimeTypeAdapterFactory<Element> elementTypeFactory = RuntimeTypeAdapterFactory
                .of(Element.class, "type", true)
                .registerSubtype(TermElement.class)
                .registerSubtype(RelationElement.class);

        gson = new GsonBuilder().registerTypeAdapterFactory(mapNodeTypeFactory).registerTypeAdapterFactory(elementTypeFactory).create();
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter fileWriter = new FileWriter(project.getFilePath())) {
            gson.toJson(project, fileWriter);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)){
            return gson.fromJson(fileReader, new TypeToken<Project>(){}.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveMindMapTemplate(MindMap mindMap) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/templateGallery/" + mindMap.getName() + ".json")) {
            gson.toJson(mindMap, fileWriter);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public MindMap loadMindMapTemplate(File file) {
        try (FileReader fileReader = new FileReader(file)){
            return gson.fromJson(fileReader, new TypeToken<MindMap>(){}.getType());
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
