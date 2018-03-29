package vo.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息，包括角色和权限
 * @author Administrator
 *
 */
@Data
@NoArgsConstructor
public class UserInfo implements Serializable {
	/**
     * 用户基本信息
     */
    private UserVo userVo;

    /**
     * 权限标识集合
     */
    private String[] permissions;

    /**
     * 角色集合
     */
    private String[] roles;

    public UserInfo(UserVo userVo) {
        this.userVo = userVo;
    }
}
