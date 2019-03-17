package hyman.springbootdemo.entity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SuperEntity extends HashMap implements Map{
    private static final long serialVersionUID = 1L;
    Map map = null;
    HttpServletRequest request;

    public SuperEntity(HttpServletRequest request){
        this.request = request;
        Map properties = request.getParameterMap();
        Map returnMap = new HashMap();

        Iterator entries = properties.entrySet().iterator();
        Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()){
            entry = (Entry) entries.next();
            name = (String) entry.getKey();
            Object valueobj = entry.getValue();

            if(null == valueobj){
                value = "";
            }else if(valueobj instanceof String[]){
                String[] values = (String[])valueobj;
                for(int i=0; i<values.length; i++){
                    value = values[i]+",";
                }
                value = value.substring(0,value.length()-1);
            }else {
                value = valueobj.toString();
            }
            returnMap.put(name,value);
        }

        if(returnMap.containsKey("page") && returnMap.containsKey("limit")){
            int page = Integer.parseInt((String)returnMap.get("page"));
            int limit = Integer.parseInt((String)returnMap.get("limit"));
            returnMap.put("start",(page-1)*limit);
            returnMap.put("end",page*limit);
        }
        map = returnMap;
    }

    public SuperEntity(){
        map = new HashMap();
    }
}
