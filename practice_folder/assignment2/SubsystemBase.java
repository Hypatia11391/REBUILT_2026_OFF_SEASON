// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



/* You will see a lot of red underlines in this file. Ignore them, they are from missing WPILib imports.
 * 
 * Assignment 2
 * 1) Create your own branch.
 * 2) In practice_folder/assignment2, create a folder with your GitHub username (if you haven’t already).
 * 3) Make a copy of this file in that folder (add your GitHub username at the end of the file name).
 * 4) Comment the code so you fully understand what each part does.
 *      - If a section is short, comment it line by line.
 *      - If a section is longer, write comments for each logical block 
 *        (for example: fields, constructor, methods, loops, or major steps inside a method).
 *      The goal is to explain the purpose of every part of the file in your own words.
 * 5) After you finish commenting this file, comment the other two files as well.
 * 6) Commit and push your branch.
 * 7) Open a pull request on GitHub.
 * 8) Wait until one of the leaders reviews your code and either approves it or leaves comments.
 * 9) When your code is approved, merge your branch into main.
 */

 

package practice_folder.assignment2;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.util.sendable.SendableRegistry;

/**
 * A base for subsystems that handles registration in the constructor, and provides a more intuitive
 * method for setting the default command.
 *
 * <p>This class is provided by the NewCommands VendorDep
 */
public abstract class SubsystemBase implements Subsystem, Sendable {
  /** Constructor. Telemetry/log name defaults to the classname. */
  @SuppressWarnings("this-escape")
  public SubsystemBase() {
    String name = this.getClass().getSimpleName();
    name = name.substring(name.lastIndexOf('.') + 1);
    SendableRegistry.addLW(this, name, name);
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  /**
   * Constructor.
   *
   * @param name Name of the subsystem for telemetry and logging.
   */
  @SuppressWarnings("this-escape")
  public SubsystemBase(String name) {
    SendableRegistry.addLW(this, name, name);
    CommandScheduler.getInstance().registerSubsystem(this);
  }

  /**
   * Gets the name of this Subsystem.
   *
   * @return Name
   */
  @Override
  public String getName() {
    return SendableRegistry.getName(this);
  }

  /**
   * Sets the name of this Subsystem.
   *
   * @param name name
   */
  public void setName(String name) {
    SendableRegistry.setName(this, name);
  }

  /**
   * Gets the subsystem name of this Subsystem.
   *
   * @return Subsystem name
   */
  public String getSubsystem() {
    return SendableRegistry.getSubsystem(this);
  }

  /**
   * Sets the subsystem name of this Subsystem.
   *
   * @param subsystem subsystem name
   */
  public void setSubsystem(String subsystem) {
    SendableRegistry.setSubsystem(this, subsystem);
  }

  /**
   * Associates a {@link Sendable} with this Subsystem. Also update the child's name.
   *
   * @param name name to give child
   * @param child sendable
   */
  public void addChild(String name, Sendable child) {
    SendableRegistry.addLW(child, getSubsystem(), name);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("Subsystem");

    builder.addBooleanProperty(".hasDefault", () -> getDefaultCommand() != null, null);
    builder.addStringProperty(
        ".default",
        () -> getDefaultCommand() != null ? getDefaultCommand().getName() : "none",
        null);
    builder.addBooleanProperty(".hasCommand", () -> getCurrentCommand() != null, null);
    builder.addStringProperty(
        ".command",
        () -> getCurrentCommand() != null ? getCurrentCommand().getName() : "none",
        null);
  }
}
