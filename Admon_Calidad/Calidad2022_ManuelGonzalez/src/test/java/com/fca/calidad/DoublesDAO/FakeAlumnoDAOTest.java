package com.fca.calidad.DoublesDAO;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FakeAlumnoDAOTest {
	private FakeAlumnoDAO DAO;
	public HashMap<String, Alumno> baseDatos;

	@Before
	public void setUp() throws Exception {
		DAO = Mockito.mock(FakeAlumnoDAO.class);
		baseDatos= new HashMap<String, Alumno>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void agregarTest(){
		when (DAO.addAlumno(any(Alumno.class))).thenAnswer (new Answer<Boolean>(){
			public Boolean answer(InvocationOnMock invocation)throws Throwable{
				Alumno arg = (Alumno) invocation.getArguments()[0];
				baseDatos.put("1",arg);
				System.out.println("Size despues= "+ baseDatos.size()+"\n");
				return true;
			}
		}
		);
		Alumno a = new Alumno( "nombre", "id", 14, "email");
		int sizeBefore = baseDatos.size();
		Boolean res = DAO.addAlumno(a);
		int sizeAfter = baseDatos.size();
		
		assertThat(sizeAfter, is(sizeBefore+1));
	}
	@Test
	public void updateEmailTest(){
		when (DAO.updateEmail(any(Alumno.class))).thenAnswer (new Answer<Boolean>(){
			//Method within the class
			public Boolean answer(InvocationOnMock invocation)throws Throwable{
				//Set behavior in every invocation
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				
				baseDatos.put(arg.getId(),arg);
				return true;
			}
		}
		);
		//Setup
		
		Alumno alumno = new Alumno( "nombre", "1", 14, "email");
		baseDatos.put("1",alumno);
		
				alumno.setEmail("nuevoCorreo");
		
		
		DAO.updateEmail(alumno);
		
		
		String valorEsperado = "nuevoCorreo";
		String valorEjecucion = baseDatos.get("1").getEmail();
		
		assertThat(valorEsperado, is(valorEjecucion));
	}
	@Test
	public void deleteTest(){
		when (DAO.delateAlumno(any(Alumno.class))).thenAnswer (new Answer<Boolean>(){
			//Method within the class
			public Boolean answer(InvocationOnMock invocation)throws Throwable{
				//Set behavior in every invocation
				Alumno arg = (Alumno) invocation.getArguments()[0];
				
				
				baseDatos.remove(arg.getId());
				return true;
			}
		}
		);
		//Setup
		
		Alumno alumno = new Alumno( "nombre", "2", 14, "email");
		baseDatos.put("2",alumno);
		
		
		DAO.delateAlumno(alumno);
		
		
		int size = baseDatos.size();

		
		assertThat(size, is(0));
	}
	@Test
	public void searchTest(){
		when (DAO.searchAlumno(any(String.class))).thenAnswer (new Answer<Alumno>(){
			//Method within the class
			public Alumno answer(InvocationOnMock invocation)throws Throwable{
				//Set behavior in every invocation
				String id = (String) invocation.getArguments()[0];
				
				
				Alumno alumno = baseDatos.get(id);
				return alumno;
			}
		}
		);
		//Setup
		
		Alumno alumno = new Alumno( "nombre", "2", 14, "email");
		baseDatos.put("2",alumno);
		
		
		
		Alumno busqueda = DAO.searchAlumno(alumno.getId());
		
		
		Alumno resultadoEjecucion = busqueda;
		Alumno resultadoEsperado = alumno;

		
		assertThat(resultadoEsperado, is(resultadoEjecucion));
	}
	
	}

