package org.ikane.microservicedemopac4j;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pac4j.core.config.Config;
import org.pac4j.core.context.J2EContext;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.RequiresHttpAction;
import org.pac4j.core.profile.ProfileManager;
import org.pac4j.core.profile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppControllers {

	@Autowired
	private Config config;
	
	@RequestMapping(value={"/"})
	public String root(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws RequiresHttpAction {
		return index(request, response, map);
	}
	
	@RequestMapping("/index.html")
	public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map)
			throws RequiresHttpAction {
		final WebContext context = new J2EContext(request, response);
		map.put("profile", getStringProfile(context));
		return "index";
	}
	
	
	protected String protectedIndex(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        final WebContext context = new J2EContext(request, response);
        map.put("profile", getStringProfile(context));
        return "protectedIndex";
    }
    
	@RequestMapping("/cas/index.html")
    public String cas(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        return protectedIndex(request, response, map);
    }

	private UserProfile getProfile(WebContext context) {
		final ProfileManager<UserProfile> manager = new ProfileManager<UserProfile>(context);
		return manager.get(true);
	}

	private String getStringProfile(WebContext context) {
		
		final UserProfile profile = getProfile(context);
		if (profile == null) {
			return "";
		} else {
			return profile.toString();
		}
	}
}
