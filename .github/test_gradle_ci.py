"""Structural regression checks for the public Gradle CI contract."""

import hashlib
from pathlib import Path
import re
import unittest

ROOT = Path(__file__).resolve().parents[1]
VERSION = "8.14.6"
DISTRIBUTION_SHA256 = "7988ed071b2a07900e2ec715fca15c6ab72bce6db433af301fa7fa7e9407bd24"
# https://services.gradle.org/distributions/gradle-8.14.6-wrapper.jar.sha256
WRAPPER_SHA256 = "ad16bca46cb71bb5887c546860d93992aeee35c1fdaf4a242b0a612375d937e0"


class GradleCiTest(unittest.TestCase):
    def workflow(self, name):
        return (ROOT / ".github" / "workflows" / name).read_text()

    def test_wrapper_pins_verified_distribution(self):
        properties = (ROOT / "gradle/wrapper/gradle-wrapper.properties").read_text()
        self.assertIn(
            "distributionUrl=https\\://services.gradle.org/distributions/"
            f"gradle-{VERSION}-bin.zip",
            properties,
        )
        self.assertIn(f"distributionSha256Sum={DISTRIBUTION_SHA256}", properties)
        jar = (ROOT / "gradle/wrapper/gradle-wrapper.jar").read_bytes()
        self.assertEqual(hashlib.sha256(jar).hexdigest(), WRAPPER_SHA256)
        self.assertTrue((ROOT / "gradlew").is_file())
        self.assertTrue((ROOT / "gradlew.bat").is_file())

    def test_both_workflows_use_wrapper_and_keep_java_11(self):
        for name, task in (
            ("build_with_gradle.yml", "build"),
            ("publish_with_gradle.yml", "publish"),
        ):
            with self.subTest(workflow=name):
                text = self.workflow(name)
                self.assertRegex(text, rf"(?m)^\s+run: \./gradlew {task}\s*$")
                self.assertRegex(text, r"(?m)^\s+java-version: 11\s*$")
                self.assertNotRegex(text, r"(?m)^\s+run: gradle\s")
                self.assertIn("run: python3 .github/test_gradle_ci.py", text)

    def test_build_preserves_push_and_pull_request_triggers(self):
        text = self.workflow("build_with_gradle.yml")
        self.assertIn("  push:", text)
        self.assertIn("  pull_request:", text)
        self.assertEqual(text.count("- hotfix/**"), 2)
        self.assertIn("ADEMPIERE_LIBRARY_VERSION: 'test-version'", text)

    def test_publish_preserves_release_and_secret_gates(self):
        text = self.workflow("publish_with_gradle.yml")
        self.assertRegex(text, r"release:\s+types:\s+- published")
        self.assertIn("needs:-check-maven-secrets", re.sub(r"\s+", "", text))
        self.assertIn("if: needs.check-maven-secrets.outputs.is_have_secrets == 'true'", text)
        for name in ("PGP_SECRET", "PGP_PASSPHRASE", "OSSRH_TOKEN", "OSSRH_USERNAME"):
            self.assertIn(f"secrets.{name}", text)
        self.assertIn("ADEMPIERE_LIBRARY_VERSION: ${{ github.event.release.tag_name }}", text)


if __name__ == "__main__":
    unittest.main()
