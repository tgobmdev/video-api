CREATE TABLE video (
	id uuid NOT NULL,
	description varchar(255) NULL,
	title varchar(255) NULL,
	url varchar(255) NULL,
	CONSTRAINT video_pkey PRIMARY KEY (id)
);