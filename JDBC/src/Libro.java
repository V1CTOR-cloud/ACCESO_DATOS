
public class Libro {
	private String titol, autor, editorial, anyo_nacimiento, anyo_publicacio, paginas;

	public Libro() {

	}

	public Libro(String titol, String autor, String anyo_nacimiento, String anyo_publicacio, String editorial,
			String paginas) {
		this.titol = titol;
		this.autor = autor;
		this.anyo_nacimiento = anyo_nacimiento;
		this.anyo_publicacio = anyo_publicacio;
		this.editorial = editorial;
		this.paginas = paginas;
	}

	public String getTitol() {
		return titol;
	}

	public String getAutor() {
		return autor;
	}

	public String getAnyo_nacimiento() {
		return anyo_nacimiento;
	}

	public String getAnyo_publicacio() {
		return anyo_publicacio;
	}

	public String getEditorial() {
		return editorial;
	}

	public String getPaginas() {
		return paginas;
	}
}
