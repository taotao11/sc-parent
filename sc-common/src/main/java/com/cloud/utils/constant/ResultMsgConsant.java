package com.cloud.utils.constant;

/**
 * @author hedonglin
 * @company 个人
 * @email 1048791780@qq.com
 * @date 2018年02月09
 */
public class ResultMsgConsant {
    public static final String Info_Result_Key = "info";
    public static final String List_Result_Key = "list";
    public static final String Page_Result_Key = "page";
    public static final String Page_Result_Datas_Key = "datas";
    public static final String Page_Result_Total_Key = "total";

    private static final String sucessMsg = "成功!";
    private static final String failMsg = "失败!";

    private static final String addMsg = "新增";
    private static final String updateMsg = "修改";
    private static final String deleteMsg = "删除";
    private static final String distributeMsg = "分配";
    private static final String inMsg = "进入";
    private static final String outMsg = "离开";
    private static final String inJobMsg = "任职";
    private static final String outJobMsg = "离职";


    public static final String DELETE_STATUS_KEY = "delsts";
    public static final String DELETE_STATUS_SIGN = "!=";


    /**
     * 返回各自类型成功的消息
     * @param way
     * @return
     */
    public static String getSuccessResultMsg(OperatorWays way) {
        return way.getValue() + sucessMsg;
    }

    /**
     * 返回各自类型失败的消息
     * @param way
     * @return
     */
    public static String getFailResultMsg(OperatorWays way) {
        return way.getValue() + failMsg;
    }

    public enum OperatorWays {
        ADD_MSG {
            @Override
            public String getValue() {
                return addMsg;
            }
        },
        UPDATE_MSG {
            @Override
            public String getValue() {
                return updateMsg;
            }
        },
        DELETE_MSG {
            @Override
            public String getValue() {
                return deleteMsg;
            }
        },
        DISTRIBUTE_MSG  {
            @Override
            public String getValue() {
                return distributeMsg;
            }
        },
        IN_MSG  {
            @Override
            public String getValue() {
                return inMsg;
            }
        },
        OUT_MSG  {
            @Override
            public String getValue() {
                return outMsg;
            }
        },
        IN_JOB_MSG  {
            @Override
            public String getValue() {
                return inJobMsg;
            }
        },
        OUT_JOB_MSG  {
            @Override
            public String getValue() {
                return outJobMsg;
            }
        };
        public abstract String getValue();
    }

}
