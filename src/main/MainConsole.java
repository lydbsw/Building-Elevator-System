package main;


import building.Building;
import java.util.Scanner;


/**
 * The driver for the elevator system.
 * This class will create the elevator system and run it.
 * this is for testing the elevator system.
 * <p>
 * It provides a user interface to the elevator system.
 */
public class MainConsole {

  /**
   * The main method for the elevator system.
   * This method creates the elevator system and runs it.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    final int numFloors = 4;
    final int numElevators = 2;
    final int numPeople = 3;

    Building building = new Building(numFloors, numElevators, numPeople);

    Scanner scanner = new Scanner(System.in);

    building.startElevatorSystem();

    while (true) {
      System.out.println(building.getElevatorSystemStatus().toString());

      System.out.print(
          "\n[s steps] Run steps times\n"
              + "[r start end] make a request \n"
              + "[h] halt building [c] continue building [q] quit > ");
      String command = scanner.nextLine();

      if (command.startsWith("s")) {
        building.steps();
      } else if (command.startsWith("r")) {
        String[] parts = command.split(" ");
        int start = Integer.parseInt(parts[1]);
        int end = Integer.parseInt(parts[2]);
        building.addRequest(start, end);
      } else if ("h".equals(command)) {
        building.stopElevatorSystem();
      } else if ("c".equals(command)) {
        building.startElevatorSystem();
      } else if ("q".equals(command)) {
        break;
      }
    }

    scanner.close();
  }
}
