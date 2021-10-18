package co.edu.unbosque.front;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.csvreader.CsvReader;







/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");

		switch (menu) {
		case "Principal":
			request.getRequestDispatcher("./Principal.jsp").forward(request, response);
			break;
		case "Usuarios":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setClave(request.getParameter("txtclave"));

				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.postJSON(usuario);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Actualizar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setClave(request.getParameter("txtclave"));

				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.putJSON(usuario, usuario.getCedula_usuario());
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				
				Long id = Long.parseLong(request.getParameter("id"));
				
				try {
					ArrayList<Usuarios> lista1 = UsuarioJSON.getJSON();
					System.out.println("PAETROR: " + id);
					for (Usuarios usuarios : lista1) {
						if (usuarios.getCedula_usuario() == id) {
							request.setAttribute("usuarioSeleccionado", usuarios);
							request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Eliminar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = UsuarioJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("./Usuarios.jsp").forward(request, response);
			break;
		case "Clientes":
	    	 if (accion.equals("Listar")) {
	    		 try {
	    			 ArrayList<Clientes> lista = ClientesTestJSON.getJSON();
	    			 request.setAttribute("lista", lista);
		     	 } catch (Exception e) {
		     		e.printStackTrace();
		     	 }
	    	 }else if(accion.equals("Agregar")) {
	    		 Clientes cliente = new Clientes();
	    		 cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
	    		 cliente.setNombre_cliente(request.getParameter("txtnombre"));
	    		 cliente.setEmail_cliente(request.getParameter("txtemail"));
	    		 cliente.setTelefono_cliente(request.getParameter("txttelefono"));
	    		 cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
	    		 	
	    		 int respuesta=0;
	    		 try {
	    			 respuesta = ClientesTestJSON.postJSON(cliente);
	    			 PrintWriter write = response.getWriter();
	    			 if (respuesta==200) {
	    				 request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request,
	                                                                               response);
	    			 } else {
	    				 write.println("Error: " +  respuesta);
	    			 }
				   		 write.close();
	    		 } catch (Exception e) {
	    			 e.printStackTrace();
	    		 }
	    	 }else if(accion.equals("Actualizar")) {
			     Clientes cliente = new Clientes();
	      
			     cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
			     cliente.setNombre_cliente(request.getParameter("txtnombre"));
			     cliente.setEmail_cliente(request.getParameter("txtemail"));
			     cliente.setTelefono_cliente(request.getParameter("txttelefono"));
			     cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				  
		          int respuesta=0;
		          try {
		        	  respuesta = ClientesTestJSON.putJSON(cliente,cliente.getCedula_cliente());
		        	  PrintWriter write = response.getWriter();
								
		        	  if (respuesta==200) {
		        		  request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
		        	  } else {
		        		  write.println("Error: " +  respuesta);
		        	  }
						  write.close();
				  } catch (Exception e) {
					   e.printStackTrace();
				  }
	    	 }else if(accion.equals("Cargar")) {
				Long id= Long.parseLong(request.getParameter("id"));
				try {
		             ArrayList<Clientes> lista1 = ClientesTestJSON.getJSON();
		             System.out.println("Parametro: " + id);						
					 for (Clientes clientes:lista1){
						if (clientes.getCedula_cliente()==id) {
							request.setAttribute("clienteSeleccionado", clientes);
							request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);	
						}
					}
				 } catch (Exception e) {
					System.out.println("Error al editar " + id);
			       	e.printStackTrace();
			       		
				 }
	    	 }else if(accion.equals("Eliminar")) {
		        	Long id= Long.parseLong(request.getParameter("id"));			
				int respuesta=0;
				try {
				   respuesta = ClientesTestJSON.deleteJSON(id);
				   PrintWriter write = response.getWriter();
				   if (respuesta==200) {
					   request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
				   } else {
					   write.println("Error: " +  respuesta);
				   }
				      write.close();
				 } catch (Exception e) {
					e.printStackTrace();
				 }	
	    	 }
	    	 request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
	    	 break;
	     case "Proveedores":
	    	 if (accion.equals("Listar")) {
	    		 try {
	    			 ArrayList<Proveedores> lista = ProveedoresTestJSON.getJSON();
	    			 request.setAttribute("lista", lista);
		     	 } catch (Exception e) {
		     		e.printStackTrace();
		     	 }
	    	 }else if(accion.equals("Agregar")) {
	    		 Proveedores proveedor = new Proveedores();
	    		 proveedor.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
	    		 proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
	    		 proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
	    		 proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
	    		 proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
	    		 	
	    		 int respuesta=0;
	    		 try {
	    			 respuesta = ProveedoresTestJSON.postJSON(proveedor);
	    			 PrintWriter write = response.getWriter();
	    			 if (respuesta==200) {
	    				 request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request,
	                                                                               response);
	    			 } else {
	    				 write.println("Error: " +  respuesta);
	    			 }
				   		 write.close();
	    		 } catch (Exception e) {
	    			 e.printStackTrace();
	    		 }
	    	 }else if(accion.equals("Actualizar")) {
	    		 Proveedores proveedor = new Proveedores();
	      
	    		 proveedor.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
	    		 proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
	    		 proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
	    		 proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
	    		 proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
				  
		          int respuesta=0;
		          try {
		        	  respuesta = ProveedoresTestJSON.putJSON(proveedor,proveedor.getNitproveedor());
		        	  PrintWriter write = response.getWriter();
								
		        	  if (respuesta==200) {
		        		  request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
		        	  } else {
		        		  write.println("Error: " +  respuesta);
		        	  }
						  write.close();
				  } catch (Exception e) {
					   e.printStackTrace();
				  }
	    	 }else if(accion.equals("Cargar")) {
				Long id= Long.parseLong(request.getParameter("id"));
				try {
		             ArrayList<Proveedores> lista1 = ProveedoresTestJSON.getJSON();
		             System.out.println("Parametro: " + id);						
					 for (Proveedores proveedores:lista1){
						if (proveedores.getNitproveedor()==id) {
							request.setAttribute("proveedorSeleccionado", proveedores);
							request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);	
						}
					}
				 } catch (Exception e) {
					System.out.println("Error al editar " + id);
			       	e.printStackTrace();
			       		
				 }
	    	 }else if(accion.equals("Eliminar")) {
		        	Long id= Long.parseLong(request.getParameter("id"));			
				int respuesta=0;
				try {
				   respuesta = ProveedoresTestJSON.deleteJSON(id);
				   PrintWriter write = response.getWriter();
				   if (respuesta==200) {
					   request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
				   } else {
					   write.println("Error: " +  respuesta);
				   }
				      write.close();
				 } catch (Exception e) {
					e.printStackTrace();
				 }	
	    	 }
	    	 request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
	    	 break;
	     case "Productos":
	    	 request.getRequestDispatcher("/Productos.jsp").forward(request, response);
	    	 break;
	     case "Ventas":	
	    	 request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
	    	 break;
	     case "Reportes":
             try {
	    	 int opcion=0;
	    	 if (accion.equals("ReporteUsuarios")) {
					opcion=1;
					try {
						ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
						request.setAttribute("lista", lista); //envio el arraylist 
						request.setAttribute("opcion", opcion); //variable creada
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (accion.equals("ReporteClientes")) {
					opcion=2;
					try {
						ArrayList<Clientes> lista = ClientesTestJSON.getJSON();
						request.setAttribute("lista", lista); //envio el arraylist 
						request.setAttribute("opcion", opcion); //variable creada
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (accion.equals("ReporteVentas")) {
					opcion=3;
					try {
						//ArrayList<Ventas> lista = VentasTestJSON.getJSON();
						//request.setAttribute("listaVentas", lista); //envio el arraylist 
						//request.setAttribute("opcion", opcion); //variable crada
					} catch (Exception e) {
						e.printStackTrace();
				}
				}
	    	 }catch (Exception e) {
					e.printStackTrace();
				}
	    	 request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
				break;
	    	 }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		PrintWriter out = response.getWriter();
		System.out.println("Entro Sevlet");
		String nomb = request.getParameter("nombre");
		Part arch = request.getPart("archivo");
		String process = request.getParameter("Procesar");
		InputStream is = arch.getInputStream();
		Charset charset = Charset.forName("UTF-8");
		
		if (process != null) {
			if(is.available() ==0) {
				is.close();
				System.out.println("Entro error sin archivo");
				request.setAttribute("error", 1);//error no se ha seleccionado el archivo
				request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			}else {
				if(ProductosTestJSON.validarCSV(nomb)) {
					try {
						String registros = "";
						boolean reg_no_cargados = false;
						CsvReader leerproducto = new CsvReader(is, charset);
						leerproducto.readHeaders();
						List<Productos> productos = new ArrayList<Productos>(); // Lista donde se guardara los datos
						while(leerproducto.readRecord()) {
							String codigo = leerproducto.get(0);
							String iva = leerproducto.get(1);
							String nit = leerproducto.get(2);
							String nombre = leerproducto.get(3);
							String compra = leerproducto.get(4);
							String venta = leerproducto.get(5);
							
							//añade lo leido a una lista tipo productios
						productos.add(new Productos(Long.parseLong(codigo), Double.parseDouble(iva), Long.parseLong(nit), nombre, Double.parseDouble(compra), Double.parseDouble(venta)));
						}
						is.close();
						System.out.println("Entro en boton");
						ArrayList<Productos> listafinal = new ArrayList<Productos>();
						try {
							
							ArrayList<Proveedores> lista = ProveedoresTestJSON.getJSON();
							for (Proveedores suplier:lista) {
								for(Productos item:productos) {
									if(suplier.getNitproveedor() == item.getNitproveedor()) {
										System.out.println("Entro for if");
										listafinal.add(item);
									}else {
										registros = String.valueOf(item.getNitproveedor())+",";
										reg_no_cargados =  true;
									}
								}
							}
						} catch (Exception e) {
							System.out.println("Entro catch");
							e.printStackTrace();
							// TODO: handle exception
						}
						String registros2 = "";
						int ban= 3;
						int validacion = 0;
						for(Productos item:listafinal) {
							ArrayList<Productos> listaproductos = ProductosTestJSON.getJSON();
							for(Productos item2:listaproductos) {
								if(item.getCodigo_producto()!=item2.getCodigo_producto()) {
									validacion = ProductosTestJSON.postJSON(item);
									if(validacion==200) {
										ban = (ban * 0) + 2;
									}
								}else {
									registros2 = String.valueOf(item.getCodigo_producto())+",";
								}
							}		
						}
						if(validacion == 200) {
							System.out.println("carga existosa");
							request.setAttribute("error", 2); //carga exitosa de csv
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}else {
							System.out.println("los registros no se cargaron");
							request.setAttribute("error", 4); //Registros no se cargaron
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}
					}catch(Exception e){
						System.out.println("Entro datos invalidos");
						request.setAttribute("error", 0);//error datos leidos invalidos
						request.getRequestDispatcher("/Productos.jsp").forward(request, response);
					}finally {
						is.close();
					}
				}else {
					is.close();
					System.out.println("Entro error formato");
					request.setAttribute("error", 3);//error datos leidos invalidos
					request.getRequestDispatcher("Productos.jsp").forward(request, response);
					//request.getRequestDispatcher("/ValidacionProductos?validar=3&error=3").forward(request, response);
				}
			}		
		
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
