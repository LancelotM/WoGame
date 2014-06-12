package com.unicom.wogame.web;

import com.unicom.wogame.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员管理用户的Controller.
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/index")
public class GameCategoryController {

	@Autowired
	private GameService accountService;

	/*@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<User> userList = accountService.getAllUser();
		model.addAttribute("userList", userList);
		return "account/userList";
	}

	@RequestMapping(value = "showRole/{id}", method = RequestMethod.GET)
	public String add(@PathVariable("id") Long id, Model model) {
		User user = accountService.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("allRoles", buildPermTree(user.getRoles()));
		return "account/showRole";
	}

	private List<Role> buildPermTree(List<Role> roles) {
		List<Role> allRoles = accountService.getAllRole();
		for (Role r : allRoles) {
			checkRight(r, roles);
		}
		return allRoles;
	}

	private void checkRight(Role r, List<Role> roles) {
		if (roles.contains(r)) {
			r.setCheck(true);
		}
	}

	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String updateAppFuns(@Valid @ModelAttribute("user") User user,
			@RequestParam(value = "permList") List<Long> checkedPermList,
			RedirectAttributes redirectAttributes) {
		user.getRoles().clear();
		if (checkedPermList != null && checkedPermList.size() > 0) {
			for (Long roleId : checkedPermList) {
				Role role = new Role(roleId);
				user.getRoles().add(role);
			}
		}
		accountService.saveUser(user);
		redirectAttributes.addFlashAttribute("message", "【" + user.getName()
				+ "】角色权限更新成功");
		return "redirect:/userMgr/showRole/" + user.getId();
	}

    @RequestMapping(value = "updatePassword", method = RequestMethod.GET)
    public String updatePassword() {
        return "account/updatePassword";
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public String updatePassword(@RequestParam(value = "srcPassword") String srcPassword,
                                 @RequestParam(value = "srcPassword") String newPassword,
                                 RedirectAttributes redirectAttributes) {

        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();

        User muser = accountService.getUser(user.getId());
        if (entryptPassword(srcPassword, muser.getSalt()).equals(muser.getPassword())) {
            muser.setPlainPassword(newPassword);
            accountService.updateUser(muser);
        }

        redirectAttributes.addFlashAttribute("message", "【" + user.getName()
                + "】密码更新成功");
        return "redirect:/account/updatePassword";
    }

    private String entryptPassword(String srcPassword, String salt) {
//        byte[] salt = Digests.generateSalt(SALT_SIZE);
//        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(srcPassword.getBytes(), Encodes.decodeHex(salt), 1024);
        return (Encodes.encodeHex(hashPassword));
    }

	@ModelAttribute
	public void getUser(
			@RequestParam(value = "id", defaultValue = "-1") Long id,
			Model model) {
		if (id != -1) {
			model.addAttribute("user", accountService.getUser(id));
		}
	}*/
}
