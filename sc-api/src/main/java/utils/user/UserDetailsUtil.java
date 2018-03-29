package utils.user;

import com.alibaba.fastjson.JSONObject;
import constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import utils.string.StringHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;

/**
 * 用户的工具类，从token中解析出用户信息
 * @author Administrator
 *
 */
@Slf4j
public class UserDetailsUtil {
    /**
     * 根据请求heard中的token获取用户
     *
     * @return 用户名
     */
    public static String getUsername(HttpServletRequest request) {
        String userName = "";
        try {
            String authorization = request.getHeader(CommonConstant.REQ_HEADER);
            log.debug("Res:authorization:{}",authorization);
            String token = StringUtils.substringAfter(authorization, CommonConstant.TOKEN_SPLIT);

            //此时token为空的话，如果是大写的bearer，则转化为小写
            if (StringUtils.isBlank(token)){
                authorization = StringHelper.captureName(authorization,false);
                token = StringUtils.substringAfter(authorization, CommonConstant.TOKEN_SPLIT);
            }

            log.debug("Res:token:{}",token);
            String key = Base64.getEncoder().encodeToString(CommonConstant.JWT_SIGN_KEY.getBytes());
            log.debug("Res:key:{}",key);
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            log.debug("Res:claims:{}", JSONObject.toJSONString(claims));
            userName = claims.get("user_name").toString();
        }catch (Exception e){
            throwGetTokenFailException(e);
        }
        return userName;
    }

    /**
     * 根据请求heard中的token获取用户角色
     *
     * @return 角色名
     */
    @SuppressWarnings("unchecked")
	public static List<String> getRole(HttpServletRequest request) {
        List<String> roleNames = null;
        try {
            String authorization = request.getHeader(CommonConstant.REQ_HEADER);
            String token = StringUtils.substringAfter(authorization, CommonConstant.TOKEN_SPLIT);
            //此时token为空的话，如果是大写的bearer，则转化为小写
            if (StringUtils.isBlank(token)){
                authorization = StringHelper.captureName(authorization,false);
                token = StringUtils.substringAfter(authorization, CommonConstant.TOKEN_SPLIT);
            }
            String key = Base64.getEncoder().encodeToString(CommonConstant.JWT_SIGN_KEY.getBytes());
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            roleNames = (List<String>) claims.get("authorities");
        }catch (Exception e){
            throwGetTokenFailException(e);
        }
        return roleNames;
    }


    /**
     * 获取token异常
     * @param e
     */
    public static void throwGetTokenFailException(Exception e){
        throw new RuntimeException("根据token获取用户信息异常",e);
    }


}
