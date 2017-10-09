package bsd.spring.weather.dao;

import bsd.spring.weather.model.Tiempo;
import bsd.spring.weather.model.Usuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MainDaoImpl implements MainDao {
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase db = mongoClient.getDatabase("spring");
	
	MongoCollection<Document> collectionUsuarios = db.getCollection("usuarios");
	MongoCollection<Document> collectionConsultas = db.getCollection("consultas");

	public boolean validLogin(Usuario user) {
		FindIterable<Document> document = collectionUsuarios.find(Filters.and(new Bson[] {
				Filters.eq("usuario", user.getUsername()), Filters.eq("contraseña", user.getPassword()) }));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	public boolean insertarUsuario(Usuario user) {
		Document doc = new Document("usuario", user.getUsername()).append("consultas", user.getPassword())
				.append("email", user.getEmail()).append("fechaNacimiento", user.getFechaNacimiento());

		if (!existeUsuario(user.getUsername(), collectionUsuarios)) {
			collectionUsuarios.insertOne(doc);
			return true;
		}

		return false;
	}

	private boolean existeUsuario(String username, MongoCollection<Document> collection) {
		FindIterable<Document> document = collection.find(Filters.eq("usuario", username));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}

	public boolean insertarConsulta(Tiempo weather , String username) {
		
		List<BasicDBObject> consultas = new ArrayList<>();
		BasicDBObject consulta = new BasicDBObject();
		Document doc = new Document();
		
		consulta.put("ciudad", weather.getCiudad());
		consulta.put("region", weather.getRegion());
		consulta.put("pais", weather.getPais());
		consulta.put("temperatura", weather.getTemperatura());
		
		consultas.add(consulta);
		
		if(existeUsuario(username, collectionConsultas)) {
			doc.append("consultas", consulta);
			collectionConsultas.updateOne(Filters.eq("usuario", username), new Document("$push", doc));
			return true;
			
		} else {
			doc.append("usuario", username);
			doc.append("consultas", consultas);
			collectionConsultas.insertOne(doc);
			return true;
		}

		
	}

	@Override
	public List<Tiempo> getConsultas(String username) {
		
		FindIterable<Document> document = collectionConsultas.find(Filters.eq("usuario", username));
		ArrayList<Document> array = (ArrayList<Document>) document.iterator().next().get("consultas");
			
	    	 List<Tiempo> listaConsultas = new ArrayList<>();
	    	 
	    	 for(Document doc : array) {
	    		 listaConsultas.add(new Tiempo(doc.getString("ciudad"), doc.getString("region"), doc.getString("pais"), doc.getString("temperatura")));
	    	 }
		//List<Tiempo> listaConsultas = new ArrayList<>();
		 
		/*Block<Document> recorrer = new Block<Document>() {
		     @Override
		     public void apply(final Document document) {
		    	 
		    	 ArrayList<Document> array = (ArrayList<Document>) document.get("consultas");
		    	 List<Tiempo> listaConsultas = new ArrayList<>();
		    	 
		    	 for(Document doc : array) {
		    		 listaConsultas.add(new Tiempo(doc.getString("ciudad"), doc.getString("region"), doc.getString("pais"), doc.getString("temperatura")));
		    	 }
		    	 
		    	 

		     }
		};
		
		document.forEach(recorrer);*/

		System.out.println("asdasdasd "+listaConsultas.size());
		return listaConsultas;

		
	}
}
