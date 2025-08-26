package building;

/**
 * This is the controller for the building.
 */
public interface BuildingController {
  void startElevatorSystem();

  void stopElevatorSystem();

  void rebuildElevatorSystem(int numberOfFloors, int numberOfElevators,
                             int elevatorCapacity);

  void stepSystem();

  void addRequest(int startFloor, int endFloor);
}
