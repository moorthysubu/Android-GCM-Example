# Android-GCM-Example
Gcm integration

GCM - Example:

First need to create google.service.json file from google developer console

Link : https://developers.google.com/cloud-messaging/android/client#get-config

####Follow the link:

click on get configuration file from developer console.

add "google-services.json" file in app folder.

#####add below line in top level
classpath 'com.google.gms:google-services:2.0.0-alpha5'

#####add below line in app level dependencies
compile 'com.google.android.gms:play-services-gcm:8.4.0'

#####then add plugin app gradle:
apply plugin: 'com.google.gms.google-services'

add gcm package into your project.

#####add following lines in androidManifest.xml.

<service
            android:name="com.gcmexample.gcm.MyGcmListenerService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.android.c2dm.intent.RECEIVE" />
            </intent-filter>
        </service>

        <service
            android:name="com.gcmexample.gcm.MyInstanceIDListenerService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.android.gms.iid.InstanceID" />
            </intent-filter>
        </service>

        <service
            android:name="com.gcmexample.gcm.RegistrationIntentService"
            android:exported="false" />

        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />

add following permissions:

<permission
        android:name="com.gcm_example.permission.C2D_MESSAGE"
        android:protectionLevel="signature" />

    <uses-permission android:name="com.gcm_example.permission.C2D_MESSAGE" />
    <uses-permission android:name="com.google.android.c2dm.permission.SEND" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />

