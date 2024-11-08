import org.jetbrains.kotlin.fir.declarations.builder.buildScript
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false

}


buildscript {
    repositories {
        google()
    }
    dependencies {
        addSafeArgs()
    }
}
