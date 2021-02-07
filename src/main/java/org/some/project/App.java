package org.some.project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Getter;

public class App {

  public static void main(String[] args) {
    HelloWorldArgs jArgs = new HelloWorldArgs();
    JCommander helloCmd = JCommander.newBuilder()
        .addObject(jArgs)
        .build();
    helloCmd.parse(args);
    System.out.println("Hello " + jArgs.getName());
  }

  @Getter
  public static class HelloWorldArgs {
    @Parameter(
        names = "--name",
        description = "User name",
        required = true
    )
    private String name;
  }
}
