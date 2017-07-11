package entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Dao.CarDao;

@ManagedBean  
@SessionScoped  
@Table(name = "car")
@Entity
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private int cid;
	@Column(name = "cname")            
	private String cname;
	@Column(name = "color")
	private String color;
	@Column(name = "speed")
	private int speed;
	@Column(name = "Manufactured_country")
	private String mfdctry;

	public Car() {
	}

	public Car(Integer cid, String cname, String color, Integer speed, String mfdctry) {
		this.cid = cid;
		this.cname = cname;
		this.color = color;
		this.speed = speed;
		this.mfdctry = mfdctry;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getMfdctry() {
		return mfdctry;
	}

	public void setMfdctry(String mfdctry) {
		this.mfdctry = mfdctry;
	}
	
	public List<Car> getList(){
		CarDao carDao = new CarDao();
		return carDao.getAllCars();
	}
	
	public void saveCar() throws IOException{
		CarDao carDao = new CarDao();
		carDao.addCar(this);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index2.xhtml?faces-redirect=true");
	}
	
	public void deleteCar(int cid){
		CarDao carDao = new CarDao();
		carDao.deleteCar(cid);
	}
	
	public void updateCar(Car car) throws IOException {
		CarDao carDao = new CarDao();
		carDao.updateCar(car);
		FacesContext.getCurrentInstance().getExternalContext().redirect("index2.xhtml?faces-redirect=true");
	}
	
	public void getById(int cid) throws IOException{
		Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
		CarDao carDao = new CarDao();
		Car car = carDao.getCarById(cid);
		sessionMap.put("editCar", car);
		FacesContext.getCurrentInstance().getExternalContext().redirect("edit.xhtml?faces-redirect=true");
	}
}