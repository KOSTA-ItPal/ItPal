INSERT INTO `itpal`.`user` (`user_id`, `user_name`, `user_pwd`, `phone_num`, `email`, `birthday`, `budget_set`, `save_set`)
VALUES
('user1', 'Kim Minji', 'pass1', '010-1234-5678', 'minji.kim@test.com', '1992-05-15', 0, 0);

//계좌 정보
INSERT INTO `itpal`.`account` (`account_num`, `account_type`, `bank_name`, `balance`, `User_user_id`)
VALUES
('110-1234-567890', 'checking', 'KB국민은행', 1500000, 'user1'),
('020-3456-789012', 'savings', '우리은행', 5000000, 'user1'),
('356-9876-543210', 'checking', '신한은행', 2500000, 'user1'),
('010-5678-123456', 'savings', '하나은행', 1000000, 'user1');

select * from account;

//결제내역 (3456-7890-1121-1314,5678-9012-1314-1516,4567-8901-1213-1415)
INSERT INTO `itpal`.`payment` (`date`, `card_num`, `store`, `pay`, `category`, `User_user_id`)
VALUES
-- 7월 사용 내역
('2024-07-02', '3456-7890-1121-1314', 'Starbucks', 5200, 'cafe', 'user1'),
('2024-07-12', '5678-9012-1314-1516', 'Daiso', 12000, 'shopping', 'user1'),
('2024-07-18', '5678-9012-1314-1516', 'CJ대한통운', 55000, 'shopping', 'user1'),
('2024-07-22', '4567-8901-1213-1415', 'KTX', 46000, 'transport', 'user1'),

-- 8월 사용 내역
('2024-08-05', '3456-7890-1121-1314', '파리바게트', 8200, 'cafe', 'user1'),
('2024-08-11', '5678-9012-1314-1516', 'Olive Young', 27000, 'shopping', 'user1'),
('2024-08-18', '5678-9012-1314-1516', '롯데백화점', 155000, 'shopping', 'user1'),

-- 9월 사용 내역
('2024-09-03', '5678-9012-1314-1516', '스타벅스', 4900, 'cafe', 'user1'),
('2024-09-09', '3456-7890-1121-1314', '쿠팡', 64000, 'shopping', 'user1'),
('2024-09-15', '5678-9012-1314-1516', 'KTX', 47000, 'transport', 'user1'),

-- 10월 사용 내역
('2024-10-06', '5678-9012-1314-1516', '투썸플레이스', 8300, 'cafe', 'user1'),
('2024-10-12', '5678-9012-1314-1516', 'ABC Mart', 45000, 'shopping', 'user1'),
('2024-10-22', '4567-8901-1213-1415', '메가박스', 13000, 'culture', 'user1'),

-- 11월 사용 내역
('2024-11-04', '5678-9012-1314-1516', '스타벅스', 5700, 'cafe', 'user1'),
('2024-11-08', '3456-7890-1121-1314', 'G마켓', 35000, 'shopping', 'user1'),
('2024-11-15', '5678-9012-1314-1516', 'KTX', 49000, 'transport', 'user1'),

-- 12월 사용 내역
('2024-12-03', '3456-7890-1121-1314', '투썸플레이스', 8900, 'cafe', 'user1'),
('2024-12-09', '5678-9012-1314-1516', '홈플러스', 82000, 'shopping', 'user1'),
('2024-12-20', '5678-9012-1314-1516', 'KTX', 45000, 'transport', 'user1'),
('2024-12-30', '4567-8901-1213-1415', 'CU 편의점', 6400, 'cvs', 'user1');
/*3456-7890-1121-1314: 6회 사용 (비율 2)
5678-9012-1314-1516: 12회 사용 (비율 4)
4567-8901-1213-1415: 3회 사용 (비율 1)*/

INSERT INTO `itpal`.`payment` (`date`, `card_num`, `store`, `pay`, `category`, `User_user_id`)
VALUES
-- 1월 사용 내역
('2024-01-05', '5678-9012-1314-1516', '스타벅스', 4500, 'cafe', 'user1'),
('2024-01-12', '5678-9012-1314-1516', '올리브영', 32000, 'shopping', 'user1'),
('2024-01-18', '4567-8901-1213-1415', 'CGV', 12000, 'culture', 'user1'),

