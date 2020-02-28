TO DO

User Help and Administration Manual

• Add Firebase to your Android project [1]
Prerequisites
• Install or update Android Studio to its latest version.
• Make sure that your project meets these requirements:
• Targets API level 16 (Jelly Bean) or later
• Uses Gradle 4.1 or later
• Uses Jetpack (AndroidX), which includes meeting these version requirements:
• com.android.tools.build:gradle v3.2.1 or later
• compileSdkVersion 28 or later
• Set up a physical device or use an emulator to run your app.
Emulators must use an emulator image with Google Play.
• Sign into Firebase using your Google account.
If you don't already have an Android project and just want to try out a Firebase product, you can
download one of our quickstart samples.
You can connect your Android app to Firebase using one of the following options:
• Option 1: (recommended) Use the Firebase console setup workflow.
• Option 2: Use the Android Studio Firebase Assistant (requires additional configuration).
Option 1: Add Firebase using the Firebase console
Adding Firebase to your app involves tasks both in the Firebase console and in your open Android project
(for example, you download Firebase config files from the console, then move them into your Android
project).
Step 1: Create a Firebase project
Before you can add Firebase to your Android app, you need to create a Firebase project to connect to your
Android app. Visit Understand Firebase Projects to learn more about Firebase projects.
Create a Firebase project
Step 2: Register your app with Firebase
After you have a Firebase project, you can add your Android app to it.
Visit Understand Firebase Projects to learn more about best practices and considerations for adding apps

to a Firebase project, including how to handle multiple build variants.
1. Go to the Firebase console.
2. In the center of the project overview page, click the Android icon (plat_android) to launch the
setup workflow.
If you've already added an app to your Firebase project, click Add app to display the platform
options.
3. Enter your app's package name in the Android package name field.
• What's a package name, and where do you find it?
• (Optional) Enter other app information: App nickname and Debug signing certificate SHA-1.
4. How are the App nickname and the Debug signing certificate SHA-1 used within Firebase?
5. Click Register app.
Step 3: Add a Firebase configuration file
1. Add the Firebase Android configuration file to your app:
1. Click Download google-services.json to obtain your Firebase Android config file
(google-services.json).
2. Move your config file into the module (app-level) directory of your app.
• What do you need to know about this config file?
• To enable Firebase products in your app, add the google-services plugin to your Gradle files.
1. In your root-level (project-level) Gradle file (build.gradle), add rules to include the Google
Services Gradle plugin. Check that you have Google's Maven repository, as well.
buildscript {
repositories {
// Check that you have the following line (if not, add it):
google() // Google's Maven repository
}
dependencies {
// ...
// Add the following line:
classpath 'com.google.gms:google-services:4.3.3' // Google Services plugin
}
}
allprojects {
// ...
repositories {
// Check that you have the following line (if not, add it):
google() // Google's Maven repository
// ...
}
}
In your module (app-level) Gradle file (usually app/build.gradle), apply the Google Services
Gradle plugin:
apply plugin: 'com.android.application'
// Add the following line:
apply plugin: 'com.google.gms.google-services' // Google Services plugin
android {
// ...
}
Step 4: Add Firebase SDKs to your app

