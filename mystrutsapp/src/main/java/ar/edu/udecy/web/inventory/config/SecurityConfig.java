package ar.edu.udecy.web.inventory.config;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.http.SessionCreationPolicy;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
        @Configuration
        class SecurityConfig {

            @Autowired
            JwtAuthorizationFilter jwtAuthorizationFilter;

            public static final String LOGIN_URL = "/login";
            public static final String GREETINGS_URL = "/greetings";

            @Bean
            public SecurityFilterChain configure(HttpSecurity http) throws Exception {
                http
                        .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/login").permitAll() // Permitir login sin autenticación
                                .requestMatchers("/productos").authenticated() // Requiere autenticación
                                .anyRequest().authenticated()) // Requerir autenticación para otros endpoints
                        .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
            }
        }