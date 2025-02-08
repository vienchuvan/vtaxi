package controllers.thaydoi;

import controllers.tools.ResponseMessage;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: PC
 * Date: 6/7/22
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class VimassManagerSecssion {
    private static HashMap<String, String> managerKey;

    private static void init()
    {
        if(managerKey == null)
        {
            managerKey = new HashMap<>();
        }
    }

    public static ResponseMessage getSecssion(String key)
    {
        init();
        String kq = null;
        if(managerKey.containsKey(key))
        {
            kq = managerKey.get(key);
        }
        if(kq == null)
        {
            return ErrorCodeWeb.getObjectMessageResult(ErrorCodeWeb.FALSE, ErrorCodeWeb.MES_FALSE);
        }
        else
        {
            ResponseMessage result = ErrorCodeWeb.getObjectMessageResult(ErrorCodeWeb.SUCCESS, ErrorCodeWeb.MES_SUCCESS);
            result.setResult(kq);

            return result;
        }
    }
    public static ResponseMessage saveSecssion(String key, String value)
    {
        init();
        managerKey.put(key, value);
        return ErrorCodeWeb.getObjectMessageResult(ErrorCodeWeb.SUCCESS, ErrorCodeWeb.MES_SUCCESS);

    }

    public static ResponseMessage deleteSecssion(String key)
    {
        init();
        if(managerKey.containsKey(key))
        {
            managerKey.remove(key);
            return ErrorCodeWeb.getObjectMessageResult(ErrorCodeWeb.SUCCESS, ErrorCodeWeb.MES_SUCCESS);
        }
        return ErrorCodeWeb.getObjectMessageResult(ErrorCodeWeb.FALSE, ErrorCodeWeb.MES_FALSE);
    }

}
