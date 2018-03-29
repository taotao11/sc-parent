package vo.authority;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限判断所需字段
 * @author Administrator
 *
 */
@Data
public class PermissionInfo implements Serializable{
	private static final long serialVersionUID = -7698119739980965601L;
	//权限代码
	private String code;
	//类型
    private String type;
    //路径
    private String uri;
    //请求方法
    private String method;
}
