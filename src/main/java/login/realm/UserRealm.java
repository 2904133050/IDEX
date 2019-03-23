package login.realm;

import login.entity.User;
import login.mapper.UserMapper;
import login.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
@Override
protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
// 这里做权限控制
return null;
}
@Override
protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
// 这里做登录控制
    AuthenticationInfo info;
    String username = (String) token.getPrincipal();

    User user = userService.selectUserByName(username);

    if(user!=null){

        info = new SimpleAuthenticationInfo(user,user.getPassword(),"yimai");
        return info;
    }
        return null;
}
}