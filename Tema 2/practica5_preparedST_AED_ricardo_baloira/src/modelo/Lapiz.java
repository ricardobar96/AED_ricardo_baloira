package modelo;

public class Lapiz {
public Lapiz() {};
	
	int idLapiz;
	int numero;
	String marca;
	
	public Lapiz(int idLapiz, int numero, String marca) {
		super();
		this.idLapiz = idLapiz;
		this.numero = numero;
		this.marca = marca;
	}
	public int getIdLapiz() {
		return idLapiz;
	}
	public void setIdLapiz(int idLapiz) {
		this.idLapiz = idLapiz;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
}
