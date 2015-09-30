
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;



import freemarker.template.TemplateException;
/**
 * <b>Bean definition for freemarker configuration</b>
 */
@Configuration
public class BankACProfileFreemarkerConfig {
	/**
	 * Logger configuration for logging
	 */
	private static final Logger logger = Logger.getLogger(BankACProfileFreemarkerConfig.class);
	
	/**
	 * Defining all the paths required for freemarker 
	 */
	private static final String ENV_PATH = "/test";
	private static final String FILE = "file:///";
	private static final String TEMPLATE_LOADER_PATH = FILE + ENV_PATH + "freemarker/";
	private static final String STATIC_FRAGMENT_LOADER_PATH = ENV_PATH;
	private static final String FREEMARKER_TEMPLATE_LOADER_PATH = ENV_PATH + "freemarker/template";

	/**
	 * Definition of bean for {@link FreeMarkerViewResolver}. Creates a
	 * FreeMarkerViewResolver type object and assigns to reference named
	 * <code>viewResolver</code>.
	 * 
	 * @return A {@link FreeMarkerViewResolver} object.
	 */
	@Bean
	public FreeMarkerViewResolver viewResolver() {
		logger.debug("Creating bean :: FreeMarkerViewResolver");
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setOrder(1);
		resolver.setCache(true);
		resolver.setPrefix("");
		resolver.setSuffix(".ftl");
		return resolver;
	}

	/**
	 * Configures the FreeMarker with source paths of static files.
	 * 
	 * @return A {@link FreeMarkerConfigurer} object.
	 * @throws IOException
	 */
	@Bean
	public FreeMarkerConfigurer getFreemarkerConfig() throws IOException, TemplateException {
		logger.debug("Creating bean :: FreeMarkerConfigurer");
		FreeMarkerConfigurer result = new FreeMarkerConfigurer();

		result.setTemplateLoaderPath(TEMPLATE_LOADER_PATH);

		FilePathTemplateLoader[] preTemplateLoaders = new FilePathTemplateLoader[3];

		FilePathTemplateLoader freeMarkerTemplateLoader = new FilePathTemplateLoader(
				FREEMARKER_TEMPLATE_LOADER_PATH);
		FilePathTemplateLoader commonFreeMarkerTemplateLoader = new FilePathTemplateLoader(
				FREEMARKER_TEMPLATE_LOADER_PATH);
		FilePathTemplateLoader staticFragmentLoader = new FilePathTemplateLoader(
				STATIC_FRAGMENT_LOADER_PATH);

		preTemplateLoaders[0] = freeMarkerTemplateLoader;
		preTemplateLoaders[1] = commonFreeMarkerTemplateLoader;
		preTemplateLoaders[2] = staticFragmentLoader;

		result.setPreTemplateLoaders(preTemplateLoaders);
		
		/** Set Exception Handler */
		freemarker.template.Configuration config = result.createConfiguration();
		config.setTemplateExceptionHandler(new FreemarkerTemplateExceptionHandler());
		result.setConfiguration(config);

		return result;

	}
}
