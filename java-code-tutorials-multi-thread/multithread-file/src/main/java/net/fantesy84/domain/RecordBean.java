/**
 * Created: 2017-01-04
 * ©2015-2017 junjie.ge. All rights reserved.
 */
package net.fantesy84.domain;

import java.io.Serializable;

/**
 * @author junjie.ge
 *
 */
public class RecordBean implements Serializable {
	private static final long serialVersionUID = 4760453770924881203L;
	private Integer id;
	private String name;
	private String type;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
