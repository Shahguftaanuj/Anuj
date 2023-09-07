package com.testNGPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTableToCSV {
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
        // Set up the WebDriver (ChromeDriver in this example)
        System.setProperty("webdriver.chrome.driver", "C:/Users/shaha/Downloads/chromedriver_win32.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Navigate to the web page
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        // Find the table element
        WebElement table = driver.findElement(By.xpath("//table[@id='challenging_dom']"));

        // Extract table rows
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Create a list to store table data
        List<String[]> tableData = new ArrayList<>();

        // Iterate through rows and extract data
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String[] rowData = new String[cells.size()];
            for (int i = 0; i < cells.size(); i++) {
                rowData[i] = cells.get(i).getText();
            }
            tableData.add(rowData);
        }

        // Generate CSV file with timestamp
        String timestamp = java.time.LocalDateTime.now().toString().replace(":", "-");
        String csvFileName = "/HOME/briq/webtable_" + timestamp + ".csv";

        try (FileWriter writer = new FileWriter(csvFileName)) {
            // Write CSV header
            writer.write("Lorem,Ipsum,Dolor,Sit,Amet,Diceret,Action\n");

            // Write table data to CSV
            for (String[] rowData : tableData) {
                writer.write(String.join(",", rowData) + "\n");
            }

            System.out.println("CSV file created successfully: " + csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the WebDriver
        driver.quit();
    }
}