-- 2월 사용 내역
('2024-02-02', '3456-7890-1121-1314', '투썸플레이스', 7800, 'cafe', 'user1'),
('2024-02-10', '5678-9012-1314-1516', '쿠팡', 64000, 'shopping', 'user1'),
('2024-02-14', '5678-9012-1314-1516', 'KTX', 48000, 'transport', 'user1'),

-- 3월 사용 내역
('2024-03-03', '5678-9012-1314-1516', '파리바게트', 8200, 'cafe', 'user1'),
('2024-03-15', '3456-7890-1121-1314', 'G마켓', 42000, 'shopping', 'user1'),
('2024-03-22', '5678-9012-1314-1516', '홈플러스', 78000, 'shopping', 'user1'),

-- 4월 사용 내역
('2024-04-05', '4567-8901-1213-1415', '메가박스', 13500, 'culture', 'user1'),
('2024-04-11', '5678-9012-1314-1516', '지하철', 1250, 'transport', 'user1'),
('2024-04-23', '5678-9012-1314-1516', '롯데백화점', 98000, 'shopping', 'user1'),

-- 5월 사용 내역
('2024-05-06', '3456-7890-1121-1314', '투썸플레이스', 6700, 'cafe', 'user1'),
('2024-05-14', '5678-9012-1314-1516', '지하철', 1250, 'transport', 'user1'),
('2024-05-26', '5678-9012-1314-1516', '스타벅스', 5900, 'cafe', 'user1'),

-- 6월 사용 내역
('2024-06-03', '3456-7890-1121-1314', 'G마켓', 51000, 'shopping', 'user1'),
('2024-06-10', '5678-9012-1314-1516', 'KTX', 49000, 'transport', 'user1'),
('2024-06-19', '4567-8901-1213-1415', 'CU 편의점', 7500, 'cvs', 'user1');
INSERT INTO `itpal`.`payment` (`date`, `card_num`, `store`, `pay`, `category`, `User_user_id`)
VALUES
-- 3456-7890-1121-1314 카드 내역 (주로 카페, 쇼핑 및 통신비)
('2024-04-01', '3456-7890-1121-1314', '스타벅스', 5100, 'cafe', 'user1'),
('2024-04-08', '3456-7890-1121-1314', '파리바게트', 8700, 'cafe', 'user1'),
('2024-04-12', '3456-7890-1121-1314', 'KT', 55000, 'mobile', 'user1'),  -- 통신비 결제
('2024-04-20', '3456-7890-1121-1314', 'G마켓', 45000, 'shopping', 'user1'),
('2024-04-25', '3456-7890-1121-1314', 'GS25', 7200, 'cvs', 'user1'),

-- 5678-9012-1314-1516 카드 내역 (쇼핑, 교통, 여행 및 OTT 결제)
('2024-04-03', '5678-9012-1314-1516', '롯데백화점', 165000, 'shopping', 'user1'),
('2024-04-10', '5678-9012-1314-1516', 'KTX', 47000, 'transport', 'user1'),
('2024-04-15', '5678-9012-1314-1516', '넷플릭스', 12000, 'digital_content', 'user1'),  -- OTT 결제
('2024-04-22', '5678-9012-1314-1516', 'CJ대한통운', 8000, 'shopping', 'user1'),
('2024-04-28', '5678-9012-1314-1516', '제주항공', 125000, 'travel', 'user1'),  -- 비행기 예약
('2024-05-05', '5678-9012-1314-1516', '호텔신라', 210000, 'travel', 'user1'),  -- 호텔 예약

-- 4567-8901-1213-1415 카드 내역 (교통, 문화, 여행 및 통신비)
('2024-04-07', '4567-8901-1213-1415', 'KTX', 46000, 'transport', 'user1'),
('2024-04-15', '4567-8901-1213-1415', '메가박스', 13000, 'culture', 'user1'),
('2024-04-20', '4567-8901-1213-1415', 'LG U+', 45000, 'mobile', 'user1'),  -- 통신비 결제
('2024-04-26', '4567-8901-1213-1415', 'CGV', 15000, 'culture', 'user1'),
('2024-05-01', '4567-8901-1213-1415', '아시아나항공', 180000, 'travel', 'user1'),  -- 비행기 예약
('2024-05-10', '4567-8901-1213-1415', 'CGV', 17000, 'culture', 'user1');