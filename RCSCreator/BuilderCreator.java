import models.AttributeModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BuilderCreator {
  private String path;
  private String modelName;
  ArrayList<AttributeModel> attributes;

  public BuilderCreator(String path, String modelName, ArrayList<AttributeModel> attributes) {
    this.path = path + "\\builders";
    this.modelName = modelName;
    this.attributes = attributes;
  }

  public void create() throws IOException {
    String header = "package " + Commons.convertPathToPackage(path) + ";\n\n" +
            "public class " + modelName + "Builder {\n" +
            "\n" +
            "}";

    //Create models directory
    File f = new File(path);

    // create the file name
    String pathWithFileName = path + "\\" + modelName + "Builder.java";

    Commons.write(pathWithFileName, header);
  }
}
