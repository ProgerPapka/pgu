package beans;

import configuration.*;
import controller.*;
import data.EsiaUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ConfigBeans {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                {
                        new ClassPathResource( "rpgu.properties" ),
                        new ClassPathResource( "esia.properties" ),
                        new ClassPathResource("keystore.properties")
                };
        pspc.setLocations( resources );
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
    }

    @Bean
    public EsiaConfig initEsia(){
        return new EsiaConfig();
    }

    @Bean
    public RpguConfigToConnect initRpgu(){
        return new RpguConfigToConnect();
    }

    @Bean
    public KeyStoreConfig intiKeyStoreConfig(){
        return new KeyStoreConfig();
    }

    @Bean(name = "rpgu")
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Token initTokenToRPGU(){
        return new Token();
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Token initTokenToELK(){
        return new Token();
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthToken init(){
        return new AuthToken();
    }

    @Bean
    public GeneratorTimestamp initGeneratorTime(){
        return new GeneratorTimestamp();
    }

    @Bean
    public InspectorExpires initInspector(){
        return new InspectorExpires();
    }

    @Bean
    public Mapper initMapper(){
        return new Mapper();
    }

    @Bean
    public CertificateUtil initCripto(){
        return new Pkcs7Util();
    }

    @Bean
    public RequestToEsiaController initRequestController(){
        return new RequestToEsiaController();
    }

    @Bean
    public TokenController initTokenController(){
        return new TokenController();
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public EsiaUser esiaUser(){
        return new EsiaUser();
    }

}
