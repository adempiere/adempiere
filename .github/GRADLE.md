# Gradle CI

## Toolchain

Build and release publication use Java 11 and the checked-in Gradle Wrapper,
pinned to Gradle 8.14.6. Do not use the runner's globally installed `gradle`:
its version can change independently of this branch and may require a newer JVM.

The Wrapper distribution checksum is recorded in
`gradle/wrapper/gradle-wrapper.properties`. The structural regression test also
checks the Wrapper JAR against its official Gradle SHA-256 checksum.

## Local checks

Select a JDK 11 installation before running the Wrapper. On macOS, for example:

```sh
export JAVA_HOME="$(/usr/libexec/java_home -v 11)"
export PATH="$JAVA_HOME/bin:$PATH"
python3 .github/test_gradle_ci.py
./gradlew --version
ADEMPIERE_LIBRARY_VERSION=test-version ./gradlew build
```

The version output should report Gradle 8.14.6 and Java 11. Both GitHub Actions
workflows run the structural checks before invoking the Wrapper.

## Release safety

The publication workflow still runs only for a published release and only after
its Maven credential checks succeed. No credentials are needed for the structural
tests. To inspect the publication task graph without uploading artifacts:

```sh
./gradlew publish --dry-run
```

A dry run validates configuration and task selection, not signing, repository
credentials, or an actual Maven publication. Do not run a real `publish` as a CI
repair test. This toolchain change does not raise the configured Java version or
alter the release trigger and credential gates.
