package com.feibai.study.demos.beans;

import java.util.Date;

public class Medal {

  private Integer id;
  private Integer tagNo;
  private Integer scope;
  private String name;
  private String params;
  private String coverPath;
  private String fontColor;
  private Long beginAt;
  private Long endAt;
  private Integer version;
  private Date createdAt;
  private Date updatedAt;

  public Integer getId() {
    return id;
  }

  public Medal withId(Integer id) {
    this.setId(id);
    return this;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTagNo() {
    return tagNo;
  }

  public Medal withTagNo(Integer tagNo) {
    this.setTagNo(tagNo);
    return this;
  }

  public void setTagNo(Integer tagNo) {
    this.tagNo = tagNo;
  }

  public Integer getScope() {
    return scope;
  }

  public Medal withScope(Integer scope) {
    this.setScope(scope);
    return this;
  }

  public void setScope(Integer scope) {
    this.scope = scope;
  }

  public String getName() {
    return name;
  }

  public Medal withName(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public String getParams() {
    return params;
  }

  public Medal withParams(String params) {
    this.setParams(params);
    return this;
  }

  public void setParams(String params) {
    this.params = params == null ? null : params.trim();
  }

  public String getCoverPath() {
    return coverPath;
  }

  public Medal withCoverPath(String coverPath) {
    this.setCoverPath(coverPath);
    return this;
  }

  public void setCoverPath(String coverPath) {
    this.coverPath = coverPath == null ? null : coverPath.trim();
  }

  public String getFontColor() {
    return fontColor;
  }

  public Medal withFontColor(String fontColor) {
    this.setFontColor(fontColor);
    return this;
  }

  public void setFontColor(String fontColor) {
    this.fontColor = fontColor == null ? null : fontColor.trim();
  }

  public Long getBeginAt() {
    return beginAt;
  }

  public Medal withBeginAt(Long beginAt) {
    this.setBeginAt(beginAt);
    return this;
  }

  public void setBeginAt(Long beginAt) {
    this.beginAt = beginAt;
  }

  public Long getEndAt() {
    return endAt;
  }

  public Medal withEndAt(Long endAt) {
    this.setEndAt(endAt);
    return this;
  }

  public void setEndAt(Long endAt) {
    this.endAt = endAt;
  }

  public Integer getVersion() {
    return version;
  }

  public Medal withVersion(Integer version) {
    this.setVersion(version);
    return this;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Medal withCreatedAt(Date createdAt) {
    this.setCreatedAt(createdAt);
    return this;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public Medal withUpdatedAt(Date updatedAt) {
    this.setUpdatedAt(updatedAt);
    return this;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
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
    Medal other = (Medal) that;
    return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTagNo() == null ? other.getTagNo() == null : this.getTagNo().equals(other.getTagNo()))
            && (this.getScope() == null ? other.getScope() == null : this.getScope().equals(other.getScope()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getParams() == null ? other.getParams() == null : this.getParams().equals(other.getParams()))
            && (this.getCoverPath() == null ? other.getCoverPath() == null : this.getCoverPath().equals(other.getCoverPath()))
            && (this.getFontColor() == null ? other.getFontColor() == null : this.getFontColor().equals(other.getFontColor()))
            && (this.getBeginAt() == null ? other.getBeginAt() == null : this.getBeginAt().equals(other.getBeginAt()))
            && (this.getEndAt() == null ? other.getEndAt() == null : this.getEndAt().equals(other.getEndAt()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    result = prime * result + ((getTagNo() == null) ? 0 : getTagNo().hashCode());
    result = prime * result + ((getScope() == null) ? 0 : getScope().hashCode());
    result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
    result = prime * result + ((getParams() == null) ? 0 : getParams().hashCode());
    result = prime * result + ((getCoverPath() == null) ? 0 : getCoverPath().hashCode());
    result = prime * result + ((getFontColor() == null) ? 0 : getFontColor().hashCode());
    result = prime * result + ((getBeginAt() == null) ? 0 : getBeginAt().hashCode());
    result = prime * result + ((getEndAt() == null) ? 0 : getEndAt().hashCode());
    result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
    result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
    result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", tagNo=").append(tagNo);
    sb.append(", scope=").append(scope);
    sb.append(", name=").append(name);
    sb.append(", params=").append(params);
    sb.append(", coverPath=").append(coverPath);
    sb.append(", fontColor=").append(fontColor);
    sb.append(", beginAt=").append(beginAt);
    sb.append(", endAt=").append(endAt);
    sb.append(", version=").append(version);
    sb.append(", createdAt=").append(createdAt);
    sb.append(", updatedAt=").append(updatedAt);
    sb.append("]");
    return sb.toString();
  }
}