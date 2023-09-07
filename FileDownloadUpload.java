package com.testNGPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FileDownloadUpload {
    public static void main(String[] args) {
        // Set the path to chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\path\\to\\chromedriver.exe");

        // Optional: Configure Chrome to automatically download files to a specific folder
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("download.default_directory=/path/to/downloaded_files");
        
        // Initialize ChromeDriver with the options
        WebDriver driver = new ChromeDriver(options);

        // Step 1: Download non-PNG file
        driver.get("https://the-internet.herokuapp.com/download");
        
        // Locate and click the download link (replace 'example.txt' with the actual file name)
        WebElement downloadLink = driver.findElement(By.linkText("example.txt"));
        downloadLink.click();

        // Sleep to allow time for the download to complete (you can use a better approach for waiting)
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Step 2: Upload the downloaded file
        driver.get("https://the-internet.herokuapp.com/upload");

        // Locate the file input element and send the file path of the downloaded file
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        fileInput.sendKeys("/path/to/downloaded_files/example.txt"); // Update with the actual file path

        // Click the "Upload" button
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        // Close the WebDriver
        driver.quit();
    }
}
