import java.io.*;

//All commong methods are written here
public class Commons {
  public static String convertPathToPackage(String path) {
    String packageName = path.substring(path.indexOf("com\\")); // get only specific path
    return packageName.replace('\\', '.'); //Replace backslashes with dots
  }

  public static void write(String path, String text) throws IOException {
    FileWriter fw = new FileWriter(path);
    fw.write(text);
    fw.close();
  }

  public static String readFile(String filePath) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));

    StringBuilder content = new StringBuilder();
    String line = "";

    while ((line = bufferedReader.readLine()) != null) {
      content.append(line).append("\n");
    }

    return content.toString();
  }

  public static String firstLetterToUpperCase(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1);
  }

  public static String firstLetterToLowerCase(String word) {
    return word.substring(0, 1).toLowerCase() + word.substring(1);
  }
}