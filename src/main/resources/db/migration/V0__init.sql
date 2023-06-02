create table `tb_user` (
	`user_id`	bigint	not null	comment '유저고유번호',
	`created_datetime`	datetime	not null	comment '생성일자',
	`updated_datetime`	datetime	not null	comment '수정일자',
	`fcm_token`	text	null	comment 'fcm토큰정보',
	`user_status_code`	varchar(1)	not null	comment '회원상태(n: normal, s: secession, d: dormancy)',
	`user_img_file_id`	bigint	null	comment '프로필이미지고유번호',
	`target_trash_liter`	int	null	comment '가입 후 최초 진입 시 반드시 작성하게 유도',
	`nickname`	varchar(100)	null	comment '가입 후 최초 진입 시 반드시 작성하게 유도',
	`account_id`	varchar(100)	not null	comment '로그인 아이디'
);

create table `tb_crew` (
	`crew_id`	bigint	not null	comment '크루고유번호',
	`user_id`	bigint	not null	comment '크루마스터유저고유번호',
	`created_datetime`	datetime	not null	comment '생성일시',
	`updated_datetime`	datetime	not null	comment '수정일시',
	`crew_name`	varchar(100)	not null	comment '크루명',
	`crew_main_img_file_id`	bigint	not null	comment '크루 메인 이미지 > 없을 경우 기본 이미지 노출',
	`crew_content`	text	not null	comment '크루내용',
	`max_count`	int	not null	comment '크루 최대 인원수(맵핑 이력 내 크루 아이디 기준으로 유저수가 최대인원을 넘지 않도록 조정)'
);

create table `tb_user_crew_mapping` (
	`user_crew_mapping_id`	bigint	not null	comment '유저크루맵핑고유번호',
	`crew_id`	bigint	not null	comment '크루고유번호',
	`user_id`	bigint	not null	comment '유저고유번호',
	`created_datetime`	datetime	not null	comment '생성일시',
	`updated_datetime`	datetime	not null	comment '수정일시'
);

create table `tb_invite` (
	`invite_id`	bigint	not null	comment '초대이력고유번호',
	`crew_id`	bigint	not null	comment '초대크루고유번호',
	`sent_user_id`	bigint	not null	comment '초대발신유저고유번호',
	`sent_url`	varchar(2000)	not null	comment '발신url 정보 > 비회원의 경우 해당 비회원의 휴대폰 번호를 uri 내 기입',
	`created_datetime`	datetime	not null	comment '발신일시',
	`reception_user_id`	bigint	null	comment '초대수신유저고유번호(비회원은 없음)',
	`reception_user_fcm_token`	text	null	comment '수신유저 fcm토큰(비회원은 없음)',
	`reception_datetime`	datetime	null	comment '수신 후 진입 일시 > 비회원 url 클릭 또는 푸시 알림을 누르고 들어오는 경우 등록'
);

create table `tb_plogging` (
	`plogging_id`	bigint	not null	comment '유저활동이력고유번호',
	`approval_id`	bigint	not null	comment '인증고유번호',
	`walking_img_file_id`	bigint	not null	comment '플로깅 시 이동한 경로 이미지 고유번호',
	`walking_count`	int	not null	comment '걸음수 - 실시간 걸음수는 로컬에서 저장하고 종료 시에만 서버로 보내기',
	`trash_liter`	int	not null	comment '쓰레기 수집 용량(종량제 봉투 리터수 * 채운 종량제 퍼센트)',
	`exercise_distance`	int	not null	comment '움직인 운동거리',
	`work_type_code`	varchar(1)	not null	comment '활동타입코드(s:솔로플레이, c: crew 활동)',
	`start_datetime`	datetime	not null	comment '시작일시',
	`end_datetime`	datetime	null	comment '종료일시'
);

create table `tb_file` (
	`file_id`	bigint	not null	comment '파일고유번호',
	`origin_name`	varchar(100)	not null	comment '유저가 저장한 파일명',
	`base_url`	varchar(2000)	not null	comment '기본 base url',
	`file_path`	varchar(2000)	not null	comment '저장 디렉토리 경로 정보',
	`save_name`	varchar(100)	not null	comment '실제 저장된 파일명',
	`file_extension`	varchar(50)	not null	comment '해당 파일의 확장자',
	`created_datetime`	datetime	not null	comment '생성일시',
	`updated_datetime`	datetime	not null	comment '수정일시'
);

create table `tb_approval` (
	`approval_id`	bigint	not null	comment '인증고유번호',
	`user_id`	bigint	not null	comment '유저고유번호',
	`approval_img_file_id`	bigint	not null	comment '인증이미지고유번호',
	`approval_datetime`	datetime	not null	comment '인증완료시간',
	`rubbish_bag_liter`	int	not null	comment '인증 종량제 봉투 리터수',
	`garbage_pickup_capacity_percentage_type_code`	int	not null	comment '종량제 봉투를 채운 %(0, 25, 50, 75, 100) 총 5단계'
);

