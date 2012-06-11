/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.huihoo.social.model;

import com.huihoo.social.service.SocialcontentLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Baihua Huang
 */
public class SocialcontentClp extends BaseModelImpl<Socialcontent>
	implements Socialcontent {
	public SocialcontentClp() {
	}

	public Class<?> getModelClass() {
		return Socialcontent.class;
	}

	public String getModelClassName() {
		return Socialcontent.class.getName();
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_id);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getPortraitUrl() {
		return _portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		_portraitUrl = portraitUrl;
	}

	public String getScreenName() {
		return _screenName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public Date getCreatedAt() {
		return _createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		_createdAt = createdAt;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SocialcontentLocalServiceUtil.addSocialcontent(this);
		}
		else {
			SocialcontentLocalServiceUtil.updateSocialcontent(this);
		}
	}

	@Override
	public Socialcontent toEscapedModel() {
		return (Socialcontent)Proxy.newProxyInstance(Socialcontent.class.getClassLoader(),
			new Class[] { Socialcontent.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SocialcontentClp clone = new SocialcontentClp();

		clone.setId(getId());
		clone.setUserId(getUserId());
		clone.setPortraitUrl(getPortraitUrl());
		clone.setScreenName(getScreenName());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setType(getType());
		clone.setContent(getContent());
		clone.setCreatedAt(getCreatedAt());

		return clone;
	}

	public int compareTo(Socialcontent socialcontent) {
		int value = 0;

		value = DateUtil.compareTo(getCreatedAt(), socialcontent.getCreatedAt());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SocialcontentClp socialcontent = null;

		try {
			socialcontent = (SocialcontentClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = socialcontent.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", portraitUrl=");
		sb.append(getPortraitUrl());
		sb.append(", screenName=");
		sb.append(getScreenName());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", createdAt=");
		sb.append(getCreatedAt());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.huihoo.social.model.Socialcontent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portraitUrl</column-name><column-value><![CDATA[");
		sb.append(getPortraitUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>screenName</column-name><column-value><![CDATA[");
		sb.append(getScreenName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdAt</column-name><column-value><![CDATA[");
		sb.append(getCreatedAt());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _id;
	private long _userId;
	private String _userUuid;
	private String _portraitUrl;
	private String _screenName;
	private long _companyId;
	private long _groupId;
	private int _type;
	private String _content;
	private Date _createdAt;
}