package jpa;

public class App {
  public static void main(String[] args) {
    boolean running = true;
    while (running) {
      running = CLI.choice();
    }
  }
}
