package building;

/**
 * This is the controller for the building.
 */
public class BuildingControllerImpl implements BuildingController {
  private BuildingInterface model;
  private BuildingView view;

  /**
   * The constructor for the BuildingControllerImpl.
   *
   * @param model the model for the building.
   * @param view  the view for the building.
   */
  public BuildingControllerImpl(BuildingInterface model, BuildingView view) {
    this.model = model;
    this.view = view;
    this.view.addControl(this);
  }

  private void displayDashboard() {
    this.view.displayBuildingInfo(this.model.getElevatorSystemStatus());
  }

  public void setView() {
    this.displayDashboard();
  }

  @Override
  public void startElevatorSystem() {
    model.startElevatorSystem();
    updateView();
  }

  @Override
  public void stopElevatorSystem() {
    model.stopElevatorSystem();
    updateView();
  }

  @Override
  public void rebuildElevatorSystem(int numberOfFloors, int numberOfElevators,
                                    int elevatorCapacity) {
    this.model = new Building(numberOfFloors, numberOfElevators, elevatorCapacity);
    updateView();
  }

  @Override
  public void stepSystem() {
    model.steps();
    updateView();
  }

  @Override
  public void addRequest(int startFloor, int endFloor) {
    model.addRequest(startFloor, endFloor);
    updateView();
  }

  private void updateView() {
    view.updateView(model.getElevatorSystemStatus().toString());
  }
}
