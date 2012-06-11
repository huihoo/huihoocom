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

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Socialcontent}.
 * </p>
 *
 * @author    Baihua Huang
 * @see       Socialcontent
 * @generated
 */
public class SocialcontentWrapper implements Socialcontent,
	ModelWrapper<Socialcontent> {
	public SocialcontentWrapper(Socialcontent socialcontent) {
		_socialcontent = socialcontent;
	}

	public Class<?> getModelClass() {
		return Socialcontent.class;
	}

	public String getModelClassName() {
		return Socialcontent.class.getName();
	}

	/**
	* Returns the primary key of this socialcontent.
	*
	* @return the primary key of this socialcontent
	*/
	public long getPrimaryKey() {
		return _socialcontent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this socialcontent.
	*
	* @param primaryKey the primary key of this socialcontent
	*/
	public void setPrimaryKey(long primaryKey) {
		_socialcontent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this socialcontent.
	*
	* @return the ID of this socialcontent
	*/
	public long getId() {
		return _socialcontent.getId();
	}

	/**
	* Sets the ID of this socialcontent.
	*
	* @param id the ID of this socialcontent
	*/
	public void setId(long id) {
		_socialcontent.setId(id);
	}

	/**
	* Returns the user ID of this socialcontent.
	*
	* @return the user ID of this socialcontent
	*/
	public long getUserId() {
		return _socialcontent.getUserId();
	}

	/**
	* Sets the user ID of this socialcontent.
	*
	* @param userId the user ID of this socialcontent
	*/
	public void setUserId(long userId) {
		_socialcontent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this socialcontent.
	*
	* @return the user uuid of this socialcontent
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontent.getUserUuid();
	}

	/**
	* Sets the user uuid of this socialcontent.
	*
	* @param userUuid the user uuid of this socialcontent
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_socialcontent.setUserUuid(userUuid);
	}

	/**
	* Returns the portrait url of this socialcontent.
	*
	* @return the portrait url of this socialcontent
	*/
	public java.lang.String getPortraitUrl() {
		return _socialcontent.getPortraitUrl();
	}

	/**
	* Sets the portrait url of this socialcontent.
	*
	* @param portraitUrl the portrait url of this socialcontent
	*/
	public void setPortraitUrl(java.lang.String portraitUrl) {
		_socialcontent.setPortraitUrl(portraitUrl);
	}

	/**
	* Returns the screen name of this socialcontent.
	*
	* @return the screen name of this socialcontent
	*/
	public java.lang.String getScreenName() {
		return _socialcontent.getScreenName();
	}

	/**
	* Sets the screen name of this socialcontent.
	*
	* @param screenName the screen name of this socialcontent
	*/
	public void setScreenName(java.lang.String screenName) {
		_socialcontent.setScreenName(screenName);
	}

	/**
	* Returns the company ID of this socialcontent.
	*
	* @return the company ID of this socialcontent
	*/
	public long getCompanyId() {
		return _socialcontent.getCompanyId();
	}

	/**
	* Sets the company ID of this socialcontent.
	*
	* @param companyId the company ID of this socialcontent
	*/
	public void setCompanyId(long companyId) {
		_socialcontent.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this socialcontent.
	*
	* @return the group ID of this socialcontent
	*/
	public long getGroupId() {
		return _socialcontent.getGroupId();
	}

	/**
	* Sets the group ID of this socialcontent.
	*
	* @param groupId the group ID of this socialcontent
	*/
	public void setGroupId(long groupId) {
		_socialcontent.setGroupId(groupId);
	}

	/**
	* Returns the type of this socialcontent.
	*
	* @return the type of this socialcontent
	*/
	public int getType() {
		return _socialcontent.getType();
	}

	/**
	* Sets the type of this socialcontent.
	*
	* @param type the type of this socialcontent
	*/
	public void setType(int type) {
		_socialcontent.setType(type);
	}

	/**
	* Returns the content of this socialcontent.
	*
	* @return the content of this socialcontent
	*/
	public java.lang.String getContent() {
		return _socialcontent.getContent();
	}

	/**
	* Sets the content of this socialcontent.
	*
	* @param content the content of this socialcontent
	*/
	public void setContent(java.lang.String content) {
		_socialcontent.setContent(content);
	}

	/**
	* Returns the created at of this socialcontent.
	*
	* @return the created at of this socialcontent
	*/
	public java.util.Date getCreatedAt() {
		return _socialcontent.getCreatedAt();
	}

	/**
	* Sets the created at of this socialcontent.
	*
	* @param createdAt the created at of this socialcontent
	*/
	public void setCreatedAt(java.util.Date createdAt) {
		_socialcontent.setCreatedAt(createdAt);
	}

	public boolean isNew() {
		return _socialcontent.isNew();
	}

	public void setNew(boolean n) {
		_socialcontent.setNew(n);
	}

	public boolean isCachedModel() {
		return _socialcontent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_socialcontent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _socialcontent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _socialcontent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_socialcontent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _socialcontent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_socialcontent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SocialcontentWrapper((Socialcontent)_socialcontent.clone());
	}

	public int compareTo(com.huihoo.social.model.Socialcontent socialcontent) {
		return _socialcontent.compareTo(socialcontent);
	}

	@Override
	public int hashCode() {
		return _socialcontent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.huihoo.social.model.Socialcontent> toCacheModel() {
		return _socialcontent.toCacheModel();
	}

	public com.huihoo.social.model.Socialcontent toEscapedModel() {
		return new SocialcontentWrapper(_socialcontent.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _socialcontent.toString();
	}

	public java.lang.String toXmlString() {
		return _socialcontent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_socialcontent.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Socialcontent getWrappedSocialcontent() {
		return _socialcontent;
	}

	public Socialcontent getWrappedModel() {
		return _socialcontent;
	}

	public void resetOriginalValues() {
		_socialcontent.resetOriginalValues();
	}

	private Socialcontent _socialcontent;
}