package Model;

import java.util.HashMap;
import java.util.Map;

public class Toponimos {

    private static Toponimos ourInstance = new Toponimos();
    public static Toponimos getInstance() {
        return ourInstance;
    }
    private Map<String, String> map;

    private Toponimos() {
        map = new HashMap<>();
        initilize();
    }

    private void initilize() {
        map.put("A CORUÑA", "ACoruña");
        map.put("LUGO", "Lugo");
        map.put("OURENSE", "Ourense");
        map.put("PONTEVEDRA", "Pontevedra");

        map.put("ASTURIAS", "Asturias");
        map.put("CANTABRIA", "Cantabria");

        map.put("BIZKAIA", "Bizkaia");
        map.put("GIPUZKOA", "Gipuzkoa");
        map.put("Alava", "Alava");

        map.put("LA RIOJA", "LaRioja");
        map.put("NAVARRA", "Navarra");

        map.put("HUESCA", "Huesca");
        map.put("ZARAGOZA", "Zaragoza");
        map.put("TERUEL", "Teruel");

        map.put("LLEIDA", "Lleida");
        map.put("GIRONA", "Girona");
        map.put("TARRAGONA", "Tarragona");
        map.put("BARCELONA", "Barcelona");

        map.put("LEON", "Leon");
        map.put("PALENCIA", "Palencia");
        map.put("ZAMORA", "Zamora");
        map.put("SORIA", "Soria");
        map.put("VALLADOLID", "Valladolid");
        map.put("BURGOS", "Burgos");
        map.put("SEGOVIA", "Segovia");
        map.put("AVILA", "Avila");
        map.put("SALAMANCA", "Salamanca");

        map.put("MADRID", "Madrid");

        map.put("GUADALAJARA", "Guadalajara");
        map.put("TOLEDO", "Toledo");
        map.put("CUENCA", "Cuenca");
        map.put("CIUDAD REAL", "Ciudad-Real");
        map.put("ALBACETE", "Albacete");

        map.put("CASTELLON", "Castellon");
        map.put("VALENCIA", "Valencia");
        map.put("ALICANTE", "Alicante");

        map.put("ILLES BALEARS", "IllesBalears");

        map.put("CACERES", "Caceres");
        map.put("BADAJOZ", "Badajoz");

        map.put("MURCIA", "Murcia");

        map.put("HUELVA", "Huelva");
        map.put("SEVILLA", "Sevilla");
        map.put("CORDOBA", "Cordoba");
        map.put("JAEN", "Jaen");
        map.put("CADIZ", "Cadiz");
        map.put("MALAGA", "Malaga");
        map.put("GRANADA", "Granada");
        map.put("ALMERIA", "Almeria");

        map.put("Sta. CRUZ DE TENERIFE", "SantaCruzDeTenerife");
        map.put("LAS PALMAS", "LasPalmas");
    }

    public String getCanonical(String key) {
        return map.get(key);
    }
}
