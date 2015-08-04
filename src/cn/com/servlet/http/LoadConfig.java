package cn.com.servlet.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class LoadConfig {
	private static ConfigBean cb = null;
	static{
		cb = new ConfigBean();
	}
	public static ConfigBean getConfig(){
		SAXBuilder build = new SAXBuilder();
		try {
			Document doc = build.build(new FileInputStream(new File("WEB-INF/web1.xml")));
			Element root = doc.getRootElement();
			Element wels = root.getChild("welcome-file-list",root.getNamespace());
			List<Element> welss = wels.getChildren("welcome-file", root.getNamespace());
			List<String> wel = new ArrayList<String>();
			for(Element e:welss){
				wel.add(e.getText());
			}
			cb.setWelcome(wel);
			
			List<Element> servlets = root.getChildren("servlet", root.getNamespace());
			List<Element> mappings = root.getChildren("servlet-mapping", root.getNamespace());
		
			Map<String, String> maps = new HashMap<String, String>();
			for(Element e:mappings){
				String pattern = e.getChildText("url-pattern", root.getNamespace());
				String name = e.getChildText("servlet-name", root.getNamespace());
				for(Element e1:servlets){
					String classs = e1.getChildText("servlet-class", root.getNamespace());
					String names = e1.getChildText("servlet-name", root.getNamespace());
					if(name.equals(names)){
						maps.put(pattern, classs);
						break;
					}
				}
			}
			cb.setServletInfo(maps);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cb;
	}
	/*public static void main(String[] args) {
		ConfigBean cn = getConfig();
		for(String s:cn.getWelcome()){
			System.out.println(s);
		}
		Map<String, String> map = cn.getServletInfo();
		for(String key:map.keySet()){
			System.out.println(map.get(key));
		}
	}*/
}
