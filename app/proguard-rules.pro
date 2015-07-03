# Proguard settings

# Preservar a classe R
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Preservar enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preservar classes serializáveis
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preservar código fonte local
-keep class com.hpedrorodrigues.dradddle.** { *; }

# Retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Support library v4
-dontwarn android.support.v4.app.**
-dontwarn android.support.v4.view.**
-dontwarn android.support.v4.widget.**

## Support library v7
-dontwarn android.support.v7.media.**

# OKHttp
-dontwarn com.squareup.okhttp.**

# Okio
-dontwarn okio.**

# RxAndroid
-dontwarn rx.**

# SuperRecyclerView
-dontwarn com.malinskiy.superrecyclerview.**

# Support Design
-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }
-dontwarn android.support.design.**

# Evitar warnings causados pelo sdk do kotlin
-dontwarn kotlin.**