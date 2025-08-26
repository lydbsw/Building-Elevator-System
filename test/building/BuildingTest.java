package building;

import static org.junit.Assert.assertEquals;

import building.enums.ElevatorSystemStatus;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the Building class.
 */
public class BuildingTest {
  private Building building;

  @Before
  public void setUp() {
    building = new Building(10, 2, 5);
  }

  /**
   * Tests the addRequest method.
   */
  @Test
  public void testAddRequest() {
    building.startElevatorSystem();
    building.addRequest(1, 3);
    BuildingReport report = building.getElevatorSystemStatus();
    assertEquals(1, report.getUpRequests().size());
    assertEquals(0, report.getDownRequests().size());
  }

  /**
   * Tests the startElevatorSystem method.
   */
  @Test
  public void testStartElevatorSystem() {
    building.startElevatorSystem();
    BuildingReport report = building.getElevatorSystemStatus();
    assertEquals(ElevatorSystemStatus.running, report.getSystemStatus());
  }

  /**
   * Tests the stopElevatorSystem method.
   */
  @Test
  public void testStopElevatorSystem() {
    building.startElevatorSystem();
    building.stopElevatorSystem();
    BuildingReport report = building.getElevatorSystemStatus();
    assertEquals(ElevatorSystemStatus.stopping, report.getSystemStatus());
  }

  /**
   * Tests the getElevatorSystemStatus method.
   */
  @Test
  public void testGetElevatorSystemStatus() {
    BuildingReport report = building.getElevatorSystemStatus();
    assertEquals(10, report.getNumFloors());
    assertEquals(2, report.getNumElevators());
    assertEquals(5, report.getElevatorCapacity());
  }

  /**
   * Tests the steps method.
   */
  @Test
  public void testSteps() {
    building.startElevatorSystem();
    BuildingReport report = building.getElevatorSystemStatus();
    building.addRequest(1, 3);
    building.addRequest(2, 4);
    assertEquals(2, report.getUpRequests().size());
    building.steps();
    assertEquals(0, report.getUpRequests().size());

    building.addRequest(3, 1);
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    building.steps();
    assertEquals(0, report.getDownRequests().size());
  }
}