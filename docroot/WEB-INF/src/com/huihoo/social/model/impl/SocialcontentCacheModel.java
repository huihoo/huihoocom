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

package com.huihoo.social.model.impl;

import com.huihoo.social.model.Socialcontent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Socialcontent in entity cache.
 *
 * @author Baihua Huang
 * @see Socialcontent
 * @generated
 */
public class SocialcontentCacheModel implements CacheModel<Socialcontent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{id=");
		sb.append(id);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", portraitUrl=");
		sb.append(portraitUrl);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", type=");
		sb.append(type);
		sb.append(", content=");
		sb.append(content);
		sb.append(", createdAt=");
		sb.append(createdAt);
		sb.append("}");

		return sb.toString();
	}

	public Socialcontent toEntityModel() {
		SocialcontentImpl socialcontentImpl = new SocialcontentImpl();

		socialcontentImpl.setId(id);
		socialcontentImpl.setUserId(userId);

		if (portraitUrl == null) {
			socialcontentImpl.setPortraitUrl(StringPool.BLANK);
		}
		else {
			socialcontentImpl.setPortraitUrl(portraitUrl);
		}

		if (screenName == null) {
			socialcontentImpl.setScreenName(StringPool.BLANK);
		}
		else {
			socialcontentImpl.setScreenName(screenName);
		}

		socialcontentImpl.setCompanyId(companyId);
		socialcontentImpl.setGroupId(groupId);
		socialcontentImpl.setType(type);

		if (content == null) {
			socialcontentImpl.setContent(StringPool.BLANK);
		}
		else {
			socialcontentImpl.setContent(content);
		}

		if (createdAt == Long.MIN_VALUE) {
			socialcontentImpl.setCreatedAt(null);
		}
		else {
			socialcontentImpl.setCreatedAt(new Date(createdAt));
		}

		socialcontentImpl.resetOriginalValues();

		return socialcontentImpl;
	}

	public long id;
	public long userId;
	public String portraitUrl;
	public String screenName;
	public long companyId;
	public long groupId;
	public int type;
	public String content;
	public long createdAt;
}