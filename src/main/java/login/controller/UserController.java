package login.controller;

import com.alibaba.fastjson.JSON;
import login.entity.User;
import login.service.UserService;
import login.util.JsonUtils;
import login.validate.SendMsg_webchinese;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import javax.sql.rowset.spi.SyncResolver;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @PostMapping("/user/login")
    public String loginMain(@RequestParam("userName") String username, @RequestParam("userPsw") String password,
                            Map map, Model model, HttpSession session) {
       //创建主体对象subject
        Subject sccurity = SecurityUtils.getSubject();
        //创建用户密码认证机制对象
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            //shiro验证登录
           sccurity.login(token);

           //得到sccurity中存放的用户信息
         User user = (User)sccurity.getPrincipal();
         session.setAttribute("USER_NAME",user.getUser_name());
         session.setAttribute("SESSION_USER",user);


            return "redirect:/index.html";
        }catch(Exception e){

            model.addAttribute("msg","登录失败!");
            return "login";
        }

    }

   //注册
    @PostMapping("/user/register")
    @ResponseBody
    public String registerMain(@Param("user")User user, HttpSession session) {
        System.out.println(user);
        User reslutUser = userService.selectUserByName(user.getUser_name());
        System.out.println(reslutUser);
        Map map = new HashMap();
        if (reslutUser==null) {
            //定义注册是否的成功标识，调用注册方式
            int flog = userService.createUser(user);

            if(flog>0){
                //注册成功，再次查询，获取User给Session
                reslutUser = userService.selectUserByNP(user.getUser_name(),user.getPassword());
                session.setAttribute("SESSION_USER",reslutUser);
                map.put("tishi",2);
                return JsonUtils.objectToJson(map);
            }else{
                map.put("tishi",1);
                map.put("msg","注册失败，请重新注册！");
                return JsonUtils.objectToJson(map);
            }

        } else {
//账号或者密码错误，返回主页面
            map.put("tishi",0);
            map.put("msg","该账号已被注册，请重新注册！");
            return JsonUtils.objectToJson(map);

        }
    }

    //验证
    @PostMapping("/user/validate")
    @ResponseBody
    public String validateMain(@Param("phone")String phone) {
        System.out.println(phone);
        //获取验证码
      //  SendMsg_webchinese ms = new SendMsg_webchinese();
        String random="";
        try {
            random = SendMsg_webchinese.validate(phone);
        }catch (Exception e){
            e.printStackTrace();
        }
       Map map = new HashMap();
        map.put("random",random);
        return JsonUtils.objectToJson(map);
    }
}
