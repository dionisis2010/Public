package ru.dedateam.innorumors.configs;


import javax.sql.DataSource;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
    DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select nickName, password from Users " +
//                                "where username=?")
//                .authoritiesByUsernameQuery(
//                        "select nickName, authority from UserAuthorities " +
//                                "where username=?");
//    }

}
