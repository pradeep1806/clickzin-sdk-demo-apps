# Clickzin Android Demo SDK Integration

Clickzin Demo apps SDK

# Step 1 : Add maven in root directory build.gradle or in settings.gradle(for latest project structure)

    allprojects { 
        repositories { 
            google()
            // Clickzin App Changes starts here
            maven { 
                url "https://mymavenrepo.com/repo/gap8CjauTS8VrUs57tgf/"
            }
            // Clickzin App Changes starts here
        }
    }

On facing below errors,
Error : Build was configured to prefer settings repositories over project repositories but
repository 'Google' was added by build file 'build.gradle' in settings.gradle,

    change `repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)`
    to `repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)`

# Step 2 : In app build.gradle under dependencies add

    implementation 'com.clickzin.android:tracking:1.0.2'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation 'com.android.installreferrer:installreferrer:2.2'

# Step 3: In AndroidManifest.xml,add below permissions if not added.

    <!--    Clickzin changes starts here-->

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <!-- Optional : -->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <!--    Clickzin changes ends here-->

# Step 4: In AndroidManifest.xml, add below android receiver component as first component inside application TAG

    <!--    Clickzin changes starts here  -->
        <receiver
            android:name="com.clickzin.tracking.ClickzinReferrerReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="com.android.vending.INSTALL_REFERRER" />
            </intent-filter>
        </receiver>
    <!--    Clickzin changes starts here  -->

# Step 5: In first activity of your application, add below function startTracking and call this  function in onCreate().

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            // Step 5 : Clickzin changes starts here
            startTracking()
            // Step 5 : Clickzin changes ends starts here
    }

    // Step 5 : Clickzin changes starts here

    private lateinit var clickzinTracker: ClickzinTracker
    private fun startTracking() {
        val appKey = "As provided by your Digital Marketing team."
        clickzinTracker = ClickzinTracker(this@MainActivity, appKey)
        clickzinTracker.startTracking()
    }
    // Step 5 : Clickzin changes ends starts here

# Step 6: To track any specific events,

    // Step 6 : Clickzin changes starts here
    // keyword 'register' should be same as configured in panel.
    clickzinTracker.trackEvent('register')
    // Step 6 : Clickzin changes ends starts here

# Step 7 : If you are using progaurd, add below line

    -keep class com.clickzin.** { *; }


