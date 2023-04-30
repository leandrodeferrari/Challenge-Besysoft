package com.besysoft.service.impl;

import com.besysoft.domain.Producto;
import com.besysoft.domain.Vendedor;
import com.besysoft.domain.Venta;
import com.besysoft.persistence.util.JpaUtil;
import com.besysoft.service.IMenuService;
import com.besysoft.service.IProductoService;
import com.besysoft.service.IVendedorService;
import com.besysoft.service.IVentaService;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuServiceImpl implements IMenuService {

    private final Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private EntityManager em;
    private final IProductoService productoService;
    private final IVendedorService vendedorService;
    private final IVentaService ventaService;

    public MenuServiceImpl() {
        this.em = JpaUtil.getEntityManager();
        this.productoService = new ProductoServiceImpl(this.em);
        this.vendedorService = new VendedorServiceImpl(this.em);
        this.ventaService = new VentaServiceImpl(this.em);
    }

    @Override
    public void iniciar() {

        int opcion = 0;

        System.out.println("Bienvenido a la tienda de productos ¡BesyProduct!");

        do {
            mostrarOpcionesPrincipales();

            try {
                opcion = this.sc.nextInt();
            } catch (InputMismatchException ex){
                this.sc.nextLine();
                opcion = 0;
            }

            System.out.println("--------------------");

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> registrarProducto();
                case 3 -> buscarProductoPorCategoria();
                case 4 -> buscarProductoPorNombre();
                case 5 -> buscarProductoPorRangoDePrecios();
                case 6 -> listarVendedores();
                case 7 -> registrarVendedor();
                case 8 -> listarVentas();
                case 9 -> registrarVenta();
                case 10 -> calcularComisionDelVendedor();
                case 11 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida, vuelva a intentarlo");
            }

            System.out.println("--------------------");
        } while (opcion != 11);

        this.sc.close();
        this.em.close();
    }

    @Override
    public void mostrarOpcionesPrincipales(){
        System.out.println("Elija una opción:");
        mostrarOpcionesDeProducto();
        mostrarOpcionesDeVendedor();
        mostrarOpcionesDeVenta();
        System.out.println("11. Salir");
    }

    @Override
    public void mostrarOpcionesDeProducto(){
        System.out.println("Productos:");
        System.out.println("1. Listar");
        System.out.println("2. Registrar");
        mostrarOpcionesDeBuscadorDeProductos();

    }

    @Override
    public void mostrarOpcionesDeBuscadorDeProductos(){
        System.out.println("3. Buscar por categoría");
        System.out.println("4. Buscar por nombre");
        System.out.println("5. Buscar por rango de precios");
    }

    @Override
    public void mostrarOpcionesDeVendedor(){
        System.out.println("Vendedores:");
        System.out.println("6. Listar");
        System.out.println("7. Registrar");
    }

    @Override
    public void mostrarOpcionesDeVenta(){
        System.out.println("Ventas:");
        System.out.println("8. Listar");
        System.out.println("9. Registrar");
        System.out.println("10. Comisiones");
    }

    private void listarProductos(){
        List<Producto> productos = this.productoService.obtenerTodos();
        productos.forEach(System.out::println);
    }

    private void listarVendedores(){
        List<Vendedor> vendedores = this.vendedorService.obtenerTodos();
        vendedores.forEach(System.out::println);
    }

    private void listarVentas(){
        List<Venta> ventas = this.ventaService.obtenerTodos();
        ventas.forEach(System.out::println);
    }

    private void buscarProductoPorCategoria(){
        System.out.println("Ingrese la categoría del producto:");
        String categoria = this.sc.next();

        List<Producto> productos = this.productoService.buscarPorCategoria(categoria);

        if(!productos.isEmpty()){
            productos.forEach(System.out::println);
        } else {
            System.out.println("No hay productos con esa categoría");
        }
    }

    private void buscarProductoPorNombre(){
        System.out.println("Ingrese el nombre del producto:");
        String nombre = this.sc.next();

        List<Producto> productos = this.productoService.buscarPorNombre(nombre);

        if(!productos.isEmpty()){
            productos.forEach(System.out::println);
        } else {
            System.out.println("No hay productos con ese nombre");
        }
    }

    private void buscarProductoPorRangoDePrecios(){
        boolean esNumeroMin = false;
        boolean esNumeroMax = false;
        BigDecimal desde = BigDecimal.ZERO;
        BigDecimal hasta = BigDecimal.ZERO;

        while (!esNumeroMin) {
            System.out.println("Ingrese el precio mínimo del producto:");
            String min = this.sc.next();

            try {
                desde = new BigDecimal(min);
                esNumeroMin = true;
            } catch (NumberFormatException ex){
                System.out.println("Debe ingresar un número válido");
                this.sc.nextLine();
            }
        }

        while (!esNumeroMax) {
            System.out.println("Ingrese el precio máximo del producto:");
            String max = this.sc.next();

            try {
                hasta = new BigDecimal(max);
                esNumeroMax = true;
            } catch (NumberFormatException ex){
                System.out.println("Debe ingresar un número válido");
                this.sc.nextLine();
            }
        }

        List<Producto> productos = this.productoService.buscarPorRangoDePrecios(desde, hasta);

        if(!productos.isEmpty()){
            productos.forEach(System.out::println);
        } else {
            System.out.println("No hay productos con ese rango de precios");
        }
    }

    private void registrarProducto(){
        Producto producto = new Producto();
        boolean esNumero = false;

        System.out.println("Ingrese el nombre del producto:");
        String nombre = this.sc.next();
        producto.setNombre(nombre);

        while (!esNumero) {
            System.out.println("Ingrese el precio del producto:");
            String precio = this.sc.next();

            try {
                producto.setPrecio(new BigDecimal(precio));
                esNumero = true;
            } catch (NumberFormatException ex){
                System.out.println("Debe ingresar un número válido");
                this.sc.nextLine();
            }
        }

        System.out.println("Ingrese la categoría del producto:");
        String categoria = this.sc.next();
        producto.setCategoria(categoria);

        this.productoService.crear(producto);
        System.out.println("Se ha registrado con éxito el producto");
    }

    private void registrarVendedor(){
        Vendedor vendedor = new Vendedor();

        System.out.println("Ingrese el nombre del vendedor:");
        String nombre = this.sc.next();
        vendedor.setNombre(nombre);

        boolean esNumero = false;

        while (!esNumero) {
            System.out.println("Ingrese el sueldo del vendedor:");
            String sueldo = this.sc.next();

            try {
                vendedor.setSueldo(new BigDecimal(sueldo));
                esNumero = true;
            } catch (NumberFormatException ex){
                System.out.println("Debe ingresar un número válido");
                this.sc.nextLine();
            }
        }

        this.vendedorService.crear(vendedor);
        System.out.println("Se ha registrado con éxito el vendedor");
    }

    private void registrarVenta(){
        Venta venta = new Venta();

        boolean esUnValorValido = false;
        int cantidad = 0;

        while(!esUnValorValido){
            try {
                System.out.println("Ingrese la cantidad del producto de la venta:");
                cantidad = this.sc.nextInt();

                esUnValorValido = true;
                venta.setCantidad(cantidad);
            } catch (InputMismatchException ex){
                System.out.println("Debe ingresar un número válido");
                this.sc.nextLine();
            }
        }

        System.out.println("Ingrese el código del producto:");
        String codigoProducto = this.sc.next();

        Optional<Producto> optionalProducto = this.productoService.buscarPorCodigo(codigoProducto);

        if(optionalProducto.isPresent()){
            Producto producto = optionalProducto.get();
            venta.setProducto(producto);

            System.out.println("Ingrese el código del vendedor:");
            String codigoVendedor = this.sc.next();

            Optional<Vendedor> optionalVendedor = this.vendedorService.buscarPorCodigo(codigoVendedor);

            if(optionalVendedor.isPresent()){
                Vendedor vendedor = optionalVendedor.get();
                venta.setVendedor(vendedor);

                this.ventaService.crear(venta);
                System.out.println("Se ha registrado con éxito la venta");
            } else {
                System.out.println("Lo siento, código de vendedor inválido");
            }
        } else {
            System.out.println("Lo siento, código de producto inválido");
        }

    }

    private void calcularComisionDelVendedor(){
        System.out.println("Ingrese el código del vendedor:");
        String codigoVendedor = this.sc.next();

        Optional<Vendedor> optionalVendedor = this.vendedorService.buscarPorCodigo(codigoVendedor);

        optionalVendedor.ifPresent(this.ventaService::calcularComisiones);
    }

}
