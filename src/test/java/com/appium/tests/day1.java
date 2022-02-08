package com.appium.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
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

        //Set the automation name
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");

        /*"http://localhost:4723/wd/hub --> address of the appium server. If you have appium server on the same computer...
         *just use local host
         * 4723 -> defualt port number
         * we need to provide 2 parameters: URL of appium servers and desired capabilities
         */
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        Thread.sleep(3000);

        //Test 2+2 is returning 4
        MobileElement digit2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        //mobileBy is child class of by
        MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        MobileElement equals = driver.findElement(MobileBy.AccessibilityId("equals"));
        MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

        digit2.click();
        Thread.sleep(1000);
        plus.click();
        Thread.sleep(1000);
        digit2.click();
        Thread.sleep(1000);
        equals.click();
        Thread.sleep(1000);

        //get the text of mobile element of result
        String resultText = result.getText();

        Assert.assertEquals(Integer.parseInt(resultText),4);
        Thread.sleep(3000);

        //verify 4 * 5 = 20
        MobileElement digit4 = driver.findElement(By.id("com.android.calculator2:id/digit_4"));
        MobileElement multiplier = driver.findElement(MobileBy.AccessibilityId("multiply"));
        //exactly same the above one --> findElementByAccessbilityId == findElement(By...)
        multiplier = driver.findElementByAccessibilityId("multiply");
        MobileElement digit5 = driver.findElement(By.id("com.android.calculator2:id/digit_5"));
        digit4.click();
        Thread.sleep(1000);
        multiplier.click();
        Thread.sleep(1000);
        digit5.click();
        Thread.sleep(1000);
        equals.click();
        Thread.sleep(1000);
        resultText = result.getText();
        Assert.assertEquals(Integer.parseInt(resultText),20);
        Thread.sleep(3000);

        //50-35=15
        getDigit(5).click();
        getDigit(0).click();
        MobileElement minus = driver.findElement(MobileBy.AccessibilityId("minus"));
        minus.click();
        getDigit(3).click();
        getDigit(5).click();
        equals.click();
        resultText=result.getText();
        Assert.assertEquals(Integer.parseInt(resultText),15);
        Thread.sleep(3000);


        //close the app
        driver.closeApp();

    }

    //CREATE A METHOD THAT İS RETURNİNG MOBİLE ELEMENT OF THE DİGİT THAT YOU PASS A PARAMETER
    public MobileElement getDigit(int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }

    @Test
    public void test2() throws MalformedURLException, InterruptedException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION,"8.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UIAutomator2");
        //remote app on the cloud
        desiredCapabilities.setCapability("app","https://cybertek-appium.s3.amazonaws.com/etsy.apk");
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"),desiredCapabilities);
        Thread.sleep(3000);
        driver.closeApp();
    }

}
