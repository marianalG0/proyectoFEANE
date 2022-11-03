package com.spring.boot.newapp.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, estatus from usuarios where username=?")
		.authoritiesByUsernameQuery("select u.username, p.perfil from usuarioperfil up " + 
		"inner join usuarios u on u.id = up.idUsuario " + 
		"inner join perfiles p on p.id = up.idPerfil " + 
		"where u.username = ?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	// Los recursos estáticos no requieren autenticación
				.antMatchers("/bootstrap/**",
						"/css/**","/js/**", 
						"/images/**", 
						"/tinymce/**",
						"/search",
						"/logos/**").permitAll()
	// Las vistas públicas no requieren autenticación
				.antMatchers("/", 
						"/signup",
						"/bcrypt/**", 
						"/menu/view/**").permitAll()
	// Asignar permisos a URLs por ROLES PARA QUE QUE DEPENDIENDO DEL ROL QUE TENGAS ES COMO VAS ACCEDER A LAS SECCIONES
				.antMatchers("/menu/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
				.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
				.antMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/acerca").hasAnyAuthority("USUARIO","SUPERVISOR","ADMINISTRADOR")
	// Todas las demás URLs de la Aplicación requieren autenticación
				.anyRequest().authenticated()
	// El formulario de Login no requiere autenticacion
				.and().formLogin().loginPage("/login").permitAll();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}
	
}
