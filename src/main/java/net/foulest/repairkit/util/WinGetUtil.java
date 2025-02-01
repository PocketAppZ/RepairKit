package net.foulest.repairkit.util;

import com.sshtools.twoslices.Toast;
import com.sshtools.twoslices.ToastType;
import lombok.Data;
import net.foulest.repairkit.util.config.ConfigLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility class for WinGet operations.
 *
 * @author Foulest
 */
@Data
@SuppressWarnings("resource")
public class WinGetUtil {

    public static List<String> excludedPrograms = new ArrayList<>();

    /**
     * Updates all outdated programs.
     */
    @SuppressWarnings("unchecked")
    public static void updateAllPrograms() {
        @NotNull ConfigLoader configLoader = new ConfigLoader(FileUtil.getConfigFile("programs.json"));
        Map<String, Object> config = configLoader.getConfig().get("excludedPrograms");
        Object values = config.get("values");

        if (values == null || ((Collection<String>) values).isEmpty()) {
            Toast.toast(ToastType.INFO, "RepairKit", "No excluded programs found.");
            DebugUtil.debug("No excluded programs found.");
            return;
        }

        excludedPrograms = new ArrayList<>((Collection<String>) values);
        @NotNull List<String> outdatedPrograms = getOutdatedPrograms();

        if (outdatedPrograms.isEmpty()) {
            Toast.toast(ToastType.INFO, "RepairKit", "No outdated programs found.");
            DebugUtil.debug("No outdated programs found.");
            return;
        }

        @NotNull List<String> updatedPrograms = new ArrayList<>();
        int excludedCount = 0;

        for (@NotNull String id : outdatedPrograms) {
            excludedPrograms.stream().filter(excluded -> excluded.contains(id)).forEach(excluded -> {
                DebugUtil.debug("Skipping excluded program: " + id);
                updatedPrograms.add(id);
            });

            // Skips programs that have already been marked as updated.
            if (updatedPrograms.contains(id)) {
                ++excludedCount;
                continue;
            }

            Toast.toast(ToastType.INFO, "RepairKit", "Updating program: " + id);
            DebugUtil.debug("Updating program: " + id);

            if (updatePackage(id)) {
                Toast.toast(ToastType.INFO, "RepairKit", "Updated program: " + id);
                DebugUtil.debug("Updated program: " + id);
                updatedPrograms.add(id);
            } else {
                Toast.toast(ToastType.INFO, "RepairKit", "Failed to update program: " + id);
                DebugUtil.debug("Failed to update program: " + id);
                updatedPrograms.add(id);
            }

            // Pause for a moment to allow the notification to fade.
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                DebugUtil.warn("Failed to sleep thread", ex);
                Thread.currentThread().interrupt();
            }
        }

        // Busy-wait until all programs have been updated.
        while (updatedPrograms.size() < outdatedPrograms.size()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                DebugUtil.warn("Failed to sleep thread", ex);
                Thread.currentThread().interrupt();
            }
        }

        // Displays a final notification message.
        if (excludedCount == outdatedPrograms.size()) {
            Toast.toast(ToastType.INFO, "RepairKit", "No outdated programs found.");
            DebugUtil.debug("No outdated programs found.");
        } else {
            Toast.toast(ToastType.INFO, "RepairKit", "All programs are up to date.");
            DebugUtil.debug("All programs are up to date.");
        }
    }

    private static void installDependencies() {
        // Installs NuGet and the WinGet client.
        CommandUtil.runPowerShellCommand("Install-PackageProvider -Name NuGet -Force | Out-Null", false);
        CommandUtil.runPowerShellCommand("Install-Module -Name Microsoft.WinGet.Client -Force -Repository PSGallery | Out-Null", false);
    }

    private static @NotNull List<String> getOutdatedPrograms() {
        Toast.toast(ToastType.INFO, "RepairKit", "Checking for outdated programs...");
        DebugUtil.debug("Checking for outdated programs...");

        // Installs the required dependencies for WinGet.
        installDependencies();

        // Gets the list of outdated programs.
        @NotNull List<String> output = CommandUtil.getPowerShellCommandOutput("Get-WinGetPackage -Source winget"
                + " | Where-Object IsUpdateAvailable | Select-Object -ExpandProperty Id", false, false);

        @NotNull List<String> programs = new ArrayList<>();

        // Ignores empty lines.
        for (@NotNull String line : output) {
            DebugUtil.debug("Found program: " + line);
            @NotNull String trim = line.trim();

            if (trim.isEmpty()) {
                DebugUtil.debug("Skipping blank program...");
                continue;
            }

            DebugUtil.debug("Adding program: " + trim);
            programs.add(trim);
        }

        DebugUtil.debug("Found " + programs.size() + " outdated programs.");
        DebugUtil.debug("Programs: " + programs);

        StringBuilder builder = new StringBuilder();
        builder.append("Make sure to close the following programs before proceeding:\n");

        // Adds the programs to the builder.
        for (String program : programs) {
            if (excludedPrograms.contains(program)) {
                DebugUtil.debug("Skipping excluded program: " + program);
                continue;
            }

            builder.append("\n- ").append(program);
        }

        // Check if the builder is empty, aside from the initial message.
        if (builder.toString().equals("Make sure to close the following programs before proceeding:\n")) {
            return programs;
        }

        SoundUtil.playSound(ConstantUtil.WARNING_SOUND);
        JOptionPane.showMessageDialog(null, builder.toString(),
                "Update Outdated Programs", JOptionPane.WARNING_MESSAGE);
        return programs;
    }

    private static boolean updatePackage(String id) {
        String output = CommandUtil.getPowerShellCommandOutput("winget upgrade --id " + id
                        + " --disable-interactivity --silent --accept-package-agreements --accept-source-agreements",
                false, false).toString();
        DebugUtil.debug("Output: " + output);

        return !output.contains("The package cannot be upgraded")
                && !output.contains("This package's version number cannot be determined")
                && !output.contains("Installer hash does not match")
                && !output.contains("No available upgrade found");
    }
}
