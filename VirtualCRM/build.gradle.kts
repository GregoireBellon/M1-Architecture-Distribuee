plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	id("io.freefair.lombok") version "8.4"

	id("com.google.osdetector") version "1.7.3"
	id("com.linecorp.thrift-gradle-plugin") version "+"
}

group = "com.ArchiDistribuee"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation ("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor ("org.mapstruct:mapstruct-processor:1.5.5.Final")

	implementation("org.apache.thrift:libthrift:0.14.1")
	
}


testing {
	suites {
		val integrationTest by registering(JvmTestSuite::class){

			dependencies{
				implementation(project())
			}
		}
	}
}

tasks {	
	
	withType<Test> {
		useJUnitPlatform()
	}

	compileThrift{
		thriftExecutable = "../thrift/libs/0.17.0/thrift.${osdetector.classifier}"
		sourceItems.from(project.layout.files("../thrift/src"))
	}
}
