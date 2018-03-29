package vo.user;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserVo implements Serializable{
    /**
     * id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证号码
     */
    private String idcard;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 常用手机号码
     */
    private String usualmobile;
    /**
     * 备用手机号码
     */
    private String sparemobile;
    /**
     * 办公电话
     */
    private String officephone;
    /**
     * qq
     */
    private String qq;
    /**
     * 微信
     */
    private String wechat;
    /**
     * 微博账号
     */
    private String microblog;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 排序
     */
    private Integer sorting;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 备注
     */
    private String remark;
    /**
     * 角色列表
     */
    private List<String> roleList = new ArrayList<>();
}
