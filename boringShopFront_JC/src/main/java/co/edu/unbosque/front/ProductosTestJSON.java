package co.edu.unbosque.front;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ProductosTestJSON {
	private static URL url;
	private static String sitio = "http://54.165.119.47:8080/Back_BoringShop-0.0.1-SNAPSHOT/";
	//private static String sitio = "http://localhost:5000/";
		
	public static ArrayList<Productos> parsingProductos(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<Productos> lista = new ArrayList<Productos>();
		JSONArray productos = (JSONArray) jsonParser.parse(json);
		Iterator i = productos.iterator();
		while (i.hasNext()) {
		JSONObject innerObj = (JSONObject) i.next();
			Productos producto = new Productos();
			producto.setCodigo_producto(Long.parseLong(innerObj.get("codigo_producto").toString()));
			producto.setIvacompra(Long.parseLong(innerObj.get("ivacompra").toString()));
			producto.setNombre_producto(innerObj.get("nombre_producto").toString());
			producto.setPrecio_compra(Long.parseLong(innerObj.get("precio_compra").toString()));
			producto.setPrecio_venta(Long.parseLong(innerObj.get("precio_venta").toString()));
			producto.setNitproveedor(Long.parseLong(innerObj.get("nitproveedor").toString()));
			lista.add(producto);
		}
		return lista;
	}
	

	public static ArrayList<Productos> getJSON() throws IOException, ParseException{
		
		url = new URL(sitio +"productos/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		
		for (int i = 0; i<inp.length ; i++) {
		   json += (char)inp[i];
		}
		System.out.println(json);
		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Productos> getforIdJSON(String id) throws IOException, ParseException { 

		url = new URL(sitio + "productos/ListarId/"+id); 
		HttpURLConnection http = (HttpURLConnection) url.openConnection();

		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");

		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		
		String json = "[";

		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		json = json + "]";
		ArrayList<Productos> lista = new ArrayList<Productos>();
		lista = parsingProductos(json);
		http.disconnect();
		return lista;
	}
	

public static int postJSON(Productos producto) throws IOException {
		
		url = new URL(sitio+"productos/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		
		try {
		  http.setRequestMethod("POST");
		} catch (ProtocolException e) {
		  e.printStackTrace();
		}
		
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"codigo_producto\":\""+ String.valueOf(producto.getCodigo_producto())
				+"\",\"ivacompra\": \""+String.valueOf(producto.getIvacompra())
				+"\",\"nombre_producto\": \""+producto.getNombre_producto()
				+"\",\"precio_compra\":\""+String.valueOf(producto.getPrecio_compra())
				+"\",\"precio_venta\":\""+String.valueOf(producto.getPrecio_venta())
				+"\",\"nitproveedor\":\""+String.valueOf(producto.getNitproveedor())
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
    
    public static int putJSON(Productos producto, Long id) throws IOException {
	
    	url = new URL(sitio+"productos/actualizar");
    	HttpURLConnection http;
    	http = (HttpURLConnection)url.openConnection();
	
    	try {
    		http.setRequestMethod("PUT");
    	} catch (ProtocolException e) {
    		e.printStackTrace();
    	}
	
    	http.setDoOutput(true);
    	http.setRequestProperty("Accept", "application/json");
    	http.setRequestProperty("Content-Type", "application/json");
	
    	String data = "{"
    			+ "\"codigo_producto\":\""+ String.valueOf(producto.getCodigo_producto())
				+"\",\"ivacompra\": \""+String.valueOf(producto.getIvacompra())
				+"\",\"nombre_producto\": \""+producto.getNombre_producto()
				+"\",\"precio_compra\":\""+String.valueOf(producto.getPrecio_compra())
				+"\",\"precio_venta\":\""+String.valueOf(producto.getPrecio_venta())
				+"\",\"nitproveedor\":\""+String.valueOf(producto.getNitproveedor())
				+ "\"}";
    	
    	byte[] out = data.getBytes(StandardCharsets.UTF_8);
    	OutputStream stream = http.getOutputStream();
    	stream.write(out);
	
    	int respuesta = http.getResponseCode();
    	http.disconnect();
    	
    	return respuesta;
    }

    public static int deleteJSON(Long id) throws IOException {

    	url = new URL(sitio+"productos/eliminar/" + id);
    	HttpURLConnection http;
    	http = (HttpURLConnection)url.openConnection();

    	try {
    		http.setRequestMethod("DELETE");
    	} catch (ProtocolException e) {
    		e.printStackTrace();
    	}

    	http.setDoOutput(true);
    	http.setRequestProperty("Accept", "application/json");
    	http.setRequestProperty("Content-Type", "application/json");

    	int respuesta = http.getResponseCode();
    	http.disconnect();
    	
    	return respuesta;
    }
	
	public static boolean validarCSV(String nombreArchivo) {
		int contador = 0;
        char valdador;
        for (int i = 0; i < nombreArchivo.length(); i++) {
        	valdador = nombreArchivo.charAt(i);
            if (valdador == '.')
            	contador++;
        }
        
        if (contador<2) {
        	if(nombreArchivo.contains(".csv")) {
    			return true;
    		}else {
    			return false;
    		}
        }else{
        	return false;
        }
	}
}
