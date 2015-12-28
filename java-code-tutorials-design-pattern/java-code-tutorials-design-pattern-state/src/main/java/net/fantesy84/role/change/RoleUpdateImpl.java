/**
 * 项目名: java-code-tutorials-design-pattern-state
 * 包名:  net.fantesy84.role.change
 * 文件名: RoleUpdateImpl.java
 * Copy Right © 2015 Andronicus Ge
 * 时间: 2015年12月28日
 */
package net.fantesy84.role.change;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fantesy84.domain.Role;
import net.fantesy84.domain.User;

/**
 * @author Andronicus
 * @since 2015年12月28日
 */
public class RoleUpdateImpl implements RoleUpdater {
	private static final Logger logger = LoggerFactory.getLogger(RoleUpdateImpl.class);
	private User user;
	
	/**
	 * @param user
	 */
	public RoleUpdateImpl(User user) {
		super();
		this.user = user;
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.role.change.RoleUpdater#addRole(net.fantesy84.domain.Role)
	 */
	@Override
	public void addRole(Role role) {
		logger.debug("Add an Role[CODE:{}] to User[ID:{}]", role.getRoleCode(), user.getId());
		List<Role> roles = user.getRoles();
		if (roles == null) {
			roles = new ArrayList<>();
		}
		roles.add(role);
		user.setRoles(roles);
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.role.change.RoleUpdater#removeRole(net.fantesy84.domain.Role)
	 */
	@Override
	public void removeRole(Role role) {
		List<Role> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			int index = roles.indexOf(role);
			roles.remove(index);
		}
	}

	/* (non-Javadoc)
	 * @see net.fantesy84.role.change.RoleUpdater#getUserRoles()
	 */
	@Override
	public List<Role> getUserRoles() {
		return user.getRoles();
	}

}
