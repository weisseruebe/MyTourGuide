package Model;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

@Root
public class Tour {

	@Attribute
	public String name = "";
	@ElementList
	public List<Poi> poi = new ArrayList<Poi>();

	public Tour() {
		super();
	}

	public void save(String path) throws Exception{
		Serializer serializer = new Persister();
		serializer.write(this, new File(path));
	}

	public static Tour load(String path) throws Exception{
		Serializer serializer = new Persister();
		return serializer.read(Tour.class, new File(path));
	}

}
