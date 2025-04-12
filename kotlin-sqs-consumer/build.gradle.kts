import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.1.10"
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(platform("software.amazon.awssdk:bom:2.27.21"))
    implementation("software.amazon.awssdk:sqs")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}
tasks.test {
    useJUnitPlatform()
}