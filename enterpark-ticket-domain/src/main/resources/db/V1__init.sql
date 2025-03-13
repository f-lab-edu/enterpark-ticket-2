CREATE TABLE IF NOT EXISTS user
(
    user_id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 id',
    oid                BIGINT       NOT NULL COMMENT 'OAuth2 고유 id',
    provider           VARCHAR(15)  NOT NULL COMMENT 'OAuth2 공급자',
    name               VARCHAR(20)  NOT NULL COMMENT '이름',
    email              VARCHAR(255) NOT NULL COMMENT '이메일',
    phone_number       VARCHAR(20)  NOT NULL COMMENT '전화번호',
    birth_date         DATE         NOT NULL COMMENT '생년월일',
    gender             VARCHAR(15)  NOT NULL COMMENT '성별',
    address            VARCHAR(255) NULL COMMENT '주소',
    role               VARCHAR(15)  NOT NULL COMMENT '역할',
    state              VARCHAR(15)  NOT NULL COMMENT '상태',
    created_date       TIMESTAMP(6) NULL COMMENT '생성일',
    last_modified_date TIMESTAMP(6) NULL COMMENT '수정일',
    deleted_date       TIMESTAMP(6) NULL COMMENT '삭제일',
    UNIQUE uk_user_provider_user_id (provider, user_id)
);
