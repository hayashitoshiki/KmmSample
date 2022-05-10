pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KmmSample"
include(":android:androidApp")
include(":android:presentation:component")
include(":android:presentation:ui")
include(":android:data:repository")
include(":android:data:local")
include(":shared:domain")
include(":shared:common")
include(":shared:model")
include(":shared:test")
