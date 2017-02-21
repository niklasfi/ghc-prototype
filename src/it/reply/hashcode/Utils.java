package it.reply.hashcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {
	
	public static <T> T clone(T t) {
		try {
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
		    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		    return (T) ois.readObject();
		} catch (Exception e) {
			System.out.println("CANNOT CLONE");
			System.exit(-3);
		}
		return null;
	}
}
