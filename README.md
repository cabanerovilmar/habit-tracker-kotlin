## Project Setup Instructions

### Prerequisites

- **Java Development Kit (JDK) 17**
  - This project requires JDK 17 for compilation and execution. Ensure you have JDK 17 installed on your machine.

### Environment Configuration

1. **Open the Project in Android Studio**

   - Launch Android Studio.
   - Use **File** -> **Open** and select the project directory to import the project.

2. **Set the JDK for Gradle**

   To manually configure the JDK in Android Studio, follow these steps:

   1. **Open Gradle Settings**:
      - Navigate to **File** -> **Settings** (or **Preferences** on macOS) -> **Build, Execution, Deployment** -> **Build Tools** -> **Gradle**.

   2. **Select the Correct JDK**:
      - Under **Gradle JDK**, click the dropdown menu and select the path to JDK 17. If JDK 17 is not listed, manually enter the path to your JDK 17 installation. For example:
        - Windows: `C:\Program Files\Java\jdk-17`
        - macOS/Linux: `/Library/Java/JavaVirtualMachines/jdk-17/Contents/Home`

   3. **Apply Changes**:
      - Click **OK** to save the settings. This ensures that Gradle uses the specified JDK for building the project.

3. **Set the Project SDK**

   - Navigate to **File** -> **Project Structure** -> **Project**.
   - Ensure the **Project SDK** is set to JDK 17.

4. **Sync Project with Gradle Files**

   - Click **Sync Project with Gradle Files** in the toolbar to ensure all configurations are updated and applied.

### Build Instructions

1. **Build the Project**

   - With the correct JDK configured, you can build the project using Android Studio’s **Build** menu or by running specific Gradle tasks.

2. **Run the Application**

   - Use the **Run** menu in Android Studio to deploy and test the application on an emulator or a connected device.

### Troubleshooting

- **Build Errors**: If you encounter issues during the build process, verify that the JDK path is correctly configured in both the Gradle settings and the Project Structure.
- **JDK Verification**: Check the Gradle console output to confirm the JDK version being used. It should reflect JDK 17 as specified.

### Additional Notes

- **Updating JDK**: If you update the JDK on your system, ensure that you update the path in Android Studio to match the new installation.
- **Consistency Across Teams**: To maintain consistency across team members, it’s recommended to document these setup steps in your project’s version control system.
