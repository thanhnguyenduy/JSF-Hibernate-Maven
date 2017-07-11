package Dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import config.HibernateUtilities;
import controller.CarController;
import entity.Car;

public class CarDao {

	public static void main(String[] args) {
		Car bean = new Car();
		CarController control = new CarController();
		CarDao dao = new CarDao();
//		Car car = dao.getCarById(3);
//		System.out.println("Car name: "+car.getCname());
//		System.out.println("country: " + car.getMfdctry());
		//dao.updateCar(car);
		Car car = new Car(); car.setCname("Yaris");
		dao.addCar(car);
		//bean.deleteCar();
	}

	public void addCar(Car car) {
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(car);
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void deleteCar(int carId){
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		Car car = (Car) session.get(Car.class, carId);
		session.delete(car);
		System.out.println("Delete success");
		session.getTransaction().commit();
		
		session.close();
	}
	
	public void updateCar(Car car){
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(car);
		session.getTransaction().commit();
		
		session.close();
	}
	
	public List<Car> getAllCars(){
		Session session = HibernateUtilities.getSessionFactory().openSession();
		session.beginTransaction();
		List cars = session.createQuery("from Car").list();
		session.getTransaction().commit();
			
		return cars;
	}
	
	public Car getCarById(int carId){
//		Session session = HibernateUtilities.getSessionFactory().openSession();
//		session.beginTransaction();
//		 Query query=session.createQuery("from car where car_id= :id");
//         query.setInteger("car_id", carId);
//         car =(Car)query.uniqueResult();
//         session.getTransaction().commit();
		Car carFind = null;
		List<Car> listCar = getAllCars();
		
		for (Iterator iterator = listCar.iterator(); iterator.hasNext();) {
		Car car = (Car) iterator.next();
		if(car.getCid() == carId){
			carFind = car;
			break;
		}
	}
         return carFind;
	}

}
