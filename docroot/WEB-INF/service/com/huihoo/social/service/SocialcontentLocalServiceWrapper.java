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

package com.huihoo.social.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SocialcontentLocalService}.
 * </p>
 *
 * @author    Baihua Huang
 * @see       SocialcontentLocalService
 * @generated
 */
public class SocialcontentLocalServiceWrapper
	implements SocialcontentLocalService,
		ServiceWrapper<SocialcontentLocalService> {
	public SocialcontentLocalServiceWrapper(
		SocialcontentLocalService socialcontentLocalService) {
		_socialcontentLocalService = socialcontentLocalService;
	}

	/**
	* Adds the socialcontent to the database. Also notifies the appropriate model listeners.
	*
	* @param socialcontent the socialcontent
	* @return the socialcontent that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent addSocialcontent(
		com.huihoo.social.model.Socialcontent socialcontent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.addSocialcontent(socialcontent);
	}

	/**
	* Creates a new socialcontent with the primary key. Does not add the socialcontent to the database.
	*
	* @param id the primary key for the new socialcontent
	* @return the new socialcontent
	*/
	public com.huihoo.social.model.Socialcontent createSocialcontent(long id) {
		return _socialcontentLocalService.createSocialcontent(id);
	}

	/**
	* Deletes the socialcontent with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the socialcontent
	* @throws PortalException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSocialcontent(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_socialcontentLocalService.deleteSocialcontent(id);
	}

	/**
	* Deletes the socialcontent from the database. Also notifies the appropriate model listeners.
	*
	* @param socialcontent the socialcontent
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSocialcontent(
		com.huihoo.social.model.Socialcontent socialcontent)
		throws com.liferay.portal.kernel.exception.SystemException {
		_socialcontentLocalService.deleteSocialcontent(socialcontent);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.huihoo.social.model.Socialcontent fetchSocialcontent(long id)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.fetchSocialcontent(id);
	}

	/**
	* Returns the socialcontent with the primary key.
	*
	* @param id the primary key of the socialcontent
	* @return the socialcontent
	* @throws PortalException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent getSocialcontent(long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getSocialcontent(id);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the socialcontents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @return the range of socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> getSocialcontents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getSocialcontents(start, end);
	}

	/**
	* Returns the number of socialcontents.
	*
	* @return the number of socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public int getSocialcontentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getSocialcontentsCount();
	}

	/**
	* Updates the socialcontent in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialcontent the socialcontent
	* @return the socialcontent that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent updateSocialcontent(
		com.huihoo.social.model.Socialcontent socialcontent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.updateSocialcontent(socialcontent);
	}

	/**
	* Updates the socialcontent in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param socialcontent the socialcontent
	* @param merge whether to merge the socialcontent with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the socialcontent that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent updateSocialcontent(
		com.huihoo.social.model.Socialcontent socialcontent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.updateSocialcontent(socialcontent,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _socialcontentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_socialcontentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.huihoo.social.model.Socialcontent addSocialContent(
		com.huihoo.social.model.Socialcontent newSocialcontent, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.addSocialContent(newSocialcontent,
			userId);
	}

	public com.huihoo.social.model.Socialcontent addSocialContent(
		com.huihoo.social.model.Socialcontent newSocialcontent,
		com.liferay.portal.model.User user)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.addSocialContent(newSocialcontent,
			user);
	}

	public java.util.List<com.huihoo.social.model.Socialcontent> getSocialContent(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getSocialContent(companyId, start, end);
	}

	public int getSocialCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getSocialCount(companyId);
	}

	public java.util.List<com.huihoo.social.model.Socialcontent> getUserLastSocialContent(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _socialcontentLocalService.getUserLastSocialContent(userId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SocialcontentLocalService getWrappedSocialcontentLocalService() {
		return _socialcontentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSocialcontentLocalService(
		SocialcontentLocalService socialcontentLocalService) {
		_socialcontentLocalService = socialcontentLocalService;
	}

	public SocialcontentLocalService getWrappedService() {
		return _socialcontentLocalService;
	}

	public void setWrappedService(
		SocialcontentLocalService socialcontentLocalService) {
		_socialcontentLocalService = socialcontentLocalService;
	}

	private SocialcontentLocalService _socialcontentLocalService;
}