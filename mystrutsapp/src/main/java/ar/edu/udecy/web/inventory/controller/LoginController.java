package ar.edu.udecy.web.inventory.controller;

        import ar.edu.udecy.web.inventory.config.JwtUtil;
        import ar.edu.udecy.web.inventory.dto.UserDTO;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        @RestController
        public class LoginController {
            @Autowired
            private JwtUtil jwtUtil;


            @PostMapping("login")
            public UserDTO login(
                    @RequestParam("user") String username,
                    @RequestParam("encryptedPass") String encryptedPass) {
                // Validate user credentials
                if (!isValidUser(username, encryptedPass)) {
                    throw new RuntimeException("Invalid username or password");
                }

                // Generate JWT token
                String token = jwtUtil.generateToken(username);

                // Return user details with token
                return new UserDTO(username, encryptedPass, token);
            }

            private boolean isValidUser(String username, String encryptedPass) {
                // Replace with actual user validation logic (e.g., database check)
                return "testUser".equals(username) && "testPass".equals(encryptedPass);
            }
        }