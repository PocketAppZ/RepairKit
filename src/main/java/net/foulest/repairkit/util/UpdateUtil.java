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
package net.foulest.repairkit.util;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.foulest.repairkit.util.CommandUtil.runCommand;
import static net.foulest.repairkit.util.ConstantUtil.ERROR_SOUND;
import static net.foulest.repairkit.util.FileUtil.getVersionFromProperties;
import static net.foulest.repairkit.util.SoundUtil.playSound;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUtil {

    private static final String REPO_API_URL = "https://api.github.com/repos/Foulest/RepairKit/releases/latest";
    private static final String DOWNLOAD_URL = "https://github.com/Foulest/RepairKit/releases/latest";
    public static final boolean CONNECTED_TO_INTERNET;

    static {
        CONNECTED_TO_INTERNET = connectedToInternet();
    }

    /**
     * Checks for updates and logs the result.
     */
    public static void checkForUpdates() {
        String latestVersion = getLatestReleaseVersion();
        String currentVersion = getVersionFromProperties();

        if (latestVersion != null) {
            if (!latestVersion.equals(currentVersion)) {
                playSound("win.sound.exclamation");

                // When the user clicks "Yes", the program will open the GitHub page in the default browser.
                int result = JOptionPane.showConfirmDialog(null,
                        "A new version of RepairKit is available. Would you like to download it?",
                        "Update Available", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    runCommand("start \"\" \"" + DOWNLOAD_URL + "\"", true);
                }
            }
        } else {
            // If the user has internet access but the update check failed, we'll notify the user.
            if (CONNECTED_TO_INTERNET) {
                playSound(ERROR_SOUND);
                JOptionPane.showMessageDialog(null,
                        "Failed to check for updates. Please try again later.",
                        "Update Check Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Checks if the system has internet access.
     *
     * @return True if the system has internet access, otherwise false.
     */
    public static boolean connectedToInternet() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            connection.disconnect();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Fetches the latest release version from the GitHub API.
     *
     * @return The latest release version or null if an error occurred.
     */
    public static @Nullable String getLatestReleaseVersion() {
        try {
            URL url = new URL(REPO_API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            @Cleanup BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            connection.disconnect();
            return extractVersion(content.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts the version from the JSON response.
     *
     * @param jsonResponse The JSON response.
     * @return The version or null if not found.
     */
    public static @Nullable String extractVersion(String jsonResponse) {
        String versionRegex = "\"tag_name\":\"(.*?)\"";
        Pattern pattern = Pattern.compile(versionRegex);
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
