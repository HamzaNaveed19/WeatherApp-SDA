This JavaFX Weather App allows users to retrieve weather information for a specific location. It utilizes the OpenWeatherMap API to fetch weather data and displays it in a user-friendly interface as well as on the console.

Installation and Setup
1. Install JavaFX
JavaFX is required to run the JavaFX Weather App. Follow these steps to install JavaFX:
  .Download JavaFX SDK from the official website: OpenJFX
  .Extract the downloaded archive to a location on your computer.
3. Set up JavaFX in VS Code
To configure JavaFX in Visual Studio Code, follow these steps:
Open your JavaFX project in Visual Studio Code.
Open a .vscode directory in the root of your project.
Open a launch.json file inside the .vscode directory.
Add the following configuration to launch.json:
{
 "vmArgs": "--module-path \"//path//javafx-sdk-21.0.2/lib\" --add-modules javafx.controls,javafx.fxml,javafx.web"
}



For SQL DataBase Connection:
  - Make sure to have MySQLWorkbench
  - RUN the schema from Weather.sql file in your ROOT Folder.
  - Make sure to change the connection string in Weather.java file.
  - Just Alter your Pasword if you have created the schema from Weather.sql file.
  - Your SQL setup completed.
For Map.js
Install Node js(from Browser - also add Environment Variables), Express Js(npm install express in cmd) , CORS(npm install CORS in cmd)
Goto Windows Firewall add A port "3000" in inbound rules.
