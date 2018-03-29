package constant;
/**
 * 公共的一些常量
 * @author Administrator
 *
 */
public class CommonConstant {
	/**
     * token请求头名称
     */
    public static String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    public static String TOKEN_SPLIT = "bearer ";

    /**
     * jwt签名
     */
    public static  String JWT_SIGN_KEY = "Res";

    /**
     * swagger-ui匹配
     */
    public static final String SWAGGER_UTI = "/**/swagger-ui.html/**";
}
