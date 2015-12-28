/**
 * 项目名: java-code-tutorials-design-pattern-state
 * 包名:  net.fantesy84.role.change
 * 文件名: RoleUpdater.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.role.change;

import java.util.List;

import net.fantesy84.domain.Role;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public interface RoleUpdater {
	void addRole(Role role);
	void removeRole(Role role);
	List<Role> getUserRoles();
}
