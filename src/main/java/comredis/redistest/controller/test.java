package comredis.redistest.controller;

import comredis.redistest.entity.UserEntity;
import comredis.redistest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class test {

    @Autowired
    private UserService userService;

    @RequestMapping("home")
    String home(HttpServletRequest request) {
        String userName = "Jef";
        int count = 100;
        return "欢迎您" + userName + "，这是您的第 " + count + " 次登录";
    }

    @RequestMapping("index")
    public String add() {
        UserEntity entity = new UserEntity();
        entity.setUserId("000002");
        entity.setEmpCode("653005");
        entity.setEmpName("ziLong,wen");
        entity.setRole("Java Development Engineer");
        entity.setTitle("AM");
        boolean isTrue = userService.addUserEntity(entity);
        System.out.println(isTrue);
        return "存入成功！";
    }

    @RequestMapping("query")
    public String get() {
        UserEntity entity = new UserEntity();
        entity.setUserId("000001");
        UserEntity userEntity = userService.queryUserEntityByUserId(entity);
        System.out.println("UserId:" + userEntity.getUserId());
        System.out.println("EmpCode:" + userEntity.getEmpCode());
        System.out.println("EmpName:" + userEntity.getEmpName());
        System.out.println("Role:" + userEntity.getRole());
        System.out.println("Title:" + userEntity.getTitle());
        return "UserId:" + userEntity.getUserId() + "\r\n" + "EmpCode:" + userEntity.getEmpCode() + "\r\n"
                + "EmpName:" + userEntity.getEmpName() + "\r\n" + "Role:" + userEntity.getRole() + "\r\n"
                + "Title:" + userEntity.getTitle();
    }

    @RequestMapping("all")
    public String getAll(){
        Map<String,UserEntity> user = userService.queryAll();
        String temp = "";
        for (String a:user.keySet()) {
            temp += "UserId:" + user.get(a).getUserId() + "  " + "EmpCode:" + user.get(a).getEmpCode() + "  "
                    + "EmpName:" + user.get(a).getEmpName() + "  " + "Role:" + user.get(a).getRole() + "  "
                    + "Title:" + user.get(a).getTitle() + "  ";
        }
        return temp;
    }
}
