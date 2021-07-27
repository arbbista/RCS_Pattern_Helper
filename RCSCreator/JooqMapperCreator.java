import models.AttributeModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JooqMapperCreator {
  private String featurePackagePath;
  private String path;
  private String modelName;
  ArrayList<AttributeModel> attributes;

  public JooqMapperCreator(String path, String modelName, ArrayList<AttributeModel> attributes) {
    this.featurePackagePath = path;
    this.path = path + "\\jooqmappers";
    this.modelName = modelName;
    this.attributes = attributes;
  }

  public void create() throws IOException {
    String header = "package " + Commons.convertPathToPackage(path) + ";" +
            "\n" +
            "\n" +
            "import " + Commons.convertPathToPackage(featurePackagePath) + ".builders." + modelName + "Builder;" +
            "\n" +
            "import " + Commons.convertPathToPackage(featurePackagePath) + ".models." + modelName + ";" +
            "\n" +
            "\n" +
            "import java.util.ArrayList;" +
            "\n" +
            "import java.util.List;" +
            "\n" +
            "\n" +
            "public class " + modelName + "JooqMapper {" +
            "\n" +
            "\n";

    String content = "  public static " + modelName + " unMap(Object o) {" +
            "\n" +
            "    return null;" +
            "\n" +
            "  }" +
            "\n" +
            "\n" +

            "public static " + modelName + " map(Object o) {" +
            "\n" +
            "    return null;" +
            "\n" +
            "  }" +
            "\n" +
            "\n" +

            "public static List<" + modelName + "> map(List<Object> objects) {" +
            "\n" +
            "    return null;" +
            "\n" +
            "  }" +
            "\n" +
            "}";

    //Create models directory
    File f = new File(path);

    // create the file name
    String pathWithFileName = path + "\\" + modelName + "JooqMapper.java";

    Commons.write(pathWithFileName, header + content);
  }
}