package com.xyc.news.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyc.news.pojo.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*** @author chenwei 登录拦截器，只要login就会到这里来
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //得到用户名和密码，然后去验证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        new ArrayList<>()
                )
        );
    }

    //身份验证成功后，在response的header增加token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();

        //获取用户名
        String userName = user.getUsername();
        //获取user中角色
        String role
                = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        //生成token
        String token = JwtUtils.generatorToken(userName, role);

        response.addHeader("token", token);

        //返回登陆成功信息
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(200);
        try (PrintWriter out = response.getWriter()) {
            Map<String,Object> map = new HashMap<>();
            map.put("code",0);
            map.put("msg","登录成功");
            map.put("data",user);
            ObjectMapper om = new ObjectMapper();
            out.write(om.writeValueAsString(map));
            out.flush();
            out.close();
        }
    }

}
