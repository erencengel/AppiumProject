package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class day1 {

    AppiumDriver<MobileElement> driver;

    @Test
    public void test1() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //we use android phone
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //version of android
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION,"8.0");
        //name of the device, if it real device we need to pass UUID parameter
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        //either you specify app --> path/to/app.apk
        //or if app is already installed, you need to specify appActivity and appPackage
        //this info you can find in the internet, at work - ask to developers
        //appİNFO.APK file -- cruical

        //Set your application's package name.
        desiredCapabilities.setCapability("appPackage","com.android.calculator2");

        //Set your application's Mainactivity i.e. the LAUNCHER activity name.
        desiredCapabilities.setCapability("appActivity","com.android.calculator2.Calculator");

        /*"http://localhost:4723/wd/hub --> address of the appium server. If you have appium server on the same computer...
         *just use local host
         * 4723 -> defualt port number
         * we need to provide 2 parameters: URL of appium servers and desired capabilities
         */
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        Thread.sleep(3000);
        driver.closeApp();


    }

}
