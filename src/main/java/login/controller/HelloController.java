package login.controller;
//import com.sbm.service.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * sbm
 * Created by yadong.zhang on com.sbm.controller
 * User：yadong.zhang
 * Date：2016/10/20
 * Time：18:26
 */
@Controller
public class HelloController {
    @RequestMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age","25");
        model.addAttribute("sex","man");
        model.addAttribute("birth",new Date());
        return "hello";
    }
    @RequestMapping("/hello/{name}/l")
    @ResponseBody
    public String hello2(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age","25");
        model.addAttribute("sex","man");
        model.addAttribute("birth",new Date());
        return "index.html";
    }
    @RequestMapping("/json")
    @ResponseBody
    public Map<String,Object> json(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","Flyat");
        map.put("age","25");
        map.put("sex","man");
        return map;
    }

    @RequestMapping({"/","/index.html"})
    public String index1(){
        return "index";
    }
    @RequestMapping("/login.html")
    public String login1(){
        return "login";
    }
    @RequestMapping("/register.html")
    public String register(){
        return "register";
    }

}
