plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(29)
    defaultConfig {

        targetSdkVersion(29)


    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
    "implementation"(project(":model"))
    "implementation"(project(":extensions"))
    "implementation"("com.github.komputing:khex:${Versions.khex}")
}