create table `tb_crew_feed` (
	`crew_feed_id`	bigint	not null	comment '크루피드고유번호',
	`crew_id`	bigint	not null	comment '크루고유번호',
	`user_id`	bigint	not null	comment '유저고유번호',
	`feed_title`	varchar(255)	not null	comment '피드제목',
	`feed_content`	text	not null	comment '피드내용',
	`created_datetime`	datetime	not null	comment '생성일시',
	`updated_datetime`	datetime	not null	comment '수정일시',
	`is_announcement`	varchar(1)	not null	comment '공지사항여부(y: 공지사항 - 상단고정, n: 일반게시글'
);

create table `tb_crew_feed_img_mapping` (
	`crew_feed_id`	bigint	not null	comment '크루피드고유번호',
	`file_id`	bigint	not null	comment '파일고유번호',
	`user_id`	bigint	not null	comment '유저고유번호'
);

create table `tb_crew_hash_tag` (
	`crew_id`	bigint	not null	comment '크루고유번호',
	`hash_tag_name`	varchar(100)	not null	comment '해쉬태그명'
);

alter table `tb_user` add constraint `pk_tb_user` primary key (
	`user_id`
);

alter table `tb_crew` add constraint `pk_tb_crew` primary key (
	`crew_id`,
	`user_id`
);

alter table `tb_user_crew_mapping` add constraint `pk_tb_user_crew_mapping` primary key (
	`user_crew_mapping_id`,
	`crew_id`,
	`user_id`
);

alter table `tb_invite` add constraint `pk_tb_invite` primary key (
	`invite_id`
);

alter table `tb_plogging` add constraint `pk_tb_plogging` primary key (
	`plogging_id`,
	`approval_id`,
	`walking_img_file_id`
);

alter table `tb_file` add constraint `pk_tb_file` primary key (
	`file_id`
);


alter table `tb_approval` add constraint `pk_tb_approval` primary key (
	`approval_id`,
	`user_id`,
	`approval_img_file_id`
);

alter table `tb_crew_feed` add constraint `pk_tb_crew_feed` primary key (
	`crew_feed_id`,
	`crew_id`,
	`user_id`
);

alter table `tb_crew_feed_img_mapping` add constraint `pk_tb_crew_feed_img_mapping` primary key (
	`crew_feed_id`,
	`file_id`,
	`user_id`
);

alter table `tb_crew_hash_tag` add constraint `pk_tb_crew_hash_tag` primary key (
	`crew_id`
);

alter table `tb_user` add constraint `fk_tb_file_to_tb_user_1` foreign key (
	`user_img_file_id`
)
references `tb_file` (
	`file_id`
);

alter table `tb_crew` add constraint `fk_tb_user_to_tb_crew_1` foreign key (
	`user_id`
)
references `tb_user` (
	`user_id`
);

alter table `tb_crew` add constraint `fk_tb_file_to_tb_crew_1` foreign key (
	`crew_main_img_file_id`
)
references `tb_file` (
	`file_id`
);

alter table `tb_user_crew_mapping` add constraint `fk_tb_crew_to_tb_user_crew_mapping_1` foreign key (
	`crew_id`
)
references `tb_crew` (
	`crew_id`
);

alter table `tb_user_crew_mapping` add constraint `fk_tb_user_to_tb_user_crew_mapping_1` foreign key (
	`user_id`
)
references `tb_user` (
	`user_id`
);

alter table `tb_plogging` add constraint `fk_tb_approval_to_tb_plogging_1` foreign key (
	`approval_id`
)
references `tb_approval` (
	`approval_id`
);

alter table `tb_plogging` add constraint `fk_tb_file_to_tb_plogging_1` foreign key (
	`walking_img_file_id`
)
references `tb_file` (
	`file_id`
);

alter table `tb_approval` add constraint `fk_tb_user_to_tb_approval_1` foreign key (
	`user_id`
)
references `tb_user` (
	`user_id`
);

alter table `tb_approval` add constraint `fk_tb_file_to_tb_approval_1` foreign key (
	`approval_img_file_id`
)
references `tb_file` (
	`file_id`
);

alter table `tb_crew_feed` add constraint `fk_tb_crew_to_tb_crew_feed_1` foreign key (
	`crew_id`
)
references `tb_crew` (
	`crew_id`
);

alter table `tb_crew_feed` add constraint `fk_tb_crew_to_tb_crew_feed_2` foreign key (
	`user_id`
)
references `tb_crew` (
	`user_id`
);

alter table `tb_crew_feed_img_mapping` add constraint `fk_tb_crew_feed_to_tb_crew_feed_img_mapping_1` foreign key (
	`crew_feed_id`
)
references `tb_crew_feed` (
	`crew_feed_id`
);

alter table `tb_crew_feed_img_mapping` add constraint `fk_tb_crew_feed_to_tb_crew_feed_img_mapping_2` foreign key (
	`user_id`
)
references `tb_crew_feed` (
	`user_id`
);

alter table `tb_crew_feed_img_mapping` add constraint `fk_tb_file_to_tb_crew_feed_img_mapping_1` foreign key (
	`file_id`
)
references `tb_file` (
	`file_id`
);

alter table `tb_crew_hash_tag` add constraint `fk_tb_crew_to_tb_crew_hash_tag_1` foreign key (
	`crew_id`
)
references `tb_crew` (
	`crew_id`
);

