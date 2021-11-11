import java.util.LinkedList;

class Camera {
	int m_patrati;
	String name_camera;
	String name_senzor;
	LinkedList<TemperaturaUmeditate> buchets;

	Camera() {}
	Camera(String name_camera, String name_senzor, int metri) {
		this.name_camera = name_camera;
		this.name_senzor = name_senzor;
		this.m_patrati = metri;
		this.buchets = new LinkedList<TemperaturaUmeditate>();
	}
	public int getM_patrati() {
		return m_patrati;
	}
	public void setM_patrati(int m_patrati) {
		this.m_patrati = m_patrati;
	}
	public String getName_camera() {
		return name_camera;
	}
	public void setName_camera(String name_camera) {
		this.name_camera = name_camera;
	}
	public String getName_senzor() {
		return name_senzor;
	}
	public void setName_senzor(String name_senzor) {
		this.name_senzor = name_senzor;
	}
	public LinkedList<TemperaturaUmeditate> getBuchets() {
		return buchets;
	}
	public void setBuchets(LinkedList<TemperaturaUmeditate> buchets) {
		this.buchets = buchets;
	}
}
