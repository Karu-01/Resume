package miniproj;

import java.io.IOException;
import java.time.Duration;


public class Main {

	

	public static void main(String[] args) throws IOException, InterruptedException {
				
        Googlesearch obj=new Googlesearch();
        obj.createDriver();
        obj.clearExcel();
        Googlesearch.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        obj.webpagelinks();
        obj.searchOptions();
        obj.navigateToAll();
        obj.navigateToNews();
        obj.navigateToImage();
        obj.navigateToVideos();
        obj.driverClose();
	}

}