1. To your module (app-level) Gradle file (usually app/build.gradle), add the dependencies
for the Firebase products that you want to use in your app.
You can add any of the supported Firebase products to your Android app.
Analytics enabled Analytics not enabled
dependencies {
// ...
// Add the Firebase SDK for Google Analytics
implementation 'com.google.firebase:firebase-analytics:17.2.2'
// Add the SDKs for any other Firebase products you want to use in your app
// For example, to use Firebase Authentication and Cloud Firestore
implementation 'com.google.firebase:firebase-auth:19.2.0'
implementation 'com.google.firebase:firebase-firestore:21.4.0'
// Getting a "Could not find" error? Make sure that you've added
// Google's Maven repository to your root-level build.gradle file
}
1. Note: Some Firebase SDKs for Android offer an alternative Kotlin extensions library.
2. Sync your app to ensure that all dependencies have the necessary versions.
3. If you added Analytics, run your app to send verification to Firebase that you've successfully
integrated Firebase. Otherwise, you can skip the verification step.
Your device logs will display the Firebase verification that initialization is complete. If you ran
your app on an emulator that has network access, the Firebase console notifies you that your app
connection is complete.
That's it! You can skip ahead to the next steps.
Option 2: Add Firebase using the Firebase Assistant
The Firebase Assistant registers your app with a Firebase project and adds the necessary Firebase files
and code to your Android project — all from within Android Studio.
Caution: When using the Firebase Assistant, carefully follow the steps described in this section of the
guide to check that all your dependencies and settings are configured correctly.
Note that the recommended option to connect your app to Firebase is to use the Firebase console setup
workflow.
1. Open your Android project in Android Studio.
2. Select Tools > Firebase to open the Assistant window.
3. Expand one of the listed Firebase products (for example, Analytics), then click the provided
tutorial link (for example, Log an Analytics event).
4. Click Connect to Firebase to register your app with an existing or new Firebase project and to
automatically add the necessary files and code to your Android project.
5. Check that your plugin and library versions are up-to-date:
• In your root-level (project-level) Gradle file (build.gradle), check that your Google

Services plugin version is up-to-date (com.google.gms:google-
services:4.3.3).

• In your module (app-level) Gradle file (usually app/build.gradle), check that your
Firebase Android library versions are up-to-date.
Note: If you're using Android Studio v3.2 or earlier, also make sure that each dependency line
only has one version number specified.
6. Sync your app to ensure that all dependencies have the necessary versions.

7. Configure your Analytics data sharing settings in the Firebase console Project settings.
Enabling the sharing of Analytics data with other Firebase products is required to use Firebase
products like Firebase Predictions or Firebase A/B Testing.
8. Run your app to send verification to Firebase that you've successfully integrated Firebase.
Your device logs will display the Firebase verification that initialization is complete. If you ran
your app on an emulator that has network access, the Firebase console notifies you that your app
connection is complete.

Available libraries
The following Android libraries are available for the various Firebase products.
Some Firebase SDKs for Android offer an alternative Kotlin extensions library.
com.google.firebase:firebase-core. This SDK included the Firebase SDK for Google
Analytics. Now, to use Analytics (or any of the Firebase products that require or recommend the use of
Analytics), you need to explicitly add the Analytics dependency:
com.google.firebase:firebase-analytics:17.2.2.
Service or Product Gradle dependencies
Google Play services plugin com.google.gms:google-services:4.3.3
AdMob com.google.firebase:firebase-ads:18.3.0

(required) com.google.firebase:firebase-analytics:17.2.2

Analytics com.google.firebase:firebase-analytics:17.2.2
App Indexing com.google.firebase:firebase-appindexing:19.1.0
Authentication com.google.firebase:firebase-auth:19.2.0
Cloud Firestore

com.google.firebase:firebase-firestore:21.4.0

(alternatively for Kotlin) com.google.firebase:firebase-firestore-
ktx:21.4.0 (beta)

Cloud Functions for
Firebase Client SDK

com.google.firebase:firebase-functions:19.0.2

(alternatively for Kotlin) com.google.firebase:firebase-functions-
ktx:19.0.2 (beta)

Cloud Messaging com.google.firebase:firebase-messaging:20.1.0

(recommended) com.google.firebase:firebase-analytics:17.2.2

Cloud Storage

com.google.firebase:firebase-storage:19.1.1

(alternatively for Kotlin) com.google.firebase:firebase-storage-
ktx:19.1.1 (beta)

Crashlytics

com.crashlytics.sdk.android:crashlytics:2.10.1
(recommended) com.google.firebase:firebase-analytics:17.2.2
Alternatively, to use the Firebase Crashlytics SDK:
com.google.firebase:firebase-crashlytics:17.0.0-beta01 (beta)
(recommended) com.google.firebase:firebase-analytics:17.2.2

Dynamic Links

com.google.firebase:firebase-dynamic-links:19.1.0

