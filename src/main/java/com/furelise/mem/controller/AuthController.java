package com.furelise.mem.controller;

import com.furelise.mem.model.entity.Mem;
import com.furelise.mem.model.vo.MemVO;
import com.furelise.mem.service.AuthService;
import com.furelise.mem.service.MemService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.furelise.emp.model.Emp;
import com.furelise.emp.model.EmpLoginDTO;
import com.furelise.emp.model.EmpVO;
import com.furelise.emp.controller.EmpAuthService;
import com.furelise.mem.model.dto.MemLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmpAuthService empAuthService;
    
    @Autowired
	private MemService memService;
    
    @GetMapping("/login")
	public String login() {
		return "login";
	}

    @PostMapping("/login-test")
    public String memLoginTst() {
        return "login-test";
    }
    
    @PostMapping("/login/mem")
    public MemVO memLogin(@RequestBody @Validated MemLoginDTO dto, HttpServletRequest req) {
        Mem mem = this.authService.verify(dto, req);
        return new MemVO(mem);
    }

    @GetMapping("/mem/me")
    public MemVO memMe(HttpServletRequest req) {
        Mem mem = (Mem) authService.validate(req, "mem");
        
        return new MemVO(mem);
    }

    @PostMapping("/login/emp")
    public EmpVO empLogin(@RequestBody @Validated EmpLoginDTO dto, HttpServletRequest req) {
        Emp emp = this.empAuthService.verify(dto, req);
        return new EmpVO(emp);
    }

    @GetMapping("/emp/me")
    public EmpVO empMe(HttpServletRequest req) {
        Emp emp = (Emp) authService.validate(req, "emp");
        
        return new EmpVO(emp);
    }
    
    @PostMapping("/google-login")
	public RedirectView googleLogin(@RequestBody String idTokenString, HttpServletRequest req) 
					throws GeneralSecurityException, IOException {
		
	    
	    
		GoogleIdToken idToken = memService.googleVerify(idTokenString);
		
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			
//			for (String claimName : payload.keySet()) {
//			    Object value = payload.get(claimName);
//			    
//			}

			// Print user identifier
			String userId = payload.getSubject();
			

			// Get profile information from payload
			String email = payload.getEmail();
			String name = (String) payload.get("name");
			
			
            
            
			// Use or store profile information
            // Use email to search if this email already has an account
            HttpSession session = req.getSession();
            if (memService.findByMemMail(email) != null) {
            	Mem mem = memService.findByMemMail(email);
            	session.setAttribute("mem", mem);
            	session.setAttribute("google", true);
            	return new RedirectView("/member/info");
            } else {
            	// Save new mem into database
                Mem newMem = new Mem();
                newMem.setMemName(name);
                newMem.setMemMail(email);
    			// Save new mem into session
    			session.setAttribute("newMem", newMem);
    			return new RedirectView("/login-google");
            }
		} else {
			
			return new RedirectView("/login");
		}
	}
    
    @GetMapping("/logout/mem")
    public void memLogout(HttpServletRequest req) {
    	HttpSession session = req.getSession();
		session.invalidate();
        
    }
    
    @GetMapping("/logout/emp")
    public void empLogout(HttpServletRequest req) {
    	HttpSession session = req.getSession();
    	session.invalidate();
    }
    
}
