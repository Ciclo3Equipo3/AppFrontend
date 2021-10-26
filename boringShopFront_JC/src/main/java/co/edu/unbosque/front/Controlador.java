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


@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	long subtotal=0, totalapagar=0;
	long codProducto=0, precio=0, valor_iva=0, iva=0, subtotaliva=0, acusubtotal=0;
	long numfac=0, item=0;
	int cantidad=0;
	String descripcion, cedulaCliente, cedula_usuario_activo;
	List<Detalle_ventas> listaVentas= new ArrayList<>();
	Usuarios usuarios= new Usuarios();
	Detalle_ventas detalle_venta = new Detalle_ventas();

	public Controlador() {
		super();
		
	}
	
	 public void buscarCliente(Long id,HttpServletRequest request, HttpServletResponse response)
	  			throws ServletException, IOException {
	  		try {
	  			ArrayList<Clientes> listac= ClientesTestJSON.getJSON();
	  			for(Clientes clientes:listac) {
	  				if (clientes.getCedula_cliente()==(id)) {
	  					request.setAttribute("clienteSeleccionado", clientes); 	}
	  			}	
	  		} catch (Exception e) {
	  				e.printStackTrace();
	  				}
	  	}
	    
	    public void buscarProducto(Long cod,HttpServletRequest request, HttpServletResponse response)
	  			throws ServletException, IOException {
	  		try {
	  			ArrayList<Productos> listap= ProductosTestJSON.getJSON();
	  			for(Productos productos:listap) {
	  				if (productos.getCodigo_producto()==(cod)) {					
	  					request.setAttribute("productoSeleccionado", productos); //envio a ventas
	  				}
	  			}
	  		} catch (Exception e) {
	  				e.printStackTrace();
	  				}
	  	}
	    
	    public void buscarFactura(String numFact,HttpServletRequest request, HttpServletResponse response)
	  			throws ServletException, IOException { 
	  		if(numFact==null) {
	  			numfac=Integer.parseInt(numFact)+1; //variable declarada arriba con valor 0
	  		}
	  		else {
	  			numfac=Integer.parseInt(numFact)+1; //cuando ya tiene valor la variable
	  		}
	  		request.setAttribute("numerofactura", numfac);
	  		
	  	}
	    
	    public void grabarDetalle(Long numFact,HttpServletRequest request, HttpServletResponse response)
	  			throws ServletException, IOException {
	  		for(int i=0; i<listaVentas.size();i++) {
	  			detalle_venta = new Detalle_ventas();
	  			detalle_venta.setCodigo_detalle_venta((Long.parseLong(String.valueOf(i+1))));
	  			detalle_venta.setCodigo_venta(numFact);
	  			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
	  			detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
	  			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
	  			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
	  			detalle_venta.setValoriva(listaVentas.get(i).getValoriva());
	  			
	  			int respuesta =0;
	  			try {
	  				respuesta= Detalle_ventasTestJSON.postJSON(detalle_venta);
	  				PrintWriter write= response.getWriter();
	  				if (respuesta==200) {
	  					System.out.println("Registros Grabados detalle ventas");
	  				}else {
	  					write.println("Error Detall venta" +respuesta);
	  				}
	  				write.close();
	  			} catch (Exception e) {
	  				e.printStackTrace();
	  			}		
	  			
	  		}
	  	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		//cedula_usuario_activo = request.getParameter("UsuarioActivo");
		
		//usuarios.setCedula_usuario(Long.parseLong("123"));
		//System.out.println(cedula_usuario_activo);
		//request.setAttribute("usuarioSeleccionado", usuarios); //se envia a ventas

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
					System.out.println("Parametro: " + id);
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
				request.setAttribute("usuarioSeleccionado", usuarios);
				request.setAttribute("numerofactura",numfac);

			/*if(accion.equals("BuscarCliente")) {
				String id=request.getParameter("cedulacliente");
				this.buscarCliente(Long.parseLong(id), request, response);
			}else if(accion.equals("BuscarProducto")) {
				String id=request.getParameter("cedulacliente");
				this.buscarCliente(Long.parseLong(id), request, response);
								
				String cod=request.getParameter("codigoproducto");
				this.buscarProducto(Long.parseLong(cod), request, response);
				
			}else if(accion.equals("AgregarProducto")) {
				String id=request.getParameter("cedulacliente");
				this.buscarCliente(Long.parseLong(id), request, response);
				
				detalle_venta= new Detalle_ventas(); 
				item++;
				acusubtotal=0;
				subtotaliva=0;
				totalapagar=0;
				codProducto=Long.parseLong(request.getParameter("codigoproducto")); 
				descripcion=request.getParameter("nombreproducto");
				precio=Long.parseLong(request.getParameter("precioproducto"));
				cantidad=Integer.parseInt(request.getParameter("cantidadproducto"));
				iva=Long.parseLong(request.getParameter("ivaproducto"));
				
				subtotal=(precio*cantidad);
				valor_iva=subtotal*iva/100;
				
				detalle_venta.setCodigo_detalle_venta(item);
				detalle_venta.setCodigo_producto(codProducto);
				detalle_venta.setDescripcion_producto(descripcion);
				detalle_venta.setPrecio_producto(precio);
				detalle_venta.setCantidad_producto(cantidad);
				detalle_venta.setCodigo_venta(numfac);
				detalle_venta.setValoriva(valor_iva);
				detalle_venta.setValor_venta(subtotal);
				listaVentas.add(detalle_venta);
				
				for(int i=0; i<listaVentas.size();i++) {
					acusubtotal += listaVentas.get(i).getValor_venta();
					subtotaliva += listaVentas.get(i).getValoriva();
				}
				totalapagar = acusubtotal + subtotaliva;
				detalle_venta.setValor_total(totalapagar);
			
				request.setAttribute("listaventas", listaVentas);
				request.setAttribute("totalsubtotal", acusubtotal);
				request.setAttribute("totaliva", subtotaliva);
				request.setAttribute("totalapagar", totalapagar);
				
			}else if(accion.equals("GenerarVenta")) {
				cedulaCliente= request.getParameter("cedulacliente");
				String numFact=request.getParameter("numerofactura");
				Ventas ventas =new Ventas();
				ventas.setCodigo_venta(Long.parseLong(numFact));
				ventas.setCedula_cliente(Long.parseLong(cedulaCliente));
				ventas.setCedula_usuario(usuarios.getCedula_usuario());
				ventas.setIvaventa(subtotaliva);
				ventas.setValor_vental(acusubtotal);
				ventas.setTotal_venta(totalapagar);
				
				int respuesta=0;
				try {
					respuesta=VentasTestJSON.postJSON(ventas);
					PrintWriter write=response.getWriter();
					if(respuesta==200) {
						System.out.println("Grabacion Exitosa " + respuesta);
						this.grabarDetalle(ventas.getCodigo_venta(), request, response);
					}else {
						write.println("error ventas");
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				//*********** muestro factura por primera vez ******
				String factura= "0"; //	request.setAttribute("numerofactura");
				//String factura= request.gesetAttribute("numerofactura");
				this.buscarFactura(factura, request, response);
			}
			*/
	    	 
	    	 request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
	    	 break;
	     case "Reportes":
             try {
	    	 int opcion=0;
	    	 if (accion.equals("ReporteUsuarios")) {
					opcion=1;
					try {
						ArrayList<Usuarios> lista = UsuarioJSON.getJSON();
						request.setAttribute("listaUsuarios", lista); 
						request.setAttribute("opcion", opcion);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (accion.equals("ReporteClientes")) {
					opcion=2;
					try {
						ArrayList<Clientes> lista = ClientesTestJSON.getJSON();
						request.setAttribute("lista", lista);  
						request.setAttribute("opcion", opcion); 
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if (accion.equals("ReporteVentas")) {
					opcion=3;
					try {
						ArrayList<Ventas> lista = VentasTestJSON.getJSON();
						request.setAttribute("listaVentas", lista);  
						request.setAttribute("opcion", opcion); 
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
		
		try {
		PrintWriter out = response.getWriter();
		System.out.println("Entro Sevlet");
		String nomb = request.getParameter("nombre");
		System.out.println(nomb);
		Part arch = request.getPart("archivo");
		System.out.println(arch);
		String process = request.getParameter("Procesar");
		System.out.println(process);
		InputStream is = arch.getInputStream();
		Charset charset = Charset.forName("UTF-8");
		
		if (process != null) {
			if(is.available() ==0) {
				is.close();
				System.out.println("Entro error sin archivo");
				request.setAttribute("error", 1);
				request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			}else {
				if(ProductosTestJSON.validarCSV(nomb)) {
					try {
						String registros = "";
						boolean reg_no_cargados = false;
						CsvReader leerproducto = new CsvReader(is, charset);
						leerproducto.readHeaders();
						List<Productos> productos = new ArrayList<Productos>();
						while(leerproducto.readRecord()) {
							String codigo = leerproducto.get(0);
							String iva = leerproducto.get(1);
							String nombre = leerproducto.get(2);
							String compra = leerproducto.get(3);
							String venta = leerproducto.get(4);
							String nit = leerproducto.get(5);
							
						productos.add(new Productos(Long.parseLong(codigo), Double.parseDouble(iva), nombre, Double.parseDouble(compra), Double.parseDouble(venta), Long.parseLong(nit)));
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
							request.setAttribute("error", 2); 
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}else {
							System.out.println("los registros no se cargaron");
							request.setAttribute("error", 4); 
							request.getRequestDispatcher("/Productos.jsp").forward(request, response);
						}
					}catch(Exception e){
						System.out.println("Entro datos invalidos");
						request.setAttribute("error", 0);
						request.getRequestDispatcher("/Productos.jsp").forward(request, response);
					}finally {
						is.close();
					}
				}else {
					is.close();
					System.out.println("Entro error formato");
					request.setAttribute("error", 3);
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