(alternatively for Kotlin) com.google.firebase:firebase-dynamic-links-
ktx:19.1.0 (beta)

(recommended) com.google.firebase:firebase-analytics:17.2.2

In-App Messaging

com.google.firebase:firebase-inappmessaging:19.0.3

(alternatively for Kotlin) com.google.firebase:firebase-
inappmessaging-ktx:19.0.3 (beta)

(required) com.google.firebase:firebase-analytics:17.2.2

In-App Messaging Display

com.google.firebase:firebase-inappmessaging-display:19.0.3

(alternatively for Kotlin) com.google.firebase:firebase-
inappmessaging-display-ktx:19.0.3 (beta)

(required) com.google.firebase:firebase-analytics:17.2.2
ML Kit: Vision APIs com.google.firebase:firebase-ml-vision:24.0.1
ML Kit: Image Labeling
Model com.google.firebase:firebase-ml-vision-image-label-model:19.0.0
ML Kit: Object Detection
and Tracking Model com.google.firebase:firebase-ml-vision-object-detection-model:19.0.3
ML Kit: Face Detection
Model com.google.firebase:firebase-ml-vision-face-model:19.0.0
ML Kit: Barcode Scanning
Model com.google.firebase:firebase-ml-vision-barcode-model:16.0.2
ML Kit: AutoML Vision
Edge API com.google.firebase:firebase-ml-vision-automl:18.0.3
ML Kit: Natural Language
APIs com.google.firebase:firebase-ml-natural-language:22.0.0
ML Kit: Language
Identification Model

com.google.firebase:firebase-ml-natural-language-language-id-
model:20.0.7

ML Kit: Translate Model com.google.firebase:firebase-ml-natural-language-translate-
model:20.0.7

ML Kit: Smart Reply Model com.google.firebase:firebase-ml-natural-language-smart-reply-
model:20.0.7

ML Kit: Custom Model
APIs com.google.firebase:firebase-ml-model-interpreter:22.0.1
Performance Monitoring com.google.firebase:firebase-perf:19.0.5
Realtime Database

com.google.firebase:firebase-database:19.2.1

(alternatively for Kotlin) com.google.firebase:firebase-database-
ktx:19.2.1 (beta)

Remote Config

com.google.firebase:firebase-config:19.1.1

(alternatively for Kotlin) com.google.firebase:firebase-config-
ktx:19.1.1 (beta)

(recommended) com.google.firebase:firebase-analytics:17.2.2

Understand Firebase projects [2]
This page offers brief overviews of several important concepts about Firebase projects. When available,
follow the links to find more detailed information about features, services, and even other platforms. At
the bottom of this page, find a listing of general best practices for Firebase projects.
Relationship between Firebase projects, apps, and products
A Firebase project is the top-level entity for Firebase. In your project, you can add Firebase apps that can
be iOS, Android, or Web apps. After you configure your apps to use Firebase, you can then add the
Firebase SDKs for any number of Firebase products, like Analytics, Cloud Firestore, Performance
Monitoring, or Remote Config.
Learn more detailed information about this process in the Getting Started guides (iOS | Android | Web |
Unity | C++).
Relationship between Firebase projects and Google Cloud
Platform (GCP)
When you create a new Firebase project in the Firebase console, you're actually creating a Google Cloud
Platform (GCP) project behind the scenes. You can think of a GCP project as a virtual container for data,

code, configuration, and services. A Firebase project is a GCP project that has additional Firebase-
specific configurations and services. You can even create a GCP project first, then add Firebase to the

