pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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
}

rootProject.name = "MultiModuleProject"
include(":app")
include(":features")
include(":features:location")
include(":features:location:core")
include(":features:location:api")
include(":features:location:dagger")
include(":features:location:ui")
include(":features:weather")
include(":features:weather:api")
include(":features:weather:core")
include(":features:weather:dagger")
include(":features:weather:ui")
