## What is a Unit Test

A unit test verifies in isolation the functionality of a certain component. For example, assume a button in an Android activity is used to start another activity. A unit test would determine, if the corresponding intent was issued, not if the second activity was started

A unit tests for an Android application can be:

 - local unit test - which runs on the JVM

 - Android unit test - which runs on the Android runtime

If they run on the JVM, they are executed against a modified version of the android.jar Android library. In this version all final modifiers have been stripped off. This makes it easier to use mocking libraries, like Mockito.

The local unit tests of an Android project should be located in the app/src/test folder.


### Simple Android application

This is a simple Android Application which displays a view and also opens another activity when clicking on a button.

## Project setup

Now when we have an application to be tested we can create our first test. We will use [JUnit4](https://github.com/junit-team/junit4/wiki/getting-started) test runner and Kotlin programming language. Test runner is a library that runs our test code and aggregates results in a friendly way.

## Gradle Configuration

    buildscript {
     repositories {
      mavenCentral()
      jcenter()
     }
     dependencies {
      classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.0.5-2'
     }
    }

    apply plugin: 'com.android.application'
    apply plugin: 'kotlin-android'
    
    ...
    
    dependencies {
    ...
    compile "org.jetbrains.kotlin:kotlin-stdlib:1.0.6"

    ...
    testCompile 'junit:junit:4.12'

    compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.6'
    testCompile 'org.robolectric:robolectric:3.2.1'
    testCompile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.6'
    testCompile 'org.jetbrains.kotlin:kotlin-test-junit:1.0.6'
    testCompile 'com.nhaarman:mockito-kotlin:1.1.0'
    testCompile 'org.amshove.kluent:kluent:1.14'

    androidTestCompile('com.android.support.test:runner:0.5', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })

    androidTestCompile('com.android.support.test:rules:0.5',{
        exclude group: 'com.android.support', module: 'support-annotations'
    })

    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })

    androidTestCompile ('com.android.support.test.espresso:espresso-intents:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    }


Also we need to set the dedicated directories for tests written in Kotlin, this is done in our sourceSets section:

    sourceSets {
        //Dedicated directories for tests written in Kotlin
        test.java.srcDirs += 'src/test/kotlin'
        androidTest.java.srcDirs += 'src/androidTest/kotlin'
    }
    
We want to make sure, we do not allow accidentally any Kotlin code in production by also adding this defensive lines:

    //Ensure Kotlin will not be used in production code.
    afterEvaluate {
        android.sourceSets.all { sourceSet ->
            if(!sourceSet.name.startsWith('test') || !sourceSet.name.startsWith('androidTest')) {
                sourceSet.kotlin.setSrcDirs([])
            }
        }
    }

### JUnit Tests

1. GetUserDetailsTest
2. SerializerTest

Something to pay attention is that when we need to construct our subject under test (in the setup method), we must declare it “lateinit“, otherwise the compiler will complain, since properties must be initialized or be abstract.


### Robolectric (Integration?) Tests

1. FileManagerTest


### Espresso Acceptance Tests (UI) 

Things to keep an eye on here:

 - A test rule is needed within Espresso (from the documentation): This rule provides functional testing of a single activity. The activity under test will be launched before each test annotated with @Test and before methods annotated with @Before. It will be terminated after the test is completed and methods annotated with @After are finished. During the duration of the test you will be able to manipulate your Activity directly.
 - We must annotate our “testRule” field with @JvmField: this is necessary to turn this Kotlin property into a JVM field that JUnit can interpret.
 - Matchers class: A wrapper around Espresso checks.
 - Events class: Another wrapper encapsulating Espresso events.
 
 1. Matcher: 
 2. Events
 3. AcceptanceTest

### Gradle tasks

    task runUnitTests(dependsOn: [':app:testDebugUnitTest']) {
      description 'Run all unit tests'
    }
    
    task runAcceptanceTests(dependsOn: [':app:connectedAndroidTest']) {
      description 'Run all acceptance tests.'
    }
    
Just type this from the command line:

    ./gradlew runUnitTests
    ./gradlew runAcceptanceTests
    
This is a Project based on this [great article: ](https://fernandocejas.com/2017/02/03/android-testing-with-kotlin/)
