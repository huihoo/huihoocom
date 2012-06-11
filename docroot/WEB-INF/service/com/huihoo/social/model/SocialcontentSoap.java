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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Baihua Huang
 * @generated
 */
public class SocialcontentSoap implements Serializable {
	public static SocialcontentSoap toSoapModel(Socialcontent model) {
		SocialcontentSoap soapModel = new SocialcontentSoap();

		soapModel.setId(model.getId());
		soapModel.setUserId(model.getUserId());
		soapModel.setPortraitUrl(model.getPortraitUrl());
		soapModel.setScreenName(model.getScreenName());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setType(model.getType());
		soapModel.setContent(model.getContent());
		soapModel.setCreatedAt(model.getCreatedAt());

		return soapModel;
	}

	public static SocialcontentSoap[] toSoapModels(Socialcontent[] models) {
		SocialcontentSoap[] soapModels = new SocialcontentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialcontentSoap[][] toSoapModels(Socialcontent[][] models) {
		SocialcontentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SocialcontentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SocialcontentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialcontentSoap[] toSoapModels(List<Socialcontent> models) {
		List<SocialcontentSoap> soapModels = new ArrayList<SocialcontentSoap>(models.size());

		for (Socialcontent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SocialcontentSoap[soapModels.size()]);
	}

	public SocialcontentSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
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

	private long _id;
	private long _userId;
	private String _portraitUrl;
	private String _screenName;
	private long _companyId;
	private long _groupId;
	private int _type;
	private String _content;
	private Date _createdAt;
}