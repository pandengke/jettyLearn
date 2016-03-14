package com.studio.service.jwt;

import com.sun.javafx.css.StyleCacheEntry;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/14.
 */
public class Utils {
    private static Map<Key, String> tokenKey = new HashMap<>();
    private static Map<String, Key> tokenMap = new HashMap<>();


    public static String generateWebToken(String name) {
        Key key = MacProvider.generateKey();
        String str = Jwts.builder().setIssuer(name).setExpiration(new Date(System.currentTimeMillis() + 5000)).
                signWith(SignatureAlgorithm.HS512, key).compact();
        tokenKey.put(key, name);
        tokenMap.put(str, key);
        return str;
    }

    public static String parseBody(String token) {
        return Jwts.parser().setSigningKey(tokenMap.get(token)).parse(token).getBody().toString();
    }
}
