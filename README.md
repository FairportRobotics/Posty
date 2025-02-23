# Posty
Posty is a Java library for building and running hardware tests for FRC Robot projects.

Posty has two types of tests. Power on Self Tests and Built in Tests.

## POST Tests
POST tests are intended to be quick checks to verify all hardware components are preset. These tests are run every time the robot powers on.

## BIT Tests
BIT tests are intended to be in-depth system checks for each subsystem. These tests are intended to be run on-demand. We typically run them all when the robot is enabled in Test mode.


# Including in FRC Project
To include this library in a Java FRC Robot project. 

# Building
The project can be built and published to the local Maven repository using `./gradlew pubLocal`

## Updating version
The version tag can be found in `gradle.properties`. When updating the version match the year to the current year, and increment the major and minor version as needed.
