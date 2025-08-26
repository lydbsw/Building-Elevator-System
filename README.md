# Building-Elevator-System

This is a Java-based application that simulates the operation of an elevator system in a building. The application provides a graphical user interface (GUI) for interacting with the system and visualizing its state.

## Features

- **Start/Stop Elevator System**: The application allows you to start and stop the elevator system.
- **Add Request**: You can add elevator requests specifying the start and end floors.
- **Step System**: This feature allows you to step through the system, simulating the passage of time.
- **Rebuild Elevator System**: You can rebuild the elevator system by specifying the number of floors, the number of elevators, and the capacity of each elevator.
- **Display Building Information**: The application displays information about the building, including the number of floors, the number of elevators, the capacity of each elevator, and the status of the elevator system.

## Code Structure

The codebase is organized into several packages and classes:

- `building`: This package contains the main classes for the building and elevator system.
  - `BuildingController`: This interface defines the operations that can be performed on the elevator system.
  - `Building`: This class implements the `BuildingController` interface and represents the building and its elevator system.
  - `BuildingView`: This interface defines the methods for displaying the state of the building and its elevator system.
  - `SwingBuildingView`: This class implements the `BuildingView` interface and provides a GUI for interacting with the elevator system.
- `main`: This package contains the `MainGui` class, which is the entry point of the application.
- `test`: This package contains the `BuildingTest` class, which includes unit tests for the `Building` class.

## Running the Application

To run the application, execute the `main` method in the `MainGui` class.

## How to Use

- Start the Elevator System: Click the "Start System" button to start the elevator system. This will initialize the system and get it ready to accept requests.  

- Add a Request: To add a request, enter the start and end floors in the respective text fields labeled "Start:" and "End:", then click the "Request" button. This will add a new elevator request to the system.  

- Step Through the System: Click the "Step" button to simulate the passage of time. This will cause the elevator system to process the next request in the queue.  
Stop the Elevator System: Click the "Stop System" button to stop the elevator system. This will halt the processing of requests.  

- Rebuild the Elevator System: To rebuild the elevator system, enter the number of floors, the number of elevators, and the elevator capacity in the respective text fields labeled "Floors:", "Elevators:", and "Capacity:", then click the "Rebuild" button. This will create a new elevator system with the specified parameters.

## Design Changes

Revised the steps method and it’s private method distributeRequests in Building class, also added the View and Controller module to implement MVC design to the application.

## Requirements

- Java Development Kit (JDK) 8 or later
- IntelliJ IDEA 2023.3.3 or any other Java IDE
