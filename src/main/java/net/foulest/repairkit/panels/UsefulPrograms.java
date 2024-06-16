/*
 * RepairKit - an all-in-one Java-based Windows repair and maintenance toolkit.
 * Copyright (C) 2024 Foulest (https://github.com/Foulest)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package net.foulest.repairkit.panels;

import net.foulest.repairkit.RepairKit;
import net.foulest.repairkit.util.TaskUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static net.foulest.repairkit.util.CommandUtil.getCommandOutput;
import static net.foulest.repairkit.util.CommandUtil.runCommand;
import static net.foulest.repairkit.util.ConstantUtil.*;
import static net.foulest.repairkit.util.DebugUtil.debug;
import static net.foulest.repairkit.util.FileUtil.*;
import static net.foulest.repairkit.util.ProcessUtil.isProcessRunning;
import static net.foulest.repairkit.util.SoundUtil.playSound;
import static net.foulest.repairkit.util.SwingUtil.*;

public class UsefulPrograms extends JPanel {

    /**
     * Creates the Useful Programs panel.
     */
    public UsefulPrograms() {
        // Sets the panel's layout to null.
        debug("Setting the Useful Programs panel layout to null...");
        setLayout(null);

        // Creates the title label.
        debug("Creating the Useful Programs title label...");
        JLabel titleLabel = createLabel("Useful Programs",
                new Rectangle(20, 15, 200, 30),
                new Font(ARIAL, Font.BOLD, 18)
        );
        add(titleLabel);

        // Adds the components to the panel.
        debug("Adding components to the Useful Programs panel...");

        // Creates tasks for the executor.
        List<Runnable> tasks = Arrays.asList(
                this::setupCPUZ,
                this::setupHWMonitor,
                this::setupAutoruns,
                this::setupProcessExplorer,
                this::setupTreeSize,
                this::setupEverything,
                this::setupFanControl,
                this::setupEmsisoftScan,
                this::setupNVCleanstall,
                this::setupSophosScan,
                this::setupUBlockOrigin,
                this::setupTrafficLight
        );

        // Executes tasks using TaskUtil.
        TaskUtil.executeTasks(tasks);

        // Sets the panel's border.
        debug("Setting the Useful Programs panel border...");
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Sets up the CPU-Z section.
     */
    public void setupCPUZ() {
        int baseHeight = 55;
        int baseWidth = 20;

        // Adds a title label for CPU-Z.
        debug("Creating the CPU-Z title label...");
        JLabel title = createLabel("CPU-Z",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for CPU-Z.
        debug("Creating the CPU-Z description label...");
        JLabel description = createLabel("Version: 2.09.0",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for CPU-Z.
        debug("Setting up the CPU-Z icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/CPU-Z.png"), this);

        // Adds a button to launch CPU-Z.
        debug("Creating the CPU-Z launch button...");
        JButton appButton = createAppButton("Launch CPU-Z",
                "Displays system hardware information.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                "CPU-Z.zip",
                "CPU-Z.exe",
                true, tempDirectory.getPath()
        );
        add(appButton);
    }

    /**
     * Sets up the HWMonitor section.
     */
    public void setupHWMonitor() {
        int baseHeight = 150;
        int baseWidth = 20;

        // Adds a title label for the HWMonitor.
        debug("Creating the HWMonitor title label...");
        JLabel title = createLabel("HWMonitor",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for HWMonitor.
        debug("Creating the HWMonitor description label...");
        JLabel description = createLabel("Version: 1.53.0",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for HWMonitor.
        debug("Setting up the HWMonitor icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/HWMonitor.png"), this);

        // Adds a button to launch HWMonitor.
        debug("Creating the HWMonitor launch button...");
        JButton appButton = createAppButton("Launch HWMonitor",
                "Displays hardware voltages & temperatures.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                "HWMonitor.zip",
                "HWMonitor.exe",
                true, tempDirectory.getPath()
        );
        add(appButton);
    }

    /**
     * Sets up the Autoruns section.
     */
    public void setupAutoruns() {
        int baseHeight = 245;
        int baseWidth = 20;

        // Adds a title label for Autoruns.
        debug("Creating the Autoruns title label...");
        JLabel title = createLabel("Autoruns",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for Autoruns.
        debug("Creating the Autoruns description label...");
        JLabel description = createLabel("Version: 14.11",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for Autoruns.
        debug("Setting up the Autoruns icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/Autoruns.png"), this);

        // Adds a button to launch HWMonitor.
        debug("Creating the Autoruns launch button...");
        JButton appButton = createAppButton("Launch Autoruns",
                "Displays startup items.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                "Autoruns.zip",
                "Autoruns.exe",
                true, tempDirectory.getPath()
        );
        add(appButton);
    }

    /**
     * Sets up the Process Explorer section.
     */
    public void setupProcessExplorer() {
        int baseHeight = 340;
        int baseWidth = 20;

        // Adds a title label for Process Explorer.
        debug("Creating the Process Explorer title label...");
        JLabel title = createLabel("Process Explorer",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for Process Explorer.
        debug("Creating the Process Explorer description label...");
        JLabel description = createLabel("Version: 17.06",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for Process Explorer.
        debug("Setting up the Process Explorer icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/ProcessExplorer.png"), this);

        // Adds a button to launch Process Explorer.
        debug("Creating the Process Explorer launch button...");
        JButton appButton = createAppButton("Launch Process Explorer",
                "Displays system processes.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                "ProcessExplorer.zip",
                "ProcessExplorer.exe",
                true, tempDirectory.getPath()
        );
        add(appButton);
    }

    /**
     * Sets up the TreeSize section.
     */
    public void setupTreeSize() {
        int baseHeight = 55;
        int baseWidth = 250;

        // Adds a title label for TreeSize.
        debug("Creating the TreeSize title label...");
        JLabel title = createLabel("TreeSize",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for TreeSize.
        debug("Creating the TreeSize description label...");
        JLabel description = createLabel("Version: 4.6.3.520",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for TreeSize.
        debug("Setting up the TreeSize icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/TreeSize.png"), this);

        // Adds a button to launch TreeSize.
        debug("Creating the TreeSize launch button...");
        JButton appButton;
        if (RepairKit.isOutdatedOperatingSystem()) {
            appButton = createActionButton("Launch TreeSize",
                    "Displays system files organized by size.",
                    new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                    new Color(200, 200, 200), () -> {
                        playSound(ERROR_SOUND);
                        JOptionPane.showMessageDialog(null, OUTDATED_OS_MESSAGE, OUTDATED_OS_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
            );
        } else {
            appButton = createAppButton("Launch TreeSize",
                    "Displays system files organized by size.",
                    new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                    new Color(200, 200, 200),
                    "TreeSize.zip",
                    "TreeSize.exe",
                    true, tempDirectory.getPath()
            );
        }
        add(appButton);
    }

    /**
     * Sets up the Everything section.
     */
    public void setupEverything() {
        int baseHeight = 150;
        int baseWidth = 250;

        // Adds a title label for Everything.
        debug("Creating the Everything title label...");
        JLabel title = createLabel("Everything",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for Everything.
        debug("Creating the Everything description label...");
        JLabel description = createLabel("Version: 1.4.1.1024",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for Everything.
        debug("Setting up the Everything icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/Everything.png"), this);

        // Adds a button to launch Everything.
        debug("Creating the Everything launch button...");
        JButton appButton = createAppButton("Launch Everything",
                "Displays all files on your system.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                "Everything.zip",
                "Everything.exe",
                true, tempDirectory.getPath()
        );
        add(appButton);
    }

    /**
     * Sets up the FanControl section.
     */
    public void setupFanControl() {
        int baseHeight = 245;
        int baseWidth = 250;

        // Adds a title label for FanControl.
        debug("Creating the FanControl title label...");
        JLabel title = createLabel("FanControl",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for FanControl.
        debug("Creating the FanControl description label...");
        JLabel description = createLabel(VERSION_AUTO_UPDATED,
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for FanControl.
        debug("Setting up the FanControl icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/FanControl.png"), this);

        // Adds a button to launch FanControl.
        debug("Creating the FanControl launch button...");
        JButton appButton = createActionButton("Launch FanControl",
                "Allows control over system fans.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200), () -> {
                    if (RepairKit.isOutdatedOperatingSystem()) {
                        playSound(ERROR_SOUND);
                        JOptionPane.showMessageDialog(null, OUTDATED_OS_MESSAGE, OUTDATED_OS_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (RepairKit.isSafeMode()) {
                        playSound(ERROR_SOUND);
                        JOptionPane.showMessageDialog(null, SAFE_MODE_MESSAGE, SAFE_MODE_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        String fanControlPath = getCommandOutput("wmic process where name=\"FanControl.exe\""
                                + " get ExecutablePath /value", false, false).toString();
                        fanControlPath = fanControlPath.replace("[, , , , ExecutablePath=", "");
                        fanControlPath = fanControlPath.replace(", , , , , , , ]", "");

                        // If FanControl is not running, extract and launch it.
                        // Otherwise, launch the existing instance.
                        if (!isProcessRunning("FanControl.exe")) {
                            launchApplication("FanControl.zip", "\\FanControl.exe",
                                    true, System.getenv("APPDATA") + "\\FanControl");
                        } else {
                            runCommand("start \"\" \"" + fanControlPath + "\"", false);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
        );
        add(appButton);
    }

    /**
     * Sets up the NVCleanstall section.
     */
    public void setupNVCleanstall() {
        int baseHeight = 340;
        int baseWidth = 250;

        // Adds a title label for NVCleanstall.
        debug("Creating the NVCleanstall title label...");
        JLabel title = createLabel("NVCleanstall",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for NVCleanstall.
        debug("Creating the NVCleanstall description label...");
        JLabel description = createLabel("Version: 1.16.0",
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for NVCleanstall.
        debug("Setting up the NVCleanstall icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/NVCleanstall.png"), this);

        // Adds a button to launch NVCleanstall.
        debug("Creating the NVCleanstall launch button...");
        JButton appButton = createActionButton("Launch NVCleanstall",
                "A lightweight NVIDIA graphics card driver updater.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200), () -> {
                    if (RepairKit.isOutdatedOperatingSystem()) {
                        playSound(ERROR_SOUND);
                        JOptionPane.showMessageDialog(null, OUTDATED_OS_MESSAGE, OUTDATED_OS_TITLE, JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try (InputStream input = RepairKit.class.getClassLoader().getResourceAsStream("bin/NVCleanstall.zip")) {
                        saveFile(Objects.requireNonNull(input), tempDirectory + "\\NVCleanstall.zip", true);
                        unzipFile(tempDirectory + "\\NVCleanstall.zip", tempDirectory.getPath());
                        runCommand(tempDirectory + "\\NVCleanstall.exe", true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        add(appButton);
    }

    /**
     * Sets up the Emsisoft Scan section.
     */
    public void setupEmsisoftScan() {
        int baseHeight = 55;
        int baseWidth = 480;

        // Adds a title label for Emsisoft Scan.
        debug("Creating the Emsisoft Scan title label...");
        JLabel title = createLabel("Emsisoft Scan",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for Emsisoft Scan.
        debug("Creating the Emsisoft Scan description label...");
        JLabel description = createLabel(VERSION_AUTO_UPDATED,
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for Emsisoft Scan.
        debug("Setting up the Emsisoft Scan icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/Emsisoft.png"), this);

        // Adds a button to launch Emsisoft Scan.
        debug("Creating the Emsisoft Scan launch button...");
        JButton appButton;
        if (RepairKit.isOutdatedOperatingSystem()) {
            appButton = createActionButton("Launch Emsisoft Scan",
                    "Scans for malware with Emsisoft.",
                    new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                    new Color(200, 200, 200), () -> {
                        playSound(ERROR_SOUND);
                        JOptionPane.showMessageDialog(null, OUTDATED_OS_MESSAGE, OUTDATED_OS_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
            );
        } else {
            appButton = createAppButton("Launch Emsisoft Scan",
                    "Scans for malware with Emsisoft.",
                    new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                    new Color(200, 200, 200),
                    "Emsisoft.zip",
                    "Emsisoft.exe",
                    true, tempDirectory.getPath()
            );
        }
        add(appButton);
    }

    /**
     * Sets up the Sophos Scan section.
     */
    public void setupSophosScan() {
        int baseHeight = 150;
        int baseWidth = 480;

        // Adds a title label for Sophos Scan.
        debug("Creating the Sophos Scan title label...");
        JLabel title = createLabel("Sophos Scan",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for Sophos Scan.
        debug("Creating the Sophos Scan description label...");
        JLabel description = createLabel(VERSION_AUTO_UPDATED,
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for Sophos Scan.
        debug("Setting up the Sophos Scan icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/Sophos.png"), this);

        // Adds a button to launch Sophos Scan.
        debug("Creating the Sophos Scan launch button...");
        JButton appButton = createActionButton("Launch Sophos Scan",
                "Scans for malware with Sophos.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200), () -> {
                    try (InputStream input = RepairKit.class.getClassLoader().getResourceAsStream("bin/Sophos.zip")) {
                        saveFile(Objects.requireNonNull(input), tempDirectory + "\\Sophos.zip", true);
                        unzipFile(tempDirectory + "\\Sophos.zip", tempDirectory.getPath());
                        runCommand("start \"\" \"" + tempDirectory + "\\Sophos.exe\"", true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
        );
        add(appButton);
    }

    /**
     * Sets up the uBlock Origin section.
     */
    public void setupUBlockOrigin() {
        int baseHeight = 245;
        int baseWidth = 480;

        // Adds a title label for uBlock Origin.
        debug("Creating the uBlock Origin title label...");
        JLabel title = createLabel("uBlock Origin",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for uBlock Origin.
        debug("Creating the uBlock Origin description label...");
        JLabel description = createLabel(VERSION_AUTO_UPDATED,
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for uBlock Origin.
        debug("Setting up the uBlock Origin icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/uBlockOrigin.png"), this);

        // Adds a button to launch uBlock Origin.
        debug("Creating the uBlock Origin launch button...");
        JButton appButton = createActionButton("Launch uBlock Origin",
                "Link to the ad-blocker browser extension.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                () -> runCommand("start https://ublockorigin.com", true)
        );
        add(appButton);
    }

    /**
     * Sets up the TrafficLight section.
     */
    public void setupTrafficLight() {
        int baseHeight = 340;
        int baseWidth = 480;

        // Adds a title label for TrafficLight.
        debug("Creating the TrafficLight title label...");
        JLabel title = createLabel("TrafficLight",
                new Rectangle(baseWidth + 43, baseHeight, 200, 30),
                new Font(ARIAL, Font.BOLD, 16)
        );
        add(title);

        // Adds a description label for TrafficLight.
        debug("Creating the TrafficLight description label...");
        JLabel description = createLabel(VERSION_AUTO_UPDATED,
                new Rectangle(baseWidth + 43, baseHeight + 20, 200, 30),
                new Font(ARIAL, Font.BOLD, 12)
        );
        add(description);

        // Adds an icon for TrafficLight.
        debug("Setting up the TrafficLight icon...");
        setupAppIcon(baseHeight, baseWidth, getImageIcon("icons/TrafficLight.png"), this);

        // Adds a button to launch TrafficLight.
        debug("Creating the TrafficLight launch button...");
        JButton appButton = createActionButton("Launch TrafficLight",
                "Link to BitDefender's TrafficLight extension.",
                new Rectangle(baseWidth, baseHeight + 50, 200, 30),
                new Color(200, 200, 200),
                () -> runCommand("start https://bitdefender.com/solutions/trafficlight.html", true)
        );
        add(appButton);
    }
}
