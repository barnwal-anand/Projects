import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.omg.CORBA.Environment;


public class JSoupHelper {
	private Document doc;
	
	public void connectToURL(String url) {
		int iterator = -1;
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US").timeout(10000).get();
			Elements vcards = doc.select(".vcard");
			
			for (Element vcard : vcards)
			{
				iterator++;
				Element nameInfo = vcard.select(".name-info").get(0);
				Element proTitle = nameInfo.select(".pro-title").get(0);
				//System.out.println("Title: " + proTitle.text());
				System.out.print(proTitle.text());
				
				String hyperlink = nameInfo.select("a").attr("href");
				//System.out.println("Link: " + hyperlink);
				System.out.print("\t" + hyperlink);
				
				if (vcard.select(".pro-phone").size() > 0) {
					Element phoneNumber = vcard.select(".pro-phone").get(0);
					//System.out.println("Contact number: " + phoneNumber.text());
					System.out.print("\t" + phoneNumber.text());
				}
				
				Element proInfo = vcard.select(".pro-info").get(0);
				Element proMeta = proInfo.select(".pro-meta").get(0);
				
				StringBuilder addressBuilder = new StringBuilder();
				
				if (proMeta.select("[itemprop=addressLocality]").size() > 0) {
					Element addressLocality = proMeta.select("[itemprop=addressLocality]").get(0);
					addressBuilder.append(addressLocality.text());
				}
				
				if (proMeta.select("[itemprop=postalCode]").size() > 0) {
					Element postalCode = proMeta.select("[itemprop=postalCode]").get(0);
					addressBuilder.append(", ");
					addressBuilder.append(postalCode.text());
				}
				
				if (proMeta.select("[itemprop=addressCountry]").size() > 0) {
					Element addressCountry = proMeta.select("[itemprop=addressCountry]").get(0);
					addressBuilder.append(", ");
					addressBuilder.append(addressCountry.text());
				}
				//System.out.println("Address: " + addressBuilder.toString());
				System.out.print("\t" + addressBuilder.toString());
				
				if (proInfo.select(".pro-miles-from-city").size() > 0) {
					Element milesFromCity = proInfo.select(".pro-miles-from-city").get(0);
					//System.out.println("Miles: " + milesFromCity.text());
					System.out.print("\t" + milesFromCity.text());
				}
				
				Element proDescription = vcard.select(".pro-description").get(0);
				
				//System.out.println("Description: " + proDescription.text());
				System.out.print("\t" + proDescription.text());
				
				System.out.println();
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Out of bounds for index + " + iterator);
		}
	}
}
