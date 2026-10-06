package com.example.api;

import org.springframework.web.bind.annotation.*;
import com.example.api.model.Mission;
import java.util.*;


@RestController
@RequestMapping ("/api")

public class MissionApiControler{
    private List<Mission> missions = new ArrayList<>(List.of(
        new Mission(1L, "斯普特尼克一号", "苏联", "1957", "成功"),
        new Mission(2L, "东方号", "苏联", "1961", "成功"),
        new Mission(3L, "阿波罗11号", "美国", "1969", "成功"),
        new Mission(4L, "东方红一号", "中国", "1970", "成功"),
        new Mission(5L, "水手8号", "美国", "1971", "失败"),
        new Mission(6L, "火星7号", "苏联", "1973", "失败"),
        new Mission(7L, "神舟五号", "中国", "2003", "成功")
    ));

    @GetMapping("/welcome")
    public String welcome() {
        return "欢迎访问航天任务档案馆";
    }

    @GetMapping("/Missions")
    public List<Mission> GetMissions(){
        return missions;
    }

    @GetMapping("/Missions/{id}")
    public Object GetMissionById(@PathVariable Long id){
        if (id < 1 || id > missions.size()){
            return "未找到该任务";
        }else{
            return missions.get(id.intValue() - 1);
        }
    }

    @GetMapping("/Missions/search?keyword={keyword}")
    public Object SearchMissions(@RequestParam String keyword){
        List<Mission> result = new ArrayList<>();
        for (Mission m : missions){
            if (m.getCountry().contains(keyword) || m.getName().contains(keyword) || m.getDate().contains(keyword) || m.getResult().contains(keyword)){
                result.add(m);
            }
        }
        if (result.isEmpty()){
            return "未找到相关任务";
        }
        return result;
    }

    @GetMapping("/statistics")
    public Map<String, Object> GetStatics(){
        Map<String, Object> statistics = new HashMap<>();
        statistics.put("任务总数", missions.size());
        statistics.put("成功任务数", missions.stream().filter(m -> m.getResult().equals("成功")).count());
        statistics.put("失败任务数", missions.stream().filter(m -> m.getResult().equals("失败")).count());
        int successCount = 0;
        for (Mission m : missions){
            if(m.getResult().equals("成功")){
                successCount++;
            }
        }
        statistics.put("成功率", String.format("%.2f", (double) successCount / missions.size() * 100) + "%");
        return statistics;
    }

}
