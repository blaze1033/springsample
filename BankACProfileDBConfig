import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;




@Configuration
@Import({BankACProfileWebConfig.class})
public class BankACProfileDBConfig {
	private static final Logger logger = Logger.getLogger(BankACProfileDBConfig.class);
	
	private static final String CBWS_JNDI_NAME = "jndi.InternetBanking_ORA_ApplDB";
	
	//	Importing the BankACHomeWebConfig class
	@Autowired BankACProfileWebConfig bankACProfileWebConfig;
	
	/**
	 * Bean configuration for {@link DataSource} with 
	 *  data source configuration
	 * @return DataSource
	 */
	@Bean
	public DataSource cbwsDataSource(){
		logger.debug("Creating bean : cbwsDataSource");
		logger.debug("looking up for jndi name "+CBWS_JNDI_NAME);
		String jndiName = bankACProfileWebConfig.managedPropertyConfig().getMessage( CBWS_JNDI_NAME, null, "jdbc/ApplDB" );
	
		DataSource ds = null;
		InitialContext context = initialContext();
		try {
			ds = (DataSource) context.lookup(jndiName);
		} catch (NamingException e) {
			logger.error("Exception occurred while creating bean of DataSource. >>>"+e.getMessage());
		}
		logger.debug("CBWS DataSource object is >>>>"+ds);
		return ds;
	}
	/**
	 * Bean configuration for {@link InitialContext}
	 * @return InitialContext
	 */
	@Bean
	public InitialContext initialContext(){
		logger.debug("Creating bean : initialContext");
		InitialContext context = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			logger.error("Exception occurred while creating bean of InitialContext. >>>"+e.getMessage());
		}
		return context;
	}

	/**
	 * Bean configuration for {@link BankACHomeDAO}  
	 * @return BankACHomeDAO
	 */
	@Bean
	public BankACProfileDAO bankACProfileDAO(){
		logger.debug("Creating bean : bankACHomeCBWSDAO");
		return new BankACProfileDAOImpl(namedParameterJdbcTemplate());
	}
	
	/**
	 * Bean configuration for {@link NamedParameterJdbcTemplate}  
	 * @return NamedParameterJdbcTemplate
	 */
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
		logger.debug("Creating bean : namedParameterJdbcTemplateCBWS");
		return new NamedParameterJdbcTemplate(cbwsDataSource());
	}
	
}
