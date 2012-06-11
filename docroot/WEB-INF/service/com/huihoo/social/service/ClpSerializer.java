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

import com.huihoo.social.model.SocialcontentClp;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"huihoo-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"huihoo-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "huihoo-portlet";
			}

			return _servletContextName;
		}
	}

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(SocialcontentClp.class.getName())) {
			return translateInputSocialcontent(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputSocialcontent(BaseModel<?> oldModel) {
		SocialcontentClp oldCplModel = (SocialcontentClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.huihoo.social.model.impl.SocialcontentImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getUserId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setPortraitUrl",
						new Class[] { String.class });

				String value2 = oldCplModel.getPortraitUrl();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setScreenName",
						new Class[] { String.class });

				String value3 = oldCplModel.getScreenName();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value4 = new Long(oldCplModel.getCompanyId());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value5 = new Long(oldCplModel.getGroupId());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setType",
						new Class[] { Integer.TYPE });

				Integer value6 = new Integer(oldCplModel.getType());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setContent",
						new Class[] { String.class });

				String value7 = oldCplModel.getContent();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setCreatedAt",
						new Class[] { Date.class });

				Date value8 = oldCplModel.getCreatedAt();

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.huihoo.social.model.impl.SocialcontentImpl")) {
			return translateOutputSocialcontent(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutputSocialcontent(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				SocialcontentClp newModel = new SocialcontentClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setId(value0);

				Method method1 = oldModelClass.getMethod("getUserId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setUserId(value1);

				Method method2 = oldModelClass.getMethod("getPortraitUrl");

				String value2 = (String)method2.invoke(oldModel, (Object[])null);

				newModel.setPortraitUrl(value2);

				Method method3 = oldModelClass.getMethod("getScreenName");

				String value3 = (String)method3.invoke(oldModel, (Object[])null);

				newModel.setScreenName(value3);

				Method method4 = oldModelClass.getMethod("getCompanyId");

				Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value4);

				Method method5 = oldModelClass.getMethod("getGroupId");

				Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value5);

				Method method6 = oldModelClass.getMethod("getType");

				Integer value6 = (Integer)method6.invoke(oldModel,
						(Object[])null);

				newModel.setType(value6);

				Method method7 = oldModelClass.getMethod("getContent");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setContent(value7);

				Method method8 = oldModelClass.getMethod("getCreatedAt");

				Date value8 = (Date)method8.invoke(oldModel, (Object[])null);

				newModel.setCreatedAt(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}