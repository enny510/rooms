import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	war
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8


repositories {
	mavenCentral()
	maven {
		url = uri("https://plugins.gradle.org/m2/")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.thymeleaf:thymeleaf-spring5:3.0.11.RELEASE")
	implementation("jstl:jstl:1.2")
	implementation("com.fasterxml.jackson.core:jackson-core:2.9.9")
	implementation("org.glassfish.tyrus.bundles:tyrus-standalone-client:1.9")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

