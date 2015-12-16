-- 某表'tbl'的'name'字段字符集为latin1,编码为latin1_swedish_ci

select *　from `tbl` order by birary(`name`) asc;

-- 某表'tbl'的'name'字段字符集为utf-8,编码为utf8_general_ci

select * from `tbl` order by convert(`name` using gbk) collate gbk_chinese_ci asc;