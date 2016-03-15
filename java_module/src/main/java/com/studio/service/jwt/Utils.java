package com.studio.service.jwt;

import com.sun.javafx.css.StyleCacheEntry;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Utils {

    static Key key = null;
    public static String generateWebToken(String name) {
        long curTime = System.currentTimeMillis();
        key = MacProvider.generateKey();
        Claims claim = Jwts.claims();
        String audience = "mjj";
        Map<String, Object> header = Jwts.header();
        Date expir = new Date(curTime + 5000);
        Date issAt = new Date(curTime);
        String id = "1234";
        Date notBef = null;
        String subject = "subject";

        String str = Jwts.builder().
                setIssuer(name).
                setExpiration(expir).
                setClaims(claim).
                setAudience(audience).
                setHeader(header).
                setIssuedAt(issAt).
                setId(id).
                setNotBefore(notBef).
                setSubject(subject).
                signWith(SignatureAlgorithm.HS512, key).
                compact();
        return str;
    }

    public static String parseBody(String token) {
        Jwt jwt = Jwts.parser().setSigningKey(key).parse(token);
        return jwt.getBody().toString();
    }
}
