package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddLocation;
import pojo.AddPlace;

public class TestDataBuild {
	public static AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);
		List<String> addtype = new ArrayList<String>();
		addtype.add("shoe park");
		addtype.add("shop");
		
		p.setTypes(addtype);
		AddLocation  l = new AddLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public String getPlaceData(String place_id ) {
		
		return "{\"place_id\":\""+place_id+"\"}";
	}

}
