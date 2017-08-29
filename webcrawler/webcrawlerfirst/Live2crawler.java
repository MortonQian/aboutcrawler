package webcrawler.webcrawlerfirst;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Live2crawler {

	public static void Get_Url(String url){
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)").get();
			//得到html中的所有东西
			Element content = doc.getElementById("content");
		
			//分离出html 下<a>标签中的所有东西
			Elements links = doc.getElementsByTag("a");
			
			//扩展名为.png的图片
			Elements pngs = doc.select("img[src$=.png]");
			
			//class等于masthead的div标签
			Element masthead = doc.select("div.masthead").first();
			
			for (Element link : links){
				String linkHref = link.attr("href");
				String linkText = link.text();
				System.out.println(linkText);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Get_Url("https://www.baidu.com/index.php?tn=monline_3_dg");
	}
	
}
