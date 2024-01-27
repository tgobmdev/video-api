CREATE TABLE categoria (
	id UUID NOT NULL,
	title VARCHAR(255) NOT NULL,
	color VARCHAR(255) NOT NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);

CREATE TABLE video_categoria (
	video_id UUID NOT NULL,
	categoria_id UUID NOT NULL,
	PRIMARY KEY (video_id,categoria_id)
);

ALTER TABLE video_categoria ADD CONSTRAINT categoria_fkey FOREIGN KEY (categoria_id) REFERENCES categoria;

ALTER TABLE video_categoria ADD CONSTRAINT video_fkey FOREIGN KEY (video_id) REFERENCES video;