package lab_5.DAO;

import lab_5.Classes.Car;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.*;

public class CarDao {
    private final Connection connection;
    static final String TABLE_NAME = "Car";

    public CarDao() {
        try {
            connection = DataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Car add(Car car) throws SQLException {
        String insertPerson = String.format("insert into \"%s\" (\"vin\", \"brand\", \"plateNumber\", \"releaseDate\", \"pricePerDay\", \"mileage\") values (?,?,?,?,?,?);", TABLE_NAME);
        try (PreparedStatement ps = connection.prepareStatement(insertPerson, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, car.getVin());
            ps.setString(2, car.getBrand());
            ps.setObject(3, car.getPlateNumber());
            ps.setObject(4, car.getReleaseDate());
            ps.setObject(5, car.getPricePerDay());
            ps.setObject(6, car.getMileage());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return car;
            } else {
                throw new IllegalArgumentException("Error while creating car " + car);
            }
        } catch (PSQLException ex){
            System.out.println("Error while creating car " + car);
            return car;
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return car;
        }
    }

    public List<Car> getAll() throws SQLException {
        String getAll = String.format("select * from %1$s;", TABLE_NAME);

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAll);
        return getSortedByNumber(rs);
    }

    private List<Car> getSortedByNumber(ResultSet rs) throws SQLException {
        Set<Car> result = new TreeSet<>();
        while (rs.next()) {
            Car car = fromResultSet(rs);
            result.add(car);
        }
        return result.stream().toList();
    }


    private Car fromResultSet(ResultSet rs) throws SQLException {
        return new Car.CarBuilder()
                .setBrand(rs.getString("brand"))
                .setVin(rs.getString("vin"))
                .setPlateNumber(rs.getString("plateNumber"))
                .setReleaseDate(rs.getDate("releaseDate").toLocalDate())
                .setPricePerDay(rs.getDouble("pricePerDay"))
                .setMileage(rs.getInt("mileage"))
                .build();
    }

}
