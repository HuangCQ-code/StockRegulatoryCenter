package com.stock.entity;

import java.io.Serializable;

public class SysOperationLog implements Serializable {
    private Long id;

    private String username;

    private String ip;

    private String url;

    private String method;

    private String parameters;

    private Long executionTime;

    private Long visitMillis;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public Long getVisitMillis() {
        return visitMillis;
    }

    public void setVisitMillis(Long visitMillis) {
        this.visitMillis = visitMillis;
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
        SysOperationLog other = (SysOperationLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getParameters() == null ? other.getParameters() == null : this.getParameters().equals(other.getParameters()))
            && (this.getExecutionTime() == null ? other.getExecutionTime() == null : this.getExecutionTime().equals(other.getExecutionTime()))
            && (this.getVisitMillis() == null ? other.getVisitMillis() == null : this.getVisitMillis().equals(other.getVisitMillis()))
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
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getParameters() == null) ? 0 : getParameters().hashCode());
        result = prime * result + ((getExecutionTime() == null) ? 0 : getExecutionTime().hashCode());
        result = prime * result + ((getVisitMillis() == null) ? 0 : getVisitMillis().hashCode());
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
        sb.append(", ip=").append(ip);
        sb.append(", url=").append(url);
        sb.append(", method=").append(method);
        sb.append(", parameters=").append(parameters);
        sb.append(", executionTime=").append(executionTime);
        sb.append(", visitMillis=").append(visitMillis);
        sb.append(", createMillis=").append(createMillis);
        sb.append(", lastUpdateMillis=").append(lastUpdateMillis);
        sb.append(", extra=").append(extra);
        sb.append("]");
        return sb.toString();
    }
}