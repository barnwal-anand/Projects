
public class WebConnect {
	
	private static final String URL = "http://www.houzz.com/professionals/c/Gurgaon%2C-India/d/25";
	
	public static void main(String[] args) {
		JSoupHelper helper = new JSoupHelper();
		helper.connectToURL(URL);
		
		// crawl other pages
		for (int pageNumber = 1; pageNumber < 20; pageNumber++) {
			System.out.println();
			System.out.println("---------------Crawling Page " + pageNumber + "---------------");
			helper.connectToURL(URL + "/p/" + pageNumber * 15);
		}
	}
}
