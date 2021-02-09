package org.some.project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class App {

  public static void main(String[] args) {
    String[] customArgs = { "-n", "Fedor", "-v", "15" };

    RegularArgs regular = new RegularArgs();
    JCommander regularResult = JCommander.newBuilder()
        .addObject(regular)
//        .allowParameterOverwriting(true)
        .build();
    regularResult.parse(customArgs);
    System.out.printf("Regular args: Hello  '%s'! Value: '%d'\n", regular.name, regular.value);

    BrokenArgs broken = new BrokenArgs();
    JCommander brokenResult = JCommander.newBuilder()
        .addObject(broken)
//        .allowParameterOverwriting(true)
        .build();

    try {
      brokenResult.parse(customArgs);
      System.out.printf(" Broken args: Hello  '%s'! Value: '%d'\n", broken.name, broken.value);
    }
    catch (ParameterException ex) {
      System.out.println("Smth went wrong");
      ex.printStackTrace();
    }
  }

  public static class RegularArgs {
    @Parameter(names = {"--name", "-n"}, description = "User name", required = true)
    private String name;

    @Parameter(names = "-v", required = true)
    private int value;
  }

  public static class BrokenArgs {
    @Parameter(names = {"--name", "-n"}, description = "User name", required = true)
    private String name;

    @Parameter(names = "-v", required = true, forceNonOverwritable = true)
    private int value;
  }
}
