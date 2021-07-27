import java.io.IOException;

public class MethodReplacer {
  private String path;
  private String prefix;
  private String oldMethodValue;
  private String newMethodValue;

  public MethodReplacer(String path, String prefix, String oldMethodValue, String newMethodValue) {
    this.path = path;
    this.prefix = prefix;
    this.oldMethodValue = oldMethodValue;
    this.newMethodValue = newMethodValue;
  }

  public void replace() throws IOException {
    String filePath = "";
    String file = "";

    filePath = path + "\\" + prefix + "Controller.java";
    file = Commons.readFile(filePath);
    file = file.replace(oldMethodValue, newMethodValue);
    Commons.write(filePath, file);

    filePath = path + "\\" + prefix + "Repository.java";
    file = Commons.readFile(filePath);
    file = file.replace(oldMethodValue, newMethodValue);
    Commons.write(filePath, file);

    filePath = path + "\\" + prefix + "RepositoryImpl.java";
    file = Commons.readFile(filePath);
    file = file.replace(oldMethodValue, newMethodValue);
    Commons.write(filePath, file);

    filePath = path + "\\" + prefix + "Service.java";
    file = Commons.readFile(filePath);
    file = file.replace(oldMethodValue, newMethodValue);
    Commons.write(filePath, file);

    filePath = path + "\\" + prefix + "ServiceImpl.java";
    file = Commons.readFile(filePath);
    file = file.replace(oldMethodValue, newMethodValue);
    Commons.write(filePath, file);
  }
}