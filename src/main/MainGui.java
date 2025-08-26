package main;

import building.Building;
import building.BuildingControllerImpl;
import building.SwingBuildingView;

/**
 * This is the main class for the elevator simulation.
 */
public class MainGui {
  /**
   * This is the main method for the elevator simulation.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {

    Building model = new Building(11, 2, 3);

    SwingBuildingView view = new SwingBuildingView();

    BuildingControllerImpl controller = new BuildingControllerImpl(model, view);
    controller.setView();
  }
}