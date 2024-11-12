package lab_5;

import lab_5.Classes.Car;
import lab_5.Classes.CarRental;
import lab_5.Classes.Renter;
import lab_5.Service.CarRentalService;
import lab_5.DAO.CarDao;
import lab_5.DAO.DataSource;
import lab_5.DAO.DataSource;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource.createTablesStructure();
        Car car = new Car.CarBuilder()
                .setBrand("Nissan")
                .setVin("JTHBE933380012245")
                .setPlateNumber("AB1324CD")
                .setReleaseDate(LocalDate.parse("2012-02-12"))
                .setPricePerDay(43.9)
                .setMileage(50000)
                .build();


        CarDao carDao = new CarDao();
        carDao.add(car);

        car = new Car.CarBuilder()
                .setBrand("Toyota")
                .setVin("JTHBE453380012245")
                .setPlateNumber("AB1544CD")
                .setReleaseDate(LocalDate.parse("2012-04-12"))
                .setPricePerDay(44.9)
                .setMileage(40000)
                .build();


        carDao.add(car);

        car = carDao.getAll().get(1);
        System.out.println(car);

    }
}
