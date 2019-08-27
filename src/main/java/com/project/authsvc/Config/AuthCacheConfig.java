package com.project.authsvc.Config;

import java.time.Duration;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWEDecryptionKeySelector;
import com.nimbusds.jose.proc.JWEKeySelector;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

@Configuration
public class AuthCacheConfig {
	
	@Value("${auth.secret.key}") 
	private String authSecretKey;
	
	@Bean
	public CacheManager cacheManager(){
		CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
		cacheManager.init();
		return cacheManager;
	}
	
	@Bean
	public Cache<String, Object> tokenCache() {
		ExpiryPolicyBuilder builder = ExpiryPolicyBuilder.expiry();
		builder.access(Duration.ofSeconds(1800)).create(Duration.ofSeconds(3600)).update(Duration.ofSeconds(3600));
		
		Cache<String, Object> tokenCache = cacheManager()
		          .createCache("tokenCache", CacheConfigurationBuilder
		            .newCacheConfigurationBuilder(
		              String.class, Object.class,
		              ResourcePoolsBuilder.heap(50)).withExpiry(builder.build())
		            	);
		return tokenCache;

	}
	
	@Bean
	public DirectEncrypter directEncrypter() throws KeyLengthException {
		byte[] secretKey = authSecretKey.getBytes();
		DirectEncrypter encrypter = new DirectEncrypter(secretKey);
		return encrypter;
	}
	
	@Bean
	public ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor() {
		
		ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor = new DefaultJWTProcessor<SimpleSecurityContext>();
		JWKSource<SimpleSecurityContext> jweKeySource = new ImmutableSecret<SimpleSecurityContext>(authSecretKey.getBytes());
		JWEKeySelector<SimpleSecurityContext> jweKeySelector = new JWEDecryptionKeySelector<SimpleSecurityContext>(
				JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256, jweKeySource);
		jwtProcessor.setJWEKeySelector(jweKeySelector);
		return jwtProcessor;
		
	}
	

}

