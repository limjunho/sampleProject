package com.motionblue.mi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.motionblue.mi.login.NoAuthCheck;
import com.motionblue.mi.login.NoLoginCheck;


@Controller
public class SignController {
     private static final Logger logger = LoggerFactory.getLogger(SignController.class);

	 //@Autowired
     //private PasswordEncoder passwordEncoder;

     @RequestMapping("/signin")
 	 @NoLoginCheck
 	 @NoAuthCheck
     public String signin(@RequestParam(value="error", required=false) String error, Model model) {

          model.addAttribute("error", error);

          // Sha 암호값을 보기 위한 테스트용.
          //String guest_password = passwordEncoder.encode("asdf");
          //String admin_password = passwordEncoder.encode("admin");

          //logger.info(guest_password + "//" + admin_password);

          return "signin";
     }

     @PreAuthorize("authenticated")
     @RequestMapping("/mypage")
     public String mypage(Model model) {

          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          model.addAttribute("user_name", auth.getName());

          return "mypage";
     }


     @RequestMapping("/denied")
     public String denied() {
          return "denied";
     }


}
