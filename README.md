# RepairKit

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![CodeQL Badge](https://github.com/Foulest/RepairKit/actions/workflows/codeql.yml/badge.svg)](https://github.com/Foulest/RepairKit/actions/workflows/codeql.yml)
[![Downloads](https://img.shields.io/github/downloads/Foulest/RepairKit/total.svg)](https://github.com/Foulest/RepairKit/releases)
[![Lines of Code](https://img.shields.io/endpoint?url=https://ghloc.vercel.app/api/Foulest/RepairKit/badge?filter=.java$&style=flat&logoColor=white&label=Lines%20of%20Code)](https://ghloc.vercel.app/Foulest/RepairKit?branch=main)

**RepairKit** is an all-in-one Java-based Windows repair and maintenance toolkit.

[Softpedia](https://softpedia.com/get/System/OS-Enhancements/RepairKit.shtml)
• [MajorGeeks](https://m.majorgeeks.com/files/details/repairkit.html)
• [Uptodown](https://repairkit.en.uptodown.com/windows)
• [AlternativeTo](https://alternativeto.net/software/repairkit/about)
• [SoftDownload](https://softdownload.com.br/repare-pc-automaticamente-repairkit.html)

## Features

### **Automatic Repairs**

Automatically perform a comprehensive system cleanup and repair, including:

- Deleting restrictive system policies
- Removing pre-installed bloatware
- Repairing various disk issues
- Running registry, service, and settings tweaks
- Scanning for malware with security software

> **Note:** The malware scan automatically runs a quick scan with Windows Defender. In the event that Windows Defender
> is disabled or unavailable, a quick scan is performed with Sophos Scan & Clean instead.

![Automatic Repairs](https://i.imgur.com/OzTvpIL.png)

### **Useful Programs**

Access essential software tools for system maintenance, including:

- **[CPU-Z](https://cpuid.com/softwares/cpu-z.html)**: Identify your system hardware details.
- **[HWMonitor](https://cpuid.com/softwares/hwmonitor.html)**: Monitor hardware temperatures and specs.
- **[Autoruns](https://learn.microsoft.com/en-us/sysinternals/downloads/autoruns)**: An alternative to Windows Startup
  Manager.
- **[Process Explorer](https://learn.microsoft.com/en-us/sysinternals/downloads/process-explorer)**: An alternative to
  Windows Task Manager.
- **[TreeSize](https://jam-software.com/treesize_free)**: Analyze and manage disk contents.
- **[Everything](https://voidtools.com)**: An ultra-fast file search engine.
- **[FanControl](https://getfancontrol.com)**: Configure your system fans and their speeds.
- **[NVCleanstall](https://techpowerup.com/download/techpowerup-nvcleanstall)**: A lightweight NVIDIA graphics card
  driver updater.
- **[Emsisoft Scan](https://emsisoft.com/en/home/emergency-kit)**: Scan for malware using Emsisoft Emergency Kit.
- **[Sophos Scan](https://www.sophos.com/en-us/free-tools/virus-removal-tool)**: Scan for malware using Sophos Scan &
  Clean.
- **[uBlock Origin](https://ublockorigin.com)**: Link to the powerful ad-blocker browser extension.
- **[TrafficLight](https://bitdefender.com/solutions/trafficlight.html)**: Link to Bitdefender's TrafficLight browser
  extension.

![Useful Programs](https://i.imgur.com/w9KJ4J0.png)

### **System Shortcuts**

Quickly access important Windows utilities like:

- Apps & Features
- Startup Apps
- Windows Update
- Windows Security
- Task Manager

![System Shortcuts](https://i.imgur.com/BURrqr1.png)

## Download and Run

Not sure how to download and run RepairKit? Follow these steps:

1. Download the latest version of RepairKit from
   the [Releases page](https://github.com/Foulest/RepairKit/releases/latest) (click the `RepairKit-X.X.X.zip` file).
2. Extract the ZIP file to a folder on your computer. You can use a program like [7-Zip](https://7-zip.org) to extract
   the
   contents.
3. Double-click the `RepairKit-X.X.X.exe` file to run the program.
4. If Windows Defender SmartScreen blocks the app, click `More info` and then `Run anyway`.
5. Click `Yes` on the User Account Control prompt.

RepairKit will now open, and you can start using its features.

## Compiling

1. Clone the repository.
2. Open a command prompt/terminal to the repository directory.
3. Run `gradlew createExe` on Windows, or `./gradlew createExe` on macOS or Linux.
4. The built `RepairKit-X.X.X.zip` file will be in the `build` folder.

## Getting Help

For support or queries, please open an issue in the [Issues section](https://github.com/Foulest/RepairKit/issues).
