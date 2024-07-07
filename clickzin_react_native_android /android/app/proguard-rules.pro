# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /usr/share/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.

# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project-specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# React Native specific rules
-keep class com.facebook.react.** { *; }
-keep class com.facebook.hermes.** { *; }
-keep class com.facebook.jni.** { *; }
-keep class com.facebook.proguard.annotations.DoNotStrip { *; }
-dontwarn com.facebook.react.**
-keepclassmembers class * {
  @com.facebook.proguard.annotations.DoNotStrip *;
}
-keepclassmembers class * extends com.facebook.react.bridge.BaseJavaModule {
  public <fields>;
}
-keepclassmembers class * extends com.facebook.react.bridge.JavaScriptModule {
  *;
}
-keepclassmembers class * extends com.facebook.react.uimanager.ViewManager {
  *;
}
-keep class com.facebook.react.bridge.** { *; }
-keep class com.facebook.react.uimanager.** { *; }
-keep class com.facebook.react.modules.** { *; }
-dontwarn com.facebook.react.**

# Ensure that logging works
-assumenosideeffects class com.facebook.common.logging.FLog {
   public static boolean isLoggable(int);
   public static boolean isLoggable(java.lang.String, int);
   public static void v(...);
   public static void v(java.lang.String, java.lang.String);
   public static void v(java.lang.String, java.lang.String, java.lang.Throwable);
   public static void d(...);
   public static void d(java.lang.String, java.lang.String);
   public static void d(java.lang.String, java.lang.String, java.lang.Throwable);
   public static void i(...);
   public static void i(java.lang.String, java.lang.String);
   public static void i(java.lang.String, java.lang.String, java.lang.Throwable);
   public static void w(...);
   public static void w(java.lang.String, java.lang.String);
   public static void w(java.lang.String, java.lang.String, java.lang.Throwable);
   public static void e(...);
   public static void e(java.lang.String, java.lang.String);
   public static void e(java.lang.String, java.lang.String, java.lang.Throwable);
}

# Workaround for https://github.com/facebook/react-native/issues/16432
-dontwarn java.nio.file.**
-keepclasseswithmembers class * {
    native <methods>;
}
-dontwarn okio.**
-dontwarn org.chromium.**
-dontwarn com.google.android.**
-dontwarn com.yqritc.**
-dontwarn com.RNFetchBlob.**
-dontwarn org.apache.**
-dontwarn com.microsoft.**

# Strip the debug symbols
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