project later.
Since a Firebase project is a GCP project:
• Projects that appear in the Firebase console also appear in the GCP console and Google APIs
console.
• Billing and permissions for projects are shared across Firebase and GCP.
• Unique identifiers for a project (like project ID) are shared across Firebase and GCP.
• You can use products and APIs from both Firebase and GCP in your project.
• Deleting a project deletes it across Firebase and GCP.
Setting up a Firebase project and adding apps
You can set up a Firebase project and add apps in the Firebase console (or, for advanced use cases, via the
Firebase Management REST API). When you set up a project and add your apps, you need to make
several configuration decisions and add special configuration files or objects to your local projects.
Make sure to review some general best practices before setting up your project and adding apps.
The project name
When you create a project, you provide a project name. This identifier is the internal-only name for your
project in the Firebase console, the GCP console, and the Firebase CLI. The project name is not exposed
in any publicly visible Firebase or GCP product, service, or resource; it simply serves to help you more
easily distinguish your various projects.
The project ID
Your Firebase project (and its associated GCP project) has a project ID which is the globally unique
identifier for your project across all of Firebase and GCP. When you create a Firebase project, Firebase
automatically assigns a unique ID to your project, but you can edit it during setup.
If you delete a project, the project ID is also deleted and can never be used again by any other project.
Firebase resources and the project ID
The project ID displays in publicly visible Firebase resources, for example:
• Default Hosting subdomain — projectID.web.app and
projectID.firebaseapp.com
• Default Realtime Database URL — projectID.firebaseio.com
• Default Cloud Storage bucket name — projectID.appspot.com
For all of the aforementioned resources, you can create non-default instances. The publicly visible names
of non-defaults are fully-customizable. You can connect custom domains to your Firebase-hosted site,
shard your Realtime Database, and create multiple Cloud Storage buckets (visit your platform's Get
Started page).
The Firebase CLI and the project ID
For some use cases, you might have multiple Firebase projects associated with the same local app
directory. In these situations, when you use the Firebase CLI, you need to pass the --project flag
with your firebase commands to communicate which Firebase project you want to interact with.
You can also set up project aliases for your various Firebase projects so that you don't have to remember
project IDs.
API calls and the project ID

For many API calls, you need to include the project ID to identify the Firebase project.
Find your project ID
• In the Firebase console, click settings, then select Project settings. Your project ID is displayed
in the top pane.
• Using the Firebase CLI, run firebase projects:list. Your project ID is displayed along
with all the Firebase projects associated with your account.
Firebase config files and objects
When you add an app to your Firebase project, the Firebase console provides a Firebase configuration file
(Android/iOS) or a configuration object (web) that you add directly into your local project.
• For iOS, you add a GoogleService-Info.plist configuration file
• For Android, you add a google-services.json configuration file
• For web, you add a Firebase configuration object for your initialization scripts
At any time, you can obtain your Firebase config files or config object.
A Firebase config file or config object associates your app with your Firebase project and its resources
(databases, storage buckets, etc.).
The content is considered public, including your platform-specific ID (entered in the Firebase console
setup workflow) and values that are specific to your Firebase project, like your API Key, Realtime
Database URL, and Storage bucket name. Given this, use security rules to protect your data and files in
Realtime Database, Cloud Firestore, and Cloud Storage.
For open source projects, we generally do not recommend including your Firebase config file or config
object in source control because, in most cases, your users should create their own Firebase projects and
point their apps to their own backends.
Managing a Firebase project
Firebase console

The Firebase console offers the richest environment for managing Firebase products, apps, and project-
level settings.

The left-side panel of the console lists the Firebase products, organized by top-level categories. At the top
of the left-side panel, access your project settings by clicking settings (settings include integrations,
access permissions, and billing).
The middle of the console displays buttons that launch setup workflows to add various types of apps.
After you start using Firebase, the main area of the console changes into a dashboard that displays stats on
the products you use.
Firebase CLI (a command line tool)
Firebase also offers the Firebase CLI for configuring and managing specific Firebase products, like
Firebase Hosting and Cloud Functions for Firebase.
After installing the CLI using npm, you have access to the global firebase command. Use the CLI to
deploy new versions of your Firebase-hosted content or updates to your functions. You can also link your
local app directory to your Firebase project using the CLI.
Firebase Management REST API
Using the Firebase Management REST API, you can programmatically manage your Firebase project. For
example, you can programmatically add an Android app or an iOS app with your project. You can also
list the apps (Android / iOS) that are within your project.

References -
More Information is available at -
[1] https://firebase.google.com/docs/android/setup
[2] https://firebase.google.com/docs/projects/learn-more
