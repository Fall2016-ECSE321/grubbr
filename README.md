# TEAM 5 - grubbr

## a simple food truck management system.

This is the final group project for Team 5 in ECSE 321: Introduction to Software Engineering at McGill University. The project is to create a working food truck management system that can manage staff, menu, and inventory.

The program has to function on three platforms and has to respond to the following user story:

>There are many facets to running a successful food truck. The Food Truck Management System (FTMS) supports managers of food trucks by keeping track of staff schedules, equipment and food supply levels, and menu item and their popularity in one central location. The FTMS offers users the ability to keep track of staff, their roles (i.e., cashier or cook), and their weekly schedules. The FTMS also offers users the ability to track equipment (e.g., grills, deep fryers, knives), supplies (e.g., cheese, lettuce, tomato), and their quantities. Finally, the FTMS offers user the ability to track customer orders in order to compute reports about the most popular menu items.

The final release version is tagged with `ECSE321_Final`.

## Repository Structure

The different applications are stored in a separate folder of the repository. `desktop` contains the Eclipse project for the native Java Desktop application, `mobile` contains the AndroidStudio project for the Android application, and the `web` folder contains the files for the PHP-based web application. The `doc` folder contains the JavaDoc API documentation for the MVC controller for the Desktop and Android implementation

## Special Instructions

* The Desktop app is stored as an Eclipse project. As such, users need to place the folder in their existing Eclipse workspace. Users need to have the Java Swing plug-in installed in order to use the Swing interface. To open the application, extract the repository into your workspace, and select `Import -> General -> Existing Projects into Workspace`. **Note that `ca.mcgill.ecse321.foodtruck.application.FoodTruckManagement.java` is the main Java class that should be run. `ca.mcgill.ecse321.foodtruck.view.FoodTruckManagementPage.java` also has a main function, but PERSISTENCE WILL NOT WORK IF YOU RUN IT. IT IS NOT INTENDED TO BE RUN.**
* The Mobile app is stored as an Android Studio project. It should be able to be opened from Android Studio as a project.
* The web app runs on an XAMPP Apache Server. **Make sure to place the contents of the `web` folder into XAMPP's htdocs folder (such that `index.php` is directly in `htdocs`.** The application should be run from `http://localhost/`. Note that the Bootstrap framework will not load unless you are connected to the internet (because we're importing the css and js files from a CDN).

## Changelog

### v 0.1
First deliverable of the project.

- Add functionality to add an item to the food truck menu
- Create persistence layer
- Has support for desktop, Android, and web apps

## Contributors

- [Andrei Ungur](https://github.com/AndreiUngur)
- [Erick Zhao](https://github.com/erickzhao)
- [Patrick Lai](https://github.com/patlai)
- [Suleman Malik](https://github.com/smalik360)
- [Yu-Yueh Liu](https://github.com/UAce)
