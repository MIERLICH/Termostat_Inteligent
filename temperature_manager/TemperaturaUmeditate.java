import java.util.LinkedList;

class TemperaturaUmeditate {
	LinkedList<Double> temp_list;
	LinkedList<Double> umeditate;
	double temp_minima;
	double umed_minima;
	int nr_temperaturi;
	int nr_umeditati;

	TemperaturaUmeditate() {
		this.temp_list = new LinkedList<Double>();
		this.umeditate = new LinkedList<Double>();
		this.temp_minima = Double.MAX_VALUE;
		this.umed_minima = Double.MIN_VALUE;
		this.nr_temperaturi = 0;
		this.nr_umeditati = 0;
	}

	public LinkedList<Double> getTemp_list() {
		return temp_list;
	}
	public void setTemp_list(LinkedList<Double> temp_list) {
		this.temp_list = temp_list;
	}
	public LinkedList<Double> getUmeditate() {
		return umeditate;
	}
	public void setUmeditate(LinkedList<Double> umeditate) {
		this.umeditate = umeditate;
	}
	public double getUmed_minima() {
		return umed_minima;
	}
	public void setUmed_minima(double umed_minima) {
		this.umed_minima = umed_minima;
	}
	public int getNr_temperaturi() {
		return nr_temperaturi;
	}
	public void setNr_temperaturi(int nr_temperaturi) {
		this.nr_temperaturi = nr_temperaturi;
	}
	public int getNr_umeditati() {
		return nr_umeditati;
	}
	public void setNr_umeditati(int nr_umeditati) {
		this.nr_umeditati = nr_umeditati;
	}
	public double getTemp_minima() {
		return temp_minima;
	}
	public void setTemp_minima(double temp_minima) {
		this.temp_minima = temp_minima;
	}
}