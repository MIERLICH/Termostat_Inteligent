import java.util.LinkedList;
import java.util.Scanner;
import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File in = new File("therm.in");
		Scanner scan = new Scanner(in);
		String param[];
		String line;
		int flag;
		Locuinta casa = null;
		line = scan.nextLine();
		param = line.split(" ");
		
		// Daca vectorul de stringuri param are lungimea 3 => ca in input
		// este data doar temperatura
		// Daca lungimea este 4 insemna ca sa dat si umeditatea
		if (param.length == 3) {
			casa = new Locuinta(Integer.parseInt(param[0]), Double.parseDouble(param[1]),
								0.0, Long.parseLong(param[2]));			
			flag = 0;
		} else {
			casa = new Locuinta(Integer.parseInt(param[0]), Double.parseDouble(param[1]),
								Double.parseDouble(param[2]), Long.parseLong(param[3]));
			flag = 1;
		}

		while (scan.hasNextLine()) {
			line = scan.nextLine();
			param = line.split(" ");
			
			if (param.length <= 1) {break;}
			// Cu un while citesc din fisierul de input cate o linie
			// transform linia in vector de stringuri cu metoda
			// split, delimitatorul fiind " "
			if (param[0].charAt(0) == 'R') {
				//ROOM construiesc si adaug in lista de camere				
				LinkedList<Camera> list_camere = casa.getRoom_list();
				list_camere.add(new Camera(param[0], param[1], Integer.parseInt(param[2])));
			} else if (param[0].compareTo("OBSERVE") == 0) {
				//Inregistreaza temperatura
				casa.ObservaT(casa, param[1], Long.parseLong(param[2]), Double.parseDouble(param[3]));
			} else if (param[0].compareTo("OBSERVEH") == 0) {
				//Inregistreaza umeditatea
				casa.ObservaU(casa, param[1], Long.parseLong(param[2]), Double.parseDouble(param[3]));
			} else if (param[0].compareTo("TRIGGER") == 0) {
				//TRIGGER
				if (flag == 0) {
					casa.TriggerTemperatura(casa);
				} else {
					casa.TriggerUmeditate(casa);
				}
			} else if (param[0].compareTo("TEMPERATURE") == 0){
				//CHANGE temperature
				casa.setTemperatura(Double.parseDouble(param[1]));
			} else if (param[0].compareTo("LIST") == 0) {
				//LIST listeaza
				int idx = casa.GetIndex(casa, param[1]);
				System.out.print("ROOM" + (idx + 1) + " ");
				casa.List(casa, idx, Long.parseLong(param[2]), Long.parseLong(param[3]));
			}
		}
		scan.close();
	}
}