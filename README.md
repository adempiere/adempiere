# ADempiere porting to Gradle

### Introduction this branch

This branch, feature/GRADLE.001 contains everything related work I'm doing to accomplish the goals detailed below.

- Porting of the ADempiere build system from Ant to Gradle
- Review of current ADempiere's project structure toward a cleaner and more efficient structure.
- Review of each subdirectory (we mean subproject) structure toward a Maven like structure and removal of any garbage or anything not related with that subproject's build

This README.md file will contain a detailed documentation of all the steps/changes takes to reach the goal.

##### NOTE:
Because of the changes I'm applying to the structure of each of the subprojects sources structure to convert them to a Maven compatible directories layout, the changes I'm going to apply here cannot be merged directly into the current ADempiere codebase by going through pull requests or other known mechanisms. This will require a precise, step by step change to have the same structure there if the ADempiere team will decide to get this changes in their codebase. For this reason, I consider this project an EXPERIMENT to test if this radical change would be possible and with which kind of effort

### Where I started from

I started my work by importing ADempiere branch release/3.9.0 final. Then, starting from that branch we defined this new branch feature/GRADLE.001 that is the branch were we're currently working on.

### How I do I intend to proceed, overall

1. Identify a Maven-compatible structure for zkwebui and all projects with structure similar to org.eevolution.freight and try to build these projects using Maven
2. Identify which directories/files (files means not code for the moment but only complementary files for the moment) can be removed because not used or not in scope for the project: light cleanup
3. Adopt a name and numbering schema (http://semver.org/).
4. Change directory structure to be Maven-compatible and reworking current buld.xml files so that everything can be build by going to the new directory layout and obtaining same jar but with new naming and numbering scheme.
5. Reworking the RUN_setup procedures so that it uses the artifact with new naming and numbering scheme.
6. Migrate all build procedures to Gradle and gradually remove dependendencies from the project (RUN_setup for the moment remains as an ANT procedure in my opinion)
7. Done \o/.

