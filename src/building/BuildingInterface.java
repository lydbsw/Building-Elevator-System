package building;

import scanerzus.Request;

/**
 * This interface is used to represent a building.
 */
public interface BuildingInterface {


  void addRequest(int startFloor, int endFloor);

  void startElevatorSystem();


  void stopElevatorSystem();

  BuildingReport getElevatorSystemStatus();

  void steps();

}
