package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Dao.CarDao;
import entity.Car;


@ManagedBean(name="control")
@SessionScoped
public class CarController {
	private CarDao carDao = new CarDao();
	//private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	private List<Car> listCar = new ArrayList<Car>();
	
	public List<Car> getList(){
		return carDao.getAllCars();
	}
	
	private Car car = new Car();
	public void saveCar() throws IOException{
		carDao.addCar(car);
		FacesContext.getCurrentInstance().getExternalContext().redirect("car.xhtml?faces-redirect=true");
	}
	
	public void deleteCar(int carId){
		carDao.deleteCar(carId);
	}
	
	public void updateCar(Car car){
	    //CarDao carDao ;
		carDao.updateCar(car);
	}
	
	public Car getById(int carId){
		return carDao.getCarById(carId);
	}
}
