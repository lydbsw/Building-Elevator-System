package building;

import building.enums.ElevatorSystemStatus;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * This class represents the Swing view for the building.
 */
public class SwingBuildingView extends JFrame implements BuildingView {
  private JTextArea statusArea;
  private JTextField startField;
  private JTextField endField;
  private JTextField numFloorsField;
  private JTextField numElevatorsField;
  private JTextField elevatorCapacityField;
  private final JButton startSystemButton;
  private final JButton stepButton;
  private final JButton stopSystemButton;
  private final JButton requestButton;
  private final JButton rebuildSystemButton;

  /**
   * The constructor for the SwingBuildingView.
   */
  public SwingBuildingView() {
    setLayout(new BorderLayout());

    statusArea = new JTextArea();
    add(new JScrollPane(statusArea), BorderLayout.CENTER);

    JPanel controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    startSystemButton = new JButton("Start System");
    controlPanel.add(startSystemButton);

    stepButton = new JButton("Step");
    controlPanel.add(stepButton);

    stopSystemButton = new JButton("Stop System");
    controlPanel.add(stopSystemButton);

    requestButton = new JButton("Request"); // Initialize requestButton here
    controlPanel.add(new JLabel("Start:"));
    startField = new JTextField(5);
    controlPanel.add(startField);
    controlPanel.add(new JLabel("End:"));
    endField = new JTextField(5);
    controlPanel.add(endField);
    controlPanel.add(requestButton);

    numFloorsField = new JTextField(5);
    controlPanel.add(new JLabel("Floors:"));
    controlPanel.add(numFloorsField);

    numElevatorsField = new JTextField(5);
    controlPanel.add(new JLabel("Elevators:"));
    controlPanel.add(numElevatorsField);

    elevatorCapacityField = new JTextField(5);
    controlPanel.add(new JLabel("Capacity:"));
    controlPanel.add(elevatorCapacityField);

    rebuildSystemButton = new JButton("Rebuild");
    controlPanel.add(rebuildSystemButton);

    add(controlPanel, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 500);
    setVisible(true);
  }

  /**
   * This method is used to display the building information.
   *
   * @param buildingReport the building report to display.
   */
  public void displayBuildingInfo(BuildingReport buildingReport) {
    int numFloors = buildingReport.getNumFloors();
    int numElevators = buildingReport.getNumElevators();
    int elevatorCapacity = buildingReport.getElevatorCapacity();
    ElevatorSystemStatus systemStatus = buildingReport.getSystemStatus();
    String infoBuilder =
        "numFloors: " + numFloors + "\nnumElevators: " + numElevators + "\nelevatorCapacity: "
            + elevatorCapacity + "\nsystemStatus: " + systemStatus;
    this.statusArea.setText(infoBuilder);
  }

  @Override
  public void addControl(BuildingController controller) {
    startSystemButton.addActionListener((e) -> {
      controller.startElevatorSystem();
    });

    stepButton.addActionListener((e) -> {
      controller.stepSystem();
    });

    stopSystemButton.addActionListener((e) -> {
      controller.stopElevatorSystem();
    });

    rebuildSystemButton.addActionListener((e) -> {
      int numFloors = Integer.parseInt(numFloorsField.getText());
      int numElevators = Integer.parseInt(numElevatorsField.getText());
      int elevatorCapacity = Integer.parseInt(elevatorCapacityField.getText());
      controller.rebuildElevatorSystem(numFloors, numElevators, elevatorCapacity);
    });

    requestButton.addActionListener((e) -> {
      int start = Integer.parseInt(startField.getText());
      int end = Integer.parseInt(endField.getText());
      controller.addRequest(start, end);
    });
  }

  @Override
  public void updateView(String status) {
    statusArea.setText(status);
  }
}