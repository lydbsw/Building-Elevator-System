package building;

/**
 * This is the interface for the building view.
 */
public interface BuildingView {

  void addControl(BuildingController controller);

  void updateView(String status);

  void displayBuildingInfo(BuildingReport elevatorSystemStatus);
}
