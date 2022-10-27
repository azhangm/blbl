package nuc.zm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import nuc.zm.exception.ConditonException;

import java.util.Calendar;
import java.util.Date;

/**
 * 令牌跑龙套
 *
 * @author zm
 * @date 2022/10/24
 */
public class TokenUtil {


    private static String ISSUER = "大卷菜";
    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND,30);
        return JWT.create().withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
//                对 头部信息  和 负载 信息 做一个 统一的算法加密
                .sign(algorithm);
    }

    public static Long verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            String keyId = verify.getKeyId();
            System.out.println(keyId);
            return Long.valueOf(keyId);
        } catch (TokenExpiredException e) {
            throw  new ConditonException("token 过期 ");
        }catch (Exception e) {
            throw new ConditonException("非法 用户token");
        }

    }

    public static void main(String[] args) {
        Long aLong = verifyToken("eyJraWQiOiI1IiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiLlpKfljbfoj5wiLCJleHAiOjE2NjY4NDc2ODB9.YvX6yZlNyrztNrxcUHZWU7Mdi89sJSkeEOMl8jPIzxxItnv1-2-s5HunrDftZZI7CahmBQH2FmQ2h8YUPcxPDPHIk6us5EFqCTv-UlSWThBqek_M_ZWbCfhxdEU12jk9vZgpCUHfTORNgKAMAD4Nov_nH26PhDnCyBuZQGfBsPg");

    }
}
