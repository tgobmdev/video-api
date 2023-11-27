CREATE TABLE video (
	id uuid NOT NULL,
	description varchar(255) NOT NULL,
	title varchar(255) NOT NULL,
	url varchar(255) NOT NULL,
	CONSTRAINT video_pkey PRIMARY KEY (id)
);