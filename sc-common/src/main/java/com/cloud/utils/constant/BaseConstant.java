package com.cloud.utils.constant;

public class BaseConstant {
    public enum DeleteStatus{
        IS_DELETE(-1),
        IS_NOT_DELETE(0);
        private int value;

        DeleteStatus(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    public enum CurrentOrHistoryStatus{
        CURRENT(1),
        HISTORY(2);
        private int value;

        CurrentOrHistoryStatus(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    // database orgid key
    public static final String ORG_ID_KEY = "org_id";

    public static final String DELETE_STATUS_KEY = "delsts";

    //entity key
    public static final String ID_KEY = "id";
    public static final String CREATE_TIME_KEY = "createTime";
    public static final String UPDATE_TIME_KEY = "updateTime";
    public static final String QPIN_KEY = "qpin";
    public static final String JPIN_KEY = "jpin";


}
