package vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserVo implements Serializable{
    /**
     * id
     */
    private Long id;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 常用手机号码
     */
    private String ipone;

    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 父id
     */
    private Long prentUid;
    /**
     * 角色列表
     */
    private List<String> roleList = new ArrayList<>();
}
