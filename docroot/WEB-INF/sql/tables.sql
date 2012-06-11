create table huihoo_app_socialcontent (
	id_ LONG not null primary key,
	userId LONG,
	portraitUrl VARCHAR(75) null,
	screenName VARCHAR(75) null,
	companyId LONG,
	groupId LONG,
	type_ INTEGER,
	content VARCHAR(75) null,
	createdAt DATE null
);