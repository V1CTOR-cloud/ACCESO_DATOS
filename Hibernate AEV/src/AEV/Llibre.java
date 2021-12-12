package AEV;

public class Llibre {

	public Llibre() {
		super();
	}

	public Llibre( String titol, String autor, String editorial, String any_naixement, String any_publicacio,
			String paginas) {
		super();
		this.titol = titol;
		this.autor = autor;
		this.editorial = editorial;
		this.any_naixement = any_naixement;
		this.any_publicacio = any_publicacio;
		this.paginas = paginas;
	}

	private int id;
	private String titol, autor, editorial, any_naixement, any_publicacio, paginas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAny_naixement() {
		return any_naixement;
	}

	public void setAny_naixement(String any_naixement) {
		this.any_naixement = any_naixement;
	}

	public String getAny_publicacio() {
		return any_publicacio;
	}

	public void setAny_publicacio(String any_publicacio) {
		this.any_publicacio = any_publicacio;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
}
