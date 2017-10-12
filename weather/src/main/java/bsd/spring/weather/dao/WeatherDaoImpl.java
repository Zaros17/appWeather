package bsd.spring.weather.dao;

import bsd.spring.weather.model.Tiempo;

import com.mongodb.BasicDBObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherDaoImpl implements WeatherDao {
	
	MongoClientURI connectionString = new MongoClientURI(Config.dbPath);
	MongoClient mongoClient = new MongoClient(connectionString);
	MongoDatabase db = mongoClient.getDatabase("development");
	
	MongoCollection<Document> collectionConsultas = db.getCollection("consultas");

	/*
	 * Método privado que comprueba si existe un usuario en la bd
	 */
	private boolean existeUsuario(String username, MongoCollection<Document> collection) {
		FindIterable<Document> document = collection.find(Filters.eq("usuario", username));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}
	
	/*
	 * Método que se ocupa de insertar una consulta en la bd. Si ya existían consultas de un
	 * determinado usuario se actualiza de lo contrario se crea
	 * 
	 */
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

	/*
	 * Método que recupera todas las consultas de un determinado usuario
	 * 
	 */
	public List<Tiempo> getConsultas(String username) {
		
		FindIterable<Document> document = collectionConsultas.find(Filters.eq("usuario", username));
		if(document.iterator().hasNext()) {
			ArrayList<Document> array = (ArrayList<Document>) document.iterator().next().get("consultas");

	    	 List<Tiempo> listaConsultas = new ArrayList<>();
	    	 
	    	 for(Document doc : array) {
	    		 listaConsultas.add(new Tiempo(doc.getString("ciudad"), doc.getString("region"), doc.getString("pais"), doc.getString("temperatura")));
	    	 }
	    
		return listaConsultas;
		
		} else
			return null;

	}
	
	/*
	 * Método que elimina todas las consultas de la bd.
	 */
	public void eliminarConsultas() {
		collectionConsultas.deleteMany(new Document());
	}
}
