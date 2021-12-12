package AEV;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

	static List<Llibre> lista = new ArrayList<Llibre>();
	static String bbdd = "jdbc:mysql://localhost:3306/jdbc";
	static String usuario = "root";
	static String contrasenya = "";
	static Scanner sc = new Scanner(System.in);

	// Metodo main aon es ejecuta el menu y podem accedir a les funcions

	public static void main(String[] args) {
		boolean salir = false;
		int opc = 0;

		do {
			System.out.println("==============================");
			System.out.println("Benvingut a Florida's  Library");
			System.out.println("==============================");
			System.out.println("\n______________________________");
			System.out.println("1. Mostrar tots els titols");
			System.out.println("2. Consultar llibre ");
			System.out.println("3. Afegir Llibre");
			System.out.println("4. Modificar Llibre");
			System.out.println("5. Borrar Llibre");
			System.out.println("6. Eixir");
			System.out.println("______________________________");
			opc = sc.nextInt();

			switch (opc) {
			case 1:
				MostrarTodos();
				break;

			case 2:
				MostrarId();
				break;
			case 3:
				Crear();
				break;
			case 4:
				Actualizar();
				break;
			case 5:
				Borrar();
				break;
			case 6:
				System.out.println("\n\n\n______________________");
				System.out.println("Gracies per visitarnos");
				System.out.println("______________________");
				salir = true;
				break;
			}

		} while (!salir);

	}

	/*
	 * Es mostra tots el llibres cargades desde la base de dades
	 */

	public static void MostrarTodos() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Hasta aci la configuracio i comença la session

		List listaLibros = new ArrayList(); // bucle que mostra tots els llibres
		listaLibros = session.createQuery("FROM Llibre").list();
		System.out.println("__________________");
		for (Object llibre : listaLibros) {
			System.out.println("El seu ID: " + ((Llibre) llibre).getId() + " TITOL: " + ((Llibre) llibre).getTitol());
		}
		System.out.println("__________________");

		// tanquem la sessio

		session.getTransaction().commit();
		session.clear();
		session.close();
	}

	private static void MostrarId() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Hasta aci la configuracio i comença la session
		// Aci introduim les dades per a buscar en la BBDD

		System.out.print("\nID:\t");
		int id = Integer.parseInt(sc.nextLine());

		// tanquem la sessio y accedim al llibre de la BBDD
		try {

			Llibre l = (Llibre) session.get(Llibre.class, id);
			System.out.println(l.toString());
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			System.out.println("\nNO existeix registres coincidents al servidor.");
		}
	}

	private static void Borrar() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Hasta aci la configuracio i comença la session
		// Aci introduim les dades per a borrar en la BBDD

		System.out.print("\nID:\t");
		int id = Integer.parseInt(sc.nextLine());
		Llibre l = new Llibre();
		// creamos el llibre
		// tanquem la sessio
		try {
			l.setId(id);
			session.delete(l);
			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			System.out.println("\nNO existeix registres coincidents al servidor.");
		}
	}

	private static void Crear() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Hasta aci la configuracio i comença la session
		// Introduim les dades per a crear el objecte

		System.out.print("\nTITOL: ");
		String titol = sc.nextLine();
		System.out.print("AUTOR: ");
		String autor = sc.nextLine();
		System.out.print("ANY NAIXEMENT: ");
		int anyoNac = Integer.parseInt(sc.nextLine());
		System.out.print("ANY PUBLICACIO: ");
		int anyoPub = Integer.parseInt(sc.nextLine());
		System.out.print("EDITORIAL: ");
		String editorial = sc.nextLine();
		System.out.print("PAGINAS: ");
		int paginas = Integer.parseInt(sc.nextLine());
		Llibre l = new Llibre();
		Serializable id = session.save(l); // Guardar en la BBDD
		session.getTransaction().commit(); // tancar sessio
		session.clear();
		session.close();
	}

	private static void Actualizar() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Hasta aci la configuracio i comença la session
		// Introduim les dades per a comparar en la BBDD

		System.out.print("\nACTUALIZAR ID: ");
		int id = Integer.parseInt(sc.nextLine());

		System.out.print("\nTITOL: ");
		String titol = sc.nextLine();
		System.out.print("AUTOR: ");
		String autor = sc.nextLine();
		System.out.print("ANY NAIXEMENT: ");
		String anyoNac = sc.nextLine();
		System.out.print("ANY PUBLICACIO: ");
		String anyoPub = sc.nextLine();
		System.out.print("EDITORIAL: ");
		String editorial = sc.nextLine();
		System.out.print("PAGINAS: ");
		String paginas = sc.nextLine();

		// Tanquem la sessio y carguem el llibre a la BBDD

		try {
			Llibre l = (Llibre) session.load(Llibre.class, id);
			l.setTitol(titol);
			l.setAutor(autor);
			l.setAny_naixement(anyoNac);
			l.setAny_publicacio(anyoPub);
			l.setEditorial(editorial);
			l.setPaginas(paginas);
			session.update(l); // Actualizem el llibre

			session.getTransaction().commit();
			session.clear();
		} catch (Exception e) {
			System.out.println("\nNO existeix registres coincidents al servidor.");
		}
	}

}
