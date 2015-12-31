package net.fantesy84.sys.domain;

import java.util.Date;

public class WebSocketHistory extends BaseDTO {
	private static final long serialVersionUID = 9116171713668606150L;
	private Long id;
    private Long userId;
    private Date createTime;
    private Boolean delMarker;
    private String contents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelMarker() {
        return delMarker;
    }

    public void setDelMarker(Boolean delMarker) {
        this.delMarker = delMarker;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }
}