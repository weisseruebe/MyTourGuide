package Model;


import java.io.File;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

@Root
public class Poi {

	@Element
	public Integer lat;
	@Element
	public Integer lon;
	@Attribute
	public String name;


	public Poi() {
		super();
	}


	public Poi(int lat, int lon, String name) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}


	public void save(String path) throws Exception{
		Serializer serializer = new Persister();
		serializer.write(this, new File(path));
	}

	public static Poi load(String path) throws Exception{
		Serializer serializer = new Persister();
		return serializer.read(Poi.class, new File(path));
	}

}
