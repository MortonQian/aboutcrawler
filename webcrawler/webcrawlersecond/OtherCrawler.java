package webcrawler.webcrawlersecond;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class OtherCrawler {

	public static void Save_Html(String url){
		try {
			File dest = new File("com/webcrawler/"+"iamhtml.html");
			
			InputStream is;
			
			FileOutputStream fos = new FileOutputStream(dest);
			
			URL temp = new URL(url);
//			is = temp.openStream();
			 temp = new URL(url);
			 URLConnection uc = temp.openConnection();
			 uc.addRequestProperty("User-Agent", "Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
			 is = temp.openStream();
			
			BufferedInputStream bis=new BufferedInputStream(is);
			
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			
			int length;
			
			byte[] bytes = new byte[1024*20];
			while((length = bis.read(bytes,0,bytes.length)) != -1){
				fos.write(bytes,0,length);
			}
			bos.close();
			fos.close();
			bis.close();
			is.close();
		} catch (IOException  e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Save_Html("http://www.cnblogs.com/TTyb/p/5784581.html");
	}
}
