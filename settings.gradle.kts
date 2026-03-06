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

rootProject.name = "Courses"
include(":app")
include(":domain")
include(":database")
include(":base-feature")
include(":uikit")
include(":course")
include(":course:common-feature")
include(":course:common-interactor")
include(":main")
include(":favourites")
include(":entry")
include(":main-screen")
include(":profile")
include(":base")
include(":network")
include(":session:interactor")
include(":transport")
