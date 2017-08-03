package ch.unibe.scg.curtys.bugreportquality.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author curtys
 */
public class ConfigurationLoader {

	public static Configuration load(String configFilePath) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		InputStream in = ConfigurationLoader.class.getResourceAsStream(configFilePath);
		Configuration config = mapper.readValue(in, Configuration.class);
		return config;
	}
}
