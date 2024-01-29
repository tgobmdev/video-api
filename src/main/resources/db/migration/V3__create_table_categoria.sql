CREATE TABLE categoria (
	id BIGSERIAL NOT NULL,
	title VARCHAR(255) NOT NULL,
	color VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE video_categoria (
	video_id UUID NOT NULL,
	categoria_id BIGINT NOT NULL,
	PRIMARY KEY (video_id,categoria_id),
	FOREIGN KEY (video_id) REFERENCES video(id),
	FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);