package com.project.authsvc.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.core.Ehcache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.project.authsvc.model.User;
import com.project.authsvc.service.AuthResponse;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jose.proc.SimpleSecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;



@Component
public class TokenServiceImpl implements TokenService {
	
	private Logger LOG = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private DirectEncrypter encrypter;

	@Autowired
	private ConfigurableJWTProcessor<SimpleSecurityContext> jwtProcessor;

	@Autowired
	private Cache<String, Object> tokenCache;
  
	@Override
	public String generateToken(User user) {

		try {
			Date issueDate = new Date();
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject(user.getEmail()).issueTime(issueDate)
					.issuer("a3corecompete").expirationTime(getExpirationDate(issueDate)).build();

			Payload payload = new Payload(claimsSet.toJSONObject());
			JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);

			JWEObject jweObject = new JWEObject(header, payload);
			jweObject.encrypt(encrypter);
			String token = jweObject.serialize();
			tokenCache.put(token, user);
			return token;
		} catch (Exception e) {
			throw new RuntimeException("problem encrypting token " + e);
		}

	}

	@Override
	public AuthResponse verifyToken(String jweString) {
		Date currentDate = new Date();
		try {
			JWTClaimsSet claims = jwtProcessor.process(jweString, null);
			User user = (User) tokenCache.get(jweString);
			
			if(user == null ) {
				return buildAuthResponse(null, ErrorCodes.E300.toString(), "Token Expired. Please login again and get a new token.");
			}
			
			if (claims != null && currentDate.before(claims.getExpirationTime())) {
				if (user != null ) {
					if(user.getEmail().equals(claims.getSubject())) {
						return buildAuthResponse(user.getEmail(), null, null);

					}else {
						return buildAuthResponse(null, ErrorCodes.E400.toString(), "Invalid Token, Please provide a valid token");
					}
				}
			}

		} catch (Exception e) {
			if(e instanceof BadJWTException ) {
				BadJWTException bje = (BadJWTException) e;
				if(bje.getMessage().contains("Expired JWT")) {
					return buildAuthResponse(null, ErrorCodes.E300.toString(), "Token Expired. Please login again and get a new token.");
				}
			}
			return buildAuthResponse(null, ErrorCodes.E400.toString(), "Invalid Token, Please provide a valid token");
		}

		return null;

	}

	@Override
	public List<String> getTokens() {
		Cache<String, Object> cache = (Ehcache<String, Object>) cacheManager.getCache("tokenCache", String.class,
				Object.class);
		List<String> keys = new ArrayList<>();
		for (Cache.Entry<String, Object> entry : cache) {
			keys.add(entry.getKey());
		}
		return keys;
	}

	public Date getExpirationDate(Date issueDate) {
		return DateUtils.addSeconds(issueDate, 3600);
	}

	@Override
	public void remove(String token) {
		tokenCache.remove(token);
		LOG.info("Clearing the token from cache {} ", token);
	}
	
	private AuthResponse buildAuthResponse(String email, String errorCode, String message) {
		
		AuthResponse response = new AuthResponse();
		response.setErrorCode(errorCode);
		response.setMessage(message);
		response.setEmail(email);
		
		return response;
	}


}
