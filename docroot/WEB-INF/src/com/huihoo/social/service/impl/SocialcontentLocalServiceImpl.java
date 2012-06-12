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

package com.huihoo.social.service.impl;

import java.util.Date;
import java.util.List;

import com.huihoo.social.model.Socialcontent;
import com.huihoo.social.service.base.SocialcontentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

/**
 * The implementation of the socialcontent local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.huihoo.social.service.SocialcontentLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Baihua Huang
 * @see com.huihoo.social.service.base.SocialcontentLocalServiceBaseImpl
 * @see com.huihoo.social.service.SocialcontentLocalServiceUtil
 */
public class SocialcontentLocalServiceImpl extends
		SocialcontentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never reference this interface directly. Always use {@link
	 * com.huihoo.social.service.SocialcontentLocalServiceUtil} to access the
	 * socialcontent local service.
	 */

	public Socialcontent addSocialContent(Socialcontent newSocialcontent,
			long userId) throws SystemException, PortalException {
		Socialcontent sc = socialcontentLocalService
				.createSocialcontent(counterLocalService
						.increment(Socialcontent.class.getName()));

		resourceLocalService.addResources(newSocialcontent.getCompanyId(),
				newSocialcontent.getGroupId(), Socialcontent.class.getName(),
				true);

		sc.setCompanyId(newSocialcontent.getCompanyId());
		sc.setGroupId(newSocialcontent.getGroupId());
		sc.setCreatedAt(new Date());
		sc.setPortraitUrl(newSocialcontent.getPortraitUrl());
		sc.setScreenName(newSocialcontent.getScreenName());
		sc.setContent(newSocialcontent.getContent());
		sc.setType(newSocialcontent.getType());
		sc.setUserId(userId);

		return socialcontentPersistence.update(sc, false);

	}

	public Socialcontent addSocialContent(Socialcontent newSocialcontent,
			User user) throws SystemException, PortalException {
		Socialcontent sc = socialcontentLocalService
				.createSocialcontent(counterLocalService
						.increment(Socialcontent.class.getName()));

		resourceLocalService.addResources(newSocialcontent.getCompanyId(),
				newSocialcontent.getGroupId(), user.getUserId(),
				Socialcontent.class.getName(),
				newSocialcontent.getPrimaryKey(), false, true, true);

		sc.setCompanyId(newSocialcontent.getCompanyId());
		sc.setGroupId(newSocialcontent.getGroupId());
		sc.setCreatedAt(new Date());
		sc.setPortraitUrl(newSocialcontent.getPortraitUrl());
		sc.setScreenName(newSocialcontent.getScreenName());
		sc.setContent(newSocialcontent.getContent());
		sc.setType(newSocialcontent.getType());
		sc.setUserId(user.getUserId());

		return socialcontentPersistence.update(sc, false);

	}

	public List<Socialcontent> getSocialContent(long companyId, int start,
			int end) throws SystemException {
		List<Socialcontent> contents = null;
		contents = socialcontentPersistence.findBycompanyId(companyId, start,
				end);
		return contents;
	}

	public int getSocialCount(long companyId) throws SystemException {
		return socialcontentPersistence.countBycompanyId(companyId);
	}

	public List<Socialcontent> getUserLastSocialContent(long userId)
			throws SystemException {
		return socialcontentPersistence.findByuserId(userId, 0, 1);
	}

}