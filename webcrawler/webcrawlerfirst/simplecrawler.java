package webcrawler.webcrawlerfirst;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class simplecrawler {

	public static void Get_Url(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			System.out.println(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String url="https://www.baidu.com/";
		Get_Url(url);
	}
}
