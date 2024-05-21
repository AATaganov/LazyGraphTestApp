pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("lib") {
            from(files("gradle/libs.versions.toml"))
        }
        create("testLib") {
            from(files("gradle/testLibs.versions.toml"))
        }
    }
}

rootProject.name = "LazyInitialization"
include(":app")
