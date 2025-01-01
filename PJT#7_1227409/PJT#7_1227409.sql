SHOW VARIABLES LIKE 'event%'; -- off일 경우 on 설정
SET GLOBAL event_scheduler = ON;

SELECT * from information_schema.EVENTS;

CREATE DATABASE ssafy_event;
use ssafy_event;

CREATE TABLE ssafy_event(
	ssafy_id varchar(20),
    ssafy_age INT
);

select * from ssafy_event;

use ssafy_event;
-- 1분마다 데이터가 등록되는 Event Scheduler 작성 (데이터가 20개가 되면 이벤트 중지)
DELIMITER //
CREATE EVENT evt_per_minute_data_insert
ON SCHEDULE EVERY 1 MINUTE
DO
BEGIN
	DECLARE cnt INT;
    SELECT count(*) into cnt from ssafy_event;
    
    if cnt < 20 THEN
		INSERT INTO ssafy_event (ssafy_id, ssafy_age) VALUES (CONCAT ('user_' , FLOOR(RAND()*1000)), 24);
	END IF;
    
END //
DELIMITER ;

-- 현재 시각으로부터 5분 후 모든 데이터를 삭제하는 Event Scheduler 작성
DELIMITER //
CREATE EVENT evt_delete_all_ssafy_data
ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 5 MINUTE
DO
BEGIN
    DELETE FROM ssafy_event;
END //
DELIMITER ;

-- 특정 기간 동안만 반복 실행되는 Event Scheduler 작성
DELIMITER //
CREATE EVENT evt_fixed_period_insert
ON SCHEDULE EVERY 1 MINUTE
STARTS CURRENT_TIMESTAMP
ENDS CURRENT_TIMESTAMP + INTERVAL 1 MINUTE  -- 1분 동안만 실행
DO
BEGIN
    INSERT INTO ssafy_event (ssafy_id, ssafy_age) 
    VALUES ('ghkdtnql', 24);
END //
DELIMITER ;

select * from ssafy_event;

