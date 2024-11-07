package lab_5.DAO;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lab_5.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    static {
        try (InputStream input = MethodHandles.lookup().lookupClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            config = new HikariConfig(prop);
            ds = new HikariDataSource(config);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void createTablesStructure() throws SQLException {
        String createCar = """
                create table if not exists "Car" (
                    "vin"         text unique, 
                    "brand"        text, 
                    "plateNumber" text,
                    "releaseDate" date, 
                    "pricePerDay" float, 
                    "mileage"     int ,
                    primary key ("vin")
                    );
                """;
        String createRenter = """
                create table if not exists "Renter"
                (
                        "documentId"      text unique,
                        "firstName"       text,
                        "lastName"        text,
                        "driverLicence"   text unique,
                        primary key ("documentId")
                )
                """;

        String createCarRental = """
                create table if not exists "CarRental"
                (
                        "id"                serial unique,
                        "Car_vin"               text,
                        "Renter_id"            text,
                        "pickUpLocation"    text,
                        "dropOffLocation"   text,
                        "startDate"         date,
                        "endDate"           date,
                        "totalPrice"        double,
                        primary key ("id"),
                        foreign key ("Car_vin") references "Car" ("vin"),
                        foreign key ("Renter_id") references "Renter" ("documentId")
                );
                """;
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.execute(createCar);
            st.execute(createRenter);
            st.execute(createCarRental);
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
    }
}