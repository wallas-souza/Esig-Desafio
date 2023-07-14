package repository;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class InsertDB {
	
    public static void main(String[] args) throws CsvValidationException {
        // Load Hibernate configuration
        Configuration configuration = new Configuration().configure();

        // Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session
        try (Session session = sessionFactory.openSession()) {
            // Read the CSV file and insert the data into the database
            try {
                // Check if there are records in the table
                boolean tableIsEmpty = session.createQuery("SELECT COUNT(*) FROM Pessoa", Long.class)
                        .getSingleResult() == 0;

                // If the table is empty, read the CSV file and insert the data
                if (tableIsEmpty) {
                    CSVReader reader = new CSVReader(new FileReader("C:\\Users\\hazen\\eclipse-workspace\\desafio-esig\\pessoa.csv"));

                    String[] nextLine;
                    String insertQuery = "INSERT INTO Pessoa (id, nome, cidade, email, cep, endereco, pais, usuario, telefone, data_nascimento, cargo_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    Connection connection = session.unwrap(Connection.class);
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

                    // Ignore the first line (header)
                    reader.readNext();

                    while ((nextLine = reader.readNext()) != null) {
                        int id = Integer.parseInt(nextLine[0]);
                        String nome = nextLine[1];
                        String cidade = nextLine[2];
                        String email = nextLine[3];
                        String cep = nextLine[4];
                        String endereco = nextLine[5];
                        String pais = nextLine[6];
                        String usuario = nextLine[7];
                        String telefone = nextLine[8];
                        String dataNascimento = nextLine[9];
                        int cargoId = Integer.parseInt(nextLine[10]);

                        insertStatement.setInt(1, id);
                        insertStatement.setString(2, nome);
                        insertStatement.setString(3, cidade);
                        insertStatement.setString(4, email);
                        insertStatement.setString(5, cep);
                        insertStatement.setString(6, endereco);
                        insertStatement.setString(7, pais);
                        insertStatement.setString(8, usuario);
                        insertStatement.setString(9, telefone);
                        insertStatement.setString(10, dataNascimento);
                        insertStatement.setInt(11, cargoId);
                        insertStatement.executeUpdate();
                    }

                    insertStatement.close();
                    reader.close();

                    session.beginTransaction();
                    session.getTransaction().commit();

                    System.out.println("Dados inseridos com sucesso!");
                } else {
                    System.out.println("A tabela já possui dados, não é necessário inserir novamente.");
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        } finally {
            // Close the SessionFactory
            sessionFactory.close();
        }
    }
}
