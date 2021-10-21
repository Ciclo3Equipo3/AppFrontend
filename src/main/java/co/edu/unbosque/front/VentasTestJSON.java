package co.edu.unbosque.front;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class VentasTestJSON {
	
	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static int postJSON(Ventas ventas) throws IOException {

		url = new URL(sitio + "ventas/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" 
				+ "\"codigo_venta\":\"" + String.valueOf(ventas.getCodigo_venta())
				+ "\",\"cedula_cliente\": \"" + String.valueOf(ventas.getCedula_cliente())
				+ "\",\"cedula_usuario\": \"" + String.valueOf(ventas.getCedula_usuario())
				+ "\",\"ivaventa\": \"" + String.valueOf(ventas.getIvaventa()) 
				+ "\",\"total_venta\": \"" + String.valueOf(ventas.getTotal_venta()) 
				+ "\",\"valor_vental\":\"" 	+ String.valueOf(ventas.getValor_vental()) 
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
}


