buildscript {
    extra["compose_ui_version"] = "1.6.8"
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.45")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.5.0" apply false
    id("com.android.library") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
}
