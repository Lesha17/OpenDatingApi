plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

group = "com.opendating.api"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mongodb:mongodb-driver-core:5.0.1")
    implementation("org.mongodb:mongodb-driver-sync:5.0.1")

    testCompileOnly("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.11.0")
}

tasks.test {
    useJUnitPlatform()
}