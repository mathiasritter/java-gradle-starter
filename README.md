# Java Gradle Starter with AspectJ

This is a Gradle Starter Template for Java with AspectJ:\
✅ Requires zero IDE configuration - simply import and start coding\
✅ Run the application or unit tests - with a single command or mouse click\
✅ Build a jar file that includes sources and javadoc - fully automated

## 🧑‍💻 Development

First, clone this repository and import the project into your preferred IDE (see below) - IntelliJ is recommended.
When developing, create your AspectJ aspects in `src/main/aspectj` and your normal Java classes in `src/main/java`.

### IntelliJ

Open IntelliJ and select "Open or Import" on the start screen OR "File" and "Open..." from the menu bar.
Choose the root repository folder when importing.

### Eclipse

Open Eclipse and select "File" and "Import..." from the menu bar.
In the dialog, select "Existing Gradle Project" from the "Gradle" folder.
Choose the root repository folder when importing.

## 🏎 Running

You can run the application by executing a Gradle Task via the command line or from your IDE.

### Command Line

On the command line you can use the Gradle Wrapper included in this repository.
Make sure you are in the root directory of this repository, then run:
- On Mac/Linux: `./gradlew run`
- On Windows: `gradlew.bat run`

### IDE

Most IDEs provide some sort of user interface that allows you to run Gradle Tasks.
- In IntelliJ, select "View", "Tool Windows" and "Gradle" from the menu bar.
- In Eclipse, select "Window", "Show View", "Other..." from the menu bar and then "Gradle", "Gradle Tasks" from the dialog.

Now you can click on the `run` Gradle Task (located in group `application`) to run the application.

## 👷 Building

Run the Gradle Task `shadowJar` (located in group `shadow`) to build a `jar` file that includes compiled classes, source code and javadoc.
Section [Running](#-running) describes how to run a Gradle Task. For example, if you are on the command line, execute:
- On Mac/Linux: `./gradlew shadowJar`
- On Windows: `gradlew.bat shadowJar`

The `jar` file will be written to `build/libs` and will include all external dependencies.


## ⚙️ Configuration

If you want to configure the build process or add external dependencies, edit the `build.gradle.kts` file.


## 🆘 Help

If you run into any issues or have any questions, feel free to contact me at [mr2n17@soton.ac.uk](mailto:mr2n17@soton.ac.uk) or on Microsoft Teams.
