import models.AttributeModel;
import models.MethodModel;

import java.io.IOException;

public class MethodAdder {
  private String path;
  private String prefix;
  private MethodModel method;

  public MethodAdder(String path, String prefix, MethodModel method) {
    this.path = path;
    this.prefix = prefix;
    this.method = method;
  }

  public void add() throws IOException {
    String controllerFile = Commons.readFile(path + "\\" + prefix + "Controller.java");
    String serviceFile = Commons.readFile(path + "\\" + prefix + "Service.java");
    String serviceImplFile = Commons.readFile(path + "\\" + prefix + "ServiceImpl.java");
    String repositoryFile = Commons.readFile(path + "\\" + prefix + "Repository.java");
    String repositoryImplFile = Commons.readFile(path + "\\" + prefix + "RepositoryImpl.java");

    controllerFile = controllerFile.substring(0, controllerFile.length() - 2) + "\n  " +
            method.getMethodPrivacy() + " " + method.getMethodReturnType() + " " + method.getMethodName() + "(";

    serviceFile = serviceFile.substring(0, serviceFile.length() - 2) + "\n  " +
            method.getMethodReturnType() + " " + method.getMethodName() + "(";

    serviceImplFile = serviceImplFile.substring(0, serviceImplFile.length() - 2) + "\n  " +
            "@Override\n  " +
            method.getMethodPrivacy() + " " + method.getMethodReturnType() + " " + method.getMethodName() + "(";

    repositoryFile = repositoryFile.substring(0, repositoryFile.length() - 2) + "\n  " +
            method.getMethodReturnType() + " " + method.getMethodName() + "(";

    repositoryImplFile = repositoryImplFile.substring(0, repositoryImplFile.length() - 2) + "\n  " +
            "@Override\n  " +
            method.getMethodPrivacy() + " " + method.getMethodReturnType() + " " + method.getMethodName() + "(";

    //Add method parameters
    for (AttributeModel parameter : method.getParameters()) {
      controllerFile += parameter.getAttributeAnnotation() + " " +
              parameter.getAttributeType() + " " +
              parameter.getAttributeName();

      serviceFile += parameter.getAttributeAnnotation() + " " +
              parameter.getAttributeType() + " " +
              parameter.getAttributeName();

      serviceImplFile += parameter.getAttributeAnnotation() + " " +
              parameter.getAttributeType() + " " +
              parameter.getAttributeName();

      repositoryFile += parameter.getAttributeAnnotation() + " " +
              parameter.getAttributeType() + " " +
              parameter.getAttributeName();

      repositoryImplFile += parameter.getAttributeAnnotation() + " " +
              parameter.getAttributeType() + " " +
              parameter.getAttributeName();

      //Don't add comma to the last method parameter
      if (!method.getParameters().get(method.getParameters().size() - 1).equals(parameter)) {
        controllerFile += ", ";
        serviceFile += ", ";
        serviceImplFile += ", ";
        repositoryFile += ", ";
        repositoryImplFile += ", ";
      }
    }

    controllerFile += ") {\n\n" + "  }" + "\n}";
    serviceFile += ");" + "\n}";
    serviceImplFile += ") {\n\n" + "  }" + "\n}";
    repositoryFile += ");" + "\n}";
    repositoryImplFile += ") {\n\n" + "  }" + "\n}";

    String pathWithPrefix = path + "\\" + prefix;
    Commons.write(pathWithPrefix + "Controller.java", controllerFile);
    Commons.write(pathWithPrefix + "Service.java", serviceFile);
    Commons.write(pathWithPrefix + "ServiceImpl.java", serviceImplFile);
    Commons.write(pathWithPrefix + "Repository.java", repositoryFile);
    Commons.write(pathWithPrefix + "RepositoryImpl.java", repositoryImplFile);
  }
}