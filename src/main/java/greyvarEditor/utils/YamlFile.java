package greyvarEditor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public abstract class YamlFile { 
	public static <T> T read(File f, Class<T> class1) {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		
		try {
			return mapper.readValue(f, class1);
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}  
}
