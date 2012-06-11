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

package com.huihoo.social.service.persistence;

import com.huihoo.social.model.Socialcontent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the socialcontent service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Baihua Huang
 * @see SocialcontentPersistenceImpl
 * @see SocialcontentUtil
 * @generated
 */
public interface SocialcontentPersistence extends BasePersistence<Socialcontent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SocialcontentUtil} to access the socialcontent persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the socialcontent in the entity cache if it is enabled.
	*
	* @param socialcontent the socialcontent
	*/
	public void cacheResult(com.huihoo.social.model.Socialcontent socialcontent);

	/**
	* Caches the socialcontents in the entity cache if it is enabled.
	*
	* @param socialcontents the socialcontents
	*/
	public void cacheResult(
		java.util.List<com.huihoo.social.model.Socialcontent> socialcontents);

	/**
	* Creates a new socialcontent with the primary key. Does not add the socialcontent to the database.
	*
	* @param id the primary key for the new socialcontent
	* @return the new socialcontent
	*/
	public com.huihoo.social.model.Socialcontent create(long id);

	/**
	* Removes the socialcontent with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the socialcontent
	* @return the socialcontent that was removed
	* @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent remove(long id)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.huihoo.social.model.Socialcontent updateImpl(
		com.huihoo.social.model.Socialcontent socialcontent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the socialcontent with the primary key or throws a {@link com.huihoo.social.NoSuchSocialcontentException} if it could not be found.
	*
	* @param id the primary key of the socialcontent
	* @return the socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findByPrimaryKey(long id)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the socialcontent with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the socialcontent
	* @return the socialcontent, or <code>null</code> if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent fetchByPrimaryKey(long id)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the socialcontents where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the socialcontents where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @return the range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the socialcontents where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first socialcontent in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBycompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last socialcontent in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBycompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the socialcontents before and after the current socialcontent in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current socialcontent
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent[] findBycompanyId_PrevAndNext(
		long id, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the socialcontents where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBygroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the socialcontents where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @return the range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBygroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the socialcontents where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBygroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first socialcontent in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBygroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last socialcontent in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBygroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the socialcontents before and after the current socialcontent in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current socialcontent
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent[] findBygroupId_PrevAndNext(
		long id, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the socialcontents where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyIdAndGroupId(
		long companyId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the socialcontents where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @return the range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyIdAndGroupId(
		long companyId, long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the socialcontents where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findBycompanyIdAndGroupId(
		long companyId, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBycompanyIdAndGroupId_First(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a matching socialcontent could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent findBycompanyIdAndGroupId_Last(
		long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the socialcontents before and after the current socialcontent in the ordered set where companyId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param id the primary key of the current socialcontent
	* @param companyId the company ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next socialcontent
	* @throws com.huihoo.social.NoSuchSocialcontentException if a socialcontent with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.huihoo.social.model.Socialcontent[] findBycompanyIdAndGroupId_PrevAndNext(
		long id, long companyId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.huihoo.social.NoSuchSocialcontentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the socialcontents.
	*
	* @return the socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.huihoo.social.model.Socialcontent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the socialcontents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of socialcontents
	* @param end the upper bound of the range of socialcontents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.huihoo.social.model.Socialcontent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the socialcontents where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBycompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the socialcontents where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBygroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the socialcontents where companyId = &#63; and groupId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBycompanyIdAndGroupId(long companyId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the socialcontents from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of socialcontents where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public int countBycompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of socialcontents where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public int countBygroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of socialcontents where companyId = &#63; and groupId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @return the number of matching socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public int countBycompanyIdAndGroupId(long companyId, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of socialcontents.
	*
	* @return the number of socialcontents
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}