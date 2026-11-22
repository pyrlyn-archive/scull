pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "scull"

// The seven core modules and their layering are in PLAN.md, section 3.
include(
    ":scull-unicode",
    ":scull-parser",
    ":scull-grid",
    ":scull-input",
    ":scull-pty",
    ":scull-term",
    ":scull-ffi",
)
