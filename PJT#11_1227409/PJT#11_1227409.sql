-- 데이터베이스 생성 (UTF8MB4 문자셋 지정)
CREATE DATABASE ssafy_1227409;
USE ssafy_1227409;

-- 테이블 생성 (UTF8MB4 문자셋 지정)
CREATE TABLE ssafy_1227409 (
   car_test_nm VARCHAR(100),
   addr_road VARCHAR(200),
   x DECIMAL(11,8),
   y DECIMAL(11,7),
   tel VARCHAR(20),
   tester INT,
   work_time VARCHAR(100),
   agency_cd VARCHAR(20),
   agency_nm VARCHAR(100),
   ssafy_id VARCHAR(50),
   reg_dt VARCHAR(20)
);

-- 로컬 파일 불러오기 기능 활성화
SET GLOBAL local_infile = 1;

LOAD DATA LOCAL INFILE '/Users/hsb/Downloads/전국자동차검사소표준데이터.csv'
INTO TABLE ssafy_1227409
CHARACTER SET utf8
FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(car_test_nm, addr_road, x, y, tel, work_time, tester, reg_dt, agency_cd, agency_nm);

-- 데이터 확인
SELECT * FROM ssafy_1227409 LIMIT 5;

-- SSAFY ID 입력
UPDATE ssafy_1227409 
SET ssafy_id = 'ghkdtnql';

-- 자동차검사소명 데이터 정제 (특수문자 제거)
UPDATE ssafy_1227409 
SET car_test_nm = REGEXP_REPLACE(car_test_nm, '[^가-힣a-zA-Z0-9]', '');

-- 자동차검사소명 데이터 정제 (공백 제거)
UPDATE ssafy_1227409 
SET car_test_nm = REPLACE(car_test_nm, ' ', '');

-- 위도, 경도 데이터 정제 (소수점 2자리로 반올림)
UPDATE ssafy_1227409
SET x = ROUND(x, 2),
    y = ROUND(y, 2);
    
ALTER TABLE ssafy_1227409
MODIFY COLUMN x DECIMAL(11,2),
MODIFY COLUMN y DECIMAL(11,2);
    
-- 검사소 인력수 결측치 처리
UPDATE ssafy_1227409 
SET tester = 0 
WHERE tester IS NULL;

-- 데이터 확인
SELECT * FROM ssafy_1227409 LIMIT 5;

SELECT * 
FROM ssafy_1227409;
-- WHERE car_test_nm LIKE '%청림%';

-- 파일을 내보내기 할 수 있는지 확인 후 null이면 설정파일에서 값 변경
SHOW VARIABLES LIKE 'secure_file_priv';

-- 구미지역 검사소 데이터 추출하여 CSV 파일로 저장
SELECT car_test_nm, addr_road, x, y, tel, tester, work_time, agency_cd, agency_nm, ssafy_id, reg_dt
FROM ssafy_1227409
WHERE addr_road LIKE '%구미%'
INTO OUTFILE '/Users/hsb/Desktop/sql/PJT#11_1227409.csv'
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n';
