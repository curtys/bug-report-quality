package ch.unibe.scg.curtys.bugreportquality.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author curtys
 */
public class ConfigurationLoader {

	public static Configuration load(String configFilePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		ClassLoader classLoader = ConfigurationLoader.class.getClassLoader();
		File yamlFile = new File(classLoader.getResource(configFilePath).getFile());
		Configuration config = mapper.readValue(yamlFile, Configuration.class);
		return config;
	}
}
