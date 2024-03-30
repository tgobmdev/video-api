CREATE TABLE category (
	id SERIAL NOT NULL,
	title VARCHAR(255) NOT NULL,
	color VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE video_category (
	video_id UUID NOT NULL,
	category_id BIGINT NOT NULL,
	PRIMARY KEY (video_id,category_id),
	FOREIGN KEY (video_id) REFERENCES video(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
);