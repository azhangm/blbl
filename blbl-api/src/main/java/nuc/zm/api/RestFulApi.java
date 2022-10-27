package nuc.zm.api;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zm
 * @date 2022/10/23
 */

@RestController
public class RestFulApi {
    private final Map<Integer,Map<String ,Object> > dataMap;

    public RestFulApi() {
        dataMap = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id", i);
            stringObjectHashMap.put("name", "name" + i);
            dataMap.put(i,stringObjectHashMap);
        }
    }

    @GetMapping("/objects/{id}")
    public Map query(@PathVariable Integer id) {
        return dataMap.get(id);
    }

    @DeleteMapping("/objects/{id}")
    public String delete(@PathVariable Integer id) {
        dataMap.remove(id);
        return "delete success";
    }

//    REQUEST BODY spring boot 会将请求参数 进行 一个自动的封装 以json 传输进来
    @PostMapping("/objects")
    public String postData(@RequestBody Map<String,Object> map) {
        Integer[] integers = dataMap.keySet().toArray(new Integer[0]);
        Arrays.sort(integers);
        Integer ids = integers.length - 1;
        dataMap.put(integers[ids] + 1,map);
        System.out.println(dataMap.get(ids).get("name"));
        return  "post success";
    }

//    不违反 幂等性原则 先查 id  有了新增 没了 更新
    @PutMapping("/objects")
    public String putData(@RequestBody Map<String,Object> map) {
        Integer id = (Integer) map.get("id");
        Map<String, Object> stringObjectMap = dataMap.get(id);
        if (stringObjectMap == null) {
            Integer[] integers = dataMap.keySet().toArray(new Integer[0]);
            int ids = integers.length - 1;
            Arrays.sort(integers);
            dataMap.put(integers[ids],map);
        }else {
            dataMap.put(id,map);
        }
        return "put success";
    }


}
