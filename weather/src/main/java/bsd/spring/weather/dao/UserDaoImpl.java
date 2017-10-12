package bsd.spring.weather.dao;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import bsd.spring.weather.model.Usuario;

@Repository
public class UserDaoImpl implements UserDao{
	
	MongoClientURI connectionString = new MongoClientURI(Config.dbPath);
	MongoClient mongoClient = new MongoClient(connectionString);
	MongoDatabase db = mongoClient.getDatabase("development");
	
	
	MongoCollection<Document> collectionUsuarios = db.getCollection("usuarios");
	
	/*
	 * M�todo que se ocupa del login. Recibe como argumento el modelo Usuario y se hace una petici�n
	 * a la base de datos con su nombre y contrase�a en busca de una coincidencia. Si se encuentra, 
	 * se devuelve true, de lo contrario false.
	 *
	 */
	public boolean validLogin(Usuario user) {
		FindIterable<Document> document = collectionUsuarios.find(Filters.and(new Bson[] {
				Filters.eq("usuario", user.getUsername()), Filters.eq("contrase�a", user.getPassword()) }));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}
	
	/*
	 * M�todo que se ocupa del registro. Recibe como argumento el modelo Usuario y se realiza
	 * un insert en la bd en caso de que el usuario y el email no estuvieran en la bd.
	 *
	 */
	public boolean insertarUsuario(Usuario user) {
		Document doc = new Document("usuario", user.getUsername()).append("contrase�a", user.getPassword())
				.append("email", user.getEmail()).append("fechaNacimiento", user.getFechaNacimiento());

		if (!existeUsuario(user.getUsername(), collectionUsuarios) && !existeEmail(user.getEmail(), collectionUsuarios)) {
			collectionUsuarios.insertOne(doc);
			return true;
		}

		return false;
	}

	/*
	 * M�todo privado que comprueba si existe un usuario en la bd
	 */
	private boolean existeUsuario(String username, MongoCollection<Document> collection) {
		FindIterable<Document> document = collection.find(Filters.eq("usuario", username));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}
	
	/*
	 * M�todo privado que comprueba si existe un email en la bd
	 */
	private boolean existeEmail(String email, MongoCollection<Document> collection) {
		FindIterable<Document> document = collection.find(Filters.eq("email", email));

		if (document.iterator().hasNext()) {
			return true;
		}
		return false;
	}

}
