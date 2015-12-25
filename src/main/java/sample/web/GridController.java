package sample.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@EnableAutoConfiguration
public class GridController {

	@RequestMapping("/dataGrid")
	public String input() {
	    return "dataGrid"; // input form
	}

	@Autowired
	UserIndoRepository repository;

	@RequestMapping("/getGrid")
	public String getGrid(Model model) {
		Iterable<UserInfo> list = repository.findAll();
	      model.addAttribute("glidList", list);
	      return "dataGrid";
	}

	@RequestMapping(value="/postGrid", method=RequestMethod.POST)
	public String insertUserInfo(Model model,
			@RequestParam("lastName") String lastName,
		    @RequestParam("firstName") String firstName){
		UserInfo info = new UserInfo(firstName,lastName);
		repository.saveAndFlush(info);

		return "dataGrid";
	}


}
