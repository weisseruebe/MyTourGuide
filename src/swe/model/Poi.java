package swe.model;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

//TODO strict in mTL
@Root(strict=false)
public class Poi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO Datentyp nach Double
	@Element
	public Double lat;
	@Element
	public Double lon;
	@Attribute
	public String name;
	//TODO required in Profil
	@Attribute(required=false)
	public String description;

	public Poi() {
		super();
	}

	//TODO erzeugen
	public Poi(double lat, double lon, String name) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}

	public void save(OutputStream stream) throws Exception{
		Serializer serializer = new Persister();
		serializer.write(this, stream);
	}

	public static Poi load(InputStream stream) throws Exception{
		Serializer serializer = new Persister();
		return serializer.read(Poi.class, stream);
	}

}
