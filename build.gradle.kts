plugins {
	java
	war
	id("org.springframework.boot") version "2.7.9"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.pkware"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.hibernate:hibernate-core:5.6.9.Final")

	// https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp
	implementation("commons-dbcp:commons-dbcp:1.4")

	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("javax.xml.bind:jaxb-api:2.3.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
