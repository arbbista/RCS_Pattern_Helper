import models.AttributeModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ModelCreator {
  private String path;
  private String modelName;
  private ArrayList<AttributeModel> attributes;

  public ModelCreator(String path, String modelName, ArrayList<AttributeModel> attributes) {
    this.path = path + "\\models";
    this.modelName = modelName;
    this.attributes = attributes;
  }

  public void create() throws IOException {
    //Class header
    String header = "package " + Commons.convertPathToPackage(path) + ";" +
            "\n" +
            "\n" +
            "public class " + modelName + " {" +
            "\n" +
            "\n";

    //Add attributes body
    String content = createAttributesBody() + "\n";

    //Add getter and setter methods body
    content += createGettersAndSettersBody();

    //Merge header with content
    String builtFileContent = header + content + "}";

    //Create models directory
    File f = new File(path);
    f.mkdir();

    //Add file name to the path
    String pathWithFileName = path + "\\" + modelName + ".java";

    Commons.write(pathWithFileName, builtFileContent);
  }

  private String createGettersAndSettersBody() {
    String gettersContent = "";
    String settersContent = "";

    for (AttributeModel attribute : attributes) {
      //getter methods body
      gettersContent += "  public " + attribute.getAttributeType() + " get" +
      attribute.getAttributeName().substring(0, 1).toUpperCase() + attribute.getAttributeName().substring(1) + // make upper case first letter of the attribute
              "() {\n" +
              "    return " + attribute.getAttributeName() + ";\n" +
              "  }" +
              "\n" +
              "\n";

      //setter methods body
      settersContent += "  public void set" +
              attribute.getAttributeName().substring(0, 1).toUpperCase() +
              attribute.getAttributeName().substring(1) + // make upper case first letter of the attribute
              "(" + attribute.getAttributeType() + " " + attribute.getAttributeName() + ") {\n" +
              "    this." + attribute.getAttributeName() + " = " + attribute.getAttributeName() + ";\n" +
              "  }" +
              "\n" +
              "\n";
    }

    return gettersContent + settersContent;
  }

  private String createAttributesBody() {
    String attributesContent = "";

    for (AttributeModel attribute : attributes) {
      attributesContent += "  " + attribute.getAttributePrivacy() + " " +
              attribute.getAttributeType() + " " + attribute.getAttributeName() + ";\n";
    }

    return attributesContent;
  }
}
