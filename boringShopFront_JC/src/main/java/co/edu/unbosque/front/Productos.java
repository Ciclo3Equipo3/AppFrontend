package co.edu.unbosque.front;

public class Productos {
	private Long codigo_producto;
	private Double ivacompra;
	private String nombre_producto;
	private Double precio_compra;
	private Double precio_venta;
	private Long nitproveedor;
	
	public Productos() {
		super();
	}

	public Productos(long codigo_producto, double ivacompra, String nombre_producto, 
			double precio_compra, double precio_venta, long nitproveedor) {
		super();
		this.codigo_producto = codigo_producto;
		this.ivacompra = ivacompra;
		this.nitproveedor = nitproveedor;
		this.nombre_producto = nombre_producto;
		this.precio_compra = precio_compra;
		this.precio_venta = precio_venta;
	}
	
	public long getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(long codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public double getIvacompra() {
		return ivacompra;
	}

	public void setIvacompra(double ivacompra) {
		this.ivacompra = ivacompra;
	}

	public long getNitproveedor() {
		return nitproveedor;
	}

	public void setNitproveedor(long nitproveedor) {
		this.nitproveedor = nitproveedor;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public double getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}
}
