package webcrawler.webcrawlermix;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MixCrawler {

	public static void Save_Html(String url){
		try{
			File dest = new File("com/crawler/"+"newhtml.html");
			
			//接收字节输入流
			InputStream is;
			//字节输出流
			FileOutputStream fos = new FileOutputStream(dest);
			
			URL temp= new URL(url);
			URLConnection uc = temp.openConnection();
			
			uc.addRequestProperty("User-Agent", "Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
			
			is = temp.openStream();
			
			BufferedInputStream bis= new BufferedInputStream(is);
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
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("openStream流错误，跳转get流");
			//如果上面的那种方法解析错误
			//那么就用下面这一种方法解析
			try {
				Document doc = Jsoup.connect(url)
							.userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
							.timeout(3000).get();
				File dest = new File("com/crawler/"+"newhtml.html");
				if(!dest.exists()){
					dest.createNewFile();
				}
				FileOutputStream out = new FileOutputStream(dest,false);
				out.write(doc.toString().getBytes("utf-8"));
				out.close();
				
			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("get流错误，请检查网址是否正确");
			}
		}
	}
	
	//解析本地的html
	public static void Get_Localhtml(String path){
		File file= new File(path);
		File[] array = file.listFiles();
		
		for(int i=0;i<array.length;i++){
			try{
				if(array[i].isFile()){
					System.out.println("正在解析网址："+array[i].getName());
					//文件地址加文件名字
					//System.out.println("#####" + array[i]); 
					//一样的文件地址加文件名字
					//System.out.println("*****" + array[i].getPath()); 
					
					Document doc = Jsoup.parse(array[i],"UTF-8");
					Element content = doc.getElementById("content");
					Elements links= content.getElementsByTag("a");
					Elements pngs=doc.select("img[src$=.png]");
					Element masthead = doc.select("div.masthead").first();
					
					for(Element link: links){
						String linkHref = link.attr("href");
						String linkText = link.text();
						System.out.println(linkText);
					}
				}
			}catch(Exception e){
				System.out.println("网址：" + array[i].getName() + "解析出错");
				e.printStackTrace();
				continue;
			}
		}
	}
	public static void main(String[] args) {
		String url = "http://www.cnblogs.com/TTyb/p/5784581.html";
		String path= "com/crawler/";
		//保存到本地的网页地址；
		Save_Html(url);
		//解析本地的网页地址
		Get_Localhtml(path);
	}
}
