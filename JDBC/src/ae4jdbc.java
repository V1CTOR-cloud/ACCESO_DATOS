import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ae4jdbc {

	static List<Libro> lista = new ArrayList<Libro>();
	static String bbdd = "jdbc:mysql://localhost:3306/jdbc";
	static String usuario = "root";
	static String contrasenya = "";

	public static void main(String[] args) {
		CsvToMysql();
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		int opcion = 0;
		do {
			System.out.println("_________________ LLibreria _________________");
			System.out.println("1. Llibres dels autors nascuts abans de 1950.");
			System.out.println("2. Editorials en el segle XXI.");
			System.out.println("3. Consulta personalizable.");
			System.out.println("4. Salir");
			System.out.println("_________________ LLibreria _________________");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				Select1950();
				break;

			case 2:
				SelectEditorial();
				break;

			case 3:

				break;

			case 4:
				salir = true;
				System.out.println("_________________ LLibreria _________________");
				System.out.println("\t\tGracies per vistiarnos");
				System.out.println("_________________ LLibreria _________________");
				break;
			}

		} while (!salir);
	}

	public static void CsvToMysql() {
		lista.clear();
		BorrarBbdd();
		Connection connection = null;
		String excel = "AE04_T1_4_JDBC_Dades.csv";
		try {
			connection = DriverManager.getConnection(bbdd, usuario, contrasenya);
			connection.setAutoCommit(false);

			String consulta = "INSERT INTO libros ( titol, autor, any_naixement, any_publicacio, editorial, paginas) VALUES (?, ?, ?, ?,?,?)";

			PreparedStatement statement = connection.prepareStatement(consulta);

			BufferedReader bf = new BufferedReader(new FileReader(excel));
			String txt = null;
			bf.readLine();

			while ((txt = bf.readLine()) != null) {
				String[] datos = txt.split(";");
				String titol = datos[0];
				String autor = datos[1];
				String anyo_nacimiento = datos[2];
				String anyo_publicacio = datos[3];
				String editorial = datos[4];
				String paginas = datos[5];

				statement.setString(1, titol);
				statement.setString(2, autor);
				statement.setString(3, anyo_nacimiento);
				statement.setString(4, anyo_publicacio);
				statement.setString(5, editorial);
				statement.setString(6, paginas);
				int resultadoInsertar = statement.executeUpdate();
				Libro l = new Libro(titol, autor, anyo_nacimiento, anyo_publicacio, editorial, paginas);

				lista.add(l);

			}
			bf.close();

			connection.commit();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void BorrarBbdd() {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(bbdd, usuario, contrasenya);
			connection.setAutoCommit(false);

			String consulta = "DELETE from libros;";

			PreparedStatement statement = connection.prepareStatement(consulta);
			int resultadoBorrar = statement.executeUpdate();
			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void Select1950() {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(bbdd, usuario, contrasenya);
			connection.setAutoCommit(false);

			String consulta = "SELECT * from libros WHERE any_publicacio < 1950;";

			Statement statement = connection.createStatement();

			ResultSet resultadoSelect = statement.executeQuery(consulta);

			while (resultadoSelect.next()) {
				System.out.println(resultadoSelect.getString(2));
			}

			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void SelectEditorial() {
		Connection connection = null;
		try {

			connection = DriverManager.getConnection(bbdd, usuario, contrasenya);
			connection.setAutoCommit(false);

			String consulta = "SELECT * from libros WHERE any_publicacio > 2000;";

			Statement statement = connection.createStatement();

			ResultSet resultadoSelect = statement.executeQuery(consulta);

			while (resultadoSelect.next()) {
				System.out.println(resultadoSelect.getString(2));
			}

			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void ConsultaPersonalizable() {
		Connection connection = null;
		Scanner sc = new Scanner(System.in);
		try {

			connection = DriverManager.getConnection(bbdd, usuario, contrasenya);
			connection.setAutoCommit(false);

			System.out.println("_________________ Consulta _________________");
			System.out.println("\t\tIntrodueix la consulta:\t");
			System.out.println("_________________ Consulta _________________");

			String consulta = sc.nextLine();

			if (consulta.contains("SELECT")) {
				Statement statement = connection.createStatement();
				ResultSet resultadoSelect = statement.executeQuery(consulta);
				while (resultadoSelect.next()) {
					System.out.println(resultadoSelect.getString(2));
				}
			} else {
				if (consulta.contains("INSERT")) {
					PreparedStatement statement = connection.prepareStatement(consulta);

					System.out.println("_________________ Introduccio dades _________________");
					System.out.println("\t\t Titol:\t");
					String titol = sc.nextLine();
					System.out.println("\t\t Autor:\t");
					String autor = sc.nextLine();
					System.out.println("\t\t Anyo Naixement:\t");
					String anyo_nacimiento = sc.nextLine();
					System.out.println("\t\t Anyo Publicacio:\t");
					String anyo_publicacio = sc.nextLine();
					System.out.println("\t\t Anyo Publicacio:\t");
					String editorial = sc.nextLine();
					System.out.println("\t\t Pagines:\t");
					String paginas = sc.nextLine();
					System.out.println("_________________ Introduccio dades _________________");
					statement.setString(1, titol);
					statement.setString(2, autor);
					statement.setString(3, anyo_nacimiento);
					statement.setString(4, anyo_publicacio);
					statement.setString(5, editorial);
					statement.setString(6, paginas);
					int resultadoInsertar = statement.executeUpdate();
				} else {
					if (consulta.contains("UPDATE")) {
						PreparedStatement psActualizar = connection.prepareStatement(consulta);

						System.out.println("_________________ Introduccio dades _________________");
						System.out.println("\t\t Titol:\t");
						String titol = sc.nextLine();
						System.out.println("\t\t Autor:\t");
						String autor = sc.nextLine();
						System.out.println("\t\t Anyo Naixement:\t");
						String anyo_nacimiento = sc.nextLine();
						System.out.println("\t\t Anyo Publicacio:\t");
						String anyo_publicacio = sc.nextLine();
						System.out.println("\t\t Anyo Publicacio:\t");
						String editorial = sc.nextLine();
						System.out.println("\t\t Pagines:\t");
						String paginas = sc.nextLine();
						System.out.println("_________________ Introduccio dades _________________");
						int resultadoActualizar = psActualizar.executeUpdate();
					}else {
						PreparedStatement psBorrar = connection.prepareStatement(consulta);						
						int resultadoBorrar = psBorrar.executeUpdate();
					}
				}
			}

			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
