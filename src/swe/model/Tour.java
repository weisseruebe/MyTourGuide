package swe.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
//TODO strict abbilden in MTL
@Root(strict=false)
public class Tour implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO initialisieren in MTL
	@Attribute
	public String name = "";
	@ElementList
	public List<Poi> poi = new ArrayList<Poi>();

	public Tour() {
		super();
	}

	public void save(OutputStream stream) throws Exception{
		Serializer serializer = new Persister();
		serializer.write(this, stream);
	}

	public static Tour load(InputStream stream) throws Exception{
		Serializer serializer = new Persister();
		return serializer.read(Tour.class,stream);
	}
	
	

}
