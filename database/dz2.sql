-- Запрос 1
select * from wards;

-- Запрос 2;
select surname, phone from doctors;

-- Запрос 3;
select distinct floor from wards;

-- Запрос 4;
select 
name as "Name of Disease",
severity as "Severity Of Disease"
from diseases;

-- Запрос 5;
select d.id as derp_id,
	doc.id as doctor_id,
	w.id as ward_id
from departments as d,
	doctors as doc, 
	wards as w;

-- Запрос 6;
select name from departments
where building = 5 and financing < 60000::money;

-- Запрос 7
select name from departments
where building = 3 and financing between 12000::money and 15000::money;

-- Запрос 8
select name from wards
where building in (4, 5) and floor = 1;

-- Запрос 9
select name, building, financing from departments
where building in (3, 6) and (financing < 11000::money or financing > 25000::money);

-- Запрос 10
select surname from doctors
where premium + salary > 50000::money;

-- Запрос 11
select name, surname from doctors
where (salary::numeric / 2) > (premium::numeric * 3);

-- Запрос 12
select distinct name from examinations
where day_of_week between 1 and 3
and start_time >= '12:00'
and end_time <= '15:00';

-- Запрос 13
select name, building from departments
where building in (1, 3, 8, 10);

-- Запрос 14
select name from diseases
where severity between 3 and 5;

-- Запрос 15
select name from departments
where not building in (1, 3);

-- Запрос 16
select name from departments
where building in (1, 3);

-- Запрос 17
select surname from doctors
where surname ilike 'W%';