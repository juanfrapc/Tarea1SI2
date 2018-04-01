package Model;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;

public class Grafo {

    public static final int TURTTLE = 0;
    public static final int XML = 1;
    public static final int NTRIPLE = 2;

    private static final String si2 = "http://www.si2.com/";
    private static final String aemet = "http://aemet.linkeddata.es/ontology/";
    private static final String geo = "http://www.w3.org/2003/01/geo/wgs84_pos#";

    private final Model modelo;
    private Toponimos toponimos = Toponimos.getInstance();
    private final Property indsinop;
    private final Property province;
    private final Property latitud;
    private final Property altitud;
    private final Property longitud;
    private Property nombre;

    public Grafo() {
        modelo = ModelFactory.createDefaultModel();
        modelo.setNsPrefix("si2", si2);
        modelo.setNsPrefix("aemet", aemet);
        modelo.setNsPrefix("geo", geo);
        nombre = ResourceFactory.createProperty(aemet, "stationName");
        indsinop = ResourceFactory.createProperty(aemet, "indsinop");
        province = ResourceFactory.createProperty(aemet, "locatedInProvince");
        latitud = ResourceFactory.createProperty(geo, "lat");
        altitud = ResourceFactory.createProperty(geo, "alt");
        longitud = ResourceFactory.createProperty(geo, "long");
    }

    public void addStation(JSONObject node) throws JSONException {
        Resource resource = modelo.createResource(si2 + node.getString("indicativo"));
        resource.addLiteral(nombre, node.getString("nombre"));
        resource.addLiteral(indsinop, node.getString("indsinop"));
        resource.addProperty(province, getProvinceNode(node));
        resource.addLiteral(latitud, node.getString("latitud"));
        resource.addLiteral(altitud, node.getString("altitud"));
        resource.addLiteral(longitud, node.getString("longitud"));
    }

    private Resource getProvinceNode(JSONObject node) throws JSONException {
        String provincia = toponimos.getCanonical(node.getString("provincia"));
        return modelo.getResource(si2 + provincia);
    }

    public void addStation(JSONArray array) throws JSONException {
        for (int i = 0; i < array.length(); i++) {
            addStation(array.getJSONObject(i));
        }
    }

    public void print(OutputStream stream, int type) {
        switch (type) {
            case TURTTLE:
                RDFDataMgr.write(stream, modelo, RDFFormat.TURTLE_PRETTY);
                break;
            case XML:
                RDFDataMgr.write(stream, modelo, RDFFormat.RDFXML_PRETTY);
                break;
            case NTRIPLE:
                RDFDataMgr.write(stream, modelo, RDFFormat.NTRIPLES);
                break;
        }
    }

}
