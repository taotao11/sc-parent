package com.cloud.utils.result;


import com.cloud.utils.constant.ResultMsgConsant;

import static com.cloud.utils.constant.ResultMsgConsant.getFailResultMsg;
import static com.cloud.utils.constant.ResultMsgConsant.getSuccessResultMsg;

public class CommonReturnMsgUtil {
    /**
     * 根据判断和操作方式，返回对应的消息，错误，正确
     * @param result
     * @param operatorWays
     * @return
     */
    public static R getCommonReturnMsg(boolean result, ResultMsgConsant.OperatorWays operatorWays){
        if(result){
            return R.ok(getSuccessResultMsg(operatorWays));
        }else{
            return R.ok(getFailResultMsg(operatorWays));
        }
    }

}
