package building;

import building.enums.ElevatorSystemStatus;
import elevator.Elevator;
import elevator.ElevatorReport;
import java.util.ArrayList;
import java.util.List;
import scanerzus.Request;


/**
 * This class represents a building.
 */
public class Building implements BuildingInterface {

  private final int numFloors;
  private final int numElevators;
  private final int elevatorCapacity;
  private ElevatorSystemStatus status;
  private List<Elevator> elevators;
  private final List<Request> upRequests;
  private final List<Request> downRequests;



  /**
   * The constructor for the building.
   *
   * @param numberOfFloors    the number of floors in the building.
   * @param numberOfElevators the number of elevators in the building.
   * @param elevatorCapacity  the capacity of the elevators in the building.
   */
  public Building(int numberOfFloors, int numberOfElevators, int elevatorCapacity) {
    System.out.println("Building constructor called");


    this.numFloors = numberOfFloors;
    this.numElevators = numberOfElevators;
    this.elevatorCapacity = elevatorCapacity;
    status = ElevatorSystemStatus.outOfService;


    elevators = new ArrayList<>();
    for (int i = 0; i < numberOfElevators; i++) {
      Elevator elevator = new Elevator(numberOfFloors, elevatorCapacity);
      elevators.add(elevator);
    }

    upRequests = new ArrayList<>();
    downRequests = new ArrayList<>();
  }

  @Override
  public void addRequest(int startFloor, int endFloor)
      throws IllegalArgumentException, IllegalStateException {

    if (status != ElevatorSystemStatus.running) {
      throw new IllegalStateException(
          "Elevator system not accepting requests."
      );
    }
    if (startFloor < 0 || startFloor >= numFloors) {
      throw new IllegalArgumentException(
          "Start floor must be between 0 and "
              + (numFloors - 1)
      );
    } else if (endFloor < 0 || endFloor >= numFloors) {
      throw new IllegalArgumentException(
          "End floor must be between 0 and "
              + (numFloors - 1)
      );
    } else if (startFloor == endFloor) {
      throw new IllegalArgumentException(
          "Start floor and end floor cannot be the same"
      );
    }

    Request request = new Request(startFloor, endFloor);
    if (startFloor < endFloor) {
      upRequests.add(request);
    } else {
      downRequests.add(request);
    }
  }

  @Override
  public void startElevatorSystem()
      throws IllegalStateException {
    if (status == ElevatorSystemStatus.outOfService) {
      status = ElevatorSystemStatus.running;
      for (Elevator elevator : elevators) {
        elevator.start();
      }
    } else if (status == ElevatorSystemStatus.stopping) {
      throw new IllegalStateException("The elevator system is currently stopping.");
    }
  }

  @Override
  public void stopElevatorSystem() {
    if (status == ElevatorSystemStatus.running) {
      status = ElevatorSystemStatus.stopping;
      upRequests.clear();
      downRequests.clear();
      for (Elevator elevator : elevators) {
        elevator.takeOutOfService();
      }
    }
  }

  @Override
  public BuildingReport getElevatorSystemStatus() {
    return new BuildingReport(
        numFloors,
        numElevators,
        elevatorCapacity,
        getElevatorReports(),
        upRequests,
        downRequests,
        status
    );
  }

  private ElevatorReport[] getElevatorReports() {
    ElevatorReport[] elevatorsReports = new ElevatorReport[elevators.size()];
    for (int i = 0; i < elevators.size(); i++) {
      elevatorsReports[i] = elevators.get(i).getElevatorStatus();
    }
    return elevatorsReports;
  }

  @Override
  public void steps() {
    if (status != ElevatorSystemStatus.outOfService
        && status != ElevatorSystemStatus.stopping) {
      distributeRequests();
    }
    for (int i = 0; i < numElevators; i++) {
      elevators.get(i).step();
    }
    System.out.println("moveElevator called");
  }

  private void distributeRequests() {
    if (!upRequests.isEmpty()) {
      for (Elevator elevator : elevators) {
        if (elevator.isTakingRequests() && elevator.getCurrentFloor() == 0) {
          List<Request> oneElevatorRequests = new ArrayList<>();
          while (oneElevatorRequests.size() < elevatorCapacity
              && !upRequests.isEmpty()) {
            oneElevatorRequests.add(upRequests.removeFirst());
          }
          elevator.processRequests(oneElevatorRequests);
        }
      }
    }
    if (!downRequests.isEmpty()) {
      for (Elevator elevator : elevators) {
        if (elevator.isTakingRequests()
            && elevator.getCurrentFloor() == numFloors - 1) {
          List<Request> oneElevatorRequests = new ArrayList<>();
          while (oneElevatorRequests.size() < elevatorCapacity
              && !downRequests.isEmpty()) {
            oneElevatorRequests.add(downRequests.removeFirst());
          }
          elevator.processRequests(oneElevatorRequests);
        }
      }
    }
  }
}



