import java.io.IOException;

public class RCSCreator {
  private String path;
  private String prefix;

  public RCSCreator(String path, String prefix) {
    this.path = path;
    this.prefix = prefix;
  }

  public void create() throws IOException {
    String controllerFile = "package " + Commons.convertPathToPackage(path) + ";\n" +
            "\n" +
            "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.web.bind.annotation.*;\n" +
            "\n" +
            "@RequestMapping(\"/\")\n" +
            "@RestController\n" +
            "public class " + prefix + "Controller {\n" +
            "  private " + prefix + "Service " + Commons.firstLetterToLowerCase(prefix) + "Service;\n" +
            "\n" +
            "  @Autowired\n" +
            "  public " + prefix + "Controller(" + prefix + "Service " + Commons.firstLetterToLowerCase(prefix) + "Service) {\n" +
            "    this." + Commons.firstLetterToLowerCase(prefix) + "Service = " + Commons.firstLetterToLowerCase(prefix) + "Service;\n" +
            "  }\n" +
            "}";

    String serviceFile = "package " + Commons.convertPathToPackage(path) + ";\n" +
            "\n" +
            "public interface " + prefix + "Service {\n" +
            "\n" +
            "}";

    String serviceImplFile = "package " + Commons.convertPathToPackage(path) + ";\n" +
            "\n" +
            "import org.springframework.beans.factory.annotation.Autowired;\n" +
            "import org.springframework.stereotype.Service;\n" +
            "\n" +
            "@Service\n" +
            "public class " + prefix + "ServiceImpl implements " + prefix + "Service {\n" +
            "  private " + prefix + "Repository " + Commons.firstLetterToLowerCase(prefix) + "Repository;\n" +
            "\n" +
            "  @Autowired\n" +
            "  public " + prefix + "ServiceImpl(" + prefix + "Repository " + Commons.firstLetterToLowerCase(prefix) + "Repository) {\n" +
            "    this." + Commons.firstLetterToLowerCase(prefix) + "Repository = " + Commons.firstLetterToLowerCase(prefix) + "Repository;\n" +
            "  }\n" +
            "}";

    String repositoryFile = "package " + Commons.convertPathToPackage(path) + ";\n" +
            "\n" +
            "public interface " + prefix + "Repository {\n" +
            "\n" +
            "}";

    String repositoryImplFile = "package " + Commons.convertPathToPackage(path) + ";\n" +
            "\n" +
            "import org.springframework.stereotype.Repository;\n" +
            "\n" +
            "@Repository\n" +
            "public class " + prefix + "RepositoryImpl implements " + prefix + "Repository{\n" +
            "\n" +
            "}";

    Commons.write(path + "\\" + prefix + "Controller.java", controllerFile);
    Commons.write(path + "\\" + prefix + "Service.java", serviceFile);
    Commons.write(path + "\\" + prefix + "ServiceImpl.java", serviceImplFile);
    Commons.write(path + "\\" + prefix + "Repository.java", repositoryFile);
    Commons.write(path + "\\" + prefix + "RepositoryImpl.java", repositoryImplFile);
  }
}