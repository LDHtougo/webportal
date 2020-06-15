package jp.ac.hcs.s3a208.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalcController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
