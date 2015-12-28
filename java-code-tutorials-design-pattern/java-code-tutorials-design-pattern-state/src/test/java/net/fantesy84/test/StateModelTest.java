/**
 * 项目名: java-code-tutorials-design-pattern-state
 * 包名:  net.fantesy84.test
 * 文件名: StateModelTest.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.fantesy84.domain.Role;
import net.fantesy84.domain.User;
import net.fantesy84.role.change.RoleUpdateImpl;
import net.fantesy84.role.change.RoleUpdater;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class StateModelTest {
	private User user;
	private RoleUpdater updater;
	@Before
	public void init(){
		user = new User();
		user.setId(1);
		Role r1 = new Role(1,"ADMIN");
		Role r2 = new Role(2,"NORMAL");
		Role r3 = new Role(3,"GUEST");
		List<Role> roles = new ArrayList<>();
		roles.add(r1);
		roles.add(r2);
		roles.add(r3);
		user.setRoles(roles);
		updater = new RoleUpdateImpl(user);
	}
	@Test
	public void test1(){
		System.out.println(user.getRoles());
		Role role = new Role(4, "SUPER_ADMIN");
		updater.addRole(role);
	}
}
