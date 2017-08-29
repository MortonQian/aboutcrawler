package webcrawler.webcrawlersecond;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Live2OtherCrawler {

	public static void Get_Localhtml(String path){
		File file = new File(path);
		//生成一个数组用来存贮这些路径下的文件名
		File[] array = file.listFiles();
		
		//循环获取这些文件的名字
		for(int i=0;i<array.length;i++){
			try{
				if(array[i].isFile()){
					System.out.println("正在解析网址："+array[i].getName());
					
					//开始解析本地的html
					Document doc=Jsoup.parse(array[i],"UTF-8");
					
					//得到html中的所有东西
					Element content = doc.getElementById("content");
					Elements links = doc.getElementsByTag("a");
					//Elements links = doc.select("a[href]");
					Elements pngs = doc.select("img[src$=.png]");
					Element masthead = doc.select("div.mastheat").first();
				
					for(Element link : links){
						String linkHref= link.attr("href");
						String linkText = link.text();
						System.out.println(linkText);
					}
					
				}
			}catch(Exception e){
				System.out.println("网址："+path+"解析错误");
				e.printStackTrace();
				continue;
			}
		}
	}
	
	
	public static void main(String[] args) {
		String url = "http://www.cnblogs.com/TTyb/";
		String path = "com/webcrawler/";
		Get_Localhtml(path);
	}
}
