package com.stock.entity;

import java.io.Serializable;

public class Manager implements Serializable {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private Integer status;

    private Integer errNum;

    private Long createMillis;

    private Long lastUpdateMillis;

    private String extra;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public Long getCreateMillis() {
        return createMillis;
    }

    public void setCreateMillis(Long createMillis) {
        this.createMillis = createMillis;
    }

    public Long getLastUpdateMillis() {
        return lastUpdateMillis;
    }

    public void setLastUpdateMillis(Long lastUpdateMillis) {
        this.lastUpdateMillis = lastUpdateMillis;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Manager other = (Manager) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getErrNum() == null ? other.getErrNum() == null : this.getErrNum().equals(other.getErrNum()))
            && (this.getCreateMillis() == null ? other.getCreateMillis() == null : this.getCreateMillis().equals(other.getCreateMillis()))
            && (this.getLastUpdateMillis() == null ? other.getLastUpdateMillis() == null : this.getLastUpdateMillis().equals(other.getLastUpdateMillis()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getErrNum() == null) ? 0 : getErrNum().hashCode());
        result = prime * result + ((getCreateMillis() == null) ? 0 : getCreateMillis().hashCode());
        result = prime * result + ((getLastUpdateMillis() == null) ? 0 : getLastUpdateMillis().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", status=").append(status);
        sb.append(", errNum=").append(errNum);
        sb.append(", createMillis=").append(createMillis);
        sb.append(", lastUpdateMillis=").append(lastUpdateMillis);
        sb.append(", extra=").append(extra);
        sb.append("]");
        return sb.toString();
    }
}