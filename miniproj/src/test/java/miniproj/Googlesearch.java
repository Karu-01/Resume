package miniproj;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;




public class Googlesearch{

        public static WebDriver driver;
        
        //for enter into the browser 
        public WebDriver createDriver() throws InterruptedException {
        	
        	System.out.println("Enter Browser Name");
            Scanner sc=new Scanner(System.in);
            String browserName=sc.nextLine();
            sc.close();
        	driver=Driversetup.driverInstantiate(browserName);
        	String baseUrl="https://www.google.com";
        	driver.get(baseUrl);
        	
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        	return driver;
        }
        
        public void clearExcel() throws IOException {
        	util.rmvExcel();
        }
        
         
        
        //select all the links in the link
        public void webpagelinks() throws IOException {
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        System.out.println("Number of links on the page: " + allLinks.size());
        System.out.println("List of links:");
        int row=1;
        util.setCellData("Sheet1", 0, 0, "Name");
        util.setCellData("Sheet1", 0, 1, "Links");
        
        for (WebElement link : allLinks) {
        	System.out.println(link.getText()+"-"+link.getAttribute("href"));
            //System.out.println(link.getText());
            util.setCellData("Sheet1", row, 0, link.getText());
            util.setCellData("Sheet1", row, 1, link.getAttribute("href"));
            row++;
        }
        }
        
        //Count the no of search options and the list and click on the the search button
        
        public void searchOptions() throws IOException, InterruptedException {
        
        driver.findElement(By.name("q")).sendKeys(util.readExcelData());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> searchOptions=driver.findElements(By.xpath("//ul[@role='listbox']/li"));									
        System.out.println("Number of search options displayed: " + searchOptions.size());
        takeScreenshot(driver, "SearchOptionsScreenshot");
        System.out.println("List of search options:");
        util.setCellData("Sheet1", 0, 2, "SearchOption");
        int row=1;
        for (WebElement option : searchOptions) {      	
            String op=option.getText();
            System.out.println(op);
            util.setCellData("Sheet1",row,2,op);
            row++;
        }
        driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
       }
        

        
        
        
        public void navigateToAll() throws IOException {
        
        

        WebElement searchResults = driver.findElement(By.id("result-stats"));
        System.out.println("Search results: " + searchResults.getText());
        
        //WebElement allTab = driver.findElement(By.xpath("//span[normalize-space()='All']"));
        //allTab.click();
        System.out.println("Number of search results for 'All': " + searchResults.getText());
        takeScreenshot(driver, "AllResultsScreenshot");
        
        }
        
        public void navigateToNews() throws IOException {
        WebElement newsTab = driver.findElement(By.xpath("//a[normalize-space()='News']"));
        newsTab.click();
        
        System.out.println("Number of search results for 'News': " + driver.findElement(By.xpath("//*[@id='result-stats']")).getText());
        takeScreenshot(driver, "NewsResultsScreenshot");
        }
        
        public void navigateToImage() throws IOException {
        WebElement imagesTab = driver.findElement(By.xpath("//a[normalize-space()='Images']"));
        imagesTab.click();
        takeScreenshot(driver, "ImagesResultsScreenshot");
        }
        
        public void navigateToVideos() throws IOException {
      
        WebElement videosTab = driver.findElement(By.xpath("//a[normalize-space()='Videos']"));
        //*[@id=\"yDmH0d\"]/div[2]/c-wiz/div[1]/div/div[1]/div[1]/div/div/a[4]
        videosTab.click();
        takeScreenshot(driver, "VideosResultsScreenshot");
        
        System.out.println("Number of search results for 'Videos': " + driver.findElement(By.xpath("//*[@id=\'result-stats\']")).getText());
        }
        
        public void driverClose() {
    		Driversetup.driver.quit();
    	}

        private static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            org.openqa.selenium.io.FileHandler.copy(screenshot, new File(".\\src\\test\\resources\\Screenshots\\"+fileName+ ".png"));
            //C:\\Users\\2303842\\eclipse-workspace\\miniproj\\src\\test\\java\\miniproj\\Screeshots\\
        	System.out.println("Screenshot saved: " + fileName + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
