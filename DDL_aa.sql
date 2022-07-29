



create table tb_user(

	id int ,
	username varchar(20),
	password varchar(32)

);





-- 学生表
create table tb_stu(
	id int ,-- 编号
	name varchar(10),-- 姓名
	gender char(1),-- 性别
	birthday date,-- 生日
	score double(5,2) ,-- 分数
	email varchar(64),-- 邮箱
	tel varchar(20),-- 手机号
	status tinyint-- 状态
);

--------------------------------------------------------------------------

create table tb_order(
	orderNumber int primary key auto_increment,-- 编号
	buyerId int,-- 姓名
	totalAmount double,
	status int,-- 性别
	createTime date-- 生日
	);


INSERT INTO tb_order (
	buyerId, -- 编号
	totalAmount,-- 姓名
	status,
	createTime
	)
VALUES
	(
		20016,
		1000.88,
		1,
		'2021-12-18'
	);


INSERT INTO tb_order (
	buyerId, -- 编号
	totalAmount,-- 姓名
	status,
	createTime
	)
VALUES
	(
		20017,
		1996.66,
		2,
		'2021-12-20'
	);