package com.fca.calidad.dbunit;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import junit.framework.TestCase;

public class DaoEstudianteSqlLiteTest extends TestCase{

	IDatabaseConnection connection;
	DAOEstudianteSQLlite daoSQLite;
	
	public DaoEstudianteSqlLiteTest(String name) {
		super(name);
	}
	protected IDataSet getDataSet() throws Exception {
		// TODO Auto-generated method stub
		return new FlatXmlDataSetBuilder().build
				(new File("src/resources/initDB.xml"));
	}
	
	@Before
	public void setUp() throws Exception {
	super.setUp();
	daoSQLite = new DAOEstudianteSQLlite();
	Connection jdbcConnection;

	jdbcConnection = DriverManager.getConnection

	("jdbc:sqlite:C:\\Users\\fca\\Documents\\Admon_Calidad\\Calidad2022_ManuelGonzalez\\src\\resources\\Alumnos.db");


	connection = new DatabaseConnection(jdbcConnection);

	try {
	PreparedStatement preparedStatement;
	preparedStatement = jdbcConnection.prepareStatement("Delete from sqlite_sequence WHERE name = ?");
	// Set the values to match in the ? on query
	preparedStatement.setString(1, "Estudiante");
	Boolean result = false;

	 

	// Return the result of connection and statement
	if (preparedStatement.executeUpdate() >= 1) {
	result = true;
	}

	preparedStatement.close();

	 
	DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	} catch(Exception e) {
	fail("Error in setup: " + e.getMessage());
	connection.close();
	} 
	}
 

	@After
	public void tearDown()
	{
	try {
	if (connection != null)
	connection.close();
	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}

	public IDatabaseConnection getConnection()  {
	return connection;
	}
	
	@Test
	public void testCreateEstudiante() {
		Estudiante alumno = new Estudiante("nombrePruebaCrear","apellidoCrear","emailCrear","carreraCrear");

		int idNuevo = daoSQLite.createEstudiante(alumno);
		alumno.setId(idNuevo);
		
		
		int numEsperado=4;
		int numReal=  -1;
		try {
			
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			numReal = actualTable.getRowCount();
						assertThat(numEsperado, is(numReal));
		} catch(Exception e) {
			System.out.println("Error en createEstudianteTest: " +e.getMessage());
		}
	}
	
	
	@Test
	public void testCrearCompararTabla() {
		
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante"); 
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("src/resources/insert_result.xml")); 
			 ITable expectedTable = expectedDataSet.getTable("Estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert "
					+ "test: " + e.getMessage());
		}
	}

	@Test
	public void testCrearComparaRegistro() {
		
		Estudiante alumno = new Estudiante ("nombre1","apellido1","email" ,"carrera");
		
		int id = daoSQLite.createEstudiante(alumno);
		alumno.setId(id);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante where id = 3"); 
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                 new File("src/resources/insert2.xml")); 
			 ITable expectedTable = expectedDataSet.getTable("Estudiante");
	        Assertion.assertEquals(actualTable, expectedTable);		 
			
			
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error in insert "
					+ "test: " + e.getMessage());
		}
	}
	@Test
	public void testDeleteEstudiante() {
		boolean result = daoSQLite.deleteEstudiante(2);
		
		
		int resultEsperado=2;
		int resultReal=0;
		try {
			
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			ITable actualTable = databaseDataSet.getTable("Estudiante");
			resultReal = actualTable.getRowCount();

		
			assertThat(resultEsperado, is(resultReal));
		} catch(Exception e) {
			System.out.println("Error en delateEstudianteTest: " +e.getMessage());
		}
	}
	
	@Test
	public void testfindEstudiante() {
		Estudiante resultBusqueda = daoSQLite.findEstudiante(0);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante where id = 0"); //tabla con los resultados del query

			
			assertThat(resultBusqueda.getNombre(), is(actualTable.getValue(0, "nombre")));
			assertThat(resultBusqueda.getApellido(), is(actualTable.getValue(0, "apellido")));
			assertThat(resultBusqueda.getEmail(), is(actualTable.getValue(0, "email")));
			assertThat(resultBusqueda.getCarrera(), is(actualTable.getValue(0, "carrera")));
		} catch(Exception e) {
			System.out.println("Error en findEstudianteTest: " +e.getMessage());
		}
	}
	@Test
	public void testUpdateEmail() {
		Estudiante estudiante = new Estudiante("alumno002", "alumnoap002", "hola2actualizado@hola.com", "carrera2");
		estudiante.setId(1);
		
		daoSQLite.updateEmailEstudiante(estudiante);
		
		
		try {
			ITable actualTable = getConnection().createQueryTable(
	                "Estudiante",
	                "SELECT * FROM Estudiante where id = 1"); 
			
			assertThat(estudiante.getEmail(), is(actualTable.getValue(0, "email")));
			
			
		} catch (Exception e) {
			System.out.println("Error en updateEstudianteTest: " +e.getMessage());
		Estudiante resultBusqueda = daoSQLite.findEstudiante(0);
		
		}
	}
	
}
