package ch.unibe.scg.curtys.bugreportquality.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author curtys
 */
class ConfigurationLoaderTest {

	@Test
	void load() throws Exception {
		Configuration config = ConfigurationLoader.load("/config.yaml");
		assertEquals(2, config.getNodes().size());
	}

}