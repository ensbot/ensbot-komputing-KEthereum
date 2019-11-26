apply {
    from("https://raw.githubusercontent.com/ligi/gradle-common/master/versions_plugin_stable_only.gradle")
}
buildscript {
    repositories {
        jcenter()
        maven("https://jitpack.io")
        google()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${Versions.versions_plugin}")
        classpath("com.github.komputing:kethabi:0.0.9")
        classpath("com.android.tools.build:gradle:3.5.2")
    }
}

subprojects {
    repositories {
        jcenter()
        maven("https://jitpack.io")
        maven("https://kotlin.bintray.com/kotlinx")
        google()
    }

    apply(plugin = "jacoco")
    apply(plugin = "maven-publish")

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

configure(subprojects.filter { !it.name.contains("android") }) {

    apply(plugin = "kotlin")

    configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }


    afterEvaluate {

        dependencies {
            "implementation"("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")

            "testImplementation"("org.assertj:assertj-core:3.14.0")
            "testImplementation"("org.junit.jupiter:junit-jupiter-api:${Versions.jupiter}")
            "testRuntime"("org.junit.jupiter:junit-jupiter-engine:${Versions.jupiter}")

            "testImplementation"("org.jetbrains.kotlin:kotlin-test")
            "testImplementation"("io.mockk:mockk:1.9.3")
        }
    }
}

apply {
    from("https://raw.githubusercontent.com/sky-uk/gradle-maven-plugin/master/gradle-mavenizer.gradle")
}

