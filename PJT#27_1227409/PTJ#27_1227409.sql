CREATE DATABASE ssafy_session;
use ssafy_sesssion;

CREATE TABLE ssafy_session(
	tour_info_nm varchar(100),
    tour_location_nm varchar(100),
    sido varchar(20),
    sigugun varchar(20),
    off_day varchar(20),
    tel varchar(15),
    addr_road varchar(50),
    addr_no varchar(50),
    ssafy_id varchar(20),
    reg_dt DATETIME DEFAULT CURRENT_TIMESTAMP
);

LOAD DATA LOCAL INFILE '/Users/hsb/Downloads/전국관광안내소표준데이터.csv' 
INTO TABLE ssafy_session
CHARACTER SET utf8
FIELDS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 2 ROWS
(tour_info_nm,tour_location_nm,sido,sigugun,off_day,tel,addr_road,addr_no);

select * from ssafy_session LIMIT 5; -- 데이터를 잘 받아왔는지 확인

UPDATE ssafy_session SET ssafy_id = 'ghkdtnql';

-- 구미 지역에는 데이터 하나만 존재
select * from ssafy_session where sigugun like '구미시';
select * from ssafy_session where addr_road like '%구미%';

select * from ssafy_session where sigugun like '구미시'
INTO OUTFILE '/Users/hsb/Desktop/sql/PJT#27_1227409.csv'
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';






