import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;

class Locuinta {
	private double temperatura;
	private double umeditatea;
	private long timestamp;
	private int nr_camere;
	private LinkedList<Camera> room_list;

	Locuinta() {}
	Locuinta(int nr_camere, double temp, double umeditate, long times) {
		this.temperatura = temp;
		this.umeditatea = umeditate;
		this.timestamp = times;
		this.room_list = new LinkedList<Camera>();
		this.nr_camere = nr_camere;
	}
	
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getNr_camere() {
		return nr_camere;
	}
	public void setNr_camere(int nr_camere) {
		this.nr_camere = nr_camere;
	}
	public LinkedList<Camera> getRoom_list() {
		return room_list;
	}
	public void setRoom_list(LinkedList<Camera> room_list) {
		this.room_list = room_list;
	}
	public double getUmeditatea() {
		return umeditatea;
	}
	public void setUmeditatea(double umeditatea) {
		this.umeditatea = umeditatea;
	}

	/**
	 * 
	 * @param l Casa care contine toata informatia
	 * @param senzor Denumirea senzorului
	 * @param timp Timpul la care sa inregistrat temperatura
	 * @param temp Temperatura inregistrata
	 */
	public void ObservaT(Locuinta l, String senzor, long timp, double temp) {
		if(timp > l.timestamp || timp < l.getTimestamp() - 86400) return;
		for(int i = 0; i < l.nr_camere; i++) {
			if(l.room_list.get(i).name_senzor.compareTo(senzor) == 0) {
				if(l.room_list.get(i).buchets.isEmpty()) {
					for(int j = 0; j < 24; j++) {
						TemperaturaUmeditate buchet = new TemperaturaUmeditate();
						l.room_list.get(i).buchets.add(buchet);
					}
				}	
				long a = l.timestamp - 86400;
				a = timp - a;
				a = a / 3600;
				int indice = (int)a;
					
				if(l.room_list.get(i).buchets.get(indice).temp_list.contains(temp) == true ) {return;}

				l.room_list.get(i).buchets.get(indice).temp_list.add(temp);
				l.room_list.get(i).buchets.get(indice).nr_temperaturi++;
				if(l.room_list.get(i).buchets.get(indice).getTemp_minima() > temp) {
					l.room_list.get(i).buchets.get(indice).setTemp_minima(temp);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param l Casa cu lista de camere
	 * @param senzor Denumirea senzorului
	 * @param timp Timpul la care sa inregistrat umeditatea
	 * @param umeditate Umeditatea inregistrata
	 */
	public void ObservaU(Locuinta l, String senzor, long timp, double umeditate) {
		if (timp > l.getTimestamp() || timp < l.getTimestamp() - 86400) return;
		for	(int i = 0; i < l.getNr_camere(); i++) {
			if(l.room_list.get(i).name_senzor.compareTo(senzor) == 0) {
				if(l.room_list.get(i).buchets.isEmpty()) {
					for(int j = 0; j < 24; j++) {
						TemperaturaUmeditate buchet = new TemperaturaUmeditate();
						l.room_list.get(i).buchets.add(buchet);
					}
				}
				long a = l.timestamp - 86400;
				a = timp - a;
				a = a / 3600;
				int indice = (int)a;
				if(l.room_list.get(i).buchets.get(indice).umeditate.contains(umeditate) == true) {return;}
				
				l.room_list.get(i).buchets.get(indice).umeditate.add(umeditate);
				l.room_list.get(i).buchets.get(indice).nr_umeditati++;
				if(l.room_list.get(i).buchets.get(indice).getUmed_minima() < umeditate) {
					l.room_list.get(i).buchets.get(indice).setUmed_minima(umeditate);
				}
			}
		}
	}

	/**
	 * 
	 * @param l Casa cu lista de camere
	 * @param idx Reprezinta o camera
	 * @param start Valoare minima a intervalului
	 * @param stop Valoare maxima a intervalului
	 */
	public void List(Locuinta l, int idx, long start, long stop) {
		long a = l.timestamp - 86400;
		a = start - a;
		a = a / 3600;
		int in = (int)a;
				
		long b = l.timestamp - 86400;
		b = stop - b;
		b = b / 3600;
		int te = (int)b;
		te--;		
		
		for (;te >= in; te--) {
			if (l.room_list.get(idx).buchets.get(in).nr_temperaturi > 0) {					
				for (int count = 0; count < l.room_list.get(idx).buchets.get(te).temp_list.size(); count++) {
					Collections.sort(l.room_list.get(idx).buchets.get(te).temp_list);
					if(te == in && count == l.room_list.get(idx).buchets.get(te).temp_list.size() - 1) {
						System.out.print(String.format(Locale.US, "%.2f", l.room_list.get(idx).buchets.get(te).temp_list.get(count)));
					} else {						
						System.out.print(String.format(Locale.US, "%.2f", l.room_list.get(idx).buchets.get(te).temp_list.get(count)) + " ");
					}
				}
			}
		}
		System.out.println();	
	}
	
	/**
	 * O parcurgere si se returneaza indicile la camera dorita
	 * @param l Casa cu lista de camere
	 * @param name_room O camera anumita care trebuie gasita
	 * @return Returneaza indicile din lista
	 */
	public int GetIndex(Locuinta l, String name_room) {
		for(int i=0; i < l.nr_camere; i++) {
			if(l.room_list.get(i).name_camera.compareTo(name_room) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Functia metru calculul mediei temperaturei
	 * @param l Casa
	 */
	public void TriggerTemperatura(Locuinta l) {
		long idx = l.timestamp - 86400;
		long indice = l.timestamp - 1000;
		indice = indice - idx;		
		indice = indice / 3600;
		int val = (int)indice;
		int copie = val;
		
		double sus = 0;
		double jos = 0;
		
		for(int i = 0; i < l.room_list.size(); i++, val = copie) {
			while(l.room_list.get(i).buchets.get(val).nr_temperaturi == 0 ) {
				val -= 1;;
			}
			sus += l.room_list.get(i).m_patrati * l.room_list.get(i).buchets.get(val).temp_minima;
			jos += l.room_list.get(i).m_patrati;
		}
		double media = sus / jos;
		if(media < l.temperatura) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
	
	/**
	 * Functia pentru calculul mediei umeditatii
	 * @param l
	 */
	public void TriggerUmeditate(Locuinta l) {
		long idx = l.timestamp - 86400;
		long indice = l.timestamp - 1000;
		indice = indice - idx;		
		indice = indice / 3600;
		int val = (int)indice;
		int copie = val;
		
		double sus = 0;;
		double jos = 0;
		
		for(int i = 0; i < l.room_list.size(); i++, val = copie) {
			while(l.room_list.get(i).buchets.get(val).nr_umeditati == 0 ) {
				val -= 1;;
			}
			sus += l.room_list.get(i).m_patrati * l.room_list.get(i).buchets.get(val).umed_minima ;
			jos += l.room_list.get(i).m_patrati;
		}
		double media = sus / jos;

		if(media > l.getUmeditatea()) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
		}
	}
}